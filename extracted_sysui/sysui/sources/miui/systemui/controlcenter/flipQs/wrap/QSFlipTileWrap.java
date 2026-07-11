package miui.systemui.controlcenter.flipQs.wrap;

import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.n;

/* JADX INFO: loaded from: classes.dex */
public final class QSFlipTileWrap {
    private final Integer iconId;
    private final String name;
    private final String spec;
    private final int titleRes;
    private int type;

    public QSFlipTileWrap(int i2, int i3, String str, String str2, Integer num) {
        this.type = i2;
        this.titleRes = i3;
        this.spec = str;
        this.name = str2;
        this.iconId = num;
    }

    public static /* synthetic */ QSFlipTileWrap copy$default(QSFlipTileWrap qSFlipTileWrap, int i2, int i3, String str, String str2, Integer num, int i4, Object obj) {
        if ((i4 & 1) != 0) {
            i2 = qSFlipTileWrap.type;
        }
        if ((i4 & 2) != 0) {
            i3 = qSFlipTileWrap.titleRes;
        }
        int i5 = i3;
        if ((i4 & 4) != 0) {
            str = qSFlipTileWrap.spec;
        }
        String str3 = str;
        if ((i4 & 8) != 0) {
            str2 = qSFlipTileWrap.name;
        }
        String str4 = str2;
        if ((i4 & 16) != 0) {
            num = qSFlipTileWrap.iconId;
        }
        return qSFlipTileWrap.copy(i2, i5, str3, str4, num);
    }

    public final int component1() {
        return this.type;
    }

    public final int component2() {
        return this.titleRes;
    }

    public final String component3() {
        return this.spec;
    }

    public final String component4() {
        return this.name;
    }

    public final Integer component5() {
        return this.iconId;
    }

    public final QSFlipTileWrap copy(int i2, int i3, String str, String str2, Integer num) {
        return new QSFlipTileWrap(i2, i3, str, str2, num);
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof QSFlipTileWrap)) {
            return false;
        }
        QSFlipTileWrap qSFlipTileWrap = (QSFlipTileWrap) obj;
        return qSFlipTileWrap.type == this.type && qSFlipTileWrap.titleRes == this.titleRes && n.c(qSFlipTileWrap.spec, this.spec);
    }

    public final Integer getIconId() {
        return this.iconId;
    }

    public final String getName() {
        return this.name;
    }

    public final String getSpec() {
        return this.spec;
    }

    public final int getTitleRes() {
        return this.titleRes;
    }

    public final int getType() {
        return this.type;
    }

    public int hashCode() {
        int i2 = ((this.type * 31) + this.titleRes) * 31;
        String str = this.spec;
        int iHashCode = (i2 + (str != null ? str.hashCode() : 0)) * 31;
        String str2 = this.name;
        int iHashCode2 = (iHashCode + (str2 != null ? str2.hashCode() : 0)) * 31;
        Integer num = this.iconId;
        return iHashCode2 + (num != null ? num.intValue() : 0);
    }

    public final void setType(int i2) {
        this.type = i2;
    }

    public String toString() {
        return "QSFlipTileWrap(type=" + this.type + ", titleRes=" + this.titleRes + ", spec=" + this.spec + ", name=" + this.name + ", iconId=" + this.iconId + ")";
    }

    public /* synthetic */ QSFlipTileWrap(int i2, int i3, String str, String str2, Integer num, int i4, DefaultConstructorMarker defaultConstructorMarker) {
        this(i2, (i4 & 2) != 0 ? 0 : i3, (i4 & 4) != 0 ? null : str, (i4 & 8) != 0 ? null : str2, (i4 & 16) != 0 ? null : num);
    }
}
