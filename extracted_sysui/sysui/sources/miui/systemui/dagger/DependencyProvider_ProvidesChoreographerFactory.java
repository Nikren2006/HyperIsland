package miui.systemui.dagger;

import F0.i;
import android.view.Choreographer;

/* JADX INFO: loaded from: classes.dex */
public final class DependencyProvider_ProvidesChoreographerFactory implements F0.e {
    private final DependencyProvider module;

    public DependencyProvider_ProvidesChoreographerFactory(DependencyProvider dependencyProvider) {
        this.module = dependencyProvider;
    }

    public static DependencyProvider_ProvidesChoreographerFactory create(DependencyProvider dependencyProvider) {
        return new DependencyProvider_ProvidesChoreographerFactory(dependencyProvider);
    }

    public static Choreographer providesChoreographer(DependencyProvider dependencyProvider) {
        return (Choreographer) i.d(dependencyProvider.providesChoreographer());
    }

    @Override // G0.a
    public Choreographer get() {
        return providesChoreographer(this.module);
    }
}
