package miui.systemui.controlcenter.panel.secondary.detail;

import android.content.Context;
import miui.systemui.controlcenter.databinding.ControlCenterSecondaryBinding;

/* JADX INFO: loaded from: classes.dex */
public final class DetailPanelTilesDelegate_Factory implements F0.e {
    private final G0.a qsControllerLazyProvider;
    private final G0.a secondaryBindingProvider;
    private final G0.a sysUIContextProvider;
    private final G0.a windowViewControllerProvider;

    public DetailPanelTilesDelegate_Factory(G0.a aVar, G0.a aVar2, G0.a aVar3, G0.a aVar4) {
        this.sysUIContextProvider = aVar;
        this.secondaryBindingProvider = aVar2;
        this.qsControllerLazyProvider = aVar3;
        this.windowViewControllerProvider = aVar4;
    }

    public static DetailPanelTilesDelegate_Factory create(G0.a aVar, G0.a aVar2, G0.a aVar3, G0.a aVar4) {
        return new DetailPanelTilesDelegate_Factory(aVar, aVar2, aVar3, aVar4);
    }

    public static DetailPanelTilesDelegate newInstance(Context context, ControlCenterSecondaryBinding controlCenterSecondaryBinding, E0.a aVar, E0.a aVar2) {
        return new DetailPanelTilesDelegate(context, controlCenterSecondaryBinding, aVar, aVar2);
    }

    @Override // G0.a
    public DetailPanelTilesDelegate get() {
        return newInstance((Context) this.sysUIContextProvider.get(), (ControlCenterSecondaryBinding) this.secondaryBindingProvider.get(), F0.d.a(this.qsControllerLazyProvider), F0.d.a(this.windowViewControllerProvider));
    }
}
