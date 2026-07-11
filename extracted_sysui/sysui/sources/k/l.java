package k;

import d.F;
import j.C0409b;
import l.AbstractC0432b;

/* JADX INFO: loaded from: classes.dex */
public class l implements c {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final String f4893a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final C0409b f4894b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final C0409b f4895c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final j.l f4896d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final boolean f4897e;

    public l(String str, C0409b c0409b, C0409b c0409b2, j.l lVar, boolean z2) {
        this.f4893a = str;
        this.f4894b = c0409b;
        this.f4895c = c0409b2;
        this.f4896d = lVar;
        this.f4897e = z2;
    }

    @Override // k.c
    public f.c a(F f2, AbstractC0432b abstractC0432b) {
        return new f.p(f2, abstractC0432b, this);
    }

    public C0409b b() {
        return this.f4894b;
    }

    public String c() {
        return this.f4893a;
    }

    public C0409b d() {
        return this.f4895c;
    }

    public j.l e() {
        return this.f4896d;
    }

    public boolean f() {
        return this.f4897e;
    }
}
