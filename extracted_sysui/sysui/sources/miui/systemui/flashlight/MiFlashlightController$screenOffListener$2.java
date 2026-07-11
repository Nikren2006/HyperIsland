package miui.systemui.flashlight;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.n;
import kotlin.jvm.internal.o;

/* JADX INFO: loaded from: classes3.dex */
public final class MiFlashlightController$screenOffListener$2 extends o implements Function0 {
    final /* synthetic */ MiFlashlightController this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public MiFlashlightController$screenOffListener$2(MiFlashlightController miFlashlightController) {
        super(0);
        this.this$0 = miFlashlightController;
    }

    /* JADX WARN: Type inference failed for: r0v0, types: [miui.systemui.flashlight.MiFlashlightController$screenOffListener$2$1] */
    @Override // kotlin.jvm.functions.Function0
    public final AnonymousClass1 invoke() {
        final MiFlashlightController miFlashlightController = this.this$0;
        return new BroadcastReceiver() { // from class: miui.systemui.flashlight.MiFlashlightController$screenOffListener$2.1
            @Override // android.content.BroadcastReceiver
            public void onReceive(Context context, Intent intent) {
                if (n.c(intent != null ? intent.getAction() : null, "android.intent.action.SCREEN_OFF")) {
                    miFlashlightController.miFlashlightManager.dismiss(miFlashlightController.miFlashlightLayout.hashCode());
                }
            }
        };
    }
}
