package g;

import android.graphics.Color;
import android.graphics.Paint;
import g.AbstractC0355a;
import l.AbstractC0432b;
import n.C0707j;

/* JADX INFO: loaded from: classes.dex */
public class c implements AbstractC0355a.b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final AbstractC0355a.b f4275a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final AbstractC0355a f4276b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final AbstractC0355a f4277c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final AbstractC0355a f4278d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final AbstractC0355a f4279e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final AbstractC0355a f4280f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public boolean f4281g = true;

    public class a extends com.airbnb.lottie.value.c {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ com.airbnb.lottie.value.c f4282a;

        public a(com.airbnb.lottie.value.c cVar) {
            this.f4282a = cVar;
        }

        @Override // com.airbnb.lottie.value.c
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public Float getValue(com.airbnb.lottie.value.b bVar) {
            Float f2 = (Float) this.f4282a.getValue(bVar);
            if (f2 == null) {
                return null;
            }
            return Float.valueOf(f2.floatValue() * 2.55f);
        }
    }

    public c(AbstractC0355a.b bVar, AbstractC0432b abstractC0432b, C0707j c0707j) {
        this.f4275a = bVar;
        AbstractC0355a abstractC0355aA = c0707j.a().a();
        this.f4276b = abstractC0355aA;
        abstractC0355aA.a(this);
        abstractC0432b.j(abstractC0355aA);
        AbstractC0355a abstractC0355aA2 = c0707j.d().a();
        this.f4277c = abstractC0355aA2;
        abstractC0355aA2.a(this);
        abstractC0432b.j(abstractC0355aA2);
        AbstractC0355a abstractC0355aA3 = c0707j.b().a();
        this.f4278d = abstractC0355aA3;
        abstractC0355aA3.a(this);
        abstractC0432b.j(abstractC0355aA3);
        AbstractC0355a abstractC0355aA4 = c0707j.c().a();
        this.f4279e = abstractC0355aA4;
        abstractC0355aA4.a(this);
        abstractC0432b.j(abstractC0355aA4);
        AbstractC0355a abstractC0355aA5 = c0707j.e().a();
        this.f4280f = abstractC0355aA5;
        abstractC0355aA5.a(this);
        abstractC0432b.j(abstractC0355aA5);
    }

    @Override // g.AbstractC0355a.b
    public void a() {
        this.f4281g = true;
        this.f4275a.a();
    }

    public void b(Paint paint) {
        if (this.f4281g) {
            this.f4281g = false;
            double dFloatValue = ((double) ((Float) this.f4278d.h()).floatValue()) * 0.017453292519943295d;
            float fFloatValue = ((Float) this.f4279e.h()).floatValue();
            float fSin = ((float) Math.sin(dFloatValue)) * fFloatValue;
            float fCos = ((float) Math.cos(dFloatValue + 3.141592653589793d)) * fFloatValue;
            int iIntValue = ((Integer) this.f4276b.h()).intValue();
            paint.setShadowLayer(((Float) this.f4280f.h()).floatValue(), fSin, fCos, Color.argb(Math.round(((Float) this.f4277c.h()).floatValue()), Color.red(iIntValue), Color.green(iIntValue), Color.blue(iIntValue)));
        }
    }

    public void c(com.airbnb.lottie.value.c cVar) {
        this.f4276b.n(cVar);
    }

    public void d(com.airbnb.lottie.value.c cVar) {
        this.f4278d.n(cVar);
    }

    public void e(com.airbnb.lottie.value.c cVar) {
        this.f4279e.n(cVar);
    }

    public void f(com.airbnb.lottie.value.c cVar) {
        if (cVar == null) {
            this.f4277c.n(null);
        } else {
            this.f4277c.n(new a(cVar));
        }
    }

    public void g(com.airbnb.lottie.value.c cVar) {
        this.f4280f.n(cVar);
    }
}
