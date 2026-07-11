package miui.systemui.controlcenter.panel.main;

import H0.s;
import N0.l;
import V0.n;

/* JADX INFO: loaded from: classes.dex */
@N0.f(c = "miui.systemui.controlcenter.panel.main.MainPanelController$clipFooter$1", f = "MainPanelController.kt", l = {}, m = "invokeSuspend")
public final class MainPanelController$clipFooter$1 extends l implements n {
    /* synthetic */ boolean Z$0;
    /* synthetic */ boolean Z$1;
    /* synthetic */ boolean Z$2;
    int label;

    public MainPanelController$clipFooter$1(L0.d dVar) {
        super(4, dVar);
    }

    @Override // V0.n
    public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2, Object obj3, Object obj4) {
        return invoke(((Boolean) obj).booleanValue(), ((Boolean) obj2).booleanValue(), ((Boolean) obj3).booleanValue(), (L0.d) obj4);
    }

    @Override // N0.a
    public final Object invokeSuspend(Object obj) throws Throwable {
        M0.c.c();
        if (this.label != 0) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        H0.k.b(obj);
        return N0.b.a(this.Z$0 || this.Z$1 || this.Z$2);
    }

    public final Object invoke(boolean z2, boolean z3, boolean z4, L0.d dVar) {
        MainPanelController$clipFooter$1 mainPanelController$clipFooter$1 = new MainPanelController$clipFooter$1(dVar);
        mainPanelController$clipFooter$1.Z$0 = z2;
        mainPanelController$clipFooter$1.Z$1 = z3;
        mainPanelController$clipFooter$1.Z$2 = z4;
        return mainPanelController$clipFooter$1.invokeSuspend(s.f314a);
    }
}
