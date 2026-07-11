package u1;

import java.util.List;
import kotlin.jvm.internal.DefaultConstructorMarker;
import s1.c;
import s1.f;

/* JADX INFO: loaded from: classes2.dex */
public abstract class k implements s1.c {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final String f6871a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final s1.c f6872b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final s1.c f6873c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final int f6874d;

    public /* synthetic */ k(String str, s1.c cVar, s1.c cVar2, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, cVar, cVar2);
    }

    @Override // s1.c
    public boolean a() {
        return c.a.b(this);
    }

    @Override // s1.c
    public int b(String name) {
        kotlin.jvm.internal.n.g(name, "name");
        Integer numF = f1.m.f(name);
        if (numF != null) {
            return numF.intValue();
        }
        throw new IllegalArgumentException(name + " is not a valid map index");
    }

    @Override // s1.c
    public s1.e c() {
        return f.c.f6482a;
    }

    @Override // s1.c
    public int d() {
        return this.f6874d;
    }

    @Override // s1.c
    public String e(int i2) {
        return String.valueOf(i2);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof k)) {
            return false;
        }
        k kVar = (k) obj;
        return kotlin.jvm.internal.n.c(h(), kVar.h()) && kotlin.jvm.internal.n.c(this.f6872b, kVar.f6872b) && kotlin.jvm.internal.n.c(this.f6873c, kVar.f6873c);
    }

    @Override // s1.c
    public List f(int i2) {
        if (i2 >= 0) {
            return I0.m.h();
        }
        throw new IllegalArgumentException(("Illegal index " + i2 + ", " + h() + " expects only non-negative indices").toString());
    }

    @Override // s1.c
    public s1.c g(int i2) {
        if (i2 >= 0) {
            int i3 = i2 % 2;
            if (i3 == 0) {
                return this.f6872b;
            }
            if (i3 == 1) {
                return this.f6873c;
            }
            throw new IllegalStateException("Unreached");
        }
        throw new IllegalArgumentException(("Illegal index " + i2 + ", " + h() + " expects only non-negative indices").toString());
    }

    @Override // s1.c
    public String h() {
        return this.f6871a;
    }

    public int hashCode() {
        return (((h().hashCode() * 31) + this.f6872b.hashCode()) * 31) + this.f6873c.hashCode();
    }

    @Override // s1.c
    public boolean i(int i2) {
        if (i2 >= 0) {
            return false;
        }
        throw new IllegalArgumentException(("Illegal index " + i2 + ", " + h() + " expects only non-negative indices").toString());
    }

    @Override // s1.c
    public boolean isInline() {
        return c.a.a(this);
    }

    public String toString() {
        return h() + '(' + this.f6872b + ", " + this.f6873c + ')';
    }

    public k(String str, s1.c cVar, s1.c cVar2) {
        this.f6871a = str;
        this.f6872b = cVar;
        this.f6873c = cVar2;
        this.f6874d = 2;
    }
}
