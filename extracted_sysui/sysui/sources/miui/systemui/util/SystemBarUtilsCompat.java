package miui.systemui.util;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;
import java.lang.reflect.Method;

/* JADX INFO: loaded from: classes4.dex */
@SuppressLint({"PrivateApi"})
public final class SystemBarUtilsCompat {
    public static final SystemBarUtilsCompat INSTANCE = new SystemBarUtilsCompat();
    private static final H0.d SystemBarUtils_getStatusBarHeight$delegate = H0.e.b(SystemBarUtilsCompat$SystemBarUtils_getStatusBarHeight$2.INSTANCE);
    private static final String TAG = "SystemBarUtilsCompat";

    private SystemBarUtilsCompat() {
    }

    private final Method getSystemBarUtils_getStatusBarHeight() {
        return (Method) SystemBarUtils_getStatusBarHeight$delegate.getValue();
    }

    public final Integer getStatusBarHeightCompat(Context context) {
        kotlin.jvm.internal.n.g(context, "<this>");
        try {
            Method systemBarUtils_getStatusBarHeight = getSystemBarUtils_getStatusBarHeight();
            Object objInvoke = systemBarUtils_getStatusBarHeight != null ? systemBarUtils_getStatusBarHeight.invoke(null, context) : null;
            kotlin.jvm.internal.n.e(objInvoke, "null cannot be cast to non-null type kotlin.Int");
            return (Integer) objInvoke;
        } catch (Throwable th) {
            Log.e(TAG, "invoke SystemBarUtils_getStatusBarHeight field failed.", th);
            return null;
        }
    }
}
