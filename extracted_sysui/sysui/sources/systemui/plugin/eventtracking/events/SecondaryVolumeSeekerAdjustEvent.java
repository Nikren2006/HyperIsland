package systemui.plugin.eventtracking.events;

import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.n;
import miui.systemui.dynamicisland.events.DynamicIslandEventsConstants;
import systemui.plugin.eventtracking.EventID;
import systemui.plugin.eventtracking.EventKey;

/* JADX INFO: loaded from: classes5.dex */
@EventID(id = "adjust")
public final class SecondaryVolumeSeekerAdjustEvent {

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

    public SecondaryVolumeSeekerAdjustEvent(String modelType, String phoneType, String screenType, String version, int i2, int i3, String qsName, String ceType, String switchFrom, String tip) {
        n.g(modelType, "modelType");
        n.g(phoneType, "phoneType");
        n.g(screenType, "screenType");
        n.g(version, "version");
        n.g(qsName, "qsName");
        n.g(ceType, "ceType");
        n.g(switchFrom, "switchFrom");
        n.g(tip, "tip");
        this.modelType = modelType;
        this.phoneType = phoneType;
        this.screenType = screenType;
        this.version = version;
        this.beforeValue = i2;
        this.afterValue = i3;
        this.qsName = qsName;
        this.ceType = ceType;
        this.switchFrom = switchFrom;
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
        return this.version;
    }

    public final int component5() {
        return this.beforeValue;
    }

    public final int component6() {
        return this.afterValue;
    }

    public final String component7() {
        return this.qsName;
    }

    public final String component8() {
        return this.ceType;
    }

    public final String component9() {
        return this.switchFrom;
    }

    public final SecondaryVolumeSeekerAdjustEvent copy(String modelType, String phoneType, String screenType, String version, int i2, int i3, String qsName, String ceType, String switchFrom, String tip) {
        n.g(modelType, "modelType");
        n.g(phoneType, "phoneType");
        n.g(screenType, "screenType");
        n.g(version, "version");
        n.g(qsName, "qsName");
        n.g(ceType, "ceType");
        n.g(switchFrom, "switchFrom");
        n.g(tip, "tip");
        return new SecondaryVolumeSeekerAdjustEvent(modelType, phoneType, screenType, version, i2, i3, qsName, ceType, switchFrom, tip);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof SecondaryVolumeSeekerAdjustEvent)) {
            return false;
        }
        SecondaryVolumeSeekerAdjustEvent secondaryVolumeSeekerAdjustEvent = (SecondaryVolumeSeekerAdjustEvent) obj;
        return n.c(this.modelType, secondaryVolumeSeekerAdjustEvent.modelType) && n.c(this.phoneType, secondaryVolumeSeekerAdjustEvent.phoneType) && n.c(this.screenType, secondaryVolumeSeekerAdjustEvent.screenType) && n.c(this.version, secondaryVolumeSeekerAdjustEvent.version) && this.beforeValue == secondaryVolumeSeekerAdjustEvent.beforeValue && this.afterValue == secondaryVolumeSeekerAdjustEvent.afterValue && n.c(this.qsName, secondaryVolumeSeekerAdjustEvent.qsName) && n.c(this.ceType, secondaryVolumeSeekerAdjustEvent.ceType) && n.c(this.switchFrom, secondaryVolumeSeekerAdjustEvent.switchFrom) && n.c(this.tip, secondaryVolumeSeekerAdjustEvent.tip);
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
        return (((((((((((((((((this.modelType.hashCode() * 31) + this.phoneType.hashCode()) * 31) + this.screenType.hashCode()) * 31) + this.version.hashCode()) * 31) + Integer.hashCode(this.beforeValue)) * 31) + Integer.hashCode(this.afterValue)) * 31) + this.qsName.hashCode()) * 31) + this.ceType.hashCode()) * 31) + this.switchFrom.hashCode()) * 31) + this.tip.hashCode();
    }

    public String toString() {
        return "SecondaryVolumeSeekerAdjustEvent(modelType=" + this.modelType + ", phoneType=" + this.phoneType + ", screenType=" + this.screenType + ", version=" + this.version + ", beforeValue=" + this.beforeValue + ", afterValue=" + this.afterValue + ", qsName=" + this.qsName + ", ceType=" + this.ceType + ", switchFrom=" + this.switchFrom + ", tip=" + this.tip + ")";
    }

    public /* synthetic */ SecondaryVolumeSeekerAdjustEvent(String str, String str2, String str3, String str4, int i2, int i3, String str5, String str6, String str7, String str8, int i4, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, str2, str3, str4, i2, i3, str5, str6, str7, (i4 & 512) != 0 ? "178.1.4.1.37248" : str8);
    }
}
