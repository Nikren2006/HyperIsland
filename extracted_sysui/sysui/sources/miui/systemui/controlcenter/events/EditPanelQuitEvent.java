package miui.systemui.controlcenter.events;

import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.n;
import miui.systemui.dynamicisland.events.DynamicIslandEventsConstants;
import systemui.plugin.eventtracking.EventID;
import systemui.plugin.eventtracking.EventKey;

/* JADX INFO: loaded from: classes.dex */
@EventID(id = "quit")
public final class EditPanelQuitEvent {

    @EventKey(key = "if_edit")
    private final String edited;

    @EventKey(key = "model_type")
    private final String modelType;

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

    @EventKey(key = "quit_way")
    private final String way;

    public EditPanelQuitEvent(String trackId, String modelType, String phoneType, String screenType, String version, String way, String edited, String tip) {
        n.g(trackId, "trackId");
        n.g(modelType, "modelType");
        n.g(phoneType, "phoneType");
        n.g(screenType, "screenType");
        n.g(version, "version");
        n.g(way, "way");
        n.g(edited, "edited");
        n.g(tip, "tip");
        this.trackId = trackId;
        this.modelType = modelType;
        this.phoneType = phoneType;
        this.screenType = screenType;
        this.version = version;
        this.way = way;
        this.edited = edited;
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
        return this.way;
    }

    public final String component7() {
        return this.edited;
    }

    public final String component8() {
        return this.tip;
    }

    public final EditPanelQuitEvent copy(String trackId, String modelType, String phoneType, String screenType, String version, String way, String edited, String tip) {
        n.g(trackId, "trackId");
        n.g(modelType, "modelType");
        n.g(phoneType, "phoneType");
        n.g(screenType, "screenType");
        n.g(version, "version");
        n.g(way, "way");
        n.g(edited, "edited");
        n.g(tip, "tip");
        return new EditPanelQuitEvent(trackId, modelType, phoneType, screenType, version, way, edited, tip);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof EditPanelQuitEvent)) {
            return false;
        }
        EditPanelQuitEvent editPanelQuitEvent = (EditPanelQuitEvent) obj;
        return n.c(this.trackId, editPanelQuitEvent.trackId) && n.c(this.modelType, editPanelQuitEvent.modelType) && n.c(this.phoneType, editPanelQuitEvent.phoneType) && n.c(this.screenType, editPanelQuitEvent.screenType) && n.c(this.version, editPanelQuitEvent.version) && n.c(this.way, editPanelQuitEvent.way) && n.c(this.edited, editPanelQuitEvent.edited) && n.c(this.tip, editPanelQuitEvent.tip);
    }

    public final String getEdited() {
        return this.edited;
    }

    public final String getModelType() {
        return this.modelType;
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

    public final String getWay() {
        return this.way;
    }

    public int hashCode() {
        return (((((((((((((this.trackId.hashCode() * 31) + this.modelType.hashCode()) * 31) + this.phoneType.hashCode()) * 31) + this.screenType.hashCode()) * 31) + this.version.hashCode()) * 31) + this.way.hashCode()) * 31) + this.edited.hashCode()) * 31) + this.tip.hashCode();
    }

    public String toString() {
        return "EditPanelQuitEvent(trackId=" + this.trackId + ", modelType=" + this.modelType + ", phoneType=" + this.phoneType + ", screenType=" + this.screenType + ", version=" + this.version + ", way=" + this.way + ", edited=" + this.edited + ", tip=" + this.tip + ")";
    }

    public /* synthetic */ EditPanelQuitEvent(String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, str2, str3, str4, str5, str6, str7, (i2 & 128) != 0 ? "178.1.7.1.18787" : str8);
    }
}
