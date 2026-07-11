package miuix.popupwidget.widget;

import android.R;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.StateSet;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.view.ViewCompat;
import java.io.IOException;
import miuix.animation.Folme;
import miuix.animation.FolmeObject;
import miuix.animation.base.AnimConfig;
import miuix.animation.controller.AnimState;
import miuix.animation.utils.EaseManager;
import miuix.device.DeviceUtils;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

/* JADX INFO: loaded from: classes5.dex */
public class PressEffectDrawable extends Drawable implements FolmeObject {
    private static final AnimConfig ACTIVATE_ENTER_CONFIG;
    private static final AnimConfig ACTIVATE_EXIT_CONFIG;
    private static final String ALPHA_F = "alphaF";
    private static final AnimConfig HOVER_ENTER_CONFIG;
    private static final AnimConfig HOVER_EXIT_CONFIG;
    private static final AnimConfig PRESS_ENTER_CONFIG;
    private static final AnimConfig PRESS_EXIT_CONFIG;
    private static final String TAG = "StateTransitionDrawable";
    private static final boolean USE_FOLME;
    private boolean mActivated;
    private float mActivatedAlpha;
    private AnimState mActivatedState;
    private Folme.ObjectFolmeImpl mFolmeAnimator;
    private boolean mHovered;
    private float mHoveredActivatedAlpha;
    private AnimState mHoveredActivatedState;
    private float mHoveredAlpha;
    private AnimState mHoveredState;
    private int mInsetB;
    private int mInsetL;
    private int mInsetR;
    private int mInsetT;
    private float mNormalAlpha;
    private AnimState mNormalState;
    private boolean mPressed;
    private float mPressedAlpha;
    private AnimState mPressedState;
    private int mTintColor;
    private static final int[] STATE_PRESSED = {R.attr.state_pressed};
    private static final int[] STATE_DRAG_HOVERED = {R.attr.state_drag_hovered};
    private static final int[] STATE_SELECTED = {R.attr.state_selected};
    private static final int[] STATE_HOVERED_ACTIVATED = {R.attr.state_hovered, R.attr.state_activated};
    private static final int[] STATE_HOVERED = {R.attr.state_hovered};
    private static final int[] STATE_ACTIVATED = {R.attr.state_activated};
    private final RectF mRect = new RectF();
    private final Paint mPaint = new Paint();
    private PressEffectState mState = new PressEffectState();

    public static final class PressEffectState extends Drawable.ConstantState {
        float mActivatedAlpha;
        float mHoveredActivatedAlpha;
        float mHoveredAlpha;
        float mNormalAlpha;
        float mPressedAlpha;
        int mRadius;
        int mTintColor;

        public PressEffectState() {
        }

        @Override // android.graphics.drawable.Drawable.ConstantState
        public int getChangingConfigurations() {
            return 0;
        }

        @Override // android.graphics.drawable.Drawable.ConstantState
        @NonNull
        public Drawable newDrawable() {
            return new PressEffectDrawable(new PressEffectState(this), null);
        }

        public PressEffectState(@NonNull PressEffectState pressEffectState) {
            this.mTintColor = pressEffectState.mTintColor;
            this.mRadius = pressEffectState.mRadius;
            this.mNormalAlpha = pressEffectState.mNormalAlpha;
            this.mPressedAlpha = pressEffectState.mPressedAlpha;
            this.mHoveredAlpha = pressEffectState.mHoveredAlpha;
            this.mActivatedAlpha = pressEffectState.mActivatedAlpha;
            this.mHoveredActivatedAlpha = pressEffectState.mHoveredActivatedAlpha;
        }

        @Override // android.graphics.drawable.Drawable.ConstantState
        @NonNull
        public Drawable newDrawable(@Nullable Resources resources) {
            return new PressEffectDrawable(new PressEffectState(this), resources);
        }
    }

    static {
        boolean z2 = (DeviceUtils.isMiuiLiteV2() || DeviceUtils.isLiteV1StockPlus() || DeviceUtils.isMiuiMiddle()) ? false : true;
        USE_FOLME = z2;
        if (!z2) {
            HOVER_ENTER_CONFIG = null;
            HOVER_EXIT_CONFIG = null;
            PRESS_ENTER_CONFIG = null;
            PRESS_EXIT_CONFIG = null;
            ACTIVATE_ENTER_CONFIG = null;
            ACTIVATE_EXIT_CONFIG = null;
            return;
        }
        HOVER_ENTER_CONFIG = new AnimConfig().setEase(EaseManager.getStyle(-2, 0.99f, 0.6f));
        HOVER_EXIT_CONFIG = new AnimConfig().setEase(EaseManager.getStyle(-2, 0.9f, 0.2f));
        AnimConfig ease = new AnimConfig().setEase(EaseManager.getStyle(-2, 0.99f, 0.25f));
        PRESS_ENTER_CONFIG = ease;
        AnimConfig ease2 = new AnimConfig().setEase(EaseManager.getStyle(-2, 0.99f, 0.35f));
        PRESS_EXIT_CONFIG = ease2;
        ACTIVATE_ENTER_CONFIG = ease;
        ACTIVATE_EXIT_CONFIG = ease2;
    }

    public PressEffectDrawable() {
    }

    private void init() {
        this.mPaint.setColor(this.mTintColor);
        this.mPaint.setAlpha(0);
        if (!USE_FOLME) {
            setAlphaF(this.mNormalAlpha);
            return;
        }
        this.mNormalState = new AnimState().add(ALPHA_F, this.mNormalAlpha);
        this.mPressedState = new AnimState().add(ALPHA_F, this.mPressedAlpha);
        this.mHoveredState = new AnimState().add(ALPHA_F, this.mHoveredAlpha);
        this.mActivatedState = new AnimState().add(ALPHA_F, this.mActivatedAlpha);
        this.mHoveredActivatedState = new AnimState().add(ALPHA_F, this.mHoveredActivatedAlpha);
    }

    private boolean toActivatedState() {
        if (this.mPressed) {
            this.mPressed = false;
            this.mHovered = false;
            this.mActivated = true;
            if (isAnimEnabled()) {
                this.mFolmeAnimator.to(this.mActivatedState, PRESS_EXIT_CONFIG);
            } else {
                setAlphaF(this.mActivatedAlpha);
            }
            return true;
        }
        if (this.mHovered) {
            this.mHovered = false;
            this.mActivated = true;
            if (isAnimEnabled()) {
                this.mFolmeAnimator.to(this.mActivatedState, HOVER_EXIT_CONFIG);
            } else {
                setAlphaF(this.mActivatedAlpha);
            }
            return true;
        }
        if (this.mActivated) {
            return false;
        }
        this.mActivated = true;
        if (isAnimEnabled()) {
            this.mFolmeAnimator.to(this.mActivatedState, ACTIVATE_ENTER_CONFIG);
        } else {
            setAlphaF(this.mActivatedAlpha);
        }
        return true;
    }

    private boolean toHoveredActivatedState() {
        if (this.mPressed) {
            this.mPressed = false;
            this.mHovered = true;
            this.mActivated = true;
            if (isAnimEnabled()) {
                this.mFolmeAnimator.to(this.mHoveredActivatedState, PRESS_EXIT_CONFIG);
            } else {
                setAlphaF(this.mHoveredActivatedAlpha);
            }
            return true;
        }
        boolean z2 = this.mHovered;
        if (z2 && this.mActivated) {
            return false;
        }
        if (z2) {
            this.mActivated = true;
            if (isAnimEnabled()) {
                this.mFolmeAnimator.to(this.mHoveredActivatedState, ACTIVATE_ENTER_CONFIG);
            } else {
                setAlphaF(this.mHoveredActivatedAlpha);
            }
            return true;
        }
        if (this.mActivated) {
            this.mHovered = true;
            if (isAnimEnabled()) {
                this.mFolmeAnimator.to(this.mHoveredActivatedState, HOVER_ENTER_CONFIG);
            } else {
                setAlphaF(this.mHoveredActivatedAlpha);
            }
            return true;
        }
        this.mActivated = true;
        this.mHovered = true;
        if (isAnimEnabled()) {
            this.mFolmeAnimator.to(this.mHoveredActivatedState, HOVER_ENTER_CONFIG);
        } else {
            setAlphaF(this.mHoveredActivatedAlpha);
        }
        return true;
    }

    private boolean toHoveredState() {
        if (this.mPressed) {
            this.mPressed = false;
            this.mHovered = true;
            this.mActivated = false;
            if (isAnimEnabled()) {
                this.mFolmeAnimator.to(this.mHoveredState, PRESS_EXIT_CONFIG);
            } else {
                setAlphaF(this.mHoveredAlpha);
            }
            return true;
        }
        if (this.mHovered) {
            if (!this.mActivated) {
                return false;
            }
            if (isAnimEnabled()) {
                this.mFolmeAnimator.to(this.mHoveredState, HOVER_EXIT_CONFIG);
            } else {
                setAlphaF(this.mHoveredAlpha);
            }
            return true;
        }
        this.mHovered = true;
        this.mActivated = false;
        if (isAnimEnabled()) {
            this.mFolmeAnimator.to(this.mHoveredState, HOVER_ENTER_CONFIG);
        } else {
            setAlphaF(this.mHoveredAlpha);
        }
        return true;
    }

    private boolean toNormalState() {
        if (this.mPressed) {
            this.mPressed = false;
            this.mHovered = false;
            this.mActivated = false;
            if (isAnimEnabled()) {
                this.mFolmeAnimator.to(this.mNormalState, PRESS_EXIT_CONFIG);
            } else {
                setAlphaF(this.mNormalAlpha);
            }
            return true;
        }
        if (this.mHovered) {
            this.mHovered = false;
            this.mActivated = false;
            if (isAnimEnabled()) {
                this.mFolmeAnimator.to(this.mNormalState, HOVER_EXIT_CONFIG);
            } else {
                setAlphaF(this.mNormalAlpha);
            }
            return true;
        }
        if (!this.mActivated) {
            return false;
        }
        this.mActivated = false;
        if (isAnimEnabled()) {
            this.mFolmeAnimator.to(this.mNormalState, ACTIVATE_EXIT_CONFIG);
        } else {
            setAlphaF(this.mNormalAlpha);
        }
        return true;
    }

    private boolean toPressedState() {
        if (this.mPressed) {
            return false;
        }
        if (isAnimEnabled()) {
            this.mFolmeAnimator.to(this.mPressedState, PRESS_ENTER_CONFIG);
        } else {
            setAlphaF(this.mPressedAlpha);
        }
        this.mPressed = true;
        this.mHovered = false;
        this.mActivated = false;
        return true;
    }

    private void updateLocalState() {
        PressEffectState pressEffectState = this.mState;
        pressEffectState.mTintColor = this.mTintColor;
        pressEffectState.mNormalAlpha = this.mNormalAlpha;
        pressEffectState.mPressedAlpha = this.mPressedAlpha;
        pressEffectState.mHoveredAlpha = this.mHoveredAlpha;
        pressEffectState.mActivatedAlpha = this.mActivatedAlpha;
        pressEffectState.mHoveredActivatedAlpha = this.mHoveredActivatedAlpha;
    }

    @Override // android.graphics.drawable.Drawable
    public void draw(@NonNull Canvas canvas) {
        if (USE_FOLME && this.mFolmeAnimator == null) {
            this.mFolmeAnimator = Folme.use((FolmeObject) this);
        }
        if (isVisible()) {
            canvas.drawRect(this.mRect, this.mPaint);
        }
    }

    @Override // miuix.animation.FolmeObject
    public Folme.ObjectFolmeImpl folme() {
        return null;
    }

    public float getAlphaF() {
        return this.mPaint.getAlpha() / 255.0f;
    }

    @Override // android.graphics.drawable.Drawable
    @Nullable
    public Drawable.ConstantState getConstantState() {
        return this.mState;
    }

    @Override // android.graphics.drawable.Drawable
    public int getOpacity() {
        return 0;
    }

    @Override // android.graphics.drawable.Drawable
    public void inflate(Resources resources, XmlPullParser xmlPullParser, AttributeSet attributeSet, Resources.Theme theme) throws XmlPullParserException, IOException {
        super.inflate(resources, xmlPullParser, attributeSet, theme);
        TypedArray typedArrayObtainStyledAttributes = theme != null ? theme.obtainStyledAttributes(attributeSet, miuix.popupwidget.R.styleable.StateTransitionDrawable, 0, 0) : resources.obtainAttributes(attributeSet, miuix.popupwidget.R.styleable.StateTransitionDrawable);
        this.mTintColor = typedArrayObtainStyledAttributes.getColor(miuix.popupwidget.R.styleable.StateTransitionDrawable_miuixDrawableTintColor, ViewCompat.MEASURED_STATE_MASK);
        this.mNormalAlpha = typedArrayObtainStyledAttributes.getFloat(miuix.popupwidget.R.styleable.StateTransitionDrawable_normalAlpha, 0.0f);
        this.mPressedAlpha = typedArrayObtainStyledAttributes.getFloat(miuix.popupwidget.R.styleable.StateTransitionDrawable_pressedAlpha, 0.0f);
        this.mHoveredAlpha = typedArrayObtainStyledAttributes.getFloat(miuix.popupwidget.R.styleable.StateTransitionDrawable_hoveredAlpha, 0.0f);
        this.mActivatedAlpha = typedArrayObtainStyledAttributes.getFloat(miuix.popupwidget.R.styleable.StateTransitionDrawable_activatedAlpha, 0.0f);
        this.mHoveredActivatedAlpha = typedArrayObtainStyledAttributes.getFloat(miuix.popupwidget.R.styleable.StateTransitionDrawable_hoveredActivatedAlpha, 0.0f);
        typedArrayObtainStyledAttributes.recycle();
        init();
        updateLocalState();
    }

    public boolean isAnimEnabled() {
        return USE_FOLME && this.mFolmeAnimator != null;
    }

    @Override // android.graphics.drawable.Drawable
    public boolean isStateful() {
        return true;
    }

    @Override // android.graphics.drawable.Drawable
    public void jumpToCurrentState() {
        if (isAnimEnabled()) {
            Folme.ObjectFolmeImpl objectFolmeImpl = this.mFolmeAnimator;
            objectFolmeImpl.setTo(objectFolmeImpl.state().getCurrentState());
        }
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
        return (StateSet.stateSetMatches(STATE_PRESSED, iArr) || StateSet.stateSetMatches(STATE_DRAG_HOVERED, iArr) || StateSet.stateSetMatches(STATE_SELECTED, iArr)) ? toPressedState() : StateSet.stateSetMatches(STATE_HOVERED_ACTIVATED, iArr) ? toHoveredActivatedState() : StateSet.stateSetMatches(STATE_HOVERED, iArr) ? toHoveredState() : StateSet.stateSetMatches(STATE_ACTIVATED, iArr) ? toActivatedState() : toNormalState();
    }

    @Override // android.graphics.drawable.Drawable
    public void setAlpha(int i2) {
    }

    public void setAlphaF(float f2) {
        this.mPaint.setAlpha((int) (f2 * 255.0f));
        invalidateSelf();
    }

    @Override // android.graphics.drawable.Drawable
    public void setColorFilter(@Nullable ColorFilter colorFilter) {
    }

    @Override // miuix.animation.FolmeObject
    public void setFolmeImpl(Folme.ObjectFolmeImpl objectFolmeImpl) {
    }

    public void setInset(int i2, int i3, int i4, int i5) {
        this.mInsetL = i2;
        this.mInsetT = i3;
        this.mInsetR = i4;
        this.mInsetB = i5;
    }

    public PressEffectDrawable(PressEffectState pressEffectState, Resources resources) {
        this.mTintColor = pressEffectState.mTintColor;
        this.mNormalAlpha = pressEffectState.mNormalAlpha;
        this.mPressedAlpha = pressEffectState.mPressedAlpha;
        this.mHoveredAlpha = pressEffectState.mHoveredAlpha;
        this.mActivatedAlpha = pressEffectState.mActivatedAlpha;
        this.mHoveredActivatedAlpha = pressEffectState.mHoveredActivatedAlpha;
        updateLocalState();
        init();
    }
}
