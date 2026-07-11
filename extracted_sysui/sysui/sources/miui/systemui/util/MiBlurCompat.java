package miui.systemui.util;

import I0.A;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.graphics.Color;
import android.graphics.Point;
import android.view.View;
import androidx.annotation.ArrayRes;
import androidx.annotation.FloatRange;
import androidx.annotation.IntRange;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Iterator;
import miuix.core.util.HyperMaterialUtils;
import miuix.core.util.MiuiBlurUtils;
import miuix.theme.token.ColorBlendToken;

/* JADX INFO: loaded from: classes4.dex */
public final class MiBlurCompat {
    public static final int BLUR_CLOSE = 2;
    public static final int BLUR_OPEN = 1;
    public static final int BLUR_UNAVAILABLE = 0;
    public static final int CLEAR = 0;
    public static final int CLEAR_BLUR = 0;
    public static final int COLOR = 27;
    public static final int COLOR_BURN = 19;
    public static final int COLOR_DODGE = 18;
    private static final int CONFIG_BLUR;
    public static final int CONTROL_CENTER_BG = 2;
    public static final int DARKEN = 16;
    private static final boolean DEBUG = false;
    public static final int DIFFERENCE = 22;
    public static final int DRAW_CUSTOM_AND_BLEND = 2;
    public static final int DRAW_CUSTOM_INSTEAD = 3;
    public static final int DRAW_RECT_AND_BLEND = 1;
    public static final int DST = 2;
    public static final int DST_ATOP = 10;
    public static final int DST_IN = 6;
    public static final int DST_OUT = 8;
    public static final int DST_OVER = 4;
    public static final int DUAL_LAYER_BLUR = 10;
    public static final int EXCLUSION = 23;
    public static final int HARD_LIGHT = 20;
    public static final int HUE = 25;
    public static final int LAB = 106;
    public static final int LAB_DARKEN_WITH_GRAYSCALE = 105;
    public static final int LAB_LIGHTEN_WITH_GRAYSCALE = 103;
    public static final int LAST_COEFF_MODE = 29;
    public static final int LAST_MODE = 31;
    public static final int LAST_SEPARABLE_MODE = 30;
    public static final int LIGHTEN = 17;
    public static final int LINEAR_LIGHT = 100;
    public static final int LINEAR_LIGHT_WITH_GREYSCALE = 101;
    public static final int LUMINOSITY = 28;
    public static final int MAX_BLUR_RADIUS = 275;
    public static final float MAX_SCALE_RADIUS = 0.075f;
    public static final float MAX_SECONDARY_SCALE_RADIUS = 0.05f;
    public static final int MIN_BLUR_RADIUS = 0;
    public static final float MIN_SCALE_RADIUS = 0.0f;
    public static final int MI_SELF_BLUR_CLEAN = -1;
    public static final int MI_SELF_BLUR_GAUSSIAN = 0;
    public static final int MI_SELF_BLUR_GRADIENT = 2;
    public static final int MI_SELF_BLUR_KAWAS = 1;
    public static final int MODULATE = 13;
    public static final int MULTIPLY = 24;
    public static final int NO_BLEND = 0;
    public static final int OVERLAY = 15;
    public static final int PLUS = 12;
    public static final int SATURATION = 26;
    public static final int SAVE_LAYER = 1;
    public static final int SCREEN = 14;
    public static final int SOFT_LIGHT = 21;
    public static final int SRC = 1;
    public static final int SRC_ATOP = 9;
    public static final int SRC_IN = 5;
    public static final int SRC_OUT = 7;
    public static final int SRC_OVER = 3;
    private static final String TAG = "MiBlurCompat";
    public static final int XOR = 11;
    private static final H0.d blurField$delegate;
    public static final MiBlurCompat INSTANCE = new MiBlurCompat();
    private static final boolean BACKGROUND_BLUR_SUPPORTED = HyperMaterialUtils.isEnable();

    @Retention(RetentionPolicy.SOURCE)
    public @interface BackgroundBlurMode {
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface BlendMode {
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface BlurConfig {
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface MISelfBlurType {
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface ViewBlurMode {
    }

    static {
        int iIntValue;
        try {
            Object obj = ActivityInfo.class.getDeclaredField("CONFIG_BLUR").get(null);
            kotlin.jvm.internal.n.e(obj, "null cannot be cast to non-null type kotlin.Int");
            iIntValue = ((Integer) obj).intValue();
        } catch (Throwable unused) {
            iIntValue = 1048576;
        }
        CONFIG_BLUR = iIntValue;
        blurField$delegate = H0.e.b(MiBlurCompat$blurField$2.INSTANCE);
    }

    private MiBlurCompat() {
    }

    private final int alphaColor(int i2, @FloatRange(from = 0.0d, to = 1.0d) float f2) {
        if (f2 < 0.01f) {
            return 0;
        }
        int i3 = (i2 >> 24) & 255;
        return (((int) (i3 * f2)) << 24) | (i2 & (~(i3 << 24)));
    }

    public static final boolean chooseBackgroundBlurContainerCompat(View view, View view2) {
        kotlin.jvm.internal.n.g(view, "<this>");
        return MiuiBlurUtils.chooseBackgroundBlurContainer(view, view2);
    }

    public static final boolean cleanMiSelfBlur(View view) {
        kotlin.jvm.internal.n.g(view, "<this>");
        return setMiSelfBlurType(view, -1);
    }

    public static final boolean clearMiBackgroundBlendColorCompat(View view) {
        kotlin.jvm.internal.n.g(view, "<this>");
        return MiuiBlurUtils.clearBackgroundBlendConfig(view);
    }

    public static final boolean disableMiBackgroundContainBelowCompat(View view, boolean z2) {
        kotlin.jvm.internal.n.g(view, "<this>");
        return MiuiBlurUtils.disableBackgroundContainBelow(view, z2);
    }

    public static final boolean getBackgroundBlurOpened(Context context) {
        kotlin.jvm.internal.n.g(context, "<this>");
        Configuration configuration = context.getResources().getConfiguration();
        kotlin.jvm.internal.n.f(configuration, "getConfiguration(...)");
        return getBackgroundBlurOpened(configuration);
    }

    public static /* synthetic */ void getBackgroundBlurOpened$annotations(Context context) {
    }

    public static final boolean getBackgroundBlurOpenedInDefaultTheme(Context context) {
        kotlin.jvm.internal.n.g(context, "<this>");
        if (BACKGROUND_BLUR_SUPPORTED) {
            Configuration configuration = context.getResources().getConfiguration();
            kotlin.jvm.internal.n.f(configuration, "getConfiguration(...)");
            if (getBlurCompat(configuration) == 1 && ThemeUtils.INSTANCE.getDefaultPluginTheme()) {
                return true;
            }
        }
        return false;
    }

    public static /* synthetic */ void getBackgroundBlurOpenedInDefaultTheme$annotations(Context context) {
    }

    public static final int getBlurCompat(Configuration configuration) {
        kotlin.jvm.internal.n.g(configuration, "<this>");
        try {
            Field blurField = INSTANCE.getBlurField();
            Object obj = blurField != null ? blurField.get(configuration) : null;
            Integer num = obj instanceof Integer ? (Integer) obj : null;
            if (num != null) {
                return num.intValue();
            }
            return 0;
        } catch (Throwable unused) {
            return 0;
        }
    }

    public static /* synthetic */ void getBlurCompat$annotations(Configuration configuration) {
    }

    private final Field getBlurField() {
        return (Field) blurField$delegate.getValue();
    }

    public static final boolean getMiSelfBlurType(View view) {
        kotlin.jvm.internal.n.g(view, "<this>");
        return MiuiBlurUtils.getSelfBlurType(view);
    }

    public static final boolean getMixEffectEnabledCompat(View view) {
        kotlin.jvm.internal.n.g(view, "<this>");
        return MiuiBlurUtils.getMixEffectEnabled(view);
    }

    public static final boolean setMiBackgroundBlendColorConfig(View view, int[] color, int[] mode) {
        kotlin.jvm.internal.n.g(view, "<this>");
        kotlin.jvm.internal.n.g(color, "color");
        kotlin.jvm.internal.n.g(mode, "mode");
        return MiuiBlurUtils.setBackgroundBlendConfig(view, color, mode);
    }

    public static final void setMiBackgroundBlendColors(View view, @ArrayRes int i2) {
        kotlin.jvm.internal.n.g(view, "<this>");
        setMiBackgroundBlendColors$default(view, i2, 0.0f, 2, (Object) null);
    }

    private static final void setMiBackgroundBlendColors$applyFrom(float f2, int[] iArr, ArrayList<Point> arrayList) {
        float f3 = 1 - f2;
        if (f3 < 0.01f) {
            return;
        }
        int length = iArr.length / 2;
        for (int i2 = 0; i2 < length; i2++) {
            int i3 = i2 * 2;
            arrayList.add(new Point(INSTANCE.alphaColor(iArr[i3], f3), iArr[i3 + 1]));
        }
    }

    private static final void setMiBackgroundBlendColors$applyTo(float f2, int[] iArr, ArrayList<Point> arrayList) {
        if (f2 < 0.01f) {
            return;
        }
        int length = iArr.length / 2;
        for (int i2 = 0; i2 < length; i2++) {
            int i3 = i2 * 2;
            arrayList.add(new Point(INSTANCE.alphaColor(iArr[i3], f2), iArr[i3 + 1]));
        }
    }

    public static /* synthetic */ void setMiBackgroundBlendColors$default(View view, int i2, float f2, int i3, Object obj) {
        if ((i3 & 2) != 0) {
            f2 = 1.0f;
        }
        setMiBackgroundBlendColors(view, i2, f2);
    }

    private static final int[] setMiBackgroundBlendColors$generateLegacyBlendColors(ColorBlendToken colorBlendToken) {
        int length = colorBlendToken.colors.length + colorBlendToken.blendModes.length;
        int[] iArr = new int[length];
        int i2 = 0;
        int i3 = 0;
        for (int i4 = 0; i4 < length; i4++) {
            if (i4 % 2 == 0) {
                iArr[i4] = colorBlendToken.colors[i3];
                i3++;
            } else {
                iArr[i4] = colorBlendToken.blendModes[i2];
                i2++;
            }
        }
        return iArr;
    }

    public static final boolean setMiBackgroundBlurModeCompat(View view, int i2) {
        kotlin.jvm.internal.n.g(view, "<this>");
        return MiuiBlurUtils.setBackgroundBlurMode(view, i2);
    }

    public static final boolean setMiBackgroundBlurRadiusCompat(View view, @IntRange(from = 0, to = 275) int i2) {
        kotlin.jvm.internal.n.g(view, "<this>");
        return MiuiBlurUtils.setBackgroundBlurRadius(view, i2);
    }

    public static final boolean setMiBackgroundBlurScaleRatioCompat(View view, float f2) {
        kotlin.jvm.internal.n.g(view, "<this>");
        return MiuiBlurUtils.setBackgroundBlurScaleRatio(view, f2);
    }

    public static final boolean setMiSelfBlur(View view, int i2, ArrayList<Point> blendConfig) {
        kotlin.jvm.internal.n.g(view, "<this>");
        kotlin.jvm.internal.n.g(blendConfig, "blendConfig");
        return MiuiBlurUtils.setSelfBlur(view, i2, blendConfig);
    }

    public static final boolean setMiSelfBlurType(View view, int i2) {
        kotlin.jvm.internal.n.g(view, "<this>");
        return MiuiBlurUtils.setSelfBlurType(view, i2);
    }

    public static final boolean setMiViewBlurModeCompat(View view, int i2) {
        kotlin.jvm.internal.n.g(view, "<this>");
        return MiuiBlurUtils.setViewBlurMode(view, i2);
    }

    public static final boolean setMixEffectEnabledCompat(View view, boolean z2) {
        kotlin.jvm.internal.n.g(view, "<this>");
        return MiuiBlurUtils.setMixEffectEnabled(view, z2);
    }

    public static final boolean setPassWindowBlurEnabledCompat(View view, boolean z2) {
        kotlin.jvm.internal.n.g(view, "<this>");
        return MiuiBlurUtils.setPassWindowBlurEnabled(view, z2);
    }

    public final int colorTranslateTo(int i2, int i3, @FloatRange(from = 0.0d, to = 1.0d) float f2) {
        return Color.argb(((i2 >> 24) & 255) + ((int) ((((i3 >> 24) & 255) - r3) * f2)), ((i2 >> 16) & 255) + ((int) ((((i3 >> 16) & 255) - r0) * f2)), ((i2 >> 8) & 255) + ((int) ((((i3 >> 8) & 255) - r1) * f2)), (i2 & 255) + ((int) (((i3 & 255) - r4) * f2)));
    }

    public final boolean getBACKGROUND_BLUR_SUPPORTED() {
        return BACKGROUND_BLUR_SUPPORTED;
    }

    public final int getCONFIG_BLUR() {
        return CONFIG_BLUR;
    }

    public final ColorBlendToken translateTo(ColorBlendToken colorBlendToken, ColorBlendToken toToken, @FloatRange(from = 0.0d, to = 1.0d) float f2) {
        kotlin.jvm.internal.n.g(colorBlendToken, "<this>");
        kotlin.jvm.internal.n.g(toToken, "toToken");
        int iMin = Math.min(colorBlendToken.colors.length, toToken.colors.length);
        int[] iArr = new int[iMin];
        int[] iArr2 = new int[iMin];
        Iterator it = c1.f.l(0, iMin).iterator();
        while (it.hasNext()) {
            int iNextInt = ((A) it).nextInt();
            iArr[iNextInt] = INSTANCE.colorTranslateTo(colorBlendToken.colors[iNextInt], toToken.colors[iNextInt], f2);
            iArr2[iNextInt] = f2 < 0.5f ? colorBlendToken.blendModes[iNextInt] : toToken.blendModes[iNextInt];
        }
        ColorBlendToken colorBlendTokenBuild = new ColorBlendToken.Builder().setConfig(iArr, iArr2).build();
        kotlin.jvm.internal.n.f(colorBlendTokenBuild, "build(...)");
        return colorBlendTokenBuild;
    }

    public static final boolean getBackgroundBlurOpened(Configuration configuration) {
        kotlin.jvm.internal.n.g(configuration, "<this>");
        return BACKGROUND_BLUR_SUPPORTED && getBlurCompat(configuration) == 1;
    }

    public static /* synthetic */ void getBackgroundBlurOpened$annotations(Configuration configuration) {
    }

    public static final boolean setMiBackgroundBlendColorConfig(View view, ArrayList<Point> config) {
        kotlin.jvm.internal.n.g(view, "<this>");
        kotlin.jvm.internal.n.g(config, "config");
        return MiuiBlurUtils.setBackgroundBlendConfig(view, config);
    }

    public static final void setMiBackgroundBlendColors(View view, int[] blendColors) {
        kotlin.jvm.internal.n.g(view, "<this>");
        kotlin.jvm.internal.n.g(blendColors, "blendColors");
        setMiBackgroundBlendColors$default(view, blendColors, 0.0f, 2, (Object) null);
    }

    public static /* synthetic */ void setMiBackgroundBlendColors$default(View view, int[] iArr, float f2, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            f2 = 1.0f;
        }
        setMiBackgroundBlendColors(view, iArr, f2);
    }

    public static final void setMiSelfBlur(View view, int i2, @ArrayRes int i3) {
        kotlin.jvm.internal.n.g(view, "<this>");
        int[] intArray = view.getContext().getResources().getIntArray(i3);
        kotlin.jvm.internal.n.f(intArray, "getIntArray(...)");
        setMiSelfBlur(view, i2, intArray);
    }

    public static final void setMiBackgroundBlendColors(View view, ColorBlendToken token) {
        kotlin.jvm.internal.n.g(view, "<this>");
        kotlin.jvm.internal.n.g(token, "token");
        ArrayList arrayList = new ArrayList();
        int iF = c1.f.f(token.colors.length, token.blendModes.length);
        for (int i2 = 0; i2 < iF; i2++) {
            arrayList.add(new Point(token.colors[i2], token.blendModes[i2]));
        }
        if (arrayList.isEmpty()) {
            return;
        }
        setMiBackgroundBlendColorConfig(view, arrayList);
    }

    public static /* synthetic */ void setMiBackgroundBlendColors$default(View view, int[] iArr, int[] iArr2, float f2, boolean z2, int i2, Object obj) {
        if ((i2 & 8) != 0) {
            z2 = false;
        }
        setMiBackgroundBlendColors(view, iArr, iArr2, f2, z2);
    }

    public static final void setMiSelfBlur(View view, int i2, int[] blendColors) {
        kotlin.jvm.internal.n.g(view, "<this>");
        kotlin.jvm.internal.n.g(blendColors, "blendColors");
        ArrayList arrayList = new ArrayList();
        int length = blendColors.length / 2;
        for (int i3 = 0; i3 < length; i3++) {
            int i4 = i3 * 2;
            arrayList.add(new Point(blendColors[i4], blendColors[i4 + 1]));
        }
        setMiSelfBlur(view, i2, (ArrayList<Point>) arrayList);
    }

    public static /* synthetic */ void setMiBackgroundBlendColors$default(View view, ColorBlendToken colorBlendToken, ColorBlendToken colorBlendToken2, float f2, boolean z2, int i2, Object obj) {
        if ((i2 & 8) != 0) {
            z2 = false;
        }
        setMiBackgroundBlendColors(view, colorBlendToken, colorBlendToken2, f2, z2);
    }

    public static final void setMiBackgroundBlendColors(View view, @ArrayRes int i2, @FloatRange(from = 0.0d, to = 1.0d) float f2) {
        kotlin.jvm.internal.n.g(view, "<this>");
        int[] intArray = view.getContext().getResources().getIntArray(i2);
        kotlin.jvm.internal.n.f(intArray, "getIntArray(...)");
        setMiBackgroundBlendColors(view, intArray, f2);
    }

    public static final void setMiBackgroundBlendColors(View view, int[] blendColors, @FloatRange(from = 0.0d, to = 1.0d) float f2) {
        kotlin.jvm.internal.n.g(view, "<this>");
        kotlin.jvm.internal.n.g(blendColors, "blendColors");
        ArrayList arrayList = new ArrayList();
        int length = blendColors.length / 2;
        for (int i2 = 0; i2 < length; i2++) {
            int i3 = i2 * 2;
            int i4 = blendColors[i3];
            if (f2 != 1.0f) {
                int i5 = (i4 >> 24) & 255;
                i4 = (i4 & (~(i5 << 24))) | (((int) (i5 * f2)) << 24);
            }
            arrayList.add(new Point(i4, blendColors[i3 + 1]));
        }
        setMiBackgroundBlendColorConfig(view, arrayList);
    }

    public static final void setMiBackgroundBlendColors(View view, int[] fromColors, int[] toColors, @FloatRange(from = 0.0d, to = 1.0d) float f2, boolean z2) {
        kotlin.jvm.internal.n.g(view, "<this>");
        kotlin.jvm.internal.n.g(fromColors, "fromColors");
        kotlin.jvm.internal.n.g(toColors, "toColors");
        ArrayList arrayList = new ArrayList();
        if (z2) {
            setMiBackgroundBlendColors$applyTo(f2, toColors, arrayList);
            setMiBackgroundBlendColors$applyFrom(f2, fromColors, arrayList);
        } else {
            setMiBackgroundBlendColors$applyFrom(f2, fromColors, arrayList);
            setMiBackgroundBlendColors$applyTo(f2, toColors, arrayList);
        }
        if (arrayList.isEmpty()) {
            return;
        }
        setMiBackgroundBlendColorConfig(view, arrayList);
    }

    public static final void setMiBackgroundBlendColors(View view, ColorBlendToken fromToken, ColorBlendToken toToken, @FloatRange(from = 0.0d, to = 1.0d) float f2, boolean z2) {
        kotlin.jvm.internal.n.g(view, "<this>");
        kotlin.jvm.internal.n.g(fromToken, "fromToken");
        kotlin.jvm.internal.n.g(toToken, "toToken");
        setMiBackgroundBlendColors(view, setMiBackgroundBlendColors$generateLegacyBlendColors(fromToken), setMiBackgroundBlendColors$generateLegacyBlendColors(toToken), f2, z2);
    }

    public static final void setMiBackgroundBlendColors(View view, ColorBlendToken fromToken, ColorBlendToken toToken, @FloatRange(from = 0.0d, to = 1.0d) float f2) {
        kotlin.jvm.internal.n.g(view, "<this>");
        kotlin.jvm.internal.n.g(fromToken, "fromToken");
        kotlin.jvm.internal.n.g(toToken, "toToken");
        setMiBackgroundBlendColors(view, INSTANCE.translateTo(fromToken, toToken, f2));
    }
}
