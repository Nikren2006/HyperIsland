package com.miui.blur.sdk.backdrop;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/* JADX INFO: loaded from: classes2.dex */
public abstract class m {
    public static Method a(Class cls, String str, Class... clsArr) {
        try {
            return cls.getDeclaredMethod(str, clsArr);
        } catch (NoSuchMethodException e2) {
            e2.printStackTrace();
            return null;
        }
    }

    public static Object b(Object obj, Method method, Object... objArr) {
        if (method == null) {
            return null;
        }
        try {
            return method.invoke(obj, objArr);
        } catch (IllegalAccessException | InvocationTargetException e2) {
            e2.printStackTrace();
            return null;
        }
    }
}
