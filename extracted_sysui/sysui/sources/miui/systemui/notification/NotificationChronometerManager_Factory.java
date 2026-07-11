package miui.systemui.notification;

import g1.E;

/* JADX INFO: loaded from: classes4.dex */
public final class NotificationChronometerManager_Factory implements F0.e {
    private final G0.a contextProvider;
    private final G0.a pluginScopeProvider;

    public NotificationChronometerManager_Factory(G0.a aVar, G0.a aVar2) {
        this.pluginScopeProvider = aVar;
        this.contextProvider = aVar2;
    }

    public static NotificationChronometerManager_Factory create(G0.a aVar, G0.a aVar2) {
        return new NotificationChronometerManager_Factory(aVar, aVar2);
    }

    public static NotificationChronometerManager newInstance(E e2, E0.a aVar) {
        return new NotificationChronometerManager(e2, aVar);
    }

    @Override // G0.a
    public NotificationChronometerManager get() {
        return newInstance((E) this.pluginScopeProvider.get(), F0.d.a(this.contextProvider));
    }
}
