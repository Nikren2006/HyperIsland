package miuix.androidbasewidget.widget;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.graphics.drawable.ClipDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.LayerDrawable;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.ViewConfiguration;
import android.view.ViewDebug;
import android.widget.SeekBar;
import androidx.annotation.StyleableRes;
import androidx.appcompat.widget.AppCompatSeekBar;
import androidx.core.view.ViewCompat;
import com.android.systemui.miui.volume.VolumePanelViewController;
import java.lang.ref.WeakReference;
import java.util.Collection;
import miuix.androidbasewidget.R;
import miuix.animation.Folme;
import miuix.animation.FolmeEase;
import miuix.animation.FolmeObject;
import miuix.animation.IHoverStyle;
import miuix.animation.base.AnimConfig;
import miuix.animation.listener.TransitionListener;
import miuix.animation.listener.UpdateInfo;
import miuix.animation.physics.DynamicAnimation;
import miuix.animation.physics.SpringAnimation;
import miuix.animation.property.FloatProperty;
import miuix.animation.property.IntValueProperty;
import miuix.util.HapticFeedbackCompat;
import miuix.view.CompatViewMethod;
import miuix.view.HapticCompat;
import miuix.view.HapticFeedbackConstants;

/* JADX INFO: loaded from: classes4.dex */
public class SeekBar extends AppCompatSeekBar {
    private static final int ALPHA_70_PERCENT = 178;
    private static final int NO_ALPHA = 255;
    private static final IntValueProperty PROPERTY_DRAW_PROGRESS = new IntValueProperty("drawProgress", 0.001f);
    private static final IntValueProperty PROPERTY_PROGRESS = new IntValueProperty("progress", 0.1f);
    private static final String TAG = "SeekBar";
    private Drawable mBackgroundDrawable;
    private int mBackgroundPrimaryColor;
    private int mBackgroundPrimaryDisableColor;
    private int mDefaultBackgroundPrimaryColor;
    private int mDefaultBackgroundPrimaryDisableColor;
    private int mDefaultForegroundPrimaryColor;
    private int mDefaultForegroundPrimaryDisableColor;
    private int mDefaultIconPrimaryColor;
    private int mDefaultProgressPrimaryColor;
    private int mDefaultProgressPrimaryDisableColor;
    private int mDefaultScalePrimaryColor;
    private int mDefaultScaleSecondaryColor;
    private float mDisabledProgressAlpha;
    private float mDraggableMaxPercentProcess;
    private float mDraggableMinPercentProgress;
    private float mDrawProgress;
    private final ProgressAnimTarget mDrawProgressAnimator;
    private int mForegroundPrimaryColor;
    private int mForegroundPrimaryDisableColor;
    private boolean mHasEdgeReached;
    private int mIconPrimaryColor;
    private int mIconTransparent;
    private boolean mIsDragAnimationEnabled;
    private boolean mIsDragging;
    private boolean mIsInMiddle;
    private boolean mIsThumbNeedAnimation;
    private boolean mIsThumbTheme;
    private boolean mIsTouchAnimationEnabled;
    private boolean mIsTouchUpEvent;
    private boolean mIsUseCustomDrawables;
    private Drawable mLayerDrawable;
    private float mMaxMiddle;
    private boolean mMiddleEnabled;
    private float mMinMiddle;
    private SeekBar.OnSeekBarChangeListener mOnSeekBarChangeListener;
    private Drawable mOvalDrawable;
    private Paint mPaint;
    private int mProgress;
    private final ProgressAnimTarget mProgressAnimator;
    private ColorStateList mProgressColorStateList;
    private Drawable mProgressDrawable;
    private int mProgressPrimaryColor;
    private int mProgressPrimaryDisableColor;
    private boolean mScaleEnabled;
    private float mScaleRadius;
    private int mScaledTouchSlop;
    private int mThumbDrawOvalHeight;
    private int mThumbDrawOvalWidth;
    private Drawable mThumbDrawable;
    private int mThumbHeight;
    private int mThumbOvalHeight;
    private int mThumbOvalWidth;
    private SpringAnimation mThumbPressedAnim;
    private float mThumbPressedScale;
    private SpringAnimation mThumbPressedUpAnim;
    private Rect mThumbRect;
    private int mThumbWidth;
    private float mTouchDownX;
    private final SeekBar.OnSeekBarChangeListener mTrainsOnSeekBarChangeListener;

    public static class ColorUpdateRunner implements Runnable {
        private WeakReference<SeekBar> mSeekBarRef;

        public ColorUpdateRunner(SeekBar seekBar) {
            this.mSeekBarRef = new WeakReference<>(seekBar);
        }

        @Override // java.lang.Runnable
        public void run() {
            WeakReference<SeekBar> weakReference = this.mSeekBarRef;
            SeekBar seekBar = weakReference == null ? null : weakReference.get();
            if (seekBar != null) {
                seekBar.updatePrimaryColor();
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

    public SeekBar(Context context) {
        this(context, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public int getMinWrapper() {
        return super.getMin();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public synchronized int getProgressForm(float f2) {
        return ((int) (f2 * getRange())) + getMinWrapper();
    }

    private synchronized int getRange() {
        return getMax() - getMinWrapper();
    }

    private float getValueFromTypedArray(TypedArray typedArray, @StyleableRes int i2, float f2) {
        TypedValue typedValuePeekValue = typedArray.peekValue(i2);
        return (typedValuePeekValue == null || typedValuePeekValue.type != 6) ? f2 : typedValuePeekValue.getFraction(1.0f, 1.0f);
    }

    private void init() {
        Paint paint = new Paint();
        this.mPaint = paint;
        paint.setAntiAlias(true);
        if (this.mIsThumbNeedAnimation) {
            float f2 = this.mThumbOvalWidth;
            float f3 = this.mThumbPressedScale;
            this.mThumbOvalWidth = (int) (f2 * f3);
            this.mThumbOvalHeight = (int) (this.mThumbOvalHeight * f3);
        }
        this.mScaleRadius = getContext().getResources().getDimensionPixelSize(R.dimen.miuix_appcompat_seekbar_icon_size) / 2.0f;
        setThumb(null);
    }

    private boolean isInMiddle(int i2, int i3) {
        float minWrapper = i2 > 0 ? (i3 - getMinWrapper()) / i2 : 0.0f;
        return minWrapper > this.mMinMiddle && minWrapper < this.mMaxMiddle;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$initAnim$0(DynamicAnimation dynamicAnimation, float f2, float f3) {
        invalidate();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$initAnim$1(DynamicAnimation dynamicAnimation, float f2, float f3) {
        invalidate();
    }

    private void onPressedInner() {
        if (this.mThumbPressedUpAnim.isRunning()) {
            this.mThumbPressedUpAnim.cancel();
        }
        if (this.mThumbPressedAnim.isRunning()) {
            return;
        }
        this.mThumbPressedAnim.start();
    }

    private void onPressedUpInner() {
        if (this.mThumbPressedAnim.isRunning()) {
            this.mThumbPressedAnim.cancel();
        }
        if (this.mThumbPressedUpAnim.isRunning()) {
            return;
        }
        this.mThumbPressedUpAnim.start();
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
                if (colorStateList != null && (colorStateList.getColorForState(new int[]{-16842910}, this.mDefaultForegroundPrimaryDisableColor) != this.mForegroundPrimaryDisableColor || this.mProgressColorStateList.getColorForState(android.widget.SeekBar.ENABLED_STATE_SET, this.mDefaultForegroundPrimaryColor) != this.mForegroundPrimaryColor)) {
                    GradientDrawable gradientDrawable2 = (GradientDrawable) gradientDrawable.mutate();
                    ColorStateList colorStateList2 = new ColorStateList(new int[][]{new int[]{-16842910}, new int[0]}, new int[]{this.mForegroundPrimaryDisableColor, this.mForegroundPrimaryColor});
                    this.mProgressColorStateList = colorStateList2;
                    gradientDrawable2.setColor(colorStateList2);
                }
            }
            if (drawableFindDrawableByLayerId instanceof ClipDrawable) {
                Drawable drawable = ((ClipDrawable) drawableFindDrawableByLayerId).getDrawable();
                if (drawable instanceof GradientDrawable) {
                    GradientDrawable gradientDrawable3 = (GradientDrawable) drawable;
                    ColorStateList color2 = gradientDrawable3.getColor();
                    if (this.mProgressColorStateList == null && color2 != null) {
                        this.mProgressColorStateList = color2;
                    }
                    ColorStateList colorStateList3 = this.mProgressColorStateList;
                    if (colorStateList3 != null && ((colorStateList3.getColorForState(new int[]{-16842910}, this.mDefaultForegroundPrimaryDisableColor) != this.mForegroundPrimaryDisableColor || this.mProgressColorStateList.getColorForState(android.widget.SeekBar.ENABLED_STATE_SET, this.mDefaultForegroundPrimaryColor) != this.mForegroundPrimaryColor) && !this.mIsThumbTheme)) {
                        GradientDrawable gradientDrawable4 = (GradientDrawable) gradientDrawable3.mutate();
                        ColorStateList colorStateList4 = new ColorStateList(new int[][]{new int[]{-16842910}, new int[0]}, new int[]{this.mForegroundPrimaryDisableColor, this.mForegroundPrimaryColor});
                        this.mProgressColorStateList = colorStateList4;
                        gradientDrawable4.setColor(colorStateList4);
                    }
                }
            }
            if (drawableFindDrawableByLayerId2 != null && (drawableFindDrawableByLayerId2 instanceof GradientDrawable)) {
                GradientDrawable gradientDrawable5 = (GradientDrawable) drawableFindDrawableByLayerId2;
                ColorStateList color3 = gradientDrawable5.getColor();
                if (this.mProgressColorStateList == null && color3 != null) {
                    this.mProgressColorStateList = color3;
                }
                ColorStateList colorStateList5 = this.mProgressColorStateList;
                if (colorStateList5 != null && (colorStateList5.getColorForState(new int[]{-16842910}, this.mDefaultBackgroundPrimaryDisableColor) != this.mBackgroundPrimaryDisableColor || this.mProgressColorStateList.getColorForState(android.widget.SeekBar.ENABLED_STATE_SET, this.mDefaultBackgroundPrimaryColor) != this.mBackgroundPrimaryColor)) {
                    GradientDrawable gradientDrawable6 = (GradientDrawable) gradientDrawable5.mutate();
                    ColorStateList colorStateList6 = new ColorStateList(new int[][]{new int[]{-16842910}, new int[0]}, new int[]{this.mBackgroundPrimaryDisableColor, this.mBackgroundPrimaryColor});
                    this.mProgressColorStateList = colorStateList6;
                    gradientDrawable6.setColor(colorStateList6);
                }
            }
            if (this.mIsThumbTheme) {
                invalidate();
                return;
            }
            Drawable drawableFindDrawableByLayerId3 = layerDrawable.findDrawableByLayerId(android.R.id.icon);
            if (drawableFindDrawableByLayerId3 instanceof GradientDrawable) {
                drawableFindDrawableByLayerId3.setColorFilter(this.mMiddleEnabled ? this.mIconPrimaryColor : this.mIconTransparent, PorterDuff.Mode.SRC);
            }
        }
    }

    @Override // androidx.appcompat.widget.AppCompatSeekBar, android.widget.AbsSeekBar, android.widget.ProgressBar, android.view.View
    public void drawableStateChanged() {
        super.drawableStateChanged();
        updatePrimaryColor();
        Drawable progressDrawable = getProgressDrawable();
        if (progressDrawable != null) {
            progressDrawable.setAlpha(isEnabled() ? 255 : (int) (this.mDisabledProgressAlpha * 255.0f));
        }
    }

    @ViewDebug.ExportedProperty(category = "draggableProgress")
    public synchronized float getDraggableMaxPercentProgress() {
        return this.mDraggableMaxPercentProcess;
    }

    @ViewDebug.ExportedProperty(category = "draggableProgress")
    public synchronized float getDraggableMinPercentProgress() {
        return this.mDraggableMinPercentProgress;
    }

    public float getThumbScale() {
        return this.mThumbPressedScale;
    }

    public void initAnim() {
        FloatProperty<SeekBar> floatProperty = new FloatProperty<SeekBar>("ThumbScale") { // from class: miuix.androidbasewidget.widget.SeekBar.1
            @Override // miuix.animation.property.FloatProperty
            public float getValue(SeekBar seekBar) {
                return seekBar.getThumbScale();
            }

            @Override // miuix.animation.property.FloatProperty
            public void setValue(SeekBar seekBar, float f2) {
                seekBar.setThumbScale(f2);
            }
        };
        SpringAnimation springAnimation = new SpringAnimation(this, floatProperty, 1.127f);
        this.mThumbPressedAnim = springAnimation;
        springAnimation.getSpring().setStiffness(986.96f);
        this.mThumbPressedAnim.getSpring().setDampingRatio(0.6f);
        this.mThumbPressedAnim.setMinimumVisibleChange(0.002f);
        this.mThumbPressedAnim.addUpdateListener(new DynamicAnimation.OnAnimationUpdateListener() { // from class: miuix.androidbasewidget.widget.b
            @Override // miuix.animation.physics.DynamicAnimation.OnAnimationUpdateListener
            public final void onAnimationUpdate(DynamicAnimation dynamicAnimation, float f2, float f3) {
                this.f5934a.lambda$initAnim$0(dynamicAnimation, f2, f3);
            }
        });
        SpringAnimation springAnimation2 = new SpringAnimation(this, floatProperty, 1.0f);
        this.mThumbPressedUpAnim = springAnimation2;
        springAnimation2.getSpring().setStiffness(986.96f);
        this.mThumbPressedUpAnim.getSpring().setDampingRatio(0.6f);
        this.mThumbPressedUpAnim.setMinimumVisibleChange(0.002f);
        this.mThumbPressedUpAnim.addUpdateListener(new DynamicAnimation.OnAnimationUpdateListener() { // from class: miuix.androidbasewidget.widget.c
            @Override // miuix.animation.physics.DynamicAnimation.OnAnimationUpdateListener
            public final void onAnimationUpdate(DynamicAnimation dynamicAnimation, float f2, float f3) {
                this.f5935a.lambda$initAnim$1(dynamicAnimation, f2, f3);
            }
        });
    }

    @Override // androidx.appcompat.widget.AppCompatSeekBar, android.widget.AbsSeekBar, android.widget.ProgressBar, android.view.View
    @SuppressLint({"DrawAllocation"})
    public synchronized void onDraw(Canvas canvas) {
        float max;
        int paddingStart;
        int i2;
        int i3;
        int i4;
        try {
            if (this.mIsThumbTheme) {
                if (this.mOvalDrawable != null) {
                    if (isEnabled()) {
                        this.mOvalDrawable.setAlpha(255);
                    } else {
                        this.mOvalDrawable.setAlpha(ALPHA_70_PERCENT);
                    }
                }
                boolean z2 = ViewCompat.getLayoutDirection(this) == 1;
                float width = getWidth();
                getHeight();
                float maxHeight = getMaxHeight();
                float width2 = getWidth();
                float dimensionPixelSize = getResources().getDimensionPixelSize(R.dimen.miuix_appcompat_seekbar_progress_custom_bg_radius);
                float paddingTop = getPaddingTop();
                float f2 = paddingTop + maxHeight;
                int min = getMin();
                if (z2) {
                    max = ((getMax() - this.mDrawProgress) / ((getMax() - min) * 1.0f)) * ((width - getPaddingStart()) - getPaddingEnd());
                    paddingStart = getPaddingEnd();
                } else {
                    max = ((this.mDrawProgress - min) / (getMax() - min)) * ((width - getPaddingStart()) - getPaddingEnd());
                    paddingStart = getPaddingStart();
                }
                float f3 = max + paddingStart;
                int i5 = this.mDefaultScalePrimaryColor;
                int i6 = this.mDefaultScaleSecondaryColor;
                float f4 = (maxHeight / 2.0f) + paddingTop;
                int i7 = (int) (f4 - (this.mThumbHeight / 2.0f));
                Drawable drawable = this.mBackgroundDrawable;
                if (drawable == null && this.mProgressDrawable == null) {
                    Drawable drawable2 = this.mLayerDrawable;
                    if (drawable2 != null) {
                        drawable2.setBounds((int) (getPaddingStart() - dimensionPixelSize), (int) paddingTop, (int) ((width - getPaddingEnd()) + dimensionPixelSize), (int) f2);
                        this.mLayerDrawable.draw(canvas);
                    }
                    i2 = i7;
                    i3 = i5;
                } else {
                    if (drawable != null) {
                        i2 = i7;
                        i3 = i5;
                        drawable.setBounds((int) (getPaddingStart() - dimensionPixelSize), (int) paddingTop, (int) ((width2 - getPaddingEnd()) + dimensionPixelSize), (int) f2);
                        this.mBackgroundDrawable.draw(canvas);
                    } else {
                        i2 = i7;
                        i3 = i5;
                    }
                    Drawable drawable3 = this.mProgressDrawable;
                    if (drawable3 != null) {
                        if (z2) {
                            drawable3.setBounds((int) (f3 - dimensionPixelSize), (int) paddingTop, (int) ((width - getPaddingEnd()) + dimensionPixelSize), (int) f2);
                        } else {
                            drawable3.setBounds((int) (getPaddingStart() - dimensionPixelSize), (int) paddingTop, (int) (dimensionPixelSize + f3), (int) f2);
                        }
                        this.mProgressDrawable.draw(canvas);
                    }
                }
                if (this.mMiddleEnabled && this.mProgress < ((getMax() - min) / 2) + min) {
                    this.mPaint.setColor(i6);
                    canvas.drawCircle(width / 2.0f, f4, this.mScaleRadius, this.mPaint);
                }
                if (this.mScaleEnabled) {
                    int max2 = getMax();
                    float paddingStart2 = ((width - getPaddingStart()) - getPaddingEnd()) / max2;
                    int layoutDirection = getLayoutDirection();
                    int i8 = 0;
                    while (i8 <= max2) {
                        int i9 = this.mProgress;
                        if (i8 != i9) {
                            float f5 = i8;
                            float f6 = this.mDrawProgress;
                            if (f5 < f6) {
                                i4 = i3;
                                this.mPaint.setColor(i4);
                            } else {
                                i4 = i3;
                                if (f5 > f6) {
                                    this.mPaint.setColor(i6);
                                }
                            }
                        } else {
                            i4 = i3;
                            if (i9 > this.mDrawProgress) {
                                this.mPaint.setColor(i6);
                            } else {
                                this.mPaint.setColor(i4);
                            }
                        }
                        canvas.drawCircle(layoutDirection == 1 ? (width - getPaddingEnd()) - (i8 * paddingStart2) : getPaddingStart() + (i8 * paddingStart2), f4, this.mScaleRadius, this.mPaint);
                        i8++;
                        i3 = i4;
                    }
                }
                int i10 = i3;
                if (this.mMiddleEnabled && this.mProgress > ((getMax() - min) / 2) + min) {
                    this.mPaint.setColor(i10);
                    canvas.drawCircle(width / 2.0f, f4, this.mScaleRadius, this.mPaint);
                }
                Drawable drawable4 = this.mThumbDrawable;
                if (drawable4 != null) {
                    int i11 = this.mThumbWidth;
                    drawable4.setBounds((int) (f3 - (i11 / 2.0f)), i2, (int) (f3 + (i11 / 2.0f)), i2 + this.mThumbHeight);
                    this.mThumbDrawable.draw(canvas);
                }
            } else {
                super.onDraw(canvas);
            }
        } catch (Throwable th) {
            throw th;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:33:0x0069  */
    @Override // android.widget.AbsSeekBar, android.view.View
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public boolean onTouchEvent(android.view.MotionEvent r6) {
        /*
            r5 = this;
            boolean r0 = r5.isEnabled()
            r1 = 0
            if (r0 != 0) goto L8
            return r1
        L8:
            boolean r0 = r5.mIsThumbTheme
            if (r0 == 0) goto L18
            boolean r0 = r5.mIsThumbNeedAnimation
            if (r0 == 0) goto L18
            android.graphics.drawable.Drawable r0 = r5.mThumbDrawable
            android.graphics.Rect r0 = r0.copyBounds()
            r5.mThumbRect = r0
        L18:
            float r0 = r6.getY()
            int r2 = r6.getAction()
            if (r2 == 0) goto L7d
            r3 = 1
            if (r2 == r3) goto L69
            r4 = 2
            if (r2 == r4) goto L2d
            r0 = 3
            if (r2 == r0) goto L69
            goto L9c
        L2d:
            boolean r1 = r5.mIsDragging
            if (r1 != 0) goto L9c
            float r1 = r6.getX()
            float r2 = r5.mTouchDownX
            float r2 = r1 - r2
            float r2 = java.lang.Math.abs(r2)
            int r4 = r5.mScaledTouchSlop
            float r4 = (float) r4
            int r2 = (r2 > r4 ? 1 : (r2 == r4 ? 0 : -1))
            if (r2 <= 0) goto L53
            r5.mIsDragging = r3
            android.view.ViewParent r2 = r5.getParent()
            if (r2 == 0) goto L53
            android.view.ViewParent r2 = r5.getParent()
            r2.requestDisallowInterceptTouchEvent(r3)
        L53:
            boolean r2 = r5.mIsThumbTheme
            if (r2 == 0) goto L9c
            boolean r2 = r5.mIsThumbNeedAnimation
            if (r2 == 0) goto L9c
            android.graphics.Rect r2 = r5.mThumbRect
            int r1 = (int) r1
            int r0 = (int) r0
            boolean r0 = r2.contains(r1, r0)
            if (r0 == 0) goto L9c
            r5.onPressedInner()
            goto L9c
        L69:
            boolean r0 = r5.mIsThumbTheme
            if (r0 == 0) goto L76
            boolean r0 = r5.mIsThumbNeedAnimation
            if (r0 == 0) goto L76
            r5.mIsTouchUpEvent = r3
            r5.onPressedUpInner()
        L76:
            boolean r0 = r5.mIsDragging
            if (r0 == 0) goto L9c
            r5.mIsDragging = r1
            return r3
        L7d:
            r5.mIsDragging = r1
            float r1 = r6.getX()
            r5.mTouchDownX = r1
            boolean r2 = r5.mIsThumbTheme
            if (r2 == 0) goto L9c
            boolean r2 = r5.mIsThumbNeedAnimation
            if (r2 == 0) goto L9c
            android.graphics.Rect r2 = r5.mThumbRect
            if (r2 == 0) goto L9c
            int r1 = (int) r1
            int r0 = (int) r0
            boolean r0 = r2.contains(r1, r0)
            if (r0 == 0) goto L9c
            r5.onPressedInner()
        L9c:
            boolean r5 = super.onTouchEvent(r6)
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: miuix.androidbasewidget.widget.SeekBar.onTouchEvent(android.view.MotionEvent):boolean");
    }

    public void setBackgroundPrimaryColor(int i2, int i3) {
        this.mBackgroundPrimaryColor = i2;
        this.mBackgroundPrimaryDisableColor = i3;
        updatePrimaryColor();
    }

    /* JADX WARN: Removed duplicated region for block: B:14:0x0028 A[Catch: all -> 0x0013, TryCatch #0 {all -> 0x0013, blocks: (B:5:0x000a, B:12:0x0022, B:14:0x0028, B:16:0x0031, B:18:0x003d, B:11:0x001a), top: B:23:0x0008 }] */
    /* JADX WARN: Removed duplicated region for block: B:15:0x0030  */
    /* JADX WARN: Removed duplicated region for block: B:18:0x003d A[Catch: all -> 0x0013, TRY_LEAVE, TryCatch #0 {all -> 0x0013, blocks: (B:5:0x000a, B:12:0x0022, B:14:0x0028, B:16:0x0031, B:18:0x003d, B:11:0x001a), top: B:23:0x0008 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public synchronized void setDraggableMaxPercentProcess(float r5) {
        /*
            r4 = this;
            monitor-enter(r4)
            double r0 = (double) r5
            r2 = 4607182418800017408(0x3ff0000000000000, double:1.0)
            int r0 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1))
            r1 = 1065353216(0x3f800000, float:1.0)
            if (r0 <= 0) goto L15
            java.lang.String r5 = "SeekBar"
            java.lang.String r0 = "The draggableMaxPercentProcess value should not be higher than the max value, reset to 1.0"
            android.util.Log.e(r5, r0)     // Catch: java.lang.Throwable -> L13
        L11:
            r5 = r1
            goto L22
        L13:
            r5 = move-exception
            goto L42
        L15:
            r0 = 0
            int r0 = (r5 > r0 ? 1 : (r5 == r0 ? 0 : -1))
            if (r0 >= 0) goto L22
            java.lang.String r5 = "SeekBar"
            java.lang.String r0 = "The draggableMaxPercentProcess value should not be lower than the min value, reset to 1.0"
            android.util.Log.e(r5, r0)     // Catch: java.lang.Throwable -> L13
            goto L11
        L22:
            float r0 = r4.mDraggableMinPercentProgress     // Catch: java.lang.Throwable -> L13
            int r0 = (r5 > r0 ? 1 : (r5 == r0 ? 0 : -1))
            if (r0 >= 0) goto L30
            java.lang.String r5 = "SeekBar"
            java.lang.String r0 = "The draggableMaxPercentProcess value should not be lower than draggableMinPercentProcess value, reset to 1.0"
            android.util.Log.e(r5, r0)     // Catch: java.lang.Throwable -> L13
            goto L31
        L30:
            r1 = r5
        L31:
            r4.mDraggableMaxPercentProcess = r1     // Catch: java.lang.Throwable -> L13
            int r5 = r4.getProgressForm(r1)     // Catch: java.lang.Throwable -> L13
            int r0 = r4.getProgress()     // Catch: java.lang.Throwable -> L13
            if (r0 <= r5) goto L40
            r4.setProgress(r5)     // Catch: java.lang.Throwable -> L13
        L40:
            monitor-exit(r4)
            return
        L42:
            monitor-exit(r4)     // Catch: java.lang.Throwable -> L13
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: miuix.androidbasewidget.widget.SeekBar.setDraggableMaxPercentProcess(float):void");
    }

    /* JADX WARN: Removed duplicated region for block: B:14:0x0028 A[Catch: all -> 0x0012, TryCatch #0 {all -> 0x0012, blocks: (B:5:0x0009, B:12:0x0022, B:14:0x0028, B:16:0x0031, B:18:0x003d, B:11:0x001a), top: B:23:0x0007 }] */
    /* JADX WARN: Removed duplicated region for block: B:15:0x0030  */
    /* JADX WARN: Removed duplicated region for block: B:18:0x003d A[Catch: all -> 0x0012, TRY_LEAVE, TryCatch #0 {all -> 0x0012, blocks: (B:5:0x0009, B:12:0x0022, B:14:0x0028, B:16:0x0031, B:18:0x003d, B:11:0x001a), top: B:23:0x0007 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public synchronized void setDraggableMinPercentProgress(float r7) {
        /*
            r6 = this;
            monitor-enter(r6)
            double r0 = (double) r7
            r2 = 4607182418800017408(0x3ff0000000000000, double:1.0)
            int r2 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1))
            r3 = 0
            if (r2 <= 0) goto L14
            java.lang.String r7 = "SeekBar"
            java.lang.String r0 = "The draggableMinPercentProgress value should not be higher than 1.0, reset to 0.0"
            android.util.Log.e(r7, r0)     // Catch: java.lang.Throwable -> L12
        L10:
            r7 = r3
            goto L22
        L12:
            r7 = move-exception
            goto L42
        L14:
            r4 = 0
            int r0 = (r0 > r4 ? 1 : (r0 == r4 ? 0 : -1))
            if (r0 >= 0) goto L22
            java.lang.String r7 = "SeekBar"
            java.lang.String r0 = "The draggableMinPercentProgress value should not be lower than 0.0, reset to 0.0"
            android.util.Log.e(r7, r0)     // Catch: java.lang.Throwable -> L12
            goto L10
        L22:
            float r0 = r6.mDraggableMaxPercentProcess     // Catch: java.lang.Throwable -> L12
            int r0 = (r7 > r0 ? 1 : (r7 == r0 ? 0 : -1))
            if (r0 <= 0) goto L30
            java.lang.String r7 = "SeekBar"
            java.lang.String r0 = "The draggableMinPercentProgress value should not be higher than draggableMaxPercentProcess value, reset to 0.0"
            android.util.Log.e(r7, r0)     // Catch: java.lang.Throwable -> L12
            goto L31
        L30:
            r3 = r7
        L31:
            r6.mDraggableMinPercentProgress = r3     // Catch: java.lang.Throwable -> L12
            int r7 = r6.getProgressForm(r3)     // Catch: java.lang.Throwable -> L12
            int r0 = r6.getProgress()     // Catch: java.lang.Throwable -> L12
            if (r0 >= r7) goto L40
            r6.setProgress(r7)     // Catch: java.lang.Throwable -> L12
        L40:
            monitor-exit(r6)
            return
        L42:
            monitor-exit(r6)     // Catch: java.lang.Throwable -> L12
            throw r7
        */
        throw new UnsupportedOperationException("Method not decompiled: miuix.androidbasewidget.widget.SeekBar.setDraggableMinPercentProgress(float):void");
    }

    public void setDraggedAnimationEnable(boolean z2) {
        this.mIsDragAnimationEnabled = z2;
    }

    public void setForegroundPrimaryColor(int i2, int i3) {
        this.mForegroundPrimaryColor = i2;
        this.mForegroundPrimaryDisableColor = i3;
        updatePrimaryColor();
    }

    public void setIconPrimaryColor(int i2) {
        this.mIconPrimaryColor = i2;
        updatePrimaryColor();
    }

    public void setMiddleEnabled(boolean z2) {
        if (z2 != this.mMiddleEnabled) {
            this.mMiddleEnabled = z2;
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
    public synchronized void setProgress(int i2) {
        super.setProgress(i2);
        if (this.mIsThumbTheme && i2 >= getMinWrapper() && i2 <= getMax()) {
            this.mProgress = i2;
            this.mDrawProgressAnimator.folme().setTo(PROPERTY_DRAW_PROGRESS, Integer.valueOf(i2));
            this.mProgressAnimator.folme().setTo(PROPERTY_PROGRESS, Integer.valueOf(i2));
        }
    }

    @Override // android.widget.ProgressBar
    public void setProgressDrawable(Drawable drawable) {
        super.setProgressDrawable(drawable);
        if (this.mIsThumbTheme) {
            this.mLayerDrawable = drawable;
            if (!(drawable instanceof LayerDrawable)) {
                this.mBackgroundDrawable = null;
                this.mProgressDrawable = null;
            } else {
                LayerDrawable layerDrawable = (LayerDrawable) drawable;
                this.mBackgroundDrawable = layerDrawable.findDrawableByLayerId(android.R.id.background);
                this.mProgressDrawable = layerDrawable.findDrawableByLayerId(android.R.id.progress);
            }
        }
    }

    @Override // android.widget.AbsSeekBar
    public void setThumb(Drawable drawable) {
        super.setThumb(drawable);
        if (!this.mIsThumbTheme || drawable == null) {
            return;
        }
        this.mThumbDrawable = drawable;
        int intrinsicWidth = drawable.getIntrinsicWidth();
        this.mThumbWidth = intrinsicWidth;
        if (intrinsicWidth % 2 != 0) {
            this.mThumbWidth = intrinsicWidth + 1;
        }
        this.mThumbHeight = this.mThumbDrawable.getIntrinsicHeight();
    }

    public void setThumbScale(float f2) {
        this.mThumbPressedScale = f2;
        int i2 = (int) (this.mThumbOvalWidth * f2);
        this.mThumbDrawOvalWidth = i2;
        int i3 = (int) (this.mThumbOvalHeight * f2);
        this.mThumbDrawOvalHeight = i3;
        Drawable drawable = this.mOvalDrawable;
        if (drawable == null || !(drawable instanceof GradientDrawable)) {
            return;
        }
        ((GradientDrawable) drawable).setSize(i2, i3);
        LayerDrawable layerDrawable = (LayerDrawable) this.mThumbDrawable;
        layerDrawable.setDrawable(0, this.mOvalDrawable);
        setThumb(layerDrawable);
    }

    public void setTouchAnimationEnable(boolean z2) {
        this.mIsTouchAnimationEnabled = z2;
    }

    public SeekBar(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, R.attr.seekBarStyle);
    }

    public SeekBar(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        this.mIsThumbTheme = true;
        this.mThumbPressedScale = 1.0f;
        this.mIsTouchUpEvent = false;
        SeekBar.OnSeekBarChangeListener onSeekBarChangeListener = new SeekBar.OnSeekBarChangeListener() { // from class: miuix.androidbasewidget.widget.SeekBar.2
            private HapticFeedbackCompat mHapticFeedbackCompat;

            private HapticFeedbackCompat getHapticFeedbackCompat() {
                if (this.mHapticFeedbackCompat == null) {
                    this.mHapticFeedbackCompat = new HapticFeedbackCompat(SeekBar.this.getContext());
                }
                return this.mHapticFeedbackCompat;
            }

            @Override // android.widget.SeekBar.OnSeekBarChangeListener
            public void onProgressChanged(android.widget.SeekBar seekBar, int i3, boolean z2) {
                if (!SeekBar.this.mIsThumbTheme && SeekBar.this.mMiddleEnabled && z2) {
                    int max = SeekBar.this.getMax() - SeekBar.this.getMinWrapper();
                    float f2 = max;
                    int iRound = Math.round(f2 * 0.5f);
                    float minWrapper = max > 0 ? (i3 - SeekBar.this.getMinWrapper()) / f2 : 0.0f;
                    if (minWrapper <= SeekBar.this.mMinMiddle || minWrapper >= SeekBar.this.mMaxMiddle) {
                        SeekBar.this.mProgress = i3;
                        SeekBar.this.mProgressAnimator.folme().setTo(SeekBar.PROPERTY_PROGRESS, Integer.valueOf(SeekBar.this.mProgress));
                    } else {
                        SeekBar.this.mProgress = iRound;
                    }
                    if (SeekBar.this.getProgress() != SeekBar.this.mProgress) {
                        SeekBar.this.mProgressAnimator.folme().to(SeekBar.PROPERTY_PROGRESS, Integer.valueOf(SeekBar.this.mProgress), new AnimConfig().setEase(FolmeEase.spring(0.9f, 0.15f)).addListeners(new TransitionListener() { // from class: miuix.androidbasewidget.widget.SeekBar.2.1
                            @Override // miuix.animation.listener.TransitionListener
                            public void onUpdate(Object obj, Collection<UpdateInfo> collection) {
                                UpdateInfo updateInfoFindBy = UpdateInfo.findBy(collection, SeekBar.PROPERTY_PROGRESS);
                                if (updateInfoFindBy != null) {
                                    SeekBar.this.setProgress(updateInfoFindBy.getIntValue());
                                }
                            }
                        }));
                    }
                }
                SeekBar seekBar2 = SeekBar.this;
                int progressForm = seekBar2.getProgressForm(seekBar2.mDraggableMinPercentProgress);
                SeekBar seekBar3 = SeekBar.this;
                int progressForm2 = seekBar3.getProgressForm(seekBar3.mDraggableMaxPercentProcess);
                if (i3 < progressForm) {
                    SeekBar.this.setProgress(progressForm);
                    i3 = progressForm;
                } else if (i3 > progressForm2) {
                    SeekBar.this.setProgress(progressForm2);
                    i3 = progressForm2;
                }
                if (SeekBar.this.mIsThumbTheme) {
                    if (z2) {
                        if (SeekBar.this.mMiddleEnabled) {
                            int max2 = SeekBar.this.getMax() - SeekBar.this.getMinWrapper();
                            float f3 = max2;
                            int iRound2 = Math.round(0.5f * f3) + SeekBar.this.getMinWrapper();
                            float minWrapper2 = max2 > 0 ? (i3 - SeekBar.this.getMinWrapper()) / f3 : 0.0f;
                            if (minWrapper2 > SeekBar.this.mMinMiddle && minWrapper2 < SeekBar.this.mMaxMiddle) {
                                i3 = iRound2;
                            }
                        }
                        if (SeekBar.this.mIsTouchUpEvent) {
                            SeekBar.this.mProgress = i3;
                            if (SeekBar.this.mIsTouchAnimationEnabled) {
                                SeekBar.this.mDrawProgressAnimator.folme().to(SeekBar.PROPERTY_DRAW_PROGRESS, Integer.valueOf(i3), new AnimConfig().setEase(FolmeEase.spring(0.96f, 0.35f)).addListeners(new TransitionListener() { // from class: miuix.androidbasewidget.widget.SeekBar.2.2
                                    @Override // miuix.animation.listener.TransitionListener
                                    public void onUpdate(Object obj, Collection<UpdateInfo> collection) {
                                        UpdateInfo updateInfoFindBy = UpdateInfo.findBy(collection, SeekBar.PROPERTY_DRAW_PROGRESS);
                                        if (updateInfoFindBy != null) {
                                            SeekBar.this.mDrawProgress = updateInfoFindBy.getFloatValue();
                                            SeekBar.this.invalidate();
                                        }
                                    }
                                }));
                            } else {
                                SeekBar.this.mDrawProgress = r3.mProgress;
                                SeekBar.this.mDrawProgressAnimator.folme().setTo(SeekBar.PROPERTY_DRAW_PROGRESS, Float.valueOf(SeekBar.this.mDrawProgress));
                            }
                        } else {
                            SeekBar.this.mProgress = i3;
                            if (SeekBar.this.mIsDragAnimationEnabled || SeekBar.this.mMiddleEnabled) {
                                SeekBar.this.mDrawProgressAnimator.folme().to(SeekBar.PROPERTY_DRAW_PROGRESS, Integer.valueOf(i3), new AnimConfig().setEase(FolmeEase.spring(0.9f, 0.15f)).addListeners(new TransitionListener() { // from class: miuix.androidbasewidget.widget.SeekBar.2.3
                                    @Override // miuix.animation.listener.TransitionListener
                                    public void onComplete(Object obj) {
                                        if (SeekBar.this.getProgress() != SeekBar.this.mProgress) {
                                            SeekBar seekBar4 = SeekBar.this;
                                            seekBar4.setProgress(seekBar4.mProgress);
                                        }
                                    }

                                    @Override // miuix.animation.listener.TransitionListener
                                    public void onUpdate(Object obj, Collection<UpdateInfo> collection) {
                                        UpdateInfo updateInfoFindBy = UpdateInfo.findBy(collection, SeekBar.PROPERTY_DRAW_PROGRESS);
                                        if (updateInfoFindBy != null) {
                                            SeekBar.this.mDrawProgress = updateInfoFindBy.getFloatValue();
                                            SeekBar.this.mDrawProgress = Math.max(r3.getMinWrapper(), Math.min(SeekBar.this.getMax(), SeekBar.this.mDrawProgress));
                                            SeekBar.this.invalidate();
                                        }
                                    }
                                }));
                            } else {
                                SeekBar.this.mDrawProgress = r3.mProgress;
                                SeekBar.this.mDrawProgressAnimator.folme().setTo(SeekBar.PROPERTY_DRAW_PROGRESS, Float.valueOf(SeekBar.this.mDrawProgress));
                            }
                        }
                        SeekBar.this.mIsTouchUpEvent = false;
                    } else {
                        SeekBar.this.mDrawProgress = i3;
                    }
                }
                boolean z3 = i3 == progressForm || i3 == progressForm2;
                if (z2) {
                    if (!z3 || SeekBar.this.mHasEdgeReached) {
                        if (!SeekBar.this.mHasEdgeReached && HapticCompat.doesSupportHaptic(HapticCompat.HapticVersion.HAPTIC_VERSION_2)) {
                            HapticCompat.performHapticFeedback(seekBar, HapticFeedbackConstants.MIUI_GEAR_LIGHT);
                        }
                    } else if (!HapticCompat.doesSupportHaptic(HapticCompat.HapticVersion.HAPTIC_VERSION_2)) {
                        HapticCompat.performHapticFeedback(seekBar, HapticFeedbackConstants.MIUI_MESH_NORMAL);
                    } else if (i3 == progressForm2) {
                        getHapticFeedbackCompat().lambda$performExtHapticFeedbackAsync$0(VolumePanelViewController.HAPTIC_V2_VOLUME_MAX);
                    } else {
                        getHapticFeedbackCompat().lambda$performExtHapticFeedbackAsync$0(VolumePanelViewController.HAPTIC_V2_VOLUME_MIN);
                    }
                }
                SeekBar.this.mHasEdgeReached = z3;
                if (SeekBar.this.mOnSeekBarChangeListener != null) {
                    SeekBar.this.mOnSeekBarChangeListener.onProgressChanged(seekBar, i3, z2);
                }
            }

            @Override // android.widget.SeekBar.OnSeekBarChangeListener
            public void onStartTrackingTouch(android.widget.SeekBar seekBar) {
                if (SeekBar.this.mOnSeekBarChangeListener != null) {
                    SeekBar.this.mOnSeekBarChangeListener.onStartTrackingTouch(seekBar);
                }
            }

            @Override // android.widget.SeekBar.OnSeekBarChangeListener
            public void onStopTrackingTouch(android.widget.SeekBar seekBar) {
                if (SeekBar.this.mOnSeekBarChangeListener != null) {
                    SeekBar.this.mOnSeekBarChangeListener.onStopTrackingTouch(seekBar);
                }
            }
        };
        this.mTrainsOnSeekBarChangeListener = onSeekBarChangeListener;
        CompatViewMethod.setForceDarkAllowed(this, false);
        this.mScaledTouchSlop = ViewConfiguration.get(context).getScaledTouchSlop();
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.SeekBar, i2, R.style.Widget_SeekBar_Thumb_DayNight);
        this.mIsUseCustomDrawables = typedArrayObtainStyledAttributes.getBoolean(R.styleable.SeekBar_useCustomDrawables, false);
        if (getThumb() != null && !this.mIsUseCustomDrawables) {
            this.mIsThumbTheme = true;
            Drawable thumb = getThumb();
            this.mThumbDrawable = thumb;
            if ((thumb instanceof LayerDrawable) && ((LayerDrawable) thumb).getNumberOfLayers() > 0 && ((LayerDrawable) this.mThumbDrawable).getDrawable(0) != null) {
                this.mIsThumbNeedAnimation = true;
                Drawable drawable = ((LayerDrawable) this.mThumbDrawable).getDrawable(0);
                if (drawable instanceof GradientDrawable) {
                    GradientDrawable gradientDrawable = (GradientDrawable) drawable;
                    this.mOvalDrawable = gradientDrawable;
                    this.mThumbOvalWidth = gradientDrawable.getIntrinsicWidth();
                    this.mThumbOvalHeight = this.mOvalDrawable.getIntrinsicHeight();
                }
            }
            this.mThumbWidth = this.mThumbDrawable.getIntrinsicWidth();
            this.mThumbHeight = this.mThumbDrawable.getIntrinsicHeight();
            setThumb(null);
        } else {
            this.mIsThumbTheme = false;
        }
        Resources resources = context.getResources();
        int i3 = R.color.miuix_appcompat_progress_primary_colors_light;
        this.mDefaultForegroundPrimaryColor = resources.getColor(i3);
        Resources resources2 = context.getResources();
        int i4 = R.color.miuix_appcompat_progress_disable_color_light;
        this.mDefaultForegroundPrimaryDisableColor = resources2.getColor(i4);
        this.mDefaultProgressPrimaryColor = context.getResources().getColor(i3);
        this.mDefaultProgressPrimaryDisableColor = context.getResources().getColor(i4);
        this.mDefaultBackgroundPrimaryColor = context.getResources().getColor(R.color.miuix_appcompat_seekbar_background_normal_color);
        this.mDefaultBackgroundPrimaryDisableColor = context.getResources().getColor(R.color.miuix_appcompat_seekbar_background_disabled_color);
        this.mDefaultScalePrimaryColor = context.getResources().getColor(R.color.miuix_appcompat_seekbar_scale_primary_color);
        this.mDefaultScaleSecondaryColor = context.getResources().getColor(R.color.miuix_appcompat_seekbar_scale_secondary_color);
        this.mDefaultIconPrimaryColor = context.getResources().getColor(R.color.miuix_appcompat_progress_background_icon_light);
        this.mMiddleEnabled = typedArrayObtainStyledAttributes.getBoolean(R.styleable.SeekBar_middleEnabled, false);
        this.mScaleEnabled = typedArrayObtainStyledAttributes.getBoolean(R.styleable.SeekBar_scaleEnable, false);
        this.mForegroundPrimaryColor = typedArrayObtainStyledAttributes.getColor(R.styleable.SeekBar_foregroundPrimaryColor, this.mDefaultForegroundPrimaryColor);
        this.mForegroundPrimaryDisableColor = typedArrayObtainStyledAttributes.getColor(R.styleable.SeekBar_foregroundPrimaryDisableColor, this.mDefaultForegroundPrimaryDisableColor);
        this.mProgressPrimaryColor = typedArrayObtainStyledAttributes.getColor(R.styleable.SeekBar_progressPrimaryColor, this.mDefaultProgressPrimaryColor);
        this.mProgressPrimaryDisableColor = typedArrayObtainStyledAttributes.getColor(R.styleable.SeekBar_progressPrimaryDisableColor, this.mDefaultProgressPrimaryDisableColor);
        this.mBackgroundPrimaryColor = typedArrayObtainStyledAttributes.getColor(R.styleable.SeekBar_backgroundPrimaryColor, this.mDefaultBackgroundPrimaryColor);
        this.mBackgroundPrimaryDisableColor = typedArrayObtainStyledAttributes.getColor(R.styleable.SeekBar_backgroundPrimaryDisableColor, this.mDefaultBackgroundPrimaryDisableColor);
        this.mIconPrimaryColor = typedArrayObtainStyledAttributes.getColor(R.styleable.SeekBar_iconPrimaryColor, this.mDefaultIconPrimaryColor);
        this.mDisabledProgressAlpha = typedArrayObtainStyledAttributes.getFloat(R.styleable.SeekBar_disabledProgressAlpha, 0.5f);
        this.mMinMiddle = typedArrayObtainStyledAttributes.getFloat(R.styleable.SeekBar_minMiddle, 0.46f);
        this.mMaxMiddle = typedArrayObtainStyledAttributes.getFloat(R.styleable.SeekBar_maxMiddle, 0.54f);
        this.mDraggableMinPercentProgress = getValueFromTypedArray(typedArrayObtainStyledAttributes, R.styleable.SeekBar_draggableMinPercentProgress, 0.0f);
        this.mDraggableMaxPercentProcess = getValueFromTypedArray(typedArrayObtainStyledAttributes, R.styleable.SeekBar_draggableMaxPercentProgress, 1.0f);
        this.mIsDragAnimationEnabled = typedArrayObtainStyledAttributes.getBoolean(R.styleable.SeekBar_dragAnimationEnable, true);
        this.mIsTouchAnimationEnabled = typedArrayObtainStyledAttributes.getBoolean(R.styleable.SeekBar_touchAnimationEnable, true);
        if (getProgressDrawable() != null) {
            Drawable progressDrawable = getProgressDrawable();
            this.mLayerDrawable = progressDrawable;
            if (progressDrawable instanceof LayerDrawable) {
                LayerDrawable layerDrawable = (LayerDrawable) progressDrawable;
                this.mBackgroundDrawable = layerDrawable.findDrawableByLayerId(android.R.id.background);
                this.mProgressDrawable = layerDrawable.findDrawableByLayerId(android.R.id.progress);
            }
        }
        setDraggableMinPercentProgress(this.mDraggableMinPercentProgress);
        setDraggableMaxPercentProcess(this.mDraggableMaxPercentProcess);
        typedArrayObtainStyledAttributes.recycle();
        this.mIconTransparent = context.getResources().getColor(R.color.miuix_appcompat_transparent);
        float f2 = this.mMinMiddle;
        if (f2 > 0.5f || f2 < 0.0f) {
            this.mMinMiddle = 0.46f;
        }
        float f3 = this.mMaxMiddle;
        if (f3 < 0.5f || f3 > 1.0f) {
            this.mMaxMiddle = 0.54f;
        }
        this.mProgress = getProgress();
        this.mDrawProgress = getProgress();
        ProgressAnimTarget progressAnimTarget = new ProgressAnimTarget();
        this.mDrawProgressAnimator = progressAnimTarget;
        ProgressAnimTarget progressAnimTarget2 = new ProgressAnimTarget();
        this.mProgressAnimator = progressAnimTarget2;
        Folme.use((FolmeObject) progressAnimTarget);
        Folme.use((FolmeObject) progressAnimTarget2);
        progressAnimTarget.folme().setTo(PROPERTY_DRAW_PROGRESS, Float.valueOf(this.mDrawProgress));
        progressAnimTarget2.folme().setTo(PROPERTY_PROGRESS, Integer.valueOf(this.mProgress));
        setOnSeekBarChangeListener(onSeekBarChangeListener);
        post(new ColorUpdateRunner(this));
        Folme.useAt(this).hover().setEffect(IHoverStyle.HoverEffect.NORMAL).handleHoverOf(this, new AnimConfig[0]);
        if (this.mMiddleEnabled) {
            int max = getMax() - getMinWrapper();
            boolean zIsInMiddle = isInMiddle(max, getProgress());
            this.mIsInMiddle = zIsInMiddle;
            if (zIsInMiddle) {
                int iRound = Math.round(max * 0.5f) + getMinWrapper();
                this.mProgress = iRound;
                setProgress(iRound);
            }
        }
        if (!this.mIsThumbTheme || this.mThumbDrawable == null) {
            return;
        }
        init();
        if (this.mIsThumbNeedAnimation) {
            initAnim();
        }
    }
}
