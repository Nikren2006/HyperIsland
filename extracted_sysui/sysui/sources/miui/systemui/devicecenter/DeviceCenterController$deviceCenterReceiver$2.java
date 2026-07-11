package miui.systemui.devicecenter;

import android.util.Log;
import com.miui.circulate.device.api.DeviceControlReceiver;
import com.miui.circulate.device.api.DeviceInfo;
import java.util.List;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.n;
import kotlin.jvm.internal.o;

/* JADX INFO: loaded from: classes3.dex */
public final class DeviceCenterController$deviceCenterReceiver$2 extends o implements Function0 {
    final /* synthetic */ DeviceCenterController this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public DeviceCenterController$deviceCenterReceiver$2(DeviceCenterController deviceCenterController) {
        super(0);
        this.this$0 = deviceCenterController;
    }

    /* JADX WARN: Type inference failed for: r0v0, types: [miui.systemui.devicecenter.DeviceCenterController$deviceCenterReceiver$2$1] */
    @Override // kotlin.jvm.functions.Function0
    public final AnonymousClass1 invoke() {
        final DeviceCenterController deviceCenterController = this.this$0;
        return new DeviceControlReceiver() { // from class: miui.systemui.devicecenter.DeviceCenterController$deviceCenterReceiver$2.1
            @Override // com.miui.circulate.device.api.DeviceControlReceiver
            public void onDeviceChange(DeviceInfo data) {
                n.g(data, "data");
                Log.i(DeviceCenterController.TAG, "onDeviceChange title = " + data.getTitle() + "  type = " + data.getDeviceType() + " state = " + data.getState());
                DeviceCenterController deviceCenterController2 = deviceCenterController;
                deviceCenterController2.updateSingleInfo(deviceCenterController2.deviceList, data);
                deviceCenterController.handleDeviceListUpdate(false);
            }

            @Override // com.miui.circulate.device.api.DeviceControlReceiver
            public void onDeviceListChange(List<DeviceInfo> data, boolean z2) {
                n.g(data, "data");
                deviceCenterController.updateDeviceList(data, z2);
            }
        };
    }
}
