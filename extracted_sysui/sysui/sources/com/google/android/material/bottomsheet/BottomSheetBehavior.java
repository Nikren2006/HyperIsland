package com.google.android.material.bottomsheet;

import H.n;
import O.k;
import android.R;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.util.Log;
import android.util.SparseIntArray;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.RoundedCorner;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.WindowInsets;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.core.widgets.analyzer.BasicMeasure;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.math.MathUtils;
import androidx.core.view.ViewCompat;
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;
import androidx.core.view.accessibility.AccessibilityViewCommand;
import androidx.customview.view.AbsSavedState;
import androidx.customview.widget.ViewDragHelper;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import t.AbstractC0741a;
import t.h;
import t.i;
import t.j;
import w.C0764a;

/* JADX INFO: loaded from: classes2.dex */
public class BottomSheetBehavior<V extends View> extends CoordinatorLayout.Behavior<V> {

    /* JADX INFO: renamed from: i0, reason: collision with root package name */
    public static final int f1639i0 = i.f6680c;

    /* JADX INFO: renamed from: A, reason: collision with root package name */
    public boolean f1640A;

    /* JADX INFO: renamed from: B, reason: collision with root package name */
    public final g f1641B;

    /* JADX INFO: renamed from: C, reason: collision with root package name */
    public ValueAnimator f1642C;

    /* JADX INFO: renamed from: D, reason: collision with root package name */
    public int f1643D;

    /* JADX INFO: renamed from: E, reason: collision with root package name */
    public int f1644E;

    /* JADX INFO: renamed from: F, reason: collision with root package name */
    public int f1645F;

    /* JADX INFO: renamed from: G, reason: collision with root package name */
    public float f1646G;

    /* JADX INFO: renamed from: H, reason: collision with root package name */
    public int f1647H;

    /* JADX INFO: renamed from: I, reason: collision with root package name */
    public float f1648I;

    /* JADX INFO: renamed from: J, reason: collision with root package name */
    public boolean f1649J;

    /* JADX INFO: renamed from: K, reason: collision with root package name */
    public boolean f1650K;

    /* JADX INFO: renamed from: L, reason: collision with root package name */
    public boolean f1651L;

    /* JADX INFO: renamed from: M, reason: collision with root package name */
    public int f1652M;

    /* JADX INFO: renamed from: N, reason: collision with root package name */
    public int f1653N;

    /* JADX INFO: renamed from: O, reason: collision with root package name */
    public ViewDragHelper f1654O;

    /* JADX INFO: renamed from: P, reason: collision with root package name */
    public boolean f1655P;

    /* JADX INFO: renamed from: Q, reason: collision with root package name */
    public int f1656Q;

    /* JADX INFO: renamed from: R, reason: collision with root package name */
    public boolean f1657R;

    /* JADX INFO: renamed from: S, reason: collision with root package name */
    public float f1658S;

    /* JADX INFO: renamed from: T, reason: collision with root package name */
    public int f1659T;

    /* JADX INFO: renamed from: U, reason: collision with root package name */
    public int f1660U;

    /* JADX INFO: renamed from: V, reason: collision with root package name */
    public int f1661V;

    /* JADX INFO: renamed from: W, reason: collision with root package name */
    public WeakReference f1662W;

    /* JADX INFO: renamed from: X, reason: collision with root package name */
    public WeakReference f1663X;

    /* JADX INFO: renamed from: Y, reason: collision with root package name */
    public WeakReference f1664Y;

    /* JADX INFO: renamed from: Z, reason: collision with root package name */
    public final ArrayList f1665Z;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public int f1666a;

    /* JADX INFO: renamed from: a0, reason: collision with root package name */
    public VelocityTracker f1667a0;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public boolean f1668b;

    /* JADX INFO: renamed from: b0, reason: collision with root package name */
    public J.b f1669b0;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public boolean f1670c;

    /* JADX INFO: renamed from: c0, reason: collision with root package name */
    public int f1671c0;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public float f1672d;

    /* JADX INFO: renamed from: d0, reason: collision with root package name */
    public int f1673d0;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public int f1674e;

    /* JADX INFO: renamed from: e0, reason: collision with root package name */
    public boolean f1675e0;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public int f1676f;

    /* JADX INFO: renamed from: f0, reason: collision with root package name */
    public Map f1677f0;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public boolean f1678g;

    /* JADX INFO: renamed from: g0, reason: collision with root package name */
    public final SparseIntArray f1679g0;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public int f1680h;

    /* JADX INFO: renamed from: h0, reason: collision with root package name */
    public final ViewDragHelper.Callback f1681h0;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public int f1682i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public O.g f1683j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public ColorStateList f1684k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public int f1685l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public int f1686m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public int f1687n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public boolean f1688o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public boolean f1689p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public boolean f1690q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public boolean f1691r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public boolean f1692s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public boolean f1693t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public boolean f1694u;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public boolean f1695v;

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    public int f1696w;

    /* JADX INFO: renamed from: x, reason: collision with root package name */
    public int f1697x;

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    public boolean f1698y;

    /* JADX INFO: renamed from: z, reason: collision with root package name */
    public k f1699z;

    public class a implements Runnable {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ View f1700a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final /* synthetic */ int f1701b;

        public a(View view, int i2) {
            this.f1700a = view;
            this.f1701b = i2;
        }

        @Override // java.lang.Runnable
        public void run() {
            BottomSheetBehavior.this.r0(this.f1700a, this.f1701b, false);
        }
    }

    public class b implements ValueAnimator.AnimatorUpdateListener {
        public b() {
        }

        @Override // android.animation.ValueAnimator.AnimatorUpdateListener
        public void onAnimationUpdate(ValueAnimator valueAnimator) {
            float fFloatValue = ((Float) valueAnimator.getAnimatedValue()).floatValue();
            if (BottomSheetBehavior.this.f1683j != null) {
                BottomSheetBehavior.this.f1683j.U(fFloatValue);
            }
        }
    }

    public class c implements n.c {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ boolean f1704a;

        public c(boolean z2) {
            this.f1704a = z2;
        }

        /* JADX WARN: Removed duplicated region for block: B:22:0x0080  */
        /* JADX WARN: Removed duplicated region for block: B:33:0x00a3  */
        @Override // H.n.c
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public androidx.core.view.WindowInsetsCompat a(android.view.View r11, androidx.core.view.WindowInsetsCompat r12, H.n.d r13) {
            /*
                Method dump skipped, instruction units count: 205
                To view this dump add '--comments-level debug' option
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.android.material.bottomsheet.BottomSheetBehavior.c.a(android.view.View, androidx.core.view.WindowInsetsCompat, H.n$d):androidx.core.view.WindowInsetsCompat");
        }
    }

    public class d extends ViewDragHelper.Callback {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public long f1706a;

        public d() {
        }

        public final boolean a(View view) {
            int top = view.getTop();
            BottomSheetBehavior bottomSheetBehavior = BottomSheetBehavior.this;
            return top > (bottomSheetBehavior.f1661V + bottomSheetBehavior.I()) / 2;
        }

        @Override // androidx.customview.widget.ViewDragHelper.Callback
        public int clampViewPositionHorizontal(View view, int i2, int i3) {
            return view.getLeft();
        }

        @Override // androidx.customview.widget.ViewDragHelper.Callback
        public int clampViewPositionVertical(View view, int i2, int i3) {
            return MathUtils.clamp(i2, BottomSheetBehavior.this.I(), getViewVerticalDragRange(view));
        }

        @Override // androidx.customview.widget.ViewDragHelper.Callback
        public int getViewVerticalDragRange(View view) {
            return BottomSheetBehavior.this.A() ? BottomSheetBehavior.this.f1661V : BottomSheetBehavior.this.f1647H;
        }

        @Override // androidx.customview.widget.ViewDragHelper.Callback
        public void onViewDragStateChanged(int i2) {
            if (i2 == 1 && BottomSheetBehavior.this.f1651L) {
                BottomSheetBehavior.this.k0(1);
            }
        }

        @Override // androidx.customview.widget.ViewDragHelper.Callback
        public void onViewPositionChanged(View view, int i2, int i3, int i4, int i5) {
            BottomSheetBehavior.this.F(i3);
        }

        /* JADX WARN: Removed duplicated region for block: B:39:0x00ad  */
        /* JADX WARN: Removed duplicated region for block: B:6:0x0010  */
        @Override // androidx.customview.widget.ViewDragHelper.Callback
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public void onViewReleased(android.view.View r8, float r9, float r10) {
            /*
                Method dump skipped, instruction units count: 308
                To view this dump add '--comments-level debug' option
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.android.material.bottomsheet.BottomSheetBehavior.d.onViewReleased(android.view.View, float, float):void");
        }

        @Override // androidx.customview.widget.ViewDragHelper.Callback
        public boolean tryCaptureView(View view, int i2) {
            BottomSheetBehavior bottomSheetBehavior = BottomSheetBehavior.this;
            int i3 = bottomSheetBehavior.f1652M;
            if (i3 == 1 || bottomSheetBehavior.f1675e0) {
                return false;
            }
            if (i3 == 3 && bottomSheetBehavior.f1671c0 == i2) {
                WeakReference weakReference = bottomSheetBehavior.f1664Y;
                View view2 = weakReference != null ? (View) weakReference.get() : null;
                if (view2 != null && view2.canScrollVertically(-1)) {
                    return false;
                }
            }
            this.f1706a = System.currentTimeMillis();
            WeakReference weakReference2 = BottomSheetBehavior.this.f1662W;
            return weakReference2 != null && weakReference2.get() == view;
        }
    }

    public class e implements AccessibilityViewCommand {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ int f1708a;

        public e(int i2) {
            this.f1708a = i2;
        }

        @Override // androidx.core.view.accessibility.AccessibilityViewCommand
        public boolean perform(View view, AccessibilityViewCommand.CommandArguments commandArguments) {
            BottomSheetBehavior.this.j0(this.f1708a);
            return true;
        }
    }

    public BottomSheetBehavior() {
        this.f1666a = 0;
        this.f1668b = true;
        this.f1670c = false;
        this.f1685l = -1;
        this.f1686m = -1;
        this.f1641B = new g(this, null);
        this.f1646G = 0.5f;
        this.f1648I = -1.0f;
        this.f1651L = true;
        this.f1652M = 4;
        this.f1653N = 4;
        this.f1658S = 0.1f;
        this.f1665Z = new ArrayList();
        this.f1673d0 = -1;
        this.f1679g0 = new SparseIntArray();
        this.f1681h0 = new d();
    }

    public final boolean A() {
        return O() && P();
    }

    public final void B(View view, int i2) {
        if (view == null) {
            return;
        }
        ViewCompat.removeAccessibilityAction(view, 524288);
        ViewCompat.removeAccessibilityAction(view, 262144);
        ViewCompat.removeAccessibilityAction(view, 1048576);
        int i3 = this.f1679g0.get(i2, -1);
        if (i3 != -1) {
            ViewCompat.removeAccessibilityAction(view, i3);
            this.f1679g0.delete(i2);
        }
    }

    public final AccessibilityViewCommand C(int i2) {
        return new e(i2);
    }

    public final void D(Context context) {
        if (this.f1699z == null) {
            return;
        }
        O.g gVar = new O.g(this.f1699z);
        this.f1683j = gVar;
        gVar.J(context);
        ColorStateList colorStateList = this.f1684k;
        if (colorStateList != null) {
            this.f1683j.T(colorStateList);
            return;
        }
        TypedValue typedValue = new TypedValue();
        context.getTheme().resolveAttribute(R.attr.colorBackground, typedValue, true);
        this.f1683j.setTint(typedValue.data);
    }

    public final void E() {
        ValueAnimator valueAnimatorOfFloat = ValueAnimator.ofFloat(x(), 1.0f);
        this.f1642C = valueAnimatorOfFloat;
        valueAnimatorOfFloat.setDuration(500L);
        this.f1642C.addUpdateListener(new b());
    }

    public void F(int i2) {
        if (((View) this.f1662W.get()) == null || this.f1665Z.isEmpty()) {
            return;
        }
        z(i2);
        if (this.f1665Z.size() <= 0) {
            return;
        }
        android.support.v4.media.a.a(this.f1665Z.get(0));
        throw null;
    }

    public View G(View view) {
        if (view.getVisibility() != 0) {
            return null;
        }
        if (ViewCompat.isNestedScrollingEnabled(view)) {
            return view;
        }
        if (view instanceof ViewGroup) {
            ViewGroup viewGroup = (ViewGroup) view;
            int childCount = viewGroup.getChildCount();
            for (int i2 = 0; i2 < childCount; i2++) {
                View viewG = G(viewGroup.getChildAt(i2));
                if (viewG != null) {
                    return viewG;
                }
            }
        }
        return null;
    }

    public final int H(int i2, int i3, int i4, int i5) {
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

    public int I() {
        if (this.f1668b) {
            return this.f1644E;
        }
        return Math.max(this.f1643D, this.f1692s ? 0 : this.f1697x);
    }

    public final int J(int i2) {
        if (i2 == 3) {
            return I();
        }
        if (i2 == 4) {
            return this.f1647H;
        }
        if (i2 == 5) {
            return this.f1661V;
        }
        if (i2 == 6) {
            return this.f1645F;
        }
        throw new IllegalArgumentException("Invalid state to get top offset: " + i2);
    }

    public final float K() {
        VelocityTracker velocityTracker = this.f1667a0;
        if (velocityTracker == null) {
            return 0.0f;
        }
        velocityTracker.computeCurrentVelocity(1000, this.f1672d);
        return this.f1667a0.getYVelocity(this.f1671c0);
    }

    public final boolean L() {
        WeakReference weakReference = this.f1662W;
        if (weakReference == null || weakReference.get() == null) {
            return false;
        }
        int[] iArr = new int[2];
        ((View) this.f1662W.get()).getLocationOnScreen(iArr);
        return iArr[1] == 0;
    }

    public final boolean M() {
        return this.f1652M == 3 && (this.f1698y || L());
    }

    public boolean N() {
        return this.f1688o;
    }

    public boolean O() {
        return this.f1649J;
    }

    public boolean P() {
        return true;
    }

    public final boolean Q(View view) {
        ViewParent parent = view.getParent();
        return parent != null && parent.isLayoutRequested() && ViewCompat.isAttachedToWindow(view);
    }

    public boolean R() {
        return true;
    }

    public final void S(View view, AccessibilityNodeInfoCompat.AccessibilityActionCompat accessibilityActionCompat, int i2) {
        ViewCompat.replaceAccessibilityAction(view, accessibilityActionCompat, null, C(i2));
    }

    public final void T() {
        this.f1671c0 = -1;
        this.f1673d0 = -1;
        VelocityTracker velocityTracker = this.f1667a0;
        if (velocityTracker != null) {
            velocityTracker.recycle();
            this.f1667a0 = null;
        }
    }

    public final void U(f fVar) {
        int i2 = this.f1666a;
        if (i2 == 0) {
            return;
        }
        if (i2 == -1 || (i2 & 1) == 1) {
            this.f1676f = fVar.f1711b;
        }
        if (i2 == -1 || (i2 & 2) == 2) {
            this.f1668b = fVar.f1712c;
        }
        if (i2 == -1 || (i2 & 4) == 4) {
            this.f1649J = fVar.f1713d;
        }
        if (i2 == -1 || (i2 & 8) == 8) {
            this.f1650K = fVar.f1714e;
        }
    }

    public final void V(View view, Runnable runnable) {
        if (Q(view)) {
            view.post(runnable);
        } else {
            runnable.run();
        }
    }

    public void W(boolean z2) {
        this.f1651L = z2;
    }

    public void X(int i2) {
        if (i2 < 0) {
            throw new IllegalArgumentException("offset must be greater than or equal to 0");
        }
        this.f1643D = i2;
        u0(this.f1652M, true);
    }

    public void Y(boolean z2) {
        if (this.f1668b == z2) {
            return;
        }
        this.f1668b = z2;
        if (this.f1662W != null) {
            u();
        }
        k0((this.f1668b && this.f1652M == 6) ? 3 : this.f1652M);
        u0(this.f1652M, true);
        s0();
    }

    public void Z(boolean z2) {
        this.f1688o = z2;
    }

    public void a0(float f2) {
        if (f2 <= 0.0f || f2 >= 1.0f) {
            throw new IllegalArgumentException("ratio must be a float value between 0 and 1");
        }
        this.f1646G = f2;
        if (this.f1662W != null) {
            w();
        }
    }

    public void b0(boolean z2) {
        if (this.f1649J != z2) {
            this.f1649J = z2;
            if (!z2 && this.f1652M == 5) {
                j0(4);
            }
            s0();
        }
    }

    public void c0(int i2) {
        this.f1686m = i2;
    }

    public void d0(int i2) {
        this.f1685l = i2;
    }

    public void e0(int i2) {
        f0(i2, false);
    }

    public final void f0(int i2, boolean z2) {
        if (i2 == -1) {
            if (this.f1678g) {
                return;
            } else {
                this.f1678g = true;
            }
        } else {
            if (!this.f1678g && this.f1676f == i2) {
                return;
            }
            this.f1678g = false;
            this.f1676f = Math.max(0, i2);
        }
        w0(z2);
    }

    public void g0(int i2) {
        this.f1666a = i2;
    }

    public void h0(int i2) {
        this.f1674e = i2;
    }

    public void i0(boolean z2) {
        this.f1650K = z2;
    }

    public void j0(int i2) {
        if (i2 == 1 || i2 == 2) {
            StringBuilder sb = new StringBuilder();
            sb.append("STATE_");
            sb.append(i2 == 1 ? "DRAGGING" : "SETTLING");
            sb.append(" should not be set externally.");
            throw new IllegalArgumentException(sb.toString());
        }
        if (!this.f1649J && i2 == 5) {
            Log.w("BottomSheetBehavior", "Cannot set state: " + i2);
            return;
        }
        int i3 = (i2 == 6 && this.f1668b && J(i2) <= this.f1644E) ? 3 : i2;
        WeakReference weakReference = this.f1662W;
        if (weakReference == null || weakReference.get() == null) {
            k0(i2);
        } else {
            View view = (View) this.f1662W.get();
            V(view, new a(view, i3));
        }
    }

    public void k0(int i2) {
        if (this.f1652M == i2) {
            return;
        }
        this.f1652M = i2;
        if (i2 == 4 || i2 == 3 || i2 == 6 || (this.f1649J && i2 == 5)) {
            this.f1653N = i2;
        }
        WeakReference weakReference = this.f1662W;
        if (weakReference == null || ((View) weakReference.get()) == null) {
            return;
        }
        if (i2 == 3) {
            v0(true);
        } else if (i2 == 6 || i2 == 5 || i2 == 4) {
            v0(false);
        }
        u0(i2, true);
        if (this.f1665Z.size() <= 0) {
            s0();
        } else {
            android.support.v4.media.a.a(this.f1665Z.get(0));
            throw null;
        }
    }

    public final void l0(View view) {
        boolean z2 = (N() || this.f1678g) ? false : true;
        if (this.f1689p || this.f1690q || this.f1691r || this.f1693t || this.f1694u || this.f1695v || z2) {
            n.b(view, new c(z2));
        }
    }

    public boolean m0(long j2, float f2) {
        return false;
    }

    public final boolean n0() {
        return this.f1654O != null && (this.f1651L || this.f1652M == 1);
    }

    public boolean o0(View view, float f2) {
        if (this.f1650K) {
            return true;
        }
        if (P() && view.getTop() >= this.f1647H) {
            return Math.abs((((float) view.getTop()) + (f2 * this.f1658S)) - ((float) this.f1647H)) / ((float) y()) > 0.5f;
        }
        return false;
    }

    @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.Behavior
    public void onAttachedToLayoutParams(CoordinatorLayout.LayoutParams layoutParams) {
        super.onAttachedToLayoutParams(layoutParams);
        this.f1662W = null;
        this.f1654O = null;
        this.f1669b0 = null;
    }

    @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.Behavior
    public void onDetachedFromLayoutParams() {
        super.onDetachedFromLayoutParams();
        this.f1662W = null;
        this.f1654O = null;
        this.f1669b0 = null;
    }

    @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.Behavior
    public boolean onInterceptTouchEvent(CoordinatorLayout coordinatorLayout, View view, MotionEvent motionEvent) {
        int i2;
        ViewDragHelper viewDragHelper;
        if (!view.isShown() || !this.f1651L) {
            this.f1655P = true;
            return false;
        }
        int actionMasked = motionEvent.getActionMasked();
        if (actionMasked == 0) {
            T();
        }
        if (this.f1667a0 == null) {
            this.f1667a0 = VelocityTracker.obtain();
        }
        this.f1667a0.addMovement(motionEvent);
        if (actionMasked == 0) {
            int x2 = (int) motionEvent.getX();
            this.f1673d0 = (int) motionEvent.getY();
            if (this.f1652M != 2) {
                WeakReference weakReference = this.f1664Y;
                View view2 = weakReference != null ? (View) weakReference.get() : null;
                if (view2 != null && coordinatorLayout.isPointInChildBounds(view2, x2, this.f1673d0)) {
                    this.f1671c0 = motionEvent.getPointerId(motionEvent.getActionIndex());
                    this.f1675e0 = true;
                }
            }
            this.f1655P = this.f1671c0 == -1 && !coordinatorLayout.isPointInChildBounds(view, x2, this.f1673d0);
        } else if (actionMasked == 1 || actionMasked == 3) {
            this.f1675e0 = false;
            this.f1671c0 = -1;
            if (this.f1655P) {
                this.f1655P = false;
                return false;
            }
        }
        if (!this.f1655P && (viewDragHelper = this.f1654O) != null && viewDragHelper.shouldInterceptTouchEvent(motionEvent)) {
            return true;
        }
        WeakReference weakReference2 = this.f1664Y;
        View view3 = weakReference2 != null ? (View) weakReference2.get() : null;
        return (actionMasked != 2 || view3 == null || this.f1655P || this.f1652M == 1 || coordinatorLayout.isPointInChildBounds(view3, (int) motionEvent.getX(), (int) motionEvent.getY()) || this.f1654O == null || (i2 = this.f1673d0) == -1 || Math.abs(((float) i2) - motionEvent.getY()) <= ((float) this.f1654O.getTouchSlop())) ? false : true;
    }

    @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.Behavior
    public boolean onLayoutChild(CoordinatorLayout coordinatorLayout, View view, int i2) {
        if (ViewCompat.getFitsSystemWindows(coordinatorLayout) && !ViewCompat.getFitsSystemWindows(view)) {
            view.setFitsSystemWindows(true);
        }
        if (this.f1662W == null) {
            this.f1680h = coordinatorLayout.getResources().getDimensionPixelSize(t.c.f6558a);
            l0(view);
            ViewCompat.setWindowInsetsAnimationCallback(view, new C0764a(view));
            this.f1662W = new WeakReference(view);
            this.f1669b0 = new J.b(view);
            O.g gVar = this.f1683j;
            if (gVar != null) {
                ViewCompat.setBackground(view, gVar);
                O.g gVar2 = this.f1683j;
                float elevation = this.f1648I;
                if (elevation == -1.0f) {
                    elevation = ViewCompat.getElevation(view);
                }
                gVar2.S(elevation);
            } else {
                ColorStateList colorStateList = this.f1684k;
                if (colorStateList != null) {
                    ViewCompat.setBackgroundTintList(view, colorStateList);
                }
            }
            s0();
            if (ViewCompat.getImportantForAccessibility(view) == 0) {
                ViewCompat.setImportantForAccessibility(view, 1);
            }
        }
        if (this.f1654O == null) {
            this.f1654O = ViewDragHelper.create(coordinatorLayout, this.f1681h0);
        }
        int top = view.getTop();
        coordinatorLayout.onLayoutChild(view, i2);
        this.f1660U = coordinatorLayout.getWidth();
        this.f1661V = coordinatorLayout.getHeight();
        int height = view.getHeight();
        this.f1659T = height;
        int iMin = this.f1661V;
        int i3 = iMin - height;
        int i4 = this.f1697x;
        if (i3 < i4) {
            if (this.f1692s) {
                int i5 = this.f1686m;
                if (i5 != -1) {
                    iMin = Math.min(iMin, i5);
                }
                this.f1659T = iMin;
            } else {
                int iMin2 = iMin - i4;
                int i6 = this.f1686m;
                if (i6 != -1) {
                    iMin2 = Math.min(iMin2, i6);
                }
                this.f1659T = iMin2;
            }
        }
        this.f1644E = Math.max(0, this.f1661V - this.f1659T);
        w();
        u();
        int i7 = this.f1652M;
        if (i7 == 3) {
            ViewCompat.offsetTopAndBottom(view, I());
        } else if (i7 == 6) {
            ViewCompat.offsetTopAndBottom(view, this.f1645F);
        } else if (this.f1649J && i7 == 5) {
            ViewCompat.offsetTopAndBottom(view, this.f1661V);
        } else if (i7 == 4) {
            ViewCompat.offsetTopAndBottom(view, this.f1647H);
        } else if (i7 == 1 || i7 == 2) {
            ViewCompat.offsetTopAndBottom(view, top - view.getTop());
        }
        u0(this.f1652M, false);
        this.f1664Y = new WeakReference(G(view));
        if (this.f1665Z.size() <= 0) {
            return true;
        }
        android.support.v4.media.a.a(this.f1665Z.get(0));
        throw null;
    }

    @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.Behavior
    public boolean onMeasureChild(CoordinatorLayout coordinatorLayout, View view, int i2, int i3, int i4, int i5) {
        ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) view.getLayoutParams();
        view.measure(H(i2, coordinatorLayout.getPaddingLeft() + coordinatorLayout.getPaddingRight() + marginLayoutParams.leftMargin + marginLayoutParams.rightMargin + i3, this.f1685l, marginLayoutParams.width), H(i4, coordinatorLayout.getPaddingTop() + coordinatorLayout.getPaddingBottom() + marginLayoutParams.topMargin + marginLayoutParams.bottomMargin + i5, this.f1686m, marginLayoutParams.height));
        return true;
    }

    @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.Behavior
    public boolean onNestedPreFling(CoordinatorLayout coordinatorLayout, View view, View view2, float f2, float f3) {
        WeakReference weakReference;
        if (R() && (weakReference = this.f1664Y) != null && view2 == weakReference.get()) {
            return this.f1652M != 3 || super.onNestedPreFling(coordinatorLayout, view, view2, f2, f3);
        }
        return false;
    }

    @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.Behavior
    public void onNestedPreScroll(CoordinatorLayout coordinatorLayout, View view, View view2, int i2, int i3, int[] iArr, int i4) {
        if (i4 == 1) {
            return;
        }
        WeakReference weakReference = this.f1664Y;
        View view3 = weakReference != null ? (View) weakReference.get() : null;
        if (!R() || view2 == view3) {
            int top = view.getTop();
            int i5 = top - i3;
            if (i3 > 0) {
                if (i5 < I()) {
                    int I2 = top - I();
                    iArr[1] = I2;
                    ViewCompat.offsetTopAndBottom(view, -I2);
                    k0(3);
                } else {
                    if (!this.f1651L) {
                        return;
                    }
                    iArr[1] = i3;
                    ViewCompat.offsetTopAndBottom(view, -i3);
                    k0(1);
                }
            } else if (i3 < 0 && !view2.canScrollVertically(-1)) {
                if (i5 > this.f1647H && !A()) {
                    int i6 = top - this.f1647H;
                    iArr[1] = i6;
                    ViewCompat.offsetTopAndBottom(view, -i6);
                    k0(4);
                } else {
                    if (!this.f1651L) {
                        return;
                    }
                    iArr[1] = i3;
                    ViewCompat.offsetTopAndBottom(view, -i3);
                    k0(1);
                }
            }
            F(view.getTop());
            this.f1656Q = i3;
            this.f1657R = true;
        }
    }

    @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.Behavior
    public void onNestedScroll(CoordinatorLayout coordinatorLayout, View view, View view2, int i2, int i3, int i4, int i5, int i6, int[] iArr) {
    }

    @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.Behavior
    public void onRestoreInstanceState(CoordinatorLayout coordinatorLayout, View view, Parcelable parcelable) {
        f fVar = (f) parcelable;
        super.onRestoreInstanceState(coordinatorLayout, view, fVar.getSuperState());
        U(fVar);
        int i2 = fVar.f1710a;
        if (i2 == 1 || i2 == 2) {
            this.f1652M = 4;
            this.f1653N = 4;
        } else {
            this.f1652M = i2;
            this.f1653N = i2;
        }
    }

    @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.Behavior
    public Parcelable onSaveInstanceState(CoordinatorLayout coordinatorLayout, View view) {
        return new f(super.onSaveInstanceState(coordinatorLayout, view), this);
    }

    @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.Behavior
    public boolean onStartNestedScroll(CoordinatorLayout coordinatorLayout, View view, View view2, View view3, int i2, int i3) {
        this.f1656Q = 0;
        this.f1657R = false;
        return (i2 & 2) != 0;
    }

    /* JADX WARN: Removed duplicated region for block: B:48:0x0092  */
    /* JADX WARN: Removed duplicated region for block: B:51:0x00a9  */
    @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.Behavior
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void onStopNestedScroll(androidx.coordinatorlayout.widget.CoordinatorLayout r3, android.view.View r4, android.view.View r5, int r6) {
        /*
            r2 = this;
            int r3 = r4.getTop()
            int r6 = r2.I()
            r0 = 3
            if (r3 != r6) goto Lf
            r2.k0(r0)
            return
        Lf:
            boolean r3 = r2.R()
            if (r3 == 0) goto L24
            java.lang.ref.WeakReference r3 = r2.f1664Y
            if (r3 == 0) goto L23
            java.lang.Object r3 = r3.get()
            if (r5 != r3) goto L23
            boolean r3 = r2.f1657R
            if (r3 != 0) goto L24
        L23:
            return
        L24:
            int r3 = r2.f1656Q
            r5 = 6
            if (r3 <= 0) goto L39
            boolean r3 = r2.f1668b
            if (r3 == 0) goto L2f
            goto Laa
        L2f:
            int r3 = r4.getTop()
            int r6 = r2.f1645F
            if (r3 <= r6) goto Laa
            goto La9
        L39:
            boolean r3 = r2.f1649J
            if (r3 == 0) goto L49
            float r3 = r2.K()
            boolean r3 = r2.o0(r4, r3)
            if (r3 == 0) goto L49
            r0 = 5
            goto Laa
        L49:
            int r3 = r2.f1656Q
            r6 = 4
            if (r3 != 0) goto L8e
            int r3 = r4.getTop()
            boolean r1 = r2.f1668b
            if (r1 == 0) goto L68
            int r5 = r2.f1644E
            int r5 = r3 - r5
            int r5 = java.lang.Math.abs(r5)
            int r1 = r2.f1647H
            int r3 = r3 - r1
            int r3 = java.lang.Math.abs(r3)
            if (r5 >= r3) goto L92
            goto Laa
        L68:
            int r1 = r2.f1645F
            if (r3 >= r1) goto L7e
            int r1 = r2.f1647H
            int r1 = r3 - r1
            int r1 = java.lang.Math.abs(r1)
            if (r3 >= r1) goto L77
            goto Laa
        L77:
            boolean r3 = r2.p0()
            if (r3 == 0) goto La9
            goto L92
        L7e:
            int r0 = r3 - r1
            int r0 = java.lang.Math.abs(r0)
            int r1 = r2.f1647H
            int r3 = r3 - r1
            int r3 = java.lang.Math.abs(r3)
            if (r0 >= r3) goto L92
            goto La9
        L8e:
            boolean r3 = r2.f1668b
            if (r3 == 0) goto L94
        L92:
            r0 = r6
            goto Laa
        L94:
            int r3 = r4.getTop()
            int r0 = r2.f1645F
            int r0 = r3 - r0
            int r0 = java.lang.Math.abs(r0)
            int r1 = r2.f1647H
            int r3 = r3 - r1
            int r3 = java.lang.Math.abs(r3)
            if (r0 >= r3) goto L92
        La9:
            r0 = r5
        Laa:
            r3 = 0
            r2.r0(r4, r0, r3)
            r2.f1657R = r3
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.material.bottomsheet.BottomSheetBehavior.onStopNestedScroll(androidx.coordinatorlayout.widget.CoordinatorLayout, android.view.View, android.view.View, int):void");
    }

    @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.Behavior
    public boolean onTouchEvent(CoordinatorLayout coordinatorLayout, View view, MotionEvent motionEvent) {
        if (!view.isShown()) {
            return false;
        }
        int actionMasked = motionEvent.getActionMasked();
        if (this.f1652M == 1 && actionMasked == 0) {
            return true;
        }
        if (n0()) {
            this.f1654O.processTouchEvent(motionEvent);
        }
        if (actionMasked == 0) {
            T();
        }
        if (this.f1667a0 == null) {
            this.f1667a0 = VelocityTracker.obtain();
        }
        this.f1667a0.addMovement(motionEvent);
        if (n0() && actionMasked == 2 && !this.f1655P && Math.abs(this.f1673d0 - motionEvent.getY()) > this.f1654O.getTouchSlop()) {
            this.f1654O.captureChildView(view, motionEvent.getPointerId(motionEvent.getActionIndex()));
        }
        return !this.f1655P;
    }

    public boolean p0() {
        return false;
    }

    public boolean q0() {
        return true;
    }

    public final void r0(View view, int i2, boolean z2) {
        int iJ = J(i2);
        ViewDragHelper viewDragHelper = this.f1654O;
        if (viewDragHelper == null || (!z2 ? viewDragHelper.smoothSlideViewTo(view, view.getLeft(), iJ) : viewDragHelper.settleCapturedViewAt(view.getLeft(), iJ))) {
            k0(i2);
            return;
        }
        k0(2);
        u0(i2, true);
        this.f1641B.c(i2);
    }

    public final void s0() {
        WeakReference weakReference = this.f1662W;
        if (weakReference != null) {
            t0((View) weakReference.get(), 0);
        }
        WeakReference weakReference2 = this.f1663X;
        if (weakReference2 != null) {
            t0((View) weakReference2.get(), 1);
        }
    }

    public final int t(View view, int i2, int i3) {
        return ViewCompat.addAccessibilityAction(view, view.getResources().getString(i2), C(i3));
    }

    public final void t0(View view, int i2) {
        if (view == null) {
            return;
        }
        B(view, i2);
        if (!this.f1668b && this.f1652M != 6) {
            this.f1679g0.put(i2, t(view, h.f6655a, 6));
        }
        if (this.f1649J && P() && this.f1652M != 5) {
            S(view, AccessibilityNodeInfoCompat.AccessibilityActionCompat.ACTION_DISMISS, 5);
        }
        int i3 = this.f1652M;
        if (i3 == 3) {
            S(view, AccessibilityNodeInfoCompat.AccessibilityActionCompat.ACTION_COLLAPSE, this.f1668b ? 4 : 6);
            return;
        }
        if (i3 == 4) {
            S(view, AccessibilityNodeInfoCompat.AccessibilityActionCompat.ACTION_EXPAND, this.f1668b ? 3 : 6);
        } else {
            if (i3 != 6) {
                return;
            }
            S(view, AccessibilityNodeInfoCompat.AccessibilityActionCompat.ACTION_COLLAPSE, 4);
            S(view, AccessibilityNodeInfoCompat.AccessibilityActionCompat.ACTION_EXPAND, 3);
        }
    }

    public final void u() {
        int iY = y();
        if (this.f1668b) {
            this.f1647H = Math.max(this.f1661V - iY, this.f1644E);
        } else {
            this.f1647H = this.f1661V - iY;
        }
    }

    public final void u0(int i2, boolean z2) {
        boolean zM;
        ValueAnimator valueAnimator;
        if (i2 == 2 || this.f1640A == (zM = M()) || this.f1683j == null) {
            return;
        }
        this.f1640A = zM;
        if (!z2 || (valueAnimator = this.f1642C) == null) {
            ValueAnimator valueAnimator2 = this.f1642C;
            if (valueAnimator2 != null && valueAnimator2.isRunning()) {
                this.f1642C.cancel();
            }
            this.f1683j.U(this.f1640A ? x() : 1.0f);
            return;
        }
        if (valueAnimator.isRunning()) {
            this.f1642C.reverse();
        } else {
            this.f1642C.setFloatValues(this.f1683j.w(), zM ? x() : 1.0f);
            this.f1642C.start();
        }
    }

    public final float v(float f2, RoundedCorner roundedCorner) {
        if (roundedCorner != null) {
            float radius = roundedCorner.getRadius();
            if (radius > 0.0f && f2 > 0.0f) {
                return radius / f2;
            }
        }
        return 0.0f;
    }

    public final void v0(boolean z2) {
        Map map;
        WeakReference weakReference = this.f1662W;
        if (weakReference == null) {
            return;
        }
        ViewParent parent = ((View) weakReference.get()).getParent();
        if (parent instanceof CoordinatorLayout) {
            CoordinatorLayout coordinatorLayout = (CoordinatorLayout) parent;
            int childCount = coordinatorLayout.getChildCount();
            if (z2) {
                if (this.f1677f0 != null) {
                    return;
                } else {
                    this.f1677f0 = new HashMap(childCount);
                }
            }
            for (int i2 = 0; i2 < childCount; i2++) {
                View childAt = coordinatorLayout.getChildAt(i2);
                if (childAt != this.f1662W.get()) {
                    if (z2) {
                        this.f1677f0.put(childAt, Integer.valueOf(childAt.getImportantForAccessibility()));
                        if (this.f1670c) {
                            ViewCompat.setImportantForAccessibility(childAt, 4);
                        }
                    } else if (this.f1670c && (map = this.f1677f0) != null && map.containsKey(childAt)) {
                        ViewCompat.setImportantForAccessibility(childAt, ((Integer) this.f1677f0.get(childAt)).intValue());
                    }
                }
            }
            if (!z2) {
                this.f1677f0 = null;
            } else if (this.f1670c) {
                ((View) this.f1662W.get()).sendAccessibilityEvent(8);
            }
        }
    }

    public final void w() {
        this.f1645F = (int) (this.f1661V * (1.0f - this.f1646G));
    }

    public final void w0(boolean z2) {
        View view;
        if (this.f1662W != null) {
            u();
            if (this.f1652M != 4 || (view = (View) this.f1662W.get()) == null) {
                return;
            }
            if (z2) {
                j0(4);
            } else {
                view.requestLayout();
            }
        }
    }

    public final float x() {
        WeakReference weakReference;
        WindowInsets rootWindowInsets;
        if (this.f1683j == null || (weakReference = this.f1662W) == null || weakReference.get() == null) {
            return 0.0f;
        }
        View view = (View) this.f1662W.get();
        if (!L() || (rootWindowInsets = view.getRootWindowInsets()) == null) {
            return 0.0f;
        }
        return Math.max(v(this.f1683j.C(), rootWindowInsets.getRoundedCorner(0)), v(this.f1683j.D(), rootWindowInsets.getRoundedCorner(1)));
    }

    public final int y() {
        int iMin;
        int i2;
        int i3;
        if (this.f1678g) {
            iMin = Math.min(Math.max(this.f1680h, this.f1661V - ((this.f1660U * 9) / 16)), this.f1659T);
            i2 = this.f1696w;
        } else {
            if (!this.f1688o && !this.f1689p && (i3 = this.f1687n) > 0) {
                return Math.max(this.f1676f, i3 + this.f1682i);
            }
            iMin = this.f1676f;
            i2 = this.f1696w;
        }
        return iMin + i2;
    }

    public final float z(int i2) {
        float f2;
        float fI;
        int i3 = this.f1647H;
        if (i2 > i3 || i3 == I()) {
            int i4 = this.f1647H;
            f2 = i4 - i2;
            fI = this.f1661V - i4;
        } else {
            int i5 = this.f1647H;
            f2 = i5 - i2;
            fI = i5 - I();
        }
        return f2 / fI;
    }

    public class g {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public int f1715a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public boolean f1716b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final Runnable f1717c;

        public class a implements Runnable {
            public a() {
            }

            @Override // java.lang.Runnable
            public void run() {
                g.this.f1716b = false;
                ViewDragHelper viewDragHelper = BottomSheetBehavior.this.f1654O;
                if (viewDragHelper != null && viewDragHelper.continueSettling(true)) {
                    g gVar = g.this;
                    gVar.c(gVar.f1715a);
                    return;
                }
                g gVar2 = g.this;
                BottomSheetBehavior bottomSheetBehavior = BottomSheetBehavior.this;
                if (bottomSheetBehavior.f1652M == 2) {
                    bottomSheetBehavior.k0(gVar2.f1715a);
                }
            }
        }

        public g() {
            this.f1717c = new a();
        }

        public void c(int i2) {
            WeakReference weakReference = BottomSheetBehavior.this.f1662W;
            if (weakReference == null || weakReference.get() == null) {
                return;
            }
            this.f1715a = i2;
            if (this.f1716b) {
                return;
            }
            ViewCompat.postOnAnimation((View) BottomSheetBehavior.this.f1662W.get(), this.f1717c);
            this.f1716b = true;
        }

        public /* synthetic */ g(BottomSheetBehavior bottomSheetBehavior, a aVar) {
            this();
        }
    }

    public static class f extends AbsSavedState {
        public static final Parcelable.Creator<f> CREATOR = new a();

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final int f1710a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public int f1711b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public boolean f1712c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public boolean f1713d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public boolean f1714e;

        public class a implements Parcelable.ClassLoaderCreator {
            @Override // android.os.Parcelable.Creator
            /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
            public f createFromParcel(Parcel parcel) {
                return new f(parcel, (ClassLoader) null);
            }

            @Override // android.os.Parcelable.ClassLoaderCreator
            /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
            public f createFromParcel(Parcel parcel, ClassLoader classLoader) {
                return new f(parcel, classLoader);
            }

            @Override // android.os.Parcelable.Creator
            /* JADX INFO: renamed from: c, reason: merged with bridge method [inline-methods] */
            public f[] newArray(int i2) {
                return new f[i2];
            }
        }

        public f(Parcel parcel, ClassLoader classLoader) {
            super(parcel, classLoader);
            this.f1710a = parcel.readInt();
            this.f1711b = parcel.readInt();
            this.f1712c = parcel.readInt() == 1;
            this.f1713d = parcel.readInt() == 1;
            this.f1714e = parcel.readInt() == 1;
        }

        @Override // androidx.customview.view.AbsSavedState, android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i2) {
            super.writeToParcel(parcel, i2);
            parcel.writeInt(this.f1710a);
            parcel.writeInt(this.f1711b);
            parcel.writeInt(this.f1712c ? 1 : 0);
            parcel.writeInt(this.f1713d ? 1 : 0);
            parcel.writeInt(this.f1714e ? 1 : 0);
        }

        public f(Parcelable parcelable, BottomSheetBehavior bottomSheetBehavior) {
            super(parcelable);
            this.f1710a = bottomSheetBehavior.f1652M;
            this.f1711b = bottomSheetBehavior.f1676f;
            this.f1712c = bottomSheetBehavior.f1668b;
            this.f1713d = bottomSheetBehavior.f1649J;
            this.f1714e = bottomSheetBehavior.f1650K;
        }
    }

    public BottomSheetBehavior(@NonNull Context context, @Nullable AttributeSet attributeSet) {
        int i2;
        super(context, attributeSet);
        this.f1666a = 0;
        this.f1668b = true;
        this.f1670c = false;
        this.f1685l = -1;
        this.f1686m = -1;
        this.f1641B = new g(this, null);
        this.f1646G = 0.5f;
        this.f1648I = -1.0f;
        this.f1651L = true;
        this.f1652M = 4;
        this.f1653N = 4;
        this.f1658S = 0.1f;
        this.f1665Z = new ArrayList();
        this.f1673d0 = -1;
        this.f1679g0 = new SparseIntArray();
        this.f1681h0 = new d();
        this.f1682i = context.getResources().getDimensionPixelSize(t.c.f6551S);
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, j.f6819x);
        int i3 = j.f6696B;
        if (typedArrayObtainStyledAttributes.hasValue(i3)) {
            this.f1684k = L.c.a(context, typedArrayObtainStyledAttributes, i3);
        }
        if (typedArrayObtainStyledAttributes.hasValue(j.f6736T)) {
            this.f1699z = k.e(context, attributeSet, AbstractC0741a.f6502b, f1639i0).m();
        }
        D(context);
        E();
        this.f1648I = typedArrayObtainStyledAttributes.getDimension(j.f6693A, -1.0f);
        int i4 = j.f6822y;
        if (typedArrayObtainStyledAttributes.hasValue(i4)) {
            d0(typedArrayObtainStyledAttributes.getDimensionPixelSize(i4, -1));
        }
        int i5 = j.f6825z;
        if (typedArrayObtainStyledAttributes.hasValue(i5)) {
            c0(typedArrayObtainStyledAttributes.getDimensionPixelSize(i5, -1));
        }
        int i6 = j.f6712H;
        TypedValue typedValuePeekValue = typedArrayObtainStyledAttributes.peekValue(i6);
        if (typedValuePeekValue != null && (i2 = typedValuePeekValue.data) == -1) {
            e0(i2);
        } else {
            e0(typedArrayObtainStyledAttributes.getDimensionPixelSize(i6, -1));
        }
        b0(typedArrayObtainStyledAttributes.getBoolean(j.f6710G, false));
        Z(typedArrayObtainStyledAttributes.getBoolean(j.f6720L, false));
        Y(typedArrayObtainStyledAttributes.getBoolean(j.f6705E, true));
        i0(typedArrayObtainStyledAttributes.getBoolean(j.f6718K, false));
        W(typedArrayObtainStyledAttributes.getBoolean(j.f6699C, true));
        g0(typedArrayObtainStyledAttributes.getInt(j.f6714I, 0));
        a0(typedArrayObtainStyledAttributes.getFloat(j.f6708F, 0.5f));
        int i7 = j.f6702D;
        TypedValue typedValuePeekValue2 = typedArrayObtainStyledAttributes.peekValue(i7);
        if (typedValuePeekValue2 != null && typedValuePeekValue2.type == 16) {
            X(typedValuePeekValue2.data);
        } else {
            X(typedArrayObtainStyledAttributes.getDimensionPixelOffset(i7, 0));
        }
        h0(typedArrayObtainStyledAttributes.getInt(j.f6716J, 500));
        this.f1689p = typedArrayObtainStyledAttributes.getBoolean(j.f6728P, false);
        this.f1690q = typedArrayObtainStyledAttributes.getBoolean(j.f6730Q, false);
        this.f1691r = typedArrayObtainStyledAttributes.getBoolean(j.f6732R, false);
        this.f1692s = typedArrayObtainStyledAttributes.getBoolean(j.f6734S, true);
        this.f1693t = typedArrayObtainStyledAttributes.getBoolean(j.f6722M, false);
        this.f1694u = typedArrayObtainStyledAttributes.getBoolean(j.f6724N, false);
        this.f1695v = typedArrayObtainStyledAttributes.getBoolean(j.f6726O, false);
        this.f1698y = typedArrayObtainStyledAttributes.getBoolean(j.f6738U, true);
        typedArrayObtainStyledAttributes.recycle();
        this.f1672d = ViewConfiguration.get(context).getScaledMaximumFlingVelocity();
    }
}
