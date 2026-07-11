package miui.systemui.dagger;

import F0.i;
import android.content.Context;
import android.util.DisplayMetrics;
import android.view.WindowManager;

/* JADX INFO: loaded from: classes.dex */
public final class DependencyProvider_ProvideDisplayMetricsFactory implements F0.e {
    private final G0.a contextProvider;
    private final DependencyProvider module;
    private final G0.a windowManagerProvider;

    public DependencyProvider_ProvideDisplayMetricsFactory(DependencyProvider dependencyProvider, G0.a aVar, G0.a aVar2) {
        this.module = dependencyProvider;
        this.contextProvider = aVar;
        this.windowManagerProvider = aVar2;
    }

    public static DependencyProvider_ProvideDisplayMetricsFactory create(DependencyProvider dependencyProvider, G0.a aVar, G0.a aVar2) {
        return new DependencyProvider_ProvideDisplayMetricsFactory(dependencyProvider, aVar, aVar2);
    }

    public static DisplayMetrics provideDisplayMetrics(DependencyProvider dependencyProvider, Context context, WindowManager windowManager) {
        return (DisplayMetrics) i.d(dependencyProvider.provideDisplayMetrics(context, windowManager));
    }

    @Override // G0.a
    public DisplayMetrics get() {
        return provideDisplayMetrics(this.module, (Context) this.contextProvider.get(), (WindowManager) this.windowManagerProvider.get());
    }
}
