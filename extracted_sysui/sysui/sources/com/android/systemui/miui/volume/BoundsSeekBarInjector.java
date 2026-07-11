package com.android.systemui.miui.volume;

import android.view.MotionEvent;
import android.widget.SeekBar;
import miui.systemui.widget.RelativeSeekBarInjector;

/* JADX INFO: loaded from: classes2.dex */
class BoundsSeekBarInjector extends RelativeSeekBarInjector {
    private float mBoundsEnd;
    private float mBoundsStart;
    private SeekBar mSeekBar;
    private float mTouchBoundsEnd;
    private float mTouchBoundsStart;
    private boolean mVertical;

    public BoundsSeekBarInjector(SeekBar seekBar, boolean z2) {
        super(seekBar, z2);
        this.mSeekBar = seekBar;
        this.mVertical = z2;
    }

    private void computeTouchOffset() {
        if (this.mVertical) {
            float height = (this.mSeekBar.getHeight() - this.mSeekBar.getPaddingTop()) - this.mSeekBar.getPaddingBottom();
            this.mTouchBoundsStart = this.mSeekBar.getPaddingTop() + ((1.0f - (this.mBoundsStart / this.mSeekBar.getMax())) * height);
            this.mTouchBoundsEnd = this.mSeekBar.getPaddingTop() + ((1.0f - (this.mBoundsEnd / this.mSeekBar.getMax())) * height);
        } else {
            float width = (this.mSeekBar.getWidth() - this.mSeekBar.getPaddingLeft()) - this.mSeekBar.getPaddingRight();
            this.mTouchBoundsStart = this.mSeekBar.getPaddingLeft() + ((this.mBoundsStart / this.mSeekBar.getMax()) * width);
            this.mTouchBoundsEnd = this.mSeekBar.getPaddingLeft() + ((this.mBoundsEnd / this.mSeekBar.getMax()) * width);
        }
    }

    public void setBounds(float f2, float f3) {
        this.mBoundsStart = f2;
        this.mBoundsEnd = f3;
    }

    @Override // miui.systemui.widget.RelativeSeekBarInjector
    public void setVertical(boolean z2) {
        super.setVertical(z2);
        this.mVertical = z2;
    }

    @Override // miui.systemui.widget.RelativeSeekBarInjector
    public void transformTouchEvent(MotionEvent motionEvent) {
        super.transformTouchEvent(motionEvent);
        if (motionEvent.getAction() == 0) {
            computeTouchOffset();
        }
        if (this.mVertical) {
            motionEvent.offsetLocation(0.0f, Util.constrain(motionEvent.getY(), Math.min(this.mTouchBoundsStart, this.mTouchBoundsEnd), Math.max(this.mTouchBoundsStart, this.mTouchBoundsEnd)) - motionEvent.getY());
        } else {
            motionEvent.offsetLocation(Util.constrain(motionEvent.getX(), Math.min(this.mTouchBoundsStart, this.mTouchBoundsEnd), Math.max(this.mTouchBoundsStart, this.mTouchBoundsEnd)) - motionEvent.getX(), 0.0f);
        }
    }
}
