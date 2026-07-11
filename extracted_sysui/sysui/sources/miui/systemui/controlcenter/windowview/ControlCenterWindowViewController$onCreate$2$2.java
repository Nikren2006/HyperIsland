package miui.systemui.controlcenter.windowview;

import H0.s;
import g1.E;
import j1.I;
import j1.InterfaceC0419g;
import kotlin.jvm.functions.Function2;

/* JADX INFO: loaded from: classes.dex */
@N0.f(c = "miui.systemui.controlcenter.windowview.ControlCenterWindowViewController$onCreate$2$2", f = "ControlCenterWindowViewController.kt", l = {369}, m = "invokeSuspend")
public final class ControlCenterWindowViewController$onCreate$2$2 extends N0.l implements Function2 {
    int label;
    final /* synthetic */ ControlCenterWindowViewController this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ControlCenterWindowViewController$onCreate$2$2(ControlCenterWindowViewController controlCenterWindowViewController, L0.d dVar) {
        super(2, dVar);
        this.this$0 = controlCenterWindowViewController;
    }

    @Override // N0.a
    public final L0.d create(Object obj, L0.d dVar) {
        return new ControlCenterWindowViewController$onCreate$2$2(this.this$0, dVar);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(E e2, L0.d dVar) {
        return ((ControlCenterWindowViewController$onCreate$2$2) create(e2, dVar)).invokeSuspend(s.f314a);
    }

    @Override // N0.a
    public final Object invokeSuspend(Object obj) throws Throwable {
        Object objC = M0.c.c();
        int i2 = this.label;
        if (i2 == 0) {
            H0.k.b(obj);
            I clipFooter = this.this$0.getMainPanelController().getClipFooter();
            final ControlCenterWindowViewController controlCenterWindowViewController = this.this$0;
            InterfaceC0419g interfaceC0419g = new InterfaceC0419g() { // from class: miui.systemui.controlcenter.windowview.ControlCenterWindowViewController$onCreate$2$2.1
                @Override // j1.InterfaceC0419g
                public /* bridge */ /* synthetic */ Object emit(Object obj2, L0.d dVar) {
                    return emit(((Boolean) obj2).booleanValue(), dVar);
                }

                public final Object emit(boolean z2, L0.d dVar) {
                    ControlCenterWindowViewController.updateClipFooter$default(controlCenterWindowViewController, false, 1, null);
                    return s.f314a;
                }
            };
            this.label = 1;
            if (clipFooter.collect(interfaceC0419g, this) == objC) {
                return objC;
            }
        } else {
            if (i2 != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            H0.k.b(obj);
        }
        throw new H0.c();
    }
}
