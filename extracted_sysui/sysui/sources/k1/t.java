package k1;

import L0.g;
import g1.AbstractC0388p0;
import j1.InterfaceC0419g;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;

/* JADX INFO: loaded from: classes2.dex */
public final class t extends N0.d implements InterfaceC0419g, N0.e {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final InterfaceC0419g f5015a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final L0.g f5016b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final int f5017c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public L0.g f5018d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public L0.d f5019e;

    public static final class a extends kotlin.jvm.internal.o implements Function2 {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final a f5020a = new a();

        public a() {
            super(2);
        }

        public final Integer b(int i2, g.b bVar) {
            return Integer.valueOf(i2 + 1);
        }

        @Override // kotlin.jvm.functions.Function2
        public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
            return b(((Number) obj).intValue(), (g.b) obj2);
        }
    }

    public t(InterfaceC0419g interfaceC0419g, L0.g gVar) {
        super(q.f5009a, L0.h.f402a);
        this.f5015a = interfaceC0419g;
        this.f5016b = gVar;
        this.f5017c = ((Number) gVar.fold(0, a.f5020a)).intValue();
    }

    public final void e(L0.g gVar, L0.g gVar2, Object obj) {
        if (gVar2 instanceof l) {
            i((l) gVar2, obj);
        }
        v.a(this, gVar);
    }

    @Override // j1.InterfaceC0419g
    public Object emit(Object obj, L0.d dVar) {
        try {
            Object objF = f(dVar, obj);
            if (objF == M0.c.c()) {
                N0.h.c(dVar);
            }
            return objF == M0.c.c() ? objF : H0.s.f314a;
        } catch (Throwable th) {
            this.f5018d = new l(th, dVar.getContext());
            throw th;
        }
    }

    public final Object f(L0.d dVar, Object obj) {
        L0.g context = dVar.getContext();
        AbstractC0388p0.g(context);
        L0.g gVar = this.f5018d;
        if (gVar != context) {
            e(context, gVar, obj);
            this.f5018d = context;
        }
        this.f5019e = dVar;
        Function3 function3 = u.f5021a;
        InterfaceC0419g interfaceC0419g = this.f5015a;
        kotlin.jvm.internal.n.e(interfaceC0419g, "null cannot be cast to non-null type kotlinx.coroutines.flow.FlowCollector<kotlin.Any?>");
        kotlin.jvm.internal.n.e(this, "null cannot be cast to non-null type kotlin.coroutines.Continuation<kotlin.Unit>");
        Object objInvoke = function3.invoke(interfaceC0419g, obj, this);
        if (!kotlin.jvm.internal.n.c(objInvoke, M0.c.c())) {
            this.f5019e = null;
        }
        return objInvoke;
    }

    @Override // N0.a, N0.e
    public N0.e getCallerFrame() {
        L0.d dVar = this.f5019e;
        if (dVar instanceof N0.e) {
            return (N0.e) dVar;
        }
        return null;
    }

    @Override // N0.d, L0.d
    public L0.g getContext() {
        L0.g gVar = this.f5018d;
        return gVar == null ? L0.h.f402a : gVar;
    }

    @Override // N0.a
    public StackTraceElement getStackTraceElement() {
        return null;
    }

    public final void i(l lVar, Object obj) {
        throw new IllegalStateException(f1.g.e("\n            Flow exception transparency is violated:\n                Previous 'emit' call has thrown exception " + lVar.f5007a + ", but then emission attempt of value '" + obj + "' has been detected.\n                Emissions from 'catch' blocks are prohibited in order to avoid unspecified behaviour, 'Flow.catch' operator can be used instead.\n                For a more detailed explanation, please refer to Flow documentation.\n            ").toString());
    }

    @Override // N0.a
    public Object invokeSuspend(Object obj) {
        Throwable thB = H0.j.b(obj);
        if (thB != null) {
            this.f5018d = new l(thB, getContext());
        }
        L0.d dVar = this.f5019e;
        if (dVar != null) {
            dVar.resumeWith(obj);
        }
        return M0.c.c();
    }

    @Override // N0.d, N0.a
    public void releaseIntercepted() {
        super.releaseIntercepted();
    }
}
