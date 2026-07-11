package miuix.animation.easing;

import miuix.animation.motion.DurationMotion;
import miuix.animation.motion.MergeMotion;
import miuix.animation.motion.Motion;
import miuix.animation.motion.UniformlyAcceleratedMotion;

/* JADX INFO: loaded from: classes4.dex */
public class QuadInOutEasing implements SimpleEasing {
    private final double duration;

    public QuadInOutEasing() {
        this(1.0d);
    }

    @Override // miuix.animation.easing.SimpleEasing
    public final double duration() {
        return this.duration;
    }

    @Override // miuix.animation.FolmeEase
    public Motion newMotion() {
        MergeMotion mergeMotion = new MergeMotion(2);
        double d2 = this.duration;
        mergeMotion.addMotion(new DurationMotion(new UniformlyAcceleratedMotion(4.0d / (d2 * d2)), this.duration / 2.0d, false));
        double d3 = this.duration;
        mergeMotion.addMotion(new DurationMotion(new UniformlyAcceleratedMotion((-4.0d) / (d3 * d3)), this.duration / 2.0d, true));
        return mergeMotion;
    }

    @Override // miuix.animation.easing.SimpleEasing
    public double startSpeed() {
        return 0.0d;
    }

    public String toString() {
        return "QuadInOut(" + this.duration + ")";
    }

    public QuadInOutEasing(double d2) {
        if (d2 <= 0.0d) {
            throw new IllegalArgumentException("duration must be positive");
        }
        this.duration = d2;
    }
}
