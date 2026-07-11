package g1;

/* JADX INFO: loaded from: classes2.dex */
public abstract class K {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final boolean f4381a = l1.G.f("kotlinx.coroutines.main.delay", false);

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final L f4382b = b();

    public static final L a() {
        return f4382b;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static final L b() {
        if (!f4381a) {
            return J.f4376g;
        }
        w0 w0VarC = Q.c();
        return (l1.v.c(w0VarC) || !(w0VarC instanceof L)) ? J.f4376g : (L) w0VarC;
    }
}
