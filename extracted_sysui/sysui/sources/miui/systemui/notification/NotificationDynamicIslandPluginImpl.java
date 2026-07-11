package miui.systemui.notification;

import android.service.notification.StatusBarNotification;
import android.util.Log;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LifecycleRegistry;
import com.android.systemui.plugins.ActivityStarter;
import com.android.systemui.plugins.annotations.Requirements;
import com.android.systemui.plugins.annotations.Requires;
import com.android.systemui.plugins.miui.dynamicisland.DynamicIslandContent;
import com.android.systemui.plugins.miui.notification.NotificationDynamicIslandPlugin;
import com.android.systemui.plugins.miui.settings.SuperSaveModeController;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.n;
import miui.systemui.dagger.PluginComponentInitializer;
import miui.systemui.dynamicisland.window.DynamicIslandWindowViewCreator;
import miui.systemui.notification.focus.FocusNotificationController;
import miui.systemui.plugins.PluginBase;

/* JADX INFO: loaded from: classes4.dex */
@Requirements({@Requires(target = NotificationDynamicIslandPlugin.class, version = 1), @Requires(target = ActivityStarter.class, version = 2), @Requires(target = SuperSaveModeController.class, version = 1)})
public final class NotificationDynamicIslandPluginImpl extends PluginBase implements NotificationDynamicIslandPlugin, LifecycleOwner {
    public static final Companion Companion = new Companion(null);
    private static final String TAG = "DynamicIslandPluginImpl";
    private DynamicIslandContent dynamicIslandContent;
    public FocusNotificationController focusNotificationController;
    private final LifecycleRegistry lifecycle = new LifecycleRegistry(this);
    public DynamicIslandWindowViewCreator windowViewCreator;

    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }

    public DynamicIslandContent getDynamicIslandContent() {
        DynamicIslandContent dynamicIslandContentCreateView = getWindowViewCreator().createView();
        if (dynamicIslandContentCreateView == null) {
            return null;
        }
        getLifecycle().handleLifecycleEvent(Lifecycle.Event.ON_START);
        return dynamicIslandContentCreateView;
    }

    public void getFocusNotificationContent(StatusBarNotification sbn, NotificationDynamicIslandPlugin.FocusInflationCallback focusInflationCallback) {
        n.g(sbn, "sbn");
        n.g(focusInflationCallback, "focusInflationCallback");
        Log.e(TAG, "getFocusNotificationContent" + getFocusNotificationController() + "sbn: " + sbn.getKey());
        getFocusNotificationController().getFocusNotificationContent(sbn, focusInflationCallback);
    }

    public final FocusNotificationController getFocusNotificationController() {
        FocusNotificationController focusNotificationController = this.focusNotificationController;
        if (focusNotificationController != null) {
            return focusNotificationController;
        }
        n.w("focusNotificationController");
        return null;
    }

    public final DynamicIslandWindowViewCreator getWindowViewCreator() {
        DynamicIslandWindowViewCreator dynamicIslandWindowViewCreator = this.windowViewCreator;
        if (dynamicIslandWindowViewCreator != null) {
            return dynamicIslandWindowViewCreator;
        }
        n.w("windowViewCreator");
        return null;
    }

    @Override // miui.systemui.plugins.PluginBase
    public void onCreated() {
        Log.e(TAG, "plugin onCreate");
        PluginComponentInitializer.getPluginComponent().inject(this);
    }

    @Override // miui.systemui.plugins.PluginBase
    public void onDestroyed() {
        Log.e(TAG, "plugin onDestroy");
        getWindowViewCreator().onPluginDestroy();
    }

    public void onMaxBoundsChanged(StatusBarNotification sbn) {
        n.g(sbn, "sbn");
    }

    public void setDynamicIslandContent(DynamicIslandContent dynamicIslandContent) {
        this.dynamicIslandContent = dynamicIslandContent;
    }

    public final void setFocusNotificationController(FocusNotificationController focusNotificationController) {
        n.g(focusNotificationController, "<set-?>");
        this.focusNotificationController = focusNotificationController;
    }

    public final void setWindowViewCreator(DynamicIslandWindowViewCreator dynamicIslandWindowViewCreator) {
        n.g(dynamicIslandWindowViewCreator, "<set-?>");
        this.windowViewCreator = dynamicIslandWindowViewCreator;
    }

    @Override // androidx.lifecycle.LifecycleOwner
    public LifecycleRegistry getLifecycle() {
        return this.lifecycle;
    }
}
