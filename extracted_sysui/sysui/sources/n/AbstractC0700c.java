package n;

import android.graphics.PointF;
import com.miui.circulate.device.api.Constant;
import d.C0307h;
import j.C0409b;
import j.C0411d;
import o.AbstractC0715c;

/* JADX INFO: renamed from: n.c, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public abstract class AbstractC0700c {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final AbstractC0715c.a f6187a = AbstractC0715c.a.a("a", "p", "s", "rz", "r", "o", "so", "eo", "sk", "sa");

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final AbstractC0715c.a f6188b = AbstractC0715c.a.a(Constant.KeyValue.KEY_COLUMN);

    public static boolean a(j.e eVar) {
        return eVar == null || (eVar.b() && ((PointF) ((com.airbnb.lottie.value.a) eVar.getKeyframes().get(0)).f1393b).equals(0.0f, 0.0f));
    }

    public static boolean b(j.m mVar) {
        return mVar == null || (!(mVar instanceof j.i) && mVar.b() && ((PointF) ((com.airbnb.lottie.value.a) mVar.getKeyframes().get(0)).f1393b).equals(0.0f, 0.0f));
    }

    public static boolean c(C0409b c0409b) {
        return c0409b == null || (c0409b.b() && ((Float) ((com.airbnb.lottie.value.a) c0409b.getKeyframes().get(0)).f1393b).floatValue() == 0.0f);
    }

    public static boolean d(j.g gVar) {
        return gVar == null || (gVar.b() && ((com.airbnb.lottie.value.d) ((com.airbnb.lottie.value.a) gVar.getKeyframes().get(0)).f1393b).a(1.0f, 1.0f));
    }

    public static boolean e(C0409b c0409b) {
        return c0409b == null || (c0409b.b() && ((Float) ((com.airbnb.lottie.value.a) c0409b.getKeyframes().get(0)).f1393b).floatValue() == 0.0f);
    }

    public static boolean f(C0409b c0409b) {
        return c0409b == null || (c0409b.b() && ((Float) ((com.airbnb.lottie.value.a) c0409b.getKeyframes().get(0)).f1393b).floatValue() == 0.0f);
    }

    public static j.l g(AbstractC0715c abstractC0715c, C0307h c0307h) {
        boolean z2;
        boolean z3 = false;
        boolean z4 = abstractC0715c.A() == AbstractC0715c.b.BEGIN_OBJECT;
        if (z4) {
            abstractC0715c.d();
        }
        C0409b c0409b = null;
        j.e eVarA = null;
        j.m mVarB = null;
        j.g gVarJ = null;
        C0409b c0409bF = null;
        C0409b c0409bF2 = null;
        C0411d c0411dH = null;
        C0409b c0409bF3 = null;
        C0409b c0409bF4 = null;
        while (abstractC0715c.n()) {
            switch (abstractC0715c.C(f6187a)) {
                case 0:
                    boolean z5 = z3;
                    abstractC0715c.d();
                    while (abstractC0715c.n()) {
                        if (abstractC0715c.C(f6188b) != 0) {
                            abstractC0715c.D();
                            abstractC0715c.E();
                        } else {
                            eVarA = AbstractC0698a.a(abstractC0715c, c0307h);
                        }
                    }
                    abstractC0715c.f();
                    z3 = z5;
                    continue;
                case 1:
                    mVarB = AbstractC0698a.b(abstractC0715c, c0307h);
                    continue;
                case 2:
                    gVarJ = AbstractC0701d.j(abstractC0715c, c0307h);
                    continue;
                case 3:
                    c0307h.a("Lottie doesn't support 3D layers.");
                    break;
                case 4:
                    break;
                case 5:
                    c0411dH = AbstractC0701d.h(abstractC0715c, c0307h);
                    continue;
                case 6:
                    c0409bF3 = AbstractC0701d.f(abstractC0715c, c0307h, z3);
                    continue;
                case 7:
                    c0409bF4 = AbstractC0701d.f(abstractC0715c, c0307h, z3);
                    continue;
                case 8:
                    c0409bF = AbstractC0701d.f(abstractC0715c, c0307h, z3);
                    continue;
                case 9:
                    c0409bF2 = AbstractC0701d.f(abstractC0715c, c0307h, z3);
                    continue;
                default:
                    abstractC0715c.D();
                    abstractC0715c.E();
                    continue;
            }
            C0409b c0409bF5 = AbstractC0701d.f(abstractC0715c, c0307h, z3);
            if (c0409bF5.getKeyframes().isEmpty()) {
                c0409bF5.getKeyframes().add(new com.airbnb.lottie.value.a(c0307h, Float.valueOf(0.0f), Float.valueOf(0.0f), null, 0.0f, Float.valueOf(c0307h.f())));
            } else {
                if (((com.airbnb.lottie.value.a) c0409bF5.getKeyframes().get(0)).f1393b == null) {
                    z2 = false;
                    c0409bF5.getKeyframes().set(0, new com.airbnb.lottie.value.a(c0307h, Float.valueOf(0.0f), Float.valueOf(0.0f), null, 0.0f, Float.valueOf(c0307h.f())));
                }
                z3 = z2;
                c0409b = c0409bF5;
            }
            z2 = false;
            z3 = z2;
            c0409b = c0409bF5;
        }
        if (z4) {
            abstractC0715c.f();
        }
        j.e eVar = a(eVarA) ? null : eVarA;
        j.m mVar = b(mVarB) ? null : mVarB;
        C0409b c0409b2 = c(c0409b) ? null : c0409b;
        if (d(gVarJ)) {
            gVarJ = null;
        }
        return new j.l(eVar, mVar, gVarJ, c0409b2, c0411dH, c0409bF3, c0409bF4, f(c0409bF) ? null : c0409bF, e(c0409bF2) ? null : c0409bF2);
    }
}
