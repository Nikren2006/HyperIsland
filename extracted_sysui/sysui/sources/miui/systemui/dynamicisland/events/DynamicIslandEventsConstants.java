package miui.systemui.dynamicisland.events;

/* JADX INFO: loaded from: classes3.dex */
public final class DynamicIslandEventsConstants {
    public static final DynamicIslandEventsConstants INSTANCE = new DynamicIslandEventsConstants();

    public static final class EventID {
        public static final String EVENT_CLICK = "click";
        public static final String EVENT_DRAG = "drag";
        public static final String EVENT_DROP_DOWN = "drop_down";
        public static final String EVENT_EXPOSE = "expose";
        public static final String EVENT_FOLD = "fold";
        public static final String EVENT_SLIDE = "slide";
        public static final EventID INSTANCE = new EventID();

        private EventID() {
        }
    }

    public static final class EventKey {
        public static final EventKey INSTANCE = new EventKey();
        public static final String KEY_APP_NAME = "app_name";
        public static final String KEY_CHANNEL_TYPE = "channel_type";
        public static final String KEY_DISAPPEAR_TYPE = "disappear_type";
        public static final String KEY_ELEMENT_NAME = "element_name";
        public static final String KEY_EXPAND_TYPE = "expand_type";
        public static final String KEY_EXPOSE_DURATION = "expose_duration";
        public static final String KEY_ISLAND_FORM = "island_form";
        public static final String KEY_ITEM = "item";
        public static final String KEY_NOTIFICATION_TAG = "notification_tag";
        public static final String KEY_NOTIFICATION_TYPE = "notification_type";
        public static final String KEY_SBN_ID = "sbn_id";
        public static final String KEY_SEND_PKG = "send_pkg";
        public static final String KEY_SERVICE_SCENE = "service_scene";

        private EventKey() {
        }
    }

    public static final class EventTips {
        public static final EventTips INSTANCE = new EventTips();
        public static final String TIP_CLICK_EXPANDED = "1868.1.2.1.45220";
        public static final String TIP_CLICK_SUMMARY = "1868.1.1.1.45216";
        public static final String TIP_EXPOSE_EXPANDED = "1868.1.2.1.45219";
        public static final String TIP_EXPOSE_SUMMARY = "1868.1.1.1.45215";

        private EventTips() {
        }
    }

    public static final class Other {
        public static final String EXTRA_ISLAND_ITEM = "notif_extend_items";
        public static final String EXTRA_MIUI_FOCUS_PARAM = "miui.focus.param";
        public static final String EXTRA_MIUI_FOCUS_PARAM_CHANNEL_TYPE = "miui.focus.param.channeltype";
        public static final String EXTRA_MIUI_FOCUS_PARAM_CUSTOM = "miui.focus.param.custom";
        public static final String EXTRA_MIUI_FOCUS_RV = "miui.focus.rv";
        public static final Other INSTANCE = new Other();
        public static final String KEY_FOCUS_EXTRA_BUSINESS = "business";
        public static final String KEY_FOCUS_EXTRA_IS_SHOW_NOTIFICATION = "isShowNotification";
        public static final String KEY_FOCUS_EXTRA_PARAM = "param_v2";
        public static final String KEY_FOCUS_EXTRA_PARAM_ISLAND = "param_island";
        public static final String KEY_FOCUS_EXTRA_SHARE_DATA = "shareData";
        public static final String KEY_FOCUS_EXTRA_UPDATABLE = "updatable";
        public static final String PHONE_TYPE_COMMON = "直板";
        public static final String PHONE_TYPE_FLIP = "flip";
        public static final String PHONE_TYPE_FOLD = "fold";
        public static final String PKG_XMSF = "com.xiaomi.xmsf";
        public static final String VALUE_ISLAND_FORM_BIG_ISLAND = "大";
        public static final String VALUE_ISLAND_FORM_EXPANDED_ISLAND = "展开";
        public static final String VALUE_ISLAND_FORM_SMALL_ISLAND = "小";

        private Other() {
        }
    }

    public static final class PublicParams {
        public static final PublicParams INSTANCE = new PublicParams();
        public static final String KEY_NOTIFICATION_DATA_VERSION = "notification_data_version";
        public static final String KEY_TIP = "tip";
        public static final String PHONE_TYPE = "phone_type";
        public static final String SCREEN_TYPE = "screen_type";
        public static final int VALUE_NOTIFICATION_DATA_VERSION = 23032700;

        private PublicParams() {
        }
    }

    public static final class Values {
        public static final String EXPAND_TYPE_ACTIVE = "主动";
        public static final String EXPAND_TYPE_PASSIVE = "被动";
        public static final Values INSTANCE = new Values();
        public static final float MINIMUM_AREA_RATIO = 0.7f;
        public static final long MINIMUM_EXPOSE_TIME = 999;
        public static final String VALUE_CHANNEL_TYPE_NORMAL = "normal";
        public static final String VALUE_CLICKED_ELEMENT_ISLAND = "岛";
        public static final String VALUE_CLICKED_ELEMENT_ISLAND_ACTION = "按钮";
        public static final String VALUE_DISAPPEAR_TYPE_DELETE = "删除";
        public static final String VALUE_DISAPPEAR_TYPE_END = "结束";
        public static final String VALUE_DISAPPEAR_TYPE_HIDDEN = "隐藏";
        public static final String VALUE_DISAPPEAR_TYPE_OTHER = "其他";
        public static final String VALUE_DISAPPEAR_TYPE_PULL_TO_EXPAND = "下拉展开";
        public static final String VALUE_NOTIFICATION_TYPE_CONSISTANT_NOTIF = "持续更新通知";
        public static final String VALUE_NOTIFICATION_TYPE_CONSISTANT_SERVICE_ALERT = "持续服务提醒";
        public static final String VALUE_NOTIFICATION_TYPE_ONCE_DEVICE_ALERT = "一次性设备提醒";
        public static final String VALUE_NOTIFICATION_TYPE_ONCE_NOTIF = "一次性通知";
        public static final String VALUE_SERVICE_SCENE_NOT_FOUND = "未获取";

        private Values() {
        }
    }

    private DynamicIslandEventsConstants() {
    }
}
