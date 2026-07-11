package androidx.collection;

import I0.AbstractC0180h;
import I0.AbstractC0181i;
import androidx.annotation.IntRange;
import java.util.Arrays;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.n;

/* JADX INFO: loaded from: classes.dex */
public final class MutableIntList extends IntList {
    public MutableIntList() {
        this(0, 1, null);
    }

    public static /* synthetic */ void trim$default(MutableIntList mutableIntList, int i2, int i3, Object obj) {
        if ((i3 & 1) != 0) {
            i2 = mutableIntList._size;
        }
        mutableIntList.trim(i2);
    }

    public final boolean add(int i2) {
        ensureCapacity(this._size + 1);
        int[] iArr = this.content;
        int i3 = this._size;
        iArr[i3] = i2;
        this._size = i3 + 1;
        return true;
    }

    public final boolean addAll(@IntRange(from = 0) int i2, int[] elements) {
        int i3;
        n.g(elements, "elements");
        if (i2 < 0 || i2 > (i3 = this._size)) {
            throw new IndexOutOfBoundsException("Index " + i2 + " must be in 0.." + this._size);
        }
        if (elements.length == 0) {
            return false;
        }
        ensureCapacity(i3 + elements.length);
        int[] iArr = this.content;
        int i4 = this._size;
        if (i2 != i4) {
            AbstractC0180h.e(iArr, iArr, elements.length + i2, i2, i4);
        }
        AbstractC0180h.i(elements, iArr, i2, 0, 0, 12, null);
        this._size += elements.length;
        return true;
    }

    public final void clear() {
        this._size = 0;
    }

    public final void ensureCapacity(int i2) {
        int[] iArr = this.content;
        if (iArr.length < i2) {
            int[] iArrCopyOf = Arrays.copyOf(iArr, Math.max(i2, (iArr.length * 3) / 2));
            n.f(iArrCopyOf, "copyOf(this, newSize)");
            this.content = iArrCopyOf;
        }
    }

    public final int getCapacity() {
        return this.content.length;
    }

    public final void minusAssign(int i2) {
        remove(i2);
    }

    public final void plusAssign(IntList elements) {
        n.g(elements, "elements");
        addAll(this._size, elements);
    }

    public final boolean remove(int i2) {
        int iIndexOf = indexOf(i2);
        if (iIndexOf < 0) {
            return false;
        }
        removeAt(iIndexOf);
        return true;
    }

    public final boolean removeAll(int[] elements) {
        n.g(elements, "elements");
        int i2 = this._size;
        for (int i3 : elements) {
            remove(i3);
        }
        return i2 != this._size;
    }

    public final int removeAt(@IntRange(from = 0) int i2) {
        int i3;
        if (i2 < 0 || i2 >= (i3 = this._size)) {
            StringBuilder sb = new StringBuilder();
            sb.append("Index ");
            sb.append(i2);
            sb.append(" must be in 0..");
            sb.append(this._size - 1);
            throw new IndexOutOfBoundsException(sb.toString());
        }
        int[] iArr = this.content;
        int i4 = iArr[i2];
        if (i2 != i3 - 1) {
            AbstractC0180h.e(iArr, iArr, i2, i2 + 1, i3);
        }
        this._size--;
        return i4;
    }

    public final void removeRange(@IntRange(from = 0) int i2, @IntRange(from = 0) int i3) {
        int i4;
        if (i2 < 0 || i2 > (i4 = this._size) || i3 < 0 || i3 > i4) {
            throw new IndexOutOfBoundsException("Start (" + i2 + ") and end (" + i3 + ") must be in 0.." + this._size);
        }
        if (i3 >= i2) {
            if (i3 != i2) {
                if (i3 < i4) {
                    int[] iArr = this.content;
                    AbstractC0180h.e(iArr, iArr, i2, i3, i4);
                }
                this._size -= i3 - i2;
                return;
            }
            return;
        }
        throw new IllegalArgumentException("Start (" + i2 + ") is more than end (" + i3 + ')');
    }

    public final boolean retainAll(int[] elements) {
        n.g(elements, "elements");
        int i2 = this._size;
        int[] iArr = this.content;
        int i3 = i2 - 1;
        while (true) {
            int i4 = 0;
            int i5 = -1;
            if (-1 >= i3) {
                break;
            }
            int i6 = iArr[i3];
            int length = elements.length;
            while (true) {
                if (i4 >= length) {
                    break;
                }
                if (elements[i4] == i6) {
                    i5 = i4;
                    break;
                }
                i4++;
            }
            if (i5 < 0) {
                removeAt(i3);
            }
            i3--;
        }
        return i2 != this._size;
    }

    public final int set(@IntRange(from = 0) int i2, int i3) {
        if (i2 >= 0 && i2 < this._size) {
            int[] iArr = this.content;
            int i4 = iArr[i2];
            iArr[i2] = i3;
            return i4;
        }
        StringBuilder sb = new StringBuilder();
        sb.append("set index ");
        sb.append(i2);
        sb.append(" must be between 0 .. ");
        sb.append(this._size - 1);
        throw new IndexOutOfBoundsException(sb.toString());
    }

    public final void sort() {
        AbstractC0180h.s(this.content, 0, this._size);
    }

    public final void sortDescending() {
        AbstractC0181i.O(this.content, 0, this._size);
    }

    public final void trim(int i2) {
        int iMax = Math.max(i2, this._size);
        int[] iArr = this.content;
        if (iArr.length > iMax) {
            int[] iArrCopyOf = Arrays.copyOf(iArr, iMax);
            n.f(iArrCopyOf, "copyOf(this, newSize)");
            this.content = iArrCopyOf;
        }
    }

    public /* synthetic */ MutableIntList(int i2, int i3, DefaultConstructorMarker defaultConstructorMarker) {
        this((i3 & 1) != 0 ? 16 : i2);
    }

    public final void minusAssign(int[] elements) {
        n.g(elements, "elements");
        for (int i2 : elements) {
            remove(i2);
        }
    }

    public final void plusAssign(int[] elements) {
        n.g(elements, "elements");
        addAll(this._size, elements);
    }

    public MutableIntList(int i2) {
        super(i2, null);
    }

    public final void plusAssign(int i2) {
        add(i2);
    }

    public final void add(@IntRange(from = 0) int i2, int i3) {
        int i4;
        if (i2 >= 0 && i2 <= (i4 = this._size)) {
            ensureCapacity(i4 + 1);
            int[] iArr = this.content;
            int i5 = this._size;
            if (i2 != i5) {
                AbstractC0180h.e(iArr, iArr, i2 + 1, i2, i5);
            }
            iArr[i2] = i3;
            this._size++;
            return;
        }
        throw new IndexOutOfBoundsException("Index " + i2 + " must be in 0.." + this._size);
    }

    public final void minusAssign(IntList elements) {
        n.g(elements, "elements");
        int[] iArr = elements.content;
        int i2 = elements._size;
        for (int i3 = 0; i3 < i2; i3++) {
            remove(iArr[i3]);
        }
    }

    public final boolean removeAll(IntList elements) {
        n.g(elements, "elements");
        int i2 = this._size;
        int i3 = elements._size - 1;
        if (i3 >= 0) {
            int i4 = 0;
            while (true) {
                remove(elements.get(i4));
                if (i4 == i3) {
                    break;
                }
                i4++;
            }
        }
        return i2 != this._size;
    }

    public final boolean retainAll(IntList elements) {
        n.g(elements, "elements");
        int i2 = this._size;
        int[] iArr = this.content;
        for (int i3 = i2 - 1; -1 < i3; i3--) {
            if (!elements.contains(iArr[i3])) {
                removeAt(i3);
            }
        }
        return i2 != this._size;
    }

    public final boolean addAll(@IntRange(from = 0) int i2, IntList elements) {
        n.g(elements, "elements");
        if (i2 >= 0 && i2 <= this._size) {
            if (elements.isEmpty()) {
                return false;
            }
            ensureCapacity(this._size + elements._size);
            int[] iArr = this.content;
            int i3 = this._size;
            if (i2 != i3) {
                AbstractC0180h.e(iArr, iArr, elements._size + i2, i2, i3);
            }
            AbstractC0180h.e(elements.content, iArr, i2, 0, elements._size);
            this._size += elements._size;
            return true;
        }
        throw new IndexOutOfBoundsException("Index " + i2 + " must be in 0.." + this._size);
    }

    public final boolean addAll(IntList elements) {
        n.g(elements, "elements");
        return addAll(this._size, elements);
    }

    public final boolean addAll(int[] elements) {
        n.g(elements, "elements");
        return addAll(this._size, elements);
    }
}
