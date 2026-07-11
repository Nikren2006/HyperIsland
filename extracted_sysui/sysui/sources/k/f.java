package k;

import d.F;
import j.C0409b;
import j.C0410c;
import j.C0411d;
import java.util.List;
import k.r;
import l.AbstractC0432b;

/* JADX INFO: loaded from: classes.dex */
public class f implements c {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final String f4839a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final g f4840b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final C0410c f4841c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final C0411d f4842d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final j.f f4843e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final j.f f4844f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final C0409b f4845g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final r.b f4846h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final r.c f4847i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public final float f4848j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public final List f4849k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final C0409b f4850l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final boolean f4851m;

    public f(String str, g gVar, C0410c c0410c, C0411d c0411d, j.f fVar, j.f fVar2, C0409b c0409b, r.b bVar, r.c cVar, float f2, List list, C0409b c0409b2, boolean z2) {
        this.f4839a = str;
        this.f4840b = gVar;
        this.f4841c = c0410c;
        this.f4842d = c0411d;
        this.f4843e = fVar;
        this.f4844f = fVar2;
        this.f4845g = c0409b;
        this.f4846h = bVar;
        this.f4847i = cVar;
        this.f4848j = f2;
        this.f4849k = list;
        this.f4850l = c0409b2;
        this.f4851m = z2;
    }

    @Override // k.c
    public f.c a(F f2, AbstractC0432b abstractC0432b) {
        return new f.i(f2, abstractC0432b, this);
    }

    public r.b b() {
        return this.f4846h;
    }

    public C0409b c() {
        return this.f4850l;
    }

    public j.f d() {
        return this.f4844f;
    }

    public C0410c e() {
        return this.f4841c;
    }

    public g f() {
        return this.f4840b;
    }

    public r.c g() {
        return this.f4847i;
    }

    public List h() {
        return this.f4849k;
    }

    public float i() {
        return this.f4848j;
    }

    public String j() {
        return this.f4839a;
    }

    public C0411d k() {
        return this.f4842d;
    }

    public j.f l() {
        return this.f4843e;
    }

    public C0409b m() {
        return this.f4845g;
    }

    public boolean n() {
        return this.f4851m;
    }
}
