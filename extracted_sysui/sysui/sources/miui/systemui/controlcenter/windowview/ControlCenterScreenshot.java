package miui.systemui.controlcenter.windowview;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.util.ArrayMap;
import androidx.lifecycle.Lifecycle;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.Executor;
import miui.systemui.broadcast.BroadcastDispatcher;
import miui.systemui.controlcenter.dagger.ControlCenterScope;
import miui.systemui.controlcenter.dagger.qualifiers.Qualifiers;
import miui.systemui.controlcenter.utils.ControlCenterViewController;
import miui.systemui.dagger.qualifiers.Background;
import miuix.android.content.MiuiIntent;

/* JADX INFO: loaded from: classes.dex */
@ControlCenterScope
public final class ControlCenterScreenshot extends ControlCenterViewController<ControlCenterWindowViewImpl> {
    private final Executor bgExecutor;
    private final BroadcastDispatcher broadcastDispatcher;
    private final ControlCenterScreenshot$broadcastReceiver$1 broadcastReceiver;
    private final ArrayMap<String, String> dumpMessages;
    private final ArrayList<OnScreenshotListener> listeners;

    public interface OnScreenshotListener {
        void onScreenshot();
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Type inference failed for: r2v3, types: [miui.systemui.controlcenter.windowview.ControlCenterScreenshot$broadcastReceiver$1] */
    public ControlCenterScreenshot(@Qualifiers.WindowView ControlCenterWindowViewImpl windowView, BroadcastDispatcher broadcastDispatcher, @Background Executor bgExecutor) {
        super(windowView);
        kotlin.jvm.internal.n.g(windowView, "windowView");
        kotlin.jvm.internal.n.g(broadcastDispatcher, "broadcastDispatcher");
        kotlin.jvm.internal.n.g(bgExecutor, "bgExecutor");
        this.broadcastDispatcher = broadcastDispatcher;
        this.bgExecutor = bgExecutor;
        this.listeners = new ArrayList<>();
        this.dumpMessages = new ArrayMap<>();
        this.broadcastReceiver = new BroadcastReceiver() { // from class: miui.systemui.controlcenter.windowview.ControlCenterScreenshot$broadcastReceiver$1
            @Override // android.content.BroadcastReceiver
            public void onReceive(Context context, Intent intent) {
                if (intent == null || intent.getBooleanExtra(MiuiIntent.EXTRA_IS_FINISHED, true)) {
                    return;
                }
                this.this$0.onScreenshot();
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Multi-variable type inference failed */
    public final void onScreenshot() {
        if (((ControlCenterWindowViewImpl) getView()).getLifecycle().getCurrentState().isAtLeast(Lifecycle.State.STARTED)) {
            Iterator<T> it = this.listeners.iterator();
            while (it.hasNext()) {
                ((OnScreenshotListener) it.next()).onScreenshot();
            }
        }
    }

    public final void addDumpMessage(String tag, String msg) {
        kotlin.jvm.internal.n.g(tag, "tag");
        kotlin.jvm.internal.n.g(msg, "msg");
        this.dumpMessages.put(tag, msg);
    }

    public final void addOnScreenshotListener(OnScreenshotListener listener) {
        kotlin.jvm.internal.n.g(listener, "listener");
        if (this.listeners.contains(listener)) {
            return;
        }
        this.listeners.add(listener);
    }

    @Override // miui.systemui.controlcenter.utils.ControlCenterViewController
    public void dump(PrintWriter pw, String[] args) {
        kotlin.jvm.internal.n.g(pw, "pw");
        kotlin.jvm.internal.n.g(args, "args");
        pw.println("ControlCenterScreenshot state:");
        for (Map.Entry<String, String> entry : this.dumpMessages.entrySet()) {
            pw.println("  " + entry.getKey() + "=" + entry.getValue());
        }
    }

    @Override // miui.systemui.util.ViewController
    public void onCreate() {
        BroadcastDispatcher.registerReceiver$default(this.broadcastDispatcher, this.broadcastReceiver, new IntentFilter(MiuiIntent.ACTION_TAKE_SCREENSHOT), this.bgExecutor, null, 8, null);
    }

    @Override // miui.systemui.controlcenter.utils.ControlCenterViewController
    public void onDestroy() {
        this.broadcastDispatcher.unregisterReceiver(this.broadcastReceiver);
        this.listeners.clear();
    }

    public final void removeOnScreenshotListener(OnScreenshotListener listener) {
        kotlin.jvm.internal.n.g(listener, "listener");
        this.listeners.remove(listener);
    }
}
