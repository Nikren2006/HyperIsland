package miui.systemui.controlcenter.windowview;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Point;
import android.hardware.display.DisplayManager;
import android.os.Handler;
import android.os.UserHandle;
import android.os.UserManager;
import android.util.Log;
import android.util.Pair;
import android.view.ContextThemeWrapper;
import android.view.Display;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.WindowInsets;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleCoroutineScope;
import androidx.lifecycle.LifecycleEventObserver;
import androidx.lifecycle.LifecycleOwner;
import com.android.systemui.plugins.miui.controlcenter.ControlCenterContent;
import com.android.systemui.plugins.miui.dump.Dumpable;
import com.android.systemui.plugins.miui.dump.PluginDumpManager;
import com.android.systemui.plugins.miui.settings.IUserTracker;
import com.android.systemui.plugins.miui.settings.SuperSaveModeController;
import com.android.systemui.plugins.statusbar.StatusBarStateController;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.Executor;
import java.util.function.Consumer;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import miui.systemui.autodensity.AutoDensityController;
import miui.systemui.broadcast.BroadcastDispatcher;
import miui.systemui.controlcenter.ConfigUtils;
import miui.systemui.controlcenter.R;
import miui.systemui.controlcenter.dagger.ControlCenterScope;
import miui.systemui.controlcenter.dagger.qualifiers.Qualifiers;
import miui.systemui.controlcenter.databinding.ControlCenterBinding;
import miui.systemui.controlcenter.events.ControlCenterEventTracker;
import miui.systemui.controlcenter.panel.SecondaryPanelRouter;
import miui.systemui.controlcenter.panel.main.MainPanelController;
import miui.systemui.controlcenter.panel.secondary.brightness.BrightnessPanelController;
import miui.systemui.controlcenter.panel.secondary.media.MediaPanelController;
import miui.systemui.controlcenter.panel.secondary.volume.VolumePanelController;
import miui.systemui.controlcenter.utils.ControlCenterUtils;
import miui.systemui.controlcenter.utils.ControlCenterViewController;
import miui.systemui.controlcenter.windowview.ControlCenterScreenshot;
import miui.systemui.dagger.qualifiers.Background;
import miui.systemui.dagger.qualifiers.Main;
import miui.systemui.util.BlurUtilsExt;
import miui.systemui.util.CommonUtils;
import miui.systemui.util.DeviceStateManagerCompat;
import miui.systemui.util.FlipUtils;
import miui.systemui.util.ThemeUtils;
import miui.systemui.util.ViewController;
import miuix.autodensity.DensityConfig;
import miuix.autodensity.DensityConfigManager;
import miuix.autodensity.DensityUtil;
import miuix.os.DeviceHelper;
import systemui.plugin.eventtracking.EventTracker;

/* JADX INFO: loaded from: classes.dex */
@ControlCenterScope
public final class ControlCenterWindowViewController extends ViewController<ControlCenterWindowViewImpl> implements ControlCenterContent, DisplayManager.DisplayListener, AutoDensityController.OnDensityChangeListener, Dumpable {
    public static final Companion Companion = new Companion(null);
    private static final int FOLD_SECOND_DISPLAY = 1;
    public static final boolean LOW_GPU = false;
    private static final int REAR_DISPLAY = 2;
    public static final int STATE_UNAVAILABLE = -1;
    private static final String TAG = "ControlCenterWindowViewController";
    public static final int VELOCITY_UNIT = 1000;
    private final AutoDensityController autoDensityController;
    private final Executor bgExecutor;
    private final ControlCenterBinding binding;
    private final BlurController blurController;
    private float blurRatio;
    private final BlurUtilsExt blurUtilsExt;
    private final Function0 boostSystemUIRunnable;
    private final BrightnessPanelController brightnessPanelController;
    private final BroadcastDispatcher broadcastDispatcher;
    private final ArrayList<ControlCenterViewController<?>> childControllers;
    private boolean clipFooter;
    private boolean clipHeader;
    private Configuration configuration;
    private final ControlCenterWindowViewImpl contentView;
    private final ControlCenterEventTracker controlCenterEventTracker;
    private final Object deviceStateManager;
    private final DisplayManager displayManager;
    private final PluginDumpManager dumpManager;
    private final ControlCenterExpandController expandController;
    private final ArrayList<ControlCenterContent.OnExpandChangedListener> expandListeners;
    private float expandProgress;
    private float expansion;
    private final ArrayList<DeviceStateManagerCompat.FoldStateCallback> foldStateCallbacks;
    private final Object foldStateListener;
    private final GestureDispatcher gestureDispatcher;
    private boolean isPanelVisible;
    private final Lifecycle lifecycle;
    private final LifecycleEventObserver lifecycleObserver;
    private final Handler mainHandler;
    private final MainPanelController mainPanelController;
    private float maxVelocity;
    private final MediaPanelController mediaPanelController;
    private final ControlCenterWindowViewController$onScreenshotListener$1 onScreenshotListener;
    private int rawScreenHeight;
    private int rawScreenWidth;
    private final LifecycleCoroutineScope scope;
    private int screenHeight;
    private int screenWidth;
    private final ControlCenterScreenshot screenshot;
    private final SecondaryPanelRouter secondaryPanelRouter;
    private final StatusBarStateController statusBarStateController;
    private final ControlCenterSuperPowerController superPowerController;
    private boolean superPowerMode;
    private final SuperSaveModeController superSaveModeController;
    private final ControlCenterWindowViewController$superSaveModeListener$1 superSaveModeListener;
    private int touchSlop;
    private final Executor uiExecutor;
    private final ControlCenterWindowViewController$userListener$1 userListener;
    private final UserManager userManager;
    private final IUserTracker userTracker;
    private final ControlCenterWindowViewController$userUnlockReceiver$1 userUnlockReceiver;
    private boolean userUnlocked;
    private final ArrayList<OnUserUnlockedListener> userUnlockedListeners;
    private final VolumePanelController volumePanelController;

    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }

    public interface OnUserUnlockedListener {
        void onUserUnlocked();
    }

    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;
        public static final /* synthetic */ int[] $EnumSwitchMapping$1;

        static {
            int[] iArr = new int[Lifecycle.Event.values().length];
            try {
                iArr[Lifecycle.Event.ON_START.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[Lifecycle.Event.ON_RESUME.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                iArr[Lifecycle.Event.ON_PAUSE.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                iArr[Lifecycle.Event.ON_STOP.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                iArr[Lifecycle.Event.ON_DESTROY.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            $EnumSwitchMapping$0 = iArr;
            int[] iArr2 = new int[Lifecycle.State.values().length];
            try {
                iArr2[Lifecycle.State.STARTED.ordinal()] = 1;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                iArr2[Lifecycle.State.RESUMED.ordinal()] = 2;
            } catch (NoSuchFieldError unused7) {
            }
            $EnumSwitchMapping$1 = iArr2;
        }
    }

    /* JADX WARN: Illegal instructions before constructor call */
    /* JADX WARN: Type inference failed for: r0v49, types: [miui.systemui.controlcenter.windowview.ControlCenterWindowViewController$onScreenshotListener$1] */
    /* JADX WARN: Type inference failed for: r0v52, types: [miui.systemui.controlcenter.windowview.ControlCenterWindowViewController$userListener$1] */
    public ControlCenterWindowViewController(ControlCenterBinding binding, @Qualifiers.ControlCenter Lifecycle lifecycle, BroadcastDispatcher broadcastDispatcher, @Background Executor bgExecutor, @Main Handler mainHandler, @Main Executor uiExecutor, @Qualifiers.ControlCenter LifecycleCoroutineScope scope, ControlCenterScreenshot screenshot, ControlCenterExpandController expandController, MainPanelController mainPanelController, BlurController blurController, GestureDispatcher gestureDispatcher, SecondaryPanelRouter secondaryPanelRouter, UserManager userManager, StatusBarStateController statusBarStateController, AutoDensityController autoDensityController, ControlCenterEventTracker controlCenterEventTracker, MediaPanelController mediaPanelController, BrightnessPanelController brightnessPanelController, VolumePanelController volumePanelController, IUserTracker userTracker, SuperSaveModeController superSaveModeController, PluginDumpManager dumpManager, ControlCenterSuperPowerController superPowerController, BlurUtilsExt blurUtilsExt) {
        kotlin.jvm.internal.n.g(binding, "binding");
        kotlin.jvm.internal.n.g(lifecycle, "lifecycle");
        kotlin.jvm.internal.n.g(broadcastDispatcher, "broadcastDispatcher");
        kotlin.jvm.internal.n.g(bgExecutor, "bgExecutor");
        kotlin.jvm.internal.n.g(mainHandler, "mainHandler");
        kotlin.jvm.internal.n.g(uiExecutor, "uiExecutor");
        kotlin.jvm.internal.n.g(scope, "scope");
        kotlin.jvm.internal.n.g(screenshot, "screenshot");
        kotlin.jvm.internal.n.g(expandController, "expandController");
        kotlin.jvm.internal.n.g(mainPanelController, "mainPanelController");
        kotlin.jvm.internal.n.g(blurController, "blurController");
        kotlin.jvm.internal.n.g(gestureDispatcher, "gestureDispatcher");
        kotlin.jvm.internal.n.g(secondaryPanelRouter, "secondaryPanelRouter");
        kotlin.jvm.internal.n.g(userManager, "userManager");
        kotlin.jvm.internal.n.g(statusBarStateController, "statusBarStateController");
        kotlin.jvm.internal.n.g(autoDensityController, "autoDensityController");
        kotlin.jvm.internal.n.g(controlCenterEventTracker, "controlCenterEventTracker");
        kotlin.jvm.internal.n.g(mediaPanelController, "mediaPanelController");
        kotlin.jvm.internal.n.g(brightnessPanelController, "brightnessPanelController");
        kotlin.jvm.internal.n.g(volumePanelController, "volumePanelController");
        kotlin.jvm.internal.n.g(userTracker, "userTracker");
        kotlin.jvm.internal.n.g(superSaveModeController, "superSaveModeController");
        kotlin.jvm.internal.n.g(dumpManager, "dumpManager");
        kotlin.jvm.internal.n.g(superPowerController, "superPowerController");
        kotlin.jvm.internal.n.g(blurUtilsExt, "blurUtilsExt");
        ControlCenterWindowViewImpl root = binding.getRoot();
        kotlin.jvm.internal.n.f(root, "getRoot(...)");
        super(root);
        this.binding = binding;
        this.lifecycle = lifecycle;
        this.broadcastDispatcher = broadcastDispatcher;
        this.bgExecutor = bgExecutor;
        this.mainHandler = mainHandler;
        this.uiExecutor = uiExecutor;
        this.scope = scope;
        this.screenshot = screenshot;
        this.expandController = expandController;
        this.mainPanelController = mainPanelController;
        this.blurController = blurController;
        this.gestureDispatcher = gestureDispatcher;
        this.secondaryPanelRouter = secondaryPanelRouter;
        this.userManager = userManager;
        this.statusBarStateController = statusBarStateController;
        this.autoDensityController = autoDensityController;
        this.controlCenterEventTracker = controlCenterEventTracker;
        this.mediaPanelController = mediaPanelController;
        this.brightnessPanelController = brightnessPanelController;
        this.volumePanelController = volumePanelController;
        this.userTracker = userTracker;
        this.superSaveModeController = superSaveModeController;
        this.dumpManager = dumpManager;
        this.superPowerController = superPowerController;
        this.blurUtilsExt = blurUtilsExt;
        this.contentView = getView();
        this.childControllers = I0.m.f(controlCenterEventTracker, screenshot, getExpandController(), mainPanelController, blurController, gestureDispatcher, secondaryPanelRouter, mediaPanelController, brightnessPanelController, volumePanelController, superPowerController);
        this.expandListeners = new ArrayList<>();
        this.userUnlockedListeners = new ArrayList<>();
        this.displayManager = (DisplayManager) getContext().getSystemService(DisplayManager.class);
        this.lifecycleObserver = new LifecycleEventObserver() { // from class: miui.systemui.controlcenter.windowview.n
            @Override // androidx.lifecycle.LifecycleEventObserver
            public final void onStateChanged(LifecycleOwner lifecycleOwner, Lifecycle.Event event) {
                ControlCenterWindowViewController.lifecycleObserver$lambda$21(this.f5507a, lifecycleOwner, event);
            }
        };
        this.boostSystemUIRunnable = ControlCenterWindowViewController$boostSystemUIRunnable$1.INSTANCE;
        this.onScreenshotListener = new ControlCenterScreenshot.OnScreenshotListener() { // from class: miui.systemui.controlcenter.windowview.ControlCenterWindowViewController$onScreenshotListener$1
            @Override // miui.systemui.controlcenter.windowview.ControlCenterScreenshot.OnScreenshotListener
            public void onScreenshot() {
                ControlCenterScreenshot screenshot2 = this.this$0.getScreenshot();
                ControlCenterWindowViewController controlCenterWindowViewController = this.this$0;
                screenshot2.addDumpMessage("lifecycleState", controlCenterWindowViewController.lifecycle.getCurrentState().toString());
                screenshot2.addDumpMessage("windowViewVisibility", String.valueOf(controlCenterWindowViewController.getView().getVisibility()));
            }
        };
        this.userUnlockReceiver = new ControlCenterWindowViewController$userUnlockReceiver$1(this);
        this.superSaveModeListener = new ControlCenterWindowViewController$superSaveModeListener$1(this);
        this.userListener = new IUserTracker.Callback() { // from class: miui.systemui.controlcenter.windowview.ControlCenterWindowViewController$userListener$1
            public void onUserChanged(int i2, Context userContext) {
                kotlin.jvm.internal.n.g(userContext, "userContext");
                if (this.this$0.getInited()) {
                    this.this$0.autoDensityController.onUserChanged(i2, userContext);
                    Iterator it = this.this$0.childControllers.iterator();
                    while (it.hasNext()) {
                        ((ControlCenterViewController) it.next()).dispatchUserSwitched(i2);
                    }
                }
            }
        };
        DeviceStateManagerCompat deviceStateManagerCompat = DeviceStateManagerCompat.INSTANCE;
        this.deviceStateManager = deviceStateManagerCompat.getDeviceStateManagerInstance();
        this.foldStateCallbacks = new ArrayList<>();
        this.foldStateListener = deviceStateManagerCompat.getFoldStateListenerInstance(getContext(), new Consumer() { // from class: miui.systemui.controlcenter.windowview.e
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                ControlCenterWindowViewController.foldStateListener$lambda$23(this.f5498a, (Boolean) obj);
            }
        });
    }

    private final void checkUserUnlocked() {
        if (this.userUnlocked) {
            return;
        }
        this.bgExecutor.execute(new Runnable() { // from class: miui.systemui.controlcenter.windowview.l
            @Override // java.lang.Runnable
            public final void run() {
                ControlCenterWindowViewController.checkUserUnlocked$lambda$29(this.f5505a);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void checkUserUnlocked$lambda$29(final ControlCenterWindowViewController this$0) {
        kotlin.jvm.internal.n.g(this$0, "this$0");
        if (this$0.userManager.isUserUnlocked()) {
            this$0.mainHandler.post(new Runnable() { // from class: miui.systemui.controlcenter.windowview.m
                @Override // java.lang.Runnable
                public final void run() {
                    ControlCenterWindowViewController.checkUserUnlocked$lambda$29$lambda$28(this.f5506a);
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void checkUserUnlocked$lambda$29$lambda$28(ControlCenterWindowViewController this$0) {
        kotlin.jvm.internal.n.g(this$0, "this$0");
        this$0.userUnlocked = true;
        Iterator<T> it = this$0.userUnlockedListeners.iterator();
        while (it.hasNext()) {
            ((OnUserUnlockedListener) it.next()).onUserUnlocked();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void foldStateListener$lambda$23(ControlCenterWindowViewController this$0, Boolean folded) {
        kotlin.jvm.internal.n.g(this$0, "this$0");
        kotlin.jvm.internal.n.g(folded, "folded");
        Log.d(TAG, "onFoldStateChanged: " + folded);
        FlipUtils.INSTANCE.updateFlipOutInsetRight(this$0.getContext());
        ControlCenterExpandController.hidePanel$default(this$0.getExpandController(), false, false, 2, null);
        if (this$0.foldStateCallbacks.isEmpty()) {
            return;
        }
        Iterator<T> it = this$0.foldStateCallbacks.iterator();
        while (it.hasNext()) {
            ((DeviceStateManagerCompat.FoldStateCallback) it.next()).onFoldStateChanged(folded.booleanValue());
        }
    }

    private final int getThemeRes() {
        return ControlCenterUtils.getBackgroundBlurOpenedInDefaultTheme(getContext()) ? R.style.ControlCenter_Blend : R.style.ControlCenter;
    }

    private final ControlCenterContent.ExpandState getToExpandState(Lifecycle.State state) {
        int i2 = WhenMappings.$EnumSwitchMapping$1[state.ordinal()];
        return i2 != 1 ? i2 != 2 ? ControlCenterContent.ExpandState.COLLAPSED : ControlCenterContent.ExpandState.EXPANDED : ControlCenterContent.ExpandState.EXPANDING;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void lifecycleObserver$lambda$21(final ControlCenterWindowViewController this$0, LifecycleOwner lifecycleOwner, Lifecycle.Event event) {
        kotlin.jvm.internal.n.g(this$0, "this$0");
        kotlin.jvm.internal.n.g(lifecycleOwner, "<anonymous parameter 0>");
        kotlin.jvm.internal.n.g(event, "event");
        int i2 = WhenMappings.$EnumSwitchMapping$0[event.ordinal()];
        if (i2 == 1) {
            this$0.boostSystemUIRunnable.invoke();
            this$0.getView().suppressLayout(false);
            Iterator<T> it = this$0.childControllers.iterator();
            while (it.hasNext()) {
                ((ControlCenterViewController) it.next()).dispatchStart();
            }
            ControlCenterWindowViewImpl view = this$0.getView();
            final Function0 function0 = this$0.boostSystemUIRunnable;
            view.removeCallbacks(new Runnable() { // from class: miui.systemui.controlcenter.windowview.d
                @Override // java.lang.Runnable
                public final void run() {
                    ControlCenterWindowViewController.lifecycleObserver$lambda$21$lambda$6(function0);
                }
            });
            ControlCenterWindowViewImpl view2 = this$0.getView();
            final Function0 function02 = this$0.boostSystemUIRunnable;
            view2.postDelayed(new Runnable() { // from class: miui.systemui.controlcenter.windowview.f
                @Override // java.lang.Runnable
                public final void run() {
                    ControlCenterWindowViewController.lifecycleObserver$lambda$21$lambda$7(function02);
                }
            }, 1000L);
            Iterator<T> it2 = this$0.expandListeners.iterator();
            while (it2.hasNext()) {
                ((ControlCenterContent.OnExpandChangedListener) it2.next()).onExpandStateChanged(this$0.getToExpandState(event.getTargetState()));
            }
            this$0.checkUserUnlocked();
            return;
        }
        if (i2 == 2) {
            Iterator<T> it3 = this$0.childControllers.iterator();
            while (it3.hasNext()) {
                ((ControlCenterViewController) it3.next()).dispatchResume();
            }
            ControlCenterWindowViewImpl view3 = this$0.getView();
            final Function0 function03 = this$0.boostSystemUIRunnable;
            view3.removeCallbacks(new Runnable() { // from class: miui.systemui.controlcenter.windowview.g
                @Override // java.lang.Runnable
                public final void run() {
                    ControlCenterWindowViewController.lifecycleObserver$lambda$21$lambda$10(function03);
                }
            });
            Iterator<T> it4 = this$0.expandListeners.iterator();
            while (it4.hasNext()) {
                ((ControlCenterContent.OnExpandChangedListener) it4.next()).onExpandStateChanged(this$0.getToExpandState(event.getTargetState()));
            }
            if (!this$0.isPanelVisible) {
                if (this$0.statusBarStateController.getState() != 0) {
                    ControlCenterEventTracker.Companion.trackControlCenterOpenEvent(EventTracker.Companion.getScreenType(this$0.getContext()), this$0.getContext().getResources().getConfiguration().orientation, ControlCenterEventTracker.KEYGURAD_EXPAND);
                }
                this$0.isPanelVisible = true;
            }
            this$0.bgExecutor.execute(new Runnable() { // from class: miui.systemui.controlcenter.windowview.h
                @Override // java.lang.Runnable
                public final void run() {
                    ControlCenterWindowViewController.lifecycleObserver$lambda$21$lambda$12(this.f5501a);
                }
            });
            return;
        }
        if (i2 == 3) {
            Iterator<T> it5 = this$0.expandListeners.iterator();
            while (it5.hasNext()) {
                ((ControlCenterContent.OnExpandChangedListener) it5.next()).onExpandStateChanged(this$0.getToExpandState(event.getTargetState()));
            }
            Iterator<T> it6 = this$0.childControllers.iterator();
            while (it6.hasNext()) {
                ((ControlCenterViewController) it6.next()).dispatchPause();
            }
            return;
        }
        if (i2 != 4) {
            if (i2 != 5) {
                return;
            }
            Iterator<T> it7 = this$0.childControllers.iterator();
            while (it7.hasNext()) {
                ((ControlCenterViewController) it7.next()).dispatchDestroy();
            }
            this$0.isPanelVisible = false;
            Iterator<T> it8 = this$0.expandListeners.iterator();
            while (it8.hasNext()) {
                ((ControlCenterContent.OnExpandChangedListener) it8.next()).onExpandStateChanged(this$0.getToExpandState(event.getTargetState()));
            }
            this$0.onDestroy();
            return;
        }
        Iterator<T> it9 = this$0.childControllers.iterator();
        while (it9.hasNext()) {
            ((ControlCenterViewController) it9.next()).dispatchStop();
        }
        this$0.getView().suppressLayout(true);
        ControlCenterWindowViewImpl view4 = this$0.getView();
        final Function0 function04 = this$0.boostSystemUIRunnable;
        view4.removeCallbacks(new Runnable() { // from class: miui.systemui.controlcenter.windowview.i
            @Override // java.lang.Runnable
            public final void run() {
                ControlCenterWindowViewController.lifecycleObserver$lambda$21$lambda$16(function04);
            }
        });
        Iterator<T> it10 = this$0.expandListeners.iterator();
        while (it10.hasNext()) {
            ((ControlCenterContent.OnExpandChangedListener) it10.next()).onExpandStateChanged(this$0.getToExpandState(event.getTargetState()));
        }
        this$0.isPanelVisible = false;
        this$0.bgExecutor.execute(new Runnable() { // from class: miui.systemui.controlcenter.windowview.j
            @Override // java.lang.Runnable
            public final void run() {
                ControlCenterWindowViewController.lifecycleObserver$lambda$21$lambda$18(this.f5503a);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void lifecycleObserver$lambda$21$lambda$10(Function0 tmp0) {
        kotlin.jvm.internal.n.g(tmp0, "$tmp0");
        tmp0.invoke();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void lifecycleObserver$lambda$21$lambda$12(ControlCenterWindowViewController this$0) {
        kotlin.jvm.internal.n.g(this$0, "this$0");
        this$0.sendStateChangedBroadcast(true);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void lifecycleObserver$lambda$21$lambda$16(Function0 tmp0) {
        kotlin.jvm.internal.n.g(tmp0, "$tmp0");
        tmp0.invoke();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void lifecycleObserver$lambda$21$lambda$18(ControlCenterWindowViewController this$0) {
        kotlin.jvm.internal.n.g(this$0, "this$0");
        this$0.sendStateChangedBroadcast(false);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void lifecycleObserver$lambda$21$lambda$6(Function0 tmp0) {
        kotlin.jvm.internal.n.g(tmp0, "$tmp0");
        tmp0.invoke();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void lifecycleObserver$lambda$21$lambda$7(Function0 tmp0) {
        kotlin.jvm.internal.n.g(tmp0, "$tmp0");
        tmp0.invoke();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onCreate$lambda$25(ControlCenterWindowViewController this$0, View view) {
        kotlin.jvm.internal.n.g(this$0, "this$0");
        if (this$0.lifecycle.getCurrentState().isAtLeast(Lifecycle.State.RESUMED)) {
            if (this$0.secondaryPanelRouter.getInMainPanel()) {
                this$0.mainPanelController.exit();
            } else {
                this$0.getExpandController().hidePanel(true, true);
            }
        }
    }

    @SuppressLint({"MissingPermission"})
    private final void sendStateChangedBroadcast(boolean z2) {
        getContext().sendBroadcastAsUser(new Intent("miui.systemui.controlcenter.STATE_CHANGED_ACTION").putExtra("expand", z2), UserHandle.OWNER, "miui.systemui.permission.CONTROL_CENTER_STATE");
    }

    public static /* synthetic */ void updateClip$default(ControlCenterWindowViewController controlCenterWindowViewController, boolean z2, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            z2 = controlCenterWindowViewController.secondaryPanelRouter.getInMainPanel();
        }
        controlCenterWindowViewController.updateClip(z2);
    }

    private final void updateClipFooter(boolean z2) {
        setClipFooter(((Boolean) this.mainPanelController.getClipFooter().getValue()).booleanValue() && z2);
    }

    public static /* synthetic */ void updateClipFooter$default(ControlCenterWindowViewController controlCenterWindowViewController, boolean z2, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            z2 = controlCenterWindowViewController.secondaryPanelRouter.getInMainPanel();
        }
        controlCenterWindowViewController.updateClipFooter(z2);
    }

    private final void updateClipHeader(boolean z2) {
        setClipHeader(((Boolean) this.mainPanelController.getClipHeader().getValue()).booleanValue() && z2);
    }

    public static /* synthetic */ void updateClipHeader$default(ControlCenterWindowViewController controlCenterWindowViewController, boolean z2, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            z2 = controlCenterWindowViewController.secondaryPanelRouter.getInMainPanel();
        }
        controlCenterWindowViewController.updateClipHeader(z2);
    }

    public static /* synthetic */ void updateCustomDensityIfNeeded$default(ControlCenterWindowViewController controlCenterWindowViewController, Configuration configuration, boolean z2, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            z2 = false;
        }
        controlCenterWindowViewController.updateCustomDensityIfNeeded(configuration, z2);
    }

    private final void updatePanelContentDescription() {
        getView().setAccessibilityPaneTitle(getResources().getString(R.string.qs_control_header_tiles_title));
    }

    private final void updateResources() {
        Configuration configuration = getContext().getResources().getConfiguration();
        if (configuration != null) {
            int i2 = configuration.screenLayout;
            CommonUtils commonUtils = CommonUtils.INSTANCE;
            commonUtils.setUSING_LARGE_SCREEN((i2 & 15) >= 3 && (!commonUtils.getIS_FOLD() || DeviceHelper.isWideScreen(getContext())));
            Log.i(CommonUtils.TAG, "using Large Screen: " + commonUtils.getUSING_LARGE_SCREEN());
        }
        ViewConfiguration viewConfiguration = ViewConfiguration.get(getContext());
        this.maxVelocity = viewConfiguration.getScaledMaximumFlingVelocity();
        this.touchSlop = viewConfiguration.getScaledTouchSlop();
        updateScreenHeight();
        updateThemeBackgroundVisibility();
    }

    private final void updateScreenHeight() {
        Display display = this.displayManager.getDisplay(0);
        if (display == null) {
            return;
        }
        Point point = new Point();
        display.getRealSize(point);
        this.rawScreenWidth = Math.min(point.x, point.y);
        this.rawScreenHeight = Math.max(point.x, point.y);
        this.screenWidth = point.x;
        this.screenHeight = point.y;
    }

    private final void updateThemeBackgroundVisibility() {
        this.binding.themeBackground.setVisibility((ThemeUtils.INSTANCE.getDefaultPluginTheme() || isCollapsed() || CommonUtils.isTinyScreen(getContext())) ? 8 : 0);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void updateThemeRes() {
        Context context = getContext();
        ContextThemeWrapper contextThemeWrapper = context instanceof ContextThemeWrapper ? (ContextThemeWrapper) context : null;
        if (contextThemeWrapper != null) {
            if (contextThemeWrapper.getThemeResId() != getThemeRes()) {
                contextThemeWrapper.setTheme((Resources.Theme) null);
                contextThemeWrapper.setTheme(getThemeRes());
            }
        }
    }

    public final void addFoldStateCallback(DeviceStateManagerCompat.FoldStateCallback callback) {
        kotlin.jvm.internal.n.g(callback, "callback");
        if (this.foldStateCallbacks.contains(callback)) {
            return;
        }
        this.foldStateCallbacks.add(callback);
    }

    public void addOnExpandStateChangedListener(ControlCenterContent.OnExpandChangedListener listener) {
        kotlin.jvm.internal.n.g(listener, "listener");
        if (this.expandListeners.contains(listener)) {
            return;
        }
        this.expandListeners.add(listener);
    }

    public final void addOnUserUnlockedListener(OnUserUnlockedListener listener) {
        kotlin.jvm.internal.n.g(listener, "listener");
        this.userUnlockedListeners.add(listener);
    }

    public void destroy() {
        Log.w(TAG, "destroy window view " + getView() + " caused by removed by window manager.");
        getView().destroy();
    }

    public final void dispatchDump(PrintWriter pw, String[] args) {
        kotlin.jvm.internal.n.g(pw, "pw");
        kotlin.jvm.internal.n.g(args, "args");
        Iterator<T> it = this.childControllers.iterator();
        while (it.hasNext()) {
            ((ControlCenterViewController) it.next()).dispatchDump(pw, args);
        }
    }

    public void dump(PrintWriter pw, String[] args) {
        kotlin.jvm.internal.n.g(pw, "pw");
        kotlin.jvm.internal.n.g(args, "args");
        pw.println("ControlCenterWindowView state:");
        pw.println("  lifecycleState=" + this.lifecycle.getCurrentState());
        pw.println("  blockTouch=" + getView().getBlockTouch$miui_controlcenter_release());
        pw.println("  ignoreExternalMotionEvent=" + getView().getIgnoreExternalMotionEvent$miui_controlcenter_release());
        dispatchDump(pw, args);
    }

    public boolean getAllowInterceptSwitchEvent() {
        return getView().getAllowInterceptSwitchEvent();
    }

    public final BlurController getBlurController() {
        return this.blurController;
    }

    public final float getBlurRatio() {
        return this.blurRatio;
    }

    public final boolean getClipFooter() {
        return this.clipFooter;
    }

    public final boolean getClipHeader() {
        return this.clipHeader;
    }

    public final int getCurrentUserId() {
        return this.userTracker.getUserId();
    }

    public final float getExpandProgress() {
        return this.expandProgress;
    }

    public ControlCenterContent.ExpandState getExpandState() {
        return getToExpandState(this.lifecycle.getCurrentState());
    }

    public final float getExpansion() {
        return this.expansion;
    }

    public final GestureDispatcher getGestureDispatcher() {
        return this.gestureDispatcher;
    }

    public final Lifecycle getLifecycle() {
        return this.lifecycle;
    }

    public final MainPanelController getMainPanelController() {
        return this.mainPanelController;
    }

    public final float getMaxVelocity() {
        return this.maxVelocity;
    }

    public Pair<Float, Float> getPanelBorder() {
        updateResources();
        return this.mainPanelController.getPanelBorder(true);
    }

    public final int getRawScreenHeight() {
        return this.rawScreenHeight;
    }

    public final int getRawScreenWidth() {
        return this.rawScreenWidth;
    }

    public final int getScreenHeight() {
        return this.screenHeight;
    }

    public final int getScreenWidth() {
        return this.screenWidth;
    }

    public final ControlCenterScreenshot getScreenshot() {
        return this.screenshot;
    }

    public final SecondaryPanelRouter getSecondaryPanelRouter() {
        return this.secondaryPanelRouter;
    }

    public final boolean getSuperPowerMode() {
        return this.superPowerMode;
    }

    public boolean getSwitchable() {
        return this.secondaryPanelRouter.getInMainPanel() && this.secondaryPanelRouter.getInIdleState() && this.mainPanelController.getModeController().getMode() != MainPanelController.Mode.EDIT && !this.mainPanelController.getModeController().getInPendingEditMode();
    }

    public final int getTouchSlop() {
        return this.touchSlop;
    }

    public final boolean getUserUnlocked() {
        return this.userUnlocked;
    }

    public boolean handleExpandTouchEvent(MotionEvent event) {
        kotlin.jvm.internal.n.g(event, "event");
        return getView().handleExpandTouchEvent(event);
    }

    public boolean handleExternalTouchEvent(MotionEvent event) {
        kotlin.jvm.internal.n.g(event, "event");
        Log.d(TAG, "receiving external event " + event.getActionMasked());
        return getView().handleExternalTouchEvent(event);
    }

    public boolean handleKeyEvent(KeyEvent event) {
        kotlin.jvm.internal.n.g(event, "event");
        if (isCollapsed()) {
            return false;
        }
        if (event.getKeyCode() == 3) {
            if (event.getAction() == 1) {
                this.mainPanelController.getExpandController().hidePanel(true, true);
            }
            return true;
        }
        Boolean boolOnKeyEvent = this.secondaryPanelRouter.onKeyEvent(event);
        if (boolOnKeyEvent != null) {
            return boolOnKeyEvent.booleanValue();
        }
        return false;
    }

    public void hidePanel(boolean z2, boolean z3) {
        Log.d(TAG, "hidePanel " + z2 + " " + z3);
        getExpandController().hidePanel(z2, z3);
    }

    @Override // miui.systemui.util.ViewController
    public void init() {
        super.init();
        FlipUtils.INSTANCE.updateFlipOutInsetRight(getContext());
        Iterator<T> it = this.childControllers.iterator();
        while (it.hasNext()) {
            ((ControlCenterViewController) it.next()).dispatchCreate();
        }
    }

    public final boolean isCollapsed() {
        return getExpandState() == ControlCenterContent.ExpandState.COLLAPSED;
    }

    public final void onApplyWindowInsets(WindowInsets insets) {
        kotlin.jvm.internal.n.g(insets, "insets");
        Iterator<T> it = this.childControllers.iterator();
        while (it.hasNext()) {
            ((ControlCenterViewController) it.next()).dispatchApplyWindowInsets(insets);
        }
    }

    @Override // miui.systemui.autodensity.AutoDensityController.OnDensityChangeListener
    public void onConfigChanged(Configuration config) {
        kotlin.jvm.internal.n.g(config, "config");
        onConfigurationChanged(getResources().getConfiguration());
    }

    public final void onConfigurationChanged(Configuration configuration) {
        Configuration configuration2 = null;
        if ((DeviceHelper.detectType(getContext()) == 4 || CommonUtils.isFlipDevice()) && CommonUtils.isTinyScreen(getContext())) {
            Configuration configuration3 = this.configuration;
            if (configuration3 == null) {
                kotlin.jvm.internal.n.w("configuration");
                configuration3 = null;
            }
            if (configuration3.orientation == 2) {
                ControlCenterExpandController.hidePanel$default(getExpandController(), false, false, 2, null);
            }
        }
        updateResources();
        if (configuration == null) {
            return;
        }
        updateCustomDensityIfNeeded(configuration, true);
        ConfigUtils configUtils = ConfigUtils.INSTANCE;
        Configuration configuration4 = this.configuration;
        if (configuration4 == null) {
            kotlin.jvm.internal.n.w("configuration");
        } else {
            configuration2 = configuration4;
        }
        int iUpdate = configUtils.update(configuration2, configuration);
        this.blurUtilsExt.onConfigurationChanged(iUpdate);
        FlipUtils.INSTANCE.updateFlipOutInsetRight(getContext());
        updateThemeRes();
        Iterator<T> it = this.childControllers.iterator();
        while (it.hasNext()) {
            ((ControlCenterViewController) it.next()).dispatchConfigurationChanged(iUpdate);
        }
        if (ConfigUtils.INSTANCE.textsChanged(iUpdate)) {
            updatePanelContentDescription();
        }
    }

    @Override // miui.systemui.util.ViewController
    public void onCreate() {
        this.superSaveModeController.addCallback(this.superSaveModeListener);
        this.superSaveModeListener.onSuperSaveModeChange(this.superSaveModeController.isActive());
        this.configuration = new Configuration(getResources().getConfiguration());
        getContext().setTheme(getThemeRes());
        updateResources();
        BroadcastDispatcher.registerReceiver$default(this.broadcastDispatcher, this.userUnlockReceiver, new IntentFilter("android.intent.action.USER_UNLOCKED"), this.bgExecutor, null, 8, null);
        checkUserUnlocked();
        this.lifecycle.addObserver(this.lifecycleObserver);
        this.displayManager.registerDisplayListener(this, this.mainHandler);
        this.screenshot.addOnScreenshotListener(this.onScreenshotListener);
        this.autoDensityController.addOnDensityChangeListener(this);
        getView().setOnClickListener(new View.OnClickListener() { // from class: miui.systemui.controlcenter.windowview.k
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ControlCenterWindowViewController.onCreate$lambda$25(this.f5504a, view);
            }
        });
        updatePanelContentDescription();
        DeviceStateManagerCompat.INSTANCE.registerCallbackCompat(this.deviceStateManager, this.uiExecutor, this.foldStateListener);
        this.userTracker.addCallback(this.userListener, this.uiExecutor);
        this.dumpManager.registerDumpable(TAG, this);
        LifecycleCoroutineScope lifecycleCoroutineScope = this.scope;
        lifecycleCoroutineScope.launchWhenCreated(new ControlCenterWindowViewController$onCreate$2$1(this, null));
        lifecycleCoroutineScope.launchWhenCreated(new ControlCenterWindowViewController$onCreate$2$2(this, null));
    }

    public final void onDestroy() {
        this.autoDensityController.removeOnDensityChangeListener(this);
        this.lifecycle.removeObserver(this.lifecycleObserver);
        this.displayManager.unregisterDisplayListener(this);
        this.screenshot.removeOnScreenshotListener(this.onScreenshotListener);
        this.broadcastDispatcher.unregisterReceiver(this.userUnlockReceiver);
        DeviceStateManagerCompat.INSTANCE.unregisterCallbackCompat(this.deviceStateManager, this.foldStateListener);
        this.userTracker.removeCallback(this.userListener);
        this.superSaveModeController.removeCallback(this.superSaveModeListener);
        this.dumpManager.unregisterDumpable(TAG);
    }

    @Override // android.hardware.display.DisplayManager.DisplayListener
    public void onDisplayAdded(int i2) {
        Log.d(TAG, "on display added " + i2);
    }

    @Override // android.hardware.display.DisplayManager.DisplayListener
    public void onDisplayChanged(int i2) {
        Log.d(TAG, "on display changed " + i2);
    }

    @Override // android.hardware.display.DisplayManager.DisplayListener
    public void onDisplayRemoved(int i2) {
        Log.d(TAG, "on display removed " + i2);
    }

    public final void removeFoldStateCallback(DeviceStateManagerCompat.FoldStateCallback callback) {
        kotlin.jvm.internal.n.g(callback, "callback");
        this.foldStateCallbacks.remove(callback);
    }

    public void removeOnExpandStateChangedListener(ControlCenterContent.OnExpandChangedListener listener) {
        kotlin.jvm.internal.n.g(listener, "listener");
        this.expandListeners.remove(listener);
    }

    public final void removeOnUserUnlockedListener(OnUserUnlockedListener listener) {
        kotlin.jvm.internal.n.g(listener, "listener");
        this.userUnlockedListeners.remove(listener);
    }

    public final void setBlurRatio(float f2) {
        if (this.blurRatio == f2) {
            return;
        }
        this.blurRatio = f2;
        Iterator<T> it = this.expandListeners.iterator();
        while (it.hasNext()) {
            ((ControlCenterContent.OnExpandChangedListener) it.next()).onBlurRatioChanged(this.blurRatio);
        }
    }

    public final void setClipFooter(boolean z2) {
        if (this.clipFooter == z2) {
            return;
        }
        this.clipFooter = z2;
        Iterator<T> it = this.expandListeners.iterator();
        while (it.hasNext()) {
            ((ControlCenterContent.OnExpandChangedListener) it.next()).onFooterClipChanged(z2);
        }
    }

    public final void setClipHeader(boolean z2) {
        if (this.clipHeader == z2) {
            return;
        }
        this.clipHeader = z2;
        Iterator<T> it = this.expandListeners.iterator();
        while (it.hasNext()) {
            ((ControlCenterContent.OnExpandChangedListener) it.next()).onHeaderClipChanged(z2);
        }
    }

    public final void setExpandProgress(float f2) {
        float fH = c1.f.h(f2, 0.0f, 1.0f);
        if (this.expandProgress == fH) {
            return;
        }
        this.expandProgress = fH;
        Iterator<T> it = this.expandListeners.iterator();
        while (it.hasNext()) {
            ((ControlCenterContent.OnExpandChangedListener) it.next()).onExpandProgressChanged(this.expandProgress);
        }
    }

    public final void setExpansion(float f2) {
        float fB = c1.f.b(f2, 0.0f);
        if (this.expansion == fB) {
            return;
        }
        this.expansion = fB;
        Iterator<T> it = this.expandListeners.iterator();
        while (it.hasNext()) {
            ((ControlCenterContent.OnExpandChangedListener) it.next()).onExpansionChanged(this.expansion);
        }
    }

    public final void setTouchSlop(int i2) {
        this.touchSlop = i2;
    }

    public void showPanel(boolean z2, boolean z3) {
        Log.d(TAG, "showPanel " + z2 + " " + z3);
        getExpandController().showPanel(z2, z3);
        ControlCenterEventTracker.Companion.trackControlCenterOpenEvent(EventTracker.Companion.getScreenType(getContext()), getContext().getResources().getConfiguration().orientation, ControlCenterEventTracker.NC_SWITCH);
    }

    public final void updateClip(boolean z2) {
        updateClipHeader(z2);
        updateClipFooter(z2);
        Log.i(TAG, "updateClip: isMainPanel = " + z2 + ", clipHeader = " + this.clipHeader + ", clipFooter = " + this.clipFooter);
    }

    public final void updateCustomDensityIfNeeded(Configuration configuration, boolean z2) {
        if (configuration == null) {
            return;
        }
        DensityConfigManager densityConfigManager = DensityConfigManager.getInstance();
        Configuration configuration2 = null;
        if (!z2) {
            if (densityConfigManager.getTargetConfig() == null) {
                return;
            }
            DensityConfig targetConfig = densityConfigManager.getTargetConfig();
            if (targetConfig != null) {
                int i2 = targetConfig.densityDpi;
                Configuration configuration3 = this.configuration;
                if (configuration3 == null) {
                    kotlin.jvm.internal.n.w("configuration");
                    configuration3 = null;
                }
                if (i2 == configuration3.densityDpi) {
                    return;
                }
            }
        }
        DensityConfig targetConfig2 = densityConfigManager.getTargetConfig();
        Integer numValueOf = targetConfig2 != null ? Integer.valueOf(targetConfig2.densityDpi) : null;
        Configuration configuration4 = this.configuration;
        if (configuration4 == null) {
            kotlin.jvm.internal.n.w("configuration");
        } else {
            configuration2 = configuration4;
        }
        Log.i(TAG, "updateCustomDensity: " + z2 + ", " + numValueOf + ", " + configuration2.densityDpi);
        densityConfigManager.tryUpdateConfig(getContext(), configuration);
        DensityUtil.updateCustomDensity(getContext());
    }

    public ControlCenterWindowViewImpl getContentView() {
        return this.contentView;
    }

    public ControlCenterExpandController getExpandController() {
        return this.expandController;
    }
}
