package miui.systemui.devicecontrols.eventtracking;

import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.n;
import miui.systemui.dynamicisland.events.DynamicIslandEventsConstants;
import miui.systemui.notification.focus.Const;
import systemui.plugin.eventtracking.EventID;
import systemui.plugin.eventtracking.EventKey;

/* JADX INFO: loaded from: classes3.dex */
@EventID(id = "equipment_click")
public final class EquipmentClickEvent {

    @EventKey(key = DynamicIslandEventsConstants.EventKey.KEY_APP_NAME)
    private final String appName;

    @EventKey(key = "app_package")
    private final String appPkg;

    @EventKey(key = "after_set_status")
    private final String deviceStatus;

    @EventKey(key = "equipment_style")
    private final String equipmentStyle;

    @EventKey(key = Const.Param.SCENE)
    private final String scene;

    @EventKey(key = "tip")
    private final String tip;

    @EventKey(key = "track_id")
    private final String trackId;

    @EventKey(key = "control_center_version")
    private final String version;

    public EquipmentClickEvent(String trackId, String version, String appName, String appPkg, String deviceStatus, String equipmentStyle, String scene, String tip) {
        n.g(trackId, "trackId");
        n.g(version, "version");
        n.g(appName, "appName");
        n.g(appPkg, "appPkg");
        n.g(deviceStatus, "deviceStatus");
        n.g(equipmentStyle, "equipmentStyle");
        n.g(scene, "scene");
        n.g(tip, "tip");
        this.trackId = trackId;
        this.version = version;
        this.appName = appName;
        this.appPkg = appPkg;
        this.deviceStatus = deviceStatus;
        this.equipmentStyle = equipmentStyle;
        this.scene = scene;
        this.tip = tip;
    }

    public final String component1() {
        return this.trackId;
    }

    public final String component2() {
        return this.version;
    }

    public final String component3() {
        return this.appName;
    }

    public final String component4() {
        return this.appPkg;
    }

    public final String component5() {
        return this.deviceStatus;
    }

    public final String component6() {
        return this.equipmentStyle;
    }

    public final String component7() {
        return this.scene;
    }

    public final String component8() {
        return this.tip;
    }

    public final EquipmentClickEvent copy(String trackId, String version, String appName, String appPkg, String deviceStatus, String equipmentStyle, String scene, String tip) {
        n.g(trackId, "trackId");
        n.g(version, "version");
        n.g(appName, "appName");
        n.g(appPkg, "appPkg");
        n.g(deviceStatus, "deviceStatus");
        n.g(equipmentStyle, "equipmentStyle");
        n.g(scene, "scene");
        n.g(tip, "tip");
        return new EquipmentClickEvent(trackId, version, appName, appPkg, deviceStatus, equipmentStyle, scene, tip);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof EquipmentClickEvent)) {
            return false;
        }
        EquipmentClickEvent equipmentClickEvent = (EquipmentClickEvent) obj;
        return n.c(this.trackId, equipmentClickEvent.trackId) && n.c(this.version, equipmentClickEvent.version) && n.c(this.appName, equipmentClickEvent.appName) && n.c(this.appPkg, equipmentClickEvent.appPkg) && n.c(this.deviceStatus, equipmentClickEvent.deviceStatus) && n.c(this.equipmentStyle, equipmentClickEvent.equipmentStyle) && n.c(this.scene, equipmentClickEvent.scene) && n.c(this.tip, equipmentClickEvent.tip);
    }

    public final String getAppName() {
        return this.appName;
    }

    public final String getAppPkg() {
        return this.appPkg;
    }

    public final String getDeviceStatus() {
        return this.deviceStatus;
    }

    public final String getEquipmentStyle() {
        return this.equipmentStyle;
    }

    public final String getScene() {
        return this.scene;
    }

    public final String getTip() {
        return this.tip;
    }

    public final String getTrackId() {
        return this.trackId;
    }

    public final String getVersion() {
        return this.version;
    }

    public int hashCode() {
        return (((((((((((((this.trackId.hashCode() * 31) + this.version.hashCode()) * 31) + this.appName.hashCode()) * 31) + this.appPkg.hashCode()) * 31) + this.deviceStatus.hashCode()) * 31) + this.equipmentStyle.hashCode()) * 31) + this.scene.hashCode()) * 31) + this.tip.hashCode();
    }

    public String toString() {
        return "EquipmentClickEvent(trackId=" + this.trackId + ", version=" + this.version + ", appName=" + this.appName + ", appPkg=" + this.appPkg + ", deviceStatus=" + this.deviceStatus + ", equipmentStyle=" + this.equipmentStyle + ", scene=" + this.scene + ", tip=" + this.tip + ")";
    }

    public /* synthetic */ EquipmentClickEvent(String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, str2, str3, str4, str5, str6, str7, (i2 & 128) != 0 ? "178.1.9.1.26179" : str8);
    }
}
