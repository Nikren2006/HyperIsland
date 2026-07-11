package n;

import d.C0307h;
import j.C0411d;
import k.h;
import o.AbstractC0715c;
import p.AbstractC0724d;

/* JADX INFO: loaded from: classes.dex */
public abstract class x {
    public static k.h a(AbstractC0715c abstractC0715c, C0307h c0307h) {
        String strW;
        abstractC0715c.d();
        h.a aVar = null;
        j.h hVarK = null;
        C0411d c0411dH = null;
        boolean zR = false;
        while (abstractC0715c.n()) {
            strW = abstractC0715c.w();
            strW.hashCode();
            switch (strW) {
                case "o":
                    c0411dH = AbstractC0701d.h(abstractC0715c, c0307h);
                    break;
                case "pt":
                    hVarK = AbstractC0701d.k(abstractC0715c, c0307h);
                    break;
                case "inv":
                    zR = abstractC0715c.r();
                    break;
                case "mode":
                    String strX = abstractC0715c.x();
                    strX.hashCode();
                    switch (strX) {
                        case "a":
                            aVar = h.a.MASK_MODE_ADD;
                            break;
                        case "i":
                            c0307h.a("Animation contains intersect masks. They are not supported but will be treated like add masks.");
                            aVar = h.a.MASK_MODE_INTERSECT;
                            break;
                        case "n":
                            aVar = h.a.MASK_MODE_NONE;
                            break;
                        case "s":
                            aVar = h.a.MASK_MODE_SUBTRACT;
                            break;
                        default:
                            AbstractC0724d.c("Unknown mask mode " + strW + ". Defaulting to Add.");
                            aVar = h.a.MASK_MODE_ADD;
                            break;
                    }
                    break;
                default:
                    abstractC0715c.E();
                    break;
            }
        }
        abstractC0715c.f();
        return new k.h(aVar, hVarK, c0411dH, zR);
    }
}
