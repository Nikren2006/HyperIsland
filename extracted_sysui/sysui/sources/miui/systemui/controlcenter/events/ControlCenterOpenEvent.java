package miui.systemui.controlcenter.events;

import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.n;
import miui.systemui.dynamicisland.events.DynamicIslandEventsConstants;
import systemui.plugin.eventtracking.EventID;
import systemui.plugin.eventtracking.EventKey;

/* JADX INFO: loaded from: classes.dex */
@EventID(id = "open")
public final class ControlCenterOpenEvent {

    @EventKey(key = "model_type")
    private final String modelType;

    @EventKey(key = "open_way")
    private final String openWay;

    @EventKey(key = "screen_orientation")
    private final String orientation;

    @EventKey(key = DynamicIslandEventsConstants.PublicParams.PHONE_TYPE)
    private final String phoneType;

    @EventKey(key = DynamicIslandEventsConstants.PublicParams.SCREEN_TYPE)
    private final String screenType;

    @EventKey(key = "tip")
    private final String tip;

    @EventKey(key = "track_id")
    private final String trackId;

    @EventKey(key = "control_center_version")
    private final String version;

    public ControlCenterOpenEvent(String trackId, String modelType, String phoneType, String screenType, String version, String orientation, String openWay, String tip) {
        n.g(trackId, "trackId");
        n.g(modelType, "modelType");
        n.g(phoneType, "phoneType");
        n.g(screenType, "screenType");
        n.g(version, "version");
        n.g(orientation, "orientation");
        n.g(openWay, "openWay");
        n.g(tip, "tip");
        this.trackId = trackId;
        this.modelType = modelType;
        this.phoneType = phoneType;
        this.screenType = screenType;
        this.version = version;
        this.orientation = orientation;
        this.openWay = openWay;
        this.tip = tip;
    }

    public final String component1() {
        return this.trackId;
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
        return this.openWay;
    }

    public final String component8() {
        return this.tip;
    }

    public final ControlCenterOpenEvent copy(String trackId, String modelType, String phoneType, String screenType, String version, String orientation, String openWay, String tip) {
        n.g(trackId, "trackId");
        n.g(modelType, "modelType");
        n.g(phoneType, "phoneType");
        n.g(screenType, "screenType");
        n.g(version, "version");
        n.g(orientation, "orientation");
        n.g(openWay, "openWay");
        n.g(tip, "tip");
        return new ControlCenterOpenEvent(trackId, modelType, phoneType, screenType, version, orientation, openWay, tip);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof ControlCenterOpenEvent)) {
            return false;
        }
        ControlCenterOpenEvent controlCenterOpenEvent = (ControlCenterOpenEvent) obj;
        return n.c(this.trackId, controlCenterOpenEvent.trackId) && n.c(this.modelType, controlCenterOpenEvent.modelType) && n.c(this.phoneType, controlCenterOpenEvent.phoneType) && n.c(this.screenType, controlCenterOpenEvent.screenType) && n.c(this.version, controlCenterOpenEvent.version) && n.c(this.orientation, controlCenterOpenEvent.orientation) && n.c(this.openWay, controlCenterOpenEvent.openWay) && n.c(this.tip, controlCenterOpenEvent.tip);
    }

    public final String getModelType() {
        return this.modelType;
    }

    public final String getOpenWay() {
        return this.openWay;
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
        return (((((((((((((this.trackId.hashCode() * 31) + this.modelType.hashCode()) * 31) + this.phoneType.hashCode()) * 31) + this.screenType.hashCode()) * 31) + this.version.hashCode()) * 31) + this.orientation.hashCode()) * 31) + this.openWay.hashCode()) * 31) + this.tip.hashCode();
    }

    public String toString() {
        return "ControlCenterOpenEvent(trackId=" + this.trackId + ", modelType=" + this.modelType + ", phoneType=" + this.phoneType + ", screenType=" + this.screenType + ", version=" + this.version + ", orientation=" + this.orientation + ", openWay=" + this.openWay + ", tip=" + this.tip + ")";
    }

    public /* synthetic */ ControlCenterOpenEvent(String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, str2, str3, str4, str5, str6, str7, (i2 & 128) != 0 ? "178.1.0.1.18776" : str8);
    }
}
