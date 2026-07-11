package miuix.core.util;

import android.content.Context;
import android.provider.Settings;
import android.util.Log;
import android.view.View;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import miuix.core.util.MaterialConfig;

/* JADX INFO: loaded from: classes3.dex */
public class HyperMaterialUtils {
    static Boolean DEFAULT_FEATURE_ENABLE = null;
    static Boolean ENABLE_HYPER_MATERIAL = null;
    static int HYPER_MATERIAL_VERSION = -1;
    static Boolean SUPPORT_HYPER_MATERIAL = null;
    private static boolean isForceEnable = true;

    static {
        if (!isForceEnable) {
            Boolean bool = Boolean.FALSE;
            SUPPORT_HYPER_MATERIAL = bool;
            HYPER_MATERIAL_VERSION = -1;
            DEFAULT_FEATURE_ENABLE = bool;
            return;
        }
        SUPPORT_HYPER_MATERIAL = Boolean.valueOf(SystemProperties.get("persist.sys.background_blur_supported", "false"));
        if (RomUtils.getHyperOsVersion() > 1) {
            HYPER_MATERIAL_VERSION = SystemProperties.getInt("persist.sys.advanced_visual_release", -1);
        } else if (RomUtils.getHyperOsVersion() == 1) {
            HYPER_MATERIAL_VERSION = SystemProperties.getInt("persist.sys.background_blur_version", -1);
        }
        DEFAULT_FEATURE_ENABLE = Boolean.valueOf(SystemProperties.get("persist.sys.background_blur_status_default", "false"));
        Log.i("HyperMaterialUtils", "SUPPORT = " + SUPPORT_HYPER_MATERIAL + ", OS = " + RomUtils.getHyperOsVersion() + ", VERSION = " + HYPER_MATERIAL_VERSION + ", DEFAULT = " + DEFAULT_FEATURE_ENABLE);
    }

    public static void applyCommonHorizontalLinearGradientBlur(@NonNull View view, int i2) {
        if (view == null) {
            return;
        }
        MiuiBlurUtils.setBackgroundBlurMode(view, 1);
        MiuiBlurUtils.setViewBlurMode(view, 1);
        MiuiBlurUtils.setBgCommonLinearGradientBlur(view, 2, i2);
    }

    public static void applyCommonVerticalLinearGradientBlur(@NonNull View view, int i2) {
        if (view == null) {
            return;
        }
        MiuiBlurUtils.setBackgroundBlurMode(view, 1);
        MiuiBlurUtils.setViewBlurMode(view, 1);
        MiuiBlurUtils.setBgCommonLinearGradientBlur(view, 1, i2);
    }

    public static void applyContainer(@NonNull View view, int i2) {
        if (view == null) {
            return;
        }
        MiuiBlurUtils.setBackgroundBlur(view, MiuixUIUtils.dp2px(view.getContext(), i2));
    }

    public static void applyElement(@NonNull View view, @Nullable MaterialConfig materialConfig) {
        if (materialConfig == null) {
            clearElement(view);
        } else {
            applyElement(view, materialConfig.getColorBlendConfig(), materialConfig.getBloomStrokeConfig(), materialConfig.getShadowConfig());
        }
    }

    public static void applyElementContent(@NonNull View view, @Nullable MaterialConfig materialConfig) {
        if (materialConfig == null) {
            clearElement(view);
        } else {
            applyElementContent(view, materialConfig.getColorBlendConfig(), materialConfig.getBloomStrokeConfig(), materialConfig.getShadowConfig());
        }
    }

    public static void applyViewMaterial(@NonNull View view, @Nullable MaterialConfig materialConfig) {
        if (materialConfig == null) {
            MiuiBlurUtils.setBlurConfig(view, 0.0f, null);
            MiShadowUtils.clearMiShadow(view);
            HyperBloomStrokeUtils.clearBloomStroke(view);
            return;
        }
        float f2 = view.getContext().getResources().getDisplayMetrics().density;
        MaterialConfig.ColorBlendConfig colorBlendConfig = materialConfig.getColorBlendConfig();
        MaterialConfig.BlurConfig blurConfig = materialConfig.getBlurConfig();
        if (colorBlendConfig != null && blurConfig == null) {
            MiuiBlurUtils.setMixEffectEnabled(view, true);
            MiuiBlurUtils.setViewBlurMode(view, 1);
            MiuiBlurUtils.setBackgroundBlendConfig(view, colorBlendConfig.blendColors, colorBlendConfig.blendModes);
        } else if (blurConfig != null) {
            MiuiBlurUtils.setMixEffectEnabled(view, false);
            MiuiBlurUtils.setBlurConfig(view, f2, blurConfig);
        }
        MaterialConfig.ShadowConfig shadowConfig = materialConfig.getShadowConfig();
        if (shadowConfig != null) {
            MiShadowUtils.setShadowConfig(view, shadowConfig);
        }
        MaterialConfig.BloomStrokeConfig bloomStrokeConfig = materialConfig.getBloomStrokeConfig();
        if (bloomStrokeConfig != null) {
            HyperBloomStrokeUtils.setBloomStrokeConfig(view, bloomStrokeConfig);
        }
    }

    public static void clearContainer(@NonNull View view) {
        if (view == null) {
            return;
        }
        MiuiBlurUtils.clearBackgroundBlur(view);
    }

    public static void clearElement(@NonNull View view) {
        if (view == null) {
            return;
        }
        MiuiBlurUtils.setViewBlurMode(view, 0);
        MiuiBlurUtils.clearBackgroundBlendConfig(view);
        HyperBloomStrokeUtils.clearBloomStroke(view);
        MiShadowUtils.clearMiShadow(view);
    }

    public static synchronized void clearFeatureEnable() {
        ENABLE_HYPER_MATERIAL = null;
    }

    public static void clearLinearGradientBlur(@NonNull View view) {
        if (view == null) {
            return;
        }
        MiuiBlurUtils.setBackgroundBlurMode(view, 0);
        MiuiBlurUtils.setViewBlurMode(view, 0);
        MiuiBlurUtils.clearBackgroundGradientBlur(view);
    }

    public static int getVersion() {
        return HYPER_MATERIAL_VERSION;
    }

    public static boolean isDefaultFeatureEnable() {
        return DEFAULT_FEATURE_ENABLE.booleanValue();
    }

    public static boolean isEnable() {
        return SUPPORT_HYPER_MATERIAL.booleanValue();
    }

    public static synchronized boolean isFeatureEnable(@Nullable Context context) {
        if (!SUPPORT_HYPER_MATERIAL.booleanValue()) {
            return false;
        }
        Boolean bool = ENABLE_HYPER_MATERIAL;
        if (bool != null) {
            return bool.booleanValue();
        }
        if (context == null) {
            return false;
        }
        Boolean boolValueOf = Boolean.valueOf(Settings.Secure.getInt(context.getContentResolver(), "background_blur_enable", 0) == 1);
        ENABLE_HYPER_MATERIAL = boolValueOf;
        return boolValueOf.booleanValue();
    }

    public static synchronized boolean isFeatureEnableNoCache(Context context) {
        if (!SUPPORT_HYPER_MATERIAL.booleanValue()) {
            return false;
        }
        if (context == null) {
            return false;
        }
        return Settings.Secure.getInt(context.getContentResolver(), "background_blur_enable", 0) == 1;
    }

    public static void mixColorBlendElements(@NonNull View view, @NonNull MaterialConfig.ColorBlendConfig colorBlendConfig, MaterialConfig.ColorBlendConfig.Element... elementArr) {
        if (colorBlendConfig == null || elementArr == null || elementArr.length == 0) {
            return;
        }
        int length = 0;
        int length2 = 0;
        for (MaterialConfig.ColorBlendConfig.Element element : elementArr) {
            MaterialConfig.ColorBlendConfig colorBlendConfig2 = element.colorBlendConfig;
            length += colorBlendConfig2.blendColors.length;
            length2 += colorBlendConfig2.blendModes.length;
        }
        if (colorBlendConfig.blendColors.length != length) {
            colorBlendConfig.blendColors = new int[length];
        }
        if (colorBlendConfig.blendModes.length != length2) {
            colorBlendConfig.blendModes = new int[length2];
        }
        int i2 = 0;
        int i3 = 0;
        for (int i4 = 0; i4 < elementArr.length; i4++) {
            MaterialConfig.ColorBlendConfig.Element element2 = elementArr[i4];
            int[] iArr = element2.elementColors;
            int[] iArr2 = element2.colorBlendConfig.blendModes;
            for (int i5 = 0; i5 < iArr.length; i5++) {
                colorBlendConfig.blendColors[i2] = iArr[i4];
                i2++;
            }
            for (int i6 = 0; i6 < iArr2.length; i6++) {
                colorBlendConfig.blendModes[i3] = iArr2[i4];
                i3++;
            }
        }
        MiuiBlurUtils.setBackgroundBlendConfig(view, colorBlendConfig.blendColors, colorBlendConfig.blendModes);
    }

    public static synchronized void setFeatureEnable(Boolean bool) {
        ENABLE_HYPER_MATERIAL = bool;
    }

    public static void updateBlurEffect(@NonNull View view, int i2, float f2) {
        if (view == null) {
            return;
        }
        MiuiBlurUtils.setBackgroundBlurRadius(view, i2);
        MiuiBlurUtils.setBackgroundBlurScaleRatio(view, f2);
    }

    public static void updateBlurRadius(@NonNull View view, int i2) {
        if (view == null) {
            return;
        }
        MiuiBlurUtils.setBackgroundBlurRadius(view, i2);
    }

    public static void updateBlurScaleRatio(@NonNull View view, float f2) {
        if (view == null) {
            return;
        }
        MiuiBlurUtils.setBackgroundBlurScaleRatio(view, f2);
    }

    public static void applyContainer(@NonNull View view, @Nullable MaterialConfig.BlurConfig blurConfig) {
        if (view == null || blurConfig == null) {
            return;
        }
        MiuiBlurUtils.setBlurConfig(view, view.getResources().getDisplayMetrics().density, blurConfig);
    }

    public static void applyElement(@NonNull View view, @Nullable MaterialConfig.ColorBlendConfig colorBlendConfig) {
        applyElement(view, colorBlendConfig, (MaterialConfig.BloomStrokeConfig) null, (MaterialConfig.ShadowConfig) null);
    }

    public static void applyElementContent(@NonNull View view, @Nullable MaterialConfig.ColorBlendConfig colorBlendConfig) {
        applyElementContent(view, colorBlendConfig, null, null);
    }

    public static void applyElement(@NonNull View view, @Nullable MaterialConfig materialConfig, @Nullable MaterialConfig.BloomStrokeConfig bloomStrokeConfig) {
        if (materialConfig != null) {
            applyElement(view, materialConfig.getColorBlendConfig(), bloomStrokeConfig, (MaterialConfig.ShadowConfig) null);
        }
    }

    public static void applyElementContent(@NonNull View view, @Nullable MaterialConfig.ColorBlendConfig colorBlendConfig, @Nullable MaterialConfig.BloomStrokeConfig bloomStrokeConfig) {
        applyElementContent(view, colorBlendConfig, bloomStrokeConfig, null);
    }

    public static void applyElement(@NonNull View view, @Nullable MaterialConfig materialConfig, @Nullable float[] fArr) {
        if (materialConfig != null) {
            applyElement(view, materialConfig.getColorBlendConfig(), fArr, (MaterialConfig.ShadowConfig) null);
        }
    }

    public static void applyElementContent(@NonNull View view, @Nullable MaterialConfig.ColorBlendConfig colorBlendConfig, @Nullable MaterialConfig.ShadowConfig shadowConfig) {
        applyElementContent(view, colorBlendConfig, null, shadowConfig);
    }

    public static void applyElement(@NonNull View view, @Nullable MaterialConfig.ColorBlendConfig colorBlendConfig, @Nullable MaterialConfig.BloomStrokeConfig bloomStrokeConfig) {
        applyElement(view, colorBlendConfig, bloomStrokeConfig, (MaterialConfig.ShadowConfig) null);
    }

    public static void applyElementContent(@NonNull View view, @Nullable MaterialConfig.ColorBlendConfig colorBlendConfig, @Nullable MaterialConfig.BloomStrokeConfig bloomStrokeConfig, @Nullable MaterialConfig.ShadowConfig shadowConfig) {
        if (view == null) {
            return;
        }
        if (colorBlendConfig != null) {
            MiuiBlurUtils.setViewBlurMode(view, 3);
            MiuiBlurUtils.setBackgroundBlendConfig(view, colorBlendConfig.blendColors, colorBlendConfig.blendModes);
        }
        if (bloomStrokeConfig != null) {
            HyperBloomStrokeUtils.setBloomStrokeConfig(view, bloomStrokeConfig);
        }
        if (shadowConfig != null) {
            MiShadowUtils.setShadowConfig(view, shadowConfig);
        }
    }

    public static void applyElement(@NonNull View view, @Nullable MaterialConfig.ColorBlendConfig colorBlendConfig, @Nullable MaterialConfig.ShadowConfig shadowConfig) {
        applyElement(view, colorBlendConfig, (MaterialConfig.BloomStrokeConfig) null, shadowConfig);
    }

    public static void applyElement(@NonNull View view, @Nullable MaterialConfig materialConfig, @Nullable float[] fArr, @Nullable MaterialConfig.ShadowConfig shadowConfig) {
        if (materialConfig != null) {
            applyElement(view, materialConfig.getColorBlendConfig(), fArr, shadowConfig);
        }
    }

    public static void applyElement(@NonNull View view, @Nullable MaterialConfig.ColorBlendConfig colorBlendConfig, @Nullable MaterialConfig.BloomStrokeConfig bloomStrokeConfig, @Nullable MaterialConfig.ShadowConfig shadowConfig) {
        if (view == null) {
            return;
        }
        if (colorBlendConfig != null) {
            MiuiBlurUtils.setViewBlurMode(view, 1);
            MiuiBlurUtils.setBackgroundBlendConfig(view, colorBlendConfig.blendColors, colorBlendConfig.blendModes);
        }
        if (bloomStrokeConfig != null) {
            HyperBloomStrokeUtils.setBloomStrokeConfig(view, bloomStrokeConfig);
        }
        if (shadowConfig != null) {
            MiShadowUtils.setShadowConfig(view, shadowConfig);
        }
    }

    public static void applyElement(@NonNull View view, @Nullable MaterialConfig.ColorBlendConfig colorBlendConfig, @Nullable float[] fArr, @Nullable MaterialConfig.ShadowConfig shadowConfig) {
        if (view == null) {
            return;
        }
        if (colorBlendConfig != null) {
            MiuiBlurUtils.setViewBlurMode(view, 1);
            MiuiBlurUtils.setBackgroundBlendConfig(view, colorBlendConfig.blendColors, colorBlendConfig.blendModes);
        }
        if (fArr != null) {
            HyperBloomStrokeUtils.setBloomStrokeWithDp(view, fArr);
        }
        if (shadowConfig != null) {
            MiShadowUtils.setShadowConfig(view, shadowConfig);
        }
    }
}
