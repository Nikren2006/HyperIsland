package U;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* JADX INFO: loaded from: classes2.dex */
public abstract class m {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final m f746a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final m f747b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final /* synthetic */ m[] f748c;

    public enum a extends m {
        public a(String str, int i2) {
            super(str, i2, null);
        }
    }

    static {
        a aVar = new a("DEFAULT", 0);
        f746a = aVar;
        m mVar = new m("STRING", 1) { // from class: U.m.b
            {
                a aVar2 = null;
            }
        };
        f747b = mVar;
        f748c = new m[]{aVar, mVar};
    }

    public m(String str, int i2) {
    }

    public static m valueOf(String str) {
        return (m) Enum.valueOf(m.class, str);
    }

    public static m[] values() {
        return (m[]) f748c.clone();
    }

    public /* synthetic */ m(String str, int i2, a aVar) {
        this(str, i2);
    }
}
