package miui.systemui.util.concurrency;

import F0.e;
import F0.i;
import android.content.Context;
import java.util.concurrent.Executor;

/* JADX INFO: loaded from: classes4.dex */
public final class ConcurrencyModule_ProvideMainExecutorFactory implements e {
    private final G0.a contextProvider;

    public ConcurrencyModule_ProvideMainExecutorFactory(G0.a aVar) {
        this.contextProvider = aVar;
    }

    public static ConcurrencyModule_ProvideMainExecutorFactory create(G0.a aVar) {
        return new ConcurrencyModule_ProvideMainExecutorFactory(aVar);
    }

    public static Executor provideMainExecutor(Context context) {
        return (Executor) i.d(ConcurrencyModule.INSTANCE.provideMainExecutor(context));
    }

    @Override // G0.a
    public Executor get() {
        return provideMainExecutor((Context) this.contextProvider.get());
    }
}
