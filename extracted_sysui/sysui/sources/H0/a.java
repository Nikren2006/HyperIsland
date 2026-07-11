package H0;

import java.lang.reflect.InvocationTargetException;

/* JADX INFO: loaded from: classes2.dex */
public abstract class a {
    public static void a(Throwable th, Throwable exception) throws IllegalAccessException, InvocationTargetException {
        kotlin.jvm.internal.n.g(th, "<this>");
        kotlin.jvm.internal.n.g(exception, "exception");
        if (th != exception) {
            P0.b.f586a.a(th, exception);
        }
    }
}
