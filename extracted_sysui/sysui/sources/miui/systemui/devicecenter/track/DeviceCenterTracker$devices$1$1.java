package miui.systemui.devicecenter.track;

import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.n;
import kotlin.jvm.internal.o;

/* JADX INFO: loaded from: classes3.dex */
public final class DeviceCenterTracker$devices$1$1 extends o implements Function2 {
    public static final DeviceCenterTracker$devices$1$1 INSTANCE = new DeviceCenterTracker$devices$1$1();

    public DeviceCenterTracker$devices$1$1() {
        super(2);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Integer invoke(DeviceFoundTimeRecord deviceFoundTimeRecord, DeviceFoundTimeRecord deviceFoundTimeRecord2) {
        return Integer.valueOf(n.j(deviceFoundTimeRecord.calculateDuration(), deviceFoundTimeRecord2.calculateDuration()));
    }
}
