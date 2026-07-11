package miui.systemui.util;

import android.app.ActivityTaskManager;
import android.util.Log;
import java.lang.reflect.Method;
import kotlin.jvm.functions.Function0;

/* JADX INFO: loaded from: classes4.dex */
public final class IActivityTaskManagerCompat$ActivityTaskManager_getService$2 extends kotlin.jvm.internal.o implements Function0 {
    public static final IActivityTaskManagerCompat$ActivityTaskManager_getService$2 INSTANCE = new IActivityTaskManagerCompat$ActivityTaskManager_getService$2();

    public IActivityTaskManagerCompat$ActivityTaskManager_getService$2() {
        super(0);
    }

    @Override // kotlin.jvm.functions.Function0
    public final Method invoke() {
        try {
            return ActivityTaskManager.class.getDeclaredMethod("getService", null);
        } catch (Throwable th) {
            Log.e("ActivityTaskManagerCompat", "get ActivityTaskManager_getService method failed.", th);
            return null;
        }
    }
}
