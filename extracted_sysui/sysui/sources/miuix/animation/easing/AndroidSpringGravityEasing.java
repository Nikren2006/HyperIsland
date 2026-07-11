package miuix.animation.easing;

import miuix.animation.motion.AndroidDampedHarmonicMotion;
import miuix.animation.motion.Motion;

/* JADX INFO: loaded from: classes4.dex */
public class AndroidSpringGravityEasing extends SpringGravityEasing implements PhysicalEasing {
    public AndroidSpringGravityEasing(double d2, double d3, double d4) {
        super(d2, d3, d4);
    }

    @Override // miuix.animation.easing.SpringGravityEasing, miuix.animation.easing.SpringEasing
    public Motion newMotion(double d2) {
        return new AndroidDampedHarmonicMotion(getZeta(), getOmega(), d2, getAcceleration());
    }
}
