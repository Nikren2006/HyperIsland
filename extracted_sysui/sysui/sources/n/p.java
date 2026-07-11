package n;

import android.graphics.Path;
import com.miui.circulate.device.api.Constant;
import d.C0307h;
import j.C0410c;
import j.C0411d;
import java.util.Collections;
import o.AbstractC0715c;

/* JADX INFO: loaded from: classes.dex */
public abstract class p {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final AbstractC0715c.a f6213a = AbstractC0715c.a.a("nm", "g", "o", "t", "s", "e", "r", "hd");

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final AbstractC0715c.a f6214b = AbstractC0715c.a.a("p", Constant.KeyValue.KEY_COLUMN);

    public static k.e a(AbstractC0715c abstractC0715c, C0307h c0307h) {
        C0411d c0411dH = null;
        Path.FillType fillType = Path.FillType.WINDING;
        String strX = null;
        k.g gVar = null;
        C0410c c0410cG = null;
        j.f fVarI = null;
        j.f fVarI2 = null;
        boolean zR = false;
        while (abstractC0715c.n()) {
            switch (abstractC0715c.C(f6213a)) {
                case 0:
                    strX = abstractC0715c.x();
                    break;
                case 1:
                    abstractC0715c.d();
                    int iU = -1;
                    while (abstractC0715c.n()) {
                        int iC = abstractC0715c.C(f6214b);
                        if (iC == 0) {
                            iU = abstractC0715c.u();
                        } else if (iC != 1) {
                            abstractC0715c.D();
                            abstractC0715c.E();
                        } else {
                            c0410cG = AbstractC0701d.g(abstractC0715c, c0307h, iU);
                        }
                    }
                    abstractC0715c.f();
                    break;
                case 2:
                    c0411dH = AbstractC0701d.h(abstractC0715c, c0307h);
                    break;
                case 3:
                    gVar = abstractC0715c.u() == 1 ? k.g.LINEAR : k.g.RADIAL;
                    break;
                case 4:
                    fVarI = AbstractC0701d.i(abstractC0715c, c0307h);
                    break;
                case 5:
                    fVarI2 = AbstractC0701d.i(abstractC0715c, c0307h);
                    break;
                case 6:
                    fillType = abstractC0715c.u() == 1 ? Path.FillType.WINDING : Path.FillType.EVEN_ODD;
                    break;
                case 7:
                    zR = abstractC0715c.r();
                    break;
                default:
                    abstractC0715c.D();
                    abstractC0715c.E();
                    break;
            }
        }
        return new k.e(strX, gVar, fillType, c0410cG, c0411dH == null ? new C0411d(Collections.singletonList(new com.airbnb.lottie.value.a(100))) : c0411dH, fVarI, fVarI2, null, null, zR);
    }
}
