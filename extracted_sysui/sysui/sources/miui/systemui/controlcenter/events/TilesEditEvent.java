package miui.systemui.controlcenter.events;

import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.n;
import miui.systemui.controlcenter.qs.TileSpecsKt;
import miui.systemui.dynamicisland.events.DynamicIslandEventsConstants;
import systemui.plugin.eventtracking.EventID;
import systemui.plugin.eventtracking.EventKey;

/* JADX INFO: loaded from: classes.dex */
@EventID(id = TileSpecsKt.TILE_SPEC_EDIT)
public final class TilesEditEvent {

    @EventKey(key = "model_type")
    private final String modelType;

    @EventKey(key = QsFlipEventsKt.EVENT_KEY_FLIP_QS_NAME)
    private final String name;

    @EventKey(key = DynamicIslandEventsConstants.PublicParams.PHONE_TYPE)
    private final String phoneType;

    @EventKey(key = QsFlipEventsKt.EVENT_KEY_FLIP_QS_INDEX)
    private final int position;

    @EventKey(key = DynamicIslandEventsConstants.PublicParams.SCREEN_TYPE)
    private final String screenType;

    @EventKey(key = "tip")
    private final String tip;

    @EventKey(key = "track_id")
    private final String trackId;

    @EventKey(key = "edit_type")
    private final String type;

    @EventKey(key = "control_center_version")
    private final String version;

    public TilesEditEvent(String trackId, String modelType, String phoneType, String screenType, String version, String type, String name, int i2, String tip) {
        n.g(trackId, "trackId");
        n.g(modelType, "modelType");
        n.g(phoneType, "phoneType");
        n.g(screenType, "screenType");
        n.g(version, "version");
        n.g(type, "type");
        n.g(name, "name");
        n.g(tip, "tip");
        this.trackId = trackId;
        this.modelType = modelType;
        this.phoneType = phoneType;
        this.screenType = screenType;
        this.version = version;
        this.type = type;
        this.name = name;
        this.position = i2;
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
        return this.type;
    }

    public final String component7() {
        return this.name;
    }

    public final int component8() {
        return this.position;
    }

    public final String component9() {
        return this.tip;
    }

    public final TilesEditEvent copy(String trackId, String modelType, String phoneType, String screenType, String version, String type, String name, int i2, String tip) {
        n.g(trackId, "trackId");
        n.g(modelType, "modelType");
        n.g(phoneType, "phoneType");
        n.g(screenType, "screenType");
        n.g(version, "version");
        n.g(type, "type");
        n.g(name, "name");
        n.g(tip, "tip");
        return new TilesEditEvent(trackId, modelType, phoneType, screenType, version, type, name, i2, tip);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof TilesEditEvent)) {
            return false;
        }
        TilesEditEvent tilesEditEvent = (TilesEditEvent) obj;
        return n.c(this.trackId, tilesEditEvent.trackId) && n.c(this.modelType, tilesEditEvent.modelType) && n.c(this.phoneType, tilesEditEvent.phoneType) && n.c(this.screenType, tilesEditEvent.screenType) && n.c(this.version, tilesEditEvent.version) && n.c(this.type, tilesEditEvent.type) && n.c(this.name, tilesEditEvent.name) && this.position == tilesEditEvent.position && n.c(this.tip, tilesEditEvent.tip);
    }

    public final String getModelType() {
        return this.modelType;
    }

    public final String getName() {
        return this.name;
    }

    public final String getPhoneType() {
        return this.phoneType;
    }

    public final int getPosition() {
        return this.position;
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

    public final String getType() {
        return this.type;
    }

    public final String getVersion() {
        return this.version;
    }

    public int hashCode() {
        return (((((((((((((((this.trackId.hashCode() * 31) + this.modelType.hashCode()) * 31) + this.phoneType.hashCode()) * 31) + this.screenType.hashCode()) * 31) + this.version.hashCode()) * 31) + this.type.hashCode()) * 31) + this.name.hashCode()) * 31) + Integer.hashCode(this.position)) * 31) + this.tip.hashCode();
    }

    public String toString() {
        return "TilesEditEvent(trackId=" + this.trackId + ", modelType=" + this.modelType + ", phoneType=" + this.phoneType + ", screenType=" + this.screenType + ", version=" + this.version + ", type=" + this.type + ", name=" + this.name + ", position=" + this.position + ", tip=" + this.tip + ")";
    }

    public /* synthetic */ TilesEditEvent(String str, String str2, String str3, String str4, String str5, String str6, String str7, int i2, String str8, int i3, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, str2, str3, str4, str5, str6, str7, i2, (i3 & 256) != 0 ? "178.1.7.1.18788" : str8);
    }
}
