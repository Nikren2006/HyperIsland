package kotlin.jvm.internal;

import java.util.Arrays;

/* JADX INFO: loaded from: classes2.dex */
public abstract class n {
    public static boolean a(float f2, Float f3) {
        return f3 != null && f2 == f3.floatValue();
    }

    public static boolean b(Float f2, float f3) {
        return f2 != null && f2.floatValue() == f3;
    }

    public static boolean c(Object obj, Object obj2) {
        return obj == null ? obj2 == null : obj.equals(obj2);
    }

    public static void d(Object obj) {
        if (obj == null) {
            p();
        }
    }

    public static void e(Object obj, String str) {
        if (obj == null) {
            q(str);
        }
    }

    public static void f(Object obj, String str) {
        if (obj != null) {
            return;
        }
        throw ((NullPointerException) m(new NullPointerException(str + " must not be null")));
    }

    public static void g(Object obj, String str) {
        if (obj == null) {
            s(str);
        }
    }

    public static void h(Object obj, String str) {
        if (obj == null) {
            r(str);
        }
    }

    public static int i(int i2, int i3) {
        if (i2 < i3) {
            return -1;
        }
        return i2 == i3 ? 0 : 1;
    }

    public static int j(long j2, long j3) {
        if (j2 < j3) {
            return -1;
        }
        return j2 == j3 ? 0 : 1;
    }

    public static String k(String str) {
        StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
        String name = n.class.getName();
        int i2 = 0;
        while (!stackTrace[i2].getClassName().equals(name)) {
            i2++;
        }
        while (stackTrace[i2].getClassName().equals(name)) {
            i2++;
        }
        StackTraceElement stackTraceElement = stackTrace[i2];
        return "Parameter specified as non-null is null: method " + stackTraceElement.getClassName() + "." + stackTraceElement.getMethodName() + ", parameter " + str;
    }

    public static void l(int i2, String str) {
        t();
    }

    public static Throwable m(Throwable th) {
        return n(th, n.class.getName());
    }

    public static Throwable n(Throwable th, String str) {
        StackTraceElement[] stackTrace = th.getStackTrace();
        int length = stackTrace.length;
        int i2 = -1;
        for (int i3 = 0; i3 < length; i3++) {
            if (str.equals(stackTrace[i3].getClassName())) {
                i2 = i3;
            }
        }
        th.setStackTrace((StackTraceElement[]) Arrays.copyOfRange(stackTrace, i2 + 1, length));
        return th;
    }

    public static String o(String str, Object obj) {
        return str + obj;
    }

    public static void p() {
        throw ((NullPointerException) m(new NullPointerException()));
    }

    public static void q(String str) {
        throw ((NullPointerException) m(new NullPointerException(str)));
    }

    public static void r(String str) {
        throw ((IllegalArgumentException) m(new IllegalArgumentException(k(str))));
    }

    public static void s(String str) {
        throw ((NullPointerException) m(new NullPointerException(k(str))));
    }

    public static void t() {
        u("This function has a reified type parameter and thus can only be inlined at compilation time, not called directly.");
    }

    public static void u(String str) {
        throw new UnsupportedOperationException(str);
    }

    public static void v(String str) {
        throw ((H0.r) m(new H0.r(str)));
    }

    public static void w(String str) {
        v("lateinit property " + str + " has not been initialized");
    }
}
