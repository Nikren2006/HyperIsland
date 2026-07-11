package com.google.android.material.search;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import com.google.android.material.appbar.AppBarLayout;

/* JADX INFO: loaded from: classes2.dex */
public class SearchBar$ScrollingViewBehavior extends AppBarLayout.ScrollingViewBehavior {

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public boolean f2063h;

    public SearchBar$ScrollingViewBehavior() {
        this.f2063h = false;
    }

    @Override // v.AbstractC0750b
    public boolean j() {
        return true;
    }

    @Override // com.google.android.material.appbar.AppBarLayout.ScrollingViewBehavior, androidx.coordinatorlayout.widget.CoordinatorLayout.Behavior
    public boolean onDependentViewChanged(CoordinatorLayout coordinatorLayout, View view, View view2) {
        return super.onDependentViewChanged(coordinatorLayout, view, view2);
    }

    public SearchBar$ScrollingViewBehavior(@NonNull Context context, @Nullable AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f2063h = false;
    }
}
