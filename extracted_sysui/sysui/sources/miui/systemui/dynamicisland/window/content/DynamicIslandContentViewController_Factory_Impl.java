package miui.systemui.dynamicisland.window.content;

import F0.f;
import miui.systemui.dynamicisland.window.content.DynamicIslandContentViewController;

/* JADX INFO: loaded from: classes3.dex */
public final class DynamicIslandContentViewController_Factory_Impl implements DynamicIslandContentViewController.Factory {
    private final C0657DynamicIslandContentViewController_Factory delegateFactory;

    public DynamicIslandContentViewController_Factory_Impl(C0657DynamicIslandContentViewController_Factory c0657DynamicIslandContentViewController_Factory) {
        this.delegateFactory = c0657DynamicIslandContentViewController_Factory;
    }

    @Override // miui.systemui.dynamicisland.window.content.DynamicIslandBaseContentViewController.Factory
    public DynamicIslandContentViewController create(DynamicIslandContentView dynamicIslandContentView) {
        return this.delegateFactory.get(dynamicIslandContentView);
    }

    public static G0.a create(C0657DynamicIslandContentViewController_Factory c0657DynamicIslandContentViewController_Factory) {
        return f.a(new DynamicIslandContentViewController_Factory_Impl(c0657DynamicIslandContentViewController_Factory));
    }
}
