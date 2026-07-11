package miui.systemui.devicecontrols.management;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.animation.Interpolator;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.OnLifecycleEvent;
import kotlin.jvm.functions.Function1;
import miui.systemui.Interpolators;
import miui.systemui.devicecontrols.R;

/* JADX INFO: loaded from: classes3.dex */
public final class ControlsAnimations {
    private static final long ALPHA_ENTER_DELAY = 183;
    private static final long ALPHA_ENTER_DURATION = 167;
    private static final long ALPHA_EXIT_DURATION = 183;
    private static final long Y_TRANSLATION_ENTER_DELAY = 0;
    private static final long Y_TRANSLATION_ENTER_DURATION = 217;
    private static final long Y_TRANSLATION_EXIT_DURATION = 183;
    public static final ControlsAnimations INSTANCE = new ControlsAnimations();
    private static float translationY = -1.0f;

    /* JADX INFO: renamed from: miui.systemui.devicecontrols.management.ControlsAnimations$enterWindowTransition$1, reason: invalid class name */
    public static final class AnonymousClass1 extends kotlin.jvm.internal.o implements Function1 {
        public static final AnonymousClass1 INSTANCE = new AnonymousClass1();

        public AnonymousClass1() {
            super(1);
        }

        @Override // kotlin.jvm.functions.Function1
        public final Animator invoke(View view) {
            kotlin.jvm.internal.n.g(view, "view");
            return ControlsAnimations.INSTANCE.enterAnimation(view);
        }
    }

    /* JADX INFO: renamed from: miui.systemui.devicecontrols.management.ControlsAnimations$exitWindowTransition$1, reason: invalid class name and case insensitive filesystem */
    public static final class C05001 extends kotlin.jvm.internal.o implements Function1 {
        public static final C05001 INSTANCE = new C05001();

        public C05001() {
            super(1);
        }

        @Override // kotlin.jvm.functions.Function1
        public final Animator invoke(View view) {
            kotlin.jvm.internal.n.g(view, "view");
            return ControlsAnimations.exitAnimation$default(view, null, 2, null);
        }
    }

    private ControlsAnimations() {
    }

    public static final Animator exitAnimation(View view, final Runnable runnable) {
        kotlin.jvm.internal.n.g(view, "view");
        Log.d("ControlsUiController", "Exit animation for " + view);
        ObjectAnimator objectAnimatorOfFloat = ObjectAnimator.ofFloat(view, "transitionAlpha", 0.0f);
        Interpolator interpolator = Interpolators.ACCELERATE;
        objectAnimatorOfFloat.setInterpolator(interpolator);
        objectAnimatorOfFloat.setDuration(183L);
        view.setTranslationY(0.0f);
        ObjectAnimator objectAnimatorOfFloat2 = ObjectAnimator.ofFloat(view, "translationY", -translationY);
        objectAnimatorOfFloat2.setInterpolator(interpolator);
        objectAnimatorOfFloat2.setDuration(183L);
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playTogether(objectAnimatorOfFloat, objectAnimatorOfFloat2);
        if (runnable != null) {
            animatorSet.addListener(new AnimatorListenerAdapter() { // from class: miui.systemui.devicecontrols.management.ControlsAnimations$exitAnimation$1$1$1
                @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
                public void onAnimationEnd(Animator animation) {
                    kotlin.jvm.internal.n.g(animation, "animation");
                    runnable.run();
                }
            });
        }
        return animatorSet;
    }

    public static /* synthetic */ Animator exitAnimation$default(View view, Runnable runnable, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            runnable = null;
        }
        return exitAnimation(view, runnable);
    }

    public final Animator enterAnimation(View view) {
        kotlin.jvm.internal.n.g(view, "view");
        Log.d("ControlsUiController", "Enter animation for " + view);
        view.setTransitionAlpha(0.0f);
        view.setAlpha(1.0f);
        view.setTranslationY(translationY);
        ObjectAnimator objectAnimatorOfFloat = ObjectAnimator.ofFloat(view, "transitionAlpha", 0.0f, 1.0f);
        Interpolator interpolator = Interpolators.DECELERATE_QUINT;
        objectAnimatorOfFloat.setInterpolator(interpolator);
        objectAnimatorOfFloat.setStartDelay(183L);
        objectAnimatorOfFloat.setDuration(ALPHA_ENTER_DURATION);
        ObjectAnimator objectAnimatorOfFloat2 = ObjectAnimator.ofFloat(view, "translationY", 0.0f);
        objectAnimatorOfFloat2.setInterpolator(interpolator);
        objectAnimatorOfFloat2.setStartDelay(Y_TRANSLATION_ENTER_DURATION);
        objectAnimatorOfFloat2.setDuration(Y_TRANSLATION_ENTER_DURATION);
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playTogether(objectAnimatorOfFloat, objectAnimatorOfFloat2);
        return animatorSet;
    }

    public final WindowTransition enterWindowTransition(int i2) {
        WindowTransition windowTransition = new WindowTransition(AnonymousClass1.INSTANCE);
        windowTransition.addTarget(i2);
        return windowTransition;
    }

    public final WindowTransition exitWindowTransition(int i2) {
        WindowTransition windowTransition = new WindowTransition(C05001.INSTANCE);
        windowTransition.addTarget(i2);
        return windowTransition;
    }

    public final LifecycleObserver observerForAnimations(ViewGroup view, Window window, Intent intent) {
        kotlin.jvm.internal.n.g(view, "view");
        kotlin.jvm.internal.n.g(window, "window");
        kotlin.jvm.internal.n.g(intent, "intent");
        return new LifecycleObserver(intent, view, window) { // from class: miui.systemui.devicecontrols.management.ControlsAnimations.observerForAnimations.1
            final /* synthetic */ ViewGroup $view;
            final /* synthetic */ Window $window;
            private boolean showAnimation;

            {
                this.$view = view;
                this.$window = window;
                this.showAnimation = intent.getBooleanExtra("extra_animate", false);
                view.setTransitionGroup(true);
                view.setTransitionAlpha(0.0f);
                if (ControlsAnimations.translationY == -1.0f) {
                    ControlsAnimations.translationY = view.getContext().getResources().getDimensionPixelSize(R.dimen.global_actions_controls_y_translation);
                }
            }

            @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
            public final void enterAnimation() {
                if (this.showAnimation) {
                    ControlsAnimations.INSTANCE.enterAnimation(this.$view).start();
                    this.showAnimation = false;
                }
            }

            public final boolean getShowAnimation() {
                return this.showAnimation;
            }

            @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
            public final void resetAnimation() {
                this.$view.setTranslationY(0.0f);
            }

            public final void setShowAnimation(boolean z2) {
                this.showAnimation = z2;
            }

            @OnLifecycleEvent(Lifecycle.Event.ON_START)
            public final void setup() {
                Window window2 = this.$window;
                ViewGroup viewGroup = this.$view;
                window2.setAllowEnterTransitionOverlap(true);
                ControlsAnimations controlsAnimations = ControlsAnimations.INSTANCE;
                window2.setEnterTransition(controlsAnimations.enterWindowTransition(viewGroup.getId()));
                window2.setExitTransition(controlsAnimations.exitWindowTransition(viewGroup.getId()));
                window2.setReenterTransition(controlsAnimations.enterWindowTransition(viewGroup.getId()));
                window2.setReturnTransition(controlsAnimations.exitWindowTransition(viewGroup.getId()));
            }
        };
    }
}
