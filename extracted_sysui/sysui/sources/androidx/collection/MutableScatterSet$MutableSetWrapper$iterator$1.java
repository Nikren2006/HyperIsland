package androidx.collection;

import W0.a;
import e1.AbstractC0343h;
import java.util.Iterator;

/* JADX INFO: Add missing generic type declarations: [E] */
/* JADX INFO: loaded from: classes.dex */
public final class MutableScatterSet$MutableSetWrapper$iterator$1<E> implements Iterator<E>, a {
    private int current = -1;
    private final Iterator<E> iterator;
    final /* synthetic */ MutableScatterSet<E> this$0;

    public MutableScatterSet$MutableSetWrapper$iterator$1(MutableScatterSet<E> mutableScatterSet) {
        this.this$0 = mutableScatterSet;
        this.iterator = AbstractC0343h.a(new MutableScatterSet$MutableSetWrapper$iterator$1$iterator$1(mutableScatterSet, this, null));
    }

    public final int getCurrent() {
        return this.current;
    }

    public final Iterator<E> getIterator() {
        return this.iterator;
    }

    @Override // java.util.Iterator
    public boolean hasNext() {
        return this.iterator.hasNext();
    }

    @Override // java.util.Iterator
    public E next() {
        return this.iterator.next();
    }

    @Override // java.util.Iterator
    public void remove() {
        int i2 = this.current;
        if (i2 != -1) {
            this.this$0.removeElementAt(i2);
            this.current = -1;
        }
    }

    public final void setCurrent(int i2) {
        this.current = i2;
    }
}
