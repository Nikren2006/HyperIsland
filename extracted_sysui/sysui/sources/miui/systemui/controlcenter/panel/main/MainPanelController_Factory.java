package miui.systemui.controlcenter.panel.main;

import android.widget.LinearLayout;
import androidx.lifecycle.Lifecycle;
import androidx.recyclerview.widget.RecyclerView;
import g1.E;
import miui.systemui.controlcenter.panel.main.anim.SpreadRowsAnimator;
import miui.systemui.controlcenter.panel.main.brightness.BrightnessSliderController;
import miui.systemui.controlcenter.panel.main.header.MainPanelHeaderController;
import miui.systemui.controlcenter.panel.main.volume.VolumeSliderController;
import miui.systemui.controlcenter.panel.secondary.SecondaryContainerController;
import miui.systemui.controlcenter.qs.QSController;
import miui.systemui.controlcenter.windowview.ControlCenterExpandController;
import miui.systemui.controlcenter.windowview.ControlCenterWindowViewImpl;
import miui.systemui.controlcenter.windowview.GestureDispatcher;
import miui.systemui.util.BlurUtilsExt;
import miui.systemui.util.SystemUIResourcesHelper;

/* JADX INFO: loaded from: classes.dex */
public final class MainPanelController_Factory implements F0.e {
    private final G0.a blurUtilsExtProvider;
    private final G0.a brightnessSliderControllerProvider;
    private final G0.a distributorProvider;
    private final G0.a expandControllerProvider;
    private final G0.a gestureDispatcherProvider;
    private final G0.a headerController2Provider;
    private final G0.a leftMainPanelProvider;
    private final G0.a lifecycleProvider;
    private final G0.a mainPanelAnimControllerProvider;
    private final G0.a mainPanelContainerProvider;
    private final G0.a modeControllerProvider;
    private final G0.a qsControllerProvider;
    private final G0.a rightMainPanelProvider;
    private final G0.a scopeProvider;
    private final G0.a secondaryContainerControllerProvider;
    private final G0.a secondaryPanelRouterProvider;
    private final G0.a spreadRowsAnimatorProvider;
    private final G0.a styleControllerProvider;
    private final G0.a systemUIResourcesHelperProvider;
    private final G0.a touchControllerProvider;
    private final G0.a volumeSliderControllerProvider;
    private final G0.a windowViewControllerProvider;
    private final G0.a windowViewProvider;

    public MainPanelController_Factory(G0.a aVar, G0.a aVar2, G0.a aVar3, G0.a aVar4, G0.a aVar5, G0.a aVar6, G0.a aVar7, G0.a aVar8, G0.a aVar9, G0.a aVar10, G0.a aVar11, G0.a aVar12, G0.a aVar13, G0.a aVar14, G0.a aVar15, G0.a aVar16, G0.a aVar17, G0.a aVar18, G0.a aVar19, G0.a aVar20, G0.a aVar21, G0.a aVar22, G0.a aVar23) {
        this.scopeProvider = aVar;
        this.windowViewProvider = aVar2;
        this.rightMainPanelProvider = aVar3;
        this.leftMainPanelProvider = aVar4;
        this.mainPanelContainerProvider = aVar5;
        this.lifecycleProvider = aVar6;
        this.styleControllerProvider = aVar7;
        this.modeControllerProvider = aVar8;
        this.mainPanelAnimControllerProvider = aVar9;
        this.touchControllerProvider = aVar10;
        this.expandControllerProvider = aVar11;
        this.volumeSliderControllerProvider = aVar12;
        this.windowViewControllerProvider = aVar13;
        this.spreadRowsAnimatorProvider = aVar14;
        this.gestureDispatcherProvider = aVar15;
        this.qsControllerProvider = aVar16;
        this.distributorProvider = aVar17;
        this.secondaryPanelRouterProvider = aVar18;
        this.headerController2Provider = aVar19;
        this.systemUIResourcesHelperProvider = aVar20;
        this.blurUtilsExtProvider = aVar21;
        this.secondaryContainerControllerProvider = aVar22;
        this.brightnessSliderControllerProvider = aVar23;
    }

    public static MainPanelController_Factory create(G0.a aVar, G0.a aVar2, G0.a aVar3, G0.a aVar4, G0.a aVar5, G0.a aVar6, G0.a aVar7, G0.a aVar8, G0.a aVar9, G0.a aVar10, G0.a aVar11, G0.a aVar12, G0.a aVar13, G0.a aVar14, G0.a aVar15, G0.a aVar16, G0.a aVar17, G0.a aVar18, G0.a aVar19, G0.a aVar20, G0.a aVar21, G0.a aVar22, G0.a aVar23) {
        return new MainPanelController_Factory(aVar, aVar2, aVar3, aVar4, aVar5, aVar6, aVar7, aVar8, aVar9, aVar10, aVar11, aVar12, aVar13, aVar14, aVar15, aVar16, aVar17, aVar18, aVar19, aVar20, aVar21, aVar22, aVar23);
    }

    public static MainPanelController newInstance(E e2, ControlCenterWindowViewImpl controlCenterWindowViewImpl, RecyclerView recyclerView, RecyclerView recyclerView2, LinearLayout linearLayout, Lifecycle lifecycle, MainPanelStyleController mainPanelStyleController, MainPanelModeController mainPanelModeController, MainPanelAnimController mainPanelAnimController, MainPanelTouchController mainPanelTouchController, ControlCenterExpandController controlCenterExpandController, VolumeSliderController volumeSliderController, E0.a aVar, SpreadRowsAnimator spreadRowsAnimator, GestureDispatcher gestureDispatcher, QSController qSController, MainPanelContentDistributor mainPanelContentDistributor, E0.a aVar2, MainPanelHeaderController mainPanelHeaderController, SystemUIResourcesHelper systemUIResourcesHelper, BlurUtilsExt blurUtilsExt, SecondaryContainerController secondaryContainerController, BrightnessSliderController brightnessSliderController) {
        return new MainPanelController(e2, controlCenterWindowViewImpl, recyclerView, recyclerView2, linearLayout, lifecycle, mainPanelStyleController, mainPanelModeController, mainPanelAnimController, mainPanelTouchController, controlCenterExpandController, volumeSliderController, aVar, spreadRowsAnimator, gestureDispatcher, qSController, mainPanelContentDistributor, aVar2, mainPanelHeaderController, systemUIResourcesHelper, blurUtilsExt, secondaryContainerController, brightnessSliderController);
    }

    @Override // G0.a
    public MainPanelController get() {
        return newInstance((E) this.scopeProvider.get(), (ControlCenterWindowViewImpl) this.windowViewProvider.get(), (RecyclerView) this.rightMainPanelProvider.get(), (RecyclerView) this.leftMainPanelProvider.get(), (LinearLayout) this.mainPanelContainerProvider.get(), (Lifecycle) this.lifecycleProvider.get(), (MainPanelStyleController) this.styleControllerProvider.get(), (MainPanelModeController) this.modeControllerProvider.get(), (MainPanelAnimController) this.mainPanelAnimControllerProvider.get(), (MainPanelTouchController) this.touchControllerProvider.get(), (ControlCenterExpandController) this.expandControllerProvider.get(), (VolumeSliderController) this.volumeSliderControllerProvider.get(), F0.d.a(this.windowViewControllerProvider), (SpreadRowsAnimator) this.spreadRowsAnimatorProvider.get(), (GestureDispatcher) this.gestureDispatcherProvider.get(), (QSController) this.qsControllerProvider.get(), (MainPanelContentDistributor) this.distributorProvider.get(), F0.d.a(this.secondaryPanelRouterProvider), (MainPanelHeaderController) this.headerController2Provider.get(), (SystemUIResourcesHelper) this.systemUIResourcesHelperProvider.get(), (BlurUtilsExt) this.blurUtilsExtProvider.get(), (SecondaryContainerController) this.secondaryContainerControllerProvider.get(), (BrightnessSliderController) this.brightnessSliderControllerProvider.get());
    }
}
