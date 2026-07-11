package k1;

import L0.g;
import g1.InterfaceC0380l0;
import kotlin.jvm.functions.Function2;
import l1.B;

/* JADX INFO: loaded from: classes2.dex */
public abstract class v {

    public static final class a extends kotlin.jvm.internal.o implements Function2 {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ t f5023a;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public a(t tVar) {
            super(2);
            this.f5023a = tVar;
        }

        public final Integer b(int i2, g.b bVar) {
            g.c key = bVar.getKey();
            g.b bVar2 = this.f5023a.f5016b.get(key);
            if (key != InterfaceC0380l0.f4430z) {
                return Integer.valueOf(bVar != bVar2 ? Integer.MIN_VALUE : i2 + 1);
            }
            InterfaceC0380l0 interfaceC0380l0 = (InterfaceC0380l0) bVar2;
            kotlin.jvm.internal.n.e(bVar, "null cannot be cast to non-null type kotlinx.coroutines.Job");
            InterfaceC0380l0 interfaceC0380l0B = v.b((InterfaceC0380l0) bVar, interfaceC0380l0);
            if (interfaceC0380l0B == interfaceC0380l0) {
                if (interfaceC0380l0 != null) {
                    i2++;
                }
                return Integer.valueOf(i2);
            }
            throw new IllegalStateException(("Flow invariant is violated:\n\t\tEmission from another coroutine is detected.\n\t\tChild of " + interfaceC0380l0B + ", expected child of " + interfaceC0380l0 + ".\n\t\tFlowCollector is not thread-safe and concurrent emissions are prohibited.\n\t\tTo mitigate this restriction please use 'channelFlow' builder instead of 'flow'").toString());
        }

        @Override // kotlin.jvm.functions.Function2
        public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
            return b(((Number) obj).intValue(), (g.b) obj2);
        }
    }

    public static final void a(t tVar, L0.g gVar) {
        if (((Number) gVar.fold(0, new a(tVar))).intValue() == tVar.f5017c) {
            return;
        }
        throw new IllegalStateException(("Flow invariant is violated:\n\t\tFlow was collected in " + tVar.f5016b + ",\n\t\tbut emission happened in " + gVar + ".\n\t\tPlease refer to 'flow' documentation or use 'flowOn' instead").toString());
    }

    public static final InterfaceC0380l0 b(InterfaceC0380l0 interfaceC0380l0, InterfaceC0380l0 interfaceC0380l02) {
        while (interfaceC0380l0 != null) {
            if (interfaceC0380l0 == interfaceC0380l02 || !(interfaceC0380l0 instanceof B)) {
                return interfaceC0380l0;
            }
            interfaceC0380l0 = interfaceC0380l0.getParent();
        }
        return null;
    }
}
