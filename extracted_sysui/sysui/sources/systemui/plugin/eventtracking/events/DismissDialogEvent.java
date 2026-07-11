package systemui.plugin.eventtracking.events;

import kotlin.jvm.internal.n;
import systemui.plugin.eventtracking.EventID;
import systemui.plugin.eventtracking.EventKey;

/* JADX INFO: loaded from: classes5.dex */
@EventID(id = "event_volume_dialog_dismiss")
public final class DismissDialogEvent {

    @EventKey(key = "reason")
    private final String reason;

    @EventKey(key = "status")
    private final String status;

    public DismissDialogEvent(String status, String reason) {
        n.g(status, "status");
        n.g(reason, "reason");
        this.status = status;
        this.reason = reason;
    }

    public static /* synthetic */ DismissDialogEvent copy$default(DismissDialogEvent dismissDialogEvent, String str, String str2, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            str = dismissDialogEvent.status;
        }
        if ((i2 & 2) != 0) {
            str2 = dismissDialogEvent.reason;
        }
        return dismissDialogEvent.copy(str, str2);
    }

    public final String component1() {
        return this.status;
    }

    public final String component2() {
        return this.reason;
    }

    public final DismissDialogEvent copy(String status, String reason) {
        n.g(status, "status");
        n.g(reason, "reason");
        return new DismissDialogEvent(status, reason);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof DismissDialogEvent)) {
            return false;
        }
        DismissDialogEvent dismissDialogEvent = (DismissDialogEvent) obj;
        return n.c(this.status, dismissDialogEvent.status) && n.c(this.reason, dismissDialogEvent.reason);
    }

    public final String getReason() {
        return this.reason;
    }

    public final String getStatus() {
        return this.status;
    }

    public int hashCode() {
        return (this.status.hashCode() * 31) + this.reason.hashCode();
    }

    public String toString() {
        return "DismissDialogEvent(status=" + this.status + ", reason=" + this.reason + ")";
    }
}
