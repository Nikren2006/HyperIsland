package miui.systemui.broadcast;

import android.content.BroadcastReceiver;
import android.content.IntentFilter;
import android.os.UserHandle;
import java.util.concurrent.Executor;
import kotlin.jvm.internal.n;

/* JADX INFO: loaded from: classes2.dex */
public final class ReceiverData {
    private final Executor executor;
    private final IntentFilter filter;
    private final BroadcastReceiver receiver;
    private final UserHandle user;

    public ReceiverData(BroadcastReceiver receiver, IntentFilter filter, Executor executor, UserHandle user) {
        n.g(receiver, "receiver");
        n.g(filter, "filter");
        n.g(executor, "executor");
        n.g(user, "user");
        this.receiver = receiver;
        this.filter = filter;
        this.executor = executor;
        this.user = user;
    }

    public static /* synthetic */ ReceiverData copy$default(ReceiverData receiverData, BroadcastReceiver broadcastReceiver, IntentFilter intentFilter, Executor executor, UserHandle userHandle, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            broadcastReceiver = receiverData.receiver;
        }
        if ((i2 & 2) != 0) {
            intentFilter = receiverData.filter;
        }
        if ((i2 & 4) != 0) {
            executor = receiverData.executor;
        }
        if ((i2 & 8) != 0) {
            userHandle = receiverData.user;
        }
        return receiverData.copy(broadcastReceiver, intentFilter, executor, userHandle);
    }

    public final BroadcastReceiver component1() {
        return this.receiver;
    }

    public final IntentFilter component2() {
        return this.filter;
    }

    public final Executor component3() {
        return this.executor;
    }

    public final UserHandle component4() {
        return this.user;
    }

    public final ReceiverData copy(BroadcastReceiver receiver, IntentFilter filter, Executor executor, UserHandle user) {
        n.g(receiver, "receiver");
        n.g(filter, "filter");
        n.g(executor, "executor");
        n.g(user, "user");
        return new ReceiverData(receiver, filter, executor, user);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof ReceiverData)) {
            return false;
        }
        ReceiverData receiverData = (ReceiverData) obj;
        return n.c(this.receiver, receiverData.receiver) && n.c(this.filter, receiverData.filter) && n.c(this.executor, receiverData.executor) && n.c(this.user, receiverData.user);
    }

    public final Executor getExecutor() {
        return this.executor;
    }

    public final IntentFilter getFilter() {
        return this.filter;
    }

    public final BroadcastReceiver getReceiver() {
        return this.receiver;
    }

    public final UserHandle getUser() {
        return this.user;
    }

    public int hashCode() {
        return (((((this.receiver.hashCode() * 31) + this.filter.hashCode()) * 31) + this.executor.hashCode()) * 31) + this.user.hashCode();
    }

    public String toString() {
        return "ReceiverData(receiver=" + this.receiver + ", filter=" + this.filter + ", executor=" + this.executor + ", user=" + this.user + ")";
    }
}
