package com.android.systemui.miui.volume;

import android.animation.ObjectAnimator;
import android.app.ActivityManager;
import android.app.ExtraNotificationManager;
import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.FrameLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import com.android.systemui.miui.volume.MiuiVolumeTimerSeekBar;
import com.android.systemui.miui.widget.TimerSeekBar;
import com.miui.maml.folme.AnimatedProperty;
import miui.systemui.util.MiBlurCompat;
import systemui.plugin.eventtracking.EventTracker;
import systemui.plugin.eventtracking.trackers.ControlsEventTracker;
import systemui.plugin.eventtracking.trackers.VolumeEventTracker;

/* JADX INFO: loaded from: classes2.dex */
public class TimerItem implements TimerSeekBar.OnTimeUpdateListener, MiuiVolumeTimerSeekBar.TimerSeekBarTouchListener {
    private static final int DURATION = 200;
    private static final int RECHECK_REMAIN_TIME_DELAY = 150;
    private static final String TAG = "TimerItem";
    private static final int ZEN_MODE_DND = 1;
    private Context mContext;
    private final RoundCornerProgressBar mCountDownProgressBar;
    private boolean mDragging;
    private boolean mExpanded;
    private boolean mIsFromUser;
    private boolean mIsZen;
    private int mMarginLeft;
    private View mParentLayout;
    private MiuiVolumeSeekBarProgressView mProgressView;
    private RoundRectFrameLayout mProgressViewBg;
    private boolean mShowing;
    private TextView mTickingTimeAboveProgressView;
    private MiuiVolumeTimerSeekBar mTimer;
    private View mTimerLayout;
    private FrameLayout mTimerTime;
    private int mWidth;
    private int mCurrentTimerMinitues = 0;
    private int mDeterminedSegment = 0;
    private Runnable mUpdateTimerRunnable = new Runnable() { // from class: com.android.systemui.miui.volume.q
        @Override // java.lang.Runnable
        public final void run() {
            this.f1505a.lambda$new$0();
        }
    };
    private boolean mState = false;
    private boolean mNeedShowDialog = true;
    private double lastTime = 0.0d;

    public TimerItem(Context context, View view, boolean z2, View view2) {
        this.mContext = context;
        this.mParentLayout = view2;
        RoundCornerProgressBar roundCornerProgressBar = (RoundCornerProgressBar) view.findViewById(R.id.count_down_progress);
        this.mCountDownProgressBar = roundCornerProgressBar;
        roundCornerProgressBar.setProgressColor(RingerButtonRes.getCountDownProgressColor(this.mContext, z2));
        View viewFindViewById = view.findViewById(R.id.timer_layout);
        this.mTimerLayout = viewFindViewById;
        this.mTimer = (MiuiVolumeTimerSeekBar) viewFindViewById.findViewById(R.id.timer_seekbar);
        this.mTickingTimeAboveProgressView = (TextView) this.mTimerLayout.findViewById(R.id.ticking_time_above_progress_view);
        this.mTimer.setOnTimeUpdateListener(this);
        this.mIsZen = z2;
        this.mTimer.addTickingTimeReceiver(this.mTickingTimeAboveProgressView, true);
        this.mTimer.addTimerSeekBarListener(this);
        FrameLayout frameLayout = (FrameLayout) this.mTimerLayout.findViewById(R.id.timer_bg);
        this.mTimerTime = frameLayout;
        this.mTimer.addCountDownStateReceiver((TextView) frameLayout.findViewById(R.id.timer_text));
        MiuiVolumeSeekBarProgressView miuiVolumeSeekBarProgressView = (MiuiVolumeSeekBarProgressView) this.mTimerLayout.findViewById(R.id.volume_timer_progress_view);
        this.mProgressView = miuiVolumeSeekBarProgressView;
        miuiVolumeSeekBarProgressView.addTimerProgressViewListener(this.mTickingTimeAboveProgressView);
        this.mProgressViewBg = (RoundRectFrameLayout) this.mTimerLayout.findViewById(R.id.volume_timer_view);
        updateTimerSeekbar();
        this.mTimer.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() { // from class: com.android.systemui.miui.volume.TimerItem.1
            @Override // android.widget.SeekBar.OnSeekBarChangeListener
            public void onProgressChanged(SeekBar seekBar, int i2, boolean z3) {
                if (z3 && TimerItem.this.mDragging) {
                    TimerItem.this.updateTimerTimePosition();
                    if (MiBlurCompat.getBackgroundBlurOpenedInDefaultTheme(TimerItem.this.mContext)) {
                        TimerItem.this.mProgressView.updateTimerProgressWidth(i2, TimerItem.this.mExpanded);
                    }
                }
            }

            @Override // android.widget.SeekBar.OnSeekBarChangeListener
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override // android.widget.SeekBar.OnSeekBarChangeListener
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });
    }

    private void fadeIn(View view, int i2) {
        ObjectAnimator objectAnimatorOfFloat = ObjectAnimator.ofFloat(view, "alpha", 0.0f, 1.0f);
        objectAnimatorOfFloat.setDuration(i2);
        objectAnimatorOfFloat.setInterpolator(Util.ACCELERATE_DECELERATE);
        objectAnimatorOfFloat.start();
    }

    private String getTimerStr(int i2) {
        if (i2 == 0) {
            return "0";
        }
        if (i2 < 60) {
            return i2 + "m";
        }
        return (i2 / 60) + AnimatedProperty.PROPERTY_NAME_H;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$onTouchRelease$1() {
        this.mIsFromUser = false;
    }

    private void recheckRemainTime() {
        this.mTimer.postDelayed(new Runnable() { // from class: com.android.systemui.miui.volume.TimerItem.2
            @Override // java.lang.Runnable
            public void run() {
                TimerItem.this.lambda$new$0();
            }
        }, 150L);
    }

    private void setRadius(int i2) {
        this.mProgressViewBg.setRadius(0);
        float f2 = i2;
        Util.setRoundRect(this.mProgressViewBg, f2);
        Util.setRoundRect(this.mTimer, f2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateTimerTimePosition() {
        this.mTimerTime.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() { // from class: com.android.systemui.miui.volume.TimerItem.3
            @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
            public void onGlobalLayout() {
                TimerItem.this.mTimerTime.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                float f2 = TimerItem.this.mWidth;
                TimerItem.this.mTimerTime.setTranslationX(Math.min(((f2 / 4.0f) * TimerItem.this.mDeterminedSegment) - (TimerItem.this.mTimerTime.getWidth() / 2), f2 - (TimerItem.this.mTimerTime.getWidth() / 2)));
            }
        });
    }

    @Override // com.android.systemui.miui.widget.TimerSeekBar.OnTimeUpdateListener
    public void onSegmentChange(int i2, int i3) {
        if (this.mDragging) {
            if (this.mDeterminedSegment != i3) {
                VolumeUtil.performHapticFeedback(this.mTimer, 268435461);
            }
            if (i3 < 1) {
                this.mTimerTime.setVisibility(8);
                return;
            }
            if (i3 == 1 && i2 == 0 && this.mTimerTime.getVisibility() == 8) {
                fadeIn(this.mTimerTime, 200);
                VolumeUtil.performHapticFeedback(this.mTimer, 268435461);
            }
            this.mTimerTime.setVisibility(0);
            if (MiBlurCompat.getBackgroundBlurOpenedInDefaultTheme(this.mContext)) {
                this.mProgressView.updateTimerProgressWidth(this.mTimer.getProgress(), this.mExpanded);
                this.mProgressView.updateFakeTimeDrawableFgWidth(this.mTimer.getProgress(), this.mTimer.getMax());
            }
        }
        this.mDeterminedSegment = i3;
    }

    @Override // com.android.systemui.miui.widget.TimerSeekBar.OnTimeUpdateListener
    public void onTimeSet(int i2) {
        Log.i(TAG, "onTimeSet Zen:" + this.mIsZen + ",time:" + i2 + ",currentUser:" + ActivityManager.getCurrentUser());
        Util.setLastTotalCountDownTime(this.mContext, i2, this.mIsZen);
        setupCountDownProgress();
        int i3 = i2 / 60;
        if (this.mIsZen) {
            VolumeUtil.setZenModeForDuration(this.mContext, 1, i3);
        } else {
            VolumeUtil.startCountDownSilenceMode(this.mContext, i3);
        }
        updateRemainTimeH(true, i2);
        if (this.mCurrentTimerMinitues != i3) {
            this.mCurrentTimerMinitues = i3;
            VolumeEventTracker.trackTimerDuration(getTimerStr(i3));
            ControlsEventTracker.trackControlCenterSecondaryVolumeTimerEvent(EventTracker.getScreenType(this.mContext), this.mIsZen, this.lastTime, this.mCurrentTimerMinitues);
        }
    }

    @Override // com.android.systemui.miui.widget.TimerSeekBar.OnTimeUpdateListener
    public void onTimeUpdate(int i2) {
        RoundCornerProgressBar roundCornerProgressBar = this.mCountDownProgressBar;
        if (roundCornerProgressBar != null) {
            roundCornerProgressBar.setProgress(i2);
        }
        if (MiBlurCompat.getBackgroundBlurOpenedInDefaultTheme(this.mContext)) {
            this.mProgressView.updateTimerProgressWidth(this.mTimer.getProgress(), this.mExpanded);
            this.mProgressView.updateFakeTimeDrawableFgWidth(this.mTimer.getProgress(), this.mTimer.getMax());
        }
    }

    @Override // com.android.systemui.miui.volume.MiuiVolumeTimerSeekBar.TimerSeekBarTouchListener
    public void onTouchDown() {
        this.mDragging = true;
        this.mIsFromUser = true;
    }

    @Override // com.android.systemui.miui.volume.MiuiVolumeTimerSeekBar.TimerSeekBarTouchListener
    public void onTouchRelease() {
        this.mDragging = false;
        this.mTimer.postDelayed(new Runnable() { // from class: com.android.systemui.miui.volume.p
            @Override // java.lang.Runnable
            public final void run() {
                this.f1504a.lambda$onTouchRelease$1();
            }
        }, 200L);
        this.mTimerTime.setVisibility(8);
    }

    public void scheduleTimerUpdateH(boolean z2) {
        this.mTimer.removeCallbacks(this.mUpdateTimerRunnable);
        if (z2 && this.mShowing) {
            this.mTimer.postDelayed(this.mUpdateTimerRunnable, 1000L);
        }
    }

    public void setNeedShowDialog(boolean z2) {
        if (this.mNeedShowDialog != z2) {
            this.mNeedShowDialog = z2;
            this.mTimer.setNeedShowDialog(z2);
            updateTimerSeekbar();
        }
    }

    public void setupCountDownProgress() {
        if (this.mCountDownProgressBar != null) {
            int lastTotalCountDownTime = Util.getLastTotalCountDownTime(this.mContext, this.mIsZen);
            Log.d(TAG, "setupCountDownProgress zen:" + this.mIsZen + ",max:" + lastTotalCountDownTime + ",currentUser:" + ActivityManager.getCurrentUser());
            this.mCountDownProgressBar.setMax(lastTotalCountDownTime);
            this.mCountDownProgressBar.setProgress(this.mTimer.getRemainTime());
        }
    }

    public void updateCountProgressH(boolean z2) {
        if (!this.mIsZen && !z2 && !this.mIsFromUser) {
            VolumeUtil.setSilenceRemainTime(this.mContext, 0L);
        }
        Util.setVisOrGone(this.mCountDownProgressBar, !this.mExpanded && z2 && this.mTimer.getRemainTime() > 0);
        this.mTimer.updateStates(z2);
        if (z2) {
            return;
        }
        recheckRemainTime();
    }

    public void updateExpanded(boolean z2) {
        this.mExpanded = z2;
        if (z2) {
            lambda$new$0();
        } else {
            scheduleTimerUpdateH(z2);
        }
        this.mTimerLayout.setVisibility(z2 ? 0 : 8);
    }

    /* JADX INFO: renamed from: updateRemainTimeH, reason: merged with bridge method [inline-methods] */
    public void lambda$new$0() {
        updateRemainTimeH(false, 0);
    }

    public void updateShowingState(boolean z2) {
        this.mShowing = z2;
        lambda$new$0();
    }

    public void updateTimerSeekbar() {
        this.mWidth = TimerItemRes.getWidth(this.mContext, this.mNeedShowDialog, Util.sIsNotificationSingle);
        this.mMarginLeft = TimerItemRes.getMarginLeft(this.mContext, this.mNeedShowDialog);
        int height = TimerItemRes.getHeight(this.mContext, this.mNeedShowDialog);
        ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) this.mTimer.getLayoutParams();
        marginLayoutParams.width = this.mWidth;
        marginLayoutParams.height = height;
        marginLayoutParams.leftMargin = this.mMarginLeft;
        ViewGroup.MarginLayoutParams marginLayoutParams2 = (ViewGroup.MarginLayoutParams) this.mProgressViewBg.getLayoutParams();
        marginLayoutParams2.height = height;
        marginLayoutParams2.width = this.mWidth;
        marginLayoutParams2.leftMargin = this.mMarginLeft;
        ((ViewGroup.MarginLayoutParams) this.mTimerLayout.getLayoutParams()).height = height;
        this.mProgressViewBg.setNeedShowDialog(this.mNeedShowDialog);
        this.mProgressView.setNeedShowDialog(this.mNeedShowDialog);
        setRadius(TimerItemRes.getRadius(this.mContext, this.mNeedShowDialog));
        this.mProgressView.setTimerProgressMaxWidth(this.mTimer.getMax());
        this.mProgressView.updateTimerProgressWidth(this.mTimer.getProgress(), this.mExpanded);
        this.mProgressView.updateFakeTimeDrawableFgWidth(this.mTimer.getProgress(), this.mTimer.getMax());
        if (MiBlurCompat.getBackgroundBlurOpenedInDefaultTheme(this.mContext)) {
            Util.setMiViewBlurAndBlendColor(this.mTimer, 1, TimerItemRes.getTimerBlendColor(this.mNeedShowDialog));
            Util.setMiViewBlurAndBlendColor(this.mProgressView, 1, TimerItemRes.getProgressBlendColor(this.mNeedShowDialog));
        } else {
            this.mTimer.setBackgroundResource(TimerItemRes.getTimerBgRes(this.mNeedShowDialog));
        }
        this.mTimer.updateTimerDrawables();
    }

    public void updateRemainTimeH(boolean z2, int i2) {
        long remainTime = i2;
        if (!z2) {
            remainTime = (this.mIsZen ? ExtraNotificationManager.getRemainTime(this.mContext) : VolumeUtil.caculateSilenceRemainTime(this.mContext)) / 1000;
        }
        this.mTimer.updateRemainTime((int) remainTime);
        scheduleTimerUpdateH(remainTime > 0 || z2);
    }
}
