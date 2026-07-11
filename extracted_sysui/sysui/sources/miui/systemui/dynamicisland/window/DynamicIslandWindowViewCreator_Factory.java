package miui.systemui.dynamicisland.window;

import miui.systemui.dynamicisland.dagger.DynamicIslandViewComponent;

/* JADX INFO: loaded from: classes3.dex */
public final class DynamicIslandWindowViewCreator_Factory implements F0.e {
    private final G0.a contextProvider;
    private final G0.a viewComponentFactoryProvider;

    public DynamicIslandWindowViewCreator_Factory(G0.a aVar, G0.a aVar2) {
        this.contextProvider = aVar;
        this.viewComponentFactoryProvider = aVar2;
    }

    public static DynamicIslandWindowViewCreator_Factory create(G0.a aVar, G0.a aVar2) {
        return new DynamicIslandWindowViewCreator_Factory(aVar, aVar2);
    }

    public static DynamicIslandWindowViewCreator newInstance(E0.a aVar, DynamicIslandViewComponent.Factory factory) {
        return new DynamicIslandWindowViewCreator(aVar, factory);
    }

    @Override // G0.a
    public DynamicIslandWindowViewCreator get() {
        return newInstance(F0.d.a(this.contextProvider), (DynamicIslandViewComponent.Factory) this.viewComponentFactoryProvider.get());
    }
}
