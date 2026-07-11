package miuix.appcompat.app;

import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.view.ViewCompat;
import java.io.IOException;
import miuix.animation.styles.ColorStateEffect;
import miuix.appcompat.R;
import miuix.internal.util.LiteUtils;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

/* JADX INFO: loaded from: classes2.dex */
public class ColorStateDrawable extends Drawable implements ColorStateEffect.ColorObserver {
    private static final boolean USE_FOLME = !LiteUtils.isCommonLiteStrategy();
    protected float[] mAllRadii;
    private int mAlpha;
    private final Paint mPaint;
    protected final RectF mRect;
    private final ColorState mState;
    private final ColorStateEffect mStateEffect;

    public static final class ColorState extends Drawable.ConstantState {
        int mActivatedColor;
        int mCheckedColor;
        int mDisabledColor;
        int mFocusedColor;
        int mHoveredCheckedColor;
        int mHoveredColor;
        int mInsetB;
        int mInsetL;
        int mInsetR;
        int mInsetT;
        int mIntrinsicHeight;
        int mIntrinsicWidth;
        int mNormalColor;
        int mPressedColor;
        int mRadius;

        public ColorState() {
        }

        @Override // android.graphics.drawable.Drawable.ConstantState
        public int getChangingConfigurations() {
            return 0;
        }

        @Override // android.graphics.drawable.Drawable.ConstantState
        @NonNull
        public Drawable newDrawable() {
            return new ColorStateDrawable(new ColorState(this), null);
        }

        public ColorState(@NonNull ColorState colorState) {
            this.mRadius = colorState.mRadius;
            this.mIntrinsicWidth = colorState.mIntrinsicWidth;
            this.mIntrinsicHeight = colorState.mIntrinsicHeight;
            this.mInsetL = colorState.mInsetL;
            this.mInsetT = colorState.mInsetT;
            this.mInsetR = colorState.mInsetR;
            this.mInsetB = colorState.mInsetB;
            this.mNormalColor = colorState.mNormalColor;
            this.mPressedColor = colorState.mPressedColor;
            this.mHoveredColor = colorState.mHoveredColor;
            this.mFocusedColor = colorState.mFocusedColor;
            this.mActivatedColor = colorState.mActivatedColor;
            this.mCheckedColor = colorState.mCheckedColor;
            this.mHoveredCheckedColor = colorState.mHoveredCheckedColor;
            this.mDisabledColor = colorState.mDisabledColor;
        }

        @Override // android.graphics.drawable.Drawable.ConstantState
        @NonNull
        public Drawable newDrawable(@Nullable Resources resources) {
            return new ColorStateDrawable(new ColorState(this), resources);
        }
    }

    public ColorStateDrawable() {
        this.mRect = new RectF();
        this.mAllRadii = new float[8];
        this.mAlpha = 255;
        this.mPaint = new Paint();
        ColorStateEffect colorStateEffect = new ColorStateEffect(this);
        this.mStateEffect = colorStateEffect;
        colorStateEffect.setEnableAnim(USE_FOLME);
        this.mState = new ColorState();
    }

    private void init() {
        this.mPaint.setColor(this.mState.mNormalColor);
        ColorStateEffect colorStateEffect = this.mStateEffect;
        ColorState colorState = this.mState;
        colorStateEffect.normalColor = colorState.mNormalColor;
        colorStateEffect.pressedColor = colorState.mPressedColor;
        colorStateEffect.hoveredColor = colorState.mHoveredColor;
        colorStateEffect.focusedColor = colorState.mFocusedColor;
        colorStateEffect.checkedColor = colorState.mCheckedColor;
        colorStateEffect.activatedColor = colorState.mActivatedColor;
        colorStateEffect.hoveredCheckedColor = colorState.mHoveredCheckedColor;
        colorStateEffect.disabledColor = colorState.mDisabledColor;
        colorStateEffect.initStates();
    }

    private int updateColorWithAlpha(int i2, int i3) {
        return ((((i2 >>> 24) * i3) / 255) << 24) | (i2 & ViewCompat.MEASURED_SIZE_MASK);
    }

    @Override // android.graphics.drawable.Drawable
    public void draw(@NonNull Canvas canvas) {
        this.mStateEffect.draw(canvas);
        if (isVisible()) {
            RectF rectF = this.mRect;
            int i2 = this.mState.mRadius;
            canvas.drawRoundRect(rectF, i2, i2, this.mPaint);
        }
    }

    @Override // android.graphics.drawable.Drawable
    @Nullable
    public Drawable.ConstantState getConstantState() {
        return this.mState;
    }

    public int getDefaultColor() {
        return this.mState.mNormalColor;
    }

    @Override // android.graphics.drawable.Drawable
    public int getIntrinsicHeight() {
        return this.mState.mIntrinsicHeight;
    }

    @Override // android.graphics.drawable.Drawable
    public int getIntrinsicWidth() {
        return this.mState.mIntrinsicWidth;
    }

    @Override // android.graphics.drawable.Drawable
    public int getOpacity() {
        return 0;
    }

    @Override // android.graphics.drawable.Drawable
    public void inflate(Resources resources, XmlPullParser xmlPullParser, AttributeSet attributeSet, Resources.Theme theme) throws XmlPullParserException, IOException {
        super.inflate(resources, xmlPullParser, attributeSet, theme);
        TypedArray typedArrayObtainStyledAttributes = theme != null ? theme.obtainStyledAttributes(attributeSet, R.styleable.StateTransitionDrawable, 0, 0) : resources.obtainAttributes(attributeSet, R.styleable.StateTransitionDrawable);
        this.mState.mRadius = typedArrayObtainStyledAttributes.getDimensionPixelSize(R.styleable.StateTransitionDrawable_miuixDrawableTintRadius, 0);
        this.mState.mIntrinsicWidth = typedArrayObtainStyledAttributes.getDimensionPixelSize(R.styleable.StateTransitionDrawable_android_width, -1);
        this.mState.mIntrinsicHeight = typedArrayObtainStyledAttributes.getDimensionPixelSize(R.styleable.StateTransitionDrawable_android_height, -1);
        this.mState.mNormalColor = typedArrayObtainStyledAttributes.getColor(R.styleable.StateTransitionDrawable_normalColor, 0);
        ColorState colorState = this.mState;
        colorState.mPressedColor = typedArrayObtainStyledAttributes.getColor(R.styleable.StateTransitionDrawable_pressedColor, colorState.mNormalColor);
        ColorState colorState2 = this.mState;
        colorState2.mHoveredColor = typedArrayObtainStyledAttributes.getColor(R.styleable.StateTransitionDrawable_hoveredColor, colorState2.mNormalColor);
        ColorState colorState3 = this.mState;
        colorState3.mFocusedColor = typedArrayObtainStyledAttributes.getColor(R.styleable.StateTransitionDrawable_focusedColor, colorState3.mHoveredColor);
        ColorState colorState4 = this.mState;
        colorState4.mActivatedColor = typedArrayObtainStyledAttributes.getColor(R.styleable.StateTransitionDrawable_activatedColor, colorState4.mNormalColor);
        ColorState colorState5 = this.mState;
        colorState5.mCheckedColor = typedArrayObtainStyledAttributes.getColor(R.styleable.StateTransitionDrawable_checkedColor, colorState5.mNormalColor);
        ColorState colorState6 = this.mState;
        colorState6.mHoveredCheckedColor = typedArrayObtainStyledAttributes.getColor(R.styleable.StateTransitionDrawable_hoveredCheckedColor, colorState6.mNormalColor);
        ColorState colorState7 = this.mState;
        colorState7.mDisabledColor = typedArrayObtainStyledAttributes.getColor(R.styleable.StateTransitionDrawable_disabledColor, colorState7.mNormalColor);
        typedArrayObtainStyledAttributes.recycle();
        int i2 = this.mState.mRadius;
        this.mAllRadii = new float[]{i2, i2, i2, i2, i2, i2, i2, i2};
        init();
    }

    @Override // android.graphics.drawable.Drawable
    public boolean isStateful() {
        return true;
    }

    @Override // android.graphics.drawable.Drawable
    public void jumpToCurrentState() {
        this.mStateEffect.jumpToCurrentState();
    }

    @Override // android.graphics.drawable.Drawable
    public void onBoundsChange(@NonNull Rect rect) {
        this.mRect.set(rect);
        RectF rectF = this.mRect;
        float f2 = rectF.left;
        ColorState colorState = this.mState;
        rectF.left = f2 + colorState.mInsetL;
        rectF.top += colorState.mInsetT;
        rectF.right -= colorState.mInsetR;
        rectF.bottom -= colorState.mInsetB;
    }

    @Override // miuix.animation.styles.ColorStateEffect.ColorObserver
    public void onColorChanged(int i2) {
        this.mPaint.setColor(updateColorWithAlpha(i2, this.mAlpha));
        invalidateSelf();
    }

    @Override // android.graphics.drawable.Drawable
    public boolean onStateChange(@NonNull int[] iArr) {
        return this.mStateEffect.onStateChange(iArr);
    }

    @Override // android.graphics.drawable.Drawable
    public void setAlpha(int i2) {
        int iMax = Math.max(0, Math.min(i2, 255));
        if (this.mAlpha != iMax) {
            this.mAlpha = iMax;
            this.mPaint.setColor(updateColorWithAlpha(this.mStateEffect.getStateColor(), this.mAlpha));
        }
    }

    @Override // android.graphics.drawable.Drawable
    public void setColorFilter(@Nullable ColorFilter colorFilter) {
    }

    public void setInset(int i2, int i3, int i4, int i5) {
        ColorState colorState = this.mState;
        colorState.mInsetL = i2;
        colorState.mInsetT = i3;
        colorState.mInsetR = i4;
        colorState.mInsetB = i5;
    }

    public void setRadius(int i2) {
        ColorState colorState = this.mState;
        if (colorState.mRadius == i2) {
            return;
        }
        colorState.mRadius = i2;
        invalidateSelf();
    }

    public ColorStateDrawable(ColorState colorState, Resources resources) {
        this.mRect = new RectF();
        this.mAllRadii = new float[8];
        this.mAlpha = 255;
        this.mPaint = new Paint();
        ColorStateEffect colorStateEffect = new ColorStateEffect(this);
        this.mStateEffect = colorStateEffect;
        colorStateEffect.setEnableAnim(USE_FOLME);
        this.mState = new ColorState(colorState);
        init();
    }
}
