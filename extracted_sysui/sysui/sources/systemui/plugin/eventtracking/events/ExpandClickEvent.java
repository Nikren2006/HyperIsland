package systemui.plugin.eventtracking.events;

import kotlin.jvm.internal.n;
import systemui.plugin.eventtracking.EventID;
import systemui.plugin.eventtracking.EventKey;

/* JADX INFO: loaded from: classes5.dex */
@EventID(id = "event_expand_click")
public final class ExpandClickEvent {

    @EventKey(key = "click_expand_btn")
    private final String type;

    public ExpandClickEvent(String type) {
        n.g(type, "type");
        this.type = type;
    }

    public static /* synthetic */ ExpandClickEvent copy$default(ExpandClickEvent expandClickEvent, String str, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            str = expandClickEvent.type;
        }
        return expandClickEvent.copy(str);
    }

    public final String component1() {
        return this.type;
    }

    public final ExpandClickEvent copy(String type) {
        n.g(type, "type");
        return new ExpandClickEvent(type);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof ExpandClickEvent) && n.c(this.type, ((ExpandClickEvent) obj).type);
    }

    public final String getType() {
        return this.type;
    }

    public int hashCode() {
        return this.type.hashCode();
    }

    public String toString() {
        return "ExpandClickEvent(type=" + this.type + ")";
    }
}
