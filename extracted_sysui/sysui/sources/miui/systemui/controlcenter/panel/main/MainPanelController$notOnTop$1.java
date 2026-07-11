package miui.systemui.controlcenter.panel.main;

import H0.s;
import N0.l;
import kotlin.jvm.functions.Function3;

/* JADX INFO: loaded from: classes.dex */
@N0.f(c = "miui.systemui.controlcenter.panel.main.MainPanelController$notOnTop$1", f = "MainPanelController.kt", l = {}, m = "invokeSuspend")
public final class MainPanelController$notOnTop$1 extends l implements Function3 {
    /* synthetic */ boolean Z$0;
    /* synthetic */ boolean Z$1;
    int label;

    public MainPanelController$notOnTop$1(L0.d dVar) {
        super(3, dVar);
    }

    @Override // kotlin.jvm.functions.Function3
    public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2, Object obj3) {
        return invoke(((Boolean) obj).booleanValue(), ((Boolean) obj2).booleanValue(), (L0.d) obj3);
    }

    @Override // N0.a
    public final Object invokeSuspend(Object obj) throws Throwable {
        M0.c.c();
        if (this.label != 0) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        H0.k.b(obj);
        return N0.b.a(this.Z$0 || this.Z$1);
    }

    public final Object invoke(boolean z2, boolean z3, L0.d dVar) {
        MainPanelController$notOnTop$1 mainPanelController$notOnTop$1 = new MainPanelController$notOnTop$1(dVar);
        mainPanelController$notOnTop$1.Z$0 = z2;
        mainPanelController$notOnTop$1.Z$1 = z3;
        return mainPanelController$notOnTop$1.invokeSuspend(s.f314a);
    }
}
