package miui.systemui.devicecontrols.eventtracking;

import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.n;
import miui.systemui.dynamicisland.events.DynamicIslandEventsConstants;
import miui.systemui.notification.focus.Const;
import systemui.plugin.eventtracking.EventID;
import systemui.plugin.eventtracking.EventKey;

/* JADX INFO: loaded from: classes3.dex */
@EventID(id = "skip_click")
public final class ControlsEditAppJumpClickEvent {

    @EventKey(key = DynamicIslandEventsConstants.EventKey.KEY_APP_NAME)
    private final String appName;

    @EventKey(key = "app_package")
    private final String appPkg;

    @EventKey(key = Const.Param.SCENE)
    private final String scene;

    @EventKey(key = "tip")
    private final String tip;

    @EventKey(key = "track_id")
    private final String trackId;

    @EventKey(key = "control_center_version")
    private final String version;

    public ControlsEditAppJumpClickEvent(String trackId, String version, String appName, String appPkg, String scene, String tip) {
        n.g(trackId, "trackId");
        n.g(version, "version");
        n.g(appName, "appName");
        n.g(appPkg, "appPkg");
        n.g(scene, "scene");
        n.g(tip, "tip");
        this.trackId = trackId;
        this.version = version;
        this.appName = appName;
        this.appPkg = appPkg;
        this.scene = scene;
        this.tip = tip;
    }

    public static /* synthetic */ ControlsEditAppJumpClickEvent copy$default(ControlsEditAppJumpClickEvent controlsEditAppJumpClickEvent, String str, String str2, String str3, String str4, String str5, String str6, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            str = controlsEditAppJumpClickEvent.trackId;
        }
        if ((i2 & 2) != 0) {
            str2 = controlsEditAppJumpClickEvent.version;
        }
        String str7 = str2;
        if ((i2 & 4) != 0) {
            str3 = controlsEditAppJumpClickEvent.appName;
        }
        String str8 = str3;
        if ((i2 & 8) != 0) {
            str4 = controlsEditAppJumpClickEvent.appPkg;
        }
        String str9 = str4;
        if ((i2 & 16) != 0) {
            str5 = controlsEditAppJumpClickEvent.scene;
        }
        String str10 = str5;
        if ((i2 & 32) != 0) {
            str6 = controlsEditAppJumpClickEvent.tip;
        }
        return controlsEditAppJumpClickEvent.copy(str, str7, str8, str9, str10, str6);
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
        return this.scene;
    }

    public final String component6() {
        return this.tip;
    }

    public final ControlsEditAppJumpClickEvent copy(String trackId, String version, String appName, String appPkg, String scene, String tip) {
        n.g(trackId, "trackId");
        n.g(version, "version");
        n.g(appName, "appName");
        n.g(appPkg, "appPkg");
        n.g(scene, "scene");
        n.g(tip, "tip");
        return new ControlsEditAppJumpClickEvent(trackId, version, appName, appPkg, scene, tip);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof ControlsEditAppJumpClickEvent)) {
            return false;
        }
        ControlsEditAppJumpClickEvent controlsEditAppJumpClickEvent = (ControlsEditAppJumpClickEvent) obj;
        return n.c(this.trackId, controlsEditAppJumpClickEvent.trackId) && n.c(this.version, controlsEditAppJumpClickEvent.version) && n.c(this.appName, controlsEditAppJumpClickEvent.appName) && n.c(this.appPkg, controlsEditAppJumpClickEvent.appPkg) && n.c(this.scene, controlsEditAppJumpClickEvent.scene) && n.c(this.tip, controlsEditAppJumpClickEvent.tip);
    }

    public final String getAppName() {
        return this.appName;
    }

    public final String getAppPkg() {
        return this.appPkg;
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
        return (((((((((this.trackId.hashCode() * 31) + this.version.hashCode()) * 31) + this.appName.hashCode()) * 31) + this.appPkg.hashCode()) * 31) + this.scene.hashCode()) * 31) + this.tip.hashCode();
    }

    public String toString() {
        return "ControlsEditAppJumpClickEvent(trackId=" + this.trackId + ", version=" + this.version + ", appName=" + this.appName + ", appPkg=" + this.appPkg + ", scene=" + this.scene + ", tip=" + this.tip + ")";
    }

    public /* synthetic */ ControlsEditAppJumpClickEvent(String str, String str2, String str3, String str4, String str5, String str6, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, str2, str3, str4, str5, (i2 & 32) != 0 ? "178.1.10.1.26183" : str6);
    }
}
