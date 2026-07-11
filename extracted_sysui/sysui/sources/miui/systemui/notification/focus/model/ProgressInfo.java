package miui.systemui.notification.focus.model;

import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.n;

/* JADX INFO: loaded from: classes4.dex */
public final class ProgressInfo {
    private final String colorProgress;
    private final String colorProgressDark;
    private final String colorProgressEnd;
    private final String colorProgressEndDark;
    private boolean isAutoProgress;
    private boolean isCCW;
    private final String picEnd;
    private final String picEndUnselected;
    private final String picForward;
    private final String picMiddle;
    private final String picMiddleUnselected;
    private int progress;

    public ProgressInfo(String str, String str2, String colorProgressEnd, String colorProgressEndDark, String picForward, String picMiddle, String picMiddleUnselected, String picEnd, String picEndUnselected, boolean z2, boolean z3) {
        n.g(colorProgressEnd, "colorProgressEnd");
        n.g(colorProgressEndDark, "colorProgressEndDark");
        n.g(picForward, "picForward");
        n.g(picMiddle, "picMiddle");
        n.g(picMiddleUnselected, "picMiddleUnselected");
        n.g(picEnd, "picEnd");
        n.g(picEndUnselected, "picEndUnselected");
        this.colorProgress = str;
        this.colorProgressDark = str2;
        this.colorProgressEnd = colorProgressEnd;
        this.colorProgressEndDark = colorProgressEndDark;
        this.picForward = picForward;
        this.picMiddle = picMiddle;
        this.picMiddleUnselected = picMiddleUnselected;
        this.picEnd = picEnd;
        this.picEndUnselected = picEndUnselected;
        this.isCCW = z2;
        this.isAutoProgress = z3;
    }

    public final String component1() {
        return this.colorProgress;
    }

    public final boolean component10() {
        return this.isCCW;
    }

    public final boolean component11() {
        return this.isAutoProgress;
    }

    public final String component2() {
        return this.colorProgressDark;
    }

    public final String component3() {
        return this.colorProgressEnd;
    }

    public final String component4() {
        return this.colorProgressEndDark;
    }

    public final String component5() {
        return this.picForward;
    }

    public final String component6() {
        return this.picMiddle;
    }

    public final String component7() {
        return this.picMiddleUnselected;
    }

    public final String component8() {
        return this.picEnd;
    }

    public final String component9() {
        return this.picEndUnselected;
    }

    public final ProgressInfo copy(String str, String str2, String colorProgressEnd, String colorProgressEndDark, String picForward, String picMiddle, String picMiddleUnselected, String picEnd, String picEndUnselected, boolean z2, boolean z3) {
        n.g(colorProgressEnd, "colorProgressEnd");
        n.g(colorProgressEndDark, "colorProgressEndDark");
        n.g(picForward, "picForward");
        n.g(picMiddle, "picMiddle");
        n.g(picMiddleUnselected, "picMiddleUnselected");
        n.g(picEnd, "picEnd");
        n.g(picEndUnselected, "picEndUnselected");
        return new ProgressInfo(str, str2, colorProgressEnd, colorProgressEndDark, picForward, picMiddle, picMiddleUnselected, picEnd, picEndUnselected, z2, z3);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof ProgressInfo)) {
            return false;
        }
        ProgressInfo progressInfo = (ProgressInfo) obj;
        return n.c(this.colorProgress, progressInfo.colorProgress) && n.c(this.colorProgressDark, progressInfo.colorProgressDark) && n.c(this.colorProgressEnd, progressInfo.colorProgressEnd) && n.c(this.colorProgressEndDark, progressInfo.colorProgressEndDark) && n.c(this.picForward, progressInfo.picForward) && n.c(this.picMiddle, progressInfo.picMiddle) && n.c(this.picMiddleUnselected, progressInfo.picMiddleUnselected) && n.c(this.picEnd, progressInfo.picEnd) && n.c(this.picEndUnselected, progressInfo.picEndUnselected) && this.isCCW == progressInfo.isCCW && this.isAutoProgress == progressInfo.isAutoProgress;
    }

    public final String getColorProgress() {
        return this.colorProgress;
    }

    public final String getColorProgressDark() {
        return this.colorProgressDark;
    }

    public final String getColorProgressEnd() {
        return this.colorProgressEnd;
    }

    public final String getColorProgressEndDark() {
        return this.colorProgressEndDark;
    }

    public final String getPicEnd() {
        return this.picEnd;
    }

    public final String getPicEndUnselected() {
        return this.picEndUnselected;
    }

    public final String getPicForward() {
        return this.picForward;
    }

    public final String getPicMiddle() {
        return this.picMiddle;
    }

    public final String getPicMiddleUnselected() {
        return this.picMiddleUnselected;
    }

    public final int getProgress() {
        int i2 = this.progress;
        if (i2 < 0) {
            return 0;
        }
        return i2;
    }

    public int hashCode() {
        String str = this.colorProgress;
        int iHashCode = (str == null ? 0 : str.hashCode()) * 31;
        String str2 = this.colorProgressDark;
        return ((((((((((((((((((iHashCode + (str2 != null ? str2.hashCode() : 0)) * 31) + this.colorProgressEnd.hashCode()) * 31) + this.colorProgressEndDark.hashCode()) * 31) + this.picForward.hashCode()) * 31) + this.picMiddle.hashCode()) * 31) + this.picMiddleUnselected.hashCode()) * 31) + this.picEnd.hashCode()) * 31) + this.picEndUnselected.hashCode()) * 31) + Boolean.hashCode(this.isCCW)) * 31) + Boolean.hashCode(this.isAutoProgress);
    }

    public final boolean isAutoProgress() {
        return this.isAutoProgress;
    }

    public final boolean isCCW() {
        return this.isCCW;
    }

    public final void setAutoProgress(boolean z2) {
        this.isAutoProgress = z2;
    }

    public final void setCCW(boolean z2) {
        this.isCCW = z2;
    }

    public final void setProgress(int i2) {
        this.progress = i2;
    }

    public String toString() {
        return "ProgressInfo(colorProgress=" + this.colorProgress + ", colorProgressDark=" + this.colorProgressDark + ", colorProgressEnd=" + this.colorProgressEnd + ", colorProgressEndDark=" + this.colorProgressEndDark + ", picForward=" + this.picForward + ", picMiddle=" + this.picMiddle + ", picMiddleUnselected=" + this.picMiddleUnselected + ", picEnd=" + this.picEnd + ", picEndUnselected=" + this.picEndUnselected + ", isCCW=" + this.isCCW + ", isAutoProgress=" + this.isAutoProgress + ")";
    }

    public /* synthetic */ ProgressInfo(String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8, String str9, boolean z2, boolean z3, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, str2, str3, str4, str5, str6, str7, str8, str9, (i2 & 512) != 0 ? false : z2, (i2 & 1024) != 0 ? false : z3);
    }
}
