package androidx.core.view;

import I0.r;
import I0.u;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.jvm.functions.Function1;

/* JADX INFO: loaded from: classes.dex */
public final class TreeIterator<T> implements Iterator<T>, W0.a {
    private final Function1 getChildIterator;
    private Iterator<? extends T> iterator;
    private final List<Iterator<T>> stack = new ArrayList();

    public TreeIterator(Iterator<? extends T> it, Function1 function1) {
        this.getChildIterator = function1;
        this.iterator = it;
    }

    /* JADX WARN: Type inference incomplete: some casts might be missing */
    private final void prepareNextIterator(T t2) {
        Iterator<? extends T> it = (Iterator) this.getChildIterator.invoke(t2);
        if (it != null && it.hasNext()) {
            this.stack.add((Iterator<T>) this.iterator);
            this.iterator = it;
        } else {
            while (!this.iterator.hasNext() && !this.stack.isEmpty()) {
                this.iterator = (Iterator) u.V(this.stack);
                r.A(this.stack);
            }
        }
    }

    @Override // java.util.Iterator
    public boolean hasNext() {
        return this.iterator.hasNext();
    }

    @Override // java.util.Iterator
    public T next() {
        T next = this.iterator.next();
        prepareNextIterator(next);
        return next;
    }

    @Override // java.util.Iterator
    public void remove() {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }
}
