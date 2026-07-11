package kotlin.jvm.internal;

import java.io.Serializable;

/* JADX INFO: renamed from: kotlin.jvm.internal.a, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes2.dex */
public class C0427a implements j, Serializable {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Object f5035a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final Class f5036b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final String f5037c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final String f5038d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final boolean f5039e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final int f5040f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final int f5041g;

    public C0427a(int i2, Object obj, Class cls, String str, String str2, int i3) {
        this.f5035a = obj;
        this.f5036b = cls;
        this.f5037c = str;
        this.f5038d = str2;
        this.f5039e = (i3 & 1) == 1;
        this.f5040f = i2;
        this.f5041g = i3 >> 1;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof C0427a)) {
            return false;
        }
        C0427a c0427a = (C0427a) obj;
        return this.f5039e == c0427a.f5039e && this.f5040f == c0427a.f5040f && this.f5041g == c0427a.f5041g && n.c(this.f5035a, c0427a.f5035a) && n.c(this.f5036b, c0427a.f5036b) && this.f5037c.equals(c0427a.f5037c) && this.f5038d.equals(c0427a.f5038d);
    }

    @Override // kotlin.jvm.internal.j
    public int getArity() {
        return this.f5040f;
    }

    public int hashCode() {
        Object obj = this.f5035a;
        int iHashCode = (obj != null ? obj.hashCode() : 0) * 31;
        Class cls = this.f5036b;
        return ((((((((((iHashCode + (cls != null ? cls.hashCode() : 0)) * 31) + this.f5037c.hashCode()) * 31) + this.f5038d.hashCode()) * 31) + (this.f5039e ? 1231 : 1237)) * 31) + this.f5040f) * 31) + this.f5041g;
    }

    public String toString() {
        return z.f(this);
    }
}
