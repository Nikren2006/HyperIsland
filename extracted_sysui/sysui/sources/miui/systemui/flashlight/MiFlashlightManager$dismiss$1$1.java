package miui.systemui.flashlight;

import H0.k;
import H0.s;
import L0.d;
import N0.f;
import N0.l;
import g1.E;
import j1.t;
import kotlin.jvm.functions.Function2;
import miui.systemui.flashlight.dagger.MiFlashlightComponent;

/* JADX INFO: loaded from: classes3.dex */
@f(c = "miui.systemui.flashlight.MiFlashlightManager$dismiss$1$1", f = "MiFlashlightManager.kt", l = {185}, m = "invokeSuspend")
public final class MiFlashlightManager$dismiss$1$1 extends l implements Function2 {
    final /* synthetic */ MiFlashlightComponent $this_run;
    int label;
    final /* synthetic */ MiFlashlightManager this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public MiFlashlightManager$dismiss$1$1(MiFlashlightManager miFlashlightManager, MiFlashlightComponent miFlashlightComponent, d dVar) {
        super(2, dVar);
        this.this$0 = miFlashlightManager;
        this.$this_run = miFlashlightComponent;
    }

    @Override // N0.a
    public final d create(Object obj, d dVar) {
        return new MiFlashlightManager$dismiss$1$1(this.this$0, this.$this_run, dVar);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(E e2, d dVar) {
        return ((MiFlashlightManager$dismiss$1$1) create(e2, dVar)).invokeSuspend(s.f314a);
    }

    @Override // N0.a
    public final Object invokeSuspend(Object obj) throws Throwable {
        Object objC = M0.c.c();
        int i2 = this.label;
        if (i2 == 0) {
            k.b(obj);
            t tVar = this.this$0._miFlashlightEventFlow;
            MiFlashlightWindowHideEvent miFlashlightWindowHideEvent = new MiFlashlightWindowHideEvent(this.$this_run.getRootView().hashCode());
            this.label = 1;
            if (tVar.emit(miFlashlightWindowHideEvent, this) == objC) {
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
