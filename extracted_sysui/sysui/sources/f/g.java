package f;

import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import androidx.core.view.ViewCompat;
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
public class g implements e, AbstractC0355a.b, k {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Path f4114a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final Paint f4115b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final AbstractC0432b f4116c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final String f4117d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final boolean f4118e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final List f4119f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final AbstractC0355a f4120g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final AbstractC0355a f4121h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public AbstractC0355a f4122i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public final F f4123j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public AbstractC0355a f4124k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public float f4125l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public g.c f4126m;

    public g(F f2, AbstractC0432b abstractC0432b, k.o oVar) {
        Path path = new Path();
        this.f4114a = path;
        this.f4115b = new C0333a(1);
        this.f4119f = new ArrayList();
        this.f4116c = abstractC0432b;
        this.f4117d = oVar.d();
        this.f4118e = oVar.f();
        this.f4123j = f2;
        if (abstractC0432b.w() != null) {
            AbstractC0355a abstractC0355aA = abstractC0432b.w().a().a();
            this.f4124k = abstractC0355aA;
            abstractC0355aA.a(this);
            abstractC0432b.j(this.f4124k);
        }
        if (abstractC0432b.y() != null) {
            this.f4126m = new g.c(this, abstractC0432b, abstractC0432b.y());
        }
        if (oVar.b() == null || oVar.e() == null) {
            this.f4120g = null;
            this.f4121h = null;
            return;
        }
        path.setFillType(oVar.c());
        AbstractC0355a abstractC0355aA2 = oVar.b().a();
        this.f4120g = abstractC0355aA2;
        abstractC0355aA2.a(this);
        abstractC0432b.j(abstractC0355aA2);
        AbstractC0355a abstractC0355aA3 = oVar.e().a();
        this.f4121h = abstractC0355aA3;
        abstractC0355aA3.a(this);
        abstractC0432b.j(abstractC0355aA3);
    }

    @Override // g.AbstractC0355a.b
    public void a() {
        this.f4123j.invalidateSelf();
    }

    @Override // f.c
    public void b(List list, List list2) {
        for (int i2 = 0; i2 < list2.size(); i2++) {
            c cVar = (c) list2.get(i2);
            if (cVar instanceof m) {
                this.f4119f.add((m) cVar);
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
        if (obj == K.f3787a) {
            this.f4120g.n(cVar);
            return;
        }
        if (obj == K.f3790d) {
            this.f4121h.n(cVar);
            return;
        }
        if (obj == K.f3782K) {
            AbstractC0355a abstractC0355a = this.f4122i;
            if (abstractC0355a != null) {
                this.f4116c.H(abstractC0355a);
            }
            if (cVar == null) {
                this.f4122i = null;
                return;
            }
            g.q qVar = new g.q(cVar);
            this.f4122i = qVar;
            qVar.a(this);
            this.f4116c.j(this.f4122i);
            return;
        }
        if (obj == K.f3796j) {
            AbstractC0355a abstractC0355a2 = this.f4124k;
            if (abstractC0355a2 != null) {
                abstractC0355a2.n(cVar);
                return;
            }
            g.q qVar2 = new g.q(cVar);
            this.f4124k = qVar2;
            qVar2.a(this);
            this.f4116c.j(this.f4124k);
            return;
        }
        if (obj == K.f3791e && (cVar6 = this.f4126m) != null) {
            cVar6.c(cVar);
            return;
        }
        if (obj == K.f3778G && (cVar5 = this.f4126m) != null) {
            cVar5.f(cVar);
            return;
        }
        if (obj == K.f3779H && (cVar4 = this.f4126m) != null) {
            cVar4.d(cVar);
            return;
        }
        if (obj == K.f3780I && (cVar3 = this.f4126m) != null) {
            cVar3.e(cVar);
        } else {
            if (obj != K.f3781J || (cVar2 = this.f4126m) == null) {
                return;
            }
            cVar2.g(cVar);
        }
    }

    @Override // f.e
    public void f(RectF rectF, Matrix matrix, boolean z2) {
        this.f4114a.reset();
        for (int i2 = 0; i2 < this.f4119f.size(); i2++) {
            this.f4114a.addPath(((m) this.f4119f.get(i2)).d(), matrix);
        }
        this.f4114a.computeBounds(rectF, false);
        rectF.set(rectF.left - 1.0f, rectF.top - 1.0f, rectF.right + 1.0f, rectF.bottom + 1.0f);
    }

    @Override // f.c
    public String getName() {
        return this.f4117d;
    }

    @Override // f.e
    public void h(Canvas canvas, Matrix matrix, int i2) {
        if (this.f4118e) {
            return;
        }
        AbstractC0302c.a("FillContent#draw");
        this.f4115b.setColor((AbstractC0727g.c((int) ((((i2 / 255.0f) * ((Integer) this.f4121h.h()).intValue()) / 100.0f) * 255.0f), 0, 255) << 24) | (((g.b) this.f4120g).p() & ViewCompat.MEASURED_SIZE_MASK));
        AbstractC0355a abstractC0355a = this.f4122i;
        if (abstractC0355a != null) {
            this.f4115b.setColorFilter((ColorFilter) abstractC0355a.h());
        }
        AbstractC0355a abstractC0355a2 = this.f4124k;
        if (abstractC0355a2 != null) {
            float fFloatValue = ((Float) abstractC0355a2.h()).floatValue();
            if (fFloatValue == 0.0f) {
                this.f4115b.setMaskFilter(null);
            } else if (fFloatValue != this.f4125l) {
                this.f4115b.setMaskFilter(this.f4116c.x(fFloatValue));
            }
            this.f4125l = fFloatValue;
        }
        g.c cVar = this.f4126m;
        if (cVar != null) {
            cVar.b(this.f4115b);
        }
        this.f4114a.reset();
        for (int i3 = 0; i3 < this.f4119f.size(); i3++) {
            this.f4114a.addPath(((m) this.f4119f.get(i3)).d(), matrix);
        }
        canvas.drawPath(this.f4114a, this.f4115b);
        AbstractC0302c.b("FillContent#draw");
    }

    @Override // i.f
    public void i(i.e eVar, int i2, List list, i.e eVar2) {
        AbstractC0727g.k(eVar, i2, list, eVar2, this);
    }
}
