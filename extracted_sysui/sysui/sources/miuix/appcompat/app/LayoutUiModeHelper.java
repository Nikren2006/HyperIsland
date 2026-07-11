package miuix.appcompat.app;

import android.R;
import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.provider.Settings;
import android.view.Window;
import android.view.WindowInsetsController;
import androidx.core.view.accessibility.AccessibilityEventCompat;
import miuix.core.util.EnvStateManager;
import miuix.core.util.RomUtils;
import miuix.internal.util.AttributeResolver;

/* JADX INFO: loaded from: classes2.dex */
public class LayoutUiModeHelper {
    public static void autoSetLayoutUiMode(Activity activity) {
        Window window;
        if (isTargetSdkVersionAboveV(activity)) {
            return;
        }
        if (activity instanceof AppCompatActivity) {
            AppCompatActivity appCompatActivity = (AppCompatActivity) activity;
            if (appCompatActivity.isFloatingWindowTheme() && appCompatActivity.isInFloatingWindowMode()) {
                activity.getWindow().addFlags(134217728);
                return;
            }
        }
        if (activity == null || (window = activity.getWindow()) == null) {
            return;
        }
        autoSetLayoutUiMode(activity, window);
    }

    public static boolean isHideGestureLine(Context context) {
        return Settings.Global.getInt(context.getContentResolver(), "hide_gesture_line", 0) != 0;
    }

    @Deprecated
    public static boolean isMiuiInFullScreenGestureMode(Context context) {
        return EnvStateManager.isFullScreenGestureMode(context);
    }

    private static boolean isTargetSdkVersionAboveV(Activity activity) {
        return Build.VERSION.SDK_INT >= 35 && activity.getApplicationInfo().targetSdkVersion >= 35;
    }

    public static void setAndroidNativeStateBarTransparent(Activity activity) {
        Window window;
        if ((!RomUtils.isAndroidNative(activity) && !RomUtils.isGoogleSystem()) || activity == null || (window = activity.getWindow()) == null) {
            return;
        }
        boolean zIsFullScreenGestureMode = EnvStateManager.isFullScreenGestureMode(activity);
        window.clearFlags(AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL);
        if (zIsFullScreenGestureMode) {
            window.clearFlags(134217728);
            window.setNavigationBarColor(0);
            window.getDecorView().setSystemUiVisibility(1792);
        } else {
            window.getDecorView().setSystemUiVisibility(1280);
        }
        window.addFlags(Integer.MIN_VALUE);
        window.setStatusBarColor(0);
        boolean zResolveBoolean = AttributeResolver.resolveBoolean(activity, R.attr.isLightTheme, true);
        WindowInsetsController insetsController = window.getInsetsController();
        if (insetsController != null) {
            if (zResolveBoolean) {
                insetsController.setSystemBarsAppearance(8, 8);
                if (zIsFullScreenGestureMode) {
                    return;
                }
                insetsController.setSystemBarsAppearance(16, 16);
                return;
            }
            insetsController.setSystemBarsAppearance(0, 8);
            if (zIsFullScreenGestureMode) {
                return;
            }
            insetsController.setSystemBarsAppearance(0, 16);
        }
    }

    public static void autoSetLayoutUiMode(Activity activity, Window window) {
        if (isTargetSdkVersionAboveV(activity)) {
            return;
        }
        String string = activity.getResources().getConfiguration().toString();
        boolean zContains = string.contains("mWindowingMode=freeform");
        boolean zContains2 = string.contains("miui-magic-windows");
        if (!EnvStateManager.isFullScreenGestureMode(activity) && (zContains2 || !zContains)) {
            window.clearFlags(134217728);
        } else {
            window.addFlags(134217728);
        }
        window.addFlags(AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL);
    }
}
