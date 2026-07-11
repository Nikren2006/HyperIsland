package f;

import android.graphics.Path;
import android.graphics.PointF;
import android.graphics.RectF;
import d.F;
import d.K;
import g.AbstractC0355a;
import java.util.List;
import l.AbstractC0432b;
import p.AbstractC0727g;

/* JADX INFO: loaded from: classes.dex */
public class o implements AbstractC0355a.b, k, m {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final String f4184c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final boolean f4185d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final F f4186e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final AbstractC0355a f4187f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final AbstractC0355a f4188g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final AbstractC0355a f4189h;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public boolean f4192k;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Path f4182a = new Path();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final RectF f4183b = new RectF();

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final b f4190i = new b();

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public AbstractC0355a f4191j = null;

    public o(F f2, AbstractC0432b abstractC0432b, k.k kVar) {
        this.f4184c = kVar.c();
        this.f4185d = kVar.f();
        this.f4186e = f2;
        AbstractC0355a abstractC0355aA = kVar.d().a();
        this.f4187f = abstractC0355aA;
        AbstractC0355a abstractC0355aA2 = kVar.e().a();
        this.f4188g = abstractC0355aA2;
        AbstractC0355a abstractC0355aA3 = kVar.b().a();
        this.f4189h = abstractC0355aA3;
        abstractC0432b.j(abstractC0355aA);
        abstractC0432b.j(abstractC0355aA2);
        abstractC0432b.j(abstractC0355aA3);
        abstractC0355aA.a(this);
        abstractC0355aA2.a(this);
        abstractC0355aA3.a(this);
    }

    private void g() {
        this.f4192k = false;
        this.f4186e.invalidateSelf();
    }

    @Override // g.AbstractC0355a.b
    public void a() {
        g();
    }

    /* JADX WARN: Removed duplicated region for block: B:10:0x0025  */
    @Override // f.c
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void b(java.util.List r5, java.util.List r6) {
        /*
            r4 = this;
            r6 = 0
        L1:
            int r0 = r5.size()
            if (r6 >= r0) goto L34
            java.lang.Object r0 = r5.get(r6)
            f.c r0 = (f.c) r0
            boolean r1 = r0 instanceof f.u
            if (r1 == 0) goto L25
            r1 = r0
            f.u r1 = (f.u) r1
            k.s$a r2 = r1.k()
            k.s$a r3 = k.s.a.SIMULTANEOUSLY
            if (r2 != r3) goto L25
            f.b r0 = r4.f4190i
            r0.a(r1)
            r1.e(r4)
            goto L31
        L25:
            boolean r1 = r0 instanceof f.q
            if (r1 == 0) goto L31
            f.q r0 = (f.q) r0
            g.a r0 = r0.i()
            r4.f4191j = r0
        L31:
            int r6 = r6 + 1
            goto L1
        L34:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: f.o.b(java.util.List, java.util.List):void");
    }

    @Override // f.m
    public Path d() {
        AbstractC0355a abstractC0355a;
        if (this.f4192k) {
            return this.f4182a;
        }
        this.f4182a.reset();
        if (this.f4185d) {
            this.f4192k = true;
            return this.f4182a;
        }
        PointF pointF = (PointF) this.f4188g.h();
        float f2 = pointF.x / 2.0f;
        float f3 = pointF.y / 2.0f;
        AbstractC0355a abstractC0355a2 = this.f4189h;
        float fP = abstractC0355a2 == null ? 0.0f : ((g.d) abstractC0355a2).p();
        if (fP == 0.0f && (abstractC0355a = this.f4191j) != null) {
            fP = Math.min(((Float) abstractC0355a.h()).floatValue(), Math.min(f2, f3));
        }
        float fMin = Math.min(f2, f3);
        if (fP > fMin) {
            fP = fMin;
        }
        PointF pointF2 = (PointF) this.f4187f.h();
        this.f4182a.moveTo(pointF2.x + f2, (pointF2.y - f3) + fP);
        this.f4182a.lineTo(pointF2.x + f2, (pointF2.y + f3) - fP);
        if (fP > 0.0f) {
            RectF rectF = this.f4183b;
            float f4 = pointF2.x;
            float f5 = fP * 2.0f;
            float f6 = pointF2.y;
            rectF.set((f4 + f2) - f5, (f6 + f3) - f5, f4 + f2, f6 + f3);
            this.f4182a.arcTo(this.f4183b, 0.0f, 90.0f, false);
        }
        this.f4182a.lineTo((pointF2.x - f2) + fP, pointF2.y + f3);
        if (fP > 0.0f) {
            RectF rectF2 = this.f4183b;
            float f7 = pointF2.x;
            float f8 = pointF2.y;
            float f9 = fP * 2.0f;
            rectF2.set(f7 - f2, (f8 + f3) - f9, (f7 - f2) + f9, f8 + f3);
            this.f4182a.arcTo(this.f4183b, 90.0f, 90.0f, false);
        }
        this.f4182a.lineTo(pointF2.x - f2, (pointF2.y - f3) + fP);
        if (fP > 0.0f) {
            RectF rectF3 = this.f4183b;
            float f10 = pointF2.x;
            float f11 = pointF2.y;
            float f12 = fP * 2.0f;
            rectF3.set(f10 - f2, f11 - f3, (f10 - f2) + f12, (f11 - f3) + f12);
            this.f4182a.arcTo(this.f4183b, 180.0f, 90.0f, false);
        }
        this.f4182a.lineTo((pointF2.x + f2) - fP, pointF2.y - f3);
        if (fP > 0.0f) {
            RectF rectF4 = this.f4183b;
            float f13 = pointF2.x;
            float f14 = fP * 2.0f;
            float f15 = pointF2.y;
            rectF4.set((f13 + f2) - f14, f15 - f3, f13 + f2, (f15 - f3) + f14);
            this.f4182a.arcTo(this.f4183b, 270.0f, 90.0f, false);
        }
        this.f4182a.close();
        this.f4190i.b(this.f4182a);
        this.f4192k = true;
        return this.f4182a;
    }

    @Override // i.f
    public void e(Object obj, com.airbnb.lottie.value.c cVar) {
        if (obj == K.f3798l) {
            this.f4188g.n(cVar);
        } else if (obj == K.f3800n) {
            this.f4187f.n(cVar);
        } else if (obj == K.f3799m) {
            this.f4189h.n(cVar);
        }
    }

    @Override // f.c
    public String getName() {
        return this.f4184c;
    }

    @Override // i.f
    public void i(i.e eVar, int i2, List list, i.e eVar2) {
        AbstractC0727g.k(eVar, i2, list, eVar2, this);
    }
}
