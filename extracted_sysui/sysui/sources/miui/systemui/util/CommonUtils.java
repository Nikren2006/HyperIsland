package miui.systemui.util;

import I0.K;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.content.res.MiuiConfiguration;
import android.graphics.Point;
import android.os.Build;
import android.os.SystemProperties;
import android.text.TextUtils;
import android.util.Base64;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.TextView;
import androidx.annotation.DrawableRes;
import androidx.core.view.AccessibilityDelegateCompat;
import androidx.core.view.ViewCompat;
import androidx.core.view.ViewGroupKt;
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;
import com.android.systemui.plugins.statusbar.StatusBarStateController;
import com.miui.circulate.device.api.Column;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Locale;
import java.util.Set;
import kotlin.jvm.functions.Function0;
import miui.content.res.ThemeResources;
import miui.os.Build;
import miui.systemui.quicksettings.common.R;
import miui.util.DeviceLevel;
import miuix.core.util.MiuiBlurUtils;
import miuix.core.util.RomUtils;

/* JADX INFO: loaded from: classes4.dex */
public final class CommonUtils {
    private static final boolean DEBUG;
    private static boolean HAS_MOBILE_FEATURE = false;
    private static final boolean IS_DATA_PAD;
    private static final boolean IS_FOLD;
    public static final boolean IS_ID_REGION;
    public static final boolean IS_KDDI_VERSION;
    private static final boolean IS_TABLET;
    private static final boolean IS_WIFI_PAD;
    private static final float MAX_BLUR_RADIUS = 100.0f;
    private static final float MIN_BLUR_RADIUS = 1.0f;
    private static final boolean MIUI_LITE_V2;
    private static final int MIUI_LITE_VERSION;
    public static final boolean NOT_SUPPORT_LOTTIE;
    public static final boolean NOT_SUPPORT_TEXT_EFFECT;
    public static final int OS3_VERSION_CODE = 3;
    public static final String PHONE = "com.android.phone";
    public static final String PKG_SETTINGS = "com.android.settings";
    public static final String TAG = "CommonUtils";
    private static boolean USING_LARGE_SCREEN;
    private static final H0.d isFlipDevice$delegate;
    private static final H0.d isFlipDeviceMethod$delegate;
    public static final CommonUtils INSTANCE = new CommonUtils();
    private static final Set<String> NOTCH_SCREEN_DEVICES = K.d("flame", "lake", "pond", "tornado", "dew", "warm");

    /* JADX INFO: renamed from: miui.systemui.util.CommonUtils$isFlipDevice$2, reason: invalid class name */
    public static final class AnonymousClass2 extends kotlin.jvm.internal.o implements Function0 {
        public static final AnonymousClass2 INSTANCE = new AnonymousClass2();

        public AnonymousClass2() {
            super(0);
        }

        @Override // kotlin.jvm.functions.Function0
        public final Boolean invoke() {
            boolean zBooleanValue = false;
            try {
                Method methodIsFlipDeviceMethod = CommonUtils.INSTANCE.isFlipDeviceMethod();
                Object objInvoke = methodIsFlipDeviceMethod != null ? methodIsFlipDeviceMethod.invoke(null, null) : null;
                Boolean bool = objInvoke instanceof Boolean ? (Boolean) objInvoke : null;
                if (bool != null) {
                    zBooleanValue = bool.booleanValue();
                }
            } catch (Throwable th) {
                Log.e(CommonUtils.TAG, "Invoke isFlipDevice method failed.", th);
            }
            return Boolean.valueOf(zBooleanValue);
        }
    }

    /* JADX INFO: renamed from: miui.systemui.util.CommonUtils$isFlipDeviceMethod$2, reason: invalid class name and case insensitive filesystem */
    public static final class C06962 extends kotlin.jvm.internal.o implements Function0 {
        public static final C06962 INSTANCE = new C06962();

        public C06962() {
            super(0);
        }

        @Override // kotlin.jvm.functions.Function0
        public final Method invoke() {
            try {
                return Class.forName("miui.util.MiuiMultiDisplayTypeInfo").getMethod("isFlipDevice", null);
            } catch (Throwable th) {
                Log.e(CommonUtils.TAG, "Get isFlipDevice method failed.", th);
                return null;
            }
        }
    }

    static {
        int miuiLiteVersion;
        try {
            miuiLiteVersion = DeviceLevel.getMiuiLiteVersion();
        } catch (Throwable unused) {
            miuiLiteVersion = -1;
        }
        MIUI_LITE_VERSION = miuiLiteVersion;
        MIUI_LITE_V2 = miuiLiteVersion == 2;
        DEBUG = Log.isLoggable("MiuiSystemUIPlugin", 3);
        NOT_SUPPORT_LOTTIE = DeviceUtils.isMidLowLevel() || DeviceUtils.isLowLevel() || Build.IS_TABLET;
        NOT_SUPPORT_TEXT_EFFECT = DeviceUtils.isSubMidLevel() || DeviceUtils.isMidLowLevel() || DeviceUtils.isLowLevel();
        IS_KDDI_VERSION = kotlin.jvm.internal.n.c(SystemProperties.get("ro.miui.customized.region"), "jp_kd");
        IS_ID_REGION = kotlin.jvm.internal.n.c(SystemProperties.get("ro.miui.build.region"), Column.ID);
        IS_FOLD = SystemProperties.getInt(FoldUtils.MUILT_DISPLAY_TYPE, 0) == 2;
        boolean z2 = Build.IS_TABLET;
        IS_TABLET = z2;
        IS_WIFI_PAD = z2 && !HAS_MOBILE_FEATURE;
        IS_DATA_PAD = z2 && HAS_MOBILE_FEATURE;
        USING_LARGE_SCREEN = z2;
        isFlipDevice$delegate = H0.e.b(AnonymousClass2.INSTANCE);
        isFlipDeviceMethod$delegate = H0.e.b(C06962.INSTANCE);
    }

    private CommonUtils() {
    }

    public static /* synthetic */ void debugLog$default(CommonUtils commonUtils, String str, String str2, Throwable th, int i2, Object obj) {
        if ((i2 & 4) != 0) {
            th = null;
        }
        commonUtils.debugLog(str, str2, th);
    }

    public static final String encodeDataToBase64(String str) {
        byte[] bytes;
        if (str != null) {
            bytes = str.getBytes(f1.c.f4238b);
            kotlin.jvm.internal.n.f(bytes, "getBytes(...)");
        } else {
            bytes = null;
        }
        String strEncodeToString = Base64.encodeToString(bytes, 0);
        kotlin.jvm.internal.n.f(strEncodeToString, "encodeToString(...)");
        return strEncodeToString;
    }

    public static final int getHyperOsVersion() {
        try {
            return RomUtils.getHyperOsVersion();
        } catch (Throwable th) {
            Log.e(TAG, "getHyperOsVersion failed.", th);
            return -1;
        }
    }

    public static final int getMiuiVersion() {
        try {
            return RomUtils.getMiuiVersion();
        } catch (Throwable th) {
            Log.e(TAG, "getMiuiVersion failed.", th);
            return -1;
        }
    }

    public static final Point getScreenSize(Context context) {
        kotlin.jvm.internal.n.g(context, "context");
        Object systemService = context.getSystemService("window");
        kotlin.jvm.internal.n.e(systemService, "null cannot be cast to non-null type android.view.WindowManager");
        Display defaultDisplay = ((WindowManager) systemService).getDefaultDisplay();
        if (defaultDisplay == null) {
            return null;
        }
        Point point = new Point();
        defaultDisplay.getRealSize(point);
        return point;
    }

    public static final long getVersionCode(Context context, String packageName) {
        kotlin.jvm.internal.n.g(context, "context");
        kotlin.jvm.internal.n.g(packageName, "packageName");
        try {
            return context.getPackageManager().getPackageInfo(packageName, PackageManager.PackageInfoFlags.of(0L)).getLongVersionCode();
        } catch (Exception unused) {
            return -1L;
        }
    }

    public static final boolean isFlipDevice() {
        return ((Boolean) isFlipDevice$delegate.getValue()).booleanValue();
    }

    public static /* synthetic */ void isFlipDevice$annotations() {
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Method isFlipDeviceMethod() {
        return (Method) isFlipDeviceMethod$delegate.getValue();
    }

    public static final boolean isHyperOsRom() {
        try {
            return RomUtils.isHyperOsRom();
        } catch (Throwable th) {
            Log.e(TAG, "isHyperOsRom failed.", th);
            return false;
        }
    }

    public static final boolean isLayoutRtl(Context context) {
        kotlin.jvm.internal.n.g(context, "<this>");
        return context.getResources().getConfiguration().getLayoutDirection() == 1;
    }

    public static /* synthetic */ void isLayoutRtl$annotations(Context context) {
    }

    public static final boolean isLocked(StatusBarStateController statusBarStateController) {
        kotlin.jvm.internal.n.g(statusBarStateController, "statusBarStateController");
        return statusBarStateController.getState() != 0;
    }

    public static final boolean isNotchScreenDevice() {
        String str = android.os.Build.DEVICE;
        Log.d(TAG, "currentDevice: " + str);
        return NOTCH_SCREEN_DEVICES.contains(str);
    }

    public static final boolean isScreenLayoutLarge(Context context) {
        kotlin.jvm.internal.n.g(context, "context");
        int i2 = context.getResources().getConfiguration().screenLayout & 15;
        return i2 == 3 || i2 == 4;
    }

    public static final boolean isTinyScreen(Context context) {
        kotlin.jvm.internal.n.g(context, "context");
        Point screenSize = getScreenSize(context);
        kotlin.jvm.internal.n.d(screenSize);
        return ((int) (((float) Math.max(screenSize.x, screenSize.y)) / context.getResources().getDisplayMetrics().density)) <= 670;
    }

    public static final boolean isUserDebug() {
        String str = android.os.Build.TYPE;
        String lowerCase = null;
        if (str != null) {
            String lowerCase2 = str.toLowerCase(Locale.ROOT);
            kotlin.jvm.internal.n.f(lowerCase2, "toLowerCase(...)");
            if (lowerCase2 != null && f1.o.v(lowerCase2, "debug", false, 2, null)) {
                return true;
            }
        }
        if (str != null) {
            lowerCase = str.toLowerCase(Locale.ROOT);
            kotlin.jvm.internal.n.f(lowerCase, "toLowerCase(...)");
        }
        return kotlin.jvm.internal.n.c("eng", lowerCase);
    }

    public static final boolean isUserRoot() {
        return kotlin.jvm.internal.n.c("user", android.os.Build.TYPE) && android.os.Build.IS_DEBUGGABLE;
    }

    public static final void removeAccessibilityClick(View view, final boolean z2) {
        kotlin.jvm.internal.n.g(view, "view");
        ViewCompat.setAccessibilityDelegate(view, new AccessibilityDelegateCompat() { // from class: miui.systemui.util.CommonUtils.removeAccessibilityClick.1
            @Override // androidx.core.view.AccessibilityDelegateCompat
            public void onInitializeAccessibilityNodeInfo(View host, AccessibilityNodeInfoCompat info) {
                kotlin.jvm.internal.n.g(host, "host");
                kotlin.jvm.internal.n.g(info, "info");
                super.onInitializeAccessibilityNodeInfo(host, info);
                if (z2) {
                    info.setLongClickable(false);
                    info.removeAction(AccessibilityNodeInfoCompat.AccessibilityActionCompat.ACTION_LONG_CLICK);
                } else {
                    info.setClickable(false);
                    info.removeAction(AccessibilityNodeInfoCompat.AccessibilityActionCompat.ACTION_CLICK);
                }
            }
        });
    }

    public static /* synthetic */ void removeAccessibilityClick$default(View view, boolean z2, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            z2 = false;
        }
        removeAccessibilityClick(view, z2);
    }

    public static /* synthetic */ void setBackgroundResourceEx$default(CommonUtils commonUtils, View view, int i2, boolean z2, int i3, Object obj) {
        if ((i3 & 2) != 0) {
            z2 = true;
        }
        commonUtils.setBackgroundResourceEx(view, i2, z2);
    }

    public static /* synthetic */ void setLayoutHeight$default(CommonUtils commonUtils, View view, int i2, boolean z2, int i3, Object obj) {
        if ((i3 & 2) != 0) {
            z2 = false;
        }
        commonUtils.setLayoutHeight(view, i2, z2);
    }

    public static /* synthetic */ void setLayoutSize$default(CommonUtils commonUtils, View view, int i2, int i3, boolean z2, int i4, Object obj) {
        if ((i4 & 4) != 0) {
            z2 = false;
        }
        commonUtils.setLayoutSize(view, i2, i3, z2);
    }

    public static /* synthetic */ void setLayoutWidth$default(CommonUtils commonUtils, View view, int i2, boolean z2, int i3, Object obj) {
        if ((i3 & 2) != 0) {
            z2 = false;
        }
        commonUtils.setLayoutWidth(view, i2, z2);
    }

    public static /* synthetic */ void setMarginBottom$default(CommonUtils commonUtils, View view, int i2, boolean z2, int i3, Object obj) {
        if ((i3 & 2) != 0) {
            z2 = false;
        }
        commonUtils.setMarginBottom(view, i2, z2);
    }

    public static /* synthetic */ void setMarginEnd$default(CommonUtils commonUtils, View view, int i2, boolean z2, int i3, Object obj) {
        if ((i3 & 2) != 0) {
            z2 = false;
        }
        commonUtils.setMarginEnd(view, i2, z2);
    }

    public static /* synthetic */ void setMarginHorizontal$default(CommonUtils commonUtils, View view, int i2, boolean z2, int i3, Object obj) {
        if ((i3 & 2) != 0) {
            z2 = false;
        }
        commonUtils.setMarginHorizontal(view, i2, z2);
    }

    public static /* synthetic */ void setMarginLeft$default(CommonUtils commonUtils, View view, int i2, boolean z2, int i3, Object obj) {
        if ((i3 & 2) != 0) {
            z2 = false;
        }
        commonUtils.setMarginLeft(view, i2, z2);
    }

    public static /* synthetic */ void setMarginRight$default(CommonUtils commonUtils, View view, int i2, boolean z2, int i3, Object obj) {
        if ((i3 & 2) != 0) {
            z2 = false;
        }
        commonUtils.setMarginRight(view, i2, z2);
    }

    public static /* synthetic */ void setMarginStart$default(CommonUtils commonUtils, View view, int i2, boolean z2, int i3, Object obj) {
        if ((i3 & 2) != 0) {
            z2 = false;
        }
        commonUtils.setMarginStart(view, i2, z2);
    }

    public static /* synthetic */ void setMarginTop$default(CommonUtils commonUtils, View view, int i2, boolean z2, int i3, Object obj) {
        if ((i3 & 2) != 0) {
            z2 = false;
        }
        commonUtils.setMarginTop(view, i2, z2);
    }

    public static /* synthetic */ void setMargins$default(CommonUtils commonUtils, View view, int i2, int i3, int i4, int i5, boolean z2, int i6, Object obj) {
        int marginStart;
        int i7;
        int marginEnd;
        int i8;
        if ((i6 & 1) == 0) {
            marginStart = i2;
        } else if (view.getLayoutParams() instanceof ViewGroup.MarginLayoutParams) {
            ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
            kotlin.jvm.internal.n.e(layoutParams, "null cannot be cast to non-null type android.view.ViewGroup.MarginLayoutParams");
            marginStart = ((ViewGroup.MarginLayoutParams) layoutParams).getMarginStart();
        } else {
            marginStart = 0;
        }
        if ((i6 & 2) == 0) {
            i7 = i3;
        } else if (view.getLayoutParams() instanceof ViewGroup.MarginLayoutParams) {
            ViewGroup.LayoutParams layoutParams2 = view.getLayoutParams();
            kotlin.jvm.internal.n.e(layoutParams2, "null cannot be cast to non-null type android.view.ViewGroup.MarginLayoutParams");
            i7 = ((ViewGroup.MarginLayoutParams) layoutParams2).topMargin;
        } else {
            i7 = 0;
        }
        if ((i6 & 4) == 0) {
            marginEnd = i4;
        } else if (view.getLayoutParams() instanceof ViewGroup.MarginLayoutParams) {
            ViewGroup.LayoutParams layoutParams3 = view.getLayoutParams();
            kotlin.jvm.internal.n.e(layoutParams3, "null cannot be cast to non-null type android.view.ViewGroup.MarginLayoutParams");
            marginEnd = ((ViewGroup.MarginLayoutParams) layoutParams3).getMarginEnd();
        } else {
            marginEnd = 0;
        }
        if ((i6 & 8) == 0) {
            i8 = i5;
        } else if (view.getLayoutParams() instanceof ViewGroup.MarginLayoutParams) {
            ViewGroup.LayoutParams layoutParams4 = view.getLayoutParams();
            kotlin.jvm.internal.n.e(layoutParams4, "null cannot be cast to non-null type android.view.ViewGroup.MarginLayoutParams");
            i8 = ((ViewGroup.MarginLayoutParams) layoutParams4).bottomMargin;
        } else {
            i8 = 0;
        }
        commonUtils.setMargins(view, marginStart, i7, marginEnd, i8, (i6 & 16) == 0 ? z2 : false);
    }

    public final int blurRadiusOfRatio(float f2) {
        if (f2 == 0.0f) {
            return 0;
        }
        return (int) MiuiMathUtils.INSTANCE.lerp(1.0f, MAX_BLUR_RADIUS, f2);
    }

    public final File buildPath(File base, String... segments) {
        kotlin.jvm.internal.n.g(base, "base");
        kotlin.jvm.internal.n.g(segments, "segments");
        int length = segments.length;
        int i2 = 0;
        while (i2 < length) {
            File file = new File(base, segments[i2]);
            i2++;
            base = file;
        }
        return base;
    }

    public final void callDismissKeyGuard(Context context) {
        kotlin.jvm.internal.n.g(context, "context");
        context.sendBroadcast(new Intent(Constant.ACTION_SHOW_UNLOCK_SCREEN));
    }

    public final Intent changeToSettingsSplitIntent(Context context, Intent intent) {
        kotlin.jvm.internal.n.g(context, "context");
        kotlin.jvm.internal.n.g(intent, "intent");
        if (Build.VERSION.SDK_INT >= 35) {
            return intent;
        }
        ComponentName component = intent.getComponent();
        String packageName = component != null ? component.getPackageName() : null;
        String action = intent.getAction();
        if (TextUtils.equals(action, "android.settings.SETTINGS")) {
            return intent;
        }
        if ((action == null || !f1.o.v(action, "android.settings", false, 2, null)) && !TextUtils.equals(packageName, "com.android.settings") && !TextUtils.equals(packageName, PHONE)) {
            return intent;
        }
        boolean z2 = (intent.getFlags() & 268435456) != 0;
        if (z2) {
            intent.removeFlags(268435456);
        }
        Intent intentA = y1.b.a(context, intent, null, z2);
        kotlin.jvm.internal.n.f(intentA, "getSettingsSplitActivityIntent(...)");
        return intentA;
    }

    public final void clearMiBlurBlendEffect(View view) {
        kotlin.jvm.internal.n.g(view, "<this>");
        MiBlurCompat.setMiViewBlurModeCompat(view, 0);
        MiuiBlurUtils.clearBackgroundBlendConfig(view);
    }

    public final void collapseControlCenter() throws IOException {
        Runtime.getRuntime().exec("cmd statusbar collapse");
    }

    public final void debugLog(String tag, String msg, Throwable th) {
        kotlin.jvm.internal.n.g(tag, "tag");
        kotlin.jvm.internal.n.g(msg, "msg");
        if (Constant.INSTANCE.getDEBUG()) {
            if (th != null) {
                Log.d(tag, msg, th);
            } else {
                Log.d(tag, msg);
            }
        }
    }

    public final void exitModal() throws IOException {
        Runtime.getRuntime().exec("input keyevent 4");
    }

    public final int getControlCenterDetailMaxHeight(Context context) {
        kotlin.jvm.internal.n.g(context, "<this>");
        return context.getResources().getDimensionPixelSize(useAlignEndStyle() ? R.dimen.control_center_detail_max_height_align_end : R.dimen.control_center_detail_max_height);
    }

    public final boolean getDEBUG() {
        return DEBUG;
    }

    public final boolean getForceVertical() {
        return IS_TABLET || (IS_FOLD && USING_LARGE_SCREEN);
    }

    public final boolean getHAS_MOBILE_FEATURE() {
        return HAS_MOBILE_FEATURE;
    }

    public final boolean getHorizontal(Context context) {
        kotlin.jvm.internal.n.g(context, "<this>");
        return context.getResources().getConfiguration().orientation == 2;
    }

    public final boolean getIS_DATA_PAD() {
        return IS_DATA_PAD;
    }

    public final boolean getIS_FOLD() {
        return IS_FOLD;
    }

    public final boolean getIS_TABLET() {
        return IS_TABLET;
    }

    public final boolean getIS_WIFI_PAD() {
        return IS_WIFI_PAD;
    }

    public final boolean getInVerticalMode(Context context) {
        kotlin.jvm.internal.n.g(context, "<this>");
        return getForceVertical() || context.getResources().getConfiguration().orientation == 1;
    }

    public final void getLocationInWindowWithoutTransform(View view, int[] inOutLocation) {
        kotlin.jvm.internal.n.g(view, "<this>");
        kotlin.jvm.internal.n.g(inOutLocation, "inOutLocation");
        if (inOutLocation.length < 2) {
            throw new IllegalArgumentException("inOutLocation must be an array of two integers");
        }
        inOutLocation[0] = view.getLeft();
        inOutLocation[1] = view.getTop();
        Object parent = view.getParent();
        while (parent instanceof View) {
            View view2 = (View) parent;
            inOutLocation[0] = inOutLocation[0] - view2.getScrollX();
            inOutLocation[1] = inOutLocation[1] - view2.getScrollY();
            inOutLocation[0] = inOutLocation[0] + view2.getLeft();
            inOutLocation[1] = inOutLocation[1] + view2.getTop();
            parent = view2.getParent();
        }
    }

    public final boolean getMIUI_LITE_V2() {
        return MIUI_LITE_V2;
    }

    public final int getMIUI_LITE_VERSION() {
        return MIUI_LITE_VERSION;
    }

    public final Method getSuperDeclaredMethod(Class<?> cls, String name, Class<?>... parameterTypes) throws NoSuchMethodException {
        kotlin.jvm.internal.n.g(cls, "<this>");
        kotlin.jvm.internal.n.g(name, "name");
        kotlin.jvm.internal.n.g(parameterTypes, "parameterTypes");
        Class<?> superclass = cls;
        while (!kotlin.jvm.internal.n.c(superclass, Object.class)) {
            try {
                Method declaredMethod = superclass.getDeclaredMethod(name, (Class[]) Arrays.copyOf(parameterTypes, parameterTypes.length));
                kotlin.jvm.internal.n.f(declaredMethod, "getDeclaredMethod(...)");
                return declaredMethod;
            } catch (Exception unused) {
                debugLog$default(this, TAG, "class " + superclass + " does not have " + name + " method.", null, 4, null);
                superclass = superclass.getSuperclass();
                kotlin.jvm.internal.n.f(superclass, "getSuperclass(...)");
            }
        }
        throw new NoSuchMethodException(cls + " or its super classes does not have " + name + " method.");
    }

    public final boolean getUSING_LARGE_SCREEN() {
        return USING_LARGE_SCREEN;
    }

    public final boolean isDefaultTheme() {
        return !ThemeResources.getSystem().containsAwesomeLockscreenEntry("manifest.xml");
    }

    public final boolean miuiThemeChanged(Configuration configuration) {
        kotlin.jvm.internal.n.g(configuration, "<this>");
        try {
            MiuiConfiguration miuiConfiguration = configuration.extraConfig;
            kotlin.jvm.internal.n.e(miuiConfiguration, "null cannot be cast to non-null type android.content.res.MiuiConfiguration");
            return MiuiConfiguration.needRestartStatusBar(miuiConfiguration.themeChangedFlags);
        } catch (Throwable unused) {
            Log.e("Common utils", "get theme change failed");
            return false;
        }
    }

    public final void setAlphaEx(View view, float f2) {
        kotlin.jvm.internal.n.g(view, "<this>");
        if (view.getAlpha() == f2) {
            return;
        }
        view.setAlpha(f2);
    }

    public final void setBackgroundResourceEx(View view, @DrawableRes int i2, boolean z2) {
        kotlin.jvm.internal.n.g(view, "<this>");
        if (i2 == 0) {
            return;
        }
        if (z2) {
            view.setBackground(view.getContext().getDrawable(i2));
        } else {
            view.setBackgroundResource(i2);
        }
    }

    public final void setGone(View view) {
        kotlin.jvm.internal.n.g(view, "<this>");
        if (view.getVisibility() != 8) {
            view.setVisibility(8);
        }
    }

    public final void setHAS_MOBILE_FEATURE(boolean z2) {
        HAS_MOBILE_FEATURE = z2;
    }

    public final void setInvisible(View view) {
        kotlin.jvm.internal.n.g(view, "<this>");
        if (view.getVisibility() != 4) {
            view.setVisibility(4);
        }
    }

    public final void setLayoutHeight(View view, int i2, boolean z2) {
        kotlin.jvm.internal.n.g(view, "<this>");
        if (view.getLayoutParams() == null) {
            return;
        }
        view.getLayoutParams().height = i2;
        if (z2) {
            view.setLayoutParams(view.getLayoutParams());
        }
    }

    public final void setLayoutSize(View view, int i2, int i3, boolean z2) {
        kotlin.jvm.internal.n.g(view, "<this>");
        if (view.getLayoutParams() == null) {
            return;
        }
        view.getLayoutParams().width = i2;
        view.getLayoutParams().height = i3;
        if (z2) {
            view.setLayoutParams(view.getLayoutParams());
        }
    }

    public final void setLayoutWidth(View view, int i2, boolean z2) {
        kotlin.jvm.internal.n.g(view, "<this>");
        if (view.getLayoutParams() == null) {
            return;
        }
        view.getLayoutParams().width = i2;
        if (z2) {
            view.setLayoutParams(view.getLayoutParams());
        }
    }

    public final void setMarginBottom(View view, int i2, boolean z2) {
        kotlin.jvm.internal.n.g(view, "<this>");
        if (view.getLayoutParams() == null) {
            return;
        }
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        ViewGroup.MarginLayoutParams marginLayoutParams = layoutParams instanceof ViewGroup.MarginLayoutParams ? (ViewGroup.MarginLayoutParams) layoutParams : null;
        if (marginLayoutParams != null) {
            marginLayoutParams.bottomMargin = i2;
        }
        if (z2) {
            view.setLayoutParams(view.getLayoutParams());
        }
    }

    public final void setMarginEnd(View view, int i2, boolean z2) {
        kotlin.jvm.internal.n.g(view, "<this>");
        if (view.getLayoutParams() == null) {
            return;
        }
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        kotlin.jvm.internal.n.e(layoutParams, "null cannot be cast to non-null type android.view.ViewGroup.MarginLayoutParams");
        ((ViewGroup.MarginLayoutParams) layoutParams).setMarginEnd(i2);
        if (z2) {
            view.setLayoutParams(view.getLayoutParams());
        }
    }

    public final void setMarginHorizontal(View view, int i2, boolean z2) {
        kotlin.jvm.internal.n.g(view, "<this>");
        if (view.getLayoutParams() == null) {
            return;
        }
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        kotlin.jvm.internal.n.e(layoutParams, "null cannot be cast to non-null type android.view.ViewGroup.MarginLayoutParams");
        ((ViewGroup.MarginLayoutParams) layoutParams).setMarginStart(i2);
        ViewGroup.LayoutParams layoutParams2 = view.getLayoutParams();
        kotlin.jvm.internal.n.e(layoutParams2, "null cannot be cast to non-null type android.view.ViewGroup.MarginLayoutParams");
        ((ViewGroup.MarginLayoutParams) layoutParams2).setMarginEnd(i2);
        if (z2) {
            view.setLayoutParams(view.getLayoutParams());
        }
    }

    public final void setMarginLeft(View view, int i2, boolean z2) {
        kotlin.jvm.internal.n.g(view, "<this>");
        if (view.getLayoutParams() == null) {
            return;
        }
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        kotlin.jvm.internal.n.e(layoutParams, "null cannot be cast to non-null type android.view.ViewGroup.MarginLayoutParams");
        ((ViewGroup.MarginLayoutParams) layoutParams).leftMargin = i2;
        if (z2) {
            view.setLayoutParams(view.getLayoutParams());
        }
    }

    public final void setMarginRight(View view, int i2, boolean z2) {
        kotlin.jvm.internal.n.g(view, "<this>");
        if (view.getLayoutParams() == null) {
            return;
        }
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        kotlin.jvm.internal.n.e(layoutParams, "null cannot be cast to non-null type android.view.ViewGroup.MarginLayoutParams");
        ((ViewGroup.MarginLayoutParams) layoutParams).rightMargin = i2;
        if (z2) {
            view.setLayoutParams(view.getLayoutParams());
        }
    }

    public final void setMarginStart(View view, int i2, boolean z2) {
        kotlin.jvm.internal.n.g(view, "<this>");
        if (view.getLayoutParams() == null) {
            return;
        }
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        kotlin.jvm.internal.n.e(layoutParams, "null cannot be cast to non-null type android.view.ViewGroup.MarginLayoutParams");
        ((ViewGroup.MarginLayoutParams) layoutParams).setMarginStart(i2);
        if (z2) {
            view.setLayoutParams(view.getLayoutParams());
        }
    }

    public final void setMarginTop(View view, int i2, boolean z2) {
        kotlin.jvm.internal.n.g(view, "<this>");
        if (view.getLayoutParams() == null) {
            return;
        }
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        kotlin.jvm.internal.n.e(layoutParams, "null cannot be cast to non-null type android.view.ViewGroup.MarginLayoutParams");
        ((ViewGroup.MarginLayoutParams) layoutParams).topMargin = i2;
        if (z2) {
            view.setLayoutParams(view.getLayoutParams());
        }
    }

    public final void setMargins(View view, int i2, int i3, int i4, int i5, boolean z2) {
        kotlin.jvm.internal.n.g(view, "<this>");
        if (view.getLayoutParams() instanceof ViewGroup.MarginLayoutParams) {
            ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
            kotlin.jvm.internal.n.e(layoutParams, "null cannot be cast to non-null type android.view.ViewGroup.MarginLayoutParams");
            ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) layoutParams;
            marginLayoutParams.setMarginStart(i2);
            marginLayoutParams.topMargin = i3;
            marginLayoutParams.setMarginEnd(i4);
            marginLayoutParams.bottomMargin = i5;
            if (z2) {
                view.setLayoutParams(view.getLayoutParams());
            }
        }
    }

    public final void setScaleXEx(View view, float f2) {
        kotlin.jvm.internal.n.g(view, "<this>");
        if (f2 < -3.4028235E38f || f2 > Float.MAX_VALUE) {
            return;
        }
        view.setScaleX(f2);
    }

    public final void setScaleYEx(View view, float f2) {
        kotlin.jvm.internal.n.g(view, "<this>");
        if (f2 < -3.4028235E38f || f2 > Float.MAX_VALUE) {
            return;
        }
        view.setScaleY(f2);
    }

    public final void setUSING_LARGE_SCREEN(boolean z2) {
        USING_LARGE_SCREEN = z2;
    }

    public final void setVisible(View view) {
        kotlin.jvm.internal.n.g(view, "<this>");
        if (view.getVisibility() != 0) {
            view.setVisibility(0);
        }
    }

    public final boolean themeChanged(Configuration configuration, Configuration configuration2) {
        kotlin.jvm.internal.n.g(configuration, "<this>");
        if (configuration2 == null) {
            return false;
        }
        int iUpdateFrom = configuration.updateFrom(configuration2);
        return ((4194304 & iUpdateFrom) != 0 && miuiThemeChanged(configuration2)) || ((iUpdateFrom & 512) != 0);
    }

    public final void traverseScale(View view, float f2) {
        kotlin.jvm.internal.n.g(view, "<this>");
        if (!(view instanceof ViewGroup)) {
            view.setScaleX(f2);
            view.setScaleY(f2);
        } else {
            Iterator it = ViewGroupKt.getChildren((ViewGroup) view).iterator();
            while (it.hasNext()) {
                INSTANCE.traverseScale((View) it.next(), f2);
            }
        }
    }

    public final boolean updateText(TextView textView, CharSequence charSequence) {
        kotlin.jvm.internal.n.g(textView, "<this>");
        if (kotlin.jvm.internal.n.c(textView.getText(), charSequence)) {
            return false;
        }
        textView.setText(charSequence);
        return true;
    }

    public final boolean useAlignEndStyle() {
        return IS_TABLET || (IS_FOLD && USING_LARGE_SCREEN);
    }

    public static /* synthetic */ void setMargins$default(CommonUtils commonUtils, View view, int i2, boolean z2, int i3, Object obj) {
        if ((i3 & 2) != 0) {
            z2 = false;
        }
        commonUtils.setMargins(view, i2, z2);
    }

    public final void setMargins(View view, int i2, boolean z2) {
        kotlin.jvm.internal.n.g(view, "<this>");
        if (view.getLayoutParams() == null) {
            return;
        }
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        kotlin.jvm.internal.n.e(layoutParams, "null cannot be cast to non-null type android.view.ViewGroup.MarginLayoutParams");
        ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) layoutParams;
        marginLayoutParams.topMargin = i2;
        marginLayoutParams.leftMargin = i2;
        marginLayoutParams.bottomMargin = i2;
        marginLayoutParams.rightMargin = i2;
        if (z2) {
            view.setLayoutParams(view.getLayoutParams());
        }
    }
}
