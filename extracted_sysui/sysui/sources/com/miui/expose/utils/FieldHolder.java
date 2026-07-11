package com.miui.expose.utils;

import java.lang.reflect.Field;

/* JADX INFO: loaded from: classes2.dex */
public class FieldHolder {
    private volatile Field field;
    private Class<?> fieldType;
    private volatile boolean initialized;
    private String name;
    private Class<?> type;

    public FieldHolder(Class<?> cls, String str, Class<?> cls2) {
        this.type = cls;
        this.name = str;
        this.fieldType = cls2;
    }

    private String buildExceptionMessage(Class<?> cls) {
        StringBuilder sb = new StringBuilder();
        sb.append(this.name);
        sb.append("(");
        sb.append(this.fieldType.getName());
        sb.append(") ");
        sb.append("is not found in ");
        sb.append(this.type.getName());
        if (cls != null) {
            sb.append("; ");
            sb.append("do you mean ");
            sb.append(this.name);
            sb.append("(");
            sb.append(cls.getName());
            sb.append(")");
            sb.append("?");
        }
        return sb.toString();
    }

    private void ensureInitialized() {
        if (this.initialized) {
            if (this.field == null) {
                throw new ReflectiveOperationError(new NoSuchFieldException(buildExceptionMessage(null)));
            }
            return;
        }
        this.initialized = true;
        try {
            Field declaredField = this.type.getDeclaredField(this.name);
            if (!declaredField.getType().equals(this.fieldType)) {
                throw new ReflectiveOperationError(new NoSuchFieldException(buildExceptionMessage(declaredField.getType())));
            }
            this.field = declaredField;
            this.field.setAccessible(true);
        } catch (NoSuchFieldException e2) {
            throw new ReflectiveOperationError(e2);
        }
    }

    public Object get(Object obj) {
        ensureInitialized();
        try {
            return this.field.get(obj);
        } catch (IllegalAccessException e2) {
            throw new ReflectiveOperationError(e2);
        }
    }

    public String getName() {
        return this.name;
    }

    public void set(Object obj, Object obj2) {
        ensureInitialized();
        try {
            this.field.set(obj, obj2);
        } catch (IllegalAccessException e2) {
            throw new ReflectiveOperationError(e2);
        }
    }
}
