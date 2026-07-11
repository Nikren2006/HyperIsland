package miuix.internal.view;

import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.PaintFlagsDrawFilter;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.util.TypedValue;
import androidx.annotation.RequiresApi;
import miuix.appcompat.R;
import miuix.internal.view.CheckWidgetAnimatedStateListDrawable;

/* JADX INFO: loaded from: classes3.dex */
@RequiresApi(api = 21)
public class CheckBoxAnimatedStateListDrawable extends CheckWidgetAnimatedStateListDrawable {
    protected static final int FULL_ALPHA = 255;
    protected static final int ONE_THIRD_ALPHA = 76;
    private static final String TAG = "MiuixCheckbox";
    private CheckWidgetDrawableAnims mCheckWidgetDrawableAnims;
    private float mContentAlpha;
    private boolean mIsEnabled;
    private boolean mPreChecked;
    private boolean mPrePressed;
    private float mScale;

    public static class CheckBoxConstantState extends CheckWidgetAnimatedStateListDrawable.CheckWidgetConstantState {
        @Override // miuix.internal.view.CheckWidgetAnimatedStateListDrawable.CheckWidgetConstantState
        public Drawable newAnimatedStateListDrawable(Resources resources, Resources.Theme theme, CheckWidgetAnimatedStateListDrawable.CheckWidgetConstantState checkWidgetConstantState) {
            return new CheckBoxAnimatedStateListDrawable(resources, theme, checkWidgetConstantState);
        }
    }

    public CheckBoxAnimatedStateListDrawable() {
        this.mScale = 1.0f;
        this.mContentAlpha = 1.0f;
        this.mPrePressed = false;
        this.mPreChecked = false;
    }

    private boolean safeGetBoolean(TypedArray typedArray, int i2, boolean z2) {
        try {
            return typedArray.getBoolean(i2, z2);
        } catch (Exception e2) {
            Log.w(TAG, "try catch Exception insafeGetBoolean", e2);
            return z2;
        }
    }

    private int safeGetColor(TypedArray typedArray, int i2, int i3) {
        try {
            return typedArray.getColor(i2, i3);
        } catch (UnsupportedOperationException e2) {
            Log.w(TAG, "try catch UnsupportedOperationException insafeGetColor", e2);
            return i3;
        }
    }

    private int safeGetInt(TypedArray typedArray, int i2, int i3) {
        try {
            return typedArray.getInt(i2, i3);
        } catch (Exception e2) {
            Log.w(TAG, "try catch Exception insafeGetInt", e2);
            return i3;
        }
    }

    @Override // android.graphics.drawable.AnimatedStateListDrawable, android.graphics.drawable.StateListDrawable, android.graphics.drawable.DrawableContainer, android.graphics.drawable.Drawable
    public void applyTheme(Resources.Theme theme) {
        super.applyTheme(theme);
        TypedArray typedArrayObtainStyledAttributes = theme.obtainStyledAttributes(getCheckWidgetDrawableStyle(), R.styleable.CheckWidgetDrawable);
        TypedValue typedValue = new TypedValue();
        theme.resolveAttribute(android.R.attr.isLightTheme, typedValue, true);
        boolean zEquals = com.xiaomi.onetrack.util.a.f3424i.equals(TypedValue.coerceToString(typedValue.type, typedValue.data));
        int color = zEquals ? Color.parseColor("#000000") : Color.parseColor("#ffffff");
        this.mCheckWidgetConstantState.grayColor = safeGetColor(typedArrayObtainStyledAttributes, R.styleable.CheckWidgetDrawable_checkwidget_disableBackgroundColor, color);
        this.mCheckWidgetConstantState.blackColor = safeGetColor(typedArrayObtainStyledAttributes, R.styleable.CheckWidgetDrawable_checkwidget_checkOnAlphaBackgroundColor, color);
        this.mCheckWidgetConstantState.backGroundColor = safeGetColor(typedArrayObtainStyledAttributes, R.styleable.CheckWidgetDrawable_checkwidget_checkOnBackgroundColor, Color.parseColor(zEquals ? "#3482FF" : "#277AF7"));
        this.mCheckWidgetConstantState.strokeColor = safeGetColor(typedArrayObtainStyledAttributes, R.styleable.CheckWidgetDrawable_checkwidget_strokeColor, Color.parseColor("#ffffff"));
        this.mCheckWidgetConstantState.backgroundNormalAlpha = safeGetInt(typedArrayObtainStyledAttributes, R.styleable.CheckWidgetDrawable_checkwidget_backgroundNormalAlpha, zEquals ? 15 : 51);
        this.mCheckWidgetConstantState.backgroundDisableAlpha = safeGetInt(typedArrayObtainStyledAttributes, R.styleable.CheckWidgetDrawable_checkwidget_backgroundDisableAlpha, zEquals ? 15 : 51);
        this.mCheckWidgetConstantState.strokeNormalAlpha = safeGetInt(typedArrayObtainStyledAttributes, R.styleable.CheckWidgetDrawable_checkwidget_strokeNormalAlpha, zEquals ? 255 : 0);
        this.mCheckWidgetConstantState.strokeDisableAlpha = safeGetInt(typedArrayObtainStyledAttributes, R.styleable.CheckWidgetDrawable_checkwidget_strokeDisableAlpha, zEquals ? 255 : 0);
        this.mCheckWidgetConstantState.touchAnimEnable = safeGetBoolean(typedArrayObtainStyledAttributes, R.styleable.CheckWidgetDrawable_checkwidget_touchAnimEnable, false);
        typedArrayObtainStyledAttributes.recycle();
        boolean zIsSingleSelectionWidget = isSingleSelectionWidget();
        CheckWidgetAnimatedStateListDrawable.CheckWidgetConstantState checkWidgetConstantState = this.mCheckWidgetConstantState;
        this.mCheckWidgetDrawableAnims = new CheckWidgetDrawableAnims(this, zIsSingleSelectionWidget, checkWidgetConstantState.grayColor, checkWidgetConstantState.blackColor, checkWidgetConstantState.backGroundColor, checkWidgetConstantState.backgroundNormalAlpha, checkWidgetConstantState.backgroundDisableAlpha, checkWidgetConstantState.strokeColor, checkWidgetConstantState.strokeNormalAlpha, checkWidgetConstantState.strokeDisableAlpha);
    }

    @Override // android.graphics.drawable.DrawableContainer, android.graphics.drawable.Drawable
    public void draw(Canvas canvas) {
        Drawable current = getCurrent();
        if (current != null && (current instanceof BitmapDrawable)) {
            super.draw(canvas);
            return;
        }
        if (!this.mCheckWidgetConstantState.touchAnimEnable) {
            CheckWidgetDrawableAnims checkWidgetDrawableAnims = this.mCheckWidgetDrawableAnims;
            if (checkWidgetDrawableAnims != null) {
                checkWidgetDrawableAnims.draw(canvas);
            }
            super.draw(canvas);
            return;
        }
        canvas.setDrawFilter(new PaintFlagsDrawFilter(0, 2));
        if (this.mIsEnabled) {
            CheckWidgetDrawableAnims checkWidgetDrawableAnims2 = this.mCheckWidgetDrawableAnims;
            if (checkWidgetDrawableAnims2 != null) {
                checkWidgetDrawableAnims2.draw(canvas);
            }
            setAlpha((int) (this.mContentAlpha * 255.0f));
        } else {
            setAlpha(76);
        }
        canvas.save();
        Rect bounds = getBounds();
        float f2 = this.mScale;
        canvas.scale(f2, f2, (bounds.left + bounds.right) / 2, (bounds.top + bounds.bottom) / 2);
        super.draw(canvas);
        canvas.restore();
    }

    public int getCheckWidgetDrawableStyle() {
        return R.style.CheckWidgetDrawable_CheckBox;
    }

    public float getContentAlpha() {
        return this.mContentAlpha;
    }

    public float getScale() {
        return this.mScale;
    }

    public boolean isSingleSelectionWidget() {
        return false;
    }

    @Override // miuix.internal.view.CheckWidgetAnimatedStateListDrawable
    public CheckWidgetAnimatedStateListDrawable.CheckWidgetConstantState newCheckWidgetConstantState() {
        return new CheckBoxConstantState();
    }

    @Override // android.graphics.drawable.AnimatedStateListDrawable, android.graphics.drawable.StateListDrawable, android.graphics.drawable.DrawableContainer, android.graphics.drawable.Drawable
    public boolean onStateChange(int[] iArr) {
        boolean zOnStateChange = super.onStateChange(iArr);
        if (this.mCheckWidgetDrawableAnims == null) {
            return zOnStateChange;
        }
        Drawable current = getCurrent();
        if (current != null && (current instanceof BitmapDrawable)) {
            return super.onStateChange(iArr);
        }
        this.mIsEnabled = false;
        boolean z2 = false;
        boolean z3 = false;
        for (int i2 : iArr) {
            if (i2 == 16842919) {
                z2 = true;
            } else if (i2 == 16842912) {
                z3 = true;
            } else if (i2 == 16842910) {
                this.mIsEnabled = true;
            }
        }
        if (z2) {
            startPressedAnim(z3);
        }
        if (!this.mPrePressed && !z2) {
            verifyChecked(z3, this.mIsEnabled);
        }
        if (!z2 && (this.mPrePressed || z3 != this.mPreChecked)) {
            startUnPressedAnim(z3);
        }
        this.mPrePressed = z2;
        this.mPreChecked = z3;
        return zOnStateChange;
    }

    @Override // android.graphics.drawable.Drawable
    public void setBounds(int i2, int i3, int i4, int i5) {
        super.setBounds(i2, i3, i4, i5);
        setCheckWidgetDrawableBounds(i2, i3, i4, i5);
    }

    public void setCheckWidgetDrawableBounds(int i2, int i3, int i4, int i5) {
        CheckWidgetDrawableAnims checkWidgetDrawableAnims = this.mCheckWidgetDrawableAnims;
        if (checkWidgetDrawableAnims != null) {
            checkWidgetDrawableAnims.setBounds(i2, i3, i4, i5);
        }
    }

    public void setContentAlpha(float f2) {
        this.mContentAlpha = f2;
    }

    public void setScale(float f2) {
        this.mScale = f2;
    }

    public void startPressedAnim(boolean z2) {
        CheckWidgetDrawableAnims checkWidgetDrawableAnims = this.mCheckWidgetDrawableAnims;
        if (checkWidgetDrawableAnims != null) {
            checkWidgetDrawableAnims.startPressedAnim(z2, this.mCheckWidgetConstantState.touchAnimEnable);
        }
    }

    public void startUnPressedAnim(boolean z2) {
        CheckWidgetDrawableAnims checkWidgetDrawableAnims = this.mCheckWidgetDrawableAnims;
        if (checkWidgetDrawableAnims != null) {
            checkWidgetDrawableAnims.startUnPressedAnim(z2, this.mCheckWidgetConstantState.touchAnimEnable);
        }
    }

    public void verifyChecked(boolean z2, boolean z3) {
        CheckWidgetDrawableAnims checkWidgetDrawableAnims = this.mCheckWidgetDrawableAnims;
        if (checkWidgetDrawableAnims != null) {
            checkWidgetDrawableAnims.verifyChecked(z2, z3);
            invalidateSelf();
        }
    }

    @Override // android.graphics.drawable.Drawable
    public void setBounds(Rect rect) {
        super.setBounds(rect);
        setCheckWidgetDrawableBounds(rect);
    }

    public void setCheckWidgetDrawableBounds(Rect rect) {
        CheckWidgetDrawableAnims checkWidgetDrawableAnims = this.mCheckWidgetDrawableAnims;
        if (checkWidgetDrawableAnims != null) {
            checkWidgetDrawableAnims.setBounds(rect);
        }
    }

    public CheckBoxAnimatedStateListDrawable(Resources resources, Resources.Theme theme, CheckWidgetAnimatedStateListDrawable.CheckWidgetConstantState checkWidgetConstantState) {
        super(resources, theme, checkWidgetConstantState);
        this.mScale = 1.0f;
        this.mContentAlpha = 1.0f;
        this.mPrePressed = false;
        this.mPreChecked = false;
        this.mCheckWidgetDrawableAnims = new CheckWidgetDrawableAnims(this, isSingleSelectionWidget(), checkWidgetConstantState.grayColor, checkWidgetConstantState.blackColor, checkWidgetConstantState.backGroundColor, checkWidgetConstantState.backgroundNormalAlpha, checkWidgetConstantState.backgroundDisableAlpha, checkWidgetConstantState.strokeColor, checkWidgetConstantState.strokeNormalAlpha, checkWidgetConstantState.strokeDisableAlpha);
    }
}
