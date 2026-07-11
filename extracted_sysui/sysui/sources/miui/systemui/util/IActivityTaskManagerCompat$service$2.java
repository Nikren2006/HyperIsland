package miui.systemui.util;

import android.app.IActivityTaskManager;
import android.util.Log;
import java.lang.reflect.Method;
import kotlin.jvm.functions.Function0;

/* JADX INFO: loaded from: classes4.dex */
public final class IActivityTaskManagerCompat$service$2 extends kotlin.jvm.internal.o implements Function0 {
    public static final IActivityTaskManagerCompat$service$2 INSTANCE = new IActivityTaskManagerCompat$service$2();

    public IActivityTaskManagerCompat$service$2() {
        super(0);
    }

    @Override // kotlin.jvm.functions.Function0
    public final IActivityTaskManager invoke() {
        try {
            Method activityTaskManager_getService = IActivityTaskManagerCompat.INSTANCE.getActivityTaskManager_getService();
            Object objInvoke = activityTaskManager_getService != null ? activityTaskManager_getService.invoke(null, null) : null;
            if (objInvoke instanceof IActivityTaskManager) {
                return (IActivityTaskManager) objInvoke;
            }
            return null;
        } catch (Throwable th) {
            Log.e("ActivityTaskManagerCompat", "invoke ActivityTaskManager_getService method failed.", th);
            return null;
        }
    }
}
