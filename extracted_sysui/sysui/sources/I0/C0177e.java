package I0;

import java.util.Collection;
import java.util.Iterator;
import java.util.NoSuchElementException;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* JADX INFO: renamed from: I0.e, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes2.dex */
public final class C0177e extends AbstractC0175c {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static final a f329d = new a(null);

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static final Object[] f330e = new Object[0];

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public int f331a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public Object[] f332b = f330e;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public int f333c;

    /* JADX INFO: renamed from: I0.e$a */
    public static final class a {
        public /* synthetic */ a(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public a() {
        }
    }

    private final void h(int i2) {
        if (i2 < 0) {
            throw new IllegalStateException("Deque is too big.");
        }
        Object[] objArr = this.f332b;
        if (i2 <= objArr.length) {
            return;
        }
        if (objArr == f330e) {
            this.f332b = new Object[c1.f.c(i2, 10)];
        } else {
            f(AbstractC0174b.f320a.d(objArr.length, i2));
        }
    }

    @Override // java.util.AbstractList, java.util.AbstractCollection, java.util.Collection, java.util.List
    public boolean add(Object obj) {
        addLast(obj);
        return true;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public boolean addAll(Collection elements) {
        kotlin.jvm.internal.n.g(elements, "elements");
        if (elements.isEmpty()) {
            return false;
        }
        h(size() + elements.size());
        e(k(this.f331a + size()), elements);
        return true;
    }

    public final void addFirst(Object obj) {
        h(size() + 1);
        int iG = g(this.f331a);
        this.f331a = iG;
        this.f332b[iG] = obj;
        this.f333c = size() + 1;
    }

    public final void addLast(Object obj) {
        h(size() + 1);
        this.f332b[k(this.f331a + size())] = obj;
        this.f333c = size() + 1;
    }

    @Override // I0.AbstractC0175c
    public int c() {
        return this.f333c;
    }

    @Override // java.util.AbstractList, java.util.AbstractCollection, java.util.Collection, java.util.List
    public void clear() {
        int iK = k(this.f331a + size());
        int i2 = this.f331a;
        if (i2 < iK) {
            AbstractC0180h.o(this.f332b, null, i2, iK);
        } else if (!isEmpty()) {
            Object[] objArr = this.f332b;
            AbstractC0180h.o(objArr, null, this.f331a, objArr.length);
            AbstractC0180h.o(this.f332b, null, 0, iK);
        }
        this.f331a = 0;
        this.f333c = 0;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public boolean contains(Object obj) {
        return indexOf(obj) != -1;
    }

    @Override // I0.AbstractC0175c
    public Object d(int i2) {
        AbstractC0174b.f320a.a(i2, size());
        if (i2 == m.i(this)) {
            return removeLast();
        }
        if (i2 == 0) {
            return removeFirst();
        }
        int iK = k(this.f331a + i2);
        Object obj = this.f332b[iK];
        if (i2 < (size() >> 1)) {
            int i3 = this.f331a;
            if (iK >= i3) {
                Object[] objArr = this.f332b;
                AbstractC0180h.g(objArr, objArr, i3 + 1, i3, iK);
            } else {
                Object[] objArr2 = this.f332b;
                AbstractC0180h.g(objArr2, objArr2, 1, 0, iK);
                Object[] objArr3 = this.f332b;
                objArr3[0] = objArr3[objArr3.length - 1];
                int i4 = this.f331a;
                AbstractC0180h.g(objArr3, objArr3, i4 + 1, i4, objArr3.length - 1);
            }
            Object[] objArr4 = this.f332b;
            int i5 = this.f331a;
            objArr4[i5] = null;
            this.f331a = i(i5);
        } else {
            int iK2 = k(this.f331a + m.i(this));
            if (iK <= iK2) {
                Object[] objArr5 = this.f332b;
                AbstractC0180h.g(objArr5, objArr5, iK, iK + 1, iK2 + 1);
            } else {
                Object[] objArr6 = this.f332b;
                AbstractC0180h.g(objArr6, objArr6, iK, iK + 1, objArr6.length);
                Object[] objArr7 = this.f332b;
                objArr7[objArr7.length - 1] = objArr7[0];
                AbstractC0180h.g(objArr7, objArr7, 0, 1, iK2 + 1);
            }
            this.f332b[iK2] = null;
        }
        this.f333c = size() - 1;
        return obj;
    }

    public final void e(int i2, Collection collection) {
        Iterator it = collection.iterator();
        int length = this.f332b.length;
        while (i2 < length && it.hasNext()) {
            this.f332b[i2] = it.next();
            i2++;
        }
        int i3 = this.f331a;
        for (int i4 = 0; i4 < i3 && it.hasNext(); i4++) {
            this.f332b[i4] = it.next();
        }
        this.f333c = size() + collection.size();
    }

    public final void f(int i2) {
        Object[] objArr = new Object[i2];
        Object[] objArr2 = this.f332b;
        AbstractC0180h.g(objArr2, objArr, 0, this.f331a, objArr2.length);
        Object[] objArr3 = this.f332b;
        int length = objArr3.length;
        int i3 = this.f331a;
        AbstractC0180h.g(objArr3, objArr, length - i3, 0, i3);
        this.f331a = 0;
        this.f332b = objArr;
    }

    public final int g(int i2) {
        return i2 == 0 ? AbstractC0181i.B(this.f332b) : i2 - 1;
    }

    @Override // java.util.AbstractList, java.util.List
    public Object get(int i2) {
        AbstractC0174b.f320a.a(i2, size());
        return this.f332b[k(this.f331a + i2)];
    }

    public final int i(int i2) {
        if (i2 == AbstractC0181i.B(this.f332b)) {
            return 0;
        }
        return i2 + 1;
    }

    @Override // java.util.AbstractList, java.util.List
    public int indexOf(Object obj) {
        int i2;
        int iK = k(this.f331a + size());
        int length = this.f331a;
        if (length < iK) {
            while (length < iK) {
                if (kotlin.jvm.internal.n.c(obj, this.f332b[length])) {
                    i2 = this.f331a;
                } else {
                    length++;
                }
            }
            return -1;
        }
        if (length < iK) {
            return -1;
        }
        int length2 = this.f332b.length;
        while (true) {
            if (length >= length2) {
                for (int i3 = 0; i3 < iK; i3++) {
                    if (kotlin.jvm.internal.n.c(obj, this.f332b[i3])) {
                        length = i3 + this.f332b.length;
                        i2 = this.f331a;
                    }
                }
                return -1;
            }
            if (kotlin.jvm.internal.n.c(obj, this.f332b[length])) {
                i2 = this.f331a;
                break;
            }
            length++;
        }
        return length - i2;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public boolean isEmpty() {
        return size() == 0;
    }

    public final int j(int i2) {
        return i2 < 0 ? i2 + this.f332b.length : i2;
    }

    public final int k(int i2) {
        Object[] objArr = this.f332b;
        return i2 >= objArr.length ? i2 - objArr.length : i2;
    }

    public final Object l() {
        if (isEmpty()) {
            return null;
        }
        return removeFirst();
    }

    @Override // java.util.AbstractList, java.util.List
    public int lastIndexOf(Object obj) {
        int iB;
        int i2;
        int iK = k(this.f331a + size());
        int i3 = this.f331a;
        if (i3 < iK) {
            iB = iK - 1;
            if (i3 <= iB) {
                while (!kotlin.jvm.internal.n.c(obj, this.f332b[iB])) {
                    if (iB != i3) {
                        iB--;
                    }
                }
                i2 = this.f331a;
                return iB - i2;
            }
            return -1;
        }
        if (i3 > iK) {
            int i4 = iK - 1;
            while (true) {
                if (-1 >= i4) {
                    iB = AbstractC0181i.B(this.f332b);
                    int i5 = this.f331a;
                    if (i5 <= iB) {
                        while (!kotlin.jvm.internal.n.c(obj, this.f332b[iB])) {
                            if (iB != i5) {
                                iB--;
                            }
                        }
                        i2 = this.f331a;
                    }
                } else {
                    if (kotlin.jvm.internal.n.c(obj, this.f332b[i4])) {
                        iB = i4 + this.f332b.length;
                        i2 = this.f331a;
                        break;
                    }
                    i4--;
                }
            }
        }
        return -1;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public boolean remove(Object obj) {
        int iIndexOf = indexOf(obj);
        if (iIndexOf == -1) {
            return false;
        }
        remove(iIndexOf);
        return true;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public boolean removeAll(Collection elements) {
        int iK;
        kotlin.jvm.internal.n.g(elements, "elements");
        boolean z2 = false;
        z2 = false;
        z2 = false;
        if (!isEmpty() && this.f332b.length != 0) {
            int iK2 = k(this.f331a + size());
            int i2 = this.f331a;
            if (i2 < iK2) {
                iK = i2;
                while (i2 < iK2) {
                    Object obj = this.f332b[i2];
                    if (elements.contains(obj)) {
                        z2 = true;
                    } else {
                        this.f332b[iK] = obj;
                        iK++;
                    }
                    i2++;
                }
                AbstractC0180h.o(this.f332b, null, iK, iK2);
            } else {
                int length = this.f332b.length;
                boolean z3 = false;
                int i3 = i2;
                while (i2 < length) {
                    Object[] objArr = this.f332b;
                    Object obj2 = objArr[i2];
                    objArr[i2] = null;
                    if (elements.contains(obj2)) {
                        z3 = true;
                    } else {
                        this.f332b[i3] = obj2;
                        i3++;
                    }
                    i2++;
                }
                iK = k(i3);
                for (int i4 = 0; i4 < iK2; i4++) {
                    Object[] objArr2 = this.f332b;
                    Object obj3 = objArr2[i4];
                    objArr2[i4] = null;
                    if (elements.contains(obj3)) {
                        z3 = true;
                    } else {
                        this.f332b[iK] = obj3;
                        iK = i(iK);
                    }
                }
                z2 = z3;
            }
            if (z2) {
                this.f333c = j(iK - this.f331a);
            }
        }
        return z2;
    }

    public final Object removeFirst() {
        if (isEmpty()) {
            throw new NoSuchElementException("ArrayDeque is empty.");
        }
        Object[] objArr = this.f332b;
        int i2 = this.f331a;
        Object obj = objArr[i2];
        objArr[i2] = null;
        this.f331a = i(i2);
        this.f333c = size() - 1;
        return obj;
    }

    public final Object removeLast() {
        if (isEmpty()) {
            throw new NoSuchElementException("ArrayDeque is empty.");
        }
        int iK = k(this.f331a + m.i(this));
        Object[] objArr = this.f332b;
        Object obj = objArr[iK];
        objArr[iK] = null;
        this.f333c = size() - 1;
        return obj;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public boolean retainAll(Collection elements) {
        int iK;
        kotlin.jvm.internal.n.g(elements, "elements");
        boolean z2 = false;
        z2 = false;
        z2 = false;
        if (!isEmpty() && this.f332b.length != 0) {
            int iK2 = k(this.f331a + size());
            int i2 = this.f331a;
            if (i2 < iK2) {
                iK = i2;
                while (i2 < iK2) {
                    Object obj = this.f332b[i2];
                    if (elements.contains(obj)) {
                        this.f332b[iK] = obj;
                        iK++;
                    } else {
                        z2 = true;
                    }
                    i2++;
                }
                AbstractC0180h.o(this.f332b, null, iK, iK2);
            } else {
                int length = this.f332b.length;
                boolean z3 = false;
                int i3 = i2;
                while (i2 < length) {
                    Object[] objArr = this.f332b;
                    Object obj2 = objArr[i2];
                    objArr[i2] = null;
                    if (elements.contains(obj2)) {
                        this.f332b[i3] = obj2;
                        i3++;
                    } else {
                        z3 = true;
                    }
                    i2++;
                }
                iK = k(i3);
                for (int i4 = 0; i4 < iK2; i4++) {
                    Object[] objArr2 = this.f332b;
                    Object obj3 = objArr2[i4];
                    objArr2[i4] = null;
                    if (elements.contains(obj3)) {
                        this.f332b[iK] = obj3;
                        iK = i(iK);
                    } else {
                        z3 = true;
                    }
                }
                z2 = z3;
            }
            if (z2) {
                this.f333c = j(iK - this.f331a);
            }
        }
        return z2;
    }

    @Override // java.util.AbstractList, java.util.List
    public Object set(int i2, Object obj) {
        AbstractC0174b.f320a.a(i2, size());
        int iK = k(this.f331a + i2);
        Object[] objArr = this.f332b;
        Object obj2 = objArr[iK];
        objArr[iK] = obj;
        return obj2;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public Object[] toArray(Object[] array) {
        kotlin.jvm.internal.n.g(array, "array");
        if (array.length < size()) {
            array = AbstractC0178f.a(array, size());
        }
        int iK = k(this.f331a + size());
        int i2 = this.f331a;
        if (i2 < iK) {
            AbstractC0180h.k(this.f332b, array, 0, i2, iK, 2, null);
        } else if (!isEmpty()) {
            Object[] objArr = this.f332b;
            AbstractC0180h.g(objArr, array, 0, this.f331a, objArr.length);
            Object[] objArr2 = this.f332b;
            AbstractC0180h.g(objArr2, array, objArr2.length - this.f331a, 0, iK);
        }
        return AbstractC0184l.e(size(), array);
    }

    @Override // java.util.AbstractList, java.util.List
    public void add(int i2, Object obj) {
        AbstractC0174b.f320a.b(i2, size());
        if (i2 == size()) {
            addLast(obj);
            return;
        }
        if (i2 == 0) {
            addFirst(obj);
            return;
        }
        h(size() + 1);
        int iK = k(this.f331a + i2);
        if (i2 < ((size() + 1) >> 1)) {
            int iG = g(iK);
            int iG2 = g(this.f331a);
            int i3 = this.f331a;
            if (iG >= i3) {
                Object[] objArr = this.f332b;
                objArr[iG2] = objArr[i3];
                AbstractC0180h.g(objArr, objArr, i3, i3 + 1, iG + 1);
            } else {
                Object[] objArr2 = this.f332b;
                AbstractC0180h.g(objArr2, objArr2, i3 - 1, i3, objArr2.length);
                Object[] objArr3 = this.f332b;
                objArr3[objArr3.length - 1] = objArr3[0];
                AbstractC0180h.g(objArr3, objArr3, 0, 1, iG + 1);
            }
            this.f332b[iG] = obj;
            this.f331a = iG2;
        } else {
            int iK2 = k(this.f331a + size());
            if (iK < iK2) {
                Object[] objArr4 = this.f332b;
                AbstractC0180h.g(objArr4, objArr4, iK + 1, iK, iK2);
            } else {
                Object[] objArr5 = this.f332b;
                AbstractC0180h.g(objArr5, objArr5, 1, 0, iK2);
                Object[] objArr6 = this.f332b;
                objArr6[0] = objArr6[objArr6.length - 1];
                AbstractC0180h.g(objArr6, objArr6, iK + 1, iK, objArr6.length - 1);
            }
            this.f332b[iK] = obj;
        }
        this.f333c = size() + 1;
    }

    @Override // java.util.AbstractList, java.util.List
    public boolean addAll(int i2, Collection elements) {
        kotlin.jvm.internal.n.g(elements, "elements");
        AbstractC0174b.f320a.b(i2, size());
        if (elements.isEmpty()) {
            return false;
        }
        if (i2 == size()) {
            return addAll(elements);
        }
        h(size() + elements.size());
        int iK = k(this.f331a + size());
        int iK2 = k(this.f331a + i2);
        int size = elements.size();
        if (i2 < ((size() + 1) >> 1)) {
            int i3 = this.f331a;
            int length = i3 - size;
            if (iK2 < i3) {
                Object[] objArr = this.f332b;
                AbstractC0180h.g(objArr, objArr, length, i3, objArr.length);
                if (size >= iK2) {
                    Object[] objArr2 = this.f332b;
                    AbstractC0180h.g(objArr2, objArr2, objArr2.length - size, 0, iK2);
                } else {
                    Object[] objArr3 = this.f332b;
                    AbstractC0180h.g(objArr3, objArr3, objArr3.length - size, 0, size);
                    Object[] objArr4 = this.f332b;
                    AbstractC0180h.g(objArr4, objArr4, 0, size, iK2);
                }
            } else if (length >= 0) {
                Object[] objArr5 = this.f332b;
                AbstractC0180h.g(objArr5, objArr5, length, i3, iK2);
            } else {
                Object[] objArr6 = this.f332b;
                length += objArr6.length;
                int i4 = iK2 - i3;
                int length2 = objArr6.length - length;
                if (length2 >= i4) {
                    AbstractC0180h.g(objArr6, objArr6, length, i3, iK2);
                } else {
                    AbstractC0180h.g(objArr6, objArr6, length, i3, i3 + length2);
                    Object[] objArr7 = this.f332b;
                    AbstractC0180h.g(objArr7, objArr7, 0, this.f331a + length2, iK2);
                }
            }
            this.f331a = length;
            e(j(iK2 - size), elements);
        } else {
            int i5 = iK2 + size;
            if (iK2 < iK) {
                int i6 = size + iK;
                Object[] objArr8 = this.f332b;
                if (i6 <= objArr8.length) {
                    AbstractC0180h.g(objArr8, objArr8, i5, iK2, iK);
                } else if (i5 >= objArr8.length) {
                    AbstractC0180h.g(objArr8, objArr8, i5 - objArr8.length, iK2, iK);
                } else {
                    int length3 = iK - (i6 - objArr8.length);
                    AbstractC0180h.g(objArr8, objArr8, 0, length3, iK);
                    Object[] objArr9 = this.f332b;
                    AbstractC0180h.g(objArr9, objArr9, i5, iK2, length3);
                }
            } else {
                Object[] objArr10 = this.f332b;
                AbstractC0180h.g(objArr10, objArr10, size, 0, iK);
                Object[] objArr11 = this.f332b;
                if (i5 >= objArr11.length) {
                    AbstractC0180h.g(objArr11, objArr11, i5 - objArr11.length, iK2, objArr11.length);
                } else {
                    AbstractC0180h.g(objArr11, objArr11, 0, objArr11.length - size, objArr11.length);
                    Object[] objArr12 = this.f332b;
                    AbstractC0180h.g(objArr12, objArr12, i5, iK2, objArr12.length - size);
                }
            }
            e(iK2, elements);
        }
        return true;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public Object[] toArray() {
        return toArray(new Object[size()]);
    }
}
