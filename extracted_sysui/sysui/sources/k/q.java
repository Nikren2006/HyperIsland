package k;

import d.F;
import l.AbstractC0432b;

/* JADX INFO: loaded from: classes.dex */
public class q implements c {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final String f4912a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final int f4913b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final j.h f4914c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final boolean f4915d;

    public q(String str, int i2, j.h hVar, boolean z2) {
        this.f4912a = str;
        this.f4913b = i2;
        this.f4914c = hVar;
        this.f4915d = z2;
    }

    @Override // k.c
    public f.c a(F f2, AbstractC0432b abstractC0432b) {
        return new f.r(f2, abstractC0432b, this);
    }

    public String b() {
        return this.f4912a;
    }

    public j.h c() {
        return this.f4914c;
    }

    public boolean d() {
        return this.f4915d;
    }

    public String toString() {
        return "ShapePath{name=" + this.f4912a + ", index=" + this.f4913b + '}';
    }
}
