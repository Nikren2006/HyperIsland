package miuix.core.util;

import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.res.Configuration;
import android.graphics.Point;
import android.graphics.Rect;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;
import android.view.WindowMetrics;
import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;

/* JADX INFO: loaded from: classes3.dex */
public class WindowUtils {
    private static final String TAG = "WindowUtils";

    @RequiresApi(30)
    public static WindowMetrics getCurrentWindowMetrics(Context context) {
        return getWindowManager(context).getCurrentWindowMetrics();
    }

    public static Display getDisplay(Context context) {
        try {
            return context.getDisplay();
        } catch (UnsupportedOperationException unused) {
            Log.w(TAG, "This context is not associated with a display. You should use createDisplayContext() to create a display context to work with windows.");
            return getWindowManager(context).getDefaultDisplay();
        }
    }

    public static void getScreenAndWindowSize(Context context, Point point, Point point2) {
        getScreenAndWindowSize(getWindowManager(context), context, point, point2);
    }

    public static void getScreenAndWindowSizeDp(Context context, Point point, Point point2) {
        float f2 = context.getResources().getDisplayMetrics().density;
        getScreenAndWindowSize(context, point, point2);
        point.x = (int) ((point.x / f2) + 0.5f);
        point.y = (int) ((point.y / f2) + 0.5f);
        point2.x = (int) ((point2.x / f2) + 0.5f);
        point2.y = (int) ((point2.y / f2) + 0.5f);
    }

    public static Point getScreenSize(Context context) {
        Point point = new Point();
        getScreenSize(context, point);
        return point;
    }

    public static Point getScreenSizeDp(Context context) {
        float f2 = context.getResources().getDisplayMetrics().density;
        Point screenSize = getScreenSize(context);
        screenSize.x = (int) ((screenSize.x / f2) + 0.5f);
        screenSize.y = (int) ((screenSize.y / f2) + 0.5f);
        return screenSize;
    }

    public static int getScreenType(Configuration configuration) {
        String string = configuration.toString();
        if (string.contains("screenType=0")) {
            return 0;
        }
        if (string.contains("screenType=1")) {
            return 1;
        }
        if (string.contains("screenType=2")) {
            return 2;
        }
        if (string.contains("screenType=3")) {
            return 3;
        }
        if (string.contains("screenType=4")) {
            return 4;
        }
        return string.contains("screenType=5") ? 5 : 0;
    }

    public static void getWindowAppContentSizeFromConfiguration(Context context, Point point) {
        getWindowSize(context.getResources().getConfiguration(), point);
    }

    public static void getWindowBounds(WindowManager windowManager, Context context, Rect rect) {
        Rect bounds = windowManager.getCurrentWindowMetrics().getBounds();
        rect.set(bounds.left, bounds.top, bounds.right, bounds.bottom);
    }

    @Deprecated
    public static int getWindowHeight(Context context) {
        return getWindowSize(context).y;
    }

    public static WindowManager getWindowManager(Context context) {
        return (WindowManager) context.getSystemService("window");
    }

    public static void getWindowSize(Context context, Point point) {
        getWindowSize(getWindowManager(context), context, point);
    }

    public static Point getWindowSizeDp(Context context) {
        float f2 = context.getResources().getDisplayMetrics().density;
        Point windowSize = getWindowSize(context);
        windowSize.x = (int) ((windowSize.x / f2) + 0.5f);
        windowSize.y = (int) ((windowSize.y / f2) + 0.5f);
        return windowSize;
    }

    public static void getWindowSizeDpFromConfiguration(Context context, Point point) {
        Configuration configuration = context.getResources().getConfiguration();
        point.x = configuration.screenWidthDp;
        point.y = configuration.screenHeightDp;
    }

    @Deprecated
    public static int getWindowWidth(Context context) {
        return getWindowSize(context).x;
    }

    @RequiresApi(30)
    public static Rect getWindowsBounds(Context context) {
        return getCurrentWindowMetrics(context).getBounds();
    }

    private static boolean isActivity(Context context) {
        while (context instanceof ContextWrapper) {
            if (context instanceof Activity) {
                return true;
            }
            context = ((ContextWrapper) context).getBaseContext();
        }
        return false;
    }

    public static boolean isFreeformMode(Configuration configuration, Point point, Point point2) {
        return configuration.toString().contains("mWindowingMode=freeform") && ((((float) point2.x) + 0.0f) / ((float) point.x) <= 0.9f || (((float) point2.y) + 0.0f) / ((float) point.y) <= 0.9f);
    }

    public static boolean isPortrait(@NonNull Context context) {
        return context.getResources().getConfiguration().orientation == 1;
    }

    public static void getWindowSize(Configuration configuration, Point point) {
        float f2 = configuration.densityDpi / 160.0f;
        point.x = MiuixUIUtils.dp2px(f2, configuration.screenWidthDp);
        point.y = MiuixUIUtils.dp2px(f2, configuration.screenHeightDp);
    }

    public static void getScreenAndWindowSize(WindowManager windowManager, Context context, Point point, Point point2) {
        Rect bounds = windowManager.getMaximumWindowMetrics().getBounds();
        point.x = bounds.width();
        point.y = bounds.height();
        Rect bounds2 = windowManager.getCurrentWindowMetrics().getBounds();
        point2.x = bounds2.width();
        point2.y = bounds2.height();
    }

    public static void getScreenSize(Context context, Point point) {
        getScreenSize(getWindowManager(context), context, point);
    }

    public static void getScreenSize(WindowManager windowManager, Context context, Point point) {
        Rect bounds = windowManager.getMaximumWindowMetrics().getBounds();
        point.x = bounds.width();
        point.y = bounds.height();
    }

    public static void getWindowSize(Configuration configuration, int i2, Point point) {
        int i3 = configuration.densityDpi;
        float f2 = (i3 / 160.0f) * ((i2 * 1.0f) / i3);
        point.x = MiuixUIUtils.dp2px(f2, configuration.screenWidthDp);
        point.y = MiuixUIUtils.dp2px(f2, configuration.screenHeightDp);
    }

    public static Point getWindowSizeDp(View view) {
        float f2 = view.getContext().getResources().getDisplayMetrics().density;
        Point windowSize = getWindowSize(view);
        windowSize.x = (int) ((windowSize.x / f2) + 0.5f);
        windowSize.y = (int) ((windowSize.y / f2) + 0.5f);
        return windowSize;
    }

    public static void getWindowSize(WindowManager windowManager, Context context, Point point) {
        Rect bounds = windowManager.getCurrentWindowMetrics().getBounds();
        point.x = bounds.width();
        point.y = bounds.height();
    }

    public static Point getWindowSize(Context context) {
        Point point = new Point();
        getWindowSize(context, point);
        return point;
    }

    public static Point getWindowSize(View view) {
        Point point = new Point();
        WindowMetrics currentWindowMetrics = ((WindowManager) view.getContext().getSystemService(WindowManager.class)).getCurrentWindowMetrics();
        point.x = currentWindowMetrics.getBounds().width();
        point.y = currentWindowMetrics.getBounds().height();
        return point;
    }
}
