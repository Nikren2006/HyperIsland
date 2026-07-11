package miui.systemui.notification;

import android.app.ActivityTaskManager;
import miui.systemui.util.IActivityTaskManagerCompat;

/* JADX INFO: loaded from: classes4.dex */
public final class ActivityManagerWrapper {
    public static final ActivityManagerWrapper INSTANCE = new ActivityManagerWrapper();

    private ActivityManagerWrapper() {
    }

    public final ActivityTaskManager.RootTaskInfo getFocusedRootTaskInfo() {
        return IActivityTaskManagerCompat.getFocusedRootTaskInfo();
    }

    public final boolean isLockToAppActive() {
        Integer lockTaskModeState = IActivityTaskManagerCompat.getLockTaskModeState();
        return lockTaskModeState == null || lockTaskModeState.intValue() != 0;
    }
}
