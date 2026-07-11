package miui.systemui.controlcenter.events;

import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.n;
import miui.systemui.dynamicisland.events.DynamicIslandEventsConstants;
import systemui.plugin.eventtracking.EventID;
import systemui.plugin.eventtracking.EventKey;

/* JADX INFO: loaded from: classes.dex */
@EventID(id = "click")
public final class SecondaryBrightnessPanelClickEvent {

    @EventKey(key = "click_status")
    private final String clickStatus;

    @EventKey(key = "click_element_style")
    private final String elementStyle;

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

    @EventKey(key = QsFlipEventsKt.EVENT_KEY_FLIP_QS_DISPLAY_NAME)
    private final String qsTitle;

    @EventKey(key = DynamicIslandEventsConstants.PublicParams.SCREEN_TYPE)
    private final String screenType;

    @EventKey(key = "style")
    private final String style;

    @EventKey(key = "quick_switch_from")
    private final String switchFrom;

    @EventKey(key = "tip")
    private final String tip;

    @EventKey(key = "track_id")
    private final String trackId;

    @EventKey(key = "control_center_version")
    private final String version;

    public SecondaryBrightnessPanelClickEvent(String trackId, String modelType, String phoneType, String screenType, String version, String style, int i2, String orientation, String qsName, String qsTitle, String elementStyle, String switchFrom, String clickStatus, String tip) {
        n.g(trackId, "trackId");
        n.g(modelType, "modelType");
        n.g(phoneType, "phoneType");
        n.g(screenType, "screenType");
        n.g(version, "version");
        n.g(style, "style");
        n.g(orientation, "orientation");
        n.g(qsName, "qsName");
        n.g(qsTitle, "qsTitle");
        n.g(elementStyle, "elementStyle");
        n.g(switchFrom, "switchFrom");
        n.g(clickStatus, "clickStatus");
        n.g(tip, "tip");
        this.trackId = trackId;
        this.modelType = modelType;
        this.phoneType = phoneType;
        this.screenType = screenType;
        this.version = version;
        this.style = style;
        this.index = i2;
        this.orientation = orientation;
        this.qsName = qsName;
        this.qsTitle = qsTitle;
        this.elementStyle = elementStyle;
        this.switchFrom = switchFrom;
        this.clickStatus = clickStatus;
        this.tip = tip;
    }

    public final String component1() {
        return this.trackId;
    }

    public final String component10() {
        return this.qsTitle;
    }

    public final String component11() {
        return this.elementStyle;
    }

    public final String component12() {
        return this.switchFrom;
    }

    public final String component13() {
        return this.clickStatus;
    }

    public final String component14() {
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
        return this.style;
    }

    public final int component7() {
        return this.index;
    }

    public final String component8() {
        return this.orientation;
    }

    public final String component9() {
        return this.qsName;
    }

    public final SecondaryBrightnessPanelClickEvent copy(String trackId, String modelType, String phoneType, String screenType, String version, String style, int i2, String orientation, String qsName, String qsTitle, String elementStyle, String switchFrom, String clickStatus, String tip) {
        n.g(trackId, "trackId");
        n.g(modelType, "modelType");
        n.g(phoneType, "phoneType");
        n.g(screenType, "screenType");
        n.g(version, "version");
        n.g(style, "style");
        n.g(orientation, "orientation");
        n.g(qsName, "qsName");
        n.g(qsTitle, "qsTitle");
        n.g(elementStyle, "elementStyle");
        n.g(switchFrom, "switchFrom");
        n.g(clickStatus, "clickStatus");
        n.g(tip, "tip");
        return new SecondaryBrightnessPanelClickEvent(trackId, modelType, phoneType, screenType, version, style, i2, orientation, qsName, qsTitle, elementStyle, switchFrom, clickStatus, tip);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof SecondaryBrightnessPanelClickEvent)) {
            return false;
        }
        SecondaryBrightnessPanelClickEvent secondaryBrightnessPanelClickEvent = (SecondaryBrightnessPanelClickEvent) obj;
        return n.c(this.trackId, secondaryBrightnessPanelClickEvent.trackId) && n.c(this.modelType, secondaryBrightnessPanelClickEvent.modelType) && n.c(this.phoneType, secondaryBrightnessPanelClickEvent.phoneType) && n.c(this.screenType, secondaryBrightnessPanelClickEvent.screenType) && n.c(this.version, secondaryBrightnessPanelClickEvent.version) && n.c(this.style, secondaryBrightnessPanelClickEvent.style) && this.index == secondaryBrightnessPanelClickEvent.index && n.c(this.orientation, secondaryBrightnessPanelClickEvent.orientation) && n.c(this.qsName, secondaryBrightnessPanelClickEvent.qsName) && n.c(this.qsTitle, secondaryBrightnessPanelClickEvent.qsTitle) && n.c(this.elementStyle, secondaryBrightnessPanelClickEvent.elementStyle) && n.c(this.switchFrom, secondaryBrightnessPanelClickEvent.switchFrom) && n.c(this.clickStatus, secondaryBrightnessPanelClickEvent.clickStatus) && n.c(this.tip, secondaryBrightnessPanelClickEvent.tip);
    }

    public final String getClickStatus() {
        return this.clickStatus;
    }

    public final String getElementStyle() {
        return this.elementStyle;
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

    public final String getQsTitle() {
        return this.qsTitle;
    }

    public final String getScreenType() {
        return this.screenType;
    }

    public final String getStyle() {
        return this.style;
    }

    public final String getSwitchFrom() {
        return this.switchFrom;
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
        return (((((((((((((((((((((((((this.trackId.hashCode() * 31) + this.modelType.hashCode()) * 31) + this.phoneType.hashCode()) * 31) + this.screenType.hashCode()) * 31) + this.version.hashCode()) * 31) + this.style.hashCode()) * 31) + Integer.hashCode(this.index)) * 31) + this.orientation.hashCode()) * 31) + this.qsName.hashCode()) * 31) + this.qsTitle.hashCode()) * 31) + this.elementStyle.hashCode()) * 31) + this.switchFrom.hashCode()) * 31) + this.clickStatus.hashCode()) * 31) + this.tip.hashCode();
    }

    public String toString() {
        return "SecondaryBrightnessPanelClickEvent(trackId=" + this.trackId + ", modelType=" + this.modelType + ", phoneType=" + this.phoneType + ", screenType=" + this.screenType + ", version=" + this.version + ", style=" + this.style + ", index=" + this.index + ", orientation=" + this.orientation + ", qsName=" + this.qsName + ", qsTitle=" + this.qsTitle + ", elementStyle=" + this.elementStyle + ", switchFrom=" + this.switchFrom + ", clickStatus=" + this.clickStatus + ", tip=" + this.tip + ")";
    }

    public /* synthetic */ SecondaryBrightnessPanelClickEvent(String str, String str2, String str3, String str4, String str5, String str6, int i2, String str7, String str8, String str9, String str10, String str11, String str12, String str13, int i3, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, str2, str3, str4, str5, str6, i2, str7, str8, str9, str10, str11, str12, (i3 & 8192) != 0 ? "178.1.2.1.18778" : str13);
    }
}
