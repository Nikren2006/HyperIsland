package l1;

import java.lang.reflect.Method;
import java.util.concurrent.Executor;
import java.util.concurrent.ScheduledThreadPoolExecutor;

/* JADX INFO: renamed from: l1.c, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes2.dex */
public abstract class AbstractC0448c {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final Method f5214a;

    static {
        Method method;
        try {
            method = ScheduledThreadPoolExecutor.class.getMethod("setRemoveOnCancelPolicy", Boolean.TYPE);
        } catch (Throwable unused) {
            method = null;
        }
        f5214a = method;
    }

    public static final boolean a(Executor executor) {
        Method method;
        try {
            ScheduledThreadPoolExecutor scheduledThreadPoolExecutor = executor instanceof ScheduledThreadPoolExecutor ? (ScheduledThreadPoolExecutor) executor : null;
            if (scheduledThreadPoolExecutor == null || (method = f5214a) == null) {
                return false;
            }
            method.invoke(scheduledThreadPoolExecutor, Boolean.TRUE);
            return true;
        } catch (Throwable unused) {
            return false;
        }
    }
}
