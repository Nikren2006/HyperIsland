package miui.systemui.util.concurrency;

import android.os.HandlerThread;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.o;

/* JADX INFO: loaded from: classes4.dex */
public final class ConcurrencyModule$workerThread$2 extends o implements Function0 {
    public static final ConcurrencyModule$workerThread$2 INSTANCE = new ConcurrencyModule$workerThread$2();

    public ConcurrencyModule$workerThread$2() {
        super(0);
    }

    @Override // kotlin.jvm.functions.Function0
    public final HandlerThread invoke() {
        HandlerThread handlerThread = new HandlerThread("PluginWorker", -2);
        handlerThread.start();
        return handlerThread;
    }
}
