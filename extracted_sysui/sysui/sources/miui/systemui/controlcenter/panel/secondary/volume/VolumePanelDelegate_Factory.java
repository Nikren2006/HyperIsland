package miui.systemui.controlcenter.panel.secondary.volume;

import F0.e;
import android.content.Context;
import com.android.systemui.plugins.VolumeDialogController;
import com.android.systemui.plugins.statusbar.StatusBarStateController;
import miui.systemui.controlcenter.databinding.ControlCenterSecondaryBinding;
import miui.systemui.controlcenter.databinding.VolumePanelBinding;

/* JADX INFO: loaded from: classes.dex */
public final class VolumePanelDelegate_Factory implements e {
    private final G0.a bindingProvider;
    private final G0.a secondaryBindingProvider;
    private final G0.a statusBarStateControllerProvider;
    private final G0.a sysUIContextProvider;
    private final G0.a volumeDialogControllerProvider;

    public VolumePanelDelegate_Factory(G0.a aVar, G0.a aVar2, G0.a aVar3, G0.a aVar4, G0.a aVar5) {
        this.sysUIContextProvider = aVar;
        this.secondaryBindingProvider = aVar2;
        this.bindingProvider = aVar3;
        this.volumeDialogControllerProvider = aVar4;
        this.statusBarStateControllerProvider = aVar5;
    }

    public static VolumePanelDelegate_Factory create(G0.a aVar, G0.a aVar2, G0.a aVar3, G0.a aVar4, G0.a aVar5) {
        return new VolumePanelDelegate_Factory(aVar, aVar2, aVar3, aVar4, aVar5);
    }

    public static VolumePanelDelegate newInstance(Context context, ControlCenterSecondaryBinding controlCenterSecondaryBinding, VolumePanelBinding volumePanelBinding, VolumeDialogController volumeDialogController, StatusBarStateController statusBarStateController) {
        return new VolumePanelDelegate(context, controlCenterSecondaryBinding, volumePanelBinding, volumeDialogController, statusBarStateController);
    }

    @Override // G0.a
    public VolumePanelDelegate get() {
        return newInstance((Context) this.sysUIContextProvider.get(), (ControlCenterSecondaryBinding) this.secondaryBindingProvider.get(), (VolumePanelBinding) this.bindingProvider.get(), (VolumeDialogController) this.volumeDialogControllerProvider.get(), (StatusBarStateController) this.statusBarStateControllerProvider.get());
    }
}
