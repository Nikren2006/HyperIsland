package l1;

import L0.g;
import g1.G0;
import kotlin.jvm.functions.Function2;

/* JADX INFO: loaded from: classes2.dex */
public abstract class J {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final F f5199a = new F("NO_THREAD_ELEMENTS");

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final Function2 f5200b = a.f5203a;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final Function2 f5201c = b.f5204a;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static final Function2 f5202d = c.f5205a;

    public static final class a extends kotlin.jvm.internal.o implements Function2 {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final a f5203a = new a();

        public a() {
            super(2);
        }

        @Override // kotlin.jvm.functions.Function2
        /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
        public final Object invoke(Object obj, g.b bVar) {
            if (!(bVar instanceof G0)) {
                return obj;
            }
            Integer num = obj instanceof Integer ? (Integer) obj : null;
            int iIntValue = num != null ? num.intValue() : 1;
            return iIntValue == 0 ? bVar : Integer.valueOf(iIntValue + 1);
        }
    }

    public static final class b extends kotlin.jvm.internal.o implements Function2 {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final b f5204a = new b();

        public b() {
            super(2);
        }

        @Override // kotlin.jvm.functions.Function2
        /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
        public final G0 invoke(G0 g02, g.b bVar) {
            if (g02 != null) {
                return g02;
            }
            if (bVar instanceof G0) {
                return (G0) bVar;
            }
            return null;
        }
    }

    public static final class c extends kotlin.jvm.internal.o implements Function2 {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final c f5205a = new c();

        public c() {
            super(2);
        }

        @Override // kotlin.jvm.functions.Function2
        /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
        public final N invoke(N n2, g.b bVar) {
            if (bVar instanceof G0) {
                G0 g02 = (G0) bVar;
                n2.a(g02, g02.x(n2.f5208a));
            }
            return n2;
        }
    }

    public static final void a(L0.g gVar, Object obj) {
        if (obj == f5199a) {
            return;
        }
        if (obj instanceof N) {
            ((N) obj).b(gVar);
            return;
        }
        Object objFold = gVar.fold(null, f5201c);
        kotlin.jvm.internal.n.e(objFold, "null cannot be cast to non-null type kotlinx.coroutines.ThreadContextElement<kotlin.Any?>");
        ((G0) objFold).w(gVar, obj);
    }

    public static final Object b(L0.g gVar) {
        Object objFold = gVar.fold(0, f5200b);
        kotlin.jvm.internal.n.d(objFold);
        return objFold;
    }

    public static final Object c(L0.g gVar, Object obj) {
        if (obj == null) {
            obj = b(gVar);
        }
        if (obj == 0) {
            return f5199a;
        }
        if (obj instanceof Integer) {
            return gVar.fold(new N(gVar, ((Number) obj).intValue()), f5202d);
        }
        kotlin.jvm.internal.n.e(obj, "null cannot be cast to non-null type kotlinx.coroutines.ThreadContextElement<kotlin.Any?>");
        return ((G0) obj).x(gVar);
    }
}
