package miui.systemui.notification.focus.model;

import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.n;

/* JADX INFO: loaded from: classes4.dex */
public final class MultiProgressInfo extends TextAndColorInfo {
    private String color;
    private int points;
    private int progress;

    public MultiProgressInfo() {
        this(0, null, 0, 7, null);
    }

    public static /* synthetic */ MultiProgressInfo copy$default(MultiProgressInfo multiProgressInfo, int i2, String str, int i3, int i4, Object obj) {
        if ((i4 & 1) != 0) {
            i2 = multiProgressInfo.progress;
        }
        if ((i4 & 2) != 0) {
            str = multiProgressInfo.color;
        }
        if ((i4 & 4) != 0) {
            i3 = multiProgressInfo.points;
        }
        return multiProgressInfo.copy(i2, str, i3);
    }

    public final int component1() {
        return this.progress;
    }

    public final String component2() {
        return this.color;
    }

    public final int component3() {
        return this.points;
    }

    public final MultiProgressInfo copy(int i2, String str, int i3) {
        return new MultiProgressInfo(i2, str, i3);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof MultiProgressInfo)) {
            return false;
        }
        MultiProgressInfo multiProgressInfo = (MultiProgressInfo) obj;
        return this.progress == multiProgressInfo.progress && n.c(this.color, multiProgressInfo.color) && this.points == multiProgressInfo.points;
    }

    public final String getColor() {
        return this.color;
    }

    public final int getPoints() {
        return this.points;
    }

    public final int getProgress() {
        return this.progress;
    }

    public int hashCode() {
        int iHashCode = Integer.hashCode(this.progress) * 31;
        String str = this.color;
        return ((iHashCode + (str == null ? 0 : str.hashCode())) * 31) + Integer.hashCode(this.points);
    }

    public final void setColor(String str) {
        this.color = str;
    }

    public final void setPoints(int i2) {
        this.points = i2;
    }

    public final void setProgress(int i2) {
        this.progress = i2;
    }

    public String toString() {
        return "MultiProgressInfo(progress=" + this.progress + ", color=" + this.color + ", points=" + this.points + ")";
    }

    public /* synthetic */ MultiProgressInfo(int i2, String str, int i3, int i4, DefaultConstructorMarker defaultConstructorMarker) {
        this((i4 & 1) != 0 ? -1 : i2, (i4 & 2) != 0 ? null : str, (i4 & 4) != 0 ? 0 : i3);
    }

    public MultiProgressInfo(int i2, String str, int i3) {
        this.progress = i2;
        this.color = str;
        this.points = i3;
    }
}
