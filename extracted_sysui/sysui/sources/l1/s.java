package l1;

import java.util.concurrent.atomic.AtomicLongFieldUpdater;
import java.util.concurrent.atomic.AtomicReferenceArray;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* JADX INFO: loaded from: classes2.dex */
public final class s {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static final a f5243e = new a(null);

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public static final AtomicReferenceFieldUpdater f5244f = AtomicReferenceFieldUpdater.newUpdater(s.class, Object.class, "_next");

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public static final AtomicLongFieldUpdater f5245g = AtomicLongFieldUpdater.newUpdater(s.class, "_state");

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public static final F f5246h = new F("REMOVE_FROZEN");
    private volatile Object _next;
    private volatile long _state;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final int f5247a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final boolean f5248b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final int f5249c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final AtomicReferenceArray f5250d;

    public static final class a {
        public /* synthetic */ a(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final int a(long j2) {
            return (j2 & 2305843009213693952L) != 0 ? 2 : 1;
        }

        public final long b(long j2, int i2) {
            return d(j2, 1073741823L) | ((long) i2);
        }

        public final long c(long j2, int i2) {
            return d(j2, 1152921503533105152L) | (((long) i2) << 30);
        }

        public final long d(long j2, long j3) {
            return j2 & (~j3);
        }

        public a() {
        }
    }

    public static final class b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final int f5251a;

        public b(int i2) {
            this.f5251a = i2;
        }
    }

    public s(int i2, boolean z2) {
        this.f5247a = i2;
        this.f5248b = z2;
        int i3 = i2 - 1;
        this.f5249c = i3;
        this.f5250d = new AtomicReferenceArray(i2);
        if (i3 > 1073741823) {
            throw new IllegalStateException("Check failed.");
        }
        if ((i2 & i3) != 0) {
            throw new IllegalStateException("Check failed.");
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:18:0x004e, code lost:
    
        return 1;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final int a(java.lang.Object r13) {
        /*
            r12 = this;
            java.util.concurrent.atomic.AtomicLongFieldUpdater r0 = l1.s.f5245g
        L2:
            long r3 = r0.get(r12)
            r1 = 3458764513820540928(0x3000000000000000, double:1.727233711018889E-77)
            long r1 = r1 & r3
            r7 = 0
            int r1 = (r1 > r7 ? 1 : (r1 == r7 ? 0 : -1))
            if (r1 == 0) goto L16
            l1.s$a r12 = l1.s.f5243e
            int r12 = r12.a(r3)
            return r12
        L16:
            r1 = 1073741823(0x3fffffff, double:5.304989472E-315)
            long r1 = r1 & r3
            int r1 = (int) r1
            r5 = 1152921503533105152(0xfffffffc0000000, double:1.2882296003504729E-231)
            long r5 = r5 & r3
            r2 = 30
            long r5 = r5 >> r2
            int r9 = (int) r5
            int r10 = r12.f5249c
            int r2 = r9 + 2
            r2 = r2 & r10
            r5 = r1 & r10
            r6 = 1
            if (r2 != r5) goto L30
            return r6
        L30:
            boolean r2 = r12.f5248b
            r5 = 1073741823(0x3fffffff, float:1.9999999)
            if (r2 != 0) goto L4f
            java.util.concurrent.atomic.AtomicReferenceArray r2 = r12.f5250d
            r11 = r9 & r10
            java.lang.Object r2 = r2.get(r11)
            if (r2 == 0) goto L4f
            int r2 = r12.f5247a
            r3 = 1024(0x400, float:1.435E-42)
            if (r2 < r3) goto L4e
            int r9 = r9 - r1
            r1 = r9 & r5
            int r2 = r2 >> 1
            if (r1 <= r2) goto L2
        L4e:
            return r6
        L4f:
            int r1 = r9 + 1
            r1 = r1 & r5
            java.util.concurrent.atomic.AtomicLongFieldUpdater r2 = l1.s.f5245g
            l1.s$a r5 = l1.s.f5243e
            long r5 = r5.c(r3, r1)
            r1 = r2
            r2 = r12
            boolean r1 = r1.compareAndSet(r2, r3, r5)
            if (r1 == 0) goto L2
            java.util.concurrent.atomic.AtomicReferenceArray r0 = r12.f5250d
            r1 = r9 & r10
            r0.set(r1, r13)
        L69:
            java.util.concurrent.atomic.AtomicLongFieldUpdater r0 = l1.s.f5245g
            long r0 = r0.get(r12)
            r2 = 1152921504606846976(0x1000000000000000, double:1.2882297539194267E-231)
            long r0 = r0 & r2
            int r0 = (r0 > r7 ? 1 : (r0 == r7 ? 0 : -1))
            if (r0 == 0) goto L80
            l1.s r12 = r12.i()
            l1.s r12 = r12.e(r9, r13)
            if (r12 != 0) goto L69
        L80:
            r12 = 0
            return r12
        */
        throw new UnsupportedOperationException("Method not decompiled: l1.s.a(java.lang.Object):int");
    }

    public final s b(long j2) {
        s sVar = new s(this.f5247a * 2, this.f5248b);
        int i2 = (int) (1073741823 & j2);
        int i3 = (int) ((1152921503533105152L & j2) >> 30);
        while (true) {
            int i4 = this.f5249c;
            if ((i2 & i4) == (i3 & i4)) {
                f5245g.set(sVar, f5243e.d(j2, 1152921504606846976L));
                return sVar;
            }
            Object bVar = this.f5250d.get(i4 & i2);
            if (bVar == null) {
                bVar = new b(i2);
            }
            sVar.f5250d.set(sVar.f5249c & i2, bVar);
            i2++;
        }
    }

    public final s c(long j2) {
        AtomicReferenceFieldUpdater atomicReferenceFieldUpdater = f5244f;
        while (true) {
            s sVar = (s) atomicReferenceFieldUpdater.get(this);
            if (sVar != null) {
                return sVar;
            }
            f5244f.compareAndSet(this, null, b(j2));
        }
    }

    public final boolean d() {
        long j2;
        AtomicLongFieldUpdater atomicLongFieldUpdater = f5245g;
        do {
            j2 = atomicLongFieldUpdater.get(this);
            if ((j2 & 2305843009213693952L) != 0) {
                return true;
            }
            if ((1152921504606846976L & j2) != 0) {
                return false;
            }
        } while (!atomicLongFieldUpdater.compareAndSet(this, j2, j2 | 2305843009213693952L));
        return true;
    }

    public final s e(int i2, Object obj) {
        Object obj2 = this.f5250d.get(this.f5249c & i2);
        if (!(obj2 instanceof b) || ((b) obj2).f5251a != i2) {
            return null;
        }
        this.f5250d.set(i2 & this.f5249c, obj);
        return this;
    }

    public final int f() {
        long j2 = f5245g.get(this);
        return 1073741823 & (((int) ((j2 & 1152921503533105152L) >> 30)) - ((int) (1073741823 & j2)));
    }

    public final boolean g() {
        long j2 = f5245g.get(this);
        return ((int) (1073741823 & j2)) == ((int) ((j2 & 1152921503533105152L) >> 30));
    }

    public final long h() {
        long j2;
        long j3;
        AtomicLongFieldUpdater atomicLongFieldUpdater = f5245g;
        do {
            j2 = atomicLongFieldUpdater.get(this);
            if ((j2 & 1152921504606846976L) != 0) {
                return j2;
            }
            j3 = j2 | 1152921504606846976L;
        } while (!atomicLongFieldUpdater.compareAndSet(this, j2, j3));
        return j3;
    }

    public final s i() {
        return c(h());
    }

    public final Object j() {
        AtomicLongFieldUpdater atomicLongFieldUpdater = f5245g;
        while (true) {
            long j2 = atomicLongFieldUpdater.get(this);
            if ((1152921504606846976L & j2) != 0) {
                return f5246h;
            }
            int i2 = (int) (1073741823 & j2);
            int i3 = this.f5249c;
            if ((((int) ((1152921503533105152L & j2) >> 30)) & i3) == (i2 & i3)) {
                return null;
            }
            Object obj = this.f5250d.get(i3 & i2);
            if (obj == null) {
                if (this.f5248b) {
                    return null;
                }
            } else {
                if (obj instanceof b) {
                    return null;
                }
                int i4 = (i2 + 1) & 1073741823;
                if (f5245g.compareAndSet(this, j2, f5243e.b(j2, i4))) {
                    this.f5250d.set(this.f5249c & i2, null);
                    return obj;
                }
                if (this.f5248b) {
                    do {
                        this = this.k(i2, i4);
                    } while (this != null);
                    return obj;
                }
            }
        }
    }

    public final s k(int i2, int i3) {
        long j2;
        int i4;
        AtomicLongFieldUpdater atomicLongFieldUpdater = f5245g;
        do {
            j2 = atomicLongFieldUpdater.get(this);
            i4 = (int) (1073741823 & j2);
            if ((1152921504606846976L & j2) != 0) {
                return i();
            }
        } while (!f5245g.compareAndSet(this, j2, f5243e.b(j2, i3)));
        this.f5250d.set(this.f5249c & i4, null);
        return null;
    }
}
