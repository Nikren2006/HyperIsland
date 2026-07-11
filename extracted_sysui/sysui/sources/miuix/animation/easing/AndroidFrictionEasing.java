package miuix.animation.easing;

import androidx.annotation.NonNull;

/* JADX INFO: loaded from: classes4.dex */
public class AndroidFrictionEasing extends AndroidDampingEasing implements PhysicalEasing {
    public AndroidFrictionEasing(double d2) {
        super(d2, 0.0d);
    }

    @Override // miuix.animation.easing.DampingEasing
    @NonNull
    public String toString() {
        return "Friction(" + getDamping() + ")";
    }
}
