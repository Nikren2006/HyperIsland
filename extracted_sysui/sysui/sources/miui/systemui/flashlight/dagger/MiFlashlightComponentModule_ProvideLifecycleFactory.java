package miui.systemui.flashlight.dagger;

import F0.e;
import F0.i;
import G0.a;
import androidx.lifecycle.Lifecycle;
import miui.systemui.flashlight.view.MiFlashlightLayout;

/* JADX INFO: loaded from: classes3.dex */
public final class MiFlashlightComponentModule_ProvideLifecycleFactory implements e {
    private final MiFlashlightComponentModule module;
    private final a viewProvider;

    public MiFlashlightComponentModule_ProvideLifecycleFactory(MiFlashlightComponentModule miFlashlightComponentModule, a aVar) {
        this.module = miFlashlightComponentModule;
        this.viewProvider = aVar;
    }

    public static MiFlashlightComponentModule_ProvideLifecycleFactory create(MiFlashlightComponentModule miFlashlightComponentModule, a aVar) {
        return new MiFlashlightComponentModule_ProvideLifecycleFactory(miFlashlightComponentModule, aVar);
    }

    public static Lifecycle provideLifecycle(MiFlashlightComponentModule miFlashlightComponentModule, MiFlashlightLayout miFlashlightLayout) {
        return (Lifecycle) i.d(miFlashlightComponentModule.provideLifecycle(miFlashlightLayout));
    }

    @Override // G0.a
    public Lifecycle get() {
        return provideLifecycle(this.module, (MiFlashlightLayout) this.viewProvider.get());
    }
}
