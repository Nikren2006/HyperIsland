package S0;

import java.io.Closeable;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

/* JADX INFO: loaded from: classes2.dex */
public abstract class a {
    public static final void a(Closeable closeable, Throwable th) throws IllegalAccessException, IOException, InvocationTargetException {
        if (closeable != null) {
            if (th == null) {
                closeable.close();
                return;
            }
            try {
                closeable.close();
            } catch (Throwable th2) {
                H0.a.a(th, th2);
            }
        }
    }
}
