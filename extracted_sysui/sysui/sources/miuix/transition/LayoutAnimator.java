package miuix.transition;

import miuix.animation.utils.EaseManager;
import miuix.transition.MiuixTransition;

/* JADX INFO: loaded from: classes5.dex */
public abstract class LayoutAnimator {
    protected MiuixTransition mTransition;

    public static EaseManager.EaseStyle spring(float f2, float f3) {
        return EaseManager.getStyle(-2, f2, f3);
    }

    public void addTransitionListener(MiuixTransition.MiuixTransitionListener miuixTransitionListener) {
        MiuixTransition miuixTransition = this.mTransition;
        if (miuixTransition == null) {
            throw new RuntimeException("please complete transition preparation before adding listener");
        }
        miuixTransition.addListener(miuixTransitionListener);
    }

    public MiuixTransition getTransition() {
        return this.mTransition;
    }

    public abstract void prepareTransition();

    public abstract void traceChangeToTransition(Runnable runnable);
}
