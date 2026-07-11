package miui.systemui.dagger;

import F0.i;
import android.content.Context;
import java.util.Optional;

/* JADX INFO: loaded from: classes.dex */
public final class ContextModule_ProvideOptionalSystemUIContextFactory implements F0.e {
    private final ContextModule module;

    public ContextModule_ProvideOptionalSystemUIContextFactory(ContextModule contextModule) {
        this.module = contextModule;
    }

    public static ContextModule_ProvideOptionalSystemUIContextFactory create(ContextModule contextModule) {
        return new ContextModule_ProvideOptionalSystemUIContextFactory(contextModule);
    }

    public static Optional<Context> provideOptionalSystemUIContext(ContextModule contextModule) {
        return (Optional) i.d(contextModule.provideOptionalSystemUIContext());
    }

    @Override // G0.a
    public Optional<Context> get() {
        return provideOptionalSystemUIContext(this.module);
    }
}
