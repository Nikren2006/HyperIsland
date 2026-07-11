package miui.systemui.util.coroutines.flow;

import H0.s;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.o;
import kotlin.jvm.internal.w;

/* JADX INFO: loaded from: classes4.dex */
public final class ViewUtilsKt$onLongClick$1$callback$1 extends o implements Function1 {
    final /* synthetic */ w $result;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ViewUtilsKt$onLongClick$1$callback$1(w wVar) {
        super(1);
        this.$result = wVar;
    }

    @Override // kotlin.jvm.functions.Function1
    public /* bridge */ /* synthetic */ Object invoke(Object obj) {
        invoke(((Boolean) obj).booleanValue());
        return s.f314a;
    }

    public final void invoke(boolean z2) {
        this.$result.f5057a = z2;
    }
}
