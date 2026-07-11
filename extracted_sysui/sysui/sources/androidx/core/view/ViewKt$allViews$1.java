package androidx.core.view;

import H0.s;
import android.view.View;
import android.view.ViewGroup;
import e1.AbstractC0342g;
import e1.InterfaceC0340e;
import kotlin.jvm.functions.Function2;
import miuix.responsive.ResponsivePolicy;

/* JADX INFO: loaded from: classes.dex */
@N0.f(c = "androidx.core.view.ViewKt$allViews$1", f = "View.kt", l = {ResponsivePolicy.THRESHOLD_REGULAR_CONTAINER, 412}, m = "invokeSuspend")
public final class ViewKt$allViews$1 extends N0.k implements Function2 {
    final /* synthetic */ View $this_allViews;
    private /* synthetic */ Object L$0;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ViewKt$allViews$1(View view, L0.d dVar) {
        super(2, dVar);
        this.$this_allViews = view;
    }

    @Override // N0.a
    public final L0.d create(Object obj, L0.d dVar) {
        ViewKt$allViews$1 viewKt$allViews$1 = new ViewKt$allViews$1(this.$this_allViews, dVar);
        viewKt$allViews$1.L$0 = obj;
        return viewKt$allViews$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(AbstractC0342g abstractC0342g, L0.d dVar) {
        return ((ViewKt$allViews$1) create(abstractC0342g, dVar)).invokeSuspend(s.f314a);
    }

    @Override // N0.a
    public final Object invokeSuspend(Object obj) throws Throwable {
        AbstractC0342g abstractC0342g;
        Object objC = M0.c.c();
        int i2 = this.label;
        if (i2 == 0) {
            H0.k.b(obj);
            abstractC0342g = (AbstractC0342g) this.L$0;
            View view = this.$this_allViews;
            this.L$0 = abstractC0342g;
            this.label = 1;
            if (abstractC0342g.c(view, this) == objC) {
                return objC;
            }
        } else {
            if (i2 != 1) {
                if (i2 != 2) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                H0.k.b(obj);
                return s.f314a;
            }
            abstractC0342g = (AbstractC0342g) this.L$0;
            H0.k.b(obj);
        }
        View view2 = this.$this_allViews;
        if (view2 instanceof ViewGroup) {
            InterfaceC0340e descendants = ViewGroupKt.getDescendants((ViewGroup) view2);
            this.L$0 = null;
            this.label = 2;
            if (abstractC0342g.d(descendants, this) == objC) {
                return objC;
            }
        }
        return s.f314a;
    }
}
