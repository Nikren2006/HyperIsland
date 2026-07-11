package miui.systemui.devicecontrols.eventtracking;

import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.n;
import miui.systemui.dynamicisland.events.DynamicIslandEventsConstants;
import miui.systemui.notification.focus.Const;
import systemui.plugin.eventtracking.EventID;
import systemui.plugin.eventtracking.EventKey;

/* JADX INFO: loaded from: classes3.dex */
@EventID(id = "page_expose")
public final class DevicesBindExposedEvent {

    @EventKey(key = DynamicIslandEventsConstants.EventKey.KEY_APP_NAME)
    private final String appName;

    @EventKey(key = "smarthome_app_number")
    private final int appNum;

    @EventKey(key = "app_package")
    private final String appPkg;

    @EventKey(key = "added_scenario_button_num")
    private final int buttonNum;

    @EventKey(key = "added_equipment_num")
    private final int deviceNum;

    @EventKey(key = "open_way")
    private final String openWay;

    @EventKey(key = Const.Param.SCENE)
    private final String scene;

    @EventKey(key = "tip")
    private final String tip;

    @EventKey(key = "track_id")
    private final String trackId;

    @EventKey(key = "control_center_version")
    private final String version;

    public DevicesBindExposedEvent(String trackId, String version, String appName, String appPkg, String openWay, int i2, int i3, int i4, String scene, String tip) {
        n.g(trackId, "trackId");
        n.g(version, "version");
        n.g(appName, "appName");
        n.g(appPkg, "appPkg");
        n.g(openWay, "openWay");
        n.g(scene, "scene");
        n.g(tip, "tip");
        this.trackId = trackId;
        this.version = version;
        this.appName = appName;
        this.appPkg = appPkg;
        this.openWay = openWay;
        this.deviceNum = i2;
        this.buttonNum = i3;
        this.appNum = i4;
        this.scene = scene;
        this.tip = tip;
    }

    public final String component1() {
        return this.trackId;
    }

    public final String component10() {
        return this.tip;
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
        return this.openWay;
    }

    public final int component6() {
        return this.deviceNum;
    }

    public final int component7() {
        return this.buttonNum;
    }

    public final int component8() {
        return this.appNum;
    }

    public final String component9() {
        return this.scene;
    }

    public final DevicesBindExposedEvent copy(String trackId, String version, String appName, String appPkg, String openWay, int i2, int i3, int i4, String scene, String tip) {
        n.g(trackId, "trackId");
        n.g(version, "version");
        n.g(appName, "appName");
        n.g(appPkg, "appPkg");
        n.g(openWay, "openWay");
        n.g(scene, "scene");
        n.g(tip, "tip");
        return new DevicesBindExposedEvent(trackId, version, appName, appPkg, openWay, i2, i3, i4, scene, tip);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof DevicesBindExposedEvent)) {
            return false;
        }
        DevicesBindExposedEvent devicesBindExposedEvent = (DevicesBindExposedEvent) obj;
        return n.c(this.trackId, devicesBindExposedEvent.trackId) && n.c(this.version, devicesBindExposedEvent.version) && n.c(this.appName, devicesBindExposedEvent.appName) && n.c(this.appPkg, devicesBindExposedEvent.appPkg) && n.c(this.openWay, devicesBindExposedEvent.openWay) && this.deviceNum == devicesBindExposedEvent.deviceNum && this.buttonNum == devicesBindExposedEvent.buttonNum && this.appNum == devicesBindExposedEvent.appNum && n.c(this.scene, devicesBindExposedEvent.scene) && n.c(this.tip, devicesBindExposedEvent.tip);
    }

    public final String getAppName() {
        return this.appName;
    }

    public final int getAppNum() {
        return this.appNum;
    }

    public final String getAppPkg() {
        return this.appPkg;
    }

    public final int getButtonNum() {
        return this.buttonNum;
    }

    public final int getDeviceNum() {
        return this.deviceNum;
    }

    public final String getOpenWay() {
        return this.openWay;
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
        return (((((((((((((((((this.trackId.hashCode() * 31) + this.version.hashCode()) * 31) + this.appName.hashCode()) * 31) + this.appPkg.hashCode()) * 31) + this.openWay.hashCode()) * 31) + Integer.hashCode(this.deviceNum)) * 31) + Integer.hashCode(this.buttonNum)) * 31) + Integer.hashCode(this.appNum)) * 31) + this.scene.hashCode()) * 31) + this.tip.hashCode();
    }

    public String toString() {
        return "DevicesBindExposedEvent(trackId=" + this.trackId + ", version=" + this.version + ", appName=" + this.appName + ", appPkg=" + this.appPkg + ", openWay=" + this.openWay + ", deviceNum=" + this.deviceNum + ", buttonNum=" + this.buttonNum + ", appNum=" + this.appNum + ", scene=" + this.scene + ", tip=" + this.tip + ")";
    }

    public /* synthetic */ DevicesBindExposedEvent(String str, String str2, String str3, String str4, String str5, int i2, int i3, int i4, String str6, String str7, int i5, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, str2, str3, str4, str5, i2, i3, i4, str6, (i5 & 512) != 0 ? "178.1.9.1.26178" : str7);
    }
}
