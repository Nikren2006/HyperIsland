package miuix.view;

import android.R;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.TextView;
import androidx.annotation.ColorInt;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.view.ViewCompat;
import miuix.core.util.HyperMaterialUtils;
import miuix.core.util.MaterialConfig;
import miuix.core.util.MiuiBlurUtils;
import miuix.core.util.MiuixUIUtils;
import miuix.internal.util.AttributeResolver;

/* JADX INFO: loaded from: classes5.dex */
public class MiuiBlurUiHelper implements BlurableWidget {
    private boolean mApplyBlur;
    private int[] mBlurBlendColorModes;
    private int[] mBlurBlendColors;
    private int mBlurEffect;
    private final BlurStateCallback mCallback;
    private final Context mContext;
    private final boolean mCreateParamsByCallback;
    private boolean mEnableBlurSelfAsBackground;
    private boolean mIsEnableBlur;
    private final boolean mIsSpecialShape;
    private boolean mIsSupportBlur;
    private boolean mNeedApplyBlur;
    private boolean mNeedEnableBlur;
    private final View mViewApplyBlur;
    private int mViewBlurMode;

    public interface BlurStateCallback {
        @Nullable
        default Drawable getBackground() {
            return null;
        }

        @Nullable
        default MaterialConfig.BlurConfig getBlurConfig(boolean z2) {
            return null;
        }

        default boolean isLightTheme() {
            return true;
        }

        void onBlurApplyStateChanged(boolean z2);

        void onBlurEnableStateChanged(boolean z2);

        default void onCreateBlurParams(MiuiBlurUiHelper miuiBlurUiHelper) {
        }
    }

    public MiuiBlurUiHelper(@NonNull Context context, @NonNull View view, boolean z2, @NonNull BlurStateCallback blurStateCallback) {
        this(context, view, z2, true, blurStateCallback);
    }

    private void applyBlurInternal(boolean z2) {
        if (this.mIsSupportBlur && this.mIsEnableBlur && this.mApplyBlur != z2) {
            this.mApplyBlur = z2;
            if (z2) {
                refreshBlur();
                return;
            }
            MiuiBlurUtils.clearBackgroundBlur(this.mViewApplyBlur);
            MiuiBlurUtils.clearBackgroundBlendConfig(this.mViewApplyBlur);
            this.mCallback.onBlurApplyStateChanged(false);
        }
    }

    private void createBlurParamsInternal() {
        BlurStateCallback blurStateCallback = this.mCallback;
        MaterialConfig.BlurConfig blurConfig = blurStateCallback.getBlurConfig(blurStateCallback.isLightTheme());
        Drawable background = this.mCallback.getBackground();
        if (blurConfig != null) {
            int i2 = blurConfig.blurRadius;
            MaterialConfig.ColorBlendConfig colorBlendConfig = blurConfig.colorBlendConfig;
            if (colorBlendConfig == null) {
                this.mBlurEffect = i2;
                return;
            }
            int[] finalBlendColorForViewByBackgroundColor = getFinalBlendColorForViewByBackgroundColor(this.mContext, background, colorBlendConfig.blendColors, colorBlendConfig.blendModes);
            int length = finalBlendColorForViewByBackgroundColor.length;
            int[] iArr = colorBlendConfig.blendModes;
            if (length > iArr.length) {
                int length2 = finalBlendColorForViewByBackgroundColor.length;
                int[] iArr2 = new int[length2];
                System.arraycopy(iArr, 0, iArr2, 0, iArr.length);
                iArr2[length2 - 1] = 3;
                iArr = iArr2;
            }
            setBlurParams(finalBlendColorForViewByBackgroundColor, iArr, i2);
        }
    }

    public static void enableOnlyTextBlur(@NonNull TextView textView, boolean z2, int i2, @NonNull int[] iArr, @NonNull int[] iArr2) {
        if (textView == null) {
            return;
        }
        Object parent = textView.getParent();
        if (parent != null) {
            if (z2) {
                View view = (View) parent;
                MiuiBlurUtils.setBackgroundBlur(view, i2);
                MiuiBlurUtils.setViewBlurMode(view, 0);
            } else {
                MiuiBlurUtils.clearBackgroundBlur((View) parent);
            }
        }
        enableTextBlur(textView, z2, iArr, iArr2);
    }

    @Deprecated
    public static void enableTextBlur(@NonNull TextView textView, boolean z2, @NonNull int[] iArr, @NonNull int[] iArr2) {
        if (textView == null) {
            return;
        }
        if (z2) {
            MiuiBlurUtils.setViewBlurMode(textView, 3);
            MiuiBlurUtils.setBackgroundBlendConfig(textView, iArr, iArr2);
        } else {
            MiuiBlurUtils.setViewBlurMode(textView, 0);
            MiuiBlurUtils.clearBackgroundBlendConfig(textView);
        }
    }

    public static int[] getFinalBlendColorForViewByBackgroundColor(Context context, @Nullable Drawable drawable, int[] iArr, int[] iArr2) {
        Integer colorFromDrawable;
        return (drawable == null || (colorFromDrawable = MiuixUIUtils.getColorFromDrawable(drawable)) == null) ? iArr : getFinalBlendColorForViewByBackgroundColor(context, colorFromDrawable.intValue(), iArr, iArr2);
    }

    private void setEnableBlurInternal(boolean z2) {
        if (this.mIsEnableBlur != z2) {
            if (!z2) {
                this.mNeedApplyBlur = isApplyBlur();
                applyBlurInternal(false);
            }
            this.mIsEnableBlur = z2;
            this.mCallback.onBlurEnableStateChanged(z2);
            if (z2 && this.mNeedApplyBlur) {
                applyBlurInternal(true);
            }
        }
    }

    @Override // miuix.view.BlurableWidget
    public void applyBlur(boolean z2) {
        this.mNeedApplyBlur = z2;
        applyBlurInternal(z2);
    }

    public void enableBlurSelfAsBackground(boolean z2) {
        this.mEnableBlurSelfAsBackground = z2;
        resetBlurParams();
    }

    public View getViewApplyBlur() {
        return this.mViewApplyBlur;
    }

    @Override // miuix.view.BlurableWidget
    public boolean isApplyBlur() {
        return this.mNeedApplyBlur;
    }

    @Override // miuix.view.BlurableWidget
    public boolean isEnableBlur() {
        return this.mNeedEnableBlur;
    }

    @Override // miuix.view.BlurableWidget
    public boolean isSupportBlur() {
        return this.mIsSupportBlur;
    }

    public void onConfigChanged() {
        resetBlurParams();
        if (!HyperMaterialUtils.isFeatureEnable(this.mContext)) {
            setEnableBlurInternal(false);
        } else if (HyperMaterialUtils.isEnable() && HyperMaterialUtils.isFeatureEnable(this.mContext) && isEnableBlur()) {
            setEnableBlurInternal(true);
        }
    }

    public void refreshBlur() {
        float f2;
        int[] iArr;
        if (this.mApplyBlur) {
            if (this.mBlurBlendColors == null) {
                if (this.mEnableBlurSelfAsBackground) {
                    MiuiBlurUtils.clearBackgroundBlur(this.mViewApplyBlur);
                } else {
                    MiuiBlurUtils.setViewBlurMode(this.mViewApplyBlur, 0);
                }
                MiuiBlurUtils.clearBackgroundBlendConfig(this.mViewApplyBlur);
                if (this.mCreateParamsByCallback) {
                    this.mCallback.onCreateBlurParams(this);
                } else {
                    createBlurParamsInternal();
                }
            }
            try {
                f2 = this.mViewApplyBlur.getContext().getResources().getDisplayMetrics().density;
            } catch (Exception unused) {
                f2 = 2.75f;
            }
            this.mCallback.onBlurApplyStateChanged(true);
            if (this.mEnableBlurSelfAsBackground) {
                int i2 = this.mBlurEffect;
                if (i2 > 0) {
                    MiuiBlurUtils.setBackgroundBlur(this.mViewApplyBlur, (int) ((i2 * f2) + 0.5f), this.mViewBlurMode);
                } else {
                    MiuiBlurUtils.clearBackgroundBlur(this.mViewApplyBlur);
                }
            } else {
                MiuiBlurUtils.setViewBlurMode(this.mViewApplyBlur, this.mViewBlurMode);
                int i3 = this.mBlurEffect;
                if (i3 >= 0) {
                    MiuiBlurUtils.setBackgroundBlurRadius(this.mViewApplyBlur, (int) ((i3 * f2) + 0.5f));
                }
            }
            int[] iArr2 = this.mBlurBlendColors;
            if (iArr2 == null || (iArr = this.mBlurBlendColorModes) == null) {
                return;
            }
            MiuiBlurUtils.setBackgroundBlendConfig(this.mViewApplyBlur, iArr2, iArr);
        }
    }

    public void resetBlurParams() {
        this.mBlurBlendColors = null;
        this.mBlurBlendColorModes = null;
        this.mBlurEffect = 0;
    }

    public void setBlurParams(@NonNull int[] iArr, @NonNull int[] iArr2, int i2) {
        this.mBlurBlendColors = iArr;
        this.mBlurBlendColorModes = iArr2;
        this.mBlurEffect = i2;
    }

    @Override // miuix.view.BlurableWidget
    public void setEnableBlur(boolean z2) {
        if (this.mIsSupportBlur) {
            this.mNeedEnableBlur = z2;
            if (HyperMaterialUtils.isFeatureEnable(this.mContext)) {
                setEnableBlurInternal(this.mNeedEnableBlur);
            }
        }
    }

    @Override // miuix.view.BlurableWidget
    public void setSupportBlur(boolean z2) {
        this.mIsSupportBlur = z2;
    }

    public void setViewBlurMode(int i2) {
        this.mViewBlurMode = i2;
        resetBlurParams();
    }

    public MiuiBlurUiHelper(@NonNull Context context, @NonNull View view, boolean z2, boolean z3, @NonNull BlurStateCallback blurStateCallback) {
        this(context, view, z2, z3, true, blurStateCallback);
    }

    public MiuiBlurUiHelper(@NonNull Context context, @NonNull View view, boolean z2, boolean z3, boolean z4, @NonNull BlurStateCallback blurStateCallback) {
        this.mIsSupportBlur = false;
        this.mNeedEnableBlur = false;
        this.mIsEnableBlur = false;
        this.mNeedApplyBlur = false;
        this.mApplyBlur = false;
        this.mBlurBlendColors = null;
        this.mBlurBlendColorModes = null;
        this.mBlurEffect = 0;
        this.mContext = context;
        this.mCreateParamsByCallback = z3;
        this.mViewApplyBlur = view;
        this.mIsSpecialShape = z2;
        this.mCallback = blurStateCallback;
        this.mEnableBlurSelfAsBackground = z4;
        if (z2) {
            this.mViewBlurMode = 2;
        } else {
            this.mViewBlurMode = 1;
        }
    }

    public static int[] getFinalBlendColorForViewByBackgroundColor(Context context, @ColorInt int i2, int[] iArr, int[] iArr2) {
        System.arraycopy(iArr, 0, iArr, 0, iArr.length);
        if (i2 == 0) {
            Drawable drawableResolveDrawable = AttributeResolver.resolveDrawable(context, R.attr.windowBackground);
            if (drawableResolveDrawable instanceof ColorDrawable) {
                i2 = ((ColorDrawable) drawableResolveDrawable).getColor();
            }
        }
        if (i2 == 0 || i2 == iArr[iArr.length - 1]) {
            return iArr;
        }
        int i3 = (i2 >> 16) & 255;
        int i4 = i2 & 255;
        if (i3 == ((i2 >> 8) & 255) && i3 == i4) {
            int length = iArr.length - 1;
            iArr[length] = (i2 & ViewCompat.MEASURED_SIZE_MASK) | ((-16777216) & iArr[length]);
            return iArr;
        }
        int length2 = iArr.length;
        int[] iArr3 = new int[length2 + 1];
        System.arraycopy(iArr, 0, iArr3, 0, iArr.length);
        iArr3[length2] = (i2 & ViewCompat.MEASURED_SIZE_MASK) | 805306368;
        return iArr3;
    }
}
