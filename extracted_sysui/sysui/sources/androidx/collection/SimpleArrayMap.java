package androidx.collection;

import I0.AbstractC0180h;
import androidx.collection.internal.ContainerHelpersKt;
import java.util.Arrays;
import java.util.ConcurrentModificationException;
import java.util.Map;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.n;

/* JADX INFO: loaded from: classes.dex */
public class SimpleArrayMap<K, V> {
    private Object[] array;
    private int[] hashes;
    private int size;

    public SimpleArrayMap() {
        this(0, 1, null);
    }

    /* JADX WARN: Multi-variable type inference failed */
    private final <T extends V> T getOrDefaultInternal(Object obj, T t2) {
        int iIndexOfKey = indexOfKey(obj);
        return iIndexOfKey >= 0 ? (T) this.array[(iIndexOfKey << 1) + 1] : t2;
    }

    private final int indexOf(K k2, int i2) {
        int i3 = this.size;
        if (i3 == 0) {
            return -1;
        }
        int iBinarySearch = ContainerHelpersKt.binarySearch(this.hashes, i3, i2);
        if (iBinarySearch < 0 || n.c(k2, this.array[iBinarySearch << 1])) {
            return iBinarySearch;
        }
        int i4 = iBinarySearch + 1;
        while (i4 < i3 && this.hashes[i4] == i2) {
            if (n.c(k2, this.array[i4 << 1])) {
                return i4;
            }
            i4++;
        }
        for (int i5 = iBinarySearch - 1; i5 >= 0 && this.hashes[i5] == i2; i5--) {
            if (n.c(k2, this.array[i5 << 1])) {
                return i5;
            }
        }
        return ~i4;
    }

    private final int indexOfNull() {
        int i2 = this.size;
        if (i2 == 0) {
            return -1;
        }
        int iBinarySearch = ContainerHelpersKt.binarySearch(this.hashes, i2, 0);
        if (iBinarySearch < 0 || this.array[iBinarySearch << 1] == null) {
            return iBinarySearch;
        }
        int i3 = iBinarySearch + 1;
        while (i3 < i2 && this.hashes[i3] == 0) {
            if (this.array[i3 << 1] == null) {
                return i3;
            }
            i3++;
        }
        for (int i4 = iBinarySearch - 1; i4 >= 0 && this.hashes[i4] == 0; i4--) {
            if (this.array[i4 << 1] == null) {
                return i4;
            }
        }
        return ~i3;
    }

    public final int __restricted$indexOfValue(V v2) {
        int i2 = this.size * 2;
        Object[] objArr = this.array;
        if (v2 == null) {
            for (int i3 = 1; i3 < i2; i3 += 2) {
                if (objArr[i3] == null) {
                    return i3 >> 1;
                }
            }
            return -1;
        }
        for (int i4 = 1; i4 < i2; i4 += 2) {
            if (n.c(v2, objArr[i4])) {
                return i4 >> 1;
            }
        }
        return -1;
    }

    public void clear() {
        if (this.size > 0) {
            this.hashes = ContainerHelpersKt.EMPTY_INTS;
            this.array = ContainerHelpersKt.EMPTY_OBJECTS;
            this.size = 0;
        }
        if (this.size > 0) {
            throw new ConcurrentModificationException();
        }
    }

    public boolean containsKey(K k2) {
        return indexOfKey(k2) >= 0;
    }

    public boolean containsValue(V v2) {
        return __restricted$indexOfValue(v2) >= 0;
    }

    public void ensureCapacity(int i2) {
        int i3 = this.size;
        int[] iArr = this.hashes;
        if (iArr.length < i2) {
            int[] iArrCopyOf = Arrays.copyOf(iArr, i2);
            n.f(iArrCopyOf, "copyOf(this, newSize)");
            this.hashes = iArrCopyOf;
            Object[] objArrCopyOf = Arrays.copyOf(this.array, i2 * 2);
            n.f(objArrCopyOf, "copyOf(this, newSize)");
            this.array = objArrCopyOf;
        }
        if (this.size != i3) {
            throw new ConcurrentModificationException();
        }
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        try {
            if (obj instanceof SimpleArrayMap) {
                if (size() != ((SimpleArrayMap) obj).size()) {
                    return false;
                }
                SimpleArrayMap simpleArrayMap = (SimpleArrayMap) obj;
                int i2 = this.size;
                for (int i3 = 0; i3 < i2; i3++) {
                    K kKeyAt = keyAt(i3);
                    V vValueAt = valueAt(i3);
                    Object obj2 = simpleArrayMap.get(kKeyAt);
                    if (vValueAt == null) {
                        if (obj2 != null || !simpleArrayMap.containsKey(kKeyAt)) {
                            return false;
                        }
                    } else if (!n.c(vValueAt, obj2)) {
                        return false;
                    }
                }
                return true;
            }
            if (!(obj instanceof Map) || size() != ((Map) obj).size()) {
                return false;
            }
            int i4 = this.size;
            for (int i5 = 0; i5 < i4; i5++) {
                K kKeyAt2 = keyAt(i5);
                V vValueAt2 = valueAt(i5);
                Object obj3 = ((Map) obj).get(kKeyAt2);
                if (vValueAt2 == null) {
                    if (obj3 != null || !((Map) obj).containsKey(kKeyAt2)) {
                        return false;
                    }
                } else if (!n.c(vValueAt2, obj3)) {
                    return false;
                }
            }
            return true;
        } catch (ClassCastException | NullPointerException unused) {
        }
        return false;
    }

    public V get(K k2) {
        int iIndexOfKey = indexOfKey(k2);
        if (iIndexOfKey >= 0) {
            return (V) this.array[(iIndexOfKey << 1) + 1];
        }
        return null;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public V getOrDefault(Object obj, V v2) {
        int iIndexOfKey = indexOfKey(obj);
        return iIndexOfKey >= 0 ? (V) this.array[(iIndexOfKey << 1) + 1] : v2;
    }

    public int hashCode() {
        int[] iArr = this.hashes;
        Object[] objArr = this.array;
        int i2 = this.size;
        int i3 = 1;
        int i4 = 0;
        int iHashCode = 0;
        while (i4 < i2) {
            Object obj = objArr[i3];
            iHashCode += (obj != null ? obj.hashCode() : 0) ^ iArr[i4];
            i4++;
            i3 += 2;
        }
        return iHashCode;
    }

    public int indexOfKey(K k2) {
        return k2 == null ? indexOfNull() : indexOf(k2, k2.hashCode());
    }

    public boolean isEmpty() {
        return this.size <= 0;
    }

    public K keyAt(int i2) {
        if (i2 >= 0 && i2 < this.size) {
            return (K) this.array[i2 << 1];
        }
        throw new IllegalArgumentException(("Expected index to be within 0..size()-1, but was " + i2).toString());
    }

    public V put(K k2, V v2) {
        int i2 = this.size;
        int iHashCode = k2 != null ? k2.hashCode() : 0;
        int iIndexOf = k2 != null ? indexOf(k2, iHashCode) : indexOfNull();
        if (iIndexOf >= 0) {
            int i3 = (iIndexOf << 1) + 1;
            Object[] objArr = this.array;
            V v3 = (V) objArr[i3];
            objArr[i3] = v2;
            return v3;
        }
        int i4 = ~iIndexOf;
        int[] iArr = this.hashes;
        if (i2 >= iArr.length) {
            int i5 = 8;
            if (i2 >= 8) {
                i5 = (i2 >> 1) + i2;
            } else if (i2 < 4) {
                i5 = 4;
            }
            int[] iArrCopyOf = Arrays.copyOf(iArr, i5);
            n.f(iArrCopyOf, "copyOf(this, newSize)");
            this.hashes = iArrCopyOf;
            Object[] objArrCopyOf = Arrays.copyOf(this.array, i5 << 1);
            n.f(objArrCopyOf, "copyOf(this, newSize)");
            this.array = objArrCopyOf;
            if (i2 != this.size) {
                throw new ConcurrentModificationException();
            }
        }
        if (i4 < i2) {
            int[] iArr2 = this.hashes;
            int i6 = i4 + 1;
            AbstractC0180h.e(iArr2, iArr2, i6, i4, i2);
            Object[] objArr2 = this.array;
            AbstractC0180h.g(objArr2, objArr2, i6 << 1, i4 << 1, this.size << 1);
        }
        int i7 = this.size;
        if (i2 == i7) {
            int[] iArr3 = this.hashes;
            if (i4 < iArr3.length) {
                iArr3[i4] = iHashCode;
                Object[] objArr3 = this.array;
                int i8 = i4 << 1;
                objArr3[i8] = k2;
                objArr3[i8 + 1] = v2;
                this.size = i7 + 1;
                return null;
            }
        }
        throw new ConcurrentModificationException();
    }

    public void putAll(SimpleArrayMap<? extends K, ? extends V> map) {
        n.g(map, "map");
        int i2 = map.size;
        ensureCapacity(this.size + i2);
        if (this.size != 0) {
            for (int i3 = 0; i3 < i2; i3++) {
                put(map.keyAt(i3), map.valueAt(i3));
            }
        } else if (i2 > 0) {
            AbstractC0180h.e(map.hashes, this.hashes, 0, 0, i2);
            AbstractC0180h.g(map.array, this.array, 0, 0, i2 << 1);
            this.size = i2;
        }
    }

    public V putIfAbsent(K k2, V v2) {
        V v3 = get(k2);
        return v3 == null ? put(k2, v2) : v3;
    }

    public V remove(K k2) {
        int iIndexOfKey = indexOfKey(k2);
        if (iIndexOfKey >= 0) {
            return removeAt(iIndexOfKey);
        }
        return null;
    }

    public V removeAt(int i2) {
        int i3;
        if (i2 < 0 || i2 >= (i3 = this.size)) {
            throw new IllegalArgumentException(("Expected index to be within 0..size()-1, but was " + i2).toString());
        }
        Object[] objArr = this.array;
        int i4 = i2 << 1;
        V v2 = (V) objArr[i4 + 1];
        if (i3 <= 1) {
            clear();
        } else {
            int i5 = i3 - 1;
            int[] iArr = this.hashes;
            if (iArr.length <= 8 || i3 >= iArr.length / 3) {
                if (i2 < i5) {
                    int i6 = i2 + 1;
                    AbstractC0180h.e(iArr, iArr, i2, i6, i3);
                    Object[] objArr2 = this.array;
                    AbstractC0180h.g(objArr2, objArr2, i4, i6 << 1, i3 << 1);
                }
                Object[] objArr3 = this.array;
                int i7 = i5 << 1;
                objArr3[i7] = null;
                objArr3[i7 + 1] = null;
            } else {
                int i8 = i3 > 8 ? i3 + (i3 >> 1) : 8;
                int[] iArrCopyOf = Arrays.copyOf(iArr, i8);
                n.f(iArrCopyOf, "copyOf(this, newSize)");
                this.hashes = iArrCopyOf;
                Object[] objArrCopyOf = Arrays.copyOf(this.array, i8 << 1);
                n.f(objArrCopyOf, "copyOf(this, newSize)");
                this.array = objArrCopyOf;
                if (i3 != this.size) {
                    throw new ConcurrentModificationException();
                }
                if (i2 > 0) {
                    AbstractC0180h.e(iArr, this.hashes, 0, 0, i2);
                    AbstractC0180h.g(objArr, this.array, 0, 0, i4);
                }
                if (i2 < i5) {
                    int i9 = i2 + 1;
                    AbstractC0180h.e(iArr, this.hashes, i2, i9, i3);
                    AbstractC0180h.g(objArr, this.array, i4, i9 << 1, i3 << 1);
                }
            }
            if (i3 != this.size) {
                throw new ConcurrentModificationException();
            }
            this.size = i5;
        }
        return v2;
    }

    public V replace(K k2, V v2) {
        int iIndexOfKey = indexOfKey(k2);
        if (iIndexOfKey >= 0) {
            return setValueAt(iIndexOfKey, v2);
        }
        return null;
    }

    public V setValueAt(int i2, V v2) {
        if (i2 < 0 || i2 >= this.size) {
            throw new IllegalArgumentException(("Expected index to be within 0..size()-1, but was " + i2).toString());
        }
        int i3 = (i2 << 1) + 1;
        Object[] objArr = this.array;
        V v3 = (V) objArr[i3];
        objArr[i3] = v2;
        return v3;
    }

    public int size() {
        return this.size;
    }

    public String toString() {
        if (isEmpty()) {
            return "{}";
        }
        StringBuilder sb = new StringBuilder(this.size * 28);
        sb.append('{');
        int i2 = this.size;
        for (int i3 = 0; i3 < i2; i3++) {
            if (i3 > 0) {
                sb.append(", ");
            }
            K kKeyAt = keyAt(i3);
            if (kKeyAt != sb) {
                sb.append(kKeyAt);
            } else {
                sb.append("(this Map)");
            }
            sb.append('=');
            V vValueAt = valueAt(i3);
            if (vValueAt != sb) {
                sb.append(vValueAt);
            } else {
                sb.append("(this Map)");
            }
        }
        sb.append('}');
        String string = sb.toString();
        n.f(string, "StringBuilder(capacity).…builderAction).toString()");
        return string;
    }

    public V valueAt(int i2) {
        if (i2 >= 0 && i2 < this.size) {
            return (V) this.array[(i2 << 1) + 1];
        }
        throw new IllegalArgumentException(("Expected index to be within 0..size()-1, but was " + i2).toString());
    }

    public SimpleArrayMap(int i2) {
        this.hashes = i2 == 0 ? ContainerHelpersKt.EMPTY_INTS : new int[i2];
        this.array = i2 == 0 ? ContainerHelpersKt.EMPTY_OBJECTS : new Object[i2 << 1];
    }

    public boolean remove(K k2, V v2) {
        int iIndexOfKey = indexOfKey(k2);
        if (iIndexOfKey < 0 || !n.c(v2, valueAt(iIndexOfKey))) {
            return false;
        }
        removeAt(iIndexOfKey);
        return true;
    }

    public boolean replace(K k2, V v2, V v3) {
        int iIndexOfKey = indexOfKey(k2);
        if (iIndexOfKey < 0 || !n.c(v2, valueAt(iIndexOfKey))) {
            return false;
        }
        setValueAt(iIndexOfKey, v3);
        return true;
    }

    public /* synthetic */ SimpleArrayMap(int i2, int i3, DefaultConstructorMarker defaultConstructorMarker) {
        this((i3 & 1) != 0 ? 0 : i2);
    }

    public SimpleArrayMap(SimpleArrayMap<? extends K, ? extends V> simpleArrayMap) {
        this(0, 1, null);
        if (simpleArrayMap != null) {
            putAll(simpleArrayMap);
        }
    }
}
