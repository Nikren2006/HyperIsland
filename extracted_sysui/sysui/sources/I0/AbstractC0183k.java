package I0;

import java.util.Iterator;

/* JADX INFO: renamed from: I0.k, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes2.dex */
public abstract class AbstractC0183k implements Iterator, W0.a {
    @Override // java.util.Iterator
    public /* bridge */ /* synthetic */ Object next() {
        return Boolean.valueOf(nextBoolean());
    }

    public abstract boolean nextBoolean();

    @Override // java.util.Iterator
    public void remove() {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    @Override // java.util.Iterator
    public final Boolean next() {
        return Boolean.valueOf(nextBoolean());
    }
}
