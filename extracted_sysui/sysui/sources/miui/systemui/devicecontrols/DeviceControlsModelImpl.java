package miui.systemui.devicecontrols;

import E0.a;
import H0.s;
import android.content.ComponentName;
import android.view.View;
import java.util.function.Consumer;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.n;
import kotlin.jvm.internal.o;
import miui.systemui.dagger.qualifiers.Background;
import miui.systemui.devicecontrols.controller.ControlsController;
import miui.systemui.devicecontrols.controller.PrefDeviceControlsController;
import miui.systemui.devicecontrols.dagger.qualifiers.DeviceControlsScope;
import miui.systemui.devicecontrols.management.ControlsListingController;
import miui.systemui.devicecontrols.ui.MiuiControlsUiController;
import miui.systemui.util.concurrency.DelayableExecutor;

/* JADX INFO: loaded from: classes3.dex */
@DeviceControlsScope
public final class DeviceControlsModelImpl implements DeviceControlsModel {
    private final DelayableExecutor bgExecutor;
    private final ControlsController controlsController;
    private final ControlsListingController controlsListingController;
    private final a controlsUiController;
    private boolean entryAvailable;
    private final PrefDeviceControlsController prefDeviceControlsController;

    /* JADX INFO: renamed from: miui.systemui.devicecontrols.DeviceControlsModelImpl$showControlsView$1, reason: invalid class name */
    public static final class AnonymousClass1 extends o implements Function0 {
        public AnonymousClass1() {
            super(0);
        }

        @Override // kotlin.jvm.functions.Function0
        public /* bridge */ /* synthetic */ Object invoke() {
            m109invoke();
            return s.f314a;
        }

        /* JADX INFO: renamed from: invoke, reason: collision with other method in class */
        public final void m109invoke() {
            Object obj = DeviceControlsModelImpl.this.controlsUiController.get();
            n.f(obj, "get(...)");
            MiuiControlsUiController.show$default((MiuiControlsUiController) obj, 0, 1, null);
        }
    }

    public DeviceControlsModelImpl(PrefDeviceControlsController prefDeviceControlsController, ControlsListingController controlsListingController, a controlsUiController, ControlsController controlsController, @Background DelayableExecutor bgExecutor) {
        n.g(prefDeviceControlsController, "prefDeviceControlsController");
        n.g(controlsListingController, "controlsListingController");
        n.g(controlsUiController, "controlsUiController");
        n.g(controlsController, "controlsController");
        n.g(bgExecutor, "bgExecutor");
        this.prefDeviceControlsController = prefDeviceControlsController;
        this.controlsListingController = controlsListingController;
        this.controlsUiController = controlsUiController;
        this.controlsController = controlsController;
        this.bgExecutor = bgExecutor;
    }

    @Override // miui.systemui.devicecontrols.DeviceControlsModel
    public void addDCEntryInfoCallback(Consumer<DCEntryInfo> onInfoUpdate) {
        n.g(onInfoUpdate, "onInfoUpdate");
        ((MiuiControlsUiController) this.controlsUiController.get()).addDCEntryInfoCallback(onInfoUpdate);
    }

    @Override // miui.systemui.devicecontrols.DeviceControlsModel
    public void create() {
        this.controlsController.create();
    }

    @Override // miui.systemui.devicecontrols.DeviceControlsModel
    public void destroy() {
        this.prefDeviceControlsController.destroy();
        ((MiuiControlsUiController) this.controlsUiController.get()).destroy();
        this.controlsListingController.release();
    }

    @Override // miui.systemui.devicecontrols.DeviceControlsModel
    public View getOrCreateControlsView(Function0 back, Function1 edit, Function0 hideCustomize) {
        n.g(back, "back");
        n.g(edit, "edit");
        n.g(hideCustomize, "hideCustomize");
        return ((MiuiControlsUiController) this.controlsUiController.get()).createDCView(back, edit, hideCustomize);
    }

    @Override // miui.systemui.controlcenter.ConfigUtils.OnConfigChangeListener
    public void onConfigurationChanged(int i2) {
        ((MiuiControlsUiController) this.controlsUiController.get()).onConfigurationChanged(i2);
    }

    @Override // miui.systemui.devicecontrols.DeviceControlsModel
    public void removeDCEntryInfoCallback(Consumer<DCEntryInfo> onServiceUpdate) {
        n.g(onServiceUpdate, "onServiceUpdate");
        ((MiuiControlsUiController) this.controlsUiController.get()).removeDCEntryInfoCallback(onServiceUpdate);
    }

    @Override // miui.systemui.devicecontrols.DeviceControlsModel
    public void setListening(boolean z2) {
        this.prefDeviceControlsController.setListening(z2);
    }

    @Override // miui.systemui.devicecontrols.DeviceControlsModel
    public void showControlsView() {
        ((MiuiControlsUiController) this.controlsUiController.get()).loadStructure(new AnonymousClass1());
    }

    @Override // miui.systemui.devicecontrols.DeviceControlsModel
    public void subscribeFavorite(ComponentName componentName) {
        ((MiuiControlsUiController) this.controlsUiController.get()).subscribe();
    }

    @Override // miui.systemui.devicecontrols.DeviceControlsModel
    public void unsubscribeFavorite() {
        ((MiuiControlsUiController) this.controlsUiController.get()).hide();
    }
}
