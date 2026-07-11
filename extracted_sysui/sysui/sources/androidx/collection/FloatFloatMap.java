package androidx.collection;

import java.util.NoSuchElementException;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.n;

/* JADX INFO: loaded from: classes.dex */
public abstract class FloatFloatMap {
    public int _capacity;
    public int _size;
    public float[] keys;
    public long[] metadata;
    public float[] values;

    public /* synthetic */ FloatFloatMap(DefaultConstructorMarker defaultConstructorMarker) {
        this();
    }

    public static /* synthetic */ void getKeys$annotations() {
    }

    public static /* synthetic */ void getMetadata$annotations() {
    }

    public static /* synthetic */ void getValues$annotations() {
    }

    public static /* synthetic */ void get_capacity$collection$annotations() {
    }

    public static /* synthetic */ void get_size$collection$annotations() {
    }

    public static /* synthetic */ String joinToString$default(FloatFloatMap floatFloatMap, CharSequence charSequence, CharSequence charSequence2, CharSequence charSequence3, int i2, CharSequence charSequence4, int i3, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: joinToString");
        }
        if ((i3 & 1) != 0) {
            charSequence = ", ";
        }
        CharSequence charSequence5 = (i3 & 2) != 0 ? "" : charSequence2;
        CharSequence charSequence6 = (i3 & 4) == 0 ? charSequence3 : "";
        if ((i3 & 8) != 0) {
            i2 = -1;
        }
        int i4 = i2;
        if ((i3 & 16) != 0) {
            charSequence4 = "...";
        }
        return floatFloatMap.joinToString(charSequence, charSequence5, charSequence6, i4, charSequence4);
    }

    public final boolean all(Function2 predicate) {
        n.g(predicate, "predicate");
        float[] fArr = this.keys;
        float[] fArr2 = this.values;
        long[] jArr = this.metadata;
        int length = jArr.length - 2;
        if (length < 0) {
            return true;
        }
        int i2 = 0;
        while (true) {
            long j2 = jArr[i2];
            if ((((~j2) << 7) & j2 & (-9187201950435737472L)) != -9187201950435737472L) {
                int i3 = 8 - ((~(i2 - length)) >>> 31);
                for (int i4 = 0; i4 < i3; i4++) {
                    if ((255 & j2) < 128) {
                        int i5 = (i2 << 3) + i4;
                        if (!((Boolean) predicate.invoke(Float.valueOf(fArr[i5]), Float.valueOf(fArr2[i5]))).booleanValue()) {
                            return false;
                        }
                    }
                    j2 >>= 8;
                }
                if (i3 != 8) {
                    return true;
                }
            }
            if (i2 == length) {
                return true;
            }
            i2++;
        }
    }

    public final boolean any() {
        return this._size != 0;
    }

    public final boolean contains(float f2) {
        return findKeyIndex(f2) >= 0;
    }

    public final boolean containsKey(float f2) {
        return findKeyIndex(f2) >= 0;
    }

    /* JADX WARN: Removed duplicated region for block: B:17:0x0041  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final boolean containsValue(float r14) {
        /*
            r13 = this;
            float[] r0 = r13.values
            long[] r13 = r13.metadata
            int r1 = r13.length
            int r1 = r1 + (-2)
            r2 = 0
            if (r1 < 0) goto L46
            r3 = r2
        Lb:
            r4 = r13[r3]
            long r6 = ~r4
            r8 = 7
            long r6 = r6 << r8
            long r6 = r6 & r4
            r8 = -9187201950435737472(0x8080808080808080, double:-2.937446524422997E-306)
            long r6 = r6 & r8
            int r6 = (r6 > r8 ? 1 : (r6 == r8 ? 0 : -1))
            if (r6 == 0) goto L41
            int r6 = r3 - r1
            int r6 = ~r6
            int r6 = r6 >>> 31
            r7 = 8
            int r6 = 8 - r6
            r8 = r2
        L25:
            if (r8 >= r6) goto L3f
            r9 = 255(0xff, double:1.26E-321)
            long r9 = r9 & r4
            r11 = 128(0x80, double:6.32E-322)
            int r9 = (r9 > r11 ? 1 : (r9 == r11 ? 0 : -1))
            if (r9 >= 0) goto L3b
            int r9 = r3 << 3
            int r9 = r9 + r8
            r9 = r0[r9]
            int r9 = (r14 > r9 ? 1 : (r14 == r9 ? 0 : -1))
            if (r9 != 0) goto L3b
            r13 = 1
            return r13
        L3b:
            long r4 = r4 >> r7
            int r8 = r8 + 1
            goto L25
        L3f:
            if (r6 != r7) goto L46
        L41:
            if (r3 == r1) goto L46
            int r3 = r3 + 1
            goto Lb
        L46:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.collection.FloatFloatMap.containsValue(float):boolean");
    }

    public final int count() {
        return getSize();
    }

    /* JADX WARN: Removed duplicated region for block: B:26:0x0063  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public boolean equals(java.lang.Object r18) {
        /*
            r17 = this;
            r0 = r17
            r1 = r18
            r2 = 1
            if (r1 != r0) goto L8
            return r2
        L8:
            boolean r3 = r1 instanceof androidx.collection.FloatFloatMap
            r4 = 0
            if (r3 != 0) goto Le
            return r4
        Le:
            androidx.collection.FloatFloatMap r1 = (androidx.collection.FloatFloatMap) r1
            int r3 = r1.getSize()
            int r5 = r17.getSize()
            if (r3 == r5) goto L1b
            return r4
        L1b:
            float[] r3 = r0.keys
            float[] r5 = r0.values
            long[] r0 = r0.metadata
            int r6 = r0.length
            int r6 = r6 + (-2)
            if (r6 < 0) goto L68
            r7 = r4
        L27:
            r8 = r0[r7]
            long r10 = ~r8
            r12 = 7
            long r10 = r10 << r12
            long r10 = r10 & r8
            r12 = -9187201950435737472(0x8080808080808080, double:-2.937446524422997E-306)
            long r10 = r10 & r12
            int r10 = (r10 > r12 ? 1 : (r10 == r12 ? 0 : -1))
            if (r10 == 0) goto L63
            int r10 = r7 - r6
            int r10 = ~r10
            int r10 = r10 >>> 31
            r11 = 8
            int r10 = 8 - r10
            r12 = r4
        L41:
            if (r12 >= r10) goto L61
            r13 = 255(0xff, double:1.26E-321)
            long r13 = r13 & r8
            r15 = 128(0x80, double:6.32E-322)
            int r13 = (r13 > r15 ? 1 : (r13 == r15 ? 0 : -1))
            if (r13 >= 0) goto L5d
            int r13 = r7 << 3
            int r13 = r13 + r12
            r14 = r3[r13]
            r13 = r5[r13]
            float r14 = r1.get(r14)
            int r13 = (r13 > r14 ? 1 : (r13 == r14 ? 0 : -1))
            if (r13 != 0) goto L5c
            goto L5d
        L5c:
            return r4
        L5d:
            long r8 = r8 >> r11
            int r12 = r12 + 1
            goto L41
        L61:
            if (r10 != r11) goto L68
        L63:
            if (r7 == r6) goto L68
            int r7 = r7 + 1
            goto L27
        L68:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.collection.FloatFloatMap.equals(java.lang.Object):boolean");
    }

    public final int findKeyIndex(float f2) {
        int iHashCode = Float.hashCode(f2) * ScatterMapKt.MurmurHashC1;
        int i2 = iHashCode ^ (iHashCode << 16);
        int i3 = i2 & 127;
        int i4 = this._capacity;
        int i5 = (i2 >>> 7) & i4;
        int i6 = 0;
        while (true) {
            long[] jArr = this.metadata;
            int i7 = i5 >> 3;
            int i8 = (i5 & 7) << 3;
            long j2 = ((jArr[i7 + 1] << (64 - i8)) & ((-i8) >> 63)) | (jArr[i7] >>> i8);
            long j3 = (((long) i3) * ScatterMapKt.BitmaskLsb) ^ j2;
            for (long j4 = (~j3) & (j3 - ScatterMapKt.BitmaskLsb) & (-9187201950435737472L); j4 != 0; j4 &= j4 - 1) {
                int iNumberOfTrailingZeros = ((Long.numberOfTrailingZeros(j4) >> 3) + i5) & i4;
                if (this.keys[iNumberOfTrailingZeros] == f2) {
                    return iNumberOfTrailingZeros;
                }
            }
            if ((j2 & ((~j2) << 6) & (-9187201950435737472L)) != 0) {
                return -1;
            }
            i6 += 8;
            i5 = (i5 + i6) & i4;
        }
    }

    public final void forEach(Function2 block) {
        n.g(block, "block");
        float[] fArr = this.keys;
        float[] fArr2 = this.values;
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
                        block.invoke(Float.valueOf(fArr[i5]), Float.valueOf(fArr2[i5]));
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

    public final void forEachIndexed(Function1 block) {
        n.g(block, "block");
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
                        block.invoke(Integer.valueOf((i2 << 3) + i4));
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

    public final void forEachKey(Function1 block) {
        n.g(block, "block");
        float[] fArr = this.keys;
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
                        block.invoke(Float.valueOf(fArr[(i2 << 3) + i4]));
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

    public final void forEachValue(Function1 block) {
        n.g(block, "block");
        float[] fArr = this.values;
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
                        block.invoke(Float.valueOf(fArr[(i2 << 3) + i4]));
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

    public final float get(float f2) {
        int iFindKeyIndex = findKeyIndex(f2);
        if (iFindKeyIndex >= 0) {
            return this.values[iFindKeyIndex];
        }
        throw new NoSuchElementException("Cannot find value for key " + f2);
    }

    public final int getCapacity() {
        return this._capacity;
    }

    public final float getOrDefault(float f2, float f3) {
        int iFindKeyIndex = findKeyIndex(f2);
        return iFindKeyIndex >= 0 ? this.values[iFindKeyIndex] : f3;
    }

    public final float getOrElse(float f2, Function0 defaultValue) {
        n.g(defaultValue, "defaultValue");
        int iFindKeyIndex = findKeyIndex(f2);
        return iFindKeyIndex < 0 ? ((Number) defaultValue.invoke()).floatValue() : this.values[iFindKeyIndex];
    }

    public final int getSize() {
        return this._size;
    }

    public int hashCode() {
        float[] fArr = this.keys;
        float[] fArr2 = this.values;
        long[] jArr = this.metadata;
        int length = jArr.length - 2;
        int i2 = 0;
        if (length >= 0) {
            int i3 = 0;
            int iHashCode = 0;
            while (true) {
                long j2 = jArr[i3];
                if ((((~j2) << 7) & j2 & (-9187201950435737472L)) != -9187201950435737472L) {
                    int i4 = 8 - ((~(i3 - length)) >>> 31);
                    for (int i5 = 0; i5 < i4; i5++) {
                        if ((255 & j2) < 128) {
                            int i6 = (i3 << 3) + i5;
                            float f2 = fArr[i6];
                            iHashCode += Float.hashCode(fArr2[i6]) ^ Float.hashCode(f2);
                        }
                        j2 >>= 8;
                    }
                    if (i4 != 8) {
                        return iHashCode;
                    }
                }
                if (i3 == length) {
                    i2 = iHashCode;
                    break;
                }
                i3++;
            }
        }
        return i2;
    }

    public final boolean isEmpty() {
        return this._size == 0;
    }

    public final boolean isNotEmpty() {
        return this._size != 0;
    }

    public final String joinToString() {
        return joinToString$default(this, null, null, null, 0, null, 31, null);
    }

    public final boolean none() {
        return this._size == 0;
    }

    /* JADX WARN: Removed duplicated region for block: B:20:0x0070 A[PHI: r8
      0x0070: PHI (r8v2 int) = (r8v1 int), (r8v3 int) binds: [B:10:0x0031, B:19:0x006e] A[DONT_GENERATE, DONT_INLINE]] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public java.lang.String toString() {
        /*
            r18 = this;
            r0 = r18
            boolean r1 = r18.isEmpty()
            if (r1 == 0) goto Lb
            java.lang.String r0 = "{}"
            return r0
        Lb:
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            r2 = 123(0x7b, float:1.72E-43)
            r1.append(r2)
            float[] r2 = r0.keys
            float[] r3 = r0.values
            long[] r4 = r0.metadata
            int r5 = r4.length
            int r5 = r5 + (-2)
            if (r5 < 0) goto L75
            r6 = 0
            r7 = r6
            r8 = r7
        L23:
            r9 = r4[r7]
            long r11 = ~r9
            r13 = 7
            long r11 = r11 << r13
            long r11 = r11 & r9
            r13 = -9187201950435737472(0x8080808080808080, double:-2.937446524422997E-306)
            long r11 = r11 & r13
            int r11 = (r11 > r13 ? 1 : (r11 == r13 ? 0 : -1))
            if (r11 == 0) goto L70
            int r11 = r7 - r5
            int r11 = ~r11
            int r11 = r11 >>> 31
            r12 = 8
            int r11 = 8 - r11
            r13 = r6
        L3d:
            if (r13 >= r11) goto L6e
            r14 = 255(0xff, double:1.26E-321)
            long r14 = r14 & r9
            r16 = 128(0x80, double:6.32E-322)
            int r14 = (r14 > r16 ? 1 : (r14 == r16 ? 0 : -1))
            if (r14 >= 0) goto L6a
            int r14 = r7 << 3
            int r14 = r14 + r13
            r15 = r2[r14]
            r14 = r3[r14]
            r1.append(r15)
            java.lang.String r15 = "="
            r1.append(r15)
            r1.append(r14)
            int r8 = r8 + 1
            int r14 = r0._size
            if (r8 >= r14) goto L6a
            r14 = 44
            r1.append(r14)
            r14 = 32
            r1.append(r14)
        L6a:
            long r9 = r9 >> r12
            int r13 = r13 + 1
            goto L3d
        L6e:
            if (r11 != r12) goto L75
        L70:
            if (r7 == r5) goto L75
            int r7 = r7 + 1
            goto L23
        L75:
            r0 = 125(0x7d, float:1.75E-43)
            r1.append(r0)
            java.lang.String r0 = r1.toString()
            java.lang.String r1 = "s.append('}').toString()"
            kotlin.jvm.internal.n.f(r0, r1)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.collection.FloatFloatMap.toString():java.lang.String");
    }

    private FloatFloatMap() {
        this.metadata = ScatterMapKt.EmptyGroup;
        this.keys = FloatSetKt.getEmptyFloatArray();
        this.values = FloatSetKt.getEmptyFloatArray();
    }

    /* JADX WARN: Removed duplicated region for block: B:17:0x005a  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final boolean any(kotlin.jvm.functions.Function2 r15) {
        /*
            r14 = this;
            java.lang.String r0 = "predicate"
            kotlin.jvm.internal.n.g(r15, r0)
            float[] r0 = r14.keys
            float[] r1 = r14.values
            long[] r14 = r14.metadata
            int r2 = r14.length
            int r2 = r2 + (-2)
            r3 = 0
            if (r2 < 0) goto L5f
            r4 = r3
        L12:
            r5 = r14[r4]
            long r7 = ~r5
            r9 = 7
            long r7 = r7 << r9
            long r7 = r7 & r5
            r9 = -9187201950435737472(0x8080808080808080, double:-2.937446524422997E-306)
            long r7 = r7 & r9
            int r7 = (r7 > r9 ? 1 : (r7 == r9 ? 0 : -1))
            if (r7 == 0) goto L5a
            int r7 = r4 - r2
            int r7 = ~r7
            int r7 = r7 >>> 31
            r8 = 8
            int r7 = 8 - r7
            r9 = r3
        L2c:
            if (r9 >= r7) goto L58
            r10 = 255(0xff, double:1.26E-321)
            long r10 = r10 & r5
            r12 = 128(0x80, double:6.32E-322)
            int r10 = (r10 > r12 ? 1 : (r10 == r12 ? 0 : -1))
            if (r10 >= 0) goto L54
            int r10 = r4 << 3
            int r10 = r10 + r9
            r11 = r0[r10]
            r10 = r1[r10]
            java.lang.Float r11 = java.lang.Float.valueOf(r11)
            java.lang.Float r10 = java.lang.Float.valueOf(r10)
            java.lang.Object r10 = r15.invoke(r11, r10)
            java.lang.Boolean r10 = (java.lang.Boolean) r10
            boolean r10 = r10.booleanValue()
            if (r10 == 0) goto L54
            r14 = 1
            return r14
        L54:
            long r5 = r5 >> r8
            int r9 = r9 + 1
            goto L2c
        L58:
            if (r7 != r8) goto L5f
        L5a:
            if (r4 == r2) goto L5f
            int r4 = r4 + 1
            goto L12
        L5f:
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.collection.FloatFloatMap.any(kotlin.jvm.functions.Function2):boolean");
    }

    public final int count(Function2 predicate) {
        n.g(predicate, "predicate");
        float[] fArr = this.keys;
        float[] fArr2 = this.values;
        long[] jArr = this.metadata;
        int length = jArr.length - 2;
        int i2 = 0;
        if (length >= 0) {
            int i3 = 0;
            int i4 = 0;
            while (true) {
                long j2 = jArr[i3];
                if ((((~j2) << 7) & j2 & (-9187201950435737472L)) != -9187201950435737472L) {
                    int i5 = 8 - ((~(i3 - length)) >>> 31);
                    for (int i6 = 0; i6 < i5; i6++) {
                        if ((255 & j2) < 128) {
                            int i7 = (i3 << 3) + i6;
                            if (((Boolean) predicate.invoke(Float.valueOf(fArr[i7]), Float.valueOf(fArr2[i7]))).booleanValue()) {
                                i4++;
                            }
                        }
                        j2 >>= 8;
                    }
                    if (i5 != 8) {
                        return i4;
                    }
                }
                if (i3 == length) {
                    i2 = i4;
                    break;
                }
                i3++;
            }
        }
        return i2;
    }

    public final String joinToString(CharSequence separator) {
        n.g(separator, "separator");
        return joinToString$default(this, separator, null, null, 0, null, 30, null);
    }

    public final String joinToString(CharSequence separator, CharSequence prefix) {
        n.g(separator, "separator");
        n.g(prefix, "prefix");
        return joinToString$default(this, separator, prefix, null, 0, null, 28, null);
    }

    public final String joinToString(CharSequence separator, CharSequence prefix, CharSequence postfix) {
        n.g(separator, "separator");
        n.g(prefix, "prefix");
        n.g(postfix, "postfix");
        return joinToString$default(this, separator, prefix, postfix, 0, null, 24, null);
    }

    public static /* synthetic */ String joinToString$default(FloatFloatMap floatFloatMap, CharSequence charSequence, CharSequence charSequence2, CharSequence charSequence3, int i2, CharSequence charSequence4, Function2 transform, int i3, Object obj) {
        long[] jArr;
        long[] jArr2;
        int i4;
        if (obj == null) {
            CharSequence separator = (i3 & 1) != 0 ? ", " : charSequence;
            CharSequence prefix = (i3 & 2) != 0 ? "" : charSequence2;
            CharSequence postfix = (i3 & 4) == 0 ? charSequence3 : "";
            int i5 = (i3 & 8) != 0 ? -1 : i2;
            CharSequence truncated = (i3 & 16) != 0 ? "..." : charSequence4;
            n.g(separator, "separator");
            n.g(prefix, "prefix");
            n.g(postfix, "postfix");
            n.g(truncated, "truncated");
            n.g(transform, "transform");
            StringBuilder sb = new StringBuilder();
            sb.append(prefix);
            float[] fArr = floatFloatMap.keys;
            float[] fArr2 = floatFloatMap.values;
            long[] jArr3 = floatFloatMap.metadata;
            int length = jArr3.length - 2;
            if (length >= 0) {
                int i6 = 0;
                int i7 = 0;
                loop0: while (true) {
                    long j2 = jArr3[i6];
                    int i8 = i6;
                    if ((((~j2) << 7) & j2 & (-9187201950435737472L)) != -9187201950435737472L) {
                        int i9 = 8;
                        int i10 = 8 - ((~(i8 - length)) >>> 31);
                        int i11 = 0;
                        while (i11 < i10) {
                            if ((j2 & 255) < 128) {
                                int i12 = (i8 << 3) + i11;
                                float f2 = fArr[i12];
                                float f3 = fArr2[i12];
                                if (i7 == i5) {
                                    sb.append(truncated);
                                    break loop0;
                                }
                                if (i7 != 0) {
                                    sb.append(separator);
                                }
                                Float fValueOf = Float.valueOf(f2);
                                jArr2 = jArr3;
                                sb.append((CharSequence) transform.invoke(fValueOf, Float.valueOf(f3)));
                                i7++;
                                i4 = 8;
                            } else {
                                jArr2 = jArr3;
                                i4 = i9;
                            }
                            j2 >>= i4;
                            i11++;
                            i9 = i4;
                            jArr3 = jArr2;
                        }
                        jArr = jArr3;
                        if (i10 != i9) {
                            break;
                        }
                    } else {
                        jArr = jArr3;
                    }
                    if (i8 == length) {
                        break;
                    }
                    i6 = i8 + 1;
                    jArr3 = jArr;
                }
                sb.append(postfix);
            } else {
                sb.append(postfix);
            }
            String string = sb.toString();
            n.f(string, "StringBuilder().apply(builderAction).toString()");
            return string;
        }
        throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: joinToString");
    }

    public final String joinToString(CharSequence separator, CharSequence prefix, CharSequence postfix, int i2) {
        n.g(separator, "separator");
        n.g(prefix, "prefix");
        n.g(postfix, "postfix");
        return joinToString$default(this, separator, prefix, postfix, i2, null, 16, null);
    }

    public final String joinToString(CharSequence separator, CharSequence prefix, CharSequence postfix, int i2, CharSequence truncated) {
        long[] jArr;
        long[] jArr2;
        int i3;
        n.g(separator, "separator");
        n.g(prefix, "prefix");
        n.g(postfix, "postfix");
        n.g(truncated, "truncated");
        StringBuilder sb = new StringBuilder();
        sb.append(prefix);
        float[] fArr = this.keys;
        float[] fArr2 = this.values;
        long[] jArr3 = this.metadata;
        int length = jArr3.length - 2;
        if (length >= 0) {
            int i4 = 0;
            int i5 = 0;
            loop0: while (true) {
                long j2 = jArr3[i4];
                if ((((~j2) << 7) & j2 & (-9187201950435737472L)) != -9187201950435737472L) {
                    int i6 = 8;
                    int i7 = 8 - ((~(i4 - length)) >>> 31);
                    int i8 = 0;
                    while (i8 < i7) {
                        if ((j2 & 255) < 128) {
                            int i9 = (i4 << 3) + i8;
                            float f2 = fArr[i9];
                            float f3 = fArr2[i9];
                            jArr2 = jArr3;
                            if (i5 == i2) {
                                sb.append(truncated);
                                break loop0;
                            }
                            if (i5 != 0) {
                                sb.append(separator);
                            }
                            sb.append(f2);
                            sb.append('=');
                            sb.append(f3);
                            i5++;
                            i3 = 8;
                        } else {
                            jArr2 = jArr3;
                            i3 = i6;
                        }
                        j2 >>= i3;
                        i8++;
                        i6 = i3;
                        jArr3 = jArr2;
                    }
                    jArr = jArr3;
                    if (i7 != i6) {
                        break;
                    }
                } else {
                    jArr = jArr3;
                }
                if (i4 == length) {
                    break;
                }
                i4++;
                jArr3 = jArr;
            }
            sb.append(postfix);
        } else {
            sb.append(postfix);
        }
        String string = sb.toString();
        n.f(string, "StringBuilder().apply(builderAction).toString()");
        return string;
    }

    public final String joinToString(CharSequence charSequence, CharSequence prefix, CharSequence postfix, int i2, CharSequence truncated, Function2 transform) {
        long[] jArr;
        long[] jArr2;
        CharSequence separator = charSequence;
        n.g(separator, "separator");
        n.g(prefix, "prefix");
        n.g(postfix, "postfix");
        n.g(truncated, "truncated");
        n.g(transform, "transform");
        StringBuilder sb = new StringBuilder();
        sb.append(prefix);
        float[] fArr = this.keys;
        float[] fArr2 = this.values;
        long[] jArr3 = this.metadata;
        int length = jArr3.length - 2;
        if (length >= 0) {
            int i3 = 0;
            int i4 = 0;
            loop0: while (true) {
                long j2 = jArr3[i3];
                if ((((~j2) << 7) & j2 & (-9187201950435737472L)) != -9187201950435737472L) {
                    int i5 = 8;
                    int i6 = 8 - ((~(i3 - length)) >>> 31);
                    int i7 = 0;
                    while (i7 < i6) {
                        if ((j2 & 255) < 128) {
                            int i8 = (i3 << 3) + i7;
                            float f2 = fArr[i8];
                            float f3 = fArr2[i8];
                            if (i4 == i2) {
                                sb.append(truncated);
                                break loop0;
                            }
                            if (i4 != 0) {
                                sb.append(separator);
                            }
                            jArr2 = jArr3;
                            sb.append((CharSequence) transform.invoke(Float.valueOf(f2), Float.valueOf(f3)));
                            i4++;
                        } else {
                            jArr2 = jArr3;
                        }
                        j2 >>= 8;
                        i7++;
                        separator = charSequence;
                        i5 = 8;
                        jArr3 = jArr2;
                    }
                    jArr = jArr3;
                    if (i6 != i5) {
                        break;
                    }
                } else {
                    jArr = jArr3;
                }
                if (i3 == length) {
                    break;
                }
                i3++;
                separator = charSequence;
                jArr3 = jArr;
            }
            sb.append(postfix);
        } else {
            sb.append(postfix);
        }
        String string = sb.toString();
        n.f(string, "StringBuilder().apply(builderAction).toString()");
        return string;
    }

    public final String joinToString(CharSequence separator, CharSequence prefix, CharSequence postfix, int i2, Function2 transform) {
        long[] jArr;
        long[] jArr2;
        int i3;
        n.g(separator, "separator");
        n.g(prefix, "prefix");
        n.g(postfix, "postfix");
        n.g(transform, "transform");
        StringBuilder sb = new StringBuilder();
        sb.append(prefix);
        float[] fArr = this.keys;
        float[] fArr2 = this.values;
        long[] jArr3 = this.metadata;
        int length = jArr3.length - 2;
        if (length >= 0) {
            int i4 = 0;
            int i5 = 0;
            loop0: while (true) {
                long j2 = jArr3[i4];
                if ((((~j2) << 7) & j2 & (-9187201950435737472L)) != -9187201950435737472L) {
                    int i6 = 8;
                    int i7 = 8 - ((~(i4 - length)) >>> 31);
                    int i8 = 0;
                    while (i8 < i7) {
                        if ((j2 & 255) < 128) {
                            int i9 = (i4 << 3) + i8;
                            float f2 = fArr[i9];
                            float f3 = fArr2[i9];
                            if (i5 == i2) {
                                sb.append((CharSequence) "...");
                                break loop0;
                            }
                            if (i5 != 0) {
                                sb.append(separator);
                            }
                            Float fValueOf = Float.valueOf(f2);
                            jArr2 = jArr3;
                            sb.append((CharSequence) transform.invoke(fValueOf, Float.valueOf(f3)));
                            i5++;
                            i3 = 8;
                        } else {
                            jArr2 = jArr3;
                            i3 = i6;
                        }
                        j2 >>= i3;
                        i8++;
                        i6 = i3;
                        jArr3 = jArr2;
                    }
                    jArr = jArr3;
                    if (i7 != i6) {
                        break;
                    }
                } else {
                    jArr = jArr3;
                }
                if (i4 == length) {
                    break;
                }
                i4++;
                jArr3 = jArr;
            }
            sb.append(postfix);
        } else {
            sb.append(postfix);
        }
        String string = sb.toString();
        n.f(string, "StringBuilder().apply(builderAction).toString()");
        return string;
    }

    /* JADX WARN: Removed duplicated region for block: B:21:0x008e A[PHI: r10
      0x008e: PHI (r10v2 int) = (r10v1 int), (r10v3 int) binds: [B:6:0x0041, B:20:0x008c] A[DONT_GENERATE, DONT_INLINE]] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.String joinToString(java.lang.CharSequence r21, java.lang.CharSequence r22, java.lang.CharSequence r23, kotlin.jvm.functions.Function2 r24) {
        /*
            r20 = this;
            r0 = r20
            r1 = r21
            r2 = r22
            r3 = r23
            r4 = r24
            java.lang.String r5 = "separator"
            kotlin.jvm.internal.n.g(r1, r5)
            java.lang.String r5 = "prefix"
            kotlin.jvm.internal.n.g(r2, r5)
            java.lang.String r5 = "postfix"
            kotlin.jvm.internal.n.g(r3, r5)
            java.lang.String r5 = "transform"
            kotlin.jvm.internal.n.g(r4, r5)
            java.lang.StringBuilder r5 = new java.lang.StringBuilder
            r5.<init>()
            r5.append(r2)
            float[] r2 = r0.keys
            float[] r6 = r0.values
            long[] r0 = r0.metadata
            int r7 = r0.length
            int r7 = r7 + (-2)
            if (r7 < 0) goto L93
            r9 = 0
            r10 = 0
        L33:
            r11 = r0[r9]
            long r13 = ~r11
            r15 = 7
            long r13 = r13 << r15
            long r13 = r13 & r11
            r15 = -9187201950435737472(0x8080808080808080, double:-2.937446524422997E-306)
            long r13 = r13 & r15
            int r13 = (r13 > r15 ? 1 : (r13 == r15 ? 0 : -1))
            if (r13 == 0) goto L8e
            int r13 = r9 - r7
            int r13 = ~r13
            int r13 = r13 >>> 31
            r14 = 8
            int r13 = 8 - r13
            r15 = 0
        L4d:
            if (r15 >= r13) goto L8b
            r16 = 255(0xff, double:1.26E-321)
            long r16 = r11 & r16
            r18 = 128(0x80, double:6.32E-322)
            int r16 = (r16 > r18 ? 1 : (r16 == r18 ? 0 : -1))
            if (r16 >= 0) goto L85
            int r16 = r9 << 3
            int r16 = r16 + r15
            r17 = r2[r16]
            r16 = r6[r16]
            r8 = -1
            if (r10 != r8) goto L6a
            java.lang.String r0 = "..."
            r5.append(r0)
            goto L96
        L6a:
            if (r10 == 0) goto L6f
            r5.append(r1)
        L6f:
            java.lang.Float r8 = java.lang.Float.valueOf(r17)
            java.lang.Float r14 = java.lang.Float.valueOf(r16)
            java.lang.Object r8 = r4.invoke(r8, r14)
            java.lang.CharSequence r8 = (java.lang.CharSequence) r8
            r5.append(r8)
            int r10 = r10 + 1
            r8 = 8
            goto L86
        L85:
            r8 = r14
        L86:
            long r11 = r11 >> r8
            int r15 = r15 + 1
            r14 = r8
            goto L4d
        L8b:
            r8 = r14
            if (r13 != r8) goto L93
        L8e:
            if (r9 == r7) goto L93
            int r9 = r9 + 1
            goto L33
        L93:
            r5.append(r3)
        L96:
            java.lang.String r0 = r5.toString()
            java.lang.String r1 = "StringBuilder().apply(builderAction).toString()"
            kotlin.jvm.internal.n.f(r0, r1)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.collection.FloatFloatMap.joinToString(java.lang.CharSequence, java.lang.CharSequence, java.lang.CharSequence, kotlin.jvm.functions.Function2):java.lang.String");
    }

    /* JADX WARN: Removed duplicated region for block: B:19:0x007f A[PHI: r9
      0x007f: PHI (r9v2 int) = (r9v1 int), (r9v3 int) binds: [B:6:0x003a, B:18:0x007d] A[DONT_GENERATE, DONT_INLINE]] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.String joinToString(java.lang.CharSequence r20, java.lang.CharSequence r21, kotlin.jvm.functions.Function2 r22) {
        /*
            r19 = this;
            r0 = r19
            r1 = r20
            r2 = r21
            r3 = r22
            java.lang.String r4 = "separator"
            kotlin.jvm.internal.n.g(r1, r4)
            java.lang.String r4 = "prefix"
            kotlin.jvm.internal.n.g(r2, r4)
            java.lang.String r4 = "transform"
            kotlin.jvm.internal.n.g(r3, r4)
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            r4.<init>()
            r4.append(r2)
            float[] r2 = r0.keys
            float[] r5 = r0.values
            long[] r0 = r0.metadata
            int r6 = r0.length
            int r6 = r6 + (-2)
            if (r6 < 0) goto L84
            r8 = 0
            r9 = 0
        L2c:
            r10 = r0[r8]
            long r12 = ~r10
            r14 = 7
            long r12 = r12 << r14
            long r12 = r12 & r10
            r14 = -9187201950435737472(0x8080808080808080, double:-2.937446524422997E-306)
            long r12 = r12 & r14
            int r12 = (r12 > r14 ? 1 : (r12 == r14 ? 0 : -1))
            if (r12 == 0) goto L7f
            int r12 = r8 - r6
            int r12 = ~r12
            int r12 = r12 >>> 31
            r13 = 8
            int r12 = 8 - r12
            r14 = 0
        L46:
            if (r14 >= r12) goto L7d
            r15 = 255(0xff, double:1.26E-321)
            long r15 = r15 & r10
            r17 = 128(0x80, double:6.32E-322)
            int r15 = (r15 > r17 ? 1 : (r15 == r17 ? 0 : -1))
            if (r15 >= 0) goto L79
            int r15 = r8 << 3
            int r15 = r15 + r14
            r16 = r2[r15]
            r15 = r5[r15]
            r7 = -1
            if (r9 != r7) goto L61
            java.lang.String r0 = "..."
            r4.append(r0)
            goto L89
        L61:
            if (r9 == 0) goto L66
            r4.append(r1)
        L66:
            java.lang.Float r7 = java.lang.Float.valueOf(r16)
            java.lang.Float r15 = java.lang.Float.valueOf(r15)
            java.lang.Object r7 = r3.invoke(r7, r15)
            java.lang.CharSequence r7 = (java.lang.CharSequence) r7
            r4.append(r7)
            int r9 = r9 + 1
        L79:
            long r10 = r10 >> r13
            int r14 = r14 + 1
            goto L46
        L7d:
            if (r12 != r13) goto L84
        L7f:
            if (r8 == r6) goto L84
            int r8 = r8 + 1
            goto L2c
        L84:
            java.lang.String r0 = ""
            r4.append(r0)
        L89:
            java.lang.String r0 = r4.toString()
            java.lang.String r1 = "StringBuilder().apply(builderAction).toString()"
            kotlin.jvm.internal.n.f(r0, r1)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.collection.FloatFloatMap.joinToString(java.lang.CharSequence, java.lang.CharSequence, kotlin.jvm.functions.Function2):java.lang.String");
    }

    /* JADX WARN: Removed duplicated region for block: B:21:0x0082 A[PHI: r10
      0x0082: PHI (r10v2 int) = (r10v1 int), (r10v3 int) binds: [B:6:0x0035, B:20:0x0080] A[DONT_GENERATE, DONT_INLINE]] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.String joinToString(java.lang.CharSequence r21, kotlin.jvm.functions.Function2 r22) {
        /*
            r20 = this;
            r0 = r20
            r1 = r21
            r2 = r22
            java.lang.String r3 = "separator"
            kotlin.jvm.internal.n.g(r1, r3)
            java.lang.String r3 = "transform"
            kotlin.jvm.internal.n.g(r2, r3)
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            java.lang.String r4 = ""
            r3.append(r4)
            float[] r5 = r0.keys
            float[] r6 = r0.values
            long[] r0 = r0.metadata
            int r7 = r0.length
            int r7 = r7 + (-2)
            if (r7 < 0) goto L87
            r9 = 0
            r10 = 0
        L27:
            r11 = r0[r9]
            long r13 = ~r11
            r15 = 7
            long r13 = r13 << r15
            long r13 = r13 & r11
            r15 = -9187201950435737472(0x8080808080808080, double:-2.937446524422997E-306)
            long r13 = r13 & r15
            int r13 = (r13 > r15 ? 1 : (r13 == r15 ? 0 : -1))
            if (r13 == 0) goto L82
            int r13 = r9 - r7
            int r13 = ~r13
            int r13 = r13 >>> 31
            r14 = 8
            int r13 = 8 - r13
            r15 = 0
        L41:
            if (r15 >= r13) goto L7f
            r16 = 255(0xff, double:1.26E-321)
            long r16 = r11 & r16
            r18 = 128(0x80, double:6.32E-322)
            int r16 = (r16 > r18 ? 1 : (r16 == r18 ? 0 : -1))
            if (r16 >= 0) goto L79
            int r16 = r9 << 3
            int r16 = r16 + r15
            r17 = r5[r16]
            r16 = r6[r16]
            r8 = -1
            if (r10 != r8) goto L5e
            java.lang.String r0 = "..."
            r3.append(r0)
            goto L8a
        L5e:
            if (r10 == 0) goto L63
            r3.append(r1)
        L63:
            java.lang.Float r8 = java.lang.Float.valueOf(r17)
            java.lang.Float r14 = java.lang.Float.valueOf(r16)
            java.lang.Object r8 = r2.invoke(r8, r14)
            java.lang.CharSequence r8 = (java.lang.CharSequence) r8
            r3.append(r8)
            int r10 = r10 + 1
            r8 = 8
            goto L7a
        L79:
            r8 = r14
        L7a:
            long r11 = r11 >> r8
            int r15 = r15 + 1
            r14 = r8
            goto L41
        L7f:
            r8 = r14
            if (r13 != r8) goto L87
        L82:
            if (r9 == r7) goto L87
            int r9 = r9 + 1
            goto L27
        L87:
            r3.append(r4)
        L8a:
            java.lang.String r0 = r3.toString()
            java.lang.String r1 = "StringBuilder().apply(builderAction).toString()"
            kotlin.jvm.internal.n.f(r0, r1)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.collection.FloatFloatMap.joinToString(java.lang.CharSequence, kotlin.jvm.functions.Function2):java.lang.String");
    }

    /* JADX WARN: Removed duplicated region for block: B:19:0x0075 A[PHI: r9
      0x0075: PHI (r9v2 int) = (r9v1 int), (r9v3 int) binds: [B:6:0x002e, B:18:0x0073] A[DONT_GENERATE, DONT_INLINE]] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.String joinToString(kotlin.jvm.functions.Function2 r20) {
        /*
            r19 = this;
            r0 = r19
            r1 = r20
            java.lang.String r2 = "transform"
            kotlin.jvm.internal.n.g(r1, r2)
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            java.lang.String r3 = ""
            r2.append(r3)
            float[] r4 = r0.keys
            float[] r5 = r0.values
            long[] r0 = r0.metadata
            int r6 = r0.length
            int r6 = r6 + (-2)
            if (r6 < 0) goto L7a
            r8 = 0
            r9 = 0
        L20:
            r10 = r0[r8]
            long r12 = ~r10
            r14 = 7
            long r12 = r12 << r14
            long r12 = r12 & r10
            r14 = -9187201950435737472(0x8080808080808080, double:-2.937446524422997E-306)
            long r12 = r12 & r14
            int r12 = (r12 > r14 ? 1 : (r12 == r14 ? 0 : -1))
            if (r12 == 0) goto L75
            int r12 = r8 - r6
            int r12 = ~r12
            int r12 = r12 >>> 31
            r13 = 8
            int r12 = 8 - r12
            r14 = 0
        L3a:
            if (r14 >= r12) goto L73
            r15 = 255(0xff, double:1.26E-321)
            long r15 = r15 & r10
            r17 = 128(0x80, double:6.32E-322)
            int r15 = (r15 > r17 ? 1 : (r15 == r17 ? 0 : -1))
            if (r15 >= 0) goto L6f
            int r15 = r8 << 3
            int r15 = r15 + r14
            r16 = r4[r15]
            r15 = r5[r15]
            r7 = -1
            if (r9 != r7) goto L55
            java.lang.String r0 = "..."
            r2.append(r0)
            goto L7d
        L55:
            if (r9 == 0) goto L5c
            java.lang.String r7 = ", "
            r2.append(r7)
        L5c:
            java.lang.Float r7 = java.lang.Float.valueOf(r16)
            java.lang.Float r15 = java.lang.Float.valueOf(r15)
            java.lang.Object r7 = r1.invoke(r7, r15)
            java.lang.CharSequence r7 = (java.lang.CharSequence) r7
            r2.append(r7)
            int r9 = r9 + 1
        L6f:
            long r10 = r10 >> r13
            int r14 = r14 + 1
            goto L3a
        L73:
            if (r12 != r13) goto L7a
        L75:
            if (r8 == r6) goto L7a
            int r8 = r8 + 1
            goto L20
        L7a:
            r2.append(r3)
        L7d:
            java.lang.String r0 = r2.toString()
            java.lang.String r1 = "StringBuilder().apply(builderAction).toString()"
            kotlin.jvm.internal.n.f(r0, r1)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.collection.FloatFloatMap.joinToString(kotlin.jvm.functions.Function2):java.lang.String");
    }
}
