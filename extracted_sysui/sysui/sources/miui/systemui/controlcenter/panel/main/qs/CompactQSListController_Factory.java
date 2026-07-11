package miui.systemui.controlcenter.panel.main.qs;

import android.content.Context;
import android.os.Handler;
import com.android.systemui.plugins.miui.settings.SuperSaveModeController;
import miui.systemui.controlcenter.panel.main.qs.QSItemViewHolder;
import miui.systemui.controlcenter.panel.main.qs.QSRecord;
import miui.systemui.controlcenter.windowview.ControlCenterWindowViewImpl;

/* JADX INFO: loaded from: classes.dex */
public final class CompactQSListController_Factory implements F0.e {
    private final G0.a bgHandlerProvider;
    private final G0.a distributorProvider;
    private final G0.a holderFactoryProvider;
    private final G0.a mainPanelControllerProvider;
    private final G0.a qsControllerProvider;
    private final G0.a qsListControllerProvider;
    private final G0.a recordFactoryProvider;
    private final G0.a superSaveModeControllerProvider;
    private final G0.a sysUIContextProvider;
    private final G0.a windowViewProvider;

    public CompactQSListController_Factory(G0.a aVar, G0.a aVar2, G0.a aVar3, G0.a aVar4, G0.a aVar5, G0.a aVar6, G0.a aVar7, G0.a aVar8, G0.a aVar9, G0.a aVar10) {
        this.windowViewProvider = aVar;
        this.sysUIContextProvider = aVar2;
        this.qsControllerProvider = aVar3;
        this.recordFactoryProvider = aVar4;
        this.holderFactoryProvider = aVar5;
        this.bgHandlerProvider = aVar6;
        this.mainPanelControllerProvider = aVar7;
        this.qsListControllerProvider = aVar8;
        this.distributorProvider = aVar9;
        this.superSaveModeControllerProvider = aVar10;
    }

    public static CompactQSListController_Factory create(G0.a aVar, G0.a aVar2, G0.a aVar3, G0.a aVar4, G0.a aVar5, G0.a aVar6, G0.a aVar7, G0.a aVar8, G0.a aVar9, G0.a aVar10) {
        return new CompactQSListController_Factory(aVar, aVar2, aVar3, aVar4, aVar5, aVar6, aVar7, aVar8, aVar9, aVar10);
    }

    public static CompactQSListController newInstance(ControlCenterWindowViewImpl controlCenterWindowViewImpl, Context context, E0.a aVar, QSRecord.Factory factory, QSItemViewHolder.Factory factory2, Handler handler, E0.a aVar2, E0.a aVar3, E0.a aVar4, SuperSaveModeController superSaveModeController) {
        return new CompactQSListController(controlCenterWindowViewImpl, context, aVar, factory, factory2, handler, aVar2, aVar3, aVar4, superSaveModeController);
    }

    @Override // G0.a
    public CompactQSListController get() {
        return newInstance((ControlCenterWindowViewImpl) this.windowViewProvider.get(), (Context) this.sysUIContextProvider.get(), F0.d.a(this.qsControllerProvider), (QSRecord.Factory) this.recordFactoryProvider.get(), (QSItemViewHolder.Factory) this.holderFactoryProvider.get(), (Handler) this.bgHandlerProvider.get(), F0.d.a(this.mainPanelControllerProvider), F0.d.a(this.qsListControllerProvider), F0.d.a(this.distributorProvider), (SuperSaveModeController) this.superSaveModeControllerProvider.get());
    }
}
