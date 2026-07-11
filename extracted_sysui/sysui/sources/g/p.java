package g;

import android.graphics.Matrix;
import android.graphics.PointF;
import d.K;
import g.AbstractC0355a;
import java.util.Collections;
import l.AbstractC0432b;

/* JADX INFO: loaded from: classes.dex */
public class p {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Matrix f4309a = new Matrix();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final Matrix f4310b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final Matrix f4311c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final Matrix f4312d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final float[] f4313e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public AbstractC0355a f4314f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public AbstractC0355a f4315g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public AbstractC0355a f4316h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public AbstractC0355a f4317i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public AbstractC0355a f4318j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public d f4319k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public d f4320l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public AbstractC0355a f4321m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public AbstractC0355a f4322n;

    public p(j.l lVar) {
        this.f4314f = lVar.c() == null ? null : lVar.c().a();
        this.f4315g = lVar.f() == null ? null : lVar.f().a();
        this.f4316h = lVar.h() == null ? null : lVar.h().a();
        this.f4317i = lVar.g() == null ? null : lVar.g().a();
        d dVar = lVar.i() == null ? null : (d) lVar.i().a();
        this.f4319k = dVar;
        if (dVar != null) {
            this.f4310b = new Matrix();
            this.f4311c = new Matrix();
            this.f4312d = new Matrix();
            this.f4313e = new float[9];
        } else {
            this.f4310b = null;
            this.f4311c = null;
            this.f4312d = null;
            this.f4313e = null;
        }
        this.f4320l = lVar.j() == null ? null : (d) lVar.j().a();
        if (lVar.e() != null) {
            this.f4318j = lVar.e().a();
        }
        if (lVar.k() != null) {
            this.f4321m = lVar.k().a();
        } else {
            this.f4321m = null;
        }
        if (lVar.d() != null) {
            this.f4322n = lVar.d().a();
        } else {
            this.f4322n = null;
        }
    }

    public void a(AbstractC0432b abstractC0432b) {
        abstractC0432b.j(this.f4318j);
        abstractC0432b.j(this.f4321m);
        abstractC0432b.j(this.f4322n);
        abstractC0432b.j(this.f4314f);
        abstractC0432b.j(this.f4315g);
        abstractC0432b.j(this.f4316h);
        abstractC0432b.j(this.f4317i);
        abstractC0432b.j(this.f4319k);
        abstractC0432b.j(this.f4320l);
    }

    public void b(AbstractC0355a.b bVar) {
        AbstractC0355a abstractC0355a = this.f4318j;
        if (abstractC0355a != null) {
            abstractC0355a.a(bVar);
        }
        AbstractC0355a abstractC0355a2 = this.f4321m;
        if (abstractC0355a2 != null) {
            abstractC0355a2.a(bVar);
        }
        AbstractC0355a abstractC0355a3 = this.f4322n;
        if (abstractC0355a3 != null) {
            abstractC0355a3.a(bVar);
        }
        AbstractC0355a abstractC0355a4 = this.f4314f;
        if (abstractC0355a4 != null) {
            abstractC0355a4.a(bVar);
        }
        AbstractC0355a abstractC0355a5 = this.f4315g;
        if (abstractC0355a5 != null) {
            abstractC0355a5.a(bVar);
        }
        AbstractC0355a abstractC0355a6 = this.f4316h;
        if (abstractC0355a6 != null) {
            abstractC0355a6.a(bVar);
        }
        AbstractC0355a abstractC0355a7 = this.f4317i;
        if (abstractC0355a7 != null) {
            abstractC0355a7.a(bVar);
        }
        d dVar = this.f4319k;
        if (dVar != null) {
            dVar.a(bVar);
        }
        d dVar2 = this.f4320l;
        if (dVar2 != null) {
            dVar2.a(bVar);
        }
    }

    public boolean c(Object obj, com.airbnb.lottie.value.c cVar) {
        if (obj == K.f3792f) {
            AbstractC0355a abstractC0355a = this.f4314f;
            if (abstractC0355a == null) {
                this.f4314f = new q(cVar, new PointF());
                return true;
            }
            abstractC0355a.n(cVar);
            return true;
        }
        if (obj == K.f3793g) {
            AbstractC0355a abstractC0355a2 = this.f4315g;
            if (abstractC0355a2 == null) {
                this.f4315g = new q(cVar, new PointF());
                return true;
            }
            abstractC0355a2.n(cVar);
            return true;
        }
        if (obj == K.f3794h) {
            AbstractC0355a abstractC0355a3 = this.f4315g;
            if (abstractC0355a3 instanceof n) {
                ((n) abstractC0355a3).r(cVar);
                return true;
            }
        }
        if (obj == K.f3795i) {
            AbstractC0355a abstractC0355a4 = this.f4315g;
            if (abstractC0355a4 instanceof n) {
                ((n) abstractC0355a4).s(cVar);
                return true;
            }
        }
        if (obj == K.f3801o) {
            AbstractC0355a abstractC0355a5 = this.f4316h;
            if (abstractC0355a5 == null) {
                this.f4316h = new q(cVar, new com.airbnb.lottie.value.d());
                return true;
            }
            abstractC0355a5.n(cVar);
            return true;
        }
        if (obj == K.f3802p) {
            AbstractC0355a abstractC0355a6 = this.f4317i;
            if (abstractC0355a6 == null) {
                this.f4317i = new q(cVar, Float.valueOf(0.0f));
                return true;
            }
            abstractC0355a6.n(cVar);
            return true;
        }
        if (obj == K.f3789c) {
            AbstractC0355a abstractC0355a7 = this.f4318j;
            if (abstractC0355a7 == null) {
                this.f4318j = new q(cVar, 100);
                return true;
            }
            abstractC0355a7.n(cVar);
            return true;
        }
        if (obj == K.f3774C) {
            AbstractC0355a abstractC0355a8 = this.f4321m;
            if (abstractC0355a8 == null) {
                this.f4321m = new q(cVar, Float.valueOf(100.0f));
                return true;
            }
            abstractC0355a8.n(cVar);
            return true;
        }
        if (obj == K.f3775D) {
            AbstractC0355a abstractC0355a9 = this.f4322n;
            if (abstractC0355a9 == null) {
                this.f4322n = new q(cVar, Float.valueOf(100.0f));
                return true;
            }
            abstractC0355a9.n(cVar);
            return true;
        }
        if (obj == K.f3803q) {
            if (this.f4319k == null) {
                this.f4319k = new d(Collections.singletonList(new com.airbnb.lottie.value.a(Float.valueOf(0.0f))));
            }
            this.f4319k.n(cVar);
            return true;
        }
        if (obj != K.f3804r) {
            return false;
        }
        if (this.f4320l == null) {
            this.f4320l = new d(Collections.singletonList(new com.airbnb.lottie.value.a(Float.valueOf(0.0f))));
        }
        this.f4320l.n(cVar);
        return true;
    }

    public final void d() {
        for (int i2 = 0; i2 < 9; i2++) {
            this.f4313e[i2] = 0.0f;
        }
    }

    public AbstractC0355a e() {
        return this.f4322n;
    }

    public Matrix f() {
        PointF pointF;
        this.f4309a.reset();
        AbstractC0355a abstractC0355a = this.f4315g;
        if (abstractC0355a != null && (pointF = (PointF) abstractC0355a.h()) != null) {
            float f2 = pointF.x;
            if (f2 != 0.0f || pointF.y != 0.0f) {
                this.f4309a.preTranslate(f2, pointF.y);
            }
        }
        AbstractC0355a abstractC0355a2 = this.f4317i;
        if (abstractC0355a2 != null) {
            float fFloatValue = abstractC0355a2 instanceof q ? ((Float) abstractC0355a2.h()).floatValue() : ((d) abstractC0355a2).p();
            if (fFloatValue != 0.0f) {
                this.f4309a.preRotate(fFloatValue);
            }
        }
        if (this.f4319k != null) {
            float fCos = this.f4320l == null ? 0.0f : (float) Math.cos(Math.toRadians((-r3.p()) + 90.0f));
            float fSin = this.f4320l == null ? 1.0f : (float) Math.sin(Math.toRadians((-r5.p()) + 90.0f));
            float fTan = (float) Math.tan(Math.toRadians(r0.p()));
            d();
            float[] fArr = this.f4313e;
            fArr[0] = fCos;
            fArr[1] = fSin;
            float f3 = -fSin;
            fArr[3] = f3;
            fArr[4] = fCos;
            fArr[8] = 1.0f;
            this.f4310b.setValues(fArr);
            d();
            float[] fArr2 = this.f4313e;
            fArr2[0] = 1.0f;
            fArr2[3] = fTan;
            fArr2[4] = 1.0f;
            fArr2[8] = 1.0f;
            this.f4311c.setValues(fArr2);
            d();
            float[] fArr3 = this.f4313e;
            fArr3[0] = fCos;
            fArr3[1] = f3;
            fArr3[3] = fSin;
            fArr3[4] = fCos;
            fArr3[8] = 1.0f;
            this.f4312d.setValues(fArr3);
            this.f4311c.preConcat(this.f4310b);
            this.f4312d.preConcat(this.f4311c);
            this.f4309a.preConcat(this.f4312d);
        }
        AbstractC0355a abstractC0355a3 = this.f4316h;
        if (abstractC0355a3 != null) {
            com.airbnb.lottie.value.d dVar = (com.airbnb.lottie.value.d) abstractC0355a3.h();
            if (dVar.b() != 1.0f || dVar.c() != 1.0f) {
                this.f4309a.preScale(dVar.b(), dVar.c());
            }
        }
        AbstractC0355a abstractC0355a4 = this.f4314f;
        if (abstractC0355a4 != null) {
            PointF pointF2 = (PointF) abstractC0355a4.h();
            float f4 = pointF2.x;
            if (f4 != 0.0f || pointF2.y != 0.0f) {
                this.f4309a.preTranslate(-f4, -pointF2.y);
            }
        }
        return this.f4309a;
    }

    public Matrix g(float f2) {
        AbstractC0355a abstractC0355a = this.f4315g;
        PointF pointF = abstractC0355a == null ? null : (PointF) abstractC0355a.h();
        AbstractC0355a abstractC0355a2 = this.f4316h;
        com.airbnb.lottie.value.d dVar = abstractC0355a2 == null ? null : (com.airbnb.lottie.value.d) abstractC0355a2.h();
        this.f4309a.reset();
        if (pointF != null) {
            this.f4309a.preTranslate(pointF.x * f2, pointF.y * f2);
        }
        if (dVar != null) {
            double d2 = f2;
            this.f4309a.preScale((float) Math.pow(dVar.b(), d2), (float) Math.pow(dVar.c(), d2));
        }
        AbstractC0355a abstractC0355a3 = this.f4317i;
        if (abstractC0355a3 != null) {
            float fFloatValue = ((Float) abstractC0355a3.h()).floatValue();
            AbstractC0355a abstractC0355a4 = this.f4314f;
            PointF pointF2 = abstractC0355a4 != null ? (PointF) abstractC0355a4.h() : null;
            this.f4309a.preRotate(fFloatValue * f2, pointF2 == null ? 0.0f : pointF2.x, pointF2 != null ? pointF2.y : 0.0f);
        }
        return this.f4309a;
    }

    public AbstractC0355a h() {
        return this.f4318j;
    }

    public AbstractC0355a i() {
        return this.f4321m;
    }

    public void j(float f2) {
        AbstractC0355a abstractC0355a = this.f4318j;
        if (abstractC0355a != null) {
            abstractC0355a.m(f2);
        }
        AbstractC0355a abstractC0355a2 = this.f4321m;
        if (abstractC0355a2 != null) {
            abstractC0355a2.m(f2);
        }
        AbstractC0355a abstractC0355a3 = this.f4322n;
        if (abstractC0355a3 != null) {
            abstractC0355a3.m(f2);
        }
        AbstractC0355a abstractC0355a4 = this.f4314f;
        if (abstractC0355a4 != null) {
            abstractC0355a4.m(f2);
        }
        AbstractC0355a abstractC0355a5 = this.f4315g;
        if (abstractC0355a5 != null) {
            abstractC0355a5.m(f2);
        }
        AbstractC0355a abstractC0355a6 = this.f4316h;
        if (abstractC0355a6 != null) {
            abstractC0355a6.m(f2);
        }
        AbstractC0355a abstractC0355a7 = this.f4317i;
        if (abstractC0355a7 != null) {
            abstractC0355a7.m(f2);
        }
        d dVar = this.f4319k;
        if (dVar != null) {
            dVar.m(f2);
        }
        d dVar2 = this.f4320l;
        if (dVar2 != null) {
            dVar2.m(f2);
        }
    }
}
