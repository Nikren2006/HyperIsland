package miui.systemui.controlcenter.events;

import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.n;
import miui.systemui.dynamicisland.events.DynamicIslandEventsConstants;
import systemui.plugin.eventtracking.EventID;
import systemui.plugin.eventtracking.EventKey;

/* JADX INFO: loaded from: classes.dex */
@EventID(id = "adjust")
public final class VolumeSeekbarAdjustEvent {

    @EventKey(key = "after_value")
    private final int afterValue;

    @EventKey(key = "before_value")
    private final int beforeValue;

    @EventKey(key = "cause_extremum_type")
    private final String ceType;

    @EventKey(key = "model_type")
    private final String modelType;

    @EventKey(key = DynamicIslandEventsConstants.PublicParams.PHONE_TYPE)
    private final String phoneType;

    @EventKey(key = DynamicIslandEventsConstants.PublicParams.SCREEN_TYPE)
    private final String screenType;

    @EventKey(key = "quick_switch_from")
    private final String switchFrom;

    @EventKey(key = "adjust_switch_name")
    private final String switchName;

    @EventKey(key = "tip")
    private final String tip;

    @EventKey(key = "control_center_version")
    private final String version;

    public VolumeSeekbarAdjustEvent(String modelType, String phoneType, String screenType, String switchName, String switchFrom, String version, int i2, int i3, String ceType, String tip) {
        n.g(modelType, "modelType");
        n.g(phoneType, "phoneType");
        n.g(screenType, "screenType");
        n.g(switchName, "switchName");
        n.g(switchFrom, "switchFrom");
        n.g(version, "version");
        n.g(ceType, "ceType");
        n.g(tip, "tip");
        this.modelType = modelType;
        this.phoneType = phoneType;
        this.screenType = screenType;
        this.switchName = switchName;
        this.switchFrom = switchFrom;
        this.version = version;
        this.beforeValue = i2;
        this.afterValue = i3;
        this.ceType = ceType;
        this.tip = tip;
    }

    public final String component1() {
        return this.modelType;
    }

    public final String component10() {
        return this.tip;
    }

    public final String component2() {
        return this.phoneType;
    }

    public final String component3() {
        return this.screenType;
    }

    public final String component4() {
        return this.switchName;
    }

    public final String component5() {
        return this.switchFrom;
    }

    public final String component6() {
        return this.version;
    }

    public final int component7() {
        return this.beforeValue;
    }

    public final int component8() {
        return this.afterValue;
    }

    public final String component9() {
        return this.ceType;
    }

    public final VolumeSeekbarAdjustEvent copy(String modelType, String phoneType, String screenType, String switchName, String switchFrom, String version, int i2, int i3, String ceType, String tip) {
        n.g(modelType, "modelType");
        n.g(phoneType, "phoneType");
        n.g(screenType, "screenType");
        n.g(switchName, "switchName");
        n.g(switchFrom, "switchFrom");
        n.g(version, "version");
        n.g(ceType, "ceType");
        n.g(tip, "tip");
        return new VolumeSeekbarAdjustEvent(modelType, phoneType, screenType, switchName, switchFrom, version, i2, i3, ceType, tip);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof VolumeSeekbarAdjustEvent)) {
            return false;
        }
        VolumeSeekbarAdjustEvent volumeSeekbarAdjustEvent = (VolumeSeekbarAdjustEvent) obj;
        return n.c(this.modelType, volumeSeekbarAdjustEvent.modelType) && n.c(this.phoneType, volumeSeekbarAdjustEvent.phoneType) && n.c(this.screenType, volumeSeekbarAdjustEvent.screenType) && n.c(this.switchName, volumeSeekbarAdjustEvent.switchName) && n.c(this.switchFrom, volumeSeekbarAdjustEvent.switchFrom) && n.c(this.version, volumeSeekbarAdjustEvent.version) && this.beforeValue == volumeSeekbarAdjustEvent.beforeValue && this.afterValue == volumeSeekbarAdjustEvent.afterValue && n.c(this.ceType, volumeSeekbarAdjustEvent.ceType) && n.c(this.tip, volumeSeekbarAdjustEvent.tip);
    }

    public final int getAfterValue() {
        return this.afterValue;
    }

    public final int getBeforeValue() {
        return this.beforeValue;
    }

    public final String getCeType() {
        return this.ceType;
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

    public final String getSwitchFrom() {
        return this.switchFrom;
    }

    public final String getSwitchName() {
        return this.switchName;
    }

    public final String getTip() {
        return this.tip;
    }

    public final String getVersion() {
        return this.version;
    }

    public int hashCode() {
        return (((((((((((((((((this.modelType.hashCode() * 31) + this.phoneType.hashCode()) * 31) + this.screenType.hashCode()) * 31) + this.switchName.hashCode()) * 31) + this.switchFrom.hashCode()) * 31) + this.version.hashCode()) * 31) + Integer.hashCode(this.beforeValue)) * 31) + Integer.hashCode(this.afterValue)) * 31) + this.ceType.hashCode()) * 31) + this.tip.hashCode();
    }

    public String toString() {
        return "VolumeSeekbarAdjustEvent(modelType=" + this.modelType + ", phoneType=" + this.phoneType + ", screenType=" + this.screenType + ", switchName=" + this.switchName + ", switchFrom=" + this.switchFrom + ", version=" + this.version + ", beforeValue=" + this.beforeValue + ", afterValue=" + this.afterValue + ", ceType=" + this.ceType + ", tip=" + this.tip + ")";
    }

    public /* synthetic */ VolumeSeekbarAdjustEvent(String str, String str2, String str3, String str4, String str5, String str6, int i2, int i3, String str7, String str8, int i4, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, str2, str3, str4, str5, str6, i2, i3, str7, (i4 & 512) != 0 ? "178.1.4.1.37248" : str8);
    }
}
