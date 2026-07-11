package H0;

import java.io.Serializable;

/* JADX INFO: loaded from: classes2.dex */
public final class i implements Serializable {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Object f297a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final Object f298b;

    public i(Object obj, Object obj2) {
        this.f297a = obj;
        this.f298b = obj2;
    }

    public final Object a() {
        return this.f297a;
    }

    public final Object b() {
        return this.f298b;
    }

    public final i c(Object obj, Object obj2) {
        return new i(obj, obj2);
    }

    public final Object d() {
        return this.f297a;
    }

    public final Object e() {
        return this.f298b;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof i)) {
            return false;
        }
        i iVar = (i) obj;
        return kotlin.jvm.internal.n.c(this.f297a, iVar.f297a) && kotlin.jvm.internal.n.c(this.f298b, iVar.f298b);
    }

    public int hashCode() {
        Object obj = this.f297a;
        int iHashCode = (obj == null ? 0 : obj.hashCode()) * 31;
        Object obj2 = this.f298b;
        return iHashCode + (obj2 != null ? obj2.hashCode() : 0);
    }

    public String toString() {
        return '(' + this.f297a + ", " + this.f298b + ')';
    }
}
