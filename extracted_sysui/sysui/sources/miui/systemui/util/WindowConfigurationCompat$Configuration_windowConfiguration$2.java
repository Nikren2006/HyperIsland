package miui.systemui.util;

import android.content.res.Configuration;
import android.util.Log;
import java.lang.reflect.Field;
import kotlin.jvm.functions.Function0;

/* JADX INFO: loaded from: classes4.dex */
public final class WindowConfigurationCompat$Configuration_windowConfiguration$2 extends kotlin.jvm.internal.o implements Function0 {
    public static final WindowConfigurationCompat$Configuration_windowConfiguration$2 INSTANCE = new WindowConfigurationCompat$Configuration_windowConfiguration$2();

    public WindowConfigurationCompat$Configuration_windowConfiguration$2() {
        super(0);
    }

    @Override // kotlin.jvm.functions.Function0
    public final Field invoke() {
        try {
            return Configuration.class.getDeclaredField("windowConfiguration");
        } catch (Throwable th) {
            Log.e("WindowConfigurationCompat", "get Configuration_windowConfiguration field failed.", th);
            return null;
        }
    }
}
