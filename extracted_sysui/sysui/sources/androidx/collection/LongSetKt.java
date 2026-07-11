package androidx.collection;

import kotlin.jvm.internal.n;

/* JADX INFO: loaded from: classes.dex */
public final class LongSetKt {
    private static final MutableLongSet EmptyLongSet = new MutableLongSet(0);
    private static final long[] EmptyLongArray = new long[0];

    public static final LongSet emptyLongSet() {
        return EmptyLongSet;
    }

    public static final long[] getEmptyLongArray() {
        return EmptyLongArray;
    }

    public static final int hash(long j2) {
        int iHashCode = Long.hashCode(j2) * ScatterMapKt.MurmurHashC1;
        return iHashCode ^ (iHashCode << 16);
    }

    public static final LongSet longSetOf() {
        return EmptyLongSet;
    }

    public static final MutableLongSet mutableLongSetOf() {
        return new MutableLongSet(0, 1, null);
    }

    public static final LongSet longSetOf(long j2) {
        return mutableLongSetOf(j2);
    }

    public static final MutableLongSet mutableLongSetOf(long j2) {
        MutableLongSet mutableLongSet = new MutableLongSet(1);
        mutableLongSet.plusAssign(j2);
        return mutableLongSet;
    }

    public static final LongSet longSetOf(long j2, long j3) {
        return mutableLongSetOf(j2, j3);
    }

    public static final LongSet longSetOf(long j2, long j3, long j4) {
        return mutableLongSetOf(j2, j3, j4);
    }

    public static final MutableLongSet mutableLongSetOf(long j2, long j3) {
        MutableLongSet mutableLongSet = new MutableLongSet(2);
        mutableLongSet.plusAssign(j2);
        mutableLongSet.plusAssign(j3);
        return mutableLongSet;
    }

    public static final LongSet longSetOf(long... elements) {
        n.g(elements, "elements");
        MutableLongSet mutableLongSet = new MutableLongSet(elements.length);
        mutableLongSet.plusAssign(elements);
        return mutableLongSet;
    }

    public static final MutableLongSet mutableLongSetOf(long j2, long j3, long j4) {
        MutableLongSet mutableLongSet = new MutableLongSet(3);
        mutableLongSet.plusAssign(j2);
        mutableLongSet.plusAssign(j3);
        mutableLongSet.plusAssign(j4);
        return mutableLongSet;
    }

    public static final MutableLongSet mutableLongSetOf(long... elements) {
        n.g(elements, "elements");
        MutableLongSet mutableLongSet = new MutableLongSet(elements.length);
        mutableLongSet.plusAssign(elements);
        return mutableLongSet;
    }
}
