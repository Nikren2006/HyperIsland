package kotlin.jvm.internal;

import d1.InterfaceC0324c;
import d1.InterfaceC0325d;
import d1.InterfaceC0326e;
import d1.InterfaceC0327f;
import d1.InterfaceC0328g;

/* JADX INFO: loaded from: classes2.dex */
public abstract class z {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final A f5060a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final InterfaceC0324c[] f5061b;

    static {
        A a2 = null;
        try {
            a2 = (A) Class.forName("kotlin.reflect.jvm.internal.ReflectionFactoryImpl").newInstance();
        } catch (ClassCastException | ClassNotFoundException | IllegalAccessException | InstantiationException unused) {
        }
        if (a2 == null) {
            a2 = new A();
        }
        f5060a = a2;
        f5061b = new InterfaceC0324c[0];
    }

    public static InterfaceC0326e a(k kVar) {
        return f5060a.a(kVar);
    }

    public static InterfaceC0324c b(Class cls) {
        return f5060a.b(cls);
    }

    public static InterfaceC0325d c(Class cls) {
        return f5060a.c(cls, "");
    }

    public static InterfaceC0327f d(p pVar) {
        return f5060a.d(pVar);
    }

    public static InterfaceC0328g e(t tVar) {
        return f5060a.e(tVar);
    }

    public static String f(j jVar) {
        return f5060a.f(jVar);
    }

    public static String g(o oVar) {
        return f5060a.g(oVar);
    }
}
