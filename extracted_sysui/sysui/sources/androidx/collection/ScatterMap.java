package androidx.collection;

import W0.a;
import androidx.collection.internal.ContainerHelpersKt;
import java.util.Collection;
import java.util.Map;
import java.util.Set;
import java.util.function.BiFunction;
import java.util.function.Function;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.n;

/* JADX INFO: loaded from: classes.dex */
public abstract class ScatterMap<K, V> {
    public int _capacity;
    public int _size;
    public Object[] keys;
    public long[] metadata;
    public Object[] values;

    public class MapWrapper implements Map<K, V>, a {
        public MapWrapper() {
        }

        @Override // java.util.Map
        public void clear() {
            throw new UnsupportedOperationException("Operation is not supported for read-only collection");
        }

        @Override // java.util.Map
        public V compute(K k2, BiFunction<? super K, ? super V, ? extends V> biFunction) {
            throw new UnsupportedOperationException("Operation is not supported for read-only collection");
        }

        @Override // java.util.Map
        public V computeIfAbsent(K k2, Function<? super K, ? extends V> function) {
            throw new UnsupportedOperationException("Operation is not supported for read-only collection");
        }

        @Override // java.util.Map
        public V computeIfPresent(K k2, BiFunction<? super K, ? super V, ? extends V> biFunction) {
            throw new UnsupportedOperationException("Operation is not supported for read-only collection");
        }

        @Override // java.util.Map
        public boolean containsKey(Object obj) {
            return ScatterMap.this.containsKey(obj);
        }

        @Override // java.util.Map
        public boolean containsValue(Object obj) {
            return ScatterMap.this.containsValue(obj);
        }

        @Override // java.util.Map
        public final /* bridge */ Set<Map.Entry<K, V>> entrySet() {
            return getEntries();
        }

        @Override // java.util.Map
        public V get(Object obj) {
            return ScatterMap.this.get(obj);
        }

        public Set<Map.Entry<K, V>> getEntries() {
            return new ScatterMap$MapWrapper$entries$1(ScatterMap.this);
        }

        public Set<K> getKeys() {
            return new ScatterMap$MapWrapper$keys$1(ScatterMap.this);
        }

        public int getSize() {
            return ScatterMap.this._size;
        }

        public Collection<V> getValues() {
            return new ScatterMap$MapWrapper$values$1(ScatterMap.this);
        }

        @Override // java.util.Map
        public boolean isEmpty() {
            return ScatterMap.this.isEmpty();
        }

        @Override // java.util.Map
        public final /* bridge */ Set<K> keySet() {
            return getKeys();
        }

        @Override // java.util.Map
        public V merge(K k2, V v2, BiFunction<? super V, ? super V, ? extends V> biFunction) {
            throw new UnsupportedOperationException("Operation is not supported for read-only collection");
        }

        @Override // java.util.Map
        public V put(K k2, V v2) {
            throw new UnsupportedOperationException("Operation is not supported for read-only collection");
        }

        @Override // java.util.Map
        public void putAll(Map<? extends K, ? extends V> map) {
            throw new UnsupportedOperationException("Operation is not supported for read-only collection");
        }

        @Override // java.util.Map
        public V putIfAbsent(K k2, V v2) {
            throw new UnsupportedOperationException("Operation is not supported for read-only collection");
        }

        @Override // java.util.Map
        public V remove(Object obj) {
            throw new UnsupportedOperationException("Operation is not supported for read-only collection");
        }

        @Override // java.util.Map
        public V replace(K k2, V v2) {
            throw new UnsupportedOperationException("Operation is not supported for read-only collection");
        }

        @Override // java.util.Map
        public void replaceAll(BiFunction<? super K, ? super V, ? extends V> biFunction) {
            throw new UnsupportedOperationException("Operation is not supported for read-only collection");
        }

        @Override // java.util.Map
        public final /* bridge */ int size() {
            return getSize();
        }

        @Override // java.util.Map
        public final /* bridge */ Collection<V> values() {
            return getValues();
        }

        @Override // java.util.Map
        public boolean remove(Object obj, Object obj2) {
            throw new UnsupportedOperationException("Operation is not supported for read-only collection");
        }

        @Override // java.util.Map
        public boolean replace(K k2, V v2, V v3) {
            throw new UnsupportedOperationException("Operation is not supported for read-only collection");
        }
    }

    public /* synthetic */ ScatterMap(DefaultConstructorMarker defaultConstructorMarker) {
        this();
    }

    public static /* synthetic */ void getKeys$annotations() {
    }

    public static /* synthetic */ void getMetadata$annotations() {
    }

    public static /* synthetic */ void getValues$annotations() {
    }

    public static /* synthetic */ String joinToString$default(ScatterMap scatterMap, CharSequence charSequence, CharSequence charSequence2, CharSequence charSequence3, int i2, CharSequence charSequence4, Function2 function2, int i3, Object obj) {
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
        CharSequence charSequence7 = charSequence4;
        if ((i3 & 32) != 0) {
            function2 = null;
        }
        return scatterMap.joinToString(charSequence, charSequence5, charSequence6, i4, charSequence7, function2);
    }

    public final boolean all(Function2 predicate) {
        n.g(predicate, "predicate");
        Object[] objArr = this.keys;
        Object[] objArr2 = this.values;
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
                        if (!((Boolean) predicate.invoke(objArr[i5], objArr2[i5])).booleanValue()) {
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

    public final String asDebugString$collection() {
        StringBuilder sb = new StringBuilder();
        sb.append('{');
        sb.append("metadata=[");
        int capacity = getCapacity();
        for (int i2 = 0; i2 < capacity; i2++) {
            long j2 = (this.metadata[i2 >> 3] >> ((i2 & 7) << 3)) & 255;
            if (j2 == 128) {
                sb.append("Empty");
            } else if (j2 == 254) {
                sb.append("Deleted");
            } else {
                sb.append(j2);
            }
            sb.append(", ");
        }
        sb.append("], ");
        sb.append("keys=[");
        int length = this.keys.length;
        for (int i3 = 0; i3 < length; i3++) {
            sb.append(this.keys[i3]);
            sb.append(", ");
        }
        sb.append("], ");
        sb.append("values=[");
        int length2 = this.values.length;
        for (int i4 = 0; i4 < length2; i4++) {
            sb.append(this.values[i4]);
            sb.append(", ");
        }
        sb.append("]");
        sb.append('}');
        String string = sb.toString();
        n.f(string, "StringBuilder().apply(builderAction).toString()");
        return string;
    }

    public final Map<K, V> asMap() {
        return new MapWrapper();
    }

    /* JADX WARN: Code restructure failed: missing block: B:15:0x006d, code lost:
    
        if (((r7 & ((~r7) << 6)) & (-9187201950435737472L)) == 0) goto L20;
     */
    /* JADX WARN: Code restructure failed: missing block: B:16:0x006f, code lost:
    
        r11 = -1;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final boolean contains(K r18) {
        /*
            r17 = this;
            r0 = r17
            r1 = r18
            r2 = 0
            if (r1 == 0) goto Lc
            int r3 = r18.hashCode()
            goto Ld
        Lc:
            r3 = r2
        Ld:
            r4 = -862048943(0xffffffffcc9e2d51, float:-8.2930312E7)
            int r3 = r3 * r4
            int r4 = r3 << 16
            r3 = r3 ^ r4
            r4 = r3 & 127(0x7f, float:1.78E-43)
            int r5 = r0._capacity
            int r3 = r3 >>> 7
            r3 = r3 & r5
            r6 = r2
        L1c:
            long[] r7 = r0.metadata
            int r8 = r3 >> 3
            r9 = r3 & 7
            int r9 = r9 << 3
            r10 = r7[r8]
            long r10 = r10 >>> r9
            r12 = 1
            int r8 = r8 + r12
            r7 = r7[r8]
            int r13 = 64 - r9
            long r7 = r7 << r13
            long r13 = (long) r9
            long r13 = -r13
            r9 = 63
            long r13 = r13 >> r9
            long r7 = r7 & r13
            long r7 = r7 | r10
            long r9 = (long) r4
            r13 = 72340172838076673(0x101010101010101, double:7.748604185489348E-304)
            long r9 = r9 * r13
            long r9 = r9 ^ r7
            long r13 = r9 - r13
            long r9 = ~r9
            long r9 = r9 & r13
            r13 = -9187201950435737472(0x8080808080808080, double:-2.937446524422997E-306)
            long r9 = r9 & r13
        L47:
            r15 = 0
            int r11 = (r9 > r15 ? 1 : (r9 == r15 ? 0 : -1))
            if (r11 == 0) goto L66
            int r11 = java.lang.Long.numberOfTrailingZeros(r9)
            int r11 = r11 >> 3
            int r11 = r11 + r3
            r11 = r11 & r5
            java.lang.Object[] r15 = r0.keys
            r15 = r15[r11]
            boolean r15 = kotlin.jvm.internal.n.c(r15, r1)
            if (r15 == 0) goto L60
            goto L70
        L60:
            r15 = 1
            long r15 = r9 - r15
            long r9 = r9 & r15
            goto L47
        L66:
            long r9 = ~r7
            r11 = 6
            long r9 = r9 << r11
            long r7 = r7 & r9
            long r7 = r7 & r13
            int r7 = (r7 > r15 ? 1 : (r7 == r15 ? 0 : -1))
            if (r7 == 0) goto L74
            r11 = -1
        L70:
            if (r11 < 0) goto L73
            r2 = r12
        L73:
            return r2
        L74:
            int r6 = r6 + 8
            int r3 = r3 + r6
            r3 = r3 & r5
            goto L1c
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.collection.ScatterMap.contains(java.lang.Object):boolean");
    }

    /* JADX WARN: Code restructure failed: missing block: B:15:0x006d, code lost:
    
        if (((r7 & ((~r7) << 6)) & (-9187201950435737472L)) == 0) goto L20;
     */
    /* JADX WARN: Code restructure failed: missing block: B:16:0x006f, code lost:
    
        r11 = -1;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final boolean containsKey(K r18) {
        /*
            r17 = this;
            r0 = r17
            r1 = r18
            r2 = 0
            if (r1 == 0) goto Lc
            int r3 = r18.hashCode()
            goto Ld
        Lc:
            r3 = r2
        Ld:
            r4 = -862048943(0xffffffffcc9e2d51, float:-8.2930312E7)
            int r3 = r3 * r4
            int r4 = r3 << 16
            r3 = r3 ^ r4
            r4 = r3 & 127(0x7f, float:1.78E-43)
            int r5 = r0._capacity
            int r3 = r3 >>> 7
            r3 = r3 & r5
            r6 = r2
        L1c:
            long[] r7 = r0.metadata
            int r8 = r3 >> 3
            r9 = r3 & 7
            int r9 = r9 << 3
            r10 = r7[r8]
            long r10 = r10 >>> r9
            r12 = 1
            int r8 = r8 + r12
            r7 = r7[r8]
            int r13 = 64 - r9
            long r7 = r7 << r13
            long r13 = (long) r9
            long r13 = -r13
            r9 = 63
            long r13 = r13 >> r9
            long r7 = r7 & r13
            long r7 = r7 | r10
            long r9 = (long) r4
            r13 = 72340172838076673(0x101010101010101, double:7.748604185489348E-304)
            long r9 = r9 * r13
            long r9 = r9 ^ r7
            long r13 = r9 - r13
            long r9 = ~r9
            long r9 = r9 & r13
            r13 = -9187201950435737472(0x8080808080808080, double:-2.937446524422997E-306)
            long r9 = r9 & r13
        L47:
            r15 = 0
            int r11 = (r9 > r15 ? 1 : (r9 == r15 ? 0 : -1))
            if (r11 == 0) goto L66
            int r11 = java.lang.Long.numberOfTrailingZeros(r9)
            int r11 = r11 >> 3
            int r11 = r11 + r3
            r11 = r11 & r5
            java.lang.Object[] r15 = r0.keys
            r15 = r15[r11]
            boolean r15 = kotlin.jvm.internal.n.c(r15, r1)
            if (r15 == 0) goto L60
            goto L70
        L60:
            r15 = 1
            long r15 = r9 - r15
            long r9 = r9 & r15
            goto L47
        L66:
            long r9 = ~r7
            r11 = 6
            long r9 = r9 << r11
            long r7 = r7 & r9
            long r7 = r7 & r13
            int r7 = (r7 > r15 ? 1 : (r7 == r15 ? 0 : -1))
            if (r7 == 0) goto L74
            r11 = -1
        L70:
            if (r11 < 0) goto L73
            r2 = r12
        L73:
            return r2
        L74:
            int r6 = r6 + 8
            int r3 = r3 + r6
            r3 = r3 & r5
            goto L1c
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.collection.ScatterMap.containsKey(java.lang.Object):boolean");
    }

    /* JADX WARN: Removed duplicated region for block: B:17:0x0043  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final boolean containsValue(V r14) {
        /*
            r13 = this;
            java.lang.Object[] r0 = r13.values
            long[] r13 = r13.metadata
            int r1 = r13.length
            int r1 = r1 + (-2)
            r2 = 0
            if (r1 < 0) goto L48
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
            if (r6 == 0) goto L43
            int r6 = r3 - r1
            int r6 = ~r6
            int r6 = r6 >>> 31
            r7 = 8
            int r6 = 8 - r6
            r8 = r2
        L25:
            if (r8 >= r6) goto L41
            r9 = 255(0xff, double:1.26E-321)
            long r9 = r9 & r4
            r11 = 128(0x80, double:6.32E-322)
            int r9 = (r9 > r11 ? 1 : (r9 == r11 ? 0 : -1))
            if (r9 >= 0) goto L3d
            int r9 = r3 << 3
            int r9 = r9 + r8
            r9 = r0[r9]
            boolean r9 = kotlin.jvm.internal.n.c(r14, r9)
            if (r9 == 0) goto L3d
            r13 = 1
            return r13
        L3d:
            long r4 = r4 >> r7
            int r8 = r8 + 1
            goto L25
        L41:
            if (r6 != r7) goto L48
        L43:
            if (r3 == r1) goto L48
            int r3 = r3 + 1
            goto Lb
        L48:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.collection.ScatterMap.containsValue(java.lang.Object):boolean");
    }

    public final int count() {
        return getSize();
    }

    /* JADX WARN: Code restructure failed: missing block: B:26:0x0061, code lost:
    
        return false;
     */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:32:0x0073  */
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
            boolean r3 = r1 instanceof androidx.collection.ScatterMap
            r4 = 0
            if (r3 != 0) goto Le
            return r4
        Le:
            androidx.collection.ScatterMap r1 = (androidx.collection.ScatterMap) r1
            int r3 = r1.getSize()
            int r5 = r17.getSize()
            if (r3 == r5) goto L1b
            return r4
        L1b:
            java.lang.Object[] r3 = r0.keys
            java.lang.Object[] r5 = r0.values
            long[] r0 = r0.metadata
            int r6 = r0.length
            int r6 = r6 + (-2)
            if (r6 < 0) goto L78
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
            if (r10 == 0) goto L73
            int r10 = r7 - r6
            int r10 = ~r10
            int r10 = r10 >>> 31
            r11 = 8
            int r10 = 8 - r10
            r12 = r4
        L41:
            if (r12 >= r10) goto L71
            r13 = 255(0xff, double:1.26E-321)
            long r13 = r13 & r8
            r15 = 128(0x80, double:6.32E-322)
            int r13 = (r13 > r15 ? 1 : (r13 == r15 ? 0 : -1))
            if (r13 >= 0) goto L6d
            int r13 = r7 << 3
            int r13 = r13 + r12
            r14 = r3[r13]
            r13 = r5[r13]
            if (r13 != 0) goto L62
            java.lang.Object r13 = r1.get(r14)
            if (r13 != 0) goto L61
            boolean r13 = r1.containsKey(r14)
            if (r13 != 0) goto L6d
        L61:
            return r4
        L62:
            java.lang.Object r14 = r1.get(r14)
            boolean r13 = kotlin.jvm.internal.n.c(r13, r14)
            if (r13 != 0) goto L6d
            return r4
        L6d:
            long r8 = r8 >> r11
            int r12 = r12 + 1
            goto L41
        L71:
            if (r10 != r11) goto L78
        L73:
            if (r7 == r6) goto L78
            int r7 = r7 + 1
            goto L27
        L78:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.collection.ScatterMap.equals(java.lang.Object):boolean");
    }

    public final int findKeyIndex$collection(K k2) {
        int i2 = 0;
        int iHashCode = (k2 != null ? k2.hashCode() : 0) * ScatterMapKt.MurmurHashC1;
        int i3 = iHashCode ^ (iHashCode << 16);
        int i4 = i3 & 127;
        int i5 = this._capacity;
        int i6 = i3 >>> 7;
        while (true) {
            int i7 = i6 & i5;
            long[] jArr = this.metadata;
            int i8 = i7 >> 3;
            int i9 = (i7 & 7) << 3;
            long j2 = ((jArr[i8 + 1] << (64 - i9)) & ((-i9) >> 63)) | (jArr[i8] >>> i9);
            long j3 = (((long) i4) * ScatterMapKt.BitmaskLsb) ^ j2;
            for (long j4 = (~j3) & (j3 - ScatterMapKt.BitmaskLsb) & (-9187201950435737472L); j4 != 0; j4 &= j4 - 1) {
                int iNumberOfTrailingZeros = ((Long.numberOfTrailingZeros(j4) >> 3) + i7) & i5;
                if (n.c(this.keys[iNumberOfTrailingZeros], k2)) {
                    return iNumberOfTrailingZeros;
                }
            }
            if ((j2 & ((~j2) << 6) & (-9187201950435737472L)) != 0) {
                return -1;
            }
            i2 += 8;
            i6 = i7 + i2;
        }
    }

    public final void forEach(Function2 block) {
        n.g(block, "block");
        Object[] objArr = this.keys;
        Object[] objArr2 = this.values;
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
                        block.invoke(objArr[i5], objArr2[i5]);
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
        Object[] objArr = this.keys;
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
                        block.invoke(objArr[(i2 << 3) + i4]);
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
        Object[] objArr = this.values;
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
                        block.invoke(objArr[(i2 << 3) + i4]);
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

    /* JADX WARN: Code restructure failed: missing block: B:15:0x0068, code lost:
    
        if (((r4 & ((~r4) << 6)) & (-9187201950435737472L)) == 0) goto L21;
     */
    /* JADX WARN: Code restructure failed: missing block: B:16:0x006a, code lost:
    
        r10 = -1;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final V get(K r14) {
        /*
            r13 = this;
            r0 = 0
            if (r14 == 0) goto L8
            int r1 = r14.hashCode()
            goto L9
        L8:
            r1 = r0
        L9:
            r2 = -862048943(0xffffffffcc9e2d51, float:-8.2930312E7)
            int r1 = r1 * r2
            int r2 = r1 << 16
            r1 = r1 ^ r2
            r2 = r1 & 127(0x7f, float:1.78E-43)
            int r3 = r13._capacity
            int r1 = r1 >>> 7
        L16:
            r1 = r1 & r3
            long[] r4 = r13.metadata
            int r5 = r1 >> 3
            r6 = r1 & 7
            int r6 = r6 << 3
            r7 = r4[r5]
            long r7 = r7 >>> r6
            int r5 = r5 + 1
            r4 = r4[r5]
            int r9 = 64 - r6
            long r4 = r4 << r9
            long r9 = (long) r6
            long r9 = -r9
            r6 = 63
            long r9 = r9 >> r6
            long r4 = r4 & r9
            long r4 = r4 | r7
            long r6 = (long) r2
            r8 = 72340172838076673(0x101010101010101, double:7.748604185489348E-304)
            long r6 = r6 * r8
            long r6 = r6 ^ r4
            long r8 = r6 - r8
            long r6 = ~r6
            long r6 = r6 & r8
            r8 = -9187201950435737472(0x8080808080808080, double:-2.937446524422997E-306)
            long r6 = r6 & r8
        L42:
            r10 = 0
            int r12 = (r6 > r10 ? 1 : (r6 == r10 ? 0 : -1))
            if (r12 == 0) goto L61
            int r10 = java.lang.Long.numberOfTrailingZeros(r6)
            int r10 = r10 >> 3
            int r10 = r10 + r1
            r10 = r10 & r3
            java.lang.Object[] r11 = r13.keys
            r11 = r11[r10]
            boolean r11 = kotlin.jvm.internal.n.c(r11, r14)
            if (r11 == 0) goto L5b
            goto L6b
        L5b:
            r10 = 1
            long r10 = r6 - r10
            long r6 = r6 & r10
            goto L42
        L61:
            long r6 = ~r4
            r12 = 6
            long r6 = r6 << r12
            long r4 = r4 & r6
            long r4 = r4 & r8
            int r4 = (r4 > r10 ? 1 : (r4 == r10 ? 0 : -1))
            if (r4 == 0) goto L74
            r10 = -1
        L6b:
            if (r10 < 0) goto L72
            java.lang.Object[] r13 = r13.values
            r13 = r13[r10]
            goto L73
        L72:
            r13 = 0
        L73:
            return r13
        L74:
            int r0 = r0 + 8
            int r1 = r1 + r0
            goto L16
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.collection.ScatterMap.get(java.lang.Object):java.lang.Object");
    }

    public final int getCapacity() {
        return this._capacity;
    }

    /* JADX WARN: Code restructure failed: missing block: B:15:0x0068, code lost:
    
        if (((r4 & ((~r4) << 6)) & (-9187201950435737472L)) == 0) goto L21;
     */
    /* JADX WARN: Code restructure failed: missing block: B:16:0x006a, code lost:
    
        r10 = -1;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final V getOrDefault(K r14, V r15) {
        /*
            r13 = this;
            r0 = 0
            if (r14 == 0) goto L8
            int r1 = r14.hashCode()
            goto L9
        L8:
            r1 = r0
        L9:
            r2 = -862048943(0xffffffffcc9e2d51, float:-8.2930312E7)
            int r1 = r1 * r2
            int r2 = r1 << 16
            r1 = r1 ^ r2
            r2 = r1 & 127(0x7f, float:1.78E-43)
            int r3 = r13._capacity
            int r1 = r1 >>> 7
        L16:
            r1 = r1 & r3
            long[] r4 = r13.metadata
            int r5 = r1 >> 3
            r6 = r1 & 7
            int r6 = r6 << 3
            r7 = r4[r5]
            long r7 = r7 >>> r6
            int r5 = r5 + 1
            r4 = r4[r5]
            int r9 = 64 - r6
            long r4 = r4 << r9
            long r9 = (long) r6
            long r9 = -r9
            r6 = 63
            long r9 = r9 >> r6
            long r4 = r4 & r9
            long r4 = r4 | r7
            long r6 = (long) r2
            r8 = 72340172838076673(0x101010101010101, double:7.748604185489348E-304)
            long r6 = r6 * r8
            long r6 = r6 ^ r4
            long r8 = r6 - r8
            long r6 = ~r6
            long r6 = r6 & r8
            r8 = -9187201950435737472(0x8080808080808080, double:-2.937446524422997E-306)
            long r6 = r6 & r8
        L42:
            r10 = 0
            int r12 = (r6 > r10 ? 1 : (r6 == r10 ? 0 : -1))
            if (r12 == 0) goto L61
            int r10 = java.lang.Long.numberOfTrailingZeros(r6)
            int r10 = r10 >> 3
            int r10 = r10 + r1
            r10 = r10 & r3
            java.lang.Object[] r11 = r13.keys
            r11 = r11[r10]
            boolean r11 = kotlin.jvm.internal.n.c(r11, r14)
            if (r11 == 0) goto L5b
            goto L6b
        L5b:
            r10 = 1
            long r10 = r6 - r10
            long r6 = r6 & r10
            goto L42
        L61:
            long r6 = ~r4
            r12 = 6
            long r6 = r6 << r12
            long r4 = r4 & r6
            long r4 = r4 & r8
            int r4 = (r4 > r10 ? 1 : (r4 == r10 ? 0 : -1))
            if (r4 == 0) goto L73
            r10 = -1
        L6b:
            if (r10 < 0) goto L72
            java.lang.Object[] r13 = r13.values
            r13 = r13[r10]
            return r13
        L72:
            return r15
        L73:
            int r0 = r0 + 8
            int r1 = r1 + r0
            goto L16
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.collection.ScatterMap.getOrDefault(java.lang.Object, java.lang.Object):java.lang.Object");
    }

    public final V getOrElse(K k2, Function0 defaultValue) {
        n.g(defaultValue, "defaultValue");
        V v2 = get(k2);
        return v2 == null ? (V) defaultValue.invoke() : v2;
    }

    public final int getSize() {
        return this._size;
    }

    public int hashCode() {
        Object[] objArr = this.keys;
        Object[] objArr2 = this.values;
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
                            Object obj = objArr[i6];
                            Object obj2 = objArr2[i6];
                            iHashCode += (obj2 != null ? obj2.hashCode() : 0) ^ (obj != null ? obj.hashCode() : 0);
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
        return joinToString$default(this, null, null, null, 0, null, null, 63, null);
    }

    public final boolean none() {
        return this._size == 0;
    }

    /* JADX WARN: Removed duplicated region for block: B:26:0x007a A[PHI: r8
      0x007a: PHI (r8v2 int) = (r8v1 int), (r8v3 int) binds: [B:10:0x0031, B:25:0x0078] A[DONT_GENERATE, DONT_INLINE]] */
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
            java.lang.Object[] r2 = r0.keys
            java.lang.Object[] r3 = r0.values
            long[] r4 = r0.metadata
            int r5 = r4.length
            int r5 = r5 + (-2)
            if (r5 < 0) goto L7f
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
            if (r11 == 0) goto L7a
            int r11 = r7 - r5
            int r11 = ~r11
            int r11 = r11 >>> 31
            r12 = 8
            int r11 = 8 - r11
            r13 = r6
        L3d:
            if (r13 >= r11) goto L78
            r14 = 255(0xff, double:1.26E-321)
            long r14 = r14 & r9
            r16 = 128(0x80, double:6.32E-322)
            int r14 = (r14 > r16 ? 1 : (r14 == r16 ? 0 : -1))
            if (r14 >= 0) goto L74
            int r14 = r7 << 3
            int r14 = r14 + r13
            r15 = r2[r14]
            r14 = r3[r14]
            java.lang.String r16 = "(this)"
            if (r15 != r0) goto L55
            r15 = r16
        L55:
            r1.append(r15)
            java.lang.String r15 = "="
            r1.append(r15)
            if (r14 != r0) goto L61
            r14 = r16
        L61:
            r1.append(r14)
            int r8 = r8 + 1
            int r14 = r0._size
            if (r8 >= r14) goto L74
            r14 = 44
            r1.append(r14)
            r14 = 32
            r1.append(r14)
        L74:
            long r9 = r9 >> r12
            int r13 = r13 + 1
            goto L3d
        L78:
            if (r11 != r12) goto L7f
        L7a:
            if (r7 == r5) goto L7f
            int r7 = r7 + 1
            goto L23
        L7f:
            r0 = 125(0x7d, float:1.75E-43)
            r1.append(r0)
            java.lang.String r0 = r1.toString()
            java.lang.String r1 = "s.append('}').toString()"
            kotlin.jvm.internal.n.f(r0, r1)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.collection.ScatterMap.toString():java.lang.String");
    }

    private ScatterMap() {
        this.metadata = ScatterMapKt.EmptyGroup;
        Object[] objArr = ContainerHelpersKt.EMPTY_OBJECTS;
        this.keys = objArr;
        this.values = objArr;
    }

    /* JADX WARN: Removed duplicated region for block: B:17:0x0052  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final boolean any(kotlin.jvm.functions.Function2 r15) {
        /*
            r14 = this;
            java.lang.String r0 = "predicate"
            kotlin.jvm.internal.n.g(r15, r0)
            java.lang.Object[] r0 = r14.keys
            java.lang.Object[] r1 = r14.values
            long[] r14 = r14.metadata
            int r2 = r14.length
            int r2 = r2 + (-2)
            r3 = 0
            if (r2 < 0) goto L57
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
            if (r7 == 0) goto L52
            int r7 = r4 - r2
            int r7 = ~r7
            int r7 = r7 >>> 31
            r8 = 8
            int r7 = 8 - r7
            r9 = r3
        L2c:
            if (r9 >= r7) goto L50
            r10 = 255(0xff, double:1.26E-321)
            long r10 = r10 & r5
            r12 = 128(0x80, double:6.32E-322)
            int r10 = (r10 > r12 ? 1 : (r10 == r12 ? 0 : -1))
            if (r10 >= 0) goto L4c
            int r10 = r4 << 3
            int r10 = r10 + r9
            r11 = r0[r10]
            r10 = r1[r10]
            java.lang.Object r10 = r15.invoke(r11, r10)
            java.lang.Boolean r10 = (java.lang.Boolean) r10
            boolean r10 = r10.booleanValue()
            if (r10 == 0) goto L4c
            r14 = 1
            return r14
        L4c:
            long r5 = r5 >> r8
            int r9 = r9 + 1
            goto L2c
        L50:
            if (r7 != r8) goto L57
        L52:
            if (r4 == r2) goto L57
            int r4 = r4 + 1
            goto L12
        L57:
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.collection.ScatterMap.any(kotlin.jvm.functions.Function2):boolean");
    }

    public final int count(Function2 predicate) {
        n.g(predicate, "predicate");
        Object[] objArr = this.keys;
        Object[] objArr2 = this.values;
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
                            if (((Boolean) predicate.invoke(objArr[i7], objArr2[i7])).booleanValue()) {
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
        return joinToString$default(this, separator, null, null, 0, null, null, 62, null);
    }

    public final String joinToString(CharSequence separator, CharSequence prefix) {
        n.g(separator, "separator");
        n.g(prefix, "prefix");
        return joinToString$default(this, separator, prefix, null, 0, null, null, 60, null);
    }

    public final String joinToString(CharSequence separator, CharSequence prefix, CharSequence postfix) {
        n.g(separator, "separator");
        n.g(prefix, "prefix");
        n.g(postfix, "postfix");
        return joinToString$default(this, separator, prefix, postfix, 0, null, null, 56, null);
    }

    public final String joinToString(CharSequence separator, CharSequence prefix, CharSequence postfix, int i2) {
        n.g(separator, "separator");
        n.g(prefix, "prefix");
        n.g(postfix, "postfix");
        return joinToString$default(this, separator, prefix, postfix, i2, null, null, 48, null);
    }

    public final String joinToString(CharSequence separator, CharSequence prefix, CharSequence postfix, int i2, CharSequence truncated) {
        n.g(separator, "separator");
        n.g(prefix, "prefix");
        n.g(postfix, "postfix");
        n.g(truncated, "truncated");
        return joinToString$default(this, separator, prefix, postfix, i2, truncated, null, 32, null);
    }

    public final String joinToString(CharSequence separator, CharSequence prefix, CharSequence postfix, int i2, CharSequence truncated, Function2 function2) {
        long[] jArr;
        Object[] objArr;
        long[] jArr2;
        Object[] objArr2;
        int i3;
        n.g(separator, "separator");
        n.g(prefix, "prefix");
        n.g(postfix, "postfix");
        n.g(truncated, "truncated");
        StringBuilder sb = new StringBuilder();
        sb.append(prefix);
        Object[] objArr3 = this.keys;
        Object[] objArr4 = this.values;
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
                            Object obj = objArr3[i9];
                            jArr2 = jArr3;
                            Object obj2 = objArr4[i9];
                            objArr2 = objArr3;
                            if (i5 == i2) {
                                sb.append(truncated);
                                break loop0;
                            }
                            if (i5 != 0) {
                                sb.append(separator);
                            }
                            if (function2 == null) {
                                sb.append(obj);
                                sb.append('=');
                                sb.append(obj2);
                            } else {
                                sb.append((CharSequence) function2.invoke(obj, obj2));
                            }
                            i5++;
                            i3 = 8;
                        } else {
                            jArr2 = jArr3;
                            objArr2 = objArr3;
                            i3 = i6;
                        }
                        j2 >>= i3;
                        i8++;
                        i6 = i3;
                        objArr3 = objArr2;
                        jArr3 = jArr2;
                    }
                    jArr = jArr3;
                    objArr = objArr3;
                    if (i7 != i6) {
                        break;
                    }
                } else {
                    jArr = jArr3;
                    objArr = objArr3;
                }
                if (i4 == length) {
                    break;
                }
                i4++;
                objArr3 = objArr;
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
}
