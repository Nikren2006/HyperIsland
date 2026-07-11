package miui.systemui.dynamicisland.window;

import android.os.Handler;
import b.BinderC0222a;
import g1.E;
import miui.systemui.autodensity.AutoDensityController;
import miui.systemui.dynamicisland.data.repository.DynamicIslandSizeRepository;
import miui.systemui.util.concurrency.DelayableExecutor;

/* JADX INFO: loaded from: classes3.dex */
public final class DynamicIslandWindowViewController_Factory implements F0.e {
    private final G0.a autoDensityControllerProvider;
    private final G0.a avoidScreenBurnInHelperProvider;
    private final G0.a bgHandlerProvider;
    private final G0.a dynamicIslandDesktopStateControllerProvider;
    private final G0.a dynamicIslandSafeguardsControllerProvider;
    private final G0.a externalStateRepositoryProvider;
    private final G0.a externalTouchHandlerProvider;
    private final G0.a hyperDropInfoNotifierServiceProvider;
    private final G0.a lottieProgressManagerProvider;
    private final G0.a notificationChronometerManagerProvider;
    private final G0.a scopeProvider;
    private final G0.a sizeRepositoryProvider;
    private final G0.a uiExecutorProvider;
    private final G0.a viewProvider;
    private final G0.a windowStateProvider;

    public DynamicIslandWindowViewController_Factory(G0.a aVar, G0.a aVar2, G0.a aVar3, G0.a aVar4, G0.a aVar5, G0.a aVar6, G0.a aVar7, G0.a aVar8, G0.a aVar9, G0.a aVar10, G0.a aVar11, G0.a aVar12, G0.a aVar13, G0.a aVar14, G0.a aVar15) {
        this.viewProvider = aVar;
        this.scopeProvider = aVar2;
        this.uiExecutorProvider = aVar3;
        this.notificationChronometerManagerProvider = aVar4;
        this.avoidScreenBurnInHelperProvider = aVar5;
        this.windowStateProvider = aVar6;
        this.externalTouchHandlerProvider = aVar7;
        this.dynamicIslandDesktopStateControllerProvider = aVar8;
        this.dynamicIslandSafeguardsControllerProvider = aVar9;
        this.externalStateRepositoryProvider = aVar10;
        this.autoDensityControllerProvider = aVar11;
        this.lottieProgressManagerProvider = aVar12;
        this.hyperDropInfoNotifierServiceProvider = aVar13;
        this.sizeRepositoryProvider = aVar14;
        this.bgHandlerProvider = aVar15;
    }

    public static DynamicIslandWindowViewController_Factory create(G0.a aVar, G0.a aVar2, G0.a aVar3, G0.a aVar4, G0.a aVar5, G0.a aVar6, G0.a aVar7, G0.a aVar8, G0.a aVar9, G0.a aVar10, G0.a aVar11, G0.a aVar12, G0.a aVar13, G0.a aVar14, G0.a aVar15) {
        return new DynamicIslandWindowViewController_Factory(aVar, aVar2, aVar3, aVar4, aVar5, aVar6, aVar7, aVar8, aVar9, aVar10, aVar11, aVar12, aVar13, aVar14, aVar15);
    }

    public static DynamicIslandWindowViewController newInstance(DynamicIslandWindowView dynamicIslandWindowView, E e2, DelayableExecutor delayableExecutor, E0.a aVar, E0.a aVar2, DynamicIslandWindowState dynamicIslandWindowState, E0.a aVar3, DynamicIslandDesktopStateController dynamicIslandDesktopStateController, DynamicIslandSafeguardsController dynamicIslandSafeguardsController, E0.a aVar4, AutoDensityController autoDensityController, E0.a aVar5, BinderC0222a binderC0222a, DynamicIslandSizeRepository dynamicIslandSizeRepository, Handler handler) {
        return new DynamicIslandWindowViewController(dynamicIslandWindowView, e2, delayableExecutor, aVar, aVar2, dynamicIslandWindowState, aVar3, dynamicIslandDesktopStateController, dynamicIslandSafeguardsController, aVar4, autoDensityController, aVar5, binderC0222a, dynamicIslandSizeRepository, handler);
    }

    @Override // G0.a
    public DynamicIslandWindowViewController get() {
        return newInstance((DynamicIslandWindowView) this.viewProvider.get(), (E) this.scopeProvider.get(), (DelayableExecutor) this.uiExecutorProvider.get(), F0.d.a(this.notificationChronometerManagerProvider), F0.d.a(this.avoidScreenBurnInHelperProvider), (DynamicIslandWindowState) this.windowStateProvider.get(), F0.d.a(this.externalTouchHandlerProvider), (DynamicIslandDesktopStateController) this.dynamicIslandDesktopStateControllerProvider.get(), (DynamicIslandSafeguardsController) this.dynamicIslandSafeguardsControllerProvider.get(), F0.d.a(this.externalStateRepositoryProvider), (AutoDensityController) this.autoDensityControllerProvider.get(), F0.d.a(this.lottieProgressManagerProvider), (BinderC0222a) this.hyperDropInfoNotifierServiceProvider.get(), (DynamicIslandSizeRepository) this.sizeRepositoryProvider.get(), (Handler) this.bgHandlerProvider.get());
    }
}
