package miui.systemui.notification.focus.model;

import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.n;

/* JADX INFO: loaded from: classes4.dex */
public final class TimerInfo {
    private Long timerSystemCurrent;
    private long timerTotal;
    private int timerType;
    private Long timerWhen;

    public TimerInfo(int i2, Long l2, long j2, Long l3) {
        this.timerType = i2;
        this.timerWhen = l2;
        this.timerTotal = j2;
        this.timerSystemCurrent = l3;
    }

    public static /* synthetic */ TimerInfo copy$default(TimerInfo timerInfo, int i2, Long l2, long j2, Long l3, int i3, Object obj) {
        if ((i3 & 1) != 0) {
            i2 = timerInfo.timerType;
        }
        if ((i3 & 2) != 0) {
            l2 = timerInfo.timerWhen;
        }
        Long l4 = l2;
        if ((i3 & 4) != 0) {
            j2 = timerInfo.timerTotal;
        }
        long j3 = j2;
        if ((i3 & 8) != 0) {
            l3 = timerInfo.timerSystemCurrent;
        }
        return timerInfo.copy(i2, l4, j3, l3);
    }

    public final int component1() {
        return this.timerType;
    }

    public final Long component2() {
        return this.timerWhen;
    }

    public final long component3() {
        return this.timerTotal;
    }

    public final Long component4() {
        return this.timerSystemCurrent;
    }

    public final TimerInfo copy(int i2, Long l2, long j2, Long l3) {
        return new TimerInfo(i2, l2, j2, l3);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof TimerInfo)) {
            return false;
        }
        TimerInfo timerInfo = (TimerInfo) obj;
        return this.timerType == timerInfo.timerType && n.c(this.timerWhen, timerInfo.timerWhen) && this.timerTotal == timerInfo.timerTotal && n.c(this.timerSystemCurrent, timerInfo.timerSystemCurrent);
    }

    public final Long getTimerSystemCurrent() {
        return this.timerSystemCurrent;
    }

    public final long getTimerTotal() {
        return this.timerTotal;
    }

    public final int getTimerType() {
        return this.timerType;
    }

    public final Long getTimerWhen() {
        return this.timerWhen;
    }

    public int hashCode() {
        int iHashCode = Integer.hashCode(this.timerType) * 31;
        Long l2 = this.timerWhen;
        int iHashCode2 = (((iHashCode + (l2 == null ? 0 : l2.hashCode())) * 31) + Long.hashCode(this.timerTotal)) * 31;
        Long l3 = this.timerSystemCurrent;
        return iHashCode2 + (l3 != null ? l3.hashCode() : 0);
    }

    public final void setTimerSystemCurrent(Long l2) {
        this.timerSystemCurrent = l2;
    }

    public final void setTimerTotal(long j2) {
        this.timerTotal = j2;
    }

    public final void setTimerType(int i2) {
        this.timerType = i2;
    }

    public final void setTimerWhen(Long l2) {
        this.timerWhen = l2;
    }

    public String toString() {
        return "TimerInfo(timerType=" + this.timerType + ", timerWhen=" + this.timerWhen + ", timerTotal=" + this.timerTotal + ", timerSystemCurrent=" + this.timerSystemCurrent + ")";
    }

    public /* synthetic */ TimerInfo(int i2, Long l2, long j2, Long l3, int i3, DefaultConstructorMarker defaultConstructorMarker) {
        this((i3 & 1) != 0 ? 0 : i2, l2, (i3 & 4) != 0 ? 0L : j2, l3);
    }
}
