package i;

import androidx.core.util.Pair;

/* JADX INFO: loaded from: classes.dex */
public class i {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public Object f4532a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public Object f4533b;

    public static boolean a(Object obj, Object obj2) {
        return obj == obj2 || (obj != null && obj.equals(obj2));
    }

    public void b(Object obj, Object obj2) {
        this.f4532a = obj;
        this.f4533b = obj2;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof Pair)) {
            return false;
        }
        Pair pair = (Pair) obj;
        return a(pair.first, this.f4532a) && a(pair.second, this.f4533b);
    }

    public int hashCode() {
        Object obj = this.f4532a;
        int iHashCode = obj == null ? 0 : obj.hashCode();
        Object obj2 = this.f4533b;
        return iHashCode ^ (obj2 != null ? obj2.hashCode() : 0);
    }

    public String toString() {
        return "Pair{" + this.f4532a + " " + this.f4533b + "}";
    }
}
