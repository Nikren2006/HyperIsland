package miui.systemui.flashlight;

import F0.e;

/* JADX INFO: loaded from: classes3.dex */
public final class MiFlashlightActivity_Factory implements e {
    private final G0.a flashlightManagerProvider;

    public MiFlashlightActivity_Factory(G0.a aVar) {
        this.flashlightManagerProvider = aVar;
    }

    public static MiFlashlightActivity_Factory create(G0.a aVar) {
        return new MiFlashlightActivity_Factory(aVar);
    }

    public static MiFlashlightActivity newInstance(MiFlashlightManager miFlashlightManager) {
        return new MiFlashlightActivity(miFlashlightManager);
    }

    @Override // G0.a
    public MiFlashlightActivity get() {
        return newInstance((MiFlashlightManager) this.flashlightManagerProvider.get());
    }
}
