package com.google.android.material.carousel;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import u.AbstractC0743a;

/* JADX INFO: loaded from: classes2.dex */
public final class b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final float f1781a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final List f1782b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final int f1783c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final int f1784d;

    /* JADX INFO: renamed from: com.google.android.material.carousel.b$b, reason: collision with other inner class name */
    public static final class C0054b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final float f1785a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final float f1786b;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public c f1788d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public c f1789e;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final List f1787c = new ArrayList();

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        public int f1790f = -1;

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        public int f1791g = -1;

        /* JADX INFO: renamed from: h, reason: collision with root package name */
        public float f1792h = 0.0f;

        /* JADX INFO: renamed from: i, reason: collision with root package name */
        public int f1793i = -1;

        public C0054b(float f2, float f3) {
            this.f1785a = f2;
            this.f1786b = f3;
        }

        public static float i(float f2, float f3, int i2, int i3) {
            return (f2 - (i2 * f3)) + (i3 * f3);
        }

        public C0054b a(float f2, float f3, float f4) {
            return d(f2, f3, f4, false, true);
        }

        public C0054b b(float f2, float f3, float f4) {
            return c(f2, f3, f4, false);
        }

        public C0054b c(float f2, float f3, float f4, boolean z2) {
            return d(f2, f3, f4, z2, false);
        }

        public C0054b d(float f2, float f3, float f4, boolean z2, boolean z3) {
            float fAbs;
            float f5 = f4 / 2.0f;
            float f6 = f2 - f5;
            float f7 = f5 + f2;
            float f8 = this.f1786b;
            if (f7 > f8) {
                fAbs = Math.abs(f7 - Math.max(f7 - f4, f8));
            } else {
                fAbs = 0.0f;
                if (f6 < 0.0f) {
                    fAbs = Math.abs(f6 - Math.min(f6 + f4, 0.0f));
                }
            }
            return e(f2, f3, f4, z2, z3, fAbs);
        }

        public C0054b e(float f2, float f3, float f4, boolean z2, boolean z3, float f5) {
            if (f4 <= 0.0f) {
                return this;
            }
            if (z3) {
                if (z2) {
                    throw new IllegalArgumentException("Anchor keylines cannot be focal.");
                }
                int i2 = this.f1793i;
                if (i2 != -1 && i2 != 0) {
                    throw new IllegalArgumentException("Anchor keylines must be either the first or last keyline.");
                }
                this.f1793i = this.f1787c.size();
            }
            c cVar = new c(Float.MIN_VALUE, f2, f3, f4, z3, f5);
            if (z2) {
                if (this.f1788d == null) {
                    this.f1788d = cVar;
                    this.f1790f = this.f1787c.size();
                }
                if (this.f1791g != -1 && this.f1787c.size() - this.f1791g > 1) {
                    throw new IllegalArgumentException("Keylines marked as focal must be placed next to each other. There cannot be non-focal keylines between focal keylines.");
                }
                if (f4 != this.f1788d.f1797d) {
                    throw new IllegalArgumentException("Keylines that are marked as focal must all have the same masked item size.");
                }
                this.f1789e = cVar;
                this.f1791g = this.f1787c.size();
            } else {
                if (this.f1788d == null && cVar.f1797d < this.f1792h) {
                    throw new IllegalArgumentException("Keylines before the first focal keyline must be ordered by incrementing masked item size.");
                }
                if (this.f1789e != null && cVar.f1797d > this.f1792h) {
                    throw new IllegalArgumentException("Keylines after the last focal keyline must be ordered by decreasing masked item size.");
                }
            }
            this.f1792h = cVar.f1797d;
            this.f1787c.add(cVar);
            return this;
        }

        public C0054b f(float f2, float f3, float f4, int i2) {
            return g(f2, f3, f4, i2, false);
        }

        public C0054b g(float f2, float f3, float f4, int i2, boolean z2) {
            if (i2 > 0 && f4 > 0.0f) {
                for (int i3 = 0; i3 < i2; i3++) {
                    c((i3 * f4) + f2, f3, f4, z2);
                }
            }
            return this;
        }

        public b h() {
            if (this.f1788d == null) {
                throw new IllegalStateException("There must be a keyline marked as focal.");
            }
            ArrayList arrayList = new ArrayList();
            for (int i2 = 0; i2 < this.f1787c.size(); i2++) {
                c cVar = (c) this.f1787c.get(i2);
                arrayList.add(new c(i(this.f1788d.f1795b, this.f1785a, this.f1790f, i2), cVar.f1795b, cVar.f1796c, cVar.f1797d, cVar.f1798e, cVar.f1799f));
            }
            return new b(this.f1785a, arrayList, this.f1790f, this.f1791g);
        }
    }

    public static final class c {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final float f1794a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final float f1795b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final float f1796c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public final float f1797d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public final boolean f1798e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        public final float f1799f;

        public c(float f2, float f3, float f4, float f5) {
            this(f2, f3, f4, f5, false, 0.0f);
        }

        public static c a(c cVar, c cVar2, float f2) {
            return new c(AbstractC0743a.a(cVar.f1794a, cVar2.f1794a, f2), AbstractC0743a.a(cVar.f1795b, cVar2.f1795b, f2), AbstractC0743a.a(cVar.f1796c, cVar2.f1796c, f2), AbstractC0743a.a(cVar.f1797d, cVar2.f1797d, f2));
        }

        public c(float f2, float f3, float f4, float f5, boolean z2, float f6) {
            this.f1794a = f2;
            this.f1795b = f3;
            this.f1796c = f4;
            this.f1797d = f5;
            this.f1798e = z2;
            this.f1799f = f6;
        }
    }

    public static b l(b bVar, b bVar2, float f2) {
        if (bVar.f() != bVar2.f()) {
            throw new IllegalArgumentException("Keylines being linearly interpolated must have the same item size.");
        }
        List listG = bVar.g();
        List listG2 = bVar2.g();
        if (listG.size() != listG2.size()) {
            throw new IllegalArgumentException("Keylines being linearly interpolated must have the same number of keylines.");
        }
        ArrayList arrayList = new ArrayList();
        for (int i2 = 0; i2 < bVar.g().size(); i2++) {
            arrayList.add(c.a((c) listG.get(i2), (c) listG2.get(i2), f2));
        }
        return new b(bVar.f(), arrayList, AbstractC0743a.c(bVar.b(), bVar2.b(), f2), AbstractC0743a.c(bVar.i(), bVar2.i(), f2));
    }

    public static b m(b bVar, float f2) {
        C0054b c0054b = new C0054b(bVar.f(), f2);
        float f3 = (f2 - bVar.j().f1795b) - (bVar.j().f1797d / 2.0f);
        int size = bVar.g().size() - 1;
        while (size >= 0) {
            c cVar = (c) bVar.g().get(size);
            c0054b.d(f3 + (cVar.f1797d / 2.0f), cVar.f1796c, cVar.f1797d, size >= bVar.b() && size <= bVar.i(), cVar.f1798e);
            f3 += cVar.f1797d;
            size--;
        }
        return c0054b.h();
    }

    public c a() {
        return (c) this.f1782b.get(this.f1783c);
    }

    public int b() {
        return this.f1783c;
    }

    public c c() {
        return (c) this.f1782b.get(0);
    }

    public c d() {
        for (int i2 = 0; i2 < this.f1782b.size(); i2++) {
            c cVar = (c) this.f1782b.get(i2);
            if (!cVar.f1798e) {
                return cVar;
            }
        }
        return null;
    }

    public List e() {
        return this.f1782b.subList(this.f1783c, this.f1784d + 1);
    }

    public float f() {
        return this.f1781a;
    }

    public List g() {
        return this.f1782b;
    }

    public c h() {
        return (c) this.f1782b.get(this.f1784d);
    }

    public int i() {
        return this.f1784d;
    }

    public c j() {
        return (c) this.f1782b.get(r1.size() - 1);
    }

    public c k() {
        for (int size = this.f1782b.size() - 1; size >= 0; size--) {
            c cVar = (c) this.f1782b.get(size);
            if (!cVar.f1798e) {
                return cVar;
            }
        }
        return null;
    }

    public b(float f2, List list, int i2, int i3) {
        this.f1781a = f2;
        this.f1782b = Collections.unmodifiableList(list);
        this.f1783c = i2;
        this.f1784d = i3;
    }
}
