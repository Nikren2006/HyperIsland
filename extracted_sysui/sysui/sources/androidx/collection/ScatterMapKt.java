package androidx.collection;

import H0.i;
import kotlin.jvm.internal.n;

/* JADX INFO: loaded from: classes.dex */
public final class ScatterMapKt {
    public static final long AllEmpty = -9187201950435737472L;
    public static final long BitmaskLsb = 72340172838076673L;
    public static final long BitmaskMsb = -9187201950435737472L;
    public static final int ClonedMetadataCount = 7;
    public static final int DefaultScatterCapacity = 6;
    public static final long Deleted = 254;
    public static final long Empty = 128;
    public static final long[] EmptyGroup = {-9187201950435737345L, -1};
    private static final MutableScatterMap EmptyScatterMap = new MutableScatterMap(0);
    public static final int GroupWidth = 8;
    public static final int MurmurHashC1 = -862048943;
    public static final long Sentinel = 255;

    public static final <K, V> ScatterMap<K, V> emptyScatterMap() {
        MutableScatterMap mutableScatterMap = EmptyScatterMap;
        n.e(mutableScatterMap, "null cannot be cast to non-null type androidx.collection.ScatterMap<K of androidx.collection.ScatterMapKt.emptyScatterMap, V of androidx.collection.ScatterMapKt.emptyScatterMap>");
        return mutableScatterMap;
    }

    public static final int get(long j2) {
        return Long.numberOfTrailingZeros(j2) >> 3;
    }

    public static /* synthetic */ void getBitmaskLsb$annotations() {
    }

    public static /* synthetic */ void getBitmaskMsb$annotations() {
    }

    public static /* synthetic */ void getSentinel$annotations() {
    }

    public static final long group(long[] metadata, int i2) {
        n.g(metadata, "metadata");
        int i3 = i2 >> 3;
        int i4 = (i2 & 7) << 3;
        return (((-i4) >> 63) & (metadata[i3 + 1] << (64 - i4))) | (metadata[i3] >>> i4);
    }

    public static final int h1(int i2) {
        return i2 >>> 7;
    }

    public static final int h2(int i2) {
        return i2 & 127;
    }

    public static final boolean hasNext(long j2) {
        return j2 != 0;
    }

    public static final int hash(Object obj) {
        int iHashCode = (obj != null ? obj.hashCode() : 0) * MurmurHashC1;
        return iHashCode ^ (iHashCode << 16);
    }

    public static final boolean isDeleted(long[] metadata, int i2) {
        n.g(metadata, "metadata");
        return ((metadata[i2 >> 3] >> ((i2 & 7) << 3)) & 255) == 254;
    }

    public static final boolean isEmpty(long[] metadata, int i2) {
        n.g(metadata, "metadata");
        return ((metadata[i2 >> 3] >> ((i2 & 7) << 3)) & 255) == 128;
    }

    public static final boolean isFull(long j2) {
        return j2 < 128;
    }

    public static final int loadedCapacity(int i2) {
        if (i2 == 7) {
            return 6;
        }
        return i2 - (i2 / 8);
    }

    public static final int lowestBitSet(long j2) {
        return Long.numberOfTrailingZeros(j2) >> 3;
    }

    public static final long maskEmpty(long j2) {
        return j2 & ((~j2) << 6) & (-9187201950435737472L);
    }

    public static final long maskEmptyOrDeleted(long j2) {
        return j2 & ((~j2) << 7) & (-9187201950435737472L);
    }

    public static final long match(long j2, int i2) {
        long j3 = j2 ^ (((long) i2) * BitmaskLsb);
        return (~j3) & (j3 - BitmaskLsb) & (-9187201950435737472L);
    }

    public static final <K, V> MutableScatterMap<K, V> mutableScatterMapOf() {
        return new MutableScatterMap<>(0, 1, null);
    }

    public static final long next(long j2) {
        return j2 & (j2 - 1);
    }

    public static final int nextCapacity(int i2) {
        if (i2 == 0) {
            return 6;
        }
        return (i2 * 2) + 1;
    }

    public static final int normalizeCapacity(int i2) {
        if (i2 > 0) {
            return (-1) >>> Integer.numberOfLeadingZeros(i2);
        }
        return 0;
    }

    public static final long readRawMetadata(long[] data, int i2) {
        n.g(data, "data");
        return (data[i2 >> 3] >> ((i2 & 7) << 3)) & 255;
    }

    public static final int unloadedCapacity(int i2) {
        if (i2 == 7) {
            return 8;
        }
        return i2 + ((i2 - 1) / 7);
    }

    public static final void writeRawMetadata(long[] data, int i2, long j2) {
        n.g(data, "data");
        int i3 = i2 >> 3;
        int i4 = (i2 & 7) << 3;
        data[i3] = (j2 << i4) | (data[i3] & (~(255 << i4)));
    }

    public static final boolean isFull(long[] metadata, int i2) {
        n.g(metadata, "metadata");
        return ((metadata[i2 >> 3] >> ((i2 & 7) << 3)) & 255) < 128;
    }

    public static final <K, V> MutableScatterMap<K, V> mutableScatterMapOf(i... pairs) {
        n.g(pairs, "pairs");
        MutableScatterMap<K, V> mutableScatterMap = new MutableScatterMap<>(pairs.length);
        mutableScatterMap.putAll(pairs);
        return mutableScatterMap;
    }
}
