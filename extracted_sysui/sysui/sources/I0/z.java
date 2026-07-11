package I0;

/* JADX INFO: loaded from: classes2.dex */
public final class z {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final int f339a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final Object f340b;

    public z(int i2, Object obj) {
        this.f339a = i2;
        this.f340b = obj;
    }

    public final int a() {
        return this.f339a;
    }

    public final Object b() {
        return this.f340b;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof z)) {
            return false;
        }
        z zVar = (z) obj;
        return this.f339a == zVar.f339a && kotlin.jvm.internal.n.c(this.f340b, zVar.f340b);
    }

    public int hashCode() {
        int iHashCode = Integer.hashCode(this.f339a) * 31;
        Object obj = this.f340b;
        return iHashCode + (obj == null ? 0 : obj.hashCode());
    }

    public String toString() {
        return "IndexedValue(index=" + this.f339a + ", value=" + this.f340b + ')';
    }
}
