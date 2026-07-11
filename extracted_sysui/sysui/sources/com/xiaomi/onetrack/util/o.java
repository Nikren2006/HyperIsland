package com.xiaomi.onetrack.util;

import android.content.Context;
import java.lang.reflect.Method;

/* JADX INFO: loaded from: classes2.dex */
public class o {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static final String f3528a = "IdentifierManager";

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private static Object f3529b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private static Class<?> f3530c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private static Method f3531d;

    static {
        try {
            Class<?> cls = Class.forName("com.android.id.impl.IdProviderImpl");
            f3530c = cls;
            f3529b = cls.newInstance();
            f3531d = f3530c.getMethod("getOAID", Context.class);
        } catch (Exception e2) {
            q.a(f3528a, "reflect exception!", e2);
        }
    }

    public static boolean a() {
        return (f3530c == null || f3529b == null) ? false : true;
    }

    public static String a(Context context) {
        return a(context, f3531d);
    }

    private static String a(Context context, Method method) {
        Object obj;
        if (context != null && (obj = f3529b) != null && method != null) {
            try {
                Object objInvoke = method.invoke(obj, context);
                if (objInvoke != null) {
                    return (String) objInvoke;
                }
                return "";
            } catch (Throwable th) {
                q.a(f3528a, "oaid invoke exception!", th);
                return "";
            }
        }
        return "";
    }
}
