package l1;

import e1.AbstractC0345j;
import g1.w0;
import java.util.Iterator;
import java.util.List;
import java.util.ServiceLoader;

/* JADX INFO: loaded from: classes2.dex */
public final class u {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final u f5252a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final w0 f5253b;

    static {
        u uVar = new u();
        f5252a = uVar;
        G.f("kotlinx.coroutines.fast.service.loader", true);
        f5253b = uVar.a();
    }

    public final w0 a() {
        Object next;
        w0 w0VarE;
        try {
            List listR = e1.l.r(AbstractC0345j.c(ServiceLoader.load(t.class, t.class.getClassLoader()).iterator()));
            Iterator it = listR.iterator();
            if (it.hasNext()) {
                next = it.next();
                if (it.hasNext()) {
                    int iC = ((t) next).c();
                    do {
                        Object next2 = it.next();
                        int iC2 = ((t) next2).c();
                        if (iC < iC2) {
                            next = next2;
                            iC = iC2;
                        }
                    } while (it.hasNext());
                }
            } else {
                next = null;
            }
            t tVar = (t) next;
            if (tVar != null && (w0VarE = v.e(tVar, listR)) != null) {
                return w0VarE;
            }
            return v.b(null, null, 3, null);
        } catch (Throwable th) {
            return v.b(th, null, 2, null);
        }
    }
}
