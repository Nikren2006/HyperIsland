package miuix.appcompat.internal.widget;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Path;
import android.graphics.Point;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.widget.LinearLayout;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.core.widgets.analyzer.BasicMeasure;
import miuix.appcompat.R;
import miuix.appcompat.app.DialogContract;
import miuix.appcompat.app.strategy.IPanelMeasureRule;
import miuix.appcompat.app.strategy.PanelMeasureRuleImpl;
import miuix.core.util.EnvStateManager;
import miuix.core.util.WindowUtils;
import miuix.internal.util.AttributeResolver;
import miuix.smooth.SmoothCornerHelper;

/* JADX INFO: loaded from: classes3.dex */
public class DialogParentPanel2 extends LinearLayout {
    private static final String TAG = "DialogParentPanel2";
    private ConfigurationChangedCallback mCallback;
    private final Path mClipPath;
    private int mDensityDpi;
    private final FloatingABOLayoutSpec mFloatingWindowSize;
    private final RectF mLayer;
    private int mPanelFixedHeight;
    private int mPanelFixedWidth;
    private float mRadius;

    public interface ConfigurationChangedCallback {
        void onConfigurationChanged(Configuration configuration);
    }

    public static class FloatingABOLayoutSpec {
        private final Context mContext;
        private TypedValue mFixedHeightMajor;
        private TypedValue mFixedHeightMinor;
        private TypedValue mFixedWidthMajor;
        private TypedValue mFixedWidthMinor;
        private TypedValue mFullHeightMajor;
        private boolean mIsDebugEnabled;
        private boolean mIsFlipTinyScreen;
        private boolean mIsFreeWindowMode;
        private TypedValue mMaxHeightMajor;
        private TypedValue mMaxHeightMinor;
        private TypedValue mMaxWidthMajor;
        private TypedValue mMaxWidthMinor;
        private IPanelMeasureRule mMeasureRule;
        private int mPanelMaxLimitHeight;
        private int mScreenHeightDp;
        private final Point mScreenSize = new Point();

        public FloatingABOLayoutSpec(Context context, AttributeSet attributeSet) {
            this.mContext = context;
            parseWindowSize(context, attributeSet);
            this.mScreenHeightDp = getScreenHeightDp();
            this.mIsFreeWindowMode = EnvStateManager.isFreeFormMode(context);
        }

        private int[] getTypedBaseValue(TypedValue typedValue, TypedValue typedValue2, TypedValue typedValue3, TypedValue typedValue4, TypedValue typedValue5, boolean z2) {
            boolean z3 = this.mIsFlipTinyScreen || this.mIsFreeWindowMode;
            return new int[]{resolveDimension(this.mMeasureRule.selectLimitValue(z3, isPortrait(), this.mScreenHeightDp, new DialogContract.ValueList(typedValue, typedValue2, typedValue5)), z2), resolveDimension(this.mMeasureRule.selectLimitValue(z3, isPortrait(), this.mScreenHeightDp, new DialogContract.ValueList(typedValue3, typedValue4, typedValue5)), z2)};
        }

        private boolean isPortrait() {
            Point point = this.mScreenSize;
            int i2 = point.x;
            return (i2 == 0 && point.y == 0) ? WindowUtils.isPortrait(this.mContext) : i2 < point.y;
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
            int i10 = R.styleable.Window_windowFullHeightMajor;
            if (typedArrayObtainStyledAttributes.hasValue(i10)) {
                TypedValue typedValue9 = new TypedValue();
                this.mFullHeightMajor = typedValue9;
                typedArrayObtainStyledAttributes.getValue(i10, typedValue9);
            }
            typedArrayObtainStyledAttributes.recycle();
        }

        private int resolveDimension(TypedValue typedValue, boolean z2) {
            int i2;
            float fraction;
            if (typedValue != null && (i2 = typedValue.type) != 0) {
                if (i2 == 5) {
                    fraction = typedValue.getDimension(this.mContext.getResources().getDisplayMetrics());
                } else if (i2 == 6) {
                    Point point = this.mScreenSize;
                    float f2 = z2 ? point.x : point.y;
                    fraction = typedValue.getFraction(f2, f2);
                }
                return (int) fraction;
            }
            return 0;
        }

        public void flushWindowSizeIfNeed(int i2) {
            if (this.mScreenHeightDp != i2) {
                this.mFixedWidthMinor = AttributeResolver.resolveTypedValue(this.mContext, R.attr.windowFixedWidthMinor);
                this.mFixedHeightMajor = AttributeResolver.resolveTypedValue(this.mContext, R.attr.windowFixedHeightMajor);
                this.mFixedWidthMajor = AttributeResolver.resolveTypedValue(this.mContext, R.attr.windowFixedWidthMajor);
                this.mFixedHeightMinor = AttributeResolver.resolveTypedValue(this.mContext, R.attr.windowFixedHeightMinor);
                this.mMaxWidthMinor = AttributeResolver.resolveTypedValue(this.mContext, R.attr.windowMaxWidthMinor);
                this.mMaxWidthMajor = AttributeResolver.resolveTypedValue(this.mContext, R.attr.windowMaxWidthMajor);
                this.mMaxHeightMinor = AttributeResolver.resolveTypedValue(this.mContext, R.attr.windowMaxHeightMinor);
                this.mFullHeightMajor = AttributeResolver.resolveTypedValue(this.mContext, R.attr.windowFullHeightMajor);
                this.mMaxHeightMajor = AttributeResolver.resolveTypedValue(this.mContext, R.attr.windowMaxHeightMajor);
                this.mScreenHeightDp = i2;
            }
            this.mIsFreeWindowMode = EnvStateManager.isFreeFormMode(this.mContext);
        }

        public int getHeightMeasureSpecForDialog(int i2) {
            boolean z2 = this.mIsFlipTinyScreen || this.mIsFreeWindowMode;
            int[] typedBaseValue = getTypedBaseValue(this.mFixedHeightMinor, this.mFixedHeightMajor, this.mMaxHeightMinor, this.mMaxHeightMajor, this.mFullHeightMajor, false);
            int iMeasurePanelHeight = this.mMeasureRule.measurePanelHeight(i2, typedBaseValue[0], typedBaseValue[1], this.mPanelMaxLimitHeight, z2);
            if (this.mIsDebugEnabled) {
                Log.d(DialogParentPanel2.TAG, "getHeightMeasureSpecForDialog: measuredValue = " + iMeasurePanelHeight + ", size = " + View.MeasureSpec.getSize(i2) + ", fixedValue = " + typedBaseValue[0] + ", maxValue = " + typedBaseValue[1] + ", useMaxLimit = " + z2 + ", mPanelMaxLimitHeight = " + this.mPanelMaxLimitHeight + ", mIsFlipTinyScreen = " + this.mIsFlipTinyScreen + ", mIsFreeWindowMode = " + this.mIsFreeWindowMode);
            }
            return iMeasurePanelHeight;
        }

        public int getScreenHeightDp() {
            WindowUtils.getScreenSize(this.mContext, this.mScreenSize);
            return (int) (this.mScreenSize.y / this.mContext.getResources().getDisplayMetrics().density);
        }

        public int getWidthMeasureSpecForDialog(int i2) {
            int[] typedBaseValue = getTypedBaseValue(this.mFixedWidthMinor, this.mFixedWidthMajor, this.mMaxWidthMinor, this.mMaxWidthMajor, this.mFullHeightMajor, true);
            int iMeasurePanelWidth = this.mMeasureRule.measurePanelWidth(i2, typedBaseValue[0], typedBaseValue[1]);
            if (this.mIsDebugEnabled) {
                Log.d(DialogParentPanel2.TAG, "getWidthMeasureSpecForDialog: measuredValue = " + iMeasurePanelWidth + ", size = " + View.MeasureSpec.getSize(i2) + ", fixedValue = " + typedBaseValue[0] + ", maxValue = " + typedBaseValue[1]);
            }
            return iMeasurePanelWidth;
        }

        public void setIsInTinyScreen(boolean z2) {
            this.mIsFlipTinyScreen = z2;
        }
    }

    public DialogParentPanel2(@NonNull Context context) {
        this(context, null);
    }

    private void clipRoundRect(Canvas canvas) {
        this.mClipPath.reset();
        Path path = this.mClipPath;
        RectF rectF = this.mLayer;
        float f2 = this.mRadius;
        path.addRoundRect(rectF, f2, f2, Path.Direction.CW);
        canvas.clipPath(this.mClipPath);
    }

    private void refresh() {
        invalidateOutline();
        invalidate();
    }

    private void setSmoothCornerEnable(boolean z2) {
        SmoothCornerHelper.setViewSmoothCornerEnable(this, z2);
    }

    @Override // android.view.ViewGroup, android.view.View
    public void dispatchConfigurationChanged(Configuration configuration) {
        super.dispatchConfigurationChanged(configuration);
        ConfigurationChangedCallback configurationChangedCallback = this.mCallback;
        if (configurationChangedCallback != null) {
            configurationChangedCallback.onConfigurationChanged(configuration);
        }
    }

    @Override // android.view.View
    public void draw(Canvas canvas) {
        int iSave = canvas.save();
        clipRoundRect(canvas);
        super.draw(canvas);
        canvas.restoreToCount(iSave);
    }

    public int getPanelMaxLimitHeight() {
        return this.mFloatingWindowSize.mPanelMaxLimitHeight;
    }

    public void notifyConfigurationChanged() {
        this.mFloatingWindowSize.flushWindowSizeIfNeed(this.mFloatingWindowSize.getScreenHeightDp());
    }

    @Override // android.view.View
    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        int i2 = configuration.densityDpi;
        if (i2 != this.mDensityDpi) {
            this.mDensityDpi = i2;
            setCornerRadius(getResources().getDimension(R.dimen.miuix_appcompat_dialog_bg_corner_radius));
        }
        notifyConfigurationChanged();
    }

    @Override // android.widget.LinearLayout, android.view.View
    public void onMeasure(int i2, int i3) {
        notifyConfigurationChanged();
        int i4 = this.mPanelFixedWidth;
        int iMakeMeasureSpec = i4 > 0 ? View.MeasureSpec.makeMeasureSpec(i4, BasicMeasure.EXACTLY) : this.mFloatingWindowSize.getWidthMeasureSpecForDialog(i2);
        int i5 = this.mPanelFixedHeight;
        super.onMeasure(iMakeMeasureSpec, i5 > 0 ? View.MeasureSpec.makeMeasureSpec(i5, BasicMeasure.EXACTLY) : this.mFloatingWindowSize.getHeightMeasureSpecForDialog(i3));
    }

    @Override // android.view.View
    public void onSizeChanged(int i2, int i3, int i4, int i5) {
        super.onSizeChanged(i2, i3, i4, i5);
        this.mLayer.set(0.0f, 0.0f, i2, i3);
    }

    public void setConfigurationChangedCallback(ConfigurationChangedCallback configurationChangedCallback) {
        this.mCallback = configurationChangedCallback;
    }

    public void setCornerRadius(float f2) {
        if (f2 < 0.0f) {
            f2 = 0.0f;
        }
        this.mRadius = f2;
        refresh();
    }

    public void setIsDebugEnabled(boolean z2) {
        this.mFloatingWindowSize.mIsDebugEnabled = z2;
    }

    public void setIsInTinyScreen(boolean z2) {
        FloatingABOLayoutSpec floatingABOLayoutSpec = this.mFloatingWindowSize;
        if (floatingABOLayoutSpec != null) {
            floatingABOLayoutSpec.setIsInTinyScreen(z2);
        }
    }

    public void setPanelFixedHeight(int i2) {
        this.mPanelFixedHeight = i2;
    }

    public void setPanelFixedWidth(int i2) {
        this.mPanelFixedWidth = i2;
    }

    public void setPanelMaxLimitHeight(int i2) {
        this.mFloatingWindowSize.mPanelMaxLimitHeight = i2;
    }

    public DialogParentPanel2(@NonNull Context context, @Nullable AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public DialogParentPanel2(@NonNull Context context, @Nullable AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        this.mLayer = new RectF();
        this.mClipPath = new Path();
        this.mPanelFixedHeight = -1;
        this.mPanelFixedWidth = -1;
        setSmoothCornerEnable(true);
        Resources resources = getResources();
        setCornerRadius(resources.getDimension(R.dimen.miuix_appcompat_dialog_bg_corner_radius));
        this.mDensityDpi = resources.getDisplayMetrics().densityDpi;
        FloatingABOLayoutSpec floatingABOLayoutSpec = new FloatingABOLayoutSpec(context, attributeSet);
        this.mFloatingWindowSize = floatingABOLayoutSpec;
        floatingABOLayoutSpec.mMeasureRule = new PanelMeasureRuleImpl();
    }
}
