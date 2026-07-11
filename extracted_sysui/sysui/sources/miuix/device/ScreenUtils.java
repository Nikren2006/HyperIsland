package miuix.device;

import android.content.Context;
import android.graphics.Point;
import android.graphics.Rect;
import android.util.Log;
import android.view.Display;
import android.view.WindowManager;

/* JADX INFO: loaded from: classes3.dex */
class ScreenUtils {
    private static final String TAG = "ScreenUtils";

    public static Display getDisplay(Context context) {
        try {
            return context.getDisplay();
        } catch (UnsupportedOperationException unused) {
            Log.w(TAG, "This context is not associated with a display. You should use createDisplayContext() to create a display context to work with windows.");
            return getWindowManager(context).getDefaultDisplay();
        }
    }

    public static Point getScreenSize(Context context) {
        Point point = new Point();
        getScreenSize(context, point);
        return point;
    }

    public static Point getScreenSizeDp(Context context) {
        float f2 = context.getResources().getDisplayMetrics().density;
        Point screenSize = getScreenSize(context);
        screenSize.x = (int) (screenSize.x / f2);
        screenSize.y = (int) (screenSize.y / f2);
        return screenSize;
    }

    public static WindowManager getWindowManager(Context context) {
        return (WindowManager) context.getSystemService("window");
    }

    public static void getScreenSize(Context context, Point point) {
        Rect bounds = getWindowManager(context).getMaximumWindowMetrics().getBounds();
        point.x = bounds.width();
        point.y = bounds.height();
    }
}
