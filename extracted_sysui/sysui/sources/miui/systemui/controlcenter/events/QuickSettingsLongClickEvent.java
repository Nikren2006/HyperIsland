package miui.systemui.controlcenter.events;

import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.n;
import miui.systemui.dynamicisland.events.DynamicIslandEventsConstants;
import systemui.plugin.eventtracking.EventID;
import systemui.plugin.eventtracking.EventKey;

/* JADX INFO: loaded from: classes.dex */
@EventID(id = "long_click")
public final class QuickSettingsLongClickEvent {

    @EventKey(key = QsFlipEventsKt.EVENT_KEY_FLIP_QS_INDEX)
    private final int index;

    @EventKey(key = "model_type")
    private final String modelType;

    @EventKey(key = "screen_orientation")
    private final String orientation;

    @EventKey(key = DynamicIslandEventsConstants.PublicParams.PHONE_TYPE)
    private final String phoneType;

    @EventKey(key = QsFlipEventsKt.EVENT_KEY_FLIP_QS_NAME)
    private final String qsName;

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

    public QuickSettingsLongClickEvent(String trackId, String modelType, String phoneType, String screenType, String version, int i2, String orientation, String style, String qsName, String tip) {
        n.g(trackId, "trackId");
        n.g(modelType, "modelType");
        n.g(phoneType, "phoneType");
        n.g(screenType, "screenType");
        n.g(version, "version");
        n.g(orientation, "orientation");
        n.g(style, "style");
        n.g(qsName, "qsName");
        n.g(tip, "tip");
        this.trackId = trackId;
        this.modelType = modelType;
        this.phoneType = phoneType;
        this.screenType = screenType;
        this.version = version;
        this.index = i2;
        this.orientation = orientation;
        this.style = style;
        this.qsName = qsName;
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

    public final int component6() {
        return this.index;
    }

    public final String component7() {
        return this.orientation;
    }

    public final String component8() {
        return this.style;
    }

    public final String component9() {
        return this.qsName;
    }

    public final QuickSettingsLongClickEvent copy(String trackId, String modelType, String phoneType, String screenType, String version, int i2, String orientation, String style, String qsName, String tip) {
        n.g(trackId, "trackId");
        n.g(modelType, "modelType");
        n.g(phoneType, "phoneType");
        n.g(screenType, "screenType");
        n.g(version, "version");
        n.g(orientation, "orientation");
        n.g(style, "style");
        n.g(qsName, "qsName");
        n.g(tip, "tip");
        return new QuickSettingsLongClickEvent(trackId, modelType, phoneType, screenType, version, i2, orientation, style, qsName, tip);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof QuickSettingsLongClickEvent)) {
            return false;
        }
        QuickSettingsLongClickEvent quickSettingsLongClickEvent = (QuickSettingsLongClickEvent) obj;
        return n.c(this.trackId, quickSettingsLongClickEvent.trackId) && n.c(this.modelType, quickSettingsLongClickEvent.modelType) && n.c(this.phoneType, quickSettingsLongClickEvent.phoneType) && n.c(this.screenType, quickSettingsLongClickEvent.screenType) && n.c(this.version, quickSettingsLongClickEvent.version) && this.index == quickSettingsLongClickEvent.index && n.c(this.orientation, quickSettingsLongClickEvent.orientation) && n.c(this.style, quickSettingsLongClickEvent.style) && n.c(this.qsName, quickSettingsLongClickEvent.qsName) && n.c(this.tip, quickSettingsLongClickEvent.tip);
    }

    public final int getIndex() {
        return this.index;
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

    public final String getQsName() {
        return this.qsName;
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
        return (((((((((((((((((this.trackId.hashCode() * 31) + this.modelType.hashCode()) * 31) + this.phoneType.hashCode()) * 31) + this.screenType.hashCode()) * 31) + this.version.hashCode()) * 31) + Integer.hashCode(this.index)) * 31) + this.orientation.hashCode()) * 31) + this.style.hashCode()) * 31) + this.qsName.hashCode()) * 31) + this.tip.hashCode();
    }

    public String toString() {
        return "QuickSettingsLongClickEvent(trackId=" + this.trackId + ", modelType=" + this.modelType + ", phoneType=" + this.phoneType + ", screenType=" + this.screenType + ", version=" + this.version + ", index=" + this.index + ", orientation=" + this.orientation + ", style=" + this.style + ", qsName=" + this.qsName + ", tip=" + this.tip + ")";
    }

    public /* synthetic */ QuickSettingsLongClickEvent(String str, String str2, String str3, String str4, String str5, int i2, String str6, String str7, String str8, String str9, int i3, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, str2, str3, str4, str5, i2, str6, str7, str8, (i3 & 512) != 0 ? "178.1.2.1.18779" : str9);
    }
}
