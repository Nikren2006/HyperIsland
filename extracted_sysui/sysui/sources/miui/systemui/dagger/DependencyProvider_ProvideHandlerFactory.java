package miui.systemui.dagger;

import F0.i;
import android.os.Handler;

/* JADX INFO: loaded from: classes.dex */
public final class DependencyProvider_ProvideHandlerFactory implements F0.e {
    private final DependencyProvider module;

    public DependencyProvider_ProvideHandlerFactory(DependencyProvider dependencyProvider) {
        this.module = dependencyProvider;
    }

    public static DependencyProvider_ProvideHandlerFactory create(DependencyProvider dependencyProvider) {
        return new DependencyProvider_ProvideHandlerFactory(dependencyProvider);
    }

    public static Handler provideHandler(DependencyProvider dependencyProvider) {
        return (Handler) i.d(dependencyProvider.provideHandler());
    }

    @Override // G0.a
    public Handler get() {
        return provideHandler(this.module);
    }
}
