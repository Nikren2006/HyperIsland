package I0;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.NoSuchElementException;
import java.util.RandomAccess;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* JADX INFO: renamed from: I0.b, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes2.dex */
public abstract class AbstractC0174b extends AbstractC0173a implements List, W0.a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final a f320a = new a(null);

    /* JADX INFO: renamed from: I0.b$a */
    public static final class a {
        public /* synthetic */ a(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final void a(int i2, int i3) {
            if (i2 < 0 || i2 >= i3) {
                throw new IndexOutOfBoundsException("index: " + i2 + ", size: " + i3);
            }
        }

        public final void b(int i2, int i3) {
            if (i2 < 0 || i2 > i3) {
                throw new IndexOutOfBoundsException("index: " + i2 + ", size: " + i3);
            }
        }

        public final void c(int i2, int i3, int i4) {
            if (i2 < 0 || i3 > i4) {
                throw new IndexOutOfBoundsException("fromIndex: " + i2 + ", toIndex: " + i3 + ", size: " + i4);
            }
            if (i2 <= i3) {
                return;
            }
            throw new IllegalArgumentException("fromIndex: " + i2 + " > toIndex: " + i3);
        }

        public final int d(int i2, int i3) {
            int i4 = i2 + (i2 >> 1);
            if (i4 - i3 < 0) {
                i4 = i3;
            }
            if (i4 - 2147483639 > 0) {
                return i3 > 2147483639 ? Integer.MAX_VALUE : 2147483639;
            }
            return i4;
        }

        public final boolean e(Collection c2, Collection other) {
            kotlin.jvm.internal.n.g(c2, "c");
            kotlin.jvm.internal.n.g(other, "other");
            if (c2.size() != other.size()) {
                return false;
            }
            Iterator it = other.iterator();
            Iterator it2 = c2.iterator();
            while (it2.hasNext()) {
                if (!kotlin.jvm.internal.n.c(it2.next(), it.next())) {
                    return false;
                }
            }
            return true;
        }

        public final int f(Collection c2) {
            kotlin.jvm.internal.n.g(c2, "c");
            Iterator it = c2.iterator();
            int iHashCode = 1;
            while (it.hasNext()) {
                Object next = it.next();
                iHashCode = (iHashCode * 31) + (next != null ? next.hashCode() : 0);
            }
            return iHashCode;
        }

        public a() {
        }
    }

    /* JADX INFO: renamed from: I0.b$b, reason: collision with other inner class name */
    public class C0011b implements Iterator, W0.a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public int f321a;

        public C0011b() {
        }

        public final int c() {
            return this.f321a;
        }

        public final void d(int i2) {
            this.f321a = i2;
        }

        @Override // java.util.Iterator
        public boolean hasNext() {
            return this.f321a < AbstractC0174b.this.size();
        }

        @Override // java.util.Iterator
        public Object next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            AbstractC0174b abstractC0174b = AbstractC0174b.this;
            int i2 = this.f321a;
            this.f321a = i2 + 1;
            return abstractC0174b.get(i2);
        }

        @Override // java.util.Iterator
        public void remove() {
            throw new UnsupportedOperationException("Operation is not supported for read-only collection");
        }
    }

    /* JADX INFO: renamed from: I0.b$c */
    public class c extends C0011b implements ListIterator, W0.a {
        public c(int i2) {
            super();
            AbstractC0174b.f320a.b(i2, AbstractC0174b.this.size());
            d(i2);
        }

        @Override // java.util.ListIterator
        public void add(Object obj) {
            throw new UnsupportedOperationException("Operation is not supported for read-only collection");
        }

        @Override // java.util.ListIterator
        public boolean hasPrevious() {
            return c() > 0;
        }

        @Override // java.util.ListIterator
        public int nextIndex() {
            return c();
        }

        @Override // java.util.ListIterator
        public Object previous() {
            if (!hasPrevious()) {
                throw new NoSuchElementException();
            }
            AbstractC0174b abstractC0174b = AbstractC0174b.this;
            d(c() - 1);
            return abstractC0174b.get(c());
        }

        @Override // java.util.ListIterator
        public int previousIndex() {
            return c() - 1;
        }

        @Override // java.util.ListIterator
        public void set(Object obj) {
            throw new UnsupportedOperationException("Operation is not supported for read-only collection");
        }
    }

    /* JADX INFO: renamed from: I0.b$d */
    public static final class d extends AbstractC0174b implements RandomAccess {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final AbstractC0174b f324b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final int f325c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public int f326d;

        public d(AbstractC0174b list, int i2, int i3) {
            kotlin.jvm.internal.n.g(list, "list");
            this.f324b = list;
            this.f325c = i2;
            AbstractC0174b.f320a.c(i2, i3, list.size());
            this.f326d = i3 - i2;
        }

        @Override // I0.AbstractC0173a
        public int c() {
            return this.f326d;
        }

        @Override // I0.AbstractC0174b, java.util.List
        public Object get(int i2) {
            AbstractC0174b.f320a.a(i2, this.f326d);
            return this.f324b.get(this.f325c + i2);
        }
    }

    @Override // java.util.List
    public void add(int i2, Object obj) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    @Override // java.util.List
    public boolean addAll(int i2, Collection collection) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    @Override // java.util.Collection, java.util.List
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof List) {
            return f320a.e(this, (Collection) obj);
        }
        return false;
    }

    @Override // java.util.List
    public abstract Object get(int i2);

    @Override // java.util.Collection, java.util.List
    public int hashCode() {
        return f320a.f(this);
    }

    @Override // java.util.List
    public int indexOf(Object obj) {
        Iterator it = iterator();
        int i2 = 0;
        while (it.hasNext()) {
            if (kotlin.jvm.internal.n.c(it.next(), obj)) {
                return i2;
            }
            i2++;
        }
        return -1;
    }

    @Override // java.util.Collection, java.lang.Iterable, java.util.List
    public Iterator iterator() {
        return new C0011b();
    }

    @Override // java.util.List
    public int lastIndexOf(Object obj) {
        ListIterator listIterator = listIterator(size());
        while (listIterator.hasPrevious()) {
            if (kotlin.jvm.internal.n.c(listIterator.previous(), obj)) {
                return listIterator.nextIndex();
            }
        }
        return -1;
    }

    @Override // java.util.List
    public ListIterator listIterator() {
        return new c(0);
    }

    @Override // java.util.List
    public Object remove(int i2) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    @Override // java.util.List
    public Object set(int i2, Object obj) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    @Override // java.util.List
    public List subList(int i2, int i3) {
        return new d(this, i2, i3);
    }

    @Override // java.util.List
    public ListIterator listIterator(int i2) {
        return new c(i2);
    }
}
