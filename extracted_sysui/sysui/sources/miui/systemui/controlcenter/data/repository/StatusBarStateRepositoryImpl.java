package miui.systemui.controlcenter.data.repository;

import g1.E;
import j1.AbstractC0420h;
import j1.I;
import kotlin.jvm.internal.n;
import miui.systemui.controlcenter.windowview.ControlCenterWindowViewCreator;
import miui.systemui.dagger.qualifiers.Plugin;
import miui.systemui.statusbar.data.repository.StatusBarStateRepository;

/* JADX INFO: loaded from: classes.dex */
public final class StatusBarStateRepositoryImpl implements StatusBarStateRepository {
    private final E scope;
    private final I state;

    public StatusBarStateRepositoryImpl(@Plugin E scope, ControlCenterWindowViewCreator windowViewCreator) {
        n.g(scope, "scope");
        n.g(windowViewCreator, "windowViewCreator");
        this.scope = scope;
        this.state = AbstractC0420h.B(AbstractC0420h.C(windowViewCreator.getWindowView(), new StatusBarStateRepositoryImpl$special$$inlined$flatMapLatest$1(null)), scope, j1.E.f4648a.c(), 0);
    }

    public static /* synthetic */ void getState$annotations() {
    }

    @Override // miui.systemui.statusbar.data.repository.StatusBarStateRepository
    public I getState() {
        return this.state;
    }
}
