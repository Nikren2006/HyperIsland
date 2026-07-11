package com.android.systemui.miui.globalactions;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.constraintlayout.core.widgets.analyzer.BasicMeasure;

/* JADX INFO: loaded from: classes2.dex */
public class DragButton extends ViewGroup {
    private int mChildHeight;
    private int mChildWidth;
    private Context mContext;
    private int mDragButtonSize;
    private ImageView mMoveIcon;

    public DragButton(Context context) {
        this(context, null);
    }

    private void init(Context context) {
        this.mContext = context;
        this.mChildWidth = (int) context.getResources().getDimension(R.dimen.moveicon_width);
        this.mChildHeight = (int) this.mContext.getResources().getDimension(R.dimen.moveicon_height);
        this.mDragButtonSize = (int) this.mContext.getResources().getDimension(R.dimen.mask_size);
        ImageView imageView = new ImageView(this.mContext);
        this.mMoveIcon = imageView;
        imageView.setImageResource(R.drawable.ic_moveicon);
        addView(this.mMoveIcon);
    }

    public void changeImage(int i2, int i3, int i4) {
        this.mChildWidth = i3;
        this.mChildHeight = i4;
        this.mMoveIcon.setImageResource(i2);
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onLayout(boolean z2, int i2, int i3, int i4, int i5) {
        int i6 = this.mDragButtonSize;
        int i7 = this.mChildWidth;
        int i8 = (i6 - i7) / 2;
        int i9 = this.mChildHeight;
        int i10 = (i6 - i9) / 2;
        this.mMoveIcon.layout(i8, i10, i7 + i8, i9 + i10);
    }

    @Override // android.view.View
    public void onMeasure(int i2, int i3) {
        this.mMoveIcon.measure(View.MeasureSpec.makeMeasureSpec(this.mChildWidth, BasicMeasure.EXACTLY), View.MeasureSpec.makeMeasureSpec(this.mChildHeight, BasicMeasure.EXACTLY));
        setMeasuredDimension(View.MeasureSpec.makeMeasureSpec(this.mDragButtonSize, BasicMeasure.EXACTLY), View.MeasureSpec.makeMeasureSpec(this.mDragButtonSize, BasicMeasure.EXACTLY));
    }

    public DragButton(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        init(context);
    }
}
