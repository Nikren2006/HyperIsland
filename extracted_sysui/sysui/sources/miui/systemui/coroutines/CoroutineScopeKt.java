package miui.systemui.coroutines;

import g1.E;
import g1.F;
import g1.F0;

/* JADX INFO: loaded from: classes.dex */
public final class CoroutineScopeKt {
    public static final E MainScope() {
        return F.a(F0.b(null, 1, null).plus(Dispatchers.INSTANCE.getMain()));
    }
}
