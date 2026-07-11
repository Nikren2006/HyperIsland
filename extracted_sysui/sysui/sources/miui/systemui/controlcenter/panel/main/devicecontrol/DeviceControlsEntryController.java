package miui.systemui.controlcenter.panel.main.devicecontrol;

import H0.s;
import I0.m;
import android.annotation.SuppressLint;
import android.app.KeyguardManager;
import android.content.ComponentName;
import android.content.ContentResolver;
import android.database.ContentObserver;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintSet;
import androidx.lifecycle.Lifecycle;
import com.android.systemui.plugins.ActivityStarter;
import java.util.ArrayList;
import java.util.Optional;
import java.util.concurrent.Executor;
import java.util.function.Consumer;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.n;
import kotlin.jvm.internal.o;
import miui.systemui.controlcenter.ConfigUtils;
import miui.systemui.controlcenter.R;
import miui.systemui.controlcenter.dagger.ControlCenterScope;
import miui.systemui.controlcenter.dagger.qualifiers.Qualifiers;
import miui.systemui.controlcenter.databinding.DeviceControlsEntryBinding;
import miui.systemui.controlcenter.events.ControlCenterEventTracker;
import miui.systemui.controlcenter.panel.SecondaryPanelRouter;
import miui.systemui.controlcenter.panel.main.MainPanelContent;
import miui.systemui.controlcenter.panel.main.MainPanelContentDistributor;
import miui.systemui.controlcenter.panel.main.MainPanelController;
import miui.systemui.controlcenter.panel.main.MainPanelModeController;
import miui.systemui.controlcenter.panel.main.MainPanelStyleController;
import miui.systemui.controlcenter.panel.main.recyclerview.MainPanelItemViewHolder;
import miui.systemui.controlcenter.panel.main.recyclerview.MainPanelListItem;
import miui.systemui.controlcenter.panel.main.recyclerview.ScaleItemViewHolder;
import miui.systemui.controlcenter.windowview.ControlCenterWindowViewController;
import miui.systemui.controlcenter.windowview.ControlCenterWindowViewImpl;
import miui.systemui.dagger.qualifiers.Background;
import miui.systemui.dagger.qualifiers.Main;
import miui.systemui.devicecontrols.DCEntryInfo;
import miui.systemui.devicecontrols.DeviceControlsPresenter;
import miui.systemui.util.CommonUtils;
import miui.systemui.util.ControlsUtils;
import miui.systemui.util.HapticFeedback;
import miui.systemui.util.MiLinkController;
import miui.systemui.util.OnClickListenerEx;
import miui.systemui.util.SmartDeviceUtils;
import miui.systemui.widget.ConstraintLayout;
import miuix.animation.base.AnimConfig;
import systemui.plugin.eventtracking.EventTracker;

/* JADX INFO: loaded from: classes.dex */
@ControlCenterScope
@SuppressLint({"UseCompatLoadingForDrawables"})
public final class DeviceControlsEntryController extends MainPanelListItem.Controller<ControlCenterWindowViewImpl> implements MainPanelContent {
    public static final Companion Companion = new Companion(null);
    private static final String TAG = "DeviceControlsEntryController";
    public static final int TYPE_DEVICE_CONTROLS = 2668765;
    private final ActivityStarter activityStarter;
    private boolean availableInKeyguard;
    private boolean availableOfSettings;
    private final Handler bgHandler;
    private DCEntryInfo currentDCEntryInfo;
    private final Consumer<DCEntryInfo> dcEntryInfoCallback;
    private boolean deviceControlListening;
    private final Optional<DeviceControlsPresenter> deviceControlsPresenter;
    private final HapticFeedback hapticFeedback;
    private boolean inKeyguard;
    private final KeyguardManager.KeyguardLockedStateListener keyguardLockedStateListener;
    private final KeyguardManager keyguardManager;
    private final ArrayList<DeviceControlsEntryController> listItems;
    private final Executor mainExecutor;
    private final E0.a mainPanelContentDistributor;
    private final E0.a mainPanelModeController;
    private final E0.a mainPanelStyleController;
    private final DeviceControlsEntryController$miLinkAvailableCallback$1 miLinkAvailableCallback;
    private final MiLinkController miLinkController;
    private final E0.a modeController;
    private final DeviceControlsEntryController$onStyleChangedListener$1 onStyleChangedListener;
    private boolean pendingUpdate;
    private final int priority;
    private final boolean rightOrLeft;
    private final E0.a secondaryPanelRouter;
    private DeviceControlsEntryController$settingsObserver$1 settingsObserver;
    private final int spanSize;
    private final int type;
    private final E0.a windowViewController;

    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }

    public static final class DeviceControlEntryViewHolder extends ScaleItemViewHolder {
        private final DeviceControlsEntryBinding binding;

        /* JADX WARN: Illegal instructions before constructor call */
        public DeviceControlEntryViewHolder(DeviceControlsEntryBinding binding) {
            n.g(binding, "binding");
            ConstraintLayout root = binding.getRoot();
            n.f(root, "getRoot(...)");
            super(root);
            this.binding = binding;
            this.itemView.setOnTouchListener(this);
        }

        public final void attachToDCEntryInfo(DCEntryInfo info) {
            Drawable.ConstantState constantState;
            n.g(info, "info");
            this.binding.entryTitle.setText(info.getAppLabel());
            ImageView imageView = this.binding.entryIcon;
            ComponentName componentName = info.getComponentName();
            Drawable drawableNewDrawable = null;
            if (n.c(componentName != null ? componentName.getPackageName() : null, "com.xiaomi.smarthome")) {
                drawableNewDrawable = getContext().getDrawable(R.drawable.ic_mi_home_entry);
            } else {
                Drawable appIcon = info.getAppIcon();
                if (appIcon != null && (constantState = appIcon.getConstantState()) != null) {
                    drawableNewDrawable = constantState.newDrawable();
                }
            }
            imageView.setImageDrawable(drawableNewDrawable);
        }

        public final DeviceControlsEntryBinding getBinding() {
            return this.binding;
        }

        @Override // miui.systemui.controlcenter.panel.main.recyclerview.MainPanelItemViewHolder
        public void onConfigurationChanged(int i2) {
            ConfigUtils configUtils = ConfigUtils.INSTANCE;
            boolean zDimensionsChanged = configUtils.dimensionsChanged(i2);
            if (zDimensionsChanged) {
                int dimensionPixelSize = getResources().getDimensionPixelSize(R.dimen.control_center_universal_margin);
                CommonUtils commonUtils = CommonUtils.INSTANCE;
                View itemView = this.itemView;
                n.f(itemView, "itemView");
                CommonUtils.setMargins$default(commonUtils, itemView, dimensionPixelSize, false, 2, null);
                View itemView2 = this.itemView;
                n.f(itemView2, "itemView");
                CommonUtils.setLayoutHeight$default(commonUtils, itemView2, getResources().getDimensionPixelSize(R.dimen.control_center_universal_1_row_size), false, 2, null);
                ImageView entryIcon = this.binding.entryIcon;
                n.f(entryIcon, "entryIcon");
                CommonUtils.setLayoutSize$default(commonUtils, entryIcon, getResources().getDimensionPixelSize(R.dimen.mi_home_entry_icon_width), getResources().getDimensionPixelSize(R.dimen.mi_home_entry_icon_height), false, 4, null);
                ImageView entryIcon2 = this.binding.entryIcon;
                n.f(entryIcon2, "entryIcon");
                CommonUtils.setMarginStart$default(commonUtils, entryIcon2, dimensionPixelSize, false, 2, null);
                TextView entryTitle = this.binding.entryTitle;
                n.f(entryTitle, "entryTitle");
                CommonUtils.setMargins$default(commonUtils, entryTitle, getResources().getDimensionPixelSize(R.dimen.control_center_device_controls_entry_title_margin_start), 0, dimensionPixelSize, 0, false, 26, null);
                ConstraintSet constraintSet = new ConstraintSet();
                View view = this.itemView;
                n.e(view, "null cannot be cast to non-null type miui.systemui.widget.ConstraintLayout");
                constraintSet.clone((ConstraintLayout) view);
                View view2 = this.itemView;
                n.e(view2, "null cannot be cast to non-null type miui.systemui.widget.ConstraintLayout");
                constraintSet.applyTo((ConstraintLayout) view2);
            }
            if (configUtils.textAppearanceChanged(i2)) {
                this.binding.entryTitle.setTextAppearance(R.style.TextAppearance_ExternalEntry_Title);
            }
            if (zDimensionsChanged || configUtils.colorsChanged(i2) || configUtils.blurChanged(i2)) {
                CommonUtils commonUtils2 = CommonUtils.INSTANCE;
                View itemView3 = this.itemView;
                n.f(itemView3, "itemView");
                CommonUtils.setBackgroundResourceEx$default(commonUtils2, itemView3, R.drawable.external_entry_background, false, 2, null);
            }
        }

        @Override // miui.systemui.controlcenter.panel.main.recyclerview.ControlCenterViewHolder
        public AnimConfig updateAnimConfig(boolean z2, boolean z3, int i2, int i3) {
            return getAnimatorConfigHelper().updateAnimEase(this, z2, z3, i2, i3);
        }
    }

    /* JADX INFO: renamed from: miui.systemui.controlcenter.panel.main.devicecontrol.DeviceControlsEntryController$onBindViewHolder$1, reason: invalid class name */
    public static final class AnonymousClass1 extends o implements Function1 {
        public AnonymousClass1() {
            super(1);
        }

        @Override // kotlin.jvm.functions.Function1
        public /* bridge */ /* synthetic */ Object invoke(Object obj) {
            invoke((View) obj);
            return s.f314a;
        }

        public final void invoke(View view) {
            if (((MainPanelModeController) DeviceControlsEntryController.this.modeController.get()).getInPendingEditMode() || ((MainPanelModeController) DeviceControlsEntryController.this.modeController.get()).getInEditMode()) {
                Log.i(DeviceControlsEntryController.TAG, "Edit pending mode , return.");
                return;
            }
            DeviceControlsEntryController.this.hapticFeedback.click();
            if (!CommonUtils.isTinyScreen(DeviceControlsEntryController.this.getContext())) {
                ControlCenterEventTracker.Companion companion = ControlCenterEventTracker.Companion;
                String screenType = EventTracker.Companion.getScreenType(DeviceControlsEntryController.this.getContext());
                int i2 = DeviceControlsEntryController.this.getContext().getResources().getConfiguration().orientation;
                String appLabel = DeviceControlsEntryController.this.currentDCEntryInfo.getAppLabel();
                ComponentName componentName = DeviceControlsEntryController.this.currentDCEntryInfo.getComponentName();
                companion.trackSmartHomeClickEvent(screenType, i2, false, appLabel, componentName != null ? componentName.flattenToString() : null);
            }
            Object obj = DeviceControlsEntryController.this.secondaryPanelRouter.get();
            n.f(obj, "get(...)");
            SecondaryPanelRouter.routeToSmartHome$default((SecondaryPanelRouter) obj, false, 1, null);
        }
    }

    /* JADX INFO: renamed from: miui.systemui.controlcenter.panel.main.devicecontrol.DeviceControlsEntryController$onStartOnce$1, reason: invalid class name and case insensitive filesystem */
    public static final class C04831 extends o implements Function1 {
        public C04831() {
            super(1);
        }

        @Override // kotlin.jvm.functions.Function1
        public /* bridge */ /* synthetic */ Object invoke(Object obj) {
            invoke((DeviceControlsPresenter) obj);
            return s.f314a;
        }

        public final void invoke(DeviceControlsPresenter it) {
            n.g(it, "it");
            it.create(true);
            it.addDCEntryInfoCallback(DeviceControlsEntryController.this.dcEntryInfoCallback);
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Type inference failed for: r2v10, types: [miui.systemui.controlcenter.panel.main.devicecontrol.DeviceControlsEntryController$miLinkAvailableCallback$1] */
    /* JADX WARN: Type inference failed for: r2v11, types: [miui.systemui.controlcenter.panel.main.devicecontrol.DeviceControlsEntryController$onStyleChangedListener$1] */
    /* JADX WARN: Type inference failed for: r2v8, types: [miui.systemui.controlcenter.panel.main.devicecontrol.DeviceControlsEntryController$settingsObserver$1] */
    public DeviceControlsEntryController(@Qualifiers.WindowView ControlCenterWindowViewImpl windowView, @Qualifiers.ControlCenter Lifecycle lifecycle, @Background final Handler bgHandler, @Main Executor mainExecutor, E0.a secondaryPanelRouter, Optional<DeviceControlsPresenter> deviceControlsPresenter, E0.a mainPanelContentDistributor, E0.a mainPanelStyleController, E0.a mainPanelModeController, MiLinkController miLinkController, HapticFeedback hapticFeedback, ActivityStarter activityStarter, E0.a windowViewController, E0.a modeController) {
        super(windowView, lifecycle);
        n.g(windowView, "windowView");
        n.g(lifecycle, "lifecycle");
        n.g(bgHandler, "bgHandler");
        n.g(mainExecutor, "mainExecutor");
        n.g(secondaryPanelRouter, "secondaryPanelRouter");
        n.g(deviceControlsPresenter, "deviceControlsPresenter");
        n.g(mainPanelContentDistributor, "mainPanelContentDistributor");
        n.g(mainPanelStyleController, "mainPanelStyleController");
        n.g(mainPanelModeController, "mainPanelModeController");
        n.g(miLinkController, "miLinkController");
        n.g(hapticFeedback, "hapticFeedback");
        n.g(activityStarter, "activityStarter");
        n.g(windowViewController, "windowViewController");
        n.g(modeController, "modeController");
        this.bgHandler = bgHandler;
        this.mainExecutor = mainExecutor;
        this.secondaryPanelRouter = secondaryPanelRouter;
        this.deviceControlsPresenter = deviceControlsPresenter;
        this.mainPanelContentDistributor = mainPanelContentDistributor;
        this.mainPanelStyleController = mainPanelStyleController;
        this.mainPanelModeController = mainPanelModeController;
        this.miLinkController = miLinkController;
        this.hapticFeedback = hapticFeedback;
        this.activityStarter = activityStarter;
        this.windowViewController = windowViewController;
        this.modeController = modeController;
        this.currentDCEntryInfo = new DCEntryInfo(false);
        this.availableInKeyguard = true;
        this.availableOfSettings = true;
        Object systemService = getContext().getSystemService("keyguard");
        n.e(systemService, "null cannot be cast to non-null type android.app.KeyguardManager");
        KeyguardManager keyguardManager = (KeyguardManager) systemService;
        this.keyguardManager = keyguardManager;
        this.inKeyguard = keyguardManager.isKeyguardLocked();
        this.dcEntryInfoCallback = new Consumer() { // from class: miui.systemui.controlcenter.panel.main.devicecontrol.a
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                DeviceControlsEntryController.dcEntryInfoCallback$lambda$0(this.f5388a, (DCEntryInfo) obj);
            }
        };
        this.settingsObserver = new ContentObserver(bgHandler) { // from class: miui.systemui.controlcenter.panel.main.devicecontrol.DeviceControlsEntryController$settingsObserver$1
            @Override // android.database.ContentObserver
            public void onChange(boolean z2, Uri uri) {
                if (uri != null) {
                    DeviceControlsEntryController deviceControlsEntryController = this.this$0;
                    SmartDeviceUtils smartDeviceUtils = SmartDeviceUtils.INSTANCE;
                    if (n.c(uri, smartDeviceUtils.getURI_SMART_DEVICE_CONTROL())) {
                        deviceControlsEntryController.updateAvailableOfSettings();
                    } else if (n.c(uri, smartDeviceUtils.getURI_LOCKSCREEN_SMART_DEVICE_CONTROL())) {
                        deviceControlsEntryController.updateAvailableInKeyguard();
                    }
                }
            }
        };
        this.keyguardLockedStateListener = new KeyguardManager.KeyguardLockedStateListener() { // from class: miui.systemui.controlcenter.panel.main.devicecontrol.b
            @Override // android.app.KeyguardManager.KeyguardLockedStateListener
            public final void onKeyguardLockedStateChanged(boolean z2) {
                DeviceControlsEntryController.keyguardLockedStateListener$lambda$2(this.f5389a, z2);
            }
        };
        this.miLinkAvailableCallback = new MiLinkController.Callback() { // from class: miui.systemui.controlcenter.panel.main.devicecontrol.DeviceControlsEntryController$miLinkAvailableCallback$1
            @Override // miui.systemui.util.MiLinkController.Callback
            public void onChanged(boolean z2) {
                this.this$0.updateAvailableOfSettings();
            }
        };
        this.onStyleChangedListener = new MainPanelStyleController.OnStyleChangedListener() { // from class: miui.systemui.controlcenter.panel.main.devicecontrol.DeviceControlsEntryController$onStyleChangedListener$1
            @Override // miui.systemui.controlcenter.panel.main.MainPanelStyleController.OnStyleChangedListener
            public void onStyleChanged() {
                DeviceControlsEntryController deviceControlsEntryController = this.this$0;
                deviceControlsEntryController.setDeviceControlListening(deviceControlsEntryController.isDeviceControlListened());
            }
        };
        this.listItems = m.f(this);
        this.priority = 51;
        this.type = TYPE_DEVICE_CONTROLS;
        this.spanSize = 4;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void _set_deviceControlListening_$lambda$1(Function1 tmp0, Object obj) {
        n.g(tmp0, "$tmp0");
        tmp0.invoke(obj);
    }

    private final boolean checkAvailable() {
        return available(!CommonUtils.INSTANCE.getInVerticalMode(getContext()));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void dcEntryInfoCallback$lambda$0(DeviceControlsEntryController this$0, DCEntryInfo dCEntryInfo) {
        n.g(this$0, "this$0");
        n.d(dCEntryInfo);
        this$0.updateInfo(dCEntryInfo);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final boolean isDeviceControlListened() {
        if (((ControlCenterWindowViewController) this.windowViewController.get()).getSuperPowerMode() || ((ControlCenterWindowViewController) this.windowViewController.get()).getCurrentUserId() != 0 || ControlsUtils.INSTANCE.isFeatureSupport(getContext())) {
            return false;
        }
        Log.i(TAG, "isDeviceControlListened " + this.availableOfSettings + " " + this.availableInKeyguard + " " + this.inKeyguard + " ");
        if (((MainPanelStyleController) this.mainPanelStyleController.get()).getStyle() == MainPanelController.Style.COMPACT || ((MainPanelModeController) this.mainPanelModeController.get()).getMode() == MainPanelController.Mode.EDIT || !this.availableOfSettings) {
            return false;
        }
        return this.availableInKeyguard || !this.inKeyguard;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void keyguardLockedStateListener$lambda$2(DeviceControlsEntryController this$0, boolean z2) {
        n.g(this$0, "this$0");
        Log.d(TAG, "onKeyguardLockedStateChanged: " + z2);
        if (z2 != this$0.inKeyguard) {
            this$0.inKeyguard = z2;
            this$0.setDeviceControlListening(this$0.isDeviceControlListened());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean onBindViewHolder$lambda$8(DeviceControlsEntryController this$0, View view) {
        n.g(this$0, "this$0");
        this$0.hapticFeedback.longClick();
        this$0.activityStarter.postStartActivityDismissingKeyguard(SmartDeviceUtils.INSTANCE.getDeviceCardIntent(2, this$0.getContext()), 0);
        if (CommonUtils.isTinyScreen(this$0.getContext())) {
            return true;
        }
        ControlCenterEventTracker.Companion.trackSmartHomeLongClickEvent(EventTracker.Companion.getScreenType(this$0.getContext()), this$0.getContext().getResources().getConfiguration().orientation, false);
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onStartOnce$lambda$4(Function1 tmp0, Object obj) {
        n.g(tmp0, "$tmp0");
        tmp0.invoke(obj);
    }

    /* JADX WARN: Multi-variable type inference failed */
    private final void postNotifyChanged(boolean z2) {
        if (z2 || ((ControlCenterWindowViewImpl) getView()).getLifecycle().getCurrentState() != Lifecycle.State.STARTED) {
            this.mainExecutor.execute(new Runnable() { // from class: miui.systemui.controlcenter.panel.main.devicecontrol.c
                @Override // java.lang.Runnable
                public final void run() {
                    DeviceControlsEntryController.postNotifyChanged$lambda$6(this.f5390a);
                }
            });
        } else {
            this.pendingUpdate = true;
        }
    }

    public static /* synthetic */ void postNotifyChanged$default(DeviceControlsEntryController deviceControlsEntryController, boolean z2, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            z2 = false;
        }
        deviceControlsEntryController.postNotifyChanged(z2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Multi-variable type inference failed */
    public static final void postNotifyChanged$lambda$6(DeviceControlsEntryController this$0) {
        n.g(this$0, "this$0");
        if (((ControlCenterWindowViewImpl) this$0.getView()).getLifecycle().getCurrentState().isAtLeast(Lifecycle.State.RESUMED)) {
            this$0.updateItemAnimatorSuppress();
        }
        MainPanelContentDistributor mainPanelContentDistributor = (MainPanelContentDistributor) this$0.mainPanelContentDistributor.get();
        n.d(mainPanelContentDistributor);
        MainPanelContentDistributor.distributePanels$default(mainPanelContentDistributor, false, 1, null);
        mainPanelContentDistributor.handleNotifyChanged(false);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void setDeviceControlListening(boolean z2) {
        if (this.deviceControlListening == z2) {
            return;
        }
        this.deviceControlListening = z2;
        Log.i(TAG, z2 + " " + this.availableOfSettings + " " + this.availableInKeyguard + " " + this.inKeyguard + " " + this.currentDCEntryInfo.getAvailable());
        postNotifyChanged$default(this, false, 1, null);
        Optional<DeviceControlsPresenter> optional = this.deviceControlsPresenter;
        final DeviceControlsEntryController$deviceControlListening$1 deviceControlsEntryController$deviceControlListening$1 = new DeviceControlsEntryController$deviceControlListening$1(z2, this);
        optional.ifPresent(new Consumer() { // from class: miui.systemui.controlcenter.panel.main.devicecontrol.g
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                DeviceControlsEntryController._set_deviceControlListening_$lambda$1(deviceControlsEntryController$deviceControlListening$1, obj);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void updateAvailableInKeyguard() {
        boolean zIsLockScreenSmartDeviceControlVisible = SmartDeviceUtils.INSTANCE.isLockScreenSmartDeviceControlVisible(getContext(), true);
        if (zIsLockScreenSmartDeviceControlVisible != this.availableInKeyguard) {
            this.availableInKeyguard = zIsLockScreenSmartDeviceControlVisible;
            setDeviceControlListening(isDeviceControlListened());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void updateAvailableOfSettings() {
        boolean zIsSmartDeviceControlVisible = SmartDeviceUtils.INSTANCE.isSmartDeviceControlVisible(getContext(), true);
        if (zIsSmartDeviceControlVisible != this.availableOfSettings) {
            this.availableOfSettings = zIsSmartDeviceControlVisible;
            setDeviceControlListening(isDeviceControlListened());
        }
    }

    private final void updateInfo(DCEntryInfo dCEntryInfo) {
        final boolean z2 = this.currentDCEntryInfo.getAvailable() != dCEntryInfo.getAvailable();
        boolean zC = n.c(this.currentDCEntryInfo.getComponentName(), dCEntryInfo.getComponentName());
        boolean zC2 = n.c(this.currentDCEntryInfo.getAppLabel(), dCEntryInfo.getAppLabel());
        boolean zC3 = n.c(this.currentDCEntryInfo.getAppIcon(), dCEntryInfo.getAppIcon());
        if (!z2 && zC && zC2 && zC3) {
            return;
        }
        this.currentDCEntryInfo = dCEntryInfo;
        this.mainExecutor.execute(new Runnable() { // from class: miui.systemui.controlcenter.panel.main.devicecontrol.e
            @Override // java.lang.Runnable
            public final void run() {
                DeviceControlsEntryController.updateInfo$lambda$7(z2, this);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void updateInfo$lambda$7(boolean z2, DeviceControlsEntryController this$0) {
        n.g(this$0, "this$0");
        if (z2) {
            postNotifyChanged$default(this$0, false, 1, null);
        }
        this$0.onBindViewHolder();
    }

    private final void updateItemAnimatorSuppress() {
        ((MainPanelContentDistributor) this.mainPanelContentDistributor.get()).getLeftAdapter().getItemAnimator().setSuppressAnimation(false);
        ((MainPanelContentDistributor) this.mainPanelContentDistributor.get()).getRightAdapter().getItemAnimator().setSuppressAnimation(false);
    }

    @Override // miui.systemui.controlcenter.panel.main.MainPanelContent
    public boolean available(boolean z2) {
        Log.i(TAG, "available " + this.availableOfSettings + " " + this.availableInKeyguard + " " + this.inKeyguard + " " + this.currentDCEntryInfo.getAvailable());
        return isDeviceControlListened() && this.currentDCEntryInfo.getAvailable();
    }

    @Override // miui.systemui.controlcenter.panel.main.MainPanelContent
    public MainPanelItemViewHolder createViewHolder(ViewGroup parent, int i2) {
        n.g(parent, "parent");
        if (i2 != 2668765) {
            return null;
        }
        DeviceControlsEntryBinding deviceControlsEntryBindingInflate = DeviceControlsEntryBinding.inflate(LayoutInflater.from(getContext()), parent, false);
        n.f(deviceControlsEntryBindingInflate, "inflate(...)");
        return new DeviceControlEntryViewHolder(deviceControlsEntryBindingInflate);
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
    public void onBindViewHolder() {
        View view;
        View view2;
        MainPanelItemViewHolder holder = getHolder();
        DeviceControlEntryViewHolder deviceControlEntryViewHolder = holder instanceof DeviceControlEntryViewHolder ? (DeviceControlEntryViewHolder) holder : null;
        if (deviceControlEntryViewHolder != null) {
            deviceControlEntryViewHolder.attachToDCEntryInfo(this.currentDCEntryInfo);
        }
        MainPanelItemViewHolder holder2 = getHolder();
        if (holder2 != null && (view2 = holder2.itemView) != null) {
            OnClickListenerEx.INSTANCE.setOnClickListenerEx(view2, new AnonymousClass1());
        }
        MainPanelItemViewHolder holder3 = getHolder();
        if (holder3 == null || (view = holder3.itemView) == null) {
            return;
        }
        view.setOnLongClickListener(new View.OnLongClickListener() { // from class: miui.systemui.controlcenter.panel.main.devicecontrol.f
            @Override // android.view.View.OnLongClickListener
            public final boolean onLongClick(View view3) {
                return DeviceControlsEntryController.onBindViewHolder$lambda$8(this.f5394a, view3);
            }
        });
    }

    @Override // miui.systemui.controlcenter.utils.ControlCenterViewController, miui.systemui.controlcenter.ConfigUtils.OnConfigChangeListener
    public void onConfigurationChanged(int i2) {
        if (ConfigUtils.INSTANCE.colorsChanged(i2)) {
            MainPanelItemViewHolder holder = getHolder();
            DeviceControlEntryViewHolder deviceControlEntryViewHolder = holder instanceof DeviceControlEntryViewHolder ? (DeviceControlEntryViewHolder) holder : null;
            if (deviceControlEntryViewHolder != null) {
                deviceControlEntryViewHolder.attachToDCEntryInfo(this.currentDCEntryInfo);
            }
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
        this.miLinkController.addCallback(this.miLinkAvailableCallback);
        ((MainPanelStyleController) this.mainPanelStyleController.get()).addOnStyleChangedListener(this.onStyleChangedListener);
        updateAvailableOfSettings();
        updateAvailableInKeyguard();
        setDeviceControlListening(isDeviceControlListened());
    }

    @Override // miui.systemui.controlcenter.utils.ControlCenterViewController
    public void onDestroy() {
        super.onDestroy();
        getContext().getContentResolver().unregisterContentObserver(this.settingsObserver);
        this.keyguardManager.removeKeyguardLockedStateListener(this.keyguardLockedStateListener);
        this.miLinkController.removeCallback(this.miLinkAvailableCallback);
        ((MainPanelStyleController) this.mainPanelStyleController.get()).removeOnStyleChangedListener(this.onStyleChangedListener);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // miui.systemui.controlcenter.utils.ControlCenterViewController
    public void onResume() {
        if (this.pendingUpdate) {
            postNotifyChanged(true);
            this.pendingUpdate = false;
        }
        ((ControlCenterWindowViewImpl) getView()).getParent();
    }

    @Override // miui.systemui.controlcenter.utils.ControlCenterViewController
    public void onStartOnce() {
        super.onStartOnce();
        Optional<DeviceControlsPresenter> optional = this.deviceControlsPresenter;
        final C04831 c04831 = new C04831();
        optional.ifPresent(new Consumer() { // from class: miui.systemui.controlcenter.panel.main.devicecontrol.d
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                DeviceControlsEntryController.onStartOnce$lambda$4(c04831, obj);
            }
        });
    }

    @Override // miui.systemui.controlcenter.utils.ControlCenterViewController
    public void onSuperPowerModeChanged(boolean z2) {
        setDeviceControlListening(isDeviceControlListened());
    }

    @Override // miui.systemui.controlcenter.panel.main.recyclerview.MainPanelListItem
    public void onUnbindViewHolder() {
        View view;
        MainPanelItemViewHolder holder = getHolder();
        if (holder == null || (view = holder.itemView) == null) {
            return;
        }
        view.setOnClickListener(null);
    }

    @Override // miui.systemui.controlcenter.utils.ControlCenterViewController
    public void onUserSwitched(int i2) {
        setDeviceControlListening(isDeviceControlListened());
    }

    @Override // miui.systemui.controlcenter.panel.main.MainPanelContent
    public ArrayList<DeviceControlsEntryController> getListItems() {
        return this.listItems;
    }
}
