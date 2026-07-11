package d;

import java.util.Arrays;

/* JADX INFO: loaded from: classes.dex */
public final class L {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Object f3813a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final Throwable f3814b;

    public L(Object obj) {
        this.f3813a = obj;
        this.f3814b = null;
    }

    public Throwable a() {
        return this.f3814b;
    }

    public Object b() {
        return this.f3813a;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof L)) {
            return false;
        }
        L l2 = (L) obj;
        if (b() != null && b().equals(l2.b())) {
            return true;
        }
        if (a() == null || l2.a() == null) {
            return false;
        }
        return a().toString().equals(a().toString());
    }

    public int hashCode() {
        return Arrays.hashCode(new Object[]{b(), a()});
    }

    public L(Throwable th) {
        this.f3814b = th;
        this.f3813a = null;
    }
}
