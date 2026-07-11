package miui.systemui.controlcenter.events;

import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.n;
import miui.systemui.dynamicisland.events.DynamicIslandEventsConstants;
import systemui.plugin.eventtracking.EventID;
import systemui.plugin.eventtracking.EventKey;

/* JADX INFO: loaded from: classes.dex */
@EventID(id = "slide")
public final class PanelSlideEvent {

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

    public PanelSlideEvent(String trackId, String modelType, String phoneType, String screenType, String orientation, String version, String tip) {
        n.g(trackId, "trackId");
        n.g(modelType, "modelType");
        n.g(phoneType, "phoneType");
        n.g(screenType, "screenType");
        n.g(orientation, "orientation");
        n.g(version, "version");
        n.g(tip, "tip");
        this.trackId = trackId;
        this.modelType = modelType;
        this.phoneType = phoneType;
        this.screenType = screenType;
        this.orientation = orientation;
        this.version = version;
        this.tip = tip;
    }

    public static /* synthetic */ PanelSlideEvent copy$default(PanelSlideEvent panelSlideEvent, String str, String str2, String str3, String str4, String str5, String str6, String str7, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            str = panelSlideEvent.trackId;
        }
        if ((i2 & 2) != 0) {
            str2 = panelSlideEvent.modelType;
        }
        String str8 = str2;
        if ((i2 & 4) != 0) {
            str3 = panelSlideEvent.phoneType;
        }
        String str9 = str3;
        if ((i2 & 8) != 0) {
            str4 = panelSlideEvent.screenType;
        }
        String str10 = str4;
        if ((i2 & 16) != 0) {
            str5 = panelSlideEvent.orientation;
        }
        String str11 = str5;
        if ((i2 & 32) != 0) {
            str6 = panelSlideEvent.version;
        }
        String str12 = str6;
        if ((i2 & 64) != 0) {
            str7 = panelSlideEvent.tip;
        }
        return panelSlideEvent.copy(str, str8, str9, str10, str11, str12, str7);
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
        return this.orientation;
    }

    public final String component6() {
        return this.version;
    }

    public final String component7() {
        return this.tip;
    }

    public final PanelSlideEvent copy(String trackId, String modelType, String phoneType, String screenType, String orientation, String version, String tip) {
        n.g(trackId, "trackId");
        n.g(modelType, "modelType");
        n.g(phoneType, "phoneType");
        n.g(screenType, "screenType");
        n.g(orientation, "orientation");
        n.g(version, "version");
        n.g(tip, "tip");
        return new PanelSlideEvent(trackId, modelType, phoneType, screenType, orientation, version, tip);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof PanelSlideEvent)) {
            return false;
        }
        PanelSlideEvent panelSlideEvent = (PanelSlideEvent) obj;
        return n.c(this.trackId, panelSlideEvent.trackId) && n.c(this.modelType, panelSlideEvent.modelType) && n.c(this.phoneType, panelSlideEvent.phoneType) && n.c(this.screenType, panelSlideEvent.screenType) && n.c(this.orientation, panelSlideEvent.orientation) && n.c(this.version, panelSlideEvent.version) && n.c(this.tip, panelSlideEvent.tip);
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
        return (((((((((((this.trackId.hashCode() * 31) + this.modelType.hashCode()) * 31) + this.phoneType.hashCode()) * 31) + this.screenType.hashCode()) * 31) + this.orientation.hashCode()) * 31) + this.version.hashCode()) * 31) + this.tip.hashCode();
    }

    public String toString() {
        return "PanelSlideEvent(trackId=" + this.trackId + ", modelType=" + this.modelType + ", phoneType=" + this.phoneType + ", screenType=" + this.screenType + ", orientation=" + this.orientation + ", version=" + this.version + ", tip=" + this.tip + ")";
    }

    public /* synthetic */ PanelSlideEvent(String str, String str2, String str3, String str4, String str5, String str6, String str7, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, str2, str3, str4, str5, str6, (i2 & 64) != 0 ? "178.1.2.1.18764" : str7);
    }
}
