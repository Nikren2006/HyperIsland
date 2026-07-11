package miui.systemui.dynamicisland.touch.domain.interactor;

import H0.k;
import H0.s;
import L0.d;
import M0.c;
import N0.f;
import N0.l;
import g1.E;
import j1.InterfaceC0418f;
import j1.InterfaceC0419g;
import kotlin.jvm.functions.Function2;
import miui.systemui.dynamicisland.touch.TouchEvent;

/* JADX INFO: loaded from: classes3.dex */
@f(c = "miui.systemui.dynamicisland.touch.domain.interactor.DynamicIslandTouchInteractor$handleExternalEvent$1$1", f = "DynamicIslandTouchInteractor.kt", l = {130}, m = "invokeSuspend")
public final class DynamicIslandTouchInteractor$handleExternalEvent$1$1 extends l implements Function2 {
    final /* synthetic */ DynamicIslandExternalTouchInteractor $this_apply;
    int label;
    final /* synthetic */ DynamicIslandTouchInteractor this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public DynamicIslandTouchInteractor$handleExternalEvent$1$1(DynamicIslandExternalTouchInteractor dynamicIslandExternalTouchInteractor, DynamicIslandTouchInteractor dynamicIslandTouchInteractor, d dVar) {
        super(2, dVar);
        this.$this_apply = dynamicIslandExternalTouchInteractor;
        this.this$0 = dynamicIslandTouchInteractor;
    }

    @Override // N0.a
    public final d create(Object obj, d dVar) {
        return new DynamicIslandTouchInteractor$handleExternalEvent$1$1(this.$this_apply, this.this$0, dVar);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(E e2, d dVar) {
        return ((DynamicIslandTouchInteractor$handleExternalEvent$1$1) create(e2, dVar)).invokeSuspend(s.f314a);
    }

    @Override // N0.a
    public final Object invokeSuspend(Object obj) throws Throwable {
        Object objC = c.c();
        int i2 = this.label;
        if (i2 == 0) {
            k.b(obj);
            InterfaceC0418f externalInterceptTouchEvent = this.$this_apply.getExternalInterceptTouchEvent();
            final DynamicIslandTouchInteractor dynamicIslandTouchInteractor = this.this$0;
            InterfaceC0419g interfaceC0419g = new InterfaceC0419g() { // from class: miui.systemui.dynamicisland.touch.domain.interactor.DynamicIslandTouchInteractor$handleExternalEvent$1$1.1
                @Override // j1.InterfaceC0419g
                public final Object emit(TouchEvent touchEvent, d dVar) {
                    touchEvent.setResult(dynamicIslandTouchInteractor.onInterceptTouchEvent(touchEvent.getEvent(), touchEvent.getSource()));
                    dynamicIslandTouchInteractor.logEvent(touchEvent.getEvent(), "intercept touch event " + touchEvent);
                    return s.f314a;
                }
            };
            this.label = 1;
            if (externalInterceptTouchEvent.collect(interfaceC0419g, this) == objC) {
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
