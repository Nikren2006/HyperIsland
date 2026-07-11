package com.google.android.material.floatingactionbutton;

import G.a;
import H.o;
import O.n;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.view.TintableBackgroundView;
import androidx.core.widget.TintableImageSourceView;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import java.util.List;
import t.j;

/* JADX INFO: loaded from: classes2.dex */
public abstract class FloatingActionButton extends o implements TintableBackgroundView, TintableImageSourceView, a, n, CoordinatorLayout.AttachedBehavior {

    public static class Behavior extends BaseBehavior<FloatingActionButton> {
        public Behavior() {
        }

        @Override // com.google.android.material.floatingactionbutton.FloatingActionButton.BaseBehavior
        public /* bridge */ /* synthetic */ boolean a(CoordinatorLayout coordinatorLayout, FloatingActionButton floatingActionButton, Rect rect) {
            return super.a(coordinatorLayout, floatingActionButton, rect);
        }

        @Override // com.google.android.material.floatingactionbutton.FloatingActionButton.BaseBehavior
        public /* bridge */ /* synthetic */ boolean d(CoordinatorLayout coordinatorLayout, FloatingActionButton floatingActionButton, View view) {
            return super.d(coordinatorLayout, floatingActionButton, view);
        }

        @Override // com.google.android.material.floatingactionbutton.FloatingActionButton.BaseBehavior
        public /* bridge */ /* synthetic */ boolean e(CoordinatorLayout coordinatorLayout, FloatingActionButton floatingActionButton, int i2) {
            return super.e(coordinatorLayout, floatingActionButton, i2);
        }

        @Override // com.google.android.material.floatingactionbutton.FloatingActionButton.BaseBehavior, androidx.coordinatorlayout.widget.CoordinatorLayout.Behavior
        public /* bridge */ /* synthetic */ void onAttachedToLayoutParams(CoordinatorLayout.LayoutParams layoutParams) {
            super.onAttachedToLayoutParams(layoutParams);
        }

        public Behavior(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
        }
    }

    public static class BaseBehavior<T extends FloatingActionButton> extends CoordinatorLayout.Behavior<T> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public boolean f2039a;

        public BaseBehavior() {
            this.f2039a = true;
        }

        private static boolean b(View view) {
            ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
            if (layoutParams instanceof CoordinatorLayout.LayoutParams) {
                return ((CoordinatorLayout.LayoutParams) layoutParams).getBehavior() instanceof BottomSheetBehavior;
            }
            return false;
        }

        public boolean a(CoordinatorLayout coordinatorLayout, FloatingActionButton floatingActionButton, Rect rect) {
            throw null;
        }

        public final void c(CoordinatorLayout coordinatorLayout, FloatingActionButton floatingActionButton) {
            throw null;
        }

        public boolean d(CoordinatorLayout coordinatorLayout, FloatingActionButton floatingActionButton, View view) {
            if (!b(view)) {
                return false;
            }
            g(view, floatingActionButton);
            return false;
        }

        public boolean e(CoordinatorLayout coordinatorLayout, FloatingActionButton floatingActionButton, int i2) {
            List<View> dependencies = coordinatorLayout.getDependencies(floatingActionButton);
            int size = dependencies.size();
            for (int i3 = 0; i3 < size; i3++) {
                View view = dependencies.get(i3);
                if (b(view) && g(view, floatingActionButton)) {
                    break;
                }
            }
            coordinatorLayout.onLayoutChild(floatingActionButton, i2);
            c(coordinatorLayout, floatingActionButton);
            return true;
        }

        public final boolean f(View view, FloatingActionButton floatingActionButton) {
            throw null;
        }

        public final boolean g(View view, FloatingActionButton floatingActionButton) {
            if (f(view, floatingActionButton)) {
                throw null;
            }
            return false;
        }

        @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.Behavior
        public /* bridge */ /* synthetic */ boolean getInsetDodgeRect(CoordinatorLayout coordinatorLayout, View view, Rect rect) {
            android.support.v4.media.a.a(view);
            return a(coordinatorLayout, null, rect);
        }

        @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.Behavior
        public void onAttachedToLayoutParams(CoordinatorLayout.LayoutParams layoutParams) {
            if (layoutParams.dodgeInsetEdges == 0) {
                layoutParams.dodgeInsetEdges = 80;
            }
        }

        @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.Behavior
        public /* bridge */ /* synthetic */ boolean onDependentViewChanged(CoordinatorLayout coordinatorLayout, View view, View view2) {
            android.support.v4.media.a.a(view);
            return d(coordinatorLayout, null, view2);
        }

        @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.Behavior
        public /* bridge */ /* synthetic */ boolean onLayoutChild(CoordinatorLayout coordinatorLayout, View view, int i2) {
            android.support.v4.media.a.a(view);
            return e(coordinatorLayout, null, i2);
        }

        public BaseBehavior(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
            TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, j.f6803r1);
            this.f2039a = typedArrayObtainStyledAttributes.getBoolean(j.f6806s1, true);
            typedArrayObtainStyledAttributes.recycle();
        }
    }
}
