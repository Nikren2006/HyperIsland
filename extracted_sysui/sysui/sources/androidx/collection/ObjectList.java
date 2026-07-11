package androidx.collection;

import androidx.annotation.IntRange;
import c1.C0232d;
import c1.f;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.n;
import kotlin.jvm.internal.o;

/* JADX INFO: loaded from: classes.dex */
public abstract class ObjectList<E> {
    public int _size;
    public Object[] content;

    /* JADX INFO: renamed from: androidx.collection.ObjectList$toString$1, reason: invalid class name */
    public static final class AnonymousClass1 extends o implements Function1 {
        final /* synthetic */ ObjectList<E> this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass1(ObjectList<E> objectList) {
            super(1);
            this.this$0 = objectList;
        }

        @Override // kotlin.jvm.functions.Function1
        public final CharSequence invoke(E e2) {
            return e2 == this.this$0 ? "(this)" : String.valueOf(e2);
        }
    }

    public /* synthetic */ ObjectList(int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this(i2);
    }

    public static /* synthetic */ void getContent$annotations() {
    }

    public static /* synthetic */ void get_size$annotations() {
    }

    public static /* synthetic */ String joinToString$default(ObjectList objectList, CharSequence charSequence, CharSequence charSequence2, CharSequence charSequence3, int i2, CharSequence charSequence4, Function1 function1, int i3, Object obj) {
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
        CharSequence charSequence7 = charSequence4;
        if ((i3 & 32) != 0) {
            function1 = null;
        }
        return objectList.joinToString(charSequence, charSequence5, charSequence6, i4, charSequence7, function1);
    }

    public final boolean any() {
        return isNotEmpty();
    }

    public abstract List<E> asList();

    public final boolean contains(E e2) {
        return indexOf(e2) >= 0;
    }

    public final boolean containsAll(E[] elements) {
        n.g(elements, "elements");
        for (E e2 : elements) {
            if (!contains(e2)) {
                return false;
            }
        }
        return true;
    }

    public final int count() {
        return this._size;
    }

    public final E elementAt(@IntRange(from = 0) int i2) {
        if (i2 >= 0 && i2 < this._size) {
            return (E) this.content[i2];
        }
        StringBuilder sb = new StringBuilder();
        sb.append("Index ");
        sb.append(i2);
        sb.append(" must be in 0..");
        sb.append(this._size - 1);
        throw new IndexOutOfBoundsException(sb.toString());
    }

    public final E elementAtOrElse(@IntRange(from = 0) int i2, Function1 defaultValue) {
        n.g(defaultValue, "defaultValue");
        return (i2 < 0 || i2 >= this._size) ? (E) defaultValue.invoke(Integer.valueOf(i2)) : (E) this.content[i2];
    }

    public boolean equals(Object obj) {
        if (obj instanceof ObjectList) {
            ObjectList objectList = (ObjectList) obj;
            int i2 = objectList._size;
            int i3 = this._size;
            if (i2 == i3) {
                Object[] objArr = this.content;
                Object[] objArr2 = objectList.content;
                C0232d c0232dL = f.l(0, i3);
                int iC = c0232dL.c();
                int iD = c0232dL.d();
                if (iC > iD) {
                    return true;
                }
                while (n.c(objArr[iC], objArr2[iC])) {
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

    public final E first() {
        if (isEmpty()) {
            throw new NoSuchElementException("ObjectList is empty.");
        }
        return (E) this.content[0];
    }

    public final E firstOrNull() {
        if (isEmpty()) {
            return null;
        }
        return get(0);
    }

    public final <R> R fold(R r2, Function2 operation) {
        n.g(operation, "operation");
        Object[] objArr = this.content;
        int i2 = this._size;
        for (int i3 = 0; i3 < i2; i3++) {
            r2 = (R) operation.invoke(r2, objArr[i3]);
        }
        return r2;
    }

    public final <R> R foldIndexed(R r2, Function3 operation) {
        n.g(operation, "operation");
        Object[] objArr = this.content;
        int i2 = this._size;
        for (int i3 = 0; i3 < i2; i3++) {
            r2 = (R) operation.invoke(Integer.valueOf(i3), r2, objArr[i3]);
        }
        return r2;
    }

    public final <R> R foldRight(R r2, Function2 operation) {
        n.g(operation, "operation");
        Object[] objArr = this.content;
        int i2 = this._size;
        while (true) {
            i2--;
            if (-1 >= i2) {
                return r2;
            }
            r2 = (R) operation.invoke(objArr[i2], r2);
        }
    }

    public final <R> R foldRightIndexed(R r2, Function3 operation) {
        n.g(operation, "operation");
        Object[] objArr = this.content;
        int i2 = this._size;
        while (true) {
            i2--;
            if (-1 >= i2) {
                return r2;
            }
            r2 = (R) operation.invoke(Integer.valueOf(i2), objArr[i2], r2);
        }
    }

    public final void forEach(Function1 block) {
        n.g(block, "block");
        Object[] objArr = this.content;
        int i2 = this._size;
        for (int i3 = 0; i3 < i2; i3++) {
            block.invoke(objArr[i3]);
        }
    }

    public final void forEachIndexed(Function2 block) {
        n.g(block, "block");
        Object[] objArr = this.content;
        int i2 = this._size;
        for (int i3 = 0; i3 < i2; i3++) {
            block.invoke(Integer.valueOf(i3), objArr[i3]);
        }
    }

    public final void forEachReversed(Function1 block) {
        n.g(block, "block");
        Object[] objArr = this.content;
        int i2 = this._size;
        while (true) {
            i2--;
            if (-1 >= i2) {
                return;
            } else {
                block.invoke(objArr[i2]);
            }
        }
    }

    public final void forEachReversedIndexed(Function2 block) {
        n.g(block, "block");
        Object[] objArr = this.content;
        int i2 = this._size;
        while (true) {
            i2--;
            if (-1 >= i2) {
                return;
            } else {
                block.invoke(Integer.valueOf(i2), objArr[i2]);
            }
        }
    }

    public final E get(@IntRange(from = 0) int i2) {
        if (i2 >= 0 && i2 < this._size) {
            return (E) this.content[i2];
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
        Object[] objArr = this.content;
        int i2 = this._size;
        int iHashCode = 0;
        for (int i3 = 0; i3 < i2; i3++) {
            Object obj = objArr[i3];
            iHashCode += (obj != null ? obj.hashCode() : 0) * 31;
        }
        return iHashCode;
    }

    public final int indexOf(E e2) {
        int i2 = 0;
        if (e2 == null) {
            Object[] objArr = this.content;
            int i3 = this._size;
            while (i2 < i3) {
                if (objArr[i2] == null) {
                    return i2;
                }
                i2++;
            }
            return -1;
        }
        Object[] objArr2 = this.content;
        int i4 = this._size;
        while (i2 < i4) {
            if (e2.equals(objArr2[i2])) {
                return i2;
            }
            i2++;
        }
        return -1;
    }

    public final int indexOfFirst(Function1 predicate) {
        n.g(predicate, "predicate");
        Object[] objArr = this.content;
        int i2 = this._size;
        for (int i3 = 0; i3 < i2; i3++) {
            if (((Boolean) predicate.invoke(objArr[i3])).booleanValue()) {
                return i3;
            }
        }
        return -1;
    }

    public final int indexOfLast(Function1 predicate) {
        n.g(predicate, "predicate");
        Object[] objArr = this.content;
        int i2 = this._size;
        do {
            i2--;
            if (-1 >= i2) {
                return -1;
            }
        } while (!((Boolean) predicate.invoke(objArr[i2])).booleanValue());
        return i2;
    }

    public final boolean isEmpty() {
        return this._size == 0;
    }

    public final boolean isNotEmpty() {
        return this._size != 0;
    }

    public final String joinToString() {
        return joinToString$default(this, null, null, null, 0, null, null, 63, null);
    }

    public final E last() {
        if (isEmpty()) {
            throw new NoSuchElementException("ObjectList is empty.");
        }
        return (E) this.content[this._size - 1];
    }

    public final int lastIndexOf(E e2) {
        if (e2 == null) {
            Object[] objArr = this.content;
            for (int i2 = this._size - 1; -1 < i2; i2--) {
                if (objArr[i2] == null) {
                    return i2;
                }
            }
        } else {
            Object[] objArr2 = this.content;
            for (int i3 = this._size - 1; -1 < i3; i3--) {
                if (e2.equals(objArr2[i3])) {
                    return i3;
                }
            }
        }
        return -1;
    }

    public final E lastOrNull() {
        if (isEmpty()) {
            return null;
        }
        return (E) this.content[this._size - 1];
    }

    public final boolean none() {
        return isEmpty();
    }

    public final boolean reversedAny(Function1 predicate) {
        n.g(predicate, "predicate");
        Object[] objArr = this.content;
        for (int i2 = this._size - 1; -1 < i2; i2--) {
            if (((Boolean) predicate.invoke(objArr[i2])).booleanValue()) {
                return true;
            }
        }
        return false;
    }

    public String toString() {
        return joinToString$default(this, null, "[", "]", 0, null, new AnonymousClass1(this), 25, null);
    }

    private ObjectList(int i2) {
        this.content = i2 == 0 ? ObjectListKt.EmptyArray : new Object[i2];
    }

    public final boolean any(Function1 predicate) {
        n.g(predicate, "predicate");
        Object[] objArr = this.content;
        int i2 = this._size;
        for (int i3 = 0; i3 < i2; i3++) {
            if (((Boolean) predicate.invoke(objArr[i3])).booleanValue()) {
                return true;
            }
        }
        return false;
    }

    public final int count(Function1 predicate) {
        n.g(predicate, "predicate");
        Object[] objArr = this.content;
        int i2 = this._size;
        int i3 = 0;
        for (int i4 = 0; i4 < i2; i4++) {
            if (((Boolean) predicate.invoke(objArr[i4])).booleanValue()) {
                i3++;
            }
        }
        return i3;
    }

    public final E firstOrNull(Function1 predicate) {
        n.g(predicate, "predicate");
        Object[] objArr = this.content;
        int i2 = this._size;
        for (int i3 = 0; i3 < i2; i3++) {
            E e2 = (E) objArr[i3];
            if (((Boolean) predicate.invoke(e2)).booleanValue()) {
                return e2;
            }
        }
        return null;
    }

    public final String joinToString(CharSequence separator) {
        n.g(separator, "separator");
        return joinToString$default(this, separator, null, null, 0, null, null, 62, null);
    }

    public final boolean containsAll(List<? extends E> elements) {
        n.g(elements, "elements");
        int size = elements.size();
        for (int i2 = 0; i2 < size; i2++) {
            if (!contains(elements.get(i2))) {
                return false;
            }
        }
        return true;
    }

    public final String joinToString(CharSequence separator, CharSequence prefix) {
        n.g(separator, "separator");
        n.g(prefix, "prefix");
        return joinToString$default(this, separator, prefix, null, 0, null, null, 60, null);
    }

    public final E lastOrNull(Function1 predicate) {
        E e2;
        n.g(predicate, "predicate");
        Object[] objArr = this.content;
        int i2 = this._size;
        do {
            i2--;
            if (-1 >= i2) {
                return null;
            }
            e2 = (E) objArr[i2];
        } while (!((Boolean) predicate.invoke(e2)).booleanValue());
        return e2;
    }

    public final E first(Function1 predicate) {
        n.g(predicate, "predicate");
        Object[] objArr = this.content;
        int i2 = this._size;
        for (int i3 = 0; i3 < i2; i3++) {
            E e2 = (E) objArr[i3];
            if (((Boolean) predicate.invoke(e2)).booleanValue()) {
                return e2;
            }
        }
        throw new NoSuchElementException("ObjectList contains no element matching the predicate.");
    }

    public final String joinToString(CharSequence separator, CharSequence prefix, CharSequence postfix) {
        n.g(separator, "separator");
        n.g(prefix, "prefix");
        n.g(postfix, "postfix");
        return joinToString$default(this, separator, prefix, postfix, 0, null, null, 56, null);
    }

    public final boolean containsAll(Iterable<? extends E> elements) {
        n.g(elements, "elements");
        Iterator<? extends E> it = elements.iterator();
        while (it.hasNext()) {
            if (!contains(it.next())) {
                return false;
            }
        }
        return true;
    }

    public final String joinToString(CharSequence separator, CharSequence prefix, CharSequence postfix, int i2) {
        n.g(separator, "separator");
        n.g(prefix, "prefix");
        n.g(postfix, "postfix");
        return joinToString$default(this, separator, prefix, postfix, i2, null, null, 48, null);
    }

    public final E last(Function1 predicate) {
        E e2;
        n.g(predicate, "predicate");
        Object[] objArr = this.content;
        int i2 = this._size;
        do {
            i2--;
            if (-1 < i2) {
                e2 = (E) objArr[i2];
            } else {
                throw new NoSuchElementException("ObjectList contains no element matching the predicate.");
            }
        } while (!((Boolean) predicate.invoke(e2)).booleanValue());
        return e2;
    }

    public final String joinToString(CharSequence separator, CharSequence prefix, CharSequence postfix, int i2, CharSequence truncated) {
        n.g(separator, "separator");
        n.g(prefix, "prefix");
        n.g(postfix, "postfix");
        n.g(truncated, "truncated");
        return joinToString$default(this, separator, prefix, postfix, i2, truncated, null, 32, null);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final boolean containsAll(ObjectList<E> elements) {
        n.g(elements, "elements");
        Object[] objArr = elements.content;
        int i2 = elements._size;
        for (int i3 = 0; i3 < i2; i3++) {
            if (!contains(objArr[i3])) {
                return false;
            }
        }
        return true;
    }

    public final String joinToString(CharSequence separator, CharSequence prefix, CharSequence postfix, int i2, CharSequence truncated, Function1 function1) {
        n.g(separator, "separator");
        n.g(prefix, "prefix");
        n.g(postfix, "postfix");
        n.g(truncated, "truncated");
        StringBuilder sb = new StringBuilder();
        sb.append(prefix);
        Object[] objArr = this.content;
        int i3 = this._size;
        int i4 = 0;
        while (true) {
            if (i4 < i3) {
                Object obj = objArr[i4];
                if (i4 == i2) {
                    sb.append(truncated);
                    break;
                }
                if (i4 != 0) {
                    sb.append(separator);
                }
                if (function1 == null) {
                    sb.append(obj);
                } else {
                    sb.append((CharSequence) function1.invoke(obj));
                }
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
}
