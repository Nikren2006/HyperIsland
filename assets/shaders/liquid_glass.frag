#include <flutter/runtime_effect.glsl>

// First float uniform is reserved by the engine: it is set to the size of the
// bound (backdrop) texture. The first sampler2D is set to that backdrop image.
uniform vec2 u_size;
uniform sampler2D u_texture;

// --- configurable liquid-glass uniforms (set from Dart) ---
uniform float u_enabled;           // 0 = pass-through, 1 = glass
uniform float u_refractionHeight;  // dp (scaled to px): edge band thickness
uniform float u_refractionAmount;  // dp (scaled to px): max displacement
uniform vec4 u_cornerRadii;        // tl, tr, br, bl (px)
uniform float u_depthEffect;       // 0..1 convex lens strength
uniform float u_chromaticAberration;
uniform float u_saturation;        // vibrancy (color)
uniform float u_vibrancy;          // vibrancy (brightness)
uniform vec4 u_tintColor;          // rgb + alpha
uniform float u_tintOpacity;       // 0..1 blend strength

out vec4 fragColor;

const float kEps = 1e-6;

float radiusAt(vec2 coord, vec4 radii) {
  if (coord.x >= 0.0) {
    if (coord.y <= 0.0) return radii.y;
    else return radii.z;
  } else {
    if (coord.y <= 0.0) return radii.x;
    else return radii.w;
  }
}

float sdRoundedRect(vec2 coord, vec2 halfSize, float radius) {
  vec2 cornerCoord = abs(coord) - (halfSize - vec2(radius));
  float outside = length(max(cornerCoord, 0.0)) - radius;
  float inside = min(max(cornerCoord.x, cornerCoord.y), 0.0);
  return outside + inside;
}

vec2 gradSdRoundedRect(vec2 coord, vec2 halfSize, float radius) {
  vec2 cornerCoord = abs(coord) - (halfSize - vec2(radius));
  if (cornerCoord.x >= 0.0 || cornerCoord.y >= 0.0) {
    return sign(coord) * normalize(max(cornerCoord, 0.0) + kEps);
  } else {
    float gradX = step(cornerCoord.y, cornerCoord.x);
    return sign(coord) * vec2(gradX, 1.0 - gradX);
  }
}

float circleMap(float x) {
  return 1.0 - sqrt(max(1.0 - x * x, 0.0));
}

vec4 sampleBackdrop(vec2 coord) {
  vec2 uv = coord / u_size;
#ifdef IMPELLER_TARGET_OPENGLES
  uv.y = 1.0 - uv.y;
#endif
  return texture(u_texture, uv);
}

void main() {
  vec2 coord = FlutterFragCoord().xy;
  vec2 halfSize = u_size * 0.5;
  vec2 centeredCoord = coord - halfSize;
  float radius = radiusAt(coord, u_cornerRadii);

  vec4 backdrop = sampleBackdrop(coord);

  if (u_enabled < 0.5) {
    fragColor = backdrop;
    return;
  }

  float sd = sdRoundedRect(centeredCoord, halfSize, radius);
  if (-sd >= u_refractionHeight) {
    fragColor = backdrop;
    return;
  }
  sd = min(sd, 0.0);

  float d = circleMap(1.0 - (-sd) / max(u_refractionHeight, kEps)) *
      u_refractionAmount;
  float gradRadius = min(radius * 1.5, min(halfSize.x, halfSize.y));
  vec2 grad = normalize(
    gradSdRoundedRect(centeredCoord, halfSize, gradRadius) +
        u_depthEffect * normalize(centeredCoord + kEps)
  );
  vec2 refractedCoord = coord + d * grad;

  vec4 color;
  if (u_chromaticAberration > 0.0) {
    float dispersionIntensity = u_chromaticAberration *
        ((centeredCoord.x * centeredCoord.y) /
            max(halfSize.x * halfSize.y, kEps));
    vec2 dispersed = d * grad * dispersionIntensity;
    vec4 rc = sampleBackdrop(refractedCoord + dispersed);
    vec4 gc = sampleBackdrop(refractedCoord);
    vec4 bc = sampleBackdrop(refractedCoord - dispersed);
    color = vec4(rc.r, gc.g, bc.b, max(max(rc.a, gc.a), bc.a));
  } else {
    color = sampleBackdrop(refractedCoord);
  }

  // vibrancy: saturation + brightness
  float luma = dot(color.rgb, vec3(0.2126, 0.7152, 0.0722));
  color.rgb = mix(vec3(luma), color.rgb, u_saturation) * u_vibrancy;

  // tint (blend shade) — mirrors the MIUI "blend shade color" glass layer
  float tOpacity = u_tintOpacity * u_tintColor.a;
  if (tOpacity > 0.0) {
    color.rgb = mix(color.rgb, u_tintColor.rgb, tOpacity);
  }

  fragColor = color;
}
