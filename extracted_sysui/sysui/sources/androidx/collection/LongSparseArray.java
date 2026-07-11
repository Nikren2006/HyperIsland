package androidx.collection;

import I0.AbstractC0180h;
import androidx.collection.internal.ContainerHelpersKt;
import java.util.Arrays;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.n;

/* JADX INFO: loaded from: classes.dex */
public class LongSparseArray<E> implements Cloneable {
    public /* synthetic */ boolean garbage;
    public /* synthetic */ long[] keys;
    public /* synthetic */ int size;
    public /* synthetic */ Object[] values;

    public LongSparseArray() {
        this(0, 1, null);
    }

    public void append(long j2, E e2) {
        int i2 = this.size;
        if (i2 != 0 && j2 <= this.keys[i2 - 1]) {
            put(j2, e2);
            return;
        }
        if (this.garbage) {
            long[] jArr = this.keys;
            if (i2 >= jArr.length) {
                Object[] objArr = this.values;
                int i3 = 0;
                for (int i4 = 0; i4 < i2; i4++) {
                    Object obj = objArr[i4];
                    if (obj != LongSparseArrayKt.DELETED) {
                        if (i4 != i3) {
                            jArr[i3] = jArr[i4];
                            objArr[i3] = obj;
                            objArr[i4] = null;
                        }
                        i3++;
                    }
                }
                this.garbage = false;
                this.size = i3;
            }
        }
        int i5 = this.size;
        if (i5 >= this.keys.length) {
            int iIdealLongArraySize = ContainerHelpersKt.idealLongArraySize(i5 + 1);
            long[] jArrCopyOf = Arrays.copyOf(this.keys, iIdealLongArraySize);
            n.f(jArrCopyOf, "copyOf(this, newSize)");
            this.keys = jArrCopyOf;
            Object[] objArrCopyOf = Arrays.copyOf(this.values, iIdealLongArraySize);
            n.f(objArrCopyOf, "copyOf(this, newSize)");
            this.values = objArrCopyOf;
        }
        this.keys[i5] = j2;
        this.values[i5] = e2;
        this.size = i5 + 1;
    }

    public void clear() {
        int i2 = this.size;
        Object[] objArr = this.values;
        for (int i3 = 0; i3 < i2; i3++) {
            objArr[i3] = null;
        }
        this.size = 0;
        this.garbage = false;
    }

    public boolean containsKey(long j2) {
        return indexOfKey(j2) >= 0;
    }

    public boolean containsValue(E e2) {
        return indexOfValue(e2) >= 0;
    }

    public void delete(long j2) {
        int iBinarySearch = ContainerHelpersKt.binarySearch(this.keys, this.size, j2);
        if (iBinarySearch < 0 || this.values[iBinarySearch] == LongSparseArrayKt.DELETED) {
            return;
        }
        this.values[iBinarySearch] = LongSparseArrayKt.DELETED;
        this.garbage = true;
    }

    public E get(long j2) {
        int iBinarySearch = ContainerHelpersKt.binarySearch(this.keys, this.size, j2);
        if (iBinarySearch < 0 || this.values[iBinarySearch] == LongSparseArrayKt.DELETED) {
            return null;
        }
        return (E) this.values[iBinarySearch];
    }

    public int indexOfKey(long j2) {
        if (this.garbage) {
            int i2 = this.size;
            long[] jArr = this.keys;
            Object[] objArr = this.values;
            int i3 = 0;
            for (int i4 = 0; i4 < i2; i4++) {
                Object obj = objArr[i4];
                if (obj != LongSparseArrayKt.DELETED) {
                    if (i4 != i3) {
                        jArr[i3] = jArr[i4];
                        objArr[i3] = obj;
                        objArr[i4] = null;
                    }
                    i3++;
                }
            }
            this.garbage = false;
            this.size = i3;
        }
        return ContainerHelpersKt.binarySearch(this.keys, this.size, j2);
    }

    public int indexOfValue(E e2) {
        if (this.garbage) {
            int i2 = this.size;
            long[] jArr = this.keys;
            Object[] objArr = this.values;
            int i3 = 0;
            for (int i4 = 0; i4 < i2; i4++) {
                Object obj = objArr[i4];
                if (obj != LongSparseArrayKt.DELETED) {
                    if (i4 != i3) {
                        jArr[i3] = jArr[i4];
                        objArr[i3] = obj;
                        objArr[i4] = null;
                    }
                    i3++;
                }
            }
            this.garbage = false;
            this.size = i3;
        }
        int i5 = this.size;
        for (int i6 = 0; i6 < i5; i6++) {
            if (this.values[i6] == e2) {
                return i6;
            }
        }
        return -1;
    }

    public boolean isEmpty() {
        return size() == 0;
    }

    public long keyAt(int i2) {
        int i3;
        if (i2 < 0 || i2 >= (i3 = this.size)) {
            throw new IllegalArgumentException(("Expected index to be within 0..size()-1, but was " + i2).toString());
        }
        if (this.garbage) {
            long[] jArr = this.keys;
            Object[] objArr = this.values;
            int i4 = 0;
            for (int i5 = 0; i5 < i3; i5++) {
                Object obj = objArr[i5];
                if (obj != LongSparseArrayKt.DELETED) {
                    if (i5 != i4) {
                        jArr[i4] = jArr[i5];
                        objArr[i4] = obj;
                        objArr[i5] = null;
                    }
                    i4++;
                }
            }
            this.garbage = false;
            this.size = i4;
        }
        return this.keys[i2];
    }

    public void put(long j2, E e2) {
        int iBinarySearch = ContainerHelpersKt.binarySearch(this.keys, this.size, j2);
        if (iBinarySearch >= 0) {
            this.values[iBinarySearch] = e2;
            return;
        }
        int i2 = ~iBinarySearch;
        if (i2 < this.size && this.values[i2] == LongSparseArrayKt.DELETED) {
            this.keys[i2] = j2;
            this.values[i2] = e2;
            return;
        }
        if (this.garbage) {
            int i3 = this.size;
            long[] jArr = this.keys;
            if (i3 >= jArr.length) {
                Object[] objArr = this.values;
                int i4 = 0;
                for (int i5 = 0; i5 < i3; i5++) {
                    Object obj = objArr[i5];
                    if (obj != LongSparseArrayKt.DELETED) {
                        if (i5 != i4) {
                            jArr[i4] = jArr[i5];
                            objArr[i4] = obj;
                            objArr[i5] = null;
                        }
                        i4++;
                    }
                }
                this.garbage = false;
                this.size = i4;
                i2 = ~ContainerHelpersKt.binarySearch(this.keys, i4, j2);
            }
        }
        int i6 = this.size;
        if (i6 >= this.keys.length) {
            int iIdealLongArraySize = ContainerHelpersKt.idealLongArraySize(i6 + 1);
            long[] jArrCopyOf = Arrays.copyOf(this.keys, iIdealLongArraySize);
            n.f(jArrCopyOf, "copyOf(this, newSize)");
            this.keys = jArrCopyOf;
            Object[] objArrCopyOf = Arrays.copyOf(this.values, iIdealLongArraySize);
            n.f(objArrCopyOf, "copyOf(this, newSize)");
            this.values = objArrCopyOf;
        }
        int i7 = this.size;
        if (i7 - i2 != 0) {
            long[] jArr2 = this.keys;
            int i8 = i2 + 1;
            AbstractC0180h.f(jArr2, jArr2, i8, i2, i7);
            Object[] objArr2 = this.values;
            AbstractC0180h.g(objArr2, objArr2, i8, i2, this.size);
        }
        this.keys[i2] = j2;
        this.values[i2] = e2;
        this.size++;
    }

    public void putAll(LongSparseArray<? extends E> other) {
        n.g(other, "other");
        int size = other.size();
        for (int i2 = 0; i2 < size; i2++) {
            put(other.keyAt(i2), other.valueAt(i2));
        }
    }

    public E putIfAbsent(long j2, E e2) {
        E e3 = get(j2);
        if (e3 == null) {
            put(j2, e2);
        }
        return e3;
    }

    public void remove(long j2) {
        int iBinarySearch = ContainerHelpersKt.binarySearch(this.keys, this.size, j2);
        if (iBinarySearch < 0 || this.values[iBinarySearch] == LongSparseArrayKt.DELETED) {
            return;
        }
        this.values[iBinarySearch] = LongSparseArrayKt.DELETED;
        this.garbage = true;
    }

    public void removeAt(int i2) {
        if (this.values[i2] != LongSparseArrayKt.DELETED) {
            this.values[i2] = LongSparseArrayKt.DELETED;
            this.garbage = true;
        }
    }

    public E replace(long j2, E e2) {
        int iIndexOfKey = indexOfKey(j2);
        if (iIndexOfKey < 0) {
            return null;
        }
        Object[] objArr = this.values;
        E e3 = (E) objArr[iIndexOfKey];
        objArr[iIndexOfKey] = e2;
        return e3;
    }

    public void setValueAt(int i2, E e2) {
        int i3;
        if (i2 < 0 || i2 >= (i3 = this.size)) {
            throw new IllegalArgumentException(("Expected index to be within 0..size()-1, but was " + i2).toString());
        }
        if (this.garbage) {
            long[] jArr = this.keys;
            Object[] objArr = this.values;
            int i4 = 0;
            for (int i5 = 0; i5 < i3; i5++) {
                Object obj = objArr[i5];
                if (obj != LongSparseArrayKt.DELETED) {
                    if (i5 != i4) {
                        jArr[i4] = jArr[i5];
                        objArr[i4] = obj;
                        objArr[i5] = null;
                    }
                    i4++;
                }
            }
            this.garbage = false;
            this.size = i4;
        }
        this.values[i2] = e2;
    }

    public int size() {
        if (this.garbage) {
            int i2 = this.size;
            long[] jArr = this.keys;
            Object[] objArr = this.values;
            int i3 = 0;
            for (int i4 = 0; i4 < i2; i4++) {
                Object obj = objArr[i4];
                if (obj != LongSparseArrayKt.DELETED) {
                    if (i4 != i3) {
                        jArr[i3] = jArr[i4];
                        objArr[i3] = obj;
                        objArr[i4] = null;
                    }
                    i3++;
                }
            }
            this.garbage = false;
            this.size = i3;
        }
        return this.size;
    }

    public String toString() {
        if (size() <= 0) {
            return "{}";
        }
        StringBuilder sb = new StringBuilder(this.size * 28);
        sb.append('{');
        int i2 = this.size;
        for (int i3 = 0; i3 < i2; i3++) {
            if (i3 > 0) {
                sb.append(", ");
            }
            sb.append(keyAt(i3));
            sb.append('=');
            E eValueAt = valueAt(i3);
            if (eValueAt != sb) {
                sb.append(eValueAt);
            } else {
                sb.append("(this Map)");
            }
        }
        sb.append('}');
        String string = sb.toString();
        n.f(string, "StringBuilder(capacity).…builderAction).toString()");
        return string;
    }

    public E valueAt(int i2) {
        int i3;
        if (i2 < 0 || i2 >= (i3 = this.size)) {
            throw new IllegalArgumentException(("Expected index to be within 0..size()-1, but was " + i2).toString());
        }
        if (this.garbage) {
            long[] jArr = this.keys;
            Object[] objArr = this.values;
            int i4 = 0;
            for (int i5 = 0; i5 < i3; i5++) {
                Object obj = objArr[i5];
                if (obj != LongSparseArrayKt.DELETED) {
                    if (i5 != i4) {
                        jArr[i4] = jArr[i5];
                        objArr[i4] = obj;
                        objArr[i5] = null;
                    }
                    i4++;
                }
            }
            this.garbage = false;
            this.size = i4;
        }
        return (E) this.values[i2];
    }

    public LongSparseArray(int i2) {
        if (i2 == 0) {
            this.keys = ContainerHelpersKt.EMPTY_LONGS;
            this.values = ContainerHelpersKt.EMPTY_OBJECTS;
        } else {
            int iIdealLongArraySize = ContainerHelpersKt.idealLongArraySize(i2);
            this.keys = new long[iIdealLongArraySize];
            this.values = new Object[iIdealLongArraySize];
        }
    }

    /* JADX INFO: renamed from: clone, reason: merged with bridge method [inline-methods] */
    public LongSparseArray<E> m30clone() throws CloneNotSupportedException {
        Object objClone = super.clone();
        n.e(objClone, "null cannot be cast to non-null type androidx.collection.LongSparseArray<E of androidx.collection.LongSparseArray>");
        LongSparseArray<E> longSparseArray = (LongSparseArray) objClone;
        longSparseArray.keys = (long[]) this.keys.clone();
        longSparseArray.values = (Object[]) this.values.clone();
        return longSparseArray;
    }

    public E get(long j2, E e2) {
        int iBinarySearch = ContainerHelpersKt.binarySearch(this.keys, this.size, j2);
        return (iBinarySearch < 0 || this.values[iBinarySearch] == LongSparseArrayKt.DELETED) ? e2 : (E) this.values[iBinarySearch];
    }

    public boolean replace(long j2, E e2, E e3) {
        int iIndexOfKey = indexOfKey(j2);
        if (iIndexOfKey < 0 || !n.c(this.values[iIndexOfKey], e2)) {
            return false;
        }
        this.values[iIndexOfKey] = e3;
        return true;
    }

    public boolean remove(long j2, E e2) {
        int iIndexOfKey = indexOfKey(j2);
        if (iIndexOfKey < 0 || !n.c(e2, valueAt(iIndexOfKey))) {
            return false;
        }
        removeAt(iIndexOfKey);
        return true;
    }

    public /* synthetic */ LongSparseArray(int i2, int i3, DefaultConstructorMarker defaultConstructorMarker) {
        this((i3 & 1) != 0 ? 10 : i2);
    }
}
