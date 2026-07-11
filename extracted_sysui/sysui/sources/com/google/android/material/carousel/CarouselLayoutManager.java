package com.google.android.material.carousel;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PointF;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.accessibility.AccessibilityEvent;
import androidx.core.graphics.ColorUtils;
import androidx.core.math.MathUtils;
import androidx.core.util.Preconditions;
import androidx.recyclerview.widget.LinearSmoothScroller;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.carousel.b;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import t.j;
import u.AbstractC0743a;
import z.e;
import z.f;
import z.g;

/* JADX INFO: loaded from: classes2.dex */
public class CarouselLayoutManager extends RecyclerView.LayoutManager implements z.b, RecyclerView.SmoothScroller.ScrollVectorProvider {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public int f1757a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public int f1758b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public int f1759c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public boolean f1760d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final c f1761e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public f f1762f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public com.google.android.material.carousel.c f1763g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public com.google.android.material.carousel.b f1764h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public int f1765i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public Map f1766j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public e f1767k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final View.OnLayoutChangeListener f1768l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public int f1769m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public int f1770n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public int f1771o;

    public class a extends LinearSmoothScroller {
        public a(Context context) {
            super(context);
        }

        @Override // androidx.recyclerview.widget.LinearSmoothScroller
        public int calculateDxToMakeVisible(View view, int i2) {
            if (CarouselLayoutManager.this.f1763g == null || !CarouselLayoutManager.this.c()) {
                return 0;
            }
            CarouselLayoutManager carouselLayoutManager = CarouselLayoutManager.this;
            return carouselLayoutManager.t(carouselLayoutManager.getPosition(view));
        }

        @Override // androidx.recyclerview.widget.LinearSmoothScroller
        public int calculateDyToMakeVisible(View view, int i2) {
            if (CarouselLayoutManager.this.f1763g == null || CarouselLayoutManager.this.c()) {
                return 0;
            }
            CarouselLayoutManager carouselLayoutManager = CarouselLayoutManager.this;
            return carouselLayoutManager.t(carouselLayoutManager.getPosition(view));
        }

        @Override // androidx.recyclerview.widget.RecyclerView.SmoothScroller
        public PointF computeScrollVectorForPosition(int i2) {
            return CarouselLayoutManager.this.computeScrollVectorForPosition(i2);
        }
    }

    public static final class b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final View f1773a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final float f1774b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final float f1775c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public final d f1776d;

        public b(View view, float f2, float f3, d dVar) {
            this.f1773a = view;
            this.f1774b = f2;
            this.f1775c = f3;
            this.f1776d = dVar;
        }
    }

    public static class c extends RecyclerView.ItemDecoration {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final Paint f1777a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public List f1778b;

        public c() {
            Paint paint = new Paint();
            this.f1777a = paint;
            this.f1778b = Collections.unmodifiableList(new ArrayList());
            paint.setStrokeWidth(5.0f);
            paint.setColor(-65281);
        }

        public void a(List list) {
            this.f1778b = Collections.unmodifiableList(list);
        }

        @Override // androidx.recyclerview.widget.RecyclerView.ItemDecoration
        public void onDrawOver(Canvas canvas, RecyclerView recyclerView, RecyclerView.State state) {
            super.onDrawOver(canvas, recyclerView, state);
            this.f1777a.setStrokeWidth(recyclerView.getResources().getDimension(t.c.f6568k));
            for (b.c cVar : this.f1778b) {
                this.f1777a.setColor(ColorUtils.blendARGB(-65281, -16776961, cVar.f1796c));
                if (((CarouselLayoutManager) recyclerView.getLayoutManager()).c()) {
                    canvas.drawLine(cVar.f1795b, ((CarouselLayoutManager) recyclerView.getLayoutManager()).H(), cVar.f1795b, ((CarouselLayoutManager) recyclerView.getLayoutManager()).C(), this.f1777a);
                } else {
                    canvas.drawLine(((CarouselLayoutManager) recyclerView.getLayoutManager()).E(), cVar.f1795b, ((CarouselLayoutManager) recyclerView.getLayoutManager()).F(), cVar.f1795b, this.f1777a);
                }
            }
        }
    }

    public static class d {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final b.c f1779a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final b.c f1780b;

        public d(b.c cVar, b.c cVar2) {
            Preconditions.checkArgument(cVar.f1794a <= cVar2.f1794a);
            this.f1779a = cVar;
            this.f1780b = cVar2;
        }
    }

    public CarouselLayoutManager() {
        this(new g());
    }

    public static d K(List list, float f2, boolean z2) {
        float f3 = Float.MAX_VALUE;
        int i2 = -1;
        int i3 = -1;
        int i4 = -1;
        int i5 = -1;
        float f4 = -3.4028235E38f;
        float f5 = Float.MAX_VALUE;
        float f6 = Float.MAX_VALUE;
        for (int i6 = 0; i6 < list.size(); i6++) {
            b.c cVar = (b.c) list.get(i6);
            float f7 = z2 ? cVar.f1795b : cVar.f1794a;
            float fAbs = Math.abs(f7 - f2);
            if (f7 <= f2 && fAbs <= f3) {
                i2 = i6;
                f3 = fAbs;
            }
            if (f7 > f2 && fAbs <= f5) {
                i4 = i6;
                f5 = fAbs;
            }
            if (f7 <= f6) {
                i3 = i6;
                f6 = f7;
            }
            if (f7 > f4) {
                i5 = i6;
                f4 = f7;
            }
        }
        if (i2 == -1) {
            i2 = i3;
        }
        if (i4 == -1) {
            i4 = i5;
        }
        return new d((b.c) list.get(i2), (b.c) list.get(i4));
    }

    private int convertFocusDirectionToLayoutDirection(int i2) {
        int orientation = getOrientation();
        if (i2 == 1) {
            return -1;
        }
        if (i2 == 2) {
            return 1;
        }
        if (i2 == 17) {
            if (orientation == 0) {
                return isLayoutRtl() ? 1 : -1;
            }
            return Integer.MIN_VALUE;
        }
        if (i2 == 33) {
            return orientation == 1 ? -1 : Integer.MIN_VALUE;
        }
        if (i2 == 66) {
            if (orientation == 0) {
                return isLayoutRtl() ? -1 : 1;
            }
            return Integer.MIN_VALUE;
        }
        if (i2 == 130) {
            return orientation == 1 ? 1 : Integer.MIN_VALUE;
        }
        Log.d("CarouselLayoutManager", "Unknown focus request:" + i2);
        return Integer.MIN_VALUE;
    }

    private int scrollBy(int i2, RecyclerView.Recycler recycler, RecyclerView.State state) {
        if (getChildCount() == 0 || i2 == 0) {
            return 0;
        }
        if (this.f1763g == null) {
            R(recycler);
        }
        int iU = u(i2, this.f1757a, this.f1758b, this.f1759c);
        this.f1757a += iU;
        Z(this.f1763g);
        float f2 = this.f1764h.f() / 2.0f;
        float fR = r(getPosition(getChildAt(0)));
        Rect rect = new Rect();
        float f3 = isLayoutRtl() ? this.f1764h.h().f1795b : this.f1764h.a().f1795b;
        float f4 = Float.MAX_VALUE;
        for (int i3 = 0; i3 < getChildCount(); i3++) {
            View childAt = getChildAt(i3);
            float fAbs = Math.abs(f3 - Q(childAt, fR, f2, rect));
            if (childAt != null && fAbs < f4) {
                this.f1770n = getPosition(childAt);
                f4 = fAbs;
            }
            fR = l(fR, this.f1764h.f());
        }
        w(recycler, state);
        return iU;
    }

    public static int u(int i2, int i3, int i4, int i5) {
        int i6 = i3 + i2;
        return i6 < i4 ? i4 - i3 : i6 > i5 ? i5 - i3 : i2;
    }

    public final float A(float f2, d dVar) {
        b.c cVar = dVar.f1779a;
        float f3 = cVar.f1797d;
        b.c cVar2 = dVar.f1780b;
        return AbstractC0743a.b(f3, cVar2.f1797d, cVar.f1795b, cVar2.f1795b, f2);
    }

    public int B(int i2, com.google.android.material.carousel.b bVar) {
        return I(i2, bVar) - this.f1757a;
    }

    public final int C() {
        return this.f1767k.e();
    }

    public final int D() {
        return this.f1767k.f();
    }

    public final int E() {
        return this.f1767k.g();
    }

    public final int F() {
        return this.f1767k.h();
    }

    public final int G() {
        return this.f1767k.i();
    }

    public final int H() {
        return this.f1767k.j();
    }

    public final int I(int i2, com.google.android.material.carousel.b bVar) {
        return (int) (isLayoutRtl() ? ((x() - bVar.h().f1794a) - (i2 * bVar.f())) - (bVar.f() / 2.0f) : ((i2 * bVar.f()) - bVar.a().f1794a) + (bVar.f() / 2.0f));
    }

    public final int J(int i2, com.google.android.material.carousel.b bVar) {
        int i3 = Integer.MAX_VALUE;
        for (b.c cVar : bVar.e()) {
            float f2 = (i2 * bVar.f()) + (bVar.f() / 2.0f);
            int iX = (isLayoutRtl() ? (int) ((x() - cVar.f1794a) - f2) : (int) (f2 - cVar.f1794a)) - this.f1757a;
            if (Math.abs(i3) > Math.abs(iX)) {
                i3 = iX;
            }
        }
        return i3;
    }

    public final boolean L(float f2, d dVar) {
        float fM = m(f2, A(f2, dVar) / 2.0f);
        if (isLayoutRtl()) {
            if (fM >= 0.0f) {
                return false;
            }
        } else if (fM <= x()) {
            return false;
        }
        return true;
    }

    public final boolean M(float f2, d dVar) {
        float fL = l(f2, A(f2, dVar) / 2.0f);
        if (isLayoutRtl()) {
            if (fL <= x()) {
                return false;
            }
        } else if (fL >= 0.0f) {
            return false;
        }
        return true;
    }

    public final /* synthetic */ void N(View view, int i2, int i3, int i4, int i5, int i6, int i7, int i8, int i9) {
        if (i2 == i6 && i3 == i7 && i4 == i8 && i5 == i9) {
            return;
        }
        view.post(new Runnable() { // from class: z.d
            @Override // java.lang.Runnable
            public final void run() {
                this.f7119a.S();
            }
        });
    }

    public final void O() {
        if (this.f1760d && Log.isLoggable("CarouselLayoutManager", 3)) {
            Log.d("CarouselLayoutManager", "internal representation of views on the screen");
            for (int i2 = 0; i2 < getChildCount(); i2++) {
                View childAt = getChildAt(i2);
                Log.d("CarouselLayoutManager", "item position " + getPosition(childAt) + ", center:" + y(childAt) + ", child index:" + i2);
            }
            Log.d("CarouselLayoutManager", "==============");
        }
    }

    public final b P(RecyclerView.Recycler recycler, float f2, int i2) {
        View viewForPosition = recycler.getViewForPosition(i2);
        measureChildWithMargins(viewForPosition, 0, 0);
        float fL = l(f2, this.f1764h.f() / 2.0f);
        d dVarK = K(this.f1764h.g(), fL, false);
        return new b(viewForPosition, fL, q(viewForPosition, fL, dVarK), dVarK);
    }

    public final float Q(View view, float f2, float f3, Rect rect) {
        float fL = l(f2, f3);
        d dVarK = K(this.f1764h.g(), fL, false);
        float fQ = q(view, fL, dVarK);
        super.getDecoratedBoundsWithMargins(view, rect);
        Y(view, fL, dVarK);
        this.f1767k.l(view, rect, f3, fQ);
        return fQ;
    }

    public final void R(RecyclerView.Recycler recycler) {
        View viewForPosition = recycler.getViewForPosition(0);
        measureChildWithMargins(viewForPosition, 0, 0);
        com.google.android.material.carousel.b bVarC = this.f1762f.c(this, viewForPosition);
        if (isLayoutRtl()) {
            bVarC = com.google.android.material.carousel.b.m(bVarC, x());
        }
        this.f1763g = com.google.android.material.carousel.c.f(this, bVarC);
    }

    public final void S() {
        this.f1763g = null;
        requestLayout();
    }

    public final void T(RecyclerView.Recycler recycler) {
        while (getChildCount() > 0) {
            View childAt = getChildAt(0);
            float fY = y(childAt);
            if (!M(fY, K(this.f1764h.g(), fY, true))) {
                break;
            } else {
                removeAndRecycleView(childAt, recycler);
            }
        }
        while (getChildCount() - 1 >= 0) {
            View childAt2 = getChildAt(getChildCount() - 1);
            float fY2 = y(childAt2);
            if (!L(fY2, K(this.f1764h.g(), fY2, true))) {
                return;
            } else {
                removeAndRecycleView(childAt2, recycler);
            }
        }
    }

    public final void U(RecyclerView recyclerView, int i2) {
        if (c()) {
            recyclerView.scrollBy(i2, 0);
        } else {
            recyclerView.scrollBy(0, i2);
        }
    }

    public void V(int i2) {
        this.f1771o = i2;
        S();
    }

    public final void W(Context context, AttributeSet attributeSet) {
        if (attributeSet != null) {
            TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, j.f6746Y);
            V(typedArrayObtainStyledAttributes.getInt(j.f6748Z, 0));
            setOrientation(typedArrayObtainStyledAttributes.getInt(j.c4, 0));
            typedArrayObtainStyledAttributes.recycle();
        }
    }

    public void X(f fVar) {
        this.f1762f = fVar;
        S();
    }

    public final void Y(View view, float f2, d dVar) {
    }

    public final void Z(com.google.android.material.carousel.c cVar) {
        int i2 = this.f1759c;
        int i3 = this.f1758b;
        if (i2 <= i3) {
            this.f1764h = isLayoutRtl() ? cVar.h() : cVar.l();
        } else {
            this.f1764h = cVar.j(this.f1757a, i3, i2);
        }
        this.f1761e.a(this.f1764h.g());
    }

    @Override // z.b
    public int a() {
        return getWidth();
    }

    public final void a0() {
        int itemCount = getItemCount();
        int i2 = this.f1769m;
        if (itemCount == i2 || this.f1763g == null) {
            return;
        }
        if (this.f1762f.d(this, i2)) {
            S();
        }
        this.f1769m = itemCount;
    }

    @Override // z.b
    public int b() {
        return this.f1771o;
    }

    public final void b0() {
        if (!this.f1760d || getChildCount() < 1) {
            return;
        }
        int i2 = 0;
        while (i2 < getChildCount() - 1) {
            int position = getPosition(getChildAt(i2));
            int i3 = i2 + 1;
            int position2 = getPosition(getChildAt(i3));
            if (position > position2) {
                O();
                throw new IllegalStateException("Detected invalid child order. Child at index [" + i2 + "] had adapter position [" + position + "] and child at index [" + i3 + "] had adapter position [" + position2 + "].");
            }
            i2 = i3;
        }
    }

    @Override // z.b
    public boolean c() {
        return this.f1767k.f7120a == 0;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public boolean canScrollHorizontally() {
        return c();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public boolean canScrollVertically() {
        return !c();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public int computeHorizontalScrollExtent(RecyclerView.State state) {
        if (getChildCount() == 0 || this.f1763g == null || getItemCount() <= 1) {
            return 0;
        }
        return (int) (getWidth() * (this.f1763g.g().f() / computeHorizontalScrollRange(state)));
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public int computeHorizontalScrollOffset(RecyclerView.State state) {
        return this.f1757a;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public int computeHorizontalScrollRange(RecyclerView.State state) {
        return this.f1759c - this.f1758b;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.SmoothScroller.ScrollVectorProvider
    public PointF computeScrollVectorForPosition(int i2) {
        if (this.f1763g == null) {
            return null;
        }
        int iB = B(i2, z(i2));
        return c() ? new PointF(iB, 0.0f) : new PointF(0.0f, iB);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public int computeVerticalScrollExtent(RecyclerView.State state) {
        if (getChildCount() == 0 || this.f1763g == null || getItemCount() <= 1) {
            return 0;
        }
        return (int) (getHeight() * (this.f1763g.g().f() / computeVerticalScrollRange(state)));
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public int computeVerticalScrollOffset(RecyclerView.State state) {
        return this.f1757a;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public int computeVerticalScrollRange(RecyclerView.State state) {
        return this.f1759c - this.f1758b;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public RecyclerView.LayoutParams generateDefaultLayoutParams() {
        return new RecyclerView.LayoutParams(-2, -2);
    }

    public final View getChildClosestToEnd() {
        return getChildAt(isLayoutRtl() ? 0 : getChildCount() - 1);
    }

    public final View getChildClosestToStart() {
        return getChildAt(isLayoutRtl() ? getChildCount() - 1 : 0);
    }

    @Override // z.b
    public int getContainerHeight() {
        return getHeight();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public void getDecoratedBoundsWithMargins(View view, Rect rect) {
        super.getDecoratedBoundsWithMargins(view, rect);
        float fCenterY = rect.centerY();
        if (c()) {
            fCenterY = rect.centerX();
        }
        float fA = A(fCenterY, K(this.f1764h.g(), fCenterY, true));
        float fWidth = c() ? (rect.width() - fA) / 2.0f : 0.0f;
        float fHeight = c() ? 0.0f : (rect.height() - fA) / 2.0f;
        rect.set((int) (rect.left + fWidth), (int) (rect.top + fHeight), (int) (rect.right - fWidth), (int) (rect.bottom - fHeight));
    }

    public int getOrientation() {
        return this.f1767k.f7120a;
    }

    public boolean isLayoutRtl() {
        return c() && getLayoutDirection() == 1;
    }

    public final void k(View view, int i2, b bVar) {
        float f2 = this.f1764h.f() / 2.0f;
        addView(view, i2);
        float f3 = bVar.f1775c;
        this.f1767k.k(view, (int) (f3 - f2), (int) (f3 + f2));
        Y(view, bVar.f1774b, bVar.f1776d);
    }

    public final float l(float f2, float f3) {
        return isLayoutRtl() ? f2 - f3 : f2 + f3;
    }

    public final float m(float f2, float f3) {
        return isLayoutRtl() ? f2 + f3 : f2 - f3;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public void measureChildWithMargins(View view, int i2, int i3) {
        throw new IllegalStateException("All children of a RecyclerView using CarouselLayoutManager must use MaskableFrameLayout as their root ViewGroup.");
    }

    public final void n(RecyclerView.Recycler recycler, int i2, int i3) {
        if (i2 < 0 || i2 >= getItemCount()) {
            return;
        }
        b bVarP = P(recycler, r(i2), i2);
        k(bVarP.f1773a, i3, bVarP);
    }

    public final void o(RecyclerView.Recycler recycler, RecyclerView.State state, int i2) {
        float fR = r(i2);
        while (i2 < state.getItemCount()) {
            b bVarP = P(recycler, fR, i2);
            if (L(bVarP.f1775c, bVarP.f1776d)) {
                return;
            }
            fR = l(fR, this.f1764h.f());
            if (!M(bVarP.f1775c, bVarP.f1776d)) {
                k(bVarP.f1773a, -1, bVarP);
            }
            i2++;
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public void onAttachedToWindow(RecyclerView recyclerView) {
        super.onAttachedToWindow(recyclerView);
        S();
        recyclerView.addOnLayoutChangeListener(this.f1768l);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public void onDetachedFromWindow(RecyclerView recyclerView, RecyclerView.Recycler recycler) {
        super.onDetachedFromWindow(recyclerView, recycler);
        recyclerView.removeOnLayoutChangeListener(this.f1768l);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public View onFocusSearchFailed(View view, int i2, RecyclerView.Recycler recycler, RecyclerView.State state) {
        int iConvertFocusDirectionToLayoutDirection;
        if (getChildCount() == 0 || (iConvertFocusDirectionToLayoutDirection = convertFocusDirectionToLayoutDirection(i2)) == Integer.MIN_VALUE) {
            return null;
        }
        if (iConvertFocusDirectionToLayoutDirection == -1) {
            if (getPosition(view) == 0) {
                return null;
            }
            n(recycler, getPosition(getChildAt(0)) - 1, 0);
            return getChildClosestToStart();
        }
        if (getPosition(view) == getItemCount() - 1) {
            return null;
        }
        n(recycler, getPosition(getChildAt(getChildCount() - 1)) + 1, -1);
        return getChildClosestToEnd();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public void onInitializeAccessibilityEvent(AccessibilityEvent accessibilityEvent) {
        super.onInitializeAccessibilityEvent(accessibilityEvent);
        if (getChildCount() > 0) {
            accessibilityEvent.setFromIndex(getPosition(getChildAt(0)));
            accessibilityEvent.setToIndex(getPosition(getChildAt(getChildCount() - 1)));
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public void onItemsAdded(RecyclerView recyclerView, int i2, int i3) {
        super.onItemsAdded(recyclerView, i2, i3);
        a0();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public void onItemsRemoved(RecyclerView recyclerView, int i2, int i3) {
        super.onItemsRemoved(recyclerView, i2, i3);
        a0();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public void onLayoutChildren(RecyclerView.Recycler recycler, RecyclerView.State state) {
        if (state.getItemCount() <= 0 || x() <= 0.0f) {
            removeAndRecycleAllViews(recycler);
            this.f1765i = 0;
            return;
        }
        boolean zIsLayoutRtl = isLayoutRtl();
        boolean z2 = this.f1763g == null;
        if (z2) {
            R(recycler);
        }
        int iV = v(this.f1763g);
        int iS = s(state, this.f1763g);
        this.f1758b = zIsLayoutRtl ? iS : iV;
        if (zIsLayoutRtl) {
            iS = iV;
        }
        this.f1759c = iS;
        if (z2) {
            this.f1757a = iV;
            this.f1766j = this.f1763g.i(getItemCount(), this.f1758b, this.f1759c, isLayoutRtl());
            int i2 = this.f1770n;
            if (i2 != -1) {
                this.f1757a = I(i2, z(i2));
            }
        }
        int i3 = this.f1757a;
        this.f1757a = i3 + u(0, i3, this.f1758b, this.f1759c);
        this.f1765i = MathUtils.clamp(this.f1765i, 0, state.getItemCount());
        Z(this.f1763g);
        detachAndScrapAttachedViews(recycler);
        w(recycler, state);
        this.f1769m = getItemCount();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public void onLayoutCompleted(RecyclerView.State state) {
        super.onLayoutCompleted(state);
        if (getChildCount() == 0) {
            this.f1765i = 0;
        } else {
            this.f1765i = getPosition(getChildAt(0));
        }
        b0();
    }

    public final void p(RecyclerView.Recycler recycler, int i2) {
        float fR = r(i2);
        while (i2 >= 0) {
            b bVarP = P(recycler, fR, i2);
            if (M(bVarP.f1775c, bVarP.f1776d)) {
                return;
            }
            fR = m(fR, this.f1764h.f());
            if (!L(bVarP.f1775c, bVarP.f1776d)) {
                k(bVarP.f1773a, 0, bVarP);
            }
            i2--;
        }
    }

    public final float q(View view, float f2, d dVar) {
        b.c cVar = dVar.f1779a;
        float f3 = cVar.f1795b;
        b.c cVar2 = dVar.f1780b;
        float fB = AbstractC0743a.b(f3, cVar2.f1795b, cVar.f1794a, cVar2.f1794a, f2);
        if (dVar.f1780b != this.f1764h.c() && dVar.f1779a != this.f1764h.j()) {
            return fB;
        }
        float fD = this.f1767k.d((RecyclerView.LayoutParams) view.getLayoutParams()) / this.f1764h.f();
        b.c cVar3 = dVar.f1780b;
        return fB + ((f2 - cVar3.f1794a) * ((1.0f - cVar3.f1796c) + fD));
    }

    public final float r(int i2) {
        return l(G() - this.f1757a, this.f1764h.f() * i2);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public boolean requestChildRectangleOnScreen(RecyclerView recyclerView, View view, Rect rect, boolean z2, boolean z3) {
        int iJ;
        if (this.f1763g == null || (iJ = J(getPosition(view), z(getPosition(view)))) == 0) {
            return false;
        }
        U(recyclerView, J(getPosition(view), this.f1763g.j(this.f1757a + u(iJ, this.f1757a, this.f1758b, this.f1759c), this.f1758b, this.f1759c)));
        return true;
    }

    public final int s(RecyclerView.State state, com.google.android.material.carousel.c cVar) {
        boolean zIsLayoutRtl = isLayoutRtl();
        com.google.android.material.carousel.b bVarL = zIsLayoutRtl ? cVar.l() : cVar.h();
        b.c cVarA = zIsLayoutRtl ? bVarL.a() : bVarL.h();
        int itemCount = (int) ((((((state.getItemCount() - 1) * bVarL.f()) + getPaddingEnd()) * (zIsLayoutRtl ? -1.0f : 1.0f)) - (cVarA.f1794a - G())) + (D() - cVarA.f1794a));
        return zIsLayoutRtl ? Math.min(0, itemCount) : Math.max(0, itemCount);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public int scrollHorizontallyBy(int i2, RecyclerView.Recycler recycler, RecyclerView.State state) {
        if (canScrollHorizontally()) {
            return scrollBy(i2, recycler, state);
        }
        return 0;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public void scrollToPosition(int i2) {
        this.f1770n = i2;
        if (this.f1763g == null) {
            return;
        }
        this.f1757a = I(i2, z(i2));
        this.f1765i = MathUtils.clamp(i2, 0, Math.max(0, getItemCount() - 1));
        Z(this.f1763g);
        requestLayout();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public int scrollVerticallyBy(int i2, RecyclerView.Recycler recycler, RecyclerView.State state) {
        if (canScrollVertically()) {
            return scrollBy(i2, recycler, state);
        }
        return 0;
    }

    public void setOrientation(int i2) {
        if (i2 != 0 && i2 != 1) {
            throw new IllegalArgumentException("invalid orientation:" + i2);
        }
        assertNotInLayoutOrScroll(null);
        e eVar = this.f1767k;
        if (eVar == null || i2 != eVar.f7120a) {
            this.f1767k = e.b(this, i2);
            S();
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public void smoothScrollToPosition(RecyclerView recyclerView, RecyclerView.State state, int i2) {
        a aVar = new a(recyclerView.getContext());
        aVar.setTargetPosition(i2);
        startSmoothScroll(aVar);
    }

    public int t(int i2) {
        return (int) (this.f1757a - I(i2, z(i2)));
    }

    public final int v(com.google.android.material.carousel.c cVar) {
        boolean zIsLayoutRtl = isLayoutRtl();
        com.google.android.material.carousel.b bVarH = zIsLayoutRtl ? cVar.h() : cVar.l();
        return (int) (((getPaddingStart() * (zIsLayoutRtl ? 1 : -1)) + G()) - m((zIsLayoutRtl ? bVarH.h() : bVarH.a()).f1794a, bVarH.f() / 2.0f));
    }

    public final void w(RecyclerView.Recycler recycler, RecyclerView.State state) {
        T(recycler);
        if (getChildCount() == 0) {
            p(recycler, this.f1765i - 1);
            o(recycler, state, this.f1765i);
        } else {
            int position = getPosition(getChildAt(0));
            int position2 = getPosition(getChildAt(getChildCount() - 1));
            p(recycler, position - 1);
            o(recycler, state, position2 + 1);
        }
        b0();
    }

    public final int x() {
        return c() ? a() : getContainerHeight();
    }

    public final float y(View view) {
        super.getDecoratedBoundsWithMargins(view, new Rect());
        return c() ? r0.centerX() : r0.centerY();
    }

    public final com.google.android.material.carousel.b z(int i2) {
        com.google.android.material.carousel.b bVar;
        Map map = this.f1766j;
        return (map == null || (bVar = (com.google.android.material.carousel.b) map.get(Integer.valueOf(MathUtils.clamp(i2, 0, Math.max(0, getItemCount() + (-1)))))) == null) ? this.f1763g.g() : bVar;
    }

    public CarouselLayoutManager(f fVar) {
        this(fVar, 0);
    }

    public CarouselLayoutManager(f fVar, int i2) {
        this.f1760d = false;
        this.f1761e = new c();
        this.f1765i = 0;
        this.f1768l = new View.OnLayoutChangeListener() { // from class: z.c
            @Override // android.view.View.OnLayoutChangeListener
            public final void onLayoutChange(View view, int i3, int i4, int i5, int i6, int i7, int i8, int i9, int i10) {
                this.f7118a.N(view, i3, i4, i5, i6, i7, i8, i9, i10);
            }
        };
        this.f1770n = -1;
        this.f1771o = 0;
        X(fVar);
        setOrientation(i2);
    }

    @SuppressLint({"UnknownNullness"})
    public CarouselLayoutManager(Context context, AttributeSet attributeSet, int i2, int i3) {
        this.f1760d = false;
        this.f1761e = new c();
        this.f1765i = 0;
        this.f1768l = new View.OnLayoutChangeListener() { // from class: z.c
            @Override // android.view.View.OnLayoutChangeListener
            public final void onLayoutChange(View view, int i32, int i4, int i5, int i6, int i7, int i8, int i9, int i10) {
                this.f7118a.N(view, i32, i4, i5, i6, i7, i8, i9, i10);
            }
        };
        this.f1770n = -1;
        this.f1771o = 0;
        X(new g());
        W(context, attributeSet);
    }
}
