package Q;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.TimeInterpolator;
import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Typeface;
import android.text.TextUtils;
import android.util.Property;
import android.view.View;
import android.view.ViewGroup;
import android.view.accessibility.AccessibilityNodeInfo;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.core.view.ViewCompat;
import androidx.core.widget.TextViewCompat;
import com.google.android.material.textfield.TextInputLayout;
import java.util.ArrayList;
import java.util.List;
import t.AbstractC0741a;
import u.AbstractC0743a;
import u.AbstractC0744b;

/* JADX INFO: loaded from: classes2.dex */
public final class t {

    /* JADX INFO: renamed from: A, reason: collision with root package name */
    public ColorStateList f632A;

    /* JADX INFO: renamed from: B, reason: collision with root package name */
    public Typeface f633B;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final int f634a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final int f635b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final int f636c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final TimeInterpolator f637d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final TimeInterpolator f638e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final TimeInterpolator f639f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final Context f640g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final TextInputLayout f641h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public LinearLayout f642i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public int f643j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public FrameLayout f644k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public Animator f645l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final float f646m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public int f647n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public int f648o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public CharSequence f649p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public boolean f650q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public TextView f651r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public CharSequence f652s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public int f653t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public int f654u;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public ColorStateList f655v;

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    public CharSequence f656w;

    /* JADX INFO: renamed from: x, reason: collision with root package name */
    public boolean f657x;

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    public TextView f658y;

    /* JADX INFO: renamed from: z, reason: collision with root package name */
    public int f659z;

    public class a extends AnimatorListenerAdapter {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ int f660a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final /* synthetic */ TextView f661b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final /* synthetic */ int f662c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public final /* synthetic */ TextView f663d;

        public a(int i2, TextView textView, int i3, TextView textView2) {
            this.f660a = i2;
            this.f661b = textView;
            this.f662c = i3;
            this.f663d = textView2;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator animator) {
            t.this.f647n = this.f660a;
            t.this.f645l = null;
            TextView textView = this.f661b;
            if (textView != null) {
                textView.setVisibility(4);
                if (this.f662c == 1 && t.this.f651r != null) {
                    t.this.f651r.setText((CharSequence) null);
                }
            }
            TextView textView2 = this.f663d;
            if (textView2 != null) {
                textView2.setTranslationY(0.0f);
                this.f663d.setAlpha(1.0f);
            }
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationStart(Animator animator) {
            TextView textView = this.f663d;
            if (textView != null) {
                textView.setVisibility(0);
                this.f663d.setAlpha(0.0f);
            }
        }
    }

    public class b extends View.AccessibilityDelegate {
        public b() {
        }

        @Override // android.view.View.AccessibilityDelegate
        public void onInitializeAccessibilityNodeInfo(View view, AccessibilityNodeInfo accessibilityNodeInfo) {
            super.onInitializeAccessibilityNodeInfo(view, accessibilityNodeInfo);
            EditText editText = t.this.f641h.getEditText();
            if (editText != null) {
                accessibilityNodeInfo.setLabeledBy(editText);
            }
        }
    }

    public t(TextInputLayout textInputLayout) {
        Context context = textInputLayout.getContext();
        this.f640g = context;
        this.f641h = textInputLayout;
        this.f646m = context.getResources().getDimensionPixelSize(t.c.f6562e);
        int i2 = AbstractC0741a.f6487B;
        this.f634a = J.d.f(context, i2, 217);
        this.f635b = J.d.f(context, AbstractC0741a.f6525y, 167);
        this.f636c = J.d.f(context, i2, 167);
        int i3 = AbstractC0741a.f6488C;
        this.f637d = J.d.g(context, i3, AbstractC0743a.f6837d);
        TimeInterpolator timeInterpolator = AbstractC0743a.f6834a;
        this.f638e = J.d.g(context, i3, timeInterpolator);
        this.f639f = J.d.g(context, AbstractC0741a.f6490E, timeInterpolator);
    }

    public boolean A() {
        return this.f650q;
    }

    public boolean B() {
        return this.f657x;
    }

    public void C(TextView textView, int i2) {
        FrameLayout frameLayout;
        if (this.f642i == null) {
            return;
        }
        if (!z(i2) || (frameLayout = this.f644k) == null) {
            this.f642i.removeView(textView);
        } else {
            frameLayout.removeView(textView);
        }
        int i3 = this.f643j - 1;
        this.f643j = i3;
        O(this.f642i, i3);
    }

    public final void D(int i2, int i3) {
        TextView textViewM;
        TextView textViewM2;
        if (i2 == i3) {
            return;
        }
        if (i3 != 0 && (textViewM2 = m(i3)) != null) {
            textViewM2.setVisibility(0);
            textViewM2.setAlpha(1.0f);
        }
        if (i2 != 0 && (textViewM = m(i2)) != null) {
            textViewM.setVisibility(4);
            if (i2 == 1) {
                textViewM.setText((CharSequence) null);
            }
        }
        this.f647n = i3;
    }

    public void E(int i2) {
        this.f653t = i2;
        TextView textView = this.f651r;
        if (textView != null) {
            ViewCompat.setAccessibilityLiveRegion(textView, i2);
        }
    }

    public void F(CharSequence charSequence) {
        this.f652s = charSequence;
        TextView textView = this.f651r;
        if (textView != null) {
            textView.setContentDescription(charSequence);
        }
    }

    public void G(boolean z2) {
        if (this.f650q == z2) {
            return;
        }
        h();
        if (z2) {
            AppCompatTextView appCompatTextView = new AppCompatTextView(this.f640g);
            this.f651r = appCompatTextView;
            appCompatTextView.setId(t.e.f6604K);
            this.f651r.setTextAlignment(5);
            Typeface typeface = this.f633B;
            if (typeface != null) {
                this.f651r.setTypeface(typeface);
            }
            H(this.f654u);
            I(this.f655v);
            F(this.f652s);
            E(this.f653t);
            this.f651r.setVisibility(4);
            e(this.f651r, 0);
        } else {
            w();
            C(this.f651r, 0);
            this.f651r = null;
            this.f641h.p0();
            this.f641h.A0();
        }
        this.f650q = z2;
    }

    public void H(int i2) {
        this.f654u = i2;
        TextView textView = this.f651r;
        if (textView != null) {
            this.f641h.c0(textView, i2);
        }
    }

    public void I(ColorStateList colorStateList) {
        this.f655v = colorStateList;
        TextView textView = this.f651r;
        if (textView == null || colorStateList == null) {
            return;
        }
        textView.setTextColor(colorStateList);
    }

    public void J(int i2) {
        this.f659z = i2;
        TextView textView = this.f658y;
        if (textView != null) {
            TextViewCompat.setTextAppearance(textView, i2);
        }
    }

    public void K(boolean z2) {
        if (this.f657x == z2) {
            return;
        }
        h();
        if (z2) {
            AppCompatTextView appCompatTextView = new AppCompatTextView(this.f640g);
            this.f658y = appCompatTextView;
            appCompatTextView.setId(t.e.f6605L);
            this.f658y.setTextAlignment(5);
            Typeface typeface = this.f633B;
            if (typeface != null) {
                this.f658y.setTypeface(typeface);
            }
            this.f658y.setVisibility(4);
            ViewCompat.setAccessibilityLiveRegion(this.f658y, 1);
            J(this.f659z);
            L(this.f632A);
            e(this.f658y, 1);
            this.f658y.setAccessibilityDelegate(new b());
        } else {
            x();
            C(this.f658y, 1);
            this.f658y = null;
            this.f641h.p0();
            this.f641h.A0();
        }
        this.f657x = z2;
    }

    public void L(ColorStateList colorStateList) {
        this.f632A = colorStateList;
        TextView textView = this.f658y;
        if (textView == null || colorStateList == null) {
            return;
        }
        textView.setTextColor(colorStateList);
    }

    public final void M(TextView textView, Typeface typeface) {
        if (textView != null) {
            textView.setTypeface(typeface);
        }
    }

    public void N(Typeface typeface) {
        if (typeface != this.f633B) {
            this.f633B = typeface;
            M(this.f651r, typeface);
            M(this.f658y, typeface);
        }
    }

    public final void O(ViewGroup viewGroup, int i2) {
        if (i2 == 0) {
            viewGroup.setVisibility(8);
        }
    }

    public final boolean P(TextView textView, CharSequence charSequence) {
        return ViewCompat.isLaidOut(this.f641h) && this.f641h.isEnabled() && !(this.f648o == this.f647n && textView != null && TextUtils.equals(textView.getText(), charSequence));
    }

    public void Q(CharSequence charSequence) {
        h();
        this.f649p = charSequence;
        this.f651r.setText(charSequence);
        int i2 = this.f647n;
        if (i2 != 1) {
            this.f648o = 1;
        }
        S(i2, this.f648o, P(this.f651r, charSequence));
    }

    public void R(CharSequence charSequence) {
        h();
        this.f656w = charSequence;
        this.f658y.setText(charSequence);
        int i2 = this.f647n;
        if (i2 != 2) {
            this.f648o = 2;
        }
        S(i2, this.f648o, P(this.f658y, charSequence));
    }

    public final void S(int i2, int i3, boolean z2) {
        if (i2 == i3) {
            return;
        }
        if (z2) {
            AnimatorSet animatorSet = new AnimatorSet();
            this.f645l = animatorSet;
            ArrayList arrayList = new ArrayList();
            i(arrayList, this.f657x, this.f658y, 2, i2, i3);
            i(arrayList, this.f650q, this.f651r, 1, i2, i3);
            AbstractC0744b.a(animatorSet, arrayList);
            animatorSet.addListener(new a(i3, m(i2), i2, m(i3)));
            animatorSet.start();
        } else {
            D(i2, i3);
        }
        this.f641h.p0();
        this.f641h.u0(z2);
        this.f641h.A0();
    }

    public void e(TextView textView, int i2) {
        if (this.f642i == null && this.f644k == null) {
            LinearLayout linearLayout = new LinearLayout(this.f640g);
            this.f642i = linearLayout;
            linearLayout.setOrientation(0);
            this.f641h.addView(this.f642i, -1, -2);
            this.f644k = new FrameLayout(this.f640g);
            this.f642i.addView(this.f644k, new LinearLayout.LayoutParams(0, -2, 1.0f));
            if (this.f641h.getEditText() != null) {
                f();
            }
        }
        if (z(i2)) {
            this.f644k.setVisibility(0);
            this.f644k.addView(textView);
        } else {
            this.f642i.addView(textView, new LinearLayout.LayoutParams(-2, -2));
        }
        this.f642i.setVisibility(0);
        this.f643j++;
    }

    public void f() {
        if (g()) {
            EditText editText = this.f641h.getEditText();
            boolean zG = L.c.g(this.f640g);
            LinearLayout linearLayout = this.f642i;
            int i2 = t.c.f6533A;
            ViewCompat.setPaddingRelative(linearLayout, v(zG, i2, ViewCompat.getPaddingStart(editText)), v(zG, t.c.f6534B, this.f640g.getResources().getDimensionPixelSize(t.c.f6583z)), v(zG, i2, ViewCompat.getPaddingEnd(editText)), 0);
        }
    }

    public final boolean g() {
        return (this.f642i == null || this.f641h.getEditText() == null) ? false : true;
    }

    public void h() {
        Animator animator = this.f645l;
        if (animator != null) {
            animator.cancel();
        }
    }

    public final void i(List list, boolean z2, TextView textView, int i2, int i3, int i4) {
        if (textView == null || !z2) {
            return;
        }
        if (i2 == i4 || i2 == i3) {
            ObjectAnimator objectAnimatorJ = j(textView, i4 == i2);
            if (i2 == i4 && i3 != 0) {
                objectAnimatorJ.setStartDelay(this.f636c);
            }
            list.add(objectAnimatorJ);
            if (i4 != i2 || i3 == 0) {
                return;
            }
            ObjectAnimator objectAnimatorK = k(textView);
            objectAnimatorK.setStartDelay(this.f636c);
            list.add(objectAnimatorK);
        }
    }

    public final ObjectAnimator j(TextView textView, boolean z2) {
        ObjectAnimator objectAnimatorOfFloat = ObjectAnimator.ofFloat(textView, (Property<TextView, Float>) View.ALPHA, z2 ? 1.0f : 0.0f);
        objectAnimatorOfFloat.setDuration(z2 ? this.f635b : this.f636c);
        objectAnimatorOfFloat.setInterpolator(z2 ? this.f638e : this.f639f);
        return objectAnimatorOfFloat;
    }

    public final ObjectAnimator k(TextView textView) {
        ObjectAnimator objectAnimatorOfFloat = ObjectAnimator.ofFloat(textView, (Property<TextView, Float>) View.TRANSLATION_Y, -this.f646m, 0.0f);
        objectAnimatorOfFloat.setDuration(this.f634a);
        objectAnimatorOfFloat.setInterpolator(this.f637d);
        return objectAnimatorOfFloat;
    }

    public boolean l() {
        return y(this.f648o);
    }

    public final TextView m(int i2) {
        if (i2 == 1) {
            return this.f651r;
        }
        if (i2 != 2) {
            return null;
        }
        return this.f658y;
    }

    public int n() {
        return this.f653t;
    }

    public CharSequence o() {
        return this.f652s;
    }

    public CharSequence p() {
        return this.f649p;
    }

    public int q() {
        TextView textView = this.f651r;
        if (textView != null) {
            return textView.getCurrentTextColor();
        }
        return -1;
    }

    public ColorStateList r() {
        TextView textView = this.f651r;
        if (textView != null) {
            return textView.getTextColors();
        }
        return null;
    }

    public CharSequence s() {
        return this.f656w;
    }

    public View t() {
        return this.f658y;
    }

    public int u() {
        TextView textView = this.f658y;
        if (textView != null) {
            return textView.getCurrentTextColor();
        }
        return -1;
    }

    public final int v(boolean z2, int i2, int i3) {
        return z2 ? this.f640g.getResources().getDimensionPixelSize(i2) : i3;
    }

    public void w() {
        this.f649p = null;
        h();
        if (this.f647n == 1) {
            if (!this.f657x || TextUtils.isEmpty(this.f656w)) {
                this.f648o = 0;
            } else {
                this.f648o = 2;
            }
        }
        S(this.f647n, this.f648o, P(this.f651r, ""));
    }

    public void x() {
        h();
        int i2 = this.f647n;
        if (i2 == 2) {
            this.f648o = 0;
        }
        S(i2, this.f648o, P(this.f658y, ""));
    }

    public final boolean y(int i2) {
        return (i2 != 1 || this.f651r == null || TextUtils.isEmpty(this.f649p)) ? false : true;
    }

    public boolean z(int i2) {
        return i2 == 0 || i2 == 1;
    }
}
