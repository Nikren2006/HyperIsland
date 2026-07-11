package miui.systemui.util;

import android.app.IActivityTaskManager;
import android.app.ITaskStackListener;
import android.util.Log;
import java.lang.reflect.Method;
import kotlin.jvm.functions.Function0;

/* JADX INFO: loaded from: classes4.dex */
public final class IActivityTaskManagerCompat$IActivityTaskManager_registerTaskStackListener$2 extends kotlin.jvm.internal.o implements Function0 {
    public static final IActivityTaskManagerCompat$IActivityTaskManager_registerTaskStackListener$2 INSTANCE = new IActivityTaskManagerCompat$IActivityTaskManager_registerTaskStackListener$2();

    public IActivityTaskManagerCompat$IActivityTaskManager_registerTaskStackListener$2() {
        super(0);
    }

    @Override // kotlin.jvm.functions.Function0
    public final Method invoke() {
        try {
            return IActivityTaskManager.class.getDeclaredMethod("registerTaskStackListener", ITaskStackListener.class);
        } catch (Throwable th) {
            Log.e("ActivityTaskManagerCompat", "get IActivityTaskManager_registerTaskStackListener method failed.", th);
            return null;
        }
    }
}
