package com.google.android.material.sidesheet;

import O.g;
import O.k;
import P.d;
import android.R;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.ViewParent;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.core.widgets.analyzer.BasicMeasure;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.math.MathUtils;
import androidx.core.view.GravityCompat;
import androidx.core.view.ViewCompat;
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;
import androidx.core.view.accessibility.AccessibilityViewCommand;
import androidx.customview.view.AbsSavedState;
import androidx.customview.widget.ViewDragHelper;
import java.lang.ref.WeakReference;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Set;
import t.h;
import t.i;
import t.j;

/* JADX INFO: loaded from: classes2.dex */
public class SideSheetBehavior<V extends View> extends CoordinatorLayout.Behavior<V> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public P.c f2066a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public float f2067b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public g f2068c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public ColorStateList f2069d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public k f2070e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final c f2071f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public float f2072g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public boolean f2073h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public int f2074i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public int f2075j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public ViewDragHelper f2076k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public boolean f2077l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public float f2078m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public int f2079n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public int f2080o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public int f2081p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public int f2082q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public WeakReference f2083r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public WeakReference f2084s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public int f2085t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public VelocityTracker f2086u;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public J.c f2087v;

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    public int f2088w;

    /* JADX INFO: renamed from: x, reason: collision with root package name */
    public final Set f2089x;

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    public final ViewDragHelper.Callback f2090y;

    /* JADX INFO: renamed from: z, reason: collision with root package name */
    public static final int f2065z = h.f6677w;

    /* JADX INFO: renamed from: A, reason: collision with root package name */
    public static final int f2064A = i.f6683f;

    public class a extends ViewDragHelper.Callback {
        public a() {
        }

        @Override // androidx.customview.widget.ViewDragHelper.Callback
        public int clampViewPositionHorizontal(View view, int i2, int i3) {
            return MathUtils.clamp(i2, SideSheetBehavior.this.f2066a.f(), SideSheetBehavior.this.f2066a.e());
        }

        @Override // androidx.customview.widget.ViewDragHelper.Callback
        public int clampViewPositionVertical(View view, int i2, int i3) {
            return view.getTop();
        }

        @Override // androidx.customview.widget.ViewDragHelper.Callback
        public int getViewHorizontalDragRange(View view) {
            return SideSheetBehavior.this.f2079n + SideSheetBehavior.this.z();
        }

        @Override // androidx.customview.widget.ViewDragHelper.Callback
        public void onViewDragStateChanged(int i2) {
            if (i2 == 1 && SideSheetBehavior.this.f2073h) {
                SideSheetBehavior.this.X(1);
            }
        }

        @Override // androidx.customview.widget.ViewDragHelper.Callback
        public void onViewPositionChanged(View view, int i2, int i3, int i4, int i5) {
            ViewGroup.MarginLayoutParams marginLayoutParams;
            View viewV = SideSheetBehavior.this.v();
            if (viewV != null && (marginLayoutParams = (ViewGroup.MarginLayoutParams) viewV.getLayoutParams()) != null) {
                SideSheetBehavior.this.f2066a.n(marginLayoutParams, view.getLeft(), view.getRight());
                viewV.setLayoutParams(marginLayoutParams);
            }
            SideSheetBehavior.this.r(view, i2);
        }

        @Override // androidx.customview.widget.ViewDragHelper.Callback
        public void onViewReleased(View view, float f2, float f3) {
            int iN = SideSheetBehavior.this.n(view, f2, f3);
            SideSheetBehavior sideSheetBehavior = SideSheetBehavior.this;
            sideSheetBehavior.c0(view, iN, sideSheetBehavior.b0());
        }

        @Override // androidx.customview.widget.ViewDragHelper.Callback
        public boolean tryCaptureView(View view, int i2) {
            return (SideSheetBehavior.this.f2074i == 1 || SideSheetBehavior.this.f2083r == null || SideSheetBehavior.this.f2083r.get() != view) ? false : true;
        }
    }

    public class c {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public int f2093a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public boolean f2094b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final Runnable f2095c = new Runnable() { // from class: P.g
            @Override // java.lang.Runnable
            public final void run() {
                this.f582a.c();
            }
        };

        public c() {
        }

        public void b(int i2) {
            if (SideSheetBehavior.this.f2083r == null || SideSheetBehavior.this.f2083r.get() == null) {
                return;
            }
            this.f2093a = i2;
            if (this.f2094b) {
                return;
            }
            ViewCompat.postOnAnimation((View) SideSheetBehavior.this.f2083r.get(), this.f2095c);
            this.f2094b = true;
        }

        public final /* synthetic */ void c() {
            this.f2094b = false;
            if (SideSheetBehavior.this.f2076k != null && SideSheetBehavior.this.f2076k.continueSettling(true)) {
                b(this.f2093a);
            } else if (SideSheetBehavior.this.f2074i == 2) {
                SideSheetBehavior.this.X(this.f2093a);
            }
        }
    }

    public SideSheetBehavior() {
        this.f2071f = new c();
        this.f2073h = true;
        this.f2074i = 5;
        this.f2075j = 5;
        this.f2078m = 0.1f;
        this.f2085t = -1;
        this.f2089x = new LinkedHashSet();
        this.f2090y = new a();
    }

    private void P(View view, AccessibilityNodeInfoCompat.AccessibilityActionCompat accessibilityActionCompat, int i2) {
        ViewCompat.replaceAccessibilityAction(view, accessibilityActionCompat, null, p(i2));
    }

    private void R(View view, Runnable runnable) {
        if (K(view)) {
            view.post(runnable);
        } else {
            runnable.run();
        }
    }

    private boolean Y() {
        return this.f2076k != null && (this.f2073h || this.f2074i == 1);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c0(View view, int i2, boolean z2) {
        if (!L(view, i2, z2)) {
            X(i2);
        } else {
            X(2);
            this.f2071f.b(i2);
        }
    }

    private void d0() {
        View view;
        WeakReference weakReference = this.f2083r;
        if (weakReference == null || (view = (View) weakReference.get()) == null) {
            return;
        }
        ViewCompat.removeAccessibilityAction(view, 262144);
        ViewCompat.removeAccessibilityAction(view, 1048576);
        if (this.f2074i != 5) {
            P(view, AccessibilityNodeInfoCompat.AccessibilityActionCompat.ACTION_DISMISS, 5);
        }
        if (this.f2074i != 3) {
            P(view, AccessibilityNodeInfoCompat.AccessibilityActionCompat.ACTION_EXPAND, 3);
        }
    }

    private AccessibilityViewCommand p(final int i2) {
        return new AccessibilityViewCommand() { // from class: P.e
            @Override // androidx.core.view.accessibility.AccessibilityViewCommand
            public final boolean perform(View view, AccessibilityViewCommand.CommandArguments commandArguments) {
                return this.f578a.M(i2, view, commandArguments);
            }
        };
    }

    private void q(Context context) {
        if (this.f2070e == null) {
            return;
        }
        g gVar = new g(this.f2070e);
        this.f2068c = gVar;
        gVar.J(context);
        ColorStateList colorStateList = this.f2069d;
        if (colorStateList != null) {
            this.f2068c.T(colorStateList);
            return;
        }
        TypedValue typedValue = new TypedValue();
        context.getTheme().resolveAttribute(R.attr.colorBackground, typedValue, true);
        this.f2068c.setTint(typedValue.data);
    }

    private int t(int i2, int i3, int i4, int i5) {
        int childMeasureSpec = ViewGroup.getChildMeasureSpec(i2, i3, i5);
        if (i4 == -1) {
            return childMeasureSpec;
        }
        int mode = View.MeasureSpec.getMode(childMeasureSpec);
        int size = View.MeasureSpec.getSize(childMeasureSpec);
        if (mode == 1073741824) {
            return View.MeasureSpec.makeMeasureSpec(Math.min(size, i4), BasicMeasure.EXACTLY);
        }
        if (size != 0) {
            i4 = Math.min(size, i4);
        }
        return View.MeasureSpec.makeMeasureSpec(i4, Integer.MIN_VALUE);
    }

    public int A(int i2) {
        if (i2 == 3) {
            return w();
        }
        if (i2 == 5) {
            return this.f2066a.d();
        }
        throw new IllegalArgumentException("Invalid state to get outer edge offset: " + i2);
    }

    public int B() {
        return this.f2081p;
    }

    public int C() {
        return this.f2080o;
    }

    public int D() {
        return 500;
    }

    public ViewDragHelper E() {
        return this.f2076k;
    }

    public final CoordinatorLayout.LayoutParams F() {
        View view;
        WeakReference weakReference = this.f2083r;
        if (weakReference == null || (view = (View) weakReference.get()) == null || !(view.getLayoutParams() instanceof CoordinatorLayout.LayoutParams)) {
            return null;
        }
        return (CoordinatorLayout.LayoutParams) view.getLayoutParams();
    }

    public final boolean G() {
        CoordinatorLayout.LayoutParams layoutParamsF = F();
        return layoutParamsF != null && ((ViewGroup.MarginLayoutParams) layoutParamsF).leftMargin > 0;
    }

    public final boolean H() {
        CoordinatorLayout.LayoutParams layoutParamsF = F();
        return layoutParamsF != null && ((ViewGroup.MarginLayoutParams) layoutParamsF).rightMargin > 0;
    }

    public final boolean I(MotionEvent motionEvent) {
        return Y() && m((float) this.f2088w, motionEvent.getX()) > ((float) this.f2076k.getTouchSlop());
    }

    public final boolean J(float f2) {
        return this.f2066a.j(f2);
    }

    public final boolean K(View view) {
        ViewParent parent = view.getParent();
        return parent != null && parent.isLayoutRequested() && ViewCompat.isAttachedToWindow(view);
    }

    public final boolean L(View view, int i2, boolean z2) {
        int iA = A(i2);
        ViewDragHelper viewDragHelperE = E();
        return viewDragHelperE != null && (!z2 ? !viewDragHelperE.smoothSlideViewTo(view, iA, view.getTop()) : !viewDragHelperE.settleCapturedViewAt(iA, view.getTop()));
    }

    public final /* synthetic */ boolean M(int i2, View view, AccessibilityViewCommand.CommandArguments commandArguments) {
        W(i2);
        return true;
    }

    public final /* synthetic */ void N(int i2) {
        View view = (View) this.f2083r.get();
        if (view != null) {
            c0(view, i2, false);
        }
    }

    public final void O(CoordinatorLayout coordinatorLayout) {
        int i2;
        View viewFindViewById;
        if (this.f2084s != null || (i2 = this.f2085t) == -1 || (viewFindViewById = coordinatorLayout.findViewById(i2)) == null) {
            return;
        }
        this.f2084s = new WeakReference(viewFindViewById);
    }

    public final void Q() {
        VelocityTracker velocityTracker = this.f2086u;
        if (velocityTracker != null) {
            velocityTracker.recycle();
            this.f2086u = null;
        }
    }

    public void S(int i2) {
        this.f2085t = i2;
        o();
        WeakReference weakReference = this.f2083r;
        if (weakReference != null) {
            View view = (View) weakReference.get();
            if (i2 == -1 || !ViewCompat.isLaidOut(view)) {
                return;
            }
            view.requestLayout();
        }
    }

    public void T(boolean z2) {
        this.f2073h = z2;
    }

    public final void U(int i2) {
        P.c cVar = this.f2066a;
        if (cVar == null || cVar.i() != i2) {
            if (i2 == 0) {
                this.f2066a = new P.b(this);
                if (this.f2070e == null || H()) {
                    return;
                }
                k.b bVarV = this.f2070e.v();
                bVarV.E(0.0f).w(0.0f);
                e0(bVarV.m());
                return;
            }
            if (i2 == 1) {
                this.f2066a = new P.a(this);
                if (this.f2070e == null || G()) {
                    return;
                }
                k.b bVarV2 = this.f2070e.v();
                bVarV2.A(0.0f).s(0.0f);
                e0(bVarV2.m());
                return;
            }
            throw new IllegalArgumentException("Invalid sheet edge position value: " + i2 + ". Must be 0 or 1.");
        }
    }

    public final void V(View view, int i2) {
        U(GravityCompat.getAbsoluteGravity(((CoordinatorLayout.LayoutParams) view.getLayoutParams()).gravity, i2) == 3 ? 1 : 0);
    }

    public void W(final int i2) {
        if (i2 == 1 || i2 == 2) {
            StringBuilder sb = new StringBuilder();
            sb.append("STATE_");
            sb.append(i2 == 1 ? "DRAGGING" : "SETTLING");
            sb.append(" should not be set externally.");
            throw new IllegalArgumentException(sb.toString());
        }
        WeakReference weakReference = this.f2083r;
        if (weakReference == null || weakReference.get() == null) {
            X(i2);
        } else {
            R((View) this.f2083r.get(), new Runnable() { // from class: P.f
                @Override // java.lang.Runnable
                public final void run() {
                    this.f580a.N(i2);
                }
            });
        }
    }

    public void X(int i2) {
        View view;
        if (this.f2074i == i2) {
            return;
        }
        this.f2074i = i2;
        if (i2 == 3 || i2 == 5) {
            this.f2075j = i2;
        }
        WeakReference weakReference = this.f2083r;
        if (weakReference == null || (view = (View) weakReference.get()) == null) {
            return;
        }
        f0(view);
        Iterator it = this.f2089x.iterator();
        if (it.hasNext()) {
            android.support.v4.media.a.a(it.next());
            throw null;
        }
        d0();
    }

    public boolean Z(View view, float f2) {
        return this.f2066a.m(view, f2);
    }

    public final boolean a0(View view) {
        return (view.isShown() || ViewCompat.getAccessibilityPaneTitle(view) != null) && this.f2073h;
    }

    public boolean b0() {
        return true;
    }

    public final void e0(k kVar) {
        g gVar = this.f2068c;
        if (gVar != null) {
            gVar.setShapeAppearanceModel(kVar);
        }
    }

    public final void f0(View view) {
        int i2 = this.f2074i == 5 ? 4 : 0;
        if (view.getVisibility() != i2) {
            view.setVisibility(i2);
        }
    }

    public final int l(int i2, View view) {
        int i3 = this.f2074i;
        if (i3 == 1 || i3 == 2) {
            return i2 - this.f2066a.g(view);
        }
        if (i3 == 3) {
            return 0;
        }
        if (i3 == 5) {
            return this.f2066a.d();
        }
        throw new IllegalStateException("Unexpected value: " + this.f2074i);
    }

    public final float m(float f2, float f3) {
        return Math.abs(f2 - f3);
    }

    public final int n(View view, float f2, float f3) {
        if (J(f2)) {
            return 3;
        }
        if (Z(view, f2)) {
            if (!this.f2066a.l(f2, f3) && !this.f2066a.k(view)) {
                return 3;
            }
        } else if (f2 == 0.0f || !d.a(f2, f3)) {
            int left = view.getLeft();
            if (Math.abs(left - w()) < Math.abs(left - this.f2066a.d())) {
                return 3;
            }
        }
        return 5;
    }

    public final void o() {
        WeakReference weakReference = this.f2084s;
        if (weakReference != null) {
            weakReference.clear();
        }
        this.f2084s = null;
    }

    @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.Behavior
    public void onAttachedToLayoutParams(CoordinatorLayout.LayoutParams layoutParams) {
        super.onAttachedToLayoutParams(layoutParams);
        this.f2083r = null;
        this.f2076k = null;
        this.f2087v = null;
    }

    @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.Behavior
    public void onDetachedFromLayoutParams() {
        super.onDetachedFromLayoutParams();
        this.f2083r = null;
        this.f2076k = null;
        this.f2087v = null;
    }

    @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.Behavior
    public boolean onInterceptTouchEvent(CoordinatorLayout coordinatorLayout, View view, MotionEvent motionEvent) {
        ViewDragHelper viewDragHelper;
        if (!a0(view)) {
            this.f2077l = true;
            return false;
        }
        int actionMasked = motionEvent.getActionMasked();
        if (actionMasked == 0) {
            Q();
        }
        if (this.f2086u == null) {
            this.f2086u = VelocityTracker.obtain();
        }
        this.f2086u.addMovement(motionEvent);
        if (actionMasked == 0) {
            this.f2088w = (int) motionEvent.getX();
        } else if ((actionMasked == 1 || actionMasked == 3) && this.f2077l) {
            this.f2077l = false;
            return false;
        }
        return (this.f2077l || (viewDragHelper = this.f2076k) == null || !viewDragHelper.shouldInterceptTouchEvent(motionEvent)) ? false : true;
    }

    @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.Behavior
    public boolean onLayoutChild(CoordinatorLayout coordinatorLayout, View view, int i2) {
        if (ViewCompat.getFitsSystemWindows(coordinatorLayout) && !ViewCompat.getFitsSystemWindows(view)) {
            view.setFitsSystemWindows(true);
        }
        if (this.f2083r == null) {
            this.f2083r = new WeakReference(view);
            this.f2087v = new J.c(view);
            g gVar = this.f2068c;
            if (gVar != null) {
                ViewCompat.setBackground(view, gVar);
                g gVar2 = this.f2068c;
                float elevation = this.f2072g;
                if (elevation == -1.0f) {
                    elevation = ViewCompat.getElevation(view);
                }
                gVar2.S(elevation);
            } else {
                ColorStateList colorStateList = this.f2069d;
                if (colorStateList != null) {
                    ViewCompat.setBackgroundTintList(view, colorStateList);
                }
            }
            f0(view);
            d0();
            if (ViewCompat.getImportantForAccessibility(view) == 0) {
                ViewCompat.setImportantForAccessibility(view, 1);
            }
            s(view);
        }
        V(view, i2);
        if (this.f2076k == null) {
            this.f2076k = ViewDragHelper.create(coordinatorLayout, this.f2090y);
        }
        int iG = this.f2066a.g(view);
        coordinatorLayout.onLayoutChild(view, i2);
        this.f2080o = coordinatorLayout.getWidth();
        this.f2081p = this.f2066a.h(coordinatorLayout);
        this.f2079n = view.getWidth();
        ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) view.getLayoutParams();
        this.f2082q = marginLayoutParams != null ? this.f2066a.a(marginLayoutParams) : 0;
        ViewCompat.offsetLeftAndRight(view, l(iG, view));
        O(coordinatorLayout);
        Iterator it = this.f2089x.iterator();
        while (it.hasNext()) {
            android.support.v4.media.a.a(it.next());
        }
        return true;
    }

    @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.Behavior
    public boolean onMeasureChild(CoordinatorLayout coordinatorLayout, View view, int i2, int i3, int i4, int i5) {
        ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) view.getLayoutParams();
        view.measure(t(i2, coordinatorLayout.getPaddingLeft() + coordinatorLayout.getPaddingRight() + marginLayoutParams.leftMargin + marginLayoutParams.rightMargin + i3, -1, marginLayoutParams.width), t(i4, coordinatorLayout.getPaddingTop() + coordinatorLayout.getPaddingBottom() + marginLayoutParams.topMargin + marginLayoutParams.bottomMargin + i5, -1, marginLayoutParams.height));
        return true;
    }

    @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.Behavior
    public void onRestoreInstanceState(CoordinatorLayout coordinatorLayout, View view, Parcelable parcelable) {
        b bVar = (b) parcelable;
        if (bVar.getSuperState() != null) {
            super.onRestoreInstanceState(coordinatorLayout, view, bVar.getSuperState());
        }
        int i2 = bVar.f2092a;
        if (i2 == 1 || i2 == 2) {
            i2 = 5;
        }
        this.f2074i = i2;
        this.f2075j = i2;
    }

    @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.Behavior
    public Parcelable onSaveInstanceState(CoordinatorLayout coordinatorLayout, View view) {
        return new b(super.onSaveInstanceState(coordinatorLayout, view), this);
    }

    @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.Behavior
    public boolean onTouchEvent(CoordinatorLayout coordinatorLayout, View view, MotionEvent motionEvent) {
        if (!view.isShown()) {
            return false;
        }
        int actionMasked = motionEvent.getActionMasked();
        if (this.f2074i == 1 && actionMasked == 0) {
            return true;
        }
        if (Y()) {
            this.f2076k.processTouchEvent(motionEvent);
        }
        if (actionMasked == 0) {
            Q();
        }
        if (this.f2086u == null) {
            this.f2086u = VelocityTracker.obtain();
        }
        this.f2086u.addMovement(motionEvent);
        if (Y() && actionMasked == 2 && !this.f2077l && I(motionEvent)) {
            this.f2076k.captureChildView(view, motionEvent.getPointerId(motionEvent.getActionIndex()));
        }
        return !this.f2077l;
    }

    public final void r(View view, int i2) {
        if (this.f2089x.isEmpty()) {
            return;
        }
        this.f2066a.b(i2);
        Iterator it = this.f2089x.iterator();
        if (it.hasNext()) {
            android.support.v4.media.a.a(it.next());
            throw null;
        }
    }

    public final void s(View view) {
        if (ViewCompat.getAccessibilityPaneTitle(view) == null) {
            ViewCompat.setAccessibilityPaneTitle(view, view.getResources().getString(f2065z));
        }
    }

    public int u() {
        return this.f2079n;
    }

    public View v() {
        WeakReference weakReference = this.f2084s;
        if (weakReference != null) {
            return (View) weakReference.get();
        }
        return null;
    }

    public int w() {
        return this.f2066a.c();
    }

    public float x() {
        return this.f2078m;
    }

    public float y() {
        return 0.5f;
    }

    public int z() {
        return this.f2082q;
    }

    public static class b extends AbsSavedState {
        public static final Parcelable.Creator<b> CREATOR = new a();

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final int f2092a;

        public class a implements Parcelable.ClassLoaderCreator {
            @Override // android.os.Parcelable.Creator
            /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
            public b createFromParcel(Parcel parcel) {
                return new b(parcel, (ClassLoader) null);
            }

            @Override // android.os.Parcelable.ClassLoaderCreator
            /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
            public b createFromParcel(Parcel parcel, ClassLoader classLoader) {
                return new b(parcel, classLoader);
            }

            @Override // android.os.Parcelable.Creator
            /* JADX INFO: renamed from: c, reason: merged with bridge method [inline-methods] */
            public b[] newArray(int i2) {
                return new b[i2];
            }
        }

        public b(Parcel parcel, ClassLoader classLoader) {
            super(parcel, classLoader);
            this.f2092a = parcel.readInt();
        }

        @Override // androidx.customview.view.AbsSavedState, android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i2) {
            super.writeToParcel(parcel, i2);
            parcel.writeInt(this.f2092a);
        }

        public b(Parcelable parcelable, SideSheetBehavior sideSheetBehavior) {
            super(parcelable);
            this.f2092a = sideSheetBehavior.f2074i;
        }
    }

    public SideSheetBehavior(@NonNull Context context, @Nullable AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f2071f = new c();
        this.f2073h = true;
        this.f2074i = 5;
        this.f2075j = 5;
        this.f2078m = 0.1f;
        this.f2085t = -1;
        this.f2089x = new LinkedHashSet();
        this.f2090y = new a();
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, j.u4);
        int i2 = j.w4;
        if (typedArrayObtainStyledAttributes.hasValue(i2)) {
            this.f2069d = L.c.a(context, typedArrayObtainStyledAttributes, i2);
        }
        if (typedArrayObtainStyledAttributes.hasValue(j.z4)) {
            this.f2070e = k.e(context, attributeSet, 0, f2064A).m();
        }
        int i3 = j.y4;
        if (typedArrayObtainStyledAttributes.hasValue(i3)) {
            S(typedArrayObtainStyledAttributes.getResourceId(i3, -1));
        }
        q(context);
        this.f2072g = typedArrayObtainStyledAttributes.getDimension(j.v4, -1.0f);
        T(typedArrayObtainStyledAttributes.getBoolean(j.x4, true));
        typedArrayObtainStyledAttributes.recycle();
        this.f2067b = ViewConfiguration.get(context).getScaledMaximumFlingVelocity();
    }
}
