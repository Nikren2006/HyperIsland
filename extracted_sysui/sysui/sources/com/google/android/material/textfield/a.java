package com.google.android.material.textfield;

import H.j;
import H.n;
import Q.f;
import Q.g;
import Q.p;
import Q.r;
import Q.s;
import Q.v;
import Q.x;
import android.content.res.ColorStateList;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.accessibility.AccessibilityManager;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.appcompat.widget.TintTypedArray;
import androidx.core.graphics.drawable.DrawableCompat;
import androidx.core.view.GravityCompat;
import androidx.core.view.MarginLayoutParamsCompat;
import androidx.core.view.ViewCompat;
import androidx.core.view.accessibility.AccessibilityManagerCompat;
import androidx.core.widget.TextViewCompat;
import com.google.android.material.internal.CheckableImageButton;
import com.google.android.material.textfield.TextInputLayout;
import java.util.Iterator;
import java.util.LinkedHashSet;
import t.e;
import t.h;

/* JADX INFO: loaded from: classes2.dex */
public class a extends LinearLayout {

    /* JADX INFO: renamed from: A, reason: collision with root package name */
    public final TextWatcher f2210A;

    /* JADX INFO: renamed from: B, reason: collision with root package name */
    public final TextInputLayout.f f2211B;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final TextInputLayout f2212a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final FrameLayout f2213b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final CheckableImageButton f2214c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public ColorStateList f2215d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public PorterDuff.Mode f2216e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public View.OnLongClickListener f2217f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final CheckableImageButton f2218g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final d f2219h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public int f2220i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public final LinkedHashSet f2221j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public ColorStateList f2222k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public PorterDuff.Mode f2223l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public int f2224m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public ImageView.ScaleType f2225n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public View.OnLongClickListener f2226o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public CharSequence f2227p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public final TextView f2228q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public boolean f2229r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public EditText f2230s;

    /* JADX INFO: renamed from: x, reason: collision with root package name */
    public final AccessibilityManager f2231x;

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    public AccessibilityManagerCompat.TouchExplorationStateChangeListener f2232y;

    /* JADX INFO: renamed from: com.google.android.material.textfield.a$a, reason: collision with other inner class name */
    public class C0059a extends j {
        public C0059a() {
        }

        @Override // android.text.TextWatcher
        public void afterTextChanged(Editable editable) {
            a.this.m().a(editable);
        }

        @Override // H.j, android.text.TextWatcher
        public void beforeTextChanged(CharSequence charSequence, int i2, int i3, int i4) {
            a.this.m().b(charSequence, i2, i3, i4);
        }
    }

    public class b implements TextInputLayout.f {
        public b() {
        }

        @Override // com.google.android.material.textfield.TextInputLayout.f
        public void a(TextInputLayout textInputLayout) {
            if (a.this.f2230s == textInputLayout.getEditText()) {
                return;
            }
            if (a.this.f2230s != null) {
                a.this.f2230s.removeTextChangedListener(a.this.f2210A);
                if (a.this.f2230s.getOnFocusChangeListener() == a.this.m().e()) {
                    a.this.f2230s.setOnFocusChangeListener(null);
                }
            }
            a.this.f2230s = textInputLayout.getEditText();
            if (a.this.f2230s != null) {
                a.this.f2230s.addTextChangedListener(a.this.f2210A);
            }
            a.this.m().n(a.this.f2230s);
            a aVar = a.this;
            aVar.h0(aVar.m());
        }
    }

    public class c implements View.OnAttachStateChangeListener {
        public c() {
        }

        @Override // android.view.View.OnAttachStateChangeListener
        public void onViewAttachedToWindow(View view) {
            a.this.g();
        }

        @Override // android.view.View.OnAttachStateChangeListener
        public void onViewDetachedFromWindow(View view) {
            a.this.M();
        }
    }

    public static class d {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final SparseArray f2236a = new SparseArray();

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final a f2237b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final int f2238c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public final int f2239d;

        public d(a aVar, TintTypedArray tintTypedArray) {
            this.f2237b = aVar;
            this.f2238c = tintTypedArray.getResourceId(t.j.P5, 0);
            this.f2239d = tintTypedArray.getResourceId(t.j.n6, 0);
        }

        public final r b(int i2) {
            if (i2 == -1) {
                return new g(this.f2237b);
            }
            if (i2 == 0) {
                return new v(this.f2237b);
            }
            if (i2 == 1) {
                return new x(this.f2237b, this.f2239d);
            }
            if (i2 == 2) {
                return new f(this.f2237b);
            }
            if (i2 == 3) {
                return new p(this.f2237b);
            }
            throw new IllegalArgumentException("Invalid end icon mode: " + i2);
        }

        public r c(int i2) {
            r rVar = (r) this.f2236a.get(i2);
            if (rVar != null) {
                return rVar;
            }
            r rVarB = b(i2);
            this.f2236a.append(i2, rVarB);
            return rVarB;
        }
    }

    public a(TextInputLayout textInputLayout, TintTypedArray tintTypedArray) {
        super(textInputLayout.getContext());
        this.f2220i = 0;
        this.f2221j = new LinkedHashSet();
        this.f2210A = new C0059a();
        b bVar = new b();
        this.f2211B = bVar;
        this.f2231x = (AccessibilityManager) getContext().getSystemService("accessibility");
        this.f2212a = textInputLayout;
        setVisibility(8);
        setOrientation(0);
        setLayoutParams(new FrameLayout.LayoutParams(-2, -1, GravityCompat.END));
        FrameLayout frameLayout = new FrameLayout(getContext());
        this.f2213b = frameLayout;
        frameLayout.setVisibility(8);
        frameLayout.setLayoutParams(new LinearLayout.LayoutParams(-2, -1));
        LayoutInflater layoutInflaterFrom = LayoutInflater.from(getContext());
        CheckableImageButton checkableImageButtonI = i(this, layoutInflaterFrom, e.f6602I);
        this.f2214c = checkableImageButtonI;
        CheckableImageButton checkableImageButtonI2 = i(frameLayout, layoutInflaterFrom, e.f6601H);
        this.f2218g = checkableImageButtonI2;
        this.f2219h = new d(this, tintTypedArray);
        AppCompatTextView appCompatTextView = new AppCompatTextView(getContext());
        this.f2228q = appCompatTextView;
        C(tintTypedArray);
        B(tintTypedArray);
        D(tintTypedArray);
        frameLayout.addView(checkableImageButtonI2);
        addView(appCompatTextView);
        addView(frameLayout);
        addView(checkableImageButtonI);
        textInputLayout.i(bVar);
        addOnAttachStateChangeListener(new c());
    }

    public boolean A() {
        return this.f2220i != 0;
    }

    public final void B(TintTypedArray tintTypedArray) {
        int i2 = t.j.o6;
        if (!tintTypedArray.hasValue(i2)) {
            int i3 = t.j.T5;
            if (tintTypedArray.hasValue(i3)) {
                this.f2222k = L.c.b(getContext(), tintTypedArray, i3);
            }
            int i4 = t.j.U5;
            if (tintTypedArray.hasValue(i4)) {
                this.f2223l = n.i(tintTypedArray.getInt(i4, -1), null);
            }
        }
        int i5 = t.j.R5;
        if (tintTypedArray.hasValue(i5)) {
            U(tintTypedArray.getInt(i5, 0));
            int i6 = t.j.O5;
            if (tintTypedArray.hasValue(i6)) {
                Q(tintTypedArray.getText(i6));
            }
            O(tintTypedArray.getBoolean(t.j.N5, true));
        } else if (tintTypedArray.hasValue(i2)) {
            int i7 = t.j.p6;
            if (tintTypedArray.hasValue(i7)) {
                this.f2222k = L.c.b(getContext(), tintTypedArray, i7);
            }
            int i8 = t.j.q6;
            if (tintTypedArray.hasValue(i8)) {
                this.f2223l = n.i(tintTypedArray.getInt(i8, -1), null);
            }
            U(tintTypedArray.getBoolean(i2, false) ? 1 : 0);
            Q(tintTypedArray.getText(t.j.m6));
        }
        T(tintTypedArray.getDimensionPixelSize(t.j.Q5, getResources().getDimensionPixelSize(t.c.f6551S)));
        int i9 = t.j.S5;
        if (tintTypedArray.hasValue(i9)) {
            X(s.b(tintTypedArray.getInt(i9, -1)));
        }
    }

    public final void C(TintTypedArray tintTypedArray) {
        int i2 = t.j.Z5;
        if (tintTypedArray.hasValue(i2)) {
            this.f2215d = L.c.b(getContext(), tintTypedArray, i2);
        }
        int i3 = t.j.a6;
        if (tintTypedArray.hasValue(i3)) {
            this.f2216e = n.i(tintTypedArray.getInt(i3, -1), null);
        }
        int i4 = t.j.Y5;
        if (tintTypedArray.hasValue(i4)) {
            c0(tintTypedArray.getDrawable(i4));
        }
        this.f2214c.setContentDescription(getResources().getText(h.f6660f));
        ViewCompat.setImportantForAccessibility(this.f2214c, 2);
        this.f2214c.setClickable(false);
        this.f2214c.setPressable(false);
        this.f2214c.setFocusable(false);
    }

    public final void D(TintTypedArray tintTypedArray) {
        this.f2228q.setVisibility(8);
        this.f2228q.setId(e.f6608O);
        this.f2228q.setLayoutParams(new LinearLayout.LayoutParams(-2, -2, 80.0f));
        ViewCompat.setAccessibilityLiveRegion(this.f2228q, 1);
        q0(tintTypedArray.getResourceId(t.j.F6, 0));
        int i2 = t.j.G6;
        if (tintTypedArray.hasValue(i2)) {
            r0(tintTypedArray.getColorStateList(i2));
        }
        p0(tintTypedArray.getText(t.j.E6));
    }

    public boolean E() {
        return A() && this.f2218g.isChecked();
    }

    public boolean F() {
        return this.f2213b.getVisibility() == 0 && this.f2218g.getVisibility() == 0;
    }

    public boolean G() {
        return this.f2214c.getVisibility() == 0;
    }

    public void H(boolean z2) {
        this.f2229r = z2;
        y0();
    }

    public void I() {
        w0();
        K();
        J();
        if (m().t()) {
            u0(this.f2212a.d0());
        }
    }

    public void J() {
        s.d(this.f2212a, this.f2218g, this.f2222k);
    }

    public void K() {
        s.d(this.f2212a, this.f2214c, this.f2215d);
    }

    public void L(boolean z2) {
        boolean z3;
        boolean zIsActivated;
        boolean zIsChecked;
        r rVarM = m();
        boolean z4 = true;
        if (!rVarM.l() || (zIsChecked = this.f2218g.isChecked()) == rVarM.m()) {
            z3 = false;
        } else {
            this.f2218g.setChecked(!zIsChecked);
            z3 = true;
        }
        if (!rVarM.j() || (zIsActivated = this.f2218g.isActivated()) == rVarM.k()) {
            z4 = z3;
        } else {
            N(!zIsActivated);
        }
        if (z2 || z4) {
            J();
        }
    }

    public final void M() {
        AccessibilityManager accessibilityManager;
        AccessibilityManagerCompat.TouchExplorationStateChangeListener touchExplorationStateChangeListener = this.f2232y;
        if (touchExplorationStateChangeListener == null || (accessibilityManager = this.f2231x) == null) {
            return;
        }
        AccessibilityManagerCompat.removeTouchExplorationStateChangeListener(accessibilityManager, touchExplorationStateChangeListener);
    }

    public void N(boolean z2) {
        this.f2218g.setActivated(z2);
    }

    public void O(boolean z2) {
        this.f2218g.setCheckable(z2);
    }

    public void P(int i2) {
        Q(i2 != 0 ? getResources().getText(i2) : null);
    }

    public void Q(CharSequence charSequence) {
        if (l() != charSequence) {
            this.f2218g.setContentDescription(charSequence);
        }
    }

    public void R(int i2) {
        S(i2 != 0 ? AppCompatResources.getDrawable(getContext(), i2) : null);
    }

    public void S(Drawable drawable) {
        this.f2218g.setImageDrawable(drawable);
        if (drawable != null) {
            s.a(this.f2212a, this.f2218g, this.f2222k, this.f2223l);
            J();
        }
    }

    public void T(int i2) {
        if (i2 < 0) {
            throw new IllegalArgumentException("endIconSize cannot be less than 0");
        }
        if (i2 != this.f2224m) {
            this.f2224m = i2;
            s.g(this.f2218g, i2);
            s.g(this.f2214c, i2);
        }
    }

    public void U(int i2) {
        if (this.f2220i == i2) {
            return;
        }
        t0(m());
        int i3 = this.f2220i;
        this.f2220i = i2;
        j(i3);
        a0(i2 != 0);
        r rVarM = m();
        R(t(rVarM));
        P(rVarM.c());
        O(rVarM.l());
        if (!rVarM.i(this.f2212a.getBoxBackgroundMode())) {
            throw new IllegalStateException("The current box background mode " + this.f2212a.getBoxBackgroundMode() + " is not supported by the end icon mode " + i2);
        }
        s0(rVarM);
        V(rVarM.f());
        EditText editText = this.f2230s;
        if (editText != null) {
            rVarM.n(editText);
            h0(rVarM);
        }
        s.a(this.f2212a, this.f2218g, this.f2222k, this.f2223l);
        L(true);
    }

    public void V(View.OnClickListener onClickListener) {
        s.h(this.f2218g, onClickListener, this.f2226o);
    }

    public void W(View.OnLongClickListener onLongClickListener) {
        this.f2226o = onLongClickListener;
        s.i(this.f2218g, onLongClickListener);
    }

    public void X(ImageView.ScaleType scaleType) {
        this.f2225n = scaleType;
        s.j(this.f2218g, scaleType);
        s.j(this.f2214c, scaleType);
    }

    public void Y(ColorStateList colorStateList) {
        if (this.f2222k != colorStateList) {
            this.f2222k = colorStateList;
            s.a(this.f2212a, this.f2218g, colorStateList, this.f2223l);
        }
    }

    public void Z(PorterDuff.Mode mode) {
        if (this.f2223l != mode) {
            this.f2223l = mode;
            s.a(this.f2212a, this.f2218g, this.f2222k, mode);
        }
    }

    public void a0(boolean z2) {
        if (F() != z2) {
            this.f2218g.setVisibility(z2 ? 0 : 8);
            v0();
            x0();
            this.f2212a.o0();
        }
    }

    public void b0(int i2) {
        c0(i2 != 0 ? AppCompatResources.getDrawable(getContext(), i2) : null);
        K();
    }

    public void c0(Drawable drawable) {
        this.f2214c.setImageDrawable(drawable);
        w0();
        s.a(this.f2212a, this.f2214c, this.f2215d, this.f2216e);
    }

    public void d0(View.OnClickListener onClickListener) {
        s.h(this.f2214c, onClickListener, this.f2217f);
    }

    public void e0(View.OnLongClickListener onLongClickListener) {
        this.f2217f = onLongClickListener;
        s.i(this.f2214c, onLongClickListener);
    }

    public void f0(ColorStateList colorStateList) {
        if (this.f2215d != colorStateList) {
            this.f2215d = colorStateList;
            s.a(this.f2212a, this.f2214c, colorStateList, this.f2216e);
        }
    }

    public final void g() {
        if (this.f2232y == null || this.f2231x == null || !ViewCompat.isAttachedToWindow(this)) {
            return;
        }
        AccessibilityManagerCompat.addTouchExplorationStateChangeListener(this.f2231x, this.f2232y);
    }

    public void g0(PorterDuff.Mode mode) {
        if (this.f2216e != mode) {
            this.f2216e = mode;
            s.a(this.f2212a, this.f2214c, this.f2215d, mode);
        }
    }

    public void h() {
        this.f2218g.performClick();
        this.f2218g.jumpDrawablesToCurrentState();
    }

    public final void h0(r rVar) {
        if (this.f2230s == null) {
            return;
        }
        if (rVar.e() != null) {
            this.f2230s.setOnFocusChangeListener(rVar.e());
        }
        if (rVar.g() != null) {
            this.f2218g.setOnFocusChangeListener(rVar.g());
        }
    }

    public final CheckableImageButton i(ViewGroup viewGroup, LayoutInflater layoutInflater, int i2) {
        CheckableImageButton checkableImageButton = (CheckableImageButton) layoutInflater.inflate(t.g.f6638b, viewGroup, false);
        checkableImageButton.setId(i2);
        s.e(checkableImageButton);
        if (L.c.g(getContext())) {
            MarginLayoutParamsCompat.setMarginStart((ViewGroup.MarginLayoutParams) checkableImageButton.getLayoutParams(), 0);
        }
        return checkableImageButton;
    }

    public void i0(int i2) {
        j0(i2 != 0 ? getResources().getText(i2) : null);
    }

    public final void j(int i2) {
        Iterator it = this.f2221j.iterator();
        if (it.hasNext()) {
            android.support.v4.media.a.a(it.next());
            throw null;
        }
    }

    public void j0(CharSequence charSequence) {
        this.f2218g.setContentDescription(charSequence);
    }

    public CheckableImageButton k() {
        if (G()) {
            return this.f2214c;
        }
        if (A() && F()) {
            return this.f2218g;
        }
        return null;
    }

    public void k0(int i2) {
        l0(i2 != 0 ? AppCompatResources.getDrawable(getContext(), i2) : null);
    }

    public CharSequence l() {
        return this.f2218g.getContentDescription();
    }

    public void l0(Drawable drawable) {
        this.f2218g.setImageDrawable(drawable);
    }

    public r m() {
        return this.f2219h.c(this.f2220i);
    }

    public void m0(boolean z2) {
        if (z2 && this.f2220i != 1) {
            U(1);
        } else {
            if (z2) {
                return;
            }
            U(0);
        }
    }

    public Drawable n() {
        return this.f2218g.getDrawable();
    }

    public void n0(ColorStateList colorStateList) {
        this.f2222k = colorStateList;
        s.a(this.f2212a, this.f2218g, colorStateList, this.f2223l);
    }

    public int o() {
        return this.f2224m;
    }

    public void o0(PorterDuff.Mode mode) {
        this.f2223l = mode;
        s.a(this.f2212a, this.f2218g, this.f2222k, mode);
    }

    public int p() {
        return this.f2220i;
    }

    public void p0(CharSequence charSequence) {
        this.f2227p = TextUtils.isEmpty(charSequence) ? null : charSequence;
        this.f2228q.setText(charSequence);
        y0();
    }

    public ImageView.ScaleType q() {
        return this.f2225n;
    }

    public void q0(int i2) {
        TextViewCompat.setTextAppearance(this.f2228q, i2);
    }

    public CheckableImageButton r() {
        return this.f2218g;
    }

    public void r0(ColorStateList colorStateList) {
        this.f2228q.setTextColor(colorStateList);
    }

    public Drawable s() {
        return this.f2214c.getDrawable();
    }

    public final void s0(r rVar) {
        rVar.s();
        this.f2232y = rVar.h();
        g();
    }

    public final int t(r rVar) {
        int i2 = this.f2219h.f2238c;
        return i2 == 0 ? rVar.d() : i2;
    }

    public final void t0(r rVar) {
        M();
        this.f2232y = null;
        rVar.u();
    }

    public CharSequence u() {
        return this.f2218g.getContentDescription();
    }

    public final void u0(boolean z2) {
        if (!z2 || n() == null) {
            s.a(this.f2212a, this.f2218g, this.f2222k, this.f2223l);
            return;
        }
        Drawable drawableMutate = DrawableCompat.wrap(n()).mutate();
        DrawableCompat.setTint(drawableMutate, this.f2212a.getErrorCurrentTextColors());
        this.f2218g.setImageDrawable(drawableMutate);
    }

    public Drawable v() {
        return this.f2218g.getDrawable();
    }

    public final void v0() {
        this.f2213b.setVisibility((this.f2218g.getVisibility() != 0 || G()) ? 8 : 0);
        setVisibility((F() || G() || ((this.f2227p == null || this.f2229r) ? '\b' : (char) 0) == 0) ? 0 : 8);
    }

    public CharSequence w() {
        return this.f2227p;
    }

    public final void w0() {
        this.f2214c.setVisibility(s() != null && this.f2212a.N() && this.f2212a.d0() ? 0 : 8);
        v0();
        x0();
        if (A()) {
            return;
        }
        this.f2212a.o0();
    }

    public ColorStateList x() {
        return this.f2228q.getTextColors();
    }

    public void x0() {
        if (this.f2212a.f2163d == null) {
            return;
        }
        ViewCompat.setPaddingRelative(this.f2228q, getContext().getResources().getDimensionPixelSize(t.c.f6535C), this.f2212a.f2163d.getPaddingTop(), (F() || G()) ? 0 : ViewCompat.getPaddingEnd(this.f2212a.f2163d), this.f2212a.f2163d.getPaddingBottom());
    }

    public int y() {
        return ViewCompat.getPaddingEnd(this) + ViewCompat.getPaddingEnd(this.f2228q) + ((F() || G()) ? this.f2218g.getMeasuredWidth() + MarginLayoutParamsCompat.getMarginStart((ViewGroup.MarginLayoutParams) this.f2218g.getLayoutParams()) : 0);
    }

    public final void y0() {
        int visibility = this.f2228q.getVisibility();
        int i2 = (this.f2227p == null || this.f2229r) ? 8 : 0;
        if (visibility != i2) {
            m().q(i2 == 0);
        }
        v0();
        this.f2228q.setVisibility(i2);
        this.f2212a.o0();
    }

    public TextView z() {
        return this.f2228q;
    }
}
