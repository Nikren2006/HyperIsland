package miui.systemui.dynamicisland.display;

import H0.k;
import H0.s;
import L0.d;
import M0.c;
import N0.f;
import N0.l;
import android.util.Log;
import g1.E;
import g1.M;
import kotlin.jvm.functions.Function2;

/* JADX INFO: loaded from: classes3.dex */
@f(c = "miui.systemui.dynamicisland.display.AntiBurnInManager$scheduleEnter$job$1", f = "AntiBurnInManager.kt", l = {180}, m = "invokeSuspend")
public final class AntiBurnInManager$scheduleEnter$job$1 extends l implements Function2 {
    final /* synthetic */ String $key;
    int label;
    final /* synthetic */ AntiBurnInManager this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public AntiBurnInManager$scheduleEnter$job$1(AntiBurnInManager antiBurnInManager, String str, d dVar) {
        super(2, dVar);
        this.this$0 = antiBurnInManager;
        this.$key = str;
    }

    @Override // N0.a
    public final d create(Object obj, d dVar) {
        return new AntiBurnInManager$scheduleEnter$job$1(this.this$0, this.$key, dVar);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(E e2, d dVar) {
        return ((AntiBurnInManager$scheduleEnter$job$1) create(e2, dVar)).invokeSuspend(s.f314a);
    }

    @Override // N0.a
    public final Object invokeSuspend(Object obj) throws Throwable {
        Object objC = c.c();
        int i2 = this.label;
        if (i2 == 0) {
            k.b(obj);
            long jCalculateRemainingTime = this.this$0.calculateRemainingTime();
            Log.i("DynamicIslandBurnIn", "enter burn in after:" + jCalculateRemainingTime + " for " + this.$key + ", current:" + this.this$0.getCurrentState());
            this.label = 1;
            if (M.b(jCalculateRemainingTime, this) == objC) {
                return objC;
            }
        } else {
            if (i2 != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            k.b(obj);
        }
        this.this$0.enter(1);
        return s.f314a;
    }
}
