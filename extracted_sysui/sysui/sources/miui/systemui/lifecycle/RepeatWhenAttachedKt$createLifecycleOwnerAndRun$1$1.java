package miui.systemui.lifecycle;

import H0.k;
import H0.s;
import L0.d;
import M0.c;
import N0.f;
import N0.l;
import android.view.View;
import g1.E;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import miui.systemui.controlcenter.panel.secondary.SecondaryParamsKt;

/* JADX INFO: loaded from: classes3.dex */
@f(c = "miui.systemui.lifecycle.RepeatWhenAttachedKt$createLifecycleOwnerAndRun$1$1", f = "RepeatWhenAttached.kt", l = {SecondaryParamsKt.FROM_WIFI}, m = "invokeSuspend")
public final class RepeatWhenAttachedKt$createLifecycleOwnerAndRun$1$1 extends l implements Function2 {
    final /* synthetic */ Function3 $block;
    final /* synthetic */ ViewLifecycleOwner $this_apply;
    final /* synthetic */ View $view;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public RepeatWhenAttachedKt$createLifecycleOwnerAndRun$1$1(Function3 function3, ViewLifecycleOwner viewLifecycleOwner, View view, d dVar) {
        super(2, dVar);
        this.$block = function3;
        this.$this_apply = viewLifecycleOwner;
        this.$view = view;
    }

    @Override // N0.a
    public final d create(Object obj, d dVar) {
        return new RepeatWhenAttachedKt$createLifecycleOwnerAndRun$1$1(this.$block, this.$this_apply, this.$view, dVar);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(E e2, d dVar) {
        return ((RepeatWhenAttachedKt$createLifecycleOwnerAndRun$1$1) create(e2, dVar)).invokeSuspend(s.f314a);
    }

    @Override // N0.a
    public final Object invokeSuspend(Object obj) throws Throwable {
        Object objC = c.c();
        int i2 = this.label;
        if (i2 == 0) {
            k.b(obj);
            Function3 function3 = this.$block;
            ViewLifecycleOwner viewLifecycleOwner = this.$this_apply;
            View view = this.$view;
            this.label = 1;
            if (function3.invoke(viewLifecycleOwner, view, this) == objC) {
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
