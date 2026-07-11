package androidx.core.os;

import android.os.UserHandle;
import androidx.annotation.RequiresApi;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

/* JADX INFO: loaded from: classes.dex */
public class UserHandleCompat {
    private static Method sGetUserIdMethod;
    private static Constructor<UserHandle> sUserHandleConstructor;

    @RequiresApi(24)
    public static class Api24Impl {
        private Api24Impl() {
        }

        public static UserHandle getUserHandleForUid(int i2) {
            return UserHandle.getUserHandleForUid(i2);
        }
    }

    private UserHandleCompat() {
    }

    private static Method getGetUserIdMethod() throws NoSuchMethodException {
        if (sGetUserIdMethod == null) {
            Method declaredMethod = UserHandle.class.getDeclaredMethod("getUserId", Integer.TYPE);
            sGetUserIdMethod = declaredMethod;
            declaredMethod.setAccessible(true);
        }
        return sGetUserIdMethod;
    }

    private static Constructor<UserHandle> getUserHandleConstructor() throws NoSuchMethodException {
        if (sUserHandleConstructor == null) {
            Constructor<UserHandle> declaredConstructor = UserHandle.class.getDeclaredConstructor(Integer.TYPE);
            sUserHandleConstructor = declaredConstructor;
            declaredConstructor.setAccessible(true);
        }
        return sUserHandleConstructor;
    }

    public static UserHandle getUserHandleForUid(int i2) {
        return Api24Impl.getUserHandleForUid(i2);
    }
}
