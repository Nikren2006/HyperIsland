package miui.systemui.util;

import android.content.Context;
import android.util.Log;
import java.lang.reflect.Method;
import kotlin.jvm.functions.Function0;

/* JADX INFO: loaded from: classes4.dex */
public final class SystemBarUtilsCompat$SystemBarUtils_getStatusBarHeight$2 extends kotlin.jvm.internal.o implements Function0 {
    public static final SystemBarUtilsCompat$SystemBarUtils_getStatusBarHeight$2 INSTANCE = new SystemBarUtilsCompat$SystemBarUtils_getStatusBarHeight$2();

    public SystemBarUtilsCompat$SystemBarUtils_getStatusBarHeight$2() {
        super(0);
    }

    @Override // kotlin.jvm.functions.Function0
    public final Method invoke() {
        try {
            return Class.forName("com.android.internal.policy.SystemBarUtils").getDeclaredMethod("getStatusBarHeight", Context.class);
        } catch (Throwable th) {
            Log.e("SystemBarUtilsCompat", "get SystemBarUtils_getStatusBarHeight field failed.", th);
            return null;
        }
    }
}
