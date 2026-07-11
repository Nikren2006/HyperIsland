package systemui.plugin.eventtracking.events;

import kotlin.jvm.internal.n;
import miui.systemui.dynamicisland.events.DynamicIslandEventsConstants;
import systemui.plugin.eventtracking.EventID;
import systemui.plugin.eventtracking.EventKey;

/* JADX INFO: loaded from: classes5.dex */
@EventID(id = "event_controls_item_click")
public final class ControlsDeviceClickEvent {

    @EventKey(key = DynamicIslandEventsConstants.EventKey.KEY_APP_NAME)
    private final String name;

    @EventKey(key = "device_position")
    private final int position;

    @EventKey(key = "device_type")
    private final int type;

    public ControlsDeviceClickEvent(int i2, int i3, String name) {
        n.g(name, "name");
        this.type = i2;
        this.position = i3;
        this.name = name;
    }

    public static /* synthetic */ ControlsDeviceClickEvent copy$default(ControlsDeviceClickEvent controlsDeviceClickEvent, int i2, int i3, String str, int i4, Object obj) {
        if ((i4 & 1) != 0) {
            i2 = controlsDeviceClickEvent.type;
        }
        if ((i4 & 2) != 0) {
            i3 = controlsDeviceClickEvent.position;
        }
        if ((i4 & 4) != 0) {
            str = controlsDeviceClickEvent.name;
        }
        return controlsDeviceClickEvent.copy(i2, i3, str);
    }

    public final int component1() {
        return this.type;
    }

    public final int component2() {
        return this.position;
    }

    public final String component3() {
        return this.name;
    }

    public final ControlsDeviceClickEvent copy(int i2, int i3, String name) {
        n.g(name, "name");
        return new ControlsDeviceClickEvent(i2, i3, name);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof ControlsDeviceClickEvent)) {
            return false;
        }
        ControlsDeviceClickEvent controlsDeviceClickEvent = (ControlsDeviceClickEvent) obj;
        return this.type == controlsDeviceClickEvent.type && this.position == controlsDeviceClickEvent.position && n.c(this.name, controlsDeviceClickEvent.name);
    }

    public final String getName() {
        return this.name;
    }

    public final int getPosition() {
        return this.position;
    }

    public final int getType() {
        return this.type;
    }

    public int hashCode() {
        return (((Integer.hashCode(this.type) * 31) + Integer.hashCode(this.position)) * 31) + this.name.hashCode();
    }

    public String toString() {
        return "ControlsDeviceClickEvent(type=" + this.type + ", position=" + this.position + ", name=" + this.name + ")";
    }
}
