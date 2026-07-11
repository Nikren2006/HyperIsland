package miui.systemui.dynamicisland.window.domain.interactor;

import j1.I;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.o;

/* JADX INFO: loaded from: classes3.dex */
public final class DynamicIslandTouchRegionInteractor$touchRegion$2 extends o implements Function0 {
    final /* synthetic */ DynamicIslandTouchRegionInteractor this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public DynamicIslandTouchRegionInteractor$touchRegion$2(DynamicIslandTouchRegionInteractor dynamicIslandTouchRegionInteractor) {
        super(0);
        this.this$0 = dynamicIslandTouchRegionInteractor;
    }

    @Override // kotlin.jvm.functions.Function0
    public final I invoke() {
        return this.this$0.getEventCoordinator().getTouchRegion();
    }
}
