package f;

import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import d.F;
import e.C0333a;
import g.AbstractC0355a;
import java.util.ArrayList;
import java.util.List;
import l.AbstractC0432b;

/* JADX INFO: loaded from: classes.dex */
public class d implements e, m, AbstractC0355a.b, i.f {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Paint f4095a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final RectF f4096b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final Matrix f4097c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final Path f4098d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final RectF f4099e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final String f4100f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final boolean f4101g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final List f4102h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final F f4103i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public List f4104j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public g.p f4105k;

    public d(F f2, AbstractC0432b abstractC0432b, k.p pVar) {
        this(f2, abstractC0432b, pVar.c(), pVar.d(), g(f2, abstractC0432b, pVar.b()), j(pVar.b()));
    }

    public static List g(F f2, AbstractC0432b abstractC0432b, List list) {
        ArrayList arrayList = new ArrayList(list.size());
        for (int i2 = 0; i2 < list.size(); i2++) {
            c cVarA = ((k.c) list.get(i2)).a(f2, abstractC0432b);
            if (cVarA != null) {
                arrayList.add(cVarA);
            }
        }
        return arrayList;
    }

    public static j.l j(List list) {
        for (int i2 = 0; i2 < list.size(); i2++) {
            k.c cVar = (k.c) list.get(i2);
            if (cVar instanceof j.l) {
                return (j.l) cVar;
            }
        }
        return null;
    }

    @Override // g.AbstractC0355a.b
    public void a() {
        this.f4103i.invalidateSelf();
    }

    @Override // f.c
    public void b(List list, List list2) {
        ArrayList arrayList = new ArrayList(list.size() + this.f4102h.size());
        arrayList.addAll(list);
        for (int size = this.f4102h.size() - 1; size >= 0; size--) {
            c cVar = (c) this.f4102h.get(size);
            cVar.b(arrayList, this.f4102h.subList(0, size));
            arrayList.add(cVar);
        }
    }

    @Override // f.m
    public Path d() {
        this.f4097c.reset();
        g.p pVar = this.f4105k;
        if (pVar != null) {
            this.f4097c.set(pVar.f());
        }
        this.f4098d.reset();
        if (this.f4101g) {
            return this.f4098d;
        }
        for (int size = this.f4102h.size() - 1; size >= 0; size--) {
            c cVar = (c) this.f4102h.get(size);
            if (cVar instanceof m) {
                this.f4098d.addPath(((m) cVar).d(), this.f4097c);
            }
        }
        return this.f4098d;
    }

    @Override // i.f
    public void e(Object obj, com.airbnb.lottie.value.c cVar) {
        g.p pVar = this.f4105k;
        if (pVar != null) {
            pVar.c(obj, cVar);
        }
    }

    @Override // f.e
    public void f(RectF rectF, Matrix matrix, boolean z2) {
        this.f4097c.set(matrix);
        g.p pVar = this.f4105k;
        if (pVar != null) {
            this.f4097c.preConcat(pVar.f());
        }
        this.f4099e.set(0.0f, 0.0f, 0.0f, 0.0f);
        for (int size = this.f4102h.size() - 1; size >= 0; size--) {
            c cVar = (c) this.f4102h.get(size);
            if (cVar instanceof e) {
                ((e) cVar).f(this.f4099e, this.f4097c, z2);
                rectF.union(this.f4099e);
            }
        }
    }

    @Override // f.c
    public String getName() {
        return this.f4100f;
    }

    @Override // f.e
    public void h(Canvas canvas, Matrix matrix, int i2) {
        if (this.f4101g) {
            return;
        }
        this.f4097c.set(matrix);
        g.p pVar = this.f4105k;
        if (pVar != null) {
            this.f4097c.preConcat(pVar.f());
            i2 = (int) (((((this.f4105k.h() == null ? 100 : ((Integer) this.f4105k.h().h()).intValue()) / 100.0f) * i2) / 255.0f) * 255.0f);
        }
        boolean z2 = this.f4103i.i0() && m() && i2 != 255;
        if (z2) {
            this.f4096b.set(0.0f, 0.0f, 0.0f, 0.0f);
            f(this.f4096b, this.f4097c, true);
            this.f4095a.setAlpha(i2);
            p.h.m(canvas, this.f4096b, this.f4095a);
        }
        if (z2) {
            i2 = 255;
        }
        for (int size = this.f4102h.size() - 1; size >= 0; size--) {
            Object obj = this.f4102h.get(size);
            if (obj instanceof e) {
                ((e) obj).h(canvas, this.f4097c, i2);
            }
        }
        if (z2) {
            canvas.restore();
        }
    }

    @Override // i.f
    public void i(i.e eVar, int i2, List list, i.e eVar2) {
        if (eVar.g(getName(), i2) || "__container".equals(getName())) {
            if (!"__container".equals(getName())) {
                eVar2 = eVar2.a(getName());
                if (eVar.c(getName(), i2)) {
                    list.add(eVar2.i(this));
                }
            }
            if (eVar.h(getName(), i2)) {
                int iE = i2 + eVar.e(getName(), i2);
                for (int i3 = 0; i3 < this.f4102h.size(); i3++) {
                    c cVar = (c) this.f4102h.get(i3);
                    if (cVar instanceof i.f) {
                        ((i.f) cVar).i(eVar, iE, list, eVar2);
                    }
                }
            }
        }
    }

    public List k() {
        if (this.f4104j == null) {
            this.f4104j = new ArrayList();
            for (int i2 = 0; i2 < this.f4102h.size(); i2++) {
                c cVar = (c) this.f4102h.get(i2);
                if (cVar instanceof m) {
                    this.f4104j.add((m) cVar);
                }
            }
        }
        return this.f4104j;
    }

    public Matrix l() {
        g.p pVar = this.f4105k;
        if (pVar != null) {
            return pVar.f();
        }
        this.f4097c.reset();
        return this.f4097c;
    }

    public final boolean m() {
        int i2 = 0;
        for (int i3 = 0; i3 < this.f4102h.size(); i3++) {
            if ((this.f4102h.get(i3) instanceof e) && (i2 = i2 + 1) >= 2) {
                return true;
            }
        }
        return false;
    }

    public d(F f2, AbstractC0432b abstractC0432b, String str, boolean z2, List list, j.l lVar) {
        this.f4095a = new C0333a();
        this.f4096b = new RectF();
        this.f4097c = new Matrix();
        this.f4098d = new Path();
        this.f4099e = new RectF();
        this.f4100f = str;
        this.f4103i = f2;
        this.f4101g = z2;
        this.f4102h = list;
        if (lVar != null) {
            g.p pVarB = lVar.b();
            this.f4105k = pVarB;
            pVarB.a(abstractC0432b);
            this.f4105k.b(this);
        }
        ArrayList arrayList = new ArrayList();
        for (int size = list.size() - 1; size >= 0; size--) {
            c cVar = (c) list.get(size);
            if (cVar instanceof j) {
                arrayList.add((j) cVar);
            }
        }
        for (int size2 = arrayList.size() - 1; size2 >= 0; size2--) {
            ((j) arrayList.get(size2)).g(list.listIterator(list.size()));
        }
    }
}
