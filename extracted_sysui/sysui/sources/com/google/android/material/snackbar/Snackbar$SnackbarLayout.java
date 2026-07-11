package com.google.android.material.snackbar;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.Nullable;
import androidx.annotation.RestrictTo;
import androidx.constraintlayout.core.widgets.analyzer.BasicMeasure;
import com.google.android.material.snackbar.BaseTransientBottomBar;

/* JADX INFO: loaded from: classes2.dex */
@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
public final class Snackbar$SnackbarLayout extends BaseTransientBottomBar.c {
    public Snackbar$SnackbarLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    @Override // com.google.android.material.snackbar.BaseTransientBottomBar.c, android.widget.FrameLayout, android.view.View
    public void onMeasure(int i2, int i3) {
        super.onMeasure(i2, i3);
        int childCount = getChildCount();
        int measuredWidth = (getMeasuredWidth() - getPaddingLeft()) - getPaddingRight();
        for (int i4 = 0; i4 < childCount; i4++) {
            View childAt = getChildAt(i4);
            if (childAt.getLayoutParams().width == -1) {
                childAt.measure(View.MeasureSpec.makeMeasureSpec(measuredWidth, BasicMeasure.EXACTLY), View.MeasureSpec.makeMeasureSpec(childAt.getMeasuredHeight(), BasicMeasure.EXACTLY));
            }
        }
    }

    @Override // com.google.android.material.snackbar.BaseTransientBottomBar.c, android.view.View
    public /* bridge */ /* synthetic */ void setBackground(@Nullable Drawable drawable) {
        super.setBackground(drawable);
    }

    @Override // com.google.android.material.snackbar.BaseTransientBottomBar.c, android.view.View
    public /* bridge */ /* synthetic */ void setBackgroundDrawable(@Nullable Drawable drawable) {
        super.setBackgroundDrawable(drawable);
    }

    @Override // com.google.android.material.snackbar.BaseTransientBottomBar.c, android.view.View
    public /* bridge */ /* synthetic */ void setBackgroundTintList(@Nullable ColorStateList colorStateList) {
        super.setBackgroundTintList(colorStateList);
    }

    @Override // com.google.android.material.snackbar.BaseTransientBottomBar.c, android.view.View
    public /* bridge */ /* synthetic */ void setBackgroundTintMode(@Nullable PorterDuff.Mode mode) {
        super.setBackgroundTintMode(mode);
    }

    @Override // com.google.android.material.snackbar.BaseTransientBottomBar.c, android.view.View
    public /* bridge */ /* synthetic */ void setLayoutParams(ViewGroup.LayoutParams layoutParams) {
        super.setLayoutParams(layoutParams);
    }

    @Override // com.google.android.material.snackbar.BaseTransientBottomBar.c, android.view.View
    public /* bridge */ /* synthetic */ void setOnClickListener(@Nullable View.OnClickListener onClickListener) {
        super.setOnClickListener(onClickListener);
    }
}
