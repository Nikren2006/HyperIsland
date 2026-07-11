package miuix.core.util;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Point;
import android.util.Log;
import android.util.Pair;
import android.view.View;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import miuix.core.util.MaterialConfig;

/* JADX INFO: loaded from: classes3.dex */
public class MiuiBlurUtils {
    public static final int BG_MODE_ENABLE = 1;
    public static final int BG_MODE_ENABLE_NOT_DRAW = 3;
    public static final int BG_MODE_NONE = 0;
    public static final int BG_MODE_ONLY_BLUR = 2;
    public static final int CONTENT_MODE_ENABLE = 1;
    public static final int CONTENT_MODE_ENABLE_ANY_SHAPE = 2;
    public static final int CONTENT_MODE_ENABLE_SELF = 3;
    public static final int CONTENT_MODE_ENABLE_SELF_LIGHT_BLEND = 4;
    public static final int CONTENT_MODE_NONE = 0;
    public static final int GRADIENT_ORI_HORI = 2;
    public static final int GRADIENT_ORI_VER = 1;
    public static Method METHOD_ADD_BG_BLEND_COLOR = null;
    public static Method METHOD_CHOOSE_BG_BLUR_CONTAINER = null;
    public static Method METHOD_CLEAR_BG_BLEND_COLOR = null;
    public static Method METHOD_DISABLE_BG_CONTAIN_BELOW = null;
    public static Method METHOD_GET_BG_BLUR_MODE = null;
    public static Method METHOD_GET_BG_BLUR_RADIUS = null;
    public static Method METHOD_GET_MIX_EFFECT_ENABLED = null;
    public static Method METHOD_GET_PASS_WINDOW_BLUR_MODE = null;
    public static Method METHOD_GET_SELF_BLUR_TYPE = null;
    public static Method METHOD_SET_BG_BLEND_COLORS = null;
    public static Method METHOD_SET_BG_BLUR_ENHANCE_FLAG = null;
    public static Method METHOD_SET_BG_BLUR_MODE = null;
    public static Method METHOD_SET_BG_BLUR_RADIUS = null;
    public static Method METHOD_SET_BG_BLUR_SCALE_RATIO = null;
    public static Method METHOD_SET_BG_BLUR_TYPE = null;
    public static Method METHOD_SET_BG_GRADIENT_BLUR_PARAMS = null;
    public static Method METHOD_SET_BG_LIGHT_BLEND_MODE = null;
    public static Method METHOD_SET_COLOR_ADJUST = null;
    public static Method METHOD_SET_MIX_EFFECT_ENABLED = null;
    public static Method METHOD_SET_PASS_TEXTURE_SCALE = null;
    public static Method METHOD_SET_PASS_WINDOW_BLUR_MODE = null;
    public static Method METHOD_SET_SELF_BLUR = null;
    public static Method METHOD_SET_SELF_BLUR_TYPE = null;
    public static Method METHOD_SET_SELF_GRADIENT_BLUR_PARAMS = null;
    public static Method METHOD_SET_VIEW_BLUR_MODE = null;
    public static final int TYPE_GAUSS = 0;
    public static final int TYPE_GRADIENT = 2;
    public static final int TYPE_KAWASSE = 1;

    @Deprecated
    public static boolean addBackgroundBlenderColor(@NonNull View view, int i2, int i3) {
        if (view == null || !HyperMaterialUtils.isEnable()) {
            return false;
        }
        try {
            if (METHOD_ADD_BG_BLEND_COLOR == null) {
                Class cls = Integer.TYPE;
                METHOD_ADD_BG_BLEND_COLOR = View.class.getMethod("addMiBackgroundBlendColor", cls, cls);
            }
            METHOD_ADD_BG_BLEND_COLOR.invoke(view, Integer.valueOf(i2), Integer.valueOf(i3));
            return true;
        } catch (Exception unused) {
            METHOD_ADD_BG_BLEND_COLOR = null;
            return false;
        }
    }

    public static boolean chooseBackgroundBlurContainer(@NonNull View view, @Nullable View view2) {
        if (view == null || !HyperMaterialUtils.isEnable()) {
            return false;
        }
        try {
            if (METHOD_CHOOSE_BG_BLUR_CONTAINER == null) {
                METHOD_CHOOSE_BG_BLUR_CONTAINER = View.class.getMethod("chooseBackgroundBlurContainer", View.class);
            }
            METHOD_CHOOSE_BG_BLUR_CONTAINER.invoke(view, view2);
            return true;
        } catch (Exception unused) {
            METHOD_CHOOSE_BG_BLUR_CONTAINER = null;
            return false;
        }
    }

    public static boolean clearBackgroundBlendConfig(View view) {
        if (!HyperMaterialUtils.isEnable()) {
            return false;
        }
        try {
            if (METHOD_CLEAR_BG_BLEND_COLOR == null) {
                METHOD_CLEAR_BG_BLEND_COLOR = View.class.getMethod("clearMiBackgroundBlendColor", null);
            }
            METHOD_CLEAR_BG_BLEND_COLOR.invoke(view, null);
            return true;
        } catch (Exception unused) {
            METHOD_CLEAR_BG_BLEND_COLOR = null;
            return false;
        }
    }

    public static boolean clearBackgroundBlur(View view) {
        if (setBackgroundBlurMode(view, 0)) {
            return setViewBlurMode(view, 0);
        }
        return false;
    }

    public static boolean clearBackgroundBlurContainer(@NonNull View view) {
        if (view == null || !HyperMaterialUtils.isEnable()) {
            return false;
        }
        try {
            if (METHOD_CHOOSE_BG_BLUR_CONTAINER == null) {
                METHOD_CHOOSE_BG_BLUR_CONTAINER = View.class.getMethod("chooseBackgroundBlurContainer", View.class);
            }
            METHOD_CHOOSE_BG_BLUR_CONTAINER.invoke(view, null);
            return true;
        } catch (Exception unused) {
            METHOD_CHOOSE_BG_BLUR_CONTAINER = null;
            return false;
        }
    }

    public static boolean clearBackgroundBlurEnhanceFlag(@NonNull View view, int i2) {
        return setBackgroundBlurEnhanceFlag(view, 0, i2);
    }

    public static boolean clearBackgroundGradientBlur(@NonNull View view) {
        if (!HyperMaterialUtils.isEnable() || !HyperMaterialUtils.isDefaultFeatureEnable()) {
            return false;
        }
        try {
            if (METHOD_SET_BG_BLUR_TYPE == null) {
                METHOD_SET_BG_BLUR_TYPE = View.class.getMethod("setMiBackgroundBlurType", Integer.TYPE);
            }
            METHOD_SET_BG_BLUR_TYPE.invoke(view, 0);
            return true;
        } catch (Exception unused) {
            METHOD_SET_BG_BLUR_TYPE = null;
            return false;
        }
    }

    public static boolean clearBackgroundLightBlendMode(View view) {
        if (!HyperMaterialUtils.isEnable()) {
            return false;
        }
        try {
            if (METHOD_SET_BG_LIGHT_BLEND_MODE == null) {
                METHOD_SET_BG_LIGHT_BLEND_MODE = View.class.getMethod("setMiBackgroundLightBlendMode", Integer.TYPE);
            }
            METHOD_SET_BG_LIGHT_BLEND_MODE.invoke(view, 0);
            return true;
        } catch (Exception unused) {
            METHOD_SET_BG_LIGHT_BLEND_MODE = null;
            return false;
        }
    }

    @Deprecated
    public static synchronized void clearEffectEnable() {
        HyperMaterialUtils.clearFeatureEnable();
    }

    public static boolean disableBackgroundContainBelow(@NonNull View view, boolean z2) {
        if (view == null || !HyperMaterialUtils.isEnable()) {
            return false;
        }
        try {
            if (METHOD_DISABLE_BG_CONTAIN_BELOW == null) {
                METHOD_DISABLE_BG_CONTAIN_BELOW = View.class.getMethod("disableMiBackgroundContainBelow", Boolean.TYPE);
            }
            METHOD_DISABLE_BG_CONTAIN_BELOW.invoke(view, Boolean.valueOf(z2));
            return true;
        } catch (Exception unused) {
            METHOD_DISABLE_BG_CONTAIN_BELOW = null;
            return false;
        }
    }

    public static void enableContentBlur(@NonNull View view, boolean z2, @NonNull MaterialConfig.ColorBlendConfig colorBlendConfig) {
        if (view == null) {
            return;
        }
        if (z2) {
            setViewBlurMode(view, 3);
            setBackgroundBlendConfig(view, colorBlendConfig.blendColors, colorBlendConfig.blendModes);
        } else {
            setViewBlurMode(view, 0);
            clearBackgroundBlendConfig(view);
        }
    }

    public static int getBackgroundBlurMode(View view) {
        if (!HyperMaterialUtils.isEnable()) {
            return 0;
        }
        try {
            if (METHOD_GET_BG_BLUR_MODE == null) {
                METHOD_GET_BG_BLUR_MODE = View.class.getMethod("getMiBackgroundBlurMode", null);
            }
            return ((Integer) METHOD_GET_BG_BLUR_MODE.invoke(view, null)).intValue();
        } catch (Exception unused) {
            METHOD_GET_BG_BLUR_MODE = null;
            return 0;
        }
    }

    public static int getBackgroundBlurRadius(View view) {
        if (!HyperMaterialUtils.isEnable()) {
            return 0;
        }
        try {
            if (METHOD_GET_BG_BLUR_RADIUS == null) {
                METHOD_GET_BG_BLUR_RADIUS = View.class.getMethod("getMiBackgroundBlurRadius", null);
            }
            return ((Integer) METHOD_GET_BG_BLUR_RADIUS.invoke(view, null)).intValue();
        } catch (Exception unused) {
            METHOD_GET_BG_BLUR_RADIUS = null;
            return 0;
        }
    }

    public static boolean getMixEffectEnabled(@NonNull View view) {
        if (!HyperMaterialUtils.isEnable()) {
            return false;
        }
        try {
            if (METHOD_GET_MIX_EFFECT_ENABLED == null) {
                METHOD_GET_MIX_EFFECT_ENABLED = View.class.getMethod("getMixEffectEnabled", null);
            }
            Object objInvoke = METHOD_GET_MIX_EFFECT_ENABLED.invoke(view, null);
            if (objInvoke instanceof Boolean) {
                return ((Boolean) objInvoke).booleanValue();
            }
            return false;
        } catch (Exception unused) {
            METHOD_GET_MIX_EFFECT_ENABLED = null;
            return false;
        }
    }

    public static boolean getPassWindowBlurEnabled(@NonNull View view) {
        if (!HyperMaterialUtils.isEnable()) {
            return false;
        }
        try {
            if (METHOD_GET_PASS_WINDOW_BLUR_MODE == null) {
                METHOD_GET_PASS_WINDOW_BLUR_MODE = View.class.getMethod("getPassWindowBlurEnabled", null);
            }
            Object objInvoke = METHOD_GET_PASS_WINDOW_BLUR_MODE.invoke(view, null);
            if (objInvoke instanceof Boolean) {
                return ((Boolean) objInvoke).booleanValue();
            }
        } catch (Exception unused) {
            METHOD_GET_PASS_WINDOW_BLUR_MODE = null;
        }
        return false;
    }

    public static boolean getSelfBlurType(@NonNull View view) {
        if (!HyperMaterialUtils.isEnable()) {
            return false;
        }
        try {
            if (METHOD_GET_SELF_BLUR_TYPE == null) {
                METHOD_GET_SELF_BLUR_TYPE = View.class.getMethod("getMiSelfBlurType", null);
            }
            Object objInvoke = METHOD_GET_SELF_BLUR_TYPE.invoke(view, null);
            if (objInvoke instanceof Boolean) {
                return ((Boolean) objInvoke).booleanValue();
            }
            return false;
        } catch (Exception unused) {
            METHOD_GET_SELF_BLUR_TYPE = null;
            return false;
        }
    }

    @Deprecated
    public static synchronized boolean isEffectEnable(Context context) {
        if (!HyperMaterialUtils.isEnable()) {
            return false;
        }
        return HyperMaterialUtils.isFeatureEnable(context);
    }

    @Deprecated
    public static boolean isEnable() {
        return HyperMaterialUtils.isEnable();
    }

    public static boolean setBackgroundBlendConfig(@NonNull View view, @NonNull MaterialConfig.BlurConfig blurConfig) {
        MaterialConfig.ColorBlendConfig colorBlendConfig;
        if (blurConfig == null || (colorBlendConfig = blurConfig.colorBlendConfig) == null) {
            return false;
        }
        return setBackgroundBlendConfig(view, colorBlendConfig);
    }

    public static boolean setBackgroundBlur(@NonNull View view, int i2) {
        return setBackgroundBlur(view, i2, false);
    }

    public static boolean setBackgroundBlurEnhanceFlag(@NonNull View view, int i2) {
        return setBackgroundBlurEnhanceFlag(view, 1, i2);
    }

    public static boolean setBackgroundBlurMode(View view, int i2) {
        if (!HyperMaterialUtils.isEnable()) {
            return false;
        }
        try {
            if (METHOD_SET_BG_BLUR_MODE == null) {
                METHOD_SET_BG_BLUR_MODE = View.class.getMethod("setMiBackgroundBlurMode", Integer.TYPE);
            }
            METHOD_SET_BG_BLUR_MODE.invoke(view, Integer.valueOf(i2));
            return true;
        } catch (Exception unused) {
            METHOD_SET_BG_BLUR_MODE = null;
            return false;
        }
    }

    public static boolean setBackgroundBlurRadius(View view, int i2) {
        if (!HyperMaterialUtils.isEnable()) {
            return false;
        }
        if (i2 > 400) {
            i2 = 400;
        }
        if (i2 < 0) {
            return false;
        }
        try {
            if (METHOD_SET_BG_BLUR_RADIUS == null) {
                METHOD_SET_BG_BLUR_RADIUS = View.class.getMethod("setMiBackgroundBlurRadius", Integer.TYPE);
            }
            METHOD_SET_BG_BLUR_RADIUS.invoke(view, Integer.valueOf(i2));
            return true;
        } catch (Exception unused) {
            METHOD_SET_BG_BLUR_RADIUS = null;
            return false;
        }
    }

    public static boolean setBackgroundBlurScaleRatio(@NonNull View view, float f2) {
        if (!HyperMaterialUtils.isEnable()) {
            return false;
        }
        float fMax = Math.max(0.0f, Math.min(1.0f, f2));
        try {
            if (METHOD_SET_BG_BLUR_SCALE_RATIO == null) {
                METHOD_SET_BG_BLUR_SCALE_RATIO = View.class.getMethod("setMiBackgroundBlurScaleRatio", Float.TYPE);
            }
            METHOD_SET_BG_BLUR_SCALE_RATIO.invoke(view, Float.valueOf(fMax));
            return true;
        } catch (Exception unused) {
            METHOD_SET_BG_BLUR_SCALE_RATIO = null;
            return false;
        }
    }

    public static boolean setBackgroundBlurType(View view, int i2) {
        if (!HyperMaterialUtils.isEnable()) {
            return false;
        }
        try {
            if (METHOD_SET_BG_BLUR_TYPE == null) {
                METHOD_SET_BG_BLUR_TYPE = View.class.getMethod("setMiBackgroundBlurType", Integer.TYPE);
            }
            METHOD_SET_BG_BLUR_TYPE.invoke(view, Integer.valueOf(i2));
            return true;
        } catch (Exception unused) {
            METHOD_SET_BG_BLUR_TYPE = null;
            return false;
        }
    }

    public static boolean setBackgroundGradientBlur(@NonNull View view, @Nullable float[] fArr, int i2) {
        if (!HyperMaterialUtils.isEnable() || !HyperMaterialUtils.isDefaultFeatureEnable()) {
            return false;
        }
        try {
            if (METHOD_SET_BG_BLUR_TYPE == null) {
                METHOD_SET_BG_BLUR_TYPE = View.class.getMethod("setMiBackgroundBlurType", Integer.TYPE);
            }
            METHOD_SET_BG_BLUR_TYPE.invoke(view, 2);
            try {
                if (METHOD_SET_BG_GRADIENT_BLUR_PARAMS == null) {
                    METHOD_SET_BG_GRADIENT_BLUR_PARAMS = View.class.getMethod("setBackgroundGradientBlurParams", float[].class, Integer.TYPE);
                }
                METHOD_SET_BG_GRADIENT_BLUR_PARAMS.invoke(view, fArr, Integer.valueOf(i2));
                return true;
            } catch (Exception unused) {
                METHOD_SET_BG_GRADIENT_BLUR_PARAMS = null;
                return false;
            }
        } catch (Exception unused2) {
            METHOD_SET_BG_BLUR_TYPE = null;
            return false;
        }
    }

    public static boolean setBackgroundGradientBlurParams(@NonNull View view, float[] fArr, int i2) {
        if (!HyperMaterialUtils.isEnable() || !HyperMaterialUtils.isDefaultFeatureEnable()) {
            return false;
        }
        try {
            if (METHOD_SET_BG_GRADIENT_BLUR_PARAMS == null) {
                METHOD_SET_BG_GRADIENT_BLUR_PARAMS = View.class.getMethod("setBackgroundGradientBlurParams", float[].class, Integer.TYPE);
            }
            METHOD_SET_BG_GRADIENT_BLUR_PARAMS.invoke(view, fArr, Integer.valueOf(i2));
            return true;
        } catch (Exception unused) {
            METHOD_SET_BG_GRADIENT_BLUR_PARAMS = null;
            return false;
        }
    }

    public static boolean setBackgroundLightBlendMode(View view, int i2) {
        if (!HyperMaterialUtils.isEnable()) {
            return false;
        }
        try {
            if (METHOD_SET_BG_LIGHT_BLEND_MODE == null) {
                METHOD_SET_BG_LIGHT_BLEND_MODE = View.class.getMethod("setMiBackgroundLightBlendMode", Integer.TYPE);
            }
            METHOD_SET_BG_LIGHT_BLEND_MODE.invoke(view, Integer.valueOf(i2));
            return true;
        } catch (Exception unused) {
            METHOD_SET_BG_LIGHT_BLEND_MODE = null;
            return false;
        }
    }

    public static boolean setBackgroundLinearGradientBlur(@NonNull View view, float[] fArr) {
        return setBackgroundGradientBlur(view, fArr, 1);
    }

    public static boolean setBackgroundMultiRadialGradientBlur(@NonNull View view, float[] fArr) {
        return setBackgroundGradientBlur(view, fArr, 2);
    }

    public static boolean setBackgroundOnlyBlur(@NonNull View view, int i2) {
        if (view == null || !HyperMaterialUtils.isEnable() || !HyperMaterialUtils.isFeatureEnable(view.getContext())) {
            return false;
        }
        if (i2 > 400) {
            i2 = 400;
        }
        try {
            if (METHOD_SET_BG_BLUR_MODE == null) {
                METHOD_SET_BG_BLUR_MODE = View.class.getMethod("setMiBackgroundBlurMode", Integer.TYPE);
            }
            METHOD_SET_BG_BLUR_MODE.invoke(view, 1);
            if (i2 >= 0) {
                if (METHOD_SET_BG_BLUR_RADIUS == null) {
                    METHOD_SET_BG_BLUR_RADIUS = View.class.getMethod("setMiBackgroundBlurRadius", Integer.TYPE);
                }
                METHOD_SET_BG_BLUR_RADIUS.invoke(view, Integer.valueOf(i2));
            }
            return true;
        } catch (Exception unused) {
            METHOD_SET_BG_BLUR_MODE = null;
            METHOD_SET_BG_BLUR_RADIUS = null;
            return false;
        }
    }

    public static boolean setBgCommonLinearGradientBlur(@NonNull View view, int i2, float f2) {
        if (view == null) {
            return false;
        }
        return setBgCommonLinearGradientBlur(view, i2, f2, 255.0f);
    }

    public static boolean setBgCommonLinearGradientBlurWithDp(@NonNull View view, int i2, float f2) {
        Resources resources;
        if (view == null || (resources = view.getResources()) == null) {
            return false;
        }
        return setBgCommonLinearGradientBlur(view, i2, (f2 * resources.getDisplayMetrics().density) + 0.5f);
    }

    public static void setBlurConfig(@NonNull View view, float f2, @Nullable MaterialConfig.BlurConfig blurConfig) {
        float[] fArr;
        int[] iArr;
        int[] iArr2;
        int i2 = 0;
        if (blurConfig == null) {
            setViewBlurMode(view, 0);
            setBackgroundBlurType(view, 0);
            clearBackgroundBlendConfig(view);
            return;
        }
        int i3 = (int) ((blurConfig.blurRadius * f2) + 0.5f);
        int i4 = blurConfig.blurBgMode;
        if (i4 > 0) {
            setBackgroundBlur(view, i3, i4);
        }
        int i5 = blurConfig.blurContentMode;
        if (i5 > 0) {
            if (i5 == 1 || i5 == 2 || i5 == 3) {
                setViewBlurMode(view, i5);
                setBackgroundBlurRadius(view, i3);
            } else if (i5 == 4 && blurConfig.colorBlendConfig != null) {
                setBackgroundLightBlendMode(view, 1);
            }
        }
        MaterialConfig.ColorBlendConfig colorBlendConfig = blurConfig.colorBlendConfig;
        if (colorBlendConfig != null && (iArr = colorBlendConfig.blendColors) != null && (iArr2 = colorBlendConfig.blendModes) != null) {
            setBackgroundBlendConfig(view, wrapBlendConfig(iArr, iArr2));
            float[] fArr2 = colorBlendConfig.blendExtraParams;
            if (fArr2 != null) {
                int length = fArr2.length / 7;
                ArrayList arrayList = new ArrayList();
                while (i2 < length) {
                    int i6 = i2 + 1;
                    arrayList.add(new Pair(Integer.valueOf(i6), Arrays.copyOfRange(fArr2, i2, i2 + 6)));
                    i2 = i6;
                }
                setColorAdjust(view, arrayList);
            }
        }
        setBackgroundBlurType(view, blurConfig.blurType);
        if (blurConfig.blurType != 2 || (fArr = blurConfig.blurExtraParams) == null) {
            return;
        }
        setBackgroundGradientBlurParams(view, fArr, blurConfig.blurSubType);
    }

    public static boolean setColorAdjust(@NonNull View view, @NonNull ArrayList<Pair<Integer, float[]>> arrayList) {
        if (view == null || !HyperMaterialUtils.isEnable()) {
            return false;
        }
        try {
            if (METHOD_SET_COLOR_ADJUST == null) {
                METHOD_SET_COLOR_ADJUST = View.class.getMethod("setMiColorAdjust", ArrayList.class);
            }
            METHOD_SET_COLOR_ADJUST.invoke(view, arrayList);
            return true;
        } catch (Exception unused) {
            METHOD_SET_COLOR_ADJUST = null;
            return false;
        }
    }

    public static boolean setMixEffectEnabled(@NonNull View view, boolean z2) {
        if (!HyperMaterialUtils.isEnable()) {
            return false;
        }
        try {
            if (METHOD_SET_MIX_EFFECT_ENABLED == null) {
                METHOD_SET_MIX_EFFECT_ENABLED = View.class.getMethod("setMixEffectEnabled", Boolean.TYPE);
            }
            METHOD_SET_MIX_EFFECT_ENABLED.invoke(view, Boolean.valueOf(z2));
            return true;
        } catch (Exception unused) {
            METHOD_SET_MIX_EFFECT_ENABLED = null;
            return false;
        }
    }

    public static boolean setPassTextureScale(@NonNull View view, float f2) {
        if (!HyperMaterialUtils.isEnable()) {
            return false;
        }
        float fMax = Math.max(0.0f, Math.min(1.0f, f2));
        try {
            if (METHOD_SET_PASS_TEXTURE_SCALE == null) {
                METHOD_SET_PASS_TEXTURE_SCALE = View.class.getMethod("setPassTextureScale", Float.TYPE);
            }
            METHOD_SET_PASS_TEXTURE_SCALE.invoke(view, Float.valueOf(fMax));
            return true;
        } catch (Exception unused) {
            METHOD_SET_PASS_TEXTURE_SCALE = null;
            return false;
        }
    }

    public static boolean setPassWindowBlurEnabled(@NonNull View view, boolean z2) {
        if (!HyperMaterialUtils.isEnable()) {
            return false;
        }
        try {
            if (METHOD_SET_PASS_WINDOW_BLUR_MODE == null) {
                METHOD_SET_PASS_WINDOW_BLUR_MODE = View.class.getMethod("setPassWindowBlurEnabled", Boolean.TYPE);
            }
            METHOD_SET_PASS_WINDOW_BLUR_MODE.invoke(view, Boolean.valueOf(z2));
            return true;
        } catch (Exception unused) {
            METHOD_SET_PASS_WINDOW_BLUR_MODE = null;
            return false;
        }
    }

    public static boolean setSelfBlur(@NonNull View view, int i2, ArrayList<Point> arrayList) {
        if (!HyperMaterialUtils.isEnable()) {
            return false;
        }
        try {
            if (METHOD_SET_SELF_BLUR == null) {
                METHOD_SET_SELF_BLUR = View.class.getMethod("setMiSelfBlur", Integer.TYPE, ArrayList.class);
            }
            METHOD_SET_SELF_BLUR.invoke(view, Integer.valueOf(i2), arrayList);
            return true;
        } catch (Exception unused) {
            METHOD_SET_SELF_BLUR = null;
            return false;
        }
    }

    public static boolean setSelfBlurType(@NonNull View view, int i2) {
        if (!HyperMaterialUtils.isEnable()) {
            return false;
        }
        try {
            if (METHOD_SET_SELF_BLUR_TYPE == null) {
                METHOD_SET_SELF_BLUR_TYPE = View.class.getMethod("setMiSelfBlurType", Integer.TYPE);
            }
            METHOD_SET_SELF_BLUR_TYPE.invoke(view, Integer.valueOf(i2));
            return true;
        } catch (Exception unused) {
            METHOD_SET_SELF_BLUR_TYPE = null;
            return false;
        }
    }

    public static boolean setSelfGradientBlurParams(@NonNull View view, float[] fArr, int i2) {
        if (!HyperMaterialUtils.isEnable()) {
            return false;
        }
        try {
            if (METHOD_SET_SELF_GRADIENT_BLUR_PARAMS == null) {
                METHOD_SET_SELF_GRADIENT_BLUR_PARAMS = View.class.getMethod("setSelfGradientBlurParams", float[].class, Integer.TYPE);
            }
            METHOD_SET_SELF_GRADIENT_BLUR_PARAMS.invoke(view, fArr, Integer.valueOf(i2));
            return true;
        } catch (Exception unused) {
            METHOD_SET_SELF_GRADIENT_BLUR_PARAMS = null;
            return false;
        }
    }

    public static boolean setViewBlurMode(@NonNull View view, int i2) {
        if (view == null || !HyperMaterialUtils.isEnable()) {
            return false;
        }
        try {
            if (METHOD_SET_VIEW_BLUR_MODE == null) {
                METHOD_SET_VIEW_BLUR_MODE = View.class.getMethod("setMiViewBlurMode", Integer.TYPE);
            }
            METHOD_SET_VIEW_BLUR_MODE.invoke(view, Integer.valueOf(i2));
            return true;
        } catch (Exception unused) {
            METHOD_SET_VIEW_BLUR_MODE = null;
            return false;
        }
    }

    @Nullable
    public static ArrayList<Point> wrapBlendConfig(@NonNull int[] iArr, @NonNull int[] iArr2) {
        if (iArr == null || iArr2 == null) {
            return null;
        }
        if (iArr.length != iArr2.length) {
            Log.w("MiuixBlur", String.format("warning!! colorInts(%s) and blendModes(%s) size not match. %s", Integer.valueOf(iArr.length), Integer.valueOf(iArr2.length), Log.getStackTraceString(new Throwable())));
        }
        int iMin = Math.min(iArr.length, iArr2.length);
        ArrayList<Point> arrayList = new ArrayList<>();
        for (int i2 = 0; i2 < iMin; i2++) {
            arrayList.add(new Point(iArr[i2], iArr2[i2]));
        }
        return arrayList;
    }

    public static float[] wrapLinearGradientBlurConfig(float f2, float f3, float f4, float f5, float f6, float f7) {
        return new float[]{f2, f3, f4, f5, f6, f7};
    }

    public static float[] wrapRadialGradientBlurConfig(float f2, float f3, float f4, float f5, float f6) {
        return new float[]{f2, f3, f4, f5, f6};
    }

    public static boolean setBackgroundBlur(@NonNull View view, int i2, boolean z2) {
        if (!z2) {
            return setBackgroundBlur(view, i2, 1);
        }
        return setViewBlurMode(view, 2) | setBackgroundBlur(view, i2, 1);
    }

    public static boolean setBackgroundBlurEnhanceFlag(@NonNull View view, int i2, int i3) {
        if (view == null || !HyperMaterialUtils.isEnable()) {
            return false;
        }
        try {
            if (METHOD_SET_BG_BLUR_ENHANCE_FLAG == null) {
                Class cls = Integer.TYPE;
                METHOD_SET_BG_BLUR_ENHANCE_FLAG = View.class.getMethod("setMiBackgroundBlurEnhanceFlag", cls, cls);
            }
            METHOD_SET_BG_BLUR_ENHANCE_FLAG.invoke(view, Integer.valueOf(i2), Integer.valueOf(i3));
            return true;
        } catch (Exception unused) {
            METHOD_SET_BG_BLUR_ENHANCE_FLAG = null;
            return false;
        }
    }

    public static boolean setBgCommonLinearGradientBlur(@NonNull View view, int i2, float f2, float f3) {
        float[] fArr;
        if (view == null) {
            return false;
        }
        if (i2 == 1) {
            float height = view.getHeight();
            fArr = new float[]{0.0f, Math.max(0.0f, height - f3), f2, 0.0f, height, 0.0f};
        } else if (i2 == 2) {
            float width = view.getWidth();
            fArr = new float[]{Math.max(0.0f, width - f3), 0.0f, f2, width, 0.0f, 0.0f};
        } else {
            fArr = null;
        }
        return setBackgroundGradientBlur(view, fArr, 1);
    }

    public static boolean setBackgroundBlendConfig(@NonNull View view, @NonNull MaterialConfig.ColorBlendConfig colorBlendConfig) {
        if (colorBlendConfig == null) {
            return false;
        }
        return setBackgroundBlendConfig(view, colorBlendConfig.blendColors, colorBlendConfig.blendModes);
    }

    public static boolean setBackgroundBlendConfig(@NonNull View view, @NonNull int[] iArr, @NonNull int[] iArr2) {
        if (view == null || iArr == null || iArr2 == null || !HyperMaterialUtils.isEnable()) {
            return false;
        }
        if (RomUtils.getHyperOsVersion() > 1) {
            ArrayList<Point> arrayListWrapBlendConfig = wrapBlendConfig(iArr, iArr2);
            if (arrayListWrapBlendConfig == null) {
                return false;
            }
            setBackgroundBlendConfig(view, arrayListWrapBlendConfig);
            return true;
        }
        clearBackgroundBlendConfig(view);
        int iMin = Math.min(iArr.length, iArr2.length);
        for (int i2 = 0; i2 < iMin; i2++) {
            if (!addBackgroundBlenderColor(view, iArr[i2], iArr2[i2])) {
                clearBackgroundBlendConfig(view);
                return false;
            }
        }
        return true;
    }

    public static boolean setBgCommonLinearGradientBlurWithDp(@NonNull View view, int i2, float f2, float f3) {
        Resources resources;
        if (view == null || (resources = view.getResources()) == null) {
            return false;
        }
        float f4 = resources.getDisplayMetrics().density;
        return setBgCommonLinearGradientBlur(view, i2, (f2 * f4) + 0.5f, (f3 * f4) + 0.5f);
    }

    public static void enableContentBlur(@NonNull View view, boolean z2, @NonNull int[] iArr, @NonNull int[] iArr2) {
        if (view == null) {
            return;
        }
        if (z2) {
            setViewBlurMode(view, 3);
            setBackgroundBlendConfig(view, iArr, iArr2);
        } else {
            setViewBlurMode(view, 0);
            clearBackgroundBlendConfig(view);
        }
    }

    public static boolean setBackgroundBlur(@NonNull View view, int i2, int i3) {
        if (view == null || !HyperMaterialUtils.isEnable() || !HyperMaterialUtils.isFeatureEnable(view.getContext())) {
            return false;
        }
        if (i2 > 400) {
            i2 = 400;
        }
        try {
            if (METHOD_SET_BG_BLUR_MODE == null) {
                METHOD_SET_BG_BLUR_MODE = View.class.getMethod("setMiBackgroundBlurMode", Integer.TYPE);
            }
            METHOD_SET_BG_BLUR_MODE.invoke(view, 1);
            if (i2 >= 0) {
                if (METHOD_SET_BG_BLUR_RADIUS == null) {
                    METHOD_SET_BG_BLUR_RADIUS = View.class.getMethod("setMiBackgroundBlurRadius", Integer.TYPE);
                }
                METHOD_SET_BG_BLUR_RADIUS.invoke(view, Integer.valueOf(i2));
            }
            return setViewBlurMode(view, i3);
        } catch (Exception unused) {
            METHOD_SET_BG_BLUR_MODE = null;
            METHOD_SET_BG_BLUR_RADIUS = null;
            return false;
        }
    }

    public static boolean setBackgroundBlendConfig(@NonNull View view, @NonNull ArrayList<Point> arrayList) {
        if (view == null || !HyperMaterialUtils.isEnable()) {
            return false;
        }
        try {
            if (METHOD_SET_BG_BLEND_COLORS == null) {
                METHOD_SET_BG_BLEND_COLORS = View.class.getMethod("setMiBackgroundBlendColors", ArrayList.class);
            }
            METHOD_SET_BG_BLEND_COLORS.invoke(view, arrayList);
            return true;
        } catch (Exception unused) {
            METHOD_SET_BG_BLEND_COLORS = null;
            return false;
        }
    }
}
