package miui.systemui.devicecenter.view;

import android.widget.ImageView;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.o;
import miui.systemui.devicecenter.R;

/* JADX INFO: loaded from: classes3.dex */
public final class MLBatteryView$mDeviceBatteryIcon$2 extends o implements Function0 {
    final /* synthetic */ MLBatteryView this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public MLBatteryView$mDeviceBatteryIcon$2(MLBatteryView mLBatteryView) {
        super(0);
        this.this$0 = mLBatteryView;
    }

    @Override // kotlin.jvm.functions.Function0
    public final ImageView invoke() {
        return (ImageView) this.this$0.findViewById(R.id.device_battery_icon);
    }
}
