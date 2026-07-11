package miuix.animation.easing;

import miuix.animation.function.Decelerate;
import miuix.animation.motion.FunctionMotion;
import miuix.animation.motion.Motion;
import miuix.animation.motion.ScaleMotion;

/* JADX INFO: loaded from: classes4.dex */
public class DecelerateEasing implements SimpleEasing {
    private final double duration;
    private final double factor;

    public DecelerateEasing() {
        this(1.0d);
    }

    @Override // miuix.animation.easing.SimpleEasing
    public final double duration() {
        return this.duration;
    }

    @Override // miuix.animation.FolmeEase
    public Motion newMotion() {
        return new ScaleMotion(new FunctionMotion(new Decelerate(this.factor)), 1.0d, this.duration);
    }

    @Override // miuix.animation.easing.SimpleEasing
    public double startSpeed() {
        return 0.0d;
    }

    public String toString() {
        return "Decelerate(" + this.duration + ")";
    }

    public DecelerateEasing(double d2) {
        if (d2 <= 0.0d) {
            throw new IllegalArgumentException("duration must be positive");
        }
        this.factor = 1.0d;
        this.duration = d2;
    }

    public DecelerateEasing(double d2, double d3) {
        if (d3 > 0.0d) {
            this.factor = Math.max(0.0d, d2);
            this.duration = d3;
            return;
        }
        throw new IllegalArgumentException("duration must be positive");
    }
}
