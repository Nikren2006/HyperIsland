package miui.systemui.dynamicisland.model;

import kotlin.jvm.internal.n;

/* JADX INFO: loaded from: classes3.dex */
public final class IslandTemplate {
    private final BigIslandArea bigIslandArea;
    private final String business;
    private final boolean dismissIsland;
    private final int expandedTime;
    private String highlightColor;
    private boolean islandOrder;
    private Integer islandPriority;
    private Integer islandProperty;
    private final int islandTimeout;
    private final Boolean maxSize;
    private Boolean needCloseAnimation;
    private final ShareData shareData;
    private final SmallIslandArea smallIslandArea;

    public IslandTemplate(BigIslandArea bigIslandArea, SmallIslandArea smallIslandArea, ShareData shareData, String str, boolean z2, int i2, String str2, Integer num, Integer num2, boolean z3, Boolean bool, int i3, Boolean bool2) {
        this.bigIslandArea = bigIslandArea;
        this.smallIslandArea = smallIslandArea;
        this.shareData = shareData;
        this.business = str;
        this.dismissIsland = z2;
        this.islandTimeout = i2;
        this.highlightColor = str2;
        this.islandProperty = num;
        this.islandPriority = num2;
        this.islandOrder = z3;
        this.needCloseAnimation = bool;
        this.expandedTime = i3;
        this.maxSize = bool2;
    }

    public final BigIslandArea component1() {
        return this.bigIslandArea;
    }

    public final boolean component10() {
        return this.islandOrder;
    }

    public final Boolean component11() {
        return this.needCloseAnimation;
    }

    public final int component12() {
        return this.expandedTime;
    }

    public final Boolean component13() {
        return this.maxSize;
    }

    public final SmallIslandArea component2() {
        return this.smallIslandArea;
    }

    public final ShareData component3() {
        return this.shareData;
    }

    public final String component4() {
        return this.business;
    }

    public final boolean component5() {
        return this.dismissIsland;
    }

    public final int component6() {
        return this.islandTimeout;
    }

    public final String component7() {
        return this.highlightColor;
    }

    public final Integer component8() {
        return this.islandProperty;
    }

    public final Integer component9() {
        return this.islandPriority;
    }

    public final IslandTemplate copy(BigIslandArea bigIslandArea, SmallIslandArea smallIslandArea, ShareData shareData, String str, boolean z2, int i2, String str2, Integer num, Integer num2, boolean z3, Boolean bool, int i3, Boolean bool2) {
        return new IslandTemplate(bigIslandArea, smallIslandArea, shareData, str, z2, i2, str2, num, num2, z3, bool, i3, bool2);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof IslandTemplate)) {
            return false;
        }
        IslandTemplate islandTemplate = (IslandTemplate) obj;
        return n.c(this.bigIslandArea, islandTemplate.bigIslandArea) && n.c(this.smallIslandArea, islandTemplate.smallIslandArea) && n.c(this.shareData, islandTemplate.shareData) && n.c(this.business, islandTemplate.business) && this.dismissIsland == islandTemplate.dismissIsland && this.islandTimeout == islandTemplate.islandTimeout && n.c(this.highlightColor, islandTemplate.highlightColor) && n.c(this.islandProperty, islandTemplate.islandProperty) && n.c(this.islandPriority, islandTemplate.islandPriority) && this.islandOrder == islandTemplate.islandOrder && n.c(this.needCloseAnimation, islandTemplate.needCloseAnimation) && this.expandedTime == islandTemplate.expandedTime && n.c(this.maxSize, islandTemplate.maxSize);
    }

    public final BigIslandArea getBigIslandArea() {
        return this.bigIslandArea;
    }

    public final String getBusiness() {
        return this.business;
    }

    public final boolean getDismissIsland() {
        return this.dismissIsland;
    }

    public final int getExpandedTime() {
        return this.expandedTime;
    }

    public final String getHighlightColor() {
        return this.highlightColor;
    }

    public final boolean getIslandOrder() {
        return this.islandOrder;
    }

    public final Integer getIslandPriority() {
        return this.islandPriority;
    }

    public final Integer getIslandProperty() {
        return this.islandProperty;
    }

    public final int getIslandTimeout() {
        return this.islandTimeout;
    }

    public final Boolean getMaxSize() {
        return this.maxSize;
    }

    public final Boolean getNeedCloseAnimation() {
        return this.needCloseAnimation;
    }

    public final ShareData getShareData() {
        return this.shareData;
    }

    public final SmallIslandArea getSmallIslandArea() {
        return this.smallIslandArea;
    }

    public int hashCode() {
        BigIslandArea bigIslandArea = this.bigIslandArea;
        int iHashCode = (bigIslandArea == null ? 0 : bigIslandArea.hashCode()) * 31;
        SmallIslandArea smallIslandArea = this.smallIslandArea;
        int iHashCode2 = (iHashCode + (smallIslandArea == null ? 0 : smallIslandArea.hashCode())) * 31;
        ShareData shareData = this.shareData;
        int iHashCode3 = (iHashCode2 + (shareData == null ? 0 : shareData.hashCode())) * 31;
        String str = this.business;
        int iHashCode4 = (((((iHashCode3 + (str == null ? 0 : str.hashCode())) * 31) + Boolean.hashCode(this.dismissIsland)) * 31) + Integer.hashCode(this.islandTimeout)) * 31;
        String str2 = this.highlightColor;
        int iHashCode5 = (iHashCode4 + (str2 == null ? 0 : str2.hashCode())) * 31;
        Integer num = this.islandProperty;
        int iHashCode6 = (iHashCode5 + (num == null ? 0 : num.hashCode())) * 31;
        Integer num2 = this.islandPriority;
        int iHashCode7 = (((iHashCode6 + (num2 == null ? 0 : num2.hashCode())) * 31) + Boolean.hashCode(this.islandOrder)) * 31;
        Boolean bool = this.needCloseAnimation;
        int iHashCode8 = (((iHashCode7 + (bool == null ? 0 : bool.hashCode())) * 31) + Integer.hashCode(this.expandedTime)) * 31;
        Boolean bool2 = this.maxSize;
        return iHashCode8 + (bool2 != null ? bool2.hashCode() : 0);
    }

    public final void setHighlightColor(String str) {
        this.highlightColor = str;
    }

    public final void setIslandOrder(boolean z2) {
        this.islandOrder = z2;
    }

    public final void setIslandPriority(Integer num) {
        this.islandPriority = num;
    }

    public final void setIslandProperty(Integer num) {
        this.islandProperty = num;
    }

    public final void setNeedCloseAnimation(Boolean bool) {
        this.needCloseAnimation = bool;
    }

    public String toString() {
        return "IslandTemplate(bigIslandArea=" + this.bigIslandArea + ", smallIslandArea=" + this.smallIslandArea + ", shareData=" + this.shareData + ", business=" + this.business + ", dismissIsland=" + this.dismissIsland + ", islandTimeout=" + this.islandTimeout + ", highlightColor=" + this.highlightColor + ", islandProperty=" + this.islandProperty + ", islandPriority=" + this.islandPriority + ", islandOrder=" + this.islandOrder + ", needCloseAnimation=" + this.needCloseAnimation + ", expandedTime=" + this.expandedTime + ", maxSize=" + this.maxSize + ")";
    }
}
