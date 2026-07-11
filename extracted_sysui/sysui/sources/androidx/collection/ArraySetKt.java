package androidx.collection;

import I0.AbstractC0180h;
import I0.u;
import androidx.collection.internal.ContainerHelpersKt;
import java.util.Collection;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.Set;
import kotlin.jvm.internal.n;

/* JADX INFO: loaded from: classes.dex */
public final class ArraySetKt {
    public static final int ARRAY_SET_BASE_SIZE = 4;

    public static final <E> void addAllInternal(ArraySet<E> arraySet, ArraySet<? extends E> array) {
        n.g(arraySet, "<this>");
        n.g(array, "array");
        int i2 = array.get_size$collection();
        arraySet.ensureCapacity(arraySet.get_size$collection() + i2);
        if (arraySet.get_size$collection() != 0) {
            for (int i3 = 0; i3 < i2; i3++) {
                arraySet.add(array.valueAt(i3));
            }
            return;
        }
        if (i2 > 0) {
            AbstractC0180h.i(array.getHashes$collection(), arraySet.getHashes$collection(), 0, 0, i2, 6, null);
            AbstractC0180h.k(array.getArray$collection(), arraySet.getArray$collection(), 0, 0, i2, 6, null);
            if (arraySet.get_size$collection() != 0) {
                throw new ConcurrentModificationException();
            }
            arraySet.set_size$collection(i2);
        }
    }

    public static final <E> boolean addInternal(ArraySet<E> arraySet, E e2) {
        int i2;
        int iIndexOf;
        n.g(arraySet, "<this>");
        int i3 = arraySet.get_size$collection();
        if (e2 == null) {
            iIndexOf = indexOfNull(arraySet);
            i2 = 0;
        } else {
            int iHashCode = e2.hashCode();
            i2 = iHashCode;
            iIndexOf = indexOf(arraySet, e2, iHashCode);
        }
        if (iIndexOf >= 0) {
            return false;
        }
        int i4 = ~iIndexOf;
        if (i3 >= arraySet.getHashes$collection().length) {
            int i5 = 8;
            if (i3 >= 8) {
                i5 = (i3 >> 1) + i3;
            } else if (i3 < 4) {
                i5 = 4;
            }
            int[] hashes$collection = arraySet.getHashes$collection();
            Object[] array$collection = arraySet.getArray$collection();
            allocArrays(arraySet, i5);
            if (i3 != arraySet.get_size$collection()) {
                throw new ConcurrentModificationException();
            }
            if (!(arraySet.getHashes$collection().length == 0)) {
                AbstractC0180h.i(hashes$collection, arraySet.getHashes$collection(), 0, 0, hashes$collection.length, 6, null);
                AbstractC0180h.k(array$collection, arraySet.getArray$collection(), 0, 0, array$collection.length, 6, null);
            }
        }
        if (i4 < i3) {
            int i6 = i4 + 1;
            AbstractC0180h.e(arraySet.getHashes$collection(), arraySet.getHashes$collection(), i6, i4, i3);
            AbstractC0180h.g(arraySet.getArray$collection(), arraySet.getArray$collection(), i6, i4, i3);
        }
        if (i3 != arraySet.get_size$collection() || i4 >= arraySet.getHashes$collection().length) {
            throw new ConcurrentModificationException();
        }
        arraySet.getHashes$collection()[i4] = i2;
        arraySet.getArray$collection()[i4] = e2;
        arraySet.set_size$collection(arraySet.get_size$collection() + 1);
        return true;
    }

    public static final <E> void allocArrays(ArraySet<E> arraySet, int i2) {
        n.g(arraySet, "<this>");
        arraySet.setHashes$collection(new int[i2]);
        arraySet.setArray$collection(new Object[i2]);
    }

    public static final <T> ArraySet<T> arraySetOf() {
        return new ArraySet<>(0, 1, null);
    }

    public static final <E> int binarySearchInternal(ArraySet<E> arraySet, int i2) {
        n.g(arraySet, "<this>");
        try {
            return ContainerHelpersKt.binarySearch(arraySet.getHashes$collection(), arraySet.get_size$collection(), i2);
        } catch (IndexOutOfBoundsException unused) {
            throw new ConcurrentModificationException();
        }
    }

    public static final <E> void clearInternal(ArraySet<E> arraySet) {
        n.g(arraySet, "<this>");
        if (arraySet.get_size$collection() != 0) {
            arraySet.setHashes$collection(ContainerHelpersKt.EMPTY_INTS);
            arraySet.setArray$collection(ContainerHelpersKt.EMPTY_OBJECTS);
            arraySet.set_size$collection(0);
        }
        if (arraySet.get_size$collection() != 0) {
            throw new ConcurrentModificationException();
        }
    }

    public static final <E> boolean containsAllInternal(ArraySet<E> arraySet, Collection<? extends E> elements) {
        n.g(arraySet, "<this>");
        n.g(elements, "elements");
        Iterator<? extends E> it = elements.iterator();
        while (it.hasNext()) {
            if (!arraySet.contains(it.next())) {
                return false;
            }
        }
        return true;
    }

    public static final <E> boolean containsInternal(ArraySet<E> arraySet, E e2) {
        n.g(arraySet, "<this>");
        return arraySet.indexOf(e2) >= 0;
    }

    public static final <E> void ensureCapacityInternal(ArraySet<E> arraySet, int i2) {
        n.g(arraySet, "<this>");
        int i3 = arraySet.get_size$collection();
        if (arraySet.getHashes$collection().length < i2) {
            int[] hashes$collection = arraySet.getHashes$collection();
            Object[] array$collection = arraySet.getArray$collection();
            allocArrays(arraySet, i2);
            if (arraySet.get_size$collection() > 0) {
                AbstractC0180h.i(hashes$collection, arraySet.getHashes$collection(), 0, 0, arraySet.get_size$collection(), 6, null);
                AbstractC0180h.k(array$collection, arraySet.getArray$collection(), 0, 0, arraySet.get_size$collection(), 6, null);
            }
        }
        if (arraySet.get_size$collection() != i3) {
            throw new ConcurrentModificationException();
        }
    }

    public static final <E> boolean equalsInternal(ArraySet<E> arraySet, Object obj) {
        n.g(arraySet, "<this>");
        if (arraySet == obj) {
            return true;
        }
        if (!(obj instanceof Set) || arraySet.size() != ((Set) obj).size()) {
            return false;
        }
        try {
            int i2 = arraySet.get_size$collection();
            for (int i3 = 0; i3 < i2; i3++) {
                if (!((Set) obj).contains(arraySet.valueAt(i3))) {
                    return false;
                }
            }
            return true;
        } catch (ClassCastException | NullPointerException unused) {
            return false;
        }
    }

    public static final <E> int hashCodeInternal(ArraySet<E> arraySet) {
        n.g(arraySet, "<this>");
        int[] hashes$collection = arraySet.getHashes$collection();
        int i2 = arraySet.get_size$collection();
        int i3 = 0;
        for (int i4 = 0; i4 < i2; i4++) {
            i3 += hashes$collection[i4];
        }
        return i3;
    }

    public static final <E> int indexOf(ArraySet<E> arraySet, Object obj, int i2) {
        n.g(arraySet, "<this>");
        int i3 = arraySet.get_size$collection();
        if (i3 == 0) {
            return -1;
        }
        int iBinarySearchInternal = binarySearchInternal(arraySet, i2);
        if (iBinarySearchInternal < 0 || n.c(obj, arraySet.getArray$collection()[iBinarySearchInternal])) {
            return iBinarySearchInternal;
        }
        int i4 = iBinarySearchInternal + 1;
        while (i4 < i3 && arraySet.getHashes$collection()[i4] == i2) {
            if (n.c(obj, arraySet.getArray$collection()[i4])) {
                return i4;
            }
            i4++;
        }
        for (int i5 = iBinarySearchInternal - 1; i5 >= 0 && arraySet.getHashes$collection()[i5] == i2; i5--) {
            if (n.c(obj, arraySet.getArray$collection()[i5])) {
                return i5;
            }
        }
        return ~i4;
    }

    public static final <E> int indexOfInternal(ArraySet<E> arraySet, Object obj) {
        n.g(arraySet, "<this>");
        return obj == null ? indexOfNull(arraySet) : indexOf(arraySet, obj, obj.hashCode());
    }

    public static final <E> int indexOfNull(ArraySet<E> arraySet) {
        n.g(arraySet, "<this>");
        return indexOf(arraySet, null, 0);
    }

    public static final <E> boolean isEmptyInternal(ArraySet<E> arraySet) {
        n.g(arraySet, "<this>");
        return arraySet.get_size$collection() <= 0;
    }

    public static final <E> boolean removeAllInternal(ArraySet<E> arraySet, ArraySet<? extends E> array) {
        n.g(arraySet, "<this>");
        n.g(array, "array");
        int i2 = array.get_size$collection();
        int i3 = arraySet.get_size$collection();
        for (int i4 = 0; i4 < i2; i4++) {
            arraySet.remove(array.valueAt(i4));
        }
        return i3 != arraySet.get_size$collection();
    }

    public static final <E> E removeAtInternal(ArraySet<E> arraySet, int i2) {
        n.g(arraySet, "<this>");
        int i3 = arraySet.get_size$collection();
        E e2 = (E) arraySet.getArray$collection()[i2];
        if (i3 <= 1) {
            arraySet.clear();
        } else {
            int i4 = i3 - 1;
            if (arraySet.getHashes$collection().length <= 8 || arraySet.get_size$collection() >= arraySet.getHashes$collection().length / 3) {
                if (i2 < i4) {
                    int i5 = i2 + 1;
                    AbstractC0180h.e(arraySet.getHashes$collection(), arraySet.getHashes$collection(), i2, i5, i3);
                    AbstractC0180h.g(arraySet.getArray$collection(), arraySet.getArray$collection(), i2, i5, i3);
                }
                arraySet.getArray$collection()[i4] = null;
            } else {
                int i6 = arraySet.get_size$collection() > 8 ? arraySet.get_size$collection() + (arraySet.get_size$collection() >> 1) : 8;
                int[] hashes$collection = arraySet.getHashes$collection();
                Object[] array$collection = arraySet.getArray$collection();
                allocArrays(arraySet, i6);
                if (i2 > 0) {
                    AbstractC0180h.i(hashes$collection, arraySet.getHashes$collection(), 0, 0, i2, 6, null);
                    AbstractC0180h.k(array$collection, arraySet.getArray$collection(), 0, 0, i2, 6, null);
                }
                if (i2 < i4) {
                    int i7 = i2 + 1;
                    AbstractC0180h.e(hashes$collection, arraySet.getHashes$collection(), i2, i7, i3);
                    AbstractC0180h.g(array$collection, arraySet.getArray$collection(), i2, i7, i3);
                }
            }
            if (i3 != arraySet.get_size$collection()) {
                throw new ConcurrentModificationException();
            }
            arraySet.set_size$collection(i4);
        }
        return e2;
    }

    public static final <E> boolean removeInternal(ArraySet<E> arraySet, E e2) {
        n.g(arraySet, "<this>");
        int iIndexOf = arraySet.indexOf(e2);
        if (iIndexOf < 0) {
            return false;
        }
        arraySet.removeAt(iIndexOf);
        return true;
    }

    public static final <E> boolean retainAllInternal(ArraySet<E> arraySet, Collection<? extends E> elements) {
        n.g(arraySet, "<this>");
        n.g(elements, "elements");
        boolean z2 = false;
        for (int i2 = arraySet.get_size$collection() - 1; -1 < i2; i2--) {
            if (!u.F(elements, arraySet.getArray$collection()[i2])) {
                arraySet.removeAt(i2);
                z2 = true;
            }
        }
        return z2;
    }

    public static final <E> String toStringInternal(ArraySet<E> arraySet) {
        n.g(arraySet, "<this>");
        if (arraySet.isEmpty()) {
            return "{}";
        }
        StringBuilder sb = new StringBuilder(arraySet.get_size$collection() * 14);
        sb.append('{');
        int i2 = arraySet.get_size$collection();
        for (int i3 = 0; i3 < i2; i3++) {
            if (i3 > 0) {
                sb.append(", ");
            }
            E eValueAt = arraySet.valueAt(i3);
            if (eValueAt != arraySet) {
                sb.append(eValueAt);
            } else {
                sb.append("(this Set)");
            }
        }
        sb.append('}');
        String string = sb.toString();
        n.f(string, "StringBuilder(capacity).…builderAction).toString()");
        return string;
    }

    public static final <E> E valueAtInternal(ArraySet<E> arraySet, int i2) {
        n.g(arraySet, "<this>");
        return (E) arraySet.getArray$collection()[i2];
    }

    public static final <T> ArraySet<T> arraySetOf(T... values) {
        n.g(values, "values");
        ArraySet<T> arraySet = new ArraySet<>(values.length);
        for (T t2 : values) {
            arraySet.add(t2);
        }
        return arraySet;
    }

    public static final <E> boolean removeAllInternal(ArraySet<E> arraySet, Collection<? extends E> elements) {
        n.g(arraySet, "<this>");
        n.g(elements, "elements");
        Iterator<? extends E> it = elements.iterator();
        boolean zRemove = false;
        while (it.hasNext()) {
            zRemove |= arraySet.remove(it.next());
        }
        return zRemove;
    }

    public static final <E> boolean addAllInternal(ArraySet<E> arraySet, Collection<? extends E> elements) {
        n.g(arraySet, "<this>");
        n.g(elements, "elements");
        arraySet.ensureCapacity(arraySet.get_size$collection() + elements.size());
        Iterator<? extends E> it = elements.iterator();
        boolean zAdd = false;
        while (it.hasNext()) {
            zAdd |= arraySet.add(it.next());
        }
        return zAdd;
    }
}
