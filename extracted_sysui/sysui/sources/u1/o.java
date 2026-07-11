package u1;

import java.util.Arrays;
import java.util.Iterator;

/* JADX INFO: loaded from: classes2.dex */
public abstract class o {
    public static final int a(s1.c cVar, s1.c[] typeParams) {
        kotlin.jvm.internal.n.g(cVar, "<this>");
        kotlin.jvm.internal.n.g(typeParams, "typeParams");
        int iHashCode = (cVar.h().hashCode() * 31) + Arrays.hashCode(typeParams);
        Iterable iterableA = s1.d.a(cVar);
        Iterator it = iterableA.iterator();
        int iHashCode2 = 1;
        int i2 = 1;
        while (true) {
            int iHashCode3 = 0;
            if (!it.hasNext()) {
                break;
            }
            int i3 = i2 * 31;
            String strH = ((s1.c) it.next()).h();
            if (strH != null) {
                iHashCode3 = strH.hashCode();
            }
            i2 = i3 + iHashCode3;
        }
        Iterator it2 = iterableA.iterator();
        while (it2.hasNext()) {
            int i4 = iHashCode2 * 31;
            s1.e eVarC = ((s1.c) it2.next()).c();
            iHashCode2 = i4 + (eVarC != null ? eVarC.hashCode() : 0);
        }
        return (((iHashCode * 31) + i2) * 31) + iHashCode2;
    }
}
