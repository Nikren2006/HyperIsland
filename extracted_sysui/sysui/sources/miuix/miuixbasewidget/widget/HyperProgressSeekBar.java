package miuix.miuixbasewidget.widget;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RuntimeShader;
import android.graphics.Shader;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.LayerDrawable;
import android.util.AttributeSet;
import android.view.ViewConfiguration;
import android.widget.SeekBar;
import androidx.annotation.ColorInt;
import androidx.annotation.ColorRes;
import androidx.annotation.IntegerRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatSeekBar;
import androidx.core.view.ViewCompat;
import java.lang.ref.WeakReference;
import java.util.Scanner;
import miuix.animation.Folme;
import miuix.animation.FolmeObject;
import miuix.animation.property.IntValueProperty;
import miuix.core.util.MiuixUIUtils;
import miuix.device.DeviceUtils;
import miuix.miuixbasewidget.R;
import miuix.util.HapticFeedbackCompat;

/* JADX INFO: loaded from: classes.dex */
public class HyperProgressSeekBar extends AppCompatSeekBar {
    public static final int DEVICE_HIGHEND = 2;
    public static final int DEVICE_MIDDLE = 1;
    public static final int DEVICE_PRIMARY = 0;
    public static final int DEVICE_UNKNOWN = -1;
    private static final int NO_ALPHA = 255;
    private BitmapShader bitmapShader;
    private int initialProgress;
    private float initialX;
    private Drawable mBackgroundDrawable;
    private int mBackgroundPrimaryColor;
    private int mBackgroundPrimaryDisableColor;
    private int mDefaultBackgroundPrimaryColor;
    private int mDefaultBackgroundPrimaryDisableColor;
    private int mDefaultForegroundPrimaryColor;
    private int mDefaultForegroundPrimaryDisableColor;
    private int mDeviceLevel;
    private int mDrawProgressAlpha;
    private int mForegroundPrimaryColor;
    private int mForegroundPrimaryDisableColor;
    private ProgressAnimTarget mHeadAlphaAnimator;
    private boolean mIsDragging;
    private boolean mIsProgressChangedInternal;
    private boolean mIsTracking;
    private Drawable mLayerDrawable;
    private SeekBar.OnSeekBarChangeListener mOnSeekBarChangeListener;
    private Paint mPaint;
    private int mProgressAlpha;
    private ProgressAnimTarget mProgressAlphaAnimator;
    private ColorStateList mProgressColorStateList;
    private Drawable mProgressDrawable;
    private int mProgressHeight;
    private ProgressAnimTarget mProgressHeightAnimator;
    private int mProgressMode;
    private int mProgressPaddingOffset;
    private int mProgressPressedAlpha;
    private int mScaledTouchSlop;
    private float mTouchDownX;
    private final SeekBar.OnSeekBarChangeListener mTrainsOnSeekBarChangeListener;
    private float progress;
    private RuntimeShader runtimeShader;
    private float uHeadGlowAlpha;
    private float[] uHeadSize;
    private float[] uTrackCanvasSize;
    private float[] uTrackPosition;
    private float[] uTrackSize;
    private static final IntValueProperty PROPERTY_PROGRESS_HEIGHT = new IntValueProperty("progressHeight", 0.001f);
    private static final IntValueProperty PROPERTY_HEAD_ALPHA = new IntValueProperty("headAlpha", 0.1f);
    private static final IntValueProperty PROPERTY_PROGRESS_ALPHA = new IntValueProperty("progressAlpha", 0.1f);

    public static class ColorUpdateRunner implements Runnable {
        private WeakReference<HyperProgressSeekBar> mSeekBarRef;

        public ColorUpdateRunner(HyperProgressSeekBar hyperProgressSeekBar) {
            this.mSeekBarRef = new WeakReference<>(hyperProgressSeekBar);
        }

        @Override // java.lang.Runnable
        public void run() {
            WeakReference<HyperProgressSeekBar> weakReference = this.mSeekBarRef;
            HyperProgressSeekBar hyperProgressSeekBar = weakReference == null ? null : weakReference.get();
            if (hyperProgressSeekBar != null) {
                hyperProgressSeekBar.updatePrimaryColor();
            }
        }
    }

    public static class ProgressAnimTarget implements FolmeObject {
        private Folme.ObjectFolmeImpl mFolmeImpl;

        private ProgressAnimTarget() {
        }

        @Override // miuix.animation.FolmeObject
        public Folme.ObjectFolmeImpl folme() {
            return this.mFolmeImpl;
        }

        @Override // miuix.animation.FolmeObject
        public void setFolmeImpl(Folme.ObjectFolmeImpl objectFolmeImpl) {
            this.mFolmeImpl = objectFolmeImpl;
        }
    }

    public HyperProgressSeekBar(@NonNull Context context) {
        this(context, null);
    }

    private void drawHighEndMode(Canvas canvas) {
        float[] fArr = this.uTrackSize;
        fArr[1] = this.mProgressHeight;
        this.runtimeShader.setFloatUniform("uTrackSize", fArr);
        this.runtimeShader.setFloatUniform("uTrackProgress", (getProgress() - getMinWrapper()) / (getMax() - getMinWrapper()));
        float[] fArr2 = this.uTrackCanvasSize;
        canvas.drawRect(0.0f, 0.0f, fArr2[0], fArr2[1], this.mPaint);
    }

    private void drawMiddleMode(Canvas canvas) {
        drawPrimaryMode(canvas);
        drawHighEndMode(canvas);
    }

    private void drawPrimaryMode(Canvas canvas) {
        float progress;
        float width = getWidth();
        float maxHeight = getMaxHeight();
        float paddingTop = getPaddingTop();
        float f2 = paddingTop + maxHeight;
        int minWrapper = getMinWrapper();
        if (isLayoutRtl()) {
            progress = ((width - this.mProgressPaddingOffset) - (maxHeight / 2.0f)) - (((getProgress() - minWrapper) / (getMax() - minWrapper)) * ((width - (this.mProgressPaddingOffset * 2)) - maxHeight));
        } else {
            float progress2 = (getProgress() - minWrapper) / (getMax() - minWrapper);
            progress = (progress2 * ((width - (r4 * 2)) - maxHeight)) + this.mProgressPaddingOffset + (maxHeight / 2.0f);
        }
        Drawable drawable = this.mBackgroundDrawable;
        if (drawable == null && this.mProgressDrawable == null) {
            return;
        }
        if (drawable != null) {
            drawable.setAlpha(255);
            Drawable drawable2 = this.mBackgroundDrawable;
            int i2 = this.mProgressPaddingOffset;
            drawable2.setBounds(i2, (int) paddingTop, (int) (width - i2), (int) f2);
            this.mBackgroundDrawable.draw(canvas);
        }
        if (this.mProgressDrawable != null) {
            if (isLayoutRtl()) {
                this.mProgressDrawable.setBounds((int) (progress - (maxHeight / 2.0f)), (int) paddingTop, (int) (width - this.mProgressPaddingOffset), (int) f2);
            } else {
                this.mProgressDrawable.setBounds(this.mProgressPaddingOffset, (int) paddingTop, (int) (progress + (maxHeight / 2.0f)), (int) f2);
            }
            this.mProgressDrawable.setAlpha(this.mDrawProgressAlpha);
            this.mProgressDrawable.draw(canvas);
        }
    }

    private int getMinWrapper() {
        return super.getMin();
    }

    private void init(Context context) {
        Paint paint = new Paint();
        this.mPaint = paint;
        paint.setAntiAlias(true);
        this.mScaledTouchSlop = ViewConfiguration.get(context).getScaledTouchSlop();
        ProgressAnimTarget progressAnimTarget = new ProgressAnimTarget();
        this.mProgressHeightAnimator = progressAnimTarget;
        Folme.use((FolmeObject) progressAnimTarget);
        this.mProgressHeight = getResources().getDimensionPixelSize(R.dimen.miuix_appcompat_hyper_progress_seekbar_min_height);
        this.mProgressHeightAnimator.folme().setTo(PROPERTY_PROGRESS_HEIGHT, Integer.valueOf(this.mProgressHeight));
        setOnSeekBarChangeListener(this.mTrainsOnSeekBarChangeListener);
    }

    private void initForHighEndDevice() {
        this.runtimeShader = new RuntimeShader(loadShader(getContext().getResources(), R.raw.music_player_tracker));
        initShaderConfig();
    }

    private void initForMiddleDevice() {
        initForPrimaryDevice();
        this.runtimeShader = new RuntimeShader(loadShader(getContext().getResources(), R.raw.music_player_tracker_middle));
        initShaderConfig();
    }

    private void initForPrimaryDevice() {
        if (getProgressDrawable() != null) {
            Drawable progressDrawable = getProgressDrawable();
            this.mLayerDrawable = progressDrawable;
            if (progressDrawable instanceof LayerDrawable) {
                LayerDrawable layerDrawable = (LayerDrawable) progressDrawable;
                this.mBackgroundDrawable = layerDrawable.findDrawableByLayerId(android.R.id.background);
                this.mProgressDrawable = layerDrawable.findDrawableByLayerId(android.R.id.progress);
            }
        }
    }

    private void initShaderConfig() {
        Bitmap bitmapDecodeResource = BitmapFactory.decodeResource(getContext().getResources(), R.drawable.miuix_hyper_progressbar_light_head);
        this.uHeadSize[0] = bitmapDecodeResource.getWidth();
        this.uHeadSize[1] = bitmapDecodeResource.getHeight();
        Shader.TileMode tileMode = Shader.TileMode.CLAMP;
        this.bitmapShader = new BitmapShader(bitmapDecodeResource, tileMode, tileMode);
        this.mPaint.setShader(this.runtimeShader);
        this.runtimeShader.setInputShader("uTex", this.bitmapShader);
        this.runtimeShader.setFloatUniform("uHeadSize", this.uHeadSize);
        this.runtimeShader.setFloatUniform("uHeadGlowAlpha", this.uHeadGlowAlpha);
        this.uTrackPosition[0] = MiuixUIUtils.dp2px(getContext(), (int) this.uTrackPosition[0]);
        this.uTrackPosition[1] = MiuixUIUtils.dp2px(getContext(), (int) this.uTrackPosition[1]);
        this.uTrackSize[0] = MiuixUIUtils.dp2px(getContext(), (int) this.uTrackSize[0]);
        this.uTrackSize[1] = MiuixUIUtils.dp2px(getContext(), (int) this.uTrackSize[1]);
        this.runtimeShader.setFloatUniform("uTrackPosition", this.uTrackPosition);
        this.runtimeShader.setFloatUniform("uTrackSize", this.uTrackSize);
    }

    private void initializeDeviceLevel() {
        int i2 = this.mProgressMode;
        if (i2 != -1) {
            this.mDeviceLevel = i2;
            return;
        }
        int deviceLevel = DeviceUtils.getDeviceLevel();
        this.mDeviceLevel = deviceLevel;
        if (deviceLevel == -1) {
            this.mDeviceLevel = 0;
        }
    }

    private boolean isLayoutRtl() {
        return ViewCompat.getLayoutDirection(this) == 1;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updatePrimaryColor() {
        Drawable progressDrawable = getProgressDrawable();
        if (progressDrawable instanceof LayerDrawable) {
            LayerDrawable layerDrawable = (LayerDrawable) progressDrawable;
            Drawable drawableFindDrawableByLayerId = layerDrawable.findDrawableByLayerId(android.R.id.progress);
            Drawable drawableFindDrawableByLayerId2 = layerDrawable.findDrawableByLayerId(android.R.id.background);
            if (drawableFindDrawableByLayerId != null && (drawableFindDrawableByLayerId instanceof GradientDrawable)) {
                GradientDrawable gradientDrawable = (GradientDrawable) drawableFindDrawableByLayerId;
                ColorStateList color = gradientDrawable.getColor();
                if (this.mProgressColorStateList == null && color != null) {
                    this.mProgressColorStateList = color;
                }
                ColorStateList colorStateList = this.mProgressColorStateList;
                if (colorStateList != null && (colorStateList.getColorForState(new int[]{-16842910}, this.mDefaultForegroundPrimaryDisableColor) != this.mForegroundPrimaryDisableColor || this.mProgressColorStateList.getColorForState(SeekBar.ENABLED_STATE_SET, this.mDefaultForegroundPrimaryColor) != this.mForegroundPrimaryColor)) {
                    GradientDrawable gradientDrawable2 = (GradientDrawable) gradientDrawable.mutate();
                    ColorStateList colorStateList2 = new ColorStateList(new int[][]{new int[]{-16842910}, new int[0]}, new int[]{this.mForegroundPrimaryDisableColor, this.mForegroundPrimaryColor});
                    this.mProgressColorStateList = colorStateList2;
                    gradientDrawable2.setColor(colorStateList2);
                }
            }
            if (drawableFindDrawableByLayerId2 == null || !(drawableFindDrawableByLayerId2 instanceof GradientDrawable)) {
                return;
            }
            GradientDrawable gradientDrawable3 = (GradientDrawable) drawableFindDrawableByLayerId2;
            ColorStateList color2 = gradientDrawable3.getColor();
            if (this.mProgressColorStateList == null && color2 != null) {
                this.mProgressColorStateList = color2;
            }
            ColorStateList colorStateList3 = this.mProgressColorStateList;
            if (colorStateList3 != null) {
                if (colorStateList3.getColorForState(new int[]{-16842910}, this.mDefaultBackgroundPrimaryDisableColor) == this.mBackgroundPrimaryDisableColor && this.mProgressColorStateList.getColorForState(SeekBar.ENABLED_STATE_SET, this.mDefaultBackgroundPrimaryColor) == this.mBackgroundPrimaryColor) {
                    return;
                }
                GradientDrawable gradientDrawable4 = (GradientDrawable) gradientDrawable3.mutate();
                ColorStateList colorStateList4 = new ColorStateList(new int[][]{new int[]{-16842910}, new int[0]}, new int[]{this.mBackgroundPrimaryDisableColor, this.mBackgroundPrimaryColor});
                this.mProgressColorStateList = colorStateList4;
                gradientDrawable4.setColor(colorStateList4);
            }
        }
    }

    @Override // androidx.appcompat.widget.AppCompatSeekBar, android.widget.AbsSeekBar, android.widget.ProgressBar, android.view.View
    public void drawableStateChanged() {
        super.drawableStateChanged();
        int i2 = this.mDeviceLevel;
        if (i2 == 0 || i2 == 1) {
            updatePrimaryColor();
        }
    }

    public String loadShader(Resources resources, int i2) {
        Scanner scanner = new Scanner(resources.openRawResource(i2));
        StringBuilder sb = new StringBuilder();
        while (scanner.hasNextLine()) {
            sb.append(scanner.nextLine());
            sb.append("\n");
        }
        return sb.toString();
    }

    @Override // androidx.appcompat.widget.AppCompatSeekBar, android.widget.AbsSeekBar, android.widget.ProgressBar, android.view.View
    public synchronized void onDraw(Canvas canvas) {
        try {
            int i2 = this.mDeviceLevel;
            if (i2 == 2) {
                drawHighEndMode(canvas);
            } else if (i2 == 0) {
                drawPrimaryMode(canvas);
            } else if (i2 == 1) {
                drawMiddleMode(canvas);
            }
        } catch (Throwable th) {
            throw th;
        }
    }

    @Override // android.view.View
    public void onLayout(boolean z2, int i2, int i3, int i4, int i5) {
        super.onLayout(z2, i2, i3, i4, i5);
        int i6 = this.mDeviceLevel;
        if (i6 == 2 || i6 == 1) {
            this.uTrackSize[0] = getWidth() - (this.uTrackPosition[0] * 2.0f);
            this.runtimeShader.setFloatUniform("uTrackSize", this.uTrackSize);
            this.runtimeShader.setIntUniform("uIsRtl", isLayoutRtl() ? 1 : 0);
        }
    }

    @Override // android.widget.AbsSeekBar, android.widget.ProgressBar, android.view.View
    public void onSizeChanged(int i2, int i3, int i4, int i5) {
        super.onSizeChanged(i2, i3, i4, i5);
        int i6 = this.mDeviceLevel;
        if (i6 == 2 || i6 == 1) {
            float[] fArr = this.uTrackCanvasSize;
            fArr[0] = i2;
            fArr[1] = i3;
            this.runtimeShader.setFloatUniform("uTrackCanvasSize", fArr);
            this.runtimeShader.setFloatUniform("uResolution", this.uTrackCanvasSize);
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:16:0x003a, code lost:
    
        if (r5 != 3) goto L26;
     */
    @Override // android.widget.AbsSeekBar, android.view.View
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public boolean onTouchEvent(android.view.MotionEvent r13) {
        /*
            Method dump skipped, instruction units count: 581
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: miuix.miuixbasewidget.widget.HyperProgressSeekBar.onTouchEvent(android.view.MotionEvent):boolean");
    }

    public void setBackgroundPrimaryColor(@ColorInt int i2) {
        int i3 = this.mDeviceLevel;
        if (i3 == 0 || i3 == 1) {
            this.mBackgroundPrimaryColor = i2;
            updatePrimaryColor();
        }
    }

    public void setBackgroundPrimaryDisableColor(@ColorInt int i2) {
        int i3 = this.mDeviceLevel;
        if (i3 == 0 || i3 == 1) {
            this.mBackgroundPrimaryDisableColor = i2;
            updatePrimaryColor();
        }
    }

    public void setForegroundPrimaryColor(@ColorInt int i2) {
        int i3 = this.mDeviceLevel;
        if (i3 == 0 || i3 == 1) {
            this.mForegroundPrimaryColor = i2;
            updatePrimaryColor();
        }
    }

    public void setForegroundPrimaryColorRes(@ColorRes int i2) {
        int i3 = this.mDeviceLevel;
        if (i3 == 0 || i3 == 1) {
            this.mForegroundPrimaryColor = getContext().getResources().getColor(i2);
            updatePrimaryColor();
        }
    }

    public void setForegroundPrimaryDisableColor(@ColorInt int i2) {
        int i3 = this.mDeviceLevel;
        if (i3 == 0 || i3 == 1) {
            this.mForegroundPrimaryDisableColor = i2;
            updatePrimaryColor();
        }
    }

    @Override // android.widget.SeekBar
    public void setOnSeekBarChangeListener(SeekBar.OnSeekBarChangeListener onSeekBarChangeListener) {
        SeekBar.OnSeekBarChangeListener onSeekBarChangeListener2 = this.mTrainsOnSeekBarChangeListener;
        if (onSeekBarChangeListener == onSeekBarChangeListener2) {
            super.setOnSeekBarChangeListener(onSeekBarChangeListener2);
        } else {
            this.mOnSeekBarChangeListener = onSeekBarChangeListener;
        }
    }

    @Override // android.widget.ProgressBar
    public void setProgress(int i2) {
        this.mIsProgressChangedInternal = true;
        super.setProgress(i2);
    }

    public void setProgressAndPressedAlpha(int i2, int i3) {
        this.mProgressAlpha = i2;
        this.mProgressPressedAlpha = i3;
        this.mProgressAlphaAnimator.folme().setTo(PROPERTY_PROGRESS_ALPHA, Integer.valueOf(this.mProgressAlpha));
        this.mDrawProgressAlpha = this.mProgressAlpha;
    }

    public void setProgressAndPressedAlphaRes(@IntegerRes int i2, @IntegerRes int i3) {
        this.mProgressAlpha = getContext().getResources().getInteger(i2);
        this.mProgressPressedAlpha = getContext().getResources().getInteger(i3);
        this.mProgressAlphaAnimator.folme().setTo(PROPERTY_PROGRESS_ALPHA, Integer.valueOf(this.mProgressAlpha));
        this.mDrawProgressAlpha = this.mProgressAlpha;
    }

    public HyperProgressSeekBar(@NonNull Context context, @Nullable AttributeSet attributeSet) {
        this(context, attributeSet, R.attr.miuixProgressSeekBarStyle);
    }

    public HyperProgressSeekBar(@NonNull Context context, @Nullable AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        this.uTrackCanvasSize = new float[]{272.0f, 38.0f};
        this.uTrackPosition = new float[]{12.0f, 19.0f};
        this.uTrackSize = new float[]{220.0f, 6.0f};
        this.uHeadSize = new float[]{75.0f, 38.0f};
        this.progress = 0.7f;
        this.uHeadGlowAlpha = 1.0f;
        this.initialX = 0.0f;
        this.initialProgress = 0;
        this.mDeviceLevel = 0;
        this.mTrainsOnSeekBarChangeListener = new SeekBar.OnSeekBarChangeListener() { // from class: miuix.miuixbasewidget.widget.HyperProgressSeekBar.7
            private HapticFeedbackCompat mHapticFeedbackCompat;

            private HapticFeedbackCompat getHapticFeedbackCompat() {
                if (this.mHapticFeedbackCompat == null) {
                    this.mHapticFeedbackCompat = new HapticFeedbackCompat(HyperProgressSeekBar.this.getContext());
                }
                return this.mHapticFeedbackCompat;
            }

            @Override // android.widget.SeekBar.OnSeekBarChangeListener
            public void onProgressChanged(SeekBar seekBar, int i3, boolean z2) {
                if (HyperProgressSeekBar.this.mOnSeekBarChangeListener != null) {
                    if (!HyperProgressSeekBar.this.mIsProgressChangedInternal) {
                        HyperProgressSeekBar.this.mOnSeekBarChangeListener.onProgressChanged(seekBar, i3, true);
                    } else {
                        HyperProgressSeekBar.this.mIsProgressChangedInternal = false;
                        HyperProgressSeekBar.this.mOnSeekBarChangeListener.onProgressChanged(seekBar, i3, false);
                    }
                }
            }

            @Override // android.widget.SeekBar.OnSeekBarChangeListener
            public void onStartTrackingTouch(SeekBar seekBar) {
                if (HyperProgressSeekBar.this.mOnSeekBarChangeListener != null) {
                    HyperProgressSeekBar.this.mOnSeekBarChangeListener.onStartTrackingTouch(seekBar);
                }
            }

            @Override // android.widget.SeekBar.OnSeekBarChangeListener
            public void onStopTrackingTouch(SeekBar seekBar) {
                if (HyperProgressSeekBar.this.mOnSeekBarChangeListener != null) {
                    HyperProgressSeekBar.this.mOnSeekBarChangeListener.onStopTrackingTouch(seekBar);
                }
            }
        };
        init(context);
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.HyperProgressSeekBar, i2, R.style.Widget_HyperProgressSeekBar_DayNight);
        this.mProgressMode = typedArrayObtainStyledAttributes.getInt(R.styleable.HyperProgressSeekBar_progressBarMode, -1);
        initializeDeviceLevel();
        int i3 = this.mDeviceLevel;
        if (i3 == 0) {
            initForPrimaryDevice();
        } else if (i3 == 1) {
            initForMiddleDevice();
        } else if (i3 == 2) {
            initForHighEndDevice();
        }
        int i4 = this.mDeviceLevel;
        if (i4 == 0 || i4 == 1) {
            this.mDefaultForegroundPrimaryColor = context.getResources().getColor(R.color.miuix_appcompat_hyper_progress_seekbar_foreground_normal_color_light);
            this.mDefaultForegroundPrimaryDisableColor = context.getResources().getColor(R.color.miuix_appcompat_hyper_progress_seekbar_background_disabled_color_light);
            this.mDefaultBackgroundPrimaryColor = context.getResources().getColor(R.color.miuix_appcompat_hyper_progress_seekbar_background_normal_color_light);
            this.mDefaultBackgroundPrimaryDisableColor = context.getResources().getColor(R.color.miuix_appcompat_hyper_progress_seekbar_background_disabled_color_dark);
            this.mForegroundPrimaryColor = typedArrayObtainStyledAttributes.getColor(R.styleable.HyperProgressSeekBar_foregroundPrimaryColor, this.mDefaultForegroundPrimaryColor);
            this.mForegroundPrimaryDisableColor = typedArrayObtainStyledAttributes.getColor(R.styleable.HyperProgressSeekBar_foregroundPrimaryDisableColor, this.mDefaultForegroundPrimaryDisableColor);
            this.mBackgroundPrimaryColor = typedArrayObtainStyledAttributes.getColor(R.styleable.HyperProgressSeekBar_backgroundPrimaryColor, this.mDefaultBackgroundPrimaryColor);
            this.mBackgroundPrimaryDisableColor = typedArrayObtainStyledAttributes.getColor(R.styleable.HyperProgressSeekBar_backgroundPrimaryDisableColor, this.mDefaultBackgroundPrimaryDisableColor);
            this.mProgressAlpha = typedArrayObtainStyledAttributes.getInt(R.styleable.HyperProgressSeekBar_progressAlpha, 255);
            this.mProgressPressedAlpha = typedArrayObtainStyledAttributes.getInt(R.styleable.HyperProgressSeekBar_progressPressedAlpha, 255);
            ProgressAnimTarget progressAnimTarget = new ProgressAnimTarget();
            this.mProgressAlphaAnimator = progressAnimTarget;
            Folme.use((FolmeObject) progressAnimTarget);
            this.mProgressAlphaAnimator.folme().setTo(PROPERTY_PROGRESS_ALPHA, Integer.valueOf(this.mProgressAlpha));
            this.mDrawProgressAlpha = this.mProgressAlpha;
            this.mProgressPaddingOffset = getResources().getDimensionPixelSize(R.dimen.miuix_appcompat_hyper_progress_seekbar_padding_offset);
            post(new ColorUpdateRunner(this));
        }
        int i5 = this.mDeviceLevel;
        if (i5 == 2 || i5 == 1) {
            ProgressAnimTarget progressAnimTarget2 = new ProgressAnimTarget();
            this.mHeadAlphaAnimator = progressAnimTarget2;
            Folme.use((FolmeObject) progressAnimTarget2);
            this.mHeadAlphaAnimator.folme().setTo(PROPERTY_HEAD_ALPHA, Float.valueOf(1.0f));
        }
        typedArrayObtainStyledAttributes.recycle();
    }
}
