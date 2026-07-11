package miui.systemui.plugins.domain.interactor;

import H0.k;
import H0.s;
import L0.d;
import L0.g;
import M0.c;
import N0.f;
import N0.l;
import android.content.Context;
import android.content.pm.PackageManager;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LifecycleRegistry;
import com.android.systemui.plugins.Plugin;
import g1.AbstractC0369g;
import g1.E;
import g1.F;
import j1.I;
import j1.InterfaceC0419g;
import java.util.Set;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.n;
import miui.systemui.coroutines.CoroutineScopeKt;
import miui.systemui.dagger.PluginComponentInitializer;
import miui.systemui.dagger.qualifiers.Main;
import miui.systemui.dagger.qualifiers.SystemUI;
import miui.systemui.eventtracking.EventTrackingInitializer;
import miui.systemui.plugins.data.repository.PluginInstancesRepositoryImpl;
import miui.systemui.util.ContextUtils;
import miui.systemui.util.DeviceUtils;
import miui.systemui.util.SystemUIResourcesHelper;
import miui.systemui.widget.SmoothRoundDrawable;
import systemui.plugin.eventtracking.utils.EventsUtils;

/* JADX INFO: loaded from: classes4.dex */
public final class PluginLifecycleInteractor implements LifecycleOwner {
    private final E immediateScope;
    private final LifecycleRegistry lifecycle;
    private final Context pluginContext;
    private final PluginInstancesRepositoryImpl pluginInstancesRepository;
    private final E scope;
    private final Context sysuiContext;

    /* JADX INFO: renamed from: miui.systemui.plugins.domain.interactor.PluginLifecycleInteractor$listenForPluginInstances$1, reason: invalid class name */
    @f(c = "miui.systemui.plugins.domain.interactor.PluginLifecycleInteractor$listenForPluginInstances$1", f = "PluginLifecycleInteractor.kt", l = {55}, m = "invokeSuspend")
    public static final class AnonymousClass1 extends l implements Function2 {
        int label;

        public AnonymousClass1(d dVar) {
            super(2, dVar);
        }

        @Override // N0.a
        public final d create(Object obj, d dVar) {
            return PluginLifecycleInteractor.this.new AnonymousClass1(dVar);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(E e2, d dVar) {
            return ((AnonymousClass1) create(e2, dVar)).invokeSuspend(s.f314a);
        }

        @Override // N0.a
        public final Object invokeSuspend(Object obj) throws Throwable {
            Object objC = c.c();
            int i2 = this.label;
            if (i2 == 0) {
                k.b(obj);
                I plugins = PluginLifecycleInteractor.this.pluginInstancesRepository.getPlugins();
                final PluginLifecycleInteractor pluginLifecycleInteractor = PluginLifecycleInteractor.this;
                InterfaceC0419g interfaceC0419g = new InterfaceC0419g() { // from class: miui.systemui.plugins.domain.interactor.PluginLifecycleInteractor.listenForPluginInstances.1.1
                    @Override // j1.InterfaceC0419g
                    public final Object emit(Set<? extends Plugin> set, d dVar) throws PackageManager.NameNotFoundException {
                        if (set.size() > 0) {
                            pluginLifecycleInteractor.onCreated();
                            pluginLifecycleInteractor.getLifecycle().setCurrentState(Lifecycle.State.CREATED);
                        }
                        if (set.size() == 0 && pluginLifecycleInteractor.getLifecycle().getCurrentState().compareTo(Lifecycle.State.CREATED) >= 0) {
                            pluginLifecycleInteractor.getLifecycle().setCurrentState(Lifecycle.State.DESTROYED);
                            pluginLifecycleInteractor.onDestroyed();
                        }
                        return s.f314a;
                    }
                };
                this.label = 1;
                if (plugins.collect(interfaceC0419g, this) == objC) {
                    return objC;
                }
            } else {
                if (i2 != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                k.b(obj);
            }
            throw new H0.c();
        }
    }

    public PluginLifecycleInteractor(@Main g mainContext, @miui.systemui.dagger.qualifiers.Plugin Context pluginContext, @SystemUI Context sysuiContext, PluginInstancesRepositoryImpl pluginInstancesRepository) {
        n.g(mainContext, "mainContext");
        n.g(pluginContext, "pluginContext");
        n.g(sysuiContext, "sysuiContext");
        n.g(pluginInstancesRepository, "pluginInstancesRepository");
        this.pluginContext = pluginContext;
        this.sysuiContext = sysuiContext;
        this.pluginInstancesRepository = pluginInstancesRepository;
        this.lifecycle = new LifecycleRegistry(this);
        E eMainScope = CoroutineScopeKt.MainScope();
        this.scope = eMainScope;
        this.immediateScope = F.g(eMainScope, mainContext);
        listenForPluginInstances();
    }

    private final void listenForPluginInstances() {
        AbstractC0369g.b(this.immediateScope, null, null, new AnonymousClass1(null), 3, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void onCreated() throws PackageManager.NameNotFoundException {
        ContextUtils.INSTANCE.clearDrawableConstructorCache();
        EventTrackingInitializer.INSTANCE.create();
        EventsUtils.INSTANCE.init(this.pluginContext);
        DeviceUtils deviceUtils = DeviceUtils.INSTANCE;
        SystemUIResourcesHelper systemUIResourcesHelper = PluginComponentInitializer.getPluginComponent().getSystemUIResourcesHelper();
        n.f(systemUIResourcesHelper, "getSystemUIResourcesHelper(...)");
        deviceUtils.updateForceMiddle(systemUIResourcesHelper);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void onDestroyed() {
        EventTrackingInitializer.INSTANCE.destroy();
        SmoothRoundDrawable.Companion.clearCache();
        F.d(this.scope, "Plugin component destroyed.", null, 2, null);
        PluginComponentInitializer.INSTANCE.destroy();
    }

    public final E getImmediateScope() {
        return this.immediateScope;
    }

    public final E getScope() {
        return this.scope;
    }

    @Override // androidx.lifecycle.LifecycleOwner
    public LifecycleRegistry getLifecycle() {
        return this.lifecycle;
    }
}
