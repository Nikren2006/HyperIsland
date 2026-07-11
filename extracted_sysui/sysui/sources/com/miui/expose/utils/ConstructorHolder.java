package com.miui.expose.utils;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/* JADX INFO: loaded from: classes2.dex */
public class ConstructorHolder<T> {
    private volatile Constructor<T> constructor;
    private volatile boolean initialized;
    private Class<?>[] parameterTypes;
    private Class<T> type;

    public ConstructorHolder(Class<T> cls, Class<?>... clsArr) {
        this.type = cls;
        this.parameterTypes = clsArr;
    }

    private String buildExceptionMessage() {
        StringBuilder sb = new StringBuilder();
        sb.append("constructor(");
        Class<?>[] clsArr = this.parameterTypes;
        if (clsArr != null && clsArr.length > 0) {
            sb.append(clsArr[0].getName());
            for (int i2 = 1; i2 < this.parameterTypes.length; i2++) {
                sb.append(", ");
                sb.append(this.parameterTypes[i2].getName());
            }
        }
        sb.append(")");
        sb.append(" is not found in ");
        sb.append(this.type.getName());
        return sb.toString();
    }

    public T newInstance(Object... objArr) {
        if (!this.initialized) {
            this.initialized = true;
            try {
                this.constructor = this.type.getConstructor(this.parameterTypes);
                this.constructor.setAccessible(true);
            } catch (NoSuchMethodException e2) {
                throw new ReflectiveOperationError(e2);
            }
        } else if (this.constructor == null) {
            throw new ReflectiveOperationError(new NoSuchMethodException(buildExceptionMessage()));
        }
        try {
            return this.constructor.newInstance(objArr);
        } catch (IllegalAccessException | InstantiationException | InvocationTargetException e3) {
            throw new ReflectiveOperationError(e3);
        }
    }
}
