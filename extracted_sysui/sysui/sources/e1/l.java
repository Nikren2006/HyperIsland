package e1;

import I0.AbstractC0184l;
import I0.r;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.n;
import kotlin.jvm.internal.o;

/* JADX INFO: loaded from: classes2.dex */
public abstract class l extends k {

    public static final class a implements Iterable, W0.a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ InterfaceC0340e f4066a;

        public a(InterfaceC0340e interfaceC0340e) {
            this.f4066a = interfaceC0340e;
        }

        @Override // java.lang.Iterable
        public Iterator iterator() {
            return this.f4066a.iterator();
        }
    }

    public static final class b extends o implements Function1 {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final b f4067a = new b();

        public b() {
            super(1);
        }

        @Override // kotlin.jvm.functions.Function1
        /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
        public final Boolean invoke(Object obj) {
            return Boolean.valueOf(obj == null);
        }
    }

    public static final class c implements InterfaceC0340e {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ Iterable f4068a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final /* synthetic */ InterfaceC0340e f4069b;

        public static final class a extends o implements Function1 {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            public final /* synthetic */ Collection f4070a;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public a(Collection collection) {
                super(1);
                this.f4070a = collection;
            }

            @Override // kotlin.jvm.functions.Function1
            /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
            public final Boolean invoke(Object obj) {
                return Boolean.valueOf(this.f4070a.contains(obj));
            }
        }

        public c(Iterable iterable, InterfaceC0340e interfaceC0340e) {
            this.f4068a = iterable;
            this.f4069b = interfaceC0340e;
        }

        @Override // e1.InterfaceC0340e
        public Iterator iterator() {
            Collection collectionV = r.v(this.f4068a);
            return collectionV.isEmpty() ? this.f4069b.iterator() : l.j(this.f4069b, new a(collectionV)).iterator();
        }
    }

    public static Iterable f(InterfaceC0340e interfaceC0340e) {
        n.g(interfaceC0340e, "<this>");
        return new a(interfaceC0340e);
    }

    public static boolean g(InterfaceC0340e interfaceC0340e, Object obj) {
        n.g(interfaceC0340e, "<this>");
        return n(interfaceC0340e, obj) >= 0;
    }

    public static int h(InterfaceC0340e interfaceC0340e) {
        n.g(interfaceC0340e, "<this>");
        Iterator it = interfaceC0340e.iterator();
        int i2 = 0;
        while (it.hasNext()) {
            it.next();
            i2++;
            if (i2 < 0) {
                I0.m.m();
            }
        }
        return i2;
    }

    public static InterfaceC0340e i(InterfaceC0340e interfaceC0340e, Function1 predicate) {
        n.g(interfaceC0340e, "<this>");
        n.g(predicate, "predicate");
        return new C0338c(interfaceC0340e, true, predicate);
    }

    public static final InterfaceC0340e j(InterfaceC0340e interfaceC0340e, Function1 predicate) {
        n.g(interfaceC0340e, "<this>");
        n.g(predicate, "predicate");
        return new C0338c(interfaceC0340e, false, predicate);
    }

    public static final InterfaceC0340e k(InterfaceC0340e interfaceC0340e) {
        n.g(interfaceC0340e, "<this>");
        InterfaceC0340e interfaceC0340eJ = j(interfaceC0340e, b.f4067a);
        n.e(interfaceC0340eJ, "null cannot be cast to non-null type kotlin.sequences.Sequence<T of kotlin.sequences.SequencesKt___SequencesKt.filterNotNull>");
        return interfaceC0340eJ;
    }

    public static Object l(InterfaceC0340e interfaceC0340e) {
        n.g(interfaceC0340e, "<this>");
        Iterator it = interfaceC0340e.iterator();
        if (it.hasNext()) {
            return it.next();
        }
        throw new NoSuchElementException("Sequence is empty.");
    }

    public static Object m(InterfaceC0340e interfaceC0340e) {
        n.g(interfaceC0340e, "<this>");
        Iterator it = interfaceC0340e.iterator();
        if (it.hasNext()) {
            return it.next();
        }
        return null;
    }

    public static final int n(InterfaceC0340e interfaceC0340e, Object obj) {
        n.g(interfaceC0340e, "<this>");
        int i2 = 0;
        for (Object obj2 : interfaceC0340e) {
            if (i2 < 0) {
                I0.m.n();
            }
            if (n.c(obj, obj2)) {
                return i2;
            }
            i2++;
        }
        return -1;
    }

    public static InterfaceC0340e o(InterfaceC0340e interfaceC0340e, Function1 transform) {
        n.g(interfaceC0340e, "<this>");
        n.g(transform, "transform");
        return new m(interfaceC0340e, transform);
    }

    public static InterfaceC0340e p(InterfaceC0340e interfaceC0340e, Function1 transform) {
        n.g(interfaceC0340e, "<this>");
        n.g(transform, "transform");
        return k(new m(interfaceC0340e, transform));
    }

    public static InterfaceC0340e q(InterfaceC0340e interfaceC0340e, Iterable elements) {
        n.g(interfaceC0340e, "<this>");
        n.g(elements, "elements");
        return new c(elements, interfaceC0340e);
    }

    public static List r(InterfaceC0340e interfaceC0340e) {
        n.g(interfaceC0340e, "<this>");
        Iterator it = interfaceC0340e.iterator();
        if (!it.hasNext()) {
            return I0.m.h();
        }
        Object next = it.next();
        if (!it.hasNext()) {
            return AbstractC0184l.d(next);
        }
        ArrayList arrayList = new ArrayList();
        arrayList.add(next);
        while (it.hasNext()) {
            arrayList.add(it.next());
        }
        return arrayList;
    }
}
