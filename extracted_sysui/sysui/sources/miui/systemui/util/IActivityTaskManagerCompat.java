package miui.systemui.util;

import android.annotation.SuppressLint;
import android.app.ActivityTaskManager;
import android.app.IActivityTaskManager;
import android.app.ITaskStackListener;
import android.util.Log;
import java.lang.reflect.Method;

/* JADX INFO: loaded from: classes4.dex */
@SuppressLint({"BlockedPrivateApi"})
public final class IActivityTaskManagerCompat {
    private static final String TAG = "ActivityTaskManagerCompat";
    public static final IActivityTaskManagerCompat INSTANCE = new IActivityTaskManagerCompat();
    private static final H0.d service$delegate = H0.e.b(IActivityTaskManagerCompat$service$2.INSTANCE);
    private static final H0.d ActivityTaskManager_getService$delegate = H0.e.b(IActivityTaskManagerCompat$ActivityTaskManager_getService$2.INSTANCE);
    private static final H0.d IActivityTaskManager_getLockTaskModeState$delegate = H0.e.b(IActivityTaskManagerCompat$IActivityTaskManager_getLockTaskModeState$2.INSTANCE);
    private static final H0.d IActivityTaskManager_getFocusedRootTaskInfo$delegate = H0.e.b(IActivityTaskManagerCompat$IActivityTaskManager_getFocusedRootTaskInfo$2.INSTANCE);
    private static final H0.d IActivityTaskManager_registerTaskStackListener$delegate = H0.e.b(IActivityTaskManagerCompat$IActivityTaskManager_registerTaskStackListener$2.INSTANCE);
    private static final H0.d IActivityTaskManager_unregisterTaskStackListener$delegate = H0.e.b(IActivityTaskManagerCompat$IActivityTaskManager_unregisterTaskStackListener$2.INSTANCE);

    private IActivityTaskManagerCompat() {
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Method getActivityTaskManager_getService() {
        return (Method) ActivityTaskManager_getService$delegate.getValue();
    }

    public static final ActivityTaskManager.RootTaskInfo getFocusedRootTaskInfo() {
        try {
            IActivityTaskManagerCompat iActivityTaskManagerCompat = INSTANCE;
            IActivityTaskManager service = iActivityTaskManagerCompat.getService();
            if (service == null) {
                return null;
            }
            Method iActivityTaskManager_getFocusedRootTaskInfo = iActivityTaskManagerCompat.getIActivityTaskManager_getFocusedRootTaskInfo();
            Object objInvoke = iActivityTaskManager_getFocusedRootTaskInfo != null ? iActivityTaskManager_getFocusedRootTaskInfo.invoke(service, null) : null;
            if (objInvoke instanceof ActivityTaskManager.RootTaskInfo) {
                return (ActivityTaskManager.RootTaskInfo) objInvoke;
            }
            return null;
        } catch (Throwable th) {
            Log.e(TAG, "invoke IActivityTaskManager_getFocusedRootTaskInfo method failed.", th);
            return null;
        }
    }

    public static /* synthetic */ void getFocusedRootTaskInfo$annotations() {
    }

    private final Method getIActivityTaskManager_getFocusedRootTaskInfo() {
        return (Method) IActivityTaskManager_getFocusedRootTaskInfo$delegate.getValue();
    }

    private final Method getIActivityTaskManager_getLockTaskModeState() {
        return (Method) IActivityTaskManager_getLockTaskModeState$delegate.getValue();
    }

    private final Method getIActivityTaskManager_registerTaskStackListener() {
        return (Method) IActivityTaskManager_registerTaskStackListener$delegate.getValue();
    }

    private final Method getIActivityTaskManager_unregisterTaskStackListener() {
        return (Method) IActivityTaskManager_unregisterTaskStackListener$delegate.getValue();
    }

    public static final Integer getLockTaskModeState() {
        try {
            IActivityTaskManagerCompat iActivityTaskManagerCompat = INSTANCE;
            IActivityTaskManager service = iActivityTaskManagerCompat.getService();
            if (service == null) {
                return null;
            }
            Method iActivityTaskManager_getLockTaskModeState = iActivityTaskManagerCompat.getIActivityTaskManager_getLockTaskModeState();
            Object objInvoke = iActivityTaskManager_getLockTaskModeState != null ? iActivityTaskManager_getLockTaskModeState.invoke(service, null) : null;
            if (objInvoke instanceof Integer) {
                return (Integer) objInvoke;
            }
            return null;
        } catch (Throwable th) {
            Log.e(TAG, "invoke IActivityTaskManager_getLockTaskModeState method failed.", th);
            return null;
        }
    }

    public static /* synthetic */ void getLockTaskModeState$annotations() {
    }

    private final IActivityTaskManager getService() {
        return (IActivityTaskManager) service$delegate.getValue();
    }

    public static final void registerTaskStackListener(ITaskStackListener listener) {
        Method iActivityTaskManager_registerTaskStackListener;
        kotlin.jvm.internal.n.g(listener, "listener");
        try {
            IActivityTaskManagerCompat iActivityTaskManagerCompat = INSTANCE;
            IActivityTaskManager service = iActivityTaskManagerCompat.getService();
            if (service == null || (iActivityTaskManager_registerTaskStackListener = iActivityTaskManagerCompat.getIActivityTaskManager_registerTaskStackListener()) == null) {
                return;
            }
            iActivityTaskManager_registerTaskStackListener.invoke(service, listener);
        } catch (Throwable th) {
            Log.e(TAG, "invoke IActivityTaskManager_registerTaskStackListener method failed.", th);
        }
    }

    public static final void unregisterTaskStackListener(ITaskStackListener listener) {
        Method iActivityTaskManager_unregisterTaskStackListener;
        kotlin.jvm.internal.n.g(listener, "listener");
        try {
            IActivityTaskManagerCompat iActivityTaskManagerCompat = INSTANCE;
            IActivityTaskManager service = iActivityTaskManagerCompat.getService();
            if (service == null || (iActivityTaskManager_unregisterTaskStackListener = iActivityTaskManagerCompat.getIActivityTaskManager_unregisterTaskStackListener()) == null) {
                return;
            }
            iActivityTaskManager_unregisterTaskStackListener.invoke(service, listener);
        } catch (Throwable th) {
            Log.e(TAG, "invoke IActivityTaskManager_unregisterTaskStackListener method failed.", th);
        }
    }
}
