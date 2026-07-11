package n;

import d.C0307h;
import o.AbstractC0715c;

/* JADX INFO: renamed from: n.f, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public abstract class AbstractC0703f {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final AbstractC0715c.a f6191a = AbstractC0715c.a.a("nm", "p", "s", "hd", "d");

    public static k.b a(AbstractC0715c abstractC0715c, C0307h c0307h, int i2) {
        boolean z2 = i2 == 3;
        boolean zR = false;
        String strX = null;
        j.m mVarB = null;
        j.f fVarI = null;
        while (abstractC0715c.n()) {
            int iC = abstractC0715c.C(f6191a);
            if (iC == 0) {
                strX = abstractC0715c.x();
            } else if (iC == 1) {
                mVarB = AbstractC0698a.b(abstractC0715c, c0307h);
            } else if (iC == 2) {
                fVarI = AbstractC0701d.i(abstractC0715c, c0307h);
            } else if (iC == 3) {
                zR = abstractC0715c.r();
            } else if (iC != 4) {
                abstractC0715c.D();
                abstractC0715c.E();
            } else {
                z2 = abstractC0715c.u() == 3;
            }
        }
        return new k.b(strX, mVarB, fVarI, z2, zR);
    }
}
