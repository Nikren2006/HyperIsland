package f;

import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Path;
import android.graphics.RectF;
import d.F;
import d.K;
import g.AbstractC0355a;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.ListIterator;
import l.AbstractC0432b;
import p.AbstractC0727g;

/* JADX INFO: loaded from: classes.dex */
public class p implements e, m, j, AbstractC0355a.b, k {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Matrix f4193a = new Matrix();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final Path f4194b = new Path();

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final F f4195c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final AbstractC0432b f4196d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final String f4197e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final boolean f4198f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final AbstractC0355a f4199g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final AbstractC0355a f4200h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final g.p f4201i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public d f4202j;

    public p(F f2, AbstractC0432b abstractC0432b, k.l lVar) {
        this.f4195c = f2;
        this.f4196d = abstractC0432b;
        this.f4197e = lVar.c();
        this.f4198f = lVar.f();
        AbstractC0355a abstractC0355aA = lVar.b().a();
        this.f4199g = abstractC0355aA;
        abstractC0432b.j(abstractC0355aA);
        abstractC0355aA.a(this);
        AbstractC0355a abstractC0355aA2 = lVar.d().a();
        this.f4200h = abstractC0355aA2;
        abstractC0432b.j(abstractC0355aA2);
        abstractC0355aA2.a(this);
        g.p pVarB = lVar.e().b();
        this.f4201i = pVarB;
        pVarB.a(abstractC0432b);
        pVarB.b(this);
    }

    @Override // g.AbstractC0355a.b
    public void a() {
        this.f4195c.invalidateSelf();
    }

    @Override // f.c
    public void b(List list, List list2) {
        this.f4202j.b(list, list2);
    }

    @Override // f.m
    public Path d() {
        Path pathD = this.f4202j.d();
        this.f4194b.reset();
        float fFloatValue = ((Float) this.f4199g.h()).floatValue();
        float fFloatValue2 = ((Float) this.f4200h.h()).floatValue();
        for (int i2 = ((int) fFloatValue) - 1; i2 >= 0; i2--) {
            this.f4193a.set(this.f4201i.g(i2 + fFloatValue2));
            this.f4194b.addPath(pathD, this.f4193a);
        }
        return this.f4194b;
    }

    @Override // i.f
    public void e(Object obj, com.airbnb.lottie.value.c cVar) {
        if (this.f4201i.c(obj, cVar)) {
            return;
        }
        if (obj == K.f3807u) {
            this.f4199g.n(cVar);
        } else if (obj == K.f3808v) {
            this.f4200h.n(cVar);
        }
    }

    @Override // f.e
    public void f(RectF rectF, Matrix matrix, boolean z2) {
        this.f4202j.f(rectF, matrix, z2);
    }

    @Override // f.j
    public void g(ListIterator listIterator) {
        if (this.f4202j != null) {
            return;
        }
        while (listIterator.hasPrevious() && listIterator.previous() != this) {
        }
        ArrayList arrayList = new ArrayList();
        while (listIterator.hasPrevious()) {
            arrayList.add((c) listIterator.previous());
            listIterator.remove();
        }
        Collections.reverse(arrayList);
        this.f4202j = new d(this.f4195c, this.f4196d, "Repeater", this.f4198f, arrayList, null);
    }

    @Override // f.c
    public String getName() {
        return this.f4197e;
    }

    @Override // f.e
    public void h(Canvas canvas, Matrix matrix, int i2) {
        float fFloatValue = ((Float) this.f4199g.h()).floatValue();
        float fFloatValue2 = ((Float) this.f4200h.h()).floatValue();
        float fFloatValue3 = ((Float) this.f4201i.i().h()).floatValue() / 100.0f;
        float fFloatValue4 = ((Float) this.f4201i.e().h()).floatValue() / 100.0f;
        for (int i3 = ((int) fFloatValue) - 1; i3 >= 0; i3--) {
            this.f4193a.set(matrix);
            float f2 = i3;
            this.f4193a.preConcat(this.f4201i.g(f2 + fFloatValue2));
            this.f4202j.h(canvas, this.f4193a, (int) (i2 * AbstractC0727g.i(fFloatValue3, fFloatValue4, f2 / fFloatValue)));
        }
    }

    @Override // i.f
    public void i(i.e eVar, int i2, List list, i.e eVar2) {
        AbstractC0727g.k(eVar, i2, list, eVar2, this);
    }
}
