package androidx.window.layout;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Point;
import android.graphics.Rect;
import android.util.Log;
import android.view.Display;
import android.view.DisplayCutout;
import androidx.annotation.RequiresApi;
import androidx.annotation.VisibleForTesting;
import androidx.mediarouter.media.SystemMediaRouteProvider;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import kotlin.jvm.internal.n;

/* JADX INFO: loaded from: classes.dex */
public final class WindowMetricsCalculatorCompat implements WindowMetricsCalculator {
    public static final WindowMetricsCalculatorCompat INSTANCE = new WindowMetricsCalculatorCompat();
    private static final String TAG;

    static {
        String simpleName = WindowMetricsCalculatorCompat.class.getSimpleName();
        n.f(simpleName, "WindowMetricsCalculatorC…at::class.java.simpleName");
        TAG = simpleName;
    }

    private WindowMetricsCalculatorCompat() {
    }

    @RequiresApi(28)
    @SuppressLint({"BanUncheckedReflection"})
    private final DisplayCutout getCutoutForDisplay(Display display) {
        try {
            Constructor<?> constructor = Class.forName("android.view.DisplayInfo").getConstructor(null);
            constructor.setAccessible(true);
            Object objNewInstance = constructor.newInstance(null);
            Method declaredMethod = display.getClass().getDeclaredMethod("getDisplayInfo", objNewInstance.getClass());
            declaredMethod.setAccessible(true);
            declaredMethod.invoke(display, objNewInstance);
            Field declaredField = objNewInstance.getClass().getDeclaredField("displayCutout");
            declaredField.setAccessible(true);
            Object obj = declaredField.get(objNewInstance);
            if (obj instanceof DisplayCutout) {
                return (DisplayCutout) obj;
            }
            return null;
        } catch (ClassNotFoundException e2) {
            Log.w(TAG, e2);
            return null;
        } catch (IllegalAccessException e3) {
            Log.w(TAG, e3);
            return null;
        } catch (InstantiationException e4) {
            Log.w(TAG, e4);
            return null;
        } catch (NoSuchFieldException e5) {
            Log.w(TAG, e5);
            return null;
        } catch (NoSuchMethodException e6) {
            Log.w(TAG, e6);
            return null;
        } catch (InvocationTargetException e7) {
            Log.w(TAG, e7);
            return null;
        }
    }

    private final int getNavigationBarHeight(Context context) {
        Resources resources = context.getResources();
        int identifier = resources.getIdentifier("navigation_bar_height", "dimen", SystemMediaRouteProvider.PACKAGE_NAME);
        if (identifier > 0) {
            return resources.getDimensionPixelSize(identifier);
        }
        return 0;
    }

    private final void getRectSizeFromDisplay(Activity activity, Rect rect) {
        activity.getWindowManager().getDefaultDisplay().getRectSize(rect);
    }

    @Override // androidx.window.layout.WindowMetricsCalculator
    public WindowMetrics computeCurrentWindowMetrics(Activity activity) {
        n.g(activity, "activity");
        return new WindowMetrics(ActivityCompatHelperApi30.INSTANCE.currentWindowBounds(activity));
    }

    @Override // androidx.window.layout.WindowMetricsCalculator
    public WindowMetrics computeMaximumWindowMetrics(Activity activity) {
        n.g(activity, "activity");
        return new WindowMetrics(ActivityCompatHelperApi30.INSTANCE.maximumWindowBounds(activity));
    }

    @RequiresApi(14)
    public final Rect computeWindowBoundsIceCreamSandwich$window_release(Activity activity) {
        int i2;
        n.g(activity, "activity");
        Display defaultDisplay = activity.getWindowManager().getDefaultDisplay();
        n.f(defaultDisplay, "defaultDisplay");
        Point realSizeForDisplay$window_release = getRealSizeForDisplay$window_release(defaultDisplay);
        Rect rect = new Rect();
        int i3 = realSizeForDisplay$window_release.x;
        if (i3 == 0 || (i2 = realSizeForDisplay$window_release.y) == 0) {
            defaultDisplay.getRectSize(rect);
        } else {
            rect.right = i3;
            rect.bottom = i2;
        }
        return rect;
    }

    @RequiresApi(24)
    public final Rect computeWindowBoundsN$window_release(Activity activity) {
        n.g(activity, "activity");
        Rect rect = new Rect();
        Display defaultDisplay = activity.getWindowManager().getDefaultDisplay();
        defaultDisplay.getRectSize(rect);
        if (!ActivityCompatHelperApi24.INSTANCE.isInMultiWindowMode(activity)) {
            n.f(defaultDisplay, "defaultDisplay");
            Point realSizeForDisplay$window_release = getRealSizeForDisplay$window_release(defaultDisplay);
            int navigationBarHeight = getNavigationBarHeight(activity);
            int i2 = rect.bottom;
            if (i2 + navigationBarHeight == realSizeForDisplay$window_release.y) {
                rect.bottom = i2 + navigationBarHeight;
            } else {
                int i3 = rect.right;
                if (i3 + navigationBarHeight == realSizeForDisplay$window_release.x) {
                    rect.right = i3 + navigationBarHeight;
                }
            }
        }
        return rect;
    }

    @RequiresApi(28)
    @SuppressLint({"BanUncheckedReflection", "BlockedPrivateApi"})
    public final Rect computeWindowBoundsP$window_release(Activity activity) {
        DisplayCutout cutoutForDisplay;
        n.g(activity, "activity");
        Rect rect = new Rect();
        Configuration configuration = activity.getResources().getConfiguration();
        try {
            Field declaredField = Configuration.class.getDeclaredField("windowConfiguration");
            declaredField.setAccessible(true);
            Object obj = declaredField.get(configuration);
            if (ActivityCompatHelperApi24.INSTANCE.isInMultiWindowMode(activity)) {
                Object objInvoke = obj.getClass().getDeclaredMethod("getBounds", null).invoke(obj, null);
                if (objInvoke == null) {
                    throw new NullPointerException("null cannot be cast to non-null type android.graphics.Rect");
                }
                rect.set((Rect) objInvoke);
            } else {
                Object objInvoke2 = obj.getClass().getDeclaredMethod("getAppBounds", null).invoke(obj, null);
                if (objInvoke2 == null) {
                    throw new NullPointerException("null cannot be cast to non-null type android.graphics.Rect");
                }
                rect.set((Rect) objInvoke2);
            }
        } catch (IllegalAccessException e2) {
            Log.w(TAG, e2);
            getRectSizeFromDisplay(activity, rect);
        } catch (NoSuchFieldException e3) {
            Log.w(TAG, e3);
            getRectSizeFromDisplay(activity, rect);
        } catch (NoSuchMethodException e4) {
            Log.w(TAG, e4);
            getRectSizeFromDisplay(activity, rect);
        } catch (InvocationTargetException e5) {
            Log.w(TAG, e5);
            getRectSizeFromDisplay(activity, rect);
        }
        Display currentDisplay = activity.getWindowManager().getDefaultDisplay();
        Point point = new Point();
        DisplayCompatHelperApi17 displayCompatHelperApi17 = DisplayCompatHelperApi17.INSTANCE;
        n.f(currentDisplay, "currentDisplay");
        displayCompatHelperApi17.getRealSize(currentDisplay, point);
        ActivityCompatHelperApi24 activityCompatHelperApi24 = ActivityCompatHelperApi24.INSTANCE;
        if (!activityCompatHelperApi24.isInMultiWindowMode(activity)) {
            int navigationBarHeight = getNavigationBarHeight(activity);
            int i2 = rect.bottom;
            if (i2 + navigationBarHeight == point.y) {
                rect.bottom = i2 + navigationBarHeight;
            } else {
                int i3 = rect.right;
                if (i3 + navigationBarHeight == point.x) {
                    rect.right = i3 + navigationBarHeight;
                } else if (rect.left == navigationBarHeight) {
                    rect.left = 0;
                }
            }
        }
        if ((rect.width() < point.x || rect.height() < point.y) && !activityCompatHelperApi24.isInMultiWindowMode(activity) && (cutoutForDisplay = getCutoutForDisplay(currentDisplay)) != null) {
            int i4 = rect.left;
            DisplayCompatHelperApi28 displayCompatHelperApi28 = DisplayCompatHelperApi28.INSTANCE;
            if (i4 == displayCompatHelperApi28.safeInsetLeft(cutoutForDisplay)) {
                rect.left = 0;
            }
            if (point.x - rect.right == displayCompatHelperApi28.safeInsetRight(cutoutForDisplay)) {
                rect.right += displayCompatHelperApi28.safeInsetRight(cutoutForDisplay);
            }
            if (rect.top == displayCompatHelperApi28.safeInsetTop(cutoutForDisplay)) {
                rect.top = 0;
            }
            if (point.y - rect.bottom == displayCompatHelperApi28.safeInsetBottom(cutoutForDisplay)) {
                rect.bottom += displayCompatHelperApi28.safeInsetBottom(cutoutForDisplay);
            }
        }
        return rect;
    }

    @RequiresApi(29)
    @SuppressLint({"BanUncheckedReflection", "BlockedPrivateApi"})
    public final Rect computeWindowBoundsQ$window_release(Activity activity) {
        n.g(activity, "activity");
        Configuration configuration = activity.getResources().getConfiguration();
        try {
            Field declaredField = Configuration.class.getDeclaredField("windowConfiguration");
            declaredField.setAccessible(true);
            Object obj = declaredField.get(configuration);
            Object objInvoke = obj.getClass().getDeclaredMethod("getBounds", null).invoke(obj, null);
            if (objInvoke != null) {
                return new Rect((Rect) objInvoke);
            }
            throw new NullPointerException("null cannot be cast to non-null type android.graphics.Rect");
        } catch (IllegalAccessException e2) {
            Log.w(TAG, e2);
            return computeWindowBoundsP$window_release(activity);
        } catch (NoSuchFieldException e3) {
            Log.w(TAG, e3);
            return computeWindowBoundsP$window_release(activity);
        } catch (NoSuchMethodException e4) {
            Log.w(TAG, e4);
            return computeWindowBoundsP$window_release(activity);
        } catch (InvocationTargetException e5) {
            Log.w(TAG, e5);
            return computeWindowBoundsP$window_release(activity);
        }
    }

    @RequiresApi(14)
    @VisibleForTesting
    public final Point getRealSizeForDisplay$window_release(Display display) {
        n.g(display, "display");
        Point point = new Point();
        DisplayCompatHelperApi17.INSTANCE.getRealSize(display, point);
        return point;
    }
}
