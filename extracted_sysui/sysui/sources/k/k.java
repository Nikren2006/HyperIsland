package k;

import d.F;
import j.C0409b;
import l.AbstractC0432b;

/* JADX INFO: loaded from: classes.dex */
public class k implements c {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final String f4888a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final j.m f4889b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final j.m f4890c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final C0409b f4891d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final boolean f4892e;

    public k(String str, j.m mVar, j.m mVar2, C0409b c0409b, boolean z2) {
        this.f4888a = str;
        this.f4889b = mVar;
        this.f4890c = mVar2;
        this.f4891d = c0409b;
        this.f4892e = z2;
    }

    @Override // k.c
    public f.c a(F f2, AbstractC0432b abstractC0432b) {
        return new f.o(f2, abstractC0432b, this);
    }

    public C0409b b() {
        return this.f4891d;
    }

    public String c() {
        return this.f4888a;
    }

    public j.m d() {
        return this.f4889b;
    }

    public j.m e() {
        return this.f4890c;
    }

    public boolean f() {
        return this.f4892e;
    }

    public String toString() {
        return "RectangleShape{position=" + this.f4889b + ", size=" + this.f4890c + '}';
    }
}
