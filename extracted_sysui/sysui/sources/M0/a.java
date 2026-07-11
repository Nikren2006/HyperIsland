package M0;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* JADX INFO: loaded from: classes2.dex */
public final class a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final a f418a = new a("COROUTINE_SUSPENDED", 0);

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final a f419b = new a("UNDECIDED", 1);

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final a f420c = new a("RESUMED", 2);

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static final /* synthetic */ a[] f421d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static final /* synthetic */ O0.a f422e;

    static {
        a[] aVarArrA = a();
        f421d = aVarArrA;
        f422e = O0.b.a(aVarArrA);
    }

    public a(String str, int i2) {
    }

    public static final /* synthetic */ a[] a() {
        return new a[]{f418a, f419b, f420c};
    }

    public static a valueOf(String str) {
        return (a) Enum.valueOf(a.class, str);
    }

    public static a[] values() {
        return (a[]) f421d.clone();
    }
}
