package miui.systemui.dagger;

import java.util.Map;
import java.util.Optional;

/* JADX INFO: loaded from: classes.dex */
public final class ContextComponentResolver_Factory implements F0.e {
    private final G0.a activityCreatorsProvider;
    private final G0.a broadcastReceiverCreatorsProvider;
    private final G0.a serviceCreatorsProvider;

    public ContextComponentResolver_Factory(G0.a aVar, G0.a aVar2, G0.a aVar3) {
        this.activityCreatorsProvider = aVar;
        this.serviceCreatorsProvider = aVar2;
        this.broadcastReceiverCreatorsProvider = aVar3;
    }

    public static ContextComponentResolver_Factory create(G0.a aVar, G0.a aVar2, G0.a aVar3) {
        return new ContextComponentResolver_Factory(aVar, aVar2, aVar3);
    }

    public static ContextComponentResolver newInstance(Optional<Map<Class<?>, G0.a>> optional, Optional<Map<Class<?>, G0.a>> optional2, Optional<Map<Class<?>, G0.a>> optional3) {
        return new ContextComponentResolver(optional, optional2, optional3);
    }

    @Override // G0.a
    public ContextComponentResolver get() {
        return newInstance((Optional) this.activityCreatorsProvider.get(), (Optional) this.serviceCreatorsProvider.get(), (Optional) this.broadcastReceiverCreatorsProvider.get());
    }
}
