package x1;

import I0.m;
import d1.InterfaceC0324c;
import java.util.List;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* JADX INFO: loaded from: classes2.dex */
public abstract class b {
    public /* synthetic */ b(DefaultConstructorMarker defaultConstructorMarker) {
        this();
    }

    public static /* synthetic */ q1.b b(b bVar, InterfaceC0324c interfaceC0324c, List list, int i2, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: getContextual");
        }
        if ((i2 & 2) != 0) {
            list = m.h();
        }
        return bVar.a(interfaceC0324c, list);
    }

    public abstract q1.b a(InterfaceC0324c interfaceC0324c, List list);

    public b() {
    }
}
