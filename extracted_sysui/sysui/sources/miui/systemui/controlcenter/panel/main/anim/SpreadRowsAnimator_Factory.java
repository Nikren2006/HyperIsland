package miui.systemui.controlcenter.panel.main.anim;

import F0.d;
import F0.e;
import android.widget.LinearLayout;
import androidx.lifecycle.Lifecycle;
import com.android.systemui.plugins.miui.shade.ShadeSwitchController;
import miui.systemui.controlcenter.panel.main.brightness.BrightnessSliderController;
import miui.systemui.controlcenter.panel.main.header.MainPanelHeaderController;
import miui.systemui.controlcenter.windowview.ControlCenterWindowViewImpl;

/* JADX INFO: loaded from: classes.dex */
public final class SpreadRowsAnimator_Factory implements e {
    private final G0.a brightnessSliderControllerProvider;
    private final G0.a expandControllerProvider;
    private final G0.a frameCallbackProvider;
    private final G0.a headerControllerProvider;
    private final G0.a mainPanelContainerProvider;
    private final G0.a mirrorLifecycleProvider;
    private final G0.a shadeSwitchControllerProvider;
    private final G0.a windowViewControllerProvider;
    private final G0.a windowViewProvider;

    public SpreadRowsAnimator_Factory(G0.a aVar, G0.a aVar2, G0.a aVar3, G0.a aVar4, G0.a aVar5, G0.a aVar6, G0.a aVar7, G0.a aVar8, G0.a aVar9) {
        this.windowViewProvider = aVar;
        this.windowViewControllerProvider = aVar2;
        this.expandControllerProvider = aVar3;
        this.mirrorLifecycleProvider = aVar4;
        this.frameCallbackProvider = aVar5;
        this.shadeSwitchControllerProvider = aVar6;
        this.headerControllerProvider = aVar7;
        this.brightnessSliderControllerProvider = aVar8;
        this.mainPanelContainerProvider = aVar9;
    }

    public static SpreadRowsAnimator_Factory create(G0.a aVar, G0.a aVar2, G0.a aVar3, G0.a aVar4, G0.a aVar5, G0.a aVar6, G0.a aVar7, G0.a aVar8, G0.a aVar9) {
        return new SpreadRowsAnimator_Factory(aVar, aVar2, aVar3, aVar4, aVar5, aVar6, aVar7, aVar8, aVar9);
    }

    public static SpreadRowsAnimator newInstance(ControlCenterWindowViewImpl controlCenterWindowViewImpl, E0.a aVar, E0.a aVar2, Lifecycle lifecycle, E0.a aVar3, ShadeSwitchController shadeSwitchController, MainPanelHeaderController mainPanelHeaderController, BrightnessSliderController brightnessSliderController, LinearLayout linearLayout) {
        return new SpreadRowsAnimator(controlCenterWindowViewImpl, aVar, aVar2, lifecycle, aVar3, shadeSwitchController, mainPanelHeaderController, brightnessSliderController, linearLayout);
    }

    @Override // G0.a
    public SpreadRowsAnimator get() {
        return newInstance((ControlCenterWindowViewImpl) this.windowViewProvider.get(), d.a(this.windowViewControllerProvider), d.a(this.expandControllerProvider), (Lifecycle) this.mirrorLifecycleProvider.get(), d.a(this.frameCallbackProvider), (ShadeSwitchController) this.shadeSwitchControllerProvider.get(), (MainPanelHeaderController) this.headerControllerProvider.get(), (BrightnessSliderController) this.brightnessSliderControllerProvider.get(), (LinearLayout) this.mainPanelContainerProvider.get());
    }
}
