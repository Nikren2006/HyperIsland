package miuix.animation.motion;

import miuix.animation.function.Constant;
import miuix.animation.function.Differentiable;

/* JADX INFO: loaded from: classes4.dex */
public final class Shift extends InstantMotion implements Motion {
    private Differentiable function;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    private final double f5988v;

    public Shift(double d2) {
        this.f5988v = d2;
    }

    @Override // miuix.animation.motion.Motion
    public Differentiable solve() {
        if (this.function == null) {
            this.function = new Constant(getInitialX());
        }
        return this.function;
    }

    @Override // miuix.animation.motion.Motion
    public double stopPosition() {
        return getInitialX();
    }

    @Override // miuix.animation.motion.Motion
    public double stopSpeed() {
        return this.f5988v;
    }
}
