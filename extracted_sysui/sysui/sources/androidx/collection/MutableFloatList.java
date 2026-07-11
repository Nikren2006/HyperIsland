package androidx.collection;

import I0.AbstractC0180h;
import I0.AbstractC0181i;
import androidx.annotation.IntRange;
import java.util.Arrays;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.n;

/* JADX INFO: loaded from: classes.dex */
public final class MutableFloatList extends FloatList {
    public MutableFloatList() {
        this(0, 1, null);
    }

    public static /* synthetic */ void trim$default(MutableFloatList mutableFloatList, int i2, int i3, Object obj) {
        if ((i3 & 1) != 0) {
            i2 = mutableFloatList._size;
        }
        mutableFloatList.trim(i2);
    }

    public final boolean add(float f2) {
        ensureCapacity(this._size + 1);
        float[] fArr = this.content;
        int i2 = this._size;
        fArr[i2] = f2;
        this._size = i2 + 1;
        return true;
    }

    public final boolean addAll(@IntRange(from = 0) int i2, float[] elements) {
        int i3;
        n.g(elements, "elements");
        if (i2 < 0 || i2 > (i3 = this._size)) {
            throw new IndexOutOfBoundsException("Index " + i2 + " must be in 0.." + this._size);
        }
        if (elements.length == 0) {
            return false;
        }
        ensureCapacity(i3 + elements.length);
        float[] fArr = this.content;
        int i4 = this._size;
        if (i2 != i4) {
            AbstractC0180h.d(fArr, fArr, elements.length + i2, i2, i4);
        }
        AbstractC0180h.h(elements, fArr, i2, 0, 0, 12, null);
        this._size += elements.length;
        return true;
    }

    public final void clear() {
        this._size = 0;
    }

    public final void ensureCapacity(int i2) {
        float[] fArr = this.content;
        if (fArr.length < i2) {
            float[] fArrCopyOf = Arrays.copyOf(fArr, Math.max(i2, (fArr.length * 3) / 2));
            n.f(fArrCopyOf, "copyOf(this, newSize)");
            this.content = fArrCopyOf;
        }
    }

    public final int getCapacity() {
        return this.content.length;
    }

    public final void minusAssign(float f2) {
        remove(f2);
    }

    public final void plusAssign(FloatList elements) {
        n.g(elements, "elements");
        addAll(this._size, elements);
    }

    public final boolean remove(float f2) {
        int iIndexOf = indexOf(f2);
        if (iIndexOf < 0) {
            return false;
        }
        removeAt(iIndexOf);
        return true;
    }

    public final boolean removeAll(float[] elements) {
        n.g(elements, "elements");
        int i2 = this._size;
        for (float f2 : elements) {
            remove(f2);
        }
        return i2 != this._size;
    }

    public final float removeAt(@IntRange(from = 0) int i2) {
        int i3;
        if (i2 < 0 || i2 >= (i3 = this._size)) {
            StringBuilder sb = new StringBuilder();
            sb.append("Index ");
            sb.append(i2);
            sb.append(" must be in 0..");
            sb.append(this._size - 1);
            throw new IndexOutOfBoundsException(sb.toString());
        }
        float[] fArr = this.content;
        float f2 = fArr[i2];
        if (i2 != i3 - 1) {
            AbstractC0180h.d(fArr, fArr, i2, i2 + 1, i3);
        }
        this._size--;
        return f2;
    }

    public final void removeRange(@IntRange(from = 0) int i2, @IntRange(from = 0) int i3) {
        int i4;
        if (i2 < 0 || i2 > (i4 = this._size) || i3 < 0 || i3 > i4) {
            throw new IndexOutOfBoundsException("Start (" + i2 + ") and end (" + i3 + ") must be in 0.." + this._size);
        }
        if (i3 >= i2) {
            if (i3 != i2) {
                if (i3 < i4) {
                    float[] fArr = this.content;
                    AbstractC0180h.d(fArr, fArr, i2, i3, i4);
                }
                this._size -= i3 - i2;
                return;
            }
            return;
        }
        throw new IllegalArgumentException("Start (" + i2 + ") is more than end (" + i3 + ')');
    }

    public final boolean retainAll(float[] elements) {
        n.g(elements, "elements");
        int i2 = this._size;
        float[] fArr = this.content;
        int i3 = i2 - 1;
        while (true) {
            int i4 = 0;
            int i5 = -1;
            if (-1 >= i3) {
                break;
            }
            float f2 = fArr[i3];
            int length = elements.length;
            while (true) {
                if (i4 >= length) {
                    break;
                }
                if (elements[i4] == f2) {
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

    public final float set(@IntRange(from = 0) int i2, float f2) {
        if (i2 >= 0 && i2 < this._size) {
            float[] fArr = this.content;
            float f3 = fArr[i2];
            fArr[i2] = f2;
            return f3;
        }
        StringBuilder sb = new StringBuilder();
        sb.append("set index ");
        sb.append(i2);
        sb.append(" must be between 0 .. ");
        sb.append(this._size - 1);
        throw new IndexOutOfBoundsException(sb.toString());
    }

    public final void sort() {
        AbstractC0180h.r(this.content, 0, this._size);
    }

    public final void sortDescending() {
        AbstractC0181i.N(this.content, 0, this._size);
    }

    public final void trim(int i2) {
        int iMax = Math.max(i2, this._size);
        float[] fArr = this.content;
        if (fArr.length > iMax) {
            float[] fArrCopyOf = Arrays.copyOf(fArr, iMax);
            n.f(fArrCopyOf, "copyOf(this, newSize)");
            this.content = fArrCopyOf;
        }
    }

    public /* synthetic */ MutableFloatList(int i2, int i3, DefaultConstructorMarker defaultConstructorMarker) {
        this((i3 & 1) != 0 ? 16 : i2);
    }

    public final void minusAssign(float[] elements) {
        n.g(elements, "elements");
        for (float f2 : elements) {
            remove(f2);
        }
    }

    public final void plusAssign(float[] elements) {
        n.g(elements, "elements");
        addAll(this._size, elements);
    }

    public MutableFloatList(int i2) {
        super(i2, null);
    }

    public final void plusAssign(float f2) {
        add(f2);
    }

    public final void add(@IntRange(from = 0) int i2, float f2) {
        int i3;
        if (i2 >= 0 && i2 <= (i3 = this._size)) {
            ensureCapacity(i3 + 1);
            float[] fArr = this.content;
            int i4 = this._size;
            if (i2 != i4) {
                AbstractC0180h.d(fArr, fArr, i2 + 1, i2, i4);
            }
            fArr[i2] = f2;
            this._size++;
            return;
        }
        throw new IndexOutOfBoundsException("Index " + i2 + " must be in 0.." + this._size);
    }

    public final void minusAssign(FloatList elements) {
        n.g(elements, "elements");
        float[] fArr = elements.content;
        int i2 = elements._size;
        for (int i3 = 0; i3 < i2; i3++) {
            remove(fArr[i3]);
        }
    }

    public final boolean removeAll(FloatList elements) {
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

    public final boolean retainAll(FloatList elements) {
        n.g(elements, "elements");
        int i2 = this._size;
        float[] fArr = this.content;
        for (int i3 = i2 - 1; -1 < i3; i3--) {
            if (!elements.contains(fArr[i3])) {
                removeAt(i3);
            }
        }
        return i2 != this._size;
    }

    public final boolean addAll(@IntRange(from = 0) int i2, FloatList elements) {
        n.g(elements, "elements");
        if (i2 >= 0 && i2 <= this._size) {
            if (elements.isEmpty()) {
                return false;
            }
            ensureCapacity(this._size + elements._size);
            float[] fArr = this.content;
            int i3 = this._size;
            if (i2 != i3) {
                AbstractC0180h.d(fArr, fArr, elements._size + i2, i2, i3);
            }
            AbstractC0180h.d(elements.content, fArr, i2, 0, elements._size);
            this._size += elements._size;
            return true;
        }
        throw new IndexOutOfBoundsException("Index " + i2 + " must be in 0.." + this._size);
    }

    public final boolean addAll(FloatList elements) {
        n.g(elements, "elements");
        return addAll(this._size, elements);
    }

    public final boolean addAll(float[] elements) {
        n.g(elements, "elements");
        return addAll(this._size, elements);
    }
}
