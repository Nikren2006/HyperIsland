package systemui.plugin.eventtracking.trackers;

import E0.b;
import G0.a;
import systemui.plugin.eventtracking.EventTracker;

/* JADX INFO: loaded from: classes5.dex */
public final class BaseEventTracker_MembersInjector implements b {
    private final a p0Provider;

    public BaseEventTracker_MembersInjector(a aVar) {
        this.p0Provider = aVar;
    }

    public static b create(a aVar) {
        return new BaseEventTracker_MembersInjector(aVar);
    }

    public static void injectSetEventTracker(BaseEventTracker baseEventTracker, EventTracker eventTracker) {
        baseEventTracker.setEventTracker(eventTracker);
    }

    public void injectMembers(BaseEventTracker baseEventTracker) {
        injectSetEventTracker(baseEventTracker, (EventTracker) this.p0Provider.get());
    }
}
