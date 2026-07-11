package miui.systemui.util;

import android.app.IActivityTaskManager;
import android.util.Log;
import java.lang.reflect.Method;
import kotlin.jvm.functions.Function0;

/* JADX INFO: loaded from: classes4.dex */
public final class IActivityTaskManagerCompat$IActivityTaskManager_getLockTaskModeState$2 extends kotlin.jvm.internal.o implements Function0 {
    public static final IActivityTaskManagerCompat$IActivityTaskManager_getLockTaskModeState$2 INSTANCE = new IActivityTaskManagerCompat$IActivityTaskManager_getLockTaskModeState$2();

    public IActivityTaskManagerCompat$IActivityTaskManager_getLockTaskModeState$2() {
        super(0);
    }

    @Override // kotlin.jvm.functions.Function0
    public final Method invoke() {
        try {
            return IActivityTaskManager.class.getDeclaredMethod("getLockTaskModeState", null);
        } catch (Throwable th) {
            Log.e("ActivityTaskManagerCompat", "get IActivityTaskManager_getLockTaskModeState method failed.", th);
            return null;
        }
    }
}
