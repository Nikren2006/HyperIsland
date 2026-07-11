package miui.systemui.util.kotlin;

import F0.e;
import F0.i;
import G0.a;
import g1.B;
import java.util.concurrent.Executor;

/* JADX INFO: loaded from: classes4.dex */
public final class CoroutinesModule_BackgroundDispatcherFactory implements e {
    private final a bgExecutorProvider;

    public CoroutinesModule_BackgroundDispatcherFactory(a aVar) {
        this.bgExecutorProvider = aVar;
    }

    public static B backgroundDispatcher(Executor executor) {
        return (B) i.d(CoroutinesModule.INSTANCE.backgroundDispatcher(executor));
    }

    public static CoroutinesModule_BackgroundDispatcherFactory create(a aVar) {
        return new CoroutinesModule_BackgroundDispatcherFactory(aVar);
    }

    @Override // G0.a
    public B get() {
        return backgroundDispatcher((Executor) this.bgExecutorProvider.get());
    }
}
