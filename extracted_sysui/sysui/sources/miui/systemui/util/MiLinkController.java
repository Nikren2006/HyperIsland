package miui.systemui.util;

import android.content.Context;
import android.content.IntentFilter;
import android.os.Handler;
import java.util.ArrayList;
import java.util.Iterator;
import kotlin.jvm.internal.DefaultConstructorMarker;
import miui.systemui.dagger.qualifiers.Background;
import miui.systemui.dagger.qualifiers.Main;
import miui.systemui.dagger.qualifiers.Plugin;
import miui.systemui.util.MiLinkController$broadcastReceiver$2;
import miui.systemui.util.concurrency.DelayableExecutor;

/* JADX INFO: loaded from: classes4.dex */
public final class MiLinkController {
    private static final String CAMERA_PERMISSION = "com.mi.systemui.permission.SEND_CAMERA_BROADCAST";
    public static final int CAMERA_UNUSED = 0;
    private static final String CAMERA_USAGE_CHANGED_ACTION = "com.mi.systemui.ACTION_CAMERA_STATE_UPDATE";
    public static final int CAMERA_USAGE_IN_USE = 1;
    public static final Companion Companion = new Companion(null);
    private static final String KEY_EXTRA_CAMERA_USAGE_STATE = "CAMERA_USAGE_STATE";
    private static final String TAG = "MiLinkController";
    private static int cameraUsageState;
    private final Handler bgHandler;
    private final H0.d broadcastReceiver$delegate;
    private final ArrayList<Callback> callbacks;
    private final Context context;
    private boolean inited;
    private boolean miLinkAvailable;
    private boolean registered;
    private final DelayableExecutor uiExecutor;

    public interface Callback {
        void onChanged(boolean z2);
    }

    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final int getCameraUsageState() {
            return MiLinkController.cameraUsageState;
        }

        public final void setCameraUsageState(int i2) {
            MiLinkController.cameraUsageState = i2;
        }

        private Companion() {
        }
    }

    public MiLinkController(@Plugin Context context, @Background Handler bgHandler, @Main DelayableExecutor uiExecutor) {
        kotlin.jvm.internal.n.g(context, "context");
        kotlin.jvm.internal.n.g(bgHandler, "bgHandler");
        kotlin.jvm.internal.n.g(uiExecutor, "uiExecutor");
        this.context = context;
        this.bgHandler = bgHandler;
        this.uiExecutor = uiExecutor;
        this.callbacks = new ArrayList<>();
        this.miLinkAvailable = true;
        this.broadcastReceiver$delegate = H0.e.b(new MiLinkController$broadcastReceiver$2(this));
    }

    private final MiLinkController$broadcastReceiver$2.AnonymousClass1 getBroadcastReceiver() {
        return (MiLinkController$broadcastReceiver$2.AnonymousClass1) this.broadcastReceiver$delegate.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onPluginCreated$lambda$1(MiLinkController this$0) {
        kotlin.jvm.internal.n.g(this$0, "this$0");
        updateMiLinkPackageAvailable$default(this$0, false, 1, null);
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.intent.action.PACKAGE_REMOVED");
        intentFilter.addAction("android.intent.action.PACKAGE_ADDED");
        intentFilter.addDataScheme("package");
        this$0.context.registerReceiver(this$0.getBroadcastReceiver(), intentFilter, null, this$0.bgHandler, 2);
        this$0.context.registerReceiver(this$0.getBroadcastReceiver(), new IntentFilter("android.intent.action.USER_UNLOCKED"), null, this$0.bgHandler, 2);
        this$0.context.registerReceiver(this$0.getBroadcastReceiver(), new IntentFilter(CAMERA_USAGE_CHANGED_ACTION), CAMERA_PERMISSION, this$0.bgHandler, 2);
        this$0.registered = true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onPluginDestroyed$lambda$2(MiLinkController this$0) {
        kotlin.jvm.internal.n.g(this$0, "this$0");
        if (this$0.registered) {
            this$0.registered = false;
            this$0.context.unregisterReceiver(this$0.getBroadcastReceiver());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void updateMiLinkPackageAvailable(boolean z2) {
        final boolean zCheckDeviceCenterAvailable = z2 ? false : SmartDeviceUtils.INSTANCE.checkDeviceCenterAvailable(this.context);
        this.uiExecutor.execute(new Runnable() { // from class: miui.systemui.util.q
            @Override // java.lang.Runnable
            public final void run() {
                MiLinkController.updateMiLinkPackageAvailable$lambda$4(this.f5919a, zCheckDeviceCenterAvailable);
            }
        });
    }

    public static /* synthetic */ void updateMiLinkPackageAvailable$default(MiLinkController miLinkController, boolean z2, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            z2 = false;
        }
        miLinkController.updateMiLinkPackageAvailable(z2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void updateMiLinkPackageAvailable$lambda$4(MiLinkController this$0, boolean z2) {
        kotlin.jvm.internal.n.g(this$0, "this$0");
        if (this$0.miLinkAvailable != z2) {
            this$0.miLinkAvailable = z2;
            Iterator<T> it = this$0.callbacks.iterator();
            while (it.hasNext()) {
                ((Callback) it.next()).onChanged(this$0.miLinkAvailable);
            }
        }
    }

    public final void addCallback(Callback callback) {
        kotlin.jvm.internal.n.g(callback, "callback");
        if (this.callbacks.contains(callback)) {
            return;
        }
        this.callbacks.add(callback);
    }

    public final boolean getMiLinkAvailable() {
        return this.miLinkAvailable;
    }

    public final void onPluginCreated() {
        if (this.inited) {
            return;
        }
        this.inited = true;
        this.bgHandler.post(new Runnable() { // from class: miui.systemui.util.r
            @Override // java.lang.Runnable
            public final void run() {
                MiLinkController.onPluginCreated$lambda$1(this.f5921a);
            }
        });
    }

    public final void onPluginDestroyed() {
        if (this.inited) {
            this.inited = false;
            this.bgHandler.post(new Runnable() { // from class: miui.systemui.util.p
                @Override // java.lang.Runnable
                public final void run() {
                    MiLinkController.onPluginDestroyed$lambda$2(this.f5918a);
                }
            });
        }
    }

    public final void removeCallback(Callback callback) {
        kotlin.jvm.internal.n.g(callback, "callback");
        this.callbacks.remove(callback);
    }
}
