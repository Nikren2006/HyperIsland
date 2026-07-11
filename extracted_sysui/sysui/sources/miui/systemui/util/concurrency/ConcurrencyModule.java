package miui.systemui.util.concurrency;

import H0.d;
import H0.e;
import android.content.Context;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import g1.E;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import kotlin.jvm.internal.n;
import miui.systemui.dagger.qualifiers.Background;
import miui.systemui.dagger.qualifiers.Main;
import miui.systemui.dagger.qualifiers.UiBackground;
import miui.systemui.dagger.qualifiers.Worker;

/* JADX INFO: loaded from: classes4.dex */
public final class ConcurrencyModule {
    public static final ConcurrencyModule INSTANCE = new ConcurrencyModule();
    private static final d workerThread$delegate = e.b(ConcurrencyModule$workerThread$2.INSTANCE);
    private static final d workerExecutor$delegate = e.b(ConcurrencyModule$workerExecutor$2.INSTANCE);
    private static final d bgThread$delegate = e.b(ConcurrencyModule$bgThread$2.INSTANCE);
    private static final d bgExecutor$delegate = e.b(ConcurrencyModule$bgExecutor$2.INSTANCE);
    private static final d uiScope$delegate = e.b(ConcurrencyModule$uiScope$2.INSTANCE);

    private ConcurrencyModule() {
    }

    private final ExecutorImpl getBgExecutor() {
        return (ExecutorImpl) bgExecutor$delegate.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final HandlerThread getBgThread() {
        return (HandlerThread) bgThread$delegate.getValue();
    }

    public static /* synthetic */ void getUiScope$annotations() {
    }

    private final ExecutorImpl getWorkerExecutor() {
        return (ExecutorImpl) workerExecutor$delegate.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final HandlerThread getWorkerThread() {
        return (HandlerThread) workerThread$delegate.getValue();
    }

    public final E getUiScope() {
        return (E) uiScope$delegate.getValue();
    }

    @Background
    public final DelayableExecutor provideBackgroundDelayableExecutor() {
        return getBgExecutor();
    }

    @Background
    public final Executor provideBackgroundExecutor() {
        return getBgExecutor();
    }

    @Background
    public final Handler provideBgHandler() {
        return new Handler(getBgThread().getLooper());
    }

    @Background
    public final Looper provideBgLooper() {
        Looper looper = getBgThread().getLooper();
        n.f(looper, "getLooper(...)");
        return looper;
    }

    public final DelayableExecutor provideDelayableExecutor() {
        return getBgExecutor();
    }

    public final Executor provideExecutor() {
        return getBgExecutor();
    }

    @Main
    public final DelayableExecutor provideMainDelayableExecutor() {
        return new ExecutorImpl(Looper.getMainLooper());
    }

    @Main
    public final Executor provideMainExecutor(Context context) {
        n.g(context, "context");
        Executor mainExecutor = context.getMainExecutor();
        n.f(mainExecutor, "getMainExecutor(...)");
        return mainExecutor;
    }

    @Main
    public final Handler provideMainHandler() {
        return new Handler(Looper.getMainLooper());
    }

    @Main
    public final Looper provideMainLooper() {
        Looper mainLooper = Looper.getMainLooper();
        n.f(mainLooper, "getMainLooper(...)");
        return mainLooper;
    }

    @UiBackground
    public final Executor provideUiBackgroundExecutor() {
        ExecutorService executorServiceNewSingleThreadExecutor = Executors.newSingleThreadExecutor();
        n.f(executorServiceNewSingleThreadExecutor, "newSingleThreadExecutor(...)");
        return executorServiceNewSingleThreadExecutor;
    }

    @Worker
    public final DelayableExecutor provideWorkerDelayableExecutor() {
        return getWorkerExecutor();
    }

    @Worker
    public final Executor provideWorkerExecutor() {
        return getWorkerExecutor();
    }
}
