package u1;

import java.util.List;
import kotlin.jvm.internal.DefaultConstructorMarker;
import s1.c;
import s1.f;

/* JADX INFO: loaded from: classes2.dex */
public abstract class j implements s1.c {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final s1.c f6869a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final int f6870b;

    public /* synthetic */ j(s1.c cVar, DefaultConstructorMarker defaultConstructorMarker) {
        this(cVar);
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
        throw new IllegalArgumentException(name + " is not a valid list index");
    }

    @Override // s1.c
    public s1.e c() {
        return f.b.f6481a;
    }

    @Override // s1.c
    public int d() {
        return this.f6870b;
    }

    @Override // s1.c
    public String e(int i2) {
        return String.valueOf(i2);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof j)) {
            return false;
        }
        j jVar = (j) obj;
        return kotlin.jvm.internal.n.c(this.f6869a, jVar.f6869a) && kotlin.jvm.internal.n.c(h(), jVar.h());
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
            return this.f6869a;
        }
        throw new IllegalArgumentException(("Illegal index " + i2 + ", " + h() + " expects only non-negative indices").toString());
    }

    public int hashCode() {
        return (this.f6869a.hashCode() * 31) + h().hashCode();
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
        return h() + '(' + this.f6869a + ')';
    }

    public j(s1.c cVar) {
        this.f6869a = cVar;
        this.f6870b = 1;
    }
}
