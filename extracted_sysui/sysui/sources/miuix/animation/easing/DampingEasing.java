package miuix.animation.easing;

import miuix.animation.motion.FreeDampedMotion;
import miuix.animation.motion.Motion;

/* JADX INFO: loaded from: classes4.dex */
public class DampingEasing implements PhysicalEasing {
    private final double acceleration;
    private final double damping;

    public DampingEasing(double d2, double d3) {
        if (d2 < 0.0d) {
            throw new IllegalArgumentException("damping must not be negative");
        }
        this.damping = d2;
        this.acceleration = d3;
    }

    public final double getAcceleration() {
        return this.acceleration;
    }

    public final double getDamping() {
        return this.damping;
    }

    @Override // miuix.animation.FolmeEase
    public Motion newMotion() {
        return new FreeDampedMotion(this.damping, this.acceleration);
    }

    public String toString() {
        return "Damping(" + this.damping + ", " + this.acceleration + ")";
    }
}
