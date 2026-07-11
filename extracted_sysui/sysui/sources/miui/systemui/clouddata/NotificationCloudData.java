package miui.systemui.clouddata;

import android.content.Context;
import java.util.List;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.n;

/* JADX INFO: loaded from: classes2.dex */
public final class NotificationCloudData {
    public static final Companion Companion = new Companion(null);
    private static final String KEY_DYNAMIC_ISLAND_MINI_WINDOW_BLACKLIST = "dynamic_island_mini_window_blacklist";
    private static final String KEY_FOCUS_DELETED_BLACKLIST = "focus_deleted_blacklist";
    private static final String KEY_WHITELIST = "whitelist";
    private static final String MODULE_DYNAMIC_ISLAND_MINI_WINDOW_BLACKLIST = "systemui_dynamic_island_mini_window_blacklist";
    private static final String MODULE_FOCUS_DELETED_BLACKLIST = "systemui_focus_deleted_blacklist";
    private static final String MODULE_FOCUS_WHITELIST = "systemui_focus_whitelist";

    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final List<String> getDynamicIslandMiniWindowBlackList(Context context) {
            n.g(context, "context");
            String cloudDataString = CloudDataManager.Companion.getCloudDataString(context, NotificationCloudData.MODULE_DYNAMIC_ISLAND_MINI_WINDOW_BLACKLIST, NotificationCloudData.KEY_DYNAMIC_ISLAND_MINI_WINDOW_BLACKLIST);
            CloudDataFormat cloudDataFormat = CloudDataFormat.INSTANCE;
            return cloudDataFormat.jsonArray2List(cloudDataFormat.createJSONArray(cloudDataString));
        }

        public final List<String> getFocusNotificationDeletedBlackList(Context context) {
            n.g(context, "context");
            String cloudDataString = CloudDataManager.Companion.getCloudDataString(context, NotificationCloudData.MODULE_FOCUS_DELETED_BLACKLIST, NotificationCloudData.KEY_FOCUS_DELETED_BLACKLIST);
            CloudDataFormat cloudDataFormat = CloudDataFormat.INSTANCE;
            return cloudDataFormat.jsonArray2List(cloudDataFormat.createJSONArray(cloudDataString));
        }

        public final List<String> getFocusWhitelist(Context context) {
            n.g(context, "context");
            String cloudDataString = CloudDataManager.Companion.getCloudDataString(context, NotificationCloudData.MODULE_FOCUS_WHITELIST, NotificationCloudData.KEY_WHITELIST);
            CloudDataFormat cloudDataFormat = CloudDataFormat.INSTANCE;
            return cloudDataFormat.jsonArray2List(cloudDataFormat.createJSONArray(cloudDataString));
        }

        private Companion() {
        }
    }
}
