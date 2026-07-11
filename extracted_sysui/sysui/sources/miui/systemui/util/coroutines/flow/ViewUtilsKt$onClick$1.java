package miui.systemui.util.coroutines.flow;

import H0.k;
import H0.s;
import L0.d;
import M0.c;
import N0.f;
import N0.l;
import android.view.View;
import i1.q;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.o;

/* JADX INFO: loaded from: classes4.dex */
@f(c = "miui.systemui.util.coroutines.flow.ViewUtilsKt$onClick$1", f = "ViewUtils.kt", l = {10}, m = "invokeSuspend")
public final class ViewUtilsKt$onClick$1 extends l implements Function2 {
    final /* synthetic */ View $this_onClick;
    private /* synthetic */ Object L$0;
    int label;

    /* JADX INFO: renamed from: miui.systemui.util.coroutines.flow.ViewUtilsKt$onClick$1$2, reason: invalid class name */
    public static final class AnonymousClass2 extends o implements Function0 {
        final /* synthetic */ View $this_onClick;
        final /* synthetic */ boolean $wasClickable;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass2(View view, boolean z2) {
            super(0);
            this.$this_onClick = view;
            this.$wasClickable = z2;
        }

        @Override // kotlin.jvm.functions.Function0
        public /* bridge */ /* synthetic */ Object invoke() {
            m155invoke();
            return s.f314a;
        }

        /* JADX INFO: renamed from: invoke, reason: collision with other method in class */
        public final void m155invoke() {
            this.$this_onClick.setOnClickListener(null);
            this.$this_onClick.setClickable(this.$wasClickable);
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ViewUtilsKt$onClick$1(View view, d dVar) {
        super(2, dVar);
        this.$this_onClick = view;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void invokeSuspend$lambda$0(q qVar, View view) {
        qVar.j(s.f314a);
    }

    @Override // N0.a
    public final d create(Object obj, d dVar) {
        ViewUtilsKt$onClick$1 viewUtilsKt$onClick$1 = new ViewUtilsKt$onClick$1(this.$this_onClick, dVar);
        viewUtilsKt$onClick$1.L$0 = obj;
        return viewUtilsKt$onClick$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(q qVar, d dVar) {
        return ((ViewUtilsKt$onClick$1) create(qVar, dVar)).invokeSuspend(s.f314a);
    }

    @Override // N0.a
    public final Object invokeSuspend(Object obj) throws Throwable {
        Object objC = c.c();
        int i2 = this.label;
        if (i2 == 0) {
            k.b(obj);
            final q qVar = (q) this.L$0;
            boolean zIsClickable = this.$this_onClick.isClickable();
            this.$this_onClick.setOnClickListener(new View.OnClickListener() { // from class: miui.systemui.util.coroutines.flow.a
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    ViewUtilsKt$onClick$1.invokeSuspend$lambda$0(qVar, view);
                }
            });
            AnonymousClass2 anonymousClass2 = new AnonymousClass2(this.$this_onClick, zIsClickable);
            this.label = 1;
            if (i1.o.a(qVar, anonymousClass2, this) == objC) {
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
