package systemui.plugin.eventtracking.events;

import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.n;
import miui.systemui.dynamicisland.events.DynamicIslandEventsConstants;
import systemui.plugin.eventtracking.EventID;
import systemui.plugin.eventtracking.EventKey;
import systemui.plugin.eventtracking.trackers.ControlsEventTracker;

/* JADX INFO: loaded from: classes5.dex */
@EventID(id = DynamicIslandEventsConstants.EventID.EVENT_EXPOSE)
public final class SmartHomeExposeEvent {

    @EventKey(key = DynamicIslandEventsConstants.EventKey.KEY_APP_NAME)
    private final String appName;

    @EventKey(key = "app_package")
    private final String appPackage;

    @EventKey(key = "screen_orientation")
    private final String orientation;

    @EventKey(key = "style")
    private final String style;

    @EventKey(key = "tip")
    private final String tip;

    @EventKey(key = "track_id")
    private final String trackId;

    @EventKey(key = "control_center_version")
    private final String version;

    public SmartHomeExposeEvent(String trackId, String version, String orientation, String style, String appName, String appPackage, String tip) {
        n.g(trackId, "trackId");
        n.g(version, "version");
        n.g(orientation, "orientation");
        n.g(style, "style");
        n.g(appName, "appName");
        n.g(appPackage, "appPackage");
        n.g(tip, "tip");
        this.trackId = trackId;
        this.version = version;
        this.orientation = orientation;
        this.style = style;
        this.appName = appName;
        this.appPackage = appPackage;
        this.tip = tip;
    }

    public static /* synthetic */ SmartHomeExposeEvent copy$default(SmartHomeExposeEvent smartHomeExposeEvent, String str, String str2, String str3, String str4, String str5, String str6, String str7, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            str = smartHomeExposeEvent.trackId;
        }
        if ((i2 & 2) != 0) {
            str2 = smartHomeExposeEvent.version;
        }
        String str8 = str2;
        if ((i2 & 4) != 0) {
            str3 = smartHomeExposeEvent.orientation;
        }
        String str9 = str3;
        if ((i2 & 8) != 0) {
            str4 = smartHomeExposeEvent.style;
        }
        String str10 = str4;
        if ((i2 & 16) != 0) {
            str5 = smartHomeExposeEvent.appName;
        }
        String str11 = str5;
        if ((i2 & 32) != 0) {
            str6 = smartHomeExposeEvent.appPackage;
        }
        String str12 = str6;
        if ((i2 & 64) != 0) {
            str7 = smartHomeExposeEvent.tip;
        }
        return smartHomeExposeEvent.copy(str, str8, str9, str10, str11, str12, str7);
    }

    public final String component1() {
        return this.trackId;
    }

    public final String component2() {
        return this.version;
    }

    public final String component3() {
        return this.orientation;
    }

    public final String component4() {
        return this.style;
    }

    public final String component5() {
        return this.appName;
    }

    public final String component6() {
        return this.appPackage;
    }

    public final String component7() {
        return this.tip;
    }

    public final SmartHomeExposeEvent copy(String trackId, String version, String orientation, String style, String appName, String appPackage, String tip) {
        n.g(trackId, "trackId");
        n.g(version, "version");
        n.g(orientation, "orientation");
        n.g(style, "style");
        n.g(appName, "appName");
        n.g(appPackage, "appPackage");
        n.g(tip, "tip");
        return new SmartHomeExposeEvent(trackId, version, orientation, style, appName, appPackage, tip);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof SmartHomeExposeEvent)) {
            return false;
        }
        SmartHomeExposeEvent smartHomeExposeEvent = (SmartHomeExposeEvent) obj;
        return n.c(this.trackId, smartHomeExposeEvent.trackId) && n.c(this.version, smartHomeExposeEvent.version) && n.c(this.orientation, smartHomeExposeEvent.orientation) && n.c(this.style, smartHomeExposeEvent.style) && n.c(this.appName, smartHomeExposeEvent.appName) && n.c(this.appPackage, smartHomeExposeEvent.appPackage) && n.c(this.tip, smartHomeExposeEvent.tip);
    }

    public final String getAppName() {
        return this.appName;
    }

    public final String getAppPackage() {
        return this.appPackage;
    }

    public final String getOrientation() {
        return this.orientation;
    }

    public final String getStyle() {
        return this.style;
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
        return (((((((((((this.trackId.hashCode() * 31) + this.version.hashCode()) * 31) + this.orientation.hashCode()) * 31) + this.style.hashCode()) * 31) + this.appName.hashCode()) * 31) + this.appPackage.hashCode()) * 31) + this.tip.hashCode();
    }

    public String toString() {
        return "SmartHomeExposeEvent(trackId=" + this.trackId + ", version=" + this.version + ", orientation=" + this.orientation + ", style=" + this.style + ", appName=" + this.appName + ", appPackage=" + this.appPackage + ", tip=" + this.tip + ")";
    }

    public /* synthetic */ SmartHomeExposeEvent(String str, String str2, String str3, String str4, String str5, String str6, String str7, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, str2, str3, str4, str5, str6, (i2 & 64) != 0 ? ControlsEventTracker.EVENT_SMART_HOME_EXPOSE_TIP : str7);
    }
}
