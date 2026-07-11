package miuix.animation.motion;

import miuix.animation.function.Constant;
import miuix.animation.function.Differentiable;

/* JADX INFO: loaded from: classes4.dex */
public final class Teleport extends InstantMotion implements Motion {
    public static final int MODE_ABSOLUTE = 0;
    public static final int MODE_RELATIVE = 1;
    private Differentiable function;
    private final int mode;

    /* JADX INFO: renamed from: x, reason: collision with root package name */
    private final double f5989x;

    public Teleport(double d2) {
        this(0, d2);
    }

    @Override // miuix.animation.motion.BaseMotion
    public void onInitialXChanged() {
        super.onInitialXChanged();
        if (this.mode == 1) {
            this.function = null;
        }
    }

    @Override // miuix.animation.motion.Motion
    public Differentiable solve() {
        if (this.function == null) {
            this.function = new Constant(getInitialX() + this.f5989x);
        }
        return this.function;
    }

    @Override // miuix.animation.motion.Motion
    public double stopPosition() {
        int i2 = this.mode;
        return i2 != 0 ? i2 != 1 ? super.stopPosition() : getInitialX() + this.f5989x : this.f5989x;
    }

    @Override // miuix.animation.motion.Motion
    public double stopSpeed() {
        return getInitialV();
    }

    public Teleport(int i2, double d2) {
        this.mode = i2;
        this.f5989x = d2;
        if (i2 == 0) {
            this.function = new Constant(d2);
        }
    }
}
