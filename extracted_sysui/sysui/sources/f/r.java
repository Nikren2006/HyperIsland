package f;

import android.graphics.Path;
import d.F;
import g.AbstractC0355a;
import l.AbstractC0432b;

/* JADX INFO: loaded from: classes.dex */
public class r implements m, AbstractC0355a.b {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final String f4208b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final boolean f4209c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final F f4210d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final g.m f4211e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public boolean f4212f;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Path f4207a = new Path();

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final b f4213g = new b();

    public r(F f2, AbstractC0432b abstractC0432b, k.q qVar) {
        this.f4208b = qVar.b();
        this.f4209c = qVar.d();
        this.f4210d = f2;
        g.m mVarA = qVar.c().a();
        this.f4211e = mVarA;
        abstractC0432b.j(mVarA);
        mVarA.a(this);
    }

    private void e() {
        this.f4212f = false;
        this.f4210d.invalidateSelf();
    }

    @Override // g.AbstractC0355a.b
    public void a() {
        e();
    }

    /* JADX WARN: Removed duplicated region for block: B:10:0x0026  */
    @Override // f.c
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void b(java.util.List r6, java.util.List r7) {
        /*
            r5 = this;
            r7 = 0
            r0 = 0
        L2:
            int r1 = r6.size()
            if (r0 >= r1) goto L39
            java.lang.Object r1 = r6.get(r0)
            f.c r1 = (f.c) r1
            boolean r2 = r1 instanceof f.u
            if (r2 == 0) goto L26
            r2 = r1
            f.u r2 = (f.u) r2
            k.s$a r3 = r2.k()
            k.s$a r4 = k.s.a.SIMULTANEOUSLY
            if (r3 != r4) goto L26
            f.b r1 = r5.f4213g
            r1.a(r2)
            r2.e(r5)
            goto L36
        L26:
            boolean r2 = r1 instanceof f.s
            if (r2 == 0) goto L36
            if (r7 != 0) goto L31
            java.util.ArrayList r7 = new java.util.ArrayList
            r7.<init>()
        L31:
            f.s r1 = (f.s) r1
            r7.add(r1)
        L36:
            int r0 = r0 + 1
            goto L2
        L39:
            g.m r5 = r5.f4211e
            r5.q(r7)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: f.r.b(java.util.List, java.util.List):void");
    }

    @Override // f.m
    public Path d() {
        if (this.f4212f) {
            return this.f4207a;
        }
        this.f4207a.reset();
        if (this.f4209c) {
            this.f4212f = true;
            return this.f4207a;
        }
        Path path = (Path) this.f4211e.h();
        if (path == null) {
            return this.f4207a;
        }
        this.f4207a.set(path);
        this.f4207a.setFillType(Path.FillType.EVEN_ODD);
        this.f4213g.b(this.f4207a);
        this.f4212f = true;
        return this.f4207a;
    }
}
