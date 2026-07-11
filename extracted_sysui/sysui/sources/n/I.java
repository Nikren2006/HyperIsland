package n;

import android.graphics.Path;
import d.C0307h;
import j.C0408a;
import j.C0411d;
import java.util.Collections;
import o.AbstractC0715c;

/* JADX INFO: loaded from: classes.dex */
public abstract class I {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final AbstractC0715c.a f6178a = AbstractC0715c.a.a("nm", "c", "o", "fillEnabled", "r", "hd");

    public static k.o a(AbstractC0715c abstractC0715c, C0307h c0307h) {
        C0411d c0411d = null;
        String strX = null;
        C0408a c0408aC = null;
        boolean zR = false;
        boolean zR2 = false;
        int iU = 1;
        while (abstractC0715c.n()) {
            int iC = abstractC0715c.C(f6178a);
            if (iC == 0) {
                strX = abstractC0715c.x();
            } else if (iC == 1) {
                c0408aC = AbstractC0701d.c(abstractC0715c, c0307h);
            } else if (iC == 2) {
                c0411d = AbstractC0701d.h(abstractC0715c, c0307h);
            } else if (iC == 3) {
                zR = abstractC0715c.r();
            } else if (iC == 4) {
                iU = abstractC0715c.u();
            } else if (iC != 5) {
                abstractC0715c.D();
                abstractC0715c.E();
            } else {
                zR2 = abstractC0715c.r();
            }
        }
        if (c0411d == null) {
            c0411d = new C0411d(Collections.singletonList(new com.airbnb.lottie.value.a(100)));
        }
        return new k.o(strX, zR, iU == 1 ? Path.FillType.WINDING : Path.FillType.EVEN_ODD, c0408aC, c0411d, zR2);
    }
}
