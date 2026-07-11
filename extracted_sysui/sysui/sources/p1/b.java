package p1;

import H0.s;
import L0.g;
import N0.h;
import g1.AbstractC0383n;
import g1.B;
import g1.C0379l;
import g1.I;
import g1.InterfaceC0377k;
import g1.O0;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.o;
import l1.C;

/* JADX INFO: loaded from: classes2.dex */
public class b extends d implements p1.a {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public static final AtomicReferenceFieldUpdater f6382i = AtomicReferenceFieldUpdater.newUpdater(b.class, Object.class, "owner");

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final Function3 f6383h;
    private volatile Object owner;

    public final class a implements InterfaceC0377k, O0 {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final C0379l f6384a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final Object f6385b;

        /* JADX INFO: renamed from: p1.b$a$a, reason: collision with other inner class name */
        public static final class C0162a extends o implements Function1 {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            public final /* synthetic */ b f6387a;

            /* JADX INFO: renamed from: b, reason: collision with root package name */
            public final /* synthetic */ a f6388b;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public C0162a(b bVar, a aVar) {
                super(1);
                this.f6387a = bVar;
                this.f6388b = aVar;
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Object invoke(Object obj) {
                invoke((Throwable) obj);
                return s.f314a;
            }

            public final void invoke(Throwable th) {
                this.f6387a.a(this.f6388b.f6385b);
            }
        }

        /* JADX INFO: renamed from: p1.b$a$b, reason: collision with other inner class name */
        public static final class C0163b extends o implements Function1 {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            public final /* synthetic */ b f6389a;

            /* JADX INFO: renamed from: b, reason: collision with root package name */
            public final /* synthetic */ a f6390b;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public C0163b(b bVar, a aVar) {
                super(1);
                this.f6389a = bVar;
                this.f6390b = aVar;
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Object invoke(Object obj) {
                invoke((Throwable) obj);
                return s.f314a;
            }

            public final void invoke(Throwable th) {
                b.f6382i.set(this.f6389a, this.f6390b.f6385b);
                this.f6389a.a(this.f6390b.f6385b);
            }
        }

        public a(C0379l c0379l, Object obj) {
            this.f6384a = c0379l;
            this.f6385b = obj;
        }

        @Override // g1.InterfaceC0377k
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public void h(s sVar, Function1 function1) {
            b.f6382i.set(b.this, this.f6385b);
            this.f6384a.h(sVar, new C0162a(b.this, this));
        }

        @Override // g1.O0
        public void b(C c2, int i2) {
            this.f6384a.b(c2, i2);
        }

        @Override // g1.InterfaceC0377k
        /* JADX INFO: renamed from: c, reason: merged with bridge method [inline-methods] */
        public void p(B b2, s sVar) {
            this.f6384a.p(b2, sVar);
        }

        @Override // g1.InterfaceC0377k
        /* JADX INFO: renamed from: d, reason: merged with bridge method [inline-methods] */
        public Object m(s sVar, Object obj, Function1 function1) {
            Object objM = this.f6384a.m(sVar, obj, new C0163b(b.this, this));
            if (objM != null) {
                b.f6382i.set(b.this, this.f6385b);
            }
            return objM;
        }

        @Override // g1.InterfaceC0377k
        public void g(Function1 function1) {
            this.f6384a.g(function1);
        }

        @Override // L0.d
        public g getContext() {
            return this.f6384a.getContext();
        }

        @Override // L0.d
        public void resumeWith(Object obj) {
            this.f6384a.resumeWith(obj);
        }

        @Override // g1.InterfaceC0377k
        public void t(Object obj) {
            this.f6384a.t(obj);
        }
    }

    /* JADX INFO: renamed from: p1.b$b, reason: collision with other inner class name */
    public static final class C0164b extends o implements Function3 {

        /* JADX INFO: renamed from: p1.b$b$a */
        public static final class a extends o implements Function1 {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            public final /* synthetic */ b f6392a;

            /* JADX INFO: renamed from: b, reason: collision with root package name */
            public final /* synthetic */ Object f6393b;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public a(b bVar, Object obj) {
                super(1);
                this.f6392a = bVar;
                this.f6393b = obj;
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Object invoke(Object obj) {
                invoke((Throwable) obj);
                return s.f314a;
            }

            public final void invoke(Throwable th) {
                this.f6392a.a(this.f6393b);
            }
        }

        public C0164b() {
            super(3);
        }

        public final Function1 b(o1.a aVar, Object obj, Object obj2) {
            return new a(b.this, obj);
        }

        @Override // kotlin.jvm.functions.Function3
        public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2, Object obj3) {
            android.support.v4.media.a.a(obj);
            return b(null, obj2, obj3);
        }
    }

    public b(boolean z2) {
        super(1, z2 ? 1 : 0);
        this.owner = z2 ? null : c.f6394a;
        this.f6383h = new C0164b();
    }

    public static /* synthetic */ Object o(b bVar, Object obj, L0.d dVar) {
        Object objP;
        return (!bVar.q(obj) && (objP = bVar.p(obj, dVar)) == M0.c.c()) ? objP : s.f314a;
    }

    @Override // p1.a
    public void a(Object obj) {
        while (n()) {
            AtomicReferenceFieldUpdater atomicReferenceFieldUpdater = f6382i;
            Object obj2 = atomicReferenceFieldUpdater.get(this);
            if (obj2 != c.f6394a) {
                if (obj2 != obj && obj != null) {
                    throw new IllegalStateException(("This mutex is locked by " + obj2 + ", but " + obj + " is expected").toString());
                }
                if (atomicReferenceFieldUpdater.compareAndSet(this, obj2, c.f6394a)) {
                    h();
                    return;
                }
            }
        }
        throw new IllegalStateException("This mutex is not locked");
    }

    @Override // p1.a
    public Object b(Object obj, L0.d dVar) {
        return o(this, obj, dVar);
    }

    public final int m(Object obj) {
        while (n()) {
            Object obj2 = f6382i.get(this);
            if (obj2 != c.f6394a) {
                return obj2 == obj ? 1 : 2;
            }
        }
        return 0;
    }

    public boolean n() {
        return g() == 0;
    }

    public final Object p(Object obj, L0.d dVar) {
        C0379l c0379lB = AbstractC0383n.b(M0.b.b(dVar));
        try {
            c(new a(c0379lB, obj));
            Object objX = c0379lB.x();
            if (objX == M0.c.c()) {
                h.c(dVar);
            }
            return objX == M0.c.c() ? objX : s.f314a;
        } catch (Throwable th) {
            c0379lB.J();
            throw th;
        }
    }

    public boolean q(Object obj) {
        int iR = r(obj);
        if (iR == 0) {
            return true;
        }
        if (iR == 1) {
            return false;
        }
        if (iR != 2) {
            throw new IllegalStateException("unexpected");
        }
        throw new IllegalStateException(("This mutex is already locked by the specified owner: " + obj).toString());
    }

    public final int r(Object obj) {
        while (!i()) {
            if (obj == null) {
                return 1;
            }
            int iM = m(obj);
            if (iM == 1) {
                return 2;
            }
            if (iM == 2) {
                return 1;
            }
        }
        f6382i.set(this, obj);
        return 0;
    }

    public String toString() {
        return "Mutex@" + I.b(this) + "[isLocked=" + n() + ",owner=" + f6382i.get(this) + ']';
    }
}
