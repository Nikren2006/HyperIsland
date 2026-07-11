package miui.systemui.util.concurrency;

import F0.e;
import F0.i;

/* JADX INFO: loaded from: classes4.dex */
public final class ConcurrencyModule_ProvideDelayableExecutorFactory implements e {

    public static final class InstanceHolder {
        private static final ConcurrencyModule_ProvideDelayableExecutorFactory INSTANCE = new ConcurrencyModule_ProvideDelayableExecutorFactory();

        private InstanceHolder() {
        }
    }

    public static ConcurrencyModule_ProvideDelayableExecutorFactory create() {
        return InstanceHolder.INSTANCE;
    }

    public static DelayableExecutor provideDelayableExecutor() {
        return (DelayableExecutor) i.d(ConcurrencyModule.INSTANCE.provideDelayableExecutor());
    }

    @Override // G0.a
    public DelayableExecutor get() {
        return provideDelayableExecutor();
    }
}
