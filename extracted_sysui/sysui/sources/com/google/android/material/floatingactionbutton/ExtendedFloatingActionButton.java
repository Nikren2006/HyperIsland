package com.google.android.material.floatingactionbutton;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Rect;
import android.support.v4.media.a;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.button.MaterialButton;
import java.util.List;
import t.j;

/* JADX INFO: loaded from: classes2.dex */
public abstract class ExtendedFloatingActionButton extends MaterialButton implements CoordinatorLayout.AttachedBehavior {

    public static class ExtendedFloatingActionButtonBehavior<T extends ExtendedFloatingActionButton> extends CoordinatorLayout.Behavior<T> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public boolean f2037a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public boolean f2038b;

        public ExtendedFloatingActionButtonBehavior() {
            this.f2037a = false;
            this.f2038b = true;
        }

        public static boolean b(View view) {
            ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
            if (layoutParams instanceof CoordinatorLayout.LayoutParams) {
                return ((CoordinatorLayout.LayoutParams) layoutParams).getBehavior() instanceof BottomSheetBehavior;
            }
            return false;
        }

        public boolean a(CoordinatorLayout coordinatorLayout, ExtendedFloatingActionButton extendedFloatingActionButton, Rect rect) {
            return super.getInsetDodgeRect(coordinatorLayout, extendedFloatingActionButton, rect);
        }

        public boolean c(CoordinatorLayout coordinatorLayout, ExtendedFloatingActionButton extendedFloatingActionButton, View view) {
            if (!b(view)) {
                return false;
            }
            f(view, extendedFloatingActionButton);
            return false;
        }

        public boolean d(CoordinatorLayout coordinatorLayout, ExtendedFloatingActionButton extendedFloatingActionButton, int i2) {
            List<View> dependencies = coordinatorLayout.getDependencies(extendedFloatingActionButton);
            int size = dependencies.size();
            for (int i3 = 0; i3 < size; i3++) {
                View view = dependencies.get(i3);
                if (b(view) && f(view, extendedFloatingActionButton)) {
                    break;
                }
            }
            coordinatorLayout.onLayoutChild(extendedFloatingActionButton, i2);
            return true;
        }

        public final boolean e(View view, ExtendedFloatingActionButton extendedFloatingActionButton) {
            throw null;
        }

        public final boolean f(View view, ExtendedFloatingActionButton extendedFloatingActionButton) {
            if (e(view, extendedFloatingActionButton)) {
                throw null;
            }
            return false;
        }

        @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.Behavior
        public /* bridge */ /* synthetic */ boolean getInsetDodgeRect(CoordinatorLayout coordinatorLayout, View view, Rect rect) {
            a.a(view);
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
            a.a(view);
            return c(coordinatorLayout, null, view2);
        }

        @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.Behavior
        public /* bridge */ /* synthetic */ boolean onLayoutChild(CoordinatorLayout coordinatorLayout, View view, int i2) {
            a.a(view);
            return d(coordinatorLayout, null, i2);
        }

        public ExtendedFloatingActionButtonBehavior(@NonNull Context context, @Nullable AttributeSet attributeSet) {
            super(context, attributeSet);
            TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, j.f6791n1);
            this.f2037a = typedArrayObtainStyledAttributes.getBoolean(j.f6794o1, false);
            this.f2038b = typedArrayObtainStyledAttributes.getBoolean(j.f6797p1, true);
            typedArrayObtainStyledAttributes.recycle();
        }
    }
}
