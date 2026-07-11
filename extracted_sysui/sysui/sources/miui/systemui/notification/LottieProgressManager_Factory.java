package miui.systemui.notification;

import g1.E;

/* JADX INFO: loaded from: classes4.dex */
public final class LottieProgressManager_Factory implements F0.e {
    private final G0.a autoDensityControllerProvider;
    private final G0.a pluginScopeProvider;

    public LottieProgressManager_Factory(G0.a aVar, G0.a aVar2) {
        this.pluginScopeProvider = aVar;
        this.autoDensityControllerProvider = aVar2;
    }

    public static LottieProgressManager_Factory create(G0.a aVar, G0.a aVar2) {
        return new LottieProgressManager_Factory(aVar, aVar2);
    }

    public static LottieProgressManager newInstance(E e2, E0.a aVar) {
        return new LottieProgressManager(e2, aVar);
    }

    @Override // G0.a
    public LottieProgressManager get() {
        return newInstance((E) this.pluginScopeProvider.get(), F0.d.a(this.autoDensityControllerProvider));
    }
}
