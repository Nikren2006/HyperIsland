package miuix.core.util;

import android.R;
import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.Point;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.os.Build;
import android.provider.Settings;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.TypedValue;
import android.view.Display;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.WindowMetrics;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import androidx.mediarouter.media.SystemMediaRouteProvider;
import miuix.internal.util.AttributeResolver;
import miuix.reflect.Reflects;

/* JADX INFO: loaded from: classes3.dex */
public class MiuixUIUtils {
    public static final int FONT_LEVEL_LARGE = 2;
    public static final int FONT_LEVEL_NORMAL = 1;
    private static final String HIDE_GESTURE_LINE = "hide_gesture_line";
    private static final String TAG = "MiuixUtils";
    private static final String USE_GESTURE_VERSION_THREE = "use_gesture_version_three";
    private static TypedValue mTmpValue = new TypedValue();

    public static boolean checkDeviceHasNavigationBar(Context context) {
        String str = SystemProperties.get("qemu.hw.mainkeys");
        if ("1".equals(str)) {
            return false;
        }
        if ("0".equals(str)) {
            return true;
        }
        Resources resources = context.getResources();
        int identifier = resources.getIdentifier("config_showNavigationBar", "bool", SystemMediaRouteProvider.PACKAGE_NAME);
        if (identifier > 0) {
            return resources.getBoolean(identifier);
        }
        return false;
    }

    private static boolean checkMultiWindow(Activity activity) {
        return activity.isInMultiWindowMode();
    }

    public static int dp2px(float f2, float f3) {
        return (int) ((f3 * f2) + 0.5f);
    }

    @Nullable
    public static Activity getActivity(Context context) {
        while (context instanceof ContextWrapper) {
            if (context instanceof Activity) {
                return (Activity) context;
            }
            context = ((ContextWrapper) context).getBaseContext();
        }
        return null;
    }

    public static Integer getColorFromDrawable(@NonNull Drawable drawable) {
        ColorStateList color;
        if (drawable instanceof ColorDrawable) {
            return Integer.valueOf(((ColorDrawable) drawable).getColor());
        }
        if (!(drawable instanceof GradientDrawable) || (color = ((GradientDrawable) drawable).getColor()) == null) {
            return null;
        }
        return Integer.valueOf(color.getColorForState(drawable.getState(), color.getDefaultColor()));
    }

    public static int getDefDimen(Context context, int i2) {
        TypedValue typedValue = new TypedValue();
        context.getResources().getValue(i2, typedValue, true);
        return (int) TypedValue.complexToFloat(typedValue.data);
    }

    public static int getFontLevel(Context context) {
        return context.getResources().getConfiguration().fontScale < 1.55f ? 1 : 2;
    }

    public static int getNaviBarInteractionMode(Context context) {
        Resources resources = context.getResources();
        int identifier = resources.getIdentifier("config_navBarInteractionMode", TypedValues.Custom.S_INT, SystemMediaRouteProvider.PACKAGE_NAME);
        if (identifier > 0) {
            return resources.getInteger(identifier);
        }
        return 0;
    }

    @Deprecated
    public static int getNaviBarIntercationMode(Context context) {
        return getNaviBarInteractionMode(context);
    }

    public static int getNavigationBarHeight(Context context) {
        int realNavigationBarHeight = (isShowNavigationHandle(context) || !isFullScreenGestureMode(context)) ? getRealNavigationBarHeight(context) : 0;
        if (realNavigationBarHeight < 0) {
            return 0;
        }
        return realNavigationBarHeight;
    }

    private static Point getPhysicalSize(Context context) {
        Point point = new Point();
        WindowManager windowManager = (WindowManager) context.getSystemService("window");
        Display defaultDisplay = windowManager.getDefaultDisplay();
        try {
            Object obj = Reflects.get(defaultDisplay, Reflects.getDeclaredField(defaultDisplay.getClass(), "mDisplayInfo"));
            point.x = ((Integer) Reflects.get(obj, Reflects.getField(obj.getClass(), "logicalWidth"))).intValue();
            point.y = ((Integer) Reflects.get(obj, Reflects.getField(obj.getClass(), "logicalHeight"))).intValue();
        } catch (Exception e2) {
            DisplayMetrics displayMetrics = new DisplayMetrics();
            windowManager.getDefaultDisplay().getRealMetrics(displayMetrics);
            point.x = displayMetrics.widthPixels;
            point.y = displayMetrics.heightPixels;
            Log.w(TAG, "catch error! failed to get physical size", e2);
        }
        return point;
    }

    public static int getRealNavigationBarHeight(Context context) {
        Resources resources;
        int identifier;
        if (checkDeviceHasNavigationBar(context) && (identifier = (resources = context.getResources()).getIdentifier("navigation_bar_height", "dimen", SystemMediaRouteProvider.PACKAGE_NAME)) > 0) {
            return resources.getDimensionPixelSize(identifier);
        }
        return 0;
    }

    public static int[] getScreenSizeDp(Context context) {
        float f2 = context.getResources().getDisplayMetrics().density;
        WindowMetrics maximumWindowMetrics = ((WindowManager) context.getSystemService(WindowManager.class)).getMaximumWindowMetrics();
        return new int[]{(int) (maximumWindowMetrics.getBounds().width() / f2), (int) (maximumWindowMetrics.getBounds().height() / f2)};
    }

    public static int getStatusBarHeight(Context context) {
        int identifier = context.getResources().getIdentifier("status_bar_height", "dimen", SystemMediaRouteProvider.PACKAGE_NAME);
        if (identifier > 0) {
            return context.getResources().getDimensionPixelSize(identifier);
        }
        return 0;
    }

    public static boolean isDisplayCutoutModeAlways(Context context) {
        if (context == null) {
            return false;
        }
        Activity activity = getActivity(context);
        if (activity == null) {
            return AttributeResolver.resolveInt(context, R.attr.windowLayoutInDisplayCutoutMode, 0) == 3;
        }
        Window window = activity.getWindow();
        return window != null && window.getAttributes().layoutInDisplayCutoutMode == 3;
    }

    public static boolean isEnableGestureLine(Context context) {
        return Settings.Global.getInt(context.getContentResolver(), HIDE_GESTURE_LINE, 0) == 0;
    }

    @Deprecated
    public static boolean isFreeformMode(Context context) {
        WindowManager windowManager = (WindowManager) context.getSystemService("window");
        Point point = new Point();
        windowManager.getDefaultDisplay().getSize(point);
        Point physicalSize = getPhysicalSize(context);
        return context.getResources().getConfiguration().toString().contains("mWindowingMode=freeform") && ((((float) point.x) + 0.0f) / ((float) physicalSize.x) <= 0.9f || (((float) point.y) + 0.0f) / ((float) physicalSize.y) <= 0.9f);
    }

    public static boolean isFullScreenGestureMode(Context context) {
        return getNaviBarInteractionMode(context) == 2;
    }

    /* JADX WARN: Removed duplicated region for block: B:23:0x0037  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static boolean isHorizontalDirectionEdge2Edge(android.content.Context r4) {
        /*
            boolean r0 = isTargetSdkVersionAboveV(r4)
            r1 = 1
            if (r0 == 0) goto L8
            return r1
        L8:
            android.app.Activity r0 = getActivity(r4)
            r2 = 3
            r3 = 0
            if (r0 == 0) goto L26
            android.view.Window r4 = r0.getWindow()
            if (r4 == 0) goto L39
            android.view.WindowManager$LayoutParams r4 = r4.getAttributes()
            int r4 = r4.layoutInDisplayCutoutMode
            if (r4 != r1) goto L20
            r0 = r1
            goto L21
        L20:
            r0 = r3
        L21:
            if (r0 != 0) goto L38
            if (r4 != r2) goto L37
            goto L38
        L26:
            r0 = 16844166(0x1010586, float:2.369752E-38)
            int r4 = miuix.internal.util.AttributeResolver.resolveInt(r4, r0, r3)
            if (r4 != r1) goto L31
            r0 = r1
            goto L32
        L31:
            r0 = r3
        L32:
            if (r0 != 0) goto L38
            if (r4 != r2) goto L37
            goto L38
        L37:
            r1 = r3
        L38:
            r3 = r1
        L39:
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: miuix.core.util.MiuixUIUtils.isHorizontalDirectionEdge2Edge(android.content.Context):boolean");
    }

    public static boolean isInMultiWindowMode(Context context) {
        while (context instanceof ContextWrapper) {
            if (context instanceof Activity) {
                return checkMultiWindow((Activity) context);
            }
            context = ((ContextWrapper) context).getBaseContext();
        }
        return false;
    }

    public static boolean isLayoutHideNavigation(View view) {
        Context context = view.getContext();
        return isTargetSdkVersionAboveV(context) || isDisplayCutoutModeAlways(context) || (view.getWindowSystemUiVisibility() & 512) != 0;
    }

    public static boolean isLightColor(int i2) {
        if (i2 == 0) {
            return true;
        }
        return ((((double) ((i2 >> 16) & 255)) * 0.299d) + (((double) ((i2 >> 8) & 255)) * 0.587d)) + (((double) (i2 & 255)) * 0.114d) > 128.0d;
    }

    @Deprecated
    public static boolean isNavigationBarFullScreen(Context context) {
        return Settings.Global.getInt(context.getContentResolver(), "force_fsg_nav_bar", 0) != 0;
    }

    public static boolean isShowNavigationHandle(Context context) {
        return isEnableGestureLine(context) && isFullScreenGestureMode(context) && isSupportGestureLine(context);
    }

    public static boolean isSupportGestureLine(Context context) {
        return Settings.Global.getInt(context.getContentResolver(), USE_GESTURE_VERSION_THREE, 0) != 0;
    }

    public static boolean isTallFontLang(Context context) {
        if (context == null) {
            return false;
        }
        String language = context.getResources().getConfiguration().locale.getLanguage();
        return "bo".equals(language) || "ta".equals(language);
    }

    public static boolean isTargetSdkVersionAboveV(Context context) {
        return context != null && Build.VERSION.SDK_INT >= 35 && context.getApplicationContext().getApplicationInfo().targetSdkVersion >= 35;
    }

    public static int px2dp(float f2, float f3) {
        return (int) ((f3 / f2) + 0.5f);
    }

    public static int dp2px(Context context, float f2) {
        return dp2px(context.getResources().getConfiguration().densityDpi / 160.0f, f2);
    }

    public static int px2dp(Context context, float f2) {
        return px2dp(context.getResources().getConfiguration().densityDpi / 160.0f, f2);
    }
}
