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
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.o;
import kotlin.jvm.internal.w;

/* JADX INFO: loaded from: classes4.dex */
@f(c = "miui.systemui.util.coroutines.flow.ViewUtilsKt$onLongClick$1", f = "ViewUtils.kt", l = {26}, m = "invokeSuspend")
public final class ViewUtilsKt$onLongClick$1 extends l implements Function2 {
    final /* synthetic */ View $this_onLongClick;
    private /* synthetic */ Object L$0;
    int label;

    /* JADX INFO: renamed from: miui.systemui.util.coroutines.flow.ViewUtilsKt$onLongClick$1$2, reason: invalid class name */
    public static final class AnonymousClass2 extends o implements Function0 {
        final /* synthetic */ View $this_onLongClick;
        final /* synthetic */ boolean $wasLongClickable;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass2(View view, boolean z2) {
            super(0);
            this.$this_onLongClick = view;
            this.$wasLongClickable = z2;
        }

        @Override // kotlin.jvm.functions.Function0
        public /* bridge */ /* synthetic */ Object invoke() {
            m156invoke();
            return s.f314a;
        }

        /* JADX INFO: renamed from: invoke, reason: collision with other method in class */
        public final void m156invoke() {
            this.$this_onLongClick.setOnLongClickListener(null);
            this.$this_onLongClick.setLongClickable(this.$wasLongClickable);
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ViewUtilsKt$onLongClick$1(View view, d dVar) {
        super(2, dVar);
        this.$this_onLongClick = view;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean invokeSuspend$lambda$0(w wVar, q qVar, Function1 function1, View view) {
        wVar.f5057a = false;
        qVar.j(function1);
        return wVar.f5057a;
    }

    @Override // N0.a
    public final d create(Object obj, d dVar) {
        ViewUtilsKt$onLongClick$1 viewUtilsKt$onLongClick$1 = new ViewUtilsKt$onLongClick$1(this.$this_onLongClick, dVar);
        viewUtilsKt$onLongClick$1.L$0 = obj;
        return viewUtilsKt$onLongClick$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(q qVar, d dVar) {
        return ((ViewUtilsKt$onLongClick$1) create(qVar, dVar)).invokeSuspend(s.f314a);
    }

    @Override // N0.a
    public final Object invokeSuspend(Object obj) throws Throwable {
        Object objC = c.c();
        int i2 = this.label;
        if (i2 == 0) {
            k.b(obj);
            final q qVar = (q) this.L$0;
            final w wVar = new w();
            final ViewUtilsKt$onLongClick$1$callback$1 viewUtilsKt$onLongClick$1$callback$1 = new ViewUtilsKt$onLongClick$1$callback$1(wVar);
            boolean zIsLongClickable = this.$this_onLongClick.isLongClickable();
            this.$this_onLongClick.setOnLongClickListener(new View.OnLongClickListener() { // from class: miui.systemui.util.coroutines.flow.b
                @Override // android.view.View.OnLongClickListener
                public final boolean onLongClick(View view) {
                    return ViewUtilsKt$onLongClick$1.invokeSuspend$lambda$0(wVar, qVar, viewUtilsKt$onLongClick$1$callback$1, view);
                }
            });
            AnonymousClass2 anonymousClass2 = new AnonymousClass2(this.$this_onLongClick, zIsLongClickable);
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
