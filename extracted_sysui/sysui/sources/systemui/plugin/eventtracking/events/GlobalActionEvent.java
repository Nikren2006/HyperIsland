package systemui.plugin.eventtracking.events;

import kotlin.jvm.internal.n;
import systemui.plugin.eventtracking.EventID;
import systemui.plugin.eventtracking.EventKey;

/* JADX INFO: loaded from: classes5.dex */
@EventID(id = "event_global_action")
public final class GlobalActionEvent {

    @EventKey(key = "move_event")
    private final String type;

    public GlobalActionEvent(String type) {
        n.g(type, "type");
        this.type = type;
    }

    public static /* synthetic */ GlobalActionEvent copy$default(GlobalActionEvent globalActionEvent, String str, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            str = globalActionEvent.type;
        }
        return globalActionEvent.copy(str);
    }

    public final String component1() {
        return this.type;
    }

    public final GlobalActionEvent copy(String type) {
        n.g(type, "type");
        return new GlobalActionEvent(type);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof GlobalActionEvent) && n.c(this.type, ((GlobalActionEvent) obj).type);
    }

    public final String getType() {
        return this.type;
    }

    public int hashCode() {
        return this.type.hashCode();
    }

    public String toString() {
        return "GlobalActionEvent(type=" + this.type + ")";
    }
}
