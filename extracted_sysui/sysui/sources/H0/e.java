package H0;

import kotlin.jvm.functions.Function0;

/* JADX INFO: loaded from: classes2.dex */
public abstract class e {

    public /* synthetic */ class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final /* synthetic */ int[] f291a;

        static {
            int[] iArr = new int[f.values().length];
            try {
                iArr[f.f292a.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[f.f293b.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                iArr[f.f294c.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            f291a = iArr;
        }
    }

    public static d a(f mode, Function0 initializer) {
        kotlin.jvm.internal.n.g(mode, "mode");
        kotlin.jvm.internal.n.g(initializer, "initializer");
        int i2 = a.f291a[mode.ordinal()];
        if (i2 == 1) {
            return new m(initializer, null, 2, null);
        }
        if (i2 == 2) {
            return new l(initializer);
        }
        if (i2 == 3) {
            return new t(initializer);
        }
        throw new g();
    }

    public static d b(Function0 initializer) {
        kotlin.jvm.internal.n.g(initializer, "initializer");
        return new m(initializer, null, 2, null);
    }
}
