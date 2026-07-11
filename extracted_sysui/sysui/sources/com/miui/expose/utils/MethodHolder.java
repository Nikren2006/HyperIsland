package com.miui.expose.utils;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/* JADX INFO: loaded from: classes2.dex */
public class MethodHolder {
    private volatile boolean initialized;
    private volatile Method method;
    private String name;
    private Class<?>[] parameterTypes;
    private Class<?> type;

    public MethodHolder(Class<?> cls, String str, Class<?>... clsArr) {
        this.type = cls;
        this.name = str;
        this.parameterTypes = clsArr;
    }

    private String buildExceptionMessage() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.name);
        sb.append("(");
        Class<?>[] clsArr = this.parameterTypes;
        if (clsArr.length > 0) {
            sb.append(clsArr[0].getName());
            for (int i2 = 1; i2 < clsArr.length; i2++) {
                sb.append(", ");
                sb.append(clsArr[i2].getName());
            }
        }
        sb.append(")");
        sb.append(" is not found in ");
        sb.append(this.type.getName());
        return sb.toString();
    }

    public String getName() {
        return this.name;
    }

    public Object invoke(Object obj, Object... objArr) {
        if (!this.initialized) {
            this.initialized = true;
            try {
                this.method = this.type.getDeclaredMethod(this.name, this.parameterTypes);
                this.method.setAccessible(true);
            } catch (NoSuchMethodException e2) {
                throw new ReflectiveOperationError(e2);
            }
        } else if (this.method == null) {
            throw new ReflectiveOperationError(new NoSuchMethodException(buildExceptionMessage()));
        }
        try {
            return this.method.invoke(obj, objArr);
        } catch (IllegalAccessException | InvocationTargetException e3) {
            throw new ReflectiveOperationError(e3);
        }
    }
}
