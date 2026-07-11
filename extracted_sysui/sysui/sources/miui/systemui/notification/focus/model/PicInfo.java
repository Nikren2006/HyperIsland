package miui.systemui.notification.focus.model;

import android.text.TextUtils;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.n;

/* JADX INFO: loaded from: classes4.dex */
public final class PicInfo extends TextAndColorInfo {
    private ActionInfo actionInfo;
    private String pic;
    private String picDark;
    private Integer type;

    public PicInfo() {
        this(null, null, null, 7, null);
    }

    public static /* synthetic */ PicInfo copy$default(PicInfo picInfo, String str, Integer num, ActionInfo actionInfo, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            str = picInfo.pic;
        }
        if ((i2 & 2) != 0) {
            num = picInfo.type;
        }
        if ((i2 & 4) != 0) {
            actionInfo = picInfo.actionInfo;
        }
        return picInfo.copy(str, num, actionInfo);
    }

    public final String component1() {
        return this.pic;
    }

    public final Integer component2() {
        return this.type;
    }

    public final ActionInfo component3() {
        return this.actionInfo;
    }

    public final PicInfo copy(String str, Integer num, ActionInfo actionInfo) {
        return new PicInfo(str, num, actionInfo);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof PicInfo)) {
            return false;
        }
        PicInfo picInfo = (PicInfo) obj;
        return n.c(this.pic, picInfo.pic) && n.c(this.type, picInfo.type) && n.c(this.actionInfo, picInfo.actionInfo);
    }

    public final ActionInfo getActionInfo() {
        return this.actionInfo;
    }

    public final String getPic() {
        return this.pic;
    }

    public final String getPicDark() {
        String str = this.picDark;
        return (str == null || TextUtils.isEmpty(str)) ? this.pic : this.picDark;
    }

    public final Integer getType() {
        return this.type;
    }

    public int hashCode() {
        String str = this.pic;
        int iHashCode = (str == null ? 0 : str.hashCode()) * 31;
        Integer num = this.type;
        int iHashCode2 = (iHashCode + (num == null ? 0 : num.hashCode())) * 31;
        ActionInfo actionInfo = this.actionInfo;
        return iHashCode2 + (actionInfo != null ? actionInfo.hashCode() : 0);
    }

    public final void setActionInfo(ActionInfo actionInfo) {
        this.actionInfo = actionInfo;
    }

    public final void setPic(String str) {
        this.pic = str;
    }

    public final void setPicDark(String str) {
        this.picDark = str;
    }

    public final void setType(Integer num) {
        this.type = num;
    }

    public String toString() {
        return "PicInfo(pic=" + this.pic + ", type=" + this.type + ", actionInfo=" + this.actionInfo + ")";
    }

    public /* synthetic */ PicInfo(String str, Integer num, ActionInfo actionInfo, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this((i2 & 1) != 0 ? null : str, (i2 & 2) != 0 ? null : num, (i2 & 4) != 0 ? null : actionInfo);
    }

    public PicInfo(String str, Integer num, ActionInfo actionInfo) {
        this.pic = str;
        this.type = num;
        this.actionInfo = actionInfo;
    }
}
