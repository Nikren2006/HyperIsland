package miui.systemui.settings;

import F0.e;
import miui.systemui.broadcast.BroadcastDispatcher;

/* JADX INFO: loaded from: classes4.dex */
public final class CurrentUserObservable_Factory implements e {
    private final G0.a broadcastDispatcherProvider;

    public CurrentUserObservable_Factory(G0.a aVar) {
        this.broadcastDispatcherProvider = aVar;
    }

    public static CurrentUserObservable_Factory create(G0.a aVar) {
        return new CurrentUserObservable_Factory(aVar);
    }

    public static CurrentUserObservable newInstance(BroadcastDispatcher broadcastDispatcher) {
        return new CurrentUserObservable(broadcastDispatcher);
    }

    @Override // G0.a
    public CurrentUserObservable get() {
        return newInstance((BroadcastDispatcher) this.broadcastDispatcherProvider.get());
    }
}
