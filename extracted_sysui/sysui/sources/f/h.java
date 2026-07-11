package f;

import android.graphics.BlurMaskFilter;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.LinearGradient;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PointF;
import android.graphics.RadialGradient;
import android.graphics.RectF;
import android.graphics.Shader;
import androidx.collection.LongSparseArray;
import d.AbstractC0302c;
import d.F;
import d.K;
import e.C0333a;
import g.AbstractC0355a;
import java.util.ArrayList;
import java.util.List;
import l.AbstractC0432b;
import p.AbstractC0727g;

/* JADX INFO: loaded from: classes.dex */
public class h implements e, AbstractC0355a.b, k {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final String f4127a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final boolean f4128b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final AbstractC0432b f4129c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final LongSparseArray f4130d = new LongSparseArray();

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final LongSparseArray f4131e = new LongSparseArray();

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final Path f4132f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final Paint f4133g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final RectF f4134h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final List f4135i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public final k.g f4136j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public final AbstractC0355a f4137k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final AbstractC0355a f4138l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final AbstractC0355a f4139m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final AbstractC0355a f4140n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public AbstractC0355a f4141o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public g.q f4142p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public final F f4143q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public final int f4144r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public AbstractC0355a f4145s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public float f4146t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public g.c f4147u;

    public h(F f2, AbstractC0432b abstractC0432b, k.e eVar) {
        Path path = new Path();
        this.f4132f = path;
        this.f4133g = new C0333a(1);
        this.f4134h = new RectF();
        this.f4135i = new ArrayList();
        this.f4146t = 0.0f;
        this.f4129c = abstractC0432b;
        this.f4127a = eVar.f();
        this.f4128b = eVar.i();
        this.f4143q = f2;
        this.f4136j = eVar.e();
        path.setFillType(eVar.c());
        this.f4144r = (int) (f2.L().d() / 32.0f);
        AbstractC0355a abstractC0355aA = eVar.d().a();
        this.f4137k = abstractC0355aA;
        abstractC0355aA.a(this);
        abstractC0432b.j(abstractC0355aA);
        AbstractC0355a abstractC0355aA2 = eVar.g().a();
        this.f4138l = abstractC0355aA2;
        abstractC0355aA2.a(this);
        abstractC0432b.j(abstractC0355aA2);
        AbstractC0355a abstractC0355aA3 = eVar.h().a();
        this.f4139m = abstractC0355aA3;
        abstractC0355aA3.a(this);
        abstractC0432b.j(abstractC0355aA3);
        AbstractC0355a abstractC0355aA4 = eVar.b().a();
        this.f4140n = abstractC0355aA4;
        abstractC0355aA4.a(this);
        abstractC0432b.j(abstractC0355aA4);
        if (abstractC0432b.w() != null) {
            AbstractC0355a abstractC0355aA5 = abstractC0432b.w().a().a();
            this.f4145s = abstractC0355aA5;
            abstractC0355aA5.a(this);
            abstractC0432b.j(this.f4145s);
        }
        if (abstractC0432b.y() != null) {
            this.f4147u = new g.c(this, abstractC0432b, abstractC0432b.y());
        }
    }

    private int[] g(int[] iArr) {
        g.q qVar = this.f4142p;
        if (qVar != null) {
            Integer[] numArr = (Integer[]) qVar.h();
            int i2 = 0;
            if (iArr.length == numArr.length) {
                while (i2 < iArr.length) {
                    iArr[i2] = numArr[i2].intValue();
                    i2++;
                }
            } else {
                iArr = new int[numArr.length];
                while (i2 < numArr.length) {
                    iArr[i2] = numArr[i2].intValue();
                    i2++;
                }
            }
        }
        return iArr;
    }

    private int j() {
        int iRound = Math.round(this.f4139m.f() * this.f4144r);
        int iRound2 = Math.round(this.f4140n.f() * this.f4144r);
        int iRound3 = Math.round(this.f4137k.f() * this.f4144r);
        int i2 = iRound != 0 ? 527 * iRound : 17;
        if (iRound2 != 0) {
            i2 = i2 * 31 * iRound2;
        }
        return iRound3 != 0 ? i2 * 31 * iRound3 : i2;
    }

    private LinearGradient k() {
        long j2 = j();
        LinearGradient linearGradient = (LinearGradient) this.f4130d.get(j2);
        if (linearGradient != null) {
            return linearGradient;
        }
        PointF pointF = (PointF) this.f4139m.h();
        PointF pointF2 = (PointF) this.f4140n.h();
        k.d dVar = (k.d) this.f4137k.h();
        LinearGradient linearGradient2 = new LinearGradient(pointF.x, pointF.y, pointF2.x, pointF2.y, g(dVar.a()), dVar.b(), Shader.TileMode.CLAMP);
        this.f4130d.put(j2, linearGradient2);
        return linearGradient2;
    }

    private RadialGradient l() {
        long j2 = j();
        RadialGradient radialGradient = (RadialGradient) this.f4131e.get(j2);
        if (radialGradient != null) {
            return radialGradient;
        }
        PointF pointF = (PointF) this.f4139m.h();
        PointF pointF2 = (PointF) this.f4140n.h();
        k.d dVar = (k.d) this.f4137k.h();
        int[] iArrG = g(dVar.a());
        float[] fArrB = dVar.b();
        float f2 = pointF.x;
        float f3 = pointF.y;
        float fHypot = (float) Math.hypot(pointF2.x - f2, pointF2.y - f3);
        if (fHypot <= 0.0f) {
            fHypot = 0.001f;
        }
        RadialGradient radialGradient2 = new RadialGradient(f2, f3, fHypot, iArrG, fArrB, Shader.TileMode.CLAMP);
        this.f4131e.put(j2, radialGradient2);
        return radialGradient2;
    }

    @Override // g.AbstractC0355a.b
    public void a() {
        this.f4143q.invalidateSelf();
    }

    @Override // f.c
    public void b(List list, List list2) {
        for (int i2 = 0; i2 < list2.size(); i2++) {
            c cVar = (c) list2.get(i2);
            if (cVar instanceof m) {
                this.f4135i.add((m) cVar);
            }
        }
    }

    @Override // i.f
    public void e(Object obj, com.airbnb.lottie.value.c cVar) {
        g.c cVar2;
        g.c cVar3;
        g.c cVar4;
        g.c cVar5;
        g.c cVar6;
        if (obj == K.f3790d) {
            this.f4138l.n(cVar);
            return;
        }
        if (obj == K.f3782K) {
            AbstractC0355a abstractC0355a = this.f4141o;
            if (abstractC0355a != null) {
                this.f4129c.H(abstractC0355a);
            }
            if (cVar == null) {
                this.f4141o = null;
                return;
            }
            g.q qVar = new g.q(cVar);
            this.f4141o = qVar;
            qVar.a(this);
            this.f4129c.j(this.f4141o);
            return;
        }
        if (obj == K.f3783L) {
            g.q qVar2 = this.f4142p;
            if (qVar2 != null) {
                this.f4129c.H(qVar2);
            }
            if (cVar == null) {
                this.f4142p = null;
                return;
            }
            this.f4130d.clear();
            this.f4131e.clear();
            g.q qVar3 = new g.q(cVar);
            this.f4142p = qVar3;
            qVar3.a(this);
            this.f4129c.j(this.f4142p);
            return;
        }
        if (obj == K.f3796j) {
            AbstractC0355a abstractC0355a2 = this.f4145s;
            if (abstractC0355a2 != null) {
                abstractC0355a2.n(cVar);
                return;
            }
            g.q qVar4 = new g.q(cVar);
            this.f4145s = qVar4;
            qVar4.a(this);
            this.f4129c.j(this.f4145s);
            return;
        }
        if (obj == K.f3791e && (cVar6 = this.f4147u) != null) {
            cVar6.c(cVar);
            return;
        }
        if (obj == K.f3778G && (cVar5 = this.f4147u) != null) {
            cVar5.f(cVar);
            return;
        }
        if (obj == K.f3779H && (cVar4 = this.f4147u) != null) {
            cVar4.d(cVar);
            return;
        }
        if (obj == K.f3780I && (cVar3 = this.f4147u) != null) {
            cVar3.e(cVar);
        } else {
            if (obj != K.f3781J || (cVar2 = this.f4147u) == null) {
                return;
            }
            cVar2.g(cVar);
        }
    }

    @Override // f.e
    public void f(RectF rectF, Matrix matrix, boolean z2) {
        this.f4132f.reset();
        for (int i2 = 0; i2 < this.f4135i.size(); i2++) {
            this.f4132f.addPath(((m) this.f4135i.get(i2)).d(), matrix);
        }
        this.f4132f.computeBounds(rectF, false);
        rectF.set(rectF.left - 1.0f, rectF.top - 1.0f, rectF.right + 1.0f, rectF.bottom + 1.0f);
    }

    @Override // f.c
    public String getName() {
        return this.f4127a;
    }

    @Override // f.e
    public void h(Canvas canvas, Matrix matrix, int i2) {
        if (this.f4128b) {
            return;
        }
        AbstractC0302c.a("GradientFillContent#draw");
        this.f4132f.reset();
        for (int i3 = 0; i3 < this.f4135i.size(); i3++) {
            this.f4132f.addPath(((m) this.f4135i.get(i3)).d(), matrix);
        }
        this.f4132f.computeBounds(this.f4134h, false);
        Shader shaderK = this.f4136j == k.g.LINEAR ? k() : l();
        shaderK.setLocalMatrix(matrix);
        this.f4133g.setShader(shaderK);
        AbstractC0355a abstractC0355a = this.f4141o;
        if (abstractC0355a != null) {
            this.f4133g.setColorFilter((ColorFilter) abstractC0355a.h());
        }
        AbstractC0355a abstractC0355a2 = this.f4145s;
        if (abstractC0355a2 != null) {
            float fFloatValue = ((Float) abstractC0355a2.h()).floatValue();
            if (fFloatValue == 0.0f) {
                this.f4133g.setMaskFilter(null);
            } else if (fFloatValue != this.f4146t) {
                this.f4133g.setMaskFilter(new BlurMaskFilter(fFloatValue, BlurMaskFilter.Blur.NORMAL));
            }
            this.f4146t = fFloatValue;
        }
        g.c cVar = this.f4147u;
        if (cVar != null) {
            cVar.b(this.f4133g);
        }
        this.f4133g.setAlpha(AbstractC0727g.c((int) ((((i2 / 255.0f) * ((Integer) this.f4138l.h()).intValue()) / 100.0f) * 255.0f), 0, 255));
        canvas.drawPath(this.f4132f, this.f4133g);
        AbstractC0302c.b("GradientFillContent#draw");
    }

    @Override // i.f
    public void i(i.e eVar, int i2, List list, i.e eVar2) {
        AbstractC0727g.k(eVar, i2, list, eVar2, this);
    }
}
