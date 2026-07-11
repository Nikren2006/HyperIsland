package miui.systemui.dagger;

/* JADX INFO: loaded from: classes.dex */
public final class InjectionInflationControllerImpl_Factory implements F0.e {
    private final G0.a pluginComponentProvider;

    public InjectionInflationControllerImpl_Factory(G0.a aVar) {
        this.pluginComponentProvider = aVar;
    }

    public static InjectionInflationControllerImpl_Factory create(G0.a aVar) {
        return new InjectionInflationControllerImpl_Factory(aVar);
    }

    public static InjectionInflationControllerImpl newInstance(PluginComponent pluginComponent) {
        return new InjectionInflationControllerImpl(pluginComponent);
    }

    @Override // G0.a
    public InjectionInflationControllerImpl get() {
        return newInstance((PluginComponent) this.pluginComponentProvider.get());
    }
}
