package miui.systemui.controlcenter.panel.secondary.brightness;

import F0.e;
import miui.systemui.controlcenter.databinding.ControlCenterSecondaryBinding;

/* JADX INFO: loaded from: classes.dex */
public final class BrightnessPanelDelegate_Factory implements e {
    private final G0.a secondaryBindingProvider;
    private final G0.a sliderDelegateProvider;
    private final G0.a tilesDelegateProvider;

    public BrightnessPanelDelegate_Factory(G0.a aVar, G0.a aVar2, G0.a aVar3) {
        this.secondaryBindingProvider = aVar;
        this.sliderDelegateProvider = aVar2;
        this.tilesDelegateProvider = aVar3;
    }

    public static BrightnessPanelDelegate_Factory create(G0.a aVar, G0.a aVar2, G0.a aVar3) {
        return new BrightnessPanelDelegate_Factory(aVar, aVar2, aVar3);
    }

    public static BrightnessPanelDelegate newInstance(ControlCenterSecondaryBinding controlCenterSecondaryBinding, BrightnessPanelSliderDelegate brightnessPanelSliderDelegate, BrightnessPanelTilesDelegate brightnessPanelTilesDelegate) {
        return new BrightnessPanelDelegate(controlCenterSecondaryBinding, brightnessPanelSliderDelegate, brightnessPanelTilesDelegate);
    }

    @Override // G0.a
    public BrightnessPanelDelegate get() {
        return newInstance((ControlCenterSecondaryBinding) this.secondaryBindingProvider.get(), (BrightnessPanelSliderDelegate) this.sliderDelegateProvider.get(), (BrightnessPanelTilesDelegate) this.tilesDelegateProvider.get());
    }
}
