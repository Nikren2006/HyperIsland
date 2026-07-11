package miui.systemui.controlcenter.panel.main.qs;

import android.content.Context;
import android.os.Handler;
import com.android.systemui.plugins.miui.qs.MiuiQSHost;
import miui.systemui.controlcenter.panel.main.qs.QSItemViewHolder;
import miui.systemui.controlcenter.panel.main.qs.QSRecord;
import miui.systemui.controlcenter.qs.customize.TileQueryHelper;
import miui.systemui.controlcenter.windowview.ControlCenterWindowViewImpl;
import miui.systemui.util.concurrency.DelayableExecutor;

/* JADX INFO: loaded from: classes.dex */
public final class QSListController_Factory implements F0.e {
    private final G0.a bgHandlerProvider;
    private final G0.a distributorProvider;
    private final G0.a holderFactoryProvider;
    private final G0.a hostProvider;
    private final G0.a mainPanelControllerProvider;
    private final G0.a qsCardsControllerProvider;
    private final G0.a qsControllerProvider;
    private final G0.a recordFactoryProvider;
    private final G0.a sysUIContextProvider;
    private final G0.a tileQueryHelperProvider;
    private final G0.a uiExecutorProvider;
    private final G0.a uiHandlerProvider;
    private final G0.a windowViewProvider;

    public QSListController_Factory(G0.a aVar, G0.a aVar2, G0.a aVar3, G0.a aVar4, G0.a aVar5, G0.a aVar6, G0.a aVar7, G0.a aVar8, G0.a aVar9, G0.a aVar10, G0.a aVar11, G0.a aVar12, G0.a aVar13) {
        this.windowViewProvider = aVar;
        this.sysUIContextProvider = aVar2;
        this.qsControllerProvider = aVar3;
        this.hostProvider = aVar4;
        this.distributorProvider = aVar5;
        this.mainPanelControllerProvider = aVar6;
        this.recordFactoryProvider = aVar7;
        this.tileQueryHelperProvider = aVar8;
        this.uiExecutorProvider = aVar9;
        this.uiHandlerProvider = aVar10;
        this.bgHandlerProvider = aVar11;
        this.qsCardsControllerProvider = aVar12;
        this.holderFactoryProvider = aVar13;
    }

    public static QSListController_Factory create(G0.a aVar, G0.a aVar2, G0.a aVar3, G0.a aVar4, G0.a aVar5, G0.a aVar6, G0.a aVar7, G0.a aVar8, G0.a aVar9, G0.a aVar10, G0.a aVar11, G0.a aVar12, G0.a aVar13) {
        return new QSListController_Factory(aVar, aVar2, aVar3, aVar4, aVar5, aVar6, aVar7, aVar8, aVar9, aVar10, aVar11, aVar12, aVar13);
    }

    public static QSListController newInstance(ControlCenterWindowViewImpl controlCenterWindowViewImpl, Context context, E0.a aVar, MiuiQSHost miuiQSHost, E0.a aVar2, E0.a aVar3, QSRecord.Factory factory, TileQueryHelper tileQueryHelper, DelayableExecutor delayableExecutor, Handler handler, Handler handler2, QSCardsController qSCardsController, QSItemViewHolder.Factory factory2) {
        return new QSListController(controlCenterWindowViewImpl, context, aVar, miuiQSHost, aVar2, aVar3, factory, tileQueryHelper, delayableExecutor, handler, handler2, qSCardsController, factory2);
    }

    @Override // G0.a
    public QSListController get() {
        return newInstance((ControlCenterWindowViewImpl) this.windowViewProvider.get(), (Context) this.sysUIContextProvider.get(), F0.d.a(this.qsControllerProvider), (MiuiQSHost) this.hostProvider.get(), F0.d.a(this.distributorProvider), F0.d.a(this.mainPanelControllerProvider), (QSRecord.Factory) this.recordFactoryProvider.get(), (TileQueryHelper) this.tileQueryHelperProvider.get(), (DelayableExecutor) this.uiExecutorProvider.get(), (Handler) this.uiHandlerProvider.get(), (Handler) this.bgHandlerProvider.get(), (QSCardsController) this.qsCardsControllerProvider.get(), (QSItemViewHolder.Factory) this.holderFactoryProvider.get());
    }
}
