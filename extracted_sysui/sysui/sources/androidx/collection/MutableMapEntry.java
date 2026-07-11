package androidx.collection;

import W0.e;
import java.util.Map;
import kotlin.jvm.internal.n;

/* JADX INFO: loaded from: classes.dex */
final class MutableMapEntry<K, V> implements Map.Entry<K, V>, e.a {
    private final int index;
    private final Object[] keys;
    private final Object[] values;

    public MutableMapEntry(Object[] keys, Object[] values, int i2) {
        n.g(keys, "keys");
        n.g(values, "values");
        this.keys = keys;
        this.values = values;
        this.index = i2;
    }

    public static /* synthetic */ void getKey$annotations() {
    }

    public static /* synthetic */ void getValue$annotations() {
    }

    public final int getIndex() {
        return this.index;
    }

    @Override // java.util.Map.Entry
    public K getKey() {
        return (K) this.keys[this.index];
    }

    public final Object[] getKeys() {
        return this.keys;
    }

    @Override // java.util.Map.Entry
    public V getValue() {
        return (V) this.values[this.index];
    }

    public final Object[] getValues() {
        return this.values;
    }

    @Override // java.util.Map.Entry
    public V setValue(V v2) {
        Object[] objArr = this.values;
        int i2 = this.index;
        V v3 = (V) objArr[i2];
        objArr[i2] = v2;
        return v3;
    }
}
