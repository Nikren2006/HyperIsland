package H0;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* JADX INFO: loaded from: classes2.dex */
public final class f {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final f f292a = new f("SYNCHRONIZED", 0);

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final f f293b = new f("PUBLICATION", 1);

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final f f294c = new f("NONE", 2);

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static final /* synthetic */ f[] f295d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static final /* synthetic */ O0.a f296e;

    static {
        f[] fVarArrA = a();
        f295d = fVarArrA;
        f296e = O0.b.a(fVarArrA);
    }

    public f(String str, int i2) {
    }

    public static final /* synthetic */ f[] a() {
        return new f[]{f292a, f293b, f294c};
    }

    public static f valueOf(String str) {
        return (f) Enum.valueOf(f.class, str);
    }

    public static f[] values() {
        return (f[]) f295d.clone();
    }
}
