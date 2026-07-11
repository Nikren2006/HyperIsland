package miui.systemui.controlcenter.panel.secondary.media;

import F0.e;
import miui.systemui.controlcenter.databinding.ControlCenterSecondaryBinding;
import miui.systemui.controlcenter.databinding.MediaPanelBinding;

/* JADX INFO: loaded from: classes.dex */
public final class MediaPanelDelegate_Factory implements e {
    private final G0.a bindingProvider;
    private final G0.a secondaryBindingProvider;

    public MediaPanelDelegate_Factory(G0.a aVar, G0.a aVar2) {
        this.secondaryBindingProvider = aVar;
        this.bindingProvider = aVar2;
    }

    public static MediaPanelDelegate_Factory create(G0.a aVar, G0.a aVar2) {
        return new MediaPanelDelegate_Factory(aVar, aVar2);
    }

    public static MediaPanelDelegate newInstance(ControlCenterSecondaryBinding controlCenterSecondaryBinding, MediaPanelBinding mediaPanelBinding) {
        return new MediaPanelDelegate(controlCenterSecondaryBinding, mediaPanelBinding);
    }

    @Override // G0.a
    public MediaPanelDelegate get() {
        return newInstance((ControlCenterSecondaryBinding) this.secondaryBindingProvider.get(), (MediaPanelBinding) this.bindingProvider.get());
    }
}
