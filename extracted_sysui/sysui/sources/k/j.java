package k;

import d.F;
import j.C0409b;
import l.AbstractC0432b;

/* JADX INFO: loaded from: classes.dex */
public class j implements c {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final String f4873a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final a f4874b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final C0409b f4875c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final j.m f4876d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final C0409b f4877e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final C0409b f4878f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final C0409b f4879g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final C0409b f4880h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final C0409b f4881i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public final boolean f4882j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public final boolean f4883k;

    public enum a {
        STAR(1),
        POLYGON(2);


        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final int f4887a;

        a(int i2) {
            this.f4887a = i2;
        }

        public static a a(int i2) {
            for (a aVar : values()) {
                if (aVar.f4887a == i2) {
                    return aVar;
                }
            }
            return null;
        }
    }

    public j(String str, a aVar, C0409b c0409b, j.m mVar, C0409b c0409b2, C0409b c0409b3, C0409b c0409b4, C0409b c0409b5, C0409b c0409b6, boolean z2, boolean z3) {
        this.f4873a = str;
        this.f4874b = aVar;
        this.f4875c = c0409b;
        this.f4876d = mVar;
        this.f4877e = c0409b2;
        this.f4878f = c0409b3;
        this.f4879g = c0409b4;
        this.f4880h = c0409b5;
        this.f4881i = c0409b6;
        this.f4882j = z2;
        this.f4883k = z3;
    }

    @Override // k.c
    public f.c a(F f2, AbstractC0432b abstractC0432b) {
        return new f.n(f2, abstractC0432b, this);
    }

    public C0409b b() {
        return this.f4878f;
    }

    public C0409b c() {
        return this.f4880h;
    }

    public String d() {
        return this.f4873a;
    }

    public C0409b e() {
        return this.f4879g;
    }

    public C0409b f() {
        return this.f4881i;
    }

    public C0409b g() {
        return this.f4875c;
    }

    public j.m h() {
        return this.f4876d;
    }

    public C0409b i() {
        return this.f4877e;
    }

    public a j() {
        return this.f4874b;
    }

    public boolean k() {
        return this.f4882j;
    }

    public boolean l() {
        return this.f4883k;
    }
}
