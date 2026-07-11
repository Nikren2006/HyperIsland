package p;

import android.animation.Animator;
import android.animation.TimeInterpolator;
import android.animation.ValueAnimator;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

/* JADX INFO: renamed from: p.a, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public abstract class AbstractC0721a extends ValueAnimator {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Set f6324a = new CopyOnWriteArraySet();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final Set f6325b = new CopyOnWriteArraySet();

    public void a() {
        Iterator it = this.f6325b.iterator();
        while (it.hasNext()) {
            ((Animator.AnimatorListener) it.next()).onAnimationCancel(this);
        }
    }

    @Override // android.animation.Animator
    public void addListener(Animator.AnimatorListener animatorListener) {
        this.f6325b.add(animatorListener);
    }

    @Override // android.animation.ValueAnimator
    public void addUpdateListener(ValueAnimator.AnimatorUpdateListener animatorUpdateListener) {
        this.f6324a.add(animatorUpdateListener);
    }

    public void b(boolean z2) {
        Iterator it = this.f6325b.iterator();
        while (it.hasNext()) {
            ((Animator.AnimatorListener) it.next()).onAnimationEnd(this, z2);
        }
    }

    public void c() {
        Iterator it = this.f6325b.iterator();
        while (it.hasNext()) {
            ((Animator.AnimatorListener) it.next()).onAnimationRepeat(this);
        }
    }

    public void d(boolean z2) {
        Iterator it = this.f6325b.iterator();
        while (it.hasNext()) {
            ((Animator.AnimatorListener) it.next()).onAnimationStart(this, z2);
        }
    }

    public void e() {
        Iterator it = this.f6324a.iterator();
        while (it.hasNext()) {
            ((ValueAnimator.AnimatorUpdateListener) it.next()).onAnimationUpdate(this);
        }
    }

    @Override // android.animation.ValueAnimator, android.animation.Animator
    public long getStartDelay() {
        throw new UnsupportedOperationException("LottieAnimator does not support getStartDelay.");
    }

    @Override // android.animation.Animator
    public void removeAllListeners() {
        this.f6325b.clear();
    }

    @Override // android.animation.ValueAnimator
    public void removeAllUpdateListeners() {
        this.f6324a.clear();
    }

    @Override // android.animation.Animator
    public void removeListener(Animator.AnimatorListener animatorListener) {
        this.f6325b.remove(animatorListener);
    }

    @Override // android.animation.ValueAnimator
    public void removeUpdateListener(ValueAnimator.AnimatorUpdateListener animatorUpdateListener) {
        this.f6324a.remove(animatorUpdateListener);
    }

    @Override // android.animation.ValueAnimator, android.animation.Animator
    public void setInterpolator(TimeInterpolator timeInterpolator) {
        throw new UnsupportedOperationException("LottieAnimator does not support setInterpolator.");
    }

    @Override // android.animation.ValueAnimator, android.animation.Animator
    public void setStartDelay(long j2) {
        throw new UnsupportedOperationException("LottieAnimator does not support setStartDelay.");
    }

    @Override // android.animation.ValueAnimator, android.animation.Animator
    public ValueAnimator setDuration(long j2) {
        throw new UnsupportedOperationException("LottieAnimator does not support setDuration.");
    }
}
