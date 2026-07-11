package miui.systemui.controlcenter.panel.main.qs;

import miui.systemui.controlcenter.panel.main.qs.CompactQSCardViewHolder;
import miui.systemui.controlcenter.panel.main.qs.QSRecord;
import miui.systemui.controlcenter.windowview.ControlCenterWindowViewImpl;

/* JADX INFO: loaded from: classes.dex */
public final class CompactQSCardController_Factory implements F0.e {
    private final G0.a holderFactoryProvider;
    private final G0.a mainPanelControllerProvider;
    private final G0.a qsControllerProvider;
    private final G0.a recordFactoryProvider;
    private final G0.a windowViewProvider;

    public CompactQSCardController_Factory(G0.a aVar, G0.a aVar2, G0.a aVar3, G0.a aVar4, G0.a aVar5) {
        this.windowViewProvider = aVar;
        this.qsControllerProvider = aVar2;
        this.recordFactoryProvider = aVar3;
        this.holderFactoryProvider = aVar4;
        this.mainPanelControllerProvider = aVar5;
    }

    public static CompactQSCardController_Factory create(G0.a aVar, G0.a aVar2, G0.a aVar3, G0.a aVar4, G0.a aVar5) {
        return new CompactQSCardController_Factory(aVar, aVar2, aVar3, aVar4, aVar5);
    }

    public static CompactQSCardController newInstance(ControlCenterWindowViewImpl controlCenterWindowViewImpl, E0.a aVar, QSRecord.Factory factory, CompactQSCardViewHolder.Factory factory2, E0.a aVar2) {
        return new CompactQSCardController(controlCenterWindowViewImpl, aVar, factory, factory2, aVar2);
    }

    @Override // G0.a
    public CompactQSCardController get() {
        return newInstance((ControlCenterWindowViewImpl) this.windowViewProvider.get(), F0.d.a(this.qsControllerProvider), (QSRecord.Factory) this.recordFactoryProvider.get(), (CompactQSCardViewHolder.Factory) this.holderFactoryProvider.get(), F0.d.a(this.mainPanelControllerProvider));
    }
}
