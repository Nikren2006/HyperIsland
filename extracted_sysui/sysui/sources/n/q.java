package n;

import com.miui.circulate.device.api.Constant;
import com.miui.maml.folme.AnimatedProperty;
import d.C0307h;
import j.C0409b;
import j.C0410c;
import j.C0411d;
import java.util.ArrayList;
import java.util.Collections;
import k.r;
import o.AbstractC0715c;

/* JADX INFO: loaded from: classes.dex */
public abstract class q {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final AbstractC0715c.a f6215a = AbstractC0715c.a.a("nm", "g", "o", "t", "s", "e", AnimatedProperty.PROPERTY_NAME_W, "lc", "lj", com.xiaomi.onetrack.b.m.f3094g, "hd", "d");

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final AbstractC0715c.a f6216b = AbstractC0715c.a.a("p", Constant.KeyValue.KEY_COLUMN);

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final AbstractC0715c.a f6217c = AbstractC0715c.a.a("n", Constant.KeyValue.VALUE_COLUMN);

    public static k.f a(AbstractC0715c abstractC0715c, C0307h c0307h) {
        String str;
        C0410c c0410c;
        ArrayList arrayList = new ArrayList();
        float fT = 0.0f;
        String strX = null;
        k.g gVar = null;
        C0410c c0410cG = null;
        j.f fVarI = null;
        j.f fVarI2 = null;
        C0409b c0409bE = null;
        r.b bVar = null;
        r.c cVar = null;
        C0409b c0409b = null;
        boolean zR = false;
        C0411d c0411d = null;
        while (abstractC0715c.n()) {
            switch (abstractC0715c.C(f6215a)) {
                case 0:
                    strX = abstractC0715c.x();
                    continue;
                case 1:
                    str = strX;
                    abstractC0715c.d();
                    int iU = -1;
                    while (abstractC0715c.n()) {
                        int iC = abstractC0715c.C(f6216b);
                        if (iC != 0) {
                            c0410c = c0410cG;
                            if (iC != 1) {
                                abstractC0715c.D();
                                abstractC0715c.E();
                            } else {
                                c0410cG = AbstractC0701d.g(abstractC0715c, c0307h, iU);
                            }
                        } else {
                            c0410c = c0410cG;
                            iU = abstractC0715c.u();
                        }
                        c0410cG = c0410c;
                    }
                    abstractC0715c.f();
                    break;
                case 2:
                    c0411d = AbstractC0701d.h(abstractC0715c, c0307h);
                    continue;
                case 3:
                    str = strX;
                    gVar = abstractC0715c.u() == 1 ? k.g.LINEAR : k.g.RADIAL;
                    break;
                case 4:
                    fVarI = AbstractC0701d.i(abstractC0715c, c0307h);
                    continue;
                case 5:
                    fVarI2 = AbstractC0701d.i(abstractC0715c, c0307h);
                    continue;
                case 6:
                    c0409bE = AbstractC0701d.e(abstractC0715c, c0307h);
                    continue;
                case 7:
                    str = strX;
                    bVar = r.b.values()[abstractC0715c.u() - 1];
                    break;
                case 8:
                    str = strX;
                    cVar = r.c.values()[abstractC0715c.u() - 1];
                    break;
                case 9:
                    str = strX;
                    fT = (float) abstractC0715c.t();
                    break;
                case 10:
                    zR = abstractC0715c.r();
                    continue;
                case 11:
                    abstractC0715c.c();
                    while (abstractC0715c.n()) {
                        abstractC0715c.d();
                        String strX2 = null;
                        C0409b c0409bE2 = null;
                        while (abstractC0715c.n()) {
                            int iC2 = abstractC0715c.C(f6217c);
                            if (iC2 != 0) {
                                C0409b c0409b2 = c0409b;
                                if (iC2 != 1) {
                                    abstractC0715c.D();
                                    abstractC0715c.E();
                                } else {
                                    c0409bE2 = AbstractC0701d.e(abstractC0715c, c0307h);
                                }
                                c0409b = c0409b2;
                            } else {
                                strX2 = abstractC0715c.x();
                            }
                        }
                        C0409b c0409b3 = c0409b;
                        abstractC0715c.f();
                        if (strX2.equals("o")) {
                            c0409b = c0409bE2;
                        } else {
                            if (strX2.equals("d") || strX2.equals("g")) {
                                c0307h.u(true);
                                arrayList.add(c0409bE2);
                            }
                            c0409b = c0409b3;
                        }
                    }
                    C0409b c0409b4 = c0409b;
                    abstractC0715c.e();
                    if (arrayList.size() == 1) {
                        arrayList.add((C0409b) arrayList.get(0));
                    }
                    c0409b = c0409b4;
                    continue;
                default:
                    abstractC0715c.D();
                    abstractC0715c.E();
                    continue;
            }
            strX = str;
        }
        String str2 = strX;
        if (c0411d == null) {
            c0411d = new C0411d(Collections.singletonList(new com.airbnb.lottie.value.a(100)));
        }
        return new k.f(str2, gVar, c0410cG, c0411d, fVarI, fVarI2, c0409bE, bVar, cVar, fT, arrayList, c0409b, zR);
    }
}
