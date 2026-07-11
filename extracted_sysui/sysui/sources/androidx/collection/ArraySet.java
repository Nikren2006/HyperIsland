package androidx.collection;

import I0.AbstractC0180h;
import I0.u;
import W0.b;
import androidx.collection.internal.ContainerHelpersKt;
import java.util.Collection;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.Set;
import kotlin.jvm.internal.AbstractC0429c;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.n;

/* JADX INFO: loaded from: classes.dex */
public final class ArraySet<E> implements Collection<E>, Set<E>, b {
    private int _size;
    private Object[] array;
    private int[] hashes;

    public final class ElementIterator extends IndexBasedArrayIterator<E> {
        public ElementIterator() {
            super(ArraySet.this.get_size$collection());
        }

        @Override // androidx.collection.IndexBasedArrayIterator
        public E elementAt(int i2) {
            return ArraySet.this.valueAt(i2);
        }

        @Override // androidx.collection.IndexBasedArrayIterator
        public void removeAt(int i2) {
            ArraySet.this.removeAt(i2);
        }
    }

    public ArraySet() {
        this(0, 1, null);
    }

    @Override // java.util.Collection, java.util.Set
    public boolean add(E e2) {
        int i2;
        int iIndexOf;
        int i3 = get_size$collection();
        if (e2 == null) {
            iIndexOf = ArraySetKt.indexOfNull(this);
            i2 = 0;
        } else {
            int iHashCode = e2.hashCode();
            i2 = iHashCode;
            iIndexOf = ArraySetKt.indexOf(this, e2, iHashCode);
        }
        if (iIndexOf >= 0) {
            return false;
        }
        int i4 = ~iIndexOf;
        if (i3 >= getHashes$collection().length) {
            int i5 = 8;
            if (i3 >= 8) {
                i5 = (i3 >> 1) + i3;
            } else if (i3 < 4) {
                i5 = 4;
            }
            int[] hashes$collection = getHashes$collection();
            Object[] array$collection = getArray$collection();
            ArraySetKt.allocArrays(this, i5);
            if (i3 != get_size$collection()) {
                throw new ConcurrentModificationException();
            }
            if (!(getHashes$collection().length == 0)) {
                AbstractC0180h.i(hashes$collection, getHashes$collection(), 0, 0, hashes$collection.length, 6, null);
                AbstractC0180h.k(array$collection, getArray$collection(), 0, 0, array$collection.length, 6, null);
            }
        }
        if (i4 < i3) {
            int i6 = i4 + 1;
            AbstractC0180h.e(getHashes$collection(), getHashes$collection(), i6, i4, i3);
            AbstractC0180h.g(getArray$collection(), getArray$collection(), i6, i4, i3);
        }
        if (i3 != get_size$collection() || i4 >= getHashes$collection().length) {
            throw new ConcurrentModificationException();
        }
        getHashes$collection()[i4] = i2;
        getArray$collection()[i4] = e2;
        set_size$collection(get_size$collection() + 1);
        return true;
    }

    public final void addAll(ArraySet<? extends E> array) {
        n.g(array, "array");
        int i2 = array.get_size$collection();
        ensureCapacity(get_size$collection() + i2);
        if (get_size$collection() != 0) {
            for (int i3 = 0; i3 < i2; i3++) {
                add(array.valueAt(i3));
            }
            return;
        }
        if (i2 > 0) {
            AbstractC0180h.i(array.getHashes$collection(), getHashes$collection(), 0, 0, i2, 6, null);
            AbstractC0180h.k(array.getArray$collection(), getArray$collection(), 0, 0, i2, 6, null);
            if (get_size$collection() != 0) {
                throw new ConcurrentModificationException();
            }
            set_size$collection(i2);
        }
    }

    @Override // java.util.Collection, java.util.Set
    public void clear() {
        if (get_size$collection() != 0) {
            setHashes$collection(ContainerHelpersKt.EMPTY_INTS);
            setArray$collection(ContainerHelpersKt.EMPTY_OBJECTS);
            set_size$collection(0);
        }
        if (get_size$collection() != 0) {
            throw new ConcurrentModificationException();
        }
    }

    @Override // java.util.Collection, java.util.Set
    public boolean contains(Object obj) {
        return indexOf(obj) >= 0;
    }

    @Override // java.util.Collection, java.util.Set
    public boolean containsAll(Collection<? extends Object> elements) {
        n.g(elements, "elements");
        Iterator<? extends Object> it = elements.iterator();
        while (it.hasNext()) {
            if (!contains(it.next())) {
                return false;
            }
        }
        return true;
    }

    public final void ensureCapacity(int i2) {
        int i3 = get_size$collection();
        if (getHashes$collection().length < i2) {
            int[] hashes$collection = getHashes$collection();
            Object[] array$collection = getArray$collection();
            ArraySetKt.allocArrays(this, i2);
            if (get_size$collection() > 0) {
                AbstractC0180h.i(hashes$collection, getHashes$collection(), 0, 0, get_size$collection(), 6, null);
                AbstractC0180h.k(array$collection, getArray$collection(), 0, 0, get_size$collection(), 6, null);
            }
        }
        if (get_size$collection() != i3) {
            throw new ConcurrentModificationException();
        }
    }

    @Override // java.util.Collection, java.util.Set
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if ((obj instanceof Set) && size() == ((Set) obj).size()) {
            try {
                int i2 = get_size$collection();
                for (int i3 = 0; i3 < i2; i3++) {
                    if (((Set) obj).contains(valueAt(i3))) {
                    }
                }
                return true;
            } catch (ClassCastException | NullPointerException unused) {
            }
        }
        return false;
    }

    public final Object[] getArray$collection() {
        return this.array;
    }

    public final int[] getHashes$collection() {
        return this.hashes;
    }

    public int getSize() {
        return this._size;
    }

    public final int get_size$collection() {
        return this._size;
    }

    @Override // java.util.Collection, java.util.Set
    public int hashCode() {
        int[] hashes$collection = getHashes$collection();
        int i2 = get_size$collection();
        int i3 = 0;
        for (int i4 = 0; i4 < i2; i4++) {
            i3 += hashes$collection[i4];
        }
        return i3;
    }

    public final int indexOf(Object obj) {
        return obj == null ? ArraySetKt.indexOfNull(this) : ArraySetKt.indexOf(this, obj, obj.hashCode());
    }

    @Override // java.util.Collection, java.util.Set
    public boolean isEmpty() {
        return get_size$collection() <= 0;
    }

    @Override // java.util.Collection, java.lang.Iterable, java.util.Set
    public Iterator<E> iterator() {
        return new ElementIterator();
    }

    @Override // java.util.Collection, java.util.Set
    public boolean remove(Object obj) {
        int iIndexOf = indexOf(obj);
        if (iIndexOf < 0) {
            return false;
        }
        removeAt(iIndexOf);
        return true;
    }

    public final boolean removeAll(ArraySet<? extends E> array) {
        n.g(array, "array");
        int i2 = array.get_size$collection();
        int i3 = get_size$collection();
        for (int i4 = 0; i4 < i2; i4++) {
            remove(array.valueAt(i4));
        }
        return i3 != get_size$collection();
    }

    public final E removeAt(int i2) {
        int i3 = get_size$collection();
        E e2 = (E) getArray$collection()[i2];
        if (i3 <= 1) {
            clear();
        } else {
            int i4 = i3 - 1;
            if (getHashes$collection().length <= 8 || get_size$collection() >= getHashes$collection().length / 3) {
                if (i2 < i4) {
                    int i5 = i2 + 1;
                    AbstractC0180h.e(getHashes$collection(), getHashes$collection(), i2, i5, i3);
                    AbstractC0180h.g(getArray$collection(), getArray$collection(), i2, i5, i3);
                }
                getArray$collection()[i4] = null;
            } else {
                int i6 = get_size$collection() > 8 ? get_size$collection() + (get_size$collection() >> 1) : 8;
                int[] hashes$collection = getHashes$collection();
                Object[] array$collection = getArray$collection();
                ArraySetKt.allocArrays(this, i6);
                if (i2 > 0) {
                    AbstractC0180h.i(hashes$collection, getHashes$collection(), 0, 0, i2, 6, null);
                    AbstractC0180h.k(array$collection, getArray$collection(), 0, 0, i2, 6, null);
                }
                if (i2 < i4) {
                    int i7 = i2 + 1;
                    AbstractC0180h.e(hashes$collection, getHashes$collection(), i2, i7, i3);
                    AbstractC0180h.g(array$collection, getArray$collection(), i2, i7, i3);
                }
            }
            if (i3 != get_size$collection()) {
                throw new ConcurrentModificationException();
            }
            set_size$collection(i4);
        }
        return e2;
    }

    @Override // java.util.Collection, java.util.Set
    public boolean retainAll(Collection<? extends Object> elements) {
        n.g(elements, "elements");
        boolean z2 = false;
        for (int i2 = get_size$collection() - 1; -1 < i2; i2--) {
            if (!u.F(elements, getArray$collection()[i2])) {
                removeAt(i2);
                z2 = true;
            }
        }
        return z2;
    }

    public final void setArray$collection(Object[] objArr) {
        n.g(objArr, "<set-?>");
        this.array = objArr;
    }

    public final void setHashes$collection(int[] iArr) {
        n.g(iArr, "<set-?>");
        this.hashes = iArr;
    }

    public final void set_size$collection(int i2) {
        this._size = i2;
    }

    @Override // java.util.Collection, java.util.Set
    public final /* bridge */ int size() {
        return getSize();
    }

    @Override // java.util.Collection, java.util.Set
    public final Object[] toArray() {
        return AbstractC0180h.m(this.array, 0, this._size);
    }

    public String toString() {
        if (isEmpty()) {
            return "{}";
        }
        StringBuilder sb = new StringBuilder(get_size$collection() * 14);
        sb.append('{');
        int i2 = get_size$collection();
        for (int i3 = 0; i3 < i2; i3++) {
            if (i3 > 0) {
                sb.append(", ");
            }
            E eValueAt = valueAt(i3);
            if (eValueAt != this) {
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

    public final E valueAt(int i2) {
        return (E) getArray$collection()[i2];
    }

    public ArraySet(int i2) {
        this.hashes = ContainerHelpersKt.EMPTY_INTS;
        this.array = ContainerHelpersKt.EMPTY_OBJECTS;
        if (i2 > 0) {
            ArraySetKt.allocArrays(this, i2);
        }
    }

    @Override // java.util.Collection, java.util.Set
    public final <T> T[] toArray(T[] array) {
        n.g(array, "array");
        T[] result = (T[]) ArraySetJvmUtil.resizeForToArray(array, this._size);
        AbstractC0180h.g(this.array, result, 0, 0, this._size);
        n.f(result, "result");
        return result;
    }

    @Override // java.util.Collection, java.util.Set
    public boolean removeAll(Collection<? extends Object> elements) {
        n.g(elements, "elements");
        Iterator<? extends Object> it = elements.iterator();
        boolean zRemove = false;
        while (it.hasNext()) {
            zRemove |= remove(it.next());
        }
        return zRemove;
    }

    public /* synthetic */ ArraySet(int i2, int i3, DefaultConstructorMarker defaultConstructorMarker) {
        this((i3 & 1) != 0 ? 0 : i2);
    }

    public ArraySet(ArraySet<? extends E> arraySet) {
        this(0);
        if (arraySet != null) {
            addAll((ArraySet) arraySet);
        }
    }

    public ArraySet(Collection<? extends E> collection) {
        this(0);
        if (collection != null) {
            addAll(collection);
        }
    }

    @Override // java.util.Collection, java.util.Set
    public boolean addAll(Collection<? extends E> elements) {
        n.g(elements, "elements");
        ensureCapacity(get_size$collection() + elements.size());
        Iterator<? extends E> it = elements.iterator();
        boolean zAdd = false;
        while (it.hasNext()) {
            zAdd |= add(it.next());
        }
        return zAdd;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public ArraySet(E[] eArr) {
        this(0);
        if (eArr != null) {
            Iterator itA = AbstractC0429c.a(eArr);
            while (itA.hasNext()) {
                add(itA.next());
            }
        }
    }
}
