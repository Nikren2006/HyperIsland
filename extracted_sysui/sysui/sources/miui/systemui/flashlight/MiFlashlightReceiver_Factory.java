package miui.systemui.flashlight;

import F0.e;

/* JADX INFO: loaded from: classes3.dex */
public final class MiFlashlightReceiver_Factory implements e {
    private final G0.a flashlightManagerProvider;

    public MiFlashlightReceiver_Factory(G0.a aVar) {
        this.flashlightManagerProvider = aVar;
    }

    public static MiFlashlightReceiver_Factory create(G0.a aVar) {
        return new MiFlashlightReceiver_Factory(aVar);
    }

    public static MiFlashlightReceiver newInstance(MiFlashlightManager miFlashlightManager) {
        return new MiFlashlightReceiver(miFlashlightManager);
    }

    @Override // G0.a
    public MiFlashlightReceiver get() {
        return newInstance((MiFlashlightManager) this.flashlightManagerProvider.get());
    }
}
