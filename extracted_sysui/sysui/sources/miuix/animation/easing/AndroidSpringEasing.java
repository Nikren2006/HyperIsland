package miuix.animation.easing;

import miuix.animation.motion.AndroidDampedHarmonicMotion;
import miuix.animation.motion.Motion;

/* JADX INFO: loaded from: classes4.dex */
public class AndroidSpringEasing extends SpringEasing implements PhysicalEasing {
    public AndroidSpringEasing(double d2, double d3) {
        super(d2, d3);
    }

    @Override // miuix.animation.easing.SpringEasing
    public Motion newMotion(double d2) {
        return new AndroidDampedHarmonicMotion(getZeta(), getOmega(), d2, 0.0d);
    }
}
