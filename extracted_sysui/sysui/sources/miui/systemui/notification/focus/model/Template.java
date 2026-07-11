package miui.systemui.notification.focus.model;

import java.util.List;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.n;

/* JADX INFO: loaded from: classes4.dex */
public final class Template {
    private final List<ActionInfo> actions;
    private final AnimTextInfo animTextInfo;
    private final String aodPic;
    private final String aodTitle;
    private final BaseInfo baseInfo;
    private final BgInfo bgInfo;
    private String business;
    private boolean cancel;
    private final ChatInfo chatInfo;
    private final CoverInfo coverInfo;
    private final boolean enableFloat;
    private boolean hideDeco;
    private final HighlightInfo highlightInfo;
    private final HighlightInfoV3 highlightInfoV3;
    private final HintInfo hintInfo;
    private final IconTextInfo iconTextInfo;
    private Boolean isShowNotification;
    private Boolean islandFirstFloat;
    private final MultiProgressInfo multiProgressInfo;
    private String notifyId;
    private String orderId;
    private String outEffectColor;
    private String outEffectSrc;
    private final PicInfo picInfo;
    private ProgressInfo progressInfo;
    private String reopen;
    private long sequence;
    private final boolean showSmallIcon;
    private List<ActionInfo> textButton;
    private final String ticker;
    private final String tickerPic;
    private final String tickerPicDark;
    private final int timeout;
    private final boolean updatable;

    public Template(boolean z2, boolean z3, String str, String aodTitle, String aodPic, String ticker, String tickerPic, String tickerPicDark, int i2, boolean z4, BaseInfo baseInfo, ChatInfo chatInfo, PicInfo picInfo, HighlightInfo highlightInfo, HighlightInfoV3 highlightInfoV3, ProgressInfo progressInfo, HintInfo hintInfo, List<ActionInfo> list, BgInfo bgInfo, AnimTextInfo animTextInfo, CoverInfo coverInfo, IconTextInfo iconTextInfo, MultiProgressInfo multiProgressInfo, List<ActionInfo> list2, Boolean bool, String notifyId, String business, Boolean bool2, boolean z5, boolean z6, String outEffectSrc, String outEffectColor, String orderId, long j2) {
        n.g(aodTitle, "aodTitle");
        n.g(aodPic, "aodPic");
        n.g(ticker, "ticker");
        n.g(tickerPic, "tickerPic");
        n.g(tickerPicDark, "tickerPicDark");
        n.g(notifyId, "notifyId");
        n.g(business, "business");
        n.g(outEffectSrc, "outEffectSrc");
        n.g(outEffectColor, "outEffectColor");
        n.g(orderId, "orderId");
        this.enableFloat = z2;
        this.updatable = z3;
        this.reopen = str;
        this.aodTitle = aodTitle;
        this.aodPic = aodPic;
        this.ticker = ticker;
        this.tickerPic = tickerPic;
        this.tickerPicDark = tickerPicDark;
        this.timeout = i2;
        this.showSmallIcon = z4;
        this.baseInfo = baseInfo;
        this.chatInfo = chatInfo;
        this.picInfo = picInfo;
        this.highlightInfo = highlightInfo;
        this.highlightInfoV3 = highlightInfoV3;
        this.progressInfo = progressInfo;
        this.hintInfo = hintInfo;
        this.actions = list;
        this.bgInfo = bgInfo;
        this.animTextInfo = animTextInfo;
        this.coverInfo = coverInfo;
        this.iconTextInfo = iconTextInfo;
        this.multiProgressInfo = multiProgressInfo;
        this.textButton = list2;
        this.isShowNotification = bool;
        this.notifyId = notifyId;
        this.business = business;
        this.islandFirstFloat = bool2;
        this.cancel = z5;
        this.hideDeco = z6;
        this.outEffectSrc = outEffectSrc;
        this.outEffectColor = outEffectColor;
        this.orderId = orderId;
        this.sequence = j2;
    }

    public final boolean component1() {
        return this.enableFloat;
    }

    public final boolean component10() {
        return this.showSmallIcon;
    }

    public final BaseInfo component11() {
        return this.baseInfo;
    }

    public final ChatInfo component12() {
        return this.chatInfo;
    }

    public final PicInfo component13() {
        return this.picInfo;
    }

    public final HighlightInfo component14() {
        return this.highlightInfo;
    }

    public final HighlightInfoV3 component15() {
        return this.highlightInfoV3;
    }

    public final ProgressInfo component16() {
        return this.progressInfo;
    }

    public final HintInfo component17() {
        return this.hintInfo;
    }

    public final List<ActionInfo> component18() {
        return this.actions;
    }

    public final BgInfo component19() {
        return this.bgInfo;
    }

    public final boolean component2() {
        return this.updatable;
    }

    public final AnimTextInfo component20() {
        return this.animTextInfo;
    }

    public final CoverInfo component21() {
        return this.coverInfo;
    }

    public final IconTextInfo component22() {
        return this.iconTextInfo;
    }

    public final MultiProgressInfo component23() {
        return this.multiProgressInfo;
    }

    public final List<ActionInfo> component24() {
        return this.textButton;
    }

    public final Boolean component25() {
        return this.isShowNotification;
    }

    public final String component26() {
        return this.notifyId;
    }

    public final String component27() {
        return this.business;
    }

    public final Boolean component28() {
        return this.islandFirstFloat;
    }

    public final boolean component29() {
        return this.cancel;
    }

    public final String component3() {
        return this.reopen;
    }

    public final boolean component30() {
        return this.hideDeco;
    }

    public final String component31() {
        return this.outEffectSrc;
    }

    public final String component32() {
        return this.outEffectColor;
    }

    public final String component33() {
        return this.orderId;
    }

    public final long component34() {
        return this.sequence;
    }

    public final String component4() {
        return this.aodTitle;
    }

    public final String component5() {
        return this.aodPic;
    }

    public final String component6() {
        return this.ticker;
    }

    public final String component7() {
        return this.tickerPic;
    }

    public final String component8() {
        return this.tickerPicDark;
    }

    public final int component9() {
        return this.timeout;
    }

    public final Template copy(boolean z2, boolean z3, String str, String aodTitle, String aodPic, String ticker, String tickerPic, String tickerPicDark, int i2, boolean z4, BaseInfo baseInfo, ChatInfo chatInfo, PicInfo picInfo, HighlightInfo highlightInfo, HighlightInfoV3 highlightInfoV3, ProgressInfo progressInfo, HintInfo hintInfo, List<ActionInfo> list, BgInfo bgInfo, AnimTextInfo animTextInfo, CoverInfo coverInfo, IconTextInfo iconTextInfo, MultiProgressInfo multiProgressInfo, List<ActionInfo> list2, Boolean bool, String notifyId, String business, Boolean bool2, boolean z5, boolean z6, String outEffectSrc, String outEffectColor, String orderId, long j2) {
        n.g(aodTitle, "aodTitle");
        n.g(aodPic, "aodPic");
        n.g(ticker, "ticker");
        n.g(tickerPic, "tickerPic");
        n.g(tickerPicDark, "tickerPicDark");
        n.g(notifyId, "notifyId");
        n.g(business, "business");
        n.g(outEffectSrc, "outEffectSrc");
        n.g(outEffectColor, "outEffectColor");
        n.g(orderId, "orderId");
        return new Template(z2, z3, str, aodTitle, aodPic, ticker, tickerPic, tickerPicDark, i2, z4, baseInfo, chatInfo, picInfo, highlightInfo, highlightInfoV3, progressInfo, hintInfo, list, bgInfo, animTextInfo, coverInfo, iconTextInfo, multiProgressInfo, list2, bool, notifyId, business, bool2, z5, z6, outEffectSrc, outEffectColor, orderId, j2);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Template)) {
            return false;
        }
        Template template = (Template) obj;
        return this.enableFloat == template.enableFloat && this.updatable == template.updatable && n.c(this.reopen, template.reopen) && n.c(this.aodTitle, template.aodTitle) && n.c(this.aodPic, template.aodPic) && n.c(this.ticker, template.ticker) && n.c(this.tickerPic, template.tickerPic) && n.c(this.tickerPicDark, template.tickerPicDark) && this.timeout == template.timeout && this.showSmallIcon == template.showSmallIcon && n.c(this.baseInfo, template.baseInfo) && n.c(this.chatInfo, template.chatInfo) && n.c(this.picInfo, template.picInfo) && n.c(this.highlightInfo, template.highlightInfo) && n.c(this.highlightInfoV3, template.highlightInfoV3) && n.c(this.progressInfo, template.progressInfo) && n.c(this.hintInfo, template.hintInfo) && n.c(this.actions, template.actions) && n.c(this.bgInfo, template.bgInfo) && n.c(this.animTextInfo, template.animTextInfo) && n.c(this.coverInfo, template.coverInfo) && n.c(this.iconTextInfo, template.iconTextInfo) && n.c(this.multiProgressInfo, template.multiProgressInfo) && n.c(this.textButton, template.textButton) && n.c(this.isShowNotification, template.isShowNotification) && n.c(this.notifyId, template.notifyId) && n.c(this.business, template.business) && n.c(this.islandFirstFloat, template.islandFirstFloat) && this.cancel == template.cancel && this.hideDeco == template.hideDeco && n.c(this.outEffectSrc, template.outEffectSrc) && n.c(this.outEffectColor, template.outEffectColor) && n.c(this.orderId, template.orderId) && this.sequence == template.sequence;
    }

    public final List<ActionInfo> getActions() {
        return this.actions;
    }

    public final AnimTextInfo getAnimTextInfo() {
        return this.animTextInfo;
    }

    public final String getAodPic() {
        return this.aodPic;
    }

    public final String getAodTitle() {
        return this.aodTitle;
    }

    public final BaseInfo getBaseInfo() {
        return this.baseInfo;
    }

    public final BgInfo getBgInfo() {
        return this.bgInfo;
    }

    public final String getBusiness() {
        return this.business;
    }

    public final boolean getCancel() {
        return this.cancel;
    }

    public final ChatInfo getChatInfo() {
        return this.chatInfo;
    }

    public final CoverInfo getCoverInfo() {
        return this.coverInfo;
    }

    public final boolean getEnableFloat() {
        return this.enableFloat;
    }

    public final boolean getHideDeco() {
        return this.hideDeco;
    }

    public final HighlightInfo getHighlightInfo() {
        return this.highlightInfo;
    }

    public final HighlightInfoV3 getHighlightInfoV3() {
        return this.highlightInfoV3;
    }

    public final HintInfo getHintInfo() {
        return this.hintInfo;
    }

    public final IconTextInfo getIconTextInfo() {
        return this.iconTextInfo;
    }

    public final Boolean getIslandFirstFloat() {
        return this.islandFirstFloat;
    }

    public final MultiProgressInfo getMultiProgressInfo() {
        return this.multiProgressInfo;
    }

    public final String getNotifyId() {
        return this.notifyId;
    }

    public final String getOrderId() {
        return this.orderId;
    }

    public final String getOutEffectColor() {
        return this.outEffectColor;
    }

    public final String getOutEffectSrc() {
        return this.outEffectSrc;
    }

    public final PicInfo getPicInfo() {
        return this.picInfo;
    }

    public final ProgressInfo getProgressInfo() {
        return this.progressInfo;
    }

    public final String getReopen() {
        return this.reopen;
    }

    public final long getSequence() {
        return this.sequence;
    }

    public final boolean getShowSmallIcon() {
        return this.showSmallIcon;
    }

    public final List<ActionInfo> getTextButton() {
        return this.textButton;
    }

    public final String getTicker() {
        return this.ticker;
    }

    public final String getTickerPic() {
        return this.tickerPic;
    }

    public final String getTickerPicDark() {
        return this.tickerPicDark;
    }

    public final int getTimeout() {
        return this.timeout;
    }

    public final boolean getUpdatable() {
        return this.updatable;
    }

    public int hashCode() {
        int iHashCode = ((Boolean.hashCode(this.enableFloat) * 31) + Boolean.hashCode(this.updatable)) * 31;
        String str = this.reopen;
        int iHashCode2 = (((((((((((((((iHashCode + (str == null ? 0 : str.hashCode())) * 31) + this.aodTitle.hashCode()) * 31) + this.aodPic.hashCode()) * 31) + this.ticker.hashCode()) * 31) + this.tickerPic.hashCode()) * 31) + this.tickerPicDark.hashCode()) * 31) + Integer.hashCode(this.timeout)) * 31) + Boolean.hashCode(this.showSmallIcon)) * 31;
        BaseInfo baseInfo = this.baseInfo;
        int iHashCode3 = (iHashCode2 + (baseInfo == null ? 0 : baseInfo.hashCode())) * 31;
        ChatInfo chatInfo = this.chatInfo;
        int iHashCode4 = (iHashCode3 + (chatInfo == null ? 0 : chatInfo.hashCode())) * 31;
        PicInfo picInfo = this.picInfo;
        int iHashCode5 = (iHashCode4 + (picInfo == null ? 0 : picInfo.hashCode())) * 31;
        HighlightInfo highlightInfo = this.highlightInfo;
        int iHashCode6 = (iHashCode5 + (highlightInfo == null ? 0 : highlightInfo.hashCode())) * 31;
        HighlightInfoV3 highlightInfoV3 = this.highlightInfoV3;
        int iHashCode7 = (iHashCode6 + (highlightInfoV3 == null ? 0 : highlightInfoV3.hashCode())) * 31;
        ProgressInfo progressInfo = this.progressInfo;
        int iHashCode8 = (iHashCode7 + (progressInfo == null ? 0 : progressInfo.hashCode())) * 31;
        HintInfo hintInfo = this.hintInfo;
        int iHashCode9 = (iHashCode8 + (hintInfo == null ? 0 : hintInfo.hashCode())) * 31;
        List<ActionInfo> list = this.actions;
        int iHashCode10 = (iHashCode9 + (list == null ? 0 : list.hashCode())) * 31;
        BgInfo bgInfo = this.bgInfo;
        int iHashCode11 = (iHashCode10 + (bgInfo == null ? 0 : bgInfo.hashCode())) * 31;
        AnimTextInfo animTextInfo = this.animTextInfo;
        int iHashCode12 = (iHashCode11 + (animTextInfo == null ? 0 : animTextInfo.hashCode())) * 31;
        CoverInfo coverInfo = this.coverInfo;
        int iHashCode13 = (iHashCode12 + (coverInfo == null ? 0 : coverInfo.hashCode())) * 31;
        IconTextInfo iconTextInfo = this.iconTextInfo;
        int iHashCode14 = (iHashCode13 + (iconTextInfo == null ? 0 : iconTextInfo.hashCode())) * 31;
        MultiProgressInfo multiProgressInfo = this.multiProgressInfo;
        int iHashCode15 = (iHashCode14 + (multiProgressInfo == null ? 0 : multiProgressInfo.hashCode())) * 31;
        List<ActionInfo> list2 = this.textButton;
        int iHashCode16 = (iHashCode15 + (list2 == null ? 0 : list2.hashCode())) * 31;
        Boolean bool = this.isShowNotification;
        int iHashCode17 = (((((iHashCode16 + (bool == null ? 0 : bool.hashCode())) * 31) + this.notifyId.hashCode()) * 31) + this.business.hashCode()) * 31;
        Boolean bool2 = this.islandFirstFloat;
        return ((((((((((((iHashCode17 + (bool2 != null ? bool2.hashCode() : 0)) * 31) + Boolean.hashCode(this.cancel)) * 31) + Boolean.hashCode(this.hideDeco)) * 31) + this.outEffectSrc.hashCode()) * 31) + this.outEffectColor.hashCode()) * 31) + this.orderId.hashCode()) * 31) + Long.hashCode(this.sequence);
    }

    public final Boolean isShowNotification() {
        return this.isShowNotification;
    }

    public final void setBusiness(String str) {
        n.g(str, "<set-?>");
        this.business = str;
    }

    public final void setCancel(boolean z2) {
        this.cancel = z2;
    }

    public final void setHideDeco(boolean z2) {
        this.hideDeco = z2;
    }

    public final void setIslandFirstFloat(Boolean bool) {
        this.islandFirstFloat = bool;
    }

    public final void setNotifyId(String str) {
        n.g(str, "<set-?>");
        this.notifyId = str;
    }

    public final void setOrderId(String str) {
        n.g(str, "<set-?>");
        this.orderId = str;
    }

    public final void setOutEffectColor(String str) {
        n.g(str, "<set-?>");
        this.outEffectColor = str;
    }

    public final void setOutEffectSrc(String str) {
        n.g(str, "<set-?>");
        this.outEffectSrc = str;
    }

    public final void setProgressInfo(ProgressInfo progressInfo) {
        this.progressInfo = progressInfo;
    }

    public final void setReopen(String str) {
        this.reopen = str;
    }

    public final void setSequence(long j2) {
        this.sequence = j2;
    }

    public final void setShowNotification(Boolean bool) {
        this.isShowNotification = bool;
    }

    public final void setTextButton(List<ActionInfo> list) {
        this.textButton = list;
    }

    public String toString() {
        return "Template(enableFloat=" + this.enableFloat + ", updatable=" + this.updatable + ", reopen=" + this.reopen + ", aodTitle=" + this.aodTitle + ", aodPic=" + this.aodPic + ", ticker=" + this.ticker + ", tickerPic=" + this.tickerPic + ", tickerPicDark=" + this.tickerPicDark + ", timeout=" + this.timeout + ", showSmallIcon=" + this.showSmallIcon + ", baseInfo=" + this.baseInfo + ", chatInfo=" + this.chatInfo + ", picInfo=" + this.picInfo + ", highlightInfo=" + this.highlightInfo + ", highlightInfoV3=" + this.highlightInfoV3 + ", progressInfo=" + this.progressInfo + ", hintInfo=" + this.hintInfo + ", actions=" + this.actions + ", bgInfo=" + this.bgInfo + ", animTextInfo=" + this.animTextInfo + ", coverInfo=" + this.coverInfo + ", iconTextInfo=" + this.iconTextInfo + ", multiProgressInfo=" + this.multiProgressInfo + ", textButton=" + this.textButton + ", isShowNotification=" + this.isShowNotification + ", notifyId=" + this.notifyId + ", business=" + this.business + ", islandFirstFloat=" + this.islandFirstFloat + ", cancel=" + this.cancel + ", hideDeco=" + this.hideDeco + ", outEffectSrc=" + this.outEffectSrc + ", outEffectColor=" + this.outEffectColor + ", orderId=" + this.orderId + ", sequence=" + this.sequence + ")";
    }

    public /* synthetic */ Template(boolean z2, boolean z3, String str, String str2, String str3, String str4, String str5, String str6, int i2, boolean z4, BaseInfo baseInfo, ChatInfo chatInfo, PicInfo picInfo, HighlightInfo highlightInfo, HighlightInfoV3 highlightInfoV3, ProgressInfo progressInfo, HintInfo hintInfo, List list, BgInfo bgInfo, AnimTextInfo animTextInfo, CoverInfo coverInfo, IconTextInfo iconTextInfo, MultiProgressInfo multiProgressInfo, List list2, Boolean bool, String str7, String str8, Boolean bool2, boolean z5, boolean z6, String str9, String str10, String str11, long j2, int i3, int i4, DefaultConstructorMarker defaultConstructorMarker) {
        this(z2, z3, str, str2, str3, str4, str5, str6, i2, z4, baseInfo, chatInfo, picInfo, highlightInfo, highlightInfoV3, progressInfo, hintInfo, list, bgInfo, animTextInfo, coverInfo, iconTextInfo, multiProgressInfo, (i3 & 8388608) != 0 ? null : list2, bool, str7, str8, bool2, z5, z6, str9, str10, str11, j2);
    }
}
