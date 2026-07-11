package miui.systemui.util;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.util.Log;

/* JADX INFO: loaded from: classes4.dex */
public final class AlarmScheduler$alarmReceiver$1 extends BroadcastReceiver {
    final /* synthetic */ AlarmScheduler this$0;

    public AlarmScheduler$alarmReceiver$1(AlarmScheduler alarmScheduler) {
        this.this$0 = alarmScheduler;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onReceive$lambda$0(AlarmScheduler this$0, Intent intent) {
        kotlin.jvm.internal.n.g(this$0, "this$0");
        this$0.onAlarmTriggered(this$0.currentAlarmKey);
        this$0.alarmCallback.invoke(intent.getStringExtra("extra_key"));
    }

    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, final Intent intent) {
        kotlin.jvm.internal.n.g(context, "context");
        Log.d(this.this$0.tag, "alarmReceiver triggered");
        if (kotlin.jvm.internal.n.c(intent != null ? intent.getAction() : null, this.this$0.workAction)) {
            Handler handler = this.this$0.handler;
            final AlarmScheduler alarmScheduler = this.this$0;
            handler.post(new Runnable() { // from class: miui.systemui.util.g
                @Override // java.lang.Runnable
                public final void run() {
                    AlarmScheduler$alarmReceiver$1.onReceive$lambda$0(alarmScheduler, intent);
                }
            });
        }
    }
}
