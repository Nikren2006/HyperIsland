package miui.systemui.dynamicisland.window;

import android.content.Context;
import android.os.Handler;
import com.android.systemui.settings.UserTracker;
import g1.E;
import java.util.concurrent.Executor;

/* JADX INFO: loaded from: classes3.dex */
public final class AppLockController_Factory implements F0.e {
    private final G0.a bgExecutorProvider;
    private final G0.a bgHandlerProvider;
    private final G0.a contextProvider;
    private final G0.a scopeProvider;
    private final G0.a userTrackerProvider;

    public AppLockController_Factory(G0.a aVar, G0.a aVar2, G0.a aVar3, G0.a aVar4, G0.a aVar5) {
        this.scopeProvider = aVar;
        this.contextProvider = aVar2;
        this.userTrackerProvider = aVar3;
        this.bgExecutorProvider = aVar4;
        this.bgHandlerProvider = aVar5;
    }

    public static AppLockController_Factory create(G0.a aVar, G0.a aVar2, G0.a aVar3, G0.a aVar4, G0.a aVar5) {
        return new AppLockController_Factory(aVar, aVar2, aVar3, aVar4, aVar5);
    }

    public static AppLockController newInstance(E e2, Context context, UserTracker userTracker, Executor executor, Handler handler) {
        return new AppLockController(e2, context, userTracker, executor, handler);
    }

    @Override // G0.a
    public AppLockController get() {
        return newInstance((E) this.scopeProvider.get(), (Context) this.contextProvider.get(), (UserTracker) this.userTrackerProvider.get(), (Executor) this.bgExecutorProvider.get(), (Handler) this.bgHandlerProvider.get());
    }
}
