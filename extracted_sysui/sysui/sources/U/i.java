package U;

import java.util.Set;

/* JADX INFO: loaded from: classes2.dex */
public final class i extends f {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final W.h f744a = new W.h(false);

    public boolean equals(Object obj) {
        return obj == this || ((obj instanceof i) && ((i) obj).f744a.equals(this.f744a));
    }

    public void h(String str, f fVar) {
        W.h hVar = this.f744a;
        if (fVar == null) {
            fVar = h.f743a;
        }
        hVar.put(str, fVar);
    }

    public int hashCode() {
        return this.f744a.hashCode();
    }

    public Set i() {
        return this.f744a.entrySet();
    }
}
