package miui.systemui.util.kotlin;

import G0.a;
import L0.g;
import g1.AbstractC0364d0;
import g1.B;
import g1.E;
import g1.F;
import java.util.concurrent.Executor;
import kotlin.jvm.internal.n;
import miui.systemui.coroutines.Dispatchers;
import miui.systemui.dagger.qualifiers.Application;
import miui.systemui.dagger.qualifiers.Background;
import miui.systemui.dagger.qualifiers.Main;
import miui.systemui.dagger.qualifiers.Plugin;
import miui.systemui.dagger.qualifiers.RunningAsPlugin;
import miui.systemui.plugins.domain.interactor.PluginLifecycleInteractor;

/* JADX INFO: loaded from: classes4.dex */
public final class CoroutinesModule {
    public static final CoroutinesModule INSTANCE = new CoroutinesModule();

    private CoroutinesModule() {
    }

    @Application
    public final E applicationScope(@RunningAsPlugin boolean z2, @Main g mainContext) {
        n.g(mainContext, "mainContext");
        if (z2) {
            throw new IllegalStateException("Application scope is only available in plugin application process.");
        }
        return F.a(mainContext);
    }

    @Background
    public final g backgroundCoroutineContext(@Background B bgDispatcher) {
        n.g(bgDispatcher, "bgDispatcher");
        return bgDispatcher;
    }

    @Background
    public final B backgroundDispatcher(@Background Executor bgExecutor) {
        n.g(bgExecutor, "bgExecutor");
        return AbstractC0364d0.a(bgExecutor);
    }

    @Main
    public final g mainCoroutineContext() {
        return Dispatchers.INSTANCE.getMain().z();
    }

    @Main
    public final B mainDispatcher() {
        return Dispatchers.INSTANCE.getMain().z();
    }

    @Background
    public final E pluginBackgroundScope(@RunningAsPlugin boolean z2, @Plugin a pluginScope, @Application a applicationScope, @Background g dispatcherContext) {
        n.g(pluginScope, "pluginScope");
        n.g(applicationScope, "applicationScope");
        n.g(dispatcherContext, "dispatcherContext");
        E e2 = (E) (z2 ? pluginScope.get() : applicationScope.get());
        n.d(e2);
        return F.g(e2, dispatcherContext);
    }

    @Plugin
    public final E pluginScope(@RunningAsPlugin boolean z2, PluginLifecycleInteractor pluginLifecycleInteractor) {
        n.g(pluginLifecycleInteractor, "pluginLifecycleInteractor");
        if (z2) {
            return pluginLifecycleInteractor.getImmediateScope();
        }
        throw new IllegalStateException("Plugin scope is only available in SystemUI process.");
    }
}
