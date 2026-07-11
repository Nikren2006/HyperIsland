package miuix.animation.easing;

import miuix.animation.motion.DurationMotion;
import miuix.animation.motion.Motion;
import miuix.animation.motion.UniformLinearMotion;

/* JADX INFO: loaded from: classes4.dex */
public class LinearEasing implements SimpleEasing {
    private final double duration;

    public LinearEasing() {
        this(1.0d);
    }

    @Override // miuix.animation.easing.SimpleEasing
    public final double duration() {
        return this.duration;
    }

    @Override // miuix.animation.FolmeEase
    public Motion newMotion() {
        return new DurationMotion(new UniformLinearMotion(), this.duration, true);
    }

    @Override // miuix.animation.easing.SimpleEasing
    public double startSpeed() {
        return 1.0d / this.duration;
    }

    public String toString() {
        return "Linear(" + this.duration + ")";
    }

    public LinearEasing(double d2) {
        if (d2 <= 0.0d) {
            throw new IllegalArgumentException("duration must be positive");
        }
        this.duration = d2;
    }
}
