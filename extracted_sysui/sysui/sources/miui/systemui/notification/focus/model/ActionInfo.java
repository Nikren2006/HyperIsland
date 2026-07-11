package miui.systemui.notification.focus.model;

import H0.g;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.n;

/* JADX INFO: loaded from: classes4.dex */
public final class ActionInfo {
    private final String action;
    private final String actionBgColor;
    private final String actionBgColorDark;
    private final String actionBgPressColor;
    private final String actionBgPressColorDark;
    private final String actionIcon;
    private final String actionIconDark;
    private final String actionIntent;
    private final Integer actionIntentType;
    private final String actionTitle;
    private final String actionTitleColor;
    private final String actionTitleColorDark;
    private final boolean clickWithCollapse;
    private final ProgressInfo progressInfo;
    private final Integer type;

    public ActionInfo() {
        this(null, null, null, null, null, null, null, null, null, null, null, null, null, false, null, 32767, null);
    }

    public final String component1() {
        return this.action;
    }

    public final String component10() {
        return this.actionBgPressColor;
    }

    public final String component11() {
        return this.actionBgPressColorDark;
    }

    public final Integer component12() {
        return this.actionIntentType;
    }

    public final String component13() {
        return this.actionIntent;
    }

    public final boolean component14() {
        return this.clickWithCollapse;
    }

    public final ProgressInfo component15() {
        return this.progressInfo;
    }

    public final Integer component2() {
        return this.type;
    }

    public final String component3() {
        return this.actionIcon;
    }

    public final String component4() {
        return this.actionIconDark;
    }

    public final String component5() {
        return this.actionTitle;
    }

    public final String component6() {
        return this.actionTitleColor;
    }

    public final String component7() {
        return this.actionTitleColorDark;
    }

    public final String component8() {
        return this.actionBgColor;
    }

    public final String component9() {
        return this.actionBgColorDark;
    }

    public final ActionInfo copy(String str, Integer num, String str2, String str3, String str4, String str5, String str6, String str7, String str8, String str9, String str10, Integer num2, String str11, boolean z2, ProgressInfo progressInfo) {
        return new ActionInfo(str, num, str2, str3, str4, str5, str6, str7, str8, str9, str10, num2, str11, z2, progressInfo);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof ActionInfo)) {
            return false;
        }
        ActionInfo actionInfo = (ActionInfo) obj;
        return n.c(this.action, actionInfo.action) && n.c(this.type, actionInfo.type) && n.c(this.actionIcon, actionInfo.actionIcon) && n.c(this.actionIconDark, actionInfo.actionIconDark) && n.c(this.actionTitle, actionInfo.actionTitle) && n.c(this.actionTitleColor, actionInfo.actionTitleColor) && n.c(this.actionTitleColorDark, actionInfo.actionTitleColorDark) && n.c(this.actionBgColor, actionInfo.actionBgColor) && n.c(this.actionBgColorDark, actionInfo.actionBgColorDark) && n.c(this.actionBgPressColor, actionInfo.actionBgPressColor) && n.c(this.actionBgPressColorDark, actionInfo.actionBgPressColorDark) && n.c(this.actionIntentType, actionInfo.actionIntentType) && n.c(this.actionIntent, actionInfo.actionIntent) && this.clickWithCollapse == actionInfo.clickWithCollapse && n.c(this.progressInfo, actionInfo.progressInfo);
    }

    public final String getAction() {
        return this.action;
    }

    public final String getActionBgColor() {
        return this.actionBgColor;
    }

    public final String getActionBgColorDark() {
        return this.actionBgColorDark;
    }

    public final String getActionBgPressColor() {
        return this.actionBgPressColor;
    }

    public final String getActionBgPressColorDark() {
        return this.actionBgPressColorDark;
    }

    public final String getActionIcon() {
        return this.actionIcon;
    }

    public final String getActionIconDark() {
        return this.actionIconDark;
    }

    public final String getActionIntent() {
        return this.actionIntent;
    }

    public final Integer getActionIntentType() {
        return this.actionIntentType;
    }

    public final String getActionTitle() {
        return this.actionTitle;
    }

    public final String getActionTitleColor() {
        return this.actionTitleColor;
    }

    public final String getActionTitleColorDark() {
        return this.actionTitleColorDark;
    }

    public final boolean getClickWithCollapse() {
        return this.clickWithCollapse;
    }

    public final ProgressInfo getProgressInfo() {
        return this.progressInfo;
    }

    public final Integer getType() {
        return this.type;
    }

    public int hashCode() {
        String str = this.action;
        int iHashCode = (str == null ? 0 : str.hashCode()) * 31;
        Integer num = this.type;
        int iHashCode2 = (iHashCode + (num == null ? 0 : num.hashCode())) * 31;
        String str2 = this.actionIcon;
        int iHashCode3 = (iHashCode2 + (str2 == null ? 0 : str2.hashCode())) * 31;
        String str3 = this.actionIconDark;
        int iHashCode4 = (iHashCode3 + (str3 == null ? 0 : str3.hashCode())) * 31;
        String str4 = this.actionTitle;
        int iHashCode5 = (iHashCode4 + (str4 == null ? 0 : str4.hashCode())) * 31;
        String str5 = this.actionTitleColor;
        int iHashCode6 = (iHashCode5 + (str5 == null ? 0 : str5.hashCode())) * 31;
        String str6 = this.actionTitleColorDark;
        int iHashCode7 = (iHashCode6 + (str6 == null ? 0 : str6.hashCode())) * 31;
        String str7 = this.actionBgColor;
        int iHashCode8 = (iHashCode7 + (str7 == null ? 0 : str7.hashCode())) * 31;
        String str8 = this.actionBgColorDark;
        int iHashCode9 = (iHashCode8 + (str8 == null ? 0 : str8.hashCode())) * 31;
        String str9 = this.actionBgPressColor;
        int iHashCode10 = (iHashCode9 + (str9 == null ? 0 : str9.hashCode())) * 31;
        String str10 = this.actionBgPressColorDark;
        int iHashCode11 = (iHashCode10 + (str10 == null ? 0 : str10.hashCode())) * 31;
        Integer num2 = this.actionIntentType;
        int iHashCode12 = (iHashCode11 + (num2 == null ? 0 : num2.hashCode())) * 31;
        String str11 = this.actionIntent;
        int iHashCode13 = (((iHashCode12 + (str11 == null ? 0 : str11.hashCode())) * 31) + Boolean.hashCode(this.clickWithCollapse)) * 31;
        ProgressInfo progressInfo = this.progressInfo;
        return iHashCode13 + (progressInfo != null ? progressInfo.hashCode() : 0);
    }

    public String toString() {
        return "ActionInfo(action=" + this.action + ", type=" + this.type + ", actionIcon=" + this.actionIcon + ", actionIconDark=" + this.actionIconDark + ", actionTitle=" + this.actionTitle + ", actionTitleColor=" + this.actionTitleColor + ", actionTitleColorDark=" + this.actionTitleColorDark + ", actionBgColor=" + this.actionBgColor + ", actionBgColorDark=" + this.actionBgColorDark + ", actionBgPressColor=" + this.actionBgPressColor + ", actionBgPressColorDark=" + this.actionBgPressColorDark + ", actionIntentType=" + this.actionIntentType + ", actionIntent=" + this.actionIntent + ", clickWithCollapse=" + this.clickWithCollapse + ", progressInfo=" + this.progressInfo + ")";
    }

    public ActionInfo(String str, Integer num, String str2, String str3, String str4, String str5, String str6, String str7, String str8, String str9, String str10, Integer num2, String str11, boolean z2, ProgressInfo progressInfo) {
        this.action = str;
        this.type = num;
        this.actionIcon = str2;
        this.actionIconDark = str3;
        this.actionTitle = str4;
        this.actionTitleColor = str5;
        this.actionTitleColorDark = str6;
        this.actionBgColor = str7;
        this.actionBgColorDark = str8;
        this.actionBgPressColor = str9;
        this.actionBgPressColorDark = str10;
        this.actionIntentType = num2;
        this.actionIntent = str11;
        this.clickWithCollapse = z2;
        this.progressInfo = progressInfo;
    }

    public final String getActionBgColor(boolean z2) {
        if (z2) {
            return this.actionBgColorDark;
        }
        if (z2) {
            throw new g();
        }
        return this.actionBgColor;
    }

    public final String getActionBgPressColor(boolean z2) {
        if (z2) {
            return this.actionBgPressColorDark;
        }
        if (z2) {
            throw new g();
        }
        return this.actionBgPressColor;
    }

    public final String getActionIcon(boolean z2) {
        if (z2) {
            return this.actionIconDark;
        }
        if (z2) {
            throw new g();
        }
        return this.actionIcon;
    }

    public final String getActionTitleColor(boolean z2) {
        if (z2) {
            return this.actionTitleColorDark;
        }
        if (z2) {
            throw new g();
        }
        return this.actionTitleColor;
    }

    public /* synthetic */ ActionInfo(String str, Integer num, String str2, String str3, String str4, String str5, String str6, String str7, String str8, String str9, String str10, Integer num2, String str11, boolean z2, ProgressInfo progressInfo, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this((i2 & 1) != 0 ? null : str, (i2 & 2) != 0 ? null : num, (i2 & 4) != 0 ? null : str2, (i2 & 8) != 0 ? null : str3, (i2 & 16) != 0 ? null : str4, (i2 & 32) != 0 ? null : str5, (i2 & 64) != 0 ? null : str6, (i2 & 128) != 0 ? null : str7, (i2 & 256) != 0 ? null : str8, (i2 & 512) != 0 ? null : str9, (i2 & 1024) != 0 ? null : str10, (i2 & 2048) != 0 ? null : num2, (i2 & 4096) != 0 ? null : str11, (i2 & 8192) != 0 ? false : z2, (i2 & 16384) == 0 ? progressInfo : null);
    }
}
