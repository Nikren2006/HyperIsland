package T0;

/* JADX INFO: loaded from: classes2.dex */
public abstract class a {
    public static final void a(AutoCloseable autoCloseable, Throwable th) throws Exception {
        if (autoCloseable != null) {
            if (th == null) {
                autoCloseable.close();
                return;
            }
            try {
                autoCloseable.close();
            } catch (Throwable th2) {
                H0.a.a(th, th2);
            }
        }
    }
}
