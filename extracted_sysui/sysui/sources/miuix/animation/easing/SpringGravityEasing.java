package miuix.animation.easing;

import miuix.animation.motion.DampedHarmonicMotion;
import miuix.animation.motion.Motion;

/* JADX INFO: loaded from: classes4.dex */
public class SpringGravityEasing extends SpringEasing implements PhysicalEasing {
    private final double acceleration;

    public SpringGravityEasing(double d2, double d3, double d4) {
        super(d2, d3);
        this.acceleration = d4;
    }

    public final double getAcceleration() {
        return this.acceleration;
    }

    @Override // miuix.animation.easing.SpringEasing
    public Motion newMotion(double d2) {
        return new DampedHarmonicMotion(getZeta(), getOmega(), d2, this.acceleration);
    }

    @Override // miuix.animation.easing.SpringEasing
    public String toString() {
        return "SpringPhy(" + getZeta() + ", " + getOmega() + ", " + this.acceleration + ")";
    }
}
