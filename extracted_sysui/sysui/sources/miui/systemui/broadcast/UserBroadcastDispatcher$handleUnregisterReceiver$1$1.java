package miui.systemui.broadcast;

import android.content.BroadcastReceiver;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.n;
import kotlin.jvm.internal.o;

/* JADX INFO: loaded from: classes2.dex */
public final class UserBroadcastDispatcher$handleUnregisterReceiver$1$1 extends o implements Function1 {
    final /* synthetic */ BroadcastReceiver $receiver;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public UserBroadcastDispatcher$handleUnregisterReceiver$1$1(BroadcastReceiver broadcastReceiver) {
        super(1);
        this.$receiver = broadcastReceiver;
    }

    @Override // kotlin.jvm.functions.Function1
    public final Boolean invoke(ReceiverData it) {
        n.g(it, "it");
        return Boolean.valueOf(n.c(it.getReceiver(), this.$receiver));
    }
}
