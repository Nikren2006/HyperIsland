package I0;

import java.io.Serializable;
import java.util.Collection;
import java.util.Iterator;
import java.util.Set;

/* JADX INFO: loaded from: classes2.dex */
public final class y implements Set, Serializable, W0.a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final y f338a = new y();

    @Override // java.util.Set, java.util.Collection
    public /* bridge */ /* synthetic */ boolean add(Object obj) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    @Override // java.util.Set, java.util.Collection
    public boolean addAll(Collection collection) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    public boolean c(Void element) {
        kotlin.jvm.internal.n.g(element, "element");
        return false;
    }

    @Override // java.util.Set, java.util.Collection
    public void clear() {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    @Override // java.util.Set, java.util.Collection
    public final /* bridge */ boolean contains(Object obj) {
        if (obj instanceof Void) {
            return c((Void) obj);
        }
        return false;
    }

    @Override // java.util.Set, java.util.Collection
    public boolean containsAll(Collection elements) {
        kotlin.jvm.internal.n.g(elements, "elements");
        return elements.isEmpty();
    }

    public int d() {
        return 0;
    }

    @Override // java.util.Set, java.util.Collection
    public boolean equals(Object obj) {
        return (obj instanceof Set) && ((Set) obj).isEmpty();
    }

    @Override // java.util.Set, java.util.Collection
    public int hashCode() {
        return 0;
    }

    @Override // java.util.Set, java.util.Collection
    public boolean isEmpty() {
        return true;
    }

    @Override // java.util.Set, java.util.Collection, java.lang.Iterable
    public Iterator iterator() {
        return v.f335a;
    }

    @Override // java.util.Set, java.util.Collection
    public boolean remove(Object obj) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    @Override // java.util.Set, java.util.Collection
    public boolean removeAll(Collection collection) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    @Override // java.util.Set, java.util.Collection
    public boolean retainAll(Collection collection) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    @Override // java.util.Set, java.util.Collection
    public final /* bridge */ int size() {
        return d();
    }

    @Override // java.util.Set, java.util.Collection
    public Object[] toArray() {
        return kotlin.jvm.internal.g.a(this);
    }

    public String toString() {
        return "[]";
    }

    @Override // java.util.Set, java.util.Collection
    public Object[] toArray(Object[] array) {
        kotlin.jvm.internal.n.g(array, "array");
        return kotlin.jvm.internal.g.b(this, array);
    }
}
