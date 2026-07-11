package miui.systemui.devicecontrols.controller;

import android.content.Context;
import com.android.systemui.settings.UserTracker;
import miui.systemui.util.concurrency.DelayableExecutor;

/* JADX INFO: loaded from: classes3.dex */
public final class ControlsBindingControllerImpl_Factory implements F0.e {
    private final G0.a backgroundExecutorProvider;
    private final G0.a contextProvider;
    private final G0.a controllerProvider;
    private final G0.a userTrackerProvider;

    public ControlsBindingControllerImpl_Factory(G0.a aVar, G0.a aVar2, G0.a aVar3, G0.a aVar4) {
        this.contextProvider = aVar;
        this.backgroundExecutorProvider = aVar2;
        this.controllerProvider = aVar3;
        this.userTrackerProvider = aVar4;
    }

    public static ControlsBindingControllerImpl_Factory create(G0.a aVar, G0.a aVar2, G0.a aVar3, G0.a aVar4) {
        return new ControlsBindingControllerImpl_Factory(aVar, aVar2, aVar3, aVar4);
    }

    public static ControlsBindingControllerImpl newInstance(Context context, DelayableExecutor delayableExecutor, E0.a aVar, UserTracker userTracker) {
        return new ControlsBindingControllerImpl(context, delayableExecutor, aVar, userTracker);
    }

    @Override // G0.a
    public ControlsBindingControllerImpl get() {
        return newInstance((Context) this.contextProvider.get(), (DelayableExecutor) this.backgroundExecutorProvider.get(), F0.d.a(this.controllerProvider), (UserTracker) this.userTrackerProvider.get());
    }
}
