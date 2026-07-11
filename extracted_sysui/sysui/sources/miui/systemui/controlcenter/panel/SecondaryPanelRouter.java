package miui.systemui.controlcenter.panel;

import E0.a;
import H0.s;
import I0.m;
import android.util.Log;
import android.view.KeyEvent;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import androidx.lifecycle.Lifecycle;
import com.android.systemui.miui.volume.VolumeColumn;
import com.android.systemui.plugins.miui.shade.ShadeSwitchController;
import j1.InterfaceC0418f;
import j1.K;
import j1.u;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.n;
import kotlin.jvm.internal.o;
import miui.systemui.controlcenter.ConfigUtils;
import miui.systemui.controlcenter.customize.CustomizeAdapter;
import miui.systemui.controlcenter.dagger.ControlCenterScope;
import miui.systemui.controlcenter.dagger.qualifiers.Qualifiers;
import miui.systemui.controlcenter.events.ControlCenterScenarioTracker;
import miui.systemui.controlcenter.panel.SecondaryPanelRouter;
import miui.systemui.controlcenter.panel.devicecontrol.DeviceControlPanelController;
import miui.systemui.controlcenter.panel.main.MainPanelAnimController;
import miui.systemui.controlcenter.panel.secondary.BrightnessPanelParams;
import miui.systemui.controlcenter.panel.secondary.DetailPanelParams;
import miui.systemui.controlcenter.panel.secondary.MediaPanelParams;
import miui.systemui.controlcenter.panel.secondary.VolumePanelParams;
import miui.systemui.controlcenter.panel.secondary.brightness.BrightnessPanelController;
import miui.systemui.controlcenter.panel.secondary.volume.VolumePanelController;
import miui.systemui.controlcenter.utils.ControlCenterViewController;
import miui.systemui.controlcenter.windowview.ControlCenterScreenshot;
import miui.systemui.controlcenter.windowview.ControlCenterWindowViewController;
import miui.systemui.controlcenter.windowview.ControlCenterWindowViewImpl;
import miui.systemui.util.DeviceUtils;

/* JADX INFO: loaded from: classes.dex */
@ControlCenterScope
public final class SecondaryPanelRouter extends ControlCenterViewController<ControlCenterWindowViewImpl> {
    public static final Companion Companion = new Companion(null);
    public static final int ROUTING_STATE_COLLAPSING = 3;
    public static final int ROUTING_STATE_EXPANDING = 4;
    public static final int ROUTING_STATE_IDLE = 2;
    public static final int ROUTING_STATE_PRE_ROUTING = 0;
    public static final int ROUTING_STATE_ROUTING = 1;
    private static final String TAG = "SecondaryPanelRouter";
    private final u _routingState;
    private boolean blockTouchUntilStop;
    private final a brightnessPanelController;
    private final ArrayList<ControlCenterViewController<? extends ViewGroup>> childControllers;
    private Function0 completeAction;
    private SecondaryPanel<?> current;
    private final a customizePanelController;
    private final a detailPanelController;
    private final a deviceControlPanelController;
    private final Lifecycle lifecycle;
    private final MainPanelAnimController mainPanelAnimController;
    private final a mediaPanelController;
    private final SecondaryPanelRouter$onGlobalLayoutListener$1 onGlobalLayoutListener;
    private final SecondaryPanelRouter$onScreenshotListener$1 onScreenshotListener;
    private SecondaryPanel<?> previous;
    private int routingState;
    private final InterfaceC0418f routingStateFlow;
    private final a screenshot;
    private final a shadeSwitchController;
    private SecondaryPanel<?> target;
    private final SecondaryPanelTouchController touchController;
    private final a volumePanelController;
    private final ControlCenterWindowViewImpl windowView;
    private final a windowViewController;

    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }

    public interface SecondaryPanel<P> {
        void cancelPrepare();

        void forceToHide();

        void forceToShow(P p2);

        boolean isAnimating();

        default boolean needCollapseOnOrientationChanged() {
            return false;
        }

        default void onCollapsed() {
        }

        void onHidden(SecondaryPanel<?> secondaryPanel);

        Boolean onKeyEvent(KeyEvent keyEvent);

        default void onMainPanelHidden() {
        }

        void onShown();

        void prepareHide();

        boolean prepareShow(P p2);

        void startHide(Function0 function0);

        void startShow(Function0 function0);
    }

    /* JADX INFO: renamed from: miui.systemui.controlcenter.panel.SecondaryPanelRouter$interceptHidePanel$1, reason: invalid class name */
    public static final class AnonymousClass1 extends o implements Function0 {
        public AnonymousClass1() {
            super(0);
        }

        @Override // kotlin.jvm.functions.Function0
        public /* bridge */ /* synthetic */ Object invoke() {
            m79invoke();
            return s.f314a;
        }

        /* JADX INFO: renamed from: invoke, reason: collision with other method in class */
        public final void m79invoke() {
            SecondaryPanelRouter.this.performComplete();
        }
    }

    /* JADX INFO: renamed from: miui.systemui.controlcenter.panel.SecondaryPanelRouter$interceptHidePanel$2, reason: invalid class name */
    public static final class AnonymousClass2 extends o implements Function0 {
        public AnonymousClass2() {
            super(0);
        }

        @Override // kotlin.jvm.functions.Function0
        public /* bridge */ /* synthetic */ Object invoke() {
            m80invoke();
            return s.f314a;
        }

        /* JADX INFO: renamed from: invoke, reason: collision with other method in class */
        public final void m80invoke() {
            SecondaryPanelRouter.this.performComplete();
        }
    }

    /* JADX INFO: renamed from: miui.systemui.controlcenter.panel.SecondaryPanelRouter$interceptHidePanel$3, reason: invalid class name */
    public static final class AnonymousClass3 extends o implements Function0 {
        public AnonymousClass3() {
            super(0);
        }

        @Override // kotlin.jvm.functions.Function0
        public /* bridge */ /* synthetic */ Object invoke() {
            m81invoke();
            return s.f314a;
        }

        /* JADX INFO: renamed from: invoke, reason: collision with other method in class */
        public final void m81invoke() {
            SecondaryPanelRouter.this.performComplete();
        }
    }

    /* JADX INFO: renamed from: miui.systemui.controlcenter.panel.SecondaryPanelRouter$interceptShowPanel$1, reason: invalid class name and case insensitive filesystem */
    public static final class C04741 extends o implements Function0 {
        public C04741() {
            super(0);
        }

        @Override // kotlin.jvm.functions.Function0
        public /* bridge */ /* synthetic */ Object invoke() {
            m82invoke();
            return s.f314a;
        }

        /* JADX INFO: renamed from: invoke, reason: collision with other method in class */
        public final void m82invoke() {
            SecondaryPanelRouter.this.performComplete();
        }
    }

    /* JADX INFO: renamed from: miui.systemui.controlcenter.panel.SecondaryPanelRouter$routeTo$2, reason: invalid class name and case insensitive filesystem */
    public static final class C04752 extends o implements Function0 {
        public C04752() {
            super(0);
        }

        @Override // kotlin.jvm.functions.Function0
        public /* bridge */ /* synthetic */ Object invoke() {
            m85invoke();
            return s.f314a;
        }

        /* JADX INFO: renamed from: invoke, reason: collision with other method in class */
        public final void m85invoke() {
            SecondaryPanelRouter.this.performComplete();
        }
    }

    /* JADX INFO: renamed from: miui.systemui.controlcenter.panel.SecondaryPanelRouter$routeTo$3, reason: invalid class name and case insensitive filesystem */
    public static final class C04763 extends o implements Function0 {
        public C04763() {
            super(0);
        }

        @Override // kotlin.jvm.functions.Function0
        public /* bridge */ /* synthetic */ Object invoke() {
            m86invoke();
            return s.f314a;
        }

        /* JADX INFO: renamed from: invoke, reason: collision with other method in class */
        public final void m86invoke() {
            SecondaryPanelRouter.this.performComplete();
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r2v3, types: [miui.systemui.controlcenter.panel.SecondaryPanelRouter$onScreenshotListener$1] */
    /* JADX WARN: Type inference failed for: r3v3, types: [miui.systemui.controlcenter.panel.SecondaryPanelRouter$onGlobalLayoutListener$1] */
    public SecondaryPanelRouter(@Qualifiers.ControlCenter Lifecycle lifecycle, @Qualifiers.WindowView ControlCenterWindowViewImpl windowView, a screenshot, SecondaryPanelTouchController touchController, MainPanelAnimController mainPanelAnimController, a deviceControlPanelController, a customizePanelController, a detailPanelController, a mediaPanelController, a brightnessPanelController, a volumePanelController, a windowViewController, a shadeSwitchController) {
        super(windowView);
        n.g(lifecycle, "lifecycle");
        n.g(windowView, "windowView");
        n.g(screenshot, "screenshot");
        n.g(touchController, "touchController");
        n.g(mainPanelAnimController, "mainPanelAnimController");
        n.g(deviceControlPanelController, "deviceControlPanelController");
        n.g(customizePanelController, "customizePanelController");
        n.g(detailPanelController, "detailPanelController");
        n.g(mediaPanelController, "mediaPanelController");
        n.g(brightnessPanelController, "brightnessPanelController");
        n.g(volumePanelController, "volumePanelController");
        n.g(windowViewController, "windowViewController");
        n.g(shadeSwitchController, "shadeSwitchController");
        this.lifecycle = lifecycle;
        this.windowView = windowView;
        this.screenshot = screenshot;
        this.touchController = touchController;
        this.mainPanelAnimController = mainPanelAnimController;
        this.deviceControlPanelController = deviceControlPanelController;
        this.customizePanelController = customizePanelController;
        this.detailPanelController = detailPanelController;
        this.mediaPanelController = mediaPanelController;
        this.brightnessPanelController = brightnessPanelController;
        this.volumePanelController = volumePanelController;
        this.windowViewController = windowViewController;
        this.shadeSwitchController = shadeSwitchController;
        this.routingState = 2;
        u uVarA = K.a(2);
        this._routingState = uVarA;
        this.routingStateFlow = uVarA;
        this.current = mainPanelAnimController;
        this.onGlobalLayoutListener = new ViewTreeObserver.OnGlobalLayoutListener() { // from class: miui.systemui.controlcenter.panel.SecondaryPanelRouter$onGlobalLayoutListener$1
            @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
            public void onGlobalLayout() {
                if (this.this$0.getRoutingState() == 0) {
                    this.this$0.setRoutingState(1);
                    this.this$0.current.startHide(new SecondaryPanelRouter$onGlobalLayoutListener$1$onGlobalLayout$1(this.this$0));
                    SecondaryPanelRouter.SecondaryPanel secondaryPanel = this.this$0.target;
                    if (secondaryPanel != null) {
                        secondaryPanel.startShow(new SecondaryPanelRouter$onGlobalLayoutListener$1$onGlobalLayout$2(this.this$0));
                    }
                }
                SecondaryPanelRouter.access$getView(this.this$0).getViewTreeObserver().removeOnGlobalLayoutListener(this);
            }
        };
        ArrayList<ControlCenterViewController<? extends ViewGroup>> arrayListF = m.f(touchController, customizePanelController.get(), detailPanelController.get());
        if (DeviceUtils.isSupportControlDevices()) {
            arrayListF.add(deviceControlPanelController.get());
        }
        this.childControllers = arrayListF;
        this.onScreenshotListener = new ControlCenterScreenshot.OnScreenshotListener() { // from class: miui.systemui.controlcenter.panel.SecondaryPanelRouter$onScreenshotListener$1
            @Override // miui.systemui.controlcenter.windowview.ControlCenterScreenshot.OnScreenshotListener
            public void onScreenshot() {
                ControlCenterScreenshot controlCenterScreenshot = (ControlCenterScreenshot) this.this$0.screenshot.get();
                String simpleName = this.this$0.current.getClass().getSimpleName();
                n.f(simpleName, "getSimpleName(...)");
                controlCenterScreenshot.addDumpMessage("currentPanel", simpleName);
            }
        };
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static final /* synthetic */ ControlCenterWindowViewImpl access$getView(SecondaryPanelRouter secondaryPanelRouter) {
        return (ControlCenterWindowViewImpl) secondaryPanelRouter.getView();
    }

    private final void cancelPrepare() {
        SecondaryPanel<?> secondaryPanel = this.target;
        if (secondaryPanel != null) {
            secondaryPanel.cancelPrepare();
        }
        this.current.cancelPrepare();
        this.target = null;
        setRoutingState(2);
    }

    private final boolean getAnySeekBarTracking() {
        if (this.lifecycle.getCurrentState().isAtLeast(Lifecycle.State.RESUMED)) {
            if (!((BrightnessPanelController) this.brightnessPanelController.get()).getSliderBinding().slider.getTracking()) {
                List<VolumeColumn> visibleColumns = ((VolumePanelController) this.volumePanelController.get()).getDelegate().getVisibleColumns();
                if (visibleColumns == null || !visibleColumns.isEmpty()) {
                    Iterator<T> it = visibleColumns.iterator();
                    while (it.hasNext()) {
                        if (((VolumeColumn) it.next()).getTracking()) {
                        }
                    }
                }
            }
            return true;
        }
        return false;
    }

    private final String getCurrentName() {
        return this.current.getClass().getSimpleName();
    }

    private final boolean getMainPanelAppearance() {
        return ((ControlCenterWindowViewController) this.windowViewController.get()).getExpandController().getAppearance();
    }

    private final boolean getNcSwitching() {
        return ((ShadeSwitchController) this.shadeSwitchController.get()).getSwitching();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Multi-variable type inference failed */
    public final void performComplete() {
        if (this.routingState == 2 || this.current.isAnimating()) {
            return;
        }
        SecondaryPanel<?> secondaryPanel = this.target;
        if (secondaryPanel == null || !secondaryPanel.isAnimating()) {
            int i2 = this.routingState;
            if (i2 == 1) {
                this.current.onHidden(this.target);
                this.previous = this.current;
                SecondaryPanel<?> secondaryPanel2 = this.target;
                if (secondaryPanel2 == null) {
                    throw new IllegalStateException("Target should not be null when completing routing.");
                }
                setCurrent(secondaryPanel2);
                this.current.onShown();
                this.target = null;
            } else if (i2 == 3) {
                SecondaryPanel<?> secondaryPanel3 = this.current;
                secondaryPanel3.onHidden(this.target);
                secondaryPanel3.onCollapsed();
                SecondaryPanel<?> secondaryPanel4 = this.target;
                if (secondaryPanel4 != null) {
                    secondaryPanel4.onHidden(null);
                    secondaryPanel4.onCollapsed();
                }
                setCurrent(this.mainPanelAnimController);
                this.target = null;
            } else if (i2 == 4) {
                this.current.onShown();
            }
            setRoutingState(2);
            Log.d(TAG, "perform route to " + getCurrentName() + " complete.");
            Function0 function0 = this.completeAction;
            if (function0 != null) {
                function0.invoke();
            }
            this.completeAction = null;
            if (this.blockTouchUntilStop) {
                return;
            }
            ((ControlCenterWindowViewImpl) getView()).requestBlockTouch("SecondaryPanelRouter perform complete", false);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    private final <P> void routeTo(SecondaryPanel<P> secondaryPanel, P p2, boolean z2, boolean z3) {
        int i2;
        Log.i(TAG, "route from " + getCurrentName() + " to " + secondaryPanel.getClass().getSimpleName() + ", routingState: " + this.routingState + ", mainPanelAppearance: " + getMainPanelAppearance());
        if ((getInMainPanel() && !n.c(secondaryPanel, this.mainPanelAnimController) && getInIdleState() && !getMainPanelAppearance()) || getNcSwitching() || getAnySeekBarTracking()) {
            Log.w(TAG, "routeTo: WARNING! Route to another panel is canceled, caused by: inMainPanel = " + getInMainPanel() + ", target = " + secondaryPanel.getClass() + ", inIdleState = " + getInIdleState() + ", mainPanelAppearance = " + getMainPanelAppearance() + ", ncSwitching = " + getNcSwitching() + ", anySeekBarTracking = " + getAnySeekBarTracking());
            secondaryPanel.cancelPrepare();
            return;
        }
        if (!z2) {
            if (!z3 && (i2 = this.routingState) != 2 && i2 != 0 && i2 != 1) {
                Log.i(TAG, "ignore routing request when panel expanding or collapsing.");
                return;
            }
            SecondaryPanel<?> secondaryPanel2 = this.current;
            if (secondaryPanel2 != secondaryPanel) {
                secondaryPanel2.forceToHide();
            }
            SecondaryPanel<?> secondaryPanel3 = this.target;
            if (secondaryPanel3 != secondaryPanel && secondaryPanel3 != null) {
                secondaryPanel3.forceToHide();
            }
            secondaryPanel.forceToShow(p2);
            this.previous = this.current;
            setCurrent(secondaryPanel);
            this.target = null;
            setRoutingState(2);
            Function0 function0 = this.completeAction;
            if (function0 != null) {
                function0.invoke();
            }
            this.completeAction = null;
            ((ControlCenterWindowViewImpl) getView()).requestBlockTouch(TAG, false);
            return;
        }
        int i3 = this.routingState;
        if (i3 == 0) {
            if (!n.c(secondaryPanel, this.current)) {
                secondaryPanel.cancelPrepare();
                return;
            } else {
                cancelPrepare();
                ((ControlCenterWindowViewImpl) getView()).requestBlockTouch(TAG, false);
                return;
            }
        }
        if (i3 != 1) {
            if (i3 != 2) {
                Log.d(TAG, "ignore routing request when panel expanding or collapsing.");
                return;
            }
            if (this.current != secondaryPanel && secondaryPanel.prepareShow(p2)) {
                this.target = secondaryPanel;
                this.current.prepareHide();
                setRoutingState(0);
                ((ControlCenterWindowViewImpl) getView()).requestBlockTouch(TAG, true);
                ((ControlCenterWindowViewImpl) getView()).getViewTreeObserver().addOnGlobalLayoutListener(this.onGlobalLayoutListener);
                return;
            }
            return;
        }
        if (!n.c(secondaryPanel, this.current)) {
            secondaryPanel.cancelPrepare();
            return;
        }
        Log.d(TAG, "reverse routing");
        SecondaryPanel<?> secondaryPanel4 = this.target;
        if (secondaryPanel4 == null) {
            throw new IllegalStateException("Target should not be null when reverse routing.");
        }
        setCurrent(secondaryPanel4);
        this.target = secondaryPanel;
        if ((this.current instanceof DeviceControlPanelController) && (secondaryPanel instanceof MainPanelAnimController)) {
            ((ControlCenterWindowViewController) this.windowViewController.get()).updateClip(true);
        }
        this.current.startHide(new C04752());
        secondaryPanel.startShow(new C04763());
    }

    public static /* synthetic */ void routeTo$default(SecondaryPanelRouter secondaryPanelRouter, SecondaryPanel secondaryPanel, Object obj, boolean z2, boolean z3, int i2, Object obj2) {
        if ((i2 & 4) != 0) {
            z2 = true;
        }
        if ((i2 & 8) != 0) {
            z3 = false;
        }
        secondaryPanelRouter.routeTo(secondaryPanel, obj, z2, z3);
    }

    public static /* synthetic */ void routeToSmartHome$default(SecondaryPanelRouter secondaryPanelRouter, boolean z2, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            z2 = true;
        }
        secondaryPanelRouter.routeToSmartHome(z2);
    }

    private final void setCurrent(SecondaryPanel<?> secondaryPanel) {
        this.current = secondaryPanel;
        Object obj = this.windowViewController.get();
        n.f(obj, "get(...)");
        ControlCenterWindowViewController.updateClip$default((ControlCenterWindowViewController) obj, false, 1, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void setRoutingState(int i2) {
        if (this.routingState == i2) {
            return;
        }
        this.routingState = i2;
        this._routingState.setValue(Integer.valueOf(i2));
    }

    @Override // miui.systemui.controlcenter.utils.ControlCenterViewController
    public void dump(PrintWriter pw, String[] args) {
        n.g(pw, "pw");
        n.g(args, "args");
        pw.println("SecondaryPanel state:");
        pw.println("  currentPanel=" + getCurrentName());
    }

    public final void fixToMainPanel() {
        SecondaryPanel<?> secondaryPanel;
        if (this.lifecycle.getCurrentState().isAtLeast(Lifecycle.State.STARTED) || (secondaryPanel = this.current) == this.mainPanelAnimController) {
            return;
        }
        secondaryPanel.forceToHide();
        ControlCenterScenarioTracker controlCenterScenarioTracker = ControlCenterScenarioTracker.INSTANCE;
        String currentName = getCurrentName();
        n.f(currentName, "<get-currentName>(...)");
        controlCenterScenarioTracker.sendSecondaryPanelEntryFailedEvent(currentName);
        Log.w(TAG, "fixToMainPanel current: " + this.current);
        setCurrent(this.mainPanelAnimController);
    }

    public final boolean getInBrightnessPanel() {
        return n.c(this.current, this.brightnessPanelController.get());
    }

    public final boolean getInCollapsingState() {
        return this.routingState == 3;
    }

    public final boolean getInDetailPanel() {
        return n.c(this.current, this.detailPanelController.get());
    }

    public final boolean getInDeviceControlPanel() {
        return n.c(this.current, this.deviceControlPanelController.get()) || n.c(this.current, this.customizePanelController.get());
    }

    public final boolean getInIdleState() {
        return this.routingState == 2;
    }

    public final boolean getInMainPanel() {
        return this.current == this.mainPanelAnimController;
    }

    public final int getRoutingState() {
        return this.routingState;
    }

    public final InterfaceC0418f getRoutingStateFlow() {
        return this.routingStateFlow;
    }

    public final boolean getRoutingToMainPanel() {
        return n.c(this.target, this.mainPanelAnimController);
    }

    public final boolean getToDetailPanel() {
        return n.c(this.target, this.detailPanelController.get());
    }

    public final boolean getToDeviceControlPanel() {
        return n.c(this.target, this.deviceControlPanelController.get()) || n.c(this.target, this.customizePanelController.get());
    }

    public final SecondaryPanelTouchController getTouchController() {
        return this.touchController;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:36:0x00b0  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final boolean interceptHidePanel(boolean r7, kotlin.jvm.functions.Function0 r8) {
        /*
            Method dump skipped, instruction units count: 216
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: miui.systemui.controlcenter.panel.SecondaryPanelRouter.interceptHidePanel(boolean, kotlin.jvm.functions.Function0):boolean");
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final boolean interceptShowPanel(boolean z2, Function0 completeAction) {
        n.g(completeAction, "completeAction");
        if (this.routingState != 3) {
            return false;
        }
        if (z2) {
            setRoutingState(4);
            this.completeAction = completeAction;
            this.current.startShow(new C04741());
            return true;
        }
        SecondaryPanel<?> secondaryPanel = this.current;
        MainPanelAnimController mainPanelAnimController = this.mainPanelAnimController;
        if (secondaryPanel == mainPanelAnimController) {
            mainPanelAnimController.forceToShow(null);
            SecondaryPanel<?> secondaryPanel2 = this.target;
            if (secondaryPanel2 != null) {
                secondaryPanel2.forceToHide();
            }
            this.target = null;
        } else if (this.target == mainPanelAnimController) {
            mainPanelAnimController.forceToShow(null);
            this.current.forceToHide();
            setCurrent(this.mainPanelAnimController);
        } else {
            secondaryPanel.forceToHide();
            SecondaryPanel<?> secondaryPanel3 = this.target;
            if (secondaryPanel3 != null) {
                secondaryPanel3.forceToHide();
            }
            setCurrent(this.mainPanelAnimController);
            this.target = null;
        }
        setRoutingState(2);
        this.blockTouchUntilStop = false;
        ((ControlCenterWindowViewImpl) getView()).requestBlockTouch(TAG, false);
        return true;
    }

    @Override // miui.systemui.controlcenter.utils.ControlCenterViewController, miui.systemui.controlcenter.ConfigUtils.OnConfigChangeListener
    public void onConfigurationChanged(int i2) {
        SecondaryPanel<?> secondaryPanel;
        if (ConfigUtils.INSTANCE.orientationChanged(i2)) {
            int i3 = this.routingState;
            if (i3 != 0 && i3 != 1) {
                if (i3 == 2) {
                    if (!this.current.needCollapseOnOrientationChanged() || (secondaryPanel = this.previous) == null) {
                        return;
                    }
                    routeTo$default(this, secondaryPanel, null, false, false, 8, null);
                    return;
                }
                if (i3 != 4) {
                    return;
                }
            }
            SecondaryPanel<?> secondaryPanel2 = this.target;
            if (secondaryPanel2 == null || !secondaryPanel2.needCollapseOnOrientationChanged()) {
                return;
            }
            routeTo$default(this, this.current, null, false, false, 8, null);
        }
    }

    @Override // miui.systemui.util.ViewController
    public void onCreate() {
        super.onCreate();
        setCurrent(this.mainPanelAnimController);
        ((ControlCenterScreenshot) this.screenshot.get()).addOnScreenshotListener(this.onScreenshotListener);
    }

    @Override // miui.systemui.controlcenter.utils.ControlCenterViewController
    public void onDestroy() {
        super.onDestroy();
        ((ControlCenterScreenshot) this.screenshot.get()).removeOnScreenshotListener(this.onScreenshotListener);
    }

    public final Boolean onKeyEvent(KeyEvent event) {
        SecondaryPanel<?> secondaryPanel;
        n.g(event, "event");
        int i2 = this.routingState;
        if (i2 != 0 && i2 != 1) {
            if (i2 != 2) {
                return null;
            }
            return this.current.onKeyEvent(event);
        }
        if (event.getAction() == 1 && ((event.getKeyCode() == 4 || event.getKeyCode() == 82) && (secondaryPanel = this.current) == this.mainPanelAnimController)) {
            routeTo$default(this, secondaryPanel, null, false, false, 12, null);
        }
        return Boolean.TRUE;
    }

    @Override // miui.systemui.controlcenter.utils.ControlCenterViewController
    public void onStart() {
        super.onStart();
        this.blockTouchUntilStop = false;
    }

    @Override // miui.systemui.controlcenter.utils.ControlCenterViewController
    public void onStop() {
        this.blockTouchUntilStop = false;
        routeTo(this.mainPanelAnimController, null, false, true);
    }

    public final void routeToBrightnessPanel(BrightnessPanelParams params) {
        n.g(params, "params");
        Object obj = this.brightnessPanelController.get();
        n.f(obj, "get(...)");
        routeTo$default(this, (SecondaryPanel) obj, params, false, false, 12, null);
    }

    public final void routeToCustomize(CustomizeAdapter customizeAdapter) {
        Object obj = this.customizePanelController.get();
        n.f(obj, "get(...)");
        routeTo$default(this, (SecondaryPanel) obj, customizeAdapter, false, false, 12, null);
    }

    public final void routeToDetail(DetailPanelParams params) {
        n.g(params, "params");
        Object obj = this.detailPanelController.get();
        n.f(obj, "get(...)");
        routeTo$default(this, (SecondaryPanel) obj, params, false, false, 12, null);
    }

    public final void routeToMain() {
        routeTo$default(this, this.mainPanelAnimController, null, false, false, 12, null);
    }

    public final void routeToMediaPanel(MediaPanelParams params) {
        n.g(params, "params");
        Object obj = this.mediaPanelController.get();
        n.f(obj, "get(...)");
        routeTo$default(this, (SecondaryPanel) obj, params, false, false, 12, null);
    }

    public final void routeToSmartHome(boolean z2) {
        Object obj = this.deviceControlPanelController.get();
        n.f(obj, "get(...)");
        routeTo$default(this, (SecondaryPanel) obj, null, z2, false, 8, null);
    }

    public final void routeToVolumePanel(VolumePanelParams params) {
        n.g(params, "params");
        Object obj = this.volumePanelController.get();
        n.f(obj, "get(...)");
        routeTo$default(this, (SecondaryPanel) obj, params, false, false, 12, null);
    }

    public final boolean toSamePanel(SecondaryPanel<?> secondaryPanel) {
        return n.c(secondaryPanel, this.target);
    }

    @Override // miui.systemui.controlcenter.utils.ControlCenterViewController
    public ArrayList<ControlCenterViewController<? extends ViewGroup>> getChildControllers() {
        return this.childControllers;
    }
}
