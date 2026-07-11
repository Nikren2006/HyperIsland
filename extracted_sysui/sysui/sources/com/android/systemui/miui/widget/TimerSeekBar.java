package com.android.systemui.miui.widget;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.Log;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.Interpolator;
import android.widget.SeekBar;
import com.android.systemui.miui.volume.R;
import com.xiaomi.onetrack.util.aa;

/* JADX INFO: loaded from: classes2.dex */
public class TimerSeekBar extends SeekBar implements SeekBar.OnSeekBarChangeListener {
    public static final Interpolator DECELERATE_QUART = new DecelerateInterpolator(2.0f);
    private static final String TAG = "TimerSeekBar";
    private int mCurrentSegmentPoint;
    private int mDeterminedSegmentPoint;
    protected boolean mDragging;
    private SeekBar.OnSeekBarChangeListener mOnSeekBarChangeListener;
    private OnTimeUpdateListener mOnTimeUpdateListener;
    private int[] mTimeSegments;

    public interface OnTimeUpdateListener {
        void onSegmentChange(int i2, int i3);

        void onTimeSet(int i2);

        void onTimeUpdate(int i2);
    }

    public TimerSeekBar(Context context) {
        this(context, null);
    }

    private void animateToProgress(int i2) {
        ObjectAnimator objectAnimatorOfInt = ObjectAnimator.ofInt(this, "progress", i2);
        objectAnimatorOfInt.setDuration(300L);
        objectAnimatorOfInt.setAutoCancel(true);
        objectAnimatorOfInt.setInterpolator(DECELERATE_QUART);
        objectAnimatorOfInt.start();
    }

    private int determineProgressToSegment(int i2) {
        int max = getMax() / (this.mTimeSegments.length - 1);
        int i3 = i2 / max;
        return i2 < ((int) ((((double) i3) + 0.5d) * ((double) max))) ? i3 : i3 + 1;
    }

    private void setCurrentSegment(int i2, int i3) {
        if (i2 == this.mCurrentSegmentPoint && i3 == this.mDeterminedSegmentPoint) {
            return;
        }
        Log.d(TAG, "setCurrentSegment: " + i2 + aa.f3429b + this.mCurrentSegmentPoint + aa.f3429b + i3 + aa.f3429b + this.mDeterminedSegmentPoint);
        this.mCurrentSegmentPoint = i2;
        this.mDeterminedSegmentPoint = i3;
        onSegmentChange(i2, i3);
    }

    private void superSetOnSeekBarChangeListener(SeekBar.OnSeekBarChangeListener onSeekBarChangeListener) {
        super.setOnSeekBarChangeListener(onSeekBarChangeListener);
    }

    private int timeToProgress(int i2) {
        int i3;
        int[] iArr = this.mTimeSegments;
        int i4 = iArr[iArr.length - 1];
        if (i2 < 0) {
            i2 = 0;
        } else if (i2 > i4) {
            i2 = i4;
        }
        if (i2 == i4) {
            return getMax();
        }
        int i5 = 0;
        while (true) {
            int[] iArr2 = this.mTimeSegments;
            if (i5 > iArr2.length - 2) {
                return 0;
            }
            if (iArr2[i5] < i2 && (i3 = iArr2[i5 + 1]) >= i2) {
                return (int) ((getMax() / r1) * (i5 + ((i2 - r4) / (i3 - r4))));
            }
            i5++;
        }
    }

    public void AccessibilityActionSetTime(boolean z2) {
        Log.i(TAG, "AccessibilityActionSetTime direction:" + z2);
        int i2 = this.mDeterminedSegmentPoint + (z2 ? 1 : -1);
        if (i2 > -1 && i2 < this.mTimeSegments.length) {
            this.mDeterminedSegmentPoint = i2;
        }
        int i3 = this.mDeterminedSegmentPoint;
        setCurrentSegment(i3, i3);
        onTimeSet(this.mTimeSegments[this.mDeterminedSegmentPoint]);
    }

    @Override // android.widget.SeekBar.OnSeekBarChangeListener
    public void onProgressChanged(SeekBar seekBar, int i2, boolean z2) {
        setCurrentSegment(i2 / (getMax() / (this.mTimeSegments.length - 1)), determineProgressToSegment(i2));
        SeekBar.OnSeekBarChangeListener onSeekBarChangeListener = this.mOnSeekBarChangeListener;
        if (onSeekBarChangeListener != null) {
            onSeekBarChangeListener.onProgressChanged(seekBar, i2, z2);
        }
    }

    public void onSegmentChange(int i2, int i3) {
        OnTimeUpdateListener onTimeUpdateListener = this.mOnTimeUpdateListener;
        if (onTimeUpdateListener != null) {
            onTimeUpdateListener.onSegmentChange(i2, i3);
        }
    }

    public void onStartTrackingTouch(SeekBar seekBar) {
        this.mDragging = true;
        SeekBar.OnSeekBarChangeListener onSeekBarChangeListener = this.mOnSeekBarChangeListener;
        if (onSeekBarChangeListener != null) {
            onSeekBarChangeListener.onStartTrackingTouch(this);
        }
    }

    public void onStopTrackingTouch(SeekBar seekBar) {
        this.mDragging = false;
        int i2 = this.mDeterminedSegmentPoint;
        setCurrentSegment(i2, i2);
        animateToProgress((this.mCurrentSegmentPoint * (getMax() / (this.mTimeSegments.length - 1))) - 1);
        int i3 = this.mCurrentSegmentPoint;
        onTimeSet(i3 >= 0 ? this.mTimeSegments[i3] : 0);
        SeekBar.OnSeekBarChangeListener onSeekBarChangeListener = this.mOnSeekBarChangeListener;
        if (onSeekBarChangeListener != null) {
            onSeekBarChangeListener.onStopTrackingTouch(this);
        }
    }

    public void onTimeSet(int i2) {
        OnTimeUpdateListener onTimeUpdateListener = this.mOnTimeUpdateListener;
        if (onTimeUpdateListener != null) {
            onTimeUpdateListener.onTimeSet(i2);
        }
    }

    public void onTimeUpdate(int i2) {
        OnTimeUpdateListener onTimeUpdateListener = this.mOnTimeUpdateListener;
        if (onTimeUpdateListener != null) {
            onTimeUpdateListener.onTimeUpdate(i2);
        }
    }

    @Override // android.widget.SeekBar
    public void setOnSeekBarChangeListener(SeekBar.OnSeekBarChangeListener onSeekBarChangeListener) {
        this.mOnSeekBarChangeListener = onSeekBarChangeListener;
    }

    public void setOnTimeUpdateListener(OnTimeUpdateListener onTimeUpdateListener) {
        this.mOnTimeUpdateListener = onTimeUpdateListener;
    }

    public void setTimeSegments(int[] iArr) {
        this.mTimeSegments = iArr;
    }

    public void updateRemainTime(int i2) {
        if (this.mDragging) {
            return;
        }
        setProgress(timeToProgress(i2));
        onTimeUpdate(i2);
    }

    public TimerSeekBar(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public TimerSeekBar(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.TimerSeekBar, i2, 0);
        int resourceId = typedArrayObtainStyledAttributes.getResourceId(R.styleable.TimerSeekBar_timeSegments, 0);
        if (resourceId > 0) {
            this.mTimeSegments = getResources().getIntArray(resourceId);
        }
        typedArrayObtainStyledAttributes.recycle();
        superSetOnSeekBarChangeListener(this);
    }
}
