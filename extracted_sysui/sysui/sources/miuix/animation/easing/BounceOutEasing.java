package miuix.animation.easing;

import miuix.animation.function.BounceOut;
import miuix.animation.motion.FunctionMotion;
import miuix.animation.motion.Motion;
import miuix.animation.motion.ScaleMotion;

/* JADX INFO: loaded from: classes4.dex */
public class BounceOutEasing implements SimpleEasing {
    private final double duration;

    public BounceOutEasing() {
        this(1.0d);
    }

    @Override // miuix.animation.easing.SimpleEasing
    public final double duration() {
        return this.duration;
    }

    @Override // miuix.animation.FolmeEase
    public Motion newMotion() {
        return new ScaleMotion(new FunctionMotion(new BounceOut()), 1.0d, this.duration);
    }

    @Override // miuix.animation.easing.SimpleEasing
    public double startSpeed() {
        return 0.0d;
    }

    public String toString() {
        return "BounceOut(" + this.duration + ")";
    }

    public BounceOutEasing(double d2) {
        if (d2 <= 0.0d) {
            throw new IllegalArgumentException("duration must be positive");
        }
        this.duration = d2;
    }
}
