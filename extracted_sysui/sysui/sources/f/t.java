package f;

import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Matrix;
import d.F;
import d.K;
import g.AbstractC0355a;
import l.AbstractC0432b;

/* JADX INFO: loaded from: classes.dex */
public class t extends AbstractC0346a {

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public final AbstractC0432b f4214r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public final String f4215s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public final boolean f4216t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public final AbstractC0355a f4217u;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public AbstractC0355a f4218v;

    public t(F f2, AbstractC0432b abstractC0432b, k.r rVar) {
        super(f2, abstractC0432b, rVar.b().a(), rVar.e().a(), rVar.g(), rVar.i(), rVar.j(), rVar.f(), rVar.d());
        this.f4214r = abstractC0432b;
        this.f4215s = rVar.h();
        this.f4216t = rVar.k();
        AbstractC0355a abstractC0355aA = rVar.c().a();
        this.f4217u = abstractC0355aA;
        abstractC0355aA.a(this);
        abstractC0432b.j(abstractC0355aA);
    }

    @Override // f.AbstractC0346a, i.f
    public void e(Object obj, com.airbnb.lottie.value.c cVar) {
        super.e(obj, cVar);
        if (obj == K.f3788b) {
            this.f4217u.n(cVar);
            return;
        }
        if (obj == K.f3782K) {
            AbstractC0355a abstractC0355a = this.f4218v;
            if (abstractC0355a != null) {
                this.f4214r.H(abstractC0355a);
            }
            if (cVar == null) {
                this.f4218v = null;
                return;
            }
            g.q qVar = new g.q(cVar);
            this.f4218v = qVar;
            qVar.a(this);
            this.f4214r.j(this.f4217u);
        }
    }

    @Override // f.c
    public String getName() {
        return this.f4215s;
    }

    @Override // f.AbstractC0346a, f.e
    public void h(Canvas canvas, Matrix matrix, int i2) {
        if (this.f4216t) {
            return;
        }
        this.f4083i.setColor(((g.b) this.f4217u).p());
        AbstractC0355a abstractC0355a = this.f4218v;
        if (abstractC0355a != null) {
            this.f4083i.setColorFilter((ColorFilter) abstractC0355a.h());
        }
        super.h(canvas, matrix, i2);
    }
}
