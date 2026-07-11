package androidx.collection;

import I0.AbstractC0180h;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.n;

/* JADX INFO: loaded from: classes.dex */
public final class CircularArray<E> {
    private int capacityBitmask;
    private E[] elements;
    private int head;
    private int tail;

    public CircularArray() {
        this(0, 1, null);
    }

    private final void doubleCapacity() {
        E[] eArr = this.elements;
        int length = eArr.length;
        int i2 = this.head;
        int i3 = length - i2;
        int i4 = length << 1;
        if (i4 < 0) {
            throw new RuntimeException("Max array capacity exceeded");
        }
        E[] eArr2 = (E[]) new Object[i4];
        AbstractC0180h.g(eArr, eArr2, 0, i2, length);
        AbstractC0180h.g(this.elements, eArr2, i3, 0, this.head);
        this.elements = eArr2;
        this.head = 0;
        this.tail = length;
        this.capacityBitmask = i4 - 1;
    }

    public final void addFirst(E e2) {
        int i2 = (this.head - 1) & this.capacityBitmask;
        this.head = i2;
        this.elements[i2] = e2;
        if (i2 == this.tail) {
            doubleCapacity();
        }
    }

    public final void addLast(E e2) {
        E[] eArr = this.elements;
        int i2 = this.tail;
        eArr[i2] = e2;
        int i3 = this.capacityBitmask & (i2 + 1);
        this.tail = i3;
        if (i3 == this.head) {
            doubleCapacity();
        }
    }

    public final void clear() {
        removeFromStart(size());
    }

    public final E get(int i2) {
        if (i2 < 0 || i2 >= size()) {
            CollectionPlatformUtils collectionPlatformUtils = CollectionPlatformUtils.INSTANCE;
            throw new ArrayIndexOutOfBoundsException();
        }
        E e2 = this.elements[this.capacityBitmask & (this.head + i2)];
        n.d(e2);
        return e2;
    }

    public final E getFirst() {
        int i2 = this.head;
        if (i2 == this.tail) {
            CollectionPlatformUtils collectionPlatformUtils = CollectionPlatformUtils.INSTANCE;
            throw new ArrayIndexOutOfBoundsException();
        }
        E e2 = this.elements[i2];
        n.d(e2);
        return e2;
    }

    public final E getLast() {
        int i2 = this.head;
        int i3 = this.tail;
        if (i2 == i3) {
            CollectionPlatformUtils collectionPlatformUtils = CollectionPlatformUtils.INSTANCE;
            throw new ArrayIndexOutOfBoundsException();
        }
        E e2 = this.elements[this.capacityBitmask & (i3 - 1)];
        n.d(e2);
        return e2;
    }

    public final boolean isEmpty() {
        return this.head == this.tail;
    }

    public final E popFirst() {
        int i2 = this.head;
        if (i2 == this.tail) {
            CollectionPlatformUtils collectionPlatformUtils = CollectionPlatformUtils.INSTANCE;
            throw new ArrayIndexOutOfBoundsException();
        }
        E[] eArr = this.elements;
        E e2 = eArr[i2];
        eArr[i2] = null;
        this.head = (i2 + 1) & this.capacityBitmask;
        return e2;
    }

    public final E popLast() {
        int i2 = this.head;
        int i3 = this.tail;
        if (i2 == i3) {
            CollectionPlatformUtils collectionPlatformUtils = CollectionPlatformUtils.INSTANCE;
            throw new ArrayIndexOutOfBoundsException();
        }
        int i4 = this.capacityBitmask & (i3 - 1);
        E[] eArr = this.elements;
        E e2 = eArr[i4];
        eArr[i4] = null;
        this.tail = i4;
        return e2;
    }

    public final void removeFromEnd(int i2) {
        if (i2 <= 0) {
            return;
        }
        if (i2 > size()) {
            CollectionPlatformUtils collectionPlatformUtils = CollectionPlatformUtils.INSTANCE;
            throw new ArrayIndexOutOfBoundsException();
        }
        int i3 = this.tail;
        int i4 = i2 < i3 ? i3 - i2 : 0;
        for (int i5 = i4; i5 < i3; i5++) {
            this.elements[i5] = null;
        }
        int i6 = this.tail;
        int i7 = i6 - i4;
        int i8 = i2 - i7;
        this.tail = i6 - i7;
        if (i8 > 0) {
            int length = this.elements.length;
            this.tail = length;
            int i9 = length - i8;
            for (int i10 = i9; i10 < length; i10++) {
                this.elements[i10] = null;
            }
            this.tail = i9;
        }
    }

    public final void removeFromStart(int i2) {
        if (i2 <= 0) {
            return;
        }
        if (i2 > size()) {
            CollectionPlatformUtils collectionPlatformUtils = CollectionPlatformUtils.INSTANCE;
            throw new ArrayIndexOutOfBoundsException();
        }
        int length = this.elements.length;
        int i3 = this.head;
        if (i2 < length - i3) {
            length = i3 + i2;
        }
        while (i3 < length) {
            this.elements[i3] = null;
            i3++;
        }
        int i4 = this.head;
        int i5 = length - i4;
        int i6 = i2 - i5;
        this.head = this.capacityBitmask & (i4 + i5);
        if (i6 > 0) {
            for (int i7 = 0; i7 < i6; i7++) {
                this.elements[i7] = null;
            }
            this.head = i6;
        }
    }

    public final int size() {
        return this.capacityBitmask & (this.tail - this.head);
    }

    public CircularArray(int i2) {
        if (i2 < 1) {
            throw new IllegalArgumentException("capacity must be >= 1");
        }
        if (i2 > 1073741824) {
            throw new IllegalArgumentException("capacity must be <= 2^30");
        }
        i2 = Integer.bitCount(i2) != 1 ? Integer.highestOneBit(i2 - 1) << 1 : i2;
        this.capacityBitmask = i2 - 1;
        this.elements = (E[]) new Object[i2];
    }

    public /* synthetic */ CircularArray(int i2, int i3, DefaultConstructorMarker defaultConstructorMarker) {
        this((i3 & 1) != 0 ? 8 : i2);
    }
}
