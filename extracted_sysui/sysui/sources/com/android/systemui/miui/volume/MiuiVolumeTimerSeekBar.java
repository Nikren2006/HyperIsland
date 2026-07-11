package com.android.systemui.miui.volume;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.accessibility.AccessibilityNodeInfo;
import android.widget.SeekBar;
import android.widget.TextView;
import com.android.systemui.miui.widget.TimerSeekBar;
import java.lang.reflect.Method;
import miui.systemui.util.MiBlurCompat;

/* JADX INFO: loaded from: classes2.dex */
public class MiuiVolumeTimerSeekBar extends TimerSeekBar {
    private int mBoundsStart;
    private int mCurrentSegment;
    private TimerSeekBarTouchListener mDragListener;
    protected BoundsSeekBarInjector mInjector;
    private TimerSeekBarMotions mMotions;
    private int mTimeRemain;

    public interface TimerSeekBarMotions {
        void addCountDownStateReceiver(TextView textView);

        void addTickingTimeReceiver(TextView textView, boolean z2);

        void onSegmentChange(int i2, int i3);

        void onTimeUpdate(int i2);

        void onTouchDown();

        void onTouchRelease();

        void setNeedShowDialog(boolean z2);

        void updateStates(boolean z2);

        void updateTimerDrawables();
    }

    public interface TimerSeekBarTouchListener {
        void onTouchDown();

        void onTouchRelease();
    }

    public MiuiVolumeTimerSeekBar(Context context) {
        this(context, null);
    }

    private int constrainProgress(int i2) {
        return Util.constrain(i2, this.mBoundsStart, getMax());
    }

    private String formatRemainTime(int i2) {
        int i3 = i2 / 60;
        int i4 = i3 / 60;
        int i5 = i3 % 60;
        int i6 = i2 % 60;
        Resources resources = getContext().getResources();
        StringBuilder sb = new StringBuilder();
        if (i4 > 0) {
            sb.append(i4);
            sb.append(resources.getString(R.string.accessibility_description_hours));
        }
        if (i5 > 0) {
            sb.append(i5);
            sb.append(resources.getString(R.string.accessibility_description_minutes));
        }
        if (i6 > 0) {
            sb.append(i6);
            sb.append(resources.getString(R.string.accessibility_description_seconds));
        }
        return sb.toString();
    }

    public void addCountDownStateReceiver(TextView textView) {
        this.mMotions.addCountDownStateReceiver(textView);
    }

    public void addTickingTimeReceiver(TextView textView, boolean z2) {
        this.mMotions.addTickingTimeReceiver(textView, z2);
    }

    public void addTimerSeekBarListener(TimerSeekBarTouchListener timerSeekBarTouchListener) {
        this.mDragListener = timerSeekBarTouchListener;
    }

    public int getRemainTime() {
        return this.mTimeRemain;
    }

    @Override // android.view.View
    public void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo accessibilityNodeInfo) {
        super.onInitializeAccessibilityNodeInfo(accessibilityNodeInfo);
        try {
            Method declaredMethod = accessibilityNodeInfo.getClass().getDeclaredMethod("setStateDescription", CharSequence.class);
            String countDownStateText = this.mDragging ? ((MiuiVolumeTimerDrawableHelper) this.mMotions).getCountDownStateText() : formatRemainTime(this.mTimeRemain);
            if (countDownStateText.isEmpty()) {
                countDownStateText = "0";
            }
            declaredMethod.invoke(accessibilityNodeInfo, countDownStateText);
        } catch (Exception e2) {
            Log.e("TimerSeekBar", "onInitializeAccessibilityNodeInfo error" + e2);
        }
        accessibilityNodeInfo.setContentDescription(getContext().getString(R.string.miui_dnd_count_down));
    }

    @Override // android.view.View
    public void onLayout(boolean z2, int i2, int i3, int i4, int i5) {
        super.onLayout(z2, i2, i3, i4, i5);
        getProgressDrawable().setBounds(0, 0, getWidth(), getHeight());
    }

    @Override // com.android.systemui.miui.widget.TimerSeekBar
    public void onSegmentChange(int i2, int i3) {
        super.onSegmentChange(i2, i3);
        this.mCurrentSegment = i2;
        this.mMotions.onSegmentChange(i2, i3);
    }

    @Override // com.android.systemui.miui.widget.TimerSeekBar, android.widget.SeekBar.OnSeekBarChangeListener
    public void onStartTrackingTouch(SeekBar seekBar) {
        super.onStartTrackingTouch(seekBar);
        this.mMotions.onTouchDown();
        TimerSeekBarTouchListener timerSeekBarTouchListener = this.mDragListener;
        if (timerSeekBarTouchListener != null) {
            timerSeekBarTouchListener.onTouchDown();
        }
    }

    @Override // com.android.systemui.miui.widget.TimerSeekBar, android.widget.SeekBar.OnSeekBarChangeListener
    public void onStopTrackingTouch(SeekBar seekBar) {
        super.onStopTrackingTouch(seekBar);
        this.mMotions.onTouchRelease();
        TimerSeekBarTouchListener timerSeekBarTouchListener = this.mDragListener;
        if (timerSeekBarTouchListener != null) {
            timerSeekBarTouchListener.onTouchRelease();
        }
    }

    @Override // com.android.systemui.miui.widget.TimerSeekBar
    public void onTimeSet(int i2) {
        super.onTimeSet(i2);
        this.mTimeRemain = i2;
        this.mMotions.onTimeUpdate(i2);
    }

    @Override // com.android.systemui.miui.widget.TimerSeekBar
    public void onTimeUpdate(int i2) {
        super.onTimeUpdate(i2);
        this.mTimeRemain = i2;
        this.mMotions.onTimeUpdate(i2);
    }

    @Override // android.widget.AbsSeekBar, android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        transformTouchEvent(motionEvent);
        return super.onTouchEvent(motionEvent);
    }

    @Override // android.view.View
    public boolean performAccessibilityAction(int i2, Bundle bundle) {
        if (i2 == 4096) {
            AccessibilityActionSetTime(true);
        } else if (i2 == 8192) {
            AccessibilityActionSetTime(false);
        }
        return super.performAccessibilityAction(i2, bundle);
    }

    @Override // android.widget.AbsSeekBar, android.widget.ProgressBar
    public synchronized void setMax(int i2) {
        super.setMax(i2);
        BoundsSeekBarInjector boundsSeekBarInjector = this.mInjector;
        if (boundsSeekBarInjector != null) {
            boundsSeekBarInjector.setBounds(this.mBoundsStart, i2);
        }
    }

    public void setNeedShowDialog(boolean z2) {
        this.mMotions.setNeedShowDialog(z2);
    }

    @Override // android.widget.ProgressBar
    public synchronized void setProgress(int i2) {
        super.setProgress(constrainProgress(i2));
    }

    public void transformTouchEvent(MotionEvent motionEvent) {
        this.mInjector.transformTouchEvent(motionEvent);
    }

    public void updateStates(boolean z2) {
        this.mMotions.updateStates(z2);
    }

    public void updateTimerDrawables() {
        this.mMotions.updateTimerDrawables();
    }

    public MiuiVolumeTimerSeekBar(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public MiuiVolumeTimerSeekBar(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.MiuiVolumeTimerSeekBar, i2, 0);
        this.mBoundsStart = typedArrayObtainStyledAttributes.getInt(R.styleable.MiuiVolumeTimerSeekBar_progressBoundsStart, 0);
        boolean z2 = typedArrayObtainStyledAttributes.getBoolean(R.styleable.MiuiVolumeTimerSeekBar_drawTickingTime, true);
        typedArrayObtainStyledAttributes.recycle();
        if (MiBlurCompat.getBackgroundBlurOpenedInDefaultTheme(context)) {
            setProgressDrawable(context.getDrawable(R.drawable.miui_volume_timer_progress_drawable_transparent));
        }
        this.mMotions = new MiuiVolumeTimerDrawableHelper(this, z2);
        BoundsSeekBarInjector boundsSeekBarInjector = new BoundsSeekBarInjector(this, false);
        this.mInjector = boundsSeekBarInjector;
        boundsSeekBarInjector.setBounds(this.mBoundsStart, getMax());
    }
}
