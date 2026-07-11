package systemui.plugin.eventtracking.events;

import kotlin.jvm.internal.n;
import miui.systemui.dynamicisland.events.DynamicIslandEventsConstants;
import systemui.plugin.eventtracking.EventID;
import systemui.plugin.eventtracking.EventKey;

/* JADX INFO: loaded from: classes5.dex */
@EventID(id = "event_controls_setup_exposed")
public final class ControlsSetupExposedEvent {

    @EventKey(key = "location")
    private final String location;

    @EventKey(key = DynamicIslandEventsConstants.EventKey.KEY_APP_NAME)
    private final String name;

    public ControlsSetupExposedEvent(String location, String name) {
        n.g(location, "location");
        n.g(name, "name");
        this.location = location;
        this.name = name;
    }

    public static /* synthetic */ ControlsSetupExposedEvent copy$default(ControlsSetupExposedEvent controlsSetupExposedEvent, String str, String str2, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            str = controlsSetupExposedEvent.location;
        }
        if ((i2 & 2) != 0) {
            str2 = controlsSetupExposedEvent.name;
        }
        return controlsSetupExposedEvent.copy(str, str2);
    }

    public final String component1() {
        return this.location;
    }

    public final String component2() {
        return this.name;
    }

    public final ControlsSetupExposedEvent copy(String location, String name) {
        n.g(location, "location");
        n.g(name, "name");
        return new ControlsSetupExposedEvent(location, name);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof ControlsSetupExposedEvent)) {
            return false;
        }
        ControlsSetupExposedEvent controlsSetupExposedEvent = (ControlsSetupExposedEvent) obj;
        return n.c(this.location, controlsSetupExposedEvent.location) && n.c(this.name, controlsSetupExposedEvent.name);
    }

    public final String getLocation() {
        return this.location;
    }

    public final String getName() {
        return this.name;
    }

    public int hashCode() {
        return (this.location.hashCode() * 31) + this.name.hashCode();
    }

    public String toString() {
        return "ControlsSetupExposedEvent(location=" + this.location + ", name=" + this.name + ")";
    }
}
