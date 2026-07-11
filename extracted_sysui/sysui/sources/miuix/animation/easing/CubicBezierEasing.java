package miuix.animation.easing;

import miuix.animation.motion.CubicBezierMotion;
import miuix.animation.motion.Motion;
import miuix.animation.motion.ScaleMotion;

/* JADX INFO: loaded from: classes4.dex */
public class CubicBezierEasing implements SimpleEasing {
    private final double duration;

    /* JADX INFO: renamed from: x1, reason: collision with root package name */
    private final double f5939x1;
    private final double x2;

    /* JADX INFO: renamed from: y1, reason: collision with root package name */
    private final double f5940y1;
    private final double y2;

    public CubicBezierEasing(double d2, double d3, double d4, double d5, double d6) {
        if (d3 < 0.0d || d3 > 1.0d) {
            throw new IllegalArgumentException("x1 must be between [0, 1]");
        }
        if (d5 < 0.0d || d5 > 1.0d) {
            throw new IllegalArgumentException("x2 must be between [0, 1]");
        }
        this.duration = d2;
        this.f5939x1 = d3;
        this.f5940y1 = d4;
        this.x2 = d5;
        this.y2 = d6;
    }

    @Override // miuix.animation.easing.SimpleEasing
    public double duration() {
        return this.duration;
    }

    @Override // miuix.animation.FolmeEase
    public Motion newMotion() {
        return new ScaleMotion(new CubicBezierMotion(this.f5939x1, this.f5940y1, this.x2, this.y2), 1.0d, this.duration);
    }

    @Override // miuix.animation.easing.SimpleEasing
    public double startSpeed() {
        return 0.0d;
    }
}
