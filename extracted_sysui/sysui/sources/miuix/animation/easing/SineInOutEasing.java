package miuix.animation.easing;

import miuix.animation.motion.DurationMotion;
import miuix.animation.motion.Motion;
import miuix.animation.motion.SimpleHarmonicMotion;

/* JADX INFO: loaded from: classes4.dex */
public class SineInOutEasing implements SimpleEasing {
    private final double duration;

    public SineInOutEasing() {
        this(1.0d);
    }

    @Override // miuix.animation.easing.SimpleEasing
    public final double duration() {
        return this.duration;
    }

    @Override // miuix.animation.FolmeEase
    public Motion newMotion() {
        double d2 = 3.141592653589793d / this.duration;
        return new DurationMotion(new SimpleHarmonicMotion(1.0d, (d2 * d2) / 2.0d, (1.0d / d2) / d2), this.duration, true);
    }

    @Override // miuix.animation.easing.SimpleEasing
    public double startSpeed() {
        return 0.0d;
    }

    public String toString() {
        return "SineInOut(" + this.duration + ")";
    }

    public SineInOutEasing(double d2) {
        if (d2 <= 0.0d) {
            throw new IllegalArgumentException("duration must be positive");
        }
        this.duration = d2;
    }
}
