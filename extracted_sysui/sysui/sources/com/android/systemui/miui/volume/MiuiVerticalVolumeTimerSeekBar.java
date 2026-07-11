package com.android.systemui.miui.volume;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.MotionEvent;

/* JADX INFO: loaded from: classes2.dex */
public class MiuiVerticalVolumeTimerSeekBar extends MiuiVolumeTimerSeekBar {
    public MiuiVerticalVolumeTimerSeekBar(Context context) {
        this(context, null);
    }

    private void drawProgress(Canvas canvas) {
        Drawable progressDrawable = getProgressDrawable();
        if (progressDrawable == null) {
            return;
        }
        canvas.save();
        canvas.rotate(-90.0f, getWidth() / 2, getHeight() / 2);
        int width = getWidth();
        int height = getHeight();
        int i2 = height - width;
        int i3 = (height + width) / 2;
        progressDrawable.setBounds(((-i2) / 2) + getPaddingBottom(), (i2 / 2) + getPaddingLeft(), i3 - getPaddingTop(), i3 - getPaddingRight());
        progressDrawable.draw(canvas);
        canvas.restore();
    }

    @Override // android.widget.AbsSeekBar, android.widget.ProgressBar, android.view.View
    public synchronized void onDraw(Canvas canvas) {
        drawProgress(canvas);
    }

    @Override // android.widget.AbsSeekBar, android.widget.ProgressBar, android.view.View
    public synchronized void onMeasure(int i2, int i3) {
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

    @Override // com.android.systemui.miui.volume.MiuiVolumeTimerSeekBar
    public void transformTouchEvent(MotionEvent motionEvent) {
        super.transformTouchEvent(motionEvent);
        motionEvent.setLocation(((((getHeight() - motionEvent.getY()) - getPaddingBottom()) / ((getHeight() - getPaddingTop()) - getPaddingBottom())) * ((getWidth() - getPaddingLeft()) - getPaddingRight())) + getPaddingLeft(), motionEvent.getY());
    }

    public MiuiVerticalVolumeTimerSeekBar(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public MiuiVerticalVolumeTimerSeekBar(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        setLayoutDirection(0);
        this.mInjector.setVertical(true);
    }
}
