package systemui.plugin.eventtracking;

import F0.e;
import android.content.Context;

/* JADX INFO: loaded from: classes5.dex */
public final class EventTracker_Factory implements e {
    private final G0.a ctxProvider;

    public EventTracker_Factory(G0.a aVar) {
        this.ctxProvider = aVar;
    }

    public static EventTracker_Factory create(G0.a aVar) {
        return new EventTracker_Factory(aVar);
    }

    public static EventTracker newInstance(Context context) {
        return new EventTracker(context);
    }

    @Override // G0.a
    public EventTracker get() {
        return newInstance((Context) this.ctxProvider.get());
    }
}
