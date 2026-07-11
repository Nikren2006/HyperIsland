package g0;

/* JADX INFO: renamed from: g0.a, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes2.dex */
public final class C0356a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final float f4324a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final float f4325b;

    public C0356a(float f2, float f3) {
        this.f4324a = f2;
        this.f4325b = f3;
    }

    public final float a() {
        return this.f4325b;
    }

    public final float b() {
        return this.f4324a;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof C0356a)) {
            return false;
        }
        C0356a c0356a = (C0356a) obj;
        return Float.compare(this.f4324a, c0356a.f4324a) == 0 && Float.compare(this.f4325b, c0356a.f4325b) == 0;
    }

    public int hashCode() {
        return (Float.hashCode(this.f4324a) * 31) + Float.hashCode(this.f4325b);
    }

    public String toString() {
        return "Gradient(low=" + this.f4324a + ", high=" + this.f4325b + ")";
    }
}
