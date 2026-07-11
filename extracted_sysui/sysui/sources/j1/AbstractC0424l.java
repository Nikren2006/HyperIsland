package j1;

import k1.p;

/* JADX INFO: renamed from: j1.l, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes2.dex */
public abstract /* synthetic */ class AbstractC0424l {
    public static final InterfaceC0418f a(InterfaceC0418f interfaceC0418f, int i2, i1.a aVar) {
        if (i2 < 0 && i2 != -2 && i2 != -1) {
            throw new IllegalArgumentException(("Buffer size should be non-negative, BUFFERED, or CONFLATED, but was " + i2).toString());
        }
        if (i2 == -1 && aVar != i1.a.SUSPEND) {
            throw new IllegalArgumentException("CONFLATED capacity cannot be used with non-default onBufferOverflow");
        }
        if (i2 == -1) {
            aVar = i1.a.DROP_OLDEST;
            i2 = 0;
        }
        int i3 = i2;
        i1.a aVar2 = aVar;
        if (interfaceC0418f instanceof k1.p) {
            return p.a.a((k1.p) interfaceC0418f, null, i3, aVar2, 1, null);
        }
        return new k1.h(interfaceC0418f, null, i3, aVar2, 2, null);
    }

    public static /* synthetic */ InterfaceC0418f b(InterfaceC0418f interfaceC0418f, int i2, i1.a aVar, int i3, Object obj) {
        if ((i3 & 1) != 0) {
            i2 = -2;
        }
        if ((i3 & 2) != 0) {
            aVar = i1.a.SUSPEND;
        }
        return AbstractC0420h.c(interfaceC0418f, i2, aVar);
    }

    public static final InterfaceC0418f c(InterfaceC0418f interfaceC0418f) {
        return b(interfaceC0418f, -1, null, 2, null);
    }
}
