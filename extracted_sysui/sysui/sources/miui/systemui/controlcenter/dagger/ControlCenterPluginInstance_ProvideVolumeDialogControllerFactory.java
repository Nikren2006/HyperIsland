package miui.systemui.controlcenter.dagger;

import F0.e;
import F0.i;
import com.android.systemui.plugins.VolumeDialogController;

/* JADX INFO: loaded from: classes.dex */
public final class ControlCenterPluginInstance_ProvideVolumeDialogControllerFactory implements e {

    public static final class InstanceHolder {
        private static final ControlCenterPluginInstance_ProvideVolumeDialogControllerFactory INSTANCE = new ControlCenterPluginInstance_ProvideVolumeDialogControllerFactory();

        private InstanceHolder() {
        }
    }

    public static ControlCenterPluginInstance_ProvideVolumeDialogControllerFactory create() {
        return InstanceHolder.INSTANCE;
    }

    public static VolumeDialogController provideVolumeDialogController() {
        return (VolumeDialogController) i.d(ControlCenterPluginInstance.provideVolumeDialogController());
    }

    @Override // G0.a
    public VolumeDialogController get() {
        return provideVolumeDialogController();
    }
}
