package miui.systemui.dynamicisland.anim;

import F0.f;
import miui.systemui.dynamicisland.anim.DynamicIslandAnimationDelegate;
import miui.systemui.dynamicisland.window.content.DynamicIslandBaseContentView;

/* JADX INFO: loaded from: classes3.dex */
public final class DynamicIslandAnimationDelegate_Factory_Impl implements DynamicIslandAnimationDelegate.Factory {
    private final C0594DynamicIslandAnimationDelegate_Factory delegateFactory;

    public DynamicIslandAnimationDelegate_Factory_Impl(C0594DynamicIslandAnimationDelegate_Factory c0594DynamicIslandAnimationDelegate_Factory) {
        this.delegateFactory = c0594DynamicIslandAnimationDelegate_Factory;
    }

    @Override // miui.systemui.dynamicisland.anim.DynamicIslandAnimationDelegate.Factory
    public DynamicIslandAnimationDelegate create(DynamicIslandBaseContentView dynamicIslandBaseContentView) {
        return this.delegateFactory.get(dynamicIslandBaseContentView);
    }

    public static G0.a create(C0594DynamicIslandAnimationDelegate_Factory c0594DynamicIslandAnimationDelegate_Factory) {
        return f.a(new DynamicIslandAnimationDelegate_Factory_Impl(c0594DynamicIslandAnimationDelegate_Factory));
    }
}
