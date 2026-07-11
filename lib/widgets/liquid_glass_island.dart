import 'dart:async';
import 'dart:math' as math;
import 'dart:ui';

import 'package:flutter/material.dart';

/// Tunable parameters for the liquid-glass effect (all sizes in logical dp).
///
/// Mirrors the layered MIUI "dynamic island" glass stack:
///   - backdrop refraction (blur + displacement)  -> [refractionAmount]/[refractionHeight]
///   - vibrancy / tint (blend shade)              -> [saturation]/[vibrancy]/[tintColor]
///   - edge highlight (inner shadow / stroke)     -> [innerShadow]/[innerShadowRadius]
class LiquidGlassConfig {
  const LiquidGlassConfig({
    this.enabled = true,
    this.refractionAmount = 14.0,
    this.refractionHeight = 24.0,
    this.cornerRadius = 26.0,
    this.depthEffect = 0.35,
    this.saturation = 1.4,
    this.vibrancy = 1.06,
    this.chromaticAberration = 0.0,
    this.tintColor = 0xFFFFFFFF,
    this.tintOpacity = 0.0,
    this.innerShadow = true,
    this.innerShadowRadius = 10.0,
  });

  final bool enabled;
  final double refractionAmount;
  final double refractionHeight;
  final double cornerRadius;
  final double depthEffect;
  final double saturation;
  final double vibrancy;
  final double chromaticAberration;
  final int tintColor;
  final double tintOpacity;
  final bool innerShadow;
  final double innerShadowRadius;

  LiquidGlassConfig copyWith({
    bool? enabled,
    double? refractionAmount,
    double? refractionHeight,
    double? cornerRadius,
    double? depthEffect,
    double? saturation,
    double? vibrancy,
    double? chromaticAberration,
    int? tintColor,
    double? tintOpacity,
    bool? innerShadow,
    double? innerShadowRadius,
  }) {
    return LiquidGlassConfig(
      enabled: enabled ?? this.enabled,
      refractionAmount: refractionAmount ?? this.refractionAmount,
      refractionHeight: refractionHeight ?? this.refractionHeight,
      cornerRadius: cornerRadius ?? this.cornerRadius,
      depthEffect: depthEffect ?? this.depthEffect,
      saturation: saturation ?? this.saturation,
      vibrancy: vibrancy ?? this.vibrancy,
      chromaticAberration: chromaticAberration ?? this.chromaticAberration,
      tintColor: tintColor ?? this.tintColor,
      tintOpacity: tintOpacity ?? this.tintOpacity,
      innerShadow: innerShadow ?? this.innerShadow,
      innerShadowRadius: innerShadowRadius ?? this.innerShadowRadius,
    );
  }

  static const LiquidGlassConfig defaults = LiquidGlassConfig();
}

/// Loads and caches the AGSL/FLSL refraction program used by [LiquidGlassIsland].
class LiquidGlassShader {
  LiquidGlassShader._();

  static FragmentProgram? _program;
  static final Completer<FragmentProgram?> _completer =
      Completer<FragmentProgram?>();
  static bool _scheduled = false;

  static bool get supported => ImageFilter.isShaderFilterSupported;

  /// Loads the program once and caches it for the whole app.
  static Future<FragmentProgram?> load() {
    if (_program != null) return Future<FragmentProgram?>.value(_program);
    if (_completer.isCompleted) return _completer.future;
    if (!_scheduled) {
      _scheduled = true;
      scheduleMicrotask(_loadAsync);
    }
    return _completer.future;
  }

  static void _loadAsync() async {
    try {
      if (!supported) {
        _completer.complete(null);
        return;
      }
      final program =
          await FragmentProgram.fromAsset('assets/shaders/liquid_glass.frag');
      _program = program;
      _completer.complete(program);
    } catch (e) {
      _completer.complete(null);
    }
  }
}

/// A configurable, shader-based "liquid glass" surface for the Dynamic Island.
///
/// Layered exactly like the MIUI island background, but with the opaque black
/// fill removed so the refracted backdrop shows through:
///
///   1. [glass layer]  - an inner [ClipRRect] holding a [BackdropFilter] whose
///      custom shader refracts + tints the backdrop behind the island.
///   2. [content]      - the caller's [child], drawn on top, never filtered.
///   3. [edge]         - a duplicated rounded-rect outline drawn as a soft
///      inner-shadow / highlight stroke (cheap, no extra pass).
class LiquidGlassIsland extends StatefulWidget {
  const LiquidGlassIsland({
    super.key,
    required this.config,
    this.child,
    this.width,
    this.height,
    this.padding = EdgeInsets.zero,
  });

  final LiquidGlassConfig config;
  final Widget? child;
  final double? width;
  final double? height;
  final EdgeInsetsGeometry padding;

  @override
  State<LiquidGlassIsland> createState() => _LiquidGlassIslandState();
}

class _LiquidGlassIslandState extends State<LiquidGlassIsland> {
  FragmentProgram? _program;

  @override
  void initState() {
    super.initState();
    LiquidGlassShader.load().then((program) {
      if (mounted) setState(() => _program = program);
    });
  }

  @override
  Widget build(BuildContext context) {
    final config = widget.config;
    final radius = Radius.circular(config.cornerRadius);

    // Fallback: shader unsupported or not ready yet. Keep the surface
    // transparent so it never "stands out" like a black box.
    if (_program == null) {
      return _fallback(radius);
    }

    final dpr = MediaQuery.of(context).devicePixelRatio;
    final shader = _program!.fragmentShader();
    _applyUniforms(shader, config, dpr);

    final filter = ImageFilter.shader(shader);

    return SizedBox(
      width: widget.width,
      height: widget.height,
      child: Stack(
        clipBehavior: Clip.none,
        children: [
          // 1) glass layer (background + shape), separated from content
          ClipRRect(
            borderRadius: BorderRadius.all(radius),
            child: BackdropFilter(
              filter: filter,
              child: const SizedBox.expand(),
            ),
          ),
          // 2) content on top, clipped to the same shape, never blended
          ClipRRect(
            borderRadius: BorderRadius.all(radius),
            child: Padding(
              padding: widget.padding,
              child: widget.child ?? const SizedBox.shrink(),
            ),
          ),
          // 3) duplicated shape: soft edge highlight / inner shadow
          if (config.innerShadow)
            ClipRRect(
              borderRadius: BorderRadius.all(radius),
              child: CustomPaint(
                painter: _GlassEdgePainter(
                  radius: config.cornerRadius,
                  blurRadius: config.innerShadowRadius,
                  color: Colors.white.withValues(alpha: 0.5),
                ),
                child: const SizedBox.expand(),
              ),
            ),
        ],
      ),
    );
  }

  void _applyUniforms(
    FragmentShader shader,
    LiquidGlassConfig config,
    double dpr,
  ) {
    final r = config.cornerRadius * dpr;
    final tint = Color(config.tintColor);
    shader
      ..setFloatUniform('u_enabled', config.enabled ? 1.0 : 0.0)
      ..setFloatUniform('u_refractionHeight', config.refractionHeight * dpr)
      ..setFloatUniform('u_refractionAmount', config.refractionAmount * dpr)
      ..setFloatUniform('u_cornerRadii', r, r, r, r)
      ..setFloatUniform('u_depthEffect', config.depthEffect)
      ..setFloatUniform('u_chromaticAberration', config.chromaticAberration)
      ..setFloatUniform('u_saturation', config.saturation)
      ..setFloatUniform('u_vibrancy', config.vibrancy)
      ..setFloatUniform(
        'u_tintColor',
        tint.r / 255,
        tint.g / 255,
        tint.b / 255,
        tint.a / 255,
      )
      ..setFloatUniform('u_tintOpacity', config.tintOpacity);
  }

  Widget _fallback(Radius radius) {
    return SizedBox(
      width: widget.width,
      height: widget.height,
      child: ClipRRect(
        borderRadius: BorderRadius.all(radius),
        child: Stack(
          children: [
            Container(
              decoration: BoxDecoration(
                color: Colors.white.withValues(alpha: 0.08),
                borderRadius: BorderRadius.all(radius),
              ),
            ),
            Padding(
              padding: widget.padding,
              child: widget.child ?? const SizedBox.shrink(),
            ),
          ],
        ),
      ),
    );
  }
}

/// Draws a soft, blurred rounded-rect stroke just inside the shape edge.
/// Cheap to duplicate the shape outline for the glass border.
class _GlassEdgePainter extends CustomPainter {
  const _GlassEdgePainter({
    required this.radius,
    required this.blurRadius,
    required this.color,
  });

  final double radius;
  final double blurRadius;
  final Color color;

  @override
  void paint(Canvas canvas, Size size) {
    if (blurRadius <= 0) return;
    final inset = blurRadius * 0.5;
    final rect = Rect.fromLTWH(
      inset,
      inset,
      math.max(0, size.width - inset * 2),
      math.max(0, size.height - inset * 2),
    );
    final rrect = RRect.fromRectAndRadius(rect, Radius.circular(radius));
    final paint = Paint()
      ..color = color
      ..style = PaintingStyle.stroke
      ..strokeWidth = 1.0
      ..maskFilter = MaskFilter.blur(BlurStyle.normal, blurRadius);
    canvas.drawRRect(rrect, paint);
  }

  @override
  bool shouldRepaint(covariant _GlassEdgePainter old) =>
      old.radius != radius ||
      old.blurRadius != blurRadius ||
      old.color != color;
}
