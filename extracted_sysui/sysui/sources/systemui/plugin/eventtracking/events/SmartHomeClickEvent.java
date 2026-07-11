package systemui.plugin.eventtracking.events;

import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.n;
import miui.systemui.dynamicisland.events.DynamicIslandEventsConstants;
import systemui.plugin.eventtracking.EventID;
import systemui.plugin.eventtracking.EventKey;
import systemui.plugin.eventtracking.trackers.ControlsEventTracker;

/* JADX INFO: loaded from: classes5.dex */
@EventID(id = "click")
public final class SmartHomeClickEvent {

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

    public SmartHomeClickEvent(String trackId, String version, String orientation, String style, String appName, String appPackage, String tip) {
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

    public static /* synthetic */ SmartHomeClickEvent copy$default(SmartHomeClickEvent smartHomeClickEvent, String str, String str2, String str3, String str4, String str5, String str6, String str7, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            str = smartHomeClickEvent.trackId;
        }
        if ((i2 & 2) != 0) {
            str2 = smartHomeClickEvent.version;
        }
        String str8 = str2;
        if ((i2 & 4) != 0) {
            str3 = smartHomeClickEvent.orientation;
        }
        String str9 = str3;
        if ((i2 & 8) != 0) {
            str4 = smartHomeClickEvent.style;
        }
        String str10 = str4;
        if ((i2 & 16) != 0) {
            str5 = smartHomeClickEvent.appName;
        }
        String str11 = str5;
        if ((i2 & 32) != 0) {
            str6 = smartHomeClickEvent.appPackage;
        }
        String str12 = str6;
        if ((i2 & 64) != 0) {
            str7 = smartHomeClickEvent.tip;
        }
        return smartHomeClickEvent.copy(str, str8, str9, str10, str11, str12, str7);
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

    public final SmartHomeClickEvent copy(String trackId, String version, String orientation, String style, String appName, String appPackage, String tip) {
        n.g(trackId, "trackId");
        n.g(version, "version");
        n.g(orientation, "orientation");
        n.g(style, "style");
        n.g(appName, "appName");
        n.g(appPackage, "appPackage");
        n.g(tip, "tip");
        return new SmartHomeClickEvent(trackId, version, orientation, style, appName, appPackage, tip);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof SmartHomeClickEvent)) {
            return false;
        }
        SmartHomeClickEvent smartHomeClickEvent = (SmartHomeClickEvent) obj;
        return n.c(this.trackId, smartHomeClickEvent.trackId) && n.c(this.version, smartHomeClickEvent.version) && n.c(this.orientation, smartHomeClickEvent.orientation) && n.c(this.style, smartHomeClickEvent.style) && n.c(this.appName, smartHomeClickEvent.appName) && n.c(this.appPackage, smartHomeClickEvent.appPackage) && n.c(this.tip, smartHomeClickEvent.tip);
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
        return "SmartHomeClickEvent(trackId=" + this.trackId + ", version=" + this.version + ", orientation=" + this.orientation + ", style=" + this.style + ", appName=" + this.appName + ", appPackage=" + this.appPackage + ", tip=" + this.tip + ")";
    }

    public /* synthetic */ SmartHomeClickEvent(String str, String str2, String str3, String str4, String str5, String str6, String str7, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, str2, str3, str4, str5, str6, (i2 & 64) != 0 ? ControlsEventTracker.EVENT_SMART_HOME_CLICK_TIP : str7);
    }
}
