package I0;

import c1.C0232d;
import e1.InterfaceC0340e;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.RandomAccess;
import kotlin.jvm.functions.Function1;

/* JADX INFO: loaded from: classes2.dex */
public abstract class r extends q {
    public static Object A(List list) {
        kotlin.jvm.internal.n.g(list, "<this>");
        if (list.isEmpty()) {
            throw new NoSuchElementException("List is empty.");
        }
        return list.remove(m.i(list));
    }

    public static final boolean B(Collection collection, Iterable elements) {
        kotlin.jvm.internal.n.g(collection, "<this>");
        kotlin.jvm.internal.n.g(elements, "elements");
        return collection.retainAll(v(elements));
    }

    public static boolean s(Collection collection, InterfaceC0340e elements) {
        kotlin.jvm.internal.n.g(collection, "<this>");
        kotlin.jvm.internal.n.g(elements, "elements");
        Iterator it = elements.iterator();
        boolean z2 = false;
        while (it.hasNext()) {
            if (collection.add(it.next())) {
                z2 = true;
            }
        }
        return z2;
    }

    public static boolean t(Collection collection, Iterable elements) {
        kotlin.jvm.internal.n.g(collection, "<this>");
        kotlin.jvm.internal.n.g(elements, "elements");
        if (elements instanceof Collection) {
            return collection.addAll((Collection) elements);
        }
        Iterator it = elements.iterator();
        boolean z2 = false;
        while (it.hasNext()) {
            if (collection.add(it.next())) {
                z2 = true;
            }
        }
        return z2;
    }

    public static boolean u(Collection collection, Object[] elements) {
        kotlin.jvm.internal.n.g(collection, "<this>");
        kotlin.jvm.internal.n.g(elements, "elements");
        return collection.addAll(AbstractC0180h.c(elements));
    }

    public static Collection v(Iterable iterable) {
        kotlin.jvm.internal.n.g(iterable, "<this>");
        return iterable instanceof Collection ? (Collection) iterable : u.k0(iterable);
    }

    public static final boolean w(Iterable iterable, Function1 function1, boolean z2) {
        Iterator it = iterable.iterator();
        boolean z3 = false;
        while (it.hasNext()) {
            if (((Boolean) function1.invoke(it.next())).booleanValue() == z2) {
                it.remove();
                z3 = true;
            }
        }
        return z3;
    }

    public static final boolean x(List list, Function1 function1, boolean z2) {
        if (!(list instanceof RandomAccess)) {
            kotlin.jvm.internal.n.e(list, "null cannot be cast to non-null type kotlin.collections.MutableIterable<T of kotlin.collections.CollectionsKt__MutableCollectionsKt.filterInPlace>");
            return w(kotlin.jvm.internal.D.b(list), function1, z2);
        }
        A it = new C0232d(0, m.i(list)).iterator();
        int i2 = 0;
        while (it.hasNext()) {
            int iNextInt = it.nextInt();
            Object obj = list.get(iNextInt);
            if (((Boolean) function1.invoke(obj)).booleanValue() != z2) {
                if (i2 != iNextInt) {
                    list.set(i2, obj);
                }
                i2++;
            }
        }
        if (i2 >= list.size()) {
            return false;
        }
        int i3 = m.i(list);
        if (i2 > i3) {
            return true;
        }
        while (true) {
            list.remove(i3);
            if (i3 == i2) {
                return true;
            }
            i3--;
        }
    }

    public static final boolean y(Collection collection, Iterable elements) {
        kotlin.jvm.internal.n.g(collection, "<this>");
        kotlin.jvm.internal.n.g(elements, "elements");
        return collection.removeAll(v(elements));
    }

    public static boolean z(List list, Function1 predicate) {
        kotlin.jvm.internal.n.g(list, "<this>");
        kotlin.jvm.internal.n.g(predicate, "predicate");
        return x(list, predicate, true);
    }
}
