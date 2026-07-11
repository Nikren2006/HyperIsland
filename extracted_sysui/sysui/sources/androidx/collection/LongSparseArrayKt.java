package androidx.collection;

import I0.AbstractC0180h;
import I0.B;
import W0.a;
import androidx.collection.internal.ContainerHelpersKt;
import java.util.Arrays;
import java.util.Iterator;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.n;

/* JADX INFO: loaded from: classes.dex */
public final class LongSparseArrayKt {
    private static final Object DELETED = new Object();

    /* JADX INFO: Add missing generic type declarations: [T] */
    /* JADX INFO: renamed from: androidx.collection.LongSparseArrayKt$valueIterator$1, reason: invalid class name and case insensitive filesystem */
    public static final class C01991<T> implements Iterator<T>, a {
        final /* synthetic */ LongSparseArray<T> $this_valueIterator;
        private int index;

        public C01991(LongSparseArray<T> longSparseArray) {
            this.$this_valueIterator = longSparseArray;
        }

        public final int getIndex() {
            return this.index;
        }

        @Override // java.util.Iterator
        public boolean hasNext() {
            return this.index < this.$this_valueIterator.size();
        }

        @Override // java.util.Iterator
        public T next() {
            LongSparseArray<T> longSparseArray = this.$this_valueIterator;
            int i2 = this.index;
            this.index = i2 + 1;
            return longSparseArray.valueAt(i2);
        }

        @Override // java.util.Iterator
        public void remove() {
            throw new UnsupportedOperationException("Operation is not supported for read-only collection");
        }

        public final void setIndex(int i2) {
            this.index = i2;
        }
    }

    public static final <E> void commonAppend(LongSparseArray<E> longSparseArray, long j2, E e2) {
        n.g(longSparseArray, "<this>");
        int i2 = longSparseArray.size;
        if (i2 != 0 && j2 <= longSparseArray.keys[i2 - 1]) {
            longSparseArray.put(j2, e2);
            return;
        }
        if (longSparseArray.garbage) {
            long[] jArr = longSparseArray.keys;
            if (i2 >= jArr.length) {
                Object[] objArr = longSparseArray.values;
                int i3 = 0;
                for (int i4 = 0; i4 < i2; i4++) {
                    Object obj = objArr[i4];
                    if (obj != DELETED) {
                        if (i4 != i3) {
                            jArr[i3] = jArr[i4];
                            objArr[i3] = obj;
                            objArr[i4] = null;
                        }
                        i3++;
                    }
                }
                longSparseArray.garbage = false;
                longSparseArray.size = i3;
            }
        }
        int i5 = longSparseArray.size;
        if (i5 >= longSparseArray.keys.length) {
            int iIdealLongArraySize = ContainerHelpersKt.idealLongArraySize(i5 + 1);
            long[] jArrCopyOf = Arrays.copyOf(longSparseArray.keys, iIdealLongArraySize);
            n.f(jArrCopyOf, "copyOf(this, newSize)");
            longSparseArray.keys = jArrCopyOf;
            Object[] objArrCopyOf = Arrays.copyOf(longSparseArray.values, iIdealLongArraySize);
            n.f(objArrCopyOf, "copyOf(this, newSize)");
            longSparseArray.values = objArrCopyOf;
        }
        longSparseArray.keys[i5] = j2;
        longSparseArray.values[i5] = e2;
        longSparseArray.size = i5 + 1;
    }

    public static final <E> void commonClear(LongSparseArray<E> longSparseArray) {
        n.g(longSparseArray, "<this>");
        int i2 = longSparseArray.size;
        Object[] objArr = longSparseArray.values;
        for (int i3 = 0; i3 < i2; i3++) {
            objArr[i3] = null;
        }
        longSparseArray.size = 0;
        longSparseArray.garbage = false;
    }

    public static final <E> boolean commonContainsKey(LongSparseArray<E> longSparseArray, long j2) {
        n.g(longSparseArray, "<this>");
        return longSparseArray.indexOfKey(j2) >= 0;
    }

    public static final <E> boolean commonContainsValue(LongSparseArray<E> longSparseArray, E e2) {
        n.g(longSparseArray, "<this>");
        return longSparseArray.indexOfValue(e2) >= 0;
    }

    public static final <E> void commonGc(LongSparseArray<E> longSparseArray) {
        n.g(longSparseArray, "<this>");
        int i2 = longSparseArray.size;
        long[] jArr = longSparseArray.keys;
        Object[] objArr = longSparseArray.values;
        int i3 = 0;
        for (int i4 = 0; i4 < i2; i4++) {
            Object obj = objArr[i4];
            if (obj != DELETED) {
                if (i4 != i3) {
                    jArr[i3] = jArr[i4];
                    objArr[i3] = obj;
                    objArr[i4] = null;
                }
                i3++;
            }
        }
        longSparseArray.garbage = false;
        longSparseArray.size = i3;
    }

    public static final <E> E commonGet(LongSparseArray<E> longSparseArray, long j2) {
        n.g(longSparseArray, "<this>");
        int iBinarySearch = ContainerHelpersKt.binarySearch(longSparseArray.keys, longSparseArray.size, j2);
        if (iBinarySearch < 0 || longSparseArray.values[iBinarySearch] == DELETED) {
            return null;
        }
        return (E) longSparseArray.values[iBinarySearch];
    }

    public static final <T extends E, E> T commonGetInternal(LongSparseArray<E> longSparseArray, long j2, T t2) {
        n.g(longSparseArray, "<this>");
        int iBinarySearch = ContainerHelpersKt.binarySearch(longSparseArray.keys, longSparseArray.size, j2);
        return (iBinarySearch < 0 || longSparseArray.values[iBinarySearch] == DELETED) ? t2 : (T) longSparseArray.values[iBinarySearch];
    }

    public static final <E> int commonIndexOfKey(LongSparseArray<E> longSparseArray, long j2) {
        n.g(longSparseArray, "<this>");
        if (longSparseArray.garbage) {
            int i2 = longSparseArray.size;
            long[] jArr = longSparseArray.keys;
            Object[] objArr = longSparseArray.values;
            int i3 = 0;
            for (int i4 = 0; i4 < i2; i4++) {
                Object obj = objArr[i4];
                if (obj != DELETED) {
                    if (i4 != i3) {
                        jArr[i3] = jArr[i4];
                        objArr[i3] = obj;
                        objArr[i4] = null;
                    }
                    i3++;
                }
            }
            longSparseArray.garbage = false;
            longSparseArray.size = i3;
        }
        return ContainerHelpersKt.binarySearch(longSparseArray.keys, longSparseArray.size, j2);
    }

    public static final <E> int commonIndexOfValue(LongSparseArray<E> longSparseArray, E e2) {
        n.g(longSparseArray, "<this>");
        if (longSparseArray.garbage) {
            int i2 = longSparseArray.size;
            long[] jArr = longSparseArray.keys;
            Object[] objArr = longSparseArray.values;
            int i3 = 0;
            for (int i4 = 0; i4 < i2; i4++) {
                Object obj = objArr[i4];
                if (obj != DELETED) {
                    if (i4 != i3) {
                        jArr[i3] = jArr[i4];
                        objArr[i3] = obj;
                        objArr[i4] = null;
                    }
                    i3++;
                }
            }
            longSparseArray.garbage = false;
            longSparseArray.size = i3;
        }
        int i5 = longSparseArray.size;
        for (int i6 = 0; i6 < i5; i6++) {
            if (longSparseArray.values[i6] == e2) {
                return i6;
            }
        }
        return -1;
    }

    public static final <E> boolean commonIsEmpty(LongSparseArray<E> longSparseArray) {
        n.g(longSparseArray, "<this>");
        return longSparseArray.size() == 0;
    }

    public static final <E> long commonKeyAt(LongSparseArray<E> longSparseArray, int i2) {
        int i3;
        n.g(longSparseArray, "<this>");
        if (i2 < 0 || i2 >= (i3 = longSparseArray.size)) {
            throw new IllegalArgumentException(("Expected index to be within 0..size()-1, but was " + i2).toString());
        }
        if (longSparseArray.garbage) {
            long[] jArr = longSparseArray.keys;
            Object[] objArr = longSparseArray.values;
            int i4 = 0;
            for (int i5 = 0; i5 < i3; i5++) {
                Object obj = objArr[i5];
                if (obj != DELETED) {
                    if (i5 != i4) {
                        jArr[i4] = jArr[i5];
                        objArr[i4] = obj;
                        objArr[i5] = null;
                    }
                    i4++;
                }
            }
            longSparseArray.garbage = false;
            longSparseArray.size = i4;
        }
        return longSparseArray.keys[i2];
    }

    public static final <E> void commonPut(LongSparseArray<E> longSparseArray, long j2, E e2) {
        n.g(longSparseArray, "<this>");
        int iBinarySearch = ContainerHelpersKt.binarySearch(longSparseArray.keys, longSparseArray.size, j2);
        if (iBinarySearch >= 0) {
            longSparseArray.values[iBinarySearch] = e2;
            return;
        }
        int i2 = ~iBinarySearch;
        if (i2 < longSparseArray.size && longSparseArray.values[i2] == DELETED) {
            longSparseArray.keys[i2] = j2;
            longSparseArray.values[i2] = e2;
            return;
        }
        if (longSparseArray.garbage) {
            int i3 = longSparseArray.size;
            long[] jArr = longSparseArray.keys;
            if (i3 >= jArr.length) {
                Object[] objArr = longSparseArray.values;
                int i4 = 0;
                for (int i5 = 0; i5 < i3; i5++) {
                    Object obj = objArr[i5];
                    if (obj != DELETED) {
                        if (i5 != i4) {
                            jArr[i4] = jArr[i5];
                            objArr[i4] = obj;
                            objArr[i5] = null;
                        }
                        i4++;
                    }
                }
                longSparseArray.garbage = false;
                longSparseArray.size = i4;
                i2 = ~ContainerHelpersKt.binarySearch(longSparseArray.keys, i4, j2);
            }
        }
        int i6 = longSparseArray.size;
        if (i6 >= longSparseArray.keys.length) {
            int iIdealLongArraySize = ContainerHelpersKt.idealLongArraySize(i6 + 1);
            long[] jArrCopyOf = Arrays.copyOf(longSparseArray.keys, iIdealLongArraySize);
            n.f(jArrCopyOf, "copyOf(this, newSize)");
            longSparseArray.keys = jArrCopyOf;
            Object[] objArrCopyOf = Arrays.copyOf(longSparseArray.values, iIdealLongArraySize);
            n.f(objArrCopyOf, "copyOf(this, newSize)");
            longSparseArray.values = objArrCopyOf;
        }
        int i7 = longSparseArray.size;
        if (i7 - i2 != 0) {
            long[] jArr2 = longSparseArray.keys;
            int i8 = i2 + 1;
            AbstractC0180h.f(jArr2, jArr2, i8, i2, i7);
            Object[] objArr2 = longSparseArray.values;
            AbstractC0180h.g(objArr2, objArr2, i8, i2, longSparseArray.size);
        }
        longSparseArray.keys[i2] = j2;
        longSparseArray.values[i2] = e2;
        longSparseArray.size++;
    }

    public static final <E> void commonPutAll(LongSparseArray<E> longSparseArray, LongSparseArray<? extends E> other) {
        n.g(longSparseArray, "<this>");
        n.g(other, "other");
        int size = other.size();
        for (int i2 = 0; i2 < size; i2++) {
            longSparseArray.put(other.keyAt(i2), other.valueAt(i2));
        }
    }

    public static final <E> E commonPutIfAbsent(LongSparseArray<E> longSparseArray, long j2, E e2) {
        n.g(longSparseArray, "<this>");
        E e3 = longSparseArray.get(j2);
        if (e3 == null) {
            longSparseArray.put(j2, e2);
        }
        return e3;
    }

    public static final <E> void commonRemove(LongSparseArray<E> longSparseArray, long j2) {
        n.g(longSparseArray, "<this>");
        int iBinarySearch = ContainerHelpersKt.binarySearch(longSparseArray.keys, longSparseArray.size, j2);
        if (iBinarySearch < 0 || longSparseArray.values[iBinarySearch] == DELETED) {
            return;
        }
        longSparseArray.values[iBinarySearch] = DELETED;
        longSparseArray.garbage = true;
    }

    public static final <E> void commonRemoveAt(LongSparseArray<E> longSparseArray, int i2) {
        n.g(longSparseArray, "<this>");
        if (longSparseArray.values[i2] != DELETED) {
            longSparseArray.values[i2] = DELETED;
            longSparseArray.garbage = true;
        }
    }

    public static final <E> E commonReplace(LongSparseArray<E> longSparseArray, long j2, E e2) {
        n.g(longSparseArray, "<this>");
        int iIndexOfKey = longSparseArray.indexOfKey(j2);
        if (iIndexOfKey < 0) {
            return null;
        }
        Object[] objArr = longSparseArray.values;
        E e3 = (E) objArr[iIndexOfKey];
        objArr[iIndexOfKey] = e2;
        return e3;
    }

    public static final <E> void commonSetValueAt(LongSparseArray<E> longSparseArray, int i2, E e2) {
        int i3;
        n.g(longSparseArray, "<this>");
        if (i2 < 0 || i2 >= (i3 = longSparseArray.size)) {
            throw new IllegalArgumentException(("Expected index to be within 0..size()-1, but was " + i2).toString());
        }
        if (longSparseArray.garbage) {
            long[] jArr = longSparseArray.keys;
            Object[] objArr = longSparseArray.values;
            int i4 = 0;
            for (int i5 = 0; i5 < i3; i5++) {
                Object obj = objArr[i5];
                if (obj != DELETED) {
                    if (i5 != i4) {
                        jArr[i4] = jArr[i5];
                        objArr[i4] = obj;
                        objArr[i5] = null;
                    }
                    i4++;
                }
            }
            longSparseArray.garbage = false;
            longSparseArray.size = i4;
        }
        longSparseArray.values[i2] = e2;
    }

    public static final <E> int commonSize(LongSparseArray<E> longSparseArray) {
        n.g(longSparseArray, "<this>");
        if (longSparseArray.garbage) {
            int i2 = longSparseArray.size;
            long[] jArr = longSparseArray.keys;
            Object[] objArr = longSparseArray.values;
            int i3 = 0;
            for (int i4 = 0; i4 < i2; i4++) {
                Object obj = objArr[i4];
                if (obj != DELETED) {
                    if (i4 != i3) {
                        jArr[i3] = jArr[i4];
                        objArr[i3] = obj;
                        objArr[i4] = null;
                    }
                    i3++;
                }
            }
            longSparseArray.garbage = false;
            longSparseArray.size = i3;
        }
        return longSparseArray.size;
    }

    public static final <E> String commonToString(LongSparseArray<E> longSparseArray) {
        n.g(longSparseArray, "<this>");
        if (longSparseArray.size() <= 0) {
            return "{}";
        }
        StringBuilder sb = new StringBuilder(longSparseArray.size * 28);
        sb.append('{');
        int i2 = longSparseArray.size;
        for (int i3 = 0; i3 < i2; i3++) {
            if (i3 > 0) {
                sb.append(", ");
            }
            sb.append(longSparseArray.keyAt(i3));
            sb.append('=');
            E eValueAt = longSparseArray.valueAt(i3);
            if (eValueAt != sb) {
                sb.append(eValueAt);
            } else {
                sb.append("(this Map)");
            }
        }
        sb.append('}');
        String string = sb.toString();
        n.f(string, "StringBuilder(capacity).…builderAction).toString()");
        return string;
    }

    public static final <E> E commonValueAt(LongSparseArray<E> longSparseArray, int i2) {
        int i3;
        n.g(longSparseArray, "<this>");
        if (i2 < 0 || i2 >= (i3 = longSparseArray.size)) {
            throw new IllegalArgumentException(("Expected index to be within 0..size()-1, but was " + i2).toString());
        }
        if (longSparseArray.garbage) {
            long[] jArr = longSparseArray.keys;
            Object[] objArr = longSparseArray.values;
            int i4 = 0;
            for (int i5 = 0; i5 < i3; i5++) {
                Object obj = objArr[i5];
                if (obj != DELETED) {
                    if (i5 != i4) {
                        jArr[i4] = jArr[i5];
                        objArr[i4] = obj;
                        objArr[i5] = null;
                    }
                    i4++;
                }
            }
            longSparseArray.garbage = false;
            longSparseArray.size = i4;
        }
        return (E) longSparseArray.values[i2];
    }

    public static final <T> boolean contains(LongSparseArray<T> longSparseArray, long j2) {
        n.g(longSparseArray, "<this>");
        return longSparseArray.containsKey(j2);
    }

    public static final <T> void forEach(LongSparseArray<T> longSparseArray, Function2 action) {
        n.g(longSparseArray, "<this>");
        n.g(action, "action");
        int size = longSparseArray.size();
        for (int i2 = 0; i2 < size; i2++) {
            action.invoke(Long.valueOf(longSparseArray.keyAt(i2)), longSparseArray.valueAt(i2));
        }
    }

    public static final <T> T getOrDefault(LongSparseArray<T> longSparseArray, long j2, T t2) {
        n.g(longSparseArray, "<this>");
        return longSparseArray.get(j2, t2);
    }

    public static final <T> T getOrElse(LongSparseArray<T> longSparseArray, long j2, Function0 defaultValue) {
        n.g(longSparseArray, "<this>");
        n.g(defaultValue, "defaultValue");
        T t2 = longSparseArray.get(j2);
        return t2 == null ? (T) defaultValue.invoke() : t2;
    }

    public static final <T> int getSize(LongSparseArray<T> longSparseArray) {
        n.g(longSparseArray, "<this>");
        return longSparseArray.size();
    }

    public static /* synthetic */ void getSize$annotations(LongSparseArray longSparseArray) {
    }

    public static final <T> boolean isNotEmpty(LongSparseArray<T> longSparseArray) {
        n.g(longSparseArray, "<this>");
        return !longSparseArray.isEmpty();
    }

    public static final <T> B keyIterator(final LongSparseArray<T> longSparseArray) {
        n.g(longSparseArray, "<this>");
        return new B() { // from class: androidx.collection.LongSparseArrayKt.keyIterator.1
            private int index;

            public final int getIndex() {
                return this.index;
            }

            @Override // java.util.Iterator
            public boolean hasNext() {
                return this.index < longSparseArray.size();
            }

            /* JADX WARN: Type inference fix 'apply assigned field type' failed
            java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$PrimitiveArg
            	at jadx.core.dex.instructions.args.ArgType.getObject(ArgType.java:593)
            	at jadx.core.dex.attributes.nodes.ClassTypeVarsAttr.getTypeVarsMapFor(ClassTypeVarsAttr.java:35)
            	at jadx.core.dex.nodes.utils.TypeUtils.replaceClassGenerics(TypeUtils.java:177)
            	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.insertExplicitUseCast(FixTypesVisitor.java:397)
            	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryFieldTypeWithNewCasts(FixTypesVisitor.java:359)
            	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.applyFieldType(FixTypesVisitor.java:309)
            	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
             */
            @Override // I0.B
            public long nextLong() {
                LongSparseArray<T> longSparseArray2 = longSparseArray;
                int i2 = this.index;
                this.index = i2 + 1;
                return longSparseArray2.keyAt(i2);
            }

            public final void setIndex(int i2) {
                this.index = i2;
            }
        };
    }

    public static final <T> LongSparseArray<T> plus(LongSparseArray<T> longSparseArray, LongSparseArray<T> other) {
        n.g(longSparseArray, "<this>");
        n.g(other, "other");
        LongSparseArray<T> longSparseArray2 = new LongSparseArray<>(longSparseArray.size() + other.size());
        longSparseArray2.putAll(longSparseArray);
        longSparseArray2.putAll(other);
        return longSparseArray2;
    }

    public static final /* synthetic */ boolean remove(LongSparseArray longSparseArray, long j2, Object obj) {
        n.g(longSparseArray, "<this>");
        return longSparseArray.remove(j2, obj);
    }

    public static final <T> void set(LongSparseArray<T> longSparseArray, long j2, T t2) {
        n.g(longSparseArray, "<this>");
        longSparseArray.put(j2, t2);
    }

    public static final <T> Iterator<T> valueIterator(LongSparseArray<T> longSparseArray) {
        n.g(longSparseArray, "<this>");
        return new C01991(longSparseArray);
    }

    public static final <E> E commonGet(LongSparseArray<E> longSparseArray, long j2, E e2) {
        n.g(longSparseArray, "<this>");
        int iBinarySearch = ContainerHelpersKt.binarySearch(longSparseArray.keys, longSparseArray.size, j2);
        return (iBinarySearch < 0 || longSparseArray.values[iBinarySearch] == DELETED) ? e2 : (E) longSparseArray.values[iBinarySearch];
    }

    public static final <E> boolean commonReplace(LongSparseArray<E> longSparseArray, long j2, E e2, E e3) {
        n.g(longSparseArray, "<this>");
        int iIndexOfKey = longSparseArray.indexOfKey(j2);
        if (iIndexOfKey < 0 || !n.c(longSparseArray.values[iIndexOfKey], e2)) {
            return false;
        }
        longSparseArray.values[iIndexOfKey] = e3;
        return true;
    }

    public static final <E> boolean commonRemove(LongSparseArray<E> longSparseArray, long j2, E e2) {
        n.g(longSparseArray, "<this>");
        int iIndexOfKey = longSparseArray.indexOfKey(j2);
        if (iIndexOfKey < 0 || !n.c(e2, longSparseArray.valueAt(iIndexOfKey))) {
            return false;
        }
        longSparseArray.removeAt(iIndexOfKey);
        return true;
    }
}
