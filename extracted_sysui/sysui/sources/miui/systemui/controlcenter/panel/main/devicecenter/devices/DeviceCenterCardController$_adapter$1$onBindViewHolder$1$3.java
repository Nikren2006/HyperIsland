package miui.systemui.controlcenter.panel.main.devicecenter.devices;

import H0.s;
import android.content.ComponentName;
import android.content.Intent;
import android.view.View;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.o;
import miui.systemui.util.ControlsUtils;

/* JADX INFO: loaded from: classes.dex */
public final class DeviceCenterCardController$_adapter$1$onBindViewHolder$1$3 extends o implements Function1 {
    final /* synthetic */ DeviceCenterCardController this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public DeviceCenterCardController$_adapter$1$onBindViewHolder$1$3(DeviceCenterCardController deviceCenterCardController) {
        super(1);
        this.this$0 = deviceCenterCardController;
    }

    @Override // kotlin.jvm.functions.Function1
    public /* bridge */ /* synthetic */ Object invoke(Object obj) {
        invoke((View) obj);
        return s.f314a;
    }

    public final void invoke(View view) {
        Intent intent = new Intent();
        DeviceCenterCardController deviceCenterCardController = this.this$0;
        intent.setComponent(new ComponentName("com.android.systemui", "com.android.systemui.controls.ui.ControlsActivity"));
        intent.addFlags(335544320);
        intent.addCategory("android.intent.category.DEFAULT");
        intent.putExtra("extra_animate", true);
        intent.putExtra(ControlsUtils.GO_TO_DEVICE_APP, deviceCenterCardController.getPackageName());
        this.this$0.activityStarter.startActivity(intent, false);
    }
}
