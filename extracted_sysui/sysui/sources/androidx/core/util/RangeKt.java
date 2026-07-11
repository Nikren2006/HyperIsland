package androidx.core.util;

import android.util.Range;
import androidx.annotation.RequiresApi;
import c1.InterfaceC0229a;

/* JADX INFO: loaded from: classes.dex */
public final class RangeKt {
    @RequiresApi(21)
    public static final <T extends Comparable<? super T>> Range<T> and(Range<T> range, Range<T> range2) {
        return range.intersect(range2);
    }

    @RequiresApi(21)
    public static final <T extends Comparable<? super T>> Range<T> plus(Range<T> range, T t2) {
        return range.extend(t2);
    }

    @RequiresApi(21)
    public static final <T extends Comparable<? super T>> Range<T> rangeTo(T t2, T t3) {
        return new Range<>(t2, t3);
    }

    @RequiresApi(21)
    public static final <T extends Comparable<? super T>> InterfaceC0229a toClosedRange(final Range<T> range) {
        return new InterfaceC0229a() { // from class: androidx.core.util.RangeKt.toClosedRange.1
            /* JADX WARN: Incorrect types in method signature: (TT;)Z */
            public boolean contains(Comparable comparable) {
                return InterfaceC0229a.C0048a.a(this, comparable);
            }

            /* JADX WARN: Incorrect return type in method signature: ()TT; */
            @Override // c1.InterfaceC0229a
            public Comparable getEndInclusive() {
                return range.getUpper();
            }

            /* JADX WARN: Incorrect return type in method signature: ()TT; */
            @Override // c1.InterfaceC0229a
            public Comparable getStart() {
                return range.getLower();
            }

            public boolean isEmpty() {
                return InterfaceC0229a.C0048a.b(this);
            }
        };
    }

    @RequiresApi(21)
    public static final <T extends Comparable<? super T>> Range<T> toRange(InterfaceC0229a interfaceC0229a) {
        return new Range<>(interfaceC0229a.getStart(), interfaceC0229a.getEndInclusive());
    }

    @RequiresApi(21)
    public static final <T extends Comparable<? super T>> Range<T> plus(Range<T> range, Range<T> range2) {
        return range.extend(range2);
    }
}
