package miui.systemui.dynamicisland.window.content;

import L0.d;
import j1.InterfaceC0419g;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.i;
import kotlin.jvm.internal.n;

/* JADX INFO: loaded from: classes3.dex */
public final class DynamicIslandBaseContentViewController$sam$kotlinx_coroutines_flow_FlowCollector$0 implements InterfaceC0419g, i {
    private final /* synthetic */ Function2 function;

    public DynamicIslandBaseContentViewController$sam$kotlinx_coroutines_flow_FlowCollector$0(Function2 function) {
        n.g(function, "function");
        this.function = function;
    }

    @Override // j1.InterfaceC0419g
    public final /* synthetic */ Object emit(Object obj, d dVar) {
        return this.function.invoke(obj, dVar);
    }

    public final boolean equals(Object obj) {
        if ((obj instanceof InterfaceC0419g) && (obj instanceof i)) {
            return n.c(getFunctionDelegate(), ((i) obj).getFunctionDelegate());
        }
        return false;
    }

    @Override // kotlin.jvm.internal.i
    public final H0.b getFunctionDelegate() {
        return this.function;
    }

    public final int hashCode() {
        return getFunctionDelegate().hashCode();
    }
}
