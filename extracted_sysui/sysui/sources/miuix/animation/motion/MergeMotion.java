package miuix.animation.motion;

import java.util.ArrayList;
import java.util.List;
import miuix.animation.function.Differentiable;
import miuix.animation.function.DifferentiableImpl;
import miuix.animation.function.Piecewise;

/* JADX INFO: loaded from: classes4.dex */
public class MergeMotion extends BaseMotion implements Motion {
    private DifferentiableImpl function;
    private final List<Motion> motions;
    private double totalDuration;

    public MergeMotion() {
        this.motions = new ArrayList();
    }

    public void addMotion(Motion motion, double d2) {
        addMotion(new DurationMotion(motion, d2));
    }

    public void clear() {
        this.motions.clear();
        this.totalDuration = 0.0d;
        this.function = null;
    }

    @Override // miuix.animation.motion.Motion
    public double finishTime() {
        return this.totalDuration;
    }

    @Override // miuix.animation.motion.BaseMotion
    public void onInitialVChanged() {
        super.onInitialVChanged();
        this.function = null;
    }

    @Override // miuix.animation.motion.BaseMotion
    public void onInitialXChanged() {
        super.onInitialXChanged();
        this.function = null;
    }

    @Override // miuix.animation.motion.Motion
    public Differentiable solve() {
        if (this.function == null) {
            Piecewise piecewise = new Piecewise(this.motions.size());
            Piecewise piecewise2 = new Piecewise(this.motions.size());
            this.function = new DifferentiableImpl(piecewise, piecewise2);
            double initialX = getInitialX();
            double initialV = getInitialV();
            double dFinishTime = 0.0d;
            for (Motion motion : this.motions) {
                motion.setInitialX(initialX);
                motion.setInitialV(initialV);
                Differentiable differentiableSolve = motion.solve();
                dFinishTime += motion.finishTime();
                piecewise.add(differentiableSolve, dFinishTime);
                piecewise2.add(differentiableSolve.derivative(), dFinishTime);
                initialX = motion.stopPosition();
                initialV = motion.stopSpeed();
            }
        }
        return this.function;
    }

    @Override // miuix.animation.motion.Motion
    public double stopPosition() {
        solve();
        return this.motions.get(r2.size() - 1).stopPosition();
    }

    @Override // miuix.animation.motion.Motion
    public double stopSpeed() {
        solve();
        return this.motions.get(r2.size() - 1).stopSpeed();
    }

    public void addMotion(Motion motion) {
        if (Double.isInfinite(this.totalDuration)) {
            throw new UnsupportedOperationException("new motion is denied after a forever motion");
        }
        this.totalDuration += motion.finishTime();
        this.motions.add(motion);
        this.function = null;
    }

    public MergeMotion(int i2) {
        this.motions = new ArrayList(i2);
    }
}
