package miui.systemui.dagger;

import F0.i;
import android.content.Context;
import android.view.LayoutInflater;

/* JADX INFO: loaded from: classes.dex */
public final class DependencyProvider_ProviderLayoutInflaterFactory implements F0.e {
    private final G0.a contextProvider;
    private final DependencyProvider module;

    public DependencyProvider_ProviderLayoutInflaterFactory(DependencyProvider dependencyProvider, G0.a aVar) {
        this.module = dependencyProvider;
        this.contextProvider = aVar;
    }

    public static DependencyProvider_ProviderLayoutInflaterFactory create(DependencyProvider dependencyProvider, G0.a aVar) {
        return new DependencyProvider_ProviderLayoutInflaterFactory(dependencyProvider, aVar);
    }

    public static LayoutInflater providerLayoutInflater(DependencyProvider dependencyProvider, Context context) {
        return (LayoutInflater) i.d(dependencyProvider.providerLayoutInflater(context));
    }

    @Override // G0.a
    public LayoutInflater get() {
        return providerLayoutInflater(this.module, (Context) this.contextProvider.get());
    }
}
