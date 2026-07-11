package miui.systemui.util.concurrency;

import F0.e;
import F0.i;

/* JADX INFO: loaded from: classes4.dex */
public final class ConcurrencyModule_ProvideMainDelayableExecutorFactory implements e {

    public static final class InstanceHolder {
        private static final ConcurrencyModule_ProvideMainDelayableExecutorFactory INSTANCE = new ConcurrencyModule_ProvideMainDelayableExecutorFactory();

        private InstanceHolder() {
        }
    }

    public static ConcurrencyModule_ProvideMainDelayableExecutorFactory create() {
        return InstanceHolder.INSTANCE;
    }

    public static DelayableExecutor provideMainDelayableExecutor() {
        return (DelayableExecutor) i.d(ConcurrencyModule.INSTANCE.provideMainDelayableExecutor());
    }

    @Override // G0.a
    public DelayableExecutor get() {
        return provideMainDelayableExecutor();
    }
}
