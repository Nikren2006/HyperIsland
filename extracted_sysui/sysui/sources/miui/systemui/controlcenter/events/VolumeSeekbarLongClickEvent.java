package miui.systemui.controlcenter.events;

import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.n;
import miui.systemui.dynamicisland.events.DynamicIslandEventsConstants;
import systemui.plugin.eventtracking.EventID;
import systemui.plugin.eventtracking.EventKey;

/* JADX INFO: loaded from: classes.dex */
@EventID(id = "long_click")
public final class VolumeSeekbarLongClickEvent {

    @EventKey(key = "model_type")
    private final String modelType;

    @EventKey(key = "screen_orientation")
    private final String orientation;

    @EventKey(key = DynamicIslandEventsConstants.PublicParams.PHONE_TYPE)
    private final String phoneType;

    @EventKey(key = DynamicIslandEventsConstants.PublicParams.SCREEN_TYPE)
    private final String screenType;

    @EventKey(key = QsFlipEventsKt.EVENT_KEY_FLIP_QS_NAME)
    private final String seekBarName;

    @EventKey(key = "style")
    private final String style;

    @EventKey(key = "tip")
    private final String tip;

    @EventKey(key = "track_id")
    private final String trackId;

    @EventKey(key = "control_center_version")
    private final String version;

    public VolumeSeekbarLongClickEvent(String trackId, String modelType, String phoneType, String screenType, String version, String orientation, String style, String seekBarName, String tip) {
        n.g(trackId, "trackId");
        n.g(modelType, "modelType");
        n.g(phoneType, "phoneType");
        n.g(screenType, "screenType");
        n.g(version, "version");
        n.g(orientation, "orientation");
        n.g(style, "style");
        n.g(seekBarName, "seekBarName");
        n.g(tip, "tip");
        this.trackId = trackId;
        this.modelType = modelType;
        this.phoneType = phoneType;
        this.screenType = screenType;
        this.version = version;
        this.orientation = orientation;
        this.style = style;
        this.seekBarName = seekBarName;
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
        return this.style;
    }

    public final String component8() {
        return this.seekBarName;
    }

    public final String component9() {
        return this.tip;
    }

    public final VolumeSeekbarLongClickEvent copy(String trackId, String modelType, String phoneType, String screenType, String version, String orientation, String style, String seekBarName, String tip) {
        n.g(trackId, "trackId");
        n.g(modelType, "modelType");
        n.g(phoneType, "phoneType");
        n.g(screenType, "screenType");
        n.g(version, "version");
        n.g(orientation, "orientation");
        n.g(style, "style");
        n.g(seekBarName, "seekBarName");
        n.g(tip, "tip");
        return new VolumeSeekbarLongClickEvent(trackId, modelType, phoneType, screenType, version, orientation, style, seekBarName, tip);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof VolumeSeekbarLongClickEvent)) {
            return false;
        }
        VolumeSeekbarLongClickEvent volumeSeekbarLongClickEvent = (VolumeSeekbarLongClickEvent) obj;
        return n.c(this.trackId, volumeSeekbarLongClickEvent.trackId) && n.c(this.modelType, volumeSeekbarLongClickEvent.modelType) && n.c(this.phoneType, volumeSeekbarLongClickEvent.phoneType) && n.c(this.screenType, volumeSeekbarLongClickEvent.screenType) && n.c(this.version, volumeSeekbarLongClickEvent.version) && n.c(this.orientation, volumeSeekbarLongClickEvent.orientation) && n.c(this.style, volumeSeekbarLongClickEvent.style) && n.c(this.seekBarName, volumeSeekbarLongClickEvent.seekBarName) && n.c(this.tip, volumeSeekbarLongClickEvent.tip);
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

    public final String getSeekBarName() {
        return this.seekBarName;
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
        return (((((((((((((((this.trackId.hashCode() * 31) + this.modelType.hashCode()) * 31) + this.phoneType.hashCode()) * 31) + this.screenType.hashCode()) * 31) + this.version.hashCode()) * 31) + this.orientation.hashCode()) * 31) + this.style.hashCode()) * 31) + this.seekBarName.hashCode()) * 31) + this.tip.hashCode();
    }

    public String toString() {
        return "VolumeSeekbarLongClickEvent(trackId=" + this.trackId + ", modelType=" + this.modelType + ", phoneType=" + this.phoneType + ", screenType=" + this.screenType + ", version=" + this.version + ", orientation=" + this.orientation + ", style=" + this.style + ", seekBarName=" + this.seekBarName + ", tip=" + this.tip + ")";
    }

    public /* synthetic */ VolumeSeekbarLongClickEvent(String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8, String str9, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, str2, str3, str4, str5, str6, str7, str8, (i2 & 256) != 0 ? "178.1.2.1.18779" : str9);
    }
}
