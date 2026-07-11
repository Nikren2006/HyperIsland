package systemui.plugin.eventtracking.trackers;

import H0.s;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.n;
import systemui.plugin.eventtracking.EventTracker;

/* JADX INFO: loaded from: classes5.dex */
public class BaseEventTracker {
    public static final Companion Companion = new Companion(null);
    private static final String DEPRECATED_MESSAGE = "Use EventTracker with dagger injection instead";
    private EventTracker eventTracker;

    public static final class Companion extends BaseEventTracker {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final void destroy() {
            EventTracker eventTracker = getEventTracker();
            if (eventTracker != null) {
                eventTracker.destroy();
            }
            setEventTracker(null);
        }

        public final Companion get() {
            return this;
        }

        private Companion() {
        }
    }

    public static final Companion get() {
        return Companion.get();
    }

    public static /* synthetic */ void getEventTracker$annotations() {
    }

    public final EventTracker getEventTracker() {
        return this.eventTracker;
    }

    public final void setEventTracker(EventTracker eventTracker) {
        this.eventTracker = eventTracker;
    }

    public final s track(Object event) {
        n.g(event, "event");
        EventTracker eventTracker = this.eventTracker;
        if (eventTracker == null) {
            return null;
        }
        eventTracker.track(event);
        return s.f314a;
    }

    public final s trackSupportGlobal(Object event) {
        n.g(event, "event");
        EventTracker eventTracker = this.eventTracker;
        if (eventTracker == null) {
            return null;
        }
        eventTracker.track(event);
        return s.f314a;
    }
}
