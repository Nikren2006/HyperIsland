package O;

import android.graphics.Matrix;
import android.graphics.Path;
import android.graphics.PointF;
import android.graphics.RectF;

/* JADX INFO: loaded from: classes2.dex */
public class l {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final m[] f529a = new m[4];

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final Matrix[] f530b = new Matrix[4];

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final Matrix[] f531c = new Matrix[4];

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final PointF f532d = new PointF();

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final Path f533e = new Path();

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final Path f534f = new Path();

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final m f535g = new m();

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final float[] f536h = new float[2];

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final float[] f537i = new float[2];

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public final Path f538j = new Path();

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public final Path f539k = new Path();

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public boolean f540l = true;

    public static class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final l f541a = new l();
    }

    public interface b {
        void a(m mVar, Matrix matrix, int i2);

        void b(m mVar, Matrix matrix, int i2);
    }

    public static final class c {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final k f542a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final Path f543b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final RectF f544c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public final b f545d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public final float f546e;

        public c(k kVar, float f2, RectF rectF, b bVar, Path path) {
            this.f545d = bVar;
            this.f542a = kVar;
            this.f546e = f2;
            this.f544c = rectF;
            this.f543b = path;
        }
    }

    public l() {
        for (int i2 = 0; i2 < 4; i2++) {
            this.f529a[i2] = new m();
            this.f530b[i2] = new Matrix();
            this.f531c[i2] = new Matrix();
        }
    }

    public static l k() {
        return a.f541a;
    }

    public final float a(int i2) {
        return ((i2 + 1) % 4) * 90;
    }

    public final void b(c cVar, int i2) {
        this.f536h[0] = this.f529a[i2].k();
        this.f536h[1] = this.f529a[i2].l();
        this.f530b[i2].mapPoints(this.f536h);
        if (i2 == 0) {
            Path path = cVar.f543b;
            float[] fArr = this.f536h;
            path.moveTo(fArr[0], fArr[1]);
        } else {
            Path path2 = cVar.f543b;
            float[] fArr2 = this.f536h;
            path2.lineTo(fArr2[0], fArr2[1]);
        }
        this.f529a[i2].d(this.f530b[i2], cVar.f543b);
        b bVar = cVar.f545d;
        if (bVar != null) {
            bVar.a(this.f529a[i2], this.f530b[i2], i2);
        }
    }

    public final void c(c cVar, int i2) {
        int i3 = (i2 + 1) % 4;
        this.f536h[0] = this.f529a[i2].i();
        this.f536h[1] = this.f529a[i2].j();
        this.f530b[i2].mapPoints(this.f536h);
        this.f537i[0] = this.f529a[i3].k();
        this.f537i[1] = this.f529a[i3].l();
        this.f530b[i3].mapPoints(this.f537i);
        float f2 = this.f536h[0];
        float[] fArr = this.f537i;
        float fMax = Math.max(((float) Math.hypot(f2 - fArr[0], r1[1] - fArr[1])) - 0.001f, 0.0f);
        float fI = i(cVar.f544c, i2);
        this.f535g.n(0.0f, 0.0f);
        f fVarJ = j(i2, cVar.f542a);
        fVarJ.b(fMax, fI, cVar.f546e, this.f535g);
        this.f538j.reset();
        this.f535g.d(this.f531c[i2], this.f538j);
        if (this.f540l && (fVarJ.a() || l(this.f538j, i2) || l(this.f538j, i3))) {
            Path path = this.f538j;
            path.op(path, this.f534f, Path.Op.DIFFERENCE);
            this.f536h[0] = this.f535g.k();
            this.f536h[1] = this.f535g.l();
            this.f531c[i2].mapPoints(this.f536h);
            Path path2 = this.f533e;
            float[] fArr2 = this.f536h;
            path2.moveTo(fArr2[0], fArr2[1]);
            this.f535g.d(this.f531c[i2], this.f533e);
        } else {
            this.f535g.d(this.f531c[i2], cVar.f543b);
        }
        b bVar = cVar.f545d;
        if (bVar != null) {
            bVar.b(this.f535g, this.f531c[i2], i2);
        }
    }

    public void d(k kVar, float f2, RectF rectF, b bVar, Path path) {
        path.rewind();
        this.f533e.rewind();
        this.f534f.rewind();
        this.f534f.addRect(rectF, Path.Direction.CW);
        c cVar = new c(kVar, f2, rectF, bVar, path);
        for (int i2 = 0; i2 < 4; i2++) {
            m(cVar, i2);
            n(i2);
        }
        for (int i3 = 0; i3 < 4; i3++) {
            b(cVar, i3);
            c(cVar, i3);
        }
        path.close();
        this.f533e.close();
        if (this.f533e.isEmpty()) {
            return;
        }
        path.op(this.f533e, Path.Op.UNION);
    }

    public void e(k kVar, float f2, RectF rectF, Path path) {
        d(kVar, f2, rectF, null, path);
    }

    public final void f(int i2, RectF rectF, PointF pointF) {
        if (i2 == 1) {
            pointF.set(rectF.right, rectF.bottom);
            return;
        }
        if (i2 == 2) {
            pointF.set(rectF.left, rectF.bottom);
        } else if (i2 != 3) {
            pointF.set(rectF.right, rectF.top);
        } else {
            pointF.set(rectF.left, rectF.top);
        }
    }

    public final O.c g(int i2, k kVar) {
        return i2 != 1 ? i2 != 2 ? i2 != 3 ? kVar.t() : kVar.r() : kVar.j() : kVar.l();
    }

    public final d h(int i2, k kVar) {
        return i2 != 1 ? i2 != 2 ? i2 != 3 ? kVar.s() : kVar.q() : kVar.i() : kVar.k();
    }

    public final float i(RectF rectF, int i2) {
        float[] fArr = this.f536h;
        m mVar = this.f529a[i2];
        fArr[0] = mVar.f549c;
        fArr[1] = mVar.f550d;
        this.f530b[i2].mapPoints(fArr);
        return (i2 == 1 || i2 == 3) ? Math.abs(rectF.centerX() - this.f536h[0]) : Math.abs(rectF.centerY() - this.f536h[1]);
    }

    public final f j(int i2, k kVar) {
        return i2 != 1 ? i2 != 2 ? i2 != 3 ? kVar.o() : kVar.p() : kVar.n() : kVar.h();
    }

    public final boolean l(Path path, int i2) {
        this.f539k.reset();
        this.f529a[i2].d(this.f530b[i2], this.f539k);
        RectF rectF = new RectF();
        path.computeBounds(rectF, true);
        this.f539k.computeBounds(rectF, true);
        path.op(this.f539k, Path.Op.INTERSECT);
        path.computeBounds(rectF, true);
        if (rectF.isEmpty()) {
            return rectF.width() > 1.0f && rectF.height() > 1.0f;
        }
        return true;
    }

    public final void m(c cVar, int i2) {
        h(i2, cVar.f542a).b(this.f529a[i2], 90.0f, cVar.f546e, cVar.f544c, g(i2, cVar.f542a));
        float fA = a(i2);
        this.f530b[i2].reset();
        f(i2, cVar.f544c, this.f532d);
        Matrix matrix = this.f530b[i2];
        PointF pointF = this.f532d;
        matrix.setTranslate(pointF.x, pointF.y);
        this.f530b[i2].preRotate(fA);
    }

    public final void n(int i2) {
        this.f536h[0] = this.f529a[i2].i();
        this.f536h[1] = this.f529a[i2].j();
        this.f530b[i2].mapPoints(this.f536h);
        float fA = a(i2);
        this.f531c[i2].reset();
        Matrix matrix = this.f531c[i2];
        float[] fArr = this.f536h;
        matrix.setTranslate(fArr[0], fArr[1]);
        this.f531c[i2].preRotate(fA);
    }
}
