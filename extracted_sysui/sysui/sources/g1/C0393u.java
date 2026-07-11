package g1;

import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* JADX INFO: renamed from: g1.u, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes2.dex */
public final class C0393u {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Object f4451a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final AbstractC0373i f4452b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final Function1 f4453c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final Object f4454d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final Throwable f4455e;

    public C0393u(Object obj, AbstractC0373i abstractC0373i, Function1 function1, Object obj2, Throwable th) {
        this.f4451a = obj;
        this.f4452b = abstractC0373i;
        this.f4453c = function1;
        this.f4454d = obj2;
        this.f4455e = th;
    }

    public static /* synthetic */ C0393u b(C0393u c0393u, Object obj, AbstractC0373i abstractC0373i, Function1 function1, Object obj2, Throwable th, int i2, Object obj3) {
        if ((i2 & 1) != 0) {
            obj = c0393u.f4451a;
        }
        if ((i2 & 2) != 0) {
            abstractC0373i = c0393u.f4452b;
        }
        AbstractC0373i abstractC0373i2 = abstractC0373i;
        if ((i2 & 4) != 0) {
            function1 = c0393u.f4453c;
        }
        Function1 function12 = function1;
        if ((i2 & 8) != 0) {
            obj2 = c0393u.f4454d;
        }
        Object obj4 = obj2;
        if ((i2 & 16) != 0) {
            th = c0393u.f4455e;
        }
        return c0393u.a(obj, abstractC0373i2, function12, obj4, th);
    }

    public final C0393u a(Object obj, AbstractC0373i abstractC0373i, Function1 function1, Object obj2, Throwable th) {
        return new C0393u(obj, abstractC0373i, function1, obj2, th);
    }

    public final boolean c() {
        return this.f4455e != null;
    }

    public final void d(C0379l c0379l, Throwable th) {
        AbstractC0373i abstractC0373i = this.f4452b;
        if (abstractC0373i != null) {
            c0379l.k(abstractC0373i, th);
        }
        Function1 function1 = this.f4453c;
        if (function1 != null) {
            c0379l.l(function1, th);
        }
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof C0393u)) {
            return false;
        }
        C0393u c0393u = (C0393u) obj;
        return kotlin.jvm.internal.n.c(this.f4451a, c0393u.f4451a) && kotlin.jvm.internal.n.c(this.f4452b, c0393u.f4452b) && kotlin.jvm.internal.n.c(this.f4453c, c0393u.f4453c) && kotlin.jvm.internal.n.c(this.f4454d, c0393u.f4454d) && kotlin.jvm.internal.n.c(this.f4455e, c0393u.f4455e);
    }

    public int hashCode() {
        Object obj = this.f4451a;
        int iHashCode = (obj == null ? 0 : obj.hashCode()) * 31;
        AbstractC0373i abstractC0373i = this.f4452b;
        int iHashCode2 = (iHashCode + (abstractC0373i == null ? 0 : abstractC0373i.hashCode())) * 31;
        Function1 function1 = this.f4453c;
        int iHashCode3 = (iHashCode2 + (function1 == null ? 0 : function1.hashCode())) * 31;
        Object obj2 = this.f4454d;
        int iHashCode4 = (iHashCode3 + (obj2 == null ? 0 : obj2.hashCode())) * 31;
        Throwable th = this.f4455e;
        return iHashCode4 + (th != null ? th.hashCode() : 0);
    }

    public String toString() {
        return "CompletedContinuation(result=" + this.f4451a + ", cancelHandler=" + this.f4452b + ", onCancellation=" + this.f4453c + ", idempotentResume=" + this.f4454d + ", cancelCause=" + this.f4455e + ')';
    }

    public /* synthetic */ C0393u(Object obj, AbstractC0373i abstractC0373i, Function1 function1, Object obj2, Throwable th, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this(obj, (i2 & 2) != 0 ? null : abstractC0373i, (i2 & 4) != 0 ? null : function1, (i2 & 8) != 0 ? null : obj2, (i2 & 16) != 0 ? null : th);
    }
}
