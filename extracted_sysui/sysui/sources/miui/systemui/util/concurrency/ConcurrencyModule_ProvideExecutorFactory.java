package miui.systemui.util.concurrency;

import F0.e;
import F0.i;
import java.util.concurrent.Executor;

/* JADX INFO: loaded from: classes4.dex */
public final class ConcurrencyModule_ProvideExecutorFactory implements e {

    public static final class InstanceHolder {
        private static final ConcurrencyModule_ProvideExecutorFactory INSTANCE = new ConcurrencyModule_ProvideExecutorFactory();

        private InstanceHolder() {
        }
    }

    public static ConcurrencyModule_ProvideExecutorFactory create() {
        return InstanceHolder.INSTANCE;
    }

    public static Executor provideExecutor() {
        return (Executor) i.d(ConcurrencyModule.INSTANCE.provideExecutor());
    }

    @Override // G0.a
    public Executor get() {
        return provideExecutor();
    }
}
