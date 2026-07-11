package f0;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* JADX INFO: renamed from: f0.e, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes2.dex */
public final class EnumC0351e {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final EnumC0351e f4230a = new EnumC0351e("DISABLE_DELAY_FRAME_CALLBACK", 0);

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final EnumC0351e f4231b = new EnumC0351e("DISABLE", 1);

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final EnumC0351e f4232c = new EnumC0351e("ENABLE", 2);

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static final /* synthetic */ EnumC0351e[] f4233d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static final /* synthetic */ O0.a f4234e;

    static {
        EnumC0351e[] enumC0351eArrA = a();
        f4233d = enumC0351eArrA;
        f4234e = O0.b.a(enumC0351eArrA);
    }

    public EnumC0351e(String str, int i2) {
    }

    public static final /* synthetic */ EnumC0351e[] a() {
        return new EnumC0351e[]{f4230a, f4231b, f4232c};
    }

    public static EnumC0351e valueOf(String str) {
        return (EnumC0351e) Enum.valueOf(EnumC0351e.class, str);
    }

    public static EnumC0351e[] values() {
        return (EnumC0351e[]) f4233d.clone();
    }
}
