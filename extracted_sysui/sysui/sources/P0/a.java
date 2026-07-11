package P0;

import I0.AbstractC0181i;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import kotlin.jvm.internal.n;

/* JADX INFO: loaded from: classes2.dex */
public abstract class a {

    /* JADX INFO: renamed from: P0.a$a, reason: collision with other inner class name */
    public static final class C0017a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final C0017a f583a = new C0017a();

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public static final Method f584b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public static final Method f585c;

        static {
            Method method;
            Method method2;
            Method[] methods = Throwable.class.getMethods();
            n.d(methods);
            int length = methods.length;
            int i2 = 0;
            int i3 = 0;
            while (true) {
                method = null;
                if (i3 >= length) {
                    method2 = null;
                    break;
                }
                method2 = methods[i3];
                if (n.c(method2.getName(), "addSuppressed")) {
                    Class<?>[] parameterTypes = method2.getParameterTypes();
                    n.f(parameterTypes, "getParameterTypes(...)");
                    if (n.c(AbstractC0181i.L(parameterTypes), Throwable.class)) {
                        break;
                    }
                }
                i3++;
            }
            f584b = method2;
            int length2 = methods.length;
            while (true) {
                if (i2 >= length2) {
                    break;
                }
                Method method3 = methods[i2];
                if (n.c(method3.getName(), "getSuppressed")) {
                    method = method3;
                    break;
                }
                i2++;
            }
            f585c = method;
        }
    }

    public void a(Throwable cause, Throwable exception) throws IllegalAccessException, InvocationTargetException {
        n.g(cause, "cause");
        n.g(exception, "exception");
        Method method = C0017a.f584b;
        if (method != null) {
            method.invoke(cause, exception);
        }
    }

    public a1.c b() {
        return new a1.b();
    }
}
