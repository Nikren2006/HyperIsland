package miui.systemui.util.concurrency;

import F0.e;
import F0.i;

/* JADX INFO: loaded from: classes4.dex */
public final class ConcurrencyModule_ProvideWorkerDelayableExecutorFactory implements e {

    public static final class InstanceHolder {
        private static final ConcurrencyModule_ProvideWorkerDelayableExecutorFactory INSTANCE = new ConcurrencyModule_ProvideWorkerDelayableExecutorFactory();

        private InstanceHolder() {
        }
    }

    public static ConcurrencyModule_ProvideWorkerDelayableExecutorFactory create() {
        return InstanceHolder.INSTANCE;
    }

    public static DelayableExecutor provideWorkerDelayableExecutor() {
        return (DelayableExecutor) i.d(ConcurrencyModule.INSTANCE.provideWorkerDelayableExecutor());
    }

    @Override // G0.a
    public DelayableExecutor get() {
        return provideWorkerDelayableExecutor();
    }
}
