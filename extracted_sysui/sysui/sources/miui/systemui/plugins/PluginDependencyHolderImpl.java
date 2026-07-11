package miui.systemui.plugins;

import d1.InterfaceC0324c;
import g1.E;
import j1.AbstractC0420h;
import j1.I;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.n;
import miui.systemui.dagger.qualifiers.Plugin;
import miui.systemui.plugins.PluginDependencyHolder;
import miui.systemui.plugins.data.repository.PluginInstancesRepositoryImpl;

/* JADX INFO: loaded from: classes4.dex */
public final class PluginDependencyHolderImpl<T> implements PluginDependencyHolder<T> {
    public static final Companion Companion = new Companion(null);
    private static final String TAG = "PluginDependencyHolder";
    private final InterfaceC0324c clazz;
    private final I instance;
    private final PluginInstancesRepositoryImpl pluginInstancesRepository;
    private final E uiScope;

    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }

    public static final class Factory implements PluginDependencyHolder.Factory {
        private final PluginInstancesRepositoryImpl pluginInstancesRepository;
        private final E uiScope;

        public Factory(@Plugin E uiScope, PluginInstancesRepositoryImpl pluginInstancesRepository) {
            n.g(uiScope, "uiScope");
            n.g(pluginInstancesRepository, "pluginInstancesRepository");
            this.uiScope = uiScope;
            this.pluginInstancesRepository = pluginInstancesRepository;
        }

        @Override // miui.systemui.plugins.PluginDependencyHolder.Factory
        public <T> PluginDependencyHolder<T> create(InterfaceC0324c clazz) {
            n.g(clazz, "clazz");
            return new PluginDependencyHolderImpl(clazz, this.uiScope, this.pluginInstancesRepository);
        }
    }

    public PluginDependencyHolderImpl(InterfaceC0324c clazz, E uiScope, PluginInstancesRepositoryImpl pluginInstancesRepository) {
        n.g(clazz, "clazz");
        n.g(uiScope, "uiScope");
        n.g(pluginInstancesRepository, "pluginInstancesRepository");
        this.clazz = clazz;
        this.uiScope = uiScope;
        this.pluginInstancesRepository = pluginInstancesRepository;
        this.instance = AbstractC0420h.B(AbstractC0420h.D(pluginInstancesRepository.getPlugins(), new PluginDependencyHolderImpl$instance$1(this, null)), uiScope, j1.E.f4648a.c(), null);
    }

    @Override // miui.systemui.plugins.PluginDependencyHolder
    public T get() {
        return (T) getInstance().getValue();
    }

    @Override // miui.systemui.plugins.PluginDependencyHolder
    public I getInstance() {
        return this.instance;
    }

    @Override // miui.systemui.plugins.PluginDependencyHolder
    public T require() {
        T t2 = (T) getInstance().getValue();
        if (t2 != null) {
            return t2;
        }
        throw new IllegalStateException(this.clazz.c() + " was not provided by any loaded plugin.");
    }
}
