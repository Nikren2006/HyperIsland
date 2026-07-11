package miui.systemui.dynamicisland.window;

import android.content.Context;
import g1.E;
import java.util.concurrent.Executor;
import miui.systemui.util.concurrency.DelayableExecutor;

/* JADX INFO: loaded from: classes3.dex */
public final class DynamicIslandDesktopStateController_Factory implements F0.e {
    private final G0.a bgExecutorProvider;
    private final G0.a contextProvider;
    private final G0.a scopeProvider;
    private final G0.a sysUIContextProvider;
    private final G0.a uiExecutorProvider;
    private final G0.a windowViewProvider;

    public DynamicIslandDesktopStateController_Factory(G0.a aVar, G0.a aVar2, G0.a aVar3, G0.a aVar4, G0.a aVar5, G0.a aVar6) {
        this.contextProvider = aVar;
        this.sysUIContextProvider = aVar2;
        this.windowViewProvider = aVar3;
        this.uiExecutorProvider = aVar4;
        this.scopeProvider = aVar5;
        this.bgExecutorProvider = aVar6;
    }

    public static DynamicIslandDesktopStateController_Factory create(G0.a aVar, G0.a aVar2, G0.a aVar3, G0.a aVar4, G0.a aVar5, G0.a aVar6) {
        return new DynamicIslandDesktopStateController_Factory(aVar, aVar2, aVar3, aVar4, aVar5, aVar6);
    }

    public static DynamicIslandDesktopStateController newInstance(Context context, Context context2, DynamicIslandWindowView dynamicIslandWindowView, DelayableExecutor delayableExecutor, E e2, Executor executor) {
        return new DynamicIslandDesktopStateController(context, context2, dynamicIslandWindowView, delayableExecutor, e2, executor);
    }

    @Override // G0.a
    public DynamicIslandDesktopStateController get() {
        return newInstance((Context) this.contextProvider.get(), (Context) this.sysUIContextProvider.get(), (DynamicIslandWindowView) this.windowViewProvider.get(), (DelayableExecutor) this.uiExecutorProvider.get(), (E) this.scopeProvider.get(), (Executor) this.bgExecutorProvider.get());
    }
}
