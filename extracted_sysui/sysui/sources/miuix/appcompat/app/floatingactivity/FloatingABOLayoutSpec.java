package miuix.appcompat.app.floatingactivity;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Point;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.TypedValue;
import android.view.ContextThemeWrapper;
import android.view.View;
import androidx.constraintlayout.core.widgets.analyzer.BasicMeasure;
import miuix.appcompat.R;
import miuix.appcompat.app.floatingactivity.helper.BaseFloatingActivityHelper;
import miuix.core.util.WindowUtils;
import miuix.internal.util.AttributeResolver;
import miuix.reflect.Reflects;

/* JADX INFO: loaded from: classes2.dex */
public class FloatingABOLayoutSpec {
    private static final String TAG = "FloatingABOLayoutSpec";
    private Context mContext;
    private DisplayMetrics mDisplayMetrics;
    private TypedValue mFixedHeightMajor;
    private TypedValue mFixedHeightMinor;
    private TypedValue mFixedWidthMajor;
    private TypedValue mFixedWidthMinor;
    private boolean mFloatingTheme;
    private boolean mFloatingWindow;
    private boolean mIsInDialogMode;
    private TypedValue mMaxHeightMajor;
    private TypedValue mMaxHeightMinor;
    private TypedValue mMaxWidthMajor;
    private TypedValue mMaxWidthMinor;
    private Point mPhysicalSize;

    public FloatingABOLayoutSpec(Context context) {
        this(context, null);
    }

    private TypedValue getFixedHeightMajor() {
        if (this.mFloatingTheme && this.mFloatingWindow) {
            return this.mFixedHeightMajor;
        }
        return null;
    }

    private TypedValue getFixedHeightMinor() {
        if (this.mFloatingTheme && this.mFloatingWindow) {
            return this.mFixedHeightMinor;
        }
        return null;
    }

    private TypedValue getFixedWidthMajor() {
        if (this.mFloatingTheme && this.mFloatingWindow) {
            return this.mFixedWidthMajor;
        }
        return null;
    }

    private TypedValue getFixedWidthMinor() {
        if (this.mFloatingTheme && this.mFloatingWindow) {
            return this.mFixedWidthMinor;
        }
        return null;
    }

    private TypedValue getMaxHeightMajor() {
        if (this.mFloatingTheme && this.mFloatingWindow) {
            return this.mMaxHeightMajor;
        }
        return null;
    }

    private TypedValue getMaxHeightMinor() {
        if (this.mFloatingTheme && this.mFloatingWindow) {
            return this.mMaxHeightMinor;
        }
        return null;
    }

    private TypedValue getMaxWidthMajor() {
        if (this.mFloatingTheme && this.mFloatingWindow) {
            return this.mMaxWidthMajor;
        }
        return null;
    }

    private TypedValue getMaxWidthMinor() {
        if (this.mFloatingTheme && this.mFloatingWindow) {
            return this.mMaxWidthMinor;
        }
        return null;
    }

    private int getMeasureSpec(int i2, boolean z2, TypedValue typedValue, TypedValue typedValue2, TypedValue typedValue3, TypedValue typedValue4) {
        if (View.MeasureSpec.getMode(i2) != Integer.MIN_VALUE) {
            return i2;
        }
        boolean zIsPortrait = isPortrait();
        if (!zIsPortrait) {
            typedValue = typedValue2;
        }
        int iResolveDimension = resolveDimension(typedValue, z2);
        if (iResolveDimension > 0) {
            return View.MeasureSpec.makeMeasureSpec(iResolveDimension, BasicMeasure.EXACTLY);
        }
        if (!zIsPortrait) {
            typedValue3 = typedValue4;
        }
        int iResolveDimension2 = resolveDimension(typedValue3, z2);
        return iResolveDimension2 > 0 ? View.MeasureSpec.makeMeasureSpec(Math.min(iResolveDimension2, View.MeasureSpec.getSize(i2)), Integer.MIN_VALUE) : i2;
    }

    private int getThemeResourceId(ContextThemeWrapper contextThemeWrapper) {
        try {
            return ((Integer) Reflects.invoke(contextThemeWrapper, Reflects.getMethod(contextThemeWrapper.getClass(), "getThemeResId", (Class<?>[]) null), null)).intValue();
        } catch (RuntimeException e2) {
            Log.w(TAG, "catch theme resource get exception", e2);
            return 0;
        }
    }

    private boolean isPortrait() {
        return WindowUtils.isPortrait(this.mContext);
    }

    private void parseWindowSize(Context context, AttributeSet attributeSet) {
        if (attributeSet == null) {
            return;
        }
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.Window);
        int i2 = R.styleable.Window_windowFixedWidthMinor;
        if (typedArrayObtainStyledAttributes.hasValue(i2)) {
            TypedValue typedValue = new TypedValue();
            this.mFixedWidthMinor = typedValue;
            typedArrayObtainStyledAttributes.getValue(i2, typedValue);
        }
        int i3 = R.styleable.Window_windowFixedHeightMajor;
        if (typedArrayObtainStyledAttributes.hasValue(i3)) {
            TypedValue typedValue2 = new TypedValue();
            this.mFixedHeightMajor = typedValue2;
            typedArrayObtainStyledAttributes.getValue(i3, typedValue2);
        }
        int i4 = R.styleable.Window_windowFixedWidthMajor;
        if (typedArrayObtainStyledAttributes.hasValue(i4)) {
            TypedValue typedValue3 = new TypedValue();
            this.mFixedWidthMajor = typedValue3;
            typedArrayObtainStyledAttributes.getValue(i4, typedValue3);
        }
        int i5 = R.styleable.Window_windowFixedHeightMinor;
        if (typedArrayObtainStyledAttributes.hasValue(i5)) {
            TypedValue typedValue4 = new TypedValue();
            this.mFixedHeightMinor = typedValue4;
            typedArrayObtainStyledAttributes.getValue(i5, typedValue4);
        }
        int i6 = R.styleable.Window_windowMaxWidthMinor;
        if (typedArrayObtainStyledAttributes.hasValue(i6)) {
            TypedValue typedValue5 = new TypedValue();
            this.mMaxWidthMinor = typedValue5;
            typedArrayObtainStyledAttributes.getValue(i6, typedValue5);
        }
        int i7 = R.styleable.Window_windowMaxWidthMajor;
        if (typedArrayObtainStyledAttributes.hasValue(i7)) {
            TypedValue typedValue6 = new TypedValue();
            this.mMaxWidthMajor = typedValue6;
            typedArrayObtainStyledAttributes.getValue(i7, typedValue6);
        }
        int i8 = R.styleable.Window_windowMaxHeightMajor;
        if (typedArrayObtainStyledAttributes.hasValue(i8)) {
            TypedValue typedValue7 = new TypedValue();
            this.mMaxHeightMajor = typedValue7;
            typedArrayObtainStyledAttributes.getValue(i8, typedValue7);
        }
        int i9 = R.styleable.Window_windowMaxHeightMinor;
        if (typedArrayObtainStyledAttributes.hasValue(i9)) {
            TypedValue typedValue8 = new TypedValue();
            this.mMaxHeightMinor = typedValue8;
            typedArrayObtainStyledAttributes.getValue(i9, typedValue8);
        }
        this.mFloatingTheme = typedArrayObtainStyledAttributes.getBoolean(R.styleable.Window_isMiuixFloatingTheme, false);
        this.mFloatingWindow = BaseFloatingActivityHelper.isFloatingWindow(context);
        typedArrayObtainStyledAttributes.recycle();
    }

    private int resolveDimension(TypedValue typedValue, boolean z2) {
        int i2;
        float fraction;
        if (typedValue != null && (i2 = typedValue.type) != 0) {
            if (i2 == 5) {
                fraction = typedValue.getDimension(this.mDisplayMetrics);
            } else if (i2 == 6) {
                Point point = this.mPhysicalSize;
                float f2 = z2 ? point.x : point.y;
                fraction = typedValue.getFraction(f2, f2);
            }
            return (int) fraction;
        }
        return 0;
    }

    public int getHeightMeasureSpec(int i2) {
        return getMeasureSpec(i2, false, getFixedHeightMinor(), getFixedHeightMajor(), getMaxHeightMinor(), getMaxHeightMajor());
    }

    public int getHeightMeasureSpecForDialog(int i2) {
        return getMeasureSpec(i2, false, this.mFixedHeightMinor, this.mFixedHeightMajor, this.mMaxHeightMinor, this.mMaxHeightMajor);
    }

    public int getWidthMeasureSpec(int i2) {
        return getMeasureSpec(i2, true, getFixedWidthMinor(), getFixedWidthMajor(), getMaxWidthMinor(), getMaxWidthMajor());
    }

    public int getWidthMeasureSpecForDialog(int i2) {
        return getMeasureSpec(i2, true, this.mFixedWidthMinor, this.mFixedWidthMajor, this.mMaxWidthMinor, this.mMaxWidthMajor);
    }

    public void onConfigurationChanged() {
        int themeResourceId;
        Context contextThemeWrapper = this.mContext;
        if (this.mIsInDialogMode && (contextThemeWrapper instanceof ContextThemeWrapper) && (themeResourceId = getThemeResourceId((ContextThemeWrapper) contextThemeWrapper)) > 0) {
            contextThemeWrapper = new ContextThemeWrapper(this.mContext.getApplicationContext(), themeResourceId);
        }
        this.mFixedWidthMinor = AttributeResolver.resolveTypedValue(contextThemeWrapper, R.attr.windowFixedWidthMinor);
        this.mFixedHeightMajor = AttributeResolver.resolveTypedValue(contextThemeWrapper, R.attr.windowFixedHeightMajor);
        this.mFixedWidthMajor = AttributeResolver.resolveTypedValue(contextThemeWrapper, R.attr.windowFixedWidthMajor);
        this.mFixedHeightMinor = AttributeResolver.resolveTypedValue(contextThemeWrapper, R.attr.windowFixedHeightMinor);
        this.mMaxWidthMinor = AttributeResolver.resolveTypedValue(contextThemeWrapper, R.attr.windowMaxWidthMinor);
        this.mMaxWidthMajor = AttributeResolver.resolveTypedValue(contextThemeWrapper, R.attr.windowMaxWidthMajor);
        this.mMaxHeightMinor = AttributeResolver.resolveTypedValue(contextThemeWrapper, R.attr.windowMaxHeightMinor);
        this.mMaxHeightMajor = AttributeResolver.resolveTypedValue(contextThemeWrapper, R.attr.windowMaxHeightMajor);
        updatePhysicalSize(contextThemeWrapper);
    }

    public void onFloatingModeChanged(boolean z2) {
        if (this.mFloatingTheme) {
            this.mFloatingWindow = z2;
        }
    }

    public void setIsInDialogMode(boolean z2) {
        this.mIsInDialogMode = z2;
    }

    public void updatePhysicalSize(Context context) {
        this.mDisplayMetrics = context.getResources().getDisplayMetrics();
        this.mPhysicalSize = WindowUtils.getWindowSize(context);
    }

    public FloatingABOLayoutSpec(Context context, AttributeSet attributeSet) {
        this.mFloatingTheme = false;
        this.mFloatingWindow = false;
        this.mContext = context;
        this.mPhysicalSize = new Point();
        updatePhysicalSize(context);
        parseWindowSize(context, attributeSet);
    }
}
