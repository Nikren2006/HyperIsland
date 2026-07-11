package F0;

/* JADX INFO: loaded from: classes2.dex */
public final class f implements e, E0.a {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final f f173b = new f(null);

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Object f174a;

    public f(Object obj) {
        this.f174a = obj;
    }

    public static e a(Object obj) {
        return new f(i.c(obj, "instance cannot be null"));
    }

    @Override // G0.a
    public Object get() {
        return this.f174a;
    }
}
