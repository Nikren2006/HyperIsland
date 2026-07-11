package miui.systemui.dynamicisland.window;

import kotlin.jvm.functions.Function0;

/* JADX INFO: loaded from: classes3.dex */
public final class DynamicIslandWindowView$windowViewController$2 extends kotlin.jvm.internal.o implements Function0 {
    final /* synthetic */ DynamicIslandWindowView this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public DynamicIslandWindowView$windowViewController$2(DynamicIslandWindowView dynamicIslandWindowView) {
        super(0);
        this.this$0 = dynamicIslandWindowView;
    }

    @Override // kotlin.jvm.functions.Function0
    public final DynamicIslandWindowViewController invoke() {
        return this.this$0.getViewComponent().getDynamicIslandWindowViewController();
    }
}
