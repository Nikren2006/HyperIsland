package miui.systemui.devicecontrols.eventtracking;

import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.n;
import miui.systemui.dynamicisland.events.DynamicIslandEventsConstants;
import miui.systemui.notification.focus.Const;
import systemui.plugin.eventtracking.EventID;
import systemui.plugin.eventtracking.EventKey;

/* JADX INFO: loaded from: classes3.dex */
@EventID(id = "page_expose")
public final class ControlsEditExposedEvent {

    @EventKey(key = DynamicIslandEventsConstants.EventKey.KEY_APP_NAME)
    private final String appName;

    @EventKey(key = "app_package")
    private final String appPkg;

    @EventKey(key = Const.Param.SCENE)
    private final String scene;

    @EventKey(key = "if_skip")
    private final String skip;

    @EventKey(key = "tip")
    private final String tip;

    @EventKey(key = "track_id")
    private final String trackId;

    @EventKey(key = "control_center_version")
    private final String version;

    public ControlsEditExposedEvent(String trackId, String version, String appName, String appPkg, String skip, String scene, String tip) {
        n.g(trackId, "trackId");
        n.g(version, "version");
        n.g(appName, "appName");
        n.g(appPkg, "appPkg");
        n.g(skip, "skip");
        n.g(scene, "scene");
        n.g(tip, "tip");
        this.trackId = trackId;
        this.version = version;
        this.appName = appName;
        this.appPkg = appPkg;
        this.skip = skip;
        this.scene = scene;
        this.tip = tip;
    }

    public static /* synthetic */ ControlsEditExposedEvent copy$default(ControlsEditExposedEvent controlsEditExposedEvent, String str, String str2, String str3, String str4, String str5, String str6, String str7, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            str = controlsEditExposedEvent.trackId;
        }
        if ((i2 & 2) != 0) {
            str2 = controlsEditExposedEvent.version;
        }
        String str8 = str2;
        if ((i2 & 4) != 0) {
            str3 = controlsEditExposedEvent.appName;
        }
        String str9 = str3;
        if ((i2 & 8) != 0) {
            str4 = controlsEditExposedEvent.appPkg;
        }
        String str10 = str4;
        if ((i2 & 16) != 0) {
            str5 = controlsEditExposedEvent.skip;
        }
        String str11 = str5;
        if ((i2 & 32) != 0) {
            str6 = controlsEditExposedEvent.scene;
        }
        String str12 = str6;
        if ((i2 & 64) != 0) {
            str7 = controlsEditExposedEvent.tip;
        }
        return controlsEditExposedEvent.copy(str, str8, str9, str10, str11, str12, str7);
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
        return this.skip;
    }

    public final String component6() {
        return this.scene;
    }

    public final String component7() {
        return this.tip;
    }

    public final ControlsEditExposedEvent copy(String trackId, String version, String appName, String appPkg, String skip, String scene, String tip) {
        n.g(trackId, "trackId");
        n.g(version, "version");
        n.g(appName, "appName");
        n.g(appPkg, "appPkg");
        n.g(skip, "skip");
        n.g(scene, "scene");
        n.g(tip, "tip");
        return new ControlsEditExposedEvent(trackId, version, appName, appPkg, skip, scene, tip);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof ControlsEditExposedEvent)) {
            return false;
        }
        ControlsEditExposedEvent controlsEditExposedEvent = (ControlsEditExposedEvent) obj;
        return n.c(this.trackId, controlsEditExposedEvent.trackId) && n.c(this.version, controlsEditExposedEvent.version) && n.c(this.appName, controlsEditExposedEvent.appName) && n.c(this.appPkg, controlsEditExposedEvent.appPkg) && n.c(this.skip, controlsEditExposedEvent.skip) && n.c(this.scene, controlsEditExposedEvent.scene) && n.c(this.tip, controlsEditExposedEvent.tip);
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

    public final String getSkip() {
        return this.skip;
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
        return (((((((((((this.trackId.hashCode() * 31) + this.version.hashCode()) * 31) + this.appName.hashCode()) * 31) + this.appPkg.hashCode()) * 31) + this.skip.hashCode()) * 31) + this.scene.hashCode()) * 31) + this.tip.hashCode();
    }

    public String toString() {
        return "ControlsEditExposedEvent(trackId=" + this.trackId + ", version=" + this.version + ", appName=" + this.appName + ", appPkg=" + this.appPkg + ", skip=" + this.skip + ", scene=" + this.scene + ", tip=" + this.tip + ")";
    }

    public /* synthetic */ ControlsEditExposedEvent(String str, String str2, String str3, String str4, String str5, String str6, String str7, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, str2, str3, str4, str5, str6, (i2 & 64) != 0 ? "178.1.10.1.26182" : str7);
    }
}
