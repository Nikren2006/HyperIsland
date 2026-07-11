package miuix.animation.motion;

import miuix.animation.function.Differentiable;

/* JADX INFO: loaded from: classes4.dex */
public interface Motion {
    default double finishTime() {
        return Double.POSITIVE_INFINITY;
    }

    double getInitialV();

    double getInitialX();

    void setInitialV(double d2);

    void setInitialX(double d2);

    Differentiable solve();

    default double stopPosition() {
        return Double.NaN;
    }

    default double stopSpeed() {
        return Double.NaN;
    }
}
