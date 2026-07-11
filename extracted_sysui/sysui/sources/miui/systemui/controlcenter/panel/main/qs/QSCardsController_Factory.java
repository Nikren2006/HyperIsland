package miui.systemui.controlcenter.panel.main.qs;

import android.content.Context;
import com.android.systemui.plugins.miui.qs.MiuiQSHost;
import miui.systemui.controlcenter.panel.main.qs.QSRecord;
import miui.systemui.controlcenter.windowview.ControlCenterWindowViewImpl;

/* JADX INFO: loaded from: classes.dex */
public final class QSCardsController_Factory implements F0.e {
    private final G0.a distributorProvider;
    private final G0.a hostProvider;
    private final G0.a mainPanelModeControllerProvider;
    private final G0.a mainPanelStyleControllerProvider;
    private final G0.a qsControllerProvider;
    private final G0.a recordFactoryProvider;
    private final G0.a sysUIContextProvider;
    private final G0.a windowViewProvider;

    public QSCardsController_Factory(G0.a aVar, G0.a aVar2, G0.a aVar3, G0.a aVar4, G0.a aVar5, G0.a aVar6, G0.a aVar7, G0.a aVar8) {
        this.windowViewProvider = aVar;
        this.sysUIContextProvider = aVar2;
        this.qsControllerProvider = aVar3;
        this.hostProvider = aVar4;
        this.distributorProvider = aVar5;
        this.recordFactoryProvider = aVar6;
        this.mainPanelModeControllerProvider = aVar7;
        this.mainPanelStyleControllerProvider = aVar8;
    }

    public static QSCardsController_Factory create(G0.a aVar, G0.a aVar2, G0.a aVar3, G0.a aVar4, G0.a aVar5, G0.a aVar6, G0.a aVar7, G0.a aVar8) {
        return new QSCardsController_Factory(aVar, aVar2, aVar3, aVar4, aVar5, aVar6, aVar7, aVar8);
    }

    public static QSCardsController newInstance(ControlCenterWindowViewImpl controlCenterWindowViewImpl, Context context, E0.a aVar, MiuiQSHost miuiQSHost, E0.a aVar2, QSRecord.Factory factory, E0.a aVar3, E0.a aVar4) {
        return new QSCardsController(controlCenterWindowViewImpl, context, aVar, miuiQSHost, aVar2, factory, aVar3, aVar4);
    }

    @Override // G0.a
    public QSCardsController get() {
        return newInstance((ControlCenterWindowViewImpl) this.windowViewProvider.get(), (Context) this.sysUIContextProvider.get(), F0.d.a(this.qsControllerProvider), (MiuiQSHost) this.hostProvider.get(), F0.d.a(this.distributorProvider), (QSRecord.Factory) this.recordFactoryProvider.get(), F0.d.a(this.mainPanelModeControllerProvider), F0.d.a(this.mainPanelStyleControllerProvider));
    }
}
