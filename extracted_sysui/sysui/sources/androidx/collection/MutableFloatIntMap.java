package androidx.collection;

import H0.p;
import I0.AbstractC0180h;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.n;

/* JADX INFO: loaded from: classes.dex */
public final class MutableFloatIntMap extends FloatIntMap {
    private int growthLimit;

    public MutableFloatIntMap() {
        this(0, 1, null);
    }

    private final void adjustStorage() {
        if (this._capacity <= 8 || Long.compareUnsigned(p.a(p.a(this._size) * 32), p.a(p.a(this._capacity) * 25)) > 0) {
            resizeStorage(ScatterMapKt.nextCapacity(this._capacity));
        } else {
            removeDeletedMarkers();
        }
    }

    private final int findFirstAvailableSlot(int i2) {
        int i3 = this._capacity;
        int i4 = i2 & i3;
        int i5 = 0;
        while (true) {
            long[] jArr = this.metadata;
            int i6 = i4 >> 3;
            int i7 = (i4 & 7) << 3;
            long j2 = ((jArr[i6 + 1] << (64 - i7)) & ((-i7) >> 63)) | (jArr[i6] >>> i7);
            long j3 = j2 & ((~j2) << 7) & (-9187201950435737472L);
            if (j3 != 0) {
                return (i4 + (Long.numberOfTrailingZeros(j3) >> 3)) & i3;
            }
            i5 += 8;
            i4 = (i4 + i5) & i3;
        }
    }

    private final int findInsertIndex(float f2) {
        int iHashCode = Float.hashCode(f2) * ScatterMapKt.MurmurHashC1;
        int i2 = iHashCode ^ (iHashCode << 16);
        int i3 = i2 >>> 7;
        int i4 = i2 & 127;
        int i5 = this._capacity;
        int i6 = i3 & i5;
        int i7 = 0;
        while (true) {
            long[] jArr = this.metadata;
            int i8 = i6 >> 3;
            int i9 = (i6 & 7) << 3;
            long j2 = ((jArr[i8 + 1] << (64 - i9)) & ((-i9) >> 63)) | (jArr[i8] >>> i9);
            long j3 = i4;
            int i10 = i7;
            long j4 = j2 ^ (j3 * ScatterMapKt.BitmaskLsb);
            for (long j5 = (~j4) & (j4 - ScatterMapKt.BitmaskLsb) & (-9187201950435737472L); j5 != 0; j5 &= j5 - 1) {
                int iNumberOfTrailingZeros = ((Long.numberOfTrailingZeros(j5) >> 3) + i6) & i5;
                if (this.keys[iNumberOfTrailingZeros] == f2) {
                    return iNumberOfTrailingZeros;
                }
            }
            if ((((~j2) << 6) & j2 & (-9187201950435737472L)) != 0) {
                int iFindFirstAvailableSlot = findFirstAvailableSlot(i3);
                if (this.growthLimit == 0 && ((this.metadata[iFindFirstAvailableSlot >> 3] >> ((iFindFirstAvailableSlot & 7) << 3)) & 255) != 254) {
                    adjustStorage();
                    iFindFirstAvailableSlot = findFirstAvailableSlot(i3);
                }
                this._size++;
                int i11 = this.growthLimit;
                long[] jArr2 = this.metadata;
                int i12 = iFindFirstAvailableSlot >> 3;
                long j6 = jArr2[i12];
                int i13 = (iFindFirstAvailableSlot & 7) << 3;
                this.growthLimit = i11 - (((j6 >> i13) & 255) == 128 ? 1 : 0);
                jArr2[i12] = (j6 & (~(255 << i13))) | (j3 << i13);
                int i14 = this._capacity;
                int i15 = ((iFindFirstAvailableSlot - 7) & i14) + (i14 & 7);
                int i16 = i15 >> 3;
                int i17 = (i15 & 7) << 3;
                jArr2[i16] = ((~(255 << i17)) & jArr2[i16]) | (j3 << i17);
                return ~iFindFirstAvailableSlot;
            }
            i7 = i10 + 8;
            i6 = (i6 + i7) & i5;
        }
    }

    private final void initializeGrowth() {
        this.growthLimit = ScatterMapKt.loadedCapacity(getCapacity()) - this._size;
    }

    private final void initializeMetadata(int i2) {
        long[] jArr;
        if (i2 == 0) {
            jArr = ScatterMapKt.EmptyGroup;
        } else {
            jArr = new long[((i2 + 15) & (-8)) >> 3];
            AbstractC0180h.p(jArr, -9187201950435737472L, 0, 0, 6, null);
        }
        this.metadata = jArr;
        int i3 = i2 >> 3;
        long j2 = 255 << ((i2 & 7) << 3);
        jArr[i3] = (jArr[i3] & (~j2)) | j2;
        initializeGrowth();
    }

    private final void initializeStorage(int i2) {
        int iMax = i2 > 0 ? Math.max(7, ScatterMapKt.normalizeCapacity(i2)) : 0;
        this._capacity = iMax;
        initializeMetadata(iMax);
        this.keys = new float[iMax];
        this.values = new int[iMax];
    }

    private final void removeDeletedMarkers() {
        long[] jArr = this.metadata;
        int i2 = this._capacity;
        int i3 = 0;
        for (int i4 = 0; i4 < i2; i4++) {
            int i5 = i4 >> 3;
            int i6 = (i4 & 7) << 3;
            if (((jArr[i5] >> i6) & 255) == 254) {
                long[] jArr2 = this.metadata;
                jArr2[i5] = (128 << i6) | (jArr2[i5] & (~(255 << i6)));
                int i7 = this._capacity;
                int i8 = ((i4 - 7) & i7) + (i7 & 7);
                int i9 = i8 >> 3;
                int i10 = (i8 & 7) << 3;
                jArr2[i9] = ((~(255 << i10)) & jArr2[i9]) | (128 << i10);
                i3++;
            }
        }
        this.growthLimit += i3;
    }

    private final void resizeStorage(int i2) {
        long[] jArr;
        float[] fArr;
        long[] jArr2 = this.metadata;
        float[] fArr2 = this.keys;
        int[] iArr = this.values;
        int i3 = this._capacity;
        initializeStorage(i2);
        float[] fArr3 = this.keys;
        int[] iArr2 = this.values;
        int i4 = 0;
        while (i4 < i3) {
            if (((jArr2[i4 >> 3] >> ((i4 & 7) << 3)) & 255) < 128) {
                float f2 = fArr2[i4];
                int iHashCode = Float.hashCode(f2) * ScatterMapKt.MurmurHashC1;
                int i5 = iHashCode ^ (iHashCode << 16);
                int iFindFirstAvailableSlot = findFirstAvailableSlot(i5 >>> 7);
                long j2 = i5 & 127;
                long[] jArr3 = this.metadata;
                int i6 = iFindFirstAvailableSlot >> 3;
                int i7 = (iFindFirstAvailableSlot & 7) << 3;
                jArr = jArr2;
                fArr = fArr2;
                jArr3[i6] = (jArr3[i6] & (~(255 << i7))) | (j2 << i7);
                int i8 = this._capacity;
                int i9 = ((iFindFirstAvailableSlot - 7) & i8) + (i8 & 7);
                int i10 = i9 >> 3;
                int i11 = (i9 & 7) << 3;
                jArr3[i10] = ((~(255 << i11)) & jArr3[i10]) | (j2 << i11);
                fArr3[iFindFirstAvailableSlot] = f2;
                iArr2[iFindFirstAvailableSlot] = iArr[i4];
            } else {
                jArr = jArr2;
                fArr = fArr2;
            }
            i4++;
            jArr2 = jArr;
            fArr2 = fArr;
        }
    }

    private final void writeMetadata(int i2, long j2) {
        long[] jArr = this.metadata;
        int i3 = i2 >> 3;
        int i4 = (i2 & 7) << 3;
        jArr[i3] = (jArr[i3] & (~(255 << i4))) | (j2 << i4);
        int i5 = this._capacity;
        int i6 = ((i2 - 7) & i5) + (i5 & 7);
        int i7 = i6 >> 3;
        int i8 = (i6 & 7) << 3;
        jArr[i7] = (j2 << i8) | (jArr[i7] & (~(255 << i8)));
    }

    public final void clear() {
        this._size = 0;
        long[] jArr = this.metadata;
        if (jArr != ScatterMapKt.EmptyGroup) {
            AbstractC0180h.p(jArr, -9187201950435737472L, 0, 0, 6, null);
            long[] jArr2 = this.metadata;
            int i2 = this._capacity;
            int i3 = i2 >> 3;
            long j2 = 255 << ((i2 & 7) << 3);
            jArr2[i3] = (jArr2[i3] & (~j2)) | j2;
        }
        initializeGrowth();
    }

    public final int getOrPut(float f2, Function0 defaultValue) {
        n.g(defaultValue, "defaultValue");
        int iFindKeyIndex = findKeyIndex(f2);
        if (iFindKeyIndex >= 0) {
            return this.values[iFindKeyIndex];
        }
        int iIntValue = ((Number) defaultValue.invoke()).intValue();
        put(f2, iIntValue);
        return iIntValue;
    }

    public final void minusAssign(float f2) {
        remove(f2);
    }

    public final void plusAssign(FloatIntMap from) {
        n.g(from, "from");
        putAll(from);
    }

    public final void put(float f2, int i2) {
        set(f2, i2);
    }

    public final void putAll(FloatIntMap from) {
        n.g(from, "from");
        float[] fArr = from.keys;
        int[] iArr = from.values;
        long[] jArr = from.metadata;
        int length = jArr.length - 2;
        if (length < 0) {
            return;
        }
        int i2 = 0;
        while (true) {
            long j2 = jArr[i2];
            if ((((~j2) << 7) & j2 & (-9187201950435737472L)) != -9187201950435737472L) {
                int i3 = 8 - ((~(i2 - length)) >>> 31);
                for (int i4 = 0; i4 < i3; i4++) {
                    if ((255 & j2) < 128) {
                        int i5 = (i2 << 3) + i4;
                        set(fArr[i5], iArr[i5]);
                    }
                    j2 >>= 8;
                }
                if (i3 != 8) {
                    return;
                }
            }
            if (i2 == length) {
                return;
            } else {
                i2++;
            }
        }
    }

    public final void remove(float f2) {
        int iFindKeyIndex = findKeyIndex(f2);
        if (iFindKeyIndex >= 0) {
            removeValueAt(iFindKeyIndex);
        }
    }

    public final void removeIf(Function2 predicate) {
        n.g(predicate, "predicate");
        long[] jArr = this.metadata;
        int length = jArr.length - 2;
        if (length < 0) {
            return;
        }
        int i2 = 0;
        while (true) {
            long j2 = jArr[i2];
            if ((((~j2) << 7) & j2 & (-9187201950435737472L)) != -9187201950435737472L) {
                int i3 = 8 - ((~(i2 - length)) >>> 31);
                for (int i4 = 0; i4 < i3; i4++) {
                    if ((255 & j2) < 128) {
                        int i5 = (i2 << 3) + i4;
                        if (((Boolean) predicate.invoke(Float.valueOf(this.keys[i5]), Integer.valueOf(this.values[i5]))).booleanValue()) {
                            removeValueAt(i5);
                        }
                    }
                    j2 >>= 8;
                }
                if (i3 != 8) {
                    return;
                }
            }
            if (i2 == length) {
                return;
            } else {
                i2++;
            }
        }
    }

    public final void removeValueAt(int i2) {
        this._size--;
        long[] jArr = this.metadata;
        int i3 = i2 >> 3;
        int i4 = (i2 & 7) << 3;
        jArr[i3] = (jArr[i3] & (~(255 << i4))) | (254 << i4);
        int i5 = this._capacity;
        int i6 = ((i2 - 7) & i5) + (i5 & 7);
        int i7 = i6 >> 3;
        int i8 = (i6 & 7) << 3;
        jArr[i7] = (jArr[i7] & (~(255 << i8))) | (254 << i8);
    }

    public final void set(float f2, int i2) {
        int iFindInsertIndex = findInsertIndex(f2);
        if (iFindInsertIndex < 0) {
            iFindInsertIndex = ~iFindInsertIndex;
        }
        this.keys[iFindInsertIndex] = f2;
        this.values[iFindInsertIndex] = i2;
    }

    public final int trim() {
        int i2 = this._capacity;
        int iNormalizeCapacity = ScatterMapKt.normalizeCapacity(ScatterMapKt.unloadedCapacity(this._size));
        if (iNormalizeCapacity >= i2) {
            return 0;
        }
        resizeStorage(iNormalizeCapacity);
        return i2 - this._capacity;
    }

    public /* synthetic */ MutableFloatIntMap(int i2, int i3, DefaultConstructorMarker defaultConstructorMarker) {
        this((i3 & 1) != 0 ? 6 : i2);
    }

    public final void minusAssign(float[] keys) {
        n.g(keys, "keys");
        for (float f2 : keys) {
            remove(f2);
        }
    }

    public final int put(float f2, int i2, int i3) {
        int iFindInsertIndex = findInsertIndex(f2);
        if (iFindInsertIndex < 0) {
            iFindInsertIndex = ~iFindInsertIndex;
        } else {
            i3 = this.values[iFindInsertIndex];
        }
        this.keys[iFindInsertIndex] = f2;
        this.values[iFindInsertIndex] = i2;
        return i3;
    }

    public MutableFloatIntMap(int i2) {
        super(null);
        if (i2 >= 0) {
            initializeStorage(ScatterMapKt.unloadedCapacity(i2));
            return;
        }
        throw new IllegalArgumentException("Capacity must be a positive value.");
    }

    public final boolean remove(float f2, int i2) {
        int iFindKeyIndex = findKeyIndex(f2);
        if (iFindKeyIndex < 0 || this.values[iFindKeyIndex] != i2) {
            return false;
        }
        removeValueAt(iFindKeyIndex);
        return true;
    }

    public final void minusAssign(FloatSet keys) {
        n.g(keys, "keys");
        float[] fArr = keys.elements;
        long[] jArr = keys.metadata;
        int length = jArr.length - 2;
        if (length < 0) {
            return;
        }
        int i2 = 0;
        while (true) {
            long j2 = jArr[i2];
            if ((((~j2) << 7) & j2 & (-9187201950435737472L)) != -9187201950435737472L) {
                int i3 = 8 - ((~(i2 - length)) >>> 31);
                for (int i4 = 0; i4 < i3; i4++) {
                    if ((255 & j2) < 128) {
                        remove(fArr[(i2 << 3) + i4]);
                    }
                    j2 >>= 8;
                }
                if (i3 != 8) {
                    return;
                }
            }
            if (i2 == length) {
                return;
            } else {
                i2++;
            }
        }
    }

    public final void minusAssign(FloatList keys) {
        n.g(keys, "keys");
        float[] fArr = keys.content;
        int i2 = keys._size;
        for (int i3 = 0; i3 < i2; i3++) {
            remove(fArr[i3]);
        }
    }
}
