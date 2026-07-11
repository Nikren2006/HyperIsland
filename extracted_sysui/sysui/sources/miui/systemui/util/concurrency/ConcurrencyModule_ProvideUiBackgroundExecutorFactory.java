package miui.systemui.util.concurrency;

import F0.e;
import F0.i;
import java.util.concurrent.Executor;

/* JADX INFO: loaded from: classes4.dex */
public final class ConcurrencyModule_ProvideUiBackgroundExecutorFactory implements e {

    public static final class InstanceHolder {
        private static final ConcurrencyModule_ProvideUiBackgroundExecutorFactory INSTANCE = new ConcurrencyModule_ProvideUiBackgroundExecutorFactory();

        private InstanceHolder() {
        }
    }

    public static ConcurrencyModule_ProvideUiBackgroundExecutorFactory create() {
        return InstanceHolder.INSTANCE;
    }

    public static Executor provideUiBackgroundExecutor() {
        return (Executor) i.d(ConcurrencyModule.INSTANCE.provideUiBackgroundExecutor());
    }

    @Override // G0.a
    public Executor get() {
        return provideUiBackgroundExecutor();
    }
}
