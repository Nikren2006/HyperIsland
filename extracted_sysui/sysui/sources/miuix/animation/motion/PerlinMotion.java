package miuix.animation.motion;

import java.util.Random;
import miuix.animation.function.Differentiable;
import miuix.animation.function.Function;
import miuix.animation.function.Polynomial;

/* JADX INFO: loaded from: classes4.dex */
public class PerlinMotion extends BaseMotion {
    public static final Differentiable INTERPOLATOR = new Polynomial(3, -2.0d, 3.0d, 0.0d, 0.0d);
    public static final Differentiable INTERPOLATOR2 = new Polynomial(5, 6.0d, -15.0d, 10.0d, 0.0d, 0.0d, 0.0d);
    private final double duration;
    private Differentiable function;
    private final Differentiable interpolator;
    private final double range;

    public class PerlinFunction implements Differentiable {
        private int currentStep;
        private Function derivative;
        private double nextFrame;
        private double prevFrame;

        /* JADX INFO: renamed from: r, reason: collision with root package name */
        private final Random f5987r;
        private final long seed;

        public PerlinFunction() {
            Random random = new Random();
            this.f5987r = random;
            this.seed = random.nextLong();
            this.nextFrame = getFrame(1);
        }

        private double getFrame(int i2) {
            if (i2 == 0) {
                return 0.0d;
            }
            this.f5987r.setSeed(this.seed + ((long) i2));
            this.f5987r.nextInt();
            return (((this.f5987r.nextDouble() * PerlinMotion.this.range) * 2.0d) - PerlinMotion.this.range) + PerlinMotion.this.getInitialX();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public /* synthetic */ double lambda$derivative$0(double d2) {
            double d3 = d2 / PerlinMotion.this.duration;
            int iFloor = (int) Math.floor(d3);
            walk(iFloor);
            return PerlinMotion.this.interpolator.derivative().apply(d3 - ((double) iFloor)) * (this.nextFrame - this.prevFrame);
        }

        private void walk(int i2) {
            while (true) {
                int i3 = this.currentStep;
                if (i3 <= i2) {
                    break;
                }
                int i4 = i3 - 1;
                this.currentStep = i4;
                this.nextFrame = this.prevFrame;
                this.prevFrame = getFrame(i4);
            }
            while (true) {
                int i5 = this.currentStep;
                if (i5 >= i2) {
                    return;
                }
                this.currentStep = i5 + 1;
                this.prevFrame = this.nextFrame;
                this.nextFrame = getFrame(i5 + 2);
            }
        }

        @Override // miuix.animation.function.Differentiable, miuix.animation.function.Function
        public double apply(double d2) {
            double d3 = d2 / PerlinMotion.this.duration;
            int iFloor = (int) Math.floor(d3);
            walk(iFloor);
            double dApply = PerlinMotion.this.interpolator.apply(d3 - ((double) iFloor));
            double d4 = this.nextFrame;
            double d5 = this.prevFrame;
            return (dApply * (d4 - d5)) + d5;
        }

        @Override // miuix.animation.function.Differentiable
        public Function derivative() {
            if (this.derivative == null) {
                this.derivative = new Function() { // from class: miuix.animation.motion.c
                    @Override // miuix.animation.function.Function
                    public final double apply(double d2) {
                        return this.f5994a.lambda$derivative$0(d2);
                    }
                };
            }
            return this.derivative;
        }
    }

    public PerlinMotion(double d2, double d3, Differentiable differentiable) {
        this.duration = d2;
        this.range = d3;
        this.interpolator = differentiable;
    }

    private Differentiable solveInternal() {
        return new PerlinFunction();
    }

    @Override // miuix.animation.motion.BaseMotion
    public void onInitialXChanged() {
        super.onInitialXChanged();
        this.function = null;
    }

    @Override // miuix.animation.motion.Motion
    public Differentiable solve() {
        if (this.function == null) {
            this.function = solveInternal();
        }
        return this.function;
    }
}
