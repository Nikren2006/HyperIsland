package miui.systemui.flashlight.dagger;

import F0.e;
import F0.i;
import G0.a;
import android.view.View;
import miui.systemui.flashlight.view.MiFlashlightLayout;

/* JADX INFO: loaded from: classes3.dex */
public final class MiFlashlightComponentModule_ProvideViewFactory implements e {
    private final MiFlashlightComponentModule module;
    private final a viewProvider;

    public MiFlashlightComponentModule_ProvideViewFactory(MiFlashlightComponentModule miFlashlightComponentModule, a aVar) {
        this.module = miFlashlightComponentModule;
        this.viewProvider = aVar;
    }

    public static MiFlashlightComponentModule_ProvideViewFactory create(MiFlashlightComponentModule miFlashlightComponentModule, a aVar) {
        return new MiFlashlightComponentModule_ProvideViewFactory(miFlashlightComponentModule, aVar);
    }

    public static View provideView(MiFlashlightComponentModule miFlashlightComponentModule, MiFlashlightLayout miFlashlightLayout) {
        return (View) i.d(miFlashlightComponentModule.provideView(miFlashlightLayout));
    }

    @Override // G0.a
    public View get() {
        return provideView(this.module, (MiFlashlightLayout) this.viewProvider.get());
    }
}
