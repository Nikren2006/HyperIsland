package miui.systemui.controlcenter.windowview;

/* JADX INFO: loaded from: classes.dex */
public final class ControlCenterExpandController_Factory implements F0.e {
    private final G0.a blurControllerProvider;
    private final G0.a brightnessSliderControllerProvider;
    private final G0.a mainPanelControllerProvider;
    private final G0.a secondaryPanelRouterProvider;
    private final G0.a spreadRowsAnimatorProvider;
    private final G0.a windowViewControllerProvider;
    private final G0.a windowViewProvider;

    public ControlCenterExpandController_Factory(G0.a aVar, G0.a aVar2, G0.a aVar3, G0.a aVar4, G0.a aVar5, G0.a aVar6, G0.a aVar7) {
        this.windowViewProvider = aVar;
        this.windowViewControllerProvider = aVar2;
        this.mainPanelControllerProvider = aVar3;
        this.secondaryPanelRouterProvider = aVar4;
        this.blurControllerProvider = aVar5;
        this.spreadRowsAnimatorProvider = aVar6;
        this.brightnessSliderControllerProvider = aVar7;
    }

    public static ControlCenterExpandController_Factory create(G0.a aVar, G0.a aVar2, G0.a aVar3, G0.a aVar4, G0.a aVar5, G0.a aVar6, G0.a aVar7) {
        return new ControlCenterExpandController_Factory(aVar, aVar2, aVar3, aVar4, aVar5, aVar6, aVar7);
    }

    public static ControlCenterExpandController newInstance(ControlCenterWindowViewImpl controlCenterWindowViewImpl, E0.a aVar, E0.a aVar2, E0.a aVar3, E0.a aVar4, E0.a aVar5, E0.a aVar6) {
        return new ControlCenterExpandController(controlCenterWindowViewImpl, aVar, aVar2, aVar3, aVar4, aVar5, aVar6);
    }

    @Override // G0.a
    public ControlCenterExpandController get() {
        return newInstance((ControlCenterWindowViewImpl) this.windowViewProvider.get(), F0.d.a(this.windowViewControllerProvider), F0.d.a(this.mainPanelControllerProvider), F0.d.a(this.secondaryPanelRouterProvider), F0.d.a(this.blurControllerProvider), F0.d.a(this.spreadRowsAnimatorProvider), F0.d.a(this.brightnessSliderControllerProvider));
    }
}
