package k1;

import j1.InterfaceC0418f;

/* JADX INFO: loaded from: classes2.dex */
public interface p extends InterfaceC0418f {

    public static final class a {
        public static /* synthetic */ InterfaceC0418f a(p pVar, L0.g gVar, int i2, i1.a aVar, int i3, Object obj) {
            if (obj != null) {
                throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: fuse");
            }
            if ((i3 & 1) != 0) {
                gVar = L0.h.f402a;
            }
            if ((i3 & 2) != 0) {
                i2 = -3;
            }
            if ((i3 & 4) != 0) {
                aVar = i1.a.SUSPEND;
            }
            return pVar.a(gVar, i2, aVar);
        }
    }

    InterfaceC0418f a(L0.g gVar, int i2, i1.a aVar);
}
