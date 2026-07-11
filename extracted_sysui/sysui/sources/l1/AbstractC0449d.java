package l1;

import kotlin.jvm.functions.Function2;

/* JADX INFO: renamed from: l1.d, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes2.dex */
public abstract class AbstractC0449d {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final F f5215a = new F("CLOSED");

    public static final AbstractC0450e b(AbstractC0450e abstractC0450e) {
        while (true) {
            Object objF = abstractC0450e.f();
            if (objF == f5215a) {
                return abstractC0450e;
            }
            AbstractC0450e abstractC0450e2 = (AbstractC0450e) objF;
            if (abstractC0450e2 != null) {
                abstractC0450e = abstractC0450e2;
            } else if (abstractC0450e.j()) {
                return abstractC0450e;
            }
        }
    }

    public static final Object c(C c2, long j2, Function2 function2) {
        while (true) {
            if (c2.f5193c >= j2 && !c2.h()) {
                return D.a(c2);
            }
            Object objF = c2.f();
            if (objF == f5215a) {
                return D.a(f5215a);
            }
            C c3 = (C) ((AbstractC0450e) objF);
            if (c3 == null) {
                c3 = (C) function2.invoke(Long.valueOf(c2.f5193c + 1), c2);
                if (c2.l(c3)) {
                    if (c2.h()) {
                        c2.k();
                    }
                }
            }
            c2 = c3;
        }
    }
}
