package k;

import android.graphics.Path;
import d.F;
import j.C0408a;
import j.C0411d;
import l.AbstractC0432b;

/* JADX INFO: loaded from: classes.dex */
public class o implements c {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final boolean f4903a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final Path.FillType f4904b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final String f4905c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final C0408a f4906d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final C0411d f4907e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final boolean f4908f;

    public o(String str, boolean z2, Path.FillType fillType, C0408a c0408a, C0411d c0411d, boolean z3) {
        this.f4905c = str;
        this.f4903a = z2;
        this.f4904b = fillType;
        this.f4906d = c0408a;
        this.f4907e = c0411d;
        this.f4908f = z3;
    }

    @Override // k.c
    public f.c a(F f2, AbstractC0432b abstractC0432b) {
        return new f.g(f2, abstractC0432b, this);
    }

    public C0408a b() {
        return this.f4906d;
    }

    public Path.FillType c() {
        return this.f4904b;
    }

    public String d() {
        return this.f4905c;
    }

    public C0411d e() {
        return this.f4907e;
    }

    public boolean f() {
        return this.f4908f;
    }

    public String toString() {
        return "ShapeFill{color=, fillEnabled=" + this.f4903a + '}';
    }
}
