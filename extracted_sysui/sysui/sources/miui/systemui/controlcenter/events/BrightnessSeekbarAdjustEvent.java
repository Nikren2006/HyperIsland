package miui.systemui.controlcenter.events;

import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.n;
import miui.systemui.dynamicisland.events.DynamicIslandEventsConstants;
import systemui.plugin.eventtracking.EventID;
import systemui.plugin.eventtracking.EventKey;

/* JADX INFO: loaded from: classes.dex */
@EventID(id = "adjust")
public final class BrightnessSeekbarAdjustEvent {

    @EventKey(key = "after_value")
    private final int afterValue;

    @EventKey(key = "before_value")
    private final int beforeValue;

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

    public BrightnessSeekbarAdjustEvent(String modelType, String phoneType, String screenType, String switchName, String switchFrom, String version, int i2, int i3, String tip) {
        n.g(modelType, "modelType");
        n.g(phoneType, "phoneType");
        n.g(screenType, "screenType");
        n.g(switchName, "switchName");
        n.g(switchFrom, "switchFrom");
        n.g(version, "version");
        n.g(tip, "tip");
        this.modelType = modelType;
        this.phoneType = phoneType;
        this.screenType = screenType;
        this.switchName = switchName;
        this.switchFrom = switchFrom;
        this.version = version;
        this.beforeValue = i2;
        this.afterValue = i3;
        this.tip = tip;
    }

    public final String component1() {
        return this.modelType;
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
        return this.tip;
    }

    public final BrightnessSeekbarAdjustEvent copy(String modelType, String phoneType, String screenType, String switchName, String switchFrom, String version, int i2, int i3, String tip) {
        n.g(modelType, "modelType");
        n.g(phoneType, "phoneType");
        n.g(screenType, "screenType");
        n.g(switchName, "switchName");
        n.g(switchFrom, "switchFrom");
        n.g(version, "version");
        n.g(tip, "tip");
        return new BrightnessSeekbarAdjustEvent(modelType, phoneType, screenType, switchName, switchFrom, version, i2, i3, tip);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof BrightnessSeekbarAdjustEvent)) {
            return false;
        }
        BrightnessSeekbarAdjustEvent brightnessSeekbarAdjustEvent = (BrightnessSeekbarAdjustEvent) obj;
        return n.c(this.modelType, brightnessSeekbarAdjustEvent.modelType) && n.c(this.phoneType, brightnessSeekbarAdjustEvent.phoneType) && n.c(this.screenType, brightnessSeekbarAdjustEvent.screenType) && n.c(this.switchName, brightnessSeekbarAdjustEvent.switchName) && n.c(this.switchFrom, brightnessSeekbarAdjustEvent.switchFrom) && n.c(this.version, brightnessSeekbarAdjustEvent.version) && this.beforeValue == brightnessSeekbarAdjustEvent.beforeValue && this.afterValue == brightnessSeekbarAdjustEvent.afterValue && n.c(this.tip, brightnessSeekbarAdjustEvent.tip);
    }

    public final int getAfterValue() {
        return this.afterValue;
    }

    public final int getBeforeValue() {
        return this.beforeValue;
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
        return (((((((((((((((this.modelType.hashCode() * 31) + this.phoneType.hashCode()) * 31) + this.screenType.hashCode()) * 31) + this.switchName.hashCode()) * 31) + this.switchFrom.hashCode()) * 31) + this.version.hashCode()) * 31) + Integer.hashCode(this.beforeValue)) * 31) + Integer.hashCode(this.afterValue)) * 31) + this.tip.hashCode();
    }

    public String toString() {
        return "BrightnessSeekbarAdjustEvent(modelType=" + this.modelType + ", phoneType=" + this.phoneType + ", screenType=" + this.screenType + ", switchName=" + this.switchName + ", switchFrom=" + this.switchFrom + ", version=" + this.version + ", beforeValue=" + this.beforeValue + ", afterValue=" + this.afterValue + ", tip=" + this.tip + ")";
    }

    public /* synthetic */ BrightnessSeekbarAdjustEvent(String str, String str2, String str3, String str4, String str5, String str6, int i2, int i3, String str7, int i4, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, str2, str3, str4, str5, str6, i2, i3, (i4 & 256) != 0 ? "178.1.4.1.37248" : str7);
    }
}
