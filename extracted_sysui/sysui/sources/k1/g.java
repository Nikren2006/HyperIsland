package k1;

import L0.e;
import g1.A;
import j1.InterfaceC0418f;
import j1.InterfaceC0419g;
import kotlin.jvm.functions.Function2;

/* JADX INFO: loaded from: classes2.dex */
public abstract class g extends e {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final InterfaceC0418f f4964d;

    public static final class a extends N0.l implements Function2 {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public int f4965a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public /* synthetic */ Object f4966b;

        public a(L0.d dVar) {
            super(2, dVar);
        }

        @Override // N0.a
        public final L0.d create(Object obj, L0.d dVar) {
            a aVar = g.this.new a(dVar);
            aVar.f4966b = obj;
            return aVar;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(InterfaceC0419g interfaceC0419g, L0.d dVar) {
            return ((a) create(interfaceC0419g, dVar)).invokeSuspend(H0.s.f314a);
        }

        @Override // N0.a
        public final Object invokeSuspend(Object obj) throws Throwable {
            Object objC = M0.c.c();
            int i2 = this.f4965a;
            if (i2 == 0) {
                H0.k.b(obj);
                InterfaceC0419g interfaceC0419g = (InterfaceC0419g) this.f4966b;
                g gVar = g.this;
                this.f4965a = 1;
                if (gVar.p(interfaceC0419g, this) == objC) {
                    return objC;
                }
            } else {
                if (i2 != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                H0.k.b(obj);
            }
            return H0.s.f314a;
        }
    }

    public g(InterfaceC0418f interfaceC0418f, L0.g gVar, int i2, i1.a aVar) {
        super(gVar, i2, aVar);
        this.f4964d = interfaceC0418f;
    }

    public static /* synthetic */ Object m(g gVar, InterfaceC0419g interfaceC0419g, L0.d dVar) {
        if (gVar.f4955b == -3) {
            L0.g context = dVar.getContext();
            L0.g gVarD = A.d(context, gVar.f4954a);
            if (kotlin.jvm.internal.n.c(gVarD, context)) {
                Object objP = gVar.p(interfaceC0419g, dVar);
                return objP == M0.c.c() ? objP : H0.s.f314a;
            }
            e.b bVar = L0.e.f399t;
            if (kotlin.jvm.internal.n.c(gVarD.get(bVar), context.get(bVar))) {
                Object objO = gVar.o(interfaceC0419g, gVarD, dVar);
                return objO == M0.c.c() ? objO : H0.s.f314a;
            }
        }
        Object objCollect = super.collect(interfaceC0419g, dVar);
        return objCollect == M0.c.c() ? objCollect : H0.s.f314a;
    }

    public static /* synthetic */ Object n(g gVar, i1.q qVar, L0.d dVar) {
        Object objP = gVar.p(new w(qVar), dVar);
        return objP == M0.c.c() ? objP : H0.s.f314a;
    }

    @Override // k1.e, j1.InterfaceC0418f
    public Object collect(InterfaceC0419g interfaceC0419g, L0.d dVar) {
        return m(this, interfaceC0419g, dVar);
    }

    @Override // k1.e
    public Object g(i1.q qVar, L0.d dVar) {
        return n(this, qVar, dVar);
    }

    public final Object o(InterfaceC0419g interfaceC0419g, L0.g gVar, L0.d dVar) {
        Object objC = f.c(gVar, f.d(interfaceC0419g, dVar.getContext()), null, new a(null), dVar, 4, null);
        return objC == M0.c.c() ? objC : H0.s.f314a;
    }

    public abstract Object p(InterfaceC0419g interfaceC0419g, L0.d dVar);

    @Override // k1.e
    public String toString() {
        return this.f4964d + " -> " + super.toString();
    }
}
