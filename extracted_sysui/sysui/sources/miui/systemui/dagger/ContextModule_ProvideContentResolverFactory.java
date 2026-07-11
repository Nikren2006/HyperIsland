package miui.systemui.dagger;

import F0.i;
import android.content.ContentResolver;
import android.content.Context;

/* JADX INFO: loaded from: classes.dex */
public final class ContextModule_ProvideContentResolverFactory implements F0.e {
    private final G0.a contextProvider;
    private final ContextModule module;

    public ContextModule_ProvideContentResolverFactory(ContextModule contextModule, G0.a aVar) {
        this.module = contextModule;
        this.contextProvider = aVar;
    }

    public static ContextModule_ProvideContentResolverFactory create(ContextModule contextModule, G0.a aVar) {
        return new ContextModule_ProvideContentResolverFactory(contextModule, aVar);
    }

    public static ContentResolver provideContentResolver(ContextModule contextModule, Context context) {
        return (ContentResolver) i.d(contextModule.provideContentResolver(context));
    }

    @Override // G0.a
    public ContentResolver get() {
        return provideContentResolver(this.module, (Context) this.contextProvider.get());
    }
}
