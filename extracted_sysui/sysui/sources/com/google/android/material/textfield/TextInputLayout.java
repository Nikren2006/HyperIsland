package com.google.android.material.textfield;

import H.n;
import O.k;
import Q.q;
import Q.t;
import Q.u;
import Q.y;
import android.R;
import android.animation.TimeInterpolator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Configuration;
import android.graphics.Canvas;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.graphics.drawable.RippleDrawable;
import android.graphics.drawable.StateListDrawable;
import android.os.Parcel;
import android.os.Parcelable;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.util.Log;
import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStructure;
import android.view.ViewTreeObserver;
import android.view.accessibility.AccessibilityEvent;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.ColorInt;
import androidx.annotation.ColorRes;
import androidx.annotation.DimenRes;
import androidx.annotation.DrawableRes;
import androidx.annotation.IntRange;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.Px;
import androidx.annotation.RequiresApi;
import androidx.annotation.StringRes;
import androidx.annotation.StyleRes;
import androidx.annotation.VisibleForTesting;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.appcompat.widget.AppCompatDrawableManager;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.appcompat.widget.DrawableUtils;
import androidx.appcompat.widget.TintTypedArray;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.drawable.DrawableCompat;
import androidx.core.text.BidiFormatter;
import androidx.core.view.AccessibilityDelegateCompat;
import androidx.core.view.MarginLayoutParamsCompat;
import androidx.core.view.ViewCompat;
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;
import androidx.core.widget.TextViewCompat;
import androidx.customview.view.AbsSavedState;
import androidx.transition.Fade;
import androidx.transition.TransitionManager;
import com.google.android.material.internal.CheckableImageButton;
import com.google.android.material.textfield.TextInputLayout;
import java.util.Iterator;
import java.util.LinkedHashSet;
import t.AbstractC0741a;
import t.h;
import t.i;
import t.j;
import u.AbstractC0743a;

/* JADX INFO: loaded from: classes2.dex */
public class TextInputLayout extends LinearLayout implements ViewTreeObserver.OnGlobalLayoutListener {

    /* JADX INFO: renamed from: H0, reason: collision with root package name */
    public static final int f2126H0 = i.f6682e;

    /* JADX INFO: renamed from: I0, reason: collision with root package name */
    public static final int[][] f2127I0 = {new int[]{R.attr.state_pressed}, new int[0]};

    /* JADX INFO: renamed from: A, reason: collision with root package name */
    public int f2128A;

    /* JADX INFO: renamed from: A0, reason: collision with root package name */
    public final H.a f2129A0;

    /* JADX INFO: renamed from: B, reason: collision with root package name */
    public Fade f2130B;

    /* JADX INFO: renamed from: B0, reason: collision with root package name */
    public boolean f2131B0;

    /* JADX INFO: renamed from: C0, reason: collision with root package name */
    public boolean f2132C0;

    /* JADX INFO: renamed from: D, reason: collision with root package name */
    public Fade f2133D;

    /* JADX INFO: renamed from: D0, reason: collision with root package name */
    public ValueAnimator f2134D0;

    /* JADX INFO: renamed from: E, reason: collision with root package name */
    public ColorStateList f2135E;

    /* JADX INFO: renamed from: E0, reason: collision with root package name */
    public boolean f2136E0;

    /* JADX INFO: renamed from: F, reason: collision with root package name */
    public ColorStateList f2137F;

    /* JADX INFO: renamed from: F0, reason: collision with root package name */
    public boolean f2138F0;

    /* JADX INFO: renamed from: G, reason: collision with root package name */
    public ColorStateList f2139G;

    /* JADX INFO: renamed from: G0, reason: collision with root package name */
    public boolean f2140G0;

    /* JADX INFO: renamed from: H, reason: collision with root package name */
    public ColorStateList f2141H;

    /* JADX INFO: renamed from: I, reason: collision with root package name */
    public boolean f2142I;

    /* JADX INFO: renamed from: J, reason: collision with root package name */
    public CharSequence f2143J;

    /* JADX INFO: renamed from: K, reason: collision with root package name */
    public boolean f2144K;

    /* JADX INFO: renamed from: L, reason: collision with root package name */
    public O.g f2145L;

    /* JADX INFO: renamed from: M, reason: collision with root package name */
    public O.g f2146M;

    /* JADX INFO: renamed from: N, reason: collision with root package name */
    public StateListDrawable f2147N;

    /* JADX INFO: renamed from: O, reason: collision with root package name */
    public boolean f2148O;

    /* JADX INFO: renamed from: P, reason: collision with root package name */
    public O.g f2149P;

    /* JADX INFO: renamed from: Q, reason: collision with root package name */
    public O.g f2150Q;

    /* JADX INFO: renamed from: R, reason: collision with root package name */
    public k f2151R;

    /* JADX INFO: renamed from: S, reason: collision with root package name */
    public boolean f2152S;

    /* JADX INFO: renamed from: T, reason: collision with root package name */
    public final int f2153T;

    /* JADX INFO: renamed from: U, reason: collision with root package name */
    public int f2154U;

    /* JADX INFO: renamed from: V, reason: collision with root package name */
    public int f2155V;

    /* JADX INFO: renamed from: W, reason: collision with root package name */
    public int f2156W;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final FrameLayout f2157a;

    /* JADX INFO: renamed from: a0, reason: collision with root package name */
    public int f2158a0;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final y f2159b;

    /* JADX INFO: renamed from: b0, reason: collision with root package name */
    public int f2160b0;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final com.google.android.material.textfield.a f2161c;

    /* JADX INFO: renamed from: c0, reason: collision with root package name */
    public int f2162c0;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public EditText f2163d;

    /* JADX INFO: renamed from: d0, reason: collision with root package name */
    public int f2164d0;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public CharSequence f2165e;

    /* JADX INFO: renamed from: e0, reason: collision with root package name */
    public final Rect f2166e0;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public int f2167f;

    /* JADX INFO: renamed from: f0, reason: collision with root package name */
    public final Rect f2168f0;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public int f2169g;

    /* JADX INFO: renamed from: g0, reason: collision with root package name */
    public final RectF f2170g0;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public int f2171h;

    /* JADX INFO: renamed from: h0, reason: collision with root package name */
    public Typeface f2172h0;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public int f2173i;

    /* JADX INFO: renamed from: i0, reason: collision with root package name */
    public Drawable f2174i0;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public final t f2175j;

    /* JADX INFO: renamed from: j0, reason: collision with root package name */
    public int f2176j0;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public boolean f2177k;

    /* JADX INFO: renamed from: k0, reason: collision with root package name */
    public final LinkedHashSet f2178k0;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public int f2179l;

    /* JADX INFO: renamed from: l0, reason: collision with root package name */
    public Drawable f2180l0;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public boolean f2181m;

    /* JADX INFO: renamed from: m0, reason: collision with root package name */
    public int f2182m0;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public e f2183n;

    /* JADX INFO: renamed from: n0, reason: collision with root package name */
    public Drawable f2184n0;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public TextView f2185o;

    /* JADX INFO: renamed from: o0, reason: collision with root package name */
    public ColorStateList f2186o0;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public int f2187p;

    /* JADX INFO: renamed from: p0, reason: collision with root package name */
    public ColorStateList f2188p0;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public int f2189q;

    /* JADX INFO: renamed from: q0, reason: collision with root package name */
    public int f2190q0;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public CharSequence f2191r;

    /* JADX INFO: renamed from: r0, reason: collision with root package name */
    public int f2192r0;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public boolean f2193s;

    /* JADX INFO: renamed from: s0, reason: collision with root package name */
    public int f2194s0;

    /* JADX INFO: renamed from: t0, reason: collision with root package name */
    public ColorStateList f2195t0;

    /* JADX INFO: renamed from: u0, reason: collision with root package name */
    public int f2196u0;

    /* JADX INFO: renamed from: v0, reason: collision with root package name */
    public int f2197v0;

    /* JADX INFO: renamed from: w0, reason: collision with root package name */
    public int f2198w0;

    /* JADX INFO: renamed from: x, reason: collision with root package name */
    public TextView f2199x;

    /* JADX INFO: renamed from: x0, reason: collision with root package name */
    public int f2200x0;

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    public ColorStateList f2201y;

    /* JADX INFO: renamed from: y0, reason: collision with root package name */
    public int f2202y0;

    /* JADX INFO: renamed from: z0, reason: collision with root package name */
    public boolean f2203z0;

    public class a implements TextWatcher {
        public a() {
        }

        @Override // android.text.TextWatcher
        public void afterTextChanged(Editable editable) {
            TextInputLayout.this.u0(!r0.f2138F0);
            TextInputLayout textInputLayout = TextInputLayout.this;
            if (textInputLayout.f2177k) {
                textInputLayout.k0(editable);
            }
            if (TextInputLayout.this.f2193s) {
                TextInputLayout.this.y0(editable);
            }
        }

        @Override // android.text.TextWatcher
        public void beforeTextChanged(CharSequence charSequence, int i2, int i3, int i4) {
        }

        @Override // android.text.TextWatcher
        public void onTextChanged(CharSequence charSequence, int i2, int i3, int i4) {
        }
    }

    public class b implements Runnable {
        public b() {
        }

        @Override // java.lang.Runnable
        public void run() {
            TextInputLayout.this.f2161c.h();
        }
    }

    public class c implements ValueAnimator.AnimatorUpdateListener {
        public c() {
        }

        @Override // android.animation.ValueAnimator.AnimatorUpdateListener
        public void onAnimationUpdate(ValueAnimator valueAnimator) {
            TextInputLayout.this.f2129A0.c0(((Float) valueAnimator.getAnimatedValue()).floatValue());
        }
    }

    public static class d extends AccessibilityDelegateCompat {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final TextInputLayout f2207a;

        public d(TextInputLayout textInputLayout) {
            this.f2207a = textInputLayout;
        }

        @Override // androidx.core.view.AccessibilityDelegateCompat
        public void onInitializeAccessibilityNodeInfo(View view, AccessibilityNodeInfoCompat accessibilityNodeInfoCompat) {
            super.onInitializeAccessibilityNodeInfo(view, accessibilityNodeInfoCompat);
            EditText editText = this.f2207a.getEditText();
            CharSequence text = editText != null ? editText.getText() : null;
            CharSequence hint = this.f2207a.getHint();
            CharSequence error = this.f2207a.getError();
            CharSequence placeholderText = this.f2207a.getPlaceholderText();
            int counterMaxLength = this.f2207a.getCounterMaxLength();
            CharSequence counterOverflowDescription = this.f2207a.getCounterOverflowDescription();
            boolean zIsEmpty = TextUtils.isEmpty(text);
            boolean zIsEmpty2 = TextUtils.isEmpty(hint);
            boolean zP = this.f2207a.P();
            boolean zIsEmpty3 = TextUtils.isEmpty(error);
            boolean z2 = (zIsEmpty3 && TextUtils.isEmpty(counterOverflowDescription)) ? false : true;
            String string = !zIsEmpty2 ? hint.toString() : "";
            this.f2207a.f2159b.A(accessibilityNodeInfoCompat);
            if (!zIsEmpty) {
                accessibilityNodeInfoCompat.setText(text);
            } else if (!TextUtils.isEmpty(string)) {
                accessibilityNodeInfoCompat.setText(string);
                if (!zP && placeholderText != null) {
                    accessibilityNodeInfoCompat.setText(string + ", " + ((Object) placeholderText));
                }
            } else if (placeholderText != null) {
                accessibilityNodeInfoCompat.setText(placeholderText);
            }
            if (!TextUtils.isEmpty(string)) {
                accessibilityNodeInfoCompat.setHintText(string);
                accessibilityNodeInfoCompat.setShowingHintText(zIsEmpty);
            }
            if (text == null || text.length() != counterMaxLength) {
                counterMaxLength = -1;
            }
            accessibilityNodeInfoCompat.setMaxTextLength(counterMaxLength);
            if (z2) {
                if (zIsEmpty3) {
                    error = counterOverflowDescription;
                }
                accessibilityNodeInfoCompat.setError(error);
            }
            View viewT = this.f2207a.f2175j.t();
            if (viewT != null) {
                accessibilityNodeInfoCompat.setLabelFor(viewT);
            }
            this.f2207a.f2161c.m().o(view, accessibilityNodeInfoCompat);
        }

        @Override // androidx.core.view.AccessibilityDelegateCompat
        public void onPopulateAccessibilityEvent(View view, AccessibilityEvent accessibilityEvent) {
            super.onPopulateAccessibilityEvent(view, accessibilityEvent);
            this.f2207a.f2161c.m().p(view, accessibilityEvent);
        }
    }

    public interface e {
        int a(Editable editable);
    }

    public interface f {
        void a(TextInputLayout textInputLayout);
    }

    public static class g extends AbsSavedState {
        public static final Parcelable.Creator<g> CREATOR = new a();

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public CharSequence f2208a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public boolean f2209b;

        public class a implements Parcelable.ClassLoaderCreator {
            @Override // android.os.Parcelable.Creator
            /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
            public g createFromParcel(Parcel parcel) {
                return new g(parcel, null);
            }

            @Override // android.os.Parcelable.ClassLoaderCreator
            /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
            public g createFromParcel(Parcel parcel, ClassLoader classLoader) {
                return new g(parcel, classLoader);
            }

            @Override // android.os.Parcelable.Creator
            /* JADX INFO: renamed from: c, reason: merged with bridge method [inline-methods] */
            public g[] newArray(int i2) {
                return new g[i2];
            }
        }

        public g(Parcelable parcelable) {
            super(parcelable);
        }

        public String toString() {
            return "TextInputLayout.SavedState{" + Integer.toHexString(System.identityHashCode(this)) + " error=" + ((Object) this.f2208a) + "}";
        }

        @Override // androidx.customview.view.AbsSavedState, android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i2) {
            super.writeToParcel(parcel, i2);
            TextUtils.writeToParcel(this.f2208a, parcel, i2);
            parcel.writeInt(this.f2209b ? 1 : 0);
        }

        public g(Parcel parcel, ClassLoader classLoader) {
            super(parcel, classLoader);
            this.f2208a = (CharSequence) TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(parcel);
            this.f2209b = parcel.readInt() == 1;
        }
    }

    public TextInputLayout(@NonNull Context context, @Nullable AttributeSet attributeSet) {
        this(context, attributeSet, AbstractC0741a.f6498M);
    }

    public static Drawable H(O.g gVar, int i2, int i3, int[][] iArr) {
        return new RippleDrawable(new ColorStateList(iArr, new int[]{C.a.j(i3, i2, 0.1f), i2}), gVar, gVar);
    }

    public static Drawable K(Context context, O.g gVar, int i2, int[][] iArr) {
        int iC = C.a.c(context, AbstractC0741a.f6511k, "TextInputLayout");
        O.g gVar2 = new O.g(gVar.A());
        int iJ = C.a.j(i2, iC, 0.1f);
        gVar2.T(new ColorStateList(iArr, new int[]{iJ, 0}));
        gVar2.setTint(iC);
        ColorStateList colorStateList = new ColorStateList(iArr, new int[]{iJ, iC});
        O.g gVar3 = new O.g(gVar.A());
        gVar3.setTint(-1);
        return new LayerDrawable(new Drawable[]{new RippleDrawable(colorStateList, gVar2, gVar3), gVar});
    }

    public static /* synthetic */ int T(Editable editable) {
        if (editable != null) {
            return editable.length();
        }
        return 0;
    }

    public static void Y(ViewGroup viewGroup, boolean z2) {
        int childCount = viewGroup.getChildCount();
        for (int i2 = 0; i2 < childCount; i2++) {
            View childAt = viewGroup.getChildAt(i2);
            childAt.setEnabled(z2);
            if (childAt instanceof ViewGroup) {
                Y((ViewGroup) childAt, z2);
            }
        }
    }

    @Nullable
    private Drawable getEditTextBoxBackground() {
        EditText editText = this.f2163d;
        if (!(editText instanceof AutoCompleteTextView) || q.a(editText)) {
            return this.f2145L;
        }
        int iD = C.a.d(this.f2163d, AbstractC0741a.f6506f);
        int i2 = this.f2154U;
        if (i2 == 2) {
            return K(getContext(), this.f2145L, iD, f2127I0);
        }
        if (i2 == 1) {
            return H(this.f2145L, this.f2164d0, iD, f2127I0);
        }
        return null;
    }

    private Drawable getOrCreateFilledDropDownMenuBackground() {
        if (this.f2147N == null) {
            StateListDrawable stateListDrawable = new StateListDrawable();
            this.f2147N = stateListDrawable;
            stateListDrawable.addState(new int[]{R.attr.state_above_anchor}, getOrCreateOutlinedDropDownMenuBackground());
            this.f2147N.addState(new int[0], G(false));
        }
        return this.f2147N;
    }

    private Drawable getOrCreateOutlinedDropDownMenuBackground() {
        if (this.f2146M == null) {
            this.f2146M = G(true);
        }
        return this.f2146M;
    }

    public static void l0(Context context, TextView textView, int i2, int i3, boolean z2) {
        textView.setContentDescription(context.getString(z2 ? h.f6657c : h.f6656b, Integer.valueOf(i2), Integer.valueOf(i3)));
    }

    private void setEditText(EditText editText) {
        if (this.f2163d != null) {
            throw new IllegalArgumentException("We already have an EditText, can only have one");
        }
        if (getEndIconMode() != 3 && !(editText instanceof TextInputEditText)) {
            Log.i("TextInputLayout", "EditText added is not a TextInputEditText. Please switch to using that class instead.");
        }
        this.f2163d = editText;
        int i2 = this.f2167f;
        if (i2 != -1) {
            setMinEms(i2);
        } else {
            setMinWidth(this.f2171h);
        }
        int i3 = this.f2169g;
        if (i3 != -1) {
            setMaxEms(i3);
        } else {
            setMaxWidth(this.f2173i);
        }
        this.f2148O = false;
        V();
        setTextInputAccessibilityDelegate(new d(this));
        this.f2129A0.i0(this.f2163d.getTypeface());
        this.f2129A0.a0(this.f2163d.getTextSize());
        this.f2129A0.X(this.f2163d.getLetterSpacing());
        int gravity = this.f2163d.getGravity();
        this.f2129A0.S((gravity & (-113)) | 48);
        this.f2129A0.Z(gravity);
        this.f2163d.addTextChangedListener(new a());
        if (this.f2186o0 == null) {
            this.f2186o0 = this.f2163d.getHintTextColors();
        }
        if (this.f2142I) {
            if (TextUtils.isEmpty(this.f2143J)) {
                CharSequence hint = this.f2163d.getHint();
                this.f2165e = hint;
                setHint(hint);
                this.f2163d.setHint((CharSequence) null);
            }
            this.f2144K = true;
        }
        n0();
        if (this.f2185o != null) {
            k0(this.f2163d.getText());
        }
        p0();
        this.f2175j.f();
        this.f2159b.bringToFront();
        this.f2161c.bringToFront();
        C();
        this.f2161c.x0();
        if (!isEnabled()) {
            editText.setEnabled(false);
        }
        v0(false, true);
    }

    private void setHintInternal(CharSequence charSequence) {
        if (TextUtils.equals(charSequence, this.f2143J)) {
            return;
        }
        this.f2143J = charSequence;
        this.f2129A0.g0(charSequence);
        if (this.f2203z0) {
            return;
        }
        W();
    }

    private void setPlaceholderTextEnabled(boolean z2) {
        if (this.f2193s == z2) {
            return;
        }
        if (z2) {
            j();
        } else {
            a0();
            this.f2199x = null;
        }
        this.f2193s = z2;
    }

    public final Fade A() {
        Fade fade = new Fade();
        fade.setDuration(J.d.f(getContext(), AbstractC0741a.f6526z, 87));
        fade.setInterpolator(J.d.g(getContext(), AbstractC0741a.f6490E, AbstractC0743a.f6834a));
        return fade;
    }

    public void A0() {
        TextView textView;
        EditText editText;
        EditText editText2;
        if (this.f2145L == null || this.f2154U == 0) {
            return;
        }
        boolean z2 = false;
        boolean z3 = isFocused() || ((editText2 = this.f2163d) != null && editText2.hasFocus());
        if (isHovered() || ((editText = this.f2163d) != null && editText.isHovered())) {
            z2 = true;
        }
        if (!isEnabled()) {
            this.f2162c0 = this.f2202y0;
        } else if (d0()) {
            if (this.f2195t0 != null) {
                z0(z3, z2);
            } else {
                this.f2162c0 = getErrorCurrentTextColors();
            }
        } else if (!this.f2181m || (textView = this.f2185o) == null) {
            if (z3) {
                this.f2162c0 = this.f2194s0;
            } else if (z2) {
                this.f2162c0 = this.f2192r0;
            } else {
                this.f2162c0 = this.f2190q0;
            }
        } else if (this.f2195t0 != null) {
            z0(z3, z2);
        } else {
            this.f2162c0 = textView.getCurrentTextColor();
        }
        n0();
        this.f2161c.I();
        Z();
        if (this.f2154U == 2) {
            int i2 = this.f2156W;
            if (z3 && isEnabled()) {
                this.f2156W = this.f2160b0;
            } else {
                this.f2156W = this.f2158a0;
            }
            if (this.f2156W != i2) {
                X();
            }
        }
        if (this.f2154U == 1) {
            if (!isEnabled()) {
                this.f2164d0 = this.f2197v0;
            } else if (z2 && !z3) {
                this.f2164d0 = this.f2200x0;
            } else if (z3) {
                this.f2164d0 = this.f2198w0;
            } else {
                this.f2164d0 = this.f2196u0;
            }
        }
        m();
    }

    public final boolean B() {
        return this.f2142I && !TextUtils.isEmpty(this.f2143J) && (this.f2145L instanceof Q.h);
    }

    public final void C() {
        Iterator it = this.f2178k0.iterator();
        while (it.hasNext()) {
            ((f) it.next()).a(this);
        }
    }

    public final void D(Canvas canvas) {
        O.g gVar;
        if (this.f2150Q == null || (gVar = this.f2149P) == null) {
            return;
        }
        gVar.draw(canvas);
        if (this.f2163d.isFocused()) {
            Rect bounds = this.f2150Q.getBounds();
            Rect bounds2 = this.f2149P.getBounds();
            float fX = this.f2129A0.x();
            int iCenterX = bounds2.centerX();
            bounds.left = AbstractC0743a.c(iCenterX, bounds2.left, fX);
            bounds.right = AbstractC0743a.c(iCenterX, bounds2.right, fX);
            this.f2150Q.draw(canvas);
        }
    }

    public final void E(Canvas canvas) {
        if (this.f2142I) {
            this.f2129A0.l(canvas);
        }
    }

    public final void F(boolean z2) {
        ValueAnimator valueAnimator = this.f2134D0;
        if (valueAnimator != null && valueAnimator.isRunning()) {
            this.f2134D0.cancel();
        }
        if (z2 && this.f2132C0) {
            l(0.0f);
        } else {
            this.f2129A0.c0(0.0f);
        }
        if (B() && ((Q.h) this.f2145L).h0()) {
            y();
        }
        this.f2203z0 = true;
        L();
        this.f2159b.l(true);
        this.f2161c.H(true);
    }

    public final O.g G(boolean z2) {
        float dimensionPixelOffset = getResources().getDimensionPixelOffset(t.c.f6552T);
        float f2 = z2 ? dimensionPixelOffset : 0.0f;
        EditText editText = this.f2163d;
        float popupElevation = editText instanceof u ? ((u) editText).getPopupElevation() : getResources().getDimensionPixelOffset(t.c.f6572o);
        int dimensionPixelOffset2 = getResources().getDimensionPixelOffset(t.c.f6550R);
        k kVarM = k.a().A(f2).E(f2).s(dimensionPixelOffset).w(dimensionPixelOffset).m();
        EditText editText2 = this.f2163d;
        O.g gVarM = O.g.m(getContext(), popupElevation, editText2 instanceof u ? ((u) editText2).getDropDownBackgroundTintList() : null);
        gVarM.setShapeAppearanceModel(kVarM);
        gVarM.V(0, dimensionPixelOffset2, 0, dimensionPixelOffset2);
        return gVarM;
    }

    public final int I(int i2, boolean z2) {
        return i2 + ((z2 || getPrefixText() == null) ? (!z2 || getSuffixText() == null) ? this.f2163d.getCompoundPaddingLeft() : this.f2161c.y() : this.f2159b.c());
    }

    public final int J(int i2, boolean z2) {
        return i2 - ((z2 || getSuffixText() == null) ? (!z2 || getPrefixText() == null) ? this.f2163d.getCompoundPaddingRight() : this.f2159b.c() : this.f2161c.y());
    }

    public final void L() {
        TextView textView = this.f2199x;
        if (textView == null || !this.f2193s) {
            return;
        }
        textView.setText((CharSequence) null);
        TransitionManager.beginDelayedTransition(this.f2157a, this.f2133D);
        this.f2199x.setVisibility(4);
    }

    public boolean M() {
        return this.f2161c.F();
    }

    public boolean N() {
        return this.f2175j.A();
    }

    public boolean O() {
        return this.f2175j.B();
    }

    public final boolean P() {
        return this.f2203z0;
    }

    public final boolean Q() {
        return d0() || (this.f2185o != null && this.f2181m);
    }

    public boolean R() {
        return this.f2144K;
    }

    public final boolean S() {
        return this.f2154U == 1 && this.f2163d.getMinLines() <= 1;
    }

    public final /* synthetic */ void U() {
        this.f2163d.requestLayout();
    }

    public final void V() {
        p();
        r0();
        A0();
        h0();
        k();
        if (this.f2154U != 0) {
            t0();
        }
        b0();
    }

    public final void W() {
        if (B()) {
            RectF rectF = this.f2170g0;
            this.f2129A0.o(rectF, this.f2163d.getWidth(), this.f2163d.getGravity());
            if (rectF.width() <= 0.0f || rectF.height() <= 0.0f) {
                return;
            }
            o(rectF);
            rectF.offset(-getPaddingLeft(), ((-getPaddingTop()) - (rectF.height() / 2.0f)) + this.f2156W);
            ((Q.h) this.f2145L).k0(rectF);
        }
    }

    public final void X() {
        if (!B() || this.f2203z0) {
            return;
        }
        y();
        W();
    }

    public void Z() {
        this.f2159b.m();
    }

    public final void a0() {
        TextView textView = this.f2199x;
        if (textView != null) {
            textView.setVisibility(8);
        }
    }

    @Override // android.view.ViewGroup
    public void addView(View view, int i2, ViewGroup.LayoutParams layoutParams) {
        if (!(view instanceof EditText)) {
            super.addView(view, i2, layoutParams);
            return;
        }
        FrameLayout.LayoutParams layoutParams2 = new FrameLayout.LayoutParams(layoutParams);
        layoutParams2.gravity = (layoutParams2.gravity & (-113)) | 16;
        this.f2157a.addView(view, layoutParams2);
        this.f2157a.setLayoutParams(layoutParams);
        t0();
        setEditText((EditText) view);
    }

    public final void b0() {
        EditText editText = this.f2163d;
        if (editText instanceof AutoCompleteTextView) {
            AutoCompleteTextView autoCompleteTextView = (AutoCompleteTextView) editText;
            if (autoCompleteTextView.getDropDownBackground() == null) {
                int i2 = this.f2154U;
                if (i2 == 2) {
                    autoCompleteTextView.setDropDownBackgroundDrawable(getOrCreateOutlinedDropDownMenuBackground());
                } else if (i2 == 1) {
                    autoCompleteTextView.setDropDownBackgroundDrawable(getOrCreateFilledDropDownMenuBackground());
                }
            }
        }
    }

    public void c0(TextView textView, int i2) {
        try {
            TextViewCompat.setTextAppearance(textView, i2);
            if (textView.getTextColors().getDefaultColor() != -65281) {
                return;
            }
        } catch (Exception unused) {
        }
        TextViewCompat.setTextAppearance(textView, i.f6678a);
        textView.setTextColor(ContextCompat.getColor(getContext(), t.b.f6527a));
    }

    public boolean d0() {
        return this.f2175j.l();
    }

    @Override // android.view.ViewGroup, android.view.View
    public void dispatchProvideAutofillStructure(ViewStructure viewStructure, int i2) {
        EditText editText = this.f2163d;
        if (editText == null) {
            super.dispatchProvideAutofillStructure(viewStructure, i2);
            return;
        }
        if (this.f2165e != null) {
            boolean z2 = this.f2144K;
            this.f2144K = false;
            CharSequence hint = editText.getHint();
            this.f2163d.setHint(this.f2165e);
            try {
                super.dispatchProvideAutofillStructure(viewStructure, i2);
                return;
            } finally {
                this.f2163d.setHint(hint);
                this.f2144K = z2;
            }
        }
        viewStructure.setAutofillId(getAutofillId());
        onProvideAutofillStructure(viewStructure, i2);
        onProvideAutofillVirtualStructure(viewStructure, i2);
        viewStructure.setChildCount(this.f2157a.getChildCount());
        for (int i3 = 0; i3 < this.f2157a.getChildCount(); i3++) {
            View childAt = this.f2157a.getChildAt(i3);
            ViewStructure viewStructureNewChild = viewStructure.newChild(i3);
            childAt.dispatchProvideAutofillStructure(viewStructureNewChild, i2);
            if (childAt == this.f2163d) {
                viewStructureNewChild.setHint(getHint());
            }
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    public void dispatchRestoreInstanceState(SparseArray sparseArray) {
        this.f2138F0 = true;
        super.dispatchRestoreInstanceState(sparseArray);
        this.f2138F0 = false;
    }

    @Override // android.view.View
    public void draw(Canvas canvas) {
        super.draw(canvas);
        E(canvas);
        D(canvas);
    }

    @Override // android.view.ViewGroup, android.view.View
    public void drawableStateChanged() {
        if (this.f2136E0) {
            return;
        }
        this.f2136E0 = true;
        super.drawableStateChanged();
        int[] drawableState = getDrawableState();
        H.a aVar = this.f2129A0;
        boolean zF0 = aVar != null ? aVar.f0(drawableState) : false;
        if (this.f2163d != null) {
            u0(ViewCompat.isLaidOut(this) && isEnabled());
        }
        p0();
        A0();
        if (zF0) {
            invalidate();
        }
        this.f2136E0 = false;
    }

    public final boolean e0() {
        return (this.f2161c.G() || ((this.f2161c.A() && M()) || this.f2161c.w() != null)) && this.f2161c.getMeasuredWidth() > 0;
    }

    public final boolean f0() {
        return (getStartIconDrawable() != null || (getPrefixText() != null && getPrefixTextView().getVisibility() == 0)) && this.f2159b.getMeasuredWidth() > 0;
    }

    public final void g0() {
        if (this.f2199x == null || !this.f2193s || TextUtils.isEmpty(this.f2191r)) {
            return;
        }
        this.f2199x.setText(this.f2191r);
        TransitionManager.beginDelayedTransition(this.f2157a, this.f2130B);
        this.f2199x.setVisibility(0);
        this.f2199x.bringToFront();
        announceForAccessibility(this.f2191r);
    }

    @Override // android.widget.LinearLayout, android.view.View
    public int getBaseline() {
        EditText editText = this.f2163d;
        return editText != null ? editText.getBaseline() + getPaddingTop() + v() : super.getBaseline();
    }

    @NonNull
    public O.g getBoxBackground() {
        int i2 = this.f2154U;
        if (i2 == 1 || i2 == 2) {
            return this.f2145L;
        }
        throw new IllegalStateException();
    }

    public int getBoxBackgroundColor() {
        return this.f2164d0;
    }

    public int getBoxBackgroundMode() {
        return this.f2154U;
    }

    public int getBoxCollapsedPaddingTop() {
        return this.f2155V;
    }

    public float getBoxCornerRadiusBottomEnd() {
        return n.g(this) ? this.f2151R.j().a(this.f2170g0) : this.f2151R.l().a(this.f2170g0);
    }

    public float getBoxCornerRadiusBottomStart() {
        return n.g(this) ? this.f2151R.l().a(this.f2170g0) : this.f2151R.j().a(this.f2170g0);
    }

    public float getBoxCornerRadiusTopEnd() {
        return n.g(this) ? this.f2151R.r().a(this.f2170g0) : this.f2151R.t().a(this.f2170g0);
    }

    public float getBoxCornerRadiusTopStart() {
        return n.g(this) ? this.f2151R.t().a(this.f2170g0) : this.f2151R.r().a(this.f2170g0);
    }

    public int getBoxStrokeColor() {
        return this.f2194s0;
    }

    @Nullable
    public ColorStateList getBoxStrokeErrorColor() {
        return this.f2195t0;
    }

    public int getBoxStrokeWidth() {
        return this.f2158a0;
    }

    public int getBoxStrokeWidthFocused() {
        return this.f2160b0;
    }

    public int getCounterMaxLength() {
        return this.f2179l;
    }

    @Nullable
    public CharSequence getCounterOverflowDescription() {
        TextView textView;
        if (this.f2177k && this.f2181m && (textView = this.f2185o) != null) {
            return textView.getContentDescription();
        }
        return null;
    }

    @Nullable
    public ColorStateList getCounterOverflowTextColor() {
        return this.f2137F;
    }

    @Nullable
    public ColorStateList getCounterTextColor() {
        return this.f2135E;
    }

    @Nullable
    @RequiresApi(29)
    public ColorStateList getCursorColor() {
        return this.f2139G;
    }

    @Nullable
    @RequiresApi(29)
    public ColorStateList getCursorErrorColor() {
        return this.f2141H;
    }

    @Nullable
    public ColorStateList getDefaultHintTextColor() {
        return this.f2186o0;
    }

    @Nullable
    public EditText getEditText() {
        return this.f2163d;
    }

    @Nullable
    public CharSequence getEndIconContentDescription() {
        return this.f2161c.l();
    }

    @Nullable
    public Drawable getEndIconDrawable() {
        return this.f2161c.n();
    }

    public int getEndIconMinSize() {
        return this.f2161c.o();
    }

    public int getEndIconMode() {
        return this.f2161c.p();
    }

    @NonNull
    public ImageView.ScaleType getEndIconScaleType() {
        return this.f2161c.q();
    }

    @NonNull
    public CheckableImageButton getEndIconView() {
        return this.f2161c.r();
    }

    @Nullable
    public CharSequence getError() {
        if (this.f2175j.A()) {
            return this.f2175j.p();
        }
        return null;
    }

    public int getErrorAccessibilityLiveRegion() {
        return this.f2175j.n();
    }

    @Nullable
    public CharSequence getErrorContentDescription() {
        return this.f2175j.o();
    }

    @ColorInt
    public int getErrorCurrentTextColors() {
        return this.f2175j.q();
    }

    @Nullable
    public Drawable getErrorIconDrawable() {
        return this.f2161c.s();
    }

    @Nullable
    public CharSequence getHelperText() {
        if (this.f2175j.B()) {
            return this.f2175j.s();
        }
        return null;
    }

    @ColorInt
    public int getHelperTextCurrentTextColor() {
        return this.f2175j.u();
    }

    @Nullable
    public CharSequence getHint() {
        if (this.f2142I) {
            return this.f2143J;
        }
        return null;
    }

    @VisibleForTesting
    public final float getHintCollapsedTextHeight() {
        return this.f2129A0.q();
    }

    @VisibleForTesting
    public final int getHintCurrentCollapsedTextColor() {
        return this.f2129A0.t();
    }

    @Nullable
    public ColorStateList getHintTextColor() {
        return this.f2188p0;
    }

    @NonNull
    public e getLengthCounter() {
        return this.f2183n;
    }

    public int getMaxEms() {
        return this.f2169g;
    }

    @Px
    public int getMaxWidth() {
        return this.f2173i;
    }

    public int getMinEms() {
        return this.f2167f;
    }

    @Px
    public int getMinWidth() {
        return this.f2171h;
    }

    @Nullable
    @Deprecated
    public CharSequence getPasswordVisibilityToggleContentDescription() {
        return this.f2161c.u();
    }

    @Nullable
    @Deprecated
    public Drawable getPasswordVisibilityToggleDrawable() {
        return this.f2161c.v();
    }

    @Nullable
    public CharSequence getPlaceholderText() {
        if (this.f2193s) {
            return this.f2191r;
        }
        return null;
    }

    @StyleRes
    public int getPlaceholderTextAppearance() {
        return this.f2128A;
    }

    @Nullable
    public ColorStateList getPlaceholderTextColor() {
        return this.f2201y;
    }

    @Nullable
    public CharSequence getPrefixText() {
        return this.f2159b.a();
    }

    @Nullable
    public ColorStateList getPrefixTextColor() {
        return this.f2159b.b();
    }

    @NonNull
    public TextView getPrefixTextView() {
        return this.f2159b.d();
    }

    @NonNull
    public k getShapeAppearanceModel() {
        return this.f2151R;
    }

    @Nullable
    public CharSequence getStartIconContentDescription() {
        return this.f2159b.e();
    }

    @Nullable
    public Drawable getStartIconDrawable() {
        return this.f2159b.f();
    }

    public int getStartIconMinSize() {
        return this.f2159b.g();
    }

    @NonNull
    public ImageView.ScaleType getStartIconScaleType() {
        return this.f2159b.h();
    }

    @Nullable
    public CharSequence getSuffixText() {
        return this.f2161c.w();
    }

    @Nullable
    public ColorStateList getSuffixTextColor() {
        return this.f2161c.x();
    }

    @NonNull
    public TextView getSuffixTextView() {
        return this.f2161c.z();
    }

    @Nullable
    public Typeface getTypeface() {
        return this.f2172h0;
    }

    public final void h0() {
        if (this.f2154U == 1) {
            if (L.c.h(getContext())) {
                this.f2155V = getResources().getDimensionPixelSize(t.c.f6582y);
            } else if (L.c.g(getContext())) {
                this.f2155V = getResources().getDimensionPixelSize(t.c.f6581x);
            }
        }
    }

    public void i(f fVar) {
        this.f2178k0.add(fVar);
        if (this.f2163d != null) {
            fVar.a(this);
        }
    }

    public final void i0(Rect rect) {
        O.g gVar = this.f2149P;
        if (gVar != null) {
            int i2 = rect.bottom;
            gVar.setBounds(rect.left, i2 - this.f2158a0, rect.right, i2);
        }
        O.g gVar2 = this.f2150Q;
        if (gVar2 != null) {
            int i3 = rect.bottom;
            gVar2.setBounds(rect.left, i3 - this.f2160b0, rect.right, i3);
        }
    }

    public final void j() {
        TextView textView = this.f2199x;
        if (textView != null) {
            this.f2157a.addView(textView);
            this.f2199x.setVisibility(0);
        }
    }

    public final void j0() {
        if (this.f2185o != null) {
            EditText editText = this.f2163d;
            k0(editText == null ? null : editText.getText());
        }
    }

    public final void k() {
        if (this.f2163d == null || this.f2154U != 1) {
            return;
        }
        if (L.c.h(getContext())) {
            EditText editText = this.f2163d;
            ViewCompat.setPaddingRelative(editText, ViewCompat.getPaddingStart(editText), getResources().getDimensionPixelSize(t.c.f6580w), ViewCompat.getPaddingEnd(this.f2163d), getResources().getDimensionPixelSize(t.c.f6579v));
        } else if (L.c.g(getContext())) {
            EditText editText2 = this.f2163d;
            ViewCompat.setPaddingRelative(editText2, ViewCompat.getPaddingStart(editText2), getResources().getDimensionPixelSize(t.c.f6578u), ViewCompat.getPaddingEnd(this.f2163d), getResources().getDimensionPixelSize(t.c.f6577t));
        }
    }

    public void k0(Editable editable) {
        int iA = this.f2183n.a(editable);
        boolean z2 = this.f2181m;
        int i2 = this.f2179l;
        if (i2 == -1) {
            this.f2185o.setText(String.valueOf(iA));
            this.f2185o.setContentDescription(null);
            this.f2181m = false;
        } else {
            this.f2181m = iA > i2;
            l0(getContext(), this.f2185o, iA, this.f2179l, this.f2181m);
            if (z2 != this.f2181m) {
                m0();
            }
            this.f2185o.setText(BidiFormatter.getInstance().unicodeWrap(getContext().getString(h.f6658d, Integer.valueOf(iA), Integer.valueOf(this.f2179l))));
        }
        if (this.f2163d == null || z2 == this.f2181m) {
            return;
        }
        u0(false);
        A0();
        p0();
    }

    public void l(float f2) {
        if (this.f2129A0.x() == f2) {
            return;
        }
        if (this.f2134D0 == null) {
            ValueAnimator valueAnimator = new ValueAnimator();
            this.f2134D0 = valueAnimator;
            valueAnimator.setInterpolator(J.d.g(getContext(), AbstractC0741a.f6489D, AbstractC0743a.f6835b));
            this.f2134D0.setDuration(J.d.f(getContext(), AbstractC0741a.f6525y, 167));
            this.f2134D0.addUpdateListener(new c());
        }
        this.f2134D0.setFloatValues(this.f2129A0.x(), f2);
        this.f2134D0.start();
    }

    public final void m() {
        O.g gVar = this.f2145L;
        if (gVar == null) {
            return;
        }
        k kVarA = gVar.A();
        k kVar = this.f2151R;
        if (kVarA != kVar) {
            this.f2145L.setShapeAppearanceModel(kVar);
        }
        if (w()) {
            this.f2145L.X(this.f2156W, this.f2162c0);
        }
        int iQ = q();
        this.f2164d0 = iQ;
        this.f2145L.T(ColorStateList.valueOf(iQ));
        n();
        r0();
    }

    public final void m0() {
        ColorStateList colorStateList;
        ColorStateList colorStateList2;
        TextView textView = this.f2185o;
        if (textView != null) {
            c0(textView, this.f2181m ? this.f2187p : this.f2189q);
            if (!this.f2181m && (colorStateList2 = this.f2135E) != null) {
                this.f2185o.setTextColor(colorStateList2);
            }
            if (!this.f2181m || (colorStateList = this.f2137F) == null) {
                return;
            }
            this.f2185o.setTextColor(colorStateList);
        }
    }

    public final void n() {
        if (this.f2149P == null || this.f2150Q == null) {
            return;
        }
        if (x()) {
            this.f2149P.T(this.f2163d.isFocused() ? ColorStateList.valueOf(this.f2190q0) : ColorStateList.valueOf(this.f2162c0));
            this.f2150Q.T(ColorStateList.valueOf(this.f2162c0));
        }
        invalidate();
    }

    public final void n0() {
        ColorStateList colorStateList;
        ColorStateList colorStateListG = this.f2139G;
        if (colorStateListG == null) {
            colorStateListG = C.a.g(getContext(), AbstractC0741a.f6505e);
        }
        EditText editText = this.f2163d;
        if (editText == null || editText.getTextCursorDrawable() == null) {
            return;
        }
        Drawable drawableMutate = DrawableCompat.wrap(this.f2163d.getTextCursorDrawable()).mutate();
        if (Q() && (colorStateList = this.f2141H) != null) {
            colorStateListG = colorStateList;
        }
        DrawableCompat.setTintList(drawableMutate, colorStateListG);
    }

    public final void o(RectF rectF) {
        float f2 = rectF.left;
        int i2 = this.f2153T;
        rectF.left = f2 - i2;
        rectF.right += i2;
    }

    public boolean o0() {
        boolean z2;
        if (this.f2163d == null) {
            return false;
        }
        boolean z3 = true;
        if (f0()) {
            int measuredWidth = this.f2159b.getMeasuredWidth() - this.f2163d.getPaddingLeft();
            if (this.f2174i0 == null || this.f2176j0 != measuredWidth) {
                ColorDrawable colorDrawable = new ColorDrawable();
                this.f2174i0 = colorDrawable;
                this.f2176j0 = measuredWidth;
                colorDrawable.setBounds(0, 0, measuredWidth, 1);
            }
            Drawable[] compoundDrawablesRelative = TextViewCompat.getCompoundDrawablesRelative(this.f2163d);
            Drawable drawable = compoundDrawablesRelative[0];
            Drawable drawable2 = this.f2174i0;
            if (drawable != drawable2) {
                TextViewCompat.setCompoundDrawablesRelative(this.f2163d, drawable2, compoundDrawablesRelative[1], compoundDrawablesRelative[2], compoundDrawablesRelative[3]);
                z2 = true;
            }
            z2 = false;
        } else {
            if (this.f2174i0 != null) {
                Drawable[] compoundDrawablesRelative2 = TextViewCompat.getCompoundDrawablesRelative(this.f2163d);
                TextViewCompat.setCompoundDrawablesRelative(this.f2163d, null, compoundDrawablesRelative2[1], compoundDrawablesRelative2[2], compoundDrawablesRelative2[3]);
                this.f2174i0 = null;
                z2 = true;
            }
            z2 = false;
        }
        if (e0()) {
            int measuredWidth2 = this.f2161c.z().getMeasuredWidth() - this.f2163d.getPaddingRight();
            CheckableImageButton checkableImageButtonK = this.f2161c.k();
            if (checkableImageButtonK != null) {
                measuredWidth2 = measuredWidth2 + checkableImageButtonK.getMeasuredWidth() + MarginLayoutParamsCompat.getMarginStart((ViewGroup.MarginLayoutParams) checkableImageButtonK.getLayoutParams());
            }
            Drawable[] compoundDrawablesRelative3 = TextViewCompat.getCompoundDrawablesRelative(this.f2163d);
            Drawable drawable3 = this.f2180l0;
            if (drawable3 == null || this.f2182m0 == measuredWidth2) {
                if (drawable3 == null) {
                    ColorDrawable colorDrawable2 = new ColorDrawable();
                    this.f2180l0 = colorDrawable2;
                    this.f2182m0 = measuredWidth2;
                    colorDrawable2.setBounds(0, 0, measuredWidth2, 1);
                }
                Drawable drawable4 = compoundDrawablesRelative3[2];
                Drawable drawable5 = this.f2180l0;
                if (drawable4 != drawable5) {
                    this.f2184n0 = drawable4;
                    TextViewCompat.setCompoundDrawablesRelative(this.f2163d, compoundDrawablesRelative3[0], compoundDrawablesRelative3[1], drawable5, compoundDrawablesRelative3[3]);
                } else {
                    z3 = z2;
                }
            } else {
                this.f2182m0 = measuredWidth2;
                drawable3.setBounds(0, 0, measuredWidth2, 1);
                TextViewCompat.setCompoundDrawablesRelative(this.f2163d, compoundDrawablesRelative3[0], compoundDrawablesRelative3[1], this.f2180l0, compoundDrawablesRelative3[3]);
            }
        } else {
            if (this.f2180l0 == null) {
                return z2;
            }
            Drawable[] compoundDrawablesRelative4 = TextViewCompat.getCompoundDrawablesRelative(this.f2163d);
            if (compoundDrawablesRelative4[2] == this.f2180l0) {
                TextViewCompat.setCompoundDrawablesRelative(this.f2163d, compoundDrawablesRelative4[0], compoundDrawablesRelative4[1], this.f2184n0, compoundDrawablesRelative4[3]);
            } else {
                z3 = z2;
            }
            this.f2180l0 = null;
        }
        return z3;
    }

    @Override // android.view.View
    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        this.f2129A0.H(configuration);
    }

    @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
    public void onGlobalLayout() {
        this.f2161c.getViewTreeObserver().removeOnGlobalLayoutListener(this);
        this.f2140G0 = false;
        boolean zS0 = s0();
        boolean zO0 = o0();
        if (zS0 || zO0) {
            this.f2163d.post(new Runnable() { // from class: Q.z
                @Override // java.lang.Runnable
                public final void run() {
                    this.f692a.U();
                }
            });
        }
    }

    @Override // android.widget.LinearLayout, android.view.ViewGroup, android.view.View
    public void onLayout(boolean z2, int i2, int i3, int i4, int i5) {
        super.onLayout(z2, i2, i3, i4, i5);
        EditText editText = this.f2163d;
        if (editText != null) {
            Rect rect = this.f2166e0;
            H.b.a(this, editText, rect);
            i0(rect);
            if (this.f2142I) {
                this.f2129A0.a0(this.f2163d.getTextSize());
                int gravity = this.f2163d.getGravity();
                this.f2129A0.S((gravity & (-113)) | 48);
                this.f2129A0.Z(gravity);
                this.f2129A0.O(r(rect));
                this.f2129A0.W(u(rect));
                this.f2129A0.J();
                if (!B() || this.f2203z0) {
                    return;
                }
                W();
            }
        }
    }

    @Override // android.widget.LinearLayout, android.view.View
    public void onMeasure(int i2, int i3) {
        super.onMeasure(i2, i3);
        if (!this.f2140G0) {
            this.f2161c.getViewTreeObserver().addOnGlobalLayoutListener(this);
            this.f2140G0 = true;
        }
        w0();
        this.f2161c.x0();
    }

    @Override // android.view.View
    public void onRestoreInstanceState(Parcelable parcelable) {
        if (!(parcelable instanceof g)) {
            super.onRestoreInstanceState(parcelable);
            return;
        }
        g gVar = (g) parcelable;
        super.onRestoreInstanceState(gVar.getSuperState());
        setError(gVar.f2208a);
        if (gVar.f2209b) {
            post(new b());
        }
        requestLayout();
    }

    @Override // android.widget.LinearLayout, android.view.View
    public void onRtlPropertiesChanged(int i2) {
        super.onRtlPropertiesChanged(i2);
        boolean z2 = i2 == 1;
        if (z2 != this.f2152S) {
            float fA = this.f2151R.r().a(this.f2170g0);
            float fA2 = this.f2151R.t().a(this.f2170g0);
            k kVarM = k.a().z(this.f2151R.s()).D(this.f2151R.q()).r(this.f2151R.k()).v(this.f2151R.i()).A(fA2).E(fA).s(this.f2151R.l().a(this.f2170g0)).w(this.f2151R.j().a(this.f2170g0)).m();
            this.f2152S = z2;
            setShapeAppearanceModel(kVarM);
        }
    }

    @Override // android.view.View
    public Parcelable onSaveInstanceState() {
        g gVar = new g(super.onSaveInstanceState());
        if (d0()) {
            gVar.f2208a = getError();
        }
        gVar.f2209b = this.f2161c.E();
        return gVar;
    }

    public final void p() {
        int i2 = this.f2154U;
        if (i2 == 0) {
            this.f2145L = null;
            this.f2149P = null;
            this.f2150Q = null;
            return;
        }
        if (i2 == 1) {
            this.f2145L = new O.g(this.f2151R);
            this.f2149P = new O.g();
            this.f2150Q = new O.g();
        } else {
            if (i2 != 2) {
                throw new IllegalArgumentException(this.f2154U + " is illegal; only @BoxBackgroundMode constants are supported.");
            }
            if (!this.f2142I || (this.f2145L instanceof Q.h)) {
                this.f2145L = new O.g(this.f2151R);
            } else {
                this.f2145L = Q.h.f0(this.f2151R);
            }
            this.f2149P = null;
            this.f2150Q = null;
        }
    }

    public void p0() {
        Drawable background;
        TextView textView;
        EditText editText = this.f2163d;
        if (editText == null || this.f2154U != 0 || (background = editText.getBackground()) == null) {
            return;
        }
        if (DrawableUtils.canSafelyMutateDrawable(background)) {
            background = background.mutate();
        }
        if (d0()) {
            background.setColorFilter(AppCompatDrawableManager.getPorterDuffColorFilter(getErrorCurrentTextColors(), PorterDuff.Mode.SRC_IN));
        } else if (this.f2181m && (textView = this.f2185o) != null) {
            background.setColorFilter(AppCompatDrawableManager.getPorterDuffColorFilter(textView.getCurrentTextColor(), PorterDuff.Mode.SRC_IN));
        } else {
            DrawableCompat.clearColorFilter(background);
            this.f2163d.refreshDrawableState();
        }
    }

    public final int q() {
        return this.f2154U == 1 ? C.a.i(C.a.e(this, AbstractC0741a.f6511k, 0), this.f2164d0) : this.f2164d0;
    }

    public final void q0() {
        ViewCompat.setBackground(this.f2163d, getEditTextBoxBackground());
    }

    public final Rect r(Rect rect) {
        if (this.f2163d == null) {
            throw new IllegalStateException();
        }
        Rect rect2 = this.f2168f0;
        boolean zG = n.g(this);
        rect2.bottom = rect.bottom;
        int i2 = this.f2154U;
        if (i2 == 1) {
            rect2.left = I(rect.left, zG);
            rect2.top = rect.top + this.f2155V;
            rect2.right = J(rect.right, zG);
            return rect2;
        }
        if (i2 != 2) {
            rect2.left = I(rect.left, zG);
            rect2.top = getPaddingTop();
            rect2.right = J(rect.right, zG);
            return rect2;
        }
        rect2.left = rect.left + this.f2163d.getPaddingLeft();
        rect2.top = rect.top - v();
        rect2.right = rect.right - this.f2163d.getPaddingRight();
        return rect2;
    }

    public void r0() {
        EditText editText = this.f2163d;
        if (editText == null || this.f2145L == null) {
            return;
        }
        if ((this.f2148O || editText.getBackground() == null) && this.f2154U != 0) {
            q0();
            this.f2148O = true;
        }
    }

    public final int s(Rect rect, Rect rect2, float f2) {
        return S() ? (int) (rect2.top + f2) : rect.bottom - this.f2163d.getCompoundPaddingBottom();
    }

    public final boolean s0() {
        int iMax;
        if (this.f2163d == null || this.f2163d.getMeasuredHeight() >= (iMax = Math.max(this.f2161c.getMeasuredHeight(), this.f2159b.getMeasuredHeight()))) {
            return false;
        }
        this.f2163d.setMinimumHeight(iMax);
        return true;
    }

    public void setBoxBackgroundColor(@ColorInt int i2) {
        if (this.f2164d0 != i2) {
            this.f2164d0 = i2;
            this.f2196u0 = i2;
            this.f2198w0 = i2;
            this.f2200x0 = i2;
            m();
        }
    }

    public void setBoxBackgroundColorResource(@ColorRes int i2) {
        setBoxBackgroundColor(ContextCompat.getColor(getContext(), i2));
    }

    public void setBoxBackgroundColorStateList(@NonNull ColorStateList colorStateList) {
        int defaultColor = colorStateList.getDefaultColor();
        this.f2196u0 = defaultColor;
        this.f2164d0 = defaultColor;
        this.f2197v0 = colorStateList.getColorForState(new int[]{-16842910}, -1);
        this.f2198w0 = colorStateList.getColorForState(new int[]{R.attr.state_focused, R.attr.state_enabled}, -1);
        this.f2200x0 = colorStateList.getColorForState(new int[]{R.attr.state_hovered, R.attr.state_enabled}, -1);
        m();
    }

    public void setBoxBackgroundMode(int i2) {
        if (i2 == this.f2154U) {
            return;
        }
        this.f2154U = i2;
        if (this.f2163d != null) {
            V();
        }
    }

    public void setBoxCollapsedPaddingTop(int i2) {
        this.f2155V = i2;
    }

    public void setBoxCornerFamily(int i2) {
        this.f2151R = this.f2151R.v().y(i2, this.f2151R.r()).C(i2, this.f2151R.t()).q(i2, this.f2151R.j()).u(i2, this.f2151R.l()).m();
        m();
    }

    public void setBoxStrokeColor(@ColorInt int i2) {
        if (this.f2194s0 != i2) {
            this.f2194s0 = i2;
            A0();
        }
    }

    public void setBoxStrokeColorStateList(@NonNull ColorStateList colorStateList) {
        if (colorStateList.isStateful()) {
            this.f2190q0 = colorStateList.getDefaultColor();
            this.f2202y0 = colorStateList.getColorForState(new int[]{-16842910}, -1);
            this.f2192r0 = colorStateList.getColorForState(new int[]{R.attr.state_hovered, R.attr.state_enabled}, -1);
            this.f2194s0 = colorStateList.getColorForState(new int[]{R.attr.state_focused, R.attr.state_enabled}, -1);
        } else if (this.f2194s0 != colorStateList.getDefaultColor()) {
            this.f2194s0 = colorStateList.getDefaultColor();
        }
        A0();
    }

    public void setBoxStrokeErrorColor(@Nullable ColorStateList colorStateList) {
        if (this.f2195t0 != colorStateList) {
            this.f2195t0 = colorStateList;
            A0();
        }
    }

    public void setBoxStrokeWidth(int i2) {
        this.f2158a0 = i2;
        A0();
    }

    public void setBoxStrokeWidthFocused(int i2) {
        this.f2160b0 = i2;
        A0();
    }

    public void setBoxStrokeWidthFocusedResource(@DimenRes int i2) {
        setBoxStrokeWidthFocused(getResources().getDimensionPixelSize(i2));
    }

    public void setBoxStrokeWidthResource(@DimenRes int i2) {
        setBoxStrokeWidth(getResources().getDimensionPixelSize(i2));
    }

    public void setCounterEnabled(boolean z2) {
        if (this.f2177k != z2) {
            if (z2) {
                AppCompatTextView appCompatTextView = new AppCompatTextView(getContext());
                this.f2185o = appCompatTextView;
                appCompatTextView.setId(t.e.f6603J);
                Typeface typeface = this.f2172h0;
                if (typeface != null) {
                    this.f2185o.setTypeface(typeface);
                }
                this.f2185o.setMaxLines(1);
                this.f2175j.e(this.f2185o, 2);
                MarginLayoutParamsCompat.setMarginStart((ViewGroup.MarginLayoutParams) this.f2185o.getLayoutParams(), getResources().getDimensionPixelOffset(t.c.f6557Y));
                m0();
                j0();
            } else {
                this.f2175j.C(this.f2185o, 2);
                this.f2185o = null;
            }
            this.f2177k = z2;
        }
    }

    public void setCounterMaxLength(int i2) {
        if (this.f2179l != i2) {
            if (i2 > 0) {
                this.f2179l = i2;
            } else {
                this.f2179l = -1;
            }
            if (this.f2177k) {
                j0();
            }
        }
    }

    public void setCounterOverflowTextAppearance(int i2) {
        if (this.f2187p != i2) {
            this.f2187p = i2;
            m0();
        }
    }

    public void setCounterOverflowTextColor(@Nullable ColorStateList colorStateList) {
        if (this.f2137F != colorStateList) {
            this.f2137F = colorStateList;
            m0();
        }
    }

    public void setCounterTextAppearance(int i2) {
        if (this.f2189q != i2) {
            this.f2189q = i2;
            m0();
        }
    }

    public void setCounterTextColor(@Nullable ColorStateList colorStateList) {
        if (this.f2135E != colorStateList) {
            this.f2135E = colorStateList;
            m0();
        }
    }

    @RequiresApi(29)
    public void setCursorColor(@Nullable ColorStateList colorStateList) {
        if (this.f2139G != colorStateList) {
            this.f2139G = colorStateList;
            n0();
        }
    }

    @RequiresApi(29)
    public void setCursorErrorColor(@Nullable ColorStateList colorStateList) {
        if (this.f2141H != colorStateList) {
            this.f2141H = colorStateList;
            if (Q()) {
                n0();
            }
        }
    }

    public void setDefaultHintTextColor(@Nullable ColorStateList colorStateList) {
        this.f2186o0 = colorStateList;
        this.f2188p0 = colorStateList;
        if (this.f2163d != null) {
            u0(false);
        }
    }

    @Override // android.view.View
    public void setEnabled(boolean z2) {
        Y(this, z2);
        super.setEnabled(z2);
    }

    public void setEndIconActivated(boolean z2) {
        this.f2161c.N(z2);
    }

    public void setEndIconCheckable(boolean z2) {
        this.f2161c.O(z2);
    }

    public void setEndIconContentDescription(@StringRes int i2) {
        this.f2161c.P(i2);
    }

    public void setEndIconDrawable(@DrawableRes int i2) {
        this.f2161c.R(i2);
    }

    public void setEndIconMinSize(@IntRange(from = 0) int i2) {
        this.f2161c.T(i2);
    }

    public void setEndIconMode(int i2) {
        this.f2161c.U(i2);
    }

    public void setEndIconOnClickListener(@Nullable View.OnClickListener onClickListener) {
        this.f2161c.V(onClickListener);
    }

    public void setEndIconOnLongClickListener(@Nullable View.OnLongClickListener onLongClickListener) {
        this.f2161c.W(onLongClickListener);
    }

    public void setEndIconScaleType(@NonNull ImageView.ScaleType scaleType) {
        this.f2161c.X(scaleType);
    }

    public void setEndIconTintList(@Nullable ColorStateList colorStateList) {
        this.f2161c.Y(colorStateList);
    }

    public void setEndIconTintMode(@Nullable PorterDuff.Mode mode) {
        this.f2161c.Z(mode);
    }

    public void setEndIconVisible(boolean z2) {
        this.f2161c.a0(z2);
    }

    public void setError(@Nullable CharSequence charSequence) {
        if (!this.f2175j.A()) {
            if (TextUtils.isEmpty(charSequence)) {
                return;
            } else {
                setErrorEnabled(true);
            }
        }
        if (TextUtils.isEmpty(charSequence)) {
            this.f2175j.w();
        } else {
            this.f2175j.Q(charSequence);
        }
    }

    public void setErrorAccessibilityLiveRegion(int i2) {
        this.f2175j.E(i2);
    }

    public void setErrorContentDescription(@Nullable CharSequence charSequence) {
        this.f2175j.F(charSequence);
    }

    public void setErrorEnabled(boolean z2) {
        this.f2175j.G(z2);
    }

    public void setErrorIconDrawable(@DrawableRes int i2) {
        this.f2161c.b0(i2);
    }

    public void setErrorIconOnClickListener(@Nullable View.OnClickListener onClickListener) {
        this.f2161c.d0(onClickListener);
    }

    public void setErrorIconOnLongClickListener(@Nullable View.OnLongClickListener onLongClickListener) {
        this.f2161c.e0(onLongClickListener);
    }

    public void setErrorIconTintList(@Nullable ColorStateList colorStateList) {
        this.f2161c.f0(colorStateList);
    }

    public void setErrorIconTintMode(@Nullable PorterDuff.Mode mode) {
        this.f2161c.g0(mode);
    }

    public void setErrorTextAppearance(@StyleRes int i2) {
        this.f2175j.H(i2);
    }

    public void setErrorTextColor(@Nullable ColorStateList colorStateList) {
        this.f2175j.I(colorStateList);
    }

    public void setExpandedHintEnabled(boolean z2) {
        if (this.f2131B0 != z2) {
            this.f2131B0 = z2;
            u0(false);
        }
    }

    public void setHelperText(@Nullable CharSequence charSequence) {
        if (TextUtils.isEmpty(charSequence)) {
            if (O()) {
                setHelperTextEnabled(false);
            }
        } else {
            if (!O()) {
                setHelperTextEnabled(true);
            }
            this.f2175j.R(charSequence);
        }
    }

    public void setHelperTextColor(@Nullable ColorStateList colorStateList) {
        this.f2175j.L(colorStateList);
    }

    public void setHelperTextEnabled(boolean z2) {
        this.f2175j.K(z2);
    }

    public void setHelperTextTextAppearance(@StyleRes int i2) {
        this.f2175j.J(i2);
    }

    public void setHint(@Nullable CharSequence charSequence) {
        if (this.f2142I) {
            setHintInternal(charSequence);
            sendAccessibilityEvent(2048);
        }
    }

    public void setHintAnimationEnabled(boolean z2) {
        this.f2132C0 = z2;
    }

    public void setHintEnabled(boolean z2) {
        if (z2 != this.f2142I) {
            this.f2142I = z2;
            if (z2) {
                CharSequence hint = this.f2163d.getHint();
                if (!TextUtils.isEmpty(hint)) {
                    if (TextUtils.isEmpty(this.f2143J)) {
                        setHint(hint);
                    }
                    this.f2163d.setHint((CharSequence) null);
                }
                this.f2144K = true;
            } else {
                this.f2144K = false;
                if (!TextUtils.isEmpty(this.f2143J) && TextUtils.isEmpty(this.f2163d.getHint())) {
                    this.f2163d.setHint(this.f2143J);
                }
                setHintInternal(null);
            }
            if (this.f2163d != null) {
                t0();
            }
        }
    }

    public void setHintTextAppearance(@StyleRes int i2) {
        this.f2129A0.P(i2);
        this.f2188p0 = this.f2129A0.p();
        if (this.f2163d != null) {
            u0(false);
            t0();
        }
    }

    public void setHintTextColor(@Nullable ColorStateList colorStateList) {
        if (this.f2188p0 != colorStateList) {
            if (this.f2186o0 == null) {
                this.f2129A0.R(colorStateList);
            }
            this.f2188p0 = colorStateList;
            if (this.f2163d != null) {
                u0(false);
            }
        }
    }

    public void setLengthCounter(@NonNull e eVar) {
        this.f2183n = eVar;
    }

    public void setMaxEms(int i2) {
        this.f2169g = i2;
        EditText editText = this.f2163d;
        if (editText == null || i2 == -1) {
            return;
        }
        editText.setMaxEms(i2);
    }

    public void setMaxWidth(@Px int i2) {
        this.f2173i = i2;
        EditText editText = this.f2163d;
        if (editText == null || i2 == -1) {
            return;
        }
        editText.setMaxWidth(i2);
    }

    public void setMaxWidthResource(@DimenRes int i2) {
        setMaxWidth(getContext().getResources().getDimensionPixelSize(i2));
    }

    public void setMinEms(int i2) {
        this.f2167f = i2;
        EditText editText = this.f2163d;
        if (editText == null || i2 == -1) {
            return;
        }
        editText.setMinEms(i2);
    }

    public void setMinWidth(@Px int i2) {
        this.f2171h = i2;
        EditText editText = this.f2163d;
        if (editText == null || i2 == -1) {
            return;
        }
        editText.setMinWidth(i2);
    }

    public void setMinWidthResource(@DimenRes int i2) {
        setMinWidth(getContext().getResources().getDimensionPixelSize(i2));
    }

    @Deprecated
    public void setPasswordVisibilityToggleContentDescription(@StringRes int i2) {
        this.f2161c.i0(i2);
    }

    @Deprecated
    public void setPasswordVisibilityToggleDrawable(@DrawableRes int i2) {
        this.f2161c.k0(i2);
    }

    @Deprecated
    public void setPasswordVisibilityToggleEnabled(boolean z2) {
        this.f2161c.m0(z2);
    }

    @Deprecated
    public void setPasswordVisibilityToggleTintList(@Nullable ColorStateList colorStateList) {
        this.f2161c.n0(colorStateList);
    }

    @Deprecated
    public void setPasswordVisibilityToggleTintMode(@Nullable PorterDuff.Mode mode) {
        this.f2161c.o0(mode);
    }

    public void setPlaceholderText(@Nullable CharSequence charSequence) {
        if (this.f2199x == null) {
            AppCompatTextView appCompatTextView = new AppCompatTextView(getContext());
            this.f2199x = appCompatTextView;
            appCompatTextView.setId(t.e.f6606M);
            ViewCompat.setImportantForAccessibility(this.f2199x, 2);
            Fade fadeA = A();
            this.f2130B = fadeA;
            fadeA.setStartDelay(67L);
            this.f2133D = A();
            setPlaceholderTextAppearance(this.f2128A);
            setPlaceholderTextColor(this.f2201y);
        }
        if (TextUtils.isEmpty(charSequence)) {
            setPlaceholderTextEnabled(false);
        } else {
            if (!this.f2193s) {
                setPlaceholderTextEnabled(true);
            }
            this.f2191r = charSequence;
        }
        x0();
    }

    public void setPlaceholderTextAppearance(@StyleRes int i2) {
        this.f2128A = i2;
        TextView textView = this.f2199x;
        if (textView != null) {
            TextViewCompat.setTextAppearance(textView, i2);
        }
    }

    public void setPlaceholderTextColor(@Nullable ColorStateList colorStateList) {
        if (this.f2201y != colorStateList) {
            this.f2201y = colorStateList;
            TextView textView = this.f2199x;
            if (textView == null || colorStateList == null) {
                return;
            }
            textView.setTextColor(colorStateList);
        }
    }

    public void setPrefixText(@Nullable CharSequence charSequence) {
        this.f2159b.n(charSequence);
    }

    public void setPrefixTextAppearance(@StyleRes int i2) {
        this.f2159b.o(i2);
    }

    public void setPrefixTextColor(@NonNull ColorStateList colorStateList) {
        this.f2159b.p(colorStateList);
    }

    public void setShapeAppearanceModel(@NonNull k kVar) {
        O.g gVar = this.f2145L;
        if (gVar == null || gVar.A() == kVar) {
            return;
        }
        this.f2151R = kVar;
        m();
    }

    public void setStartIconCheckable(boolean z2) {
        this.f2159b.q(z2);
    }

    public void setStartIconContentDescription(@StringRes int i2) {
        setStartIconContentDescription(i2 != 0 ? getResources().getText(i2) : null);
    }

    public void setStartIconDrawable(@DrawableRes int i2) {
        setStartIconDrawable(i2 != 0 ? AppCompatResources.getDrawable(getContext(), i2) : null);
    }

    public void setStartIconMinSize(@IntRange(from = 0) int i2) {
        this.f2159b.t(i2);
    }

    public void setStartIconOnClickListener(@Nullable View.OnClickListener onClickListener) {
        this.f2159b.u(onClickListener);
    }

    public void setStartIconOnLongClickListener(@Nullable View.OnLongClickListener onLongClickListener) {
        this.f2159b.v(onLongClickListener);
    }

    public void setStartIconScaleType(@NonNull ImageView.ScaleType scaleType) {
        this.f2159b.w(scaleType);
    }

    public void setStartIconTintList(@Nullable ColorStateList colorStateList) {
        this.f2159b.x(colorStateList);
    }

    public void setStartIconTintMode(@Nullable PorterDuff.Mode mode) {
        this.f2159b.y(mode);
    }

    public void setStartIconVisible(boolean z2) {
        this.f2159b.z(z2);
    }

    public void setSuffixText(@Nullable CharSequence charSequence) {
        this.f2161c.p0(charSequence);
    }

    public void setSuffixTextAppearance(@StyleRes int i2) {
        this.f2161c.q0(i2);
    }

    public void setSuffixTextColor(@NonNull ColorStateList colorStateList) {
        this.f2161c.r0(colorStateList);
    }

    public void setTextInputAccessibilityDelegate(@Nullable d dVar) {
        EditText editText = this.f2163d;
        if (editText != null) {
            ViewCompat.setAccessibilityDelegate(editText, dVar);
        }
    }

    public void setTypeface(@Nullable Typeface typeface) {
        if (typeface != this.f2172h0) {
            this.f2172h0 = typeface;
            this.f2129A0.i0(typeface);
            this.f2175j.N(typeface);
            TextView textView = this.f2185o;
            if (textView != null) {
                textView.setTypeface(typeface);
            }
        }
    }

    public final int t(Rect rect, float f2) {
        return S() ? (int) (rect.centerY() - (f2 / 2.0f)) : rect.top + this.f2163d.getCompoundPaddingTop();
    }

    public final void t0() {
        if (this.f2154U != 1) {
            LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) this.f2157a.getLayoutParams();
            int iV = v();
            if (iV != layoutParams.topMargin) {
                layoutParams.topMargin = iV;
                this.f2157a.requestLayout();
            }
        }
    }

    public final Rect u(Rect rect) {
        if (this.f2163d == null) {
            throw new IllegalStateException();
        }
        Rect rect2 = this.f2168f0;
        float fW = this.f2129A0.w();
        rect2.left = rect.left + this.f2163d.getCompoundPaddingLeft();
        rect2.top = t(rect, fW);
        rect2.right = rect.right - this.f2163d.getCompoundPaddingRight();
        rect2.bottom = s(rect, rect2, fW);
        return rect2;
    }

    public void u0(boolean z2) {
        v0(z2, false);
    }

    public final int v() {
        float fQ;
        if (!this.f2142I) {
            return 0;
        }
        int i2 = this.f2154U;
        if (i2 == 0) {
            fQ = this.f2129A0.q();
        } else {
            if (i2 != 2) {
                return 0;
            }
            fQ = this.f2129A0.q() / 2.0f;
        }
        return (int) fQ;
    }

    public final void v0(boolean z2, boolean z3) {
        ColorStateList colorStateList;
        TextView textView;
        boolean zIsEnabled = isEnabled();
        EditText editText = this.f2163d;
        boolean z4 = false;
        boolean z5 = (editText == null || TextUtils.isEmpty(editText.getText())) ? false : true;
        EditText editText2 = this.f2163d;
        if (editText2 != null && editText2.hasFocus()) {
            z4 = true;
        }
        ColorStateList colorStateList2 = this.f2186o0;
        if (colorStateList2 != null) {
            this.f2129A0.M(colorStateList2);
        }
        if (!zIsEnabled) {
            ColorStateList colorStateList3 = this.f2186o0;
            this.f2129A0.M(ColorStateList.valueOf(colorStateList3 != null ? colorStateList3.getColorForState(new int[]{-16842910}, this.f2202y0) : this.f2202y0));
        } else if (d0()) {
            this.f2129A0.M(this.f2175j.r());
        } else if (this.f2181m && (textView = this.f2185o) != null) {
            this.f2129A0.M(textView.getTextColors());
        } else if (z4 && (colorStateList = this.f2188p0) != null) {
            this.f2129A0.R(colorStateList);
        }
        if (z5 || !this.f2131B0 || (isEnabled() && z4)) {
            if (z3 || this.f2203z0) {
                z(z2);
                return;
            }
            return;
        }
        if (z3 || !this.f2203z0) {
            F(z2);
        }
    }

    public final boolean w() {
        return this.f2154U == 2 && x();
    }

    public final void w0() {
        EditText editText;
        if (this.f2199x == null || (editText = this.f2163d) == null) {
            return;
        }
        this.f2199x.setGravity(editText.getGravity());
        this.f2199x.setPadding(this.f2163d.getCompoundPaddingLeft(), this.f2163d.getCompoundPaddingTop(), this.f2163d.getCompoundPaddingRight(), this.f2163d.getCompoundPaddingBottom());
    }

    public final boolean x() {
        return this.f2156W > -1 && this.f2162c0 != 0;
    }

    public final void x0() {
        EditText editText = this.f2163d;
        y0(editText == null ? null : editText.getText());
    }

    public final void y() {
        if (B()) {
            ((Q.h) this.f2145L).i0();
        }
    }

    public final void y0(Editable editable) {
        if (this.f2183n.a(editable) != 0 || this.f2203z0) {
            L();
        } else {
            g0();
        }
    }

    public final void z(boolean z2) {
        ValueAnimator valueAnimator = this.f2134D0;
        if (valueAnimator != null && valueAnimator.isRunning()) {
            this.f2134D0.cancel();
        }
        if (z2 && this.f2132C0) {
            l(1.0f);
        } else {
            this.f2129A0.c0(1.0f);
        }
        this.f2203z0 = false;
        if (B()) {
            W();
        }
        x0();
        this.f2159b.l(false);
        this.f2161c.H(false);
    }

    public final void z0(boolean z2, boolean z3) {
        int defaultColor = this.f2195t0.getDefaultColor();
        int colorForState = this.f2195t0.getColorForState(new int[]{R.attr.state_hovered, R.attr.state_enabled}, defaultColor);
        int colorForState2 = this.f2195t0.getColorForState(new int[]{R.attr.state_activated, R.attr.state_enabled}, defaultColor);
        if (z2) {
            this.f2162c0 = colorForState2;
        } else if (z3) {
            this.f2162c0 = colorForState;
        } else {
            this.f2162c0 = defaultColor;
        }
    }

    /* JADX WARN: Illegal instructions before constructor call */
    public TextInputLayout(Context context, AttributeSet attributeSet, int i2) {
        int i3 = f2126H0;
        super(S.a.c(context, attributeSet, i2, i3), attributeSet, i2);
        this.f2167f = -1;
        this.f2169g = -1;
        this.f2171h = -1;
        this.f2173i = -1;
        this.f2175j = new t(this);
        this.f2183n = new e() { // from class: Q.A
            @Override // com.google.android.material.textfield.TextInputLayout.e
            public final int a(Editable editable) {
                return TextInputLayout.T(editable);
            }
        };
        this.f2166e0 = new Rect();
        this.f2168f0 = new Rect();
        this.f2170g0 = new RectF();
        this.f2178k0 = new LinkedHashSet();
        H.a aVar = new H.a(this);
        this.f2129A0 = aVar;
        this.f2140G0 = false;
        Context context2 = getContext();
        setOrientation(1);
        setWillNotDraw(false);
        setAddStatesFromChildren(true);
        FrameLayout frameLayout = new FrameLayout(context2);
        this.f2157a = frameLayout;
        frameLayout.setAddStatesFromChildren(true);
        TimeInterpolator timeInterpolator = AbstractC0743a.f6834a;
        aVar.h0(timeInterpolator);
        aVar.e0(timeInterpolator);
        aVar.S(8388659);
        int[] iArr = j.m5;
        int i4 = j.J5;
        int i5 = j.H5;
        int i6 = j.b6;
        int i7 = j.g6;
        int i8 = j.k6;
        TintTypedArray tintTypedArrayJ = H.k.j(context2, attributeSet, iArr, i2, i3, i4, i5, i6, i7, i8);
        y yVar = new y(this, tintTypedArrayJ);
        this.f2159b = yVar;
        this.f2142I = tintTypedArrayJ.getBoolean(j.j6, true);
        setHint(tintTypedArrayJ.getText(j.r5));
        this.f2132C0 = tintTypedArrayJ.getBoolean(j.i6, true);
        this.f2131B0 = tintTypedArrayJ.getBoolean(j.d6, true);
        int i9 = j.t5;
        if (tintTypedArrayJ.hasValue(i9)) {
            setMinEms(tintTypedArrayJ.getInt(i9, -1));
        } else {
            int i10 = j.q5;
            if (tintTypedArrayJ.hasValue(i10)) {
                setMinWidth(tintTypedArrayJ.getDimensionPixelSize(i10, -1));
            }
        }
        int i11 = j.s5;
        if (tintTypedArrayJ.hasValue(i11)) {
            setMaxEms(tintTypedArrayJ.getInt(i11, -1));
        } else {
            int i12 = j.p5;
            if (tintTypedArrayJ.hasValue(i12)) {
                setMaxWidth(tintTypedArrayJ.getDimensionPixelSize(i12, -1));
            }
        }
        this.f2151R = k.e(context2, attributeSet, i2, i3).m();
        this.f2153T = context2.getResources().getDimensionPixelOffset(t.c.f6554V);
        this.f2155V = tintTypedArrayJ.getDimensionPixelOffset(j.w5, 0);
        this.f2158a0 = tintTypedArrayJ.getDimensionPixelSize(j.D5, context2.getResources().getDimensionPixelSize(t.c.f6555W));
        this.f2160b0 = tintTypedArrayJ.getDimensionPixelSize(j.E5, context2.getResources().getDimensionPixelSize(t.c.f6556X));
        this.f2156W = this.f2158a0;
        float dimension = tintTypedArrayJ.getDimension(j.A5, -1.0f);
        float dimension2 = tintTypedArrayJ.getDimension(j.z5, -1.0f);
        float dimension3 = tintTypedArrayJ.getDimension(j.x5, -1.0f);
        float dimension4 = tintTypedArrayJ.getDimension(j.y5, -1.0f);
        k.b bVarV = this.f2151R.v();
        if (dimension >= 0.0f) {
            bVarV.A(dimension);
        }
        if (dimension2 >= 0.0f) {
            bVarV.E(dimension2);
        }
        if (dimension3 >= 0.0f) {
            bVarV.w(dimension3);
        }
        if (dimension4 >= 0.0f) {
            bVarV.s(dimension4);
        }
        this.f2151R = bVarV.m();
        ColorStateList colorStateListB = L.c.b(context2, tintTypedArrayJ, j.u5);
        if (colorStateListB != null) {
            int defaultColor = colorStateListB.getDefaultColor();
            this.f2196u0 = defaultColor;
            this.f2164d0 = defaultColor;
            if (colorStateListB.isStateful()) {
                this.f2197v0 = colorStateListB.getColorForState(new int[]{-16842910}, -1);
                this.f2198w0 = colorStateListB.getColorForState(new int[]{R.attr.state_focused, R.attr.state_enabled}, -1);
                this.f2200x0 = colorStateListB.getColorForState(new int[]{R.attr.state_hovered, R.attr.state_enabled}, -1);
            } else {
                this.f2198w0 = this.f2196u0;
                ColorStateList colorStateList = AppCompatResources.getColorStateList(context2, t.b.f6529c);
                this.f2197v0 = colorStateList.getColorForState(new int[]{-16842910}, -1);
                this.f2200x0 = colorStateList.getColorForState(new int[]{R.attr.state_hovered}, -1);
            }
        } else {
            this.f2164d0 = 0;
            this.f2196u0 = 0;
            this.f2197v0 = 0;
            this.f2198w0 = 0;
            this.f2200x0 = 0;
        }
        int i13 = j.o5;
        if (tintTypedArrayJ.hasValue(i13)) {
            ColorStateList colorStateList2 = tintTypedArrayJ.getColorStateList(i13);
            this.f2188p0 = colorStateList2;
            this.f2186o0 = colorStateList2;
        }
        int i14 = j.B5;
        ColorStateList colorStateListB2 = L.c.b(context2, tintTypedArrayJ, i14);
        this.f2194s0 = tintTypedArrayJ.getColor(i14, 0);
        this.f2190q0 = ContextCompat.getColor(context2, t.b.f6530d);
        this.f2202y0 = ContextCompat.getColor(context2, t.b.f6531e);
        this.f2192r0 = ContextCompat.getColor(context2, t.b.f6532f);
        if (colorStateListB2 != null) {
            setBoxStrokeColorStateList(colorStateListB2);
        }
        int i15 = j.C5;
        if (tintTypedArrayJ.hasValue(i15)) {
            setBoxStrokeErrorColor(L.c.b(context2, tintTypedArrayJ, i15));
        }
        if (tintTypedArrayJ.getResourceId(i8, -1) != -1) {
            setHintTextAppearance(tintTypedArrayJ.getResourceId(i8, 0));
        }
        this.f2139G = tintTypedArrayJ.getColorStateList(j.L5);
        this.f2141H = tintTypedArrayJ.getColorStateList(j.M5);
        int resourceId = tintTypedArrayJ.getResourceId(i6, 0);
        CharSequence text = tintTypedArrayJ.getText(j.W5);
        int i16 = tintTypedArrayJ.getInt(j.V5, 1);
        boolean z2 = tintTypedArrayJ.getBoolean(j.X5, false);
        int resourceId2 = tintTypedArrayJ.getResourceId(i7, 0);
        boolean z3 = tintTypedArrayJ.getBoolean(j.f6, false);
        CharSequence text2 = tintTypedArrayJ.getText(j.e6);
        int resourceId3 = tintTypedArrayJ.getResourceId(j.s6, 0);
        CharSequence text3 = tintTypedArrayJ.getText(j.r6);
        boolean z4 = tintTypedArrayJ.getBoolean(j.F5, false);
        setCounterMaxLength(tintTypedArrayJ.getInt(j.G5, -1));
        this.f2189q = tintTypedArrayJ.getResourceId(i4, 0);
        this.f2187p = tintTypedArrayJ.getResourceId(i5, 0);
        setBoxBackgroundMode(tintTypedArrayJ.getInt(j.v5, 0));
        setErrorContentDescription(text);
        setErrorAccessibilityLiveRegion(i16);
        setCounterOverflowTextAppearance(this.f2187p);
        setHelperTextTextAppearance(resourceId2);
        setErrorTextAppearance(resourceId);
        setCounterTextAppearance(this.f2189q);
        setPlaceholderText(text3);
        setPlaceholderTextAppearance(resourceId3);
        int i17 = j.c6;
        if (tintTypedArrayJ.hasValue(i17)) {
            setErrorTextColor(tintTypedArrayJ.getColorStateList(i17));
        }
        int i18 = j.h6;
        if (tintTypedArrayJ.hasValue(i18)) {
            setHelperTextColor(tintTypedArrayJ.getColorStateList(i18));
        }
        int i19 = j.l6;
        if (tintTypedArrayJ.hasValue(i19)) {
            setHintTextColor(tintTypedArrayJ.getColorStateList(i19));
        }
        int i20 = j.K5;
        if (tintTypedArrayJ.hasValue(i20)) {
            setCounterTextColor(tintTypedArrayJ.getColorStateList(i20));
        }
        int i21 = j.I5;
        if (tintTypedArrayJ.hasValue(i21)) {
            setCounterOverflowTextColor(tintTypedArrayJ.getColorStateList(i21));
        }
        int i22 = j.t6;
        if (tintTypedArrayJ.hasValue(i22)) {
            setPlaceholderTextColor(tintTypedArrayJ.getColorStateList(i22));
        }
        com.google.android.material.textfield.a aVar2 = new com.google.android.material.textfield.a(this, tintTypedArrayJ);
        this.f2161c = aVar2;
        boolean z5 = tintTypedArrayJ.getBoolean(j.n5, true);
        tintTypedArrayJ.recycle();
        ViewCompat.setImportantForAccessibility(this, 2);
        ViewCompat.setImportantForAutofill(this, 1);
        frameLayout.addView(yVar);
        frameLayout.addView(aVar2);
        addView(frameLayout);
        setEnabled(z5);
        setHelperTextEnabled(z3);
        setErrorEnabled(z2);
        setCounterEnabled(z4);
        setHelperText(text2);
    }

    public void setEndIconContentDescription(@Nullable CharSequence charSequence) {
        this.f2161c.Q(charSequence);
    }

    public void setEndIconDrawable(@Nullable Drawable drawable) {
        this.f2161c.S(drawable);
    }

    public void setErrorIconDrawable(@Nullable Drawable drawable) {
        this.f2161c.c0(drawable);
    }

    @Deprecated
    public void setPasswordVisibilityToggleContentDescription(@Nullable CharSequence charSequence) {
        this.f2161c.j0(charSequence);
    }

    @Deprecated
    public void setPasswordVisibilityToggleDrawable(@Nullable Drawable drawable) {
        this.f2161c.l0(drawable);
    }

    public void setStartIconContentDescription(@Nullable CharSequence charSequence) {
        this.f2159b.r(charSequence);
    }

    public void setStartIconDrawable(@Nullable Drawable drawable) {
        this.f2159b.s(drawable);
    }

    public void setHint(@StringRes int i2) {
        setHint(i2 != 0 ? getResources().getText(i2) : null);
    }
}
