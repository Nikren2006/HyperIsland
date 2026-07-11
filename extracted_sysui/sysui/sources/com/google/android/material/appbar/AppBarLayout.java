package com.google.android.material.appbar;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Rect;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.math.MathUtils;
import androidx.core.view.ViewCompat;
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;
import androidx.customview.view.AbsSavedState;
import java.lang.ref.WeakReference;
import java.util.List;
import t.j;
import v.AbstractC0749a;
import v.AbstractC0750b;

/* JADX INFO: loaded from: classes2.dex */
public abstract class AppBarLayout extends LinearLayout implements CoordinatorLayout.AttachedBehavior {

    public static class BaseBehavior<T extends AppBarLayout> extends AbstractC0749a {

        /* JADX INFO: renamed from: k, reason: collision with root package name */
        public int f1585k;

        /* JADX INFO: renamed from: l, reason: collision with root package name */
        public int f1586l;

        /* JADX INFO: renamed from: m, reason: collision with root package name */
        public a f1587m;

        /* JADX INFO: renamed from: n, reason: collision with root package name */
        public WeakReference f1588n;

        public static class a extends AbsSavedState {
            public static final Parcelable.Creator<a> CREATOR = new C0053a();

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            public boolean f1589a;

            /* JADX INFO: renamed from: b, reason: collision with root package name */
            public boolean f1590b;

            /* JADX INFO: renamed from: c, reason: collision with root package name */
            public int f1591c;

            /* JADX INFO: renamed from: d, reason: collision with root package name */
            public float f1592d;

            /* JADX INFO: renamed from: e, reason: collision with root package name */
            public boolean f1593e;

            /* JADX INFO: renamed from: com.google.android.material.appbar.AppBarLayout$BaseBehavior$a$a, reason: collision with other inner class name */
            public class C0053a implements Parcelable.ClassLoaderCreator {
                @Override // android.os.Parcelable.Creator
                /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
                public a createFromParcel(Parcel parcel) {
                    return new a(parcel, null);
                }

                @Override // android.os.Parcelable.ClassLoaderCreator
                /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
                public a createFromParcel(Parcel parcel, ClassLoader classLoader) {
                    return new a(parcel, classLoader);
                }

                @Override // android.os.Parcelable.Creator
                /* JADX INFO: renamed from: c, reason: merged with bridge method [inline-methods] */
                public a[] newArray(int i2) {
                    return new a[i2];
                }
            }

            public a(Parcel parcel, ClassLoader classLoader) {
                super(parcel, classLoader);
                this.f1589a = parcel.readByte() != 0;
                this.f1590b = parcel.readByte() != 0;
                this.f1591c = parcel.readInt();
                this.f1592d = parcel.readFloat();
                this.f1593e = parcel.readByte() != 0;
            }

            @Override // androidx.customview.view.AbsSavedState, android.os.Parcelable
            public void writeToParcel(Parcel parcel, int i2) {
                super.writeToParcel(parcel, i2);
                parcel.writeByte(this.f1589a ? (byte) 1 : (byte) 0);
                parcel.writeByte(this.f1590b ? (byte) 1 : (byte) 0);
                parcel.writeInt(this.f1591c);
                parcel.writeFloat(this.f1592d);
                parcel.writeByte(this.f1593e ? (byte) 1 : (byte) 0);
            }
        }

        public BaseBehavior() {
        }

        public a A(Parcelable parcelable, AppBarLayout appBarLayout) {
            a();
            throw null;
        }

        public int B(CoordinatorLayout coordinatorLayout, AppBarLayout appBarLayout, int i2, int i3, int i4) {
            int iH = h();
            if (i3 == 0 || iH < i3 || iH > i4) {
                this.f1585k = 0;
            } else if (iH != MathUtils.clamp(i2, i3, i4)) {
                throw null;
            }
            D(coordinatorLayout, appBarLayout);
            return 0;
        }

        public final void C(CoordinatorLayout coordinatorLayout, AppBarLayout appBarLayout) {
            throw null;
        }

        public final void D(CoordinatorLayout coordinatorLayout, AppBarLayout appBarLayout) {
            ViewCompat.removeAccessibilityAction(coordinatorLayout, AccessibilityNodeInfoCompat.AccessibilityActionCompat.ACTION_SCROLL_FORWARD.getId());
            ViewCompat.removeAccessibilityAction(coordinatorLayout, AccessibilityNodeInfoCompat.AccessibilityActionCompat.ACTION_SCROLL_BACKWARD.getId());
            throw null;
        }

        @Override // v.AbstractC0749a
        public /* bridge */ /* synthetic */ boolean c(View view) {
            android.support.v4.media.a.a(view);
            return n(null);
        }

        @Override // v.AbstractC0749a
        public /* bridge */ /* synthetic */ int f(View view) {
            android.support.v4.media.a.a(view);
            return o(null);
        }

        @Override // v.AbstractC0749a
        public /* bridge */ /* synthetic */ int g(View view) {
            android.support.v4.media.a.a(view);
            return p(null);
        }

        @Override // v.AbstractC0749a
        public int h() {
            return a() + this.f1585k;
        }

        @Override // v.AbstractC0749a
        public /* bridge */ /* synthetic */ void i(CoordinatorLayout coordinatorLayout, View view) {
            android.support.v4.media.a.a(view);
            q(coordinatorLayout, null);
        }

        @Override // v.AbstractC0749a
        public /* bridge */ /* synthetic */ int l(CoordinatorLayout coordinatorLayout, View view, int i2, int i3, int i4) {
            android.support.v4.media.a.a(view);
            return B(coordinatorLayout, null, i2, i3, i4);
        }

        public boolean n(AppBarLayout appBarLayout) {
            WeakReference weakReference = this.f1588n;
            if (weakReference == null) {
                return true;
            }
            View view = (View) weakReference.get();
            return (view == null || !view.isShown() || view.canScrollVertically(-1)) ? false : true;
        }

        public int o(AppBarLayout appBarLayout) {
            throw null;
        }

        @Override // v.AbstractC0751c, androidx.coordinatorlayout.widget.CoordinatorLayout.Behavior
        public /* bridge */ /* synthetic */ boolean onLayoutChild(CoordinatorLayout coordinatorLayout, View view, int i2) {
            android.support.v4.media.a.a(view);
            return r(coordinatorLayout, null, i2);
        }

        @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.Behavior
        public /* bridge */ /* synthetic */ boolean onMeasureChild(CoordinatorLayout coordinatorLayout, View view, int i2, int i3, int i4, int i5) {
            android.support.v4.media.a.a(view);
            return s(coordinatorLayout, null, i2, i3, i4, i5);
        }

        @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.Behavior
        public /* bridge */ /* synthetic */ void onNestedPreScroll(CoordinatorLayout coordinatorLayout, View view, View view2, int i2, int i3, int[] iArr, int i4) {
            android.support.v4.media.a.a(view);
            t(coordinatorLayout, null, view2, i2, i3, iArr, i4);
        }

        @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.Behavior
        public /* bridge */ /* synthetic */ void onNestedScroll(CoordinatorLayout coordinatorLayout, View view, View view2, int i2, int i3, int i4, int i5, int i6, int[] iArr) {
            android.support.v4.media.a.a(view);
            u(coordinatorLayout, null, view2, i2, i3, i4, i5, i6, iArr);
        }

        @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.Behavior
        public /* bridge */ /* synthetic */ void onRestoreInstanceState(CoordinatorLayout coordinatorLayout, View view, Parcelable parcelable) {
            android.support.v4.media.a.a(view);
            v(coordinatorLayout, null, parcelable);
        }

        @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.Behavior
        public /* bridge */ /* synthetic */ Parcelable onSaveInstanceState(CoordinatorLayout coordinatorLayout, View view) {
            android.support.v4.media.a.a(view);
            return w(coordinatorLayout, null);
        }

        @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.Behavior
        public /* bridge */ /* synthetic */ boolean onStartNestedScroll(CoordinatorLayout coordinatorLayout, View view, View view2, View view3, int i2, int i3) {
            android.support.v4.media.a.a(view);
            return x(coordinatorLayout, null, view2, view3, i2, i3);
        }

        @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.Behavior
        public /* bridge */ /* synthetic */ void onStopNestedScroll(CoordinatorLayout coordinatorLayout, View view, View view2, int i2) {
            android.support.v4.media.a.a(view);
            y(coordinatorLayout, null, view2, i2);
        }

        public int p(AppBarLayout appBarLayout) {
            throw null;
        }

        public void q(CoordinatorLayout coordinatorLayout, AppBarLayout appBarLayout) {
            C(coordinatorLayout, appBarLayout);
            throw null;
        }

        public boolean r(CoordinatorLayout coordinatorLayout, AppBarLayout appBarLayout, int i2) {
            super.onLayoutChild(coordinatorLayout, appBarLayout, i2);
            throw null;
        }

        public boolean s(CoordinatorLayout coordinatorLayout, AppBarLayout appBarLayout, int i2, int i3, int i4, int i5) {
            throw null;
        }

        public void t(CoordinatorLayout coordinatorLayout, AppBarLayout appBarLayout, View view, int i2, int i3, int[] iArr, int i4) {
            if (i3 != 0 && i3 >= 0) {
                throw null;
            }
            throw null;
        }

        public void u(CoordinatorLayout coordinatorLayout, AppBarLayout appBarLayout, View view, int i2, int i3, int i4, int i5, int i6, int[] iArr) {
            if (i5 < 0) {
                throw null;
            }
            if (i5 == 0) {
                D(coordinatorLayout, appBarLayout);
            }
        }

        public void v(CoordinatorLayout coordinatorLayout, AppBarLayout appBarLayout, Parcelable parcelable) {
            if (parcelable instanceof a) {
                z((a) parcelable, true);
                super.onRestoreInstanceState(coordinatorLayout, appBarLayout, this.f1587m.getSuperState());
            } else {
                super.onRestoreInstanceState(coordinatorLayout, appBarLayout, parcelable);
                this.f1587m = null;
            }
        }

        public Parcelable w(CoordinatorLayout coordinatorLayout, AppBarLayout appBarLayout) {
            Parcelable parcelableOnSaveInstanceState = super.onSaveInstanceState(coordinatorLayout, appBarLayout);
            a aVarA = A(parcelableOnSaveInstanceState, appBarLayout);
            return aVarA == null ? parcelableOnSaveInstanceState : aVarA;
        }

        public boolean x(CoordinatorLayout coordinatorLayout, AppBarLayout appBarLayout, View view, View view2, int i2, int i3) {
            if ((i2 & 2) != 0) {
                throw null;
            }
            this.f1588n = null;
            this.f1586l = i3;
            return false;
        }

        public void y(CoordinatorLayout coordinatorLayout, AppBarLayout appBarLayout, View view, int i2) {
            if (this.f1586l == 0 || i2 == 1) {
                C(coordinatorLayout, appBarLayout);
                throw null;
            }
            this.f1588n = new WeakReference(view);
        }

        public void z(a aVar, boolean z2) {
            if (this.f1587m == null || z2) {
                this.f1587m = aVar;
            }
        }

        public BaseBehavior(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
        }
    }

    public static class Behavior extends BaseBehavior<AppBarLayout> {
        public Behavior() {
        }

        @Override // v.AbstractC0751c
        public /* bridge */ /* synthetic */ int a() {
            return super.a();
        }

        @Override // v.AbstractC0749a, androidx.coordinatorlayout.widget.CoordinatorLayout.Behavior
        public /* bridge */ /* synthetic */ boolean onInterceptTouchEvent(CoordinatorLayout coordinatorLayout, View view, MotionEvent motionEvent) {
            return super.onInterceptTouchEvent(coordinatorLayout, view, motionEvent);
        }

        @Override // v.AbstractC0749a, androidx.coordinatorlayout.widget.CoordinatorLayout.Behavior
        public /* bridge */ /* synthetic */ boolean onTouchEvent(CoordinatorLayout coordinatorLayout, View view, MotionEvent motionEvent) {
            return super.onTouchEvent(coordinatorLayout, view, motionEvent);
        }

        @Override // com.google.android.material.appbar.AppBarLayout.BaseBehavior
        public /* bridge */ /* synthetic */ boolean r(CoordinatorLayout coordinatorLayout, AppBarLayout appBarLayout, int i2) {
            return super.r(coordinatorLayout, appBarLayout, i2);
        }

        @Override // com.google.android.material.appbar.AppBarLayout.BaseBehavior
        public /* bridge */ /* synthetic */ boolean s(CoordinatorLayout coordinatorLayout, AppBarLayout appBarLayout, int i2, int i3, int i4, int i5) {
            return super.s(coordinatorLayout, appBarLayout, i2, i3, i4, i5);
        }

        @Override // com.google.android.material.appbar.AppBarLayout.BaseBehavior
        public /* bridge */ /* synthetic */ void t(CoordinatorLayout coordinatorLayout, AppBarLayout appBarLayout, View view, int i2, int i3, int[] iArr, int i4) {
            super.t(coordinatorLayout, appBarLayout, view, i2, i3, iArr, i4);
        }

        @Override // com.google.android.material.appbar.AppBarLayout.BaseBehavior
        public /* bridge */ /* synthetic */ void u(CoordinatorLayout coordinatorLayout, AppBarLayout appBarLayout, View view, int i2, int i3, int i4, int i5, int i6, int[] iArr) {
            super.u(coordinatorLayout, appBarLayout, view, i2, i3, i4, i5, i6, iArr);
        }

        @Override // com.google.android.material.appbar.AppBarLayout.BaseBehavior
        public /* bridge */ /* synthetic */ void v(CoordinatorLayout coordinatorLayout, AppBarLayout appBarLayout, Parcelable parcelable) {
            super.v(coordinatorLayout, appBarLayout, parcelable);
        }

        @Override // com.google.android.material.appbar.AppBarLayout.BaseBehavior
        public /* bridge */ /* synthetic */ Parcelable w(CoordinatorLayout coordinatorLayout, AppBarLayout appBarLayout) {
            return super.w(coordinatorLayout, appBarLayout);
        }

        @Override // com.google.android.material.appbar.AppBarLayout.BaseBehavior
        public /* bridge */ /* synthetic */ boolean x(CoordinatorLayout coordinatorLayout, AppBarLayout appBarLayout, View view, View view2, int i2, int i3) {
            return super.x(coordinatorLayout, appBarLayout, view, view2, i2, i3);
        }

        @Override // com.google.android.material.appbar.AppBarLayout.BaseBehavior
        public /* bridge */ /* synthetic */ void y(CoordinatorLayout coordinatorLayout, AppBarLayout appBarLayout, View view, int i2) {
            super.y(coordinatorLayout, appBarLayout, view, i2);
        }

        public Behavior(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
        }
    }

    public static class ScrollingViewBehavior extends AbstractC0750b {
        public ScrollingViewBehavior() {
        }

        @Override // v.AbstractC0750b
        public /* bridge */ /* synthetic */ View c(List list) {
            k(list);
            return null;
        }

        @Override // v.AbstractC0750b
        public float e(View view) {
            return 0.0f;
        }

        @Override // v.AbstractC0750b
        public int f(View view) {
            return super.f(view);
        }

        public AppBarLayout k(List list) {
            int size = list.size();
            for (int i2 = 0; i2 < size; i2++) {
            }
            return null;
        }

        public final void l(View view, View view2) {
            CoordinatorLayout.Behavior behavior = ((CoordinatorLayout.LayoutParams) view2.getLayoutParams()).getBehavior();
            if (behavior instanceof BaseBehavior) {
                ViewCompat.offsetTopAndBottom(view, (((view2.getBottom() - view.getTop()) + ((BaseBehavior) behavior).f1585k) + g()) - d(view2));
            }
        }

        @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.Behavior
        public boolean layoutDependsOn(CoordinatorLayout coordinatorLayout, View view, View view2) {
            return false;
        }

        public final void m(View view, View view2) {
        }

        @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.Behavior
        public boolean onDependentViewChanged(CoordinatorLayout coordinatorLayout, View view, View view2) {
            l(view, view2);
            m(view, view2);
            return false;
        }

        @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.Behavior
        public void onDependentViewRemoved(CoordinatorLayout coordinatorLayout, View view, View view2) {
        }

        @Override // v.AbstractC0751c, androidx.coordinatorlayout.widget.CoordinatorLayout.Behavior
        public /* bridge */ /* synthetic */ boolean onLayoutChild(CoordinatorLayout coordinatorLayout, View view, int i2) {
            return super.onLayoutChild(coordinatorLayout, view, i2);
        }

        @Override // v.AbstractC0750b, androidx.coordinatorlayout.widget.CoordinatorLayout.Behavior
        public /* bridge */ /* synthetic */ boolean onMeasureChild(CoordinatorLayout coordinatorLayout, View view, int i2, int i3, int i4, int i5) {
            return super.onMeasureChild(coordinatorLayout, view, i2, i3, i4, i5);
        }

        @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.Behavior
        public boolean onRequestChildRectangleOnScreen(CoordinatorLayout coordinatorLayout, View view, Rect rect, boolean z2) {
            k(coordinatorLayout.getDependencies(view));
            return false;
        }

        public ScrollingViewBehavior(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
            TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, j.e4);
            i(typedArrayObtainStyledAttributes.getDimensionPixelSize(j.f4, 0));
            typedArrayObtainStyledAttributes.recycle();
        }
    }
}
