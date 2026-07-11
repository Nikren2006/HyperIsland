package p1;

import H0.s;
import g1.InterfaceC0377k;
import g1.O0;
import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;
import java.util.concurrent.atomic.AtomicLongFieldUpdater;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.l;
import kotlin.jvm.internal.n;
import kotlin.jvm.internal.o;
import l1.AbstractC0449d;
import l1.C;
import l1.D;

/* JADX INFO: loaded from: classes2.dex */
public class d {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final AtomicReferenceFieldUpdater f6396c = AtomicReferenceFieldUpdater.newUpdater(d.class, Object.class, "head");

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static final AtomicLongFieldUpdater f6397d = AtomicLongFieldUpdater.newUpdater(d.class, "deqIdx");

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static final AtomicReferenceFieldUpdater f6398e = AtomicReferenceFieldUpdater.newUpdater(d.class, Object.class, "tail");

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public static final AtomicLongFieldUpdater f6399f = AtomicLongFieldUpdater.newUpdater(d.class, "enqIdx");

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public static final AtomicIntegerFieldUpdater f6400g = AtomicIntegerFieldUpdater.newUpdater(d.class, "_availablePermits");
    private volatile int _availablePermits;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final int f6401a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final Function1 f6402b;
    private volatile long deqIdx;
    private volatile long enqIdx;
    private volatile Object head;
    private volatile Object tail;

    public /* synthetic */ class a extends l implements Function2 {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final a f6403a = new a();

        public a() {
            super(2, e.class, "createSegment", "createSegment(JLkotlinx/coroutines/sync/SemaphoreSegment;)Lkotlinx/coroutines/sync/SemaphoreSegment;", 1);
        }

        public final f b(long j2, f fVar) {
            return e.h(j2, fVar);
        }

        @Override // kotlin.jvm.functions.Function2
        public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
            return b(((Number) obj).longValue(), (f) obj2);
        }
    }

    public static final class b extends o implements Function1 {
        public b() {
            super(1);
        }

        @Override // kotlin.jvm.functions.Function1
        public /* bridge */ /* synthetic */ Object invoke(Object obj) {
            invoke((Throwable) obj);
            return s.f314a;
        }

        public final void invoke(Throwable th) {
            d.this.h();
        }
    }

    public /* synthetic */ class c extends l implements Function2 {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final c f6405a = new c();

        public c() {
            super(2, e.class, "createSegment", "createSegment(JLkotlinx/coroutines/sync/SemaphoreSegment;)Lkotlinx/coroutines/sync/SemaphoreSegment;", 1);
        }

        public final f b(long j2, f fVar) {
            return e.h(j2, fVar);
        }

        @Override // kotlin.jvm.functions.Function2
        public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
            return b(((Number) obj).longValue(), (f) obj2);
        }
    }

    public d(int i2, int i3) {
        this.f6401a = i2;
        if (i2 <= 0) {
            throw new IllegalArgumentException(("Semaphore should have at least 1 permit, but had " + i2).toString());
        }
        if (i3 < 0 || i3 > i2) {
            throw new IllegalArgumentException(("The number of acquired permits should be in 0.." + i2).toString());
        }
        f fVar = new f(0L, null, 2);
        this.head = fVar;
        this.tail = fVar;
        this._availablePermits = i2 - i3;
        this.f6402b = new b();
    }

    public final void c(InterfaceC0377k interfaceC0377k) {
        while (f() <= 0) {
            n.e(interfaceC0377k, "null cannot be cast to non-null type kotlinx.coroutines.Waiter");
            if (d((O0) interfaceC0377k)) {
                return;
            }
        }
        interfaceC0377k.h(s.f314a, this.f6402b);
    }

    public final boolean d(O0 o02) {
        Object objC;
        AtomicReferenceFieldUpdater atomicReferenceFieldUpdater = f6398e;
        f fVar = (f) atomicReferenceFieldUpdater.get(this);
        long andIncrement = f6399f.getAndIncrement(this);
        a aVar = a.f6403a;
        long j2 = andIncrement / ((long) e.f6411f);
        loop0: while (true) {
            objC = AbstractC0449d.c(fVar, j2, aVar);
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
        f fVar2 = (f) D.b(objC);
        int i2 = (int) (andIncrement % ((long) e.f6411f));
        if (fVar2.r().compareAndSet(i2, null, o02)) {
            o02.b(fVar2, i2);
            return true;
        }
        if (!fVar2.r().compareAndSet(i2, e.f6407b, e.f6408c)) {
            return false;
        }
        if (o02 instanceof InterfaceC0377k) {
            n.e(o02, "null cannot be cast to non-null type kotlinx.coroutines.CancellableContinuation<kotlin.Unit>");
            ((InterfaceC0377k) o02).h(s.f314a, this.f6402b);
            return true;
        }
        throw new IllegalStateException(("unexpected: " + o02).toString());
    }

    public final void e() {
        AtomicIntegerFieldUpdater atomicIntegerFieldUpdater;
        int i2;
        int i3;
        do {
            atomicIntegerFieldUpdater = f6400g;
            i2 = atomicIntegerFieldUpdater.get(this);
            i3 = this.f6401a;
            if (i2 <= i3) {
                return;
            }
        } while (!atomicIntegerFieldUpdater.compareAndSet(this, i2, i3));
    }

    public final int f() {
        int andDecrement;
        do {
            andDecrement = f6400g.getAndDecrement(this);
        } while (andDecrement > this.f6401a);
        return andDecrement;
    }

    public int g() {
        return Math.max(f6400g.get(this), 0);
    }

    public void h() {
        do {
            int andIncrement = f6400g.getAndIncrement(this);
            if (andIncrement >= this.f6401a) {
                e();
                throw new IllegalStateException(("The number of released permits cannot be greater than " + this.f6401a).toString());
            }
            if (andIncrement >= 0) {
                return;
            }
        } while (!k());
    }

    public boolean i() {
        while (true) {
            AtomicIntegerFieldUpdater atomicIntegerFieldUpdater = f6400g;
            int i2 = atomicIntegerFieldUpdater.get(this);
            if (i2 > this.f6401a) {
                e();
            } else {
                if (i2 <= 0) {
                    return false;
                }
                if (atomicIntegerFieldUpdater.compareAndSet(this, i2, i2 - 1)) {
                    return true;
                }
            }
        }
    }

    public final boolean j(Object obj) {
        if (!(obj instanceof InterfaceC0377k)) {
            throw new IllegalStateException(("unexpected: " + obj).toString());
        }
        n.e(obj, "null cannot be cast to non-null type kotlinx.coroutines.CancellableContinuation<kotlin.Unit>");
        InterfaceC0377k interfaceC0377k = (InterfaceC0377k) obj;
        Object objM = interfaceC0377k.m(s.f314a, null, this.f6402b);
        if (objM == null) {
            return false;
        }
        interfaceC0377k.t(objM);
        return true;
    }

    public final boolean k() {
        Object objC;
        AtomicReferenceFieldUpdater atomicReferenceFieldUpdater = f6396c;
        f fVar = (f) atomicReferenceFieldUpdater.get(this);
        long andIncrement = f6397d.getAndIncrement(this);
        long j2 = andIncrement / ((long) e.f6411f);
        c cVar = c.f6405a;
        loop0: while (true) {
            objC = AbstractC0449d.c(fVar, j2, cVar);
            if (D.c(objC)) {
                break;
            }
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
        }
        f fVar2 = (f) D.b(objC);
        fVar2.b();
        if (fVar2.f5193c > j2) {
            return false;
        }
        int i2 = (int) (andIncrement % ((long) e.f6411f));
        Object andSet = fVar2.r().getAndSet(i2, e.f6407b);
        if (andSet != null) {
            if (andSet == e.f6410e) {
                return false;
            }
            return j(andSet);
        }
        int i3 = e.f6406a;
        for (int i4 = 0; i4 < i3; i4++) {
            if (fVar2.r().get(i2) == e.f6408c) {
                return true;
            }
        }
        return !fVar2.r().compareAndSet(i2, e.f6407b, e.f6409d);
    }
}
