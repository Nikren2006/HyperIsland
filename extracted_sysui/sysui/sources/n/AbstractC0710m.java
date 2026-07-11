package n;

import com.miui.maml.folme.AnimatedProperty;
import d.C0307h;
import java.util.ArrayList;
import o.AbstractC0715c;

/* JADX INFO: renamed from: n.m, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public abstract class AbstractC0710m {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final AbstractC0715c.a f6209a = AbstractC0715c.a.a("ch", "size", AnimatedProperty.PROPERTY_NAME_W, "style", "fFamily", "data");

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final AbstractC0715c.a f6210b = AbstractC0715c.a.a("shapes");

    public static i.d a(AbstractC0715c abstractC0715c, C0307h c0307h) {
        ArrayList arrayList = new ArrayList();
        abstractC0715c.d();
        double dT = 0.0d;
        String strX = null;
        String strX2 = null;
        char cCharAt = 0;
        double dT2 = 0.0d;
        while (abstractC0715c.n()) {
            int iC = abstractC0715c.C(f6209a);
            if (iC == 0) {
                cCharAt = abstractC0715c.x().charAt(0);
            } else if (iC == 1) {
                dT2 = abstractC0715c.t();
            } else if (iC == 2) {
                dT = abstractC0715c.t();
            } else if (iC == 3) {
                strX = abstractC0715c.x();
            } else if (iC == 4) {
                strX2 = abstractC0715c.x();
            } else if (iC != 5) {
                abstractC0715c.D();
                abstractC0715c.E();
            } else {
                abstractC0715c.d();
                while (abstractC0715c.n()) {
                    if (abstractC0715c.C(f6210b) != 0) {
                        abstractC0715c.D();
                        abstractC0715c.E();
                    } else {
                        abstractC0715c.c();
                        while (abstractC0715c.n()) {
                            arrayList.add((k.p) AbstractC0705h.a(abstractC0715c, c0307h));
                        }
                        abstractC0715c.e();
                    }
                }
                abstractC0715c.f();
            }
        }
        abstractC0715c.f();
        return new i.d(arrayList, cCharAt, dT2, dT, strX, strX2);
    }
}
