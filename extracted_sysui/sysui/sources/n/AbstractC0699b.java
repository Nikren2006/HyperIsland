package n;

import d.C0307h;
import j.C0408a;
import j.C0409b;
import o.AbstractC0715c;

/* JADX INFO: renamed from: n.b, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public abstract class AbstractC0699b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final AbstractC0715c.a f6185a = AbstractC0715c.a.a("a");

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final AbstractC0715c.a f6186b = AbstractC0715c.a.a("fc", "sc", "sw", "t");

    public static j.k a(AbstractC0715c abstractC0715c, C0307h c0307h) {
        abstractC0715c.d();
        j.k kVarB = null;
        while (abstractC0715c.n()) {
            if (abstractC0715c.C(f6185a) != 0) {
                abstractC0715c.D();
                abstractC0715c.E();
            } else {
                kVarB = b(abstractC0715c, c0307h);
            }
        }
        abstractC0715c.f();
        return kVarB == null ? new j.k(null, null, null, null) : kVarB;
    }

    public static j.k b(AbstractC0715c abstractC0715c, C0307h c0307h) {
        abstractC0715c.d();
        C0408a c0408aC = null;
        C0408a c0408aC2 = null;
        C0409b c0409bE = null;
        C0409b c0409bE2 = null;
        while (abstractC0715c.n()) {
            int iC = abstractC0715c.C(f6186b);
            if (iC == 0) {
                c0408aC = AbstractC0701d.c(abstractC0715c, c0307h);
            } else if (iC == 1) {
                c0408aC2 = AbstractC0701d.c(abstractC0715c, c0307h);
            } else if (iC == 2) {
                c0409bE = AbstractC0701d.e(abstractC0715c, c0307h);
            } else if (iC != 3) {
                abstractC0715c.D();
                abstractC0715c.E();
            } else {
                c0409bE2 = AbstractC0701d.e(abstractC0715c, c0307h);
            }
        }
        abstractC0715c.f();
        return new j.k(c0408aC, c0408aC2, c0409bE, c0409bE2);
    }
}
