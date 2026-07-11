package miuix.internal.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import androidx.appcompat.widget.ViewUtils;
import miuix.appcompat.R;
import miuix.appcompat.widget.Button;

/* JADX INFO: loaded from: classes3.dex */
public class GroupButton extends Button {
    private AttributeSet mAttrsCache;
    private boolean mPrimary;
    private static final int[] STATE_FIRST_V = {R.attr.state_first_v};
    private static final int[] STATE_MIDDLE_V = {R.attr.state_middle_v};
    private static final int[] STATE_LAST_V = {R.attr.state_last_v};
    private static final int[] STATE_FIRST_H = {R.attr.state_first_h};
    private static final int[] STATE_MIDDLE_H = {R.attr.state_middle_h};
    private static final int[] STATE_LAST_H = {R.attr.state_last_h};
    private static final int[] STATE_SINGLE_H = {R.attr.state_single_h};

    public GroupButton(Context context) {
        super(context);
    }

    private void initAttr(Context context, AttributeSet attributeSet, int i2) {
        this.mAttrsCache = attributeSet;
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.GroupButton, i2, 0);
        try {
            int i3 = R.styleable.GroupButton_primaryButton;
            if (typedArrayObtainStyledAttributes.hasValue(i3)) {
                this.mPrimary = typedArrayObtainStyledAttributes.getBoolean(i3, false);
            }
        } finally {
            typedArrayObtainStyledAttributes.recycle();
        }
    }

    public boolean isPrimary() {
        return this.mPrimary;
    }

    @Override // android.widget.TextView, android.view.View
    public int[] onCreateDrawableState(int i2) {
        ViewGroup viewGroup = (ViewGroup) getParent();
        if (viewGroup != null && (viewGroup instanceof LinearLayout)) {
            int orientation = ((LinearLayout) viewGroup).getOrientation();
            int iIndexOfChild = viewGroup.indexOfChild(this);
            int i3 = 0;
            boolean z2 = true;
            boolean z3 = true;
            for (int i4 = 0; i4 < viewGroup.getChildCount(); i4++) {
                if (viewGroup.getChildAt(i4).getVisibility() == 0) {
                    i3++;
                    if (i4 < iIndexOfChild) {
                        z2 = false;
                    }
                    if (i4 > iIndexOfChild) {
                        z3 = false;
                    }
                }
            }
            boolean z4 = i3 == 1;
            if (orientation == 1) {
                int[] iArrOnCreateDrawableState = super.onCreateDrawableState(i2 + 2);
                android.widget.Button.mergeDrawableStates(iArrOnCreateDrawableState, STATE_SINGLE_H);
                if (!z4) {
                    if (z2) {
                        android.widget.Button.mergeDrawableStates(iArrOnCreateDrawableState, STATE_FIRST_V);
                    } else if (z3) {
                        android.widget.Button.mergeDrawableStates(iArrOnCreateDrawableState, STATE_LAST_V);
                    } else {
                        android.widget.Button.mergeDrawableStates(iArrOnCreateDrawableState, STATE_MIDDLE_V);
                    }
                }
                return iArrOnCreateDrawableState;
            }
            boolean zIsLayoutRtl = ViewUtils.isLayoutRtl(this);
            int[] iArrOnCreateDrawableState2 = super.onCreateDrawableState(i2 + 1);
            if (z4) {
                android.widget.Button.mergeDrawableStates(iArrOnCreateDrawableState2, STATE_SINGLE_H);
            } else if (z2) {
                android.widget.Button.mergeDrawableStates(iArrOnCreateDrawableState2, zIsLayoutRtl ? STATE_LAST_H : STATE_FIRST_H);
            } else if (z3) {
                android.widget.Button.mergeDrawableStates(iArrOnCreateDrawableState2, zIsLayoutRtl ? STATE_FIRST_H : STATE_LAST_H);
            } else {
                android.widget.Button.mergeDrawableStates(iArrOnCreateDrawableState2, STATE_MIDDLE_H);
            }
            return iArrOnCreateDrawableState2;
        }
        return super.onCreateDrawableState(i2);
    }

    public GroupButton(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public GroupButton(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        initAttr(context, attributeSet, i2);
    }
}
