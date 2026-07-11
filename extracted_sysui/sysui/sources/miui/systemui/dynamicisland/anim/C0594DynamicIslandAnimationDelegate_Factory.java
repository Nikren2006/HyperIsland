package miui.systemui.dynamicisland.anim;

import miui.systemui.dynamicisland.event.handler.BigIslandStateHandler;
import miui.systemui.dynamicisland.event.handler.ExpandedStateHandler;
import miui.systemui.dynamicisland.event.handler.SmallIslandStateHandler;
import miui.systemui.dynamicisland.window.DynamicIslandWindowView;
import miui.systemui.dynamicisland.window.content.DynamicIslandBaseContentView;

/* JADX INFO: renamed from: miui.systemui.dynamicisland.anim.DynamicIslandAnimationDelegate_Factory, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes3.dex */
public final class C0594DynamicIslandAnimationDelegate_Factory {
    private final G0.a bigIslandStateHandlerProvider;
    private final G0.a dynamicIslandAnimControllerProvider;
    private final G0.a expandedStateHandlerProvider;
    private final G0.a smallIslandStateHandlerProvider;
    private final G0.a windowViewProvider;

    public C0594DynamicIslandAnimationDelegate_Factory(G0.a aVar, G0.a aVar2, G0.a aVar3, G0.a aVar4, G0.a aVar5) {
        this.dynamicIslandAnimControllerProvider = aVar;
        this.smallIslandStateHandlerProvider = aVar2;
        this.bigIslandStateHandlerProvider = aVar3;
        this.expandedStateHandlerProvider = aVar4;
        this.windowViewProvider = aVar5;
    }

    public static C0594DynamicIslandAnimationDelegate_Factory create(G0.a aVar, G0.a aVar2, G0.a aVar3, G0.a aVar4, G0.a aVar5) {
        return new C0594DynamicIslandAnimationDelegate_Factory(aVar, aVar2, aVar3, aVar4, aVar5);
    }

    public static DynamicIslandAnimationDelegate newInstance(DynamicIslandBaseContentView dynamicIslandBaseContentView, DynamicIslandAnimationController dynamicIslandAnimationController, SmallIslandStateHandler smallIslandStateHandler, BigIslandStateHandler bigIslandStateHandler, ExpandedStateHandler expandedStateHandler, DynamicIslandWindowView dynamicIslandWindowView) {
        return new DynamicIslandAnimationDelegate(dynamicIslandBaseContentView, dynamicIslandAnimationController, smallIslandStateHandler, bigIslandStateHandler, expandedStateHandler, dynamicIslandWindowView);
    }

    public DynamicIslandAnimationDelegate get(DynamicIslandBaseContentView dynamicIslandBaseContentView) {
        return newInstance(dynamicIslandBaseContentView, (DynamicIslandAnimationController) this.dynamicIslandAnimControllerProvider.get(), (SmallIslandStateHandler) this.smallIslandStateHandlerProvider.get(), (BigIslandStateHandler) this.bigIslandStateHandlerProvider.get(), (ExpandedStateHandler) this.expandedStateHandlerProvider.get(), (DynamicIslandWindowView) this.windowViewProvider.get());
    }
}
