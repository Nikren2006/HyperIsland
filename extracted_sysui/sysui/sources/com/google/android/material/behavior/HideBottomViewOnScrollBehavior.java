package com.google.android.material.behavior;

import J.d;
import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.TimeInterpolator;
import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewPropertyAnimator;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import java.util.Iterator;
import java.util.LinkedHashSet;
import t.AbstractC0741a;
import u.AbstractC0743a;

/* JADX INFO: loaded from: classes2.dex */
public class HideBottomViewOnScrollBehavior<V extends View> extends CoordinatorLayout.Behavior<V> {

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public static final int f1601j = AbstractC0741a.f6523w;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public static final int f1602k = AbstractC0741a.f6525y;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public static final int f1603l = AbstractC0741a.f6489D;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final LinkedHashSet f1604a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public int f1605b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public int f1606c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public TimeInterpolator f1607d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public TimeInterpolator f1608e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public int f1609f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public int f1610g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public int f1611h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public ViewPropertyAnimator f1612i;

    public class a extends AnimatorListenerAdapter {
        public a() {
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator animator) {
            HideBottomViewOnScrollBehavior.this.f1612i = null;
        }
    }

    public HideBottomViewOnScrollBehavior() {
        this.f1604a = new LinkedHashSet();
        this.f1609f = 0;
        this.f1610g = 2;
        this.f1611h = 0;
    }

    public final void b(View view, int i2, long j2, TimeInterpolator timeInterpolator) {
        this.f1612i = view.animate().translationY(i2).setInterpolator(timeInterpolator).setDuration(j2).setListener(new a());
    }

    public boolean c() {
        return this.f1610g == 1;
    }

    public boolean d() {
        return this.f1610g == 2;
    }

    public void e(View view) {
        f(view, true);
    }

    public void f(View view, boolean z2) {
        if (c()) {
            return;
        }
        ViewPropertyAnimator viewPropertyAnimator = this.f1612i;
        if (viewPropertyAnimator != null) {
            viewPropertyAnimator.cancel();
            view.clearAnimation();
        }
        i(view, 1);
        int i2 = this.f1609f + this.f1611h;
        if (z2) {
            b(view, i2, this.f1606c, this.f1608e);
        } else {
            view.setTranslationY(i2);
        }
    }

    public void g(View view) {
        h(view, true);
    }

    public void h(View view, boolean z2) {
        if (d()) {
            return;
        }
        ViewPropertyAnimator viewPropertyAnimator = this.f1612i;
        if (viewPropertyAnimator != null) {
            viewPropertyAnimator.cancel();
            view.clearAnimation();
        }
        i(view, 2);
        if (z2) {
            b(view, 0, this.f1605b, this.f1607d);
        } else {
            view.setTranslationY(0);
        }
    }

    public final void i(View view, int i2) {
        this.f1610g = i2;
        Iterator it = this.f1604a.iterator();
        if (it.hasNext()) {
            android.support.v4.media.a.a(it.next());
            throw null;
        }
    }

    @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.Behavior
    public boolean onLayoutChild(CoordinatorLayout coordinatorLayout, View view, int i2) {
        this.f1609f = view.getMeasuredHeight() + ((ViewGroup.MarginLayoutParams) view.getLayoutParams()).bottomMargin;
        this.f1605b = d.f(view.getContext(), f1601j, 225);
        this.f1606c = d.f(view.getContext(), f1602k, 175);
        Context context = view.getContext();
        int i3 = f1603l;
        this.f1607d = d.g(context, i3, AbstractC0743a.f6837d);
        this.f1608e = d.g(view.getContext(), i3, AbstractC0743a.f6836c);
        return super.onLayoutChild(coordinatorLayout, view, i2);
    }

    @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.Behavior
    public void onNestedScroll(CoordinatorLayout coordinatorLayout, View view, View view2, int i2, int i3, int i4, int i5, int i6, int[] iArr) {
        if (i3 > 0) {
            e(view);
        } else if (i3 < 0) {
            g(view);
        }
    }

    @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.Behavior
    public boolean onStartNestedScroll(CoordinatorLayout coordinatorLayout, View view, View view2, View view3, int i2, int i3) {
        return i2 == 2;
    }

    public HideBottomViewOnScrollBehavior(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f1604a = new LinkedHashSet();
        this.f1609f = 0;
        this.f1610g = 2;
        this.f1611h = 0;
    }
}
