package miui.systemui.controlcenter.dagger;

import F0.e;
import F0.i;
import G0.a;
import androidx.lifecycle.Lifecycle;
import miui.systemui.controlcenter.panel.main.brightness.BrightnessSliderController;

/* JADX INFO: loaded from: classes.dex */
public final class ControlCenterViewModule_ProvideBrightnessMirrorLifecycleFactory implements e {
    private final a brightnessSliderControllerProvider;
    private final ControlCenterViewModule module;

    public ControlCenterViewModule_ProvideBrightnessMirrorLifecycleFactory(ControlCenterViewModule controlCenterViewModule, a aVar) {
        this.module = controlCenterViewModule;
        this.brightnessSliderControllerProvider = aVar;
    }

    public static ControlCenterViewModule_ProvideBrightnessMirrorLifecycleFactory create(ControlCenterViewModule controlCenterViewModule, a aVar) {
        return new ControlCenterViewModule_ProvideBrightnessMirrorLifecycleFactory(controlCenterViewModule, aVar);
    }

    public static Lifecycle provideBrightnessMirrorLifecycle(ControlCenterViewModule controlCenterViewModule, BrightnessSliderController brightnessSliderController) {
        return (Lifecycle) i.d(controlCenterViewModule.provideBrightnessMirrorLifecycle(brightnessSliderController));
    }

    @Override // G0.a
    public Lifecycle get() {
        return provideBrightnessMirrorLifecycle(this.module, (BrightnessSliderController) this.brightnessSliderControllerProvider.get());
    }
}
