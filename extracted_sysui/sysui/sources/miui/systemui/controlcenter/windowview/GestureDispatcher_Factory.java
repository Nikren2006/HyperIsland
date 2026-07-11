package miui.systemui.controlcenter.windowview;

/* JADX INFO: loaded from: classes.dex */
public final class GestureDispatcher_Factory implements F0.e {
    private final G0.a windowViewControllerProvider;
    private final G0.a windowViewImplProvider;

    public GestureDispatcher_Factory(G0.a aVar, G0.a aVar2) {
        this.windowViewImplProvider = aVar;
        this.windowViewControllerProvider = aVar2;
    }

    public static GestureDispatcher_Factory create(G0.a aVar, G0.a aVar2) {
        return new GestureDispatcher_Factory(aVar, aVar2);
    }

    public static GestureDispatcher newInstance(ControlCenterWindowViewImpl controlCenterWindowViewImpl, E0.a aVar) {
        return new GestureDispatcher(controlCenterWindowViewImpl, aVar);
    }

    @Override // G0.a
    public GestureDispatcher get() {
        return newInstance((ControlCenterWindowViewImpl) this.windowViewImplProvider.get(), F0.d.a(this.windowViewControllerProvider));
    }
}
