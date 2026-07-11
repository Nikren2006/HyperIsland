package systemui.plugin.eventtracking.events;

import kotlin.jvm.internal.n;
import systemui.plugin.eventtracking.EventID;
import systemui.plugin.eventtracking.EventKey;

/* JADX INFO: loaded from: classes5.dex */
@EventID(id = "event_controls_dismiss_dialog")
public final class ControlsSetupExposedDismissDialogEvent {

    @EventKey(key = "operation")
    private final String operation;

    public ControlsSetupExposedDismissDialogEvent(String operation) {
        n.g(operation, "operation");
        this.operation = operation;
    }

    public static /* synthetic */ ControlsSetupExposedDismissDialogEvent copy$default(ControlsSetupExposedDismissDialogEvent controlsSetupExposedDismissDialogEvent, String str, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            str = controlsSetupExposedDismissDialogEvent.operation;
        }
        return controlsSetupExposedDismissDialogEvent.copy(str);
    }

    public final String component1() {
        return this.operation;
    }

    public final ControlsSetupExposedDismissDialogEvent copy(String operation) {
        n.g(operation, "operation");
        return new ControlsSetupExposedDismissDialogEvent(operation);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof ControlsSetupExposedDismissDialogEvent) && n.c(this.operation, ((ControlsSetupExposedDismissDialogEvent) obj).operation);
    }

    public final String getOperation() {
        return this.operation;
    }

    public int hashCode() {
        return this.operation.hashCode();
    }

    public String toString() {
        return "ControlsSetupExposedDismissDialogEvent(operation=" + this.operation + ")";
    }
}
