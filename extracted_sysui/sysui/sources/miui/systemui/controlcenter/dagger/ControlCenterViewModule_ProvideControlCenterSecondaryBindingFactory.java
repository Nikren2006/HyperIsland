package miui.systemui.controlcenter.dagger;

import F0.e;
import F0.i;
import G0.a;
import android.view.LayoutInflater;
import miui.systemui.controlcenter.databinding.ControlCenterSecondaryBinding;

/* JADX INFO: loaded from: classes.dex */
public final class ControlCenterViewModule_ProvideControlCenterSecondaryBindingFactory implements e {
    private final a layoutInflaterProvider;
    private final ControlCenterViewModule module;

    public ControlCenterViewModule_ProvideControlCenterSecondaryBindingFactory(ControlCenterViewModule controlCenterViewModule, a aVar) {
        this.module = controlCenterViewModule;
        this.layoutInflaterProvider = aVar;
    }

    public static ControlCenterViewModule_ProvideControlCenterSecondaryBindingFactory create(ControlCenterViewModule controlCenterViewModule, a aVar) {
        return new ControlCenterViewModule_ProvideControlCenterSecondaryBindingFactory(controlCenterViewModule, aVar);
    }

    public static ControlCenterSecondaryBinding provideControlCenterSecondaryBinding(ControlCenterViewModule controlCenterViewModule, LayoutInflater layoutInflater) {
        return (ControlCenterSecondaryBinding) i.d(controlCenterViewModule.provideControlCenterSecondaryBinding(layoutInflater));
    }

    @Override // G0.a
    public ControlCenterSecondaryBinding get() {
        return provideControlCenterSecondaryBinding(this.module, (LayoutInflater) this.layoutInflaterProvider.get());
    }
}
