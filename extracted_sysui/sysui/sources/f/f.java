package f;

import android.graphics.Path;
import android.graphics.PointF;
import d.F;
import d.K;
import g.AbstractC0355a;
import java.util.List;
import k.s;
import l.AbstractC0432b;
import p.AbstractC0727g;

/* JADX INFO: loaded from: classes.dex */
public class f implements m, AbstractC0355a.b, k {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final String f4107b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final F f4108c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final AbstractC0355a f4109d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final AbstractC0355a f4110e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final k.b f4111f;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public boolean f4113h;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Path f4106a = new Path();

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final b f4112g = new b();

    public f(F f2, AbstractC0432b abstractC0432b, k.b bVar) {
        this.f4107b = bVar.b();
        this.f4108c = f2;
        AbstractC0355a abstractC0355aA = bVar.d().a();
        this.f4109d = abstractC0355aA;
        AbstractC0355a abstractC0355aA2 = bVar.c().a();
        this.f4110e = abstractC0355aA2;
        this.f4111f = bVar;
        abstractC0432b.j(abstractC0355aA);
        abstractC0432b.j(abstractC0355aA2);
        abstractC0355aA.a(this);
        abstractC0355aA2.a(this);
    }

    @Override // g.AbstractC0355a.b
    public void a() {
        g();
    }

    @Override // f.c
    public void b(List list, List list2) {
        for (int i2 = 0; i2 < list.size(); i2++) {
            c cVar = (c) list.get(i2);
            if (cVar instanceof u) {
                u uVar = (u) cVar;
                if (uVar.k() == s.a.SIMULTANEOUSLY) {
                    this.f4112g.a(uVar);
                    uVar.e(this);
                }
            }
        }
    }

    @Override // f.m
    public Path d() {
        if (this.f4113h) {
            return this.f4106a;
        }
        this.f4106a.reset();
        if (this.f4111f.e()) {
            this.f4113h = true;
            return this.f4106a;
        }
        PointF pointF = (PointF) this.f4109d.h();
        float f2 = pointF.x / 2.0f;
        float f3 = pointF.y / 2.0f;
        float f4 = f2 * 0.55228f;
        float f5 = 0.55228f * f3;
        this.f4106a.reset();
        if (this.f4111f.f()) {
            float f6 = -f3;
            this.f4106a.moveTo(0.0f, f6);
            float f7 = 0.0f - f4;
            float f8 = -f2;
            float f9 = 0.0f - f5;
            this.f4106a.cubicTo(f7, f6, f8, f9, f8, 0.0f);
            float f10 = f5 + 0.0f;
            this.f4106a.cubicTo(f8, f10, f7, f3, 0.0f, f3);
            float f11 = f4 + 0.0f;
            this.f4106a.cubicTo(f11, f3, f2, f10, f2, 0.0f);
            this.f4106a.cubicTo(f2, f9, f11, f6, 0.0f, f6);
        } else {
            float f12 = -f3;
            this.f4106a.moveTo(0.0f, f12);
            float f13 = f4 + 0.0f;
            float f14 = 0.0f - f5;
            this.f4106a.cubicTo(f13, f12, f2, f14, f2, 0.0f);
            float f15 = f5 + 0.0f;
            this.f4106a.cubicTo(f2, f15, f13, f3, 0.0f, f3);
            float f16 = 0.0f - f4;
            float f17 = -f2;
            this.f4106a.cubicTo(f16, f3, f17, f15, f17, 0.0f);
            this.f4106a.cubicTo(f17, f14, f16, f12, 0.0f, f12);
        }
        PointF pointF2 = (PointF) this.f4110e.h();
        this.f4106a.offset(pointF2.x, pointF2.y);
        this.f4106a.close();
        this.f4112g.b(this.f4106a);
        this.f4113h = true;
        return this.f4106a;
    }

    @Override // i.f
    public void e(Object obj, com.airbnb.lottie.value.c cVar) {
        if (obj == K.f3797k) {
            this.f4109d.n(cVar);
        } else if (obj == K.f3800n) {
            this.f4110e.n(cVar);
        }
    }

    public final void g() {
        this.f4113h = false;
        this.f4108c.invalidateSelf();
    }

    @Override // f.c
    public String getName() {
        return this.f4107b;
    }

    @Override // i.f
    public void i(i.e eVar, int i2, List list, i.e eVar2) {
        AbstractC0727g.k(eVar, i2, list, eVar2, this);
    }
}
