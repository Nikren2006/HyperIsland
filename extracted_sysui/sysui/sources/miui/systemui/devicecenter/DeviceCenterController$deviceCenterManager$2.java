package miui.systemui.devicecenter;

import I0.m;
import com.miui.circulate.device.api.Constant;
import com.miui.circulate.device.api.DeviceControlManager;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.o;

/* JADX INFO: loaded from: classes3.dex */
public final class DeviceCenterController$deviceCenterManager$2 extends o implements Function0 {
    final /* synthetic */ DeviceCenterController this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public DeviceCenterController$deviceCenterManager$2(DeviceCenterController deviceCenterController) {
        super(0);
        this.this$0 = deviceCenterController;
    }

    @Override // kotlin.jvm.functions.Function0
    public final DeviceControlManager invoke() {
        return new DeviceControlManager(this.this$0.context, this.this$0.bgLooper, m.f(Constant.INSTANCE.getEXPORT_LIST_URI().buildUpon().appendQueryParameter(Constant.QueryParameter.MAX_COUNT, "2147483647").build()));
    }
}
