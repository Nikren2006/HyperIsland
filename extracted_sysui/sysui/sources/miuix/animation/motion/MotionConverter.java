package miuix.animation.motion;

import miuix.animation.function.Differentiable;
import miuix.animation.function.Function;

/* JADX INFO: loaded from: classes4.dex */
public final class MotionConverter implements Motion {
    private Differentiable function;
    private final Motion mMotion;
    private final double mScale;
    private final double mZeroPoint;

    /* JADX INFO: renamed from: miuix.animation.motion.MotionConverter$1, reason: invalid class name */
    public class AnonymousClass1 implements Differentiable {
        public AnonymousClass1() {
        }

        /* JADX INFO: Access modifiers changed from: private */
        public /* synthetic */ double lambda$derivative$0(double d2) {
            return MotionConverter.this.mMotion.solve().derivative().apply(d2) * MotionConverter.this.mScale;
        }

        @Override // miuix.animation.function.Differentiable, miuix.animation.function.Function
        public double apply(double d2) {
            return (MotionConverter.this.mMotion.solve().apply(d2) * MotionConverter.this.mScale) + MotionConverter.this.mZeroPoint;
        }

        @Override // miuix.animation.function.Differentiable
        public Function derivative() {
            return new Function() { // from class: miuix.animation.motion.b
                @Override // miuix.animation.function.Function
                public final double apply(double d2) {
                    return this.f5993a.lambda$derivative$0(d2);
                }
            };
        }
    }

    public MotionConverter(Motion motion, double d2, double d3) {
        if (d3 == 0.0d) {
            throw new IllegalArgumentException("scale must not be zero");
        }
        this.mMotion = motion;
        this.mZeroPoint = d2;
        this.mScale = d3;
    }

    @Override // miuix.animation.motion.Motion
    public double finishTime() {
        return this.mMotion.finishTime();
    }

    @Override // miuix.animation.motion.Motion
    public double getInitialV() {
        return this.mMotion.getInitialV() * this.mScale;
    }

    @Override // miuix.animation.motion.Motion
    public double getInitialX() {
        return this.mScale + this.mZeroPoint;
    }

    @Override // miuix.animation.motion.Motion
    public void setInitialV(double d2) {
        this.mMotion.setInitialV(d2 / this.mScale);
    }

    @Override // miuix.animation.motion.Motion
    public void setInitialX(double d2) {
        this.mMotion.setInitialX((d2 - this.mZeroPoint) / this.mScale);
    }

    @Override // miuix.animation.motion.Motion
    public Differentiable solve() {
        if (this.function == null) {
            this.function = new AnonymousClass1();
        }
        return this.function;
    }

    @Override // miuix.animation.motion.Motion
    public double stopPosition() {
        return (this.mMotion.stopPosition() * this.mScale) + this.mZeroPoint;
    }

    @Override // miuix.animation.motion.Motion
    public double stopSpeed() {
        return this.mMotion.stopSpeed() * this.mScale;
    }
}
