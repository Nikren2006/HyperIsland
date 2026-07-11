// Liquid-glass refraction shader for the Dynamic Island.
//
// Used by `LiquidGlassIsland` through `ImageFilter.shader`. Flutter's
// `BackdropFilter` automatically binds the backdrop to `u_texture` (sampler 0)
// and the filtered size to `u_size` (vec2, float indices 0..1). Our custom
// float uniforms therefore start at index 2, which is exactly what
// `_LiquidGlassIslandState._applyUniforms` assumes:
//   u_enabled            2
//   u_refractionHeight   3
//   u_refractionAmount   4
//   u_cornerRadii (vec4) 5..8
//   u_depthEffect        9
//   u_chromaticAberration 10
//   u_saturation         11
//   u_vibrancy           12
//   u_tintColor (vec4)   13..16
//   u_tintOpacity        17
//
// The refraction math is adapted from Kyant0/AndroidLiquidGlass (Apache 2.0),
// the same source used by the reference `Lens` in manager.zip.

// NOTE: `u_texture` and `u_size` are provided by the engine — do not declare.

uniform float u_enabled;
uniform float u_refractionHeight;
uniform float u_refractionAmount;
uniform float4 u_cornerRadii;
uniform float u_depthEffect;
uniform float u_chromaticAberration;
uniform float u_saturation;
uniform float u_vibrancy;
uniform float4 u_tintColor;
uniform float u_tintOpacity;

float radiusAt(float2 coord, float4 radii) {
    if (coord.x >= 0.0) {
        if (coord.y <= 0.0) return radii.y;
        else return radii.z;
    } else {
        if (coord.y <= 0.0) return radii.x;
        else return radii.w;
    }
}

float sdRoundedRect(float2 coord, float2 halfSize, float radius) {
    float2 cornerCoord = abs(coord) - (halfSize - float2(radius));
    float outside = length(max(cornerCoord, 0.0)) - radius;
    float inside = min(max(cornerCoord.x, cornerCoord.y), 0.0);
    return outside + inside;
}

float2 gradSdRoundedRect(float2 coord, float2 halfSize, float radius) {
    float2 cornerCoord = abs(coord) - (halfSize - float2(radius));
    if (cornerCoord.x >= 0.0 || cornerCoord.y >= 0.0) {
        return sign(coord) * normalize(max(cornerCoord, 0.0));
    } else {
        float gradX = step(cornerCoord.y, cornerCoord.x);
        return sign(coord) * float2(gradX, 1.0 - gradX);
    }
}

float circleMap(float x) {
    return 1.0 - sqrt(1.0 - x * x);
}

float3 applyVibrancy(float3 color, float vibrancy) {
    float l = dot(color, float3(0.2126, 0.7152, 0.0722));
    return mix(float3(l), color, vibrancy);
}

half4 main(float2 coord) {
    if (u_enabled < 0.5) {
        return u_texture.eval(coord);
    }

    float2 halfSize = u_size * 0.5;
    float2 centeredCoord = coord - halfSize;
    float radius = radiusAt(coord, u_cornerRadii);

    float sd = sdRoundedRect(centeredCoord, halfSize, radius);
    if (-sd >= u_refractionHeight) {
        return u_texture.eval(coord);
    }
    sd = min(sd, 0.0);

    float d = circleMap(1.0 - (-sd) / u_refractionHeight) * u_refractionAmount;
    float gradRadius = min(radius * 1.5, min(halfSize.x, halfSize.y));
    float2 grad = normalize(
        gradSdRoundedRect(centeredCoord, halfSize, gradRadius) +
        u_depthEffect * normalize(centeredCoord),
    );

    float2 refractedCoord = coord + d * grad;

    half4 color;
    if (u_chromaticAberration > 0.0) {
        float dispersion = u_chromaticAberration * ((-sd) / u_refractionHeight);
        float2 off = grad * dispersion;
        color.r = u_texture.eval(refractedCoord + off).r;
        color.g = u_texture.eval(refractedCoord).g;
        color.b = u_texture.eval(refractedCoord - off).b;
        color.a = u_texture.eval(refractedCoord).a;
    } else {
        color = u_texture.eval(refractedCoord);
    }

    color.rgb = applyVibrancy(color.rgb, u_vibrancy * u_saturation);

    if (u_tintOpacity > 0.0) {
        color.rgb = mix(color.rgb, u_tintColor.rgb, u_tintOpacity * u_tintColor.a);
    }

    return color;
}
