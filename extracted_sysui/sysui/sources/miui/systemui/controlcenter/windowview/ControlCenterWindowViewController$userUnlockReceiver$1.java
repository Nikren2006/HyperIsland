package miui.systemui.controlcenter.windowview;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import java.util.Iterator;
import miui.systemui.controlcenter.windowview.ControlCenterWindowViewController;

/* JADX INFO: loaded from: classes.dex */
public final class ControlCenterWindowViewController$userUnlockReceiver$1 extends BroadcastReceiver {
    final /* synthetic */ ControlCenterWindowViewController this$0;

    public ControlCenterWindowViewController$userUnlockReceiver$1(ControlCenterWindowViewController controlCenterWindowViewController) {
        this.this$0 = controlCenterWindowViewController;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onReceive$lambda$1(ControlCenterWindowViewController this$0) {
        kotlin.jvm.internal.n.g(this$0, "this$0");
        this$0.userUnlocked = true;
        Iterator it = this$0.userUnlockedListeners.iterator();
        while (it.hasNext()) {
            ((ControlCenterWindowViewController.OnUserUnlockedListener) it.next()).onUserUnlocked();
        }
    }

    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        Handler handler = this.this$0.mainHandler;
        final ControlCenterWindowViewController controlCenterWindowViewController = this.this$0;
        handler.post(new Runnable() { // from class: miui.systemui.controlcenter.windowview.o
            @Override // java.lang.Runnable
            public final void run() {
                ControlCenterWindowViewController$userUnlockReceiver$1.onReceive$lambda$1(controlCenterWindowViewController);
            }
        });
    }
}
