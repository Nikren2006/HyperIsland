package com.android.systemui.miui.volume;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Path;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.widget.FrameLayout;
import androidx.annotation.Nullable;

/* JADX INFO: loaded from: classes2.dex */
public class RoundRectFrameLayout extends FrameLayout {
    private Context mContext;
    private boolean mNeedShowDialog;
    private int mRadius;

    public RoundRectFrameLayout(Context context) {
        this(context, null);
    }

    @Override // android.view.ViewGroup, android.view.View
    public void dispatchDraw(Canvas canvas) {
        Path path = new Path();
        RectF rectF = new RectF();
        rectF.set(0.0f, 0.0f, getWidth(), getHeight());
        int i2 = this.mRadius;
        path.addRoundRect(rectF, i2, i2, Path.Direction.CW);
        canvas.clipPath(path);
        super.dispatchDraw(canvas);
    }

    public int getRadius() {
        return this.mRadius;
    }

    @Override // android.widget.FrameLayout, android.view.View
    public void onMeasure(int i2, int i3) {
        super.onMeasure(i2, i3);
        int measuredWidth = getMeasuredWidth();
        int measuredHeight = getMeasuredHeight();
        if (measuredWidth % 2 == 1) {
            measuredWidth++;
        }
        if (measuredHeight % 2 == 1) {
            measuredHeight++;
        }
        setMeasuredDimension(measuredWidth, measuredHeight);
    }

    public void setNeedShowDialog(boolean z2) {
        this.mNeedShowDialog = z2;
    }

    public void setRadius(int i2) {
        this.mRadius = i2;
    }

    public RoundRectFrameLayout(Context context, @Nullable AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public RoundRectFrameLayout(Context context, @Nullable AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        this.mRadius = 0;
        this.mNeedShowDialog = true;
        this.mContext = context;
    }
}
