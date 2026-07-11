package k;

import d.F;
import f.u;
import j.C0409b;
import l.AbstractC0432b;

/* JADX INFO: loaded from: classes.dex */
public class s implements c {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final String f4936a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final a f4937b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final C0409b f4938c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final C0409b f4939d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final C0409b f4940e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final boolean f4941f;

    public enum a {
        SIMULTANEOUSLY,
        INDIVIDUALLY;

        public static a a(int i2) {
            if (i2 == 1) {
                return SIMULTANEOUSLY;
            }
            if (i2 == 2) {
                return INDIVIDUALLY;
            }
            throw new IllegalArgumentException("Unknown trim path type " + i2);
        }
    }

    public s(String str, a aVar, C0409b c0409b, C0409b c0409b2, C0409b c0409b3, boolean z2) {
        this.f4936a = str;
        this.f4937b = aVar;
        this.f4938c = c0409b;
        this.f4939d = c0409b2;
        this.f4940e = c0409b3;
        this.f4941f = z2;
    }

    @Override // k.c
    public f.c a(F f2, AbstractC0432b abstractC0432b) {
        return new u(abstractC0432b, this);
    }

    public C0409b b() {
        return this.f4939d;
    }

    public String c() {
        return this.f4936a;
    }

    public C0409b d() {
        return this.f4940e;
    }

    public C0409b e() {
        return this.f4938c;
    }

    public a f() {
        return this.f4937b;
    }

    public boolean g() {
        return this.f4941f;
    }

    public String toString() {
        return "Trim Path: {start: " + this.f4938c + ", end: " + this.f4939d + ", offset: " + this.f4940e + "}";
    }
}
