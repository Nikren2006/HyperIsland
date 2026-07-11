package miui.systemui.controlcenter.data.repository;

import F0.e;
import G0.a;
import g1.E;
import miui.systemui.controlcenter.windowview.ControlCenterWindowViewCreator;

/* JADX INFO: loaded from: classes.dex */
public final class ControlCenterExpandRepositoryImpl_Factory implements e {
    private final a scopeProvider;
    private final a windowViewCreatorProvider;

    public ControlCenterExpandRepositoryImpl_Factory(a aVar, a aVar2) {
        this.scopeProvider = aVar;
        this.windowViewCreatorProvider = aVar2;
    }

    public static ControlCenterExpandRepositoryImpl_Factory create(a aVar, a aVar2) {
        return new ControlCenterExpandRepositoryImpl_Factory(aVar, aVar2);
    }

    public static ControlCenterExpandRepositoryImpl newInstance(E e2, ControlCenterWindowViewCreator controlCenterWindowViewCreator) {
        return new ControlCenterExpandRepositoryImpl(e2, controlCenterWindowViewCreator);
    }

    @Override // G0.a
    public ControlCenterExpandRepositoryImpl get() {
        return newInstance((E) this.scopeProvider.get(), (ControlCenterWindowViewCreator) this.windowViewCreatorProvider.get());
    }
}
