package d1;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* JADX INFO: renamed from: d1.k, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes2.dex */
public final class EnumC0332k {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final EnumC0332k f3994a = new EnumC0332k("PUBLIC", 0);

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final EnumC0332k f3995b = new EnumC0332k("PROTECTED", 1);

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final EnumC0332k f3996c = new EnumC0332k("INTERNAL", 2);

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static final EnumC0332k f3997d = new EnumC0332k("PRIVATE", 3);

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static final /* synthetic */ EnumC0332k[] f3998e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public static final /* synthetic */ O0.a f3999f;

    static {
        EnumC0332k[] enumC0332kArrA = a();
        f3998e = enumC0332kArrA;
        f3999f = O0.b.a(enumC0332kArrA);
    }

    public EnumC0332k(String str, int i2) {
    }

    public static final /* synthetic */ EnumC0332k[] a() {
        return new EnumC0332k[]{f3994a, f3995b, f3996c, f3997d};
    }

    public static EnumC0332k valueOf(String str) {
        return (EnumC0332k) Enum.valueOf(EnumC0332k.class, str);
    }

    public static EnumC0332k[] values() {
        return (EnumC0332k[]) f3998e.clone();
    }
}
