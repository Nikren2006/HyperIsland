package miui.systemui.util.coroutines.flow;

import j1.AbstractC0420h;
import j1.InterfaceC0418f;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.n;

/* JADX INFO: loaded from: classes4.dex */
public final class FlowConflatedKt {
    public static final <T> InterfaceC0418f conflatedCallbackFlow(Function2 block) {
        n.g(block, "block");
        return AbstractC0420h.m(AbstractC0420h.e(block));
    }

    public static final <T> InterfaceC0418f conflatedChannelFlow(Function2 block) {
        n.g(block, "block");
        return AbstractC0420h.m(AbstractC0420h.f(block));
    }
}
