package miui.systemui.controlcenter.events;

import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.n;
import miui.systemui.dynamicisland.events.DynamicIslandEventsConstants;
import systemui.plugin.eventtracking.EventID;
import systemui.plugin.eventtracking.EventKey;

/* JADX INFO: loaded from: classes.dex */
@EventID(id = "click")
public final class SmartHomeClickEvent {

    @EventKey(key = DynamicIslandEventsConstants.EventKey.KEY_APP_NAME)
    private final String appName;

    @EventKey(key = "app_package")
    private final String appPkg;

    @EventKey(key = "model_type")
    private final String modelType;

    @EventKey(key = "screen_orientation")
    private final String orientation;

    @EventKey(key = DynamicIslandEventsConstants.PublicParams.PHONE_TYPE)
    private final String phoneType;

    @EventKey(key = DynamicIslandEventsConstants.PublicParams.SCREEN_TYPE)
    private final String screenType;

    @EventKey(key = "style")
    private final String style;

    @EventKey(key = "tip")
    private final String tip;

    @EventKey(key = "track_id")
    private final String trackId;

    @EventKey(key = "control_center_version")
    private final String version;

    public SmartHomeClickEvent(String trackId, String modelType, String phoneType, String screenType, String version, String orientation, String style, String appName, String appPkg, String tip) {
        n.g(trackId, "trackId");
        n.g(modelType, "modelType");
        n.g(phoneType, "phoneType");
        n.g(screenType, "screenType");
        n.g(version, "version");
        n.g(orientation, "orientation");
        n.g(style, "style");
        n.g(appName, "appName");
        n.g(appPkg, "appPkg");
        n.g(tip, "tip");
        this.trackId = trackId;
        this.modelType = modelType;
        this.phoneType = phoneType;
        this.screenType = screenType;
        this.version = version;
        this.orientation = orientation;
        this.style = style;
        this.appName = appName;
        this.appPkg = appPkg;
        this.tip = tip;
    }

    public final String component1() {
        return this.trackId;
    }

    public final String component10() {
        return this.tip;
    }

    public final String component2() {
        return this.modelType;
    }

    public final String component3() {
        return this.phoneType;
    }

    public final String component4() {
        return this.screenType;
    }

    public final String component5() {
        return this.version;
    }

    public final String component6() {
        return this.orientation;
    }

    public final String component7() {
        return this.style;
    }

    public final String component8() {
        return this.appName;
    }

    public final String component9() {
        return this.appPkg;
    }

    public final SmartHomeClickEvent copy(String trackId, String modelType, String phoneType, String screenType, String version, String orientation, String style, String appName, String appPkg, String tip) {
        n.g(trackId, "trackId");
        n.g(modelType, "modelType");
        n.g(phoneType, "phoneType");
        n.g(screenType, "screenType");
        n.g(version, "version");
        n.g(orientation, "orientation");
        n.g(style, "style");
        n.g(appName, "appName");
        n.g(appPkg, "appPkg");
        n.g(tip, "tip");
        return new SmartHomeClickEvent(trackId, modelType, phoneType, screenType, version, orientation, style, appName, appPkg, tip);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof SmartHomeClickEvent)) {
            return false;
        }
        SmartHomeClickEvent smartHomeClickEvent = (SmartHomeClickEvent) obj;
        return n.c(this.trackId, smartHomeClickEvent.trackId) && n.c(this.modelType, smartHomeClickEvent.modelType) && n.c(this.phoneType, smartHomeClickEvent.phoneType) && n.c(this.screenType, smartHomeClickEvent.screenType) && n.c(this.version, smartHomeClickEvent.version) && n.c(this.orientation, smartHomeClickEvent.orientation) && n.c(this.style, smartHomeClickEvent.style) && n.c(this.appName, smartHomeClickEvent.appName) && n.c(this.appPkg, smartHomeClickEvent.appPkg) && n.c(this.tip, smartHomeClickEvent.tip);
    }

    public final String getAppName() {
        return this.appName;
    }

    public final String getAppPkg() {
        return this.appPkg;
    }

    public final String getModelType() {
        return this.modelType;
    }

    public final String getOrientation() {
        return this.orientation;
    }

    public final String getPhoneType() {
        return this.phoneType;
    }

    public final String getScreenType() {
        return this.screenType;
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
        return (((((((((((((((((this.trackId.hashCode() * 31) + this.modelType.hashCode()) * 31) + this.phoneType.hashCode()) * 31) + this.screenType.hashCode()) * 31) + this.version.hashCode()) * 31) + this.orientation.hashCode()) * 31) + this.style.hashCode()) * 31) + this.appName.hashCode()) * 31) + this.appPkg.hashCode()) * 31) + this.tip.hashCode();
    }

    public String toString() {
        return "SmartHomeClickEvent(trackId=" + this.trackId + ", modelType=" + this.modelType + ", phoneType=" + this.phoneType + ", screenType=" + this.screenType + ", version=" + this.version + ", orientation=" + this.orientation + ", style=" + this.style + ", appName=" + this.appName + ", appPkg=" + this.appPkg + ", tip=" + this.tip + ")";
    }

    public /* synthetic */ SmartHomeClickEvent(String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8, String str9, String str10, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, str2, str3, str4, str5, str6, str7, str8, str9, (i2 & 512) != 0 ? "178.1.1.1.18769" : str10);
    }
}
