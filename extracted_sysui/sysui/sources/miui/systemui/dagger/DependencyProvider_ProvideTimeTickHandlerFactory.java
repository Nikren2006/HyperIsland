package miui.systemui.dagger;

import F0.i;
import android.os.Handler;

/* JADX INFO: loaded from: classes.dex */
public final class DependencyProvider_ProvideTimeTickHandlerFactory implements F0.e {
    private final DependencyProvider module;

    public DependencyProvider_ProvideTimeTickHandlerFactory(DependencyProvider dependencyProvider) {
        this.module = dependencyProvider;
    }

    public static DependencyProvider_ProvideTimeTickHandlerFactory create(DependencyProvider dependencyProvider) {
        return new DependencyProvider_ProvideTimeTickHandlerFactory(dependencyProvider);
    }

    public static Handler provideTimeTickHandler(DependencyProvider dependencyProvider) {
        return (Handler) i.d(dependencyProvider.provideTimeTickHandler());
    }

    @Override // G0.a
    public Handler get() {
        return provideTimeTickHandler(this.module);
    }
}
