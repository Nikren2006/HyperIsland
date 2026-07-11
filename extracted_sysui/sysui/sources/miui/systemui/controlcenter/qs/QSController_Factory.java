package miui.systemui.controlcenter.qs;

import F0.d;
import F0.e;
import android.os.Looper;
import com.android.systemui.plugins.miui.qs.MiuiQSHost;
import miui.systemui.controlcenter.windowview.ControlCenterWindowViewImpl;

/* JADX INFO: loaded from: classes.dex */
public final class QSController_Factory implements e {
    private final G0.a detailControllerProvider;
    private final G0.a hostProvider;
    private final G0.a mainLooperProvider;
    private final G0.a modeControllerProvider;
    private final G0.a msgHeaderController2Provider;
    private final G0.a qsListControllerProvider;
    private final G0.a secondaryPanelRouterProvider;
    private final G0.a windowViewControllerProvider;
    private final G0.a windowViewProvider;

    public QSController_Factory(G0.a aVar, G0.a aVar2, G0.a aVar3, G0.a aVar4, G0.a aVar5, G0.a aVar6, G0.a aVar7, G0.a aVar8, G0.a aVar9) {
        this.windowViewProvider = aVar;
        this.mainLooperProvider = aVar2;
        this.hostProvider = aVar3;
        this.secondaryPanelRouterProvider = aVar4;
        this.detailControllerProvider = aVar5;
        this.qsListControllerProvider = aVar6;
        this.windowViewControllerProvider = aVar7;
        this.msgHeaderController2Provider = aVar8;
        this.modeControllerProvider = aVar9;
    }

    public static QSController_Factory create(G0.a aVar, G0.a aVar2, G0.a aVar3, G0.a aVar4, G0.a aVar5, G0.a aVar6, G0.a aVar7, G0.a aVar8, G0.a aVar9) {
        return new QSController_Factory(aVar, aVar2, aVar3, aVar4, aVar5, aVar6, aVar7, aVar8, aVar9);
    }

    public static QSController newInstance(ControlCenterWindowViewImpl controlCenterWindowViewImpl, Looper looper, MiuiQSHost miuiQSHost, E0.a aVar, E0.a aVar2, E0.a aVar3, E0.a aVar4, E0.a aVar5, E0.a aVar6) {
        return new QSController(controlCenterWindowViewImpl, looper, miuiQSHost, aVar, aVar2, aVar3, aVar4, aVar5, aVar6);
    }

    @Override // G0.a
    public QSController get() {
        return newInstance((ControlCenterWindowViewImpl) this.windowViewProvider.get(), (Looper) this.mainLooperProvider.get(), (MiuiQSHost) this.hostProvider.get(), d.a(this.secondaryPanelRouterProvider), d.a(this.detailControllerProvider), d.a(this.qsListControllerProvider), d.a(this.windowViewControllerProvider), d.a(this.msgHeaderController2Provider), d.a(this.modeControllerProvider));
    }
}
