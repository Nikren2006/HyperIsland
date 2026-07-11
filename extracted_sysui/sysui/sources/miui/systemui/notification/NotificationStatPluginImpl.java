package miui.systemui.notification;

import android.content.Context;
import android.service.notification.StatusBarNotification;
import android.util.ArrayMap;
import com.android.systemui.plugins.annotations.Requires;
import com.android.systemui.plugins.miui.notification.MiuiNotificationStatPlugin;
import java.util.HashMap;
import miui.systemui.notification.stat.MiuiNotificationStat;
import miui.systemui.notification.stat.NotificationStatForPkg;

/* JADX INFO: loaded from: classes4.dex */
@Requires(target = MiuiNotificationStatPlugin.class, version = 1)
public class NotificationStatPluginImpl implements MiuiNotificationStatPlugin, MiuiNotificationStat {
    private static final boolean DEBUG = false;
    private static final String TAG = "NotificationStatPlugin";
    ArrayMap<String, NotificationStatForPkg> statForPkgs;

    public NotificationStatPluginImpl() {
        ArrayMap<String, NotificationStatForPkg> arrayMap = new ArrayMap<>();
        this.statForPkgs = arrayMap;
        arrayMap.put("com.miui.systemAdSolution", new NotificationStatForPkg("com.miui.systemAdSolution"));
    }

    @Override // miui.systemui.notification.stat.MiuiNotificationStat
    public void handleVisibleEvent(Context context, StatusBarNotification statusBarNotification, HashMap<String, Object> map) {
        NotificationStatForPkg notificationStatForPkg = this.statForPkgs.get(statusBarNotification.getOpPkg());
        if (notificationStatForPkg != null) {
            notificationStatForPkg.handleVisibleEvent(context, statusBarNotification, map);
        }
    }

    public void onPluginEvent(Context context, String str, HashMap<String, Object> map) {
        StatusBarNotification statusBarNotification;
        if (!"notification_visible".equals(str) || (statusBarNotification = (StatusBarNotification) map.get("sbn")) == null) {
            return;
        }
        handleVisibleEvent(context, statusBarNotification, map);
    }
}
