package miui.systemui.dynamicisland.events;

import android.app.Notification;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.service.notification.StatusBarNotification;
import android.text.TextUtils;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.n;
import miui.systemui.dynamicisland.DynamicIslandUtils;
import miui.systemui.dynamicisland.events.DynamicIslandEventsConstants;
import miui.systemui.util.CommonUtils;
import org.json.JSONObject;
import systemui.plugin.eventtracking.trackers.BaseEventTracker;

/* JADX INFO: loaded from: classes3.dex */
public final class DynamicIslandEventTracker {
    public static final Companion Companion = new Companion(null);
    private static final String DEFAULT_ISLAND_STRING_NULL = "null";

    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private final String getPhoneType() {
            return CommonUtils.isFlipDevice() ? DynamicIslandEventsConstants.Other.PHONE_TYPE_FLIP : CommonUtils.INSTANCE.getIS_FOLD() ? "fold" : DynamicIslandEventsConstants.Other.PHONE_TYPE_COMMON;
        }

        private final String resolveAppName(Context context, String str) {
            try {
                PackageManager packageManager = context.getPackageManager();
                return packageManager.getApplicationLabel(packageManager.getApplicationInfo(str, 0)).toString();
            } catch (PackageManager.NameNotFoundException unused) {
                return DynamicIslandEventTracker.DEFAULT_ISLAND_STRING_NULL;
            }
        }

        private final String resolveChannelType(StatusBarNotification statusBarNotification) {
            Bundle bundle;
            Notification notification = statusBarNotification.getNotification();
            String string = (notification == null || (bundle = notification.extras) == null) ? null : bundle.getString(DynamicIslandEventsConstants.Other.EXTRA_MIUI_FOCUS_PARAM_CHANNEL_TYPE);
            return string == null ? DynamicIslandEventsConstants.Values.VALUE_CHANNEL_TYPE_NORMAL : string;
        }

        private final String resolveClickTip(String str) {
            return n.c(str, DynamicIslandEventsConstants.Other.VALUE_ISLAND_FORM_SMALL_ISLAND) ? true : n.c(str, DynamicIslandEventsConstants.Other.VALUE_ISLAND_FORM_BIG_ISLAND) ? DynamicIslandEventsConstants.EventTips.TIP_CLICK_SUMMARY : DynamicIslandEventsConstants.EventTips.TIP_CLICK_EXPANDED;
        }

        private final String resolveExtraItems(StatusBarNotification statusBarNotification) {
            Bundle bundle;
            Notification notification = statusBarNotification.getNotification();
            String string = (notification == null || (bundle = notification.extras) == null) ? null : bundle.getString(DynamicIslandEventsConstants.Other.EXTRA_ISLAND_ITEM);
            return string == null ? DynamicIslandEventTracker.DEFAULT_ISLAND_STRING_NULL : string;
        }

        private final String resolveNotifTag(StatusBarNotification statusBarNotification) {
            String tag = statusBarNotification.getTag();
            return tag == null ? DynamicIslandEventTracker.DEFAULT_ISLAND_STRING_NULL : tag;
        }

        private final String resolveNotifType(StatusBarNotification statusBarNotification) {
            JSONObject jSONObjectResolveFocusParam = DynamicIslandUtils.INSTANCE.resolveFocusParam(statusBarNotification);
            if (jSONObjectResolveFocusParam == null) {
                return DynamicIslandEventTracker.DEFAULT_ISLAND_STRING_NULL;
            }
            boolean zOptBoolean = jSONObjectResolveFocusParam.optBoolean("isShowNotification", true);
            boolean zOptBoolean2 = jSONObjectResolveFocusParam.optBoolean("updatable");
            return (zOptBoolean && zOptBoolean2) ? DynamicIslandEventsConstants.Values.VALUE_NOTIFICATION_TYPE_CONSISTANT_NOTIF : zOptBoolean ? DynamicIslandEventsConstants.Values.VALUE_NOTIFICATION_TYPE_ONCE_NOTIF : zOptBoolean2 ? DynamicIslandEventsConstants.Values.VALUE_NOTIFICATION_TYPE_CONSISTANT_SERVICE_ALERT : DynamicIslandEventsConstants.Values.VALUE_NOTIFICATION_TYPE_ONCE_DEVICE_ALERT;
        }

        private final String resolvePackage(StatusBarNotification statusBarNotification) {
            String packageName = statusBarNotification.getPackageName();
            if (TextUtils.equals("com.xiaomi.xmsf", packageName)) {
                packageName = null;
            }
            if (packageName != null) {
                return packageName;
            }
            String opPkg = statusBarNotification.getOpPkg();
            n.f(opPkg, "getOpPkg(...)");
            return opPkg;
        }

        private final String resolveServiceScene(StatusBarNotification statusBarNotification) {
            JSONObject jSONObjectResolveFocusParam = DynamicIslandUtils.INSTANCE.resolveFocusParam(statusBarNotification);
            if (jSONObjectResolveFocusParam == null) {
                return DynamicIslandEventTracker.DEFAULT_ISLAND_STRING_NULL;
            }
            String strOptString = jSONObjectResolveFocusParam.optString(DynamicIslandEventsConstants.Other.KEY_FOCUS_EXTRA_BUSINESS, DynamicIslandEventsConstants.Values.VALUE_SERVICE_SCENE_NOT_FOUND);
            n.f(strOptString, "optString(...)");
            return strOptString;
        }

        public final void trackExpandedClick(Context context, StatusBarNotification statusBarNotification, String expandedType, String screenType) {
            n.g(context, "context");
            n.g(expandedType, "expandedType");
            n.g(screenType, "screenType");
            if (statusBarNotification == null) {
                return;
            }
            BaseEventTracker.Companion.get().track(new DynamicIslandExpandedClick(resolveAppName(context, resolvePackage(statusBarNotification)), resolvePackage(statusBarNotification), String.valueOf(statusBarNotification.getId()), resolveNotifTag(statusBarNotification), resolveNotifType(statusBarNotification), resolveServiceScene(statusBarNotification), expandedType, DynamicIslandEventsConstants.Values.VALUE_CLICKED_ELEMENT_ISLAND, resolveChannelType(statusBarNotification), resolveExtraItems(statusBarNotification), DynamicIslandEventsConstants.EventTips.TIP_CLICK_EXPANDED, getPhoneType(), screenType));
        }

        public final void trackExpandedExpose(Context context, StatusBarNotification statusBarNotification, int i2, String expandedType, String screenType) {
            n.g(context, "context");
            n.g(expandedType, "expandedType");
            n.g(screenType, "screenType");
            if (statusBarNotification == null) {
                return;
            }
            BaseEventTracker.Companion.get().track(new DynamicIslandExpandedExpose(resolveAppName(context, resolvePackage(statusBarNotification)), resolvePackage(statusBarNotification), String.valueOf(statusBarNotification.getId()), resolveNotifTag(statusBarNotification), resolveNotifType(statusBarNotification), resolveServiceScene(statusBarNotification), i2, expandedType, "展开", resolveChannelType(statusBarNotification), resolveExtraItems(statusBarNotification), DynamicIslandEventsConstants.EventTips.TIP_EXPOSE_EXPANDED, getPhoneType(), screenType));
        }

        public final void trackSummaryClick(Context context, StatusBarNotification statusBarNotification, String islandForm, String screenType) {
            n.g(context, "context");
            n.g(islandForm, "islandForm");
            n.g(screenType, "screenType");
            if (statusBarNotification == null) {
                return;
            }
            BaseEventTracker.Companion.get().track(new DynamicIslandSummaryClick(resolveAppName(context, resolvePackage(statusBarNotification)), resolvePackage(statusBarNotification), String.valueOf(statusBarNotification.getId()), resolveNotifTag(statusBarNotification), resolveNotifType(statusBarNotification), resolveServiceScene(statusBarNotification), islandForm, resolveChannelType(statusBarNotification), resolveExtraItems(statusBarNotification), resolveClickTip(islandForm), getPhoneType(), screenType));
        }

        public final void trackSummaryExpose(Context context, StatusBarNotification statusBarNotification, int i2, String disappearType, String islandForm, String screenType) {
            n.g(context, "context");
            n.g(disappearType, "disappearType");
            n.g(islandForm, "islandForm");
            n.g(screenType, "screenType");
            if (statusBarNotification == null) {
                return;
            }
            BaseEventTracker.Companion.get().track(new DynamicIslandSummaryExpose(resolveAppName(context, resolvePackage(statusBarNotification)), resolvePackage(statusBarNotification), String.valueOf(statusBarNotification.getId()), resolveNotifTag(statusBarNotification), resolveNotifType(statusBarNotification), resolveServiceScene(statusBarNotification), i2, disappearType, islandForm, resolveChannelType(statusBarNotification), resolveExtraItems(statusBarNotification), DynamicIslandEventsConstants.EventTips.TIP_EXPOSE_SUMMARY, getPhoneType(), screenType));
        }

        private Companion() {
        }

        public final void trackExpandedClick(Context context, StatusBarNotification statusBarNotification, String buttonText, String expandedType, String screenType) {
            n.g(context, "context");
            n.g(buttonText, "buttonText");
            n.g(expandedType, "expandedType");
            n.g(screenType, "screenType");
            if (statusBarNotification == null) {
                return;
            }
            BaseEventTracker.Companion.get().track(new DynamicIslandExpandedClick(resolveAppName(context, resolvePackage(statusBarNotification)), resolvePackage(statusBarNotification), String.valueOf(statusBarNotification.getId()), resolveNotifTag(statusBarNotification), resolveNotifType(statusBarNotification), resolveServiceScene(statusBarNotification), expandedType, DynamicIslandEventsConstants.Values.VALUE_CLICKED_ELEMENT_ISLAND, resolveChannelType(statusBarNotification), resolveExtraItems(statusBarNotification), DynamicIslandEventsConstants.EventTips.TIP_CLICK_EXPANDED, getPhoneType(), screenType));
        }
    }
}
