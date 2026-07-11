package miui.systemui.controlcenter.windowview;

import android.os.Handler;

/* JADX INFO: loaded from: classes.dex */
public final class BlurController_Factory implements F0.e {
    private final G0.a brightnessSliderControllerProvider;
    private final G0.a expandControllerProvider;
    private final G0.a screenshotProvider;
    private final G0.a uiHandlerProvider;
    private final G0.a windowViewControllerProvider;
    private final G0.a windowViewProvider;

    public BlurController_Factory(G0.a aVar, G0.a aVar2, G0.a aVar3, G0.a aVar4, G0.a aVar5, G0.a aVar6) {
        this.windowViewProvider = aVar;
        this.uiHandlerProvider = aVar2;
        this.screenshotProvider = aVar3;
        this.brightnessSliderControllerProvider = aVar4;
        this.expandControllerProvider = aVar5;
        this.windowViewControllerProvider = aVar6;
    }

    public static BlurController_Factory create(G0.a aVar, G0.a aVar2, G0.a aVar3, G0.a aVar4, G0.a aVar5, G0.a aVar6) {
        return new BlurController_Factory(aVar, aVar2, aVar3, aVar4, aVar5, aVar6);
    }

    public static BlurController newInstance(ControlCenterWindowViewImpl controlCenterWindowViewImpl, Handler handler, E0.a aVar, E0.a aVar2, E0.a aVar3, E0.a aVar4) {
        return new BlurController(controlCenterWindowViewImpl, handler, aVar, aVar2, aVar3, aVar4);
    }

    @Override // G0.a
    public BlurController get() {
        return newInstance((ControlCenterWindowViewImpl) this.windowViewProvider.get(), (Handler) this.uiHandlerProvider.get(), F0.d.a(this.screenshotProvider), F0.d.a(this.brightnessSliderControllerProvider), F0.d.a(this.expandControllerProvider), F0.d.a(this.windowViewControllerProvider));
    }
}
