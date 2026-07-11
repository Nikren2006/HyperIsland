package miui.systemui.flashlight;

import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.o;
import miui.systemui.util.DeviceStateManagerCompat;

/* JADX INFO: loaded from: classes3.dex */
public final class MiFlashlightController$deviceStateManager$2 extends o implements Function0 {
    public static final MiFlashlightController$deviceStateManager$2 INSTANCE = new MiFlashlightController$deviceStateManager$2();

    public MiFlashlightController$deviceStateManager$2() {
        super(0);
    }

    @Override // kotlin.jvm.functions.Function0
    public final Object invoke() {
        return DeviceStateManagerCompat.INSTANCE.getDeviceStateManagerInstance();
    }
}
