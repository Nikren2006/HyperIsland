package k;

import android.graphics.Path;
import d.F;
import j.C0409b;
import j.C0410c;
import j.C0411d;
import l.AbstractC0432b;

/* JADX INFO: loaded from: classes.dex */
public class e implements c {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final g f4829a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final Path.FillType f4830b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final C0410c f4831c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final C0411d f4832d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final j.f f4833e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final j.f f4834f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final String f4835g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final C0409b f4836h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final C0409b f4837i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public final boolean f4838j;

    public e(String str, g gVar, Path.FillType fillType, C0410c c0410c, C0411d c0411d, j.f fVar, j.f fVar2, C0409b c0409b, C0409b c0409b2, boolean z2) {
        this.f4829a = gVar;
        this.f4830b = fillType;
        this.f4831c = c0410c;
        this.f4832d = c0411d;
        this.f4833e = fVar;
        this.f4834f = fVar2;
        this.f4835g = str;
        this.f4836h = c0409b;
        this.f4837i = c0409b2;
        this.f4838j = z2;
    }

    @Override // k.c
    public f.c a(F f2, AbstractC0432b abstractC0432b) {
        return new f.h(f2, abstractC0432b, this);
    }

    public j.f b() {
        return this.f4834f;
    }

    public Path.FillType c() {
        return this.f4830b;
    }

    public C0410c d() {
        return this.f4831c;
    }

    public g e() {
        return this.f4829a;
    }

    public String f() {
        return this.f4835g;
    }

    public C0411d g() {
        return this.f4832d;
    }

    public j.f h() {
        return this.f4833e;
    }

    public boolean i() {
        return this.f4838j;
    }
}
