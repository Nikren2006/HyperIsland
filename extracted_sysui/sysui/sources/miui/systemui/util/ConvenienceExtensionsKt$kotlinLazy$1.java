package miui.systemui.util;

import kotlin.jvm.functions.Function0;

/* JADX INFO: loaded from: classes4.dex */
public final class ConvenienceExtensionsKt$kotlinLazy$1 extends kotlin.jvm.internal.o implements Function0 {
    final /* synthetic */ E0.a $this_kotlinLazy;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ConvenienceExtensionsKt$kotlinLazy$1(E0.a aVar) {
        super(0);
        this.$this_kotlinLazy = aVar;
    }

    /* JADX WARN: Type inference failed for: r0v2, types: [T, java.lang.Object] */
    @Override // kotlin.jvm.functions.Function0
    public final T invoke() {
        return this.$this_kotlinLazy.get();
    }
}
