package com.google.android.material.transformation;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import u.C0746d;

/* JADX INFO: loaded from: classes2.dex */
@Deprecated
public class FabTransformationScrimBehavior extends ExpandableTransformationBehavior {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final C0746d f2313b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final C0746d f2314c;

    public FabTransformationScrimBehavior() {
        this.f2313b = new C0746d(75L, 150L);
        this.f2314c = new C0746d(0L, 150L);
    }

    @Override // com.google.android.material.transformation.ExpandableBehavior, androidx.coordinatorlayout.widget.CoordinatorLayout.Behavior
    public boolean layoutDependsOn(CoordinatorLayout coordinatorLayout, View view, View view2) {
        return false;
    }

    @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.Behavior
    public boolean onTouchEvent(CoordinatorLayout coordinatorLayout, View view, MotionEvent motionEvent) {
        return super.onTouchEvent(coordinatorLayout, view, motionEvent);
    }

    public FabTransformationScrimBehavior(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f2313b = new C0746d(75L, 150L);
        this.f2314c = new C0746d(0L, 150L);
    }
}
