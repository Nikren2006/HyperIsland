package miui.systemui.dagger;

import F0.i;
import android.content.Context;

/* JADX INFO: loaded from: classes.dex */
public final class ContextModule_ProvidePluginContextFactory implements F0.e {
    private final ContextModule module;

    public ContextModule_ProvidePluginContextFactory(ContextModule contextModule) {
        this.module = contextModule;
    }

    public static ContextModule_ProvidePluginContextFactory create(ContextModule contextModule) {
        return new ContextModule_ProvidePluginContextFactory(contextModule);
    }

    public static Context providePluginContext(ContextModule contextModule) {
        return (Context) i.d(contextModule.providePluginContext());
    }

    @Override // G0.a
    public Context get() {
        return providePluginContext(this.module);
    }
}
