package n;

import d.C0307h;
import j.C0409b;
import o.AbstractC0715c;

/* JADX INFO: loaded from: classes.dex */
public abstract class D {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final AbstractC0715c.a f6172a = AbstractC0715c.a.a("nm", "p", "s", "r", "hd");

    public static k.k a(AbstractC0715c abstractC0715c, C0307h c0307h) {
        String strX = null;
        j.m mVarB = null;
        j.f fVarI = null;
        C0409b c0409bE = null;
        boolean zR = false;
        while (abstractC0715c.n()) {
            int iC = abstractC0715c.C(f6172a);
            if (iC == 0) {
                strX = abstractC0715c.x();
            } else if (iC == 1) {
                mVarB = AbstractC0698a.b(abstractC0715c, c0307h);
            } else if (iC == 2) {
                fVarI = AbstractC0701d.i(abstractC0715c, c0307h);
            } else if (iC == 3) {
                c0409bE = AbstractC0701d.e(abstractC0715c, c0307h);
            } else if (iC != 4) {
                abstractC0715c.E();
            } else {
                zR = abstractC0715c.r();
            }
        }
        return new k.k(strX, mVarB, fVarI, c0409bE, zR);
    }
}
