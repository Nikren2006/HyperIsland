package miui.systemui.controlcenter.events;

import java.util.List;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.n;
import miui.systemui.dynamicisland.events.DynamicIslandEventsConstants;
import systemui.plugin.eventtracking.EventID;
import systemui.plugin.eventtracking.EventKey;

/* JADX INFO: loaded from: classes.dex */
@EventID(id = "state_qs_tile")
public final class TilePanelEditEvent {

    @EventKey(key = "abnormal_quick_switch_list")
    private final String abnormalQSList;

    @EventKey(key = "system_qs_tile_added")
    private final int addNumber;

    @EventKey(key = "model_type")
    private final String modelType;

    @EventKey(key = DynamicIslandEventsConstants.PublicParams.PHONE_TYPE)
    private final String phoneType;

    @EventKey(key = "quick_switch_list")
    private final List<String> quickSwitchList;

    @EventKey(key = DynamicIslandEventsConstants.PublicParams.SCREEN_TYPE)
    private final String screenType;

    @EventKey(key = "tip")
    private final String tip;

    @EventKey(key = "control_center_version")
    private final String version;

    public TilePanelEditEvent(String modelType, String phoneType, String screenType, String version, int i2, String abnormalQSList, List<String> quickSwitchList, String tip) {
        n.g(modelType, "modelType");
        n.g(phoneType, "phoneType");
        n.g(screenType, "screenType");
        n.g(version, "version");
        n.g(abnormalQSList, "abnormalQSList");
        n.g(quickSwitchList, "quickSwitchList");
        n.g(tip, "tip");
        this.modelType = modelType;
        this.phoneType = phoneType;
        this.screenType = screenType;
        this.version = version;
        this.addNumber = i2;
        this.abnormalQSList = abnormalQSList;
        this.quickSwitchList = quickSwitchList;
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

    public final int component5() {
        return this.addNumber;
    }

    public final String component6() {
        return this.abnormalQSList;
    }

    public final List<String> component7() {
        return this.quickSwitchList;
    }

    public final String component8() {
        return this.tip;
    }

    public final TilePanelEditEvent copy(String modelType, String phoneType, String screenType, String version, int i2, String abnormalQSList, List<String> quickSwitchList, String tip) {
        n.g(modelType, "modelType");
        n.g(phoneType, "phoneType");
        n.g(screenType, "screenType");
        n.g(version, "version");
        n.g(abnormalQSList, "abnormalQSList");
        n.g(quickSwitchList, "quickSwitchList");
        n.g(tip, "tip");
        return new TilePanelEditEvent(modelType, phoneType, screenType, version, i2, abnormalQSList, quickSwitchList, tip);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof TilePanelEditEvent)) {
            return false;
        }
        TilePanelEditEvent tilePanelEditEvent = (TilePanelEditEvent) obj;
        return n.c(this.modelType, tilePanelEditEvent.modelType) && n.c(this.phoneType, tilePanelEditEvent.phoneType) && n.c(this.screenType, tilePanelEditEvent.screenType) && n.c(this.version, tilePanelEditEvent.version) && this.addNumber == tilePanelEditEvent.addNumber && n.c(this.abnormalQSList, tilePanelEditEvent.abnormalQSList) && n.c(this.quickSwitchList, tilePanelEditEvent.quickSwitchList) && n.c(this.tip, tilePanelEditEvent.tip);
    }

    public final String getAbnormalQSList() {
        return this.abnormalQSList;
    }

    public final int getAddNumber() {
        return this.addNumber;
    }

    public final String getModelType() {
        return this.modelType;
    }

    public final String getPhoneType() {
        return this.phoneType;
    }

    public final List<String> getQuickSwitchList() {
        return this.quickSwitchList;
    }

    public final String getScreenType() {
        return this.screenType;
    }

    public final String getTip() {
        return this.tip;
    }

    public final String getVersion() {
        return this.version;
    }

    public int hashCode() {
        return (((((((((((((this.modelType.hashCode() * 31) + this.phoneType.hashCode()) * 31) + this.screenType.hashCode()) * 31) + this.version.hashCode()) * 31) + Integer.hashCode(this.addNumber)) * 31) + this.abnormalQSList.hashCode()) * 31) + this.quickSwitchList.hashCode()) * 31) + this.tip.hashCode();
    }

    public String toString() {
        return "TilePanelEditEvent(modelType=" + this.modelType + ", phoneType=" + this.phoneType + ", screenType=" + this.screenType + ", version=" + this.version + ", addNumber=" + this.addNumber + ", abnormalQSList=" + this.abnormalQSList + ", quickSwitchList=" + this.quickSwitchList + ", tip=" + this.tip + ")";
    }

    public /* synthetic */ TilePanelEditEvent(String str, String str2, String str3, String str4, int i2, String str5, List list, String str6, int i3, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, str2, str3, str4, i2, str5, list, (i3 & 128) != 0 ? "178.1.0.1.18789" : str6);
    }
}
