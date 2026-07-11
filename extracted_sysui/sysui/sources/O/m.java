package O;

import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Path;
import android.graphics.RectF;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public class m {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public float f547a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public float f548b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public float f549c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public float f550d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public float f551e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public float f552f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final List f553g = new ArrayList();

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final List f554h = new ArrayList();

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public boolean f555i;

    public class a extends g {

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final /* synthetic */ List f556c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public final /* synthetic */ Matrix f557d;

        public a(List list, Matrix matrix) {
            this.f556c = list;
            this.f557d = matrix;
        }

        @Override // O.m.g
        public void b(Matrix matrix, N.a aVar, int i2, Canvas canvas) {
            Iterator it = this.f556c.iterator();
            while (it.hasNext()) {
                ((g) it.next()).b(this.f557d, aVar, i2, canvas);
            }
        }
    }

    public static class b extends g {

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final d f559c;

        public b(d dVar) {
            this.f559c = dVar;
        }

        @Override // O.m.g
        public void b(Matrix matrix, N.a aVar, int i2, Canvas canvas) {
            aVar.a(canvas, matrix, new RectF(this.f559c.k(), this.f559c.o(), this.f559c.l(), this.f559c.j()), i2, this.f559c.m(), this.f559c.n());
        }
    }

    public static class c extends g {

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final e f560c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public final float f561d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public final float f562e;

        public c(e eVar, float f2, float f3) {
            this.f560c = eVar;
            this.f561d = f2;
            this.f562e = f3;
        }

        @Override // O.m.g
        public void b(Matrix matrix, N.a aVar, int i2, Canvas canvas) {
            RectF rectF = new RectF(0.0f, 0.0f, (float) Math.hypot(this.f560c.f571c - this.f562e, this.f560c.f570b - this.f561d), 0.0f);
            this.f574a.set(matrix);
            this.f574a.preTranslate(this.f561d, this.f562e);
            this.f574a.preRotate(c());
            aVar.b(canvas, this.f574a, rectF, i2);
        }

        public float c() {
            return (float) Math.toDegrees(Math.atan((this.f560c.f571c - this.f562e) / (this.f560c.f570b - this.f561d)));
        }
    }

    public static class d extends f {

        /* JADX INFO: renamed from: h, reason: collision with root package name */
        public static final RectF f563h = new RectF();

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public float f564b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public float f565c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public float f566d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public float f567e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        public float f568f;

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        public float f569g;

        public d(float f2, float f3, float f4, float f5) {
            q(f2);
            u(f3);
            r(f4);
            p(f5);
        }

        @Override // O.m.f
        public void a(Matrix matrix, Path path) {
            Matrix matrix2 = this.f572a;
            matrix.invert(matrix2);
            path.transform(matrix2);
            RectF rectF = f563h;
            rectF.set(k(), o(), l(), j());
            path.arcTo(rectF, m(), n(), false);
            path.transform(matrix);
        }

        public final float j() {
            return this.f567e;
        }

        public final float k() {
            return this.f564b;
        }

        public final float l() {
            return this.f566d;
        }

        public final float m() {
            return this.f568f;
        }

        public final float n() {
            return this.f569g;
        }

        public final float o() {
            return this.f565c;
        }

        public final void p(float f2) {
            this.f567e = f2;
        }

        public final void q(float f2) {
            this.f564b = f2;
        }

        public final void r(float f2) {
            this.f566d = f2;
        }

        public final void s(float f2) {
            this.f568f = f2;
        }

        public final void t(float f2) {
            this.f569g = f2;
        }

        public final void u(float f2) {
            this.f565c = f2;
        }
    }

    public static class e extends f {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public float f570b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public float f571c;

        @Override // O.m.f
        public void a(Matrix matrix, Path path) {
            Matrix matrix2 = this.f572a;
            matrix.invert(matrix2);
            path.transform(matrix2);
            path.lineTo(this.f570b, this.f571c);
            path.transform(matrix);
        }
    }

    public static abstract class f {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final Matrix f572a = new Matrix();

        public abstract void a(Matrix matrix, Path path);
    }

    public static abstract class g {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public static final Matrix f573b = new Matrix();

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final Matrix f574a = new Matrix();

        public final void a(N.a aVar, int i2, Canvas canvas) {
            b(f573b, aVar, i2, canvas);
        }

        public abstract void b(Matrix matrix, N.a aVar, int i2, Canvas canvas);
    }

    public m() {
        n(0.0f, 0.0f);
    }

    public void a(float f2, float f3, float f4, float f5, float f6, float f7) {
        d dVar = new d(f2, f3, f4, f5);
        dVar.s(f6);
        dVar.t(f7);
        this.f553g.add(dVar);
        b bVar = new b(dVar);
        float f8 = f6 + f7;
        boolean z2 = f7 < 0.0f;
        if (z2) {
            f6 = (f6 + 180.0f) % 360.0f;
        }
        c(bVar, f6, z2 ? (180.0f + f8) % 360.0f : f8);
        double d2 = f8;
        r(((f2 + f4) * 0.5f) + (((f4 - f2) / 2.0f) * ((float) Math.cos(Math.toRadians(d2)))));
        s(((f3 + f5) * 0.5f) + (((f5 - f3) / 2.0f) * ((float) Math.sin(Math.toRadians(d2)))));
    }

    public final void b(float f2) {
        if (g() == f2) {
            return;
        }
        float fG = ((f2 - g()) + 360.0f) % 360.0f;
        if (fG > 180.0f) {
            return;
        }
        d dVar = new d(i(), j(), i(), j());
        dVar.s(g());
        dVar.t(fG);
        this.f554h.add(new b(dVar));
        p(f2);
    }

    public final void c(g gVar, float f2, float f3) {
        b(f2);
        this.f554h.add(gVar);
        p(f3);
    }

    public void d(Matrix matrix, Path path) {
        int size = this.f553g.size();
        for (int i2 = 0; i2 < size; i2++) {
            ((f) this.f553g.get(i2)).a(matrix, path);
        }
    }

    public boolean e() {
        return this.f555i;
    }

    public g f(Matrix matrix) {
        b(h());
        return new a(new ArrayList(this.f554h), new Matrix(matrix));
    }

    public final float g() {
        return this.f551e;
    }

    public final float h() {
        return this.f552f;
    }

    public float i() {
        return this.f549c;
    }

    public float j() {
        return this.f550d;
    }

    public float k() {
        return this.f547a;
    }

    public float l() {
        return this.f548b;
    }

    public void m(float f2, float f3) {
        e eVar = new e();
        eVar.f570b = f2;
        eVar.f571c = f3;
        this.f553g.add(eVar);
        c cVar = new c(eVar, i(), j());
        c(cVar, cVar.c() + 270.0f, cVar.c() + 270.0f);
        r(f2);
        s(f3);
    }

    public void n(float f2, float f3) {
        o(f2, f3, 270.0f, 0.0f);
    }

    public void o(float f2, float f3, float f4, float f5) {
        t(f2);
        u(f3);
        r(f2);
        s(f3);
        p(f4);
        q((f4 + f5) % 360.0f);
        this.f553g.clear();
        this.f554h.clear();
        this.f555i = false;
    }

    public final void p(float f2) {
        this.f551e = f2;
    }

    public final void q(float f2) {
        this.f552f = f2;
    }

    public final void r(float f2) {
        this.f549c = f2;
    }

    public final void s(float f2) {
        this.f550d = f2;
    }

    public final void t(float f2) {
        this.f547a = f2;
    }

    public final void u(float f2) {
        this.f548b = f2;
    }
}
