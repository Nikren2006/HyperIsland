package g1;

import kotlin.jvm.functions.Function1;

/* JADX INFO: renamed from: g1.w, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes2.dex */
public final class C0395w {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Object f4466a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final Function1 f4467b;

    public C0395w(Object obj, Function1 function1) {
        this.f4466a = obj;
        this.f4467b = function1;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof C0395w)) {
            return false;
        }
        C0395w c0395w = (C0395w) obj;
        return kotlin.jvm.internal.n.c(this.f4466a, c0395w.f4466a) && kotlin.jvm.internal.n.c(this.f4467b, c0395w.f4467b);
    }

    public int hashCode() {
        Object obj = this.f4466a;
        return ((obj == null ? 0 : obj.hashCode()) * 31) + this.f4467b.hashCode();
    }

    public String toString() {
        return "CompletedWithCancellation(result=" + this.f4466a + ", onCancellation=" + this.f4467b + ')';
    }
}
