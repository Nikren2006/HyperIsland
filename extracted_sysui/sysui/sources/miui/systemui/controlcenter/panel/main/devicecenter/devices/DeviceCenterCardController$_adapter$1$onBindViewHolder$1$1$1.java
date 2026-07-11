package miui.systemui.controlcenter.panel.main.devicecenter.devices;

import H0.s;
import android.view.View;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.o;
import miui.systemui.controlcenter.R;
import miui.systemui.controlcenter.panel.main.devicecenter.DeviceCenterTrackHelper;
import miui.systemui.devicecenter.DeviceCenterController;
import miui.systemui.devicecenter.devices.DeviceInfoWrapper;
import miui.systemui.widget.FrameLayout;

/* JADX INFO: loaded from: classes.dex */
public final class DeviceCenterCardController$_adapter$1$onBindViewHolder$1$1$1 extends o implements Function1 {
    final /* synthetic */ DeviceViewHolder $holder;
    final /* synthetic */ Object $it;
    final /* synthetic */ int $position;
    final /* synthetic */ DeviceCenterCardController this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public DeviceCenterCardController$_adapter$1$onBindViewHolder$1$1$1(DeviceCenterCardController deviceCenterCardController, Object obj, DeviceViewHolder deviceViewHolder, int i2) {
        super(1);
        this.this$0 = deviceCenterCardController;
        this.$it = obj;
        this.$holder = deviceViewHolder;
        this.$position = i2;
    }

    @Override // kotlin.jvm.functions.Function1
    public /* bridge */ /* synthetic */ Object invoke(Object obj) {
        invoke((View) obj);
        return s.f314a;
    }

    public final void invoke(View view) {
        DeviceCenterController deviceCenterController = this.this$0.deviceCenterController;
        DeviceInfoWrapper deviceInfoWrapper = (DeviceInfoWrapper) this.$it;
        FrameLayout container = ((DeviceItemViewHolder) this.$holder).getBinding().container;
        kotlin.jvm.internal.n.f(container, "container");
        deviceCenterController.deviceClick(deviceInfoWrapper, container, R.drawable.ic_device_center_item_background_active, R.drawable.ic_device_center_item_background_default, this.this$0.activityStarter);
        DeviceCenterTrackHelper.INSTANCE.trackDeviceClick((DeviceInfoWrapper) this.$it, this.$position);
    }
}
