package miui.systemui.flashlight.effect;

import F0.e;

/* JADX INFO: loaded from: classes3.dex */
public final class MiFlashlightUiOpenGl_Factory implements e {

    public static final class InstanceHolder {
        private static final MiFlashlightUiOpenGl_Factory INSTANCE = new MiFlashlightUiOpenGl_Factory();

        private InstanceHolder() {
        }
    }

    public static MiFlashlightUiOpenGl_Factory create() {
        return InstanceHolder.INSTANCE;
    }

    public static MiFlashlightUiOpenGl newInstance() {
        return new MiFlashlightUiOpenGl();
    }

    @Override // G0.a
    public MiFlashlightUiOpenGl get() {
        return newInstance();
    }
}
