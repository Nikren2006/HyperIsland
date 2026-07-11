package miui.systemui.util;

import android.app.PendingIntent;
import android.content.Intent;
import android.service.notification.StatusBarNotification;
import com.android.systemui.plugins.ActivityStarter;
import com.android.systemui.plugins.NotificationListenerController;

/* JADX INFO: loaded from: classes4.dex */
public final class FocusNotificationUtil {
    public static final FocusNotificationUtil INSTANCE = new FocusNotificationUtil();
    private static ActivityStarter activityStarter;
    private static NotificationListenerController.NotificationProvider notificationProvider;

    private FocusNotificationUtil() {
    }

    public static final void onActionClick(Intent intent) {
        kotlin.jvm.internal.n.g(intent, "intent");
        ActivityStarter activityStarter2 = activityStarter;
        if (activityStarter2 != null) {
            activityStarter2.postStartActivityDismissingKeyguard(intent, 350);
        }
    }

    public static final void removeFocusNotification(StatusBarNotification sbn) {
        kotlin.jvm.internal.n.g(sbn, "sbn");
        NotificationListenerController.NotificationProvider notificationProvider2 = notificationProvider;
        if (notificationProvider2 != null) {
            notificationProvider2.removeNotification(sbn);
        }
    }

    public final ActivityStarter getActivityStarter() {
        return activityStarter;
    }

    public final NotificationListenerController.NotificationProvider getNotificationProvider() {
        return notificationProvider;
    }

    public final void setActivityStarter(ActivityStarter activityStarter2) {
        activityStarter = activityStarter2;
    }

    public final void setNotificationProvider(NotificationListenerController.NotificationProvider notificationProvider2) {
        notificationProvider = notificationProvider2;
    }

    public static final void onActionClick(PendingIntent pendingIntent) throws PendingIntent.CanceledException {
        kotlin.jvm.internal.n.g(pendingIntent, "pendingIntent");
        if (!pendingIntent.isActivity()) {
            pendingIntent.send();
            return;
        }
        ActivityStarter activityStarter2 = activityStarter;
        if (activityStarter2 != null) {
            activityStarter2.postStartActivityDismissingKeyguard(pendingIntent.getIntent(), 350);
        }
    }
}
