package n;

import d.C0307h;
import o.AbstractC0715c;

/* JADX INFO: loaded from: classes.dex */
public abstract class K {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static AbstractC0715c.a f6180a = AbstractC0715c.a.a("nm", "ind", "ks", "hd");

    public static k.q a(AbstractC0715c abstractC0715c, C0307h c0307h) {
        String strX = null;
        int iU = 0;
        boolean zR = false;
        j.h hVarK = null;
        while (abstractC0715c.n()) {
            int iC = abstractC0715c.C(f6180a);
            if (iC == 0) {
                strX = abstractC0715c.x();
            } else if (iC == 1) {
                iU = abstractC0715c.u();
            } else if (iC == 2) {
                hVarK = AbstractC0701d.k(abstractC0715c, c0307h);
            } else if (iC != 3) {
                abstractC0715c.E();
            } else {
                zR = abstractC0715c.r();
            }
        }
        return new k.q(strX, iU, hVarK, zR);
    }
}
