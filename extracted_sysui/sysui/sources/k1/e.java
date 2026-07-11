package k1;

import g1.E;
import g1.F;
import g1.G;
import g1.I;
import j1.AbstractC0420h;
import j1.InterfaceC0418f;
import j1.InterfaceC0419g;
import java.util.ArrayList;
import kotlin.jvm.functions.Function2;

/* JADX INFO: loaded from: classes2.dex */
public abstract class e implements p {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final L0.g f4954a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final int f4955b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final i1.a f4956c;

    public static final class a extends N0.l implements Function2 {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public int f4957a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public /* synthetic */ Object f4958b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final /* synthetic */ InterfaceC0419g f4959c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public final /* synthetic */ e f4960d;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public a(InterfaceC0419g interfaceC0419g, e eVar, L0.d dVar) {
            super(2, dVar);
            this.f4959c = interfaceC0419g;
            this.f4960d = eVar;
        }

        @Override // N0.a
        public final L0.d create(Object obj, L0.d dVar) {
            a aVar = new a(this.f4959c, this.f4960d, dVar);
            aVar.f4958b = obj;
            return aVar;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(E e2, L0.d dVar) {
            return ((a) create(e2, dVar)).invokeSuspend(H0.s.f314a);
        }

        @Override // N0.a
        public final Object invokeSuspend(Object obj) throws Throwable {
            Object objC = M0.c.c();
            int i2 = this.f4957a;
            if (i2 == 0) {
                H0.k.b(obj);
                E e2 = (E) this.f4958b;
                InterfaceC0419g interfaceC0419g = this.f4959c;
                i1.s sVarL = this.f4960d.l(e2);
                this.f4957a = 1;
                if (AbstractC0420h.p(interfaceC0419g, sVarL, this) == objC) {
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

    public static final class b extends N0.l implements Function2 {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public int f4961a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public /* synthetic */ Object f4962b;

        public b(L0.d dVar) {
            super(2, dVar);
        }

        @Override // N0.a
        public final L0.d create(Object obj, L0.d dVar) {
            b bVar = e.this.new b(dVar);
            bVar.f4962b = obj;
            return bVar;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(i1.q qVar, L0.d dVar) {
            return ((b) create(qVar, dVar)).invokeSuspend(H0.s.f314a);
        }

        @Override // N0.a
        public final Object invokeSuspend(Object obj) throws Throwable {
            Object objC = M0.c.c();
            int i2 = this.f4961a;
            if (i2 == 0) {
                H0.k.b(obj);
                i1.q qVar = (i1.q) this.f4962b;
                e eVar = e.this;
                this.f4961a = 1;
                if (eVar.g(qVar, this) == objC) {
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

    public e(L0.g gVar, int i2, i1.a aVar) {
        this.f4954a = gVar;
        this.f4955b = i2;
        this.f4956c = aVar;
    }

    public static /* synthetic */ Object f(e eVar, InterfaceC0419g interfaceC0419g, L0.d dVar) {
        Object objF = F.f(new a(interfaceC0419g, eVar, null), dVar);
        return objF == M0.c.c() ? objF : H0.s.f314a;
    }

    /* JADX WARN: Removed duplicated region for block: B:9:0x0013  */
    @Override // k1.p
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public j1.InterfaceC0418f a(L0.g r2, int r3, i1.a r4) {
        /*
            r1 = this;
            L0.g r0 = r1.f4954a
            L0.g r2 = r2.plus(r0)
            i1.a r0 = i1.a.SUSPEND
            if (r4 == r0) goto Lb
            goto L25
        Lb:
            int r4 = r1.f4955b
            r0 = -3
            if (r4 != r0) goto L11
            goto L23
        L11:
            if (r3 != r0) goto L15
        L13:
            r3 = r4
            goto L23
        L15:
            r0 = -2
            if (r4 != r0) goto L19
            goto L23
        L19:
            if (r3 != r0) goto L1c
            goto L13
        L1c:
            int r3 = r3 + r4
            if (r3 < 0) goto L20
            goto L23
        L20:
            r3 = 2147483647(0x7fffffff, float:NaN)
        L23:
            i1.a r4 = r1.f4956c
        L25:
            L0.g r0 = r1.f4954a
            boolean r0 = kotlin.jvm.internal.n.c(r2, r0)
            if (r0 == 0) goto L36
            int r0 = r1.f4955b
            if (r3 != r0) goto L36
            i1.a r0 = r1.f4956c
            if (r4 != r0) goto L36
            return r1
        L36:
            k1.e r1 = r1.h(r2, r3, r4)
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: k1.e.a(L0.g, int, i1.a):j1.f");
    }

    @Override // j1.InterfaceC0418f
    public Object collect(InterfaceC0419g interfaceC0419g, L0.d dVar) {
        return f(this, interfaceC0419g, dVar);
    }

    public String e() {
        return null;
    }

    public abstract Object g(i1.q qVar, L0.d dVar);

    public abstract e h(L0.g gVar, int i2, i1.a aVar);

    public InterfaceC0418f i() {
        return null;
    }

    public final Function2 j() {
        return new b(null);
    }

    public final int k() {
        int i2 = this.f4955b;
        if (i2 == -3) {
            return -2;
        }
        return i2;
    }

    public i1.s l(E e2) {
        return i1.o.c(e2, this.f4954a, k(), this.f4956c, G.ATOMIC, null, j(), 16, null);
    }

    public String toString() {
        ArrayList arrayList = new ArrayList(4);
        String strE = e();
        if (strE != null) {
            arrayList.add(strE);
        }
        if (this.f4954a != L0.h.f402a) {
            arrayList.add("context=" + this.f4954a);
        }
        if (this.f4955b != -3) {
            arrayList.add("capacity=" + this.f4955b);
        }
        if (this.f4956c != i1.a.SUSPEND) {
            arrayList.add("onBufferOverflow=" + this.f4956c);
        }
        return I.a(this) + '[' + I0.u.T(arrayList, ", ", null, null, 0, null, null, 62, null) + ']';
    }
}
