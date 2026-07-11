package systemui.plugin.eventtracking.events;

import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.n;
import miui.systemui.dynamicisland.events.DynamicIslandEventsConstants;
import systemui.plugin.eventtracking.EventID;
import systemui.plugin.eventtracking.EventKey;

/* JADX INFO: loaded from: classes5.dex */
@EventID(id = "adjust")
public final class SecondaryVolumeTimerAdjustEvent {

    @EventKey(key = "after_value")
    private final double afterValue;

    @EventKey(key = "before_value")
    private final double beforeValue;

    @EventKey(key = "model_type")
    private final String modelType;

    @EventKey(key = DynamicIslandEventsConstants.PublicParams.PHONE_TYPE)
    private final String phoneType;

    @EventKey(key = "adjust_switch_name")
    private final String qsName;

    @EventKey(key = DynamicIslandEventsConstants.PublicParams.SCREEN_TYPE)
    private final String screenType;

    @EventKey(key = "quick_switch_from")
    private final String switchFrom;

    @EventKey(key = "tip")
    private final String tip;

    @EventKey(key = "control_center_version")
    private final String version;

    public SecondaryVolumeTimerAdjustEvent(String modelType, String phoneType, String screenType, String version, double d2, double d3, String qsName, String switchFrom, String tip) {
        n.g(modelType, "modelType");
        n.g(phoneType, "phoneType");
        n.g(screenType, "screenType");
        n.g(version, "version");
        n.g(qsName, "qsName");
        n.g(switchFrom, "switchFrom");
        n.g(tip, "tip");
        this.modelType = modelType;
        this.phoneType = phoneType;
        this.screenType = screenType;
        this.version = version;
        this.beforeValue = d2;
        this.afterValue = d3;
        this.qsName = qsName;
        this.switchFrom = switchFrom;
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
        return this.version;
    }

    public final double component5() {
        return this.beforeValue;
    }

    public final double component6() {
        return this.afterValue;
    }

    public final String component7() {
        return this.qsName;
    }

    public final String component8() {
        return this.switchFrom;
    }

    public final String component9() {
        return this.tip;
    }

    public final SecondaryVolumeTimerAdjustEvent copy(String modelType, String phoneType, String screenType, String version, double d2, double d3, String qsName, String switchFrom, String tip) {
        n.g(modelType, "modelType");
        n.g(phoneType, "phoneType");
        n.g(screenType, "screenType");
        n.g(version, "version");
        n.g(qsName, "qsName");
        n.g(switchFrom, "switchFrom");
        n.g(tip, "tip");
        return new SecondaryVolumeTimerAdjustEvent(modelType, phoneType, screenType, version, d2, d3, qsName, switchFrom, tip);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof SecondaryVolumeTimerAdjustEvent)) {
            return false;
        }
        SecondaryVolumeTimerAdjustEvent secondaryVolumeTimerAdjustEvent = (SecondaryVolumeTimerAdjustEvent) obj;
        return n.c(this.modelType, secondaryVolumeTimerAdjustEvent.modelType) && n.c(this.phoneType, secondaryVolumeTimerAdjustEvent.phoneType) && n.c(this.screenType, secondaryVolumeTimerAdjustEvent.screenType) && n.c(this.version, secondaryVolumeTimerAdjustEvent.version) && Double.compare(this.beforeValue, secondaryVolumeTimerAdjustEvent.beforeValue) == 0 && Double.compare(this.afterValue, secondaryVolumeTimerAdjustEvent.afterValue) == 0 && n.c(this.qsName, secondaryVolumeTimerAdjustEvent.qsName) && n.c(this.switchFrom, secondaryVolumeTimerAdjustEvent.switchFrom) && n.c(this.tip, secondaryVolumeTimerAdjustEvent.tip);
    }

    public final double getAfterValue() {
        return this.afterValue;
    }

    public final double getBeforeValue() {
        return this.beforeValue;
    }

    public final String getModelType() {
        return this.modelType;
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

    public final String getSwitchFrom() {
        return this.switchFrom;
    }

    public final String getTip() {
        return this.tip;
    }

    public final String getVersion() {
        return this.version;
    }

    public int hashCode() {
        return (((((((((((((((this.modelType.hashCode() * 31) + this.phoneType.hashCode()) * 31) + this.screenType.hashCode()) * 31) + this.version.hashCode()) * 31) + Double.hashCode(this.beforeValue)) * 31) + Double.hashCode(this.afterValue)) * 31) + this.qsName.hashCode()) * 31) + this.switchFrom.hashCode()) * 31) + this.tip.hashCode();
    }

    public String toString() {
        return "SecondaryVolumeTimerAdjustEvent(modelType=" + this.modelType + ", phoneType=" + this.phoneType + ", screenType=" + this.screenType + ", version=" + this.version + ", beforeValue=" + this.beforeValue + ", afterValue=" + this.afterValue + ", qsName=" + this.qsName + ", switchFrom=" + this.switchFrom + ", tip=" + this.tip + ")";
    }

    public /* synthetic */ SecondaryVolumeTimerAdjustEvent(String str, String str2, String str3, String str4, double d2, double d3, String str5, String str6, String str7, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, str2, str3, str4, d2, d3, str5, str6, (i2 & 256) != 0 ? "178.1.4.1.37248" : str7);
    }
}
