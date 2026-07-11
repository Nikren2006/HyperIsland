package miui.systemui.dynamicisland.events;

import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.n;
import miui.systemui.dynamicisland.events.DynamicIslandEventsConstants;
import systemui.plugin.eventtracking.EventID;
import systemui.plugin.eventtracking.EventKey;
import systemui.plugin.eventtracking.events.DynamicIslandEvent;

/* JADX INFO: loaded from: classes3.dex */
@EventID(id = DynamicIslandEventsConstants.EventID.EVENT_EXPOSE)
public final class DynamicIslandSummaryExpose implements DynamicIslandEvent {

    @EventKey(key = DynamicIslandEventsConstants.EventKey.KEY_APP_NAME)
    private final String appName;

    @EventKey(key = DynamicIslandEventsConstants.EventKey.KEY_CHANNEL_TYPE)
    private final String channelType;

    @EventKey(key = DynamicIslandEventsConstants.EventKey.KEY_DISAPPEAR_TYPE)
    private final String disappearType;

    @EventKey(key = DynamicIslandEventsConstants.EventKey.KEY_EXPOSE_DURATION)
    private final int duration;

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

    public DynamicIslandSummaryExpose(String appName, String pkg, String sbnId, String notifTag, String notifType, String scene, int i2, String disappearType, String islandForm, String channelType, String extraItem, String tip, String phoneType, String screenType) {
        n.g(appName, "appName");
        n.g(pkg, "pkg");
        n.g(sbnId, "sbnId");
        n.g(notifTag, "notifTag");
        n.g(notifType, "notifType");
        n.g(scene, "scene");
        n.g(disappearType, "disappearType");
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
        this.duration = i2;
        this.disappearType = disappearType;
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
        return this.channelType;
    }

    public final String component11() {
        return this.extraItem;
    }

    public final String component12() {
        return this.tip;
    }

    public final String component13() {
        return this.phoneType;
    }

    public final String component14() {
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

    public final int component7() {
        return this.duration;
    }

    public final String component8() {
        return this.disappearType;
    }

    public final String component9() {
        return this.islandForm;
    }

    public final DynamicIslandSummaryExpose copy(String appName, String pkg, String sbnId, String notifTag, String notifType, String scene, int i2, String disappearType, String islandForm, String channelType, String extraItem, String tip, String phoneType, String screenType) {
        n.g(appName, "appName");
        n.g(pkg, "pkg");
        n.g(sbnId, "sbnId");
        n.g(notifTag, "notifTag");
        n.g(notifType, "notifType");
        n.g(scene, "scene");
        n.g(disappearType, "disappearType");
        n.g(islandForm, "islandForm");
        n.g(channelType, "channelType");
        n.g(extraItem, "extraItem");
        n.g(tip, "tip");
        n.g(phoneType, "phoneType");
        n.g(screenType, "screenType");
        return new DynamicIslandSummaryExpose(appName, pkg, sbnId, notifTag, notifType, scene, i2, disappearType, islandForm, channelType, extraItem, tip, phoneType, screenType);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof DynamicIslandSummaryExpose)) {
            return false;
        }
        DynamicIslandSummaryExpose dynamicIslandSummaryExpose = (DynamicIslandSummaryExpose) obj;
        return n.c(this.appName, dynamicIslandSummaryExpose.appName) && n.c(this.pkg, dynamicIslandSummaryExpose.pkg) && n.c(this.sbnId, dynamicIslandSummaryExpose.sbnId) && n.c(this.notifTag, dynamicIslandSummaryExpose.notifTag) && n.c(this.notifType, dynamicIslandSummaryExpose.notifType) && n.c(this.scene, dynamicIslandSummaryExpose.scene) && this.duration == dynamicIslandSummaryExpose.duration && n.c(this.disappearType, dynamicIslandSummaryExpose.disappearType) && n.c(this.islandForm, dynamicIslandSummaryExpose.islandForm) && n.c(this.channelType, dynamicIslandSummaryExpose.channelType) && n.c(this.extraItem, dynamicIslandSummaryExpose.extraItem) && n.c(this.tip, dynamicIslandSummaryExpose.tip) && n.c(this.phoneType, dynamicIslandSummaryExpose.phoneType) && n.c(this.screenType, dynamicIslandSummaryExpose.screenType);
    }

    public final String getAppName() {
        return this.appName;
    }

    public final String getChannelType() {
        return this.channelType;
    }

    public final String getDisappearType() {
        return this.disappearType;
    }

    public final int getDuration() {
        return this.duration;
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
        return (((((((((((((((((((((((((this.appName.hashCode() * 31) + this.pkg.hashCode()) * 31) + this.sbnId.hashCode()) * 31) + this.notifTag.hashCode()) * 31) + this.notifType.hashCode()) * 31) + this.scene.hashCode()) * 31) + Integer.hashCode(this.duration)) * 31) + this.disappearType.hashCode()) * 31) + this.islandForm.hashCode()) * 31) + this.channelType.hashCode()) * 31) + this.extraItem.hashCode()) * 31) + this.tip.hashCode()) * 31) + this.phoneType.hashCode()) * 31) + this.screenType.hashCode();
    }

    public String toString() {
        return "DynamicIslandSummaryExpose(appName=" + this.appName + ", pkg=" + this.pkg + ", sbnId=" + this.sbnId + ", notifTag=" + this.notifTag + ", notifType=" + this.notifType + ", scene=" + this.scene + ", duration=" + this.duration + ", disappearType=" + this.disappearType + ", islandForm=" + this.islandForm + ", channelType=" + this.channelType + ", extraItem=" + this.extraItem + ", tip=" + this.tip + ", phoneType=" + this.phoneType + ", screenType=" + this.screenType + ")";
    }

    public /* synthetic */ DynamicIslandSummaryExpose(String str, String str2, String str3, String str4, String str5, String str6, int i2, String str7, String str8, String str9, String str10, String str11, String str12, String str13, int i3, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, str2, str3, str4, str5, str6, i2, str7, str8, str9, str10, (i3 & 2048) != 0 ? DynamicIslandEventsConstants.EventTips.TIP_EXPOSE_SUMMARY : str11, str12, str13);
    }
}
