package n;

import o.AbstractC0715c;

/* JADX INFO: renamed from: n.n, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public abstract class AbstractC0711n {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final AbstractC0715c.a f6211a = AbstractC0715c.a.a("fFamily", "fName", "fStyle", "ascent");

    public static i.c a(AbstractC0715c abstractC0715c) {
        abstractC0715c.d();
        String strX = null;
        String strX2 = null;
        float fT = 0.0f;
        String strX3 = null;
        while (abstractC0715c.n()) {
            int iC = abstractC0715c.C(f6211a);
            if (iC == 0) {
                strX = abstractC0715c.x();
            } else if (iC == 1) {
                strX3 = abstractC0715c.x();
            } else if (iC == 2) {
                strX2 = abstractC0715c.x();
            } else if (iC != 3) {
                abstractC0715c.D();
                abstractC0715c.E();
            } else {
                fT = (float) abstractC0715c.t();
            }
        }
        abstractC0715c.f();
        return new i.c(strX, strX3, strX2, fT);
    }
}
