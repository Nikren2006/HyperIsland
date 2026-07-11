package miui.systemui.dynamicisland.display;

import H0.k;
import H0.s;
import L0.d;
import M0.c;
import N0.f;
import N0.l;
import g1.E;
import g1.M;
import kotlin.jvm.functions.Function2;
import miui.systemui.dynamicisland.display.AntiBurnInManager;
import miui.systemui.dynamicisland.window.content.DynamicIslandContentView;

/* JADX INFO: loaded from: classes3.dex */
@f(c = "miui.systemui.dynamicisland.display.AntiBurnInManager$extendExpose$job$1", f = "AntiBurnInManager.kt", l = {252}, m = "invokeSuspend")
public final class AntiBurnInManager$extendExpose$job$1 extends l implements Function2 {
    int label;
    final /* synthetic */ AntiBurnInManager this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public AntiBurnInManager$extendExpose$job$1(AntiBurnInManager antiBurnInManager, d dVar) {
        super(2, dVar);
        this.this$0 = antiBurnInManager;
    }

    @Override // N0.a
    public final d create(Object obj, d dVar) {
        return new AntiBurnInManager$extendExpose$job$1(this.this$0, dVar);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(E e2, d dVar) {
        return ((AntiBurnInManager$extendExpose$job$1) create(e2, dVar)).invokeSuspend(s.f314a);
    }

    @Override // N0.a
    public final Object invokeSuspend(Object obj) throws Throwable {
        Object objC = c.c();
        int i2 = this.label;
        if (i2 == 0) {
            k.b(obj);
            long extend_expose_time = AntiBurnInManager.Companion.getEXTEND_EXPOSE_TIME();
            AntiBurnInManager antiBurnInManager = this.this$0;
            DynamicIslandContentView current = antiBurnInManager.getBigIslandHandler().getCurrent();
            AntiBurnInManager.BurnInStates burnInState = current != null ? current.getBurnInState() : null;
            DynamicIslandContentView current2 = this.this$0.getSmallIslandHandler().getCurrent();
            antiBurnInManager.log("extend (" + burnInState + ", " + (current2 != null ? current2.getBurnInState() : null) + ") for " + extend_expose_time);
            this.label = 1;
            if (M.b(extend_expose_time, this) == objC) {
                return objC;
            }
        } else {
            if (i2 != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            k.b(obj);
        }
        this.this$0.enter(3);
        return s.f314a;
    }
}
