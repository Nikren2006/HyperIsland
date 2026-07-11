package R0;

import a1.c;
import b1.C0224a;

/* JADX INFO: loaded from: classes2.dex */
public class a extends Q0.a {

    /* JADX INFO: renamed from: R0.a$a, reason: collision with other inner class name */
    public static final class C0019a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final C0019a f695a = new C0019a();

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public static final Integer f696b;

        static {
            Object obj;
            Integer num = null;
            try {
                obj = Class.forName("android.os.Build$VERSION").getField("SDK_INT").get(null);
            } catch (Throwable unused) {
            }
            Integer num2 = obj instanceof Integer ? (Integer) obj : null;
            if (num2 != null && num2.intValue() > 0) {
                num = num2;
            }
            f696b = num;
        }
    }

    @Override // P0.a
    public c b() {
        return c(34) ? new C0224a() : super.b();
    }

    public final boolean c(int i2) {
        Integer num = C0019a.f696b;
        return num == null || num.intValue() >= i2;
    }
}
