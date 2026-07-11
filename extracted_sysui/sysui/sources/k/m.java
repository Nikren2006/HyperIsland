package k;

import d.F;
import l.AbstractC0432b;

/* JADX INFO: loaded from: classes.dex */
public class m implements c {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final String f4898a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final j.m f4899b;

    public m(String str, j.m mVar) {
        this.f4898a = str;
        this.f4899b = mVar;
    }

    @Override // k.c
    public f.c a(F f2, AbstractC0432b abstractC0432b) {
        return new f.q(f2, abstractC0432b, this);
    }

    public j.m b() {
        return this.f4899b;
    }

    public String c() {
        return this.f4898a;
    }
}
