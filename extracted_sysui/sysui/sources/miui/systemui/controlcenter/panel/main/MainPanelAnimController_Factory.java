package miui.systemui.controlcenter.panel.main;

import android.widget.LinearLayout;
import miui.systemui.controlcenter.databinding.MainPanelHeaderBinding;
import miui.systemui.controlcenter.panel.main.qs.QSCardsController;
import miui.systemui.util.BlurUtilsExt;

/* JADX INFO: loaded from: classes.dex */
public final class MainPanelAnimController_Factory implements F0.e {
    private final G0.a blurUtilsExtProvider;
    private final G0.a headerBindingProvider;
    private final G0.a headerControllerProvider;
    private final G0.a mainPanelContainerProvider;
    private final G0.a mainPanelControllerProvider;
    private final G0.a qsCardsControllerProvider;
    private final G0.a secondaryPanelRouterProvider;

    public MainPanelAnimController_Factory(G0.a aVar, G0.a aVar2, G0.a aVar3, G0.a aVar4, G0.a aVar5, G0.a aVar6, G0.a aVar7) {
        this.headerBindingProvider = aVar;
        this.mainPanelContainerProvider = aVar2;
        this.mainPanelControllerProvider = aVar3;
        this.headerControllerProvider = aVar4;
        this.secondaryPanelRouterProvider = aVar5;
        this.blurUtilsExtProvider = aVar6;
        this.qsCardsControllerProvider = aVar7;
    }

    public static MainPanelAnimController_Factory create(G0.a aVar, G0.a aVar2, G0.a aVar3, G0.a aVar4, G0.a aVar5, G0.a aVar6, G0.a aVar7) {
        return new MainPanelAnimController_Factory(aVar, aVar2, aVar3, aVar4, aVar5, aVar6, aVar7);
    }

    public static MainPanelAnimController newInstance(MainPanelHeaderBinding mainPanelHeaderBinding, LinearLayout linearLayout, E0.a aVar, E0.a aVar2, E0.a aVar3, BlurUtilsExt blurUtilsExt, QSCardsController qSCardsController) {
        return new MainPanelAnimController(mainPanelHeaderBinding, linearLayout, aVar, aVar2, aVar3, blurUtilsExt, qSCardsController);
    }

    @Override // G0.a
    public MainPanelAnimController get() {
        return newInstance((MainPanelHeaderBinding) this.headerBindingProvider.get(), (LinearLayout) this.mainPanelContainerProvider.get(), F0.d.a(this.mainPanelControllerProvider), F0.d.a(this.headerControllerProvider), F0.d.a(this.secondaryPanelRouterProvider), (BlurUtilsExt) this.blurUtilsExtProvider.get(), (QSCardsController) this.qsCardsControllerProvider.get());
    }
}
