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

/* JADX INFO: loaded from: classes3.dex */
@f(c = "miui.systemui.lifecycle.RepeatWhenAttachedKt$repeatWhenAttached$create$1$1", f = "RepeatWhenAttached.kt", l = {112}, m = "invokeSuspend")
public final class RepeatWhenAttachedKt$repeatWhenAttached$create$1$1 extends l implements Function2 {
    final /* synthetic */ Function3 $block;
    final /* synthetic */ View $view;
    private /* synthetic */ Object L$0;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public RepeatWhenAttachedKt$repeatWhenAttached$create$1$1(Function3 function3, View view, d dVar) {
        super(2, dVar);
        this.$block = function3;
        this.$view = view;
    }

    @Override // N0.a
    public final d create(Object obj, d dVar) {
        RepeatWhenAttachedKt$repeatWhenAttached$create$1$1 repeatWhenAttachedKt$repeatWhenAttached$create$1$1 = new RepeatWhenAttachedKt$repeatWhenAttached$create$1$1(this.$block, this.$view, dVar);
        repeatWhenAttachedKt$repeatWhenAttached$create$1$1.L$0 = obj;
        return repeatWhenAttachedKt$repeatWhenAttached$create$1$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(E e2, d dVar) {
        return ((RepeatWhenAttachedKt$repeatWhenAttached$create$1$1) create(e2, dVar)).invokeSuspend(s.f314a);
    }

    @Override // N0.a
    public final Object invokeSuspend(Object obj) throws Throwable {
        Object objC = c.c();
        int i2 = this.label;
        if (i2 == 0) {
            k.b(obj);
            E e2 = (E) this.L$0;
            Function3 function3 = this.$block;
            View view = this.$view;
            this.label = 1;
            if (function3.invoke(e2, view, this) == objC) {
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
