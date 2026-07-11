package n;

import android.graphics.Rect;
import androidx.collection.LongSparseArray;
import androidx.collection.SparseArrayCompat;
import com.miui.circulate.device.api.Column;
import com.miui.circulate.device.api.Constant;
import com.miui.maml.folme.AnimatedProperty;
import com.xiaomi.onetrack.util.aa;
import d.C0307h;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import l.C0435e;
import o.AbstractC0715c;
import p.AbstractC0724d;

/* JADX INFO: loaded from: classes.dex */
public abstract class w {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final AbstractC0715c.a f6230a = AbstractC0715c.a.a(AnimatedProperty.PROPERTY_NAME_W, AnimatedProperty.PROPERTY_NAME_H, "ip", "op", "fr", Constant.KeyValue.VALUE_COLUMN, "layers", "assets", "fonts", "chars", "markers");

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static AbstractC0715c.a f6231b = AbstractC0715c.a.a(Column.ID, "layers", AnimatedProperty.PROPERTY_NAME_W, AnimatedProperty.PROPERTY_NAME_H, "p", "u");

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final AbstractC0715c.a f6232c = AbstractC0715c.a.a("list");

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static final AbstractC0715c.a f6233d = AbstractC0715c.a.a("cm", "tm", "dr");

    public static C0307h a(AbstractC0715c abstractC0715c) {
        HashMap map;
        ArrayList arrayList;
        AbstractC0715c abstractC0715c2 = abstractC0715c;
        float fE = p.h.e();
        LongSparseArray longSparseArray = new LongSparseArray();
        ArrayList arrayList2 = new ArrayList();
        HashMap map2 = new HashMap();
        HashMap map3 = new HashMap();
        HashMap map4 = new HashMap();
        ArrayList arrayList3 = new ArrayList();
        SparseArrayCompat sparseArrayCompat = new SparseArrayCompat();
        C0307h c0307h = new C0307h();
        abstractC0715c.d();
        float fT = 0.0f;
        float fT2 = 0.0f;
        float fT3 = 0.0f;
        int iU = 0;
        int iU2 = 0;
        while (abstractC0715c.n()) {
            switch (abstractC0715c2.C(f6230a)) {
                case 0:
                    iU = abstractC0715c.u();
                    continue;
                    abstractC0715c2 = abstractC0715c;
                    break;
                case 1:
                    iU2 = abstractC0715c.u();
                    continue;
                    abstractC0715c2 = abstractC0715c;
                    break;
                case 2:
                    fT = (float) abstractC0715c.t();
                    continue;
                    abstractC0715c2 = abstractC0715c;
                    break;
                case 3:
                    map = map4;
                    arrayList = arrayList3;
                    fT2 = ((float) abstractC0715c.t()) - 0.01f;
                    break;
                case 4:
                    map = map4;
                    arrayList = arrayList3;
                    fT3 = (float) abstractC0715c.t();
                    break;
                case 5:
                    String[] strArrSplit = abstractC0715c.x().split(aa.f3428a);
                    if (!p.h.j(Integer.parseInt(strArrSplit[0]), Integer.parseInt(strArrSplit[1]), Integer.parseInt(strArrSplit[2]), 4, 4, 0)) {
                        c0307h.a("Lottie only supports bodymovin >= 4.4.0");
                        continue;
                    }
                    abstractC0715c2 = abstractC0715c;
                    break;
                case 6:
                    e(abstractC0715c2, c0307h, arrayList2, longSparseArray);
                    continue;
                    abstractC0715c2 = abstractC0715c;
                    break;
                case 7:
                    b(abstractC0715c2, c0307h, map2, map3);
                    continue;
                    abstractC0715c2 = abstractC0715c;
                    break;
                case 8:
                    d(abstractC0715c2, map4);
                    continue;
                    abstractC0715c2 = abstractC0715c;
                    break;
                case 9:
                    c(abstractC0715c2, c0307h, sparseArrayCompat);
                    continue;
                    abstractC0715c2 = abstractC0715c;
                    break;
                case 10:
                    f(abstractC0715c2, arrayList3);
                    continue;
                    abstractC0715c2 = abstractC0715c;
                    break;
                default:
                    abstractC0715c.D();
                    abstractC0715c.E();
                    continue;
                    abstractC0715c2 = abstractC0715c;
                    break;
            }
            map4 = map;
            arrayList3 = arrayList;
            abstractC0715c2 = abstractC0715c;
        }
        c0307h.s(new Rect(0, 0, (int) (iU * fE), (int) (iU2 * fE)), fT, fT2, fT3, arrayList2, longSparseArray, map2, map3, sparseArrayCompat, map4, arrayList3);
        return c0307h;
    }

    public static void b(AbstractC0715c abstractC0715c, C0307h c0307h, Map map, Map map2) {
        abstractC0715c.c();
        while (abstractC0715c.n()) {
            ArrayList arrayList = new ArrayList();
            LongSparseArray longSparseArray = new LongSparseArray();
            abstractC0715c.d();
            int iU = 0;
            int iU2 = 0;
            String strX = null;
            String strX2 = null;
            String strX3 = null;
            while (abstractC0715c.n()) {
                int iC = abstractC0715c.C(f6231b);
                if (iC == 0) {
                    strX = abstractC0715c.x();
                } else if (iC == 1) {
                    abstractC0715c.c();
                    while (abstractC0715c.n()) {
                        C0435e c0435eB = v.b(abstractC0715c, c0307h);
                        longSparseArray.put(c0435eB.d(), c0435eB);
                        arrayList.add(c0435eB);
                    }
                    abstractC0715c.e();
                } else if (iC == 2) {
                    iU = abstractC0715c.u();
                } else if (iC == 3) {
                    iU2 = abstractC0715c.u();
                } else if (iC == 4) {
                    strX2 = abstractC0715c.x();
                } else if (iC != 5) {
                    abstractC0715c.D();
                    abstractC0715c.E();
                } else {
                    strX3 = abstractC0715c.x();
                }
            }
            abstractC0715c.f();
            if (strX2 != null) {
                d.G g2 = new d.G(iU, iU2, strX, strX2, strX3);
                map2.put(g2.d(), g2);
            } else {
                map.put(strX, arrayList);
            }
        }
        abstractC0715c.e();
    }

    public static void c(AbstractC0715c abstractC0715c, C0307h c0307h, SparseArrayCompat sparseArrayCompat) {
        abstractC0715c.c();
        while (abstractC0715c.n()) {
            i.d dVarA = AbstractC0710m.a(abstractC0715c, c0307h);
            sparseArrayCompat.put(dVarA.hashCode(), dVarA);
        }
        abstractC0715c.e();
    }

    public static void d(AbstractC0715c abstractC0715c, Map map) {
        abstractC0715c.d();
        while (abstractC0715c.n()) {
            if (abstractC0715c.C(f6232c) != 0) {
                abstractC0715c.D();
                abstractC0715c.E();
            } else {
                abstractC0715c.c();
                while (abstractC0715c.n()) {
                    i.c cVarA = AbstractC0711n.a(abstractC0715c);
                    map.put(cVarA.b(), cVarA);
                }
                abstractC0715c.e();
            }
        }
        abstractC0715c.f();
    }

    public static void e(AbstractC0715c abstractC0715c, C0307h c0307h, List list, LongSparseArray longSparseArray) {
        abstractC0715c.c();
        int i2 = 0;
        while (abstractC0715c.n()) {
            C0435e c0435eB = v.b(abstractC0715c, c0307h);
            if (c0435eB.f() == C0435e.a.IMAGE) {
                i2++;
            }
            list.add(c0435eB);
            longSparseArray.put(c0435eB.d(), c0435eB);
            if (i2 > 4) {
                AbstractC0724d.c("You have " + i2 + " images. Lottie should primarily be used with shapes. If you are using Adobe Illustrator, convert the Illustrator layers to shape layers.");
            }
        }
        abstractC0715c.e();
    }

    public static void f(AbstractC0715c abstractC0715c, List list) {
        abstractC0715c.c();
        while (abstractC0715c.n()) {
            abstractC0715c.d();
            float fT = 0.0f;
            String strX = null;
            float fT2 = 0.0f;
            while (abstractC0715c.n()) {
                int iC = abstractC0715c.C(f6233d);
                if (iC == 0) {
                    strX = abstractC0715c.x();
                } else if (iC == 1) {
                    fT = (float) abstractC0715c.t();
                } else if (iC != 2) {
                    abstractC0715c.D();
                    abstractC0715c.E();
                } else {
                    fT2 = (float) abstractC0715c.t();
                }
            }
            abstractC0715c.f();
            list.add(new i.h(strX, fT, fT2));
        }
        abstractC0715c.e();
    }
}
