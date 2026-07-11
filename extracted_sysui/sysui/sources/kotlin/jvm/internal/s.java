package kotlin.jvm.internal;

/* JADX INFO: loaded from: classes2.dex */
public final class s implements e {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Class f5054a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final String f5055b;

    public s(Class jClass, String moduleName) {
        n.g(jClass, "jClass");
        n.g(moduleName, "moduleName");
        this.f5054a = jClass;
        this.f5055b = moduleName;
    }

    @Override // kotlin.jvm.internal.e
    public Class b() {
        return this.f5054a;
    }

    public boolean equals(Object obj) {
        return (obj instanceof s) && n.c(b(), ((s) obj).b());
    }

    public int hashCode() {
        return b().hashCode();
    }

    public String toString() {
        return b().toString() + " (Kotlin reflection is not available)";
    }
}
