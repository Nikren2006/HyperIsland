package miui.systemui.controlcenter.panel.secondary.brightness;

import F0.d;
import F0.e;
import android.content.Context;

/* JADX INFO: loaded from: classes.dex */
public final class BrightnessPanelAnimator_Factory implements e {
    private final G0.a contextProvider;
    private final G0.a mainPanelControllerProvider;
    private final G0.a panelControllerProvider;
    private final G0.a sliderDelegateProvider;
    private final G0.a windowViewControllerProvider;

    public BrightnessPanelAnimator_Factory(G0.a aVar, G0.a aVar2, G0.a aVar3, G0.a aVar4, G0.a aVar5) {
        this.contextProvider = aVar;
        this.panelControllerProvider = aVar2;
        this.sliderDelegateProvider = aVar3;
        this.windowViewControllerProvider = aVar4;
        this.mainPanelControllerProvider = aVar5;
    }

    public static BrightnessPanelAnimator_Factory create(G0.a aVar, G0.a aVar2, G0.a aVar3, G0.a aVar4, G0.a aVar5) {
        return new BrightnessPanelAnimator_Factory(aVar, aVar2, aVar3, aVar4, aVar5);
    }

    public static BrightnessPanelAnimator newInstance(Context context, E0.a aVar, BrightnessPanelSliderDelegate brightnessPanelSliderDelegate, E0.a aVar2, E0.a aVar3) {
        return new BrightnessPanelAnimator(context, aVar, brightnessPanelSliderDelegate, aVar2, aVar3);
    }

    @Override // G0.a
    public BrightnessPanelAnimator get() {
        return newInstance((Context) this.contextProvider.get(), d.a(this.panelControllerProvider), (BrightnessPanelSliderDelegate) this.sliderDelegateProvider.get(), d.a(this.windowViewControllerProvider), d.a(this.mainPanelControllerProvider));
    }
}
