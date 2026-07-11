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
import miuix.animation.styles.AlphaBlendingStateEffect;
import miuix.appcompat.R;
import miuix.internal.util.LiteUtils;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

/* JADX INFO: loaded from: classes2.dex */
public class AlphaBlendingDrawable extends Drawable implements AlphaBlendingStateEffect.AlphaObserver {
    private static final boolean USE_FOLME = !LiteUtils.isCommonLiteStrategy();
    private float mActivatedAlpha;
    protected float[] mAllRadii;
    private int mAlpha;
    private float mCheckedAlpha;
    private float mDisabledAlpha;
    private float mFocusedAlpha;
    private float mHoveredAlpha;
    private float mHoveredCheckedAlpha;
    private int mInsetB;
    private int mInsetL;
    private int mInsetR;
    private int mInsetT;
    private int mMinHeight;
    private int mMinWidth;
    private float mNormalAlpha;
    private final Paint mPaint;
    private float mPressedAlpha;
    private int mRadius;
    protected final RectF mRect;

    @NonNull
    private final AlphaBlendingState mState;

    @NonNull
    private final AlphaBlendingStateEffect mStateEffect;
    private int mTintColor;

    public static final class AlphaBlendingState extends Drawable.ConstantState {
        float mActivatedAlpha;
        int mAlpha;
        float mCheckedAlpha;
        float mDisabledAlpha;
        float mFocusedAlpha;
        float mHoveredAlpha;
        float mHoveredCheckedAlpha;
        int mMinHeight;
        int mMinWidth;
        float mNormalAlpha;
        float mPressedAlpha;
        int mRadius;
        int mTintColor;

        public AlphaBlendingState() {
        }

        @Override // android.graphics.drawable.Drawable.ConstantState
        public int getChangingConfigurations() {
            return 0;
        }

        @Override // android.graphics.drawable.Drawable.ConstantState
        @NonNull
        public Drawable newDrawable() {
            return new AlphaBlendingDrawable(new AlphaBlendingState(this), null);
        }

        public AlphaBlendingState(@NonNull AlphaBlendingState alphaBlendingState) {
            this.mTintColor = alphaBlendingState.mTintColor;
            this.mAlpha = alphaBlendingState.mAlpha;
            this.mMinWidth = alphaBlendingState.mMinWidth;
            this.mMinHeight = alphaBlendingState.mMinHeight;
            this.mRadius = alphaBlendingState.mRadius;
            this.mNormalAlpha = alphaBlendingState.mNormalAlpha;
            this.mPressedAlpha = alphaBlendingState.mPressedAlpha;
            this.mHoveredAlpha = alphaBlendingState.mHoveredAlpha;
            this.mFocusedAlpha = alphaBlendingState.mFocusedAlpha;
            this.mActivatedAlpha = alphaBlendingState.mActivatedAlpha;
            this.mCheckedAlpha = alphaBlendingState.mCheckedAlpha;
            this.mHoveredCheckedAlpha = alphaBlendingState.mHoveredCheckedAlpha;
            this.mDisabledAlpha = alphaBlendingState.mDisabledAlpha;
        }

        @Override // android.graphics.drawable.Drawable.ConstantState
        @NonNull
        public Drawable newDrawable(@Nullable Resources resources) {
            return new AlphaBlendingDrawable(new AlphaBlendingState(this), resources);
        }
    }

    public AlphaBlendingDrawable() {
        this.mRect = new RectF();
        this.mAllRadii = new float[8];
        this.mPaint = new Paint();
        this.mAlpha = 255;
        AlphaBlendingStateEffect alphaBlendingStateEffect = new AlphaBlendingStateEffect(this);
        this.mStateEffect = alphaBlendingStateEffect;
        alphaBlendingStateEffect.setEnableAnim(USE_FOLME);
        this.mState = new AlphaBlendingState();
    }

    private void init() {
        this.mPaint.setColor(this.mTintColor);
        AlphaBlendingStateEffect alphaBlendingStateEffect = this.mStateEffect;
        alphaBlendingStateEffect.normalAlpha = this.mNormalAlpha;
        alphaBlendingStateEffect.pressedAlpha = this.mPressedAlpha;
        alphaBlendingStateEffect.hoveredAlpha = this.mHoveredAlpha;
        alphaBlendingStateEffect.focusedAlpha = this.mFocusedAlpha;
        alphaBlendingStateEffect.checkedAlpha = this.mCheckedAlpha;
        alphaBlendingStateEffect.activatedAlpha = this.mActivatedAlpha;
        alphaBlendingStateEffect.hoveredCheckedAlpha = this.mHoveredCheckedAlpha;
        alphaBlendingStateEffect.disabledAlpha = this.mDisabledAlpha;
        alphaBlendingStateEffect.initStates();
    }

    private void updateLocalState() {
        AlphaBlendingState alphaBlendingState = this.mState;
        alphaBlendingState.mTintColor = this.mTintColor;
        alphaBlendingState.mAlpha = this.mAlpha;
        alphaBlendingState.mMinWidth = this.mMinWidth;
        alphaBlendingState.mMinHeight = this.mMinHeight;
        alphaBlendingState.mRadius = this.mRadius;
        alphaBlendingState.mNormalAlpha = this.mNormalAlpha;
        alphaBlendingState.mPressedAlpha = this.mPressedAlpha;
        alphaBlendingState.mHoveredAlpha = this.mHoveredAlpha;
        alphaBlendingState.mFocusedAlpha = this.mFocusedAlpha;
        alphaBlendingState.mActivatedAlpha = this.mActivatedAlpha;
        alphaBlendingState.mCheckedAlpha = this.mCheckedAlpha;
        alphaBlendingState.mHoveredCheckedAlpha = this.mHoveredCheckedAlpha;
        alphaBlendingState.mDisabledAlpha = this.mDisabledAlpha;
    }

    @Override // android.graphics.drawable.Drawable
    public void draw(@NonNull Canvas canvas) {
        this.mStateEffect.draw(canvas);
        if (isVisible()) {
            RectF rectF = this.mRect;
            int i2 = this.mRadius;
            canvas.drawRoundRect(rectF, i2, i2, this.mPaint);
        }
    }

    @Override // android.graphics.drawable.Drawable
    @Nullable
    public Drawable.ConstantState getConstantState() {
        return this.mState;
    }

    @Override // android.graphics.drawable.Drawable
    public int getIntrinsicHeight() {
        return this.mState.mMinHeight;
    }

    @Override // android.graphics.drawable.Drawable
    public int getIntrinsicWidth() {
        return this.mState.mMinWidth;
    }

    @Override // android.graphics.drawable.Drawable
    public int getOpacity() {
        return 0;
    }

    public int getTintColor() {
        return this.mTintColor;
    }

    @Override // android.graphics.drawable.Drawable
    public void inflate(Resources resources, XmlPullParser xmlPullParser, AttributeSet attributeSet, Resources.Theme theme) throws XmlPullParserException, IOException {
        super.inflate(resources, xmlPullParser, attributeSet, theme);
        TypedArray typedArrayObtainStyledAttributes = theme != null ? theme.obtainStyledAttributes(attributeSet, R.styleable.StateTransitionDrawable, 0, 0) : resources.obtainAttributes(attributeSet, R.styleable.StateTransitionDrawable);
        this.mTintColor = typedArrayObtainStyledAttributes.getColor(R.styleable.StateTransitionDrawable_miuixDrawableTintColor, ViewCompat.MEASURED_STATE_MASK);
        this.mRadius = typedArrayObtainStyledAttributes.getDimensionPixelSize(R.styleable.StateTransitionDrawable_miuixDrawableTintRadius, 0);
        this.mNormalAlpha = typedArrayObtainStyledAttributes.getFloat(R.styleable.StateTransitionDrawable_normalAlpha, 0.0f);
        this.mPressedAlpha = typedArrayObtainStyledAttributes.getFloat(R.styleable.StateTransitionDrawable_pressedAlpha, 0.0f);
        float f2 = typedArrayObtainStyledAttributes.getFloat(R.styleable.StateTransitionDrawable_hoveredAlpha, 0.0f);
        this.mHoveredAlpha = f2;
        this.mFocusedAlpha = typedArrayObtainStyledAttributes.getFloat(R.styleable.StateTransitionDrawable_focusedAlpha, f2);
        this.mActivatedAlpha = typedArrayObtainStyledAttributes.getFloat(R.styleable.StateTransitionDrawable_activatedAlpha, 0.0f);
        this.mCheckedAlpha = typedArrayObtainStyledAttributes.getFloat(R.styleable.StateTransitionDrawable_checkedAlpha, 0.0f);
        this.mHoveredCheckedAlpha = typedArrayObtainStyledAttributes.getFloat(R.styleable.StateTransitionDrawable_hoveredCheckedAlpha, 0.0f);
        this.mDisabledAlpha = typedArrayObtainStyledAttributes.getFloat(R.styleable.StateTransitionDrawable_disabledAlpha, 0.0f);
        this.mMinWidth = typedArrayObtainStyledAttributes.getDimensionPixelSize(R.styleable.StateTransitionDrawable_android_width, -1);
        this.mMinHeight = typedArrayObtainStyledAttributes.getDimensionPixelSize(R.styleable.StateTransitionDrawable_android_height, -1);
        typedArrayObtainStyledAttributes.recycle();
        int i2 = this.mRadius;
        this.mAllRadii = new float[]{i2, i2, i2, i2, i2, i2, i2, i2};
        init();
        updateLocalState();
    }

    @Override // android.graphics.drawable.Drawable
    public boolean isStateful() {
        return true;
    }

    @Override // android.graphics.drawable.Drawable
    public void jumpToCurrentState() {
        this.mStateEffect.jumpToCurrentState();
    }

    @Override // miuix.animation.styles.AlphaBlendingStateEffect.AlphaObserver
    public void onAlphaChanged(float f2) {
        this.mPaint.setAlpha((int) (((Math.min(Math.max(f2, 0.0f), 1.0f) * this.mAlpha) / 255.0f) * 255.0f));
        invalidateSelf();
    }

    @Override // android.graphics.drawable.Drawable
    public void onBoundsChange(@NonNull Rect rect) {
        this.mRect.set(rect);
        RectF rectF = this.mRect;
        rectF.left += this.mInsetL;
        rectF.top += this.mInsetT;
        rectF.right -= this.mInsetR;
        rectF.bottom -= this.mInsetB;
    }

    @Override // android.graphics.drawable.Drawable
    public boolean onStateChange(@NonNull int[] iArr) {
        return this.mStateEffect.onStateChange(iArr);
    }

    @Override // android.graphics.drawable.Drawable
    public void setAlpha(int i2) {
        if (this.mAlpha != i2) {
            this.mAlpha = i2;
            this.mState.mAlpha = i2;
            this.mPaint.setAlpha((int) (i2 * this.mStateEffect.getAlphaF()));
            invalidateSelf();
        }
    }

    @Override // android.graphics.drawable.Drawable
    public void setColorFilter(@Nullable ColorFilter colorFilter) {
    }

    public void setInset(int i2, int i3, int i4, int i5) {
        this.mInsetL = i2;
        this.mInsetT = i3;
        this.mInsetR = i4;
        this.mInsetB = i5;
    }

    public void setRadius(int i2) {
        if (this.mRadius == i2) {
            return;
        }
        this.mRadius = i2;
        this.mState.mRadius = i2;
        invalidateSelf();
    }

    public AlphaBlendingDrawable(AlphaBlendingState alphaBlendingState, Resources resources) {
        this.mRect = new RectF();
        this.mAllRadii = new float[8];
        this.mPaint = new Paint();
        this.mAlpha = 255;
        AlphaBlendingStateEffect alphaBlendingStateEffect = new AlphaBlendingStateEffect(this);
        this.mStateEffect = alphaBlendingStateEffect;
        alphaBlendingStateEffect.setEnableAnim(USE_FOLME);
        this.mTintColor = alphaBlendingState.mTintColor;
        this.mMinHeight = alphaBlendingState.mMinHeight;
        this.mRadius = alphaBlendingState.mRadius;
        this.mNormalAlpha = alphaBlendingState.mNormalAlpha;
        this.mPressedAlpha = alphaBlendingState.mPressedAlpha;
        this.mHoveredAlpha = alphaBlendingState.mHoveredAlpha;
        this.mFocusedAlpha = alphaBlendingState.mFocusedAlpha;
        this.mActivatedAlpha = alphaBlendingState.mActivatedAlpha;
        this.mCheckedAlpha = alphaBlendingState.mCheckedAlpha;
        this.mHoveredCheckedAlpha = alphaBlendingState.mHoveredCheckedAlpha;
        this.mDisabledAlpha = alphaBlendingState.mDisabledAlpha;
        this.mState = new AlphaBlendingState();
        updateLocalState();
        init();
    }
}
