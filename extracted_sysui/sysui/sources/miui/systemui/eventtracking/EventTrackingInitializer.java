package miui.systemui.eventtracking;

import miui.systemui.dagger.PluginComponentInitializer;
import systemui.plugin.eventtracking.trackers.BaseEventTracker;

/* JADX INFO: loaded from: classes3.dex */
public final class EventTrackingInitializer {
    public static final EventTrackingInitializer INSTANCE = new EventTrackingInitializer();

    private EventTrackingInitializer() {
    }

    public final void create() {
        PluginComponentInitializer.getPluginComponent().inject(BaseEventTracker.Companion.get());
    }

    public final void destroy() {
        BaseEventTracker.Companion.destroy();
    }
}
