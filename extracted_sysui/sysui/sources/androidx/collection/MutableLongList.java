package androidx.collection;

import I0.AbstractC0180h;
import I0.AbstractC0181i;
import androidx.annotation.IntRange;
import java.util.Arrays;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.n;

/* JADX INFO: loaded from: classes.dex */
public final class MutableLongList extends LongList {
    public MutableLongList() {
        this(0, 1, null);
    }

    public static /* synthetic */ void trim$default(MutableLongList mutableLongList, int i2, int i3, Object obj) {
        if ((i3 & 1) != 0) {
            i2 = mutableLongList._size;
        }
        mutableLongList.trim(i2);
    }

    public final boolean add(long j2) {
        ensureCapacity(this._size + 1);
        long[] jArr = this.content;
        int i2 = this._size;
        jArr[i2] = j2;
        this._size = i2 + 1;
        return true;
    }

    public final boolean addAll(@IntRange(from = 0) int i2, long[] elements) {
        int i3;
        n.g(elements, "elements");
        if (i2 < 0 || i2 > (i3 = this._size)) {
            throw new IndexOutOfBoundsException("Index " + i2 + " must be in 0.." + this._size);
        }
        if (elements.length == 0) {
            return false;
        }
        ensureCapacity(i3 + elements.length);
        long[] jArr = this.content;
        int i4 = this._size;
        if (i2 != i4) {
            AbstractC0180h.f(jArr, jArr, elements.length + i2, i2, i4);
        }
        AbstractC0180h.j(elements, jArr, i2, 0, 0, 12, null);
        this._size += elements.length;
        return true;
    }

    public final void clear() {
        this._size = 0;
    }

    public final void ensureCapacity(int i2) {
        long[] jArr = this.content;
        if (jArr.length < i2) {
            long[] jArrCopyOf = Arrays.copyOf(jArr, Math.max(i2, (jArr.length * 3) / 2));
            n.f(jArrCopyOf, "copyOf(this, newSize)");
            this.content = jArrCopyOf;
        }
    }

    public final int getCapacity() {
        return this.content.length;
    }

    public final void minusAssign(long j2) {
        remove(j2);
    }

    public final void plusAssign(LongList elements) {
        n.g(elements, "elements");
        addAll(this._size, elements);
    }

    public final boolean remove(long j2) {
        int iIndexOf = indexOf(j2);
        if (iIndexOf < 0) {
            return false;
        }
        removeAt(iIndexOf);
        return true;
    }

    public final boolean removeAll(long[] elements) {
        n.g(elements, "elements");
        int i2 = this._size;
        for (long j2 : elements) {
            remove(j2);
        }
        return i2 != this._size;
    }

    public final long removeAt(@IntRange(from = 0) int i2) {
        int i3;
        if (i2 < 0 || i2 >= (i3 = this._size)) {
            StringBuilder sb = new StringBuilder();
            sb.append("Index ");
            sb.append(i2);
            sb.append(" must be in 0..");
            sb.append(this._size - 1);
            throw new IndexOutOfBoundsException(sb.toString());
        }
        long[] jArr = this.content;
        long j2 = jArr[i2];
        if (i2 != i3 - 1) {
            AbstractC0180h.f(jArr, jArr, i2, i2 + 1, i3);
        }
        this._size--;
        return j2;
    }

    public final void removeRange(@IntRange(from = 0) int i2, @IntRange(from = 0) int i3) {
        int i4;
        if (i2 < 0 || i2 > (i4 = this._size) || i3 < 0 || i3 > i4) {
            throw new IndexOutOfBoundsException("Start (" + i2 + ") and end (" + i3 + ") must be in 0.." + this._size);
        }
        if (i3 >= i2) {
            if (i3 != i2) {
                if (i3 < i4) {
                    long[] jArr = this.content;
                    AbstractC0180h.f(jArr, jArr, i2, i3, i4);
                }
                this._size -= i3 - i2;
                return;
            }
            return;
        }
        throw new IllegalArgumentException("Start (" + i2 + ") is more than end (" + i3 + ')');
    }

    public final boolean retainAll(long[] elements) {
        n.g(elements, "elements");
        int i2 = this._size;
        long[] jArr = this.content;
        int i3 = i2 - 1;
        while (true) {
            int i4 = 0;
            int i5 = -1;
            if (-1 >= i3) {
                break;
            }
            long j2 = jArr[i3];
            int length = elements.length;
            while (true) {
                if (i4 >= length) {
                    break;
                }
                if (elements[i4] == j2) {
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

    public final long set(@IntRange(from = 0) int i2, long j2) {
        if (i2 >= 0 && i2 < this._size) {
            long[] jArr = this.content;
            long j3 = jArr[i2];
            jArr[i2] = j2;
            return j3;
        }
        StringBuilder sb = new StringBuilder();
        sb.append("set index ");
        sb.append(i2);
        sb.append(" must be between 0 .. ");
        sb.append(this._size - 1);
        throw new IndexOutOfBoundsException(sb.toString());
    }

    public final void sort() {
        AbstractC0180h.t(this.content, 0, this._size);
    }

    public final void sortDescending() {
        AbstractC0181i.P(this.content, 0, this._size);
    }

    public final void trim(int i2) {
        int iMax = Math.max(i2, this._size);
        long[] jArr = this.content;
        if (jArr.length > iMax) {
            long[] jArrCopyOf = Arrays.copyOf(jArr, iMax);
            n.f(jArrCopyOf, "copyOf(this, newSize)");
            this.content = jArrCopyOf;
        }
    }

    public /* synthetic */ MutableLongList(int i2, int i3, DefaultConstructorMarker defaultConstructorMarker) {
        this((i3 & 1) != 0 ? 16 : i2);
    }

    public final void minusAssign(long[] elements) {
        n.g(elements, "elements");
        for (long j2 : elements) {
            remove(j2);
        }
    }

    public final void plusAssign(long[] elements) {
        n.g(elements, "elements");
        addAll(this._size, elements);
    }

    public MutableLongList(int i2) {
        super(i2, null);
    }

    public final void plusAssign(long j2) {
        add(j2);
    }

    public final void add(@IntRange(from = 0) int i2, long j2) {
        int i3;
        if (i2 >= 0 && i2 <= (i3 = this._size)) {
            ensureCapacity(i3 + 1);
            long[] jArr = this.content;
            int i4 = this._size;
            if (i2 != i4) {
                AbstractC0180h.f(jArr, jArr, i2 + 1, i2, i4);
            }
            jArr[i2] = j2;
            this._size++;
            return;
        }
        throw new IndexOutOfBoundsException("Index " + i2 + " must be in 0.." + this._size);
    }

    public final void minusAssign(LongList elements) {
        n.g(elements, "elements");
        long[] jArr = elements.content;
        int i2 = elements._size;
        for (int i3 = 0; i3 < i2; i3++) {
            remove(jArr[i3]);
        }
    }

    public final boolean removeAll(LongList elements) {
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

    public final boolean retainAll(LongList elements) {
        n.g(elements, "elements");
        int i2 = this._size;
        long[] jArr = this.content;
        for (int i3 = i2 - 1; -1 < i3; i3--) {
            if (!elements.contains(jArr[i3])) {
                removeAt(i3);
            }
        }
        return i2 != this._size;
    }

    public final boolean addAll(@IntRange(from = 0) int i2, LongList elements) {
        n.g(elements, "elements");
        if (i2 >= 0 && i2 <= this._size) {
            if (elements.isEmpty()) {
                return false;
            }
            ensureCapacity(this._size + elements._size);
            long[] jArr = this.content;
            int i3 = this._size;
            if (i2 != i3) {
                AbstractC0180h.f(jArr, jArr, elements._size + i2, i2, i3);
            }
            AbstractC0180h.f(elements.content, jArr, i2, 0, elements._size);
            this._size += elements._size;
            return true;
        }
        throw new IndexOutOfBoundsException("Index " + i2 + " must be in 0.." + this._size);
    }

    public final boolean addAll(LongList elements) {
        n.g(elements, "elements");
        return addAll(this._size, elements);
    }

    public final boolean addAll(long[] elements) {
        n.g(elements, "elements");
        return addAll(this._size, elements);
    }
}
