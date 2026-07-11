package miui.systemui.shade;

import H0.k;
import H0.s;
import L0.d;
import M0.c;
import N0.f;
import N0.l;
import com.android.systemui.plugins.miui.shade.PanelExpandController;
import i1.q;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.o;

/* JADX INFO: loaded from: classes4.dex */
@f(c = "miui.systemui.shade.PanelExpandControllerExtKt$visibleState$1", f = "PanelExpandControllerExt.kt", l = {25}, m = "invokeSuspend")
public final class PanelExpandControllerExtKt$visibleState$1 extends l implements Function2 {
    final /* synthetic */ PanelExpandController $this_visibleState;
    private /* synthetic */ Object L$0;
    int label;

    /* JADX INFO: renamed from: miui.systemui.shade.PanelExpandControllerExtKt$visibleState$1$1, reason: invalid class name */
    public static final class AnonymousClass1 extends o implements Function0 {
        final /* synthetic */ PanelExpandControllerExtKt$visibleState$1$callback$1 $callback;
        final /* synthetic */ PanelExpandController $this_visibleState;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass1(PanelExpandController panelExpandController, PanelExpandControllerExtKt$visibleState$1$callback$1 panelExpandControllerExtKt$visibleState$1$callback$1) {
            super(0);
            this.$this_visibleState = panelExpandController;
            this.$callback = panelExpandControllerExtKt$visibleState$1$callback$1;
        }

        @Override // kotlin.jvm.functions.Function0
        public /* bridge */ /* synthetic */ Object invoke() {
            m152invoke();
            return s.f314a;
        }

        /* JADX INFO: renamed from: invoke, reason: collision with other method in class */
        public final void m152invoke() {
            this.$this_visibleState.removeCallback(this.$callback);
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public PanelExpandControllerExtKt$visibleState$1(PanelExpandController panelExpandController, d dVar) {
        super(2, dVar);
        this.$this_visibleState = panelExpandController;
    }

    @Override // N0.a
    public final d create(Object obj, d dVar) {
        PanelExpandControllerExtKt$visibleState$1 panelExpandControllerExtKt$visibleState$1 = new PanelExpandControllerExtKt$visibleState$1(this.$this_visibleState, dVar);
        panelExpandControllerExtKt$visibleState$1.L$0 = obj;
        return panelExpandControllerExtKt$visibleState$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(q qVar, d dVar) {
        return ((PanelExpandControllerExtKt$visibleState$1) create(qVar, dVar)).invokeSuspend(s.f314a);
    }

    /* JADX WARN: Type inference failed for: r1v1, types: [com.android.systemui.plugins.miui.shade.PanelExpandController$Callback, miui.systemui.shade.PanelExpandControllerExtKt$visibleState$1$callback$1] */
    @Override // N0.a
    public final Object invokeSuspend(Object obj) throws Throwable {
        Object objC = c.c();
        int i2 = this.label;
        if (i2 == 0) {
            k.b(obj);
            final q qVar = (q) this.L$0;
            ?? r12 = new PanelExpandController.Callback() { // from class: miui.systemui.shade.PanelExpandControllerExtKt$visibleState$1$callback$1
                public void onVisibleChanged(boolean z2) {
                    qVar.j(Boolean.valueOf(z2));
                }
            };
            this.$this_visibleState.addCallback((PanelExpandController.Callback) r12);
            AnonymousClass1 anonymousClass1 = new AnonymousClass1(this.$this_visibleState, r12);
            this.label = 1;
            if (i1.o.a(qVar, anonymousClass1, this) == objC) {
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
