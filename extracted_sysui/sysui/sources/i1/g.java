package i1;

import kotlin.jvm.functions.Function1;

/* JADX INFO: loaded from: classes2.dex */
public abstract class g {
    public static final d a(int i2, a aVar, Function1 function1) {
        d bVar;
        if (i2 == -2) {
            bVar = aVar == a.SUSPEND ? new b(d.f4600C.a(), function1) : new n(1, aVar, function1);
        } else {
            if (i2 == -1) {
                if (aVar == a.SUSPEND) {
                    return new n(1, a.DROP_OLDEST, function1);
                }
                throw new IllegalArgumentException("CONFLATED capacity cannot be used with non-default onBufferOverflow");
            }
            if (i2 != 0) {
                return i2 != Integer.MAX_VALUE ? aVar == a.SUSPEND ? new b(i2, function1) : new n(i2, aVar, function1) : new b(Integer.MAX_VALUE, function1);
            }
            bVar = aVar == a.SUSPEND ? new b(0, function1) : new n(1, aVar, function1);
        }
        return bVar;
    }

    public static /* synthetic */ d b(int i2, a aVar, Function1 function1, int i3, Object obj) {
        if ((i3 & 1) != 0) {
            i2 = 0;
        }
        if ((i3 & 2) != 0) {
            aVar = a.SUSPEND;
        }
        if ((i3 & 4) != 0) {
            function1 = null;
        }
        return a(i2, aVar, function1);
    }
}
