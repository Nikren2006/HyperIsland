package systemui.plugin.eventtracking.events;

import kotlin.jvm.internal.n;
import systemui.plugin.eventtracking.EventID;
import systemui.plugin.eventtracking.EventKey;

/* JADX INFO: loaded from: classes5.dex */
@EventID(id = "event_controls_items_do_edit")
public final class ControlsEditOperationEvent {

    @EventKey(key = "operation")
    private final String operation;

    @EventKey(key = "device_type")
    private final int type;

    public ControlsEditOperationEvent(int i2, String operation) {
        n.g(operation, "operation");
        this.type = i2;
        this.operation = operation;
    }

    public static /* synthetic */ ControlsEditOperationEvent copy$default(ControlsEditOperationEvent controlsEditOperationEvent, int i2, String str, int i3, Object obj) {
        if ((i3 & 1) != 0) {
            i2 = controlsEditOperationEvent.type;
        }
        if ((i3 & 2) != 0) {
            str = controlsEditOperationEvent.operation;
        }
        return controlsEditOperationEvent.copy(i2, str);
    }

    public final int component1() {
        return this.type;
    }

    public final String component2() {
        return this.operation;
    }

    public final ControlsEditOperationEvent copy(int i2, String operation) {
        n.g(operation, "operation");
        return new ControlsEditOperationEvent(i2, operation);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof ControlsEditOperationEvent)) {
            return false;
        }
        ControlsEditOperationEvent controlsEditOperationEvent = (ControlsEditOperationEvent) obj;
        return this.type == controlsEditOperationEvent.type && n.c(this.operation, controlsEditOperationEvent.operation);
    }

    public final String getOperation() {
        return this.operation;
    }

    public final int getType() {
        return this.type;
    }

    public int hashCode() {
        return (Integer.hashCode(this.type) * 31) + this.operation.hashCode();
    }

    public String toString() {
        return "ControlsEditOperationEvent(type=" + this.type + ", operation=" + this.operation + ")";
    }
}
