package k;

import d.F;
import l.AbstractC0432b;

/* JADX INFO: loaded from: classes.dex */
public class b implements c {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final String f4822a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final j.m f4823b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final j.f f4824c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final boolean f4825d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final boolean f4826e;

    public b(String str, j.m mVar, j.f fVar, boolean z2, boolean z3) {
        this.f4822a = str;
        this.f4823b = mVar;
        this.f4824c = fVar;
        this.f4825d = z2;
        this.f4826e = z3;
    }

    @Override // k.c
    public f.c a(F f2, AbstractC0432b abstractC0432b) {
        return new f.f(f2, abstractC0432b, this);
    }

    public String b() {
        return this.f4822a;
    }

    public j.m c() {
        return this.f4823b;
    }

    public j.f d() {
        return this.f4824c;
    }

    public boolean e() {
        return this.f4826e;
    }

    public boolean f() {
        return this.f4825d;
    }
}
