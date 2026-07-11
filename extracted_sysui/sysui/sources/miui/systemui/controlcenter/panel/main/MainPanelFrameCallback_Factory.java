package miui.systemui.controlcenter.panel.main;

/* JADX INFO: loaded from: classes.dex */
public final class MainPanelFrameCallback_Factory implements F0.e {
    private final G0.a contentDistributorProvider;
    private final G0.a spreadRowsAnimatorProvider;

    public MainPanelFrameCallback_Factory(G0.a aVar, G0.a aVar2) {
        this.contentDistributorProvider = aVar;
        this.spreadRowsAnimatorProvider = aVar2;
    }

    public static MainPanelFrameCallback_Factory create(G0.a aVar, G0.a aVar2) {
        return new MainPanelFrameCallback_Factory(aVar, aVar2);
    }

    public static MainPanelFrameCallback newInstance(E0.a aVar, E0.a aVar2) {
        return new MainPanelFrameCallback(aVar, aVar2);
    }

    @Override // G0.a
    public MainPanelFrameCallback get() {
        return newInstance(F0.d.a(this.contentDistributorProvider), F0.d.a(this.spreadRowsAnimatorProvider));
    }
}
