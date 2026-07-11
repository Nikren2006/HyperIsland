package miui.systemui.dagger;

import F0.i;
import android.content.Context;

/* JADX INFO: loaded from: classes.dex */
public final class ContextModule_ProvideContextFactory implements F0.e {
    private final ContextModule module;

    public ContextModule_ProvideContextFactory(ContextModule contextModule) {
        this.module = contextModule;
    }

    public static ContextModule_ProvideContextFactory create(ContextModule contextModule) {
        return new ContextModule_ProvideContextFactory(contextModule);
    }

    public static Context provideContext(ContextModule contextModule) {
        return (Context) i.d(contextModule.provideContext());
    }

    @Override // G0.a
    public Context get() {
        return provideContext(this.module);
    }
}
