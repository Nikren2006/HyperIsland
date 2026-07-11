package miui.systemui.dagger;

import F0.i;
import com.android.internal.logging.MetricsLogger;

/* JADX INFO: loaded from: classes.dex */
public final class DependencyProvider_ProvideMetricsLoggerFactory implements F0.e {
    private final DependencyProvider module;

    public DependencyProvider_ProvideMetricsLoggerFactory(DependencyProvider dependencyProvider) {
        this.module = dependencyProvider;
    }

    public static DependencyProvider_ProvideMetricsLoggerFactory create(DependencyProvider dependencyProvider) {
        return new DependencyProvider_ProvideMetricsLoggerFactory(dependencyProvider);
    }

    public static MetricsLogger provideMetricsLogger(DependencyProvider dependencyProvider) {
        return (MetricsLogger) i.d(dependencyProvider.provideMetricsLogger());
    }

    @Override // G0.a
    public MetricsLogger get() {
        return provideMetricsLogger(this.module);
    }
}
