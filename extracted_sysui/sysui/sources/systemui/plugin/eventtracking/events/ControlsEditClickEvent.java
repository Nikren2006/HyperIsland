package systemui.plugin.eventtracking.events;

import kotlin.jvm.internal.n;
import systemui.plugin.eventtracking.EventID;
import systemui.plugin.eventtracking.EventKey;

/* JADX INFO: loaded from: classes5.dex */
@EventID(id = "event_controls_item_edit_click")
public final class ControlsEditClickEvent {

    /* JADX INFO: renamed from: goto, reason: not valid java name */
    @EventKey(key = "go_to")
    private final String f0goto;

    public ControlsEditClickEvent(String str) {
        n.g(str, "goto");
        this.f0goto = str;
    }

    public static /* synthetic */ ControlsEditClickEvent copy$default(ControlsEditClickEvent controlsEditClickEvent, String str, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            str = controlsEditClickEvent.f0goto;
        }
        return controlsEditClickEvent.copy(str);
    }

    public final String component1() {
        return this.f0goto;
    }

    public final ControlsEditClickEvent copy(String str) {
        n.g(str, "goto");
        return new ControlsEditClickEvent(str);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof ControlsEditClickEvent) && n.c(this.f0goto, ((ControlsEditClickEvent) obj).f0goto);
    }

    public final String getGoto() {
        return this.f0goto;
    }

    public int hashCode() {
        return this.f0goto.hashCode();
    }

    public String toString() {
        return "ControlsEditClickEvent(goto=" + this.f0goto + ")";
    }
}
