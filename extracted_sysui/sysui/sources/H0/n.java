package H0;

import java.io.Serializable;

/* JADX INFO: loaded from: classes2.dex */
public final class n implements Serializable {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Object f309a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final Object f310b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final Object f311c;

    public n(Object obj, Object obj2, Object obj3) {
        this.f309a = obj;
        this.f310b = obj2;
        this.f311c = obj3;
    }

    public final Object a() {
        return this.f309a;
    }

    public final Object b() {
        return this.f310b;
    }

    public final Object c() {
        return this.f311c;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof n)) {
            return false;
        }
        n nVar = (n) obj;
        return kotlin.jvm.internal.n.c(this.f309a, nVar.f309a) && kotlin.jvm.internal.n.c(this.f310b, nVar.f310b) && kotlin.jvm.internal.n.c(this.f311c, nVar.f311c);
    }

    public int hashCode() {
        Object obj = this.f309a;
        int iHashCode = (obj == null ? 0 : obj.hashCode()) * 31;
        Object obj2 = this.f310b;
        int iHashCode2 = (iHashCode + (obj2 == null ? 0 : obj2.hashCode())) * 31;
        Object obj3 = this.f311c;
        return iHashCode2 + (obj3 != null ? obj3.hashCode() : 0);
    }

    public String toString() {
        return '(' + this.f309a + ", " + this.f310b + ", " + this.f311c + ')';
    }
}
