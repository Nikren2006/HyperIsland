package miui.systemui.util.concurrency;

import g1.E;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.o;
import miui.systemui.coroutines.CoroutineScopeKt;

/* JADX INFO: loaded from: classes4.dex */
public final class ConcurrencyModule$uiScope$2 extends o implements Function0 {
    public static final ConcurrencyModule$uiScope$2 INSTANCE = new ConcurrencyModule$uiScope$2();

    public ConcurrencyModule$uiScope$2() {
        super(0);
    }

    @Override // kotlin.jvm.functions.Function0
    public final E invoke() {
        return CoroutineScopeKt.MainScope();
    }
}
