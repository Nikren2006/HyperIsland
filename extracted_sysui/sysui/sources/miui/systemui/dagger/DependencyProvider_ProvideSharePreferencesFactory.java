package miui.systemui.dagger;

import F0.i;
import android.content.Context;
import android.content.SharedPreferences;

/* JADX INFO: loaded from: classes.dex */
public final class DependencyProvider_ProvideSharePreferencesFactory implements F0.e {
    private final G0.a contextProvider;
    private final DependencyProvider module;

    public DependencyProvider_ProvideSharePreferencesFactory(DependencyProvider dependencyProvider, G0.a aVar) {
        this.module = dependencyProvider;
        this.contextProvider = aVar;
    }

    public static DependencyProvider_ProvideSharePreferencesFactory create(DependencyProvider dependencyProvider, G0.a aVar) {
        return new DependencyProvider_ProvideSharePreferencesFactory(dependencyProvider, aVar);
    }

    public static SharedPreferences provideSharePreferences(DependencyProvider dependencyProvider, Context context) {
        return (SharedPreferences) i.d(dependencyProvider.provideSharePreferences(context));
    }

    @Override // G0.a
    public SharedPreferences get() {
        return provideSharePreferences(this.module, (Context) this.contextProvider.get());
    }
}
