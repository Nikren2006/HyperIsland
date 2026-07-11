package miui.systemui.controlcenter.panel.secondary.detail;

import android.content.Context;
import android.os.Handler;
import com.android.systemui.plugins.ActivityStarter;
import com.android.systemui.plugins.statusbar.StatusBarStateController;
import miui.systemui.controlcenter.databinding.ControlCenterSecondaryBinding;
import miui.systemui.controlcenter.databinding.DetailPanelBinding;
import miui.systemui.controlcenter.windowview.ControlCenterExpandController;

/* JADX INFO: loaded from: classes.dex */
public final class DetailPanelDelegate_Factory implements F0.e {
    private final G0.a activityStarterProvider;
    private final G0.a bindingProvider;
    private final G0.a cellAnimatorProvider;
    private final G0.a expandControllerProvider;
    private final G0.a mainHandlerProvider;
    private final G0.a secondaryBindingProvider;
    private final G0.a statusBarStateControllerProvider;
    private final G0.a sysUIContextProvider;
    private final G0.a tilesDelegateProvider;

    public DetailPanelDelegate_Factory(G0.a aVar, G0.a aVar2, G0.a aVar3, G0.a aVar4, G0.a aVar5, G0.a aVar6, G0.a aVar7, G0.a aVar8, G0.a aVar9) {
        this.sysUIContextProvider = aVar;
        this.mainHandlerProvider = aVar2;
        this.secondaryBindingProvider = aVar3;
        this.bindingProvider = aVar4;
        this.activityStarterProvider = aVar5;
        this.statusBarStateControllerProvider = aVar6;
        this.expandControllerProvider = aVar7;
        this.tilesDelegateProvider = aVar8;
        this.cellAnimatorProvider = aVar9;
    }

    public static DetailPanelDelegate_Factory create(G0.a aVar, G0.a aVar2, G0.a aVar3, G0.a aVar4, G0.a aVar5, G0.a aVar6, G0.a aVar7, G0.a aVar8, G0.a aVar9) {
        return new DetailPanelDelegate_Factory(aVar, aVar2, aVar3, aVar4, aVar5, aVar6, aVar7, aVar8, aVar9);
    }

    public static DetailPanelDelegate newInstance(Context context, Handler handler, ControlCenterSecondaryBinding controlCenterSecondaryBinding, DetailPanelBinding detailPanelBinding, ActivityStarter activityStarter, StatusBarStateController statusBarStateController, ControlCenterExpandController controlCenterExpandController, DetailPanelTilesDelegate detailPanelTilesDelegate, DetailPanelCellAnimator detailPanelCellAnimator) {
        return new DetailPanelDelegate(context, handler, controlCenterSecondaryBinding, detailPanelBinding, activityStarter, statusBarStateController, controlCenterExpandController, detailPanelTilesDelegate, detailPanelCellAnimator);
    }

    @Override // G0.a
    public DetailPanelDelegate get() {
        return newInstance((Context) this.sysUIContextProvider.get(), (Handler) this.mainHandlerProvider.get(), (ControlCenterSecondaryBinding) this.secondaryBindingProvider.get(), (DetailPanelBinding) this.bindingProvider.get(), (ActivityStarter) this.activityStarterProvider.get(), (StatusBarStateController) this.statusBarStateControllerProvider.get(), (ControlCenterExpandController) this.expandControllerProvider.get(), (DetailPanelTilesDelegate) this.tilesDelegateProvider.get(), (DetailPanelCellAnimator) this.cellAnimatorProvider.get());
    }
}
