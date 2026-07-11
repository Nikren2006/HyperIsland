package miui.systemui.util.concurrency;

import F0.e;
import F0.i;
import java.util.concurrent.Executor;

/* JADX INFO: loaded from: classes4.dex */
public final class ConcurrencyModule_ProvideWorkerExecutorFactory implements e {

    public static final class InstanceHolder {
        private static final ConcurrencyModule_ProvideWorkerExecutorFactory INSTANCE = new ConcurrencyModule_ProvideWorkerExecutorFactory();

        private InstanceHolder() {
        }
    }

    public static ConcurrencyModule_ProvideWorkerExecutorFactory create() {
        return InstanceHolder.INSTANCE;
    }

    public static Executor provideWorkerExecutor() {
        return (Executor) i.d(ConcurrencyModule.INSTANCE.provideWorkerExecutor());
    }

    @Override // G0.a
    public Executor get() {
        return provideWorkerExecutor();
    }
}
