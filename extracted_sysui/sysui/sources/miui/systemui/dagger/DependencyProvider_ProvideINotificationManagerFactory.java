package miui.systemui.dagger;

import F0.i;
import android.app.INotificationManager;

/* JADX INFO: loaded from: classes.dex */
public final class DependencyProvider_ProvideINotificationManagerFactory implements F0.e {
    private final DependencyProvider module;

    public DependencyProvider_ProvideINotificationManagerFactory(DependencyProvider dependencyProvider) {
        this.module = dependencyProvider;
    }

    public static DependencyProvider_ProvideINotificationManagerFactory create(DependencyProvider dependencyProvider) {
        return new DependencyProvider_ProvideINotificationManagerFactory(dependencyProvider);
    }

    public static INotificationManager provideINotificationManager(DependencyProvider dependencyProvider) {
        return (INotificationManager) i.d(dependencyProvider.provideINotificationManager());
    }

    @Override // G0.a
    public INotificationManager get() {
        return provideINotificationManager(this.module);
    }
}
