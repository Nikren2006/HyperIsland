package miui.systemui.flashlight;

import F0.e;
import android.content.Context;
import miui.systemui.flashlight.dagger.MiFlashlightComponent;

/* JADX INFO: loaded from: classes3.dex */
public final class MiFlashlightManager_Factory implements e {
    private final G0.a contextProvider;
    private final G0.a miFlashlightComponentFactoryProvider;

    public MiFlashlightManager_Factory(G0.a aVar, G0.a aVar2) {
        this.contextProvider = aVar;
        this.miFlashlightComponentFactoryProvider = aVar2;
    }

    public static MiFlashlightManager_Factory create(G0.a aVar, G0.a aVar2) {
        return new MiFlashlightManager_Factory(aVar, aVar2);
    }

    public static MiFlashlightManager newInstance(Context context, MiFlashlightComponent.Factory factory) {
        return new MiFlashlightManager(context, factory);
    }

    @Override // G0.a
    public MiFlashlightManager get() {
        return newInstance((Context) this.contextProvider.get(), (MiFlashlightComponent.Factory) this.miFlashlightComponentFactoryProvider.get());
    }
}
