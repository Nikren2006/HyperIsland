package miui.systemui.devicecontrols.eventtracking;

import kotlin.jvm.internal.n;
import miui.systemui.devicecontrols.dagger.qualifiers.DeviceControlsScope;
import systemui.plugin.eventtracking.trackers.BaseEventTracker;
import systemui.plugin.eventtracking.utils.EventsUtils;

/* JADX INFO: loaded from: classes3.dex */
@DeviceControlsScope
public final class DeviceControlsEventTracker {
    public static final String DEVICE_STATUS_OFF = "关闭";
    public static final String DEVICE_STATUS_ON = "开启";
    public static final String DEVICE_STATUS_UNCHANGING = "未改变状态";
    public static final String EQUIPMENT_STYLE_ITEM = "设备";
    public static final String EQUIPMENT_STYLE_SCENE_ITEM = "一键";
    public static final DeviceControlsEventTracker INSTANCE = new DeviceControlsEventTracker();
    public static final String IS_SKIP = "是";
    public static final String NOT_SKIP = "否";
    public static final String OPEN_WAY_CHANGE = "切换应用";
    public static final String OPEN_WAY_CLICK = "点击智能生活卡片";
    public static final String OPEN_WAY_OTHER = "其他";
    public static final String SCENE_IS_KEYGUARD = "锁屏";
    public static final String SCENE_NOT_KEYGUARD = "非锁屏";
    private static final String TAG = "DeviceControlsEventTracker";
    private static final String UNKNOWN = "未知";

    private DeviceControlsEventTracker() {
    }

    public final void trackAppChangeEvent(boolean z2) {
        BaseEventTracker.Companion companion = BaseEventTracker.Companion.get();
        EventsUtils eventsUtils = EventsUtils.INSTANCE;
        companion.track(new AppChangeEvent(eventsUtils.getGetDate(), eventsUtils.getVersionName(), z2 ? SCENE_IS_KEYGUARD : SCENE_NOT_KEYGUARD, null, 8, null));
    }

    public final void trackControlsEditAppJumpClickEvent(String str, String str2, boolean z2) {
        BaseEventTracker.Companion companion = BaseEventTracker.Companion.get();
        EventsUtils eventsUtils = EventsUtils.INSTANCE;
        companion.track(new ControlsEditAppJumpClickEvent(eventsUtils.getGetDate(), eventsUtils.getVersionName(), str == null ? "未知" : str, str2 == null ? "未知" : str2, z2 ? SCENE_IS_KEYGUARD : SCENE_NOT_KEYGUARD, null, 32, null));
    }

    public final void trackControlsEditExposedEvent(String str, String str2, String skip, boolean z2) {
        n.g(skip, "skip");
        BaseEventTracker.Companion companion = BaseEventTracker.Companion.get();
        EventsUtils eventsUtils = EventsUtils.INSTANCE;
        companion.track(new ControlsEditExposedEvent(eventsUtils.getGetDate(), eventsUtils.getVersionName(), str == null ? "未知" : str, str2 == null ? "未知" : str2, skip, z2 ? SCENE_IS_KEYGUARD : SCENE_NOT_KEYGUARD, null, 64, null));
    }

    public final void trackDevicesBindExposedEvent(String str, String str2, String openWay, int i2, int i3, int i4, boolean z2) {
        n.g(openWay, "openWay");
        BaseEventTracker.Companion companion = BaseEventTracker.Companion.get();
        EventsUtils eventsUtils = EventsUtils.INSTANCE;
        companion.track(new DevicesBindExposedEvent(eventsUtils.getGetDate(), eventsUtils.getVersionName(), str == null ? "未知" : str, str2 == null ? "未知" : str2, openWay, i2, i3, i4, z2 ? SCENE_IS_KEYGUARD : SCENE_NOT_KEYGUARD, null, 512, null));
    }

    public final void trackDevicesNotBindExposedEvent(String str, String str2, String openWay, int i2, boolean z2) {
        n.g(openWay, "openWay");
        BaseEventTracker.Companion companion = BaseEventTracker.Companion.get();
        EventsUtils eventsUtils = EventsUtils.INSTANCE;
        companion.track(new DevicesNotBindExposedEvent(eventsUtils.getGetDate(), eventsUtils.getVersionName(), str == null ? "未知" : str, str2 == null ? "未知" : str2, openWay, i2, z2 ? SCENE_IS_KEYGUARD : SCENE_NOT_KEYGUARD, null, 128, null));
    }

    public final void trackEquipmentClickEvent(String str, String str2, String deviceStatus, String equipmentStyle, boolean z2) {
        n.g(deviceStatus, "deviceStatus");
        n.g(equipmentStyle, "equipmentStyle");
        BaseEventTracker.Companion companion = BaseEventTracker.Companion.get();
        EventsUtils eventsUtils = EventsUtils.INSTANCE;
        companion.track(new EquipmentClickEvent(eventsUtils.getGetDate(), eventsUtils.getVersionName(), str == null ? "未知" : str, str2 == null ? "未知" : str2, deviceStatus, equipmentStyle, z2 ? SCENE_IS_KEYGUARD : SCENE_NOT_KEYGUARD, null, 128, null));
    }

    public final void trackEquipmentLongClickEvent(String str, String str2, String equipmentStyle, boolean z2) {
        n.g(equipmentStyle, "equipmentStyle");
        BaseEventTracker.Companion companion = BaseEventTracker.Companion.get();
        EventsUtils eventsUtils = EventsUtils.INSTANCE;
        companion.track(new EquipmentLongClickEvent(eventsUtils.getGetDate(), eventsUtils.getVersionName(), str == null ? "未知" : str, str2 == null ? "未知" : str2, equipmentStyle, z2 ? SCENE_IS_KEYGUARD : SCENE_NOT_KEYGUARD, null, 64, null));
    }
}
