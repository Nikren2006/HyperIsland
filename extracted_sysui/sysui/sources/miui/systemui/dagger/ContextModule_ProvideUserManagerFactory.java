package miui.systemui.dagger;

import F0.i;
import android.content.Context;
import android.os.UserManager;

/* JADX INFO: loaded from: classes.dex */
public final class ContextModule_ProvideUserManagerFactory implements F0.e {
    private final G0.a contextProvider;
    private final ContextModule module;

    public ContextModule_ProvideUserManagerFactory(ContextModule contextModule, G0.a aVar) {
        this.module = contextModule;
        this.contextProvider = aVar;
    }

    public static ContextModule_ProvideUserManagerFactory create(ContextModule contextModule, G0.a aVar) {
        return new ContextModule_ProvideUserManagerFactory(contextModule, aVar);
    }

    public static UserManager provideUserManager(ContextModule contextModule, Context context) {
        return (UserManager) i.d(contextModule.provideUserManager(context));
    }

    @Override // G0.a
    public UserManager get() {
        return provideUserManager(this.module, (Context) this.contextProvider.get());
    }
}
