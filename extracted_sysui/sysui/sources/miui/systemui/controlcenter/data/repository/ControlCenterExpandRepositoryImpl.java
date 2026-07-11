package miui.systemui.controlcenter.data.repository;

import g1.E;
import j1.AbstractC0420h;
import j1.E;
import j1.I;
import j1.InterfaceC0418f;
import kotlin.jvm.internal.n;
import miui.systemui.controlcenter.windowview.ControlCenterWindowViewCreator;
import miui.systemui.dagger.qualifiers.Plugin;

/* JADX INFO: loaded from: classes.dex */
public final class ControlCenterExpandRepositoryImpl implements ControlCenterExpandRepository {
    private final I appearance;
    private final I isVisible;
    private final I mainPanelAppearance;
    private final I mainPanelIsEditMode;
    private final E scope;

    public ControlCenterExpandRepositoryImpl(@Plugin E scope, ControlCenterWindowViewCreator windowViewCreator) {
        n.g(scope, "scope");
        n.g(windowViewCreator, "windowViewCreator");
        this.scope = scope;
        InterfaceC0418f interfaceC0418fC = AbstractC0420h.C(windowViewCreator.getWindowView(), new ControlCenterExpandRepositoryImpl$special$$inlined$flatMapLatest$1(null));
        E.a aVar = j1.E.f4648a;
        j1.E eC = aVar.c();
        Boolean bool = Boolean.FALSE;
        this.isVisible = AbstractC0420h.B(interfaceC0418fC, scope, eC, bool);
        this.appearance = AbstractC0420h.B(AbstractC0420h.C(windowViewCreator.getWindowView(), new ControlCenterExpandRepositoryImpl$special$$inlined$flatMapLatest$2(null)), scope, aVar.c(), bool);
        this.mainPanelAppearance = AbstractC0420h.B(AbstractC0420h.C(windowViewCreator.getWindowView(), new ControlCenterExpandRepositoryImpl$special$$inlined$flatMapLatest$3(null)), scope, aVar.c(), Boolean.TRUE);
        this.mainPanelIsEditMode = AbstractC0420h.B(AbstractC0420h.C(windowViewCreator.getWindowView(), new ControlCenterExpandRepositoryImpl$special$$inlined$flatMapLatest$4(null)), scope, aVar.c(), bool);
    }

    public static /* synthetic */ void getAppearance$annotations() {
    }

    public static /* synthetic */ void getMainPanelAppearance$annotations() {
    }

    public static /* synthetic */ void getMainPanelIsEditMode$annotations() {
    }

    public static /* synthetic */ void isVisible$annotations() {
    }

    @Override // miui.systemui.controlcenter.data.repository.ControlCenterExpandRepository
    public I getAppearance() {
        return this.appearance;
    }

    @Override // miui.systemui.controlcenter.data.repository.ControlCenterExpandRepository
    public I getMainPanelAppearance() {
        return this.mainPanelAppearance;
    }

    @Override // miui.systemui.controlcenter.data.repository.ControlCenterExpandRepository
    public I getMainPanelIsEditMode() {
        return this.mainPanelIsEditMode;
    }

    @Override // miui.systemui.controlcenter.data.repository.ControlCenterExpandRepository
    public I isVisible() {
        return this.isVisible;
    }
}
