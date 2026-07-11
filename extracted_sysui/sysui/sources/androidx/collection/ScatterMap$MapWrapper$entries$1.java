package androidx.collection;

import W0.a;
import e1.AbstractC0343h;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import kotlin.jvm.internal.g;
import kotlin.jvm.internal.n;

/* JADX INFO: Add missing generic type declarations: [V, K] */
/* JADX INFO: loaded from: classes.dex */
public final class ScatterMap$MapWrapper$entries$1<K, V> implements Set<Map.Entry<? extends K, ? extends V>>, a {
    final /* synthetic */ ScatterMap<K, V> this$0;

    public ScatterMap$MapWrapper$entries$1(ScatterMap<K, V> scatterMap) {
        this.this$0 = scatterMap;
    }

    @Override // java.util.Set, java.util.Collection
    public /* bridge */ /* synthetic */ boolean add(Object obj) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    @Override // java.util.Set, java.util.Collection
    public boolean addAll(Collection<? extends Map.Entry<? extends K, ? extends V>> collection) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    @Override // java.util.Set, java.util.Collection
    public void clear() {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    @Override // java.util.Set, java.util.Collection
    public final /* bridge */ boolean contains(Object obj) {
        if (obj instanceof Map.Entry) {
            return contains((Map.Entry) obj);
        }
        return false;
    }

    @Override // java.util.Set, java.util.Collection
    public boolean containsAll(Collection<? extends Object> elements) {
        n.g(elements, "elements");
        ScatterMap<K, V> scatterMap = this.this$0;
        if (elements.isEmpty()) {
            return true;
        }
        Iterator<T> it = elements.iterator();
        while (it.hasNext()) {
            Map.Entry entry = (Map.Entry) it.next();
            if (!n.c(scatterMap.get((K) entry.getKey()), entry.getValue())) {
                return false;
            }
        }
        return true;
    }

    public int getSize() {
        return this.this$0._size;
    }

    @Override // java.util.Set, java.util.Collection
    public boolean isEmpty() {
        return this.this$0.isEmpty();
    }

    @Override // java.util.Set, java.util.Collection, java.lang.Iterable
    public Iterator<Map.Entry<K, V>> iterator() {
        return AbstractC0343h.a(new ScatterMap$MapWrapper$entries$1$iterator$1(this.this$0, null));
    }

    @Override // java.util.Set, java.util.Collection
    public boolean remove(Object obj) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    @Override // java.util.Set, java.util.Collection
    public boolean removeAll(Collection<? extends Object> collection) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    @Override // java.util.Set, java.util.Collection
    public boolean retainAll(Collection<? extends Object> collection) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    @Override // java.util.Set, java.util.Collection
    public final /* bridge */ int size() {
        return getSize();
    }

    @Override // java.util.Set, java.util.Collection
    public Object[] toArray() {
        return g.a(this);
    }

    public boolean add(Map.Entry<? extends K, ? extends V> entry) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    public boolean contains(Map.Entry<? extends K, ? extends V> element) {
        n.g(element, "element");
        return n.c(this.this$0.get(element.getKey()), element.getValue());
    }

    @Override // java.util.Set, java.util.Collection
    public <T> T[] toArray(T[] array) {
        n.g(array, "array");
        return (T[]) g.b(this, array);
    }
}
