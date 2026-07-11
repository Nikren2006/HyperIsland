package I0;

import java.util.Collection;
import java.util.Iterator;
import kotlin.jvm.functions.Function1;

/* JADX INFO: renamed from: I0.a, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes2.dex */
public abstract class AbstractC0173a implements Collection, W0.a {

    /* JADX INFO: renamed from: I0.a$a, reason: collision with other inner class name */
    public static final class C0010a extends kotlin.jvm.internal.o implements Function1 {
        public C0010a() {
            super(1);
        }

        @Override // kotlin.jvm.functions.Function1
        public final CharSequence invoke(Object obj) {
            return obj == AbstractC0173a.this ? "(this Collection)" : String.valueOf(obj);
        }
    }

    @Override // java.util.Collection
    public boolean add(Object obj) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    @Override // java.util.Collection
    public boolean addAll(Collection collection) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    public abstract int c();

    @Override // java.util.Collection
    public void clear() {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    @Override // java.util.Collection
    public boolean contains(Object obj) {
        if (isEmpty()) {
            return false;
        }
        Iterator<E> it = iterator();
        while (it.hasNext()) {
            if (kotlin.jvm.internal.n.c(it.next(), obj)) {
                return true;
            }
        }
        return false;
    }

    @Override // java.util.Collection
    public boolean containsAll(Collection elements) {
        kotlin.jvm.internal.n.g(elements, "elements");
        if (elements.isEmpty()) {
            return true;
        }
        Iterator it = elements.iterator();
        while (it.hasNext()) {
            if (!contains(it.next())) {
                return false;
            }
        }
        return true;
    }

    @Override // java.util.Collection
    public boolean isEmpty() {
        return size() == 0;
    }

    @Override // java.util.Collection
    public boolean remove(Object obj) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    @Override // java.util.Collection
    public boolean removeAll(Collection collection) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    @Override // java.util.Collection
    public boolean retainAll(Collection collection) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    @Override // java.util.Collection
    public final /* bridge */ int size() {
        return c();
    }

    @Override // java.util.Collection
    public Object[] toArray() {
        return kotlin.jvm.internal.g.a(this);
    }

    public String toString() {
        return u.T(this, ", ", "[", "]", 0, null, new C0010a(), 24, null);
    }

    @Override // java.util.Collection
    public Object[] toArray(Object[] array) {
        kotlin.jvm.internal.n.g(array, "array");
        return kotlin.jvm.internal.g.b(this, array);
    }
}
