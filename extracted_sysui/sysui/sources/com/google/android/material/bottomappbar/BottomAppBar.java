package com.google.android.material.bottomappbar;

import android.content.Context;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import androidx.appcompat.widget.Toolbar;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.view.ViewCompat;
import com.google.android.material.behavior.HideBottomViewOnScrollBehavior;
import java.lang.ref.WeakReference;
import t.AbstractC0741a;
import t.i;

/* JADX INFO: loaded from: classes2.dex */
public abstract class BottomAppBar extends Toolbar implements CoordinatorLayout.AttachedBehavior {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final int f1631a = i.f6684g;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final int f1632b = AbstractC0741a.f6523w;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final int f1633c = AbstractC0741a.f6489D;

    public static /* synthetic */ void a(BottomAppBar bottomAppBar) {
        throw null;
    }

    public static /* synthetic */ View b(BottomAppBar bottomAppBar) {
        throw null;
    }

    public static void d(BottomAppBar bottomAppBar, View view) {
        ((CoordinatorLayout.LayoutParams) view.getLayoutParams()).anchorGravity = 17;
        throw null;
    }

    public static class Behavior extends HideBottomViewOnScrollBehavior<BottomAppBar> {

        /* JADX INFO: renamed from: m, reason: collision with root package name */
        public final Rect f1634m;

        /* JADX INFO: renamed from: n, reason: collision with root package name */
        public WeakReference f1635n;

        /* JADX INFO: renamed from: o, reason: collision with root package name */
        public int f1636o;

        /* JADX INFO: renamed from: p, reason: collision with root package name */
        public final View.OnLayoutChangeListener f1637p;

        public class a implements View.OnLayoutChangeListener {
            public a() {
            }

            @Override // android.view.View.OnLayoutChangeListener
            public void onLayoutChange(View view, int i2, int i3, int i4, int i5, int i6, int i7, int i8, int i9) {
                android.support.v4.media.a.a(Behavior.this.f1635n.get());
                view.removeOnLayoutChangeListener(this);
            }
        }

        public Behavior() {
            this.f1637p = new a();
            this.f1634m = new Rect();
        }

        public boolean k(CoordinatorLayout coordinatorLayout, BottomAppBar bottomAppBar, int i2) {
            this.f1635n = new WeakReference(bottomAppBar);
            View viewB = BottomAppBar.b(bottomAppBar);
            if (viewB != null && !ViewCompat.isLaidOut(viewB)) {
                BottomAppBar.d(bottomAppBar, viewB);
                this.f1636o = ((ViewGroup.MarginLayoutParams) ((CoordinatorLayout.LayoutParams) viewB.getLayoutParams())).bottomMargin;
                viewB.addOnLayoutChangeListener(this.f1637p);
                BottomAppBar.a(bottomAppBar);
            }
            coordinatorLayout.onLayoutChild(bottomAppBar, i2);
            return super.onLayoutChild(coordinatorLayout, bottomAppBar, i2);
        }

        public boolean l(CoordinatorLayout coordinatorLayout, BottomAppBar bottomAppBar, View view, View view2, int i2, int i3) {
            throw null;
        }

        @Override // com.google.android.material.behavior.HideBottomViewOnScrollBehavior, androidx.coordinatorlayout.widget.CoordinatorLayout.Behavior
        public /* bridge */ /* synthetic */ boolean onLayoutChild(CoordinatorLayout coordinatorLayout, View view, int i2) {
            android.support.v4.media.a.a(view);
            return k(coordinatorLayout, null, i2);
        }

        @Override // com.google.android.material.behavior.HideBottomViewOnScrollBehavior, androidx.coordinatorlayout.widget.CoordinatorLayout.Behavior
        public /* bridge */ /* synthetic */ boolean onStartNestedScroll(CoordinatorLayout coordinatorLayout, View view, View view2, View view3, int i2, int i3) {
            android.support.v4.media.a.a(view);
            return l(coordinatorLayout, null, view2, view3, i2, i3);
        }

        public Behavior(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
            this.f1637p = new a();
            this.f1634m = new Rect();
        }
    }
}
