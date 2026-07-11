package miui.systemui.devicecontrols.controller;

import android.content.Context;
import com.android.systemui.settings.UserContextProvider;
import miui.systemui.devicecontrols.management.ControlsListingController;
import miui.systemui.util.concurrency.DelayableExecutor;
import miui.systemui.util.settings.SecureSettings;

/* JADX INFO: loaded from: classes3.dex */
public final class PrefDeviceControlsController_Factory implements F0.e {
    private final G0.a bgExecutorProvider;
    private final G0.a contextProvider;
    private final G0.a controlsControllerProvider;
    private final G0.a controlsListingControllerProvider;
    private final G0.a secureSettingsProvider;
    private final G0.a userContextProvider;

    public PrefDeviceControlsController_Factory(G0.a aVar, G0.a aVar2, G0.a aVar3, G0.a aVar4, G0.a aVar5, G0.a aVar6) {
        this.contextProvider = aVar;
        this.controlsControllerProvider = aVar2;
        this.controlsListingControllerProvider = aVar3;
        this.userContextProvider = aVar4;
        this.secureSettingsProvider = aVar5;
        this.bgExecutorProvider = aVar6;
    }

    public static PrefDeviceControlsController_Factory create(G0.a aVar, G0.a aVar2, G0.a aVar3, G0.a aVar4, G0.a aVar5, G0.a aVar6) {
        return new PrefDeviceControlsController_Factory(aVar, aVar2, aVar3, aVar4, aVar5, aVar6);
    }

    public static PrefDeviceControlsController newInstance(Context context, ControlsController controlsController, ControlsListingController controlsListingController, UserContextProvider userContextProvider, SecureSettings secureSettings, DelayableExecutor delayableExecutor) {
        return new PrefDeviceControlsController(context, controlsController, controlsListingController, userContextProvider, secureSettings, delayableExecutor);
    }

    @Override // G0.a
    public PrefDeviceControlsController get() {
        return newInstance((Context) this.contextProvider.get(), (ControlsController) this.controlsControllerProvider.get(), (ControlsListingController) this.controlsListingControllerProvider.get(), (UserContextProvider) this.userContextProvider.get(), (SecureSettings) this.secureSettingsProvider.get(), (DelayableExecutor) this.bgExecutorProvider.get());
    }
}
