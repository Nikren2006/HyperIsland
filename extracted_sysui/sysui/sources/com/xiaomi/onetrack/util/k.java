package com.xiaomi.onetrack.util;

import java.lang.reflect.Method;

/* JADX INFO: loaded from: classes2.dex */
public class k {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static Method f3517a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private static Method f3518b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private static Method f3519c;

    static {
        try {
            Class<?> cls = Class.forName("miui.util.FeatureParser");
            f3517a = cls.getMethod("getString", String.class);
            f3518b = cls.getMethod("getBoolean", String.class);
            f3519c = cls.getMethod("getInteger", String.class);
        } catch (Throwable unused) {
        }
    }

    public static String a(String str) {
        Method method = f3517a;
        if (method == null) {
            return "";
        }
        try {
            return String.valueOf(method.invoke(null, str));
        } catch (Throwable unused) {
            return "";
        }
    }

    public static boolean a(String str, boolean z2) {
        Method method = f3518b;
        if (method == null) {
            return z2;
        }
        try {
            return ((Boolean) method.invoke(null, str, Boolean.valueOf(z2))).booleanValue();
        } catch (Throwable unused) {
            return z2;
        }
    }

    public static int a(String str, int i2) {
        Method method = f3519c;
        if (method == null) {
            return i2;
        }
        try {
            return ((Integer) method.invoke(null, str, Integer.valueOf(i2))).intValue();
        } catch (Throwable unused) {
            return i2;
        }
    }
}
