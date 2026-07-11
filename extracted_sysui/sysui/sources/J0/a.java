package J0;

import I0.AbstractC0174b;
import I0.AbstractC0175c;
import I0.AbstractC0180h;
import I0.AbstractC0184l;
import W0.d;
import java.io.Serializable;
import java.util.AbstractList;
import java.util.Arrays;
import java.util.Collection;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.NoSuchElementException;
import java.util.RandomAccess;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.n;

/* JADX INFO: loaded from: classes2.dex */
public final class a extends AbstractC0175c implements List, RandomAccess, Serializable, d {

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public static final C0012a f351g = new C0012a(null);

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public static final a f352h;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public Object[] f353a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public int f354b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public int f355c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public boolean f356d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final a f357e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final a f358f;

    /* JADX INFO: renamed from: J0.a$a, reason: collision with other inner class name */
    public static final class C0012a {
        public /* synthetic */ C0012a(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public C0012a() {
        }
    }

    public static final class b implements ListIterator, W0.a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final a f359a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public int f360b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public int f361c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public int f362d;

        public b(a list, int i2) {
            n.g(list, "list");
            this.f359a = list;
            this.f360b = i2;
            this.f361c = -1;
            this.f362d = ((AbstractList) list).modCount;
        }

        @Override // java.util.ListIterator
        public void add(Object obj) {
            c();
            a aVar = this.f359a;
            int i2 = this.f360b;
            this.f360b = i2 + 1;
            aVar.add(i2, obj);
            this.f361c = -1;
            this.f362d = ((AbstractList) this.f359a).modCount;
        }

        public final void c() {
            if (((AbstractList) this.f359a).modCount != this.f362d) {
                throw new ConcurrentModificationException();
            }
        }

        @Override // java.util.ListIterator, java.util.Iterator
        public boolean hasNext() {
            return this.f360b < this.f359a.f355c;
        }

        @Override // java.util.ListIterator
        public boolean hasPrevious() {
            return this.f360b > 0;
        }

        @Override // java.util.ListIterator, java.util.Iterator
        public Object next() {
            c();
            if (this.f360b >= this.f359a.f355c) {
                throw new NoSuchElementException();
            }
            int i2 = this.f360b;
            this.f360b = i2 + 1;
            this.f361c = i2;
            return this.f359a.f353a[this.f359a.f354b + this.f361c];
        }

        @Override // java.util.ListIterator
        public int nextIndex() {
            return this.f360b;
        }

        @Override // java.util.ListIterator
        public Object previous() {
            c();
            int i2 = this.f360b;
            if (i2 <= 0) {
                throw new NoSuchElementException();
            }
            int i3 = i2 - 1;
            this.f360b = i3;
            this.f361c = i3;
            return this.f359a.f353a[this.f359a.f354b + this.f361c];
        }

        @Override // java.util.ListIterator
        public int previousIndex() {
            return this.f360b - 1;
        }

        @Override // java.util.ListIterator, java.util.Iterator
        public void remove() {
            c();
            int i2 = this.f361c;
            if (i2 == -1) {
                throw new IllegalStateException("Call next() or previous() before removing element from the iterator.");
            }
            this.f359a.remove(i2);
            this.f360b = this.f361c;
            this.f361c = -1;
            this.f362d = ((AbstractList) this.f359a).modCount;
        }

        @Override // java.util.ListIterator
        public void set(Object obj) {
            c();
            int i2 = this.f361c;
            if (i2 == -1) {
                throw new IllegalStateException("Call next() or previous() before replacing element from the iterator.");
            }
            this.f359a.set(i2, obj);
        }
    }

    static {
        a aVar = new a(0);
        aVar.f356d = true;
        f352h = aVar;
    }

    public a(Object[] objArr, int i2, int i3, boolean z2, a aVar, a aVar2) {
        this.f353a = objArr;
        this.f354b = i2;
        this.f355c = i3;
        this.f356d = z2;
        this.f357e = aVar;
        this.f358f = aVar2;
        if (aVar != null) {
            ((AbstractList) this).modCount = ((AbstractList) aVar).modCount;
        }
    }

    private final void l() {
        a aVar = this.f358f;
        if (aVar != null && ((AbstractList) aVar).modCount != ((AbstractList) this).modCount) {
            throw new ConcurrentModificationException();
        }
    }

    @Override // java.util.AbstractList, java.util.AbstractCollection, java.util.Collection, java.util.List
    public boolean add(Object obj) {
        m();
        l();
        j(this.f354b + this.f355c, obj);
        return true;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public boolean addAll(Collection elements) {
        n.g(elements, "elements");
        m();
        l();
        int size = elements.size();
        i(this.f354b + this.f355c, elements, size);
        return size > 0;
    }

    @Override // I0.AbstractC0175c
    public int c() {
        l();
        return this.f355c;
    }

    @Override // java.util.AbstractList, java.util.AbstractCollection, java.util.Collection, java.util.List
    public void clear() {
        m();
        l();
        u(this.f354b, this.f355c);
    }

    @Override // I0.AbstractC0175c
    public Object d(int i2) {
        m();
        l();
        AbstractC0174b.f320a.a(i2, this.f355c);
        return t(this.f354b + i2);
    }

    @Override // java.util.AbstractList, java.util.Collection, java.util.List
    public boolean equals(Object obj) {
        l();
        return obj == this || ((obj instanceof List) && n((List) obj));
    }

    @Override // java.util.AbstractList, java.util.List
    public Object get(int i2) {
        l();
        AbstractC0174b.f320a.a(i2, this.f355c);
        return this.f353a[this.f354b + i2];
    }

    @Override // java.util.AbstractList, java.util.Collection, java.util.List
    public int hashCode() {
        l();
        return J0.b.i(this.f353a, this.f354b, this.f355c);
    }

    public final void i(int i2, Collection collection, int i3) {
        s();
        a aVar = this.f357e;
        if (aVar != null) {
            aVar.i(i2, collection, i3);
            this.f353a = this.f357e.f353a;
            this.f355c += i3;
        } else {
            q(i2, i3);
            Iterator it = collection.iterator();
            for (int i4 = 0; i4 < i3; i4++) {
                this.f353a[i2 + i4] = it.next();
            }
        }
    }

    @Override // java.util.AbstractList, java.util.List
    public int indexOf(Object obj) {
        l();
        for (int i2 = 0; i2 < this.f355c; i2++) {
            if (n.c(this.f353a[this.f354b + i2], obj)) {
                return i2;
            }
        }
        return -1;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public boolean isEmpty() {
        l();
        return this.f355c == 0;
    }

    @Override // java.util.AbstractList, java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.List
    public Iterator iterator() {
        return listIterator(0);
    }

    public final void j(int i2, Object obj) {
        s();
        a aVar = this.f357e;
        if (aVar == null) {
            q(i2, 1);
            this.f353a[i2] = obj;
        } else {
            aVar.j(i2, obj);
            this.f353a = this.f357e.f353a;
            this.f355c++;
        }
    }

    public final List k() {
        if (this.f357e != null) {
            throw new IllegalStateException();
        }
        m();
        this.f356d = true;
        return this.f355c > 0 ? this : f352h;
    }

    @Override // java.util.AbstractList, java.util.List
    public int lastIndexOf(Object obj) {
        l();
        for (int i2 = this.f355c - 1; i2 >= 0; i2--) {
            if (n.c(this.f353a[this.f354b + i2], obj)) {
                return i2;
            }
        }
        return -1;
    }

    @Override // java.util.AbstractList, java.util.List
    public ListIterator listIterator() {
        return listIterator(0);
    }

    public final void m() {
        if (r()) {
            throw new UnsupportedOperationException();
        }
    }

    public final boolean n(List list) {
        return J0.b.h(this.f353a, this.f354b, this.f355c, list);
    }

    public final void o(int i2) {
        if (i2 < 0) {
            throw new OutOfMemoryError();
        }
        Object[] objArr = this.f353a;
        if (i2 > objArr.length) {
            this.f353a = J0.b.e(this.f353a, AbstractC0174b.f320a.d(objArr.length, i2));
        }
    }

    public final void p(int i2) {
        o(this.f355c + i2);
    }

    public final void q(int i2, int i3) {
        p(i3);
        Object[] objArr = this.f353a;
        AbstractC0180h.g(objArr, objArr, i2 + i3, i2, this.f354b + this.f355c);
        this.f355c += i3;
    }

    public final boolean r() {
        a aVar;
        return this.f356d || ((aVar = this.f358f) != null && aVar.f356d);
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public boolean remove(Object obj) {
        m();
        l();
        int iIndexOf = indexOf(obj);
        if (iIndexOf >= 0) {
            remove(iIndexOf);
        }
        return iIndexOf >= 0;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public boolean removeAll(Collection elements) {
        n.g(elements, "elements");
        m();
        l();
        return v(this.f354b, this.f355c, elements, false) > 0;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public boolean retainAll(Collection elements) {
        n.g(elements, "elements");
        m();
        l();
        return v(this.f354b, this.f355c, elements, true) > 0;
    }

    public final void s() {
        ((AbstractList) this).modCount++;
    }

    @Override // java.util.AbstractList, java.util.List
    public Object set(int i2, Object obj) {
        m();
        l();
        AbstractC0174b.f320a.a(i2, this.f355c);
        Object[] objArr = this.f353a;
        int i3 = this.f354b;
        Object obj2 = objArr[i3 + i2];
        objArr[i3 + i2] = obj;
        return obj2;
    }

    @Override // java.util.AbstractList, java.util.List
    public List subList(int i2, int i3) {
        AbstractC0174b.f320a.c(i2, i3, this.f355c);
        Object[] objArr = this.f353a;
        int i4 = this.f354b + i2;
        int i5 = i3 - i2;
        boolean z2 = this.f356d;
        a aVar = this.f358f;
        return new a(objArr, i4, i5, z2, this, aVar == null ? this : aVar);
    }

    public final Object t(int i2) {
        s();
        a aVar = this.f357e;
        if (aVar != null) {
            this.f355c--;
            return aVar.t(i2);
        }
        Object[] objArr = this.f353a;
        Object obj = objArr[i2];
        AbstractC0180h.g(objArr, objArr, i2, i2 + 1, this.f354b + this.f355c);
        J0.b.f(this.f353a, (this.f354b + this.f355c) - 1);
        this.f355c--;
        return obj;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public Object[] toArray(Object[] destination) {
        n.g(destination, "destination");
        l();
        int length = destination.length;
        int i2 = this.f355c;
        if (length >= i2) {
            Object[] objArr = this.f353a;
            int i3 = this.f354b;
            AbstractC0180h.g(objArr, destination, 0, i3, i2 + i3);
            return AbstractC0184l.e(this.f355c, destination);
        }
        Object[] objArr2 = this.f353a;
        int i4 = this.f354b;
        Object[] objArrCopyOfRange = Arrays.copyOfRange(objArr2, i4, i2 + i4, destination.getClass());
        n.f(objArrCopyOfRange, "copyOfRange(...)");
        return objArrCopyOfRange;
    }

    @Override // java.util.AbstractCollection
    public String toString() {
        l();
        return J0.b.j(this.f353a, this.f354b, this.f355c, this);
    }

    public final void u(int i2, int i3) {
        if (i3 > 0) {
            s();
        }
        a aVar = this.f357e;
        if (aVar != null) {
            aVar.u(i2, i3);
        } else {
            Object[] objArr = this.f353a;
            AbstractC0180h.g(objArr, objArr, i2, i2 + i3, this.f355c);
            Object[] objArr2 = this.f353a;
            int i4 = this.f355c;
            J0.b.g(objArr2, i4 - i3, i4);
        }
        this.f355c -= i3;
    }

    public final int v(int i2, int i3, Collection collection, boolean z2) {
        int iV;
        a aVar = this.f357e;
        if (aVar != null) {
            iV = aVar.v(i2, i3, collection, z2);
        } else {
            int i4 = 0;
            int i5 = 0;
            while (i4 < i3) {
                int i6 = i2 + i4;
                if (collection.contains(this.f353a[i6]) == z2) {
                    Object[] objArr = this.f353a;
                    i4++;
                    objArr[i5 + i2] = objArr[i6];
                    i5++;
                } else {
                    i4++;
                }
            }
            int i7 = i3 - i5;
            Object[] objArr2 = this.f353a;
            AbstractC0180h.g(objArr2, objArr2, i2 + i5, i3 + i2, this.f355c);
            Object[] objArr3 = this.f353a;
            int i8 = this.f355c;
            J0.b.g(objArr3, i8 - i7, i8);
            iV = i7;
        }
        if (iV > 0) {
            s();
        }
        this.f355c -= iV;
        return iV;
    }

    @Override // java.util.AbstractList, java.util.List
    public ListIterator listIterator(int i2) {
        l();
        AbstractC0174b.f320a.b(i2, this.f355c);
        return new b(this, i2);
    }

    @Override // java.util.AbstractList, java.util.List
    public void add(int i2, Object obj) {
        m();
        l();
        AbstractC0174b.f320a.b(i2, this.f355c);
        j(this.f354b + i2, obj);
    }

    @Override // java.util.AbstractList, java.util.List
    public boolean addAll(int i2, Collection elements) {
        n.g(elements, "elements");
        m();
        l();
        AbstractC0174b.f320a.b(i2, this.f355c);
        int size = elements.size();
        i(this.f354b + i2, elements, size);
        return size > 0;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public Object[] toArray() {
        l();
        Object[] objArr = this.f353a;
        int i2 = this.f354b;
        return AbstractC0180h.m(objArr, i2, this.f355c + i2);
    }

    public a(int i2) {
        this(J0.b.d(i2), 0, 0, false, null, null);
    }
}
