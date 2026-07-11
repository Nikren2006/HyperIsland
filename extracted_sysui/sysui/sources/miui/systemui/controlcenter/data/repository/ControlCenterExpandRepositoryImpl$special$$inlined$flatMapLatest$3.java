package miui.systemui.controlcenter.data.repository;

import H0.k;
import H0.s;
import L0.d;
import M0.c;
import N0.f;
import N0.l;
import j1.AbstractC0420h;
import j1.InterfaceC0418f;
import j1.InterfaceC0419g;
import kotlin.jvm.functions.Function3;
import miui.systemui.controlcenter.dagger.ControlCenterViewComponent;
import miui.systemui.controlcenter.panel.main.MainPanelAnimController;
import miui.systemui.controlcenter.windowview.ControlCenterWindowViewImpl;
import miuix.view.HapticFeedbackConstants;

/* JADX INFO: loaded from: classes.dex */
@f(c = "miui.systemui.controlcenter.data.repository.ControlCenterExpandRepositoryImpl$special$$inlined$flatMapLatest$3", f = "ControlCenterExpandRepositoryImpl.kt", l = {HapticFeedbackConstants.MIUI_KEYBOARD_CLICKY_DOWN_RTP}, m = "invokeSuspend")
public final class ControlCenterExpandRepositoryImpl$special$$inlined$flatMapLatest$3 extends l implements Function3 {
    private /* synthetic */ Object L$0;
    /* synthetic */ Object L$1;
    int label;

    public ControlCenterExpandRepositoryImpl$special$$inlined$flatMapLatest$3(d dVar) {
        super(3, dVar);
    }

    public final Object invoke(InterfaceC0419g interfaceC0419g, ControlCenterWindowViewImpl controlCenterWindowViewImpl, d dVar) {
        ControlCenterExpandRepositoryImpl$special$$inlined$flatMapLatest$3 controlCenterExpandRepositoryImpl$special$$inlined$flatMapLatest$3 = new ControlCenterExpandRepositoryImpl$special$$inlined$flatMapLatest$3(dVar);
        controlCenterExpandRepositoryImpl$special$$inlined$flatMapLatest$3.L$0 = interfaceC0419g;
        controlCenterExpandRepositoryImpl$special$$inlined$flatMapLatest$3.L$1 = controlCenterWindowViewImpl;
        return controlCenterExpandRepositoryImpl$special$$inlined$flatMapLatest$3.invokeSuspend(s.f314a);
    }

    @Override // N0.a
    public final Object invokeSuspend(Object obj) throws Throwable {
        InterfaceC0418f interfaceC0418fR;
        ControlCenterViewComponent viewComponent;
        MainPanelAnimController mainPanelAnimController;
        Object objC = c.c();
        int i2 = this.label;
        if (i2 == 0) {
            k.b(obj);
            InterfaceC0419g interfaceC0419g = (InterfaceC0419g) this.L$0;
            ControlCenterWindowViewImpl controlCenterWindowViewImpl = (ControlCenterWindowViewImpl) this.L$1;
            if (controlCenterWindowViewImpl == null || (viewComponent = controlCenterWindowViewImpl.getViewComponent()) == null || (mainPanelAnimController = viewComponent.getMainPanelAnimController()) == null || (interfaceC0418fR = mainPanelAnimController.getMainPanelAppearance()) == null) {
                interfaceC0418fR = AbstractC0420h.r();
            }
            this.label = 1;
            if (AbstractC0420h.q(interfaceC0419g, interfaceC0418fR, this) == objC) {
                return objC;
            }
        } else {
            if (i2 != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            k.b(obj);
        }
        return s.f314a;
    }

    @Override // kotlin.jvm.functions.Function3
    public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2, Object obj3) {
        return invoke((InterfaceC0419g) obj, (ControlCenterWindowViewImpl) obj2, (d) obj3);
    }
}
