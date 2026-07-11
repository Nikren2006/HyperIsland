package miuix.animation.easing;

import miuix.animation.motion.DampedHarmonicMotion;
import miuix.animation.motion.Motion;

/* JADX INFO: loaded from: classes4.dex */
public class SpringEasing implements PhysicalEasing {
    private final double omega;
    private final double zeta;

    public SpringEasing(double d2, double d3) {
        if (d2 < 0.0d) {
            throw new IllegalArgumentException("damping must not be negative");
        }
        if (d3 < 0.0d) {
            throw new IllegalArgumentException("response must not be negative");
        }
        this.zeta = d2;
        this.omega = 6.283185307179586d / d3;
    }

    public final double getOmega() {
        return this.omega;
    }

    public final double getZeta() {
        return this.zeta;
    }

    @Override // miuix.animation.FolmeEase
    public Motion newMotion() {
        return newMotion(0.0d);
    }

    public String toString() {
        return "Spring(" + this.zeta + ", " + this.omega + ")";
    }

    public Motion newMotion(double d2) {
        return new DampedHarmonicMotion(this.zeta, this.omega, d2, 0.0d);
    }
}
