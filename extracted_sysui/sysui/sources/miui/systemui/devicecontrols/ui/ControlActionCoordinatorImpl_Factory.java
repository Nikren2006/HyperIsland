package miui.systemui.devicecontrols.ui;

import android.content.Context;
import com.android.systemui.plugins.ActivityStarter;
import com.android.systemui.plugins.statusbar.StatusBarStateController;
import miui.systemui.broadcast.BroadcastDispatcher;
import miui.systemui.util.HapticFeedback;
import miui.systemui.util.concurrency.DelayableExecutor;

/* JADX INFO: loaded from: classes3.dex */
public final class ControlActionCoordinatorImpl_Factory implements F0.e {
    private final G0.a activityStarterProvider;
    private final G0.a bgExecutorProvider;
    private final G0.a broadcastDispatcherProvider;
    private final G0.a contextProvider;
    private final G0.a hapticFeedbackProvider;
    private final G0.a statusBarStateControllerProvider;
    private final G0.a uiExecutorProvider;

    public ControlActionCoordinatorImpl_Factory(G0.a aVar, G0.a aVar2, G0.a aVar3, G0.a aVar4, G0.a aVar5, G0.a aVar6, G0.a aVar7) {
        this.contextProvider = aVar;
        this.bgExecutorProvider = aVar2;
        this.uiExecutorProvider = aVar3;
        this.activityStarterProvider = aVar4;
        this.statusBarStateControllerProvider = aVar5;
        this.broadcastDispatcherProvider = aVar6;
        this.hapticFeedbackProvider = aVar7;
    }

    public static ControlActionCoordinatorImpl_Factory create(G0.a aVar, G0.a aVar2, G0.a aVar3, G0.a aVar4, G0.a aVar5, G0.a aVar6, G0.a aVar7) {
        return new ControlActionCoordinatorImpl_Factory(aVar, aVar2, aVar3, aVar4, aVar5, aVar6, aVar7);
    }

    public static ControlActionCoordinatorImpl newInstance(Context context, DelayableExecutor delayableExecutor, DelayableExecutor delayableExecutor2, ActivityStarter activityStarter, StatusBarStateController statusBarStateController, BroadcastDispatcher broadcastDispatcher, HapticFeedback hapticFeedback) {
        return new ControlActionCoordinatorImpl(context, delayableExecutor, delayableExecutor2, activityStarter, statusBarStateController, broadcastDispatcher, hapticFeedback);
    }

    @Override // G0.a
    public ControlActionCoordinatorImpl get() {
        return newInstance((Context) this.contextProvider.get(), (DelayableExecutor) this.bgExecutorProvider.get(), (DelayableExecutor) this.uiExecutorProvider.get(), (ActivityStarter) this.activityStarterProvider.get(), (StatusBarStateController) this.statusBarStateControllerProvider.get(), (BroadcastDispatcher) this.broadcastDispatcherProvider.get(), (HapticFeedback) this.hapticFeedbackProvider.get());
    }
}
