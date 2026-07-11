package miui.systemui.notification;

import android.content.Context;
import android.os.Handler;
import android.os.SystemProperties;
import java.util.Arrays;
import java.util.List;
import miui.systemui.clouddata.CloudDataListener;
import miui.systemui.clouddata.CloudDataManager;
import miui.systemui.clouddata.NotificationCloudData;
import miui.systemui.dagger.qualifiers.Background;
import miui.systemui.quicksettings.common.R;

/* JADX INFO: loaded from: classes4.dex */
public class NotificationSettingsManager {
    public static final boolean DEBUG = SystemProperties.getBoolean("debug.sysui", false);
    private List<String> mAllowCustomFocusPkgs;
    private List<String> mAllowFocusPkgs;
    private Context mContext;
    private List<String> mDynamicIslandMiniWindowBlackList;
    private List<String> mFocusNotificationDeletedBlackList;
    private List<String> mPassXMSPkgs;

    public NotificationSettingsManager(Context context, CloudDataManager cloudDataManager, @Background final Handler handler) {
        this.mContext = context;
        cloudDataManager.registerListener(new CloudDataListener() { // from class: miui.systemui.notification.e
            @Override // miui.systemui.clouddata.CloudDataListener
            public final void onCloudDataUpdate(boolean z2) {
                this.f5793a.lambda$new$0(handler, z2);
            }
        });
        this.mAllowFocusPkgs = getStringArray(R.array.config_canShowFocusPackages);
        this.mAllowCustomFocusPkgs = getStringArray(R.array.config_canShowCustomFocusPackages);
        this.mFocusNotificationDeletedBlackList = getStringArray(R.array.config_focus_notification_deleted_blacklist);
        this.mPassXMSPkgs = getStringArray(R.array.config_pass_xms_permission);
        this.mDynamicIslandMiniWindowBlackList = getStringArray(R.array.config_dynamic_island_miniwindow_blackList);
    }

    private List<String> getStringArray(int i2) {
        return Arrays.asList(this.mContext.getResources().getStringArray(i2));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$new$0(Handler handler, boolean z2) {
        handler.post(new Runnable() { // from class: miui.systemui.notification.d
            @Override // java.lang.Runnable
            public final void run() {
                this.f5792a.onCloudDataUpdated();
            }
        });
    }

    public boolean canCustomFocus(String str) {
        return this.mAllowCustomFocusPkgs.contains(str) || DEBUG;
    }

    public boolean canPassXMSPermission(String str) {
        return this.mPassXMSPkgs.contains(str);
    }

    public boolean canShowFocus(Context context, String str) {
        return this.mAllowFocusPkgs.contains(str) || DEBUG;
    }

    public boolean isDynamicIslandMiniWindowBlackList(String str) {
        return this.mDynamicIslandMiniWindowBlackList.contains(str);
    }

    public boolean isFocusNotificationDeletedBlackList(String str) {
        return this.mFocusNotificationDeletedBlackList.contains(str);
    }

    public void onCloudDataUpdated() {
        NotificationCloudData.Companion companion = NotificationCloudData.Companion;
        List<String> focusWhitelist = companion.getFocusWhitelist(this.mContext);
        if (focusWhitelist != null && !focusWhitelist.isEmpty()) {
            this.mAllowFocusPkgs = focusWhitelist;
        }
        List<String> focusNotificationDeletedBlackList = companion.getFocusNotificationDeletedBlackList(this.mContext);
        if (focusNotificationDeletedBlackList != null && !focusNotificationDeletedBlackList.isEmpty()) {
            this.mFocusNotificationDeletedBlackList = focusNotificationDeletedBlackList;
        }
        List<String> dynamicIslandMiniWindowBlackList = companion.getDynamicIslandMiniWindowBlackList(this.mContext);
        if (dynamicIslandMiniWindowBlackList == null || dynamicIslandMiniWindowBlackList.isEmpty()) {
            return;
        }
        this.mDynamicIslandMiniWindowBlackList = dynamicIslandMiniWindowBlackList;
    }
}
