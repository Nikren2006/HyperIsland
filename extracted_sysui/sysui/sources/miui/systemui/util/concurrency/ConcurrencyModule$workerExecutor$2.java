package miui.systemui.util.concurrency;

import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.o;

/* JADX INFO: loaded from: classes4.dex */
public final class ConcurrencyModule$workerExecutor$2 extends o implements Function0 {
    public static final ConcurrencyModule$workerExecutor$2 INSTANCE = new ConcurrencyModule$workerExecutor$2();

    public ConcurrencyModule$workerExecutor$2() {
        super(0);
    }

    @Override // kotlin.jvm.functions.Function0
    public final ExecutorImpl invoke() {
        return new ExecutorImpl(ConcurrencyModule.INSTANCE.getWorkerThread().getLooper());
    }
}
