package miui.systemui.controlcenter.panel.main.qs;

import android.content.Context;
import android.os.Looper;
import androidx.lifecycle.Lifecycle;
import com.android.systemui.plugins.miui.qs.MiuiQSHost;
import miui.systemui.controlcenter.panel.main.qs.QSRecord;
import miui.systemui.controlcenter.panel.secondary.detail.DetailPanelTilesDelegate;
import miui.systemui.util.HapticFeedback;

/* JADX INFO: loaded from: classes.dex */
public final class QSRecord_Factory_Factory implements F0.e {
    private final G0.a contextProvider;
    private final G0.a detailPanelTilesDelegateProvider;
    private final G0.a hapticFeedbackProvider;
    private final G0.a hostProvider;
    private final G0.a lifecycleProvider;
    private final G0.a qsControllerProvider;
    private final G0.a secondaryPanelRouterProvider;
    private final G0.a uiLooperProvider;

    public QSRecord_Factory_Factory(G0.a aVar, G0.a aVar2, G0.a aVar3, G0.a aVar4, G0.a aVar5, G0.a aVar6, G0.a aVar7, G0.a aVar8) {
        this.hostProvider = aVar;
        this.contextProvider = aVar2;
        this.qsControllerProvider = aVar3;
        this.lifecycleProvider = aVar4;
        this.hapticFeedbackProvider = aVar5;
        this.uiLooperProvider = aVar6;
        this.detailPanelTilesDelegateProvider = aVar7;
        this.secondaryPanelRouterProvider = aVar8;
    }

    public static QSRecord_Factory_Factory create(G0.a aVar, G0.a aVar2, G0.a aVar3, G0.a aVar4, G0.a aVar5, G0.a aVar6, G0.a aVar7, G0.a aVar8) {
        return new QSRecord_Factory_Factory(aVar, aVar2, aVar3, aVar4, aVar5, aVar6, aVar7, aVar8);
    }

    public static QSRecord.Factory newInstance(MiuiQSHost miuiQSHost, Context context, E0.a aVar, Lifecycle lifecycle, HapticFeedback hapticFeedback, Looper looper, DetailPanelTilesDelegate detailPanelTilesDelegate, E0.a aVar2) {
        return new QSRecord.Factory(miuiQSHost, context, aVar, lifecycle, hapticFeedback, looper, detailPanelTilesDelegate, aVar2);
    }

    @Override // G0.a
    public QSRecord.Factory get() {
        return newInstance((MiuiQSHost) this.hostProvider.get(), (Context) this.contextProvider.get(), F0.d.a(this.qsControllerProvider), (Lifecycle) this.lifecycleProvider.get(), (HapticFeedback) this.hapticFeedbackProvider.get(), (Looper) this.uiLooperProvider.get(), (DetailPanelTilesDelegate) this.detailPanelTilesDelegateProvider.get(), F0.d.a(this.secondaryPanelRouterProvider));
    }
}
