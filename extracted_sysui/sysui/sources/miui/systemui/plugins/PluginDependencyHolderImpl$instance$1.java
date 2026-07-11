package miui.systemui.plugins;

import H0.k;
import H0.s;
import L0.d;
import M0.c;
import N0.b;
import N0.f;
import N0.l;
import U0.a;
import android.util.Log;
import com.android.systemui.plugins.Plugin;
import com.android.systemui.plugins.PluginDependency;
import j1.InterfaceC0419g;
import java.util.Iterator;
import java.util.Set;
import kotlin.jvm.functions.Function3;

/* JADX INFO: loaded from: classes4.dex */
@f(c = "miui.systemui.plugins.PluginDependencyHolderImpl$instance$1", f = "PluginDependencyHolderImpl.kt", l = {44}, m = "invokeSuspend")
public final class PluginDependencyHolderImpl$instance$1 extends l implements Function3 {
    private /* synthetic */ Object L$0;
    /* synthetic */ Object L$1;
    Object L$2;
    Object L$3;
    int label;
    final /* synthetic */ PluginDependencyHolderImpl<T> this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public PluginDependencyHolderImpl$instance$1(PluginDependencyHolderImpl<T> pluginDependencyHolderImpl, d dVar) {
        super(3, dVar);
        this.this$0 = pluginDependencyHolderImpl;
    }

    @Override // kotlin.jvm.functions.Function3
    public final Object invoke(InterfaceC0419g interfaceC0419g, Set<? extends Plugin> set, d dVar) {
        PluginDependencyHolderImpl$instance$1 pluginDependencyHolderImpl$instance$1 = new PluginDependencyHolderImpl$instance$1(this.this$0, dVar);
        pluginDependencyHolderImpl$instance$1.L$0 = interfaceC0419g;
        pluginDependencyHolderImpl$instance$1.L$1 = set;
        return pluginDependencyHolderImpl$instance$1.invokeSuspend(s.f314a);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r1v0, types: [int] */
    /* JADX WARN: Type inference failed for: r1v15 */
    /* JADX WARN: Type inference failed for: r1v16 */
    /* JADX WARN: Type inference failed for: r1v9 */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:17:0x0064 -> B:19:0x0067). Please report as a decompilation issue!!! */
    @Override // N0.a
    public final Object invokeSuspend(Object obj) throws Throwable {
        InterfaceC0419g interfaceC0419g;
        PluginDependencyHolderImpl pluginDependencyHolderImpl;
        Iterator it;
        Object objC = c.c();
        Object obj2 = this.label;
        try {
        } catch (Throwable th) {
            Log.w("PluginDependencyHolder", obj2.getClass().getSimpleName() + " does not support " + pluginDependencyHolderImpl.clazz.c() + ".", th);
        }
        if (obj2 != 0) {
            if (obj2 != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            Plugin plugin = (Plugin) this.L$3;
            it = (Iterator) this.L$2;
            pluginDependencyHolderImpl = (PluginDependencyHolderImpl) this.L$1;
            interfaceC0419g = (InterfaceC0419g) this.L$0;
            k.b(obj);
            obj2 = plugin;
            return b.a(false);
        }
        k.b(obj);
        InterfaceC0419g interfaceC0419g2 = (InterfaceC0419g) this.L$0;
        Set set = (Set) this.L$1;
        interfaceC0419g = interfaceC0419g2;
        pluginDependencyHolderImpl = this.this$0;
        it = set.iterator();
        if (!it.hasNext()) {
            return b.a(true);
        }
        Plugin plugin2 = (Plugin) it.next();
        Object obj3 = PluginDependency.get(plugin2, a.a(pluginDependencyHolderImpl.clazz));
        this.L$0 = interfaceC0419g;
        this.L$1 = pluginDependencyHolderImpl;
        this.L$2 = it;
        this.L$3 = plugin2;
        this.label = 1;
        obj2 = plugin2;
        if (interfaceC0419g.emit(obj3, this) == objC) {
            return objC;
        }
        return b.a(false);
    }
}
