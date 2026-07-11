package miui.systemui.controlcenter.policy;

import F0.e;
import android.content.Context;
import android.os.Handler;
import java.util.concurrent.Executor;
import miui.systemui.broadcast.BroadcastDispatcher;
import miui.systemui.util.SystemUIResourcesHelper;

/* JADX INFO: loaded from: classes.dex */
public final class SecurityController_Factory implements e {
    private final G0.a bgExecutorProvider;
    private final G0.a bgHandlerProvider;
    private final G0.a broadcastDispatcherProvider;
    private final G0.a contextProvider;
    private final G0.a systemUIResourcesHelperProvider;

    public SecurityController_Factory(G0.a aVar, G0.a aVar2, G0.a aVar3, G0.a aVar4, G0.a aVar5) {
        this.contextProvider = aVar;
        this.bgHandlerProvider = aVar2;
        this.bgExecutorProvider = aVar3;
        this.broadcastDispatcherProvider = aVar4;
        this.systemUIResourcesHelperProvider = aVar5;
    }

    public static SecurityController_Factory create(G0.a aVar, G0.a aVar2, G0.a aVar3, G0.a aVar4, G0.a aVar5) {
        return new SecurityController_Factory(aVar, aVar2, aVar3, aVar4, aVar5);
    }

    public static SecurityController newInstance(Context context, Handler handler, Executor executor, BroadcastDispatcher broadcastDispatcher, SystemUIResourcesHelper systemUIResourcesHelper) {
        return new SecurityController(context, handler, executor, broadcastDispatcher, systemUIResourcesHelper);
    }

    @Override // G0.a
    public SecurityController get() {
        return newInstance((Context) this.contextProvider.get(), (Handler) this.bgHandlerProvider.get(), (Executor) this.bgExecutorProvider.get(), (BroadcastDispatcher) this.broadcastDispatcherProvider.get(), (SystemUIResourcesHelper) this.systemUIResourcesHelperProvider.get());
    }
}
