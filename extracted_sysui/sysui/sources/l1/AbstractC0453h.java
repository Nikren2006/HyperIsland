package l1;

import java.util.Iterator;

/* JADX INFO: renamed from: l1.h, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes2.dex */
public abstract class AbstractC0453h {
    public static final void a(L0.g gVar, Throwable th) {
        Iterator it = AbstractC0452g.a().iterator();
        while (it.hasNext()) {
            try {
                ((g1.C) it.next()).u(gVar, th);
            } catch (Throwable th2) {
                AbstractC0452g.b(g1.D.b(th, th2));
            }
        }
        try {
            H0.a.a(th, new C0454i(gVar));
        } catch (Throwable unused) {
        }
        AbstractC0452g.b(th);
    }
}
