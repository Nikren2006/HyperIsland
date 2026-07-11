import 'dart:async';
import 'dart:developer' as dev;
import 'dart:math' as math;
import 'dart:ui';

import 'package:flutter/material.dart';

const String _kLiquidGlassLog = 'LiquidGlass';

void _log(String message) => dev.log(message, name: _kLiquidGlassLog);

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
      _log('FragmentProgram loaded OK');
      _completer.complete(program);
    } catch (e, st) {
      _log('FragmentProgram FAILED to load: $e\n$st');
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
///
/// IMPORTANT (correct view): this widget is meant to wrap the ISLAND BODY
/// (the always-visible pill surface), e.g. the small/big island background in
/// the live preview. It must NOT be attached to the glow container or to the
/// expanded/mini-window view that only becomes active while dragging the
/// island down to the app-collapse handle — otherwise the effect only shows up
/// in that transient state (and only once thickness is increased).
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
  void     initState() {
    super.initState();
    _log('initState: supported=${LiquidGlassShader.supported}');
    LiquidGlassShader.load().then((program) {
      _log('shader loaded: ${program == null ? "NULL (fallback will be used)" : "OK"}');
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
      _log('build: NO shader program -> using transparent fallback '
          '(enabled=${config.enabled}, refractionAmount=${config.refractionAmount}, '
          'refractionHeight=${config.refractionHeight}). '
          'If you expected glass, the AGSL likely failed to compile/load.');
      return _fallback(radius);
    }

    final dpr = MediaQuery.of(context).devicePixelRatio;
    _log('build: applying shader. enabled=${config.enabled} '
        'refractionAmount=${config.refractionAmount} '
        'refractionHeight=${config.refractionHeight} '
        'cornerRadius=${config.cornerRadius} vibrancy=${config.vibrancy} '
        'tintOpacity=${config.tintOpacity} dpr=${dpr.toStringAsFixed(2)}');

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
    // Flutter's `ImageFilter.shader` provides `u_size` (vec2, indices 0,1) and
    // `u_texture` (sampler 0) automatically. Our float uniforms start at 2.
    //   u_enabled             2
    //   u_refractionHeight    3
    //   u_refractionAmount    4
    //   u_cornerRadii (vec4)  5..8
    //   u_depthEffect         9
    //   u_chromaticAberration 10
    //   u_saturation          11
    //   u_vibrancy            12
    //   u_tintColor (vec4)    13..16
    //   u_tintOpacity         17
    final r = config.cornerRadius * dpr;
    final tint = Color(config.tintColor);
    try {
      shader
        ..setFloat(2, config.enabled ? 1.0 : 0.0)
        ..setFloat(3, config.refractionHeight * dpr)
        ..setFloat(4, config.refractionAmount * dpr)
        ..setFloat(5, r)
        ..setFloat(6, r)
        ..setFloat(7, r)
        ..setFloat(8, r)
        ..setFloat(9, config.depthEffect)
        ..setFloat(10, config.chromaticAberration)
        ..setFloat(11, config.saturation)
        ..setFloat(12, config.vibrancy)
        ..setFloat(13, tint.r / 255)
        ..setFloat(14, tint.g / 255)
        ..setFloat(15, tint.b / 255)
        ..setFloat(16, tint.a / 255)
        ..setFloat(17, config.tintOpacity);
      _log('uniforms applied: r=$r tint=0x${config.tintColor.toRadixString(16)} '
          'depthEffect=${config.depthEffect} aberration=${config.chromaticAberration}');
    } catch (e, st) {
      _log('setFloat FAILED (wrong uniform index/name?): $e\n$st');
    }
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

    // soft overall edge border / inner shadow
    canvas.drawRRect(
      rrect,
      Paint()
        ..color = color.withValues(alpha: 0.35)
        ..style = PaintingStyle.stroke
        ..strokeWidth = 1.0
        ..maskFilter = MaskFilter.blur(BlurStyle.normal, blurRadius),
    );

    // bright top specular highlight (the glass "блик")
    canvas.save();
    canvas.clipRect(
      Rect.fromLTRB(rect.left, rect.top, rect.right, rect.center.dy),
    );
    canvas.drawRRect(
      rrect,
      Paint()
        ..color = Colors.white.withValues(alpha: 0.9)
        ..style = PaintingStyle.stroke
        ..strokeWidth = 1.5
        ..maskFilter = MaskFilter.blur(BlurStyle.normal, blurRadius * 0.5),
    );
    canvas.restore();
  }

  @override
  bool shouldRepaint(covariant _GlassEdgePainter old) =>
      old.radius != radius ||
      old.blurRadius != blurRadius ||
      old.color != color;
}
