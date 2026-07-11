package miuix.animation.easing;

import miuix.animation.motion.AndroidFreeDampedMotion;
import miuix.animation.motion.Motion;

/* JADX INFO: loaded from: classes4.dex */
public class AndroidDampingEasing extends DampingEasing implements PhysicalEasing {
    public AndroidDampingEasing(double d2, double d3) {
        super(d2, d3);
    }

    @Override // miuix.animation.easing.DampingEasing, miuix.animation.FolmeEase
    public Motion newMotion() {
        return new AndroidFreeDampedMotion(getDamping(), getAcceleration());
    }
}
