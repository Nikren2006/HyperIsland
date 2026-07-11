package miui.systemui.notification;

import android.app.NotificationChannel;
import android.content.Context;
import android.os.UserHandle;
import android.service.notification.NotificationListenerService;
import android.service.notification.StatusBarNotification;
import android.util.Log;
import com.android.systemui.plugins.ActivityStarter;
import com.android.systemui.plugins.NotificationListenerController;
import com.android.systemui.plugins.PluginDependency;
import com.android.systemui.plugins.annotations.DependsOn;
import com.android.systemui.plugins.annotations.Requirements;
import com.android.systemui.plugins.annotations.Requires;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.function.BiConsumer;
import miui.systemui.dagger.PluginComponentInitializer;
import miui.systemui.drawable.AppIconsManager;
import miui.systemui.notification.auth.AuthManager;
import miui.systemui.notification.focus.Const;
import miui.systemui.notification.focus.FocusIconCache;
import miui.systemui.notification.focus.FocusNotifUtils;
import miui.systemui.notification.focus.FocusNotificationController;
import miui.systemui.plugins.PluginBase;
import miui.systemui.util.FocusNotificationUtil;

/* JADX INFO: loaded from: classes4.dex */
@DependsOn(target = NotificationListenerController.NotificationProvider.class)
@Requirements({@Requires(target = NotificationListenerController.class, version = 1), @Requires(target = ActivityStarter.class, version = 2)})
public class FocusNotificationPluginImpl extends PluginBase implements NotificationListenerController {
    private static final int REASON_CANCEL_END_LIFETIME_EXTENSION = 1000;
    private static final int REASON_CANCEL_FORCE_END_LIFETIME_EXTENSION = 1001;
    private HashMap<String, Boolean> alreadyExtendLifetime = new HashMap<>();
    public E0.a focusNotificationController;
    private boolean isFlipDevice;
    private int mFlags;
    public E0.a mFocusNotifUtils;
    public NotificationSettingsManager mNotifSettingsMgr;
    private Context mPluginCtx;
    private NotificationListenerController.NotificationProvider mProvider;
    private Context mSysuiCtx;

    private boolean isFlipDevice() {
        try {
            return ((Boolean) Class.forName("miui.util.MiuiMultiDisplayTypeInfo").getMethod("isFlipDevice", null).invoke(null, null)).booleanValue();
        } catch (ClassNotFoundException | IllegalAccessException | NoSuchMethodException | InvocationTargetException unused) {
            return false;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void lambda$dump$0(PrintWriter printWriter, String str, Long l2) {
        printWriter.println("        " + str + "-" + l2);
    }

    public void dump(FileDescriptor fileDescriptor, final PrintWriter printWriter, String[] strArr) {
        printWriter.println("    FocusNotificationPluginImpl start");
        ((FocusNotifUtils) this.mFocusNotifUtils.get()).getMaxSeq().forEach(new BiConsumer() { // from class: miui.systemui.notification.a
            @Override // java.util.function.BiConsumer
            public final void accept(Object obj, Object obj2) {
                FocusNotificationPluginImpl.lambda$dump$0(printWriter, (String) obj, (Long) obj2);
            }
        });
        printWriter.println("    FocusNotificationPluginImpl end");
    }

    public StatusBarNotification[] getActiveNotifications(StatusBarNotification[] statusBarNotificationArr) {
        return super.getActiveNotifications(statusBarNotificationArr);
    }

    public NotificationListenerService.RankingMap getCurrentRanking(NotificationListenerService.RankingMap rankingMap) {
        return super.getCurrentRanking(rankingMap);
    }

    @Override // miui.systemui.plugins.PluginBase
    public void onCreated() {
        PluginComponentInitializer.getPluginComponent().inject(this);
        this.mSysuiCtx = getSysuiContext();
        this.mPluginCtx = getPluginContext();
        this.isFlipDevice = isFlipDevice();
        FocusNotificationUtil.INSTANCE.setActivityStarter((ActivityStarter) PluginDependency.get(this, ActivityStarter.class));
    }

    @Override // miui.systemui.plugins.PluginBase
    public void onDestroyed() {
        AppIconsManager.INSTANCE.clearAllIconCache();
        AuthManager.INSTANCE.disConnect(this.mSysuiCtx);
    }

    public void onListenerConnected(NotificationListenerController.NotificationProvider notificationProvider) {
        NotificationUtil.debugLog(Const.TAG, "plugin onListenerConnected " + this);
        this.isFlipDevice = isFlipDevice();
        this.mProvider = notificationProvider;
        FocusNotificationUtil.INSTANCE.setNotificationProvider(notificationProvider);
    }

    public boolean onNotificationChannelModified(String str, UserHandle userHandle, NotificationChannel notificationChannel, int i2) {
        return super.onNotificationChannelModified(str, userHandle, notificationChannel, i2);
    }

    public boolean onNotificationPosted(StatusBarNotification statusBarNotification, NotificationListenerService.RankingMap rankingMap) {
        return ((FocusNotificationController) this.focusNotificationController.get()).onNotificationPosted(statusBarNotification, this.mPluginCtx, this.mSysuiCtx, this.mProvider);
    }

    public boolean onNotificationRemoved(StatusBarNotification statusBarNotification, NotificationListenerService.RankingMap rankingMap) {
        String key = statusBarNotification.getKey();
        int i2 = statusBarNotification.getNotification().extras.getInt(Const.Param.REMOVE_REASON);
        if (i2 == 1001) {
            ((FocusNotificationController) this.focusNotificationController.get()).removeExtendLifetime(key);
        }
        boolean zNeedExtendLifetime = ((FocusNotificationController) this.focusNotificationController.get()).needExtendLifetime(key);
        Log.d(Const.TAG, "NotifLifetime: onNotificationRemoved, key: " + key + ", needExtendLifetime: " + zNeedExtendLifetime + ", reason: " + i2);
        if (zNeedExtendLifetime) {
            this.alreadyExtendLifetime.put(key, Boolean.TRUE);
            return super.onNotificationRemoved(statusBarNotification, rankingMap);
        }
        if (i2 == 1000) {
            Boolean bool = Boolean.FALSE;
            if (bool.equals(this.alreadyExtendLifetime.getOrDefault(key, bool))) {
                return super.onNotificationRemoved(statusBarNotification, rankingMap);
            }
        }
        this.alreadyExtendLifetime.remove(key);
        FocusIconCache.INSTANCE.remove(key);
        AppIconsManager.INSTANCE.clearIconCache(statusBarNotification.getUser().getIdentifier(), statusBarNotification.getPackageName());
        ((FocusNotificationController) this.focusNotificationController.get()).onNotificationRemoved(statusBarNotification, true);
        return super.onNotificationRemoved(statusBarNotification, rankingMap);
    }
}
