package com.android.systemui;

/* JADX INFO: loaded from: classes.dex */
public class SimpleReflectionUtils {
    public static <T> T callSimpleMethod(Object obj, String str, Object... objArr) {
        return (T) obj.getClass().getMethod(str, getParameterTypes(objArr)).invoke(obj, objArr);
    }

    public static Class<?>[] getParameterTypes(Object... objArr) {
        Class<?>[] clsArr = new Class[objArr.length];
        for (int i2 = 0; i2 < objArr.length; i2++) {
            Object obj = objArr[i2];
            Class<?> cls = obj != null ? obj.getClass() : null;
            clsArr[i2] = cls;
            if (cls == Integer.class) {
                clsArr[i2] = Integer.TYPE;
            }
        }
        return clsArr;
    }
}
