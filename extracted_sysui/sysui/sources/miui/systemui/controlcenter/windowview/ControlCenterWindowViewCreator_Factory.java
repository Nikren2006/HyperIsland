package miui.systemui.controlcenter.windowview;

/* JADX INFO: loaded from: classes.dex */
public final class ControlCenterWindowViewCreator_Factory implements F0.e {
    private final G0.a contextProvider;
    private final G0.a injectionInflationControllerProvider;

    public ControlCenterWindowViewCreator_Factory(G0.a aVar, G0.a aVar2) {
        this.contextProvider = aVar;
        this.injectionInflationControllerProvider = aVar2;
    }

    public static ControlCenterWindowViewCreator_Factory create(G0.a aVar, G0.a aVar2) {
        return new ControlCenterWindowViewCreator_Factory(aVar, aVar2);
    }

    public static ControlCenterWindowViewCreator newInstance(E0.a aVar, E0.a aVar2) {
        return new ControlCenterWindowViewCreator(aVar, aVar2);
    }

    @Override // G0.a
    public ControlCenterWindowViewCreator get() {
        return newInstance(F0.d.a(this.contextProvider), F0.d.a(this.injectionInflationControllerProvider));
    }
}
