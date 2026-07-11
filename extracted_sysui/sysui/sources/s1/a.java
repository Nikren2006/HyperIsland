package s1;

import d1.InterfaceC0324c;
import kotlin.jvm.internal.n;

/* JADX INFO: loaded from: classes2.dex */
public abstract class a {
    public static final InterfaceC0324c a(c cVar) {
        n.g(cVar, "<this>");
        return null;
    }

    public static final c b(x1.b bVar, c descriptor) {
        q1.b bVarB;
        n.g(bVar, "<this>");
        n.g(descriptor, "descriptor");
        InterfaceC0324c interfaceC0324cA = a(descriptor);
        if (interfaceC0324cA == null || (bVarB = x1.b.b(bVar, interfaceC0324cA, null, 2, null)) == null) {
            return null;
        }
        return bVarB.getDescriptor();
    }
}
