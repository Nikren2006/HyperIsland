package miui.systemui.flashlight;

import H0.k;
import H0.s;
import L0.d;
import N0.f;
import N0.l;
import android.view.View;
import g1.E;
import j1.t;
import kotlin.jvm.functions.Function2;
import miui.systemui.flashlight.view.MiFlashlightLayout;

/* JADX INFO: loaded from: classes3.dex */
@f(c = "miui.systemui.flashlight.MiFlashlightManager$hideFlashlight$1$1", f = "MiFlashlightManager.kt", l = {143}, m = "invokeSuspend")
public final class MiFlashlightManager$hideFlashlight$1$1 extends l implements Function2 {
    final /* synthetic */ View $rootView;
    int label;
    final /* synthetic */ MiFlashlightManager this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public MiFlashlightManager$hideFlashlight$1$1(MiFlashlightManager miFlashlightManager, View view, d dVar) {
        super(2, dVar);
        this.this$0 = miFlashlightManager;
        this.$rootView = view;
    }

    @Override // N0.a
    public final d create(Object obj, d dVar) {
        return new MiFlashlightManager$hideFlashlight$1$1(this.this$0, this.$rootView, dVar);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(E e2, d dVar) {
        return ((MiFlashlightManager$hideFlashlight$1$1) create(e2, dVar)).invokeSuspend(s.f314a);
    }

    @Override // N0.a
    public final Object invokeSuspend(Object obj) throws Throwable {
        Object objC = M0.c.c();
        int i2 = this.label;
        if (i2 == 0) {
            k.b(obj);
            t tVar = this.this$0._miFlashlightEventFlow;
            MiFlashlightHideEvent miFlashlightHideEvent = new MiFlashlightHideEvent(this.$rootView.hashCode());
            this.label = 1;
            if (tVar.emit(miFlashlightHideEvent, this) == objC) {
                return objC;
            }
        } else {
            if (i2 != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            k.b(obj);
        }
        View view = this.$rootView;
        MiFlashlightLayout miFlashlightLayout = view instanceof MiFlashlightLayout ? (MiFlashlightLayout) view : null;
        if (miFlashlightLayout != null) {
            miFlashlightLayout.hideView();
        }
        return s.f314a;
    }
}
