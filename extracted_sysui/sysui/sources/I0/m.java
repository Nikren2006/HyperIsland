package I0;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public abstract class m extends AbstractC0184l {
    public static ArrayList f(Object... elements) {
        kotlin.jvm.internal.n.g(elements, "elements");
        return elements.length == 0 ? new ArrayList() : new ArrayList(new C0176d(elements, true));
    }

    public static final Collection g(Object[] objArr) {
        kotlin.jvm.internal.n.g(objArr, "<this>");
        return new C0176d(objArr, false);
    }

    public static List h() {
        return w.f336a;
    }

    public static int i(List list) {
        kotlin.jvm.internal.n.g(list, "<this>");
        return list.size() - 1;
    }

    public static List j(Object... elements) {
        kotlin.jvm.internal.n.g(elements, "elements");
        return elements.length > 0 ? AbstractC0180h.c(elements) : h();
    }

    public static List k(Object... elements) {
        kotlin.jvm.internal.n.g(elements, "elements");
        return elements.length == 0 ? new ArrayList() : new ArrayList(new C0176d(elements, true));
    }

    public static final List l(List list) {
        kotlin.jvm.internal.n.g(list, "<this>");
        int size = list.size();
        return size != 0 ? size != 1 ? list : AbstractC0184l.d(list.get(0)) : h();
    }

    public static void m() {
        throw new ArithmeticException("Count overflow has happened.");
    }

    public static void n() {
        throw new ArithmeticException("Index overflow has happened.");
    }
}
