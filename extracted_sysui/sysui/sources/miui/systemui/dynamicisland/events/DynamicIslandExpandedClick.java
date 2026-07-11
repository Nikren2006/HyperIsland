package miui.systemui.dynamicisland.events;

import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.n;
import miui.systemui.dynamicisland.events.DynamicIslandEventsConstants;
import systemui.plugin.eventtracking.EventID;
import systemui.plugin.eventtracking.EventKey;
import systemui.plugin.eventtracking.events.DynamicIslandEvent;

/* JADX INFO: loaded from: classes3.dex */
@EventID(id = "click")
public final class DynamicIslandExpandedClick implements DynamicIslandEvent {

    @EventKey(key = DynamicIslandEventsConstants.EventKey.KEY_APP_NAME)
    private final String appName;

    @EventKey(key = DynamicIslandEventsConstants.EventKey.KEY_CHANNEL_TYPE)
    private final String channelType;

    @EventKey(key = DynamicIslandEventsConstants.EventKey.KEY_ELEMENT_NAME)
    private final String element;

    @EventKey(key = DynamicIslandEventsConstants.EventKey.KEY_EXPAND_TYPE)
    private final String expandedType;

    @EventKey(key = "item")
    private final String extraItem;

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

    public DynamicIslandExpandedClick(String appName, String pkg, String sbnId, String notifTag, String notifType, String scene, String expandedType, String element, String channelType, String extraItem, String tip, String phoneType, String screenType) {
        n.g(appName, "appName");
        n.g(pkg, "pkg");
        n.g(sbnId, "sbnId");
        n.g(notifTag, "notifTag");
        n.g(notifType, "notifType");
        n.g(scene, "scene");
        n.g(expandedType, "expandedType");
        n.g(element, "element");
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
        this.expandedType = expandedType;
        this.element = element;
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
        return this.extraItem;
    }

    public final String component11() {
        return this.tip;
    }

    public final String component12() {
        return this.phoneType;
    }

    public final String component13() {
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
        return this.expandedType;
    }

    public final String component8() {
        return this.element;
    }

    public final String component9() {
        return this.channelType;
    }

    public final DynamicIslandExpandedClick copy(String appName, String pkg, String sbnId, String notifTag, String notifType, String scene, String expandedType, String element, String channelType, String extraItem, String tip, String phoneType, String screenType) {
        n.g(appName, "appName");
        n.g(pkg, "pkg");
        n.g(sbnId, "sbnId");
        n.g(notifTag, "notifTag");
        n.g(notifType, "notifType");
        n.g(scene, "scene");
        n.g(expandedType, "expandedType");
        n.g(element, "element");
        n.g(channelType, "channelType");
        n.g(extraItem, "extraItem");
        n.g(tip, "tip");
        n.g(phoneType, "phoneType");
        n.g(screenType, "screenType");
        return new DynamicIslandExpandedClick(appName, pkg, sbnId, notifTag, notifType, scene, expandedType, element, channelType, extraItem, tip, phoneType, screenType);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof DynamicIslandExpandedClick)) {
            return false;
        }
        DynamicIslandExpandedClick dynamicIslandExpandedClick = (DynamicIslandExpandedClick) obj;
        return n.c(this.appName, dynamicIslandExpandedClick.appName) && n.c(this.pkg, dynamicIslandExpandedClick.pkg) && n.c(this.sbnId, dynamicIslandExpandedClick.sbnId) && n.c(this.notifTag, dynamicIslandExpandedClick.notifTag) && n.c(this.notifType, dynamicIslandExpandedClick.notifType) && n.c(this.scene, dynamicIslandExpandedClick.scene) && n.c(this.expandedType, dynamicIslandExpandedClick.expandedType) && n.c(this.element, dynamicIslandExpandedClick.element) && n.c(this.channelType, dynamicIslandExpandedClick.channelType) && n.c(this.extraItem, dynamicIslandExpandedClick.extraItem) && n.c(this.tip, dynamicIslandExpandedClick.tip) && n.c(this.phoneType, dynamicIslandExpandedClick.phoneType) && n.c(this.screenType, dynamicIslandExpandedClick.screenType);
    }

    public final String getAppName() {
        return this.appName;
    }

    public final String getChannelType() {
        return this.channelType;
    }

    public final String getElement() {
        return this.element;
    }

    public final String getExpandedType() {
        return this.expandedType;
    }

    public final String getExtraItem() {
        return this.extraItem;
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
        return (((((((((((((((((((((((this.appName.hashCode() * 31) + this.pkg.hashCode()) * 31) + this.sbnId.hashCode()) * 31) + this.notifTag.hashCode()) * 31) + this.notifType.hashCode()) * 31) + this.scene.hashCode()) * 31) + this.expandedType.hashCode()) * 31) + this.element.hashCode()) * 31) + this.channelType.hashCode()) * 31) + this.extraItem.hashCode()) * 31) + this.tip.hashCode()) * 31) + this.phoneType.hashCode()) * 31) + this.screenType.hashCode();
    }

    public String toString() {
        return "DynamicIslandExpandedClick(appName=" + this.appName + ", pkg=" + this.pkg + ", sbnId=" + this.sbnId + ", notifTag=" + this.notifTag + ", notifType=" + this.notifType + ", scene=" + this.scene + ", expandedType=" + this.expandedType + ", element=" + this.element + ", channelType=" + this.channelType + ", extraItem=" + this.extraItem + ", tip=" + this.tip + ", phoneType=" + this.phoneType + ", screenType=" + this.screenType + ")";
    }

    public /* synthetic */ DynamicIslandExpandedClick(String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8, String str9, String str10, String str11, String str12, String str13, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, str2, str3, str4, str5, str6, str7, str8, str9, str10, (i2 & 1024) != 0 ? DynamicIslandEventsConstants.EventTips.TIP_CLICK_EXPANDED : str11, str12, str13);
    }
}
