package U;

import java.util.ArrayList;
import java.util.Iterator;

/* JADX INFO: loaded from: classes2.dex */
public final class e extends f implements Iterable {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final ArrayList f742a = new ArrayList();

    public boolean equals(Object obj) {
        return obj == this || ((obj instanceof e) && ((e) obj).f742a.equals(this.f742a));
    }

    public void h(f fVar) {
        if (fVar == null) {
            fVar = h.f743a;
        }
        this.f742a.add(fVar);
    }

    public int hashCode() {
        return this.f742a.hashCode();
    }

    @Override // java.lang.Iterable
    public Iterator iterator() {
        return this.f742a.iterator();
    }
}
