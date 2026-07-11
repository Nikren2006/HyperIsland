package u1;

import java.util.List;
import s1.c;

/* JADX INFO: loaded from: classes2.dex */
public final class t implements s1.c {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final String f6896a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final s1.b f6897b;

    public t(String serialName, s1.b kind) {
        kotlin.jvm.internal.n.g(serialName, "serialName");
        kotlin.jvm.internal.n.g(kind, "kind");
        this.f6896a = serialName;
        this.f6897b = kind;
    }

    @Override // s1.c
    public boolean a() {
        return c.a.b(this);
    }

    @Override // s1.c
    public int b(String name) {
        kotlin.jvm.internal.n.g(name, "name");
        j();
        throw new H0.c();
    }

    @Override // s1.c
    public int d() {
        return 0;
    }

    @Override // s1.c
    public String e(int i2) {
        j();
        throw new H0.c();
    }

    @Override // s1.c
    public List f(int i2) {
        j();
        throw new H0.c();
    }

    @Override // s1.c
    public s1.c g(int i2) {
        j();
        throw new H0.c();
    }

    @Override // s1.c
    public String h() {
        return this.f6896a;
    }

    @Override // s1.c
    public boolean i(int i2) {
        j();
        throw new H0.c();
    }

    @Override // s1.c
    public boolean isInline() {
        return c.a.a(this);
    }

    public final Void j() {
        throw new IllegalStateException("Primitive descriptor does not have elements");
    }

    @Override // s1.c
    /* JADX INFO: renamed from: k, reason: merged with bridge method [inline-methods] */
    public s1.b c() {
        return this.f6897b;
    }

    public String toString() {
        return "PrimitiveDescriptor(" + h() + ')';
    }
}
