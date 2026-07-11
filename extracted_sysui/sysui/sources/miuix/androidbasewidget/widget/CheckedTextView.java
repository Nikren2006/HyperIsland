package miuix.androidbasewidget.widget;

import android.R;
import android.content.Context;
import android.content.res.Configuration;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.NinePatchDrawable;
import android.graphics.drawable.StateListDrawable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;

/* JADX INFO: loaded from: classes4.dex */
public class CheckedTextView extends AppCompatCheckedTextView {
    private static final int[] CHECKED_STATE_SET = {R.attr.state_checked};
    private static final int CHECK_MARK_PADDING = 0;
    private Drawable mCheckMarkDrawable;
    private int mCheckMarkMarginToText;
    private boolean mDrawCheckMark;
    private boolean mDrawTextOffsetInRtl;

    public CheckedTextView(Context context) {
        this(context, null);
    }

    private void drawCheckMark(Canvas canvas, boolean z2) {
        int width;
        int scrollX;
        Drawable checkMarkDrawable = getCheckMarkDrawable();
        if (checkMarkDrawable != null) {
            int intrinsicWidth = checkMarkDrawable.getCurrent().getIntrinsicWidth();
            if (z2) {
                width = getPaddingLeft();
                scrollX = getScrollX();
            } else {
                width = (getWidth() - getPaddingRight()) - intrinsicWidth;
                scrollX = getScrollX();
            }
            int i2 = width + scrollX;
            int paddingTop = getPaddingTop();
            int paddingTop2 = getPaddingTop();
            int paddingBottom = getPaddingBottom();
            int intrinsicHeight = checkMarkDrawable.getIntrinsicHeight();
            if (checkMarkDrawable.getCurrent() instanceof NinePatchDrawable) {
                intrinsicHeight = (getHeight() - paddingTop) - getPaddingBottom();
            } else {
                int gravity = getGravity() & 112;
                if (gravity == 16) {
                    paddingTop = getCheckMarkPositionY(getHeight(), intrinsicHeight, paddingTop2, paddingBottom);
                } else if (gravity == 80) {
                    paddingTop = getHeight() - intrinsicHeight;
                }
            }
            checkMarkDrawable.setBounds(i2, paddingTop, intrinsicWidth + i2, intrinsicHeight + paddingTop);
            checkMarkDrawable.draw(canvas);
        }
    }

    public static int getCheckMarkPositionY(int i2, int i3, int i4, int i5) {
        return ((((i2 - i4) - i5) - i3) / 2) + i4;
    }

    private int getCheckWidth() {
        Drawable checkMarkDrawable = getCheckMarkDrawable();
        if (checkMarkDrawable == null) {
            return 0;
        }
        return checkMarkDrawable.getCurrent().getIntrinsicWidth();
    }

    @Override // androidx.appcompat.widget.AppCompatCheckedTextView, android.widget.CheckedTextView, android.widget.TextView, android.view.View
    public void drawableStateChanged() {
        super.drawableStateChanged();
        if (this.mCheckMarkDrawable != null) {
            this.mCheckMarkDrawable.setState(getDrawableState());
            invalidate();
        }
    }

    @Override // android.widget.CheckedTextView
    public Drawable getCheckMarkDrawable() {
        return this.mCheckMarkDrawable;
    }

    @Override // android.widget.CheckedTextView, android.widget.TextView, android.view.View
    public void jumpDrawablesToCurrentState() {
        super.jumpDrawablesToCurrentState();
        Drawable drawable = this.mCheckMarkDrawable;
        if (drawable != null) {
            drawable.jumpToCurrentState();
        }
    }

    @Override // android.widget.TextView, android.view.View
    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        this.mCheckMarkMarginToText = (int) getContext().getResources().getDimension(miuix.androidbasewidget.R.dimen.miuix_appcompat_checked_text_view_addition_margin);
    }

    @Override // android.widget.CheckedTextView, android.widget.TextView, android.view.View
    public int[] onCreateDrawableState(int i2) {
        int[] iArrOnCreateDrawableState = super.onCreateDrawableState(i2 + 1);
        if (isChecked()) {
            android.widget.CheckedTextView.mergeDrawableStates(iArrOnCreateDrawableState, CHECKED_STATE_SET);
        }
        return iArrOnCreateDrawableState;
    }

    @Override // android.widget.CheckedTextView, android.widget.TextView, android.view.View
    public void onDraw(Canvas canvas) {
        boolean z2 = getLayoutDirection() == 1;
        if (this.mDrawCheckMark) {
            drawCheckMark(canvas, z2);
        }
        if (getCheckMarkDrawable() != null) {
            this.mDrawTextOffsetInRtl = getCheckMarkDrawable().getClass().isAssignableFrom(StateListDrawable.class);
        } else {
            this.mDrawTextOffsetInRtl = false;
        }
        if (z2 && this.mDrawCheckMark && this.mDrawTextOffsetInRtl) {
            canvas.save();
            canvas.translate(getCheckWidth(), 0.0f);
        }
        super.onDraw(canvas);
        if (z2 && this.mDrawCheckMark && this.mDrawTextOffsetInRtl) {
            canvas.restore();
        }
    }

    @Override // android.widget.TextView, android.view.View
    public void onMeasure(int i2, int i3) {
        int size = View.MeasureSpec.getSize(i2);
        int checkWidth = getCheckWidth();
        if (checkWidth > 0) {
            if (TextUtils.isEmpty(getText())) {
                this.mDrawCheckMark = true;
                super.onMeasure(View.MeasureSpec.makeMeasureSpec(checkWidth, View.MeasureSpec.getMode(i2)), i3);
                setMeasuredDimension(checkWidth, getMeasuredHeight());
                return;
            } else if (size - getPaddingEnd() < checkWidth * 2) {
                this.mDrawCheckMark = false;
                checkWidth = 0;
            } else {
                this.mDrawCheckMark = true;
            }
        }
        int mode = View.MeasureSpec.getMode(i2);
        int iMakeMeasureSpec = View.MeasureSpec.makeMeasureSpec(size - checkWidth, View.MeasureSpec.getMode(i2));
        if (mode == 1073741824) {
            super.onMeasure(iMakeMeasureSpec, i3);
            if (checkWidth == 0) {
                return;
            }
            setMeasuredDimension(getMeasuredWidth() + checkWidth, getMeasuredHeight());
            return;
        }
        int minWidth = getMinWidth();
        if (this.mDrawCheckMark) {
            setMinWidth(0);
            setMinimumWidth(0);
        }
        super.onMeasure(iMakeMeasureSpec, i3);
        if (checkWidth == 0) {
            return;
        }
        setMeasuredDimension(Math.max(getMeasuredWidth() + checkWidth, minWidth), getMeasuredHeight());
        setMinWidth(minWidth);
        setMinimumWidth(minWidth);
    }

    @Override // android.widget.TextView
    public void onTextChanged(CharSequence charSequence, int i2, int i3, int i4) {
        super.onTextChanged(charSequence, i2, i3, i4);
        requestLayout();
    }

    @Override // androidx.appcompat.widget.AppCompatCheckedTextView, android.widget.CheckedTextView
    public void setCheckMarkDrawable(Drawable drawable) {
        Drawable drawable2 = this.mCheckMarkDrawable;
        if (drawable2 != null) {
            drawable2.setCallback(null);
            unscheduleDrawable(this.mCheckMarkDrawable);
        }
        if (drawable != null) {
            drawable.setCallback(this);
            drawable.setVisible(getVisibility() == 0, false);
            drawable.setState(CHECKED_STATE_SET);
            setMinHeight(drawable.getIntrinsicHeight());
            drawable.setState(getDrawableState());
        }
        this.mCheckMarkDrawable = drawable;
    }

    @Override // android.widget.CheckedTextView, android.widget.TextView, android.view.View
    public boolean verifyDrawable(Drawable drawable) {
        return super.verifyDrawable(drawable) || drawable == this.mCheckMarkDrawable;
    }

    public CheckedTextView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, R.attr.checkedTextViewStyle);
    }

    public CheckedTextView(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        this.mDrawCheckMark = true;
        this.mDrawTextOffsetInRtl = false;
        this.mCheckMarkMarginToText = (int) getContext().getResources().getDimension(miuix.androidbasewidget.R.dimen.miuix_appcompat_checked_text_view_addition_margin);
    }
}
