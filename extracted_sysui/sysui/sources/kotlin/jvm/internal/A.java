package kotlin.jvm.internal;

import d1.InterfaceC0324c;
import d1.InterfaceC0325d;
import d1.InterfaceC0326e;
import d1.InterfaceC0327f;
import d1.InterfaceC0328g;

/* JADX INFO: loaded from: classes2.dex */
public class A {
    public InterfaceC0326e a(k kVar) {
        return kVar;
    }

    public InterfaceC0324c b(Class cls) {
        return new f(cls);
    }

    public InterfaceC0325d c(Class cls, String str) {
        return new s(cls, str);
    }

    public InterfaceC0327f d(p pVar) {
        return pVar;
    }

    public InterfaceC0328g e(t tVar) {
        return tVar;
    }

    public String f(j jVar) {
        String string = jVar.getClass().getGenericInterfaces()[0].toString();
        return string.startsWith("kotlin.jvm.functions.") ? string.substring(21) : string;
    }

    public String g(o oVar) {
        return f(oVar);
    }
}
