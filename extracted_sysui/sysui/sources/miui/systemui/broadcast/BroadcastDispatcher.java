package miui.systemui.broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.IntentFilter;
import android.os.Handler;
import android.os.HandlerExecutor;
import android.os.Looper;
import android.os.Message;
import android.os.UserHandle;
import android.text.TextUtils;
import android.util.Log;
import android.util.SparseArray;
import com.android.internal.annotations.VisibleForTesting;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.util.concurrent.Executor;
import kotlin.jvm.internal.n;
import miui.systemui.Dumpable;
import miui.systemui.dagger.qualifiers.Background;
import miui.systemui.dagger.qualifiers.Main;

/* JADX INFO: loaded from: classes2.dex */
public class BroadcastDispatcher implements Dumpable {
    private final Looper bgLooper;
    private final Context context;
    private final BroadcastDispatcher$handler$1 handler;
    private final Handler mainHandler;
    private final SparseArray<UserBroadcastDispatcher> receiversByUser;

    /* JADX WARN: Type inference failed for: r2v2, types: [miui.systemui.broadcast.BroadcastDispatcher$handler$1] */
    public BroadcastDispatcher(Context context, @Main Handler mainHandler, @Background final Looper bgLooper) {
        n.g(context, "context");
        n.g(mainHandler, "mainHandler");
        n.g(bgLooper, "bgLooper");
        this.context = context;
        this.mainHandler = mainHandler;
        this.bgLooper = bgLooper;
        this.receiversByUser = new SparseArray<>(20);
        this.handler = new Handler(bgLooper) { // from class: miui.systemui.broadcast.BroadcastDispatcher$handler$1
            @Override // android.os.Handler
            public void handleMessage(Message msg) {
                n.g(msg, "msg");
                int i2 = msg.what;
                if (i2 == 0) {
                    Object obj = msg.obj;
                    n.e(obj, "null cannot be cast to non-null type miui.systemui.broadcast.ReceiverData");
                    ReceiverData receiverData = (ReceiverData) obj;
                    int userId = receiverData.getUser().getIdentifier() == -2 ? this.this$0.context.getUserId() : receiverData.getUser().getIdentifier();
                    if (userId >= -1) {
                        UserBroadcastDispatcher userBroadcastDispatcher = (UserBroadcastDispatcher) this.this$0.receiversByUser.get(userId, this.this$0.createUBRForUser(userId));
                        this.this$0.receiversByUser.put(userId, userBroadcastDispatcher);
                        userBroadcastDispatcher.registerReceiver(receiverData);
                        return;
                    } else {
                        if (BroadcastDispatcherKt.DEBUG) {
                            Log.w("BroadcastDispatcher", "Register receiver for invalid user: " + userId);
                            return;
                        }
                        return;
                    }
                }
                if (i2 == 1) {
                    int size = this.this$0.receiversByUser.size();
                    for (int i3 = 0; i3 < size; i3++) {
                        UserBroadcastDispatcher userBroadcastDispatcher2 = (UserBroadcastDispatcher) this.this$0.receiversByUser.valueAt(i3);
                        Object obj2 = msg.obj;
                        n.e(obj2, "null cannot be cast to non-null type android.content.BroadcastReceiver");
                        userBroadcastDispatcher2.unregisterReceiver((BroadcastReceiver) obj2);
                    }
                    return;
                }
                if (i2 != 2) {
                    super.handleMessage(msg);
                    return;
                }
                UserBroadcastDispatcher userBroadcastDispatcher3 = (UserBroadcastDispatcher) this.this$0.receiversByUser.get(msg.arg1);
                if (userBroadcastDispatcher3 != null) {
                    Object obj3 = msg.obj;
                    n.e(obj3, "null cannot be cast to non-null type android.content.BroadcastReceiver");
                    userBroadcastDispatcher3.unregisterReceiver((BroadcastReceiver) obj3);
                }
            }
        };
    }

    private final void checkFilter(IntentFilter intentFilter) {
        StringBuilder sb = new StringBuilder();
        if (intentFilter.countActions() == 0) {
            sb.append("Filter must contain at least one action. ");
        }
        if (intentFilter.countDataAuthorities() != 0) {
            sb.append("Filter cannot contain DataAuthorities. ");
        }
        if (intentFilter.countDataPaths() != 0) {
            sb.append("Filter cannot contain DataPaths. ");
        }
        if (intentFilter.countDataSchemes() != 0) {
            sb.append("Filter cannot contain DataSchemes. ");
        }
        if (intentFilter.countDataTypes() != 0) {
            sb.append("Filter cannot contain DataTypes. ");
        }
        if (intentFilter.getPriority() != 0) {
            sb.append("Filter cannot modify priority. ");
        }
        if (!TextUtils.isEmpty(sb)) {
            throw new IllegalArgumentException(sb.toString());
        }
    }

    public static /* synthetic */ void registerReceiver$default(BroadcastDispatcher broadcastDispatcher, BroadcastReceiver broadcastReceiver, IntentFilter intentFilter, Executor executor, UserHandle CURRENT, int i2, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: registerReceiver");
        }
        if ((i2 & 4) != 0) {
            executor = broadcastDispatcher.context.getMainExecutor();
        }
        if ((i2 & 8) != 0) {
            CURRENT = UserHandle.CURRENT;
            n.f(CURRENT, "CURRENT");
        }
        broadcastDispatcher.registerReceiver(broadcastReceiver, intentFilter, executor, CURRENT);
    }

    @VisibleForTesting
    public UserBroadcastDispatcher createUBRForUser(int i2) {
        return new UserBroadcastDispatcher(this.context, i2, this.bgLooper);
    }

    @Override // miui.systemui.Dumpable
    public void dump(FileDescriptor fileDescriptor, PrintWriter pw, String[] args) {
        n.g(pw, "pw");
        n.g(args, "args");
        pw.println("Broadcast dispatcher:");
        int size = this.receiversByUser.size();
        for (int i2 = 0; i2 < size; i2++) {
            pw.println("  User " + this.receiversByUser.keyAt(i2));
            this.receiversByUser.valueAt(i2).dump(fileDescriptor, pw, args);
        }
    }

    public final void registerReceiver(BroadcastReceiver receiver, IntentFilter filter) {
        n.g(receiver, "receiver");
        n.g(filter, "filter");
        registerReceiver$default(this, receiver, filter, null, null, 12, null);
    }

    public void registerReceiverWithHandler(BroadcastReceiver receiver, IntentFilter filter, Handler handler, UserHandle user) {
        n.g(receiver, "receiver");
        n.g(filter, "filter");
        n.g(handler, "handler");
        n.g(user, "user");
        registerReceiver(receiver, filter, new HandlerExecutor(handler), user);
    }

    public void unregisterReceiver(BroadcastReceiver receiver) {
        n.g(receiver, "receiver");
        obtainMessage(1, receiver).sendToTarget();
    }

    public void unregisterReceiverForUser(BroadcastReceiver receiver, UserHandle user) {
        n.g(receiver, "receiver");
        n.g(user, "user");
        obtainMessage(2, user.getIdentifier(), 0, receiver).sendToTarget();
    }

    public final void registerReceiver(BroadcastReceiver receiver, IntentFilter filter, Executor executor) {
        n.g(receiver, "receiver");
        n.g(filter, "filter");
        registerReceiver$default(this, receiver, filter, executor, null, 8, null);
    }

    public void registerReceiver(BroadcastReceiver receiver, IntentFilter filter, Executor executor, UserHandle user) {
        n.g(receiver, "receiver");
        n.g(filter, "filter");
        n.g(user, "user");
        checkFilter(filter);
        BroadcastDispatcher$handler$1 broadcastDispatcher$handler$1 = this.handler;
        if (executor == null) {
            executor = this.context.getMainExecutor();
        }
        n.d(executor);
        broadcastDispatcher$handler$1.obtainMessage(0, new ReceiverData(receiver, filter, executor, user)).sendToTarget();
    }
}
