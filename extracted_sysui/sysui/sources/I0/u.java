package I0;

import e1.InterfaceC0340e;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.ListIterator;
import java.util.NoSuchElementException;
import java.util.RandomAccess;
import java.util.Set;
import kotlin.jvm.functions.Function1;

/* JADX INFO: loaded from: classes2.dex */
public abstract class u extends t {

    public static final class a implements InterfaceC0340e {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ Iterable f334a;

        public a(Iterable iterable) {
            this.f334a = iterable;
        }

        @Override // e1.InterfaceC0340e
        public Iterator iterator() {
            return this.f334a.iterator();
        }
    }

    public static InterfaceC0340e E(Iterable iterable) {
        kotlin.jvm.internal.n.g(iterable, "<this>");
        return new a(iterable);
    }

    public static boolean F(Iterable iterable, Object obj) {
        kotlin.jvm.internal.n.g(iterable, "<this>");
        return iterable instanceof Collection ? ((Collection) iterable).contains(obj) : N(iterable, obj) >= 0;
    }

    public static List G(Iterable iterable, int i2) {
        ArrayList arrayList;
        kotlin.jvm.internal.n.g(iterable, "<this>");
        if (i2 < 0) {
            throw new IllegalArgumentException(("Requested element count " + i2 + " is less than zero.").toString());
        }
        if (i2 == 0) {
            return k0(iterable);
        }
        if (iterable instanceof Collection) {
            Collection collection = (Collection) iterable;
            int size = collection.size() - i2;
            if (size <= 0) {
                return m.h();
            }
            if (size == 1) {
                return AbstractC0184l.d(U(iterable));
            }
            arrayList = new ArrayList(size);
            if (iterable instanceof List) {
                if (iterable instanceof RandomAccess) {
                    int size2 = collection.size();
                    while (i2 < size2) {
                        arrayList.add(((List) iterable).get(i2));
                        i2++;
                    }
                } else {
                    ListIterator listIterator = ((List) iterable).listIterator(i2);
                    while (listIterator.hasNext()) {
                        arrayList.add(listIterator.next());
                    }
                }
                return arrayList;
            }
        } else {
            arrayList = new ArrayList();
        }
        int i3 = 0;
        for (Object obj : iterable) {
            if (i3 >= i2) {
                arrayList.add(obj);
            } else {
                i3++;
            }
        }
        return m.l(arrayList);
    }

    public static List H(Iterable iterable) {
        kotlin.jvm.internal.n.g(iterable, "<this>");
        return (List) I(iterable, new ArrayList());
    }

    public static final Collection I(Iterable iterable, Collection destination) {
        kotlin.jvm.internal.n.g(iterable, "<this>");
        kotlin.jvm.internal.n.g(destination, "destination");
        for (Object obj : iterable) {
            if (obj != null) {
                destination.add(obj);
            }
        }
        return destination;
    }

    public static final Object J(Iterable iterable) {
        kotlin.jvm.internal.n.g(iterable, "<this>");
        if (iterable instanceof List) {
            return K((List) iterable);
        }
        Iterator it = iterable.iterator();
        if (it.hasNext()) {
            return it.next();
        }
        throw new NoSuchElementException("Collection is empty.");
    }

    public static Object K(List list) {
        kotlin.jvm.internal.n.g(list, "<this>");
        if (list.isEmpty()) {
            throw new NoSuchElementException("List is empty.");
        }
        return list.get(0);
    }

    public static Object L(Iterable iterable) {
        kotlin.jvm.internal.n.g(iterable, "<this>");
        if (iterable instanceof List) {
            List list = (List) iterable;
            if (list.isEmpty()) {
                return null;
            }
            return list.get(0);
        }
        Iterator it = iterable.iterator();
        if (it.hasNext()) {
            return it.next();
        }
        return null;
    }

    public static Object M(List list) {
        kotlin.jvm.internal.n.g(list, "<this>");
        if (list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }

    public static final int N(Iterable iterable, Object obj) {
        kotlin.jvm.internal.n.g(iterable, "<this>");
        if (iterable instanceof List) {
            return ((List) iterable).indexOf(obj);
        }
        int i2 = 0;
        for (Object obj2 : iterable) {
            if (i2 < 0) {
                m.n();
            }
            if (kotlin.jvm.internal.n.c(obj, obj2)) {
                return i2;
            }
            i2++;
        }
        return -1;
    }

    public static int O(List list, Object obj) {
        kotlin.jvm.internal.n.g(list, "<this>");
        return list.indexOf(obj);
    }

    public static Set P(Iterable iterable, Iterable other) {
        kotlin.jvm.internal.n.g(iterable, "<this>");
        kotlin.jvm.internal.n.g(other, "other");
        Set setN0 = n0(iterable);
        r.B(setN0, other);
        return setN0;
    }

    public static final Appendable Q(Iterable iterable, Appendable buffer, CharSequence separator, CharSequence prefix, CharSequence postfix, int i2, CharSequence truncated, Function1 function1) throws IOException {
        kotlin.jvm.internal.n.g(iterable, "<this>");
        kotlin.jvm.internal.n.g(buffer, "buffer");
        kotlin.jvm.internal.n.g(separator, "separator");
        kotlin.jvm.internal.n.g(prefix, "prefix");
        kotlin.jvm.internal.n.g(postfix, "postfix");
        kotlin.jvm.internal.n.g(truncated, "truncated");
        buffer.append(prefix);
        int i3 = 0;
        for (Object obj : iterable) {
            i3++;
            if (i3 > 1) {
                buffer.append(separator);
            }
            if (i2 >= 0 && i3 > i2) {
                break;
            }
            f1.f.a(buffer, obj, function1);
        }
        if (i2 >= 0 && i3 > i2) {
            buffer.append(truncated);
        }
        buffer.append(postfix);
        return buffer;
    }

    public static final String S(Iterable iterable, CharSequence separator, CharSequence prefix, CharSequence postfix, int i2, CharSequence truncated, Function1 function1) {
        kotlin.jvm.internal.n.g(iterable, "<this>");
        kotlin.jvm.internal.n.g(separator, "separator");
        kotlin.jvm.internal.n.g(prefix, "prefix");
        kotlin.jvm.internal.n.g(postfix, "postfix");
        kotlin.jvm.internal.n.g(truncated, "truncated");
        String string = ((StringBuilder) Q(iterable, new StringBuilder(), separator, prefix, postfix, i2, truncated, function1)).toString();
        kotlin.jvm.internal.n.f(string, "toString(...)");
        return string;
    }

    public static /* synthetic */ String T(Iterable iterable, CharSequence charSequence, CharSequence charSequence2, CharSequence charSequence3, int i2, CharSequence charSequence4, Function1 function1, int i3, Object obj) {
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
        return S(iterable, charSequence, charSequence5, charSequence6, i4, charSequence7, function1);
    }

    public static final Object U(Iterable iterable) {
        kotlin.jvm.internal.n.g(iterable, "<this>");
        if (iterable instanceof List) {
            return V((List) iterable);
        }
        Iterator it = iterable.iterator();
        if (!it.hasNext()) {
            throw new NoSuchElementException("Collection is empty.");
        }
        Object next = it.next();
        while (it.hasNext()) {
            next = it.next();
        }
        return next;
    }

    public static Object V(List list) {
        kotlin.jvm.internal.n.g(list, "<this>");
        if (list.isEmpty()) {
            throw new NoSuchElementException("List is empty.");
        }
        return list.get(m.i(list));
    }

    public static Object W(List list) {
        kotlin.jvm.internal.n.g(list, "<this>");
        if (list.isEmpty()) {
            return null;
        }
        return list.get(list.size() - 1);
    }

    public static Comparable X(Iterable iterable) {
        kotlin.jvm.internal.n.g(iterable, "<this>");
        Iterator it = iterable.iterator();
        if (!it.hasNext()) {
            return null;
        }
        Comparable comparable = (Comparable) it.next();
        while (it.hasNext()) {
            Comparable comparable2 = (Comparable) it.next();
            if (comparable.compareTo(comparable2) > 0) {
                comparable = comparable2;
            }
        }
        return comparable;
    }

    public static List Y(Iterable iterable, Iterable elements) {
        kotlin.jvm.internal.n.g(iterable, "<this>");
        kotlin.jvm.internal.n.g(elements, "elements");
        Collection collectionV = r.v(elements);
        if (collectionV.isEmpty()) {
            return k0(iterable);
        }
        ArrayList arrayList = new ArrayList();
        for (Object obj : iterable) {
            if (!collectionV.contains(obj)) {
                arrayList.add(obj);
            }
        }
        return arrayList;
    }

    public static List Z(Iterable iterable, Object obj) {
        kotlin.jvm.internal.n.g(iterable, "<this>");
        ArrayList arrayList = new ArrayList(n.o(iterable, 10));
        boolean z2 = false;
        for (Object obj2 : iterable) {
            boolean z3 = true;
            if (!z2 && kotlin.jvm.internal.n.c(obj2, obj)) {
                z2 = true;
                z3 = false;
            }
            if (z3) {
                arrayList.add(obj2);
            }
        }
        return arrayList;
    }

    public static List a0(Collection collection, Iterable elements) {
        kotlin.jvm.internal.n.g(collection, "<this>");
        kotlin.jvm.internal.n.g(elements, "elements");
        if (!(elements instanceof Collection)) {
            ArrayList arrayList = new ArrayList(collection);
            r.t(arrayList, elements);
            return arrayList;
        }
        Collection collection2 = (Collection) elements;
        ArrayList arrayList2 = new ArrayList(collection.size() + collection2.size());
        arrayList2.addAll(collection);
        arrayList2.addAll(collection2);
        return arrayList2;
    }

    public static List b0(Collection collection, Object obj) {
        kotlin.jvm.internal.n.g(collection, "<this>");
        ArrayList arrayList = new ArrayList(collection.size() + 1);
        arrayList.addAll(collection);
        arrayList.add(obj);
        return arrayList;
    }

    public static List c0(Iterable iterable) {
        kotlin.jvm.internal.n.g(iterable, "<this>");
        if ((iterable instanceof Collection) && ((Collection) iterable).size() <= 1) {
            return k0(iterable);
        }
        List listL0 = l0(iterable);
        t.C(listL0);
        return listL0;
    }

    public static Object d0(Iterable iterable) {
        kotlin.jvm.internal.n.g(iterable, "<this>");
        if (iterable instanceof List) {
            return e0((List) iterable);
        }
        Iterator it = iterable.iterator();
        if (!it.hasNext()) {
            throw new NoSuchElementException("Collection is empty.");
        }
        Object next = it.next();
        if (it.hasNext()) {
            throw new IllegalArgumentException("Collection has more than one element.");
        }
        return next;
    }

    public static final Object e0(List list) {
        kotlin.jvm.internal.n.g(list, "<this>");
        int size = list.size();
        if (size == 0) {
            throw new NoSuchElementException("List is empty.");
        }
        if (size == 1) {
            return list.get(0);
        }
        throw new IllegalArgumentException("List has more than one element.");
    }

    public static Object f0(List list) {
        kotlin.jvm.internal.n.g(list, "<this>");
        if (list.size() == 1) {
            return list.get(0);
        }
        return null;
    }

    public static List g0(Iterable iterable, Comparator comparator) {
        kotlin.jvm.internal.n.g(iterable, "<this>");
        kotlin.jvm.internal.n.g(comparator, "comparator");
        if (!(iterable instanceof Collection)) {
            List listL0 = l0(iterable);
            q.r(listL0, comparator);
            return listL0;
        }
        Collection collection = (Collection) iterable;
        if (collection.size() <= 1) {
            return k0(iterable);
        }
        Object[] array = collection.toArray(new Object[0]);
        AbstractC0180h.u(array, comparator);
        return AbstractC0180h.c(array);
    }

    public static Set h0(Iterable iterable, Iterable other) {
        kotlin.jvm.internal.n.g(iterable, "<this>");
        kotlin.jvm.internal.n.g(other, "other");
        Set setN0 = n0(iterable);
        r.y(setN0, other);
        return setN0;
    }

    public static List i0(Iterable iterable, int i2) {
        kotlin.jvm.internal.n.g(iterable, "<this>");
        if (i2 < 0) {
            throw new IllegalArgumentException(("Requested element count " + i2 + " is less than zero.").toString());
        }
        if (i2 == 0) {
            return m.h();
        }
        if (iterable instanceof Collection) {
            if (i2 >= ((Collection) iterable).size()) {
                return k0(iterable);
            }
            if (i2 == 1) {
                return AbstractC0184l.d(J(iterable));
            }
        }
        ArrayList arrayList = new ArrayList(i2);
        Iterator it = iterable.iterator();
        int i3 = 0;
        while (it.hasNext()) {
            arrayList.add(it.next());
            i3++;
            if (i3 == i2) {
                break;
            }
        }
        return m.l(arrayList);
    }

    public static final Collection j0(Iterable iterable, Collection destination) {
        kotlin.jvm.internal.n.g(iterable, "<this>");
        kotlin.jvm.internal.n.g(destination, "destination");
        Iterator it = iterable.iterator();
        while (it.hasNext()) {
            destination.add(it.next());
        }
        return destination;
    }

    public static List k0(Iterable iterable) {
        kotlin.jvm.internal.n.g(iterable, "<this>");
        if (!(iterable instanceof Collection)) {
            return m.l(l0(iterable));
        }
        Collection collection = (Collection) iterable;
        int size = collection.size();
        if (size == 0) {
            return m.h();
        }
        if (size != 1) {
            return m0(collection);
        }
        return AbstractC0184l.d(iterable instanceof List ? ((List) iterable).get(0) : iterable.iterator().next());
    }

    public static final List l0(Iterable iterable) {
        kotlin.jvm.internal.n.g(iterable, "<this>");
        return iterable instanceof Collection ? m0((Collection) iterable) : (List) j0(iterable, new ArrayList());
    }

    public static List m0(Collection collection) {
        kotlin.jvm.internal.n.g(collection, "<this>");
        return new ArrayList(collection);
    }

    public static Set n0(Iterable iterable) {
        kotlin.jvm.internal.n.g(iterable, "<this>");
        return iterable instanceof Collection ? new LinkedHashSet((Collection) iterable) : (Set) j0(iterable, new LinkedHashSet());
    }

    public static Set o0(Iterable iterable) {
        kotlin.jvm.internal.n.g(iterable, "<this>");
        if (!(iterable instanceof Collection)) {
            return K.c((Set) j0(iterable, new LinkedHashSet()));
        }
        Collection collection = (Collection) iterable;
        int size = collection.size();
        if (size == 0) {
            return K.b();
        }
        if (size != 1) {
            return (Set) j0(iterable, new LinkedHashSet(F.c(collection.size())));
        }
        return J.a(iterable instanceof List ? ((List) iterable).get(0) : iterable.iterator().next());
    }

    public static List p0(Iterable iterable, Iterable other) {
        kotlin.jvm.internal.n.g(iterable, "<this>");
        kotlin.jvm.internal.n.g(other, "other");
        Iterator it = iterable.iterator();
        Iterator it2 = other.iterator();
        ArrayList arrayList = new ArrayList(Math.min(n.o(iterable, 10), n.o(other, 10)));
        while (it.hasNext() && it2.hasNext()) {
            arrayList.add(H0.o.a(it.next(), it2.next()));
        }
        return arrayList;
    }
}
