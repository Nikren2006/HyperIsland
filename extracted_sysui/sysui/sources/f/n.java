package f;

import android.graphics.Path;
import android.graphics.PointF;
import d.F;
import d.K;
import g.AbstractC0355a;
import java.util.List;
import k.j;
import k.s;
import l.AbstractC0432b;
import p.AbstractC0727g;

/* JADX INFO: loaded from: classes.dex */
public class n implements m, AbstractC0355a.b, k {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final String f4167b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final F f4168c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final j.a f4169d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final boolean f4170e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final boolean f4171f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final AbstractC0355a f4172g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final AbstractC0355a f4173h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final AbstractC0355a f4174i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public final AbstractC0355a f4175j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public final AbstractC0355a f4176k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final AbstractC0355a f4177l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final AbstractC0355a f4178m;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public boolean f4180o;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Path f4166a = new Path();

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final b f4179n = new b();

    public static /* synthetic */ class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final /* synthetic */ int[] f4181a;

        static {
            int[] iArr = new int[j.a.values().length];
            f4181a = iArr;
            try {
                iArr[j.a.STAR.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f4181a[j.a.POLYGON.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
        }
    }

    public n(F f2, AbstractC0432b abstractC0432b, k.j jVar) {
        this.f4168c = f2;
        this.f4167b = jVar.d();
        j.a aVarJ = jVar.j();
        this.f4169d = aVarJ;
        this.f4170e = jVar.k();
        this.f4171f = jVar.l();
        AbstractC0355a abstractC0355aA = jVar.g().a();
        this.f4172g = abstractC0355aA;
        AbstractC0355a abstractC0355aA2 = jVar.h().a();
        this.f4173h = abstractC0355aA2;
        AbstractC0355a abstractC0355aA3 = jVar.i().a();
        this.f4174i = abstractC0355aA3;
        AbstractC0355a abstractC0355aA4 = jVar.e().a();
        this.f4176k = abstractC0355aA4;
        AbstractC0355a abstractC0355aA5 = jVar.f().a();
        this.f4178m = abstractC0355aA5;
        j.a aVar = j.a.STAR;
        if (aVarJ == aVar) {
            this.f4175j = jVar.b().a();
            this.f4177l = jVar.c().a();
        } else {
            this.f4175j = null;
            this.f4177l = null;
        }
        abstractC0432b.j(abstractC0355aA);
        abstractC0432b.j(abstractC0355aA2);
        abstractC0432b.j(abstractC0355aA3);
        abstractC0432b.j(abstractC0355aA4);
        abstractC0432b.j(abstractC0355aA5);
        if (aVarJ == aVar) {
            abstractC0432b.j(this.f4175j);
            abstractC0432b.j(this.f4177l);
        }
        abstractC0355aA.a(this);
        abstractC0355aA2.a(this);
        abstractC0355aA3.a(this);
        abstractC0355aA4.a(this);
        abstractC0355aA5.a(this);
        if (aVarJ == aVar) {
            this.f4175j.a(this);
            this.f4177l.a(this);
        }
    }

    private void k() {
        this.f4180o = false;
        this.f4168c.invalidateSelf();
    }

    @Override // g.AbstractC0355a.b
    public void a() {
        k();
    }

    @Override // f.c
    public void b(List list, List list2) {
        for (int i2 = 0; i2 < list.size(); i2++) {
            c cVar = (c) list.get(i2);
            if (cVar instanceof u) {
                u uVar = (u) cVar;
                if (uVar.k() == s.a.SIMULTANEOUSLY) {
                    this.f4179n.a(uVar);
                    uVar.e(this);
                }
            }
        }
    }

    @Override // f.m
    public Path d() {
        if (this.f4180o) {
            return this.f4166a;
        }
        this.f4166a.reset();
        if (this.f4170e) {
            this.f4180o = true;
            return this.f4166a;
        }
        int i2 = a.f4181a[this.f4169d.ordinal()];
        if (i2 == 1) {
            j();
        } else if (i2 == 2) {
            g();
        }
        this.f4166a.close();
        this.f4179n.b(this.f4166a);
        this.f4180o = true;
        return this.f4166a;
    }

    @Override // i.f
    public void e(Object obj, com.airbnb.lottie.value.c cVar) {
        AbstractC0355a abstractC0355a;
        AbstractC0355a abstractC0355a2;
        if (obj == K.f3809w) {
            this.f4172g.n(cVar);
            return;
        }
        if (obj == K.f3810x) {
            this.f4174i.n(cVar);
            return;
        }
        if (obj == K.f3800n) {
            this.f4173h.n(cVar);
            return;
        }
        if (obj == K.f3811y && (abstractC0355a2 = this.f4175j) != null) {
            abstractC0355a2.n(cVar);
            return;
        }
        if (obj == K.f3812z) {
            this.f4176k.n(cVar);
            return;
        }
        if (obj == K.f3772A && (abstractC0355a = this.f4177l) != null) {
            abstractC0355a.n(cVar);
        } else if (obj == K.f3773B) {
            this.f4178m.n(cVar);
        }
    }

    public final void g() {
        int i2;
        double d2;
        double d3;
        double d4;
        int iFloor = (int) Math.floor(((Float) this.f4172g.h()).floatValue());
        double radians = Math.toRadians((this.f4174i == null ? 0.0d : ((Float) r2.h()).floatValue()) - 90.0d);
        double d5 = iFloor;
        float fFloatValue = ((Float) this.f4178m.h()).floatValue() / 100.0f;
        float fFloatValue2 = ((Float) this.f4176k.h()).floatValue();
        double d6 = fFloatValue2;
        float fCos = (float) (Math.cos(radians) * d6);
        float fSin = (float) (Math.sin(radians) * d6);
        this.f4166a.moveTo(fCos, fSin);
        double d7 = (float) (6.283185307179586d / d5);
        double d8 = radians + d7;
        double dCeil = Math.ceil(d5);
        int i3 = 0;
        while (i3 < dCeil) {
            float fCos2 = (float) (Math.cos(d8) * d6);
            double d9 = dCeil;
            float fSin2 = (float) (d6 * Math.sin(d8));
            if (fFloatValue != 0.0f) {
                d3 = d6;
                i2 = i3;
                d2 = d8;
                double dAtan2 = (float) (Math.atan2(fSin, fCos) - 1.5707963267948966d);
                float fCos3 = (float) Math.cos(dAtan2);
                float fSin3 = (float) Math.sin(dAtan2);
                d4 = d7;
                double dAtan22 = (float) (Math.atan2(fSin2, fCos2) - 1.5707963267948966d);
                float f2 = fFloatValue2 * fFloatValue * 0.25f;
                this.f4166a.cubicTo(fCos - (fCos3 * f2), fSin - (fSin3 * f2), fCos2 + (((float) Math.cos(dAtan22)) * f2), fSin2 + (f2 * ((float) Math.sin(dAtan22))), fCos2, fSin2);
            } else {
                i2 = i3;
                d2 = d8;
                d3 = d6;
                d4 = d7;
                this.f4166a.lineTo(fCos2, fSin2);
            }
            d8 = d2 + d4;
            i3 = i2 + 1;
            fSin = fSin2;
            fCos = fCos2;
            dCeil = d9;
            d6 = d3;
            d7 = d4;
        }
        PointF pointF = (PointF) this.f4173h.h();
        this.f4166a.offset(pointF.x, pointF.y);
        this.f4166a.close();
    }

    @Override // f.c
    public String getName() {
        return this.f4167b;
    }

    @Override // i.f
    public void i(i.e eVar, int i2, List list, i.e eVar2) {
        AbstractC0727g.k(eVar, i2, list, eVar2, this);
    }

    public final void j() {
        int i2;
        float f2;
        float f3;
        double d2;
        float fSin;
        float f4;
        float f5;
        float f6;
        double d3;
        float f7;
        float f8;
        float f9;
        double d4;
        float fFloatValue = ((Float) this.f4172g.h()).floatValue();
        double radians = Math.toRadians((this.f4174i == null ? 0.0d : ((Float) r2.h()).floatValue()) - 90.0d);
        double d5 = fFloatValue;
        float f10 = (float) (6.283185307179586d / d5);
        if (this.f4171f) {
            f10 *= -1.0f;
        }
        float f11 = f10 / 2.0f;
        float f12 = fFloatValue - ((int) fFloatValue);
        int i3 = (f12 > 0.0f ? 1 : (f12 == 0.0f ? 0 : -1));
        if (i3 != 0) {
            radians += (double) ((1.0f - f12) * f11);
        }
        float fFloatValue2 = ((Float) this.f4176k.h()).floatValue();
        float fFloatValue3 = ((Float) this.f4175j.h()).floatValue();
        AbstractC0355a abstractC0355a = this.f4177l;
        float fFloatValue4 = abstractC0355a != null ? ((Float) abstractC0355a.h()).floatValue() / 100.0f : 0.0f;
        AbstractC0355a abstractC0355a2 = this.f4178m;
        float fFloatValue5 = abstractC0355a2 != null ? ((Float) abstractC0355a2.h()).floatValue() / 100.0f : 0.0f;
        if (i3 != 0) {
            f4 = ((fFloatValue2 - fFloatValue3) * f12) + fFloatValue3;
            i2 = i3;
            double d6 = f4;
            float fCos = (float) (d6 * Math.cos(radians));
            fSin = (float) (d6 * Math.sin(radians));
            this.f4166a.moveTo(fCos, fSin);
            d2 = radians + ((double) ((f10 * f12) / 2.0f));
            f2 = fCos;
            f3 = f11;
        } else {
            i2 = i3;
            double d7 = fFloatValue2;
            float fCos2 = (float) (Math.cos(radians) * d7);
            float fSin2 = (float) (d7 * Math.sin(radians));
            this.f4166a.moveTo(fCos2, fSin2);
            f2 = fCos2;
            f3 = f11;
            d2 = radians + ((double) f3);
            fSin = fSin2;
            f4 = 0.0f;
        }
        double dCeil = Math.ceil(d5) * 2.0d;
        int i4 = 0;
        float f13 = f3;
        float f14 = f2;
        boolean z2 = false;
        while (true) {
            double d8 = i4;
            if (d8 >= dCeil) {
                PointF pointF = (PointF) this.f4173h.h();
                this.f4166a.offset(pointF.x, pointF.y);
                this.f4166a.close();
                return;
            }
            float f15 = z2 ? fFloatValue2 : fFloatValue3;
            if (f4 == 0.0f || d8 != dCeil - 2.0d) {
                f5 = f10;
                f6 = f13;
            } else {
                f5 = f10;
                f6 = (f10 * f12) / 2.0f;
            }
            if (f4 == 0.0f || d8 != dCeil - 1.0d) {
                d3 = d8;
                f7 = f4;
                f4 = f15;
            } else {
                d3 = d8;
                f7 = f4;
            }
            double d9 = f4;
            double d10 = dCeil;
            float fCos3 = (float) (d9 * Math.cos(d2));
            float fSin3 = (float) (d9 * Math.sin(d2));
            if (fFloatValue4 == 0.0f && fFloatValue5 == 0.0f) {
                this.f4166a.lineTo(fCos3, fSin3);
                d4 = d2;
                f8 = fFloatValue4;
                f9 = fFloatValue5;
            } else {
                f8 = fFloatValue4;
                double dAtan2 = (float) (Math.atan2(fSin, f14) - 1.5707963267948966d);
                float fCos4 = (float) Math.cos(dAtan2);
                float fSin4 = (float) Math.sin(dAtan2);
                f9 = fFloatValue5;
                d4 = d2;
                double dAtan22 = (float) (Math.atan2(fSin3, fCos3) - 1.5707963267948966d);
                float fCos5 = (float) Math.cos(dAtan22);
                float fSin5 = (float) Math.sin(dAtan22);
                float f16 = z2 ? f8 : f9;
                float f17 = z2 ? f9 : f8;
                float f18 = (z2 ? fFloatValue3 : fFloatValue2) * f16 * 0.47829f;
                float f19 = fCos4 * f18;
                float f20 = f18 * fSin4;
                float f21 = (z2 ? fFloatValue2 : fFloatValue3) * f17 * 0.47829f;
                float f22 = fCos5 * f21;
                float f23 = f21 * fSin5;
                if (i2 != 0) {
                    if (i4 == 0) {
                        f19 *= f12;
                        f20 *= f12;
                    } else if (d3 == d10 - 1.0d) {
                        f22 *= f12;
                        f23 *= f12;
                    }
                }
                this.f4166a.cubicTo(f14 - f19, fSin - f20, fCos3 + f22, fSin3 + f23, fCos3, fSin3);
            }
            d2 = d4 + ((double) f6);
            z2 = !z2;
            i4++;
            f14 = fCos3;
            fSin = fSin3;
            fFloatValue5 = f9;
            fFloatValue4 = f8;
            f4 = f7;
            f10 = f5;
            dCeil = d10;
        }
    }
}
