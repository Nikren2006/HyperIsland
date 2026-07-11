package miui.systemui.controlcenter.panel.secondary.detail;

import android.content.Context;

/* JADX INFO: loaded from: classes.dex */
public final class DetailPanelAnimator_Factory implements F0.e {
    private final G0.a contextProvider;
    private final G0.a detailPanelControllerProvider;
    private final G0.a mainPanelControllerProvider;
    private final G0.a tilesDelegateProvider;
    private final G0.a windowViewControllerProvider;

    public DetailPanelAnimator_Factory(G0.a aVar, G0.a aVar2, G0.a aVar3, G0.a aVar4, G0.a aVar5) {
        this.contextProvider = aVar;
        this.detailPanelControllerProvider = aVar2;
        this.windowViewControllerProvider = aVar3;
        this.mainPanelControllerProvider = aVar4;
        this.tilesDelegateProvider = aVar5;
    }

    public static DetailPanelAnimator_Factory create(G0.a aVar, G0.a aVar2, G0.a aVar3, G0.a aVar4, G0.a aVar5) {
        return new DetailPanelAnimator_Factory(aVar, aVar2, aVar3, aVar4, aVar5);
    }

    public static DetailPanelAnimator newInstance(Context context, E0.a aVar, E0.a aVar2, E0.a aVar3, E0.a aVar4) {
        return new DetailPanelAnimator(context, aVar, aVar2, aVar3, aVar4);
    }

    @Override // G0.a
    public DetailPanelAnimator get() {
        return newInstance((Context) this.contextProvider.get(), F0.d.a(this.detailPanelControllerProvider), F0.d.a(this.windowViewControllerProvider), F0.d.a(this.mainPanelControllerProvider), F0.d.a(this.tilesDelegateProvider));
    }
}
