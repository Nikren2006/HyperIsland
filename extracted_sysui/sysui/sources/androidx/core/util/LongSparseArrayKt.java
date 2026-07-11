package androidx.core.util;

import I0.B;
import android.util.LongSparseArray;
import java.util.Iterator;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.n;

/* JADX INFO: loaded from: classes.dex */
public final class LongSparseArrayKt {

    /* JADX INFO: Add missing generic type declarations: [T] */
    /* JADX INFO: renamed from: androidx.core.util.LongSparseArrayKt$valueIterator$1, reason: invalid class name and case insensitive filesystem */
    public static final class C02041<T> implements Iterator<T>, W0.a {
        final /* synthetic */ LongSparseArray<T> $this_valueIterator;
        private int index;

        public C02041(LongSparseArray<T> longSparseArray) {
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

    public static final <T> boolean contains(LongSparseArray<T> longSparseArray, long j2) {
        return longSparseArray.indexOfKey(j2) >= 0;
    }

    public static final <T> boolean containsKey(LongSparseArray<T> longSparseArray, long j2) {
        return longSparseArray.indexOfKey(j2) >= 0;
    }

    public static final <T> boolean containsValue(LongSparseArray<T> longSparseArray, T t2) {
        return longSparseArray.indexOfValue(t2) >= 0;
    }

    public static final <T> void forEach(LongSparseArray<T> longSparseArray, Function2 function2) {
        int size = longSparseArray.size();
        for (int i2 = 0; i2 < size; i2++) {
            function2.invoke(Long.valueOf(longSparseArray.keyAt(i2)), longSparseArray.valueAt(i2));
        }
    }

    public static final <T> T getOrDefault(LongSparseArray<T> longSparseArray, long j2, T t2) {
        T t3 = longSparseArray.get(j2);
        return t3 == null ? t2 : t3;
    }

    public static final <T> T getOrElse(LongSparseArray<T> longSparseArray, long j2, Function0 function0) {
        T t2 = longSparseArray.get(j2);
        return t2 == null ? (T) function0.invoke() : t2;
    }

    public static final <T> int getSize(LongSparseArray<T> longSparseArray) {
        return longSparseArray.size();
    }

    public static final <T> boolean isEmpty(LongSparseArray<T> longSparseArray) {
        return longSparseArray.size() == 0;
    }

    public static final <T> boolean isNotEmpty(LongSparseArray<T> longSparseArray) {
        return longSparseArray.size() != 0;
    }

    public static final <T> B keyIterator(final LongSparseArray<T> longSparseArray) {
        return new B() { // from class: androidx.core.util.LongSparseArrayKt.keyIterator.1
            private int index;

            public final int getIndex() {
                return this.index;
            }

            @Override // java.util.Iterator
            public boolean hasNext() {
                return this.index < longSparseArray.size();
            }

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

    public static final <T> LongSparseArray<T> plus(LongSparseArray<T> longSparseArray, LongSparseArray<T> longSparseArray2) {
        LongSparseArray<T> longSparseArray3 = new LongSparseArray<>(longSparseArray.size() + longSparseArray2.size());
        putAll(longSparseArray3, longSparseArray);
        putAll(longSparseArray3, longSparseArray2);
        return longSparseArray3;
    }

    public static final <T> void putAll(LongSparseArray<T> longSparseArray, LongSparseArray<T> longSparseArray2) {
        int size = longSparseArray2.size();
        for (int i2 = 0; i2 < size; i2++) {
            longSparseArray.put(longSparseArray2.keyAt(i2), longSparseArray2.valueAt(i2));
        }
    }

    public static final <T> boolean remove(LongSparseArray<T> longSparseArray, long j2, T t2) {
        int iIndexOfKey = longSparseArray.indexOfKey(j2);
        if (iIndexOfKey < 0 || !n.c(t2, longSparseArray.valueAt(iIndexOfKey))) {
            return false;
        }
        longSparseArray.removeAt(iIndexOfKey);
        return true;
    }

    public static final <T> void set(LongSparseArray<T> longSparseArray, long j2, T t2) {
        longSparseArray.put(j2, t2);
    }

    public static final <T> Iterator<T> valueIterator(LongSparseArray<T> longSparseArray) {
        return new C02041(longSparseArray);
    }
}
