package androidx.collection;

import kotlin.jvm.internal.n;

/* JADX INFO: loaded from: classes.dex */
public final class ObjectIntMapKt {
    private static final MutableObjectIntMap<Object> EmptyObjectIntMap = new MutableObjectIntMap<>(0);

    public static final <K> ObjectIntMap<K> emptyObjectIntMap() {
        MutableObjectIntMap<Object> mutableObjectIntMap = EmptyObjectIntMap;
        n.e(mutableObjectIntMap, "null cannot be cast to non-null type androidx.collection.ObjectIntMap<K of androidx.collection.ObjectIntMapKt.emptyObjectIntMap>");
        return mutableObjectIntMap;
    }

    public static final <K> MutableObjectIntMap<K> mutableObjectIntMapOf() {
        return new MutableObjectIntMap<>(0, 1, null);
    }

    public static final <K> ObjectIntMap<K> objectIntMap() {
        MutableObjectIntMap<Object> mutableObjectIntMap = EmptyObjectIntMap;
        n.e(mutableObjectIntMap, "null cannot be cast to non-null type androidx.collection.ObjectIntMap<K of androidx.collection.ObjectIntMapKt.objectIntMap>");
        return mutableObjectIntMap;
    }

    public static final <K> ObjectIntMap<K> objectIntMapOf(K k2, int i2) {
        MutableObjectIntMap mutableObjectIntMap = new MutableObjectIntMap(0, 1, null);
        mutableObjectIntMap.set(k2, i2);
        return mutableObjectIntMap;
    }

    public static final <K> MutableObjectIntMap<K> mutableObjectIntMapOf(K k2, int i2) {
        MutableObjectIntMap<K> mutableObjectIntMap = new MutableObjectIntMap<>(0, 1, null);
        mutableObjectIntMap.set(k2, i2);
        return mutableObjectIntMap;
    }

    public static final <K> ObjectIntMap<K> objectIntMapOf(K k2, int i2, K k3, int i3) {
        MutableObjectIntMap mutableObjectIntMap = new MutableObjectIntMap(0, 1, null);
        mutableObjectIntMap.set(k2, i2);
        mutableObjectIntMap.set(k3, i3);
        return mutableObjectIntMap;
    }

    public static final <K> MutableObjectIntMap<K> mutableObjectIntMapOf(K k2, int i2, K k3, int i3) {
        MutableObjectIntMap<K> mutableObjectIntMap = new MutableObjectIntMap<>(0, 1, null);
        mutableObjectIntMap.set(k2, i2);
        mutableObjectIntMap.set(k3, i3);
        return mutableObjectIntMap;
    }

    public static final <K> ObjectIntMap<K> objectIntMapOf(K k2, int i2, K k3, int i3, K k4, int i4) {
        MutableObjectIntMap mutableObjectIntMap = new MutableObjectIntMap(0, 1, null);
        mutableObjectIntMap.set(k2, i2);
        mutableObjectIntMap.set(k3, i3);
        mutableObjectIntMap.set(k4, i4);
        return mutableObjectIntMap;
    }

    public static final <K> MutableObjectIntMap<K> mutableObjectIntMapOf(K k2, int i2, K k3, int i3, K k4, int i4) {
        MutableObjectIntMap<K> mutableObjectIntMap = new MutableObjectIntMap<>(0, 1, null);
        mutableObjectIntMap.set(k2, i2);
        mutableObjectIntMap.set(k3, i3);
        mutableObjectIntMap.set(k4, i4);
        return mutableObjectIntMap;
    }

    public static final <K> ObjectIntMap<K> objectIntMapOf(K k2, int i2, K k3, int i3, K k4, int i4, K k5, int i5) {
        MutableObjectIntMap mutableObjectIntMap = new MutableObjectIntMap(0, 1, null);
        mutableObjectIntMap.set(k2, i2);
        mutableObjectIntMap.set(k3, i3);
        mutableObjectIntMap.set(k4, i4);
        mutableObjectIntMap.set(k5, i5);
        return mutableObjectIntMap;
    }

    public static final <K> MutableObjectIntMap<K> mutableObjectIntMapOf(K k2, int i2, K k3, int i3, K k4, int i4, K k5, int i5) {
        MutableObjectIntMap<K> mutableObjectIntMap = new MutableObjectIntMap<>(0, 1, null);
        mutableObjectIntMap.set(k2, i2);
        mutableObjectIntMap.set(k3, i3);
        mutableObjectIntMap.set(k4, i4);
        mutableObjectIntMap.set(k5, i5);
        return mutableObjectIntMap;
    }

    public static final <K> ObjectIntMap<K> objectIntMapOf(K k2, int i2, K k3, int i3, K k4, int i4, K k5, int i5, K k6, int i6) {
        MutableObjectIntMap mutableObjectIntMap = new MutableObjectIntMap(0, 1, null);
        mutableObjectIntMap.set(k2, i2);
        mutableObjectIntMap.set(k3, i3);
        mutableObjectIntMap.set(k4, i4);
        mutableObjectIntMap.set(k5, i5);
        mutableObjectIntMap.set(k6, i6);
        return mutableObjectIntMap;
    }

    public static final <K> MutableObjectIntMap<K> mutableObjectIntMapOf(K k2, int i2, K k3, int i3, K k4, int i4, K k5, int i5, K k6, int i6) {
        MutableObjectIntMap<K> mutableObjectIntMap = new MutableObjectIntMap<>(0, 1, null);
        mutableObjectIntMap.set(k2, i2);
        mutableObjectIntMap.set(k3, i3);
        mutableObjectIntMap.set(k4, i4);
        mutableObjectIntMap.set(k5, i5);
        mutableObjectIntMap.set(k6, i6);
        return mutableObjectIntMap;
    }
}
