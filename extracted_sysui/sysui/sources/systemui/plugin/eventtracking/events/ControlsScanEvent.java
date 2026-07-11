package systemui.plugin.eventtracking.events;

import kotlin.jvm.internal.n;
import miui.systemui.dynamicisland.events.DynamicIslandEventsConstants;
import systemui.plugin.eventtracking.EventID;
import systemui.plugin.eventtracking.EventKey;

/* JADX INFO: loaded from: classes5.dex */
@EventID(id = "event_controls_scan")
public final class ControlsScanEvent {

    @EventKey(key = "device_count")
    private final int count;

    @EventKey(key = DynamicIslandEventsConstants.EventKey.KEY_APP_NAME)
    private final String name;

    @EventKey(key = "device_type")
    private final int type;

    public ControlsScanEvent(int i2, int i3, String name) {
        n.g(name, "name");
        this.count = i2;
        this.type = i3;
        this.name = name;
    }

    public static /* synthetic */ ControlsScanEvent copy$default(ControlsScanEvent controlsScanEvent, int i2, int i3, String str, int i4, Object obj) {
        if ((i4 & 1) != 0) {
            i2 = controlsScanEvent.count;
        }
        if ((i4 & 2) != 0) {
            i3 = controlsScanEvent.type;
        }
        if ((i4 & 4) != 0) {
            str = controlsScanEvent.name;
        }
        return controlsScanEvent.copy(i2, i3, str);
    }

    public final int component1() {
        return this.count;
    }

    public final int component2() {
        return this.type;
    }

    public final String component3() {
        return this.name;
    }

    public final ControlsScanEvent copy(int i2, int i3, String name) {
        n.g(name, "name");
        return new ControlsScanEvent(i2, i3, name);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof ControlsScanEvent)) {
            return false;
        }
        ControlsScanEvent controlsScanEvent = (ControlsScanEvent) obj;
        return this.count == controlsScanEvent.count && this.type == controlsScanEvent.type && n.c(this.name, controlsScanEvent.name);
    }

    public final int getCount() {
        return this.count;
    }

    public final String getName() {
        return this.name;
    }

    public final int getType() {
        return this.type;
    }

    public int hashCode() {
        return (((Integer.hashCode(this.count) * 31) + Integer.hashCode(this.type)) * 31) + this.name.hashCode();
    }

    public String toString() {
        return "ControlsScanEvent(count=" + this.count + ", type=" + this.type + ", name=" + this.name + ")";
    }
}
