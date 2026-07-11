package androidx.core.view;

import android.R;
import android.content.Context;
import android.content.res.Resources;
import android.util.Log;
import android.util.TypedValue;
import android.view.InputDevice;
import android.view.ViewConfiguration;
import androidx.annotation.ReplaceWith;
import androidx.annotation.RequiresApi;
import androidx.core.util.Supplier;
import androidx.mediarouter.media.SystemMediaRouteProvider;
import java.lang.reflect.Method;

/* JADX INFO: loaded from: classes.dex */
public final class ViewConfigurationCompat {
    private static final int NO_FLING_MAX_VELOCITY = Integer.MIN_VALUE;
    private static final int NO_FLING_MIN_VELOCITY = Integer.MAX_VALUE;
    private static final int RESOURCE_ID_NOT_SUPPORTED = -1;
    private static final int RESOURCE_ID_SUPPORTED_BUT_NOT_FOUND = 0;
    private static final String TAG = "ViewConfigCompat";
    private static Method sGetScaledScrollFactorMethod;

    @RequiresApi(26)
    public static class Api26Impl {
        private Api26Impl() {
        }

        public static float getScaledHorizontalScrollFactor(ViewConfiguration viewConfiguration) {
            return viewConfiguration.getScaledHorizontalScrollFactor();
        }

        public static float getScaledVerticalScrollFactor(ViewConfiguration viewConfiguration) {
            return viewConfiguration.getScaledVerticalScrollFactor();
        }
    }

    @RequiresApi(28)
    public static class Api28Impl {
        private Api28Impl() {
        }

        public static int getScaledHoverSlop(ViewConfiguration viewConfiguration) {
            return viewConfiguration.getScaledHoverSlop();
        }

        public static boolean shouldShowMenuShortcutsWhenKeyboardPresent(ViewConfiguration viewConfiguration) {
            return viewConfiguration.shouldShowMenuShortcutsWhenKeyboardPresent();
        }
    }

    @RequiresApi(34)
    public static class Api34Impl {
        private Api34Impl() {
        }

        public static int getScaledMaximumFlingVelocity(ViewConfiguration viewConfiguration, int i2, int i3, int i4) {
            return viewConfiguration.getScaledMaximumFlingVelocity(i2, i3, i4);
        }

        public static int getScaledMinimumFlingVelocity(ViewConfiguration viewConfiguration, int i2, int i3, int i4) {
            return viewConfiguration.getScaledMinimumFlingVelocity(i2, i3, i4);
        }
    }

    private ViewConfigurationCompat() {
    }

    private static int getCompatFlingVelocityThreshold(Resources resources, int i2, Supplier<Integer> supplier, int i3) {
        int dimensionPixelSize;
        return i2 != -1 ? (i2 == 0 || (dimensionPixelSize = resources.getDimensionPixelSize(i2)) < 0) ? i3 : dimensionPixelSize : supplier.get().intValue();
    }

    private static float getLegacyScrollFactor(ViewConfiguration viewConfiguration, Context context) {
        if (sGetScaledScrollFactorMethod != null) {
            try {
                return ((Integer) r0.invoke(viewConfiguration, null)).intValue();
            } catch (Exception unused) {
                Log.i(TAG, "Could not find method getScaledScrollFactor() on ViewConfiguration");
            }
        }
        TypedValue typedValue = new TypedValue();
        if (context.getTheme().resolveAttribute(R.attr.listPreferredItemHeight, typedValue, true)) {
            return typedValue.getDimension(context.getResources().getDisplayMetrics());
        }
        return 0.0f;
    }

    private static int getPlatformResId(Resources resources, String str, String str2) {
        return resources.getIdentifier(str, str2, SystemMediaRouteProvider.PACKAGE_NAME);
    }

    private static int getPreApi34MaximumFlingVelocityResId(Resources resources, int i2, int i3) {
        if (i2 == 4194304 && i3 == 26) {
            return getPlatformResId(resources, "config_viewMaxRotaryEncoderFlingVelocity", "dimen");
        }
        return -1;
    }

    private static int getPreApi34MinimumFlingVelocityResId(Resources resources, int i2, int i3) {
        if (i2 == 4194304 && i3 == 26) {
            return getPlatformResId(resources, "config_viewMinRotaryEncoderFlingVelocity", "dimen");
        }
        return -1;
    }

    public static float getScaledHorizontalScrollFactor(ViewConfiguration viewConfiguration, Context context) {
        return Api26Impl.getScaledHorizontalScrollFactor(viewConfiguration);
    }

    public static int getScaledHoverSlop(ViewConfiguration viewConfiguration) {
        return Api28Impl.getScaledHoverSlop(viewConfiguration);
    }

    public static int getScaledMaximumFlingVelocity(Context context, ViewConfiguration viewConfiguration, int i2, int i3, int i4) {
        return Api34Impl.getScaledMaximumFlingVelocity(viewConfiguration, i2, i3, i4);
    }

    public static int getScaledMinimumFlingVelocity(Context context, ViewConfiguration viewConfiguration, int i2, int i3, int i4) {
        return Api34Impl.getScaledMinimumFlingVelocity(viewConfiguration, i2, i3, i4);
    }

    @ReplaceWith(expression = "config.getScaledPagingTouchSlop()")
    @Deprecated
    public static int getScaledPagingTouchSlop(ViewConfiguration viewConfiguration) {
        return viewConfiguration.getScaledPagingTouchSlop();
    }

    public static float getScaledVerticalScrollFactor(ViewConfiguration viewConfiguration, Context context) {
        return Api26Impl.getScaledVerticalScrollFactor(viewConfiguration);
    }

    @ReplaceWith(expression = "config.hasPermanentMenuKey()")
    @Deprecated
    public static boolean hasPermanentMenuKey(ViewConfiguration viewConfiguration) {
        return viewConfiguration.hasPermanentMenuKey();
    }

    private static boolean isInputDeviceInfoValid(int i2, int i3, int i4) {
        InputDevice device = InputDevice.getDevice(i2);
        return (device == null || device.getMotionRange(i3, i4) == null) ? false : true;
    }

    public static boolean shouldShowMenuShortcutsWhenKeyboardPresent(ViewConfiguration viewConfiguration, Context context) {
        return Api28Impl.shouldShowMenuShortcutsWhenKeyboardPresent(viewConfiguration);
    }
}
