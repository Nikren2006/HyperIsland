package g1;

import kotlin.jvm.functions.Function2;

/* JADX INFO: loaded from: classes2.dex */
public enum G {
    DEFAULT,
    LAZY,
    ATOMIC,
    UNDISPATCHED;

    public /* synthetic */ class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final /* synthetic */ int[] f4373a;

        static {
            int[] iArr = new int[G.values().length];
            try {
                iArr[G.DEFAULT.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[G.ATOMIC.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                iArr[G.UNDISPATCHED.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                iArr[G.LAZY.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            f4373a = iArr;
        }
    }

    public final void b(Function2 function2, Object obj, L0.d dVar) throws Throwable {
        int i2 = a.f4373a[ordinal()];
        if (i2 == 1) {
            m1.a.d(function2, obj, dVar, null, 4, null);
            return;
        }
        if (i2 == 2) {
            L0.f.a(function2, obj, dVar);
        } else if (i2 == 3) {
            m1.b.a(function2, obj, dVar);
        } else if (i2 != 4) {
            throw new H0.g();
        }
    }

    public final boolean c() {
        return this == LAZY;
    }
}
