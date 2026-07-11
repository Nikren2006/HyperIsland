package miui.systemui.widget;

import android.view.MotionEvent;
import android.widget.SeekBar;

/* JADX INFO: loaded from: classes4.dex */
public class RelativeSeekBarInjector {
    private float mOffset;
    private final SeekBar mSeekBar;
    private boolean mVertical;

    public RelativeSeekBarInjector(SeekBar seekBar, boolean z2) {
        this.mSeekBar = seekBar;
        this.mVertical = z2;
    }

    private void computeTouchOffset(MotionEvent motionEvent) {
        float progress = this.mSeekBar.getProgress() / this.mSeekBar.getMax();
        if (this.mVertical) {
            this.mOffset = (this.mSeekBar.getPaddingTop() + ((1.0f - progress) * ((this.mSeekBar.getHeight() - this.mSeekBar.getPaddingTop()) - this.mSeekBar.getPaddingBottom()))) - motionEvent.getY();
        } else {
            this.mOffset = (this.mSeekBar.getPaddingStart() + (progress * ((this.mSeekBar.getWidth() - this.mSeekBar.getPaddingStart()) - this.mSeekBar.getPaddingEnd()))) - motionEvent.getX();
        }
    }

    public void setVertical(boolean z2) {
        this.mVertical = z2;
    }

    public void transformTouchEvent(MotionEvent motionEvent) {
        if (motionEvent.getAction() == 0) {
            computeTouchOffset(motionEvent);
        }
        boolean z2 = this.mVertical;
        motionEvent.offsetLocation(z2 ? 0.0f : this.mOffset, z2 ? this.mOffset : 0.0f);
    }
}
