package miuix.animation.motion;

import miuix.animation.function.Differentiable;
import miuix.animation.function.Polynomial;

/* JADX INFO: loaded from: classes4.dex */
public final class PolynomialMotion extends BaseMotion implements Motion {
    private final Differentiable function;

    public PolynomialMotion(int i2, double... dArr) {
        this.function = new Polynomial(i2, dArr);
    }

    @Override // miuix.animation.motion.Motion
    public Differentiable solve() {
        return this.function;
    }

    public PolynomialMotion(Polynomial polynomial) {
        this.function = polynomial;
    }
}
