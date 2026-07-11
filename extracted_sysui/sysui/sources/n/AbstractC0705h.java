package n;

import d.C0307h;
import o.AbstractC0715c;
import p.AbstractC0724d;

/* JADX INFO: renamed from: n.h, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public abstract class AbstractC0705h {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final AbstractC0715c.a f6193a = AbstractC0715c.a.a("ty", "d");

    public static k.c a(AbstractC0715c abstractC0715c, C0307h c0307h) {
        k.c cVarA;
        String strX;
        abstractC0715c.d();
        int iU = 2;
        while (true) {
            cVarA = null;
            if (!abstractC0715c.n()) {
                strX = null;
                break;
            }
            int iC = abstractC0715c.C(f6193a);
            if (iC == 0) {
                strX = abstractC0715c.x();
                break;
            }
            if (iC != 1) {
                abstractC0715c.D();
                abstractC0715c.E();
            } else {
                iU = abstractC0715c.u();
            }
        }
        if (strX == null) {
            return null;
        }
        switch (strX) {
            case "el":
                cVarA = AbstractC0703f.a(abstractC0715c, c0307h, iU);
                break;
            case "fl":
                cVarA = I.a(abstractC0715c, c0307h);
                break;
            case "gf":
                cVarA = p.a(abstractC0715c, c0307h);
                break;
            case "gr":
                cVarA = J.a(abstractC0715c, c0307h);
                break;
            case "gs":
                cVarA = q.a(abstractC0715c, c0307h);
                break;
            case "mm":
                cVarA = y.a(abstractC0715c);
                c0307h.a("Animation contains merge paths. Merge paths are only supported on KitKat+ and must be manually enabled by calling enableMergePathsForKitKatAndAbove().");
                break;
            case "rc":
                cVarA = D.a(abstractC0715c, c0307h);
                break;
            case "rd":
                cVarA = F.a(abstractC0715c, c0307h);
                break;
            case "rp":
                cVarA = E.a(abstractC0715c, c0307h);
                break;
            case "sh":
                cVarA = K.a(abstractC0715c, c0307h);
                break;
            case "sr":
                cVarA = C.a(abstractC0715c, c0307h, iU);
                break;
            case "st":
                cVarA = L.a(abstractC0715c, c0307h);
                break;
            case "tm":
                cVarA = M.a(abstractC0715c, c0307h);
                break;
            case "tr":
                cVarA = AbstractC0700c.g(abstractC0715c, c0307h);
                break;
            default:
                AbstractC0724d.c("Unknown shape type " + strX);
                break;
        }
        while (abstractC0715c.n()) {
            abstractC0715c.E();
        }
        abstractC0715c.f();
        return cVarA;
    }
}
