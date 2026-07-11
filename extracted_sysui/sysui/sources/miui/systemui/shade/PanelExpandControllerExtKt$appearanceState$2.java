package miui.systemui.shade;

import H0.k;
import H0.s;
import L0.d;
import M0.c;
import N0.b;
import N0.f;
import N0.l;
import com.android.systemui.plugins.miui.shade.PanelExpandController;
import j1.InterfaceC0419g;
import kotlin.jvm.functions.Function2;

/* JADX INFO: loaded from: classes4.dex */
@f(c = "miui.systemui.shade.PanelExpandControllerExtKt$appearanceState$2", f = "PanelExpandControllerExt.kt", l = {16}, m = "invokeSuspend")
public final class PanelExpandControllerExtKt$appearanceState$2 extends l implements Function2 {
    final /* synthetic */ PanelExpandController $this_appearanceState;
    private /* synthetic */ Object L$0;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public PanelExpandControllerExtKt$appearanceState$2(PanelExpandController panelExpandController, d dVar) {
        super(2, dVar);
        this.$this_appearanceState = panelExpandController;
    }

    @Override // N0.a
    public final d create(Object obj, d dVar) {
        PanelExpandControllerExtKt$appearanceState$2 panelExpandControllerExtKt$appearanceState$2 = new PanelExpandControllerExtKt$appearanceState$2(this.$this_appearanceState, dVar);
        panelExpandControllerExtKt$appearanceState$2.L$0 = obj;
        return panelExpandControllerExtKt$appearanceState$2;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(InterfaceC0419g interfaceC0419g, d dVar) {
        return ((PanelExpandControllerExtKt$appearanceState$2) create(interfaceC0419g, dVar)).invokeSuspend(s.f314a);
    }

    @Override // N0.a
    public final Object invokeSuspend(Object obj) throws Throwable {
        Object objC = c.c();
        int i2 = this.label;
        if (i2 == 0) {
            k.b(obj);
            InterfaceC0419g interfaceC0419g = (InterfaceC0419g) this.L$0;
            Boolean boolA = b.a(this.$this_appearanceState.getAppearance());
            this.label = 1;
            if (interfaceC0419g.emit(boolA, this) == objC) {
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
}
