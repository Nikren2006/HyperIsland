package miui.systemui.dynamicisland.window.content;

import F0.f;
import miui.systemui.dynamicisland.window.content.DynamicIslandBaseContentViewController;

/* JADX INFO: loaded from: classes3.dex */
public final class DynamicIslandBaseContentViewController_FactoryImpl_Impl implements DynamicIslandBaseContentViewController.FactoryImpl {
    private final C0645DynamicIslandBaseContentViewController_Factory<DynamicIslandBaseContentView> delegateFactory;

    public DynamicIslandBaseContentViewController_FactoryImpl_Impl(C0645DynamicIslandBaseContentViewController_Factory<DynamicIslandBaseContentView> c0645DynamicIslandBaseContentViewController_Factory) {
        this.delegateFactory = c0645DynamicIslandBaseContentViewController_Factory;
    }

    @Override // miui.systemui.dynamicisland.window.content.DynamicIslandBaseContentViewController.Factory
    public DynamicIslandBaseContentViewController<DynamicIslandBaseContentView> create(DynamicIslandBaseContentView dynamicIslandBaseContentView) {
        return this.delegateFactory.get(dynamicIslandBaseContentView);
    }

    public static <T extends DynamicIslandBaseContentView> G0.a create(C0645DynamicIslandBaseContentViewController_Factory<DynamicIslandBaseContentView> c0645DynamicIslandBaseContentViewController_Factory) {
        return f.a(new DynamicIslandBaseContentViewController_FactoryImpl_Impl(c0645DynamicIslandBaseContentViewController_Factory));
    }
}
