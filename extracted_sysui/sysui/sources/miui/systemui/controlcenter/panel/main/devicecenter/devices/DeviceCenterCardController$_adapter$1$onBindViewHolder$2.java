package miui.systemui.controlcenter.panel.main.devicecenter.devices;

import H0.s;
import android.util.Log;
import android.view.View;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.o;
import miui.systemui.controlcenter.R;
import miui.systemui.controlcenter.events.ControlCenterEventTracker;
import miui.systemui.controlcenter.panel.main.devicecenter.DeviceCenterTrackHelper;
import miui.systemui.devicecenter.DeviceCenterController;
import miui.systemui.devicecenter.devices.DeviceInfoWrapper;
import miui.systemui.util.CommonUtils;
import miui.systemui.widget.FrameLayout;
import systemui.plugin.eventtracking.EventTracker;

/* JADX INFO: loaded from: classes.dex */
public final class DeviceCenterCardController$_adapter$1$onBindViewHolder$2 extends o implements Function1 {
    final /* synthetic */ DeviceInfoWrapper $deviceInfo;
    final /* synthetic */ DeviceViewHolder $holder;
    final /* synthetic */ int $position;
    final /* synthetic */ DeviceCenterCardController this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public DeviceCenterCardController$_adapter$1$onBindViewHolder$2(DeviceInfoWrapper deviceInfoWrapper, int i2, DeviceCenterCardController deviceCenterCardController, DeviceViewHolder deviceViewHolder) {
        super(1);
        this.$deviceInfo = deviceInfoWrapper;
        this.$position = i2;
        this.this$0 = deviceCenterCardController;
        this.$holder = deviceViewHolder;
    }

    @Override // kotlin.jvm.functions.Function1
    public /* bridge */ /* synthetic */ Object invoke(Object obj) {
        invoke((View) obj);
        return s.f314a;
    }

    public final void invoke(View view) {
        Log.i("DeviceCenterCardController", "item onClick! and Stop Discover,deviceType is " + this.$deviceInfo.getType());
        DeviceCenterTrackHelper.INSTANCE.trackDeviceClick(this.$deviceInfo, this.$position);
        DeviceCenterController deviceCenterController = this.this$0.deviceCenterController;
        DeviceInfoWrapper deviceInfoWrapper = this.$deviceInfo;
        FrameLayout container = ((DeviceItemViewHolder) this.$holder).getBinding().container;
        kotlin.jvm.internal.n.f(container, "container");
        deviceCenterController.deviceClick(deviceInfoWrapper, container, R.drawable.ic_device_center_item_background_active, R.drawable.ic_device_center_item_background_default, this.this$0.activityStarter);
        if (CommonUtils.isTinyScreen(this.this$0.getContext())) {
            return;
        }
        ControlCenterEventTracker.Companion.trackMiSmartHubClickedEvent(EventTracker.Companion.getScreenType(this.this$0.getContext()), this.this$0.getContext().getResources().getConfiguration().orientation, false);
    }
}
