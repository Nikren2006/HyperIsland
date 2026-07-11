package miui.systemui.util;

import java.lang.reflect.Method;

/* JADX INFO: loaded from: classes4.dex */
public class ReflectUtil {
    public static Object callObjectMethod(Object obj, String str, Class<?>[] clsArr, Object... objArr) throws NoSuchMethodException {
        Method declaredMethod = obj.getClass().getDeclaredMethod(str, clsArr);
        declaredMethod.setAccessible(true);
        return declaredMethod.invoke(obj, objArr);
    }

    public static <T> T callStaticObjectMethod(Class<?> cls, Class<T> cls2, String str, Class<?>[] clsArr, Object... objArr) throws NoSuchMethodException {
        Method declaredMethod = cls.getDeclaredMethod(str, clsArr);
        declaredMethod.setAccessible(true);
        return (T) declaredMethod.invoke(null, objArr);
    }

    public static <T> T callObjectMethod(Object obj, Class<T> cls, String str, Class<?>[] clsArr, Object... objArr) throws NoSuchMethodException {
        Method declaredMethod = obj.getClass().getDeclaredMethod(str, clsArr);
        declaredMethod.setAccessible(true);
        return (T) declaredMethod.invoke(obj, objArr);
    }
}
