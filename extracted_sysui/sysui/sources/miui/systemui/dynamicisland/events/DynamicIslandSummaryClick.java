package miui.systemui.dynamicisland.events;

import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.n;
import miui.systemui.dynamicisland.events.DynamicIslandEventsConstants;
import systemui.plugin.eventtracking.EventID;
import systemui.plugin.eventtracking.EventKey;
import systemui.plugin.eventtracking.events.DynamicIslandEvent;

/* JADX INFO: loaded from: classes3.dex */
@EventID(id = "click")
public final class DynamicIslandSummaryClick implements DynamicIslandEvent {

    @EventKey(key = DynamicIslandEventsConstants.EventKey.KEY_APP_NAME)
    private final String appName;

    @EventKey(key = DynamicIslandEventsConstants.EventKey.KEY_CHANNEL_TYPE)
    private final String channelType;

    @EventKey(key = "item")
    private final String extraItem;

    @EventKey(key = DynamicIslandEventsConstants.EventKey.KEY_ISLAND_FORM)
    private final String islandForm;

    @EventKey(key = DynamicIslandEventsConstants.EventKey.KEY_NOTIFICATION_TAG)
    private final String notifTag;

    @EventKey(key = DynamicIslandEventsConstants.EventKey.KEY_NOTIFICATION_TYPE)
    private final String notifType;

    @EventKey(key = DynamicIslandEventsConstants.PublicParams.PHONE_TYPE)
    private final String phoneType;

    @EventKey(key = DynamicIslandEventsConstants.EventKey.KEY_SEND_PKG)
    private final String pkg;

    @EventKey(key = DynamicIslandEventsConstants.EventKey.KEY_SBN_ID)
    private final String sbnId;

    @EventKey(key = DynamicIslandEventsConstants.EventKey.KEY_SERVICE_SCENE)
    private final String scene;

    @EventKey(key = DynamicIslandEventsConstants.PublicParams.SCREEN_TYPE)
    private final String screenType;

    @EventKey(key = "tip")
    private final String tip;

    public DynamicIslandSummaryClick(String appName, String pkg, String sbnId, String notifTag, String notifType, String scene, String islandForm, String channelType, String extraItem, String tip, String phoneType, String screenType) {
        n.g(appName, "appName");
        n.g(pkg, "pkg");
        n.g(sbnId, "sbnId");
        n.g(notifTag, "notifTag");
        n.g(notifType, "notifType");
        n.g(scene, "scene");
        n.g(islandForm, "islandForm");
        n.g(channelType, "channelType");
        n.g(extraItem, "extraItem");
        n.g(tip, "tip");
        n.g(phoneType, "phoneType");
        n.g(screenType, "screenType");
        this.appName = appName;
        this.pkg = pkg;
        this.sbnId = sbnId;
        this.notifTag = notifTag;
        this.notifType = notifType;
        this.scene = scene;
        this.islandForm = islandForm;
        this.channelType = channelType;
        this.extraItem = extraItem;
        this.tip = tip;
        this.phoneType = phoneType;
        this.screenType = screenType;
    }

    public final String component1() {
        return this.appName;
    }

    public final String component10() {
        return this.tip;
    }

    public final String component11() {
        return this.phoneType;
    }

    public final String component12() {
        return this.screenType;
    }

    public final String component2() {
        return this.pkg;
    }

    public final String component3() {
        return this.sbnId;
    }

    public final String component4() {
        return this.notifTag;
    }

    public final String component5() {
        return this.notifType;
    }

    public final String component6() {
        return this.scene;
    }

    public final String component7() {
        return this.islandForm;
    }

    public final String component8() {
        return this.channelType;
    }

    public final String component9() {
        return this.extraItem;
    }

    public final DynamicIslandSummaryClick copy(String appName, String pkg, String sbnId, String notifTag, String notifType, String scene, String islandForm, String channelType, String extraItem, String tip, String phoneType, String screenType) {
        n.g(appName, "appName");
        n.g(pkg, "pkg");
        n.g(sbnId, "sbnId");
        n.g(notifTag, "notifTag");
        n.g(notifType, "notifType");
        n.g(scene, "scene");
        n.g(islandForm, "islandForm");
        n.g(channelType, "channelType");
        n.g(extraItem, "extraItem");
        n.g(tip, "tip");
        n.g(phoneType, "phoneType");
        n.g(screenType, "screenType");
        return new DynamicIslandSummaryClick(appName, pkg, sbnId, notifTag, notifType, scene, islandForm, channelType, extraItem, tip, phoneType, screenType);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof DynamicIslandSummaryClick)) {
            return false;
        }
        DynamicIslandSummaryClick dynamicIslandSummaryClick = (DynamicIslandSummaryClick) obj;
        return n.c(this.appName, dynamicIslandSummaryClick.appName) && n.c(this.pkg, dynamicIslandSummaryClick.pkg) && n.c(this.sbnId, dynamicIslandSummaryClick.sbnId) && n.c(this.notifTag, dynamicIslandSummaryClick.notifTag) && n.c(this.notifType, dynamicIslandSummaryClick.notifType) && n.c(this.scene, dynamicIslandSummaryClick.scene) && n.c(this.islandForm, dynamicIslandSummaryClick.islandForm) && n.c(this.channelType, dynamicIslandSummaryClick.channelType) && n.c(this.extraItem, dynamicIslandSummaryClick.extraItem) && n.c(this.tip, dynamicIslandSummaryClick.tip) && n.c(this.phoneType, dynamicIslandSummaryClick.phoneType) && n.c(this.screenType, dynamicIslandSummaryClick.screenType);
    }

    public final String getAppName() {
        return this.appName;
    }

    public final String getChannelType() {
        return this.channelType;
    }

    public final String getExtraItem() {
        return this.extraItem;
    }

    public final String getIslandForm() {
        return this.islandForm;
    }

    public final String getNotifTag() {
        return this.notifTag;
    }

    public final String getNotifType() {
        return this.notifType;
    }

    public final String getPhoneType() {
        return this.phoneType;
    }

    public final String getPkg() {
        return this.pkg;
    }

    public final String getSbnId() {
        return this.sbnId;
    }

    public final String getScene() {
        return this.scene;
    }

    public final String getScreenType() {
        return this.screenType;
    }

    public final String getTip() {
        return this.tip;
    }

    public int hashCode() {
        return (((((((((((((((((((((this.appName.hashCode() * 31) + this.pkg.hashCode()) * 31) + this.sbnId.hashCode()) * 31) + this.notifTag.hashCode()) * 31) + this.notifType.hashCode()) * 31) + this.scene.hashCode()) * 31) + this.islandForm.hashCode()) * 31) + this.channelType.hashCode()) * 31) + this.extraItem.hashCode()) * 31) + this.tip.hashCode()) * 31) + this.phoneType.hashCode()) * 31) + this.screenType.hashCode();
    }

    public String toString() {
        return "DynamicIslandSummaryClick(appName=" + this.appName + ", pkg=" + this.pkg + ", sbnId=" + this.sbnId + ", notifTag=" + this.notifTag + ", notifType=" + this.notifType + ", scene=" + this.scene + ", islandForm=" + this.islandForm + ", channelType=" + this.channelType + ", extraItem=" + this.extraItem + ", tip=" + this.tip + ", phoneType=" + this.phoneType + ", screenType=" + this.screenType + ")";
    }

    public /* synthetic */ DynamicIslandSummaryClick(String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8, String str9, String str10, String str11, String str12, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, str2, str3, str4, str5, str6, str7, str8, str9, (i2 & 512) != 0 ? DynamicIslandEventsConstants.EventTips.TIP_CLICK_SUMMARY : str10, str11, str12);
    }
}
