package androidx.collection;

import I0.AbstractC0180h;
import I0.AbstractC0181i;
import I0.m;
import I0.u;
import W0.a;
import W0.d;
import androidx.annotation.IntRange;
import c1.C0232d;
import c1.f;
import e1.InterfaceC0340e;
import e1.l;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.g;
import kotlin.jvm.internal.n;

/* JADX INFO: loaded from: classes.dex */
public final class MutableObjectList<E> extends ObjectList<E> {
    private ObjectListMutableList<E> list;

    public static final class MutableObjectListIterator<T> implements ListIterator<T>, a {
        private final List<T> list;
        private int prevIndex;

        public MutableObjectListIterator(List<T> list, int i2) {
            n.g(list, "list");
            this.list = list;
            this.prevIndex = i2 - 1;
        }

        @Override // java.util.ListIterator
        public void add(T t2) {
            List<T> list = this.list;
            int i2 = this.prevIndex + 1;
            this.prevIndex = i2;
            list.add(i2, t2);
        }

        @Override // java.util.ListIterator, java.util.Iterator
        public boolean hasNext() {
            return this.prevIndex < this.list.size() - 1;
        }

        @Override // java.util.ListIterator
        public boolean hasPrevious() {
            return this.prevIndex >= 0;
        }

        @Override // java.util.ListIterator, java.util.Iterator
        public T next() {
            List<T> list = this.list;
            int i2 = this.prevIndex + 1;
            this.prevIndex = i2;
            return list.get(i2);
        }

        @Override // java.util.ListIterator
        public int nextIndex() {
            return this.prevIndex + 1;
        }

        @Override // java.util.ListIterator
        public T previous() {
            List<T> list = this.list;
            int i2 = this.prevIndex;
            this.prevIndex = i2 - 1;
            return list.get(i2);
        }

        @Override // java.util.ListIterator
        public int previousIndex() {
            return this.prevIndex;
        }

        @Override // java.util.ListIterator, java.util.Iterator
        public void remove() {
            this.list.remove(this.prevIndex);
            this.prevIndex--;
        }

        @Override // java.util.ListIterator
        public void set(T t2) {
            this.list.set(this.prevIndex, t2);
        }
    }

    public static final class ObjectListMutableList<T> implements List<T>, d {
        private final MutableObjectList<T> objectList;

        public ObjectListMutableList(MutableObjectList<T> objectList) {
            n.g(objectList, "objectList");
            this.objectList = objectList;
        }

        @Override // java.util.List, java.util.Collection
        public boolean add(T t2) {
            return this.objectList.add(t2);
        }

        @Override // java.util.List
        public boolean addAll(int i2, Collection<? extends T> elements) {
            n.g(elements, "elements");
            return this.objectList.addAll(i2, elements);
        }

        @Override // java.util.List, java.util.Collection
        public void clear() {
            this.objectList.clear();
        }

        @Override // java.util.List, java.util.Collection
        public boolean contains(Object obj) {
            return this.objectList.contains(obj);
        }

        @Override // java.util.List, java.util.Collection
        public boolean containsAll(Collection<? extends Object> elements) {
            n.g(elements, "elements");
            return this.objectList.containsAll(elements);
        }

        @Override // java.util.List
        public T get(int i2) {
            ObjectListKt.checkIndex(this, i2);
            return this.objectList.get(i2);
        }

        public int getSize() {
            return this.objectList.getSize();
        }

        @Override // java.util.List
        public int indexOf(Object obj) {
            return this.objectList.indexOf(obj);
        }

        @Override // java.util.List, java.util.Collection
        public boolean isEmpty() {
            return this.objectList.isEmpty();
        }

        @Override // java.util.List, java.util.Collection, java.lang.Iterable
        public Iterator<T> iterator() {
            return new MutableObjectListIterator(this, 0);
        }

        @Override // java.util.List
        public int lastIndexOf(Object obj) {
            return this.objectList.lastIndexOf(obj);
        }

        @Override // java.util.List
        public ListIterator<T> listIterator() {
            return new MutableObjectListIterator(this, 0);
        }

        @Override // java.util.List
        public final /* bridge */ T remove(int i2) {
            return removeAt(i2);
        }

        @Override // java.util.List, java.util.Collection
        public boolean removeAll(Collection<? extends Object> elements) {
            n.g(elements, "elements");
            return this.objectList.removeAll(elements);
        }

        public T removeAt(int i2) {
            ObjectListKt.checkIndex(this, i2);
            return this.objectList.removeAt(i2);
        }

        @Override // java.util.List, java.util.Collection
        public boolean retainAll(Collection<? extends Object> elements) {
            n.g(elements, "elements");
            return this.objectList.retainAll((Collection<? extends T>) elements);
        }

        @Override // java.util.List
        public T set(int i2, T t2) {
            ObjectListKt.checkIndex(this, i2);
            return this.objectList.set(i2, t2);
        }

        @Override // java.util.List, java.util.Collection
        public final /* bridge */ int size() {
            return getSize();
        }

        @Override // java.util.List
        public List<T> subList(int i2, int i3) {
            ObjectListKt.checkSubIndex(this, i2, i3);
            return new SubList(this, i2, i3);
        }

        @Override // java.util.List, java.util.Collection
        public Object[] toArray() {
            return g.a(this);
        }

        @Override // java.util.List
        public void add(int i2, T t2) {
            this.objectList.add(i2, t2);
        }

        @Override // java.util.List, java.util.Collection
        public boolean addAll(Collection<? extends T> elements) {
            n.g(elements, "elements");
            return this.objectList.addAll(elements);
        }

        @Override // java.util.List
        public ListIterator<T> listIterator(int i2) {
            return new MutableObjectListIterator(this, i2);
        }

        @Override // java.util.List, java.util.Collection
        public boolean remove(Object obj) {
            return this.objectList.remove(obj);
        }

        @Override // java.util.List, java.util.Collection
        public <T> T[] toArray(T[] array) {
            n.g(array, "array");
            return (T[]) g.b(this, array);
        }
    }

    public static final class SubList<T> implements List<T>, d {
        private int end;
        private final List<T> list;
        private final int start;

        public SubList(List<T> list, int i2, int i3) {
            n.g(list, "list");
            this.list = list;
            this.start = i2;
            this.end = i3;
        }

        @Override // java.util.List, java.util.Collection
        public boolean add(T t2) {
            List<T> list = this.list;
            int i2 = this.end;
            this.end = i2 + 1;
            list.add(i2, t2);
            return true;
        }

        @Override // java.util.List
        public boolean addAll(int i2, Collection<? extends T> elements) {
            n.g(elements, "elements");
            this.list.addAll(i2 + this.start, elements);
            this.end += elements.size();
            return elements.size() > 0;
        }

        @Override // java.util.List, java.util.Collection
        public void clear() {
            int i2 = this.end - 1;
            int i3 = this.start;
            if (i3 <= i2) {
                while (true) {
                    this.list.remove(i2);
                    if (i2 == i3) {
                        break;
                    } else {
                        i2--;
                    }
                }
            }
            this.end = this.start;
        }

        @Override // java.util.List, java.util.Collection
        public boolean contains(Object obj) {
            int i2 = this.end;
            for (int i3 = this.start; i3 < i2; i3++) {
                if (n.c(this.list.get(i3), obj)) {
                    return true;
                }
            }
            return false;
        }

        @Override // java.util.List, java.util.Collection
        public boolean containsAll(Collection<? extends Object> elements) {
            n.g(elements, "elements");
            Iterator<T> it = elements.iterator();
            while (it.hasNext()) {
                if (!contains(it.next())) {
                    return false;
                }
            }
            return true;
        }

        @Override // java.util.List
        public T get(int i2) {
            ObjectListKt.checkIndex(this, i2);
            return this.list.get(i2 + this.start);
        }

        public int getSize() {
            return this.end - this.start;
        }

        @Override // java.util.List
        public int indexOf(Object obj) {
            int i2 = this.end;
            for (int i3 = this.start; i3 < i2; i3++) {
                if (n.c(this.list.get(i3), obj)) {
                    return i3 - this.start;
                }
            }
            return -1;
        }

        @Override // java.util.List, java.util.Collection
        public boolean isEmpty() {
            return this.end == this.start;
        }

        @Override // java.util.List, java.util.Collection, java.lang.Iterable
        public Iterator<T> iterator() {
            return new MutableObjectListIterator(this, 0);
        }

        @Override // java.util.List
        public int lastIndexOf(Object obj) {
            int i2 = this.end - 1;
            int i3 = this.start;
            if (i3 > i2) {
                return -1;
            }
            while (!n.c(this.list.get(i2), obj)) {
                if (i2 == i3) {
                    return -1;
                }
                i2--;
            }
            return i2 - this.start;
        }

        @Override // java.util.List
        public ListIterator<T> listIterator() {
            return new MutableObjectListIterator(this, 0);
        }

        @Override // java.util.List
        public final /* bridge */ T remove(int i2) {
            return removeAt(i2);
        }

        @Override // java.util.List, java.util.Collection
        public boolean removeAll(Collection<? extends Object> elements) {
            n.g(elements, "elements");
            int i2 = this.end;
            Iterator<T> it = elements.iterator();
            while (it.hasNext()) {
                remove(it.next());
            }
            return i2 != this.end;
        }

        public T removeAt(int i2) {
            ObjectListKt.checkIndex(this, i2);
            this.end--;
            return this.list.remove(i2 + this.start);
        }

        @Override // java.util.List, java.util.Collection
        public boolean retainAll(Collection<? extends Object> elements) {
            n.g(elements, "elements");
            int i2 = this.end;
            int i3 = i2 - 1;
            int i4 = this.start;
            if (i4 <= i3) {
                while (true) {
                    if (!elements.contains(this.list.get(i3))) {
                        this.list.remove(i3);
                        this.end--;
                    }
                    if (i3 == i4) {
                        break;
                    }
                    i3--;
                }
            }
            return i2 != this.end;
        }

        @Override // java.util.List
        public T set(int i2, T t2) {
            ObjectListKt.checkIndex(this, i2);
            return this.list.set(i2 + this.start, t2);
        }

        @Override // java.util.List, java.util.Collection
        public final /* bridge */ int size() {
            return getSize();
        }

        @Override // java.util.List
        public List<T> subList(int i2, int i3) {
            ObjectListKt.checkSubIndex(this, i2, i3);
            return new SubList(this, i2, i3);
        }

        @Override // java.util.List, java.util.Collection
        public Object[] toArray() {
            return g.a(this);
        }

        @Override // java.util.List
        public void add(int i2, T t2) {
            this.list.add(i2 + this.start, t2);
            this.end++;
        }

        @Override // java.util.List
        public ListIterator<T> listIterator(int i2) {
            return new MutableObjectListIterator(this, i2);
        }

        @Override // java.util.List, java.util.Collection
        public boolean remove(Object obj) {
            int i2 = this.end;
            for (int i3 = this.start; i3 < i2; i3++) {
                if (n.c(this.list.get(i3), obj)) {
                    this.list.remove(i3);
                    this.end--;
                    return true;
                }
            }
            return false;
        }

        @Override // java.util.List, java.util.Collection
        public <T> T[] toArray(T[] array) {
            n.g(array, "array");
            return (T[]) g.b(this, array);
        }

        @Override // java.util.List, java.util.Collection
        public boolean addAll(Collection<? extends T> elements) {
            n.g(elements, "elements");
            this.list.addAll(this.end, elements);
            this.end += elements.size();
            return elements.size() > 0;
        }
    }

    public MutableObjectList() {
        this(0, 1, null);
    }

    public static /* synthetic */ void trim$default(MutableObjectList mutableObjectList, int i2, int i3, Object obj) {
        if ((i3 & 1) != 0) {
            i2 = mutableObjectList._size;
        }
        mutableObjectList.trim(i2);
    }

    public final boolean add(E e2) {
        ensureCapacity(this._size + 1);
        Object[] objArr = this.content;
        int i2 = this._size;
        objArr[i2] = e2;
        this._size = i2 + 1;
        return true;
    }

    public final boolean addAll(@IntRange(from = 0) int i2, E[] elements) {
        int i3;
        n.g(elements, "elements");
        if (i2 < 0 || i2 > (i3 = this._size)) {
            throw new IndexOutOfBoundsException("Index " + i2 + " must be in 0.." + this._size);
        }
        if (elements.length == 0) {
            return false;
        }
        ensureCapacity(i3 + elements.length);
        Object[] objArr = this.content;
        int i4 = this._size;
        if (i2 != i4) {
            AbstractC0180h.g(objArr, objArr, elements.length + i2, i2, i4);
        }
        AbstractC0180h.k(elements, objArr, i2, 0, 0, 12, null);
        this._size += elements.length;
        return true;
    }

    @Override // androidx.collection.ObjectList
    public List<E> asList() {
        return asMutableList();
    }

    public final List<E> asMutableList() {
        ObjectListMutableList<E> objectListMutableList = this.list;
        if (objectListMutableList != null) {
            return objectListMutableList;
        }
        ObjectListMutableList<E> objectListMutableList2 = new ObjectListMutableList<>(this);
        this.list = objectListMutableList2;
        return objectListMutableList2;
    }

    public final void clear() {
        AbstractC0180h.o(this.content, null, 0, this._size);
        this._size = 0;
    }

    public final void ensureCapacity(int i2) {
        Object[] objArr = this.content;
        if (objArr.length < i2) {
            Object[] objArrCopyOf = Arrays.copyOf(objArr, Math.max(i2, (objArr.length * 3) / 2));
            n.f(objArrCopyOf, "copyOf(this, newSize)");
            this.content = objArrCopyOf;
        }
    }

    public final int getCapacity() {
        return this.content.length;
    }

    public final void minusAssign(E e2) {
        remove(e2);
    }

    public final void plusAssign(ObjectList<E> elements) {
        n.g(elements, "elements");
        if (elements.isEmpty()) {
            return;
        }
        ensureCapacity(this._size + elements._size);
        AbstractC0180h.g(elements.content, this.content, this._size, 0, elements._size);
        this._size += elements._size;
    }

    public final boolean remove(E e2) {
        int iIndexOf = indexOf(e2);
        if (iIndexOf < 0) {
            return false;
        }
        removeAt(iIndexOf);
        return true;
    }

    public final boolean removeAll(E[] elements) {
        n.g(elements, "elements");
        int i2 = this._size;
        for (E e2 : elements) {
            remove(e2);
        }
        return i2 != this._size;
    }

    public final E removeAt(@IntRange(from = 0) int i2) {
        int i3;
        if (i2 < 0 || i2 >= (i3 = this._size)) {
            StringBuilder sb = new StringBuilder();
            sb.append("Index ");
            sb.append(i2);
            sb.append(" must be in 0..");
            sb.append(this._size - 1);
            throw new IndexOutOfBoundsException(sb.toString());
        }
        Object[] objArr = this.content;
        E e2 = (E) objArr[i2];
        if (i2 != i3 - 1) {
            AbstractC0180h.g(objArr, objArr, i2, i2 + 1, i3);
        }
        int i4 = this._size - 1;
        this._size = i4;
        objArr[i4] = null;
        return e2;
    }

    public final void removeIf(Function1 predicate) {
        n.g(predicate, "predicate");
        int i2 = this._size;
        Object[] objArr = this.content;
        int i3 = 0;
        C0232d c0232dL = f.l(0, i2);
        int iC = c0232dL.c();
        int iD = c0232dL.d();
        if (iC <= iD) {
            while (true) {
                objArr[iC - i3] = objArr[iC];
                if (((Boolean) predicate.invoke(objArr[iC])).booleanValue()) {
                    i3++;
                }
                if (iC == iD) {
                    break;
                } else {
                    iC++;
                }
            }
        }
        AbstractC0180h.o(objArr, null, i2 - i3, i2);
        this._size -= i3;
    }

    public final void removeRange(@IntRange(from = 0) int i2, @IntRange(from = 0) int i3) {
        int i4;
        if (i2 < 0 || i2 > (i4 = this._size) || i3 < 0 || i3 > i4) {
            throw new IndexOutOfBoundsException("Start (" + i2 + ") and end (" + i3 + ") must be in 0.." + this._size);
        }
        if (i3 < i2) {
            throw new IllegalArgumentException("Start (" + i2 + ") is more than end (" + i3 + ')');
        }
        if (i3 != i2) {
            if (i3 < i4) {
                Object[] objArr = this.content;
                AbstractC0180h.g(objArr, objArr, i2, i3, i4);
            }
            int i5 = this._size;
            int i6 = i5 - (i3 - i2);
            AbstractC0180h.o(this.content, null, i6, i5);
            this._size = i6;
        }
    }

    public final boolean retainAll(E[] elements) {
        n.g(elements, "elements");
        int i2 = this._size;
        Object[] objArr = this.content;
        for (int i3 = i2 - 1; -1 < i3; i3--) {
            if (AbstractC0181i.D(elements, objArr[i3]) < 0) {
                removeAt(i3);
            }
        }
        return i2 != this._size;
    }

    public final E set(@IntRange(from = 0) int i2, E e2) {
        if (i2 >= 0 && i2 < this._size) {
            Object[] objArr = this.content;
            E e3 = (E) objArr[i2];
            objArr[i2] = e2;
            return e3;
        }
        StringBuilder sb = new StringBuilder();
        sb.append("set index ");
        sb.append(i2);
        sb.append(" must be between 0 .. ");
        sb.append(this._size - 1);
        throw new IndexOutOfBoundsException(sb.toString());
    }

    public final void trim(int i2) {
        int iMax = Math.max(i2, this._size);
        Object[] objArr = this.content;
        if (objArr.length > iMax) {
            Object[] objArrCopyOf = Arrays.copyOf(objArr, iMax);
            n.f(objArrCopyOf, "copyOf(this, newSize)");
            this.content = objArrCopyOf;
        }
    }

    public /* synthetic */ MutableObjectList(int i2, int i3, DefaultConstructorMarker defaultConstructorMarker) {
        this((i3 & 1) != 0 ? 16 : i2);
    }

    public final void minusAssign(List<? extends E> elements) {
        n.g(elements, "elements");
        int size = elements.size();
        for (int i2 = 0; i2 < size; i2++) {
            remove(elements.get(i2));
        }
    }

    public MutableObjectList(int i2) {
        super(i2, null);
    }

    public final void add(@IntRange(from = 0) int i2, E e2) {
        int i3;
        if (i2 >= 0 && i2 <= (i3 = this._size)) {
            ensureCapacity(i3 + 1);
            Object[] objArr = this.content;
            int i4 = this._size;
            if (i2 != i4) {
                AbstractC0180h.g(objArr, objArr, i2 + 1, i2, i4);
            }
            objArr[i2] = e2;
            this._size++;
            return;
        }
        throw new IndexOutOfBoundsException("Index " + i2 + " must be in 0.." + this._size);
    }

    public final void minusAssign(E[] elements) {
        n.g(elements, "elements");
        for (E e2 : elements) {
            remove(e2);
        }
    }

    public final boolean removeAll(ObjectList<E> elements) {
        n.g(elements, "elements");
        int i2 = this._size;
        minusAssign((ObjectList) elements);
        return i2 != this._size;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final void minusAssign(ObjectList<E> elements) {
        n.g(elements, "elements");
        Object[] objArr = elements.content;
        int i2 = elements._size;
        for (int i3 = 0; i3 < i2; i3++) {
            remove(objArr[i3]);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final boolean retainAll(ObjectList<E> elements) {
        n.g(elements, "elements");
        int i2 = this._size;
        Object[] objArr = this.content;
        for (int i3 = i2 - 1; -1 < i3; i3--) {
            if (!elements.contains(objArr[i3])) {
                removeAt(i3);
            }
        }
        return i2 != this._size;
    }

    public final boolean removeAll(ScatterSet<E> elements) {
        n.g(elements, "elements");
        int i2 = this._size;
        minusAssign((ScatterSet) elements);
        return i2 != this._size;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final void plusAssign(ScatterSet<E> elements) {
        n.g(elements, "elements");
        if (elements.isEmpty()) {
            return;
        }
        ensureCapacity(this._size + elements.getSize());
        Object[] objArr = elements.elements;
        long[] jArr = elements.metadata;
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
                        add(objArr[(i2 << 3) + i4]);
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

    public final boolean addAll(@IntRange(from = 0) int i2, Collection<? extends E> elements) {
        n.g(elements, "elements");
        if (i2 >= 0 && i2 <= this._size) {
            int i3 = 0;
            if (elements.isEmpty()) {
                return false;
            }
            ensureCapacity(this._size + elements.size());
            Object[] objArr = this.content;
            if (i2 != this._size) {
                AbstractC0180h.g(objArr, objArr, elements.size() + i2, i2, this._size);
            }
            for (Object obj : elements) {
                int i4 = i3 + 1;
                if (i3 < 0) {
                    m.n();
                }
                objArr[i3 + i2] = obj;
                i3 = i4;
            }
            this._size += elements.size();
            return true;
        }
        throw new IndexOutOfBoundsException("Index " + i2 + " must be in 0.." + this._size);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final void minusAssign(ScatterSet<E> elements) {
        n.g(elements, "elements");
        Object[] objArr = elements.elements;
        long[] jArr = elements.metadata;
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
                        remove(objArr[(i2 << 3) + i4]);
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

    public final boolean removeAll(List<? extends E> elements) {
        n.g(elements, "elements");
        int i2 = this._size;
        minusAssign((List) elements);
        return i2 != this._size;
    }

    public final boolean retainAll(Collection<? extends E> elements) {
        n.g(elements, "elements");
        int i2 = this._size;
        Object[] objArr = this.content;
        for (int i3 = i2 - 1; -1 < i3; i3--) {
            if (!elements.contains(objArr[i3])) {
                removeAt(i3);
            }
        }
        return i2 != this._size;
    }

    public final boolean removeAll(Iterable<? extends E> elements) {
        n.g(elements, "elements");
        int i2 = this._size;
        minusAssign((Iterable) elements);
        return i2 != this._size;
    }

    public final void minusAssign(Iterable<? extends E> elements) {
        n.g(elements, "elements");
        Iterator<? extends E> it = elements.iterator();
        while (it.hasNext()) {
            remove(it.next());
        }
    }

    public final void plusAssign(E[] elements) {
        n.g(elements, "elements");
        if (elements.length == 0) {
            return;
        }
        ensureCapacity(this._size + elements.length);
        AbstractC0180h.k(elements, this.content, this._size, 0, 0, 12, null);
        this._size += elements.length;
    }

    public final boolean removeAll(InterfaceC0340e elements) {
        n.g(elements, "elements");
        int i2 = this._size;
        minusAssign(elements);
        return i2 != this._size;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final void minusAssign(InterfaceC0340e elements) {
        n.g(elements, "elements");
        Iterator it = elements.iterator();
        while (it.hasNext()) {
            remove(it.next());
        }
    }

    public final boolean retainAll(Iterable<? extends E> elements) {
        n.g(elements, "elements");
        int i2 = this._size;
        Object[] objArr = this.content;
        for (int i3 = i2 - 1; -1 < i3; i3--) {
            if (!u.F(elements, objArr[i3])) {
                removeAt(i3);
            }
        }
        return i2 != this._size;
    }

    public final void plusAssign(List<? extends E> elements) {
        n.g(elements, "elements");
        if (elements.isEmpty()) {
            return;
        }
        int i2 = this._size;
        ensureCapacity(elements.size() + i2);
        Object[] objArr = this.content;
        int size = elements.size();
        for (int i3 = 0; i3 < size; i3++) {
            objArr[i3 + i2] = elements.get(i3);
        }
        this._size += elements.size();
    }

    public final boolean addAll(@IntRange(from = 0) int i2, ObjectList<E> elements) {
        n.g(elements, "elements");
        if (i2 >= 0 && i2 <= this._size) {
            if (elements.isEmpty()) {
                return false;
            }
            ensureCapacity(this._size + elements._size);
            Object[] objArr = this.content;
            int i3 = this._size;
            if (i2 != i3) {
                AbstractC0180h.g(objArr, objArr, elements._size + i2, i2, i3);
            }
            AbstractC0180h.g(elements.content, objArr, i2, 0, elements._size);
            this._size += elements._size;
            return true;
        }
        throw new IndexOutOfBoundsException("Index " + i2 + " must be in 0.." + this._size);
    }

    public final boolean retainAll(InterfaceC0340e elements) {
        n.g(elements, "elements");
        int i2 = this._size;
        Object[] objArr = this.content;
        for (int i3 = i2 - 1; -1 < i3; i3--) {
            if (!l.g(elements, objArr[i3])) {
                removeAt(i3);
            }
        }
        return i2 != this._size;
    }

    public final void plusAssign(E e2) {
        add(e2);
    }

    public final void plusAssign(Iterable<? extends E> elements) {
        n.g(elements, "elements");
        Iterator<? extends E> it = elements.iterator();
        while (it.hasNext()) {
            add(it.next());
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final void plusAssign(InterfaceC0340e elements) {
        n.g(elements, "elements");
        Iterator it = elements.iterator();
        while (it.hasNext()) {
            add(it.next());
        }
    }

    public final boolean addAll(ObjectList<E> elements) {
        n.g(elements, "elements");
        int i2 = this._size;
        plusAssign((ObjectList) elements);
        return i2 != this._size;
    }

    public final boolean addAll(ScatterSet<E> elements) {
        n.g(elements, "elements");
        int i2 = this._size;
        plusAssign((ScatterSet) elements);
        return i2 != this._size;
    }

    public final boolean addAll(E[] elements) {
        n.g(elements, "elements");
        int i2 = this._size;
        plusAssign((Object[]) elements);
        return i2 != this._size;
    }

    public final boolean addAll(List<? extends E> elements) {
        n.g(elements, "elements");
        int i2 = this._size;
        plusAssign((List) elements);
        return i2 != this._size;
    }

    public final boolean addAll(Iterable<? extends E> elements) {
        n.g(elements, "elements");
        int i2 = this._size;
        plusAssign((Iterable) elements);
        return i2 != this._size;
    }

    public final boolean addAll(InterfaceC0340e elements) {
        n.g(elements, "elements");
        int i2 = this._size;
        plusAssign(elements);
        return i2 != this._size;
    }
}
