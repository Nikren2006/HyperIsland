package systemui.plugin.eventtracking.events;

import kotlin.jvm.internal.n;
import systemui.plugin.eventtracking.EventID;
import systemui.plugin.eventtracking.EventKey;

/* JADX INFO: loaded from: classes5.dex */
@EventID(id = "event_volume_dialog_show")
public final class ShowDialogEvent {

    @EventKey(key = "str_type")
    private final String reason;

    public ShowDialogEvent(String reason) {
        n.g(reason, "reason");
        this.reason = reason;
    }

    public static /* synthetic */ ShowDialogEvent copy$default(ShowDialogEvent showDialogEvent, String str, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            str = showDialogEvent.reason;
        }
        return showDialogEvent.copy(str);
    }

    public final String component1() {
        return this.reason;
    }

    public final ShowDialogEvent copy(String reason) {
        n.g(reason, "reason");
        return new ShowDialogEvent(reason);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof ShowDialogEvent) && n.c(this.reason, ((ShowDialogEvent) obj).reason);
    }

    public final String getReason() {
        return this.reason;
    }

    public int hashCode() {
        return this.reason.hashCode();
    }

    public String toString() {
        return "ShowDialogEvent(reason=" + this.reason + ")";
    }
}
