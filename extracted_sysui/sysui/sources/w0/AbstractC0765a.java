package w0;

import android.os.Process;
import android.os.UserHandle;
import java.lang.reflect.Method;
import z0.e;

/* JADX INFO: renamed from: w0.a, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes2.dex */
public abstract class AbstractC0765a {
    public static UserHandle a() {
        UserHandle userHandleC = c(b());
        return userHandleC == null ? Process.myUserHandle() : userHandleC;
    }

    public static int b() {
        try {
            Method method = Class.forName("miui.securityspace.CrossUserUtils").getMethod("getCurrentUserId", null);
            method.setAccessible(true);
            return ((Integer) method.invoke(null, null)).intValue();
        } catch (Exception e2) {
            e.b("CrossUserUtils", "getCurrentUserId: ", e2);
            return -1;
        }
    }

    public static UserHandle c(int i2) {
        try {
            Method declaredMethod = UserHandle.class.getDeclaredMethod("of", Integer.TYPE);
            declaredMethod.setAccessible(true);
            return (UserHandle) declaredMethod.invoke(null, Integer.valueOf(i2));
        } catch (Exception unused) {
            return null;
        }
    }
}
