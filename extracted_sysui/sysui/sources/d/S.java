package d;

/* JADX INFO: loaded from: classes.dex */
public enum S {
    AUTOMATIC,
    HARDWARE,
    SOFTWARE;

    public static /* synthetic */ class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final /* synthetic */ int[] f3894a;

        static {
            int[] iArr = new int[S.values().length];
            f3894a = iArr;
            try {
                iArr[S.HARDWARE.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f3894a[S.SOFTWARE.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f3894a[S.AUTOMATIC.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
        }
    }

    public boolean a(int i2, boolean z2, int i3) {
        int i4 = a.f3894a[ordinal()];
        if (i4 == 1) {
            return false;
        }
        if (i4 != 2) {
            return (z2 && i2 < 28) || i3 > 4 || i2 <= 25;
        }
        return true;
    }
}
