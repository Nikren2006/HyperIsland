package com.android.systemui.miui.volume.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.LinearLayout;
import com.android.systemui.miui.volume.widget.ExpandCollapseStateHelper;

/* JADX INFO: loaded from: classes2.dex */
public class ExpandCollapseLinearLayout extends LinearLayout implements ExpandCollapseStateHelper.OnExpandStateUpdatedListener {
    public boolean isInterceptTouchEvent;
    private ExpandCollapseStateHelper mStateHelper;

    public ExpandCollapseLinearLayout(Context context) {
        this(context, null);
    }

    public boolean isExpanded() {
        return this.mStateHelper.isExpanded();
    }

    public void onExpandStateUpdated(boolean z2) {
    }

    @Override // android.view.ViewGroup
    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        if (this.isInterceptTouchEvent) {
            return true;
        }
        return super.onInterceptTouchEvent(motionEvent);
    }

    public void setInterceptTouchEvent(boolean z2) {
        this.isInterceptTouchEvent = z2;
    }

    public void updateExpanded(boolean z2, boolean z3) {
        this.mStateHelper.updateExpanded(z2, z3);
    }

    public ExpandCollapseLinearLayout(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public ExpandCollapseLinearLayout(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        this.isInterceptTouchEvent = false;
        this.mStateHelper = new ExpandCollapseStateHelper(this, this, attributeSet, i2);
    }
}
