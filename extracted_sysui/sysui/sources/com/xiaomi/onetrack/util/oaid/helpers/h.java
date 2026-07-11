package com.xiaomi.onetrack.util.oaid.helpers;

import android.content.Context;
import com.xiaomi.onetrack.util.q;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

/* JADX INFO: loaded from: classes2.dex */
public class h {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private static final String f3590b = "MsaSDKHelper";

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final LinkedBlockingQueue<a> f3591a = new LinkedBlockingQueue<>(1);

    public class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        Object f3592a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        Method f3593b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        Object[] f3594c;

        public a(Object obj, Method method, Object[] objArr) {
            this.f3592a = obj;
            this.f3593b = method;
            this.f3594c = objArr;
        }
    }

    public class b implements InvocationHandler {
        public b() {
        }

        @Override // java.lang.reflect.InvocationHandler
        public Object invoke(Object obj, Method method, Object[] objArr) {
            try {
                h.this.f3591a.offer(h.this.new a(obj, method, objArr), 1L, TimeUnit.SECONDS);
                return null;
            } catch (Exception e2) {
                try {
                    e2.printStackTrace();
                    return null;
                } catch (Exception e3) {
                    q.a(h.f3590b, e3.getMessage());
                    return null;
                }
            }
        }
    }

    public String a(Context context) {
        try {
            Class<?> cls = Class.forName("com.bun.miitmdid.core.MdidSdkHelper");
            Class<?> cls2 = Class.forName("com.bun.supplier.IIdentifierListener");
            cls.getDeclaredMethod("InitSdk", Context.class, Boolean.TYPE, cls2).invoke(cls, context, Boolean.TRUE, Proxy.newProxyInstance(context.getClassLoader(), new Class[]{cls2}, new b()));
            a aVarPoll = this.f3591a.poll(1L, TimeUnit.SECONDS);
            return aVarPoll != null ? aVarPoll.f3594c[1].getClass().getMethod("getOAID", null).invoke(aVarPoll.f3594c[1], null).toString() : "";
        } catch (Throwable th) {
            q.a(f3590b, th.getMessage());
            return "";
        }
    }
}
