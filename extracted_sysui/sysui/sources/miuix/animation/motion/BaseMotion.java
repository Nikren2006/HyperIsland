package miuix.animation.motion;

/* JADX INFO: loaded from: classes4.dex */
public abstract class BaseMotion implements Motion {
    private double initialV;
    private double initialX;

    @Override // miuix.animation.motion.Motion
    public double getInitialV() {
        return this.initialV;
    }

    @Override // miuix.animation.motion.Motion
    public double getInitialX() {
        return this.initialX;
    }

    public void onInitialVChanged() {
    }

    public void onInitialXChanged() {
    }

    @Override // miuix.animation.motion.Motion
    public final void setInitialV(double d2) {
        if (this.initialV != d2) {
            this.initialV = d2;
            onInitialVChanged();
        }
    }

    @Override // miuix.animation.motion.Motion
    public final void setInitialX(double d2) {
        if (this.initialX != d2) {
            this.initialX = d2;
            onInitialXChanged();
        }
    }
}
