package f;

import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.DashPathEffect;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.graphics.RectF;
import d.AbstractC0302c;
import d.F;
import d.K;
import e.C0333a;
import g.AbstractC0355a;
import j.C0409b;
import j.C0411d;
import java.util.ArrayList;
import java.util.List;
import l.AbstractC0432b;
import p.AbstractC0727g;

/* JADX INFO: renamed from: f.a, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public abstract class AbstractC0346a implements AbstractC0355a.b, k, e {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final F f4079e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final AbstractC0432b f4080f;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final float[] f4082h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final Paint f4083i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public final AbstractC0355a f4084j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public final AbstractC0355a f4085k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final List f4086l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final AbstractC0355a f4087m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public AbstractC0355a f4088n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public AbstractC0355a f4089o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public float f4090p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public g.c f4091q;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final PathMeasure f4075a = new PathMeasure();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final Path f4076b = new Path();

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final Path f4077c = new Path();

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final RectF f4078d = new RectF();

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final List f4081g = new ArrayList();

    /* JADX INFO: renamed from: f.a$b */
    public static final class b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final List f4092a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final u f4093b;

        public b(u uVar) {
            this.f4092a = new ArrayList();
            this.f4093b = uVar;
        }
    }

    public AbstractC0346a(F f2, AbstractC0432b abstractC0432b, Paint.Cap cap, Paint.Join join, float f3, C0411d c0411d, C0409b c0409b, List list, C0409b c0409b2) {
        C0333a c0333a = new C0333a(1);
        this.f4083i = c0333a;
        this.f4090p = 0.0f;
        this.f4079e = f2;
        this.f4080f = abstractC0432b;
        c0333a.setStyle(Paint.Style.STROKE);
        c0333a.setStrokeCap(cap);
        c0333a.setStrokeJoin(join);
        c0333a.setStrokeMiter(f3);
        this.f4085k = c0411d.a();
        this.f4084j = c0409b.a();
        if (c0409b2 == null) {
            this.f4087m = null;
        } else {
            this.f4087m = c0409b2.a();
        }
        this.f4086l = new ArrayList(list.size());
        this.f4082h = new float[list.size()];
        for (int i2 = 0; i2 < list.size(); i2++) {
            this.f4086l.add(((C0409b) list.get(i2)).a());
        }
        abstractC0432b.j(this.f4085k);
        abstractC0432b.j(this.f4084j);
        for (int i3 = 0; i3 < this.f4086l.size(); i3++) {
            abstractC0432b.j((AbstractC0355a) this.f4086l.get(i3));
        }
        AbstractC0355a abstractC0355a = this.f4087m;
        if (abstractC0355a != null) {
            abstractC0432b.j(abstractC0355a);
        }
        this.f4085k.a(this);
        this.f4084j.a(this);
        for (int i4 = 0; i4 < list.size(); i4++) {
            ((AbstractC0355a) this.f4086l.get(i4)).a(this);
        }
        AbstractC0355a abstractC0355a2 = this.f4087m;
        if (abstractC0355a2 != null) {
            abstractC0355a2.a(this);
        }
        if (abstractC0432b.w() != null) {
            AbstractC0355a abstractC0355aA = abstractC0432b.w().a().a();
            this.f4089o = abstractC0355aA;
            abstractC0355aA.a(this);
            abstractC0432b.j(this.f4089o);
        }
        if (abstractC0432b.y() != null) {
            this.f4091q = new g.c(this, abstractC0432b, abstractC0432b.y());
        }
    }

    @Override // g.AbstractC0355a.b
    public void a() {
        this.f4079e.invalidateSelf();
    }

    /* JADX WARN: Removed duplicated region for block: B:21:0x0055  */
    @Override // f.c
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void b(java.util.List r8, java.util.List r9) {
        /*
            r7 = this;
            int r0 = r8.size()
            int r0 = r0 + (-1)
            r1 = 0
            r2 = r1
        L8:
            if (r0 < 0) goto L22
            java.lang.Object r3 = r8.get(r0)
            f.c r3 = (f.c) r3
            boolean r4 = r3 instanceof f.u
            if (r4 == 0) goto L1f
            f.u r3 = (f.u) r3
            k.s$a r4 = r3.k()
            k.s$a r5 = k.s.a.INDIVIDUALLY
            if (r4 != r5) goto L1f
            r2 = r3
        L1f:
            int r0 = r0 + (-1)
            goto L8
        L22:
            if (r2 == 0) goto L27
            r2.e(r7)
        L27:
            int r8 = r9.size()
            int r8 = r8 + (-1)
            r0 = r1
        L2e:
            if (r8 < 0) goto L6c
            java.lang.Object r3 = r9.get(r8)
            f.c r3 = (f.c) r3
            boolean r4 = r3 instanceof f.u
            if (r4 == 0) goto L55
            r4 = r3
            f.u r4 = (f.u) r4
            k.s$a r5 = r4.k()
            k.s$a r6 = k.s.a.INDIVIDUALLY
            if (r5 != r6) goto L55
            if (r0 == 0) goto L4c
            java.util.List r3 = r7.f4081g
            r3.add(r0)
        L4c:
            f.a$b r0 = new f.a$b
            r0.<init>(r4)
            r4.e(r7)
            goto L69
        L55:
            boolean r4 = r3 instanceof f.m
            if (r4 == 0) goto L69
            if (r0 != 0) goto L60
            f.a$b r0 = new f.a$b
            r0.<init>(r2)
        L60:
            java.util.List r4 = f.AbstractC0346a.b.a(r0)
            f.m r3 = (f.m) r3
            r4.add(r3)
        L69:
            int r8 = r8 + (-1)
            goto L2e
        L6c:
            if (r0 == 0) goto L73
            java.util.List r7 = r7.f4081g
            r7.add(r0)
        L73:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: f.AbstractC0346a.b(java.util.List, java.util.List):void");
    }

    @Override // i.f
    public void e(Object obj, com.airbnb.lottie.value.c cVar) {
        g.c cVar2;
        g.c cVar3;
        g.c cVar4;
        g.c cVar5;
        g.c cVar6;
        if (obj == K.f3790d) {
            this.f4085k.n(cVar);
            return;
        }
        if (obj == K.f3805s) {
            this.f4084j.n(cVar);
            return;
        }
        if (obj == K.f3782K) {
            AbstractC0355a abstractC0355a = this.f4088n;
            if (abstractC0355a != null) {
                this.f4080f.H(abstractC0355a);
            }
            if (cVar == null) {
                this.f4088n = null;
                return;
            }
            g.q qVar = new g.q(cVar);
            this.f4088n = qVar;
            qVar.a(this);
            this.f4080f.j(this.f4088n);
            return;
        }
        if (obj == K.f3796j) {
            AbstractC0355a abstractC0355a2 = this.f4089o;
            if (abstractC0355a2 != null) {
                abstractC0355a2.n(cVar);
                return;
            }
            g.q qVar2 = new g.q(cVar);
            this.f4089o = qVar2;
            qVar2.a(this);
            this.f4080f.j(this.f4089o);
            return;
        }
        if (obj == K.f3791e && (cVar6 = this.f4091q) != null) {
            cVar6.c(cVar);
            return;
        }
        if (obj == K.f3778G && (cVar5 = this.f4091q) != null) {
            cVar5.f(cVar);
            return;
        }
        if (obj == K.f3779H && (cVar4 = this.f4091q) != null) {
            cVar4.d(cVar);
            return;
        }
        if (obj == K.f3780I && (cVar3 = this.f4091q) != null) {
            cVar3.e(cVar);
        } else {
            if (obj != K.f3781J || (cVar2 = this.f4091q) == null) {
                return;
            }
            cVar2.g(cVar);
        }
    }

    @Override // f.e
    public void f(RectF rectF, Matrix matrix, boolean z2) {
        AbstractC0302c.a("StrokeContent#getBounds");
        this.f4076b.reset();
        for (int i2 = 0; i2 < this.f4081g.size(); i2++) {
            b bVar = (b) this.f4081g.get(i2);
            for (int i3 = 0; i3 < bVar.f4092a.size(); i3++) {
                this.f4076b.addPath(((m) bVar.f4092a.get(i3)).d(), matrix);
            }
        }
        this.f4076b.computeBounds(this.f4078d, false);
        float fP = ((g.d) this.f4084j).p();
        RectF rectF2 = this.f4078d;
        float f2 = fP / 2.0f;
        rectF2.set(rectF2.left - f2, rectF2.top - f2, rectF2.right + f2, rectF2.bottom + f2);
        rectF.set(this.f4078d);
        rectF.set(rectF.left - 1.0f, rectF.top - 1.0f, rectF.right + 1.0f, rectF.bottom + 1.0f);
        AbstractC0302c.b("StrokeContent#getBounds");
    }

    public final void g(Matrix matrix) {
        AbstractC0302c.a("StrokeContent#applyDashPattern");
        if (this.f4086l.isEmpty()) {
            AbstractC0302c.b("StrokeContent#applyDashPattern");
            return;
        }
        float fG = p.h.g(matrix);
        for (int i2 = 0; i2 < this.f4086l.size(); i2++) {
            this.f4082h[i2] = ((Float) ((AbstractC0355a) this.f4086l.get(i2)).h()).floatValue();
            if (i2 % 2 == 0) {
                float[] fArr = this.f4082h;
                if (fArr[i2] < 1.0f) {
                    fArr[i2] = 1.0f;
                }
            } else {
                float[] fArr2 = this.f4082h;
                if (fArr2[i2] < 0.1f) {
                    fArr2[i2] = 0.1f;
                }
            }
            float[] fArr3 = this.f4082h;
            fArr3[i2] = fArr3[i2] * fG;
        }
        AbstractC0355a abstractC0355a = this.f4087m;
        this.f4083i.setPathEffect(new DashPathEffect(this.f4082h, abstractC0355a == null ? 0.0f : fG * ((Float) abstractC0355a.h()).floatValue()));
        AbstractC0302c.b("StrokeContent#applyDashPattern");
    }

    @Override // f.e
    public void h(Canvas canvas, Matrix matrix, int i2) {
        AbstractC0302c.a("StrokeContent#draw");
        if (p.h.h(matrix)) {
            AbstractC0302c.b("StrokeContent#draw");
            return;
        }
        this.f4083i.setAlpha(AbstractC0727g.c((int) ((((i2 / 255.0f) * ((g.f) this.f4085k).p()) / 100.0f) * 255.0f), 0, 255));
        this.f4083i.setStrokeWidth(((g.d) this.f4084j).p() * p.h.g(matrix));
        if (this.f4083i.getStrokeWidth() <= 0.0f) {
            AbstractC0302c.b("StrokeContent#draw");
            return;
        }
        g(matrix);
        AbstractC0355a abstractC0355a = this.f4088n;
        if (abstractC0355a != null) {
            this.f4083i.setColorFilter((ColorFilter) abstractC0355a.h());
        }
        AbstractC0355a abstractC0355a2 = this.f4089o;
        if (abstractC0355a2 != null) {
            float fFloatValue = ((Float) abstractC0355a2.h()).floatValue();
            if (fFloatValue == 0.0f) {
                this.f4083i.setMaskFilter(null);
            } else if (fFloatValue != this.f4090p) {
                this.f4083i.setMaskFilter(this.f4080f.x(fFloatValue));
            }
            this.f4090p = fFloatValue;
        }
        g.c cVar = this.f4091q;
        if (cVar != null) {
            cVar.b(this.f4083i);
        }
        for (int i3 = 0; i3 < this.f4081g.size(); i3++) {
            b bVar = (b) this.f4081g.get(i3);
            if (bVar.f4093b != null) {
                j(canvas, bVar, matrix);
            } else {
                AbstractC0302c.a("StrokeContent#buildPath");
                this.f4076b.reset();
                for (int size = bVar.f4092a.size() - 1; size >= 0; size--) {
                    this.f4076b.addPath(((m) bVar.f4092a.get(size)).d(), matrix);
                }
                AbstractC0302c.b("StrokeContent#buildPath");
                AbstractC0302c.a("StrokeContent#drawPath");
                canvas.drawPath(this.f4076b, this.f4083i);
                AbstractC0302c.b("StrokeContent#drawPath");
            }
        }
        AbstractC0302c.b("StrokeContent#draw");
    }

    @Override // i.f
    public void i(i.e eVar, int i2, List list, i.e eVar2) {
        AbstractC0727g.k(eVar, i2, list, eVar2, this);
    }

    /* JADX WARN: Removed duplicated region for block: B:32:0x011c  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void j(android.graphics.Canvas r17, f.AbstractC0346a.b r18, android.graphics.Matrix r19) {
        /*
            Method dump skipped, instruction units count: 350
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: f.AbstractC0346a.j(android.graphics.Canvas, f.a$b, android.graphics.Matrix):void");
    }
}
