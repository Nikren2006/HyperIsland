package j1;

/* JADX INFO: loaded from: classes2.dex */
public abstract class A {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final l1.F f4637a = new l1.F("NO_VALUE");

    public static final t a(int i2, int i3, i1.a aVar) {
        if (i2 < 0) {
            throw new IllegalArgumentException(("replay cannot be negative, but was " + i2).toString());
        }
        if (i3 < 0) {
            throw new IllegalArgumentException(("extraBufferCapacity cannot be negative, but was " + i3).toString());
        }
        if (i2 > 0 || i3 > 0 || aVar == i1.a.SUSPEND) {
            int i4 = i3 + i2;
            if (i4 < 0) {
                i4 = Integer.MAX_VALUE;
            }
            return new z(i2, i4, aVar);
        }
        throw new IllegalArgumentException(("replay or extraBufferCapacity must be positive with non-default onBufferOverflow strategy " + aVar).toString());
    }

    public static /* synthetic */ t b(int i2, int i3, i1.a aVar, int i4, Object obj) {
        if ((i4 & 1) != 0) {
            i2 = 0;
        }
        if ((i4 & 2) != 0) {
            i3 = 0;
        }
        if ((i4 & 4) != 0) {
            aVar = i1.a.SUSPEND;
        }
        return a(i2, i3, aVar);
    }

    public static final InterfaceC0418f e(y yVar, L0.g gVar, int i2, i1.a aVar) {
        return ((i2 == 0 || i2 == -3) && aVar == i1.a.SUSPEND) ? yVar : new k1.h(yVar, gVar, i2, aVar);
    }

    public static final Object f(Object[] objArr, long j2) {
        return objArr[((int) j2) & (objArr.length - 1)];
    }

    public static final void g(Object[] objArr, long j2, Object obj) {
        objArr[((int) j2) & (objArr.length - 1)] = obj;
    }
}
