package miui.systemui.notification.stat;

import android.content.Context;
import android.service.notification.StatusBarNotification;
import java.util.HashMap;

/* JADX INFO: loaded from: classes4.dex */
public interface MiuiNotificationStat {
    default void handleVisibleEvent(Context context, StatusBarNotification statusBarNotification, HashMap<String, Object> map) {
    }
}
