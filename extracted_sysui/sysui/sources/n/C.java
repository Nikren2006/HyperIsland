package n;

import d.C0307h;
import j.C0409b;
import k.j;
import o.AbstractC0715c;

/* JADX INFO: loaded from: classes.dex */
public abstract class C {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final AbstractC0715c.a f6171a = AbstractC0715c.a.a("nm", "sy", "pt", "p", "r", "or", "os", "ir", "is", "hd", "d");

    public static k.j a(AbstractC0715c abstractC0715c, C0307h c0307h, int i2) {
        boolean zR = false;
        boolean z2 = i2 == 3;
        String strX = null;
        j.a aVarA = null;
        C0409b c0409bF = null;
        j.m mVarB = null;
        C0409b c0409bF2 = null;
        C0409b c0409bE = null;
        C0409b c0409bE2 = null;
        C0409b c0409bF3 = null;
        C0409b c0409bF4 = null;
        while (abstractC0715c.n()) {
            switch (abstractC0715c.C(f6171a)) {
                case 0:
                    strX = abstractC0715c.x();
                    break;
                case 1:
                    aVarA = j.a.a(abstractC0715c.u());
                    break;
                case 2:
                    c0409bF = AbstractC0701d.f(abstractC0715c, c0307h, false);
                    break;
                case 3:
                    mVarB = AbstractC0698a.b(abstractC0715c, c0307h);
                    break;
                case 4:
                    c0409bF2 = AbstractC0701d.f(abstractC0715c, c0307h, false);
                    break;
                case 5:
                    c0409bE2 = AbstractC0701d.e(abstractC0715c, c0307h);
                    break;
                case 6:
                    c0409bF4 = AbstractC0701d.f(abstractC0715c, c0307h, false);
                    break;
                case 7:
                    c0409bE = AbstractC0701d.e(abstractC0715c, c0307h);
                    break;
                case 8:
                    c0409bF3 = AbstractC0701d.f(abstractC0715c, c0307h, false);
                    break;
                case 9:
                    zR = abstractC0715c.r();
                    break;
                case 10:
                    z2 = abstractC0715c.u() == 3;
                    break;
                default:
                    abstractC0715c.D();
                    abstractC0715c.E();
                    break;
            }
        }
        return new k.j(strX, aVarA, c0409bF, mVarB, c0409bF2, c0409bE, c0409bE2, c0409bF3, c0409bF4, zR, z2);
    }
}
