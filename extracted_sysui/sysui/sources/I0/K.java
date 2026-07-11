package I0;

import java.util.Set;

/* JADX INFO: loaded from: classes2.dex */
public abstract class K extends J {
    public static Set b() {
        return y.f338a;
    }

    public static final Set c(Set set) {
        kotlin.jvm.internal.n.g(set, "<this>");
        int size = set.size();
        return size != 0 ? size != 1 ? set : J.a(set.iterator().next()) : b();
    }

    public static Set d(Object... elements) {
        kotlin.jvm.internal.n.g(elements, "elements");
        return elements.length > 0 ? AbstractC0181i.U(elements) : b();
    }
}
