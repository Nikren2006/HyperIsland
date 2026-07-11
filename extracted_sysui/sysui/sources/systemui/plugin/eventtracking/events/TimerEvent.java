package systemui.plugin.eventtracking.events;

import kotlin.jvm.internal.n;
import systemui.plugin.eventtracking.EventID;
import systemui.plugin.eventtracking.EventKey;

/* JADX INFO: loaded from: classes5.dex */
@EventID(id = "event_volume_timer")
public final class TimerEvent {

    @EventKey(key = "str_duration")
    private final String time;

    @EventKey(key = "str_type")
    private final String type;

    public TimerEvent(String type, String time) {
        n.g(type, "type");
        n.g(time, "time");
        this.type = type;
        this.time = time;
    }

    public static /* synthetic */ TimerEvent copy$default(TimerEvent timerEvent, String str, String str2, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            str = timerEvent.type;
        }
        if ((i2 & 2) != 0) {
            str2 = timerEvent.time;
        }
        return timerEvent.copy(str, str2);
    }

    public final String component1() {
        return this.type;
    }

    public final String component2() {
        return this.time;
    }

    public final TimerEvent copy(String type, String time) {
        n.g(type, "type");
        n.g(time, "time");
        return new TimerEvent(type, time);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof TimerEvent)) {
            return false;
        }
        TimerEvent timerEvent = (TimerEvent) obj;
        return n.c(this.type, timerEvent.type) && n.c(this.time, timerEvent.time);
    }

    public final String getTime() {
        return this.time;
    }

    public final String getType() {
        return this.type;
    }

    public int hashCode() {
        return (this.type.hashCode() * 31) + this.time.hashCode();
    }

    public String toString() {
        return "TimerEvent(type=" + this.type + ", time=" + this.time + ")";
    }
}
