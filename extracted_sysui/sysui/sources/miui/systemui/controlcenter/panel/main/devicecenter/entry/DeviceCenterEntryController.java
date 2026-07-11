package miui.systemui.controlcenter.panel.main.devicecenter.entry;

import I0.m;
import android.annotation.SuppressLint;
import android.app.KeyguardManager;
import android.content.ContentResolver;
import android.content.Intent;
import android.database.ContentObserver;
import android.net.Uri;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import androidx.lifecycle.Lifecycle;
import com.android.systemui.plugins.ActivityStarter;
import com.android.systemui.plugins.miui.settings.IUserTracker;
import com.android.systemui.plugins.miui.settings.SuperSaveModeController;
import java.util.ArrayList;
import java.util.concurrent.Executor;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.n;
import miui.systemui.controlcenter.dagger.ControlCenterScope;
import miui.systemui.controlcenter.dagger.qualifiers.Qualifiers;
import miui.systemui.controlcenter.databinding.DeviceCenterEntryItemBinding;
import miui.systemui.controlcenter.events.ControlCenterEventTracker;
import miui.systemui.controlcenter.panel.main.MainPanelContent;
import miui.systemui.controlcenter.panel.main.MainPanelContentDistributor;
import miui.systemui.controlcenter.panel.main.MainPanelController;
import miui.systemui.controlcenter.panel.main.MainPanelModeController;
import miui.systemui.controlcenter.panel.main.MainPanelStyleController;
import miui.systemui.controlcenter.panel.main.devicecenter.devices.DeviceCenterCardController;
import miui.systemui.controlcenter.panel.main.recyclerview.MainPanelItemViewHolder;
import miui.systemui.controlcenter.panel.main.recyclerview.MainPanelListItem;
import miui.systemui.controlcenter.windowview.ControlCenterWindowViewController;
import miui.systemui.controlcenter.windowview.ControlCenterWindowViewImpl;
import miui.systemui.dagger.qualifiers.Background;
import miui.systemui.dagger.qualifiers.Main;
import miui.systemui.miplay.MiPlayDetailActivity;
import miui.systemui.util.CommonUtils;
import miui.systemui.util.ControlsUtils;
import miui.systemui.util.HapticFeedback;
import miui.systemui.util.MiLinkController;
import miui.systemui.util.SmartDeviceUtils;
import systemui.plugin.eventtracking.EventTracker;
import systemui.plugin.eventtracking.events.DeviceCenterEventsKt;

/* JADX INFO: loaded from: classes.dex */
@ControlCenterScope
@SuppressLint({"UseCompatLoadingForDrawables"})
public final class DeviceCenterEntryController extends MainPanelListItem.Controller<ControlCenterWindowViewImpl> implements MainPanelContent {
    public static final Companion Companion = new Companion(null);
    private static final String TAG = "DeviceCenterEntryController";
    public static final int TYPE_DEVICE_CENTER = 338423;
    private final ActivityStarter activityStarter;
    private boolean availableInKeyguard;
    private boolean availableOfSettings;
    private final Handler bgHandler;
    private final ArrayList<DeviceCenterCardController> childControllers;
    private final DeviceCenterCardController deviceCenterCardController;
    private boolean deviceCenterListening;
    private final HapticFeedback hapticFeedback;
    private boolean inKeyguard;
    private final KeyguardManager.KeyguardLockedStateListener keyguardLockedStateListener;
    private final KeyguardManager keyguardManager;
    private final ArrayList<DeviceCenterEntryController> listItems;
    private final Executor mainExecutor;
    private final E0.a mainPanelContentDistributor;
    private final E0.a mainPanelModeController;
    private final E0.a mainPanelStyleController;
    private final DeviceCenterEntryController$miLinkAvailableCallback$1 miLinkAvailableCallback;
    private final MiLinkController miLinkController;
    private final DeviceCenterEntryController$onStyleChangedListener$1 onStyleChangedListener;
    private final DeviceCenterEntryController$onUserUnlockedListener$1 onUserUnlockedListener;
    private final int priority;
    private final boolean rightOrLeft;
    private DeviceCenterEntryController$settingsObserver$1 settingsObserver;
    private final int spanSize;
    private final SuperSaveModeController superSaveModeController;
    private final int type;
    private final IUserTracker userTracker;
    private final E0.a windowViewController;

    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Type inference failed for: r2v10, types: [miui.systemui.controlcenter.panel.main.devicecenter.entry.DeviceCenterEntryController$onUserUnlockedListener$1] */
    /* JADX WARN: Type inference failed for: r2v11, types: [miui.systemui.controlcenter.panel.main.devicecenter.entry.DeviceCenterEntryController$miLinkAvailableCallback$1] */
    /* JADX WARN: Type inference failed for: r2v12, types: [miui.systemui.controlcenter.panel.main.devicecenter.entry.DeviceCenterEntryController$onStyleChangedListener$1] */
    /* JADX WARN: Type inference failed for: r2v8, types: [miui.systemui.controlcenter.panel.main.devicecenter.entry.DeviceCenterEntryController$settingsObserver$1] */
    public DeviceCenterEntryController(@Qualifiers.WindowView ControlCenterWindowViewImpl windowView, @Qualifiers.ControlCenter Lifecycle lifecycle, DeviceCenterCardController deviceCenterCardController, @Background final Handler bgHandler, @Main Executor mainExecutor, ActivityStarter activityStarter, E0.a mainPanelContentDistributor, E0.a windowViewController, E0.a mainPanelStyleController, E0.a mainPanelModeController, MiLinkController miLinkController, HapticFeedback hapticFeedback, IUserTracker userTracker, SuperSaveModeController superSaveModeController) {
        super(windowView, lifecycle);
        n.g(windowView, "windowView");
        n.g(lifecycle, "lifecycle");
        n.g(deviceCenterCardController, "deviceCenterCardController");
        n.g(bgHandler, "bgHandler");
        n.g(mainExecutor, "mainExecutor");
        n.g(activityStarter, "activityStarter");
        n.g(mainPanelContentDistributor, "mainPanelContentDistributor");
        n.g(windowViewController, "windowViewController");
        n.g(mainPanelStyleController, "mainPanelStyleController");
        n.g(mainPanelModeController, "mainPanelModeController");
        n.g(miLinkController, "miLinkController");
        n.g(hapticFeedback, "hapticFeedback");
        n.g(userTracker, "userTracker");
        n.g(superSaveModeController, "superSaveModeController");
        this.deviceCenterCardController = deviceCenterCardController;
        this.bgHandler = bgHandler;
        this.mainExecutor = mainExecutor;
        this.activityStarter = activityStarter;
        this.mainPanelContentDistributor = mainPanelContentDistributor;
        this.windowViewController = windowViewController;
        this.mainPanelStyleController = mainPanelStyleController;
        this.mainPanelModeController = mainPanelModeController;
        this.miLinkController = miLinkController;
        this.hapticFeedback = hapticFeedback;
        this.userTracker = userTracker;
        this.superSaveModeController = superSaveModeController;
        this.childControllers = m.f(deviceCenterCardController);
        this.availableInKeyguard = true;
        this.availableOfSettings = true;
        Object systemService = getContext().getSystemService("keyguard");
        n.e(systemService, "null cannot be cast to non-null type android.app.KeyguardManager");
        KeyguardManager keyguardManager = (KeyguardManager) systemService;
        this.keyguardManager = keyguardManager;
        this.inKeyguard = keyguardManager.isKeyguardLocked();
        this.settingsObserver = new ContentObserver(bgHandler) { // from class: miui.systemui.controlcenter.panel.main.devicecenter.entry.DeviceCenterEntryController$settingsObserver$1
            @Override // android.database.ContentObserver
            public void onChange(boolean z2, Uri uri) {
                if (uri != null) {
                    DeviceCenterEntryController deviceCenterEntryController = this.this$0;
                    SmartDeviceUtils smartDeviceUtils = SmartDeviceUtils.INSTANCE;
                    if (n.c(uri, smartDeviceUtils.getURI_SMART_DEVICE_CONTROL())) {
                        deviceCenterEntryController.updateAvailableOfSettings();
                    } else if (n.c(uri, smartDeviceUtils.getURI_LOCKSCREEN_SMART_DEVICE_CONTROL())) {
                        deviceCenterEntryController.updateAvailableInKeyguard();
                    }
                }
            }
        };
        this.keyguardLockedStateListener = new KeyguardManager.KeyguardLockedStateListener() { // from class: miui.systemui.controlcenter.panel.main.devicecenter.entry.a
            @Override // android.app.KeyguardManager.KeyguardLockedStateListener
            public final void onKeyguardLockedStateChanged(boolean z2) {
                DeviceCenterEntryController.keyguardLockedStateListener$lambda$0(this.f5373a, z2);
            }
        };
        this.onUserUnlockedListener = new ControlCenterWindowViewController.OnUserUnlockedListener() { // from class: miui.systemui.controlcenter.panel.main.devicecenter.entry.DeviceCenterEntryController$onUserUnlockedListener$1
            @Override // miui.systemui.controlcenter.windowview.ControlCenterWindowViewController.OnUserUnlockedListener
            public void onUserUnlocked() {
                DeviceCenterEntryController deviceCenterEntryController = this.this$0;
                deviceCenterEntryController.setDeviceCenterListening(deviceCenterEntryController.checkAvailable());
            }
        };
        this.miLinkAvailableCallback = new MiLinkController.Callback() { // from class: miui.systemui.controlcenter.panel.main.devicecenter.entry.DeviceCenterEntryController$miLinkAvailableCallback$1
            @Override // miui.systemui.util.MiLinkController.Callback
            public void onChanged(boolean z2) {
                this.this$0.updateAvailableOfSettings();
            }
        };
        this.onStyleChangedListener = new MainPanelStyleController.OnStyleChangedListener() { // from class: miui.systemui.controlcenter.panel.main.devicecenter.entry.DeviceCenterEntryController$onStyleChangedListener$1
            @Override // miui.systemui.controlcenter.panel.main.MainPanelStyleController.OnStyleChangedListener
            public void onStyleChanged() {
                DeviceCenterEntryController deviceCenterEntryController = this.this$0;
                deviceCenterEntryController.setDeviceCenterListening(deviceCenterEntryController.checkAvailable());
            }
        };
        this.listItems = m.f(this);
        this.priority = 50;
        this.type = TYPE_DEVICE_CENTER;
        this.spanSize = 4;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final boolean checkAvailable() {
        return available(!CommonUtils.INSTANCE.getInVerticalMode(getContext()));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void keyguardLockedStateListener$lambda$0(DeviceCenterEntryController this$0, boolean z2) {
        n.g(this$0, "this$0");
        Log.d(TAG, "onKeyguardLockedStateChanged: " + z2);
        if (z2 != this$0.inKeyguard) {
            this$0.inKeyguard = z2;
            this$0.setDeviceCenterListening(this$0.checkAvailable());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onBindViewHolder$lambda$9$lambda$6(final DeviceCenterEntryViewHolder this_apply, DeviceCenterEntryController this$0, View view) {
        n.g(this_apply, "$this_apply");
        n.g(this$0, "this$0");
        if (!CommonUtils.isTinyScreen(this_apply.getContext())) {
            ControlCenterEventTracker.Companion.trackMiSmartHubClickedEvent(EventTracker.Companion.getScreenType(this_apply.getContext()), this_apply.getContext().getResources().getConfiguration().orientation, false);
        }
        this$0.hapticFeedback.click();
        Intent intent = new Intent(ControlsUtils.MI_SMART_HUB_ACTION);
        intent.addCategory("android.intent.category.DEFAULT");
        intent.putExtra(MiPlayDetailActivity.EXTRA_PARAM_REF, DeviceCenterEventsKt.PAGE_DEVICE_CENTER_EXPOSE);
        this$0.activityStarter.startActivity(intent, false);
        this_apply.itemView.post(new Runnable() { // from class: miui.systemui.controlcenter.panel.main.devicecenter.entry.f
            @Override // java.lang.Runnable
            public final void run() {
                DeviceCenterEntryController.onBindViewHolder$lambda$9$lambda$6$lambda$5(this_apply);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onBindViewHolder$lambda$9$lambda$6$lambda$5(DeviceCenterEntryViewHolder this_apply) {
        n.g(this_apply, "$this_apply");
        Log.i(TAG, "dpi: " + this_apply.getContext().getResources().getDisplayMetrics().density + "+ \npixel-width: " + this_apply.itemView.getWidth());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean onBindViewHolder$lambda$9$lambda$7(DeviceCenterEntryController this$0, DeviceCenterEntryViewHolder this_apply, View view) {
        n.g(this$0, "this$0");
        n.g(this_apply, "$this_apply");
        this$0.hapticFeedback.longClick();
        this$0.activityStarter.postStartActivityDismissingKeyguard(SmartDeviceUtils.INSTANCE.getDeviceCardIntent(1, this_apply.getContext()), 0);
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean onBindViewHolder$lambda$9$lambda$8(DeviceCenterEntryController this$0, DeviceCenterEntryViewHolder this_apply, View view, MotionEvent motionEvent) {
        n.g(this$0, "this$0");
        n.g(this_apply, "$this_apply");
        MainPanelItemViewHolder holder = this$0.getHolder();
        n.e(holder, "null cannot be cast to non-null type miui.systemui.controlcenter.panel.main.devicecenter.entry.DeviceCenterEntryViewHolder");
        ((DeviceCenterEntryViewHolder) holder).onTouch(this_apply.itemView, motionEvent);
        return false;
    }

    private final void postNotifyChanged() {
        this.mainExecutor.execute(new Runnable() { // from class: miui.systemui.controlcenter.panel.main.devicecenter.entry.e
            @Override // java.lang.Runnable
            public final void run() {
                DeviceCenterEntryController.postNotifyChanged$lambda$3(this.f5380a);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void postNotifyChanged$lambda$3(DeviceCenterEntryController this$0) {
        n.g(this$0, "this$0");
        MainPanelContentDistributor mainPanelContentDistributor = (MainPanelContentDistributor) this$0.mainPanelContentDistributor.get();
        n.d(mainPanelContentDistributor);
        MainPanelContentDistributor.distributePanels$default(mainPanelContentDistributor, false, 1, null);
        mainPanelContentDistributor.handleNotifyChanged(false);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void updateAvailableInKeyguard() {
        boolean zIsLockScreenSmartDeviceControlVisible = SmartDeviceUtils.INSTANCE.isLockScreenSmartDeviceControlVisible(getContext(), false);
        if (zIsLockScreenSmartDeviceControlVisible != this.availableInKeyguard) {
            this.availableInKeyguard = zIsLockScreenSmartDeviceControlVisible;
            setDeviceCenterListening(checkAvailable());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void updateAvailableOfSettings() {
        boolean zIsSmartDeviceControlVisible = SmartDeviceUtils.INSTANCE.isSmartDeviceControlVisible(getContext(), false);
        if (zIsSmartDeviceControlVisible != this.availableOfSettings) {
            this.availableOfSettings = zIsSmartDeviceControlVisible;
            setDeviceCenterListening(checkAvailable());
        }
    }

    @Override // miui.systemui.controlcenter.panel.main.MainPanelContent
    public boolean available(boolean z2) {
        Log.i(TAG, "available " + this.miLinkController.getMiLinkAvailable() + " " + this.availableOfSettings + " " + this.availableInKeyguard + " " + this.inKeyguard + " " + ((ControlCenterWindowViewController) this.windowViewController.get()).getUserUnlocked());
        return ((MainPanelStyleController) this.mainPanelStyleController.get()).getStyle() != MainPanelController.Style.COMPACT && ((MainPanelModeController) this.mainPanelModeController.get()).getMode() != MainPanelController.Mode.EDIT && !this.superSaveModeController.isActive() && this.userTracker.getUserId() == 0 && this.miLinkController.getMiLinkAvailable() && this.availableOfSettings && (this.availableInKeyguard || !this.inKeyguard) && ((ControlCenterWindowViewController) this.windowViewController.get()).getUserUnlocked();
    }

    @Override // miui.systemui.controlcenter.panel.main.MainPanelContent
    public MainPanelItemViewHolder createViewHolder(ViewGroup parent, int i2) {
        n.g(parent, "parent");
        if (i2 != 338423) {
            return null;
        }
        DeviceCenterEntryItemBinding deviceCenterEntryItemBindingInflate = DeviceCenterEntryItemBinding.inflate(LayoutInflater.from(getContext()), parent, false);
        n.f(deviceCenterEntryItemBindingInflate, "inflate(...)");
        return new DeviceCenterEntryViewHolder(deviceCenterEntryItemBindingInflate);
    }

    public final boolean getDeviceCenterListening() {
        return this.deviceCenterListening;
    }

    @Override // miui.systemui.controlcenter.panel.main.MainPanelContent
    public int getPriority() {
        return this.priority;
    }

    @Override // miui.systemui.controlcenter.panel.main.MainPanelContent
    public boolean getRightOrLeft() {
        return this.rightOrLeft;
    }

    @Override // miui.systemui.controlcenter.panel.main.recyclerview.MainPanelListItem
    public int getSpanSize() {
        return this.spanSize;
    }

    @Override // miui.systemui.controlcenter.panel.main.recyclerview.MainPanelListItem
    public int getType() {
        return this.type;
    }

    @Override // miui.systemui.controlcenter.panel.main.recyclerview.MainPanelListItem
    @SuppressLint({"ClickableViewAccessibility"})
    public void onBindViewHolder() {
        MainPanelItemViewHolder holder = getHolder();
        final DeviceCenterEntryViewHolder deviceCenterEntryViewHolder = holder instanceof DeviceCenterEntryViewHolder ? (DeviceCenterEntryViewHolder) holder : null;
        if (deviceCenterEntryViewHolder != null) {
            deviceCenterEntryViewHolder.getBinding().list.setAdapter(this.deviceCenterCardController.getAdapter());
            deviceCenterEntryViewHolder.setListening(getListening());
            deviceCenterEntryViewHolder.getBinding().list.setOnClickListener(new View.OnClickListener() { // from class: miui.systemui.controlcenter.panel.main.devicecenter.entry.b
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    DeviceCenterEntryController.onBindViewHolder$lambda$9$lambda$6(deviceCenterEntryViewHolder, this, view);
                }
            });
            deviceCenterEntryViewHolder.getBinding().list.setOnLongClickListener(new View.OnLongClickListener() { // from class: miui.systemui.controlcenter.panel.main.devicecenter.entry.c
                @Override // android.view.View.OnLongClickListener
                public final boolean onLongClick(View view) {
                    return DeviceCenterEntryController.onBindViewHolder$lambda$9$lambda$7(this.f5376a, deviceCenterEntryViewHolder, view);
                }
            });
            deviceCenterEntryViewHolder.getBinding().list.setOnTouchListener(new View.OnTouchListener() { // from class: miui.systemui.controlcenter.panel.main.devicecenter.entry.d
                @Override // android.view.View.OnTouchListener
                public final boolean onTouch(View view, MotionEvent motionEvent) {
                    return DeviceCenterEntryController.onBindViewHolder$lambda$9$lambda$8(this.f5378a, deviceCenterEntryViewHolder, view, motionEvent);
                }
            });
            DeviceCenterEntryViewHolder.changeMode$default(deviceCenterEntryViewHolder, null, false, true, false, 9, null);
        }
    }

    @Override // miui.systemui.util.ViewController
    public void onCreate() {
        super.onCreate();
        this.keyguardManager.addKeyguardLockedStateListener(this.mainExecutor, this.keyguardLockedStateListener);
        ContentResolver contentResolver = getContext().getContentResolver();
        SmartDeviceUtils smartDeviceUtils = SmartDeviceUtils.INSTANCE;
        contentResolver.registerContentObserver(smartDeviceUtils.getURI_SMART_DEVICE_CONTROL(), true, this.settingsObserver);
        contentResolver.registerContentObserver(smartDeviceUtils.getURI_LOCKSCREEN_SMART_DEVICE_CONTROL(), true, this.settingsObserver);
        ((ControlCenterWindowViewController) this.windowViewController.get()).addOnUserUnlockedListener(this.onUserUnlockedListener);
        this.miLinkController.addCallback(this.miLinkAvailableCallback);
        ((MainPanelStyleController) this.mainPanelStyleController.get()).addOnStyleChangedListener(this.onStyleChangedListener);
        updateAvailableOfSettings();
        updateAvailableInKeyguard();
        setDeviceCenterListening(checkAvailable());
    }

    @Override // miui.systemui.controlcenter.utils.ControlCenterViewController
    public void onDestroy() {
        super.onDestroy();
        getContext().getContentResolver().unregisterContentObserver(this.settingsObserver);
        this.keyguardManager.removeKeyguardLockedStateListener(this.keyguardLockedStateListener);
        ((ControlCenterWindowViewController) this.windowViewController.get()).removeOnUserUnlockedListener(this.onUserUnlockedListener);
        this.miLinkController.removeCallback(this.miLinkAvailableCallback);
        ((MainPanelStyleController) this.mainPanelStyleController.get()).removeOnStyleChangedListener(this.onStyleChangedListener);
    }

    @Override // miui.systemui.controlcenter.utils.ControlCenterViewController
    public void onSuperPowerModeChanged(boolean z2) {
        setDeviceCenterListening(checkAvailable());
    }

    @Override // miui.systemui.controlcenter.panel.main.recyclerview.MainPanelListItem
    public void onUnbindViewHolder() {
        MainPanelItemViewHolder holder = getHolder();
        DeviceCenterEntryViewHolder deviceCenterEntryViewHolder = holder instanceof DeviceCenterEntryViewHolder ? (DeviceCenterEntryViewHolder) holder : null;
        if (deviceCenterEntryViewHolder != null) {
            deviceCenterEntryViewHolder.getBinding().list.setAdapter(null);
            deviceCenterEntryViewHolder.setListening(false);
        }
    }

    @Override // miui.systemui.controlcenter.utils.ControlCenterViewController
    public void onUserSwitched(int i2) {
        setDeviceCenterListening(checkAvailable());
    }

    public final void setDeviceCenterListening(boolean z2) {
        if (this.deviceCenterListening == z2) {
            return;
        }
        this.deviceCenterListening = z2;
        postNotifyChanged();
        Log.i(TAG, z2 + " " + this.miLinkController.getMiLinkAvailable() + " " + this.availableOfSettings + " " + this.availableInKeyguard + " " + this.inKeyguard + " " + ((ControlCenterWindowViewController) this.windowViewController.get()).getUserUnlocked());
        MainPanelItemViewHolder holder = getHolder();
        DeviceCenterEntryViewHolder deviceCenterEntryViewHolder = holder instanceof DeviceCenterEntryViewHolder ? (DeviceCenterEntryViewHolder) holder : null;
        if (deviceCenterEntryViewHolder == null) {
            return;
        }
        deviceCenterEntryViewHolder.setListening(z2);
    }

    @Override // miui.systemui.controlcenter.utils.ControlCenterViewController
    public ArrayList<DeviceCenterCardController> getChildControllers() {
        return this.childControllers;
    }

    @Override // miui.systemui.controlcenter.panel.main.MainPanelContent
    public ArrayList<DeviceCenterEntryController> getListItems() {
        return this.listItems;
    }
}
