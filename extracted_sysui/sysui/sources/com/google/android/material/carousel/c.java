package com.google.android.material.carousel;

import androidx.core.math.MathUtils;
import com.google.android.material.carousel.b;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import u.AbstractC0743a;

/* JADX INFO: loaded from: classes2.dex */
public class c {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final b f1800a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final List f1801b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final List f1802c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final float[] f1803d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final float[] f1804e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final float f1805f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final float f1806g;

    public c(b bVar, List list, List list2) {
        this.f1800a = bVar;
        this.f1801b = Collections.unmodifiableList(list);
        this.f1802c = Collections.unmodifiableList(list2);
        float f2 = ((b) list.get(list.size() - 1)).c().f1794a - bVar.c().f1794a;
        this.f1805f = f2;
        float f3 = bVar.j().f1794a - ((b) list2.get(list2.size() - 1)).j().f1794a;
        this.f1806g = f3;
        this.f1803d = m(f2, list, true);
        this.f1804e = m(f3, list2, false);
    }

    public static int b(b bVar, float f2) {
        for (int i2 = bVar.i(); i2 < bVar.g().size(); i2++) {
            if (f2 == ((b.c) bVar.g().get(i2)).f1796c) {
                return i2;
            }
        }
        return bVar.g().size() - 1;
    }

    public static int c(b bVar) {
        for (int i2 = 0; i2 < bVar.g().size(); i2++) {
            if (!((b.c) bVar.g().get(i2)).f1798e) {
                return i2;
            }
        }
        return -1;
    }

    public static int d(b bVar, float f2) {
        for (int iB = bVar.b() - 1; iB >= 0; iB--) {
            if (f2 == ((b.c) bVar.g().get(iB)).f1796c) {
                return iB;
            }
        }
        return 0;
    }

    public static int e(b bVar) {
        for (int size = bVar.g().size() - 1; size >= 0; size--) {
            if (!((b.c) bVar.g().get(size)).f1798e) {
                return size;
            }
        }
        return -1;
    }

    public static c f(z.b bVar, b bVar2) {
        return new c(bVar2, p(bVar, bVar2), n(bVar, bVar2));
    }

    public static float[] m(float f2, List list, boolean z2) {
        int size = list.size();
        float[] fArr = new float[size];
        int i2 = 1;
        while (i2 < size) {
            int i3 = i2 - 1;
            b bVar = (b) list.get(i3);
            b bVar2 = (b) list.get(i2);
            fArr[i2] = i2 == size + (-1) ? 1.0f : fArr[i3] + ((z2 ? bVar2.c().f1794a - bVar.c().f1794a : bVar.j().f1794a - bVar2.j().f1794a) / f2);
            i2++;
        }
        return fArr;
    }

    public static List n(z.b bVar, b bVar2) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(bVar2);
        int iE = e(bVar2);
        if (!r(bVar, bVar2) && iE != -1) {
            int i2 = iE - bVar2.i();
            float fA = bVar.c() ? bVar.a() : bVar.getContainerHeight();
            float f2 = bVar2.c().f1795b - (bVar2.c().f1797d / 2.0f);
            float f3 = 0.0f;
            if (i2 <= 0 && bVar2.h().f1799f > 0.0f) {
                arrayList.add(u(bVar2, f2 - bVar2.h().f1799f, fA));
                return arrayList;
            }
            int i3 = 0;
            while (i3 < i2) {
                b bVar3 = (b) arrayList.get(arrayList.size() - 1);
                int i4 = iE - i3;
                float f4 = f3 + ((b.c) bVar2.g().get(i4)).f1799f;
                int i5 = i4 + 1;
                arrayList.add(t(bVar3, iE, i5 < bVar2.g().size() ? d(bVar3, ((b.c) bVar2.g().get(i5)).f1796c) + 1 : 0, f2 - f4, bVar2.b() + i3 + 1, bVar2.i() + i3 + 1, fA));
                i3++;
                f3 = f4;
            }
        }
        return arrayList;
    }

    public static float[] o(List list, float f2, float[] fArr) {
        int size = list.size();
        float f3 = fArr[0];
        int i2 = 1;
        while (i2 < size) {
            float f4 = fArr[i2];
            if (f2 <= f4) {
                return new float[]{AbstractC0743a.b(0.0f, 1.0f, f3, f4, f2), i2 - 1, i2};
            }
            i2++;
            f3 = f4;
        }
        return new float[]{0.0f, 0.0f, 0.0f};
    }

    public static List p(z.b bVar, b bVar2) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(bVar2);
        int iC = c(bVar2);
        if (!q(bVar2) && iC != -1) {
            int iB = bVar2.b() - iC;
            float fA = bVar.c() ? bVar.a() : bVar.getContainerHeight();
            float f2 = bVar2.c().f1795b - (bVar2.c().f1797d / 2.0f);
            float f3 = 0.0f;
            if (iB <= 0 && bVar2.a().f1799f > 0.0f) {
                arrayList.add(u(bVar2, f2 + bVar2.a().f1799f, fA));
                return arrayList;
            }
            int i2 = 0;
            while (i2 < iB) {
                b bVar3 = (b) arrayList.get(arrayList.size() - 1);
                int i3 = iC + i2;
                int size = bVar2.g().size() - 1;
                float f4 = f3 + ((b.c) bVar2.g().get(i3)).f1799f;
                arrayList.add(t(bVar3, iC, i3 - 1 >= 0 ? b(bVar3, ((b.c) bVar2.g().get(r3)).f1796c) - 1 : size, f2 + f4, (bVar2.b() - i2) - 1, (bVar2.i() - i2) - 1, fA));
                i2++;
                f3 = f4;
            }
        }
        return arrayList;
    }

    public static boolean q(b bVar) {
        return bVar.a().f1795b - (bVar.a().f1797d / 2.0f) >= 0.0f && bVar.a() == bVar.d();
    }

    public static boolean r(z.b bVar, b bVar2) {
        int containerHeight = bVar.getContainerHeight();
        if (bVar.c()) {
            containerHeight = bVar.a();
        }
        return bVar2.h().f1795b + (bVar2.h().f1797d / 2.0f) <= ((float) containerHeight) && bVar2.h() == bVar2.k();
    }

    public static b s(List list, float f2, float[] fArr) {
        float[] fArrO = o(list, f2, fArr);
        return b.l((b) list.get((int) fArrO[1]), (b) list.get((int) fArrO[2]), fArrO[0]);
    }

    public static b t(b bVar, int i2, int i3, float f2, int i4, int i5, float f3) {
        ArrayList arrayList = new ArrayList(bVar.g());
        arrayList.add(i3, (b.c) arrayList.remove(i2));
        b.C0054b c0054b = new b.C0054b(bVar.f(), f3);
        int i6 = 0;
        while (i6 < arrayList.size()) {
            b.c cVar = (b.c) arrayList.get(i6);
            float f4 = cVar.f1797d;
            c0054b.e(f2 + (f4 / 2.0f), cVar.f1796c, f4, i6 >= i4 && i6 <= i5, cVar.f1798e, cVar.f1799f);
            f2 += cVar.f1797d;
            i6++;
        }
        return c0054b.h();
    }

    public static b u(b bVar, float f2, float f3) {
        return t(bVar, 0, 0, f2, bVar.b(), bVar.i(), f3);
    }

    public final b a(List list, float f2, float[] fArr) {
        float[] fArrO = o(list, f2, fArr);
        return fArrO[0] > 0.5f ? (b) list.get((int) fArrO[2]) : (b) list.get((int) fArrO[1]);
    }

    public b g() {
        return this.f1800a;
    }

    public b h() {
        return (b) this.f1802c.get(r1.size() - 1);
    }

    public Map i(int i2, int i3, int i4, boolean z2) {
        float f2 = this.f1800a.f();
        HashMap map = new HashMap();
        int i5 = 0;
        int i6 = 0;
        while (true) {
            if (i5 >= i2) {
                break;
            }
            int i7 = z2 ? (i2 - i5) - 1 : i5;
            if (i7 * f2 * (z2 ? -1 : 1) > i4 - this.f1806g || i5 >= i2 - this.f1802c.size()) {
                Integer numValueOf = Integer.valueOf(i7);
                List list = this.f1802c;
                map.put(numValueOf, (b) list.get(MathUtils.clamp(i6, 0, list.size() - 1)));
                i6++;
            }
            i5++;
        }
        int i8 = 0;
        for (int i9 = i2 - 1; i9 >= 0; i9--) {
            int i10 = z2 ? (i2 - i9) - 1 : i9;
            if (i10 * f2 * (z2 ? -1 : 1) < i3 + this.f1805f || i9 < this.f1801b.size()) {
                Integer numValueOf2 = Integer.valueOf(i10);
                List list2 = this.f1801b;
                map.put(numValueOf2, (b) list2.get(MathUtils.clamp(i8, 0, list2.size() - 1)));
                i8++;
            }
        }
        return map;
    }

    public b j(float f2, float f3, float f4) {
        return k(f2, f3, f4, false);
    }

    public b k(float f2, float f3, float f4, boolean z2) {
        float fB;
        List list;
        float[] fArr;
        float f5 = this.f1805f + f3;
        float f6 = f4 - this.f1806g;
        if (f2 < f5) {
            fB = AbstractC0743a.b(1.0f, 0.0f, f3, f5, f2);
            list = this.f1801b;
            fArr = this.f1803d;
        } else {
            if (f2 <= f6) {
                return this.f1800a;
            }
            fB = AbstractC0743a.b(0.0f, 1.0f, f6, f4, f2);
            list = this.f1802c;
            fArr = this.f1804e;
        }
        return z2 ? a(list, fB, fArr) : s(list, fB, fArr);
    }

    public b l() {
        return (b) this.f1801b.get(r1.size() - 1);
    }
}
