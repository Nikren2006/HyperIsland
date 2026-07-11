package miui.systemui.controlcenter.windowview;

import java.util.concurrent.Executor;
import miui.systemui.broadcast.BroadcastDispatcher;

/* JADX INFO: loaded from: classes.dex */
public final class ControlCenterScreenshot_Factory implements F0.e {
    private final G0.a bgExecutorProvider;
    private final G0.a broadcastDispatcherProvider;
    private final G0.a windowViewProvider;

    public ControlCenterScreenshot_Factory(G0.a aVar, G0.a aVar2, G0.a aVar3) {
        this.windowViewProvider = aVar;
        this.broadcastDispatcherProvider = aVar2;
        this.bgExecutorProvider = aVar3;
    }

    public static ControlCenterScreenshot_Factory create(G0.a aVar, G0.a aVar2, G0.a aVar3) {
        return new ControlCenterScreenshot_Factory(aVar, aVar2, aVar3);
    }

    public static ControlCenterScreenshot newInstance(ControlCenterWindowViewImpl controlCenterWindowViewImpl, BroadcastDispatcher broadcastDispatcher, Executor executor) {
        return new ControlCenterScreenshot(controlCenterWindowViewImpl, broadcastDispatcher, executor);
    }

    @Override // G0.a
    public ControlCenterScreenshot get() {
        return newInstance((ControlCenterWindowViewImpl) this.windowViewProvider.get(), (BroadcastDispatcher) this.broadcastDispatcherProvider.get(), (Executor) this.bgExecutorProvider.get());
    }
}
