package miui.systemui.devicecontrols.dagger;

import android.content.pm.PackageManager;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.n;
import miui.systemui.devicecontrols.DeviceControlsModel;
import miui.systemui.devicecontrols.DeviceControlsModelImpl;
import miui.systemui.devicecontrols.controller.ControlsBindingController;
import miui.systemui.devicecontrols.controller.ControlsBindingControllerImpl;
import miui.systemui.devicecontrols.controller.ControlsController;
import miui.systemui.devicecontrols.controller.ControlsControllerImpl;
import miui.systemui.devicecontrols.controller.ControlsFavoritePersistenceWrapper;
import miui.systemui.devicecontrols.dagger.qualifiers.DeviceControlsScope;
import miui.systemui.devicecontrols.management.ControlsListingController;
import miui.systemui.devicecontrols.management.ControlsListingControllerImpl;
import miui.systemui.devicecontrols.ui.ControlActionCoordinator;
import miui.systemui.devicecontrols.ui.ControlActionCoordinatorImpl;
import miui.systemui.devicecontrols.ui.MiuiControlsUiController;
import miui.systemui.devicecontrols.ui.MiuiControlsUiControllerImpl;

/* JADX INFO: loaded from: classes3.dex */
public abstract class ControlsModule {
    public static final Companion Companion = new Companion(null);

    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @ControlsFeatureEnabled
        @DeviceControlsScope
        public final boolean providesControlsFeatureEnabled(PackageManager pm) {
            n.g(pm, "pm");
            return pm.hasSystemFeature("android.software.controls");
        }

        private Companion() {
        }
    }

    @ControlsFeatureEnabled
    @DeviceControlsScope
    public static final boolean providesControlsFeatureEnabled(PackageManager packageManager) {
        return Companion.providesControlsFeatureEnabled(packageManager);
    }

    public abstract DeviceControlsModel bindDeviceControlsModel(DeviceControlsModelImpl deviceControlsModelImpl);

    public abstract ControlsFavoritePersistenceWrapper optionalPersistenceWrapper();

    public abstract ControlActionCoordinator provideControlActionCoordinator(ControlActionCoordinatorImpl controlActionCoordinatorImpl);

    public abstract ControlsBindingController provideControlsBindingController(ControlsBindingControllerImpl controlsBindingControllerImpl);

    public abstract ControlsController provideControlsController(ControlsControllerImpl controlsControllerImpl);

    public abstract ControlsListingController provideControlsListingController(ControlsListingControllerImpl controlsListingControllerImpl);

    public abstract MiuiControlsUiController provideUiController(MiuiControlsUiControllerImpl miuiControlsUiControllerImpl);
}
