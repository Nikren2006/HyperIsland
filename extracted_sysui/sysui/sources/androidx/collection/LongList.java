package androidx.collection;

import androidx.annotation.IntRange;
import c1.C0232d;
import c1.f;
import java.util.NoSuchElementException;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.n;

/* JADX INFO: loaded from: classes.dex */
public abstract class LongList {
    public int _size;
    public long[] content;

    public /* synthetic */ LongList(int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this(i2);
    }

    public static /* synthetic */ void getContent$annotations() {
    }

    public static /* synthetic */ void get_size$annotations() {
    }

    public static /* synthetic */ String joinToString$default(LongList longList, CharSequence charSequence, CharSequence charSequence2, CharSequence charSequence3, int i2, CharSequence charSequence4, int i3, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: joinToString");
        }
        if ((i3 & 1) != 0) {
            charSequence = ", ";
        }
        CharSequence charSequence5 = (i3 & 2) != 0 ? "" : charSequence2;
        CharSequence charSequence6 = (i3 & 4) == 0 ? charSequence3 : "";
        if ((i3 & 8) != 0) {
            i2 = -1;
        }
        int i4 = i2;
        if ((i3 & 16) != 0) {
            charSequence4 = "...";
        }
        return longList.joinToString(charSequence, charSequence5, charSequence6, i4, charSequence4);
    }

    public final boolean any() {
        return isNotEmpty();
    }

    public final boolean contains(long j2) {
        long[] jArr = this.content;
        int i2 = this._size;
        for (int i3 = 0; i3 < i2; i3++) {
            if (jArr[i3] == j2) {
                return true;
            }
        }
        return false;
    }

    public final boolean containsAll(LongList elements) {
        n.g(elements, "elements");
        C0232d c0232dL = f.l(0, elements._size);
        int iC = c0232dL.c();
        int iD = c0232dL.d();
        if (iC > iD) {
            return true;
        }
        while (contains(elements.get(iC))) {
            if (iC == iD) {
                return true;
            }
            iC++;
        }
        return false;
    }

    public final int count() {
        return this._size;
    }

    public final long elementAt(@IntRange(from = 0) int i2) {
        if (i2 >= 0 && i2 < this._size) {
            return this.content[i2];
        }
        StringBuilder sb = new StringBuilder();
        sb.append("Index ");
        sb.append(i2);
        sb.append(" must be in 0..");
        sb.append(this._size - 1);
        throw new IndexOutOfBoundsException(sb.toString());
    }

    public final long elementAtOrElse(@IntRange(from = 0) int i2, Function1 defaultValue) {
        n.g(defaultValue, "defaultValue");
        return (i2 < 0 || i2 >= this._size) ? ((Number) defaultValue.invoke(Integer.valueOf(i2))).longValue() : this.content[i2];
    }

    public boolean equals(Object obj) {
        if (obj instanceof LongList) {
            LongList longList = (LongList) obj;
            int i2 = longList._size;
            int i3 = this._size;
            if (i2 == i3) {
                long[] jArr = this.content;
                long[] jArr2 = longList.content;
                C0232d c0232dL = f.l(0, i3);
                int iC = c0232dL.c();
                int iD = c0232dL.d();
                if (iC > iD) {
                    return true;
                }
                while (jArr[iC] == jArr2[iC]) {
                    if (iC == iD) {
                        return true;
                    }
                    iC++;
                }
                return false;
            }
        }
        return false;
    }

    public final long first() {
        if (isEmpty()) {
            throw new NoSuchElementException("LongList is empty.");
        }
        return this.content[0];
    }

    public final <R> R fold(R r2, Function2 operation) {
        n.g(operation, "operation");
        long[] jArr = this.content;
        int i2 = this._size;
        for (int i3 = 0; i3 < i2; i3++) {
            r2 = (R) operation.invoke(r2, Long.valueOf(jArr[i3]));
        }
        return r2;
    }

    public final <R> R foldIndexed(R r2, Function3 operation) {
        n.g(operation, "operation");
        long[] jArr = this.content;
        int i2 = this._size;
        for (int i3 = 0; i3 < i2; i3++) {
            R r3 = r2;
            r2 = (R) operation.invoke(Integer.valueOf(i3), r3, Long.valueOf(jArr[i3]));
        }
        return r2;
    }

    public final <R> R foldRight(R r2, Function2 operation) {
        n.g(operation, "operation");
        long[] jArr = this.content;
        int i2 = this._size;
        while (true) {
            i2--;
            if (-1 >= i2) {
                return r2;
            }
            r2 = (R) operation.invoke(Long.valueOf(jArr[i2]), r2);
        }
    }

    public final <R> R foldRightIndexed(R r2, Function3 operation) {
        n.g(operation, "operation");
        long[] jArr = this.content;
        int i2 = this._size;
        while (true) {
            i2--;
            if (-1 >= i2) {
                return r2;
            }
            r2 = (R) operation.invoke(Integer.valueOf(i2), Long.valueOf(jArr[i2]), r2);
        }
    }

    public final void forEach(Function1 block) {
        n.g(block, "block");
        long[] jArr = this.content;
        int i2 = this._size;
        for (int i3 = 0; i3 < i2; i3++) {
            block.invoke(Long.valueOf(jArr[i3]));
        }
    }

    public final void forEachIndexed(Function2 block) {
        n.g(block, "block");
        long[] jArr = this.content;
        int i2 = this._size;
        for (int i3 = 0; i3 < i2; i3++) {
            block.invoke(Integer.valueOf(i3), Long.valueOf(jArr[i3]));
        }
    }

    public final void forEachReversed(Function1 block) {
        n.g(block, "block");
        long[] jArr = this.content;
        int i2 = this._size;
        while (true) {
            i2--;
            if (-1 >= i2) {
                return;
            } else {
                block.invoke(Long.valueOf(jArr[i2]));
            }
        }
    }

    public final void forEachReversedIndexed(Function2 block) {
        n.g(block, "block");
        long[] jArr = this.content;
        int i2 = this._size;
        while (true) {
            i2--;
            if (-1 >= i2) {
                return;
            } else {
                block.invoke(Integer.valueOf(i2), Long.valueOf(jArr[i2]));
            }
        }
    }

    public final long get(@IntRange(from = 0) int i2) {
        if (i2 >= 0 && i2 < this._size) {
            return this.content[i2];
        }
        StringBuilder sb = new StringBuilder();
        sb.append("Index ");
        sb.append(i2);
        sb.append(" must be in 0..");
        sb.append(this._size - 1);
        throw new IndexOutOfBoundsException(sb.toString());
    }

    public final C0232d getIndices() {
        return f.l(0, this._size);
    }

    @IntRange(from = -1)
    public final int getLastIndex() {
        return this._size - 1;
    }

    @IntRange(from = 0)
    public final int getSize() {
        return this._size;
    }

    public int hashCode() {
        long[] jArr = this.content;
        int i2 = this._size;
        int iHashCode = 0;
        for (int i3 = 0; i3 < i2; i3++) {
            iHashCode += Long.hashCode(jArr[i3]) * 31;
        }
        return iHashCode;
    }

    public final int indexOf(long j2) {
        long[] jArr = this.content;
        int i2 = this._size;
        for (int i3 = 0; i3 < i2; i3++) {
            if (j2 == jArr[i3]) {
                return i3;
            }
        }
        return -1;
    }

    public final int indexOfFirst(Function1 predicate) {
        n.g(predicate, "predicate");
        long[] jArr = this.content;
        int i2 = this._size;
        for (int i3 = 0; i3 < i2; i3++) {
            if (((Boolean) predicate.invoke(Long.valueOf(jArr[i3]))).booleanValue()) {
                return i3;
            }
        }
        return -1;
    }

    public final int indexOfLast(Function1 predicate) {
        n.g(predicate, "predicate");
        long[] jArr = this.content;
        int i2 = this._size;
        do {
            i2--;
            if (-1 >= i2) {
                return -1;
            }
        } while (!((Boolean) predicate.invoke(Long.valueOf(jArr[i2]))).booleanValue());
        return i2;
    }

    public final boolean isEmpty() {
        return this._size == 0;
    }

    public final boolean isNotEmpty() {
        return this._size != 0;
    }

    public final String joinToString() {
        return joinToString$default(this, null, null, null, 0, null, 31, null);
    }

    public final long last() {
        if (isEmpty()) {
            throw new NoSuchElementException("LongList is empty.");
        }
        return this.content[this._size - 1];
    }

    public final int lastIndexOf(long j2) {
        long[] jArr = this.content;
        int i2 = this._size;
        do {
            i2--;
            if (-1 >= i2) {
                return -1;
            }
        } while (jArr[i2] != j2);
        return i2;
    }

    public final boolean none() {
        return isEmpty();
    }

    public final boolean reversedAny(Function1 predicate) {
        n.g(predicate, "predicate");
        long[] jArr = this.content;
        for (int i2 = this._size - 1; -1 < i2; i2--) {
            if (((Boolean) predicate.invoke(Long.valueOf(jArr[i2]))).booleanValue()) {
                return true;
            }
        }
        return false;
    }

    public String toString() {
        return joinToString$default(this, null, "[", "]", 0, null, 25, null);
    }

    private LongList(int i2) {
        this.content = i2 == 0 ? LongSetKt.getEmptyLongArray() : new long[i2];
    }

    public final boolean any(Function1 predicate) {
        n.g(predicate, "predicate");
        long[] jArr = this.content;
        int i2 = this._size;
        for (int i3 = 0; i3 < i2; i3++) {
            if (((Boolean) predicate.invoke(Long.valueOf(jArr[i3]))).booleanValue()) {
                return true;
            }
        }
        return false;
    }

    public final int count(Function1 predicate) {
        n.g(predicate, "predicate");
        long[] jArr = this.content;
        int i2 = this._size;
        int i3 = 0;
        for (int i4 = 0; i4 < i2; i4++) {
            if (((Boolean) predicate.invoke(Long.valueOf(jArr[i4]))).booleanValue()) {
                i3++;
            }
        }
        return i3;
    }

    public final String joinToString(CharSequence separator) {
        n.g(separator, "separator");
        return joinToString$default(this, separator, null, null, 0, null, 30, null);
    }

    public final String joinToString(CharSequence separator, CharSequence prefix) {
        n.g(separator, "separator");
        n.g(prefix, "prefix");
        return joinToString$default(this, separator, prefix, null, 0, null, 28, null);
    }

    public final long first(Function1 predicate) {
        n.g(predicate, "predicate");
        long[] jArr = this.content;
        int i2 = this._size;
        for (int i3 = 0; i3 < i2; i3++) {
            long j2 = jArr[i3];
            if (((Boolean) predicate.invoke(Long.valueOf(j2))).booleanValue()) {
                return j2;
            }
        }
        throw new NoSuchElementException("LongList contains no element matching the predicate.");
    }

    public final String joinToString(CharSequence separator, CharSequence prefix, CharSequence postfix) {
        n.g(separator, "separator");
        n.g(prefix, "prefix");
        n.g(postfix, "postfix");
        return joinToString$default(this, separator, prefix, postfix, 0, null, 24, null);
    }

    public static /* synthetic */ String joinToString$default(LongList longList, CharSequence separator, CharSequence prefix, CharSequence postfix, int i2, CharSequence truncated, Function1 transform, int i3, Object obj) {
        if (obj == null) {
            if ((i3 & 1) != 0) {
                separator = ", ";
            }
            if ((i3 & 2) != 0) {
                prefix = "";
            }
            if ((i3 & 4) != 0) {
                postfix = "";
            }
            if ((i3 & 8) != 0) {
                i2 = -1;
            }
            if ((i3 & 16) != 0) {
                truncated = "...";
            }
            n.g(separator, "separator");
            n.g(prefix, "prefix");
            n.g(postfix, "postfix");
            n.g(truncated, "truncated");
            n.g(transform, "transform");
            StringBuilder sb = new StringBuilder();
            sb.append(prefix);
            long[] jArr = longList.content;
            int i4 = longList._size;
            int i5 = 0;
            while (true) {
                if (i5 < i4) {
                    long j2 = jArr[i5];
                    if (i5 == i2) {
                        sb.append(truncated);
                        break;
                    }
                    if (i5 != 0) {
                        sb.append(separator);
                    }
                    sb.append((CharSequence) transform.invoke(Long.valueOf(j2)));
                    i5++;
                } else {
                    sb.append(postfix);
                    break;
                }
            }
            String string = sb.toString();
            n.f(string, "StringBuilder().apply(builderAction).toString()");
            return string;
        }
        throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: joinToString");
    }

    public final String joinToString(CharSequence separator, CharSequence prefix, CharSequence postfix, int i2) {
        n.g(separator, "separator");
        n.g(prefix, "prefix");
        n.g(postfix, "postfix");
        return joinToString$default(this, separator, prefix, postfix, i2, null, 16, null);
    }

    public final long last(Function1 predicate) {
        long j2;
        n.g(predicate, "predicate");
        long[] jArr = this.content;
        int i2 = this._size;
        do {
            i2--;
            if (-1 < i2) {
                j2 = jArr[i2];
            } else {
                throw new NoSuchElementException("LongList contains no element matching the predicate.");
            }
        } while (!((Boolean) predicate.invoke(Long.valueOf(j2))).booleanValue());
        return j2;
    }

    public final String joinToString(CharSequence separator, CharSequence prefix, CharSequence postfix, int i2, CharSequence truncated) {
        n.g(separator, "separator");
        n.g(prefix, "prefix");
        n.g(postfix, "postfix");
        n.g(truncated, "truncated");
        StringBuilder sb = new StringBuilder();
        sb.append(prefix);
        long[] jArr = this.content;
        int i3 = this._size;
        int i4 = 0;
        while (true) {
            if (i4 < i3) {
                long j2 = jArr[i4];
                if (i4 == i2) {
                    sb.append(truncated);
                    break;
                }
                if (i4 != 0) {
                    sb.append(separator);
                }
                sb.append(j2);
                i4++;
            } else {
                sb.append(postfix);
                break;
            }
        }
        String string = sb.toString();
        n.f(string, "StringBuilder().apply(builderAction).toString()");
        return string;
    }

    public final String joinToString(CharSequence separator, CharSequence prefix, CharSequence postfix, int i2, CharSequence truncated, Function1 transform) {
        n.g(separator, "separator");
        n.g(prefix, "prefix");
        n.g(postfix, "postfix");
        n.g(truncated, "truncated");
        n.g(transform, "transform");
        StringBuilder sb = new StringBuilder();
        sb.append(prefix);
        long[] jArr = this.content;
        int i3 = this._size;
        int i4 = 0;
        while (true) {
            if (i4 < i3) {
                long j2 = jArr[i4];
                if (i4 == i2) {
                    sb.append(truncated);
                    break;
                }
                if (i4 != 0) {
                    sb.append(separator);
                }
                sb.append((CharSequence) transform.invoke(Long.valueOf(j2)));
                i4++;
            } else {
                sb.append(postfix);
                break;
            }
        }
        String string = sb.toString();
        n.f(string, "StringBuilder().apply(builderAction).toString()");
        return string;
    }

    public final String joinToString(CharSequence separator, CharSequence prefix, CharSequence postfix, int i2, Function1 transform) {
        n.g(separator, "separator");
        n.g(prefix, "prefix");
        n.g(postfix, "postfix");
        n.g(transform, "transform");
        StringBuilder sb = new StringBuilder();
        sb.append(prefix);
        long[] jArr = this.content;
        int i3 = this._size;
        int i4 = 0;
        while (true) {
            if (i4 < i3) {
                long j2 = jArr[i4];
                if (i4 == i2) {
                    sb.append((CharSequence) "...");
                    break;
                }
                if (i4 != 0) {
                    sb.append(separator);
                }
                sb.append((CharSequence) transform.invoke(Long.valueOf(j2)));
                i4++;
            } else {
                sb.append(postfix);
                break;
            }
        }
        String string = sb.toString();
        n.f(string, "StringBuilder().apply(builderAction).toString()");
        return string;
    }

    public final String joinToString(CharSequence separator, CharSequence prefix, CharSequence postfix, Function1 transform) {
        n.g(separator, "separator");
        n.g(prefix, "prefix");
        n.g(postfix, "postfix");
        n.g(transform, "transform");
        StringBuilder sb = new StringBuilder();
        sb.append(prefix);
        long[] jArr = this.content;
        int i2 = this._size;
        int i3 = 0;
        while (true) {
            if (i3 < i2) {
                long j2 = jArr[i3];
                if (i3 == -1) {
                    sb.append((CharSequence) "...");
                    break;
                }
                if (i3 != 0) {
                    sb.append(separator);
                }
                sb.append((CharSequence) transform.invoke(Long.valueOf(j2)));
                i3++;
            } else {
                sb.append(postfix);
                break;
            }
        }
        String string = sb.toString();
        n.f(string, "StringBuilder().apply(builderAction).toString()");
        return string;
    }

    public final String joinToString(CharSequence separator, CharSequence prefix, Function1 transform) {
        n.g(separator, "separator");
        n.g(prefix, "prefix");
        n.g(transform, "transform");
        StringBuilder sb = new StringBuilder();
        sb.append(prefix);
        long[] jArr = this.content;
        int i2 = this._size;
        int i3 = 0;
        while (true) {
            if (i3 < i2) {
                long j2 = jArr[i3];
                if (i3 == -1) {
                    sb.append((CharSequence) "...");
                    break;
                }
                if (i3 != 0) {
                    sb.append(separator);
                }
                sb.append((CharSequence) transform.invoke(Long.valueOf(j2)));
                i3++;
            } else {
                sb.append((CharSequence) "");
                break;
            }
        }
        String string = sb.toString();
        n.f(string, "StringBuilder().apply(builderAction).toString()");
        return string;
    }

    public final String joinToString(CharSequence separator, Function1 transform) {
        n.g(separator, "separator");
        n.g(transform, "transform");
        StringBuilder sb = new StringBuilder();
        sb.append((CharSequence) "");
        long[] jArr = this.content;
        int i2 = this._size;
        int i3 = 0;
        while (true) {
            if (i3 < i2) {
                long j2 = jArr[i3];
                if (i3 == -1) {
                    sb.append((CharSequence) "...");
                    break;
                }
                if (i3 != 0) {
                    sb.append(separator);
                }
                sb.append((CharSequence) transform.invoke(Long.valueOf(j2)));
                i3++;
            } else {
                sb.append((CharSequence) "");
                break;
            }
        }
        String string = sb.toString();
        n.f(string, "StringBuilder().apply(builderAction).toString()");
        return string;
    }

    public final String joinToString(Function1 transform) {
        n.g(transform, "transform");
        StringBuilder sb = new StringBuilder();
        sb.append((CharSequence) "");
        long[] jArr = this.content;
        int i2 = this._size;
        int i3 = 0;
        while (true) {
            if (i3 < i2) {
                long j2 = jArr[i3];
                if (i3 == -1) {
                    sb.append((CharSequence) "...");
                    break;
                }
                if (i3 != 0) {
                    sb.append((CharSequence) ", ");
                }
                sb.append((CharSequence) transform.invoke(Long.valueOf(j2)));
                i3++;
            } else {
                sb.append((CharSequence) "");
                break;
            }
        }
        String string = sb.toString();
        n.f(string, "StringBuilder().apply(builderAction).toString()");
        return string;
    }
}
