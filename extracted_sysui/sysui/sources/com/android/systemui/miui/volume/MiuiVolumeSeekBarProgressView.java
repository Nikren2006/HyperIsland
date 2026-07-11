package com.android.systemui.miui.volume;

import android.content.Context;
import android.graphics.Outline;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewOutlineProvider;
import android.widget.SeekBar;
import android.widget.TextView;
import com.android.systemui.miui.volume.MiuiVolumeTimerDrawableHelper;
import miui.systemui.util.MiBlurCompat;
import miuix.animation.Folme;
import miuix.animation.IStateStyle;
import miuix.animation.base.AnimConfig;
import miuix.animation.property.FloatProperty;
import miuix.animation.utils.EaseManager;

/* JADX INFO: loaded from: classes2.dex */
public class MiuiVolumeSeekBarProgressView extends View implements MiuiVolumeTimerDrawableHelper.TickingTimeAboveProgressViewMotions {
    private static final int SEEKBAR_PROGRESS_VIEW_MULTIPLE = 100;
    private IStateStyle mAnim;
    private Context mContext;
    private float mCurrentLevel;
    private EaseManager.EaseStyle mEase;
    private TextView mFakeTimeDrawableFg;
    private int mHeight;
    private boolean mIsTimerProgress;
    private int mMaxLevel;
    private boolean mNeedShowDialog;
    private int mProgress;
    private int mProgressMax;
    private float mProgressRadius;
    private int mTimerProgressMaxWidth;
    private static final String TAG = "ProgressView";
    private static FloatProperty<MiuiVolumeSeekBarProgressView> PROGRESS = new FloatProperty<MiuiVolumeSeekBarProgressView>(TAG, 1.0f) { // from class: com.android.systemui.miui.volume.MiuiVolumeSeekBarProgressView.1
        @Override // miuix.animation.property.FloatProperty
        public float getValue(MiuiVolumeSeekBarProgressView miuiVolumeSeekBarProgressView) {
            if (miuiVolumeSeekBarProgressView != null) {
                return miuiVolumeSeekBarProgressView.getProgress();
            }
            return 0.0f;
        }

        @Override // miuix.animation.property.FloatProperty
        public void setValue(MiuiVolumeSeekBarProgressView miuiVolumeSeekBarProgressView, float f2) {
            if (miuiVolumeSeekBarProgressView != null) {
                miuiVolumeSeekBarProgressView.setProgress((int) f2);
            }
        }
    };

    public MiuiVolumeSeekBarProgressView(Context context) {
        this(context, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public int getProgress() {
        return this.mProgress;
    }

    private int levelToProgressHeight(float f2) {
        int i2 = this.mMaxLevel;
        if (i2 == 0) {
            return 0;
        }
        return (f2 != 0.0f ? f2 == ((float) i2) ? this.mHeight * 100 : (int) (((r4 * 100) / i2) * f2) : 0) / 100;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setProgress(int i2) {
        this.mProgress = i2;
        if (this.mIsTimerProgress) {
            setRoundRectTimerProgressHorizontal(i2);
        } else {
            setRoundRect(i2);
        }
    }

    private void setToProgress(int i2) {
        IStateStyle iStateStyle = this.mAnim;
        if (iStateStyle != null) {
            iStateStyle.cancel(PROGRESS);
            this.mAnim.setTo(PROGRESS, Integer.valueOf(i2));
        }
    }

    private void updateProgressHeight() {
        setToProgress(levelToProgressHeight(this.mCurrentLevel));
    }

    private float volumeLevelToProgressHeight(float f2) {
        int i2 = this.mMaxLevel;
        if (i2 == 0) {
            return 0.0f;
        }
        int i3 = this.mHeight;
        return ((int) (((f2 > 0.0f ? f2 >= ((float) i2) ? i3 * 100 : (((i3 * 100) * 1.0f) / i2) * f2 : 0.0f) / 100.0f) * 10000.0f)) / 10000.0f;
    }

    @Override // com.android.systemui.miui.volume.MiuiVolumeTimerDrawableHelper.TickingTimeAboveProgressViewMotions
    public void addTimerProgressViewListener(TextView textView) {
        this.mFakeTimeDrawableFg = textView;
    }

    public void frameUpdateProgressHeightForCenter() {
        this.mHeight = getHeight();
        setToProgress((int) Math.ceil(volumeLevelToProgressHeight(this.mCurrentLevel)));
    }

    public void frameUpdateProgressHeightForVolume() {
        this.mHeight = getHeight();
        updateProgressHeight();
    }

    public float getVolumeLevel() {
        return this.mCurrentLevel;
    }

    @Override // android.view.View
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

    public void setHeight(int i2) {
        if (i2 % 2 == 1) {
            i2++;
        }
        if (this.mHeight != i2) {
            this.mHeight = i2;
            updateProgressHeight();
        }
    }

    public void setMaxLevel(int i2) {
        this.mMaxLevel = i2;
    }

    public void setNeedShowDialog(boolean z2) {
        this.mNeedShowDialog = z2;
    }

    public void setRadius(float f2) {
        this.mProgressRadius = f2;
    }

    public void setRoundRect(final int i2) {
        setClipToOutline(true);
        setOutlineProvider(new ViewOutlineProvider() { // from class: com.android.systemui.miui.volume.MiuiVolumeSeekBarProgressView.2
            @Override // android.view.ViewOutlineProvider
            public void getOutline(View view, Outline outline) {
                outline.setRoundRect(0, view.getHeight() - i2, view.getWidth(), view.getHeight(), MiuiVolumeSeekBarProgressView.this.mProgressRadius);
            }
        });
    }

    public void setRoundRectTimerProgressHorizontal(final int i2) {
        setClipToOutline(true);
        setOutlineProvider(new ViewOutlineProvider(this) { // from class: com.android.systemui.miui.volume.MiuiVolumeSeekBarProgressView.3
            @Override // android.view.ViewOutlineProvider
            public void getOutline(View view, Outline outline) {
                outline.setRoundRect(0, 0, i2, view.getHeight(), 0.0f);
            }
        });
    }

    public void setRoundRectTimerProgressVertical(final int i2) {
        setClipToOutline(true);
        setOutlineProvider(new ViewOutlineProvider(this) { // from class: com.android.systemui.miui.volume.MiuiVolumeSeekBarProgressView.4
            @Override // android.view.ViewOutlineProvider
            public void getOutline(View view, Outline outline) {
                outline.setRoundRect(0, view.getHeight() - i2, view.getWidth(), view.getHeight(), 0.0f);
            }
        });
    }

    public void setTimerProgressMaxWidth(int i2) {
        int dimensionPixelSize = this.mNeedShowDialog ? Util.sIsNotificationSingle ? this.mContext.getResources().getDimensionPixelSize(R.dimen.miui_volume_timer_seekbar_width_4stream) : this.mContext.getResources().getDimensionPixelSize(R.dimen.miui_volume_timer_seekbar_width) : this.mContext.getResources().getDimensionPixelSize(R.dimen.miui_volume_timer_seekbar_width_cc);
        if (dimensionPixelSize % 2 == 1) {
            dimensionPixelSize++;
        }
        this.mProgressMax = dimensionPixelSize;
        this.mTimerProgressMaxWidth = i2;
        this.mIsTimerProgress = true;
    }

    public void setVolumeLevel(float f2) {
        this.mCurrentLevel = f2;
    }

    public void toProgressWithAnim(boolean z2, SeekBar seekBar) {
        if (MiBlurCompat.getBackgroundBlurOpenedInDefaultTheme(this.mContext)) {
            float progress = ((seekBar.getProgress() * 1.0f) / seekBar.getMax()) * this.mMaxLevel;
            this.mCurrentLevel = progress;
            int iLevelToProgressHeight = levelToProgressHeight(progress);
            if (!z2) {
                setToProgress(iLevelToProgressHeight);
                return;
            }
            IStateStyle iStateStyle = this.mAnim;
            if (iStateStyle != null) {
                iStateStyle.to(PROGRESS, Integer.valueOf(iLevelToProgressHeight), new AnimConfig().setEase(this.mEase));
            }
        }
    }

    @Override // com.android.systemui.miui.volume.MiuiVolumeTimerDrawableHelper.TickingTimeAboveProgressViewMotions
    public void updateFakeTimeDrawableFgWidth(int i2, int i3) {
        TextView textView = this.mFakeTimeDrawableFg;
        if (textView != null) {
            int i4 = (i2 * this.mProgressMax) / i3;
            ((ViewGroup.MarginLayoutParams) textView.getLayoutParams()).leftMargin = (i4 - this.mContext.getResources().getDimensionPixelSize(R.dimen.miui_volume_timer_ticking_text_width)) / 2;
        }
    }

    public void updateTimerProgressWidth(int i2, boolean z2) {
        IStateStyle iStateStyle;
        int i3 = (i2 * this.mProgressMax) / this.mTimerProgressMaxWidth;
        if (!z2 || (iStateStyle = this.mAnim) == null) {
            setToProgress(i3);
        } else {
            iStateStyle.to(PROGRESS, Integer.valueOf(i3));
        }
    }

    public MiuiVolumeSeekBarProgressView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public void updateProgressHeight(SeekBar seekBar) {
        float progress = ((seekBar.getProgress() * 1.0f) / seekBar.getMax()) * this.mMaxLevel;
        this.mCurrentLevel = progress;
        setToProgress(levelToProgressHeight(progress));
    }

    public MiuiVolumeSeekBarProgressView(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        this.mTimerProgressMaxWidth = 0;
        this.mIsTimerProgress = false;
        this.mHeight = 0;
        this.mProgressMax = 0;
        this.mMaxLevel = 0;
        this.mCurrentLevel = 0.0f;
        this.mProgress = 0;
        this.mNeedShowDialog = true;
        this.mProgressRadius = 0.0f;
        this.mEase = new EaseManager.EaseStyle(-2, 0.9f, 0.2f);
        this.mContext = context;
        this.mAnim = Folme.useValue(this);
        this.mProgressRadius = this.mContext.getResources().getDimensionPixelSize(R.dimen.miui_volume_seekbar_progress_radius);
    }
}
