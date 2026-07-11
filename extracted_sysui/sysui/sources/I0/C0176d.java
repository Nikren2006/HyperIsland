package I0;

import java.util.Collection;
import java.util.Iterator;
import kotlin.jvm.internal.AbstractC0429c;

/* JADX INFO: renamed from: I0.d, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes2.dex */
public final class C0176d implements Collection, W0.a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Object[] f327a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final boolean f328b;

    public C0176d(Object[] values, boolean z2) {
        kotlin.jvm.internal.n.g(values, "values");
        this.f327a = values;
        this.f328b = z2;
    }

    @Override // java.util.Collection
    public boolean add(Object obj) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    @Override // java.util.Collection
    public boolean addAll(Collection collection) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    public int c() {
        return this.f327a.length;
    }

    @Override // java.util.Collection
    public void clear() {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    @Override // java.util.Collection
    public boolean contains(Object obj) {
        return AbstractC0181i.w(this.f327a, obj);
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
        return this.f327a.length == 0;
    }

    @Override // java.util.Collection, java.lang.Iterable
    public Iterator iterator() {
        return AbstractC0429c.a(this.f327a);
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
    public Object[] toArray(Object[] array) {
        kotlin.jvm.internal.n.g(array, "array");
        return kotlin.jvm.internal.g.b(this, array);
    }

    @Override // java.util.Collection
    public final Object[] toArray() {
        return AbstractC0184l.b(this.f327a, this.f328b);
    }
}
