package l1;

import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;

/* JADX INFO: loaded from: classes2.dex */
public class q {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final AtomicReferenceFieldUpdater f5237a = AtomicReferenceFieldUpdater.newUpdater(q.class, Object.class, "_next");

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final AtomicReferenceFieldUpdater f5238b = AtomicReferenceFieldUpdater.newUpdater(q.class, Object.class, "_prev");

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final AtomicReferenceFieldUpdater f5239c = AtomicReferenceFieldUpdater.newUpdater(q.class, Object.class, "_removedRef");
    private volatile Object _next = this;
    private volatile Object _prev = this;
    private volatile Object _removedRef;

    public static abstract class a extends AbstractC0447b {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final q f5240b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public q f5241c;

        public a(q qVar) {
            this.f5240b = qVar;
        }

        @Override // l1.AbstractC0447b
        /* JADX INFO: renamed from: e, reason: merged with bridge method [inline-methods] */
        public void b(q qVar, Object obj) {
            boolean z2 = obj == null;
            q qVar2 = z2 ? this.f5240b : this.f5241c;
            if (qVar2 != null && q.f5237a.compareAndSet(qVar, this, qVar2) && z2) {
                q qVar3 = this.f5240b;
                q qVar4 = this.f5241c;
                kotlin.jvm.internal.n.d(qVar4);
                qVar3.i(qVar4);
            }
        }
    }

    public final boolean f(q qVar) {
        f5238b.lazySet(qVar, this);
        f5237a.lazySet(qVar, this);
        while (j() == this) {
            if (f5237a.compareAndSet(this, this, qVar)) {
                qVar.i(this);
                return true;
            }
        }
        return false;
    }

    /* JADX WARN: Code restructure failed: missing block: B:24:0x0042, code lost:
    
        if (r4.compareAndSet(r3, r2, ((l1.z) r5).f5257a) != false) goto L26;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final l1.q g(l1.y r9) {
        /*
            r8 = this;
        L0:
            java.util.concurrent.atomic.AtomicReferenceFieldUpdater r0 = l1.q.f5238b
            java.lang.Object r0 = r0.get(r8)
            l1.q r0 = (l1.q) r0
            r1 = 0
            r2 = r0
        La:
            r3 = r1
        Lb:
            java.util.concurrent.atomic.AtomicReferenceFieldUpdater r4 = l1.q.f5237a
            java.lang.Object r5 = r4.get(r2)
            if (r5 != r8) goto L20
            if (r0 != r2) goto L16
            return r2
        L16:
            java.util.concurrent.atomic.AtomicReferenceFieldUpdater r1 = l1.q.f5238b
            boolean r0 = r1.compareAndSet(r8, r0, r2)
            if (r0 != 0) goto L1f
            goto L0
        L1f:
            return r2
        L20:
            boolean r6 = r8.m()
            if (r6 == 0) goto L27
            return r1
        L27:
            if (r5 != r9) goto L2a
            return r2
        L2a:
            boolean r6 = r5 instanceof l1.y
            if (r6 == 0) goto L34
            l1.y r5 = (l1.y) r5
            r5.a(r2)
            goto L0
        L34:
            boolean r6 = r5 instanceof l1.z
            if (r6 == 0) goto L50
            if (r3 == 0) goto L47
            l1.z r5 = (l1.z) r5
            l1.q r5 = r5.f5257a
            boolean r2 = r4.compareAndSet(r3, r2, r5)
            if (r2 != 0) goto L45
            goto L0
        L45:
            r2 = r3
            goto La
        L47:
            java.util.concurrent.atomic.AtomicReferenceFieldUpdater r4 = l1.q.f5238b
            java.lang.Object r2 = r4.get(r2)
            l1.q r2 = (l1.q) r2
            goto Lb
        L50:
            java.lang.String r3 = "null cannot be cast to non-null type kotlinx.coroutines.internal.LockFreeLinkedListNode{ kotlinx.coroutines.internal.LockFreeLinkedListKt.Node }"
            kotlin.jvm.internal.n.e(r5, r3)
            r3 = r5
            l1.q r3 = (l1.q) r3
            r7 = r3
            r3 = r2
            r2 = r7
            goto Lb
        */
        throw new UnsupportedOperationException("Method not decompiled: l1.q.g(l1.y):l1.q");
    }

    public final q h(q qVar) {
        while (qVar.m()) {
            qVar = (q) f5238b.get(qVar);
        }
        return qVar;
    }

    public final void i(q qVar) {
        q qVar2;
        AtomicReferenceFieldUpdater atomicReferenceFieldUpdater = f5238b;
        do {
            qVar2 = (q) atomicReferenceFieldUpdater.get(qVar);
            if (j() != qVar) {
                return;
            }
        } while (!f5238b.compareAndSet(qVar, qVar2, this));
        if (m()) {
            qVar.g(null);
        }
    }

    public final Object j() {
        AtomicReferenceFieldUpdater atomicReferenceFieldUpdater = f5237a;
        while (true) {
            Object obj = atomicReferenceFieldUpdater.get(this);
            if (!(obj instanceof y)) {
                return obj;
            }
            ((y) obj).a(this);
        }
    }

    public final q k() {
        return p.b(j());
    }

    public final q l() {
        q qVarG = g(null);
        return qVarG == null ? h((q) f5238b.get(this)) : qVarG;
    }

    public boolean m() {
        return j() instanceof z;
    }

    public boolean n() {
        return o() == null;
    }

    public final q o() {
        Object objJ;
        q qVar;
        do {
            objJ = j();
            if (objJ instanceof z) {
                return ((z) objJ).f5257a;
            }
            if (objJ == this) {
                return (q) objJ;
            }
            kotlin.jvm.internal.n.e(objJ, "null cannot be cast to non-null type kotlinx.coroutines.internal.LockFreeLinkedListNode{ kotlinx.coroutines.internal.LockFreeLinkedListKt.Node }");
            qVar = (q) objJ;
        } while (!f5237a.compareAndSet(this, objJ, qVar.p()));
        qVar.g(null);
        return null;
    }

    public final z p() {
        AtomicReferenceFieldUpdater atomicReferenceFieldUpdater = f5239c;
        z zVar = (z) atomicReferenceFieldUpdater.get(this);
        if (zVar != null) {
            return zVar;
        }
        z zVar2 = new z(this);
        atomicReferenceFieldUpdater.lazySet(this, zVar2);
        return zVar2;
    }

    public final int q(q qVar, q qVar2, a aVar) {
        f5238b.lazySet(qVar, this);
        AtomicReferenceFieldUpdater atomicReferenceFieldUpdater = f5237a;
        atomicReferenceFieldUpdater.lazySet(qVar, qVar2);
        aVar.f5241c = qVar2;
        if (atomicReferenceFieldUpdater.compareAndSet(this, qVar2, aVar)) {
            return aVar.a(this) == null ? 1 : 2;
        }
        return 0;
    }

    public String toString() {
        return new kotlin.jvm.internal.u(this) { // from class: l1.q.b
            @Override // d1.InterfaceC0328g
            public Object get() {
                return g1.I.a(this.receiver);
            }
        } + '@' + g1.I.b(this);
    }
}
