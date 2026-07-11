package j1;

import I0.AbstractC0184l;
import androidx.core.location.LocationRequestCompat;
import java.util.List;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;

/* JADX INFO: loaded from: classes2.dex */
public final class H implements E {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final long f4660b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final long f4661c;

    public static final class a extends N0.l implements Function3 {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public int f4662a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public /* synthetic */ Object f4663b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public /* synthetic */ int f4664c;

        public a(L0.d dVar) {
            super(3, dVar);
        }

        public final Object e(InterfaceC0419g interfaceC0419g, int i2, L0.d dVar) {
            a aVar = H.this.new a(dVar);
            aVar.f4663b = interfaceC0419g;
            aVar.f4664c = i2;
            return aVar.invokeSuspend(H0.s.f314a);
        }

        @Override // kotlin.jvm.functions.Function3
        public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2, Object obj3) {
            return e((InterfaceC0419g) obj, ((Number) obj2).intValue(), (L0.d) obj3);
        }

        /* JADX WARN: Removed duplicated region for block: B:26:0x0070  */
        /* JADX WARN: Removed duplicated region for block: B:31:0x008d A[RETURN] */
        /* JADX WARN: Removed duplicated region for block: B:34:0x009b A[RETURN] */
        @Override // N0.a
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public final java.lang.Object invokeSuspend(java.lang.Object r10) throws java.lang.Throwable {
            /*
                r9 = this;
                java.lang.Object r0 = M0.c.c()
                int r1 = r9.f4662a
                r2 = 5
                r3 = 4
                r4 = 3
                r5 = 2
                r6 = 1
                if (r1 == 0) goto L3c
                if (r1 == r6) goto L38
                if (r1 == r5) goto L30
                if (r1 == r4) goto L28
                if (r1 == r3) goto L20
                if (r1 != r2) goto L18
                goto L38
            L18:
                java.lang.IllegalStateException r9 = new java.lang.IllegalStateException
                java.lang.String r10 = "call to 'resume' before 'invoke' with coroutine"
                r9.<init>(r10)
                throw r9
            L20:
                java.lang.Object r1 = r9.f4663b
                j1.g r1 = (j1.InterfaceC0419g) r1
                H0.k.b(r10)
                goto L8e
            L28:
                java.lang.Object r1 = r9.f4663b
                j1.g r1 = (j1.InterfaceC0419g) r1
                H0.k.b(r10)
                goto L7d
            L30:
                java.lang.Object r1 = r9.f4663b
                j1.g r1 = (j1.InterfaceC0419g) r1
                H0.k.b(r10)
                goto L64
            L38:
                H0.k.b(r10)
                goto L9c
            L3c:
                H0.k.b(r10)
                java.lang.Object r10 = r9.f4663b
                r1 = r10
                j1.g r1 = (j1.InterfaceC0419g) r1
                int r10 = r9.f4664c
                if (r10 <= 0) goto L53
                j1.C r10 = j1.C.START
                r9.f4662a = r6
                java.lang.Object r9 = r1.emit(r10, r9)
                if (r9 != r0) goto L9c
                return r0
            L53:
                j1.H r10 = j1.H.this
                long r6 = j1.H.c(r10)
                r9.f4663b = r1
                r9.f4662a = r5
                java.lang.Object r10 = g1.M.b(r6, r9)
                if (r10 != r0) goto L64
                return r0
            L64:
                j1.H r10 = j1.H.this
                long r5 = j1.H.b(r10)
                r7 = 0
                int r10 = (r5 > r7 ? 1 : (r5 == r7 ? 0 : -1))
                if (r10 <= 0) goto L8e
                j1.C r10 = j1.C.STOP
                r9.f4663b = r1
                r9.f4662a = r4
                java.lang.Object r10 = r1.emit(r10, r9)
                if (r10 != r0) goto L7d
                return r0
            L7d:
                j1.H r10 = j1.H.this
                long r4 = j1.H.b(r10)
                r9.f4663b = r1
                r9.f4662a = r3
                java.lang.Object r10 = g1.M.b(r4, r9)
                if (r10 != r0) goto L8e
                return r0
            L8e:
                j1.C r10 = j1.C.STOP_AND_RESET_REPLAY_CACHE
                r3 = 0
                r9.f4663b = r3
                r9.f4662a = r2
                java.lang.Object r9 = r1.emit(r10, r9)
                if (r9 != r0) goto L9c
                return r0
            L9c:
                H0.s r9 = H0.s.f314a
                return r9
            */
            throw new UnsupportedOperationException("Method not decompiled: j1.H.a.invokeSuspend(java.lang.Object):java.lang.Object");
        }
    }

    public static final class b extends N0.l implements Function2 {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public int f4666a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public /* synthetic */ Object f4667b;

        public b(L0.d dVar) {
            super(2, dVar);
        }

        @Override // N0.a
        public final L0.d create(Object obj, L0.d dVar) {
            b bVar = new b(dVar);
            bVar.f4667b = obj;
            return bVar;
        }

        @Override // kotlin.jvm.functions.Function2
        /* JADX INFO: renamed from: e, reason: merged with bridge method [inline-methods] */
        public final Object invoke(C c2, L0.d dVar) {
            return ((b) create(c2, dVar)).invokeSuspend(H0.s.f314a);
        }

        @Override // N0.a
        public final Object invokeSuspend(Object obj) throws Throwable {
            M0.c.c();
            if (this.f4666a != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            H0.k.b(obj);
            return N0.b.a(((C) this.f4667b) != C.START);
        }
    }

    public H(long j2, long j3) {
        this.f4660b = j2;
        this.f4661c = j3;
        if (j2 < 0) {
            throw new IllegalArgumentException(("stopTimeout(" + j2 + " ms) cannot be negative").toString());
        }
        if (j3 >= 0) {
            return;
        }
        throw new IllegalArgumentException(("replayExpiration(" + j3 + " ms) cannot be negative").toString());
    }

    @Override // j1.E
    public InterfaceC0418f a(I i2) {
        return AbstractC0420h.n(AbstractC0420h.o(AbstractC0420h.C(i2, new a(null)), new b(null)));
    }

    public boolean equals(Object obj) {
        if (obj instanceof H) {
            H h2 = (H) obj;
            if (this.f4660b == h2.f4660b && this.f4661c == h2.f4661c) {
                return true;
            }
        }
        return false;
    }

    public int hashCode() {
        return (Long.hashCode(this.f4660b) * 31) + Long.hashCode(this.f4661c);
    }

    public String toString() {
        List listC = AbstractC0184l.c(2);
        if (this.f4660b > 0) {
            listC.add("stopTimeout=" + this.f4660b + "ms");
        }
        if (this.f4661c < LocationRequestCompat.PASSIVE_INTERVAL) {
            listC.add("replayExpiration=" + this.f4661c + "ms");
        }
        return "SharingStarted.WhileSubscribed(" + I0.u.T(AbstractC0184l.a(listC), null, null, null, 0, null, null, 63, null) + ')';
    }
}
