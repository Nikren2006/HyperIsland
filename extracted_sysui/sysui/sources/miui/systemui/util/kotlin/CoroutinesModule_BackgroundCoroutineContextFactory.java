package miui.systemui.util.kotlin;

import F0.e;
import F0.i;
import G0.a;
import L0.g;
import g1.B;

/* JADX INFO: loaded from: classes4.dex */
public final class CoroutinesModule_BackgroundCoroutineContextFactory implements e {
    private final a bgDispatcherProvider;

    public CoroutinesModule_BackgroundCoroutineContextFactory(a aVar) {
        this.bgDispatcherProvider = aVar;
    }

    public static g backgroundCoroutineContext(B b2) {
        return (g) i.d(CoroutinesModule.INSTANCE.backgroundCoroutineContext(b2));
    }

    public static CoroutinesModule_BackgroundCoroutineContextFactory create(a aVar) {
        return new CoroutinesModule_BackgroundCoroutineContextFactory(aVar);
    }

    @Override // G0.a
    public g get() {
        return backgroundCoroutineContext((B) this.bgDispatcherProvider.get());
    }
}
