package f;

import android.graphics.PointF;
import d.F;
import g.AbstractC0355a;
import i.C0402a;
import java.util.ArrayList;
import java.util.List;
import l.AbstractC0432b;

/* JADX INFO: loaded from: classes.dex */
public class q implements s, AbstractC0355a.b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final F f4203a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final String f4204b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final AbstractC0355a f4205c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public k.n f4206d;

    public q(F f2, AbstractC0432b abstractC0432b, k.m mVar) {
        this.f4203a = f2;
        this.f4204b = mVar.c();
        AbstractC0355a abstractC0355aA = mVar.b().a();
        this.f4205c = abstractC0355aA;
        abstractC0432b.j(abstractC0355aA);
        abstractC0355aA.a(this);
    }

    public static int e(int i2, int i3) {
        int i4 = i2 / i3;
        return ((i2 ^ i3) >= 0 || i3 * i4 == i2) ? i4 : i4 - 1;
    }

    public static int g(int i2, int i3) {
        return i2 - (e(i2, i3) * i3);
    }

    @Override // g.AbstractC0355a.b
    public void a() {
        this.f4203a.invalidateSelf();
    }

    @Override // f.c
    public void b(List list, List list2) {
    }

    /* JADX WARN: Removed duplicated region for block: B:26:0x009f  */
    @Override // f.s
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public k.n c(k.n r19) {
        /*
            Method dump skipped, instruction units count: 413
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: f.q.c(k.n):k.n");
    }

    public AbstractC0355a i() {
        return this.f4205c;
    }

    public final k.n j(k.n nVar) {
        List listA = nVar.a();
        boolean zD = nVar.d();
        int size = listA.size() - 1;
        int i2 = 0;
        while (size >= 0) {
            C0402a c0402a = (C0402a) listA.get(size);
            C0402a c0402a2 = (C0402a) listA.get(g(size - 1, listA.size()));
            PointF pointFC = (size != 0 || zD) ? c0402a2.c() : nVar.b();
            i2 = (((size != 0 || zD) ? c0402a2.b() : pointFC).equals(pointFC) && c0402a.a().equals(pointFC) && !(!nVar.d() && size == 0 && size == listA.size() - 1)) ? i2 + 2 : i2 + 1;
            size--;
        }
        k.n nVar2 = this.f4206d;
        if (nVar2 == null || nVar2.a().size() != i2) {
            ArrayList arrayList = new ArrayList(i2);
            for (int i3 = 0; i3 < i2; i3++) {
                arrayList.add(new C0402a());
            }
            this.f4206d = new k.n(new PointF(0.0f, 0.0f), false, arrayList);
        }
        this.f4206d.e(zD);
        return this.f4206d;
    }
}
