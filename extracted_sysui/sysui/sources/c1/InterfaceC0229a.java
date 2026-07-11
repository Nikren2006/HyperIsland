package c1;

import kotlin.jvm.internal.n;

/* JADX INFO: renamed from: c1.a, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes2.dex */
public interface InterfaceC0229a {

    /* JADX INFO: renamed from: c1.a$a, reason: collision with other inner class name */
    public static final class C0048a {
        public static boolean a(InterfaceC0229a interfaceC0229a, Comparable value) {
            n.g(value, "value");
            return value.compareTo(interfaceC0229a.getStart()) >= 0 && value.compareTo(interfaceC0229a.getEndInclusive()) <= 0;
        }

        public static boolean b(InterfaceC0229a interfaceC0229a) {
            return interfaceC0229a.getStart().compareTo(interfaceC0229a.getEndInclusive()) > 0;
        }
    }

    Comparable getEndInclusive();

    Comparable getStart();
}
