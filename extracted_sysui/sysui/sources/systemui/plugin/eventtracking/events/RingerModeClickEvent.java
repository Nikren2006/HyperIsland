package systemui.plugin.eventtracking.events;

import kotlin.jvm.internal.n;
import systemui.plugin.eventtracking.EventID;
import systemui.plugin.eventtracking.EventKey;

/* JADX INFO: loaded from: classes5.dex */
@EventID(id = "event_volume_collapse_click_btn")
public final class RingerModeClickEvent {

    @EventKey(key = "ringer_mode_after_click")
    private final String typeAndState;

    public RingerModeClickEvent(String typeAndState) {
        n.g(typeAndState, "typeAndState");
        this.typeAndState = typeAndState;
    }

    public static /* synthetic */ RingerModeClickEvent copy$default(RingerModeClickEvent ringerModeClickEvent, String str, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            str = ringerModeClickEvent.typeAndState;
        }
        return ringerModeClickEvent.copy(str);
    }

    public final String component1() {
        return this.typeAndState;
    }

    public final RingerModeClickEvent copy(String typeAndState) {
        n.g(typeAndState, "typeAndState");
        return new RingerModeClickEvent(typeAndState);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof RingerModeClickEvent) && n.c(this.typeAndState, ((RingerModeClickEvent) obj).typeAndState);
    }

    public final String getTypeAndState() {
        return this.typeAndState;
    }

    public int hashCode() {
        return this.typeAndState.hashCode();
    }

    public String toString() {
        return "RingerModeClickEvent(typeAndState=" + this.typeAndState + ")";
    }
}
