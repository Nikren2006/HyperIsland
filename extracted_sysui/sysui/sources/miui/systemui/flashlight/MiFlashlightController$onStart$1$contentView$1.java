package miui.systemui.flashlight;

import H0.k;
import H0.s;
import L0.d;
import N0.f;
import N0.l;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import g1.E;
import kotlin.jvm.functions.Function2;

/* JADX INFO: loaded from: classes3.dex */
@f(c = "miui.systemui.flashlight.MiFlashlightController$onStart$1$contentView$1", f = "MiFlashlightController.kt", l = {}, m = "invokeSuspend")
public final class MiFlashlightController$onStart$1$contentView$1 extends l implements Function2 {
    int label;
    final /* synthetic */ MiFlashlightController this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public MiFlashlightController$onStart$1$contentView$1(MiFlashlightController miFlashlightController, d dVar) {
        super(2, dVar);
        this.this$0 = miFlashlightController;
    }

    @Override // N0.a
    public final d create(Object obj, d dVar) {
        return new MiFlashlightController$onStart$1$contentView$1(this.this$0, dVar);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(E e2, d dVar) {
        return ((MiFlashlightController$onStart$1$contentView$1) create(e2, dVar)).invokeSuspend(s.f314a);
    }

    @Override // N0.a
    public final Object invokeSuspend(Object obj) throws Throwable {
        M0.c.c();
        if (this.label != 0) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        k.b(obj);
        return LayoutInflater.from(this.this$0.context).inflate(R.layout.mi_flashlight_layout, (ViewGroup) this.this$0.miFlashlightLayout, false);
    }
}
