package g;

import android.graphics.PointF;
import g.AbstractC0355a;
import java.util.Collections;

/* JADX INFO: loaded from: classes.dex */
public class n extends AbstractC0355a {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final PointF f4299i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public final PointF f4300j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public final AbstractC0355a f4301k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final AbstractC0355a f4302l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public com.airbnb.lottie.value.c f4303m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public com.airbnb.lottie.value.c f4304n;

    public n(AbstractC0355a abstractC0355a, AbstractC0355a abstractC0355a2) {
        super(Collections.emptyList());
        this.f4299i = new PointF();
        this.f4300j = new PointF();
        this.f4301k = abstractC0355a;
        this.f4302l = abstractC0355a2;
        m(f());
    }

    @Override // g.AbstractC0355a
    public void m(float f2) {
        this.f4301k.m(f2);
        this.f4302l.m(f2);
        this.f4299i.set(((Float) this.f4301k.h()).floatValue(), ((Float) this.f4302l.h()).floatValue());
        for (int i2 = 0; i2 < this.f4261a.size(); i2++) {
            ((AbstractC0355a.b) this.f4261a.get(i2)).a();
        }
    }

    @Override // g.AbstractC0355a
    /* JADX INFO: renamed from: p, reason: merged with bridge method [inline-methods] */
    public PointF h() {
        return i(null, 0.0f);
    }

    @Override // g.AbstractC0355a
    /* JADX INFO: renamed from: q, reason: merged with bridge method [inline-methods] */
    public PointF i(com.airbnb.lottie.value.a aVar, float f2) {
        Float f3;
        com.airbnb.lottie.value.a aVarB;
        com.airbnb.lottie.value.a aVarB2;
        Float f4 = null;
        if (this.f4303m == null || (aVarB2 = this.f4301k.b()) == null) {
            f3 = null;
        } else {
            float fD = this.f4301k.d();
            Float f5 = aVarB2.f1399h;
            com.airbnb.lottie.value.c cVar = this.f4303m;
            float f6 = aVarB2.f1398g;
            f3 = (Float) cVar.getValueInternal(f6, f5 == null ? f6 : f5.floatValue(), (Float) aVarB2.f1393b, (Float) aVarB2.f1394c, f2, f2, fD);
        }
        if (this.f4304n != null && (aVarB = this.f4302l.b()) != null) {
            float fD2 = this.f4302l.d();
            Float f7 = aVarB.f1399h;
            com.airbnb.lottie.value.c cVar2 = this.f4304n;
            float f8 = aVarB.f1398g;
            f4 = (Float) cVar2.getValueInternal(f8, f7 == null ? f8 : f7.floatValue(), (Float) aVarB.f1393b, (Float) aVarB.f1394c, f2, f2, fD2);
        }
        if (f3 == null) {
            this.f4300j.set(this.f4299i.x, 0.0f);
        } else {
            this.f4300j.set(f3.floatValue(), 0.0f);
        }
        if (f4 == null) {
            PointF pointF = this.f4300j;
            pointF.set(pointF.x, this.f4299i.y);
        } else {
            PointF pointF2 = this.f4300j;
            pointF2.set(pointF2.x, f4.floatValue());
        }
        return this.f4300j;
    }

    public void r(com.airbnb.lottie.value.c cVar) {
        com.airbnb.lottie.value.c cVar2 = this.f4303m;
        if (cVar2 != null) {
            cVar2.setAnimation(null);
        }
        this.f4303m = cVar;
        if (cVar != null) {
            cVar.setAnimation(this);
        }
    }

    public void s(com.airbnb.lottie.value.c cVar) {
        com.airbnb.lottie.value.c cVar2 = this.f4304n;
        if (cVar2 != null) {
            cVar2.setAnimation(null);
        }
        this.f4304n = cVar;
        if (cVar != null) {
            cVar.setAnimation(this);
        }
    }
}
