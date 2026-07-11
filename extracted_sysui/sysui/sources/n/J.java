package n;

import d.C0307h;
import java.util.ArrayList;
import o.AbstractC0715c;

/* JADX INFO: loaded from: classes.dex */
public abstract class J {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final AbstractC0715c.a f6179a = AbstractC0715c.a.a("nm", "hd", "it");

    public static k.p a(AbstractC0715c abstractC0715c, C0307h c0307h) {
        ArrayList arrayList = new ArrayList();
        String strX = null;
        boolean zR = false;
        while (abstractC0715c.n()) {
            int iC = abstractC0715c.C(f6179a);
            if (iC == 0) {
                strX = abstractC0715c.x();
            } else if (iC == 1) {
                zR = abstractC0715c.r();
            } else if (iC != 2) {
                abstractC0715c.E();
            } else {
                abstractC0715c.c();
                while (abstractC0715c.n()) {
                    k.c cVarA = AbstractC0705h.a(abstractC0715c, c0307h);
                    if (cVarA != null) {
                        arrayList.add(cVarA);
                    }
                }
                abstractC0715c.e();
            }
        }
        return new k.p(strX, arrayList, zR);
    }
}
