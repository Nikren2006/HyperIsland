package com.android.systemui.miui.volume.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.transition.AutoTransition;
import android.transition.Transition;
import android.transition.TransitionInflater;
import android.transition.TransitionManager;
import android.util.AttributeSet;
import android.view.ViewGroup;
import com.android.systemui.miui.volume.R;

/* JADX INFO: loaded from: classes2.dex */
class ExpandCollapseStateHelper {
    private boolean mExpanded = false;
    private OnExpandStateUpdatedListener mListener;
    private Transition mTransitionCollapse;
    private Transition mTransitionExpand;
    private ViewGroup mTransitionRoot;

    public interface OnExpandStateUpdatedListener {
        void onExpandStateUpdated(boolean z2);
    }

    public ExpandCollapseStateHelper(ViewGroup viewGroup, OnExpandStateUpdatedListener onExpandStateUpdatedListener, AttributeSet attributeSet, int i2) {
        this.mTransitionRoot = viewGroup;
        Context context = viewGroup.getContext();
        this.mListener = onExpandStateUpdatedListener;
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.ExpandCollapseLayout, i2, 0);
        this.mTransitionExpand = getTransition(context, typedArrayObtainStyledAttributes, R.styleable.ExpandCollapseLayout_expandingTransition, new AutoTransition());
        this.mTransitionCollapse = getTransition(context, typedArrayObtainStyledAttributes, R.styleable.ExpandCollapseLayout_collapsingTransition, new AutoTransition());
        typedArrayObtainStyledAttributes.recycle();
    }

    private static Transition getTransition(Context context, TypedArray typedArray, int i2, Transition transition) {
        int resourceId = typedArray.getResourceId(i2, -1);
        return resourceId > 0 ? TransitionInflater.from(context).inflateTransition(resourceId) : transition;
    }

    public void beginDelayedTransition() {
        TransitionManager.endTransitions(this.mTransitionRoot);
        TransitionManager.beginDelayedTransition(this.mTransitionRoot, this.mExpanded ? this.mTransitionExpand : this.mTransitionCollapse);
    }

    public boolean isExpanded() {
        return this.mExpanded;
    }

    public final void updateExpanded(boolean z2, boolean z3) {
        this.mExpanded = z2;
        if (z3) {
            beginDelayedTransition();
        }
        OnExpandStateUpdatedListener onExpandStateUpdatedListener = this.mListener;
        if (onExpandStateUpdatedListener != null) {
            onExpandStateUpdatedListener.onExpandStateUpdated(z2);
        }
    }
}
