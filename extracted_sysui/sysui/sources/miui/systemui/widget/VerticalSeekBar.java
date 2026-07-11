package miui.systemui.widget;

import android.R;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.SeekBar;

/* JADX INFO: loaded from: classes4.dex */
public class VerticalSeekBar extends SeekBar {
    private final Rect mBounds;

    public VerticalSeekBar(Context context) {
        this(context, null);
    }

    private void drawProgress(Canvas canvas) {
        Drawable progressDrawable = getProgressDrawable();
        if (progressDrawable == null) {
            return;
        }
        canvas.save();
        canvas.rotate(-90.0f);
        progressDrawable.setBounds((-getHeight()) + getPaddingBottom(), getPaddingLeft(), -getPaddingTop(), getWidth() - getPaddingRight());
        progressDrawable.draw(canvas);
        canvas.restore();
    }

    private void drawThumb(Canvas canvas) {
        Drawable thumb = getThumb();
        if (thumb == null) {
            return;
        }
        thumb.copyBounds(this.mBounds);
        int intrinsicWidth = thumb.getIntrinsicWidth();
        int intrinsicHeight = thumb.getIntrinsicHeight();
        int width = (getWidth() - getPaddingLeft()) - getPaddingRight();
        int height = (getHeight() - getPaddingTop()) - getPaddingBottom();
        int thumbOffset = getThumbOffset();
        int i2 = (height - intrinsicHeight) + (thumbOffset * 2);
        int max = getMax();
        int iCalcTop = VerticalSeekBarHelper.calcTop(max > 0 ? 1.0f - (getProgress() / max) : 0.0f, i2, getPaddingTop(), thumbOffset);
        int paddingLeft = ((width - intrinsicWidth) / 2) + getPaddingLeft();
        thumb.setBounds(paddingLeft, iCalcTop, intrinsicWidth + paddingLeft, intrinsicHeight + iCalcTop);
        thumb.draw(canvas);
        thumb.setBounds(this.mBounds);
    }

    @Override // android.widget.AbsSeekBar, android.widget.ProgressBar, android.view.View
    public synchronized void onDraw(Canvas canvas) {
        drawProgress(canvas);
        drawThumb(canvas);
    }

    @Override // android.widget.AbsSeekBar, android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        motionEvent.setLocation(((((getHeight() - motionEvent.getY()) - getPaddingBottom()) / ((getHeight() - getPaddingTop()) - getPaddingBottom())) * ((getWidth() - getPaddingLeft()) - getPaddingRight())) + getPaddingLeft(), motionEvent.getY());
        return super.onTouchEvent(motionEvent);
    }

    public VerticalSeekBar(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, R.attr.seekBarStyle);
    }

    public VerticalSeekBar(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        this.mBounds = new Rect();
        setLayoutDirection(0);
    }
}
