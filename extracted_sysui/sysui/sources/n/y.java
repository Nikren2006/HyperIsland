package n;

import k.i;
import o.AbstractC0715c;

/* JADX INFO: loaded from: classes.dex */
public abstract class y {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final AbstractC0715c.a f6234a = AbstractC0715c.a.a("nm", "mm", "hd");

    public static k.i a(AbstractC0715c abstractC0715c) {
        String strX = null;
        boolean zR = false;
        i.a aVarA = null;
        while (abstractC0715c.n()) {
            int iC = abstractC0715c.C(f6234a);
            if (iC == 0) {
                strX = abstractC0715c.x();
            } else if (iC == 1) {
                aVarA = i.a.a(abstractC0715c.u());
            } else if (iC != 2) {
                abstractC0715c.D();
                abstractC0715c.E();
            } else {
                zR = abstractC0715c.r();
            }
        }
        return new k.i(strX, aVarA, zR);
    }
}
