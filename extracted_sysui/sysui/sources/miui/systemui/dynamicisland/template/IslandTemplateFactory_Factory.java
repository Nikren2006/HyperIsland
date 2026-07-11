package miui.systemui.dynamicisland.template;

import F0.e;
import G0.a;
import g1.E;
import miui.systemui.dynamicisland.template.IslandTemplateBuilder;

/* JADX INFO: loaded from: classes3.dex */
public final class IslandTemplateFactory_Factory implements e {
    private final a islandTemplateBuilderFactoryProvider;
    private final a scopeProvider;

    public IslandTemplateFactory_Factory(a aVar, a aVar2) {
        this.scopeProvider = aVar;
        this.islandTemplateBuilderFactoryProvider = aVar2;
    }

    public static IslandTemplateFactory_Factory create(a aVar, a aVar2) {
        return new IslandTemplateFactory_Factory(aVar, aVar2);
    }

    public static IslandTemplateFactory newInstance(E e2, IslandTemplateBuilder.Factory factory) {
        return new IslandTemplateFactory(e2, factory);
    }

    @Override // G0.a
    public IslandTemplateFactory get() {
        return newInstance((E) this.scopeProvider.get(), (IslandTemplateBuilder.Factory) this.islandTemplateBuilderFactoryProvider.get());
    }
}
