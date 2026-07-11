package miui.systemui.devicecontrols.eventtracking;

import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.n;
import miui.systemui.dynamicisland.events.DynamicIslandEventsConstants;
import miui.systemui.notification.focus.Const;
import systemui.plugin.eventtracking.EventID;
import systemui.plugin.eventtracking.EventKey;

/* JADX INFO: loaded from: classes3.dex */
@EventID(id = "page_expose")
public final class DevicesNotBindExposedEvent {

    @EventKey(key = DynamicIslandEventsConstants.EventKey.KEY_APP_NAME)
    private final String appName;

    @EventKey(key = "smarthome_app_number")
    private final int appNum;

    @EventKey(key = "app_package")
    private final String appPkg;

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

    public DevicesNotBindExposedEvent(String trackId, String version, String appName, String appPkg, String openWay, int i2, String scene, String tip) {
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
        this.appNum = i2;
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
        return this.openWay;
    }

    public final int component6() {
        return this.appNum;
    }

    public final String component7() {
        return this.scene;
    }

    public final String component8() {
        return this.tip;
    }

    public final DevicesNotBindExposedEvent copy(String trackId, String version, String appName, String appPkg, String openWay, int i2, String scene, String tip) {
        n.g(trackId, "trackId");
        n.g(version, "version");
        n.g(appName, "appName");
        n.g(appPkg, "appPkg");
        n.g(openWay, "openWay");
        n.g(scene, "scene");
        n.g(tip, "tip");
        return new DevicesNotBindExposedEvent(trackId, version, appName, appPkg, openWay, i2, scene, tip);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof DevicesNotBindExposedEvent)) {
            return false;
        }
        DevicesNotBindExposedEvent devicesNotBindExposedEvent = (DevicesNotBindExposedEvent) obj;
        return n.c(this.trackId, devicesNotBindExposedEvent.trackId) && n.c(this.version, devicesNotBindExposedEvent.version) && n.c(this.appName, devicesNotBindExposedEvent.appName) && n.c(this.appPkg, devicesNotBindExposedEvent.appPkg) && n.c(this.openWay, devicesNotBindExposedEvent.openWay) && this.appNum == devicesNotBindExposedEvent.appNum && n.c(this.scene, devicesNotBindExposedEvent.scene) && n.c(this.tip, devicesNotBindExposedEvent.tip);
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
        return (((((((((((((this.trackId.hashCode() * 31) + this.version.hashCode()) * 31) + this.appName.hashCode()) * 31) + this.appPkg.hashCode()) * 31) + this.openWay.hashCode()) * 31) + Integer.hashCode(this.appNum)) * 31) + this.scene.hashCode()) * 31) + this.tip.hashCode();
    }

    public String toString() {
        return "DevicesNotBindExposedEvent(trackId=" + this.trackId + ", version=" + this.version + ", appName=" + this.appName + ", appPkg=" + this.appPkg + ", openWay=" + this.openWay + ", appNum=" + this.appNum + ", scene=" + this.scene + ", tip=" + this.tip + ")";
    }

    public /* synthetic */ DevicesNotBindExposedEvent(String str, String str2, String str3, String str4, String str5, int i2, String str6, String str7, int i3, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, str2, str3, str4, str5, i2, str6, (i3 & 128) != 0 ? "178.1.8.1.26177" : str7);
    }
}
