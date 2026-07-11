package miui.systemui.util;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import kotlin.jvm.functions.Function0;
import miui.systemui.util.MiLinkController;

/* JADX INFO: loaded from: classes4.dex */
public final class MiLinkController$broadcastReceiver$2 extends kotlin.jvm.internal.o implements Function0 {
    final /* synthetic */ MiLinkController this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public MiLinkController$broadcastReceiver$2(MiLinkController miLinkController) {
        super(0);
        this.this$0 = miLinkController;
    }

    /* JADX WARN: Type inference failed for: r0v0, types: [miui.systemui.util.MiLinkController$broadcastReceiver$2$1] */
    @Override // kotlin.jvm.functions.Function0
    public final AnonymousClass1 invoke() {
        final MiLinkController miLinkController = this.this$0;
        return new BroadcastReceiver() { // from class: miui.systemui.util.MiLinkController$broadcastReceiver$2.1
            @Override // android.content.BroadcastReceiver
            public void onReceive(Context context, Intent intent) {
                kotlin.jvm.internal.n.g(context, "context");
                kotlin.jvm.internal.n.g(intent, "intent");
                String action = intent.getAction();
                if (kotlin.jvm.internal.n.c("com.mi.systemui.ACTION_CAMERA_STATE_UPDATE", action)) {
                    int intExtra = intent.getIntExtra("CAMERA_USAGE_STATE", 0);
                    MiLinkController.Companion companion = MiLinkController.Companion;
                    companion.setCameraUsageState(intExtra != 1 ? 0 : 1);
                    Log.i("MiLinkController", "onReceive " + action + " cameraUsageState " + companion.getCameraUsageState());
                    return;
                }
                if (kotlin.jvm.internal.n.c(action, "android.intent.action.USER_UNLOCKED")) {
                    MiLinkController.updateMiLinkPackageAvailable$default(miLinkController, false, 1, null);
                    return;
                }
                Uri data = intent.getData();
                if (kotlin.jvm.internal.n.c(data != null ? data.getEncodedSchemeSpecificPart() : null, SmartDeviceUtils.MI_LINK_PACKAGE_NAME)) {
                    if (kotlin.jvm.internal.n.c(action, "android.intent.action.PACKAGE_REMOVED")) {
                        miLinkController.updateMiLinkPackageAvailable(intent.getBooleanExtra("android.intent.extra.REPLACING", false));
                    } else if (kotlin.jvm.internal.n.c(action, "android.intent.action.PACKAGE_ADDED")) {
                        MiLinkController.updateMiLinkPackageAvailable$default(miLinkController, false, 1, null);
                    }
                }
            }
        };
    }
}
