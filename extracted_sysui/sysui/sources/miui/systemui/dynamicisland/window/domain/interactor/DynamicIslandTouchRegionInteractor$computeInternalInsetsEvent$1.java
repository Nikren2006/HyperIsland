package miui.systemui.dynamicisland.window.domain.interactor;

import H0.k;
import H0.s;
import L0.d;
import M0.c;
import N0.f;
import N0.l;
import android.view.ViewTreeObserver;
import i1.h;
import i1.q;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.o;

/* JADX INFO: loaded from: classes3.dex */
@f(c = "miui.systemui.dynamicisland.window.domain.interactor.DynamicIslandTouchRegionInteractor$computeInternalInsetsEvent$1", f = "DynamicIslandTouchRegionInteractor.kt", l = {84}, m = "invokeSuspend")
public final class DynamicIslandTouchRegionInteractor$computeInternalInsetsEvent$1 extends l implements Function2 {
    private /* synthetic */ Object L$0;
    int label;
    final /* synthetic */ DynamicIslandTouchRegionInteractor this$0;

    /* JADX INFO: renamed from: miui.systemui.dynamicisland.window.domain.interactor.DynamicIslandTouchRegionInteractor$computeInternalInsetsEvent$1$1, reason: invalid class name */
    public static final class AnonymousClass1 extends o implements Function0 {
        final /* synthetic */ DynamicIslandTouchRegionInteractor$computeInternalInsetsEvent$1$onComputeInternalInsetsListener$1 $onComputeInternalInsetsListener;
        final /* synthetic */ ViewTreeObserver $viewTreeObserver;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass1(ViewTreeObserver viewTreeObserver, DynamicIslandTouchRegionInteractor$computeInternalInsetsEvent$1$onComputeInternalInsetsListener$1 dynamicIslandTouchRegionInteractor$computeInternalInsetsEvent$1$onComputeInternalInsetsListener$1) {
            super(0);
            this.$viewTreeObserver = viewTreeObserver;
            this.$onComputeInternalInsetsListener = dynamicIslandTouchRegionInteractor$computeInternalInsetsEvent$1$onComputeInternalInsetsListener$1;
        }

        @Override // kotlin.jvm.functions.Function0
        public /* bridge */ /* synthetic */ Object invoke() {
            m134invoke();
            return s.f314a;
        }

        /* JADX INFO: renamed from: invoke, reason: collision with other method in class */
        public final void m134invoke() {
            if (this.$viewTreeObserver.isAlive()) {
                this.$viewTreeObserver.removeOnComputeInternalInsetsListener(this.$onComputeInternalInsetsListener);
            }
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public DynamicIslandTouchRegionInteractor$computeInternalInsetsEvent$1(DynamicIslandTouchRegionInteractor dynamicIslandTouchRegionInteractor, d dVar) {
        super(2, dVar);
        this.this$0 = dynamicIslandTouchRegionInteractor;
    }

    @Override // N0.a
    public final d create(Object obj, d dVar) {
        DynamicIslandTouchRegionInteractor$computeInternalInsetsEvent$1 dynamicIslandTouchRegionInteractor$computeInternalInsetsEvent$1 = new DynamicIslandTouchRegionInteractor$computeInternalInsetsEvent$1(this.this$0, dVar);
        dynamicIslandTouchRegionInteractor$computeInternalInsetsEvent$1.L$0 = obj;
        return dynamicIslandTouchRegionInteractor$computeInternalInsetsEvent$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(q qVar, d dVar) {
        return ((DynamicIslandTouchRegionInteractor$computeInternalInsetsEvent$1) create(qVar, dVar)).invokeSuspend(s.f314a);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r1v1, types: [android.view.ViewTreeObserver$OnComputeInternalInsetsListener, miui.systemui.dynamicisland.window.domain.interactor.DynamicIslandTouchRegionInteractor$computeInternalInsetsEvent$1$onComputeInternalInsetsListener$1] */
    @Override // N0.a
    public final Object invokeSuspend(Object obj) throws Throwable {
        Object objC = c.c();
        int i2 = this.label;
        if (i2 == 0) {
            k.b(obj);
            final q qVar = (q) this.L$0;
            ?? r12 = new ViewTreeObserver.OnComputeInternalInsetsListener() { // from class: miui.systemui.dynamicisland.window.domain.interactor.DynamicIslandTouchRegionInteractor$computeInternalInsetsEvent$1$onComputeInternalInsetsListener$1
                public void onComputeInternalInsets(ViewTreeObserver.InternalInsetsInfo internalInsetsInfo) {
                    if (internalInsetsInfo != null) {
                        h.b(qVar.j(internalInsetsInfo));
                    }
                }
            };
            ViewTreeObserver viewTreeObserver = this.this$0.windowView.getViewTreeObserver();
            viewTreeObserver.addOnComputeInternalInsetsListener(r12);
            AnonymousClass1 anonymousClass1 = new AnonymousClass1(viewTreeObserver, r12);
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
