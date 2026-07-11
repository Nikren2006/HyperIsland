package androidx.collection.internal;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.n;

/* JADX INFO: loaded from: classes.dex */
public final class LruHashMap<K, V> {
    private final LinkedHashMap<K, V> map;

    public LruHashMap() {
        this(0, 0.0f, 3, null);
    }

    public final V get(K key) {
        n.g(key, "key");
        return this.map.get(key);
    }

    public final Set<Map.Entry<K, V>> getEntries() {
        Set<Map.Entry<K, V>> setEntrySet = this.map.entrySet();
        n.f(setEntrySet, "map.entries");
        return setEntrySet;
    }

    public final boolean isEmpty() {
        return this.map.isEmpty();
    }

    public final V put(K key, V value) {
        n.g(key, "key");
        n.g(value, "value");
        return this.map.put(key, value);
    }

    public final V remove(K key) {
        n.g(key, "key");
        return this.map.remove(key);
    }

    public LruHashMap(int i2, float f2) {
        this.map = new LinkedHashMap<>(i2, f2, true);
    }

    public /* synthetic */ LruHashMap(int i2, float f2, int i3, DefaultConstructorMarker defaultConstructorMarker) {
        this((i3 & 1) != 0 ? 16 : i2, (i3 & 2) != 0 ? 0.75f : f2);
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public LruHashMap(LruHashMap<? extends K, V> original) {
        this(0, 0.0f, 3, null);
        n.g(original, "original");
        for (Map.Entry<? extends K, V> entry : original.getEntries()) {
            put(entry.getKey(), entry.getValue());
        }
    }
}
