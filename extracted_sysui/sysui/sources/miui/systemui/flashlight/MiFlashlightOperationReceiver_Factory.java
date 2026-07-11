package miui.systemui.flashlight;

import F0.e;

/* JADX INFO: loaded from: classes3.dex */
public final class MiFlashlightOperationReceiver_Factory implements e {

    public static final class InstanceHolder {
        private static final MiFlashlightOperationReceiver_Factory INSTANCE = new MiFlashlightOperationReceiver_Factory();

        private InstanceHolder() {
        }
    }

    public static MiFlashlightOperationReceiver_Factory create() {
        return InstanceHolder.INSTANCE;
    }

    public static MiFlashlightOperationReceiver newInstance() {
        return new MiFlashlightOperationReceiver();
    }

    @Override // G0.a
    public MiFlashlightOperationReceiver get() {
        return newInstance();
    }
}
