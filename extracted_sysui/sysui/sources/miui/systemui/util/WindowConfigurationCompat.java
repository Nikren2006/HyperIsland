package miui.systemui.util;

import android.annotation.SuppressLint;
import android.app.WindowConfiguration;
import android.content.res.Configuration;
import android.graphics.Rect;
import android.util.Log;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

/* JADX INFO: loaded from: classes4.dex */
@SuppressLint({"BlockedPrivateApi"})
public final class WindowConfigurationCompat {
    private static final String TAG = "WindowConfigurationCompat";
    public static final WindowConfigurationCompat INSTANCE = new WindowConfigurationCompat();
    private static final H0.d Configuration_windowConfiguration$delegate = H0.e.b(WindowConfigurationCompat$Configuration_windowConfiguration$2.INSTANCE);
    private static final H0.d WindowConfiguration_getMaxBounds$delegate = H0.e.b(WindowConfigurationCompat$WindowConfiguration_getMaxBounds$2.INSTANCE);
    private static final H0.d WindowConfiguration_getWindowingMode$delegate = H0.e.b(WindowConfigurationCompat$WindowConfiguration_getWindowingMode$2.INSTANCE);
    private static final H0.d WindowConfiguration_inMultiWindowMode$delegate = H0.e.b(WindowConfigurationCompat$WindowConfiguration_inMultiWindowMode$2.INSTANCE);

    private WindowConfigurationCompat() {
    }

    private final Field getConfiguration_windowConfiguration() {
        return (Field) Configuration_windowConfiguration$delegate.getValue();
    }

    private final Method getWindowConfiguration_getMaxBounds() {
        return (Method) WindowConfiguration_getMaxBounds$delegate.getValue();
    }

    private final Method getWindowConfiguration_getWindowingMode() {
        return (Method) WindowConfiguration_getWindowingMode$delegate.getValue();
    }

    private final Method getWindowConfiguration_inMultiWindowMode() {
        return (Method) WindowConfiguration_inMultiWindowMode$delegate.getValue();
    }

    public final Rect getMaxBoundsCompat(WindowConfiguration windowConfiguration) {
        kotlin.jvm.internal.n.g(windowConfiguration, "<this>");
        try {
            Method windowConfiguration_getMaxBounds = getWindowConfiguration_getMaxBounds();
            Object objInvoke = windowConfiguration_getMaxBounds != null ? windowConfiguration_getMaxBounds.invoke(windowConfiguration, null) : null;
            kotlin.jvm.internal.n.e(objInvoke, "null cannot be cast to non-null type android.graphics.Rect");
            return (Rect) objInvoke;
        } catch (Throwable th) {
            Log.e(TAG, "invoke WindowConfiguration_getMaxBounds method failed.", th);
            return null;
        }
    }

    public final WindowConfiguration getWindowConfigurationCompat(Configuration configuration) {
        kotlin.jvm.internal.n.g(configuration, "<this>");
        try {
            Field configuration_windowConfiguration = getConfiguration_windowConfiguration();
            Object obj = configuration_windowConfiguration != null ? configuration_windowConfiguration.get(configuration) : null;
            kotlin.jvm.internal.n.e(obj, "null cannot be cast to non-null type android.app.WindowConfiguration");
            return (WindowConfiguration) obj;
        } catch (Throwable th) {
            Log.e(TAG, "invoke Configuration_windowConfiguration field failed.", th);
            return null;
        }
    }

    public final Integer getWindowingModeCompat(WindowConfiguration windowConfiguration) {
        kotlin.jvm.internal.n.g(windowConfiguration, "<this>");
        try {
            Method windowConfiguration_getWindowingMode = getWindowConfiguration_getWindowingMode();
            Object objInvoke = windowConfiguration_getWindowingMode != null ? windowConfiguration_getWindowingMode.invoke(windowConfiguration, null) : null;
            kotlin.jvm.internal.n.e(objInvoke, "null cannot be cast to non-null type kotlin.Int");
            return (Integer) objInvoke;
        } catch (Throwable th) {
            Log.e(TAG, "invoke WindowConfiguration_getWindowingMode method failed.", th);
            return null;
        }
    }

    public final Boolean inMultiWindowMode(int i2) {
        try {
            Method windowConfiguration_inMultiWindowMode = getWindowConfiguration_inMultiWindowMode();
            Object objInvoke = windowConfiguration_inMultiWindowMode != null ? windowConfiguration_inMultiWindowMode.invoke(null, Integer.valueOf(i2)) : null;
            kotlin.jvm.internal.n.e(objInvoke, "null cannot be cast to non-null type kotlin.Boolean");
            return (Boolean) objInvoke;
        } catch (Throwable th) {
            Log.e(TAG, "invoke WindowConfiguration_inMultiWindowMode method failed.", th);
            return null;
        }
    }
}
