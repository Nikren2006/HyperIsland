package miui.systemui.util;

import android.app.WindowConfiguration;
import android.util.Log;
import java.lang.reflect.Method;
import kotlin.jvm.functions.Function0;

/* JADX INFO: loaded from: classes4.dex */
public final class WindowConfigurationCompat$WindowConfiguration_getMaxBounds$2 extends kotlin.jvm.internal.o implements Function0 {
    public static final WindowConfigurationCompat$WindowConfiguration_getMaxBounds$2 INSTANCE = new WindowConfigurationCompat$WindowConfiguration_getMaxBounds$2();

    public WindowConfigurationCompat$WindowConfiguration_getMaxBounds$2() {
        super(0);
    }

    @Override // kotlin.jvm.functions.Function0
    public final Method invoke() {
        try {
            return WindowConfiguration.class.getDeclaredMethod("getMaxBounds", null);
        } catch (Throwable th) {
            Log.e("WindowConfigurationCompat", "get WindowConfiguration_getMaxBounds method failed.", th);
            return null;
        }
    }
}
