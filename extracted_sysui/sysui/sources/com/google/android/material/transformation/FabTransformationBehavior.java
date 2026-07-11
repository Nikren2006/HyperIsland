package com.google.android.material.transformation;

import android.content.Context;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;
import androidx.coordinatorlayout.widget.CoordinatorLayout;

/* JADX INFO: loaded from: classes2.dex */
@Deprecated
public abstract class FabTransformationBehavior extends ExpandableTransformationBehavior {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final Rect f2309b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final RectF f2310c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final RectF f2311d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final int[] f2312e;

    public FabTransformationBehavior() {
        this.f2309b = new Rect();
        this.f2310c = new RectF();
        this.f2311d = new RectF();
        this.f2312e = new int[2];
    }

    @Override // com.google.android.material.transformation.ExpandableBehavior, androidx.coordinatorlayout.widget.CoordinatorLayout.Behavior
    public boolean layoutDependsOn(CoordinatorLayout coordinatorLayout, View view, View view2) {
        if (view.getVisibility() != 8) {
            return false;
        }
        throw new IllegalStateException("This behavior cannot be attached to a GONE view. Set the view to INVISIBLE instead.");
    }

    @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.Behavior
    public void onAttachedToLayoutParams(CoordinatorLayout.LayoutParams layoutParams) {
        if (layoutParams.dodgeInsetEdges == 0) {
            layoutParams.dodgeInsetEdges = 80;
        }
    }

    public FabTransformationBehavior(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f2309b = new Rect();
        this.f2310c = new RectF();
        this.f2311d = new RectF();
        this.f2312e = new int[2];
    }
}
