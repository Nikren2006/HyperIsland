package miui.systemui.devicecontrols.controller;

import android.content.Context;
import com.android.systemui.settings.UserTracker;
import java.util.Optional;
import miui.systemui.broadcast.BroadcastDispatcher;
import miui.systemui.devicecontrols.management.ControlsListingController;
import miui.systemui.devicecontrols.ui.MiuiControlsUiController;
import miui.systemui.util.concurrency.DelayableExecutor;

/* JADX INFO: loaded from: classes3.dex */
public final class ControlsControllerImpl_Factory implements F0.e {
    private final G0.a bgExecutorProvider;
    private final G0.a bindingControllerProvider;
    private final G0.a broadcastDispatcherProvider;
    private final G0.a contextProvider;
    private final G0.a listingControllerProvider;
    private final G0.a mainExecutorProvider;
    private final G0.a optionalWrapperProvider;
    private final G0.a sysUIContextProvider;
    private final G0.a uiControllerProvider;
    private final G0.a userTrackerProvider;

    public ControlsControllerImpl_Factory(G0.a aVar, G0.a aVar2, G0.a aVar3, G0.a aVar4, G0.a aVar5, G0.a aVar6, G0.a aVar7, G0.a aVar8, G0.a aVar9, G0.a aVar10) {
        this.sysUIContextProvider = aVar;
        this.contextProvider = aVar2;
        this.bgExecutorProvider = aVar3;
        this.mainExecutorProvider = aVar4;
        this.uiControllerProvider = aVar5;
        this.bindingControllerProvider = aVar6;
        this.listingControllerProvider = aVar7;
        this.broadcastDispatcherProvider = aVar8;
        this.optionalWrapperProvider = aVar9;
        this.userTrackerProvider = aVar10;
    }

    public static ControlsControllerImpl_Factory create(G0.a aVar, G0.a aVar2, G0.a aVar3, G0.a aVar4, G0.a aVar5, G0.a aVar6, G0.a aVar7, G0.a aVar8, G0.a aVar9, G0.a aVar10) {
        return new ControlsControllerImpl_Factory(aVar, aVar2, aVar3, aVar4, aVar5, aVar6, aVar7, aVar8, aVar9, aVar10);
    }

    public static ControlsControllerImpl newInstance(Context context, Context context2, DelayableExecutor delayableExecutor, DelayableExecutor delayableExecutor2, MiuiControlsUiController miuiControlsUiController, ControlsBindingController controlsBindingController, ControlsListingController controlsListingController, BroadcastDispatcher broadcastDispatcher, Optional<ControlsFavoritePersistenceWrapper> optional, UserTracker userTracker) {
        return new ControlsControllerImpl(context, context2, delayableExecutor, delayableExecutor2, miuiControlsUiController, controlsBindingController, controlsListingController, broadcastDispatcher, optional, userTracker);
    }

    @Override // G0.a
    public ControlsControllerImpl get() {
        return newInstance((Context) this.sysUIContextProvider.get(), (Context) this.contextProvider.get(), (DelayableExecutor) this.bgExecutorProvider.get(), (DelayableExecutor) this.mainExecutorProvider.get(), (MiuiControlsUiController) this.uiControllerProvider.get(), (ControlsBindingController) this.bindingControllerProvider.get(), (ControlsListingController) this.listingControllerProvider.get(), (BroadcastDispatcher) this.broadcastDispatcherProvider.get(), (Optional) this.optionalWrapperProvider.get(), (UserTracker) this.userTrackerProvider.get());
    }
}
