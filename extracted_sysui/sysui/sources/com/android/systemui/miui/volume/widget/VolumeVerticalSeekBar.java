package com.android.systemui.miui.volume.widget;

import android.content.Context;
import android.util.AttributeSet;
import miui.systemui.widget.VerticalSeekBar;

/* JADX INFO: loaded from: classes2.dex */
public class VolumeVerticalSeekBar extends VerticalSeekBar {
    public VolumeVerticalSeekBar(Context context) {
        super(context);
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

    public VolumeVerticalSeekBar(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public VolumeVerticalSeekBar(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
    }
}
