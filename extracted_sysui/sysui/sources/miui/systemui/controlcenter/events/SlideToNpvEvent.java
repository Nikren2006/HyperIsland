package miui.systemui.controlcenter.events;

import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.n;
import miui.systemui.dynamicisland.events.DynamicIslandEventsConstants;
import systemui.plugin.eventtracking.EventID;
import systemui.plugin.eventtracking.EventKey;

/* JADX INFO: loaded from: classes.dex */
@EventID(id = "switch_notification_bar")
public final class SlideToNpvEvent {

    @EventKey(key = "model_type")
    private final String modelType;

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

    public SlideToNpvEvent(String trackId, String modelType, String phoneType, String screenType, String version, String orientation, String tip) {
        n.g(trackId, "trackId");
        n.g(modelType, "modelType");
        n.g(phoneType, "phoneType");
        n.g(screenType, "screenType");
        n.g(version, "version");
        n.g(orientation, "orientation");
        n.g(tip, "tip");
        this.trackId = trackId;
        this.modelType = modelType;
        this.phoneType = phoneType;
        this.screenType = screenType;
        this.version = version;
        this.orientation = orientation;
        this.tip = tip;
    }

    public static /* synthetic */ SlideToNpvEvent copy$default(SlideToNpvEvent slideToNpvEvent, String str, String str2, String str3, String str4, String str5, String str6, String str7, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            str = slideToNpvEvent.trackId;
        }
        if ((i2 & 2) != 0) {
            str2 = slideToNpvEvent.modelType;
        }
        String str8 = str2;
        if ((i2 & 4) != 0) {
            str3 = slideToNpvEvent.phoneType;
        }
        String str9 = str3;
        if ((i2 & 8) != 0) {
            str4 = slideToNpvEvent.screenType;
        }
        String str10 = str4;
        if ((i2 & 16) != 0) {
            str5 = slideToNpvEvent.version;
        }
        String str11 = str5;
        if ((i2 & 32) != 0) {
            str6 = slideToNpvEvent.orientation;
        }
        String str12 = str6;
        if ((i2 & 64) != 0) {
            str7 = slideToNpvEvent.tip;
        }
        return slideToNpvEvent.copy(str, str8, str9, str10, str11, str12, str7);
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
        return this.tip;
    }

    public final SlideToNpvEvent copy(String trackId, String modelType, String phoneType, String screenType, String version, String orientation, String tip) {
        n.g(trackId, "trackId");
        n.g(modelType, "modelType");
        n.g(phoneType, "phoneType");
        n.g(screenType, "screenType");
        n.g(version, "version");
        n.g(orientation, "orientation");
        n.g(tip, "tip");
        return new SlideToNpvEvent(trackId, modelType, phoneType, screenType, version, orientation, tip);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof SlideToNpvEvent)) {
            return false;
        }
        SlideToNpvEvent slideToNpvEvent = (SlideToNpvEvent) obj;
        return n.c(this.trackId, slideToNpvEvent.trackId) && n.c(this.modelType, slideToNpvEvent.modelType) && n.c(this.phoneType, slideToNpvEvent.phoneType) && n.c(this.screenType, slideToNpvEvent.screenType) && n.c(this.version, slideToNpvEvent.version) && n.c(this.orientation, slideToNpvEvent.orientation) && n.c(this.tip, slideToNpvEvent.tip);
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
        return (((((((((((this.trackId.hashCode() * 31) + this.modelType.hashCode()) * 31) + this.phoneType.hashCode()) * 31) + this.screenType.hashCode()) * 31) + this.version.hashCode()) * 31) + this.orientation.hashCode()) * 31) + this.tip.hashCode();
    }

    public String toString() {
        return "SlideToNpvEvent(trackId=" + this.trackId + ", modelType=" + this.modelType + ", phoneType=" + this.phoneType + ", screenType=" + this.screenType + ", version=" + this.version + ", orientation=" + this.orientation + ", tip=" + this.tip + ")";
    }

    public /* synthetic */ SlideToNpvEvent(String str, String str2, String str3, String str4, String str5, String str6, String str7, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, str2, str3, str4, str5, str6, (i2 & 64) != 0 ? "178.1.0.1.18782" : str7);
    }
}
