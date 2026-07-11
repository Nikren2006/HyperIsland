package systemui.plugin.eventtracking.trackers;

import systemui.plugin.eventtracking.events.GlobalActionDismissEvent;
import systemui.plugin.eventtracking.events.GlobalActionEvent;

/* JADX INFO: loaded from: classes5.dex */
public class GlobalActionEventTracker {
    private GlobalActionEventTracker() {
    }

    public static void trackDismissReason(String str) {
        BaseEventTracker.get().track(new GlobalActionDismissEvent(str));
    }

    public static void trackMoveAction(String str) {
        BaseEventTracker.get().track(new GlobalActionEvent(str));
    }
}
