package Q;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.TimeInterpolator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.text.Editable;
import android.view.View;
import android.widget.EditText;
import miui.systemui.controlcenter.panel.secondary.SecondaryParamsKt;
import t.AbstractC0741a;
import u.AbstractC0743a;

/* JADX INFO: loaded from: classes2.dex */
public class f extends r {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final int f592e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final int f593f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final TimeInterpolator f594g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final TimeInterpolator f595h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public EditText f596i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public final View.OnClickListener f597j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public final View.OnFocusChangeListener f598k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public AnimatorSet f599l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public ValueAnimator f600m;

    public class a extends AnimatorListenerAdapter {
        public a() {
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationStart(Animator animator) {
            f.this.f629b.a0(true);
        }
    }

    public class b extends AnimatorListenerAdapter {
        public b() {
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator animator) {
            f.this.f629b.a0(false);
        }
    }

    public f(com.google.android.material.textfield.a aVar) {
        super(aVar);
        this.f597j = new View.OnClickListener() { // from class: Q.a
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f587a.G(view);
            }
        };
        this.f598k = new View.OnFocusChangeListener() { // from class: Q.b
            @Override // android.view.View.OnFocusChangeListener
            public final void onFocusChange(View view, boolean z2) {
                this.f588a.H(view, z2);
            }
        };
        Context context = aVar.getContext();
        int i2 = AbstractC0741a.f6486A;
        this.f592e = J.d.f(context, i2, 100);
        this.f593f = J.d.f(aVar.getContext(), i2, SecondaryParamsKt.FROM_BT);
        this.f594g = J.d.g(aVar.getContext(), AbstractC0741a.f6490E, AbstractC0743a.f6834a);
        this.f595h = J.d.g(aVar.getContext(), AbstractC0741a.f6489D, AbstractC0743a.f6837d);
    }

    public final void A(boolean z2) {
        boolean z3 = this.f629b.F() == z2;
        if (z2 && !this.f599l.isRunning()) {
            this.f600m.cancel();
            this.f599l.start();
            if (z3) {
                this.f599l.end();
                return;
            }
            return;
        }
        if (z2) {
            return;
        }
        this.f599l.cancel();
        this.f600m.start();
        if (z3) {
            this.f600m.end();
        }
    }

    public final ValueAnimator B(float... fArr) {
        ValueAnimator valueAnimatorOfFloat = ValueAnimator.ofFloat(fArr);
        valueAnimatorOfFloat.setInterpolator(this.f594g);
        valueAnimatorOfFloat.setDuration(this.f592e);
        valueAnimatorOfFloat.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: Q.c
            @Override // android.animation.ValueAnimator.AnimatorUpdateListener
            public final void onAnimationUpdate(ValueAnimator valueAnimator) {
                this.f589a.E(valueAnimator);
            }
        });
        return valueAnimatorOfFloat;
    }

    public final ValueAnimator C() {
        ValueAnimator valueAnimatorOfFloat = ValueAnimator.ofFloat(0.8f, 1.0f);
        valueAnimatorOfFloat.setInterpolator(this.f595h);
        valueAnimatorOfFloat.setDuration(this.f593f);
        valueAnimatorOfFloat.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: Q.e
            @Override // android.animation.ValueAnimator.AnimatorUpdateListener
            public final void onAnimationUpdate(ValueAnimator valueAnimator) {
                this.f591a.F(valueAnimator);
            }
        });
        return valueAnimatorOfFloat;
    }

    public final void D() {
        ValueAnimator valueAnimatorC = C();
        ValueAnimator valueAnimatorB = B(0.0f, 1.0f);
        AnimatorSet animatorSet = new AnimatorSet();
        this.f599l = animatorSet;
        animatorSet.playTogether(valueAnimatorC, valueAnimatorB);
        this.f599l.addListener(new a());
        ValueAnimator valueAnimatorB2 = B(1.0f, 0.0f);
        this.f600m = valueAnimatorB2;
        valueAnimatorB2.addListener(new b());
    }

    public final /* synthetic */ void E(ValueAnimator valueAnimator) {
        this.f631d.setAlpha(((Float) valueAnimator.getAnimatedValue()).floatValue());
    }

    public final /* synthetic */ void F(ValueAnimator valueAnimator) {
        float fFloatValue = ((Float) valueAnimator.getAnimatedValue()).floatValue();
        this.f631d.setScaleX(fFloatValue);
        this.f631d.setScaleY(fFloatValue);
    }

    public final /* synthetic */ void G(View view) {
        EditText editText = this.f596i;
        if (editText == null) {
            return;
        }
        Editable text = editText.getText();
        if (text != null) {
            text.clear();
        }
        r();
    }

    public final /* synthetic */ void H(View view, boolean z2) {
        A(J());
    }

    public final /* synthetic */ void I() {
        A(true);
    }

    public final boolean J() {
        EditText editText = this.f596i;
        return editText != null && (editText.hasFocus() || this.f631d.hasFocus()) && this.f596i.getText().length() > 0;
    }

    @Override // Q.r
    public void a(Editable editable) {
        if (this.f629b.w() != null) {
            return;
        }
        A(J());
    }

    @Override // Q.r
    public int c() {
        return t.h.f6659e;
    }

    @Override // Q.r
    public int d() {
        return t.d.f6592i;
    }

    @Override // Q.r
    public View.OnFocusChangeListener e() {
        return this.f598k;
    }

    @Override // Q.r
    public View.OnClickListener f() {
        return this.f597j;
    }

    @Override // Q.r
    public View.OnFocusChangeListener g() {
        return this.f598k;
    }

    @Override // Q.r
    public void n(EditText editText) {
        this.f596i = editText;
        this.f628a.setEndIconVisible(J());
    }

    @Override // Q.r
    public void q(boolean z2) {
        if (this.f629b.w() == null) {
            return;
        }
        A(z2);
    }

    @Override // Q.r
    public void s() {
        D();
    }

    @Override // Q.r
    public void u() {
        EditText editText = this.f596i;
        if (editText != null) {
            editText.post(new Runnable() { // from class: Q.d
                @Override // java.lang.Runnable
                public final void run() {
                    this.f590a.I();
                }
            });
        }
    }
}
