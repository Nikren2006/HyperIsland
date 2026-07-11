package miui.systemui.devicecontrols.management;

import android.animation.Animator;
import android.transition.Transition;
import android.transition.TransitionValues;
import android.view.View;
import android.view.ViewGroup;
import java.util.Map;
import kotlin.jvm.functions.Function1;

/* JADX INFO: loaded from: classes3.dex */
public final class WindowTransition extends Transition {
    private final Function1 animator;

    public WindowTransition(Function1 animator) {
        kotlin.jvm.internal.n.g(animator, "animator");
        this.animator = animator;
    }

    @Override // android.transition.Transition
    public void captureEndValues(TransitionValues tv) {
        kotlin.jvm.internal.n.g(tv, "tv");
        Map values = tv.values;
        kotlin.jvm.internal.n.f(values, "values");
        values.put("item", Float.valueOf(1.0f));
    }

    @Override // android.transition.Transition
    public void captureStartValues(TransitionValues tv) {
        kotlin.jvm.internal.n.g(tv, "tv");
        Map values = tv.values;
        kotlin.jvm.internal.n.f(values, "values");
        values.put("item", Float.valueOf(0.0f));
    }

    @Override // android.transition.Transition
    public Animator createAnimator(ViewGroup sceneRoot, TransitionValues transitionValues, TransitionValues transitionValues2) {
        kotlin.jvm.internal.n.g(sceneRoot, "sceneRoot");
        Function1 function1 = this.animator;
        kotlin.jvm.internal.n.d(transitionValues);
        View view = transitionValues.view;
        kotlin.jvm.internal.n.f(view, "view");
        return (Animator) function1.invoke(view);
    }

    public final Function1 getAnimator() {
        return this.animator;
    }
}
