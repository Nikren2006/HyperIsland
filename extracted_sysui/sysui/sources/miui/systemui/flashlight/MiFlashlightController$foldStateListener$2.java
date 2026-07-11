package miui.systemui.flashlight;

import android.content.Context;
import java.util.function.Consumer;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.n;
import kotlin.jvm.internal.o;
import miui.systemui.util.CommonUtils;
import miui.systemui.util.DeviceStateManagerCompat;

/* JADX INFO: loaded from: classes3.dex */
public final class MiFlashlightController$foldStateListener$2 extends o implements Function0 {
    final /* synthetic */ MiFlashlightController this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public MiFlashlightController$foldStateListener$2(MiFlashlightController miFlashlightController) {
        super(0);
        this.this$0 = miFlashlightController;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void invoke$lambda$0(MiFlashlightController this$0, Boolean folded) {
        n.g(this$0, "this$0");
        n.g(folded, "folded");
        if (folded.booleanValue() && CommonUtils.isFlipDevice() && n.c(this$0.getSwitchStatus(), Boolean.TRUE)) {
            this$0.miFlashlightManager.removeFocusNotification();
        }
    }

    @Override // kotlin.jvm.functions.Function0
    public final Object invoke() {
        DeviceStateManagerCompat deviceStateManagerCompat = DeviceStateManagerCompat.INSTANCE;
        Context context = this.this$0.context;
        final MiFlashlightController miFlashlightController = this.this$0;
        return deviceStateManagerCompat.getFoldStateListenerInstance(context, new Consumer() { // from class: miui.systemui.flashlight.b
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                MiFlashlightController$foldStateListener$2.invoke$lambda$0(miFlashlightController, (Boolean) obj);
            }
        });
    }
}
