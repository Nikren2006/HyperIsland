package miui.systemui.util.concurrency;

import F0.e;
import F0.i;
import java.util.concurrent.Executor;

/* JADX INFO: loaded from: classes4.dex */
public final class ConcurrencyModule_ProvideBackgroundExecutorFactory implements e {

    public static final class InstanceHolder {
        private static final ConcurrencyModule_ProvideBackgroundExecutorFactory INSTANCE = new ConcurrencyModule_ProvideBackgroundExecutorFactory();

        private InstanceHolder() {
        }
    }

    public static ConcurrencyModule_ProvideBackgroundExecutorFactory create() {
        return InstanceHolder.INSTANCE;
    }

    public static Executor provideBackgroundExecutor() {
        return (Executor) i.d(ConcurrencyModule.INSTANCE.provideBackgroundExecutor());
    }

    @Override // G0.a
    public Executor get() {
        return provideBackgroundExecutor();
    }
}
