package systemui.plugin.eventtracking.events;

import kotlin.jvm.internal.n;
import miui.systemui.dynamicisland.events.DynamicIslandEventsConstants;
import systemui.plugin.eventtracking.EventID;
import systemui.plugin.eventtracking.EventKey;

/* JADX INFO: loaded from: classes5.dex */
@EventID(id = "event_controls_setup_dismiss")
public final class ControlsSetupExposedDismissClickEvent {

    @EventKey(key = "location")
    private final String location;

    @EventKey(key = DynamicIslandEventsConstants.EventKey.KEY_APP_NAME)
    private final String name;

    public ControlsSetupExposedDismissClickEvent(String location, String name) {
        n.g(location, "location");
        n.g(name, "name");
        this.location = location;
        this.name = name;
    }

    public static /* synthetic */ ControlsSetupExposedDismissClickEvent copy$default(ControlsSetupExposedDismissClickEvent controlsSetupExposedDismissClickEvent, String str, String str2, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            str = controlsSetupExposedDismissClickEvent.location;
        }
        if ((i2 & 2) != 0) {
            str2 = controlsSetupExposedDismissClickEvent.name;
        }
        return controlsSetupExposedDismissClickEvent.copy(str, str2);
    }

    public final String component1() {
        return this.location;
    }

    public final String component2() {
        return this.name;
    }

    public final ControlsSetupExposedDismissClickEvent copy(String location, String name) {
        n.g(location, "location");
        n.g(name, "name");
        return new ControlsSetupExposedDismissClickEvent(location, name);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof ControlsSetupExposedDismissClickEvent)) {
            return false;
        }
        ControlsSetupExposedDismissClickEvent controlsSetupExposedDismissClickEvent = (ControlsSetupExposedDismissClickEvent) obj;
        return n.c(this.location, controlsSetupExposedDismissClickEvent.location) && n.c(this.name, controlsSetupExposedDismissClickEvent.name);
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
        return "ControlsSetupExposedDismissClickEvent(location=" + this.location + ", name=" + this.name + ")";
    }
}
