package miui.systemui.devicecontrols.eventtracking;

import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.n;
import miui.systemui.notification.focus.Const;
import systemui.plugin.eventtracking.EventID;
import systemui.plugin.eventtracking.EventKey;

/* JADX INFO: loaded from: classes3.dex */
@EventID(id = "app_change")
public final class AppChangeEvent {

    @EventKey(key = Const.Param.SCENE)
    private final String scene;

    @EventKey(key = "tip")
    private final String tip;

    @EventKey(key = "track_id")
    private final String trackId;

    @EventKey(key = "control_center_version")
    private final String version;

    public AppChangeEvent(String trackId, String version, String scene, String tip) {
        n.g(trackId, "trackId");
        n.g(version, "version");
        n.g(scene, "scene");
        n.g(tip, "tip");
        this.trackId = trackId;
        this.version = version;
        this.scene = scene;
        this.tip = tip;
    }

    public static /* synthetic */ AppChangeEvent copy$default(AppChangeEvent appChangeEvent, String str, String str2, String str3, String str4, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            str = appChangeEvent.trackId;
        }
        if ((i2 & 2) != 0) {
            str2 = appChangeEvent.version;
        }
        if ((i2 & 4) != 0) {
            str3 = appChangeEvent.scene;
        }
        if ((i2 & 8) != 0) {
            str4 = appChangeEvent.tip;
        }
        return appChangeEvent.copy(str, str2, str3, str4);
    }

    public final String component1() {
        return this.trackId;
    }

    public final String component2() {
        return this.version;
    }

    public final String component3() {
        return this.scene;
    }

    public final String component4() {
        return this.tip;
    }

    public final AppChangeEvent copy(String trackId, String version, String scene, String tip) {
        n.g(trackId, "trackId");
        n.g(version, "version");
        n.g(scene, "scene");
        n.g(tip, "tip");
        return new AppChangeEvent(trackId, version, scene, tip);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof AppChangeEvent)) {
            return false;
        }
        AppChangeEvent appChangeEvent = (AppChangeEvent) obj;
        return n.c(this.trackId, appChangeEvent.trackId) && n.c(this.version, appChangeEvent.version) && n.c(this.scene, appChangeEvent.scene) && n.c(this.tip, appChangeEvent.tip);
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
        return (((((this.trackId.hashCode() * 31) + this.version.hashCode()) * 31) + this.scene.hashCode()) * 31) + this.tip.hashCode();
    }

    public String toString() {
        return "AppChangeEvent(trackId=" + this.trackId + ", version=" + this.version + ", scene=" + this.scene + ", tip=" + this.tip + ")";
    }

    public /* synthetic */ AppChangeEvent(String str, String str2, String str3, String str4, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, str2, str3, (i2 & 8) != 0 ? "178.1.0.1.26181" : str4);
    }
}
