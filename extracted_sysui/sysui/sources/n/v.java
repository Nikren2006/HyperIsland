package n;

import android.graphics.Color;
import android.graphics.Rect;
import com.miui.maml.folme.AnimatedProperty;
import d.C0307h;
import j.C0409b;
import java.util.ArrayList;
import java.util.Collections;
import k.C0425a;
import l.C0435e;
import o.AbstractC0715c;

/* JADX INFO: loaded from: classes.dex */
public abstract class v {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final AbstractC0715c.a f6226a = AbstractC0715c.a.a("nm", "ind", "refId", "ty", "parent", "sw", "sh", "sc", "ks", "tt", "masksProperties", "shapes", "t", "ef", "sr", "st", AnimatedProperty.PROPERTY_NAME_W, AnimatedProperty.PROPERTY_NAME_H, "ip", "op", "tm", "cl", "hd");

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final AbstractC0715c.a f6227b = AbstractC0715c.a.a("d", "a");

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final AbstractC0715c.a f6228c = AbstractC0715c.a.a("ty", "nm");

    public static /* synthetic */ class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final /* synthetic */ int[] f6229a;

        static {
            int[] iArr = new int[C0435e.b.values().length];
            f6229a = iArr;
            try {
                iArr[C0435e.b.LUMA.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f6229a[C0435e.b.LUMA_INVERTED.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
        }
    }

    public static C0435e a(C0307h c0307h) {
        Rect rectB = c0307h.b();
        return new C0435e(Collections.emptyList(), c0307h, "__container", -1L, C0435e.a.PRE_COMP, -1L, null, Collections.emptyList(), new j.l(), 0, 0, 0, 0.0f, 0.0f, rectB.width(), rectB.height(), null, null, Collections.emptyList(), C0435e.b.NONE, null, false, null, null);
    }

    public static C0435e b(AbstractC0715c abstractC0715c, C0307h c0307h) {
        ArrayList arrayList;
        ArrayList arrayList2;
        float f2;
        C0435e.b bVar = C0435e.b.NONE;
        ArrayList arrayList3 = new ArrayList();
        ArrayList arrayList4 = new ArrayList();
        abstractC0715c.d();
        Float fValueOf = Float.valueOf(1.0f);
        Float fValueOf2 = Float.valueOf(0.0f);
        C0435e.b bVar2 = bVar;
        C0435e.a aVar = null;
        String strX = null;
        j.l lVarG = null;
        j.j jVarD = null;
        j.k kVarA = null;
        C0409b c0409bF = null;
        C0425a c0425aB = null;
        C0707j c0707jB = null;
        long jU = 0;
        int iU = 0;
        int iU2 = 0;
        int color = 0;
        int iU3 = 0;
        int iU4 = 0;
        boolean zR = false;
        float fT = 1.0f;
        long jU2 = -1;
        float fT2 = 0.0f;
        float fT3 = 0.0f;
        float f3 = 0.0f;
        String strX2 = "UNSET";
        String strX3 = null;
        while (abstractC0715c.n()) {
            switch (abstractC0715c.C(f6226a)) {
                case 0:
                    strX2 = abstractC0715c.x();
                    break;
                case 1:
                    jU = abstractC0715c.u();
                    break;
                case 2:
                    strX = abstractC0715c.x();
                    break;
                case 3:
                    int iU5 = abstractC0715c.u();
                    aVar = C0435e.a.UNKNOWN;
                    if (iU5 < aVar.ordinal()) {
                        aVar = C0435e.a.values()[iU5];
                    }
                    break;
                case 4:
                    jU2 = abstractC0715c.u();
                    break;
                case 5:
                    iU = (int) (abstractC0715c.u() * p.h.e());
                    break;
                case 6:
                    iU2 = (int) (abstractC0715c.u() * p.h.e());
                    break;
                case 7:
                    color = Color.parseColor(abstractC0715c.x());
                    break;
                case 8:
                    lVarG = AbstractC0700c.g(abstractC0715c, c0307h);
                    break;
                case 9:
                    int iU6 = abstractC0715c.u();
                    if (iU6 < C0435e.b.values().length) {
                        bVar2 = C0435e.b.values()[iU6];
                        int i2 = a.f6229a[bVar2.ordinal()];
                        if (i2 == 1) {
                            c0307h.a("Unsupported matte type: Luma");
                        } else if (i2 == 2) {
                            c0307h.a("Unsupported matte type: Luma Inverted");
                        }
                        c0307h.r(1);
                    } else {
                        c0307h.a("Unsupported matte type: " + iU6);
                    }
                    break;
                case 10:
                    abstractC0715c.c();
                    while (abstractC0715c.n()) {
                        arrayList3.add(x.a(abstractC0715c, c0307h));
                    }
                    c0307h.r(arrayList3.size());
                    abstractC0715c.e();
                    break;
                case 11:
                    abstractC0715c.c();
                    while (abstractC0715c.n()) {
                        k.c cVarA = AbstractC0705h.a(abstractC0715c, c0307h);
                        if (cVarA != null) {
                            arrayList4.add(cVarA);
                        }
                    }
                    abstractC0715c.e();
                    break;
                case 12:
                    abstractC0715c.d();
                    while (abstractC0715c.n()) {
                        int iC = abstractC0715c.C(f6227b);
                        if (iC == 0) {
                            jVarD = AbstractC0701d.d(abstractC0715c, c0307h);
                        } else if (iC != 1) {
                            abstractC0715c.D();
                            abstractC0715c.E();
                        } else {
                            abstractC0715c.c();
                            if (abstractC0715c.n()) {
                                kVarA = AbstractC0699b.a(abstractC0715c, c0307h);
                            }
                            while (abstractC0715c.n()) {
                                abstractC0715c.E();
                            }
                            abstractC0715c.e();
                        }
                    }
                    abstractC0715c.f();
                    break;
                case 13:
                    abstractC0715c.c();
                    ArrayList arrayList5 = new ArrayList();
                    while (abstractC0715c.n()) {
                        abstractC0715c.d();
                        while (abstractC0715c.n()) {
                            int iC2 = abstractC0715c.C(f6228c);
                            if (iC2 == 0) {
                                int iU7 = abstractC0715c.u();
                                if (iU7 == 29) {
                                    c0425aB = AbstractC0702e.b(abstractC0715c, c0307h);
                                } else if (iU7 == 25) {
                                    c0707jB = new C0708k().b(abstractC0715c, c0307h);
                                }
                            } else if (iC2 != 1) {
                                abstractC0715c.D();
                                abstractC0715c.E();
                            } else {
                                arrayList5.add(abstractC0715c.x());
                            }
                        }
                        abstractC0715c.f();
                    }
                    abstractC0715c.e();
                    c0307h.a("Lottie doesn't support layer effects. If you are using them for  fills, strokes, trim paths etc. then try adding them directly as contents  in your shape. Found: " + arrayList5);
                    break;
                case 14:
                    fT = (float) abstractC0715c.t();
                    break;
                case 15:
                    fT3 = (float) abstractC0715c.t();
                    break;
                case 16:
                    iU3 = (int) (abstractC0715c.u() * p.h.e());
                    break;
                case 17:
                    iU4 = (int) (abstractC0715c.u() * p.h.e());
                    break;
                case 18:
                    fT2 = (float) abstractC0715c.t();
                    break;
                case 19:
                    f3 = (float) abstractC0715c.t();
                    break;
                case 20:
                    c0409bF = AbstractC0701d.f(abstractC0715c, c0307h, false);
                    break;
                case 21:
                    strX3 = abstractC0715c.x();
                    break;
                case 22:
                    zR = abstractC0715c.r();
                    break;
                default:
                    abstractC0715c.D();
                    abstractC0715c.E();
                    break;
            }
        }
        abstractC0715c.f();
        ArrayList arrayList6 = new ArrayList();
        if (fT2 > 0.0f) {
            arrayList = arrayList3;
            arrayList2 = arrayList6;
            arrayList2.add(new com.airbnb.lottie.value.a(c0307h, fValueOf2, fValueOf2, null, 0.0f, Float.valueOf(fT2)));
            f2 = 0.0f;
        } else {
            arrayList = arrayList3;
            arrayList2 = arrayList6;
            f2 = 0.0f;
        }
        if (f3 <= f2) {
            f3 = c0307h.f();
        }
        arrayList2.add(new com.airbnb.lottie.value.a(c0307h, fValueOf, fValueOf, null, fT2, Float.valueOf(f3)));
        arrayList2.add(new com.airbnb.lottie.value.a(c0307h, fValueOf2, fValueOf2, null, f3, Float.valueOf(Float.MAX_VALUE)));
        if (strX2.endsWith(".ai") || "ai".equals(strX3)) {
            c0307h.a("Convert your Illustrator layers to shape layers.");
        }
        return new C0435e(arrayList4, c0307h, strX2, jU, aVar, jU2, strX, arrayList, lVarG, iU, iU2, color, fT, fT3, iU3, iU4, jVarD, kVarA, arrayList2, bVar2, c0409bF, zR, c0425aB, c0707jB);
    }
}
