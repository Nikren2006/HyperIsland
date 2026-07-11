package miui.systemui.notification.focus.model;

import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.n;

/* JADX INFO: loaded from: classes4.dex */
public final class BgInfo {
    private final String colorBg;
    private String picBg;
    private int type;

    public BgInfo(int i2, String colorBg, String str) {
        n.g(colorBg, "colorBg");
        this.type = i2;
        this.colorBg = colorBg;
        this.picBg = str;
    }

    public static /* synthetic */ BgInfo copy$default(BgInfo bgInfo, int i2, String str, String str2, int i3, Object obj) {
        if ((i3 & 1) != 0) {
            i2 = bgInfo.type;
        }
        if ((i3 & 2) != 0) {
            str = bgInfo.colorBg;
        }
        if ((i3 & 4) != 0) {
            str2 = bgInfo.picBg;
        }
        return bgInfo.copy(i2, str, str2);
    }

    public final int component1() {
        return this.type;
    }

    public final String component2() {
        return this.colorBg;
    }

    public final String component3() {
        return this.picBg;
    }

    public final BgInfo copy(int i2, String colorBg, String str) {
        n.g(colorBg, "colorBg");
        return new BgInfo(i2, colorBg, str);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof BgInfo)) {
            return false;
        }
        BgInfo bgInfo = (BgInfo) obj;
        return this.type == bgInfo.type && n.c(this.colorBg, bgInfo.colorBg) && n.c(this.picBg, bgInfo.picBg);
    }

    public final String getColorBg() {
        return this.colorBg;
    }

    public final String getPicBg() {
        return this.picBg;
    }

    public final int getType() {
        return this.type;
    }

    public int hashCode() {
        int iHashCode = ((Integer.hashCode(this.type) * 31) + this.colorBg.hashCode()) * 31;
        String str = this.picBg;
        return iHashCode + (str == null ? 0 : str.hashCode());
    }

    public final void setPicBg(String str) {
        this.picBg = str;
    }

    public final void setType(int i2) {
        this.type = i2;
    }

    public String toString() {
        return "BgInfo(type=" + this.type + ", colorBg=" + this.colorBg + ", picBg=" + this.picBg + ")";
    }

    public /* synthetic */ BgInfo(int i2, String str, String str2, int i3, DefaultConstructorMarker defaultConstructorMarker) {
        this((i3 & 1) != 0 ? 0 : i2, str, (i3 & 4) != 0 ? null : str2);
    }
}
