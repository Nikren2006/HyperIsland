package miui.systemui.dynamicisland.window.domain.interactor;

import H0.k;
import H0.s;
import L0.d;
import M0.c;
import N0.b;
import N0.f;
import N0.l;
import g1.E;
import j1.InterfaceC0419g;
import j1.u;
import j1.y;
import kotlin.jvm.functions.Function2;
import miui.systemui.dynamicisland.touch.TouchEvent;
import miui.systemui.dynamicisland.window.domain.interactor.DynamicIslandWindowViewTouchInteractor;

/* JADX INFO: loaded from: classes3.dex */
@f(c = "miui.systemui.dynamicisland.window.domain.interactor.DynamicIslandWindowViewTouchInteractor$isTracking$2$1$1", f = "DynamicIslandWindowViewTouchInteractor.kt", l = {62}, m = "invokeSuspend")
public final class DynamicIslandWindowViewTouchInteractor$isTracking$2$1$1 extends l implements Function2 {
    final /* synthetic */ u $isTracking;
    int label;
    final /* synthetic */ DynamicIslandWindowViewTouchInteractor this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public DynamicIslandWindowViewTouchInteractor$isTracking$2$1$1(DynamicIslandWindowViewTouchInteractor dynamicIslandWindowViewTouchInteractor, u uVar, d dVar) {
        super(2, dVar);
        this.this$0 = dynamicIslandWindowViewTouchInteractor;
        this.$isTracking = uVar;
    }

    @Override // N0.a
    public final d create(Object obj, d dVar) {
        return new DynamicIslandWindowViewTouchInteractor$isTracking$2$1$1(this.this$0, this.$isTracking, dVar);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(E e2, d dVar) {
        return ((DynamicIslandWindowViewTouchInteractor$isTracking$2$1$1) create(e2, dVar)).invokeSuspend(s.f314a);
    }

    @Override // N0.a
    public final Object invokeSuspend(Object obj) throws Throwable {
        Object objC = c.c();
        int i2 = this.label;
        if (i2 == 0) {
            k.b(obj);
            y islandInterceptTouchEvent = this.this$0.getIslandInterceptTouchEvent();
            final u uVar = this.$isTracking;
            InterfaceC0419g interfaceC0419g = new InterfaceC0419g() { // from class: miui.systemui.dynamicisland.window.domain.interactor.DynamicIslandWindowViewTouchInteractor$isTracking$2$1$1.1
                @Override // j1.InterfaceC0419g
                public final Object emit(TouchEvent touchEvent, d dVar) {
                    uVar.setValue(b.a(!DynamicIslandWindowViewTouchInteractor.AnonymousClass2.invoke$lambda$1$isUpOrCancel(touchEvent)));
                    return s.f314a;
                }
            };
            this.label = 1;
            if (islandInterceptTouchEvent.collect(interfaceC0419g, this) == objC) {
                return objC;
            }
        } else {
            if (i2 != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            k.b(obj);
        }
        throw new H0.c();
    }
}
