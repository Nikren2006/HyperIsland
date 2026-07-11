package miui.systemui.devicecontrols.controller;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import miui.systemui.util.concurrency.DelayableExecutor;

/* JADX INFO: loaded from: classes3.dex */
public final class ControlsControllerImpl$restoreFinishedReceiver$1 extends BroadcastReceiver {
    final /* synthetic */ ControlsControllerImpl this$0;

    public ControlsControllerImpl$restoreFinishedReceiver$1(ControlsControllerImpl controlsControllerImpl) {
        this.this$0 = controlsControllerImpl;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onReceive$lambda$0(ControlsControllerImpl this$0) {
        kotlin.jvm.internal.n.g(this$0, "this$0");
        Log.d("ControlsControllerImpl", "Restore finished, storing auxiliary favorites");
        this$0.getAuxiliaryPersistenceWrapper$miui_devicecontrols_release().initialize();
        this$0.persistenceWrapper.storeFavorites(this$0.getAuxiliaryPersistenceWrapper$miui_devicecontrols_release().getFavorites());
        this$0.resetFavorites();
    }

    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        kotlin.jvm.internal.n.g(context, "context");
        kotlin.jvm.internal.n.g(intent, "intent");
        if (intent.getIntExtra("android.intent.extra.USER_ID", -10000) == this.this$0.getCurrentUserId()) {
            DelayableExecutor delayableExecutor = this.this$0.bgExecutor;
            final ControlsControllerImpl controlsControllerImpl = this.this$0;
            delayableExecutor.execute(new Runnable() { // from class: miui.systemui.devicecontrols.controller.t
                @Override // java.lang.Runnable
                public final void run() {
                    ControlsControllerImpl$restoreFinishedReceiver$1.onReceive$lambda$0(controlsControllerImpl);
                }
            });
        }
    }
}
