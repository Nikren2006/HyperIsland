package miui.systemui.controlcenter.events;

import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.n;
import miui.systemui.dynamicisland.events.DynamicIslandEventsConstants;
import systemui.plugin.eventtracking.EventID;
import systemui.plugin.eventtracking.EventKey;

/* JADX INFO: loaded from: classes.dex */
@EventID(id = "open")
public final class EditPanelOpenEvent {

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

    public EditPanelOpenEvent(String trackId, String modelType, String phoneType, String screenType, String version, String tip) {
        n.g(trackId, "trackId");
        n.g(modelType, "modelType");
        n.g(phoneType, "phoneType");
        n.g(screenType, "screenType");
        n.g(version, "version");
        n.g(tip, "tip");
        this.trackId = trackId;
        this.modelType = modelType;
        this.phoneType = phoneType;
        this.screenType = screenType;
        this.version = version;
        this.tip = tip;
    }

    public static /* synthetic */ EditPanelOpenEvent copy$default(EditPanelOpenEvent editPanelOpenEvent, String str, String str2, String str3, String str4, String str5, String str6, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            str = editPanelOpenEvent.trackId;
        }
        if ((i2 & 2) != 0) {
            str2 = editPanelOpenEvent.modelType;
        }
        String str7 = str2;
        if ((i2 & 4) != 0) {
            str3 = editPanelOpenEvent.phoneType;
        }
        String str8 = str3;
        if ((i2 & 8) != 0) {
            str4 = editPanelOpenEvent.screenType;
        }
        String str9 = str4;
        if ((i2 & 16) != 0) {
            str5 = editPanelOpenEvent.version;
        }
        String str10 = str5;
        if ((i2 & 32) != 0) {
            str6 = editPanelOpenEvent.tip;
        }
        return editPanelOpenEvent.copy(str, str7, str8, str9, str10, str6);
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
        return this.tip;
    }

    public final EditPanelOpenEvent copy(String trackId, String modelType, String phoneType, String screenType, String version, String tip) {
        n.g(trackId, "trackId");
        n.g(modelType, "modelType");
        n.g(phoneType, "phoneType");
        n.g(screenType, "screenType");
        n.g(version, "version");
        n.g(tip, "tip");
        return new EditPanelOpenEvent(trackId, modelType, phoneType, screenType, version, tip);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof EditPanelOpenEvent)) {
            return false;
        }
        EditPanelOpenEvent editPanelOpenEvent = (EditPanelOpenEvent) obj;
        return n.c(this.trackId, editPanelOpenEvent.trackId) && n.c(this.modelType, editPanelOpenEvent.modelType) && n.c(this.phoneType, editPanelOpenEvent.phoneType) && n.c(this.screenType, editPanelOpenEvent.screenType) && n.c(this.version, editPanelOpenEvent.version) && n.c(this.tip, editPanelOpenEvent.tip);
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

    public int hashCode() {
        return (((((((((this.trackId.hashCode() * 31) + this.modelType.hashCode()) * 31) + this.phoneType.hashCode()) * 31) + this.screenType.hashCode()) * 31) + this.version.hashCode()) * 31) + this.tip.hashCode();
    }

    public String toString() {
        return "EditPanelOpenEvent(trackId=" + this.trackId + ", modelType=" + this.modelType + ", phoneType=" + this.phoneType + ", screenType=" + this.screenType + ", version=" + this.version + ", tip=" + this.tip + ")";
    }

    public /* synthetic */ EditPanelOpenEvent(String str, String str2, String str3, String str4, String str5, String str6, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, str2, str3, str4, str5, (i2 & 32) != 0 ? "178.1.7.1.18786" : str6);
    }
}
