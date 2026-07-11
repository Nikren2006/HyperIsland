package n;

import d.C0307h;
import j.C0409b;
import o.AbstractC0715c;

/* JADX INFO: loaded from: classes.dex */
public abstract class F {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final AbstractC0715c.a f6174a = AbstractC0715c.a.a("nm", "r", "hd");

    public static k.m a(AbstractC0715c abstractC0715c, C0307h c0307h) {
        boolean zR = false;
        String strX = null;
        C0409b c0409bF = null;
        while (abstractC0715c.n()) {
            int iC = abstractC0715c.C(f6174a);
            if (iC == 0) {
                strX = abstractC0715c.x();
            } else if (iC == 1) {
                c0409bF = AbstractC0701d.f(abstractC0715c, c0307h, true);
            } else if (iC != 2) {
                abstractC0715c.E();
            } else {
                zR = abstractC0715c.r();
            }
        }
        if (zR) {
            return null;
        }
        return new k.m(strX, c0409bF);
    }
}
