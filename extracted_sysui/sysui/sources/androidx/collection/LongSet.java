package androidx.collection;

import androidx.annotation.IntRange;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.n;

/* JADX INFO: loaded from: classes.dex */
public abstract class LongSet {
    public int _capacity;
    public int _size;
    public long[] elements;
    public long[] metadata;

    public /* synthetic */ LongSet(DefaultConstructorMarker defaultConstructorMarker) {
        this();
    }

    public static /* synthetic */ void getElements$annotations() {
    }

    public static /* synthetic */ void getMetadata$annotations() {
    }

    public static /* synthetic */ String joinToString$default(LongSet longSet, CharSequence charSequence, CharSequence charSequence2, CharSequence charSequence3, int i2, CharSequence charSequence4, int i3, Object obj) {
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
        return longSet.joinToString(charSequence, charSequence5, charSequence6, i4, charSequence4);
    }

    public final boolean all(Function1 predicate) {
        n.g(predicate, "predicate");
        long[] jArr = this.elements;
        long[] jArr2 = this.metadata;
        int length = jArr2.length - 2;
        if (length < 0) {
            return true;
        }
        int i2 = 0;
        while (true) {
            long j2 = jArr2[i2];
            if ((((~j2) << 7) & j2 & (-9187201950435737472L)) != -9187201950435737472L) {
                int i3 = 8 - ((~(i2 - length)) >>> 31);
                for (int i4 = 0; i4 < i3; i4++) {
                    if ((255 & j2) < 128 && !((Boolean) predicate.invoke(Long.valueOf(jArr[(i2 << 3) + i4]))).booleanValue()) {
                        return false;
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

    /* JADX WARN: Code restructure failed: missing block: B:11:0x0065, code lost:
    
        if (((r6 & ((~r6) << 6)) & (-9187201950435737472L)) == 0) goto L16;
     */
    /* JADX WARN: Code restructure failed: missing block: B:12:0x0067, code lost:
    
        r10 = -1;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final boolean contains(long r17) {
        /*
            r16 = this;
            r0 = r16
            int r1 = java.lang.Long.hashCode(r17)
            r2 = -862048943(0xffffffffcc9e2d51, float:-8.2930312E7)
            int r1 = r1 * r2
            int r2 = r1 << 16
            r1 = r1 ^ r2
            r2 = r1 & 127(0x7f, float:1.78E-43)
            int r3 = r0._capacity
            int r1 = r1 >>> 7
            r1 = r1 & r3
            r4 = 0
            r5 = r4
        L16:
            long[] r6 = r0.metadata
            int r7 = r1 >> 3
            r8 = r1 & 7
            int r8 = r8 << 3
            r9 = r6[r7]
            long r9 = r9 >>> r8
            r11 = 1
            int r7 = r7 + r11
            r6 = r6[r7]
            int r12 = 64 - r8
            long r6 = r6 << r12
            long r12 = (long) r8
            long r12 = -r12
            r8 = 63
            long r12 = r12 >> r8
            long r6 = r6 & r12
            long r6 = r6 | r9
            long r8 = (long) r2
            r12 = 72340172838076673(0x101010101010101, double:7.748604185489348E-304)
            long r8 = r8 * r12
            long r8 = r8 ^ r6
            long r12 = r8 - r12
            long r8 = ~r8
            long r8 = r8 & r12
            r12 = -9187201950435737472(0x8080808080808080, double:-2.937446524422997E-306)
            long r8 = r8 & r12
        L41:
            r14 = 0
            int r10 = (r8 > r14 ? 1 : (r8 == r14 ? 0 : -1))
            if (r10 == 0) goto L5e
            int r10 = java.lang.Long.numberOfTrailingZeros(r8)
            int r10 = r10 >> 3
            int r10 = r10 + r1
            r10 = r10 & r3
            long[] r14 = r0.elements
            r14 = r14[r10]
            int r14 = (r14 > r17 ? 1 : (r14 == r17 ? 0 : -1))
            if (r14 != 0) goto L58
            goto L68
        L58:
            r14 = 1
            long r14 = r8 - r14
            long r8 = r8 & r14
            goto L41
        L5e:
            long r8 = ~r6
            r10 = 6
            long r8 = r8 << r10
            long r6 = r6 & r8
            long r6 = r6 & r12
            int r6 = (r6 > r14 ? 1 : (r6 == r14 ? 0 : -1))
            if (r6 == 0) goto L6c
            r10 = -1
        L68:
            if (r10 < 0) goto L6b
            r4 = r11
        L6b:
            return r4
        L6c:
            int r5 = r5 + 8
            int r1 = r1 + r5
            r1 = r1 & r3
            goto L16
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.collection.LongSet.contains(long):boolean");
    }

    @IntRange(from = 0)
    public final int count() {
        return this._size;
    }

    /* JADX WARN: Removed duplicated region for block: B:25:0x0054  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public boolean equals(java.lang.Object r15) {
        /*
            r14 = this;
            r0 = 1
            if (r15 != r14) goto L4
            return r0
        L4:
            boolean r1 = r15 instanceof androidx.collection.LongSet
            r2 = 0
            if (r1 != 0) goto La
            return r2
        La:
            androidx.collection.LongSet r15 = (androidx.collection.LongSet) r15
            int r1 = r15._size
            int r3 = r14._size
            if (r1 == r3) goto L13
            return r2
        L13:
            long[] r1 = r14.elements
            long[] r14 = r14.metadata
            int r3 = r14.length
            int r3 = r3 + (-2)
            if (r3 < 0) goto L59
            r4 = r2
        L1d:
            r5 = r14[r4]
            long r7 = ~r5
            r9 = 7
            long r7 = r7 << r9
            long r7 = r7 & r5
            r9 = -9187201950435737472(0x8080808080808080, double:-2.937446524422997E-306)
            long r7 = r7 & r9
            int r7 = (r7 > r9 ? 1 : (r7 == r9 ? 0 : -1))
            if (r7 == 0) goto L54
            int r7 = r4 - r3
            int r7 = ~r7
            int r7 = r7 >>> 31
            r8 = 8
            int r7 = 8 - r7
            r9 = r2
        L37:
            if (r9 >= r7) goto L52
            r10 = 255(0xff, double:1.26E-321)
            long r10 = r10 & r5
            r12 = 128(0x80, double:6.32E-322)
            int r10 = (r10 > r12 ? 1 : (r10 == r12 ? 0 : -1))
            if (r10 >= 0) goto L4e
            int r10 = r4 << 3
            int r10 = r10 + r9
            r10 = r1[r10]
            boolean r10 = r15.contains(r10)
            if (r10 != 0) goto L4e
            return r2
        L4e:
            long r5 = r5 >> r8
            int r9 = r9 + 1
            goto L37
        L52:
            if (r7 != r8) goto L59
        L54:
            if (r4 == r3) goto L59
            int r4 = r4 + 1
            goto L1d
        L59:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.collection.LongSet.equals(java.lang.Object):boolean");
    }

    public final int findElementIndex$collection(long j2) {
        int iHashCode = Long.hashCode(j2) * ScatterMapKt.MurmurHashC1;
        int i2 = iHashCode ^ (iHashCode << 16);
        int i3 = i2 & 127;
        int i4 = this._capacity;
        int i5 = (i2 >>> 7) & i4;
        int i6 = 0;
        while (true) {
            long[] jArr = this.metadata;
            int i7 = i5 >> 3;
            int i8 = (i5 & 7) << 3;
            long j3 = ((jArr[i7 + 1] << (64 - i8)) & ((-i8) >> 63)) | (jArr[i7] >>> i8);
            long j4 = (((long) i3) * ScatterMapKt.BitmaskLsb) ^ j3;
            for (long j5 = (~j4) & (j4 - ScatterMapKt.BitmaskLsb) & (-9187201950435737472L); j5 != 0; j5 &= j5 - 1) {
                int iNumberOfTrailingZeros = ((Long.numberOfTrailingZeros(j5) >> 3) + i5) & i4;
                if (this.elements[iNumberOfTrailingZeros] == j2) {
                    return iNumberOfTrailingZeros;
                }
            }
            if ((j3 & ((~j3) << 6) & (-9187201950435737472L)) != 0) {
                return -1;
            }
            i6 += 8;
            i5 = (i5 + i6) & i4;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:15:0x003c  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final long first() {
        /*
            r13 = this;
            long[] r0 = r13.elements
            long[] r13 = r13.metadata
            int r1 = r13.length
            int r1 = r1 + (-2)
            if (r1 < 0) goto L41
            r2 = 0
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
            if (r6 == 0) goto L3c
            int r6 = r3 - r1
            int r6 = ~r6
            int r6 = r6 >>> 31
            r7 = 8
            int r6 = 8 - r6
            r8 = r2
        L25:
            if (r8 >= r6) goto L3a
            r9 = 255(0xff, double:1.26E-321)
            long r9 = r9 & r4
            r11 = 128(0x80, double:6.32E-322)
            int r9 = (r9 > r11 ? 1 : (r9 == r11 ? 0 : -1))
            if (r9 >= 0) goto L36
            int r13 = r3 << 3
            int r13 = r13 + r8
            r0 = r0[r13]
            return r0
        L36:
            long r4 = r4 >> r7
            int r8 = r8 + 1
            goto L25
        L3a:
            if (r6 != r7) goto L41
        L3c:
            if (r3 == r1) goto L41
            int r3 = r3 + 1
            goto Lb
        L41:
            java.util.NoSuchElementException r13 = new java.util.NoSuchElementException
            java.lang.String r0 = "The LongSet is empty"
            r13.<init>(r0)
            throw r13
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.collection.LongSet.first():long");
    }

    public final void forEach(Function1 block) {
        n.g(block, "block");
        long[] jArr = this.elements;
        long[] jArr2 = this.metadata;
        int length = jArr2.length - 2;
        if (length < 0) {
            return;
        }
        int i2 = 0;
        while (true) {
            long j2 = jArr2[i2];
            if ((((~j2) << 7) & j2 & (-9187201950435737472L)) != -9187201950435737472L) {
                int i3 = 8 - ((~(i2 - length)) >>> 31);
                for (int i4 = 0; i4 < i3; i4++) {
                    if ((255 & j2) < 128) {
                        block.invoke(Long.valueOf(jArr[(i2 << 3) + i4]));
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

    public final void forEachIndex(Function1 block) {
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

    @IntRange(from = 0)
    public final int getCapacity() {
        return this._capacity;
    }

    @IntRange(from = 0)
    public final int getSize() {
        return this._size;
    }

    public int hashCode() {
        long[] jArr = this.elements;
        long[] jArr2 = this.metadata;
        int length = jArr2.length - 2;
        int i2 = 0;
        if (length >= 0) {
            int i3 = 0;
            int iHashCode = 0;
            while (true) {
                long j2 = jArr2[i3];
                if ((((~j2) << 7) & j2 & (-9187201950435737472L)) != -9187201950435737472L) {
                    int i4 = 8 - ((~(i3 - length)) >>> 31);
                    for (int i5 = 0; i5 < i4; i5++) {
                        if ((255 & j2) < 128) {
                            iHashCode += Long.hashCode(jArr[(i3 << 3) + i5]);
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

    public String toString() {
        return joinToString$default(this, null, "[", "]", 0, null, 25, null);
    }

    private LongSet() {
        this.metadata = ScatterMapKt.EmptyGroup;
        this.elements = LongSetKt.getEmptyLongArray();
    }

    /* JADX WARN: Removed duplicated region for block: B:17:0x0052  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final boolean any(kotlin.jvm.functions.Function1 r14) {
        /*
            r13 = this;
            java.lang.String r0 = "predicate"
            kotlin.jvm.internal.n.g(r14, r0)
            long[] r0 = r13.elements
            long[] r13 = r13.metadata
            int r1 = r13.length
            int r1 = r1 + (-2)
            r2 = 0
            if (r1 < 0) goto L57
            r3 = r2
        L10:
            r4 = r13[r3]
            long r6 = ~r4
            r8 = 7
            long r6 = r6 << r8
            long r6 = r6 & r4
            r8 = -9187201950435737472(0x8080808080808080, double:-2.937446524422997E-306)
            long r6 = r6 & r8
            int r6 = (r6 > r8 ? 1 : (r6 == r8 ? 0 : -1))
            if (r6 == 0) goto L52
            int r6 = r3 - r1
            int r6 = ~r6
            int r6 = r6 >>> 31
            r7 = 8
            int r6 = 8 - r6
            r8 = r2
        L2a:
            if (r8 >= r6) goto L50
            r9 = 255(0xff, double:1.26E-321)
            long r9 = r9 & r4
            r11 = 128(0x80, double:6.32E-322)
            int r9 = (r9 > r11 ? 1 : (r9 == r11 ? 0 : -1))
            if (r9 >= 0) goto L4c
            int r9 = r3 << 3
            int r9 = r9 + r8
            r9 = r0[r9]
            java.lang.Long r9 = java.lang.Long.valueOf(r9)
            java.lang.Object r9 = r14.invoke(r9)
            java.lang.Boolean r9 = (java.lang.Boolean) r9
            boolean r9 = r9.booleanValue()
            if (r9 == 0) goto L4c
            r13 = 1
            return r13
        L4c:
            long r4 = r4 >> r7
            int r8 = r8 + 1
            goto L2a
        L50:
            if (r6 != r7) goto L57
        L52:
            if (r3 == r1) goto L57
            int r3 = r3 + 1
            goto L10
        L57:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.collection.LongSet.any(kotlin.jvm.functions.Function1):boolean");
    }

    @IntRange(from = 0)
    public final int count(Function1 predicate) {
        n.g(predicate, "predicate");
        long[] jArr = this.elements;
        long[] jArr2 = this.metadata;
        int length = jArr2.length - 2;
        int i2 = 0;
        if (length >= 0) {
            int i3 = 0;
            int i4 = 0;
            while (true) {
                long j2 = jArr2[i3];
                if ((((~j2) << 7) & j2 & (-9187201950435737472L)) != -9187201950435737472L) {
                    int i5 = 8 - ((~(i3 - length)) >>> 31);
                    for (int i6 = 0; i6 < i5; i6++) {
                        if ((255 & j2) < 128 && ((Boolean) predicate.invoke(Long.valueOf(jArr[(i3 << 3) + i6]))).booleanValue()) {
                            i4++;
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

    /* JADX WARN: Removed duplicated region for block: B:41:0x00ae A[PHI: r11
      0x00ae: PHI (r11v2 int) = (r11v1 int), (r11v3 int) binds: [B:28:0x006e, B:40:0x00ac] A[DONT_GENERATE, DONT_INLINE]] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static /* synthetic */ java.lang.String joinToString$default(androidx.collection.LongSet r20, java.lang.CharSequence r21, java.lang.CharSequence r22, java.lang.CharSequence r23, int r24, java.lang.CharSequence r25, kotlin.jvm.functions.Function1 r26, int r27, java.lang.Object r28) {
        /*
            r0 = r20
            r1 = r26
            if (r28 != 0) goto Lc0
            r2 = r27 & 1
            if (r2 == 0) goto Ld
            java.lang.String r2 = ", "
            goto Lf
        Ld:
            r2 = r21
        Lf:
            r3 = r27 & 2
            java.lang.String r4 = ""
            if (r3 == 0) goto L17
            r3 = r4
            goto L19
        L17:
            r3 = r22
        L19:
            r5 = r27 & 4
            if (r5 == 0) goto L1e
            goto L20
        L1e:
            r4 = r23
        L20:
            r5 = r27 & 8
            if (r5 == 0) goto L26
            r5 = -1
            goto L28
        L26:
            r5 = r24
        L28:
            r6 = r27 & 16
            if (r6 == 0) goto L2f
            java.lang.String r6 = "..."
            goto L31
        L2f:
            r6 = r25
        L31:
            java.lang.String r7 = "separator"
            kotlin.jvm.internal.n.g(r2, r7)
            java.lang.String r7 = "prefix"
            kotlin.jvm.internal.n.g(r3, r7)
            java.lang.String r7 = "postfix"
            kotlin.jvm.internal.n.g(r4, r7)
            java.lang.String r7 = "truncated"
            kotlin.jvm.internal.n.g(r6, r7)
            java.lang.String r7 = "transform"
            kotlin.jvm.internal.n.g(r1, r7)
            java.lang.StringBuilder r7 = new java.lang.StringBuilder
            r7.<init>()
            r7.append(r3)
            long[] r3 = r0.elements
            long[] r0 = r0.metadata
            int r8 = r0.length
            int r8 = r8 + (-2)
            if (r8 < 0) goto Lb3
            r10 = 0
            r11 = 0
        L5d:
            r12 = r0[r10]
            long r14 = ~r12
            r16 = 7
            long r14 = r14 << r16
            long r14 = r14 & r12
            r16 = -9187201950435737472(0x8080808080808080, double:-2.937446524422997E-306)
            long r14 = r14 & r16
            int r14 = (r14 > r16 ? 1 : (r14 == r16 ? 0 : -1))
            if (r14 == 0) goto Lae
            int r14 = r10 - r8
            int r14 = ~r14
            int r14 = r14 >>> 31
            r15 = 8
            int r14 = 8 - r14
            r9 = 0
        L7a:
            if (r9 >= r14) goto Lac
            r16 = 255(0xff, double:1.26E-321)
            long r16 = r12 & r16
            r18 = 128(0x80, double:6.32E-322)
            int r16 = (r16 > r18 ? 1 : (r16 == r18 ? 0 : -1))
            if (r16 >= 0) goto La8
            int r16 = r10 << 3
            int r16 = r16 + r9
            r16 = r3[r16]
            if (r11 != r5) goto L92
            r7.append(r6)
            goto Lb6
        L92:
            if (r11 == 0) goto L97
            r7.append(r2)
        L97:
            java.lang.Long r15 = java.lang.Long.valueOf(r16)
            java.lang.Object r15 = r1.invoke(r15)
            java.lang.CharSequence r15 = (java.lang.CharSequence) r15
            r7.append(r15)
            int r11 = r11 + 1
            r15 = 8
        La8:
            long r12 = r12 >> r15
            int r9 = r9 + 1
            goto L7a
        Lac:
            if (r14 != r15) goto Lb3
        Lae:
            if (r10 == r8) goto Lb3
            int r10 = r10 + 1
            goto L5d
        Lb3:
            r7.append(r4)
        Lb6:
            java.lang.String r0 = r7.toString()
            java.lang.String r1 = "StringBuilder().apply(builderAction).toString()"
            kotlin.jvm.internal.n.f(r0, r1)
            return r0
        Lc0:
            java.lang.UnsupportedOperationException r0 = new java.lang.UnsupportedOperationException
            java.lang.String r1 = "Super calls with default arguments not supported in this target, function: joinToString"
            r0.<init>(r1)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.collection.LongSet.joinToString$default(androidx.collection.LongSet, java.lang.CharSequence, java.lang.CharSequence, java.lang.CharSequence, int, java.lang.CharSequence, kotlin.jvm.functions.Function1, int, java.lang.Object):java.lang.String");
    }

    public final String joinToString(CharSequence separator, CharSequence prefix, CharSequence postfix, int i2) {
        n.g(separator, "separator");
        n.g(prefix, "prefix");
        n.g(postfix, "postfix");
        return joinToString$default(this, separator, prefix, postfix, i2, null, 16, null);
    }

    public final String joinToString(CharSequence separator, CharSequence prefix, CharSequence postfix, int i2, CharSequence truncated) {
        int i3;
        int i4;
        n.g(separator, "separator");
        n.g(prefix, "prefix");
        n.g(postfix, "postfix");
        n.g(truncated, "truncated");
        StringBuilder sb = new StringBuilder();
        sb.append(prefix);
        long[] jArr = this.elements;
        long[] jArr2 = this.metadata;
        int length = jArr2.length - 2;
        if (length >= 0) {
            int i5 = 0;
            int i6 = 0;
            loop0: while (true) {
                long j2 = jArr2[i5];
                if ((((~j2) << 7) & j2 & (-9187201950435737472L)) != -9187201950435737472L) {
                    int i7 = 8 - ((~(i5 - length)) >>> 31);
                    int i8 = 0;
                    while (i8 < i7) {
                        if ((255 & j2) < 128) {
                            i4 = i5;
                            long j3 = jArr[(i5 << 3) + i8];
                            if (i6 == i2) {
                                sb.append(truncated);
                                break loop0;
                            }
                            if (i6 != 0) {
                                sb.append(separator);
                            }
                            sb.append(j3);
                            i6++;
                        } else {
                            i4 = i5;
                        }
                        j2 >>= 8;
                        i8++;
                        i5 = i4;
                    }
                    int i9 = i5;
                    if (i7 != 8) {
                        break;
                    }
                    i3 = i9;
                } else {
                    i3 = i5;
                }
                if (i3 == length) {
                    break;
                }
                i5 = i3 + 1;
            }
            sb.append(postfix);
        } else {
            sb.append(postfix);
        }
        String string = sb.toString();
        n.f(string, "StringBuilder().apply(builderAction).toString()");
        return string;
    }

    /* JADX WARN: Removed duplicated region for block: B:16:0x0051  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final long first(kotlin.jvm.functions.Function1 r14) {
        /*
            r13 = this;
            java.lang.String r0 = "predicate"
            kotlin.jvm.internal.n.g(r14, r0)
            long[] r0 = r13.elements
            long[] r13 = r13.metadata
            int r1 = r13.length
            int r1 = r1 + (-2)
            if (r1 < 0) goto L56
            r2 = 0
            r3 = r2
        L10:
            r4 = r13[r3]
            long r6 = ~r4
            r8 = 7
            long r6 = r6 << r8
            long r6 = r6 & r4
            r8 = -9187201950435737472(0x8080808080808080, double:-2.937446524422997E-306)
            long r6 = r6 & r8
            int r6 = (r6 > r8 ? 1 : (r6 == r8 ? 0 : -1))
            if (r6 == 0) goto L51
            int r6 = r3 - r1
            int r6 = ~r6
            int r6 = r6 >>> 31
            r7 = 8
            int r6 = 8 - r6
            r8 = r2
        L2a:
            if (r8 >= r6) goto L4f
            r9 = 255(0xff, double:1.26E-321)
            long r9 = r9 & r4
            r11 = 128(0x80, double:6.32E-322)
            int r9 = (r9 > r11 ? 1 : (r9 == r11 ? 0 : -1))
            if (r9 >= 0) goto L4b
            int r9 = r3 << 3
            int r9 = r9 + r8
            r9 = r0[r9]
            java.lang.Long r11 = java.lang.Long.valueOf(r9)
            java.lang.Object r11 = r14.invoke(r11)
            java.lang.Boolean r11 = (java.lang.Boolean) r11
            boolean r11 = r11.booleanValue()
            if (r11 == 0) goto L4b
            return r9
        L4b:
            long r4 = r4 >> r7
            int r8 = r8 + 1
            goto L2a
        L4f:
            if (r6 != r7) goto L56
        L51:
            if (r3 == r1) goto L56
            int r3 = r3 + 1
            goto L10
        L56:
            java.util.NoSuchElementException r13 = new java.util.NoSuchElementException
            java.lang.String r14 = "Could not find a match"
            r13.<init>(r14)
            throw r13
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.collection.LongSet.first(kotlin.jvm.functions.Function1):long");
    }

    public final String joinToString(CharSequence separator, CharSequence prefix, CharSequence postfix, int i2, CharSequence truncated, Function1 transform) {
        n.g(separator, "separator");
        n.g(prefix, "prefix");
        n.g(postfix, "postfix");
        n.g(truncated, "truncated");
        n.g(transform, "transform");
        StringBuilder sb = new StringBuilder();
        sb.append(prefix);
        long[] jArr = this.elements;
        long[] jArr2 = this.metadata;
        int length = jArr2.length - 2;
        if (length >= 0) {
            int i3 = 0;
            int i4 = 0;
            loop0: while (true) {
                long j2 = jArr2[i3];
                if ((((~j2) << 7) & j2 & (-9187201950435737472L)) != -9187201950435737472L) {
                    int i5 = 8;
                    int i6 = 8 - ((~(i3 - length)) >>> 31);
                    for (int i7 = 0; i7 < i6; i7++) {
                        if ((j2 & 255) < 128) {
                            long j3 = jArr[(i3 << 3) + i7];
                            if (i4 == i2) {
                                sb.append(truncated);
                                break loop0;
                            }
                            if (i4 != 0) {
                                sb.append(separator);
                            }
                            sb.append((CharSequence) transform.invoke(Long.valueOf(j3)));
                            i4++;
                            i5 = 8;
                        }
                        j2 >>= i5;
                    }
                    if (i6 != i5) {
                        break;
                    }
                }
                if (i3 == length) {
                    break;
                }
                i3++;
            }
            sb.append(postfix);
        } else {
            sb.append(postfix);
        }
        String string = sb.toString();
        n.f(string, "StringBuilder().apply(builderAction).toString()");
        return string;
    }

    public final String joinToString(CharSequence separator, CharSequence prefix, CharSequence postfix, int i2, Function1 transform) {
        n.g(separator, "separator");
        n.g(prefix, "prefix");
        n.g(postfix, "postfix");
        n.g(transform, "transform");
        StringBuilder sb = new StringBuilder();
        sb.append(prefix);
        long[] jArr = this.elements;
        long[] jArr2 = this.metadata;
        int length = jArr2.length - 2;
        if (length >= 0) {
            int i3 = 0;
            int i4 = 0;
            loop0: while (true) {
                long j2 = jArr2[i3];
                if ((((~j2) << 7) & j2 & (-9187201950435737472L)) != -9187201950435737472L) {
                    int i5 = 8 - ((~(i3 - length)) >>> 31);
                    for (int i6 = 0; i6 < i5; i6++) {
                        if ((255 & j2) < 128) {
                            long j3 = jArr[(i3 << 3) + i6];
                            if (i4 == i2) {
                                sb.append((CharSequence) "...");
                                break loop0;
                            }
                            if (i4 != 0) {
                                sb.append(separator);
                            }
                            sb.append((CharSequence) transform.invoke(Long.valueOf(j3)));
                            i4++;
                        }
                        j2 >>= 8;
                    }
                    if (i5 != 8) {
                        break;
                    }
                }
                if (i3 == length) {
                    break;
                }
                i3++;
            }
            sb.append(postfix);
        } else {
            sb.append(postfix);
        }
        String string = sb.toString();
        n.f(string, "StringBuilder().apply(builderAction).toString()");
        return string;
    }

    /* JADX WARN: Removed duplicated region for block: B:19:0x007e A[PHI: r9
      0x007e: PHI (r9v2 int) = (r9v1 int), (r9v3 int) binds: [B:6:0x003f, B:18:0x007c] A[DONT_GENERATE, DONT_INLINE]] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.String joinToString(java.lang.CharSequence r20, java.lang.CharSequence r21, java.lang.CharSequence r22, kotlin.jvm.functions.Function1 r23) {
        /*
            r19 = this;
            r0 = r19
            r1 = r20
            r2 = r21
            r3 = r22
            r4 = r23
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
            long[] r2 = r0.elements
            long[] r0 = r0.metadata
            int r6 = r0.length
            int r6 = r6 + (-2)
            if (r6 < 0) goto L83
            r8 = 0
            r9 = 0
        L31:
            r10 = r0[r8]
            long r12 = ~r10
            r14 = 7
            long r12 = r12 << r14
            long r12 = r12 & r10
            r14 = -9187201950435737472(0x8080808080808080, double:-2.937446524422997E-306)
            long r12 = r12 & r14
            int r12 = (r12 > r14 ? 1 : (r12 == r14 ? 0 : -1))
            if (r12 == 0) goto L7e
            int r12 = r8 - r6
            int r12 = ~r12
            int r12 = r12 >>> 31
            r13 = 8
            int r12 = 8 - r12
            r14 = 0
        L4b:
            if (r14 >= r12) goto L7c
            r15 = 255(0xff, double:1.26E-321)
            long r15 = r15 & r10
            r17 = 128(0x80, double:6.32E-322)
            int r15 = (r15 > r17 ? 1 : (r15 == r17 ? 0 : -1))
            if (r15 >= 0) goto L78
            int r15 = r8 << 3
            int r15 = r15 + r14
            r15 = r2[r15]
            r7 = -1
            if (r9 != r7) goto L64
            java.lang.String r0 = "..."
            r5.append(r0)
            goto L86
        L64:
            if (r9 == 0) goto L69
            r5.append(r1)
        L69:
            java.lang.Long r7 = java.lang.Long.valueOf(r15)
            java.lang.Object r7 = r4.invoke(r7)
            java.lang.CharSequence r7 = (java.lang.CharSequence) r7
            r5.append(r7)
            int r9 = r9 + 1
        L78:
            long r10 = r10 >> r13
            int r14 = r14 + 1
            goto L4b
        L7c:
            if (r12 != r13) goto L83
        L7e:
            if (r8 == r6) goto L83
            int r8 = r8 + 1
            goto L31
        L83:
            r5.append(r3)
        L86:
            java.lang.String r0 = r5.toString()
            java.lang.String r1 = "StringBuilder().apply(builderAction).toString()"
            kotlin.jvm.internal.n.f(r0, r1)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.collection.LongSet.joinToString(java.lang.CharSequence, java.lang.CharSequence, java.lang.CharSequence, kotlin.jvm.functions.Function1):java.lang.String");
    }

    /* JADX WARN: Removed duplicated region for block: B:19:0x0077 A[PHI: r8
      0x0077: PHI (r8v2 int) = (r8v1 int), (r8v3 int) binds: [B:6:0x0038, B:18:0x0075] A[DONT_GENERATE, DONT_INLINE]] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.String joinToString(java.lang.CharSequence r19, java.lang.CharSequence r20, kotlin.jvm.functions.Function1 r21) {
        /*
            r18 = this;
            r0 = r18
            r1 = r19
            r2 = r20
            r3 = r21
            java.lang.String r4 = "separator"
            kotlin.jvm.internal.n.g(r1, r4)
            java.lang.String r4 = "prefix"
            kotlin.jvm.internal.n.g(r2, r4)
            java.lang.String r4 = "transform"
            kotlin.jvm.internal.n.g(r3, r4)
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            r4.<init>()
            r4.append(r2)
            long[] r2 = r0.elements
            long[] r0 = r0.metadata
            int r5 = r0.length
            int r5 = r5 + (-2)
            if (r5 < 0) goto L7c
            r7 = 0
            r8 = 0
        L2a:
            r9 = r0[r7]
            long r11 = ~r9
            r13 = 7
            long r11 = r11 << r13
            long r11 = r11 & r9
            r13 = -9187201950435737472(0x8080808080808080, double:-2.937446524422997E-306)
            long r11 = r11 & r13
            int r11 = (r11 > r13 ? 1 : (r11 == r13 ? 0 : -1))
            if (r11 == 0) goto L77
            int r11 = r7 - r5
            int r11 = ~r11
            int r11 = r11 >>> 31
            r12 = 8
            int r11 = 8 - r11
            r13 = 0
        L44:
            if (r13 >= r11) goto L75
            r14 = 255(0xff, double:1.26E-321)
            long r14 = r14 & r9
            r16 = 128(0x80, double:6.32E-322)
            int r14 = (r14 > r16 ? 1 : (r14 == r16 ? 0 : -1))
            if (r14 >= 0) goto L71
            int r14 = r7 << 3
            int r14 = r14 + r13
            r14 = r2[r14]
            r6 = -1
            if (r8 != r6) goto L5d
            java.lang.String r0 = "..."
            r4.append(r0)
            goto L81
        L5d:
            if (r8 == 0) goto L62
            r4.append(r1)
        L62:
            java.lang.Long r6 = java.lang.Long.valueOf(r14)
            java.lang.Object r6 = r3.invoke(r6)
            java.lang.CharSequence r6 = (java.lang.CharSequence) r6
            r4.append(r6)
            int r8 = r8 + 1
        L71:
            long r9 = r9 >> r12
            int r13 = r13 + 1
            goto L44
        L75:
            if (r11 != r12) goto L7c
        L77:
            if (r7 == r5) goto L7c
            int r7 = r7 + 1
            goto L2a
        L7c:
            java.lang.String r0 = ""
            r4.append(r0)
        L81:
            java.lang.String r0 = r4.toString()
            java.lang.String r1 = "StringBuilder().apply(builderAction).toString()"
            kotlin.jvm.internal.n.f(r0, r1)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.collection.LongSet.joinToString(java.lang.CharSequence, java.lang.CharSequence, kotlin.jvm.functions.Function1):java.lang.String");
    }

    /* JADX WARN: Removed duplicated region for block: B:19:0x0072 A[PHI: r9
      0x0072: PHI (r9v2 int) = (r9v1 int), (r9v3 int) binds: [B:6:0x0033, B:18:0x0070] A[DONT_GENERATE, DONT_INLINE]] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.String joinToString(java.lang.CharSequence r20, kotlin.jvm.functions.Function1 r21) {
        /*
            r19 = this;
            r0 = r19
            r1 = r20
            r2 = r21
            java.lang.String r3 = "separator"
            kotlin.jvm.internal.n.g(r1, r3)
            java.lang.String r3 = "transform"
            kotlin.jvm.internal.n.g(r2, r3)
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            java.lang.String r4 = ""
            r3.append(r4)
            long[] r5 = r0.elements
            long[] r0 = r0.metadata
            int r6 = r0.length
            int r6 = r6 + (-2)
            if (r6 < 0) goto L77
            r8 = 0
            r9 = 0
        L25:
            r10 = r0[r8]
            long r12 = ~r10
            r14 = 7
            long r12 = r12 << r14
            long r12 = r12 & r10
            r14 = -9187201950435737472(0x8080808080808080, double:-2.937446524422997E-306)
            long r12 = r12 & r14
            int r12 = (r12 > r14 ? 1 : (r12 == r14 ? 0 : -1))
            if (r12 == 0) goto L72
            int r12 = r8 - r6
            int r12 = ~r12
            int r12 = r12 >>> 31
            r13 = 8
            int r12 = 8 - r12
            r14 = 0
        L3f:
            if (r14 >= r12) goto L70
            r15 = 255(0xff, double:1.26E-321)
            long r15 = r15 & r10
            r17 = 128(0x80, double:6.32E-322)
            int r15 = (r15 > r17 ? 1 : (r15 == r17 ? 0 : -1))
            if (r15 >= 0) goto L6c
            int r15 = r8 << 3
            int r15 = r15 + r14
            r15 = r5[r15]
            r7 = -1
            if (r9 != r7) goto L58
            java.lang.String r0 = "..."
            r3.append(r0)
            goto L7a
        L58:
            if (r9 == 0) goto L5d
            r3.append(r1)
        L5d:
            java.lang.Long r7 = java.lang.Long.valueOf(r15)
            java.lang.Object r7 = r2.invoke(r7)
            java.lang.CharSequence r7 = (java.lang.CharSequence) r7
            r3.append(r7)
            int r9 = r9 + 1
        L6c:
            long r10 = r10 >> r13
            int r14 = r14 + 1
            goto L3f
        L70:
            if (r12 != r13) goto L77
        L72:
            if (r8 == r6) goto L77
            int r8 = r8 + 1
            goto L25
        L77:
            r3.append(r4)
        L7a:
            java.lang.String r0 = r3.toString()
            java.lang.String r1 = "StringBuilder().apply(builderAction).toString()"
            kotlin.jvm.internal.n.f(r0, r1)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.collection.LongSet.joinToString(java.lang.CharSequence, kotlin.jvm.functions.Function1):java.lang.String");
    }

    /* JADX WARN: Removed duplicated region for block: B:19:0x006d A[PHI: r8
      0x006d: PHI (r8v2 int) = (r8v1 int), (r8v3 int) binds: [B:6:0x002c, B:18:0x006b] A[DONT_GENERATE, DONT_INLINE]] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.String joinToString(kotlin.jvm.functions.Function1 r19) {
        /*
            r18 = this;
            r0 = r18
            r1 = r19
            java.lang.String r2 = "transform"
            kotlin.jvm.internal.n.g(r1, r2)
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            java.lang.String r3 = ""
            r2.append(r3)
            long[] r4 = r0.elements
            long[] r0 = r0.metadata
            int r5 = r0.length
            int r5 = r5 + (-2)
            if (r5 < 0) goto L72
            r7 = 0
            r8 = 0
        L1e:
            r9 = r0[r7]
            long r11 = ~r9
            r13 = 7
            long r11 = r11 << r13
            long r11 = r11 & r9
            r13 = -9187201950435737472(0x8080808080808080, double:-2.937446524422997E-306)
            long r11 = r11 & r13
            int r11 = (r11 > r13 ? 1 : (r11 == r13 ? 0 : -1))
            if (r11 == 0) goto L6d
            int r11 = r7 - r5
            int r11 = ~r11
            int r11 = r11 >>> 31
            r12 = 8
            int r11 = 8 - r11
            r13 = 0
        L38:
            if (r13 >= r11) goto L6b
            r14 = 255(0xff, double:1.26E-321)
            long r14 = r14 & r9
            r16 = 128(0x80, double:6.32E-322)
            int r14 = (r14 > r16 ? 1 : (r14 == r16 ? 0 : -1))
            if (r14 >= 0) goto L67
            int r14 = r7 << 3
            int r14 = r14 + r13
            r14 = r4[r14]
            r6 = -1
            if (r8 != r6) goto L51
            java.lang.String r0 = "..."
            r2.append(r0)
            goto L75
        L51:
            if (r8 == 0) goto L58
            java.lang.String r6 = ", "
            r2.append(r6)
        L58:
            java.lang.Long r6 = java.lang.Long.valueOf(r14)
            java.lang.Object r6 = r1.invoke(r6)
            java.lang.CharSequence r6 = (java.lang.CharSequence) r6
            r2.append(r6)
            int r8 = r8 + 1
        L67:
            long r9 = r9 >> r12
            int r13 = r13 + 1
            goto L38
        L6b:
            if (r11 != r12) goto L72
        L6d:
            if (r7 == r5) goto L72
            int r7 = r7 + 1
            goto L1e
        L72:
            r2.append(r3)
        L75:
            java.lang.String r0 = r2.toString()
            java.lang.String r1 = "StringBuilder().apply(builderAction).toString()"
            kotlin.jvm.internal.n.f(r0, r1)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.collection.LongSet.joinToString(kotlin.jvm.functions.Function1):java.lang.String");
    }
}
