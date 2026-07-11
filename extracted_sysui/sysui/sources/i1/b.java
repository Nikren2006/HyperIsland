package i1;

import H0.j;
import androidx.core.location.LocationRequestCompat;
import g1.AbstractC0383n;
import g1.C0379l;
import g1.InterfaceC0377k;
import g1.O0;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.concurrent.CancellationException;
import java.util.concurrent.atomic.AtomicLongFieldUpdater;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import l1.AbstractC0449d;
import l1.AbstractC0450e;
import l1.AbstractC0457l;
import l1.C;
import l1.D;
import l1.E;
import l1.O;
import l1.x;

/* JADX INFO: loaded from: classes2.dex */
public class b implements i1.d {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static final AtomicLongFieldUpdater f4552d = AtomicLongFieldUpdater.newUpdater(b.class, "sendersAndCloseStatus");

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static final AtomicLongFieldUpdater f4553e = AtomicLongFieldUpdater.newUpdater(b.class, "receivers");

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public static final AtomicLongFieldUpdater f4554f = AtomicLongFieldUpdater.newUpdater(b.class, "bufferEnd");

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public static final AtomicLongFieldUpdater f4555g = AtomicLongFieldUpdater.newUpdater(b.class, "completedExpandBuffersAndPauseFlag");

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public static final AtomicReferenceFieldUpdater f4556h = AtomicReferenceFieldUpdater.newUpdater(b.class, Object.class, "sendSegment");

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public static final AtomicReferenceFieldUpdater f4557i = AtomicReferenceFieldUpdater.newUpdater(b.class, Object.class, "receiveSegment");

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public static final AtomicReferenceFieldUpdater f4558j = AtomicReferenceFieldUpdater.newUpdater(b.class, Object.class, "bufferEndSegment");

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public static final AtomicReferenceFieldUpdater f4559k = AtomicReferenceFieldUpdater.newUpdater(b.class, Object.class, "_closeCause");

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public static final AtomicReferenceFieldUpdater f4560l = AtomicReferenceFieldUpdater.newUpdater(b.class, Object.class, "closeHandler");
    private volatile Object _closeCause;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final int f4561a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final Function1 f4562b;
    private volatile long bufferEnd;
    private volatile Object bufferEndSegment;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final Function3 f4563c;
    private volatile Object closeHandler;
    private volatile long completedExpandBuffersAndPauseFlag;
    private volatile Object receiveSegment;
    private volatile long receivers;
    private volatile Object sendSegment;
    private volatile long sendersAndCloseStatus;

    public final class a implements f, O0 {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public Object f4564a = i1.c.f4595p;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public C0379l f4565b;

        public a() {
        }

        @Override // i1.f
        public Object a(L0.d dVar) {
            i iVar;
            b bVar = b.this;
            i iVar2 = (i) b.f4557i.get(bVar);
            while (!bVar.W()) {
                long andIncrement = b.f4553e.getAndIncrement(bVar);
                int i2 = i1.c.f4581b;
                long j2 = andIncrement / ((long) i2);
                int i3 = (int) (andIncrement % ((long) i2));
                if (iVar2.f5193c != j2) {
                    i iVarI = bVar.I(j2, iVar2);
                    if (iVarI == null) {
                        continue;
                    } else {
                        iVar = iVarI;
                    }
                } else {
                    iVar = iVar2;
                }
                Object objB0 = bVar.B0(iVar, i3, andIncrement, null);
                if (objB0 == i1.c.f4592m) {
                    throw new IllegalStateException("unreachable");
                }
                if (objB0 != i1.c.f4594o) {
                    if (objB0 == i1.c.f4593n) {
                        return f(iVar, i3, andIncrement, dVar);
                    }
                    iVar.b();
                    this.f4564a = objB0;
                    return N0.b.a(true);
                }
                if (andIncrement < bVar.P()) {
                    iVar.b();
                }
                iVar2 = iVar;
            }
            return N0.b.a(g());
        }

        @Override // g1.O0
        public void b(C c2, int i2) {
            C0379l c0379l = this.f4565b;
            if (c0379l != null) {
                c0379l.b(c2, i2);
            }
        }

        public final Object f(i iVar, int i2, long j2, L0.d dVar) {
            Boolean boolA;
            b bVar = b.this;
            C0379l c0379lB = AbstractC0383n.b(M0.b.b(dVar));
            try {
                this.f4565b = c0379lB;
                Object objB0 = bVar.B0(iVar, i2, j2, this);
                if (objB0 == i1.c.f4592m) {
                    bVar.m0(this, iVar, i2);
                } else {
                    Function1 function1A = null;
                    if (objB0 == i1.c.f4594o) {
                        if (j2 < bVar.P()) {
                            iVar.b();
                        }
                        i iVar2 = (i) b.f4557i.get(bVar);
                        while (true) {
                            if (bVar.W()) {
                                h();
                                break;
                            }
                            long andIncrement = b.f4553e.getAndIncrement(bVar);
                            int i3 = i1.c.f4581b;
                            long j3 = andIncrement / ((long) i3);
                            int i4 = (int) (andIncrement % ((long) i3));
                            if (iVar2.f5193c != j3) {
                                i iVarI = bVar.I(j3, iVar2);
                                if (iVarI != null) {
                                    iVar2 = iVarI;
                                }
                            }
                            Object objB02 = bVar.B0(iVar2, i4, andIncrement, this);
                            if (objB02 == i1.c.f4592m) {
                                bVar.m0(this, iVar2, i4);
                                break;
                            }
                            if (objB02 == i1.c.f4594o) {
                                if (andIncrement < bVar.P()) {
                                    iVar2.b();
                                }
                            } else {
                                if (objB02 == i1.c.f4593n) {
                                    throw new IllegalStateException("unexpected");
                                }
                                iVar2.b();
                                this.f4564a = objB02;
                                this.f4565b = null;
                                boolA = N0.b.a(true);
                                Function1 function1 = bVar.f4562b;
                                if (function1 != null) {
                                    function1A = x.a(function1, objB02, c0379lB.getContext());
                                }
                            }
                        }
                    } else {
                        iVar.b();
                        this.f4564a = objB0;
                        this.f4565b = null;
                        boolA = N0.b.a(true);
                        Function1 function12 = bVar.f4562b;
                        if (function12 != null) {
                            function1A = x.a(function12, objB0, c0379lB.getContext());
                        }
                    }
                    c0379lB.h(boolA, function1A);
                }
                Object objX = c0379lB.x();
                if (objX == M0.c.c()) {
                    N0.h.c(dVar);
                }
                return objX;
            } catch (Throwable th) {
                c0379lB.J();
                throw th;
            }
        }

        public final boolean g() throws Throwable {
            this.f4564a = i1.c.z();
            Throwable thL = b.this.L();
            if (thL == null) {
                return false;
            }
            throw E.a(thL);
        }

        public final void h() {
            C0379l c0379l = this.f4565b;
            kotlin.jvm.internal.n.d(c0379l);
            this.f4565b = null;
            this.f4564a = i1.c.z();
            Throwable thL = b.this.L();
            if (thL == null) {
                j.a aVar = H0.j.f299a;
                c0379l.resumeWith(H0.j.a(Boolean.FALSE));
            } else {
                j.a aVar2 = H0.j.f299a;
                c0379l.resumeWith(H0.j.a(H0.k.a(thL)));
            }
        }

        public final boolean i(Object obj) {
            C0379l c0379l = this.f4565b;
            kotlin.jvm.internal.n.d(c0379l);
            this.f4565b = null;
            this.f4564a = obj;
            Boolean bool = Boolean.TRUE;
            Function1 function1 = b.this.f4562b;
            return i1.c.B(c0379l, bool, function1 != null ? x.a(function1, obj, c0379l.getContext()) : null);
        }

        public final void j() {
            C0379l c0379l = this.f4565b;
            kotlin.jvm.internal.n.d(c0379l);
            this.f4565b = null;
            this.f4564a = i1.c.z();
            Throwable thL = b.this.L();
            if (thL == null) {
                j.a aVar = H0.j.f299a;
                c0379l.resumeWith(H0.j.a(Boolean.FALSE));
            } else {
                j.a aVar2 = H0.j.f299a;
                c0379l.resumeWith(H0.j.a(H0.k.a(thL)));
            }
        }

        @Override // i1.f
        public Object next() throws Throwable {
            Object obj = this.f4564a;
            if (obj == i1.c.f4595p) {
                throw new IllegalStateException("`hasNext()` has not been invoked");
            }
            this.f4564a = i1.c.f4595p;
            if (obj != i1.c.z()) {
                return obj;
            }
            throw E.a(b.this.M());
        }
    }

    /* JADX INFO: renamed from: i1.b$b, reason: collision with other inner class name */
    public static final class C0086b extends kotlin.jvm.internal.o implements Function3 {

        /* JADX INFO: renamed from: i1.b$b$a */
        public static final class a extends kotlin.jvm.internal.o implements Function1 {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            public final /* synthetic */ Object f4568a;

            /* JADX INFO: renamed from: b, reason: collision with root package name */
            public final /* synthetic */ b f4569b;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public a(Object obj, b bVar, o1.a aVar) {
                super(1);
                this.f4568a = obj;
                this.f4569b = bVar;
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Object invoke(Object obj) {
                invoke((Throwable) obj);
                return H0.s.f314a;
            }

            public final void invoke(Throwable th) {
                if (this.f4568a == i1.c.z()) {
                    return;
                }
                Function1 function1 = this.f4569b.f4562b;
                throw null;
            }
        }

        public C0086b() {
            super(3);
        }

        public final Function1 b(o1.a aVar, Object obj, Object obj2) {
            return new a(obj2, b.this, aVar);
        }

        @Override // kotlin.jvm.functions.Function3
        public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2, Object obj3) {
            android.support.v4.media.a.a(obj);
            return b(null, obj2, obj3);
        }
    }

    public static final class c extends N0.d {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public /* synthetic */ Object f4570a;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public int f4572c;

        public c(L0.d dVar) {
            super(dVar);
        }

        @Override // N0.a
        public final Object invokeSuspend(Object obj) throws Throwable {
            this.f4570a = obj;
            this.f4572c |= Integer.MIN_VALUE;
            Object objO0 = b.o0(b.this, this);
            return objO0 == M0.c.c() ? objO0 : h.b(objO0);
        }
    }

    public static final class d extends N0.d {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public Object f4573a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public Object f4574b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public int f4575c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public long f4576d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public /* synthetic */ Object f4577e;

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        public int f4579g;

        public d(L0.d dVar) {
            super(dVar);
        }

        @Override // N0.a
        public final Object invokeSuspend(Object obj) throws Throwable {
            this.f4577e = obj;
            this.f4579g |= Integer.MIN_VALUE;
            Object objP0 = b.this.p0(null, 0, 0L, this);
            return objP0 == M0.c.c() ? objP0 : h.b(objP0);
        }
    }

    public b(int i2, Function1 function1) {
        this.f4561a = i2;
        this.f4562b = function1;
        if (i2 < 0) {
            throw new IllegalArgumentException(("Invalid channel capacity: " + i2 + ", should be >=0").toString());
        }
        this.bufferEnd = i1.c.A(i2);
        this.completedExpandBuffersAndPauseFlag = K();
        i iVar = new i(0L, null, this, 3);
        this.sendSegment = iVar;
        this.receiveSegment = iVar;
        if (a0()) {
            iVar = i1.c.f4580a;
            kotlin.jvm.internal.n.e(iVar, "null cannot be cast to non-null type kotlinx.coroutines.channels.ChannelSegment<E of kotlinx.coroutines.channels.BufferedChannel>");
        }
        this.bufferEndSegment = iVar;
        this.f4563c = function1 != null ? new C0086b() : null;
        this._closeCause = i1.c.f4598s;
    }

    public static /* synthetic */ void S(b bVar, long j2, int i2, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: incCompletedExpandBufferAttempts");
        }
        if ((i2 & 1) != 0) {
            j2 = 1;
        }
        bVar.R(j2);
    }

    /* JADX WARN: Removed duplicated region for block: B:8:0x0014  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static /* synthetic */ java.lang.Object o0(i1.b r13, L0.d r14) throws java.lang.Throwable {
        /*
            boolean r0 = r14 instanceof i1.b.c
            if (r0 == 0) goto L14
            r0 = r14
            i1.b$c r0 = (i1.b.c) r0
            int r1 = r0.f4572c
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L14
            int r1 = r1 - r2
            r0.f4572c = r1
        L12:
            r6 = r0
            goto L1a
        L14:
            i1.b$c r0 = new i1.b$c
            r0.<init>(r14)
            goto L12
        L1a:
            java.lang.Object r14 = r6.f4570a
            java.lang.Object r0 = M0.c.c()
            int r1 = r6.f4572c
            r2 = 1
            if (r1 == 0) goto L3a
            if (r1 != r2) goto L32
            H0.k.b(r14)
            i1.h r14 = (i1.h) r14
            java.lang.Object r13 = r14.k()
            goto Lb2
        L32:
            java.lang.IllegalStateException r13 = new java.lang.IllegalStateException
            java.lang.String r14 = "call to 'resume' before 'invoke' with coroutine"
            r13.<init>(r14)
            throw r13
        L3a:
            H0.k.b(r14)
            java.util.concurrent.atomic.AtomicReferenceFieldUpdater r14 = f()
            java.lang.Object r14 = r14.get(r13)
            i1.i r14 = (i1.i) r14
        L47:
            boolean r1 = r13.W()
            if (r1 == 0) goto L58
            i1.h$b r14 = i1.h.f4604b
            java.lang.Throwable r13 = r13.L()
            java.lang.Object r13 = r14.a(r13)
            goto Lb2
        L58:
            java.util.concurrent.atomic.AtomicLongFieldUpdater r1 = g()
            long r4 = r1.getAndIncrement(r13)
            int r1 = i1.c.f4581b
            long r7 = (long) r1
            long r7 = r4 / r7
            long r9 = (long) r1
            long r9 = r4 % r9
            int r3 = (int) r9
            long r9 = r14.f5193c
            int r1 = (r9 > r7 ? 1 : (r9 == r7 ? 0 : -1))
            if (r1 == 0) goto L77
            i1.i r1 = c(r13, r7, r14)
            if (r1 != 0) goto L76
            goto L47
        L76:
            r14 = r1
        L77:
            r12 = 0
            r7 = r13
            r8 = r14
            r9 = r3
            r10 = r4
            java.lang.Object r1 = v(r7, r8, r9, r10, r12)
            l1.F r7 = i1.c.r()
            if (r1 == r7) goto Lb3
            l1.F r7 = i1.c.h()
            if (r1 != r7) goto L98
            long r7 = r13.P()
            int r1 = (r4 > r7 ? 1 : (r4 == r7 ? 0 : -1))
            if (r1 >= 0) goto L47
            r14.b()
            goto L47
        L98:
            l1.F r7 = i1.c.s()
            if (r1 != r7) goto La9
            r6.f4572c = r2
            r1 = r13
            r2 = r14
            java.lang.Object r13 = r1.p0(r2, r3, r4, r6)
            if (r13 != r0) goto Lb2
            return r0
        La9:
            r14.b()
            i1.h$b r13 = i1.h.f4604b
            java.lang.Object r13 = r13.c(r1)
        Lb2:
            return r13
        Lb3:
            java.lang.IllegalStateException r13 = new java.lang.IllegalStateException
            java.lang.String r14 = "unexpected"
            r13.<init>(r14)
            throw r13
        */
        throw new UnsupportedOperationException("Method not decompiled: i1.b.o0(i1.b, L0.d):java.lang.Object");
    }

    public static /* synthetic */ Object u0(b bVar, Object obj, L0.d dVar) throws IllegalAccessException, InvocationTargetException {
        i iVar = (i) f4556h.get(bVar);
        while (true) {
            long andIncrement = f4552d.getAndIncrement(bVar);
            long j2 = 1152921504606846975L & andIncrement;
            boolean zY = bVar.Y(andIncrement);
            int i2 = i1.c.f4581b;
            long j3 = j2 / ((long) i2);
            int i3 = (int) (j2 % ((long) i2));
            if (iVar.f5193c != j3) {
                i iVarJ = bVar.J(j3, iVar);
                if (iVarJ != null) {
                    iVar = iVarJ;
                } else if (zY) {
                    Object objI0 = bVar.i0(obj, dVar);
                    if (objI0 == M0.c.c()) {
                        return objI0;
                    }
                }
            }
            int iD0 = bVar.D0(iVar, i3, obj, j2, null, zY);
            if (iD0 == 0) {
                iVar.b();
                break;
            }
            if (iD0 == 1) {
                break;
            }
            if (iD0 != 2) {
                if (iD0 == 3) {
                    Object objV0 = bVar.v0(iVar, i3, obj, j2, dVar);
                    if (objV0 == M0.c.c()) {
                        return objV0;
                    }
                } else if (iD0 == 4) {
                    if (j2 < bVar.N()) {
                        iVar.b();
                    }
                    Object objI02 = bVar.i0(obj, dVar);
                    if (objI02 == M0.c.c()) {
                        return objI02;
                    }
                } else if (iD0 == 5) {
                    iVar.b();
                }
            } else if (zY) {
                iVar.p();
                Object objI03 = bVar.i0(obj, dVar);
                if (objI03 == M0.c.c()) {
                    return objI03;
                }
            }
        }
        return H0.s.f314a;
    }

    public final i A() {
        Object obj = f4558j.get(this);
        i iVar = (i) f4556h.get(this);
        if (iVar.f5193c > ((i) obj).f5193c) {
            obj = iVar;
        }
        i iVar2 = (i) f4557i.get(this);
        if (iVar2.f5193c > ((i) obj).f5193c) {
            obj = iVar2;
        }
        return (i) AbstractC0449d.b((AbstractC0450e) obj);
    }

    public final boolean A0(i iVar, int i2, long j2) {
        while (true) {
            Object objW = iVar.w(i2);
            if (objW instanceof O0) {
                if (j2 < f4553e.get(this)) {
                    if (iVar.r(i2, objW, new u((O0) objW))) {
                        return true;
                    }
                } else if (iVar.r(i2, objW, i1.c.f4586g)) {
                    if (y0(objW, iVar, i2)) {
                        iVar.A(i2, i1.c.f4583d);
                        return true;
                    }
                    iVar.A(i2, i1.c.f4589j);
                    iVar.x(i2, false);
                    return false;
                }
            } else {
                if (objW == i1.c.f4589j) {
                    return false;
                }
                if (objW == null) {
                    if (iVar.r(i2, objW, i1.c.f4584e)) {
                        return true;
                    }
                } else {
                    if (objW == i1.c.f4583d || objW == i1.c.f4587h || objW == i1.c.f4588i || objW == i1.c.f4590k || objW == i1.c.z()) {
                        return true;
                    }
                    if (objW != i1.c.f4585f) {
                        throw new IllegalStateException(("Unexpected cell state: " + objW).toString());
                    }
                }
            }
        }
    }

    public boolean B(Throwable th, boolean z2) {
        if (z2) {
            c0();
        }
        boolean zCompareAndSet = f4559k.compareAndSet(this, i1.c.f4598s, th);
        if (z2) {
            d0();
        } else {
            e0();
        }
        E();
        g0();
        if (zCompareAndSet) {
            T();
        }
        return zCompareAndSet;
    }

    public final Object B0(i iVar, int i2, long j2, Object obj) {
        Object objW = iVar.w(i2);
        if (objW == null) {
            if (j2 >= (f4552d.get(this) & 1152921504606846975L)) {
                if (obj == null) {
                    return i1.c.f4593n;
                }
                if (iVar.r(i2, objW, obj)) {
                    G();
                    return i1.c.f4592m;
                }
            }
        } else if (objW == i1.c.f4583d && iVar.r(i2, objW, i1.c.f4588i)) {
            G();
            return iVar.y(i2);
        }
        return C0(iVar, i2, j2, obj);
    }

    public final void C(long j2) throws IllegalAccessException, InvocationTargetException {
        q0(D(j2));
    }

    public final Object C0(i iVar, int i2, long j2, Object obj) {
        while (true) {
            Object objW = iVar.w(i2);
            if (objW == null || objW == i1.c.f4584e) {
                if (j2 < (f4552d.get(this) & 1152921504606846975L)) {
                    if (iVar.r(i2, objW, i1.c.f4587h)) {
                        G();
                        return i1.c.f4594o;
                    }
                } else {
                    if (obj == null) {
                        return i1.c.f4593n;
                    }
                    if (iVar.r(i2, objW, obj)) {
                        G();
                        return i1.c.f4592m;
                    }
                }
            } else {
                if (objW != i1.c.f4583d) {
                    if (objW != i1.c.f4589j && objW != i1.c.f4587h) {
                        if (objW == i1.c.z()) {
                            G();
                            return i1.c.f4594o;
                        }
                        if (objW != i1.c.f4586g && iVar.r(i2, objW, i1.c.f4585f)) {
                            boolean z2 = objW instanceof u;
                            if (z2) {
                                objW = ((u) objW).f4618a;
                            }
                            if (y0(objW, iVar, i2)) {
                                iVar.A(i2, i1.c.f4588i);
                                G();
                                return iVar.y(i2);
                            }
                            iVar.A(i2, i1.c.f4589j);
                            iVar.x(i2, false);
                            if (z2) {
                                G();
                            }
                            return i1.c.f4594o;
                        }
                    }
                    return i1.c.f4594o;
                }
                if (iVar.r(i2, objW, i1.c.f4588i)) {
                    G();
                    return iVar.y(i2);
                }
            }
        }
    }

    public final i D(long j2) {
        i iVarA = A();
        if (Z()) {
            long jB0 = b0(iVarA);
            if (jB0 != -1) {
                F(jB0);
            }
        }
        z(iVarA, j2);
        return iVarA;
    }

    public final int D0(i iVar, int i2, Object obj, long j2, Object obj2, boolean z2) {
        iVar.B(i2, obj);
        if (z2) {
            return E0(iVar, i2, obj, j2, obj2, z2);
        }
        Object objW = iVar.w(i2);
        if (objW == null) {
            if (x(j2)) {
                if (iVar.r(i2, null, i1.c.f4583d)) {
                    return 1;
                }
            } else {
                if (obj2 == null) {
                    return 3;
                }
                if (iVar.r(i2, null, obj2)) {
                    return 2;
                }
            }
        } else if (objW instanceof O0) {
            iVar.s(i2);
            if (x0(objW, obj)) {
                iVar.A(i2, i1.c.f4588i);
                k0();
                return 0;
            }
            if (iVar.t(i2, i1.c.f4590k) != i1.c.f4590k) {
                iVar.x(i2, true);
            }
            return 5;
        }
        return E0(iVar, i2, obj, j2, obj2, z2);
    }

    public final void E() {
        s();
    }

    public final int E0(i iVar, int i2, Object obj, long j2, Object obj2, boolean z2) {
        while (true) {
            Object objW = iVar.w(i2);
            if (objW == null) {
                if (!x(j2) || z2) {
                    if (z2) {
                        if (iVar.r(i2, null, i1.c.f4589j)) {
                            iVar.x(i2, false);
                            return 4;
                        }
                    } else {
                        if (obj2 == null) {
                            return 3;
                        }
                        if (iVar.r(i2, null, obj2)) {
                            return 2;
                        }
                    }
                } else if (iVar.r(i2, null, i1.c.f4583d)) {
                    return 1;
                }
            } else {
                if (objW != i1.c.f4584e) {
                    if (objW == i1.c.f4590k) {
                        iVar.s(i2);
                        return 5;
                    }
                    if (objW == i1.c.f4587h) {
                        iVar.s(i2);
                        return 5;
                    }
                    if (objW == i1.c.z()) {
                        iVar.s(i2);
                        E();
                        return 4;
                    }
                    iVar.s(i2);
                    if (objW instanceof u) {
                        objW = ((u) objW).f4618a;
                    }
                    if (x0(objW, obj)) {
                        iVar.A(i2, i1.c.f4588i);
                        k0();
                        return 0;
                    }
                    if (iVar.t(i2, i1.c.f4590k) != i1.c.f4590k) {
                        iVar.x(i2, true);
                    }
                    return 5;
                }
                if (iVar.r(i2, objW, i1.c.f4583d)) {
                    return 1;
                }
            }
        }
    }

    public final void F(long j2) {
        O oD;
        i iVar = (i) f4557i.get(this);
        while (true) {
            AtomicLongFieldUpdater atomicLongFieldUpdater = f4553e;
            long j3 = atomicLongFieldUpdater.get(this);
            if (j2 < Math.max(((long) this.f4561a) + j3, K())) {
                return;
            }
            if (atomicLongFieldUpdater.compareAndSet(this, j3, j3 + 1)) {
                int i2 = i1.c.f4581b;
                long j4 = j3 / ((long) i2);
                int i3 = (int) (j3 % ((long) i2));
                if (iVar.f5193c != j4) {
                    i iVarI = I(j4, iVar);
                    if (iVarI == null) {
                        continue;
                    } else {
                        iVar = iVarI;
                    }
                }
                Object objB0 = B0(iVar, i3, j3, null);
                if (objB0 != i1.c.f4594o) {
                    iVar.b();
                    Function1 function1 = this.f4562b;
                    if (function1 != null && (oD = x.d(function1, objB0, null, 2, null)) != null) {
                        throw oD;
                    }
                } else if (j3 < P()) {
                    iVar.b();
                }
            }
        }
    }

    public final void F0(long j2) {
        long j3;
        AtomicLongFieldUpdater atomicLongFieldUpdater = f4553e;
        do {
            j3 = atomicLongFieldUpdater.get(this);
            if (j3 >= j2) {
                return;
            }
        } while (!f4553e.compareAndSet(this, j3, j2));
    }

    public final void G() {
        if (a0()) {
            return;
        }
        i iVar = (i) f4558j.get(this);
        while (true) {
            long andIncrement = f4554f.getAndIncrement(this);
            int i2 = i1.c.f4581b;
            long j2 = andIncrement / ((long) i2);
            if (P() <= andIncrement) {
                if (iVar.f5193c < j2 && iVar.e() != null) {
                    f0(j2, iVar);
                }
                S(this, 0L, 1, null);
                return;
            }
            if (iVar.f5193c != j2) {
                i iVarH = H(j2, iVar, andIncrement);
                if (iVarH == null) {
                    continue;
                } else {
                    iVar = iVarH;
                }
            }
            if (z0(iVar, (int) (andIncrement % ((long) i2)), andIncrement)) {
                S(this, 0L, 1, null);
                return;
            }
            S(this, 0L, 1, null);
        }
    }

    public final void G0(long j2) {
        long j3;
        long j4;
        AtomicLongFieldUpdater atomicLongFieldUpdater = f4552d;
        do {
            j3 = atomicLongFieldUpdater.get(this);
            j4 = 1152921504606846975L & j3;
            if (j4 >= j2) {
                return;
            }
        } while (!f4552d.compareAndSet(this, j3, i1.c.w(j4, (int) (j3 >> 60))));
    }

    public final i H(long j2, i iVar, long j3) {
        Object objC;
        AtomicReferenceFieldUpdater atomicReferenceFieldUpdater = f4558j;
        Function2 function2 = (Function2) i1.c.y();
        loop0: while (true) {
            objC = AbstractC0449d.c(iVar, j2, function2);
            if (!D.c(objC)) {
                C cB = D.b(objC);
                while (true) {
                    C c2 = (C) atomicReferenceFieldUpdater.get(this);
                    if (c2.f5193c >= cB.f5193c) {
                        break loop0;
                    }
                    if (!cB.q()) {
                        break;
                    }
                    if (atomicReferenceFieldUpdater.compareAndSet(this, c2, cB)) {
                        if (c2.m()) {
                            c2.k();
                        }
                    } else if (cB.m()) {
                        cB.k();
                    }
                }
            } else {
                break;
            }
        }
        if (D.c(objC)) {
            E();
            f0(j2, iVar);
            S(this, 0L, 1, null);
            return null;
        }
        i iVar2 = (i) D.b(objC);
        long j4 = iVar2.f5193c;
        if (j4 <= j2) {
            return iVar2;
        }
        int i2 = i1.c.f4581b;
        if (f4554f.compareAndSet(this, j3 + 1, ((long) i2) * j4)) {
            R((iVar2.f5193c * ((long) i2)) - j3);
            return null;
        }
        S(this, 0L, 1, null);
        return null;
    }

    public final void H0(long j2) {
        long j3;
        AtomicLongFieldUpdater atomicLongFieldUpdater;
        long j4;
        if (a0()) {
            return;
        }
        while (K() <= j2) {
        }
        int i2 = i1.c.f4582c;
        for (int i3 = 0; i3 < i2; i3++) {
            long jK = K();
            if (jK == (4611686018427387903L & f4555g.get(this)) && jK == K()) {
                return;
            }
        }
        AtomicLongFieldUpdater atomicLongFieldUpdater2 = f4555g;
        do {
            j3 = atomicLongFieldUpdater2.get(this);
        } while (!atomicLongFieldUpdater2.compareAndSet(this, j3, i1.c.v(j3 & 4611686018427387903L, true)));
        while (true) {
            long jK2 = K();
            atomicLongFieldUpdater = f4555g;
            long j5 = atomicLongFieldUpdater.get(this);
            long j6 = j5 & 4611686018427387903L;
            boolean z2 = (4611686018427387904L & j5) != 0;
            if (jK2 == j6 && jK2 == K()) {
                break;
            } else if (!z2) {
                atomicLongFieldUpdater.compareAndSet(this, j5, i1.c.v(j6, true));
            }
        }
        do {
            j4 = atomicLongFieldUpdater.get(this);
        } while (!atomicLongFieldUpdater.compareAndSet(this, j4, i1.c.v(j4 & 4611686018427387903L, false)));
    }

    public final i I(long j2, i iVar) {
        Object objC;
        AtomicReferenceFieldUpdater atomicReferenceFieldUpdater = f4557i;
        Function2 function2 = (Function2) i1.c.y();
        loop0: while (true) {
            objC = AbstractC0449d.c(iVar, j2, function2);
            if (!D.c(objC)) {
                C cB = D.b(objC);
                while (true) {
                    C c2 = (C) atomicReferenceFieldUpdater.get(this);
                    if (c2.f5193c >= cB.f5193c) {
                        break loop0;
                    }
                    if (!cB.q()) {
                        break;
                    }
                    if (atomicReferenceFieldUpdater.compareAndSet(this, c2, cB)) {
                        if (c2.m()) {
                            c2.k();
                        }
                    } else if (cB.m()) {
                        cB.k();
                    }
                }
            } else {
                break;
            }
        }
        if (D.c(objC)) {
            E();
            if (iVar.f5193c * ((long) i1.c.f4581b) >= P()) {
                return null;
            }
            iVar.b();
            return null;
        }
        i iVar2 = (i) D.b(objC);
        if (!a0() && j2 <= K() / ((long) i1.c.f4581b)) {
            AtomicReferenceFieldUpdater atomicReferenceFieldUpdater2 = f4558j;
            while (true) {
                C c3 = (C) atomicReferenceFieldUpdater2.get(this);
                if (c3.f5193c >= iVar2.f5193c || !iVar2.q()) {
                    break;
                }
                if (atomicReferenceFieldUpdater2.compareAndSet(this, c3, iVar2)) {
                    if (c3.m()) {
                        c3.k();
                    }
                } else if (iVar2.m()) {
                    iVar2.k();
                }
            }
        }
        long j3 = iVar2.f5193c;
        if (j3 <= j2) {
            return iVar2;
        }
        int i2 = i1.c.f4581b;
        F0(j3 * ((long) i2));
        if (iVar2.f5193c * ((long) i2) >= P()) {
            return null;
        }
        iVar2.b();
        return null;
    }

    public final i J(long j2, i iVar) {
        Object objC;
        AtomicReferenceFieldUpdater atomicReferenceFieldUpdater = f4556h;
        Function2 function2 = (Function2) i1.c.y();
        loop0: while (true) {
            objC = AbstractC0449d.c(iVar, j2, function2);
            if (!D.c(objC)) {
                C cB = D.b(objC);
                while (true) {
                    C c2 = (C) atomicReferenceFieldUpdater.get(this);
                    if (c2.f5193c >= cB.f5193c) {
                        break loop0;
                    }
                    if (!cB.q()) {
                        break;
                    }
                    if (atomicReferenceFieldUpdater.compareAndSet(this, c2, cB)) {
                        if (c2.m()) {
                            c2.k();
                        }
                    } else if (cB.m()) {
                        cB.k();
                    }
                }
            } else {
                break;
            }
        }
        if (D.c(objC)) {
            E();
            if (iVar.f5193c * ((long) i1.c.f4581b) >= N()) {
                return null;
            }
            iVar.b();
            return null;
        }
        i iVar2 = (i) D.b(objC);
        long j3 = iVar2.f5193c;
        if (j3 <= j2) {
            return iVar2;
        }
        int i2 = i1.c.f4581b;
        G0(j3 * ((long) i2));
        if (iVar2.f5193c * ((long) i2) >= N()) {
            return null;
        }
        iVar2.b();
        return null;
    }

    public final long K() {
        return f4554f.get(this);
    }

    public final Throwable L() {
        return (Throwable) f4559k.get(this);
    }

    public final Throwable M() {
        Throwable thL = L();
        return thL == null ? new l("Channel was closed") : thL;
    }

    public final long N() {
        return f4553e.get(this);
    }

    public final Throwable O() {
        Throwable thL = L();
        return thL == null ? new m("Channel was closed") : thL;
    }

    public final long P() {
        return f4552d.get(this) & 1152921504606846975L;
    }

    public final boolean Q() {
        while (true) {
            AtomicReferenceFieldUpdater atomicReferenceFieldUpdater = f4557i;
            i iVarI = (i) atomicReferenceFieldUpdater.get(this);
            long jN = N();
            if (P() <= jN) {
                return false;
            }
            int i2 = i1.c.f4581b;
            long j2 = jN / ((long) i2);
            if (iVarI.f5193c == j2 || (iVarI = I(j2, iVarI)) != null) {
                iVarI.b();
                if (U(iVarI, (int) (jN % ((long) i2)), jN)) {
                    return true;
                }
                f4553e.compareAndSet(this, jN, jN + 1);
            } else if (((i) atomicReferenceFieldUpdater.get(this)).f5193c < j2) {
                return false;
            }
        }
    }

    public final void R(long j2) {
        if ((f4555g.addAndGet(this, j2) & 4611686018427387904L) != 0) {
            while ((f4555g.get(this) & 4611686018427387904L) != 0) {
            }
        }
    }

    public final void T() {
        Object obj;
        AtomicReferenceFieldUpdater atomicReferenceFieldUpdater = f4560l;
        do {
            obj = atomicReferenceFieldUpdater.get(this);
        } while (!atomicReferenceFieldUpdater.compareAndSet(this, obj, obj == null ? i1.c.f4596q : i1.c.f4597r));
        if (obj == null) {
            return;
        }
        ((Function1) obj).invoke(L());
    }

    public final boolean U(i iVar, int i2, long j2) {
        Object objW;
        do {
            objW = iVar.w(i2);
            if (objW != null && objW != i1.c.f4584e) {
                if (objW == i1.c.f4583d) {
                    return true;
                }
                if (objW == i1.c.f4589j || objW == i1.c.z() || objW == i1.c.f4588i || objW == i1.c.f4587h) {
                    return false;
                }
                if (objW == i1.c.f4586g) {
                    return true;
                }
                return objW != i1.c.f4585f && j2 == N();
            }
        } while (!iVar.r(i2, objW, i1.c.f4587h));
        G();
        return false;
    }

    public final boolean V(long j2, boolean z2) throws IllegalAccessException, InvocationTargetException {
        int i2 = (int) (j2 >> 60);
        if (i2 == 0 || i2 == 1) {
            return false;
        }
        if (i2 == 2) {
            D(j2 & 1152921504606846975L);
            if (z2 && Q()) {
                return false;
            }
        } else {
            if (i2 != 3) {
                throw new IllegalStateException(("unexpected close status: " + i2).toString());
            }
            C(j2 & 1152921504606846975L);
        }
        return true;
    }

    public boolean W() {
        return X(f4552d.get(this));
    }

    public final boolean X(long j2) {
        return V(j2, true);
    }

    public final boolean Y(long j2) {
        return V(j2, false);
    }

    public boolean Z() {
        return false;
    }

    @Override // i1.s
    public final void a(CancellationException cancellationException) {
        y(cancellationException);
    }

    public final boolean a0() {
        long jK = K();
        return jK == 0 || jK == LocationRequestCompat.PASSIVE_INTERVAL;
    }

    @Override // i1.t
    public Object b(Object obj, L0.d dVar) {
        return u0(this, obj, dVar);
    }

    /* JADX WARN: Code restructure failed: missing block: B:20:0x003c, code lost:
    
        r8 = (i1.i) r8.g();
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final long b0(i1.i r8) {
        /*
            r7 = this;
        L0:
            int r0 = i1.c.f4581b
            int r0 = r0 + (-1)
        L4:
            r1 = -1
            r3 = -1
            if (r3 >= r0) goto L3c
            long r3 = r8.f5193c
            int r5 = i1.c.f4581b
            long r5 = (long) r5
            long r3 = r3 * r5
            long r5 = (long) r0
            long r3 = r3 + r5
            long r5 = r7.N()
            int r5 = (r3 > r5 ? 1 : (r3 == r5 ? 0 : -1))
            if (r5 >= 0) goto L1a
            return r1
        L1a:
            java.lang.Object r1 = r8.w(r0)
            if (r1 == 0) goto L2c
            l1.F r2 = i1.c.k()
            if (r1 != r2) goto L27
            goto L2c
        L27:
            l1.F r2 = i1.c.f4583d
            if (r1 != r2) goto L39
            return r3
        L2c:
            l1.F r2 = i1.c.z()
            boolean r1 = r8.r(r0, r1, r2)
            if (r1 == 0) goto L1a
            r8.p()
        L39:
            int r0 = r0 + (-1)
            goto L4
        L3c:
            l1.e r8 = r8.g()
            i1.i r8 = (i1.i) r8
            if (r8 != 0) goto L0
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: i1.b.b0(i1.i):long");
    }

    public final void c0() {
        long j2;
        AtomicLongFieldUpdater atomicLongFieldUpdater = f4552d;
        do {
            j2 = atomicLongFieldUpdater.get(this);
            if (((int) (j2 >> 60)) != 0) {
                return;
            }
        } while (!atomicLongFieldUpdater.compareAndSet(this, j2, i1.c.w(1152921504606846975L & j2, 1)));
    }

    public final void d0() {
        long j2;
        AtomicLongFieldUpdater atomicLongFieldUpdater = f4552d;
        do {
            j2 = atomicLongFieldUpdater.get(this);
        } while (!atomicLongFieldUpdater.compareAndSet(this, j2, i1.c.w(1152921504606846975L & j2, 3)));
    }

    public final void e0() {
        long j2;
        long jW;
        AtomicLongFieldUpdater atomicLongFieldUpdater = f4552d;
        do {
            j2 = atomicLongFieldUpdater.get(this);
            int i2 = (int) (j2 >> 60);
            if (i2 == 0) {
                jW = i1.c.w(j2 & 1152921504606846975L, 2);
            } else if (i2 != 1) {
                return;
            } else {
                jW = i1.c.w(j2 & 1152921504606846975L, 3);
            }
        } while (!atomicLongFieldUpdater.compareAndSet(this, j2, jW));
    }

    /* JADX WARN: Code restructure failed: missing block: B:38:0x0011, code lost:
    
        continue;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void f0(long r5, i1.i r7) {
        /*
            r4 = this;
        L0:
            long r0 = r7.f5193c
            int r0 = (r0 > r5 ? 1 : (r0 == r5 ? 0 : -1))
            if (r0 >= 0) goto L11
            l1.e r0 = r7.e()
            i1.i r0 = (i1.i) r0
            if (r0 != 0) goto Lf
            goto L11
        Lf:
            r7 = r0
            goto L0
        L11:
            boolean r5 = r7.h()
            if (r5 == 0) goto L22
            l1.e r5 = r7.e()
            i1.i r5 = (i1.i) r5
            if (r5 != 0) goto L20
            goto L22
        L20:
            r7 = r5
            goto L11
        L22:
            java.util.concurrent.atomic.AtomicReferenceFieldUpdater r5 = i1.b.f4558j
        L24:
            java.lang.Object r6 = r5.get(r4)
            l1.C r6 = (l1.C) r6
            long r0 = r6.f5193c
            long r2 = r7.f5193c
            int r0 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1))
            if (r0 < 0) goto L33
            goto L49
        L33:
            boolean r0 = r7.q()
            if (r0 != 0) goto L3a
            goto L11
        L3a:
            boolean r0 = r5.compareAndSet(r4, r6, r7)
            if (r0 == 0) goto L4a
            boolean r4 = r6.m()
            if (r4 == 0) goto L49
            r6.k()
        L49:
            return
        L4a:
            boolean r6 = r7.m()
            if (r6 == 0) goto L24
            r7.k()
            goto L24
        */
        throw new UnsupportedOperationException("Method not decompiled: i1.b.f0(long, i1.i):void");
    }

    public void g0() {
    }

    public final void h0(InterfaceC0377k interfaceC0377k) {
        j.a aVar = H0.j.f299a;
        interfaceC0377k.resumeWith(H0.j.a(h.b(h.f4604b.a(L()))));
    }

    @Override // i1.t
    public void i(Function1 function1) {
        AtomicReferenceFieldUpdater atomicReferenceFieldUpdater = f4560l;
        if (atomicReferenceFieldUpdater.compareAndSet(this, null, function1)) {
            return;
        }
        do {
            Object obj = atomicReferenceFieldUpdater.get(this);
            if (obj != i1.c.f4596q) {
                if (obj == i1.c.f4597r) {
                    throw new IllegalStateException("Another handler was already registered and successfully invoked");
                }
                throw new IllegalStateException(("Another handler is already registered: " + obj).toString());
            }
        } while (!f4560l.compareAndSet(this, i1.c.f4596q, i1.c.f4597r));
        function1.invoke(L());
    }

    public final Object i0(Object obj, L0.d dVar) throws IllegalAccessException, InvocationTargetException {
        O oD;
        C0379l c0379l = new C0379l(M0.b.b(dVar), 1);
        c0379l.A();
        Function1 function1 = this.f4562b;
        if (function1 == null || (oD = x.d(function1, obj, null, 2, null)) == null) {
            Throwable thO = O();
            j.a aVar = H0.j.f299a;
            c0379l.resumeWith(H0.j.a(H0.k.a(thO)));
        } else {
            H0.a.a(oD, O());
            j.a aVar2 = H0.j.f299a;
            c0379l.resumeWith(H0.j.a(H0.k.a(oD)));
        }
        Object objX = c0379l.x();
        if (objX == M0.c.c()) {
            N0.h.c(dVar);
        }
        return objX == M0.c.c() ? objX : H0.s.f314a;
    }

    @Override // i1.s
    public f iterator() {
        return new a();
    }

    /* JADX WARN: Code restructure failed: missing block: B:60:?, code lost:
    
        return i1.h.f4604b.c(H0.s.f314a);
     */
    @Override // i1.t
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public java.lang.Object j(java.lang.Object r15) {
        /*
            r14 = this;
            java.util.concurrent.atomic.AtomicLongFieldUpdater r0 = i1.b.f4552d
            long r0 = r0.get(r14)
            boolean r0 = r14.w0(r0)
            if (r0 == 0) goto L13
            i1.h$b r14 = i1.h.f4604b
            java.lang.Object r14 = r14.b()
            return r14
        L13:
            l1.F r8 = i1.c.j()
            java.util.concurrent.atomic.AtomicReferenceFieldUpdater r0 = h()
            java.lang.Object r0 = r0.get(r14)
            i1.i r0 = (i1.i) r0
        L21:
            java.util.concurrent.atomic.AtomicLongFieldUpdater r1 = l()
            long r1 = r1.getAndIncrement(r14)
            r3 = 1152921504606846975(0xfffffffffffffff, double:1.2882297539194265E-231)
            long r9 = r1 & r3
            boolean r11 = m(r14, r1)
            int r1 = i1.c.f4581b
            long r2 = (long) r1
            long r2 = r9 / r2
            long r4 = (long) r1
            long r4 = r9 % r4
            int r12 = (int) r4
            long r4 = r0.f5193c
            int r1 = (r4 > r2 ? 1 : (r4 == r2 ? 0 : -1))
            if (r1 == 0) goto L59
            i1.i r1 = d(r14, r2, r0)
            if (r1 != 0) goto L57
            if (r11 == 0) goto L21
        L4b:
            i1.h$b r15 = i1.h.f4604b
            java.lang.Throwable r14 = r14.O()
            java.lang.Object r14 = r15.a(r14)
            goto Lba
        L57:
            r13 = r1
            goto L5a
        L59:
            r13 = r0
        L5a:
            r0 = r14
            r1 = r13
            r2 = r12
            r3 = r15
            r4 = r9
            r6 = r8
            r7 = r11
            int r0 = w(r0, r1, r2, r3, r4, r6, r7)
            if (r0 == 0) goto Lb6
            r1 = 1
            if (r0 == r1) goto Lad
            r1 = 2
            if (r0 == r1) goto L90
            r1 = 3
            if (r0 == r1) goto L88
            r1 = 4
            if (r0 == r1) goto L7c
            r1 = 5
            if (r0 == r1) goto L77
            goto L7a
        L77:
            r13.b()
        L7a:
            r0 = r13
            goto L21
        L7c:
            long r0 = r14.N()
            int r15 = (r9 > r0 ? 1 : (r9 == r0 ? 0 : -1))
            if (r15 >= 0) goto L4b
            r13.b()
            goto L4b
        L88:
            java.lang.IllegalStateException r14 = new java.lang.IllegalStateException
            java.lang.String r15 = "unexpected"
            r14.<init>(r15)
            throw r14
        L90:
            if (r11 == 0) goto L96
            r13.p()
            goto L4b
        L96:
            boolean r15 = r8 instanceof g1.O0
            if (r15 == 0) goto L9d
            g1.O0 r8 = (g1.O0) r8
            goto L9e
        L9d:
            r8 = 0
        L9e:
            if (r8 == 0) goto La3
            t(r14, r8, r13, r12)
        La3:
            r13.p()
            i1.h$b r14 = i1.h.f4604b
            java.lang.Object r14 = r14.b()
            goto Lba
        Lad:
            i1.h$b r14 = i1.h.f4604b
            H0.s r15 = H0.s.f314a
            java.lang.Object r14 = r14.c(r15)
            goto Lba
        Lb6:
            r13.b()
            goto Lad
        Lba:
            return r14
        */
        throw new UnsupportedOperationException("Method not decompiled: i1.b.j(java.lang.Object):java.lang.Object");
    }

    public final void j0(Object obj, InterfaceC0377k interfaceC0377k) throws IllegalAccessException, InvocationTargetException {
        Function1 function1 = this.f4562b;
        if (function1 != null) {
            x.b(function1, obj, interfaceC0377k.getContext());
        }
        Throwable thO = O();
        j.a aVar = H0.j.f299a;
        interfaceC0377k.resumeWith(H0.j.a(H0.k.a(thO)));
    }

    @Override // i1.s
    public Object k() {
        i iVar;
        long j2 = f4553e.get(this);
        long j3 = f4552d.get(this);
        if (X(j3)) {
            return h.f4604b.a(L());
        }
        if (j2 >= (j3 & 1152921504606846975L)) {
            return h.f4604b.b();
        }
        Object obj = i1.c.f4590k;
        i iVar2 = (i) f4557i.get(this);
        while (!W()) {
            long andIncrement = f4553e.getAndIncrement(this);
            int i2 = i1.c.f4581b;
            long j4 = andIncrement / ((long) i2);
            int i3 = (int) (andIncrement % ((long) i2));
            if (iVar2.f5193c != j4) {
                i iVarI = I(j4, iVar2);
                if (iVarI == null) {
                    continue;
                } else {
                    iVar = iVarI;
                }
            } else {
                iVar = iVar2;
            }
            Object objB0 = B0(iVar, i3, andIncrement, obj);
            if (objB0 == i1.c.f4592m) {
                O0 o02 = obj instanceof O0 ? (O0) obj : null;
                if (o02 != null) {
                    m0(o02, iVar, i3);
                }
                H0(andIncrement);
                iVar.p();
                return h.f4604b.b();
            }
            if (objB0 != i1.c.f4594o) {
                if (objB0 == i1.c.f4593n) {
                    throw new IllegalStateException("unexpected");
                }
                iVar.b();
                return h.f4604b.c(objB0);
            }
            if (andIncrement < P()) {
                iVar.b();
            }
            iVar2 = iVar;
        }
        return h.f4604b.a(L());
    }

    public void k0() {
    }

    public void l0() {
    }

    public final void m0(O0 o02, i iVar, int i2) {
        l0();
        o02.b(iVar, i2);
    }

    public final void n0(O0 o02, i iVar, int i2) {
        o02.b(iVar, i2 + i1.c.f4581b);
    }

    @Override // i1.t
    public boolean o(Throwable th) {
        return B(th, false);
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object p0(i1.i r11, int r12, long r13, L0.d r15) throws java.lang.Throwable {
        /*
            Method dump skipped, instruction units count: 315
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: i1.b.p0(i1.i, int, long, L0.d):java.lang.Object");
    }

    @Override // i1.s
    public Object q(L0.d dVar) {
        return o0(this, dVar);
    }

    /* JADX WARN: Code restructure failed: missing block: B:50:0x00b3, code lost:
    
        r12 = (i1.i) r12.g();
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void q0(i1.i r12) throws java.lang.IllegalAccessException, java.lang.reflect.InvocationTargetException {
        /*
            Method dump skipped, instruction units count: 229
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: i1.b.q0(i1.i):void");
    }

    public final void r0(O0 o02) {
        t0(o02, true);
    }

    @Override // i1.t
    public boolean s() {
        return Y(f4552d.get(this));
    }

    public final void s0(O0 o02) {
        t0(o02, false);
    }

    public final void t0(O0 o02, boolean z2) {
        if (o02 instanceof InterfaceC0377k) {
            L0.d dVar = (L0.d) o02;
            j.a aVar = H0.j.f299a;
            dVar.resumeWith(H0.j.a(H0.k.a(z2 ? M() : O())));
        } else if (o02 instanceof r) {
            C0379l c0379l = ((r) o02).f4617a;
            j.a aVar2 = H0.j.f299a;
            c0379l.resumeWith(H0.j.a(h.b(h.f4604b.a(L()))));
        } else {
            if (o02 instanceof a) {
                ((a) o02).j();
                return;
            }
            throw new IllegalStateException(("Unexpected waiter: " + o02).toString());
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:78:0x01b7, code lost:
    
        r3 = (i1.i) r3.e();
     */
    /* JADX WARN: Code restructure failed: missing block: B:79:0x01be, code lost:
    
        if (r3 != null) goto L85;
     */
    /* JADX WARN: Multi-variable type inference failed */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public java.lang.String toString() {
        /*
            Method dump skipped, instruction units count: 487
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: i1.b.toString():java.lang.String");
    }

    /* JADX WARN: Removed duplicated region for block: B:61:0x0112  */
    /* JADX WARN: Removed duplicated region for block: B:64:0x011b A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:65:0x011c  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object v0(i1.i r21, int r22, java.lang.Object r23, long r24, L0.d r26) {
        /*
            Method dump skipped, instruction units count: 291
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: i1.b.v0(i1.i, int, java.lang.Object, long, L0.d):java.lang.Object");
    }

    public final boolean w0(long j2) {
        if (Y(j2)) {
            return false;
        }
        return !x(j2 & 1152921504606846975L);
    }

    public final boolean x(long j2) {
        return j2 < K() || j2 < N() + ((long) this.f4561a);
    }

    public final boolean x0(Object obj, Object obj2) {
        if (obj instanceof r) {
            kotlin.jvm.internal.n.e(obj, "null cannot be cast to non-null type kotlinx.coroutines.channels.ReceiveCatching<E of kotlinx.coroutines.channels.BufferedChannel>");
            r rVar = (r) obj;
            C0379l c0379l = rVar.f4617a;
            h hVarB = h.b(h.f4604b.c(obj2));
            Function1 function1 = this.f4562b;
            return i1.c.B(c0379l, hVarB, function1 != null ? x.a(function1, obj2, rVar.f4617a.getContext()) : null);
        }
        if (obj instanceof a) {
            kotlin.jvm.internal.n.e(obj, "null cannot be cast to non-null type kotlinx.coroutines.channels.BufferedChannel.BufferedChannelIterator<E of kotlinx.coroutines.channels.BufferedChannel>");
            return ((a) obj).i(obj2);
        }
        if (obj instanceof InterfaceC0377k) {
            kotlin.jvm.internal.n.e(obj, "null cannot be cast to non-null type kotlinx.coroutines.CancellableContinuation<E of kotlinx.coroutines.channels.BufferedChannel>");
            InterfaceC0377k interfaceC0377k = (InterfaceC0377k) obj;
            Function1 function12 = this.f4562b;
            return i1.c.B(interfaceC0377k, obj2, function12 != null ? x.a(function12, obj2, interfaceC0377k.getContext()) : null);
        }
        throw new IllegalStateException(("Unexpected receiver type: " + obj).toString());
    }

    public boolean y(Throwable th) {
        if (th == null) {
            th = new CancellationException("Channel was cancelled");
        }
        return B(th, true);
    }

    public final boolean y0(Object obj, i iVar, int i2) {
        if (obj instanceof InterfaceC0377k) {
            kotlin.jvm.internal.n.e(obj, "null cannot be cast to non-null type kotlinx.coroutines.CancellableContinuation<kotlin.Unit>");
            return i1.c.C((InterfaceC0377k) obj, H0.s.f314a, null, 2, null);
        }
        throw new IllegalStateException(("Unexpected waiter: " + obj).toString());
    }

    public final void z(i iVar, long j2) {
        Object objB = AbstractC0457l.b(null, 1, null);
        loop0: while (iVar != null) {
            for (int i2 = i1.c.f4581b - 1; -1 < i2; i2--) {
                if ((iVar.f5193c * ((long) i1.c.f4581b)) + ((long) i2) < j2) {
                    break loop0;
                }
                while (true) {
                    Object objW = iVar.w(i2);
                    if (objW != null && objW != i1.c.f4584e) {
                        if (!(objW instanceof u)) {
                            if (!(objW instanceof O0)) {
                                break;
                            }
                            if (iVar.r(i2, objW, i1.c.z())) {
                                objB = AbstractC0457l.c(objB, objW);
                                iVar.x(i2, true);
                                break;
                            }
                        } else {
                            if (iVar.r(i2, objW, i1.c.z())) {
                                objB = AbstractC0457l.c(objB, ((u) objW).f4618a);
                                iVar.x(i2, true);
                                break;
                            }
                        }
                    } else {
                        if (iVar.r(i2, objW, i1.c.z())) {
                            iVar.p();
                            break;
                        }
                    }
                }
            }
            iVar = (i) iVar.g();
        }
        if (objB != null) {
            if (!(objB instanceof ArrayList)) {
                r0((O0) objB);
                return;
            }
            kotlin.jvm.internal.n.e(objB, "null cannot be cast to non-null type java.util.ArrayList<E of kotlinx.coroutines.internal.InlineList>{ kotlin.collections.TypeAliasesKt.ArrayList<E of kotlinx.coroutines.internal.InlineList> }");
            ArrayList arrayList = (ArrayList) objB;
            for (int size = arrayList.size() - 1; -1 < size; size--) {
                r0((O0) arrayList.get(size));
            }
        }
    }

    public final boolean z0(i iVar, int i2, long j2) {
        Object objW = iVar.w(i2);
        if (!(objW instanceof O0) || j2 < f4553e.get(this) || !iVar.r(i2, objW, i1.c.f4586g)) {
            return A0(iVar, i2, j2);
        }
        if (y0(objW, iVar, i2)) {
            iVar.A(i2, i1.c.f4583d);
            return true;
        }
        iVar.A(i2, i1.c.f4589j);
        iVar.x(i2, false);
        return false;
    }
}
