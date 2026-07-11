package androidx.collection;

import W0.a;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
final class MapEntry<K, V> implements Map.Entry<K, V>, a {
    private final K key;
    private final V value;

    public MapEntry(K k2, V v2) {
        this.key = k2;
        this.value = v2;
    }

    @Override // java.util.Map.Entry
    public K getKey() {
        return this.key;
    }

    @Override // java.util.Map.Entry
    public V getValue() {
        return this.value;
    }

    @Override // java.util.Map.Entry
    public V setValue(V v2) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }
}
