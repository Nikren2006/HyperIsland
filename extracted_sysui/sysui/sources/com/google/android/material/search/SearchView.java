package com.google.android.material.search;

import android.content.Context;
import android.support.v4.media.a;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.coordinatorlayout.widget.CoordinatorLayout;

/* JADX INFO: loaded from: classes2.dex */
public abstract class SearchView extends FrameLayout implements CoordinatorLayout.AttachedBehavior {

    public static class Behavior extends CoordinatorLayout.Behavior<SearchView> {
        public Behavior() {
        }

        public boolean a(CoordinatorLayout coordinatorLayout, SearchView searchView, View view) {
            throw null;
        }

        @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.Behavior
        public /* bridge */ /* synthetic */ boolean onDependentViewChanged(CoordinatorLayout coordinatorLayout, View view, View view2) {
            a.a(view);
            return a(coordinatorLayout, null, view2);
        }

        public Behavior(@NonNull Context context, @Nullable AttributeSet attributeSet) {
            super(context, attributeSet);
        }
    }
}
