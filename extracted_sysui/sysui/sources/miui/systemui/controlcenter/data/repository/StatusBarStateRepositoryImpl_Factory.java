package miui.systemui.controlcenter.data.repository;

import F0.e;
import G0.a;
import g1.E;
import miui.systemui.controlcenter.windowview.ControlCenterWindowViewCreator;

/* JADX INFO: loaded from: classes.dex */
public final class StatusBarStateRepositoryImpl_Factory implements e {
    private final a scopeProvider;
    private final a windowViewCreatorProvider;

    public StatusBarStateRepositoryImpl_Factory(a aVar, a aVar2) {
        this.scopeProvider = aVar;
        this.windowViewCreatorProvider = aVar2;
    }

    public static StatusBarStateRepositoryImpl_Factory create(a aVar, a aVar2) {
        return new StatusBarStateRepositoryImpl_Factory(aVar, aVar2);
    }

    public static StatusBarStateRepositoryImpl newInstance(E e2, ControlCenterWindowViewCreator controlCenterWindowViewCreator) {
        return new StatusBarStateRepositoryImpl(e2, controlCenterWindowViewCreator);
    }

    @Override // G0.a
    public StatusBarStateRepositoryImpl get() {
        return newInstance((E) this.scopeProvider.get(), (ControlCenterWindowViewCreator) this.windowViewCreatorProvider.get());
    }
}
