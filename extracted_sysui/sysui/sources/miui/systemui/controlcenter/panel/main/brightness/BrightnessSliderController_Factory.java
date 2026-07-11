package miui.systemui.controlcenter.panel.main.brightness;

import F0.d;
import F0.e;
import androidx.lifecycle.Lifecycle;
import com.android.systemui.plugins.miui.controlcenter.BrightnessControllerBase;
import com.android.systemui.plugins.miui.settings.SuperSaveModeController;
import java.util.concurrent.Executor;
import miui.systemui.controlcenter.panel.main.recyclerview.ToggleSliderViewHolder;
import miui.systemui.controlcenter.windowview.ControlCenterWindowViewImpl;
import miui.systemui.util.HapticFeedback;

/* JADX INFO: loaded from: classes.dex */
public final class BrightnessSliderController_Factory implements e {
    private final G0.a bgExecutorProvider;
    private final G0.a brightnessControllerProvider;
    private final G0.a brightnessPanelControllerProvider;
    private final G0.a hapticFeedbackProvider;
    private final G0.a itemFactoryProvider;
    private final G0.a mainPanelModeControllerProvider;
    private final G0.a mainPanelStyleControllerProvider;
    private final G0.a secondaryPanelRouterProvider;
    private final G0.a superSaveModeControllerProvider;
    private final G0.a uiExecutorProvider;
    private final G0.a windowViewControllerProvider;
    private final G0.a windowViewLifecycleProvider;
    private final G0.a windowViewProvider;

    public BrightnessSliderController_Factory(G0.a aVar, G0.a aVar2, G0.a aVar3, G0.a aVar4, G0.a aVar5, G0.a aVar6, G0.a aVar7, G0.a aVar8, G0.a aVar9, G0.a aVar10, G0.a aVar11, G0.a aVar12, G0.a aVar13) {
        this.windowViewProvider = aVar;
        this.windowViewLifecycleProvider = aVar2;
        this.itemFactoryProvider = aVar3;
        this.uiExecutorProvider = aVar4;
        this.bgExecutorProvider = aVar5;
        this.windowViewControllerProvider = aVar6;
        this.mainPanelStyleControllerProvider = aVar7;
        this.mainPanelModeControllerProvider = aVar8;
        this.hapticFeedbackProvider = aVar9;
        this.secondaryPanelRouterProvider = aVar10;
        this.brightnessPanelControllerProvider = aVar11;
        this.brightnessControllerProvider = aVar12;
        this.superSaveModeControllerProvider = aVar13;
    }

    public static BrightnessSliderController_Factory create(G0.a aVar, G0.a aVar2, G0.a aVar3, G0.a aVar4, G0.a aVar5, G0.a aVar6, G0.a aVar7, G0.a aVar8, G0.a aVar9, G0.a aVar10, G0.a aVar11, G0.a aVar12, G0.a aVar13) {
        return new BrightnessSliderController_Factory(aVar, aVar2, aVar3, aVar4, aVar5, aVar6, aVar7, aVar8, aVar9, aVar10, aVar11, aVar12, aVar13);
    }

    public static BrightnessSliderController newInstance(ControlCenterWindowViewImpl controlCenterWindowViewImpl, Lifecycle lifecycle, ToggleSliderViewHolder.Factory factory, Executor executor, Executor executor2, E0.a aVar, E0.a aVar2, E0.a aVar3, HapticFeedback hapticFeedback, E0.a aVar4, E0.a aVar5, BrightnessControllerBase brightnessControllerBase, SuperSaveModeController superSaveModeController) {
        return new BrightnessSliderController(controlCenterWindowViewImpl, lifecycle, factory, executor, executor2, aVar, aVar2, aVar3, hapticFeedback, aVar4, aVar5, brightnessControllerBase, superSaveModeController);
    }

    @Override // G0.a
    public BrightnessSliderController get() {
        return newInstance((ControlCenterWindowViewImpl) this.windowViewProvider.get(), (Lifecycle) this.windowViewLifecycleProvider.get(), (ToggleSliderViewHolder.Factory) this.itemFactoryProvider.get(), (Executor) this.uiExecutorProvider.get(), (Executor) this.bgExecutorProvider.get(), d.a(this.windowViewControllerProvider), d.a(this.mainPanelStyleControllerProvider), d.a(this.mainPanelModeControllerProvider), (HapticFeedback) this.hapticFeedbackProvider.get(), d.a(this.secondaryPanelRouterProvider), d.a(this.brightnessPanelControllerProvider), (BrightnessControllerBase) this.brightnessControllerProvider.get(), (SuperSaveModeController) this.superSaveModeControllerProvider.get());
    }
}
