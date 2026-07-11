package miui.systemui.devicecenter.view;

import H0.d;
import H0.e;
import I0.m;
import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import c1.f;
import java.util.List;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.n;
import miui.systemui.devicecenter.R;

/* JADX INFO: loaded from: classes3.dex */
public final class MLBatteryView extends LinearLayout {
    private final List<Integer> batteryImageList;
    private final d mDeviceBatteryIcon$delegate;

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public MLBatteryView(Context context) {
        this(context, null, 0, 6, null);
        n.g(context, "context");
    }

    private final ImageView getMDeviceBatteryIcon() {
        Object value = this.mDeviceBatteryIcon$delegate.getValue();
        n.f(value, "getValue(...)");
        return (ImageView) value;
    }

    public final void setBattery(Integer num) {
        if (num == null || num.intValue() < 0 || num.intValue() > 100) {
            getMDeviceBatteryIcon().setImageResource(this.batteryImageList.get(9).intValue());
            return;
        }
        int iIntValue = (num.intValue() / 10) - 1;
        getMDeviceBatteryIcon().setImageResource(this.batteryImageList.get(f.i(iIntValue, 0, r0.size() - 1)).intValue());
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public MLBatteryView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0, 4, null);
        n.g(context, "context");
    }

    public /* synthetic */ MLBatteryView(Context context, AttributeSet attributeSet, int i2, int i3, DefaultConstructorMarker defaultConstructorMarker) {
        this(context, (i3 & 2) != 0 ? null : attributeSet, (i3 & 4) != 0 ? 0 : i2);
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public MLBatteryView(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        n.g(context, "context");
        LayoutInflater.from(context).inflate(R.layout.card_device_layout_battery_layout, (ViewGroup) this, true);
        setGravity(16);
        this.batteryImageList = m.j(Integer.valueOf(R.drawable.battery10), Integer.valueOf(R.drawable.battery20), Integer.valueOf(R.drawable.battery30), Integer.valueOf(R.drawable.battery40), Integer.valueOf(R.drawable.battery50), Integer.valueOf(R.drawable.battery60), Integer.valueOf(R.drawable.battery70), Integer.valueOf(R.drawable.battery80), Integer.valueOf(R.drawable.battery90), Integer.valueOf(R.drawable.battery100));
        this.mDeviceBatteryIcon$delegate = e.b(new MLBatteryView$mDeviceBatteryIcon$2(this));
    }
}
