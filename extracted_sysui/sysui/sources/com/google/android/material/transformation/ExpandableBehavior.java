package com.google.android.material.transformation;

import G.a;
import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.view.ViewCompat;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
@Deprecated
public abstract class ExpandableBehavior extends CoordinatorLayout.Behavior<View> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public int f2308a;

    public ExpandableBehavior() {
        this.f2308a = 0;
    }

    public a a(CoordinatorLayout coordinatorLayout, View view) {
        List<View> dependencies = coordinatorLayout.getDependencies(view);
        int size = dependencies.size();
        for (int i2 = 0; i2 < size; i2++) {
            View view2 = dependencies.get(i2);
            if (layoutDependsOn(coordinatorLayout, view, view2)) {
                android.support.v4.media.a.a(view2);
                return null;
            }
        }
        return null;
    }

    @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.Behavior
    public abstract boolean layoutDependsOn(CoordinatorLayout coordinatorLayout, View view, View view2);

    @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.Behavior
    public boolean onDependentViewChanged(CoordinatorLayout coordinatorLayout, View view, View view2) {
        android.support.v4.media.a.a(view2);
        throw null;
    }

    @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.Behavior
    public boolean onLayoutChild(CoordinatorLayout coordinatorLayout, View view, int i2) {
        if (ViewCompat.isLaidOut(view)) {
            return false;
        }
        a(coordinatorLayout, view);
        return false;
    }

    public ExpandableBehavior(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f2308a = 0;
    }
}
