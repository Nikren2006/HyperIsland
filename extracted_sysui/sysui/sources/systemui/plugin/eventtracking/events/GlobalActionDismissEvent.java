package systemui.plugin.eventtracking.events;

import kotlin.jvm.internal.n;
import systemui.plugin.eventtracking.EventID;
import systemui.plugin.eventtracking.EventKey;

/* JADX INFO: loaded from: classes5.dex */
@EventID(id = "event_global_action_dismiss")
public final class GlobalActionDismissEvent {

    @EventKey(key = "reason")
    private final String type;

    public GlobalActionDismissEvent(String type) {
        n.g(type, "type");
        this.type = type;
    }

    public static /* synthetic */ GlobalActionDismissEvent copy$default(GlobalActionDismissEvent globalActionDismissEvent, String str, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            str = globalActionDismissEvent.type;
        }
        return globalActionDismissEvent.copy(str);
    }

    public final String component1() {
        return this.type;
    }

    public final GlobalActionDismissEvent copy(String type) {
        n.g(type, "type");
        return new GlobalActionDismissEvent(type);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof GlobalActionDismissEvent) && n.c(this.type, ((GlobalActionDismissEvent) obj).type);
    }

    public final String getType() {
        return this.type;
    }

    public int hashCode() {
        return this.type.hashCode();
    }

    public String toString() {
        return "GlobalActionDismissEvent(type=" + this.type + ")";
    }
}
