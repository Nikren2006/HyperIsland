package g1;

import L0.g;
import kotlin.jvm.functions.Function2;

/* JADX INFO: loaded from: classes2.dex */
public abstract class A {

    public static final class a extends kotlin.jvm.internal.o implements Function2 {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final a f4358a = new a();

        public a() {
            super(2);
        }

        @Override // kotlin.jvm.functions.Function2
        /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
        public final L0.g invoke(L0.g gVar, g.b bVar) {
            return gVar.plus(bVar);
        }
    }

    public static final class b extends kotlin.jvm.internal.o implements Function2 {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ kotlin.jvm.internal.y f4359a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final /* synthetic */ boolean f4360b;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public b(kotlin.jvm.internal.y yVar, boolean z2) {
            super(2);
            this.f4359a = yVar;
            this.f4360b = z2;
        }

        @Override // kotlin.jvm.functions.Function2
        /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
        public final L0.g invoke(L0.g gVar, g.b bVar) {
            return gVar.plus(bVar);
        }
    }

    public static final class c extends kotlin.jvm.internal.o implements Function2 {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final c f4361a = new c();

        public c() {
            super(2);
        }

        public final Boolean b(boolean z2, g.b bVar) {
            return Boolean.valueOf(z2);
        }

        @Override // kotlin.jvm.functions.Function2
        public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
            return b(((Boolean) obj).booleanValue(), (g.b) obj2);
        }
    }

    public static final L0.g a(L0.g gVar, L0.g gVar2, boolean z2) {
        boolean zC = c(gVar);
        boolean zC2 = c(gVar2);
        if (!zC && !zC2) {
            return gVar.plus(gVar2);
        }
        kotlin.jvm.internal.y yVar = new kotlin.jvm.internal.y();
        yVar.f5059a = gVar2;
        L0.h hVar = L0.h.f402a;
        L0.g gVar3 = (L0.g) gVar.fold(hVar, new b(yVar, z2));
        if (zC2) {
            yVar.f5059a = ((L0.g) yVar.f5059a).fold(hVar, a.f4358a);
        }
        return gVar3.plus((L0.g) yVar.f5059a);
    }

    public static final String b(L0.g gVar) {
        return null;
    }

    public static final boolean c(L0.g gVar) {
        return ((Boolean) gVar.fold(Boolean.FALSE, c.f4361a)).booleanValue();
    }

    public static final L0.g d(L0.g gVar, L0.g gVar2) {
        return !c(gVar2) ? gVar.plus(gVar2) : a(gVar, gVar2, false);
    }

    public static final L0.g e(E e2, L0.g gVar) {
        L0.g gVarA = a(e2.getCoroutineContext(), gVar, true);
        return (gVarA == Q.a() || gVarA.get(L0.e.f399t) != null) ? gVarA : gVarA.plus(Q.a());
    }

    public static final M0 f(N0.e eVar) {
        while (!(eVar instanceof N) && (eVar = eVar.getCallerFrame()) != null) {
            if (eVar instanceof M0) {
                return (M0) eVar;
            }
        }
        return null;
    }

    public static final M0 g(L0.d dVar, L0.g gVar, Object obj) {
        if (!(dVar instanceof N0.e) || gVar.get(N0.f4388a) == null) {
            return null;
        }
        M0 m0F = f((N0.e) dVar);
        if (m0F != null) {
            m0F.G0(gVar, obj);
        }
        return m0F;
    }
}
