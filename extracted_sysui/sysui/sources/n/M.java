package n;

import d.C0307h;
import j.C0409b;
import k.s;
import o.AbstractC0715c;

/* JADX INFO: loaded from: classes.dex */
public abstract class M {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final AbstractC0715c.a f6183a = AbstractC0715c.a.a("s", "e", "o", "nm", "m", "hd");

    public static k.s a(AbstractC0715c abstractC0715c, C0307h c0307h) {
        String strX = null;
        s.a aVarA = null;
        C0409b c0409bF = null;
        C0409b c0409bF2 = null;
        C0409b c0409bF3 = null;
        boolean zR = false;
        while (abstractC0715c.n()) {
            int iC = abstractC0715c.C(f6183a);
            if (iC == 0) {
                c0409bF = AbstractC0701d.f(abstractC0715c, c0307h, false);
            } else if (iC == 1) {
                c0409bF2 = AbstractC0701d.f(abstractC0715c, c0307h, false);
            } else if (iC == 2) {
                c0409bF3 = AbstractC0701d.f(abstractC0715c, c0307h, false);
            } else if (iC == 3) {
                strX = abstractC0715c.x();
            } else if (iC == 4) {
                aVarA = s.a.a(abstractC0715c.u());
            } else if (iC != 5) {
                abstractC0715c.E();
            } else {
                zR = abstractC0715c.r();
            }
        }
        return new k.s(strX, aVarA, c0409bF, c0409bF2, c0409bF3, zR);
    }
}
