package systemui.plugin.eventtracking.events;

import kotlin.jvm.internal.n;
import systemui.plugin.eventtracking.EventID;
import systemui.plugin.eventtracking.EventKey;

/* JADX INFO: loaded from: classes5.dex */
@EventID(id = "event_volume_expand_click_btn")
public final class RingerModeExpandClickEvent {

    @EventKey(key = "click_btn_type")
    private final String type;

    public RingerModeExpandClickEvent(String type) {
        n.g(type, "type");
        this.type = type;
    }

    public static /* synthetic */ RingerModeExpandClickEvent copy$default(RingerModeExpandClickEvent ringerModeExpandClickEvent, String str, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            str = ringerModeExpandClickEvent.type;
        }
        return ringerModeExpandClickEvent.copy(str);
    }

    public final String component1() {
        return this.type;
    }

    public final RingerModeExpandClickEvent copy(String type) {
        n.g(type, "type");
        return new RingerModeExpandClickEvent(type);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof RingerModeExpandClickEvent) && n.c(this.type, ((RingerModeExpandClickEvent) obj).type);
    }

    public final String getType() {
        return this.type;
    }

    public int hashCode() {
        return this.type.hashCode();
    }

    public String toString() {
        return "RingerModeExpandClickEvent(type=" + this.type + ")";
    }
}
