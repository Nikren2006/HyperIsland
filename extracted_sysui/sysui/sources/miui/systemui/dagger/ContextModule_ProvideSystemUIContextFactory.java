package miui.systemui.dagger;

import F0.i;
import android.content.Context;

/* JADX INFO: loaded from: classes.dex */
public final class ContextModule_ProvideSystemUIContextFactory implements F0.e {
    private final ContextModule module;

    public ContextModule_ProvideSystemUIContextFactory(ContextModule contextModule) {
        this.module = contextModule;
    }

    public static ContextModule_ProvideSystemUIContextFactory create(ContextModule contextModule) {
        return new ContextModule_ProvideSystemUIContextFactory(contextModule);
    }

    public static Context provideSystemUIContext(ContextModule contextModule) {
        return (Context) i.d(contextModule.provideSystemUIContext());
    }

    @Override // G0.a
    public Context get() {
        return provideSystemUIContext(this.module);
    }
}
