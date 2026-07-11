package Q0;

import java.lang.reflect.InvocationTargetException;
import kotlin.jvm.internal.n;

/* JADX INFO: loaded from: classes2.dex */
public abstract class a extends P0.a {

    /* JADX INFO: renamed from: Q0.a$a, reason: collision with other inner class name */
    public static final class C0018a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final C0018a f693a = new C0018a();

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public static final Integer f694b;

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
            f694b = num;
        }
    }

    private final boolean c(int i2) {
        Integer num = C0018a.f694b;
        return num == null || num.intValue() >= i2;
    }

    @Override // P0.a
    public void a(Throwable cause, Throwable exception) throws IllegalAccessException, InvocationTargetException {
        n.g(cause, "cause");
        n.g(exception, "exception");
        if (c(19)) {
            cause.addSuppressed(exception);
        } else {
            super.a(cause, exception);
        }
    }
}
