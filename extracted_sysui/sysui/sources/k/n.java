package k;

import android.graphics.PointF;
import i.C0402a;
import java.util.ArrayList;
import java.util.List;
import p.AbstractC0724d;
import p.AbstractC0727g;

/* JADX INFO: loaded from: classes.dex */
public class n {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final List f4900a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public PointF f4901b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public boolean f4902c;

    public n(PointF pointF, boolean z2, List list) {
        this.f4901b = pointF;
        this.f4902c = z2;
        this.f4900a = new ArrayList(list);
    }

    public List a() {
        return this.f4900a;
    }

    public PointF b() {
        return this.f4901b;
    }

    public void c(n nVar, n nVar2, float f2) {
        if (this.f4901b == null) {
            this.f4901b = new PointF();
        }
        this.f4902c = nVar.d() || nVar2.d();
        if (nVar.a().size() != nVar2.a().size()) {
            AbstractC0724d.c("Curves must have the same number of control points. Shape 1: " + nVar.a().size() + "\tShape 2: " + nVar2.a().size());
        }
        int iMin = Math.min(nVar.a().size(), nVar2.a().size());
        if (this.f4900a.size() < iMin) {
            for (int size = this.f4900a.size(); size < iMin; size++) {
                this.f4900a.add(new C0402a());
            }
        } else if (this.f4900a.size() > iMin) {
            for (int size2 = this.f4900a.size() - 1; size2 >= iMin; size2--) {
                List list = this.f4900a;
                list.remove(list.size() - 1);
            }
        }
        PointF pointFB = nVar.b();
        PointF pointFB2 = nVar2.b();
        f(AbstractC0727g.i(pointFB.x, pointFB2.x, f2), AbstractC0727g.i(pointFB.y, pointFB2.y, f2));
        for (int size3 = this.f4900a.size() - 1; size3 >= 0; size3--) {
            C0402a c0402a = (C0402a) nVar.a().get(size3);
            C0402a c0402a2 = (C0402a) nVar2.a().get(size3);
            PointF pointFA = c0402a.a();
            PointF pointFB3 = c0402a.b();
            PointF pointFC = c0402a.c();
            PointF pointFA2 = c0402a2.a();
            PointF pointFB4 = c0402a2.b();
            PointF pointFC2 = c0402a2.c();
            ((C0402a) this.f4900a.get(size3)).d(AbstractC0727g.i(pointFA.x, pointFA2.x, f2), AbstractC0727g.i(pointFA.y, pointFA2.y, f2));
            ((C0402a) this.f4900a.get(size3)).e(AbstractC0727g.i(pointFB3.x, pointFB4.x, f2), AbstractC0727g.i(pointFB3.y, pointFB4.y, f2));
            ((C0402a) this.f4900a.get(size3)).f(AbstractC0727g.i(pointFC.x, pointFC2.x, f2), AbstractC0727g.i(pointFC.y, pointFC2.y, f2));
        }
    }

    public boolean d() {
        return this.f4902c;
    }

    public void e(boolean z2) {
        this.f4902c = z2;
    }

    public void f(float f2, float f3) {
        if (this.f4901b == null) {
            this.f4901b = new PointF();
        }
        this.f4901b.set(f2, f3);
    }

    public String toString() {
        return "ShapeData{numCurves=" + this.f4900a.size() + "closed=" + this.f4902c + '}';
    }

    public n() {
        this.f4900a = new ArrayList();
    }
}
