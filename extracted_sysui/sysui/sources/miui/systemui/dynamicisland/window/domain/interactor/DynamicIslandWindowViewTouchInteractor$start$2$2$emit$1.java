package miui.systemui.dynamicisland.window.domain.interactor;

import N0.d;
import N0.f;
import miui.systemui.dynamicisland.touch.TouchEvent;
import miui.systemui.dynamicisland.window.domain.interactor.DynamicIslandWindowViewTouchInteractor;

/* JADX INFO: loaded from: classes3.dex */
@f(c = "miui.systemui.dynamicisland.window.domain.interactor.DynamicIslandWindowViewTouchInteractor$start$2$2", f = "DynamicIslandWindowViewTouchInteractor.kt", l = {107, 111}, m = "emit")
public final class DynamicIslandWindowViewTouchInteractor$start$2$2$emit$1 extends d {
    Object L$0;
    Object L$1;
    int label;
    /* synthetic */ Object result;
    final /* synthetic */ DynamicIslandWindowViewTouchInteractor.C06602.C01432<T> this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Multi-variable type inference failed */
    public DynamicIslandWindowViewTouchInteractor$start$2$2$emit$1(DynamicIslandWindowViewTouchInteractor.C06602.C01432<? super T> c01432, L0.d dVar) {
        super(dVar);
        this.this$0 = c01432;
    }

    @Override // N0.a
    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return this.this$0.emit((TouchEvent) null, (L0.d) this);
    }
}
