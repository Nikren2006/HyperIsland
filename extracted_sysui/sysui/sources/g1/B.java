package g1;

import L0.e;
import L0.g;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import l1.AbstractC0459n;
import l1.C0455j;
import l1.C0458m;

/* JADX INFO: loaded from: classes2.dex */
public abstract class B extends L0.a implements L0.e {
    public static final a Key = new a(null);

    public static final class a extends L0.b {

        /* JADX INFO: renamed from: g1.B$a$a, reason: collision with other inner class name */
        public static final class C0082a extends kotlin.jvm.internal.o implements Function1 {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            public static final C0082a f4362a = new C0082a();

            public C0082a() {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
            public final B invoke(g.b bVar) {
                if (bVar instanceof B) {
                    return (B) bVar;
                }
                return null;
            }
        }

        public /* synthetic */ a(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public a() {
            super(L0.e.f399t, C0082a.f4362a);
        }
    }

    public B() {
        super(L0.e.f399t);
    }

    public abstract void dispatch(L0.g gVar, Runnable runnable);

    public void dispatchYield(L0.g gVar, Runnable runnable) {
        dispatch(gVar, runnable);
    }

    @Override // L0.a, L0.g.b, L0.g
    public <E extends g.b> E get(g.c cVar) {
        return (E) e.a.a(this, cVar);
    }

    @Override // L0.e
    public final <T> L0.d interceptContinuation(L0.d dVar) {
        return new C0455j(this, dVar);
    }

    public boolean isDispatchNeeded(L0.g gVar) {
        return true;
    }

    public B limitedParallelism(int i2) {
        AbstractC0459n.a(i2);
        return new C0458m(this, i2);
    }

    @Override // L0.a, L0.g
    public L0.g minusKey(g.c cVar) {
        return e.a.b(this, cVar);
    }

    public final B plus(B b2) {
        return b2;
    }

    @Override // L0.e
    public final void releaseInterceptedContinuation(L0.d dVar) {
        kotlin.jvm.internal.n.e(dVar, "null cannot be cast to non-null type kotlinx.coroutines.internal.DispatchedContinuation<*>");
        ((C0455j) dVar).r();
    }

    public String toString() {
        return I.a(this) + '@' + I.b(this);
    }
}
