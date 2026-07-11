package n;

import d.C0307h;
import j.C0409b;
import o.AbstractC0715c;

/* JADX INFO: loaded from: classes.dex */
public abstract class E {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final AbstractC0715c.a f6173a = AbstractC0715c.a.a("nm", "c", "o", "tr", "hd");

    public static k.l a(AbstractC0715c abstractC0715c, C0307h c0307h) {
        String strX = null;
        C0409b c0409bF = null;
        C0409b c0409bF2 = null;
        j.l lVarG = null;
        boolean zR = false;
        while (abstractC0715c.n()) {
            int iC = abstractC0715c.C(f6173a);
            if (iC == 0) {
                strX = abstractC0715c.x();
            } else if (iC == 1) {
                c0409bF = AbstractC0701d.f(abstractC0715c, c0307h, false);
            } else if (iC == 2) {
                c0409bF2 = AbstractC0701d.f(abstractC0715c, c0307h, false);
            } else if (iC == 3) {
                lVarG = AbstractC0700c.g(abstractC0715c, c0307h);
            } else if (iC != 4) {
                abstractC0715c.E();
            } else {
                zR = abstractC0715c.r();
            }
        }
        return new k.l(strX, c0409bF, c0409bF2, lVarG, zR);
    }
}
