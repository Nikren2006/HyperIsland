package miuix.animation.motion;

/* JADX INFO: loaded from: classes4.dex */
public class AndroidFreeDampedMotion extends FreeDampedMotion implements AndroidMotion {
    private double finishTime;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private final double f5978g;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    private final double f5979p;
    private double threshold;

    public AndroidFreeDampedMotion(double d2, double d3) {
        super(d2, d3);
        this.f5979p = d2;
        this.f5978g = d3;
    }

    private double solveFinishTime() {
        double dFinishTime = super.finishTime();
        if (dFinishTime == 0.0d) {
            return dFinishTime;
        }
        double d2 = this.threshold;
        return d2 == 0.0d ? dFinishTime : (-Math.log(d2)) / this.f5979p;
    }

    @Override // miuix.animation.motion.FreeDampedMotion, miuix.animation.motion.Motion
    public double finishTime() {
        if (Double.isNaN(this.finishTime)) {
            this.finishTime = solveFinishTime();
        }
        return this.finishTime;
    }

    @Override // miuix.animation.motion.FreeDampedMotion, miuix.animation.motion.BaseMotion
    public void onInitialVChanged() {
        super.onInitialVChanged();
        this.finishTime = Double.NaN;
    }

    @Override // miuix.animation.motion.FreeDampedMotion, miuix.animation.motion.BaseMotion
    public void onInitialXChanged() {
        super.onInitialXChanged();
        this.finishTime = Double.NaN;
    }

    @Override // miuix.animation.motion.AndroidMotion
    public void setThreshold(double d2) {
        this.threshold = d2;
        this.finishTime = Double.NaN;
    }

    @Override // miuix.animation.motion.FreeDampedMotion, miuix.animation.motion.Motion
    public double stopPosition() {
        return this.f5978g == 0.0d ? getInitialX() + (getInitialV() / this.f5979p) : solve().apply(finishTime());
    }
}
