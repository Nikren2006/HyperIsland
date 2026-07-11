package miui.systemui.util.concurrency;

import F0.e;
import F0.i;

/* JADX INFO: loaded from: classes4.dex */
public final class ConcurrencyModule_ProvideBackgroundDelayableExecutorFactory implements e {

    public static final class InstanceHolder {
        private static final ConcurrencyModule_ProvideBackgroundDelayableExecutorFactory INSTANCE = new ConcurrencyModule_ProvideBackgroundDelayableExecutorFactory();

        private InstanceHolder() {
        }
    }

    public static ConcurrencyModule_ProvideBackgroundDelayableExecutorFactory create() {
        return InstanceHolder.INSTANCE;
    }

    public static DelayableExecutor provideBackgroundDelayableExecutor() {
        return (DelayableExecutor) i.d(ConcurrencyModule.INSTANCE.provideBackgroundDelayableExecutor());
    }

    @Override // G0.a
    public DelayableExecutor get() {
        return provideBackgroundDelayableExecutor();
    }
}
