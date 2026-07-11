package com.android.systemui.miui.volume;

import android.content.Context;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.graphics.drawable.ScaleDrawable;
import android.util.Log;
import android.widget.SeekBar;
import android.widget.TextView;
import androidx.core.content.ContextCompat;
import com.android.systemui.miui.DrawableAnimators;
import com.android.systemui.miui.DrawableUtils;
import com.android.systemui.miui.volume.MiuiVolumeTimerSeekBar;
import com.android.systemui.miui.widget.CenterTextDrawable;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import miui.systemui.util.MiBlurCompat;

/* JADX INFO: loaded from: classes2.dex */
class MiuiVolumeTimerDrawableHelper implements MiuiVolumeTimerSeekBar.TimerSeekBarMotions {
    private static final int SEGMENT_TIME_FG_THRESHOLD = 1;
    private static final String TAG = "VolumeTimerDrawables";
    private Drawable mBackground;
    private Drawable mBackgroundSegments;
    private Drawable[] mBgDrawable;
    private LayerDrawable mBgLayer;
    private Context mContext;
    private int mCurrentSegment;
    private int mDeterminedSegment;
    private boolean mDragging;
    private Drawable mDrawable;
    private TextView mFakeTimeDrawableFg;
    private boolean mIsVerticalSeekBar;
    private LayerDrawable mLayer;
    private TickingTimeAboveProgressViewMotions mMotions;
    private Drawable mProgress;
    private Drawable mProgressDraggingRect;
    private Drawable mProgressNormalRect;
    private boolean mTicking;
    private CenterTextDrawable mTimeDrawableBg;
    private CenterTextDrawable mTimeDrawableFg;
    private String mTimeDrawableHint;
    private int mTimeRemain;
    private String[] mTimeSegmentTitle;
    private SeekBar mTimer;
    private List<Object> mTickingTimes = new ArrayList();
    private List<Object> mCountDownStates = new ArrayList();
    private boolean mState = true;
    private boolean mNeedShowDialog = true;

    public interface TickingTimeAboveProgressViewMotions {
        void addTimerProgressViewListener(TextView textView);

        void updateFakeTimeDrawableFgWidth(int i2, int i3);
    }

    public MiuiVolumeTimerDrawableHelper(SeekBar seekBar, boolean z2) {
        this.mContext = seekBar.getContext();
        this.mTimer = seekBar;
        this.mTimeDrawableHint = seekBar.getResources().getString(R.string.miui_dnd_count_down);
        initTimerString();
        this.mDrawable = seekBar.getProgressDrawable().mutate();
        this.mIsVerticalSeekBar = seekBar instanceof MiuiVerticalVolumeTimerSeekBar;
        this.mMotions = new MiuiVolumeSeekBarProgressView(this.mContext);
        if (this.mDrawable != null) {
            setupDrawables(seekBar.getContext(), z2);
            seekBar.setProgressDrawable(this.mDrawable);
            updateDrawables();
        }
    }

    private void addTextDrawables(Context context) {
        if (!(this.mDrawable instanceof LayerDrawable)) {
            Log.e(TAG, "progress drawable is not a LayerDrawable");
            return;
        }
        this.mBackgroundSegments = this.mContext.getResources().getDrawable(R.drawable.miui_volume_timer_segment_indicator, null);
        this.mLayer = (LayerDrawable) this.mDrawable;
        float dimension = context.getResources().getDimension(R.dimen.miui_volume_timer_time_text_size);
        Typeface typefaceCreate = Typeface.create("mipro-medium", 0);
        CenterTextDrawable centerTextDrawable = new CenterTextDrawable(this.mContext);
        this.mTimeDrawableBg = centerTextDrawable;
        centerTextDrawable.setTextSize(dimension);
        this.mTimeDrawableBg.setTextColor(context.getResources().getColor(R.color.miui_volume_tint_light_alpha));
        this.mTimeDrawableBg.setTypeface(typefaceCreate);
        this.mBgDrawable = new Drawable[]{this.mBackground, this.mTimeDrawableBg, this.mBackgroundSegments};
        LayerDrawable layerDrawable = new LayerDrawable(this.mBgDrawable);
        this.mBgLayer = layerDrawable;
        layerDrawable.setLayerHeight(this.mBgDrawable.length - 1, this.mContext.getResources().getDimensionPixelSize(R.dimen.miui_volume_segment_indicator_height));
        this.mBgLayer.setLayerGravity(this.mBgDrawable.length - 1, 17);
        this.mBgLayer.setLayerWidth(this.mBgDrawable.length - 1, this.mContext.getResources().getDimensionPixelSize((Util.sIsNotificationSingle || !this.mNeedShowDialog) ? R.dimen.miui_volume_segment_indicator_width_4stream : R.dimen.miui_volume_segment_indicator_width));
        this.mLayer.setDrawableByLayerId(android.R.id.background, this.mBgLayer);
        CenterTextDrawable centerTextDrawable2 = new CenterTextDrawable(this.mContext);
        this.mTimeDrawableFg = centerTextDrawable2;
        centerTextDrawable2.setTextSize(dimension);
        this.mTimeDrawableFg.setTextColor(ContextCompat.getColor(this.mContext, R.color.vp_o3_count_down_text_color_fg));
        this.mTimeDrawableFg.setTypeface(typefaceCreate);
        this.mLayer.setDrawableByLayerId(android.R.id.progress, new LayerDrawable(new Drawable[]{this.mProgress, new ScaleDrawable(this.mTimeDrawableFg, 8388611, 1.0f, 0.0f)}));
        this.mTickingTimes.add(this.mTimeDrawableFg);
        this.mTickingTimes.add(this.mTimeDrawableBg);
    }

    private String formatRemainTime(int i2) {
        int i3 = i2 / 60;
        return String.format(Locale.getDefault(), "%d:%02d:%02d", Integer.valueOf(i3 / 60), Integer.valueOf(i3 % 60), Integer.valueOf(i2 % 60));
    }

    private void initTimerString() {
        String[] strArr = new String[5];
        this.mTimeSegmentTitle = strArr;
        strArr[0] = this.mContext.getResources().getString(R.string.timer_off);
        this.mTimeSegmentTitle[1] = this.mContext.getResources().getString(R.string.timer_30_minutes, 30);
        this.mTimeSegmentTitle[2] = this.mContext.getResources().getString(R.string.timer_1_hour, 1);
        this.mTimeSegmentTitle[3] = this.mContext.getResources().getString(R.string.timer_2_hours, 2);
        this.mTimeSegmentTitle[4] = this.mContext.getResources().getString(R.string.timer_8_hours, 8);
    }

    private void setupDrawables(Context context, boolean z2) {
        this.mBackground = DrawableUtils.findDrawableById(this.mDrawable, android.R.id.background);
        Drawable drawableFindDrawableById = DrawableUtils.findDrawableById(this.mDrawable, android.R.id.progress);
        this.mProgress = drawableFindDrawableById;
        this.mProgressNormalRect = DrawableUtils.findDrawableById(drawableFindDrawableById, R.id.miui_volume_timer_progress_normal);
        this.mProgressDraggingRect = DrawableUtils.findDrawableById(this.mProgress, R.id.miui_volume_timer_progress_dragging_rect);
        if (z2) {
            addTextDrawables(context);
        }
    }

    private void updateCountDownStateText() {
        int iConstrain;
        if (this.mDragging && (iConstrain = Util.constrain(this.mDeterminedSegment, 0, this.mTimeSegmentTitle.length - 1)) != 0) {
            String str = this.mTimeSegmentTitle[iConstrain];
            for (Object obj : this.mCountDownStates) {
                if (obj instanceof CenterTextDrawable) {
                    ((CenterTextDrawable) obj).setText(str);
                } else if (obj instanceof TextView) {
                    TextView textView = (TextView) obj;
                    textView.setText(str);
                    textView.setFallbackLineSpacing(false);
                }
            }
        }
    }

    private void updateDrawables() {
        TextView textView;
        boolean z2 = false;
        DrawableAnimators.fade(this.mBackgroundSegments, this.mDragging || (this.mIsVerticalSeekBar && !this.mTicking));
        boolean z3 = this.mTicking;
        boolean z4 = z3 && !this.mDragging;
        boolean z5 = (z3 || this.mDragging) ? false : true;
        int i2 = this.mCurrentSegment;
        boolean z6 = i2 == 1 && this.mDeterminedSegment == 2;
        boolean z7 = z4 && (i2 > 1 || z6) && !this.mIsVerticalSeekBar;
        boolean z8 = (((!z4 || i2 > 1) && !z5) || z6 || this.mIsVerticalSeekBar) ? false : true;
        CenterTextDrawable centerTextDrawable = this.mTimeDrawableBg;
        if (centerTextDrawable != null) {
            DrawableAnimators.fade(centerTextDrawable, z8);
        }
        if (!MiBlurCompat.getBackgroundBlurOpenedInDefaultTheme(this.mContext) || (textView = this.mFakeTimeDrawableFg) == null) {
            CenterTextDrawable centerTextDrawable2 = this.mTimeDrawableFg;
            if (centerTextDrawable2 != null) {
                DrawableAnimators.fade(centerTextDrawable2, z7);
            }
        } else {
            Util.setVisOrGone(textView, (this.mTimer.getProgress() == 0 || !z7 || z8) ? false : true);
        }
        if (!z4) {
            Util.setVisOrInvis(this.mProgressNormalRect, false);
            Util.setVisOrInvis(this.mProgressDraggingRect, this.mDragging);
            return;
        }
        if (this.mTicking && !this.mDragging) {
            z2 = true;
        }
        Util.setVisOrInvis(this.mProgressNormalRect, z2);
        Util.setVisOrInvis(this.mProgressDraggingRect, !z2);
    }

    private void updateIndicatorWidth() {
        this.mBgLayer.setLayerWidth(this.mBgDrawable.length - 1, this.mContext.getResources().getDimensionPixelSize((Util.sIsNotificationSingle || !this.mNeedShowDialog) ? R.dimen.miui_volume_segment_indicator_width_4stream : R.dimen.miui_volume_segment_indicator_width));
    }

    private void updateTickingTimeText(int i2) {
        boolean z2 = (!this.mTicking || i2 <= 0) && !this.mDragging;
        String remainTime = z2 ? this.mTimeDrawableHint : formatRemainTime(i2);
        for (Object obj : this.mTickingTimes) {
            if (obj instanceof CenterTextDrawable) {
                ((CenterTextDrawable) obj).setText(remainTime, z2);
            } else if (obj instanceof TextView) {
                ((TextView) obj).setText(remainTime);
            }
        }
    }

    @Override // com.android.systemui.miui.volume.MiuiVolumeTimerSeekBar.TimerSeekBarMotions
    public void addCountDownStateReceiver(TextView textView) {
        this.mCountDownStates.add(textView);
    }

    @Override // com.android.systemui.miui.volume.MiuiVolumeTimerSeekBar.TimerSeekBarMotions
    public void addTickingTimeReceiver(TextView textView, boolean z2) {
        this.mTickingTimes.add(textView);
        if (z2) {
            this.mFakeTimeDrawableFg = textView;
        }
    }

    public String getCountDownStateText() {
        int iConstrain = Util.constrain(this.mDeterminedSegment, 0, this.mTimeSegmentTitle.length - 1);
        return iConstrain == 0 ? "" : this.mTimeSegmentTitle[iConstrain];
    }

    @Override // com.android.systemui.miui.volume.MiuiVolumeTimerSeekBar.TimerSeekBarMotions
    public void onSegmentChange(int i2, int i3) {
        if (this.mCurrentSegment != i2 || this.mDeterminedSegment != i3) {
            this.mCurrentSegment = i2;
            this.mDeterminedSegment = i3;
            updateDrawables();
        }
        if (this.mDragging) {
            updateCountDownStateText();
        }
    }

    @Override // com.android.systemui.miui.volume.MiuiVolumeTimerSeekBar.TimerSeekBarMotions
    public void onTimeUpdate(int i2) {
        this.mTimeRemain = i2;
        updateTickingTimeText(i2);
        boolean z2 = i2 > 0;
        if (this.mTicking != z2) {
            this.mTicking = z2;
            updateDrawables();
        }
    }

    @Override // com.android.systemui.miui.volume.MiuiVolumeTimerSeekBar.TimerSeekBarMotions
    public void onTouchDown() {
        this.mDragging = true;
        updateDrawables();
        updateCountDownStateText();
    }

    @Override // com.android.systemui.miui.volume.MiuiVolumeTimerSeekBar.TimerSeekBarMotions
    public void onTouchRelease() {
        this.mDragging = false;
        updateDrawables();
        updateTickingTimeText(this.mTimeRemain);
    }

    @Override // com.android.systemui.miui.volume.MiuiVolumeTimerSeekBar.TimerSeekBarMotions
    public void setNeedShowDialog(boolean z2) {
        if (this.mNeedShowDialog != z2) {
            this.mNeedShowDialog = z2;
            updateTimerDrawables();
        }
    }

    @Override // com.android.systemui.miui.volume.MiuiVolumeTimerSeekBar.TimerSeekBarMotions
    public void updateStates(boolean z2) {
        this.mState = z2;
    }

    @Override // com.android.systemui.miui.volume.MiuiVolumeTimerSeekBar.TimerSeekBarMotions
    public void updateTimerDrawables() {
        if (this.mDrawable != null) {
            updateIndicatorWidth();
            updateDrawables();
        }
    }
}
