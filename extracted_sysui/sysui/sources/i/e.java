package i;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class e {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final e f4524c = new e("COMPOSITION");

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final List f4525a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public f f4526b;

    public e(String... strArr) {
        this.f4525a = Arrays.asList(strArr);
    }

    public e a(String str) {
        e eVar = new e(this);
        eVar.f4525a.add(str);
        return eVar;
    }

    public final boolean b() {
        return ((String) this.f4525a.get(r1.size() - 1)).equals("**");
    }

    public boolean c(String str, int i2) {
        if (i2 >= this.f4525a.size()) {
            return false;
        }
        boolean z2 = i2 == this.f4525a.size() - 1;
        String str2 = (String) this.f4525a.get(i2);
        if (!str2.equals("**")) {
            return (z2 || (i2 == this.f4525a.size() + (-2) && b())) && (str2.equals(str) || str2.equals("*"));
        }
        if (!z2 && ((String) this.f4525a.get(i2 + 1)).equals(str)) {
            return i2 == this.f4525a.size() + (-2) || (i2 == this.f4525a.size() + (-3) && b());
        }
        if (z2) {
            return true;
        }
        int i3 = i2 + 1;
        if (i3 < this.f4525a.size() - 1) {
            return false;
        }
        return ((String) this.f4525a.get(i3)).equals(str);
    }

    public f d() {
        return this.f4526b;
    }

    public int e(String str, int i2) {
        if (f(str)) {
            return 0;
        }
        if (((String) this.f4525a.get(i2)).equals("**")) {
            return (i2 != this.f4525a.size() - 1 && ((String) this.f4525a.get(i2 + 1)).equals(str)) ? 2 : 0;
        }
        return 1;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        e eVar = (e) obj;
        if (!this.f4525a.equals(eVar.f4525a)) {
            return false;
        }
        f fVar = this.f4526b;
        return fVar != null ? fVar.equals(eVar.f4526b) : eVar.f4526b == null;
    }

    public final boolean f(String str) {
        return "__container".equals(str);
    }

    public boolean g(String str, int i2) {
        if (f(str)) {
            return true;
        }
        if (i2 >= this.f4525a.size()) {
            return false;
        }
        return ((String) this.f4525a.get(i2)).equals(str) || ((String) this.f4525a.get(i2)).equals("**") || ((String) this.f4525a.get(i2)).equals("*");
    }

    public boolean h(String str, int i2) {
        return "__container".equals(str) || i2 < this.f4525a.size() - 1 || ((String) this.f4525a.get(i2)).equals("**");
    }

    public int hashCode() {
        int iHashCode = this.f4525a.hashCode() * 31;
        f fVar = this.f4526b;
        return iHashCode + (fVar != null ? fVar.hashCode() : 0);
    }

    public e i(f fVar) {
        e eVar = new e(this);
        eVar.f4526b = fVar;
        return eVar;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("KeyPath{keys=");
        sb.append(this.f4525a);
        sb.append(",resolved=");
        sb.append(this.f4526b != null);
        sb.append('}');
        return sb.toString();
    }

    public e(e eVar) {
        this.f4525a = new ArrayList(eVar.f4525a);
        this.f4526b = eVar.f4526b;
    }
}
