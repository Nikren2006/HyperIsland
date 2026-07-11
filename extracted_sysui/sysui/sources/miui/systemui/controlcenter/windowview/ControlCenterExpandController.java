package miui.systemui.controlcenter.windowview;

import android.util.Log;
import android.view.ViewTreeObserver;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleEventObserver;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LifecycleOwnerKt;
import com.android.systemui.plugins.miui.shade.PanelExpandController;
import g1.AbstractC0369g;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;
import kotlin.jvm.internal.DefaultConstructorMarker;
import miui.systemui.controlcenter.R;
import miui.systemui.controlcenter.dagger.ControlCenterScope;
import miui.systemui.controlcenter.dagger.qualifiers.Qualifiers;
import miui.systemui.controlcenter.events.ControlCenterScenarioTracker;
import miui.systemui.controlcenter.panel.SecondaryPanelRouter;
import miui.systemui.controlcenter.panel.main.MainPanelController;
import miui.systemui.controlcenter.panel.main.anim.SpreadRowsAnimator;
import miui.systemui.controlcenter.panel.main.brightness.BrightnessSliderController;
import miui.systemui.controlcenter.utils.ControlCenterViewController;
import miui.systemui.util.MiBlurCompat;

/* JADX INFO: loaded from: classes.dex */
@ControlCenterScope
public final class ControlCenterExpandController extends ControlCenterViewController<ControlCenterWindowViewImpl> implements PanelExpandController {
    public static final Companion Companion = new Companion(null);
    private static final int STATE_BLOCKING = 1;
    private static final int STATE_IDLE = 0;
    private static final int STATE_PENDING_EXPAND = 3;
    private static final int STATE_PENDING_SHOW = 2;
    private static final String TAG = "ControlCenterExpandController";
    private boolean appearance;
    private final E0.a blurController;
    private final E0.a brightnessSliderController;
    private final ArrayList<PanelExpandController.Callback> callbacks;
    private int dragState;
    private float expandHeight;
    private float expandThresh;
    private float expansion;
    private final ControlCenterExpandController$expansionAnimator$1 expansionAnimator;
    private boolean inMirror;
    private float lastVelocity;
    private final E0.a mainPanelController;
    private final LifecycleEventObserver observer;
    private final ControlCenterExpandController$onGlobalLayoutListener$1 onGlobalLayoutListener;
    private int pendingState;
    private final E0.a secondaryPanelRouter;
    private final E0.a spreadRowsAnimator;
    private boolean switchedFromNPV;
    private boolean tracking;
    private boolean windowLayoutFinished;
    private final E0.a windowViewController;

    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Type inference failed for: r2v1, types: [miui.systemui.controlcenter.windowview.ControlCenterExpandController$onGlobalLayoutListener$1] */
    public ControlCenterExpandController(@Qualifiers.WindowView ControlCenterWindowViewImpl windowView, E0.a windowViewController, E0.a mainPanelController, E0.a secondaryPanelRouter, E0.a blurController, E0.a spreadRowsAnimator, E0.a brightnessSliderController) {
        super(windowView);
        kotlin.jvm.internal.n.g(windowView, "windowView");
        kotlin.jvm.internal.n.g(windowViewController, "windowViewController");
        kotlin.jvm.internal.n.g(mainPanelController, "mainPanelController");
        kotlin.jvm.internal.n.g(secondaryPanelRouter, "secondaryPanelRouter");
        kotlin.jvm.internal.n.g(blurController, "blurController");
        kotlin.jvm.internal.n.g(spreadRowsAnimator, "spreadRowsAnimator");
        kotlin.jvm.internal.n.g(brightnessSliderController, "brightnessSliderController");
        this.windowViewController = windowViewController;
        this.mainPanelController = mainPanelController;
        this.secondaryPanelRouter = secondaryPanelRouter;
        this.blurController = blurController;
        this.spreadRowsAnimator = spreadRowsAnimator;
        this.brightnessSliderController = brightnessSliderController;
        this.onGlobalLayoutListener = new ViewTreeObserver.OnGlobalLayoutListener() { // from class: miui.systemui.controlcenter.windowview.ControlCenterExpandController$onGlobalLayoutListener$1
            @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
            public void onGlobalLayout() {
                Log.v("ControlCenterExpandController", "global layout finished, pending state is " + this.this$0.pendingState);
                ControlCenterExpandController.access$getView(this.this$0).getViewTreeObserver().removeOnGlobalLayoutListener(this);
                int i2 = this.this$0.pendingState;
                if (i2 == 2) {
                    Object obj = this.this$0.spreadRowsAnimator.get();
                    kotlin.jvm.internal.n.f(obj, "get(...)");
                    SpreadRowsAnimator.changeVisible$default((SpreadRowsAnimator) obj, true, true, false, 4, null);
                    Object obj2 = this.this$0.spreadRowsAnimator.get();
                    kotlin.jvm.internal.n.f(obj2, "get(...)");
                    SpreadRowsAnimator.changeExpand$default((SpreadRowsAnimator) obj2, this.this$0.getExpandThresh(), 0.0f, true, true, 2, null);
                } else if (i2 == 3) {
                    Object obj3 = this.this$0.spreadRowsAnimator.get();
                    kotlin.jvm.internal.n.f(obj3, "get(...)");
                    SpreadRowsAnimator.changeVisible$default((SpreadRowsAnimator) obj3, true, true, false, 4, null);
                }
                this.this$0.windowLayoutFinished = true;
                this.this$0.pendingState = 0;
            }
        };
        this.expansionAnimator = new ControlCenterExpandController$expansionAnimator$1(this);
        this.callbacks = new ArrayList<>();
        this.observer = new LifecycleEventObserver() { // from class: miui.systemui.controlcenter.windowview.c
            @Override // androidx.lifecycle.LifecycleEventObserver
            public final void onStateChanged(LifecycleOwner lifecycleOwner, Lifecycle.Event event) {
                ControlCenterExpandController.observer$lambda$1(this.f5496a, lifecycleOwner, event);
            }
        };
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static final /* synthetic */ ControlCenterWindowViewImpl access$getView(ControlCenterExpandController controlCenterExpandController) {
        return (ControlCenterWindowViewImpl) controlCenterExpandController.getView();
    }

    public static /* synthetic */ void changeItemVisible$default(ControlCenterExpandController controlCenterExpandController, boolean z2, boolean z3, boolean z4, int i2, Object obj) {
        if ((i2 & 4) != 0) {
            z4 = false;
        }
        controlCenterExpandController.changeItemVisible(z2, z3, z4);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Multi-variable type inference failed */
    public final void handleAnimFinish() {
        boolean z2 = this.dragState == 0;
        Lifecycle.State currentState = ((BlurController) this.blurController.get()).getLifecycle().getCurrentState();
        Lifecycle.State state = Lifecycle.State.CREATED;
        boolean z3 = currentState == state;
        boolean inIdleState = ((SecondaryPanelRouter) this.secondaryPanelRouter.get()).getInIdleState();
        boolean z4 = this.expansionAnimator.getLifecycle().getCurrentState() == state;
        Log.i(TAG, "handle anim finish " + this.tracking + " " + z2 + " " + z3 + " " + inIdleState + " " + z4);
        if (!this.tracking && z2 && z3 && inIdleState && z4) {
            if (getAppearance()) {
                ControlCenterScenarioTracker.INSTANCE.reportExpandAnimEnd(false);
            } else {
                ControlCenterScenarioTracker.INSTANCE.reportCollapseAnimEnd(false);
            }
            ControlCenterWindowViewImpl controlCenterWindowViewImpl = (ControlCenterWindowViewImpl) getView();
            if (getAppearance()) {
                state = Lifecycle.State.RESUMED;
            }
            controlCenterWindowViewImpl.notifyLifecycleStateChanged(TAG, state);
        }
    }

    public static /* synthetic */ void hidePanel$default(ControlCenterExpandController controlCenterExpandController, boolean z2, boolean z3, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            z2 = true;
        }
        if ((i2 & 2) != 0) {
            z3 = true;
        }
        controlCenterExpandController.hidePanel(z2, z3);
    }

    private final void notifyAppearanceChanged(boolean z2, boolean z3) {
        Iterator<T> it = this.callbacks.iterator();
        while (it.hasNext()) {
            ((PanelExpandController.Callback) it.next()).onAppearanceChanged(z2, z3);
        }
    }

    private final void notifyExpandHeightChanged(float f2, float f3, boolean z2, boolean z3) {
        Iterator<T> it = this.callbacks.iterator();
        while (it.hasNext()) {
            ((PanelExpandController.Callback) it.next()).onExpandHeightChanged(f2, f3, z2, z3);
        }
    }

    private final void notifyExpansionChanged(float f2) {
        Iterator<T> it = this.callbacks.iterator();
        while (it.hasNext()) {
            ((PanelExpandController.Callback) it.next()).onExpansionChanged(f2);
        }
    }

    private final void notifyVisibleChanged(boolean z2) {
        Iterator<T> it = this.callbacks.iterator();
        while (it.hasNext()) {
            ((PanelExpandController.Callback) it.next()).onVisibleChanged(z2);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Multi-variable type inference failed */
    public static final void observer$lambda$1(ControlCenterExpandController this$0, LifecycleOwner source, Lifecycle.Event event) {
        kotlin.jvm.internal.n.g(this$0, "this$0");
        kotlin.jvm.internal.n.g(source, "source");
        kotlin.jvm.internal.n.g(event, "event");
        if (source != this$0.brightnessSliderController.get()) {
            this$0.handleAnimFinish();
        } else if (event == Lifecycle.Event.ON_START && ((ControlCenterWindowViewImpl) this$0.getView()).getLifecycle().getCurrentState() == Lifecycle.State.STARTED) {
            ((ControlCenterWindowViewImpl) this$0.getView()).notifyLifecycleStateChanged(TAG, Lifecycle.State.RESUMED);
        }
    }

    public static /* synthetic */ void onExpandChange$default(ControlCenterExpandController controlCenterExpandController, float f2, float f3, boolean z2, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            f3 = 0.0f;
        }
        if ((i2 & 4) != 0) {
            z2 = false;
        }
        controlCenterExpandController.onExpandChange(f2, f3, z2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setExpansion(float f2) {
        this.expansion = f2;
        notifyExpansionChanged(getExpansion());
    }

    private final void setTracking(boolean z2) {
        if (this.tracking == z2) {
            return;
        }
        this.tracking = z2;
        if (z2) {
            return;
        }
        handleAnimFinish();
    }

    public static /* synthetic */ void showPanel$default(ControlCenterExpandController controlCenterExpandController, boolean z2, boolean z3, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            z2 = true;
        }
        if ((i2 & 2) != 0) {
            z3 = true;
        }
        controlCenterExpandController.showPanel(z2, z3);
    }

    /* JADX WARN: Multi-variable type inference failed */
    private final void updateThresholds() {
        if (((ControlCenterWindowViewImpl) getView()).getLifecycle().getCurrentState().isAtLeast(Lifecycle.State.STARTED)) {
            Log.w(TAG, "updating thresholds when window is not fully collapsed.");
        }
        this.expandThresh = getResources().getDimension(R.dimen.control_center_expand_thresh);
    }

    public void addCallback(PanelExpandController.Callback callback) {
        kotlin.jvm.internal.n.g(callback, "callback");
        if (this.callbacks.contains(callback)) {
            return;
        }
        this.callbacks.add(callback);
    }

    public final void changeItemVisible(boolean z2, boolean z3, boolean z4) {
        ((MainPanelController) this.mainPanelController.get()).changeItemVisible(z2, z3, z4);
    }

    public final void changePanelExpand(boolean z2, boolean z3, float f2, float f3, float f4) {
        ((MainPanelController) this.mainPanelController.get()).changePanelExpand(z2, z3, f2, f3, f4);
    }

    @Override // miui.systemui.controlcenter.utils.ControlCenterViewController
    public void dump(PrintWriter pw, String[] args) {
        kotlin.jvm.internal.n.g(pw, "pw");
        kotlin.jvm.internal.n.g(args, "args");
        pw.println("ControlCenterExpandController state:");
        pw.println("  dragState=" + this.dragState);
        pw.println("  tracking=" + this.tracking);
        pw.println("  appearance=" + getAppearance());
    }

    public boolean getAppearance() {
        return this.appearance;
    }

    public float getBlurRatio() {
        return ((BlurController) this.blurController.get()).getBlurRatio();
    }

    public float getExpandHeight() {
        return this.expandHeight;
    }

    public float getExpandThresh() {
        return this.expandThresh;
    }

    public float getExpansion() {
        return this.expansion;
    }

    public final boolean getInMirror() {
        return this.inMirror;
    }

    public final boolean getInteractive() {
        return getVisible() && (getAppearance() || this.tracking);
    }

    public float getStretchHeight() {
        return ((SpreadRowsAnimator) this.spreadRowsAnimator.get()).getExpandValues()[0];
    }

    public final boolean getSwitchedFromNPV() {
        return this.switchedFromNPV;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public boolean getVisible() {
        return ((ControlCenterWindowViewImpl) getView()).getLifecycle().getCurrentState().isAtLeast(Lifecycle.State.STARTED);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final void hidePanel(boolean z2, boolean z3) {
        if (((ControlCenterWindowViewController) this.windowViewController.get()).isCollapsed()) {
            Log.v(TAG, "control center is collapsed, ignore hide with anim: " + z2 + " with blur: " + z3);
            return;
        }
        Log.i(TAG, "hide panel " + z2 + " " + z3, new Throwable());
        this.dragState = 0;
        if (getAppearance()) {
            this.appearance = false;
            notifyAppearanceChanged(getAppearance(), z2);
        }
        setTracking(false);
        this.expandHeight = 0.0f;
        notifyExpandHeightChanged(getExpandHeight(), getExpandThresh(), false, z2);
        ((ControlCenterWindowViewImpl) getView()).notifyLifecycleStateChanged(TAG, Lifecycle.State.STARTED);
        if (((SecondaryPanelRouter) this.secondaryPanelRouter.get()).interceptHidePanel(z2, new ControlCenterExpandController$hidePanel$secondaryPanelIntercepted$1(this))) {
            Object obj = this.spreadRowsAnimator.get();
            kotlin.jvm.internal.n.f(obj, "get(...)");
            SpreadRowsAnimator.changeVisible$default((SpreadRowsAnimator) obj, false, false, false, 4, null);
            Object obj2 = this.blurController.get();
            kotlin.jvm.internal.n.f(obj2, "get(...)");
            BlurController.showBlur$default((BlurController) obj2, false, z2, z3, false, 8, null);
            ControlCenterExpandController$expansionAnimator$1.setExpandProgress$default(this.expansionAnimator, false, 0.0f, z2, 1, null);
            return;
        }
        this.pendingState = 0;
        if (!z2) {
            Object obj3 = this.spreadRowsAnimator.get();
            kotlin.jvm.internal.n.f(obj3, "get(...)");
            SpreadRowsAnimator.changeVisible$default((SpreadRowsAnimator) obj3, false, false, false, 4, null);
            Object obj4 = this.blurController.get();
            kotlin.jvm.internal.n.f(obj4, "get(...)");
            BlurController.showBlur$default((BlurController) obj4, false, false, false, false, 12, null);
            ControlCenterExpandController$expansionAnimator$1.setExpandProgress$default(this.expansionAnimator, false, 0.0f, false, 1, null);
            handleAnimFinish();
            return;
        }
        ControlCenterScenarioTracker.INSTANCE.reportCollapseAnimStart();
        Object obj5 = this.spreadRowsAnimator.get();
        kotlin.jvm.internal.n.f(obj5, "get(...)");
        SpreadRowsAnimator.changeVisible$default((SpreadRowsAnimator) obj5, false, true, false, 4, null);
        Object obj6 = this.blurController.get();
        kotlin.jvm.internal.n.f(obj6, "get(...)");
        BlurController.showBlur$default((BlurController) obj6, false, true, z3, false, 8, null);
        SpreadRowsAnimator spreadRowsAnimator = (SpreadRowsAnimator) this.spreadRowsAnimator.get();
        MainPanelController.Companion companion = MainPanelController.Companion;
        spreadRowsAnimator.changeExpand(companion.getLowEndAnim() ? getExpandThresh() : 0.0f, (companion.getLowEndAnim() || !z3) ? getExpandThresh() : 0.0f, true, true);
        ControlCenterExpandController$expansionAnimator$1.setExpandProgress$default(this.expansionAnimator, false, 0.0f, false, 5, null);
        ((ControlCenterWindowViewImpl) getView()).requestBlockTouch(TAG, true);
    }

    public final boolean isMainPanelCollapsing() {
        return getBlurRatio() < 1.0f && !this.inMirror;
    }

    public final void notifyBlurRatioChanged(float f2) {
        Iterator<T> it = this.callbacks.iterator();
        while (it.hasNext()) {
            ((PanelExpandController.Callback) it.next()).onBlurRatioChanged(f2);
        }
    }

    public final void notifyStretchHeightChanged(float f2) {
        Iterator<T> it = this.callbacks.iterator();
        while (it.hasNext()) {
            ((PanelExpandController.Callback) it.next()).onStretchHeightChanged(c1.f.b(f2, 0.0f));
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // miui.systemui.util.ViewController
    public void onCreate() {
        super.onCreate();
        updateThresholds();
        ((BrightnessSliderController) this.brightnessSliderController.get()).getLifecycle().addObserver(this.observer);
        ((BlurController) this.blurController.get()).getLifecycle().addObserver(this.observer);
        this.expansionAnimator.getLifecycle().addObserver(this.observer);
        ControlCenterWindowViewImpl controlCenterWindowViewImpl = (ControlCenterWindowViewImpl) getView();
        AbstractC0369g.b(LifecycleOwnerKt.getLifecycleScope(controlCenterWindowViewImpl), null, null, new ControlCenterExpandController$onCreate$1$1(controlCenterWindowViewImpl, this, null), 3, null);
    }

    @Override // miui.systemui.controlcenter.utils.ControlCenterViewController
    public void onDestroy() {
        super.onDestroy();
        this.expansionAnimator.cleanAnim();
    }

    public final void onExpandCancel() {
        Log.w(TAG, "on expand canceled");
        this.dragState = 0;
        this.expandHeight = getAppearance() ? getExpandThresh() : 0.0f;
        notifyExpandHeightChanged(getExpandHeight(), getExpandThresh(), false, true);
        if (this.pendingState == 0) {
            Object obj = this.spreadRowsAnimator.get();
            kotlin.jvm.internal.n.f(obj, "get(...)");
            SpreadRowsAnimator.changeExpand$default((SpreadRowsAnimator) obj, getAppearance() ? getExpandThresh() : 0.0f, 0.0f, true, true, 2, null);
        }
        setTracking(false);
    }

    public final void onExpandChange(float f2, float f3, boolean z2) {
        this.dragState = 3;
        ControlCenterScenarioTracker controlCenterScenarioTracker = ControlCenterScenarioTracker.INSTANCE;
        controlCenterScenarioTracker.sendControlCenterExpandingFailedEvent(z2, this.windowLayoutFinished);
        float expandHeight = getExpandHeight();
        if ((this.pendingState == 0 && this.windowLayoutFinished) || MainPanelController.Companion.getLowEndAnim()) {
            this.expandHeight = getExpandHeight() + f2;
            expandHeight = getExpandHeight();
            notifyExpandHeightChanged(getExpandHeight(), getExpandThresh(), true, true);
            if (z2 && getExpandHeight() >= 0.0f && getExpandHeight() < getExpandThresh()) {
                expandHeight = c1.f.b(f3, getExpandHeight());
            }
        }
        boolean z3 = expandHeight >= getExpandThresh();
        float fH = c1.f.h(expandHeight, 0.0f, getExpandThresh()) / getExpandThresh();
        float fB = c1.f.b(expandHeight, 0.0f);
        MainPanelController.Companion companion = MainPanelController.Companion;
        Log.v(TAG, "on expand change " + expandHeight + " " + z2 + " " + companion.getLowEndAnim() + " " + f3 + " " + getExpandHeight() + " " + f2 + " " + this.pendingState + " " + this.windowLayoutFinished + " " + z3 + " " + fH + " " + getAppearance() + " ");
        if (!getAppearance() && getExpandHeight() == 0.0f && this.pendingState == 1) {
            controlCenterScenarioTracker.reportExpandAnimStart();
        } else if (!z3 && getAppearance()) {
            controlCenterScenarioTracker.reportCollapseAnimStart();
        }
        ControlCenterExpandController$expansionAnimator$1.setExpandProgress$default(this.expansionAnimator, false, fH, false, 5, null);
        if (getAppearance() != z3) {
            this.appearance = z3;
            if (z3) {
                int i2 = this.pendingState;
                if (i2 == 0) {
                    ((MainPanelController) this.mainPanelController.get()).getTouchController().setForceInterceptUntilStop(false);
                    Object obj = this.spreadRowsAnimator.get();
                    kotlin.jvm.internal.n.f(obj, "get(...)");
                    SpreadRowsAnimator.changeVisible$default((SpreadRowsAnimator) obj, true, true, false, 4, null);
                } else if (i2 == 1) {
                    this.pendingState = 3;
                }
            } else {
                ((MainPanelController) this.mainPanelController.get()).getTouchController().setForceInterceptUntilStop(true);
                Object obj2 = this.spreadRowsAnimator.get();
                kotlin.jvm.internal.n.f(obj2, "get(...)");
                SpreadRowsAnimator.changeVisible$default((SpreadRowsAnimator) obj2, false, true, false, 4, null);
            }
            notifyAppearanceChanged(getAppearance(), true);
        }
        if (this.pendingState == 0 && this.windowLayoutFinished) {
            Object obj3 = this.spreadRowsAnimator.get();
            kotlin.jvm.internal.n.f(obj3, "get(...)");
            SpreadRowsAnimator spreadRowsAnimator = (SpreadRowsAnimator) obj3;
            if (companion.getLowEndAnim()) {
                fB = c1.f.b(fB, getExpandThresh());
            }
            SpreadRowsAnimator.changeExpand$default(spreadRowsAnimator, fB, 0.0f, false, true, 2, null);
            Object obj4 = this.blurController.get();
            kotlin.jvm.internal.n.f(obj4, "get(...)");
            BlurController.setBlurRatio$default((BlurController) obj4, fH, false, 2, null);
        }
        setTracking(true);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final void onExpandFinish(float f2) {
        Log.v(TAG, "on expand finish " + f2 + " " + this.dragState + " " + getAppearance());
        if (getAppearance()) {
            ((ControlCenterWindowViewImpl) getView()).announceForAccessibility(getContext().getString(R.string.qs_control_header_tiles_title));
        }
        this.dragState = 0;
        this.expandHeight = getAppearance() ? getExpandThresh() : 0.0f;
        notifyExpandHeightChanged(getExpandHeight(), getExpandThresh(), false, true);
        this.lastVelocity = f2;
        if (this.pendingState == 0) {
            ((SpreadRowsAnimator) this.spreadRowsAnimator.get()).changeExpand(MainPanelController.Companion.getLowEndAnim() ? c1.f.b(getExpandHeight(), getExpandThresh()) : getExpandHeight(), getAppearance() ? getExpandThresh() : 0.0f, true, true);
        }
        Object obj = this.blurController.get();
        kotlin.jvm.internal.n.f(obj, "get(...)");
        BlurController.showBlur$default((BlurController) obj, getAppearance(), true, true, false, 8, null);
        ControlCenterExpandController$expansionAnimator$1.setExpandProgress$default(this.expansionAnimator, getAppearance(), 0.0f, false, 6, null);
        setTracking(false);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // miui.systemui.controlcenter.utils.ControlCenterViewController
    public void onStart() {
        this.pendingState = 1;
        ((ControlCenterWindowViewImpl) getView()).getViewTreeObserver().addOnGlobalLayoutListener(this.onGlobalLayoutListener);
        ((SpreadRowsAnimator) this.spreadRowsAnimator.get()).changeExpand(getExpandThresh(), 0.0f, false, false);
        notifyVisibleChanged(true);
    }

    @Override // miui.systemui.controlcenter.utils.ControlCenterViewController
    public void onStop() {
        setSwitchedFromNPV(false);
        this.windowLayoutFinished = false;
        this.expandHeight = 0.0f;
        Object obj = this.spreadRowsAnimator.get();
        kotlin.jvm.internal.n.f(obj, "get(...)");
        SpreadRowsAnimator.changeExpand$default((SpreadRowsAnimator) obj, 0.0f, 0.0f, false, false, 2, null);
        notifyVisibleChanged(false);
        notifyExpandHeightChanged(getExpandHeight(), getExpandThresh(), false, false);
    }

    public void removeCallback(PanelExpandController.Callback callback) {
        kotlin.jvm.internal.n.g(callback, "callback");
        this.callbacks.remove(callback);
    }

    public final void setInMirror(boolean z2) {
        this.inMirror = z2;
    }

    /* JADX WARN: Type inference failed for: r1v1, types: [android.view.View] */
    public final void setSwitchedFromNPV(boolean z2) {
        if (this.switchedFromNPV == z2) {
            return;
        }
        this.switchedFromNPV = z2;
        MiBlurCompat.setMixEffectEnabledCompat(getView(), !z2);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r2v0, types: [android.view.View] */
    public final void showPanel(boolean z2, boolean z3) {
        Log.i(TAG, "show panel " + z2 + " " + z3 + " " + MiBlurCompat.getMixEffectEnabledCompat(getView()));
        if (!z3) {
            setSwitchedFromNPV(true);
        }
        if (((ControlCenterWindowViewImpl) getView()).getLifecycle().getCurrentState().isAtLeast(Lifecycle.State.RESUMED)) {
            return;
        }
        if (!getAppearance()) {
            this.appearance = true;
            notifyAppearanceChanged(getAppearance(), z2);
        }
        if (getAppearance()) {
            ((ControlCenterWindowViewImpl) getView()).announceForAccessibility(getContext().getString(R.string.qs_control_header_tiles_title));
        }
        Lifecycle.State currentState = ((ControlCenterWindowViewImpl) getView()).getLifecycle().getCurrentState();
        Lifecycle.State state = Lifecycle.State.STARTED;
        boolean zIsAtLeast = currentState.isAtLeast(state);
        ((ControlCenterWindowViewImpl) getView()).notifyLifecycleStateChanged(TAG, state);
        if (!zIsAtLeast) {
            this.expandHeight = getExpandThresh();
            notifyExpandHeightChanged(getExpandHeight(), getExpandThresh(), false, z2);
            ((SpreadRowsAnimator) this.spreadRowsAnimator.get()).changeExpand(getExpandThresh(), z3 ? 0.0f : getExpandThresh(), false, false);
        }
        boolean zInterceptShowPanel = ((SecondaryPanelRouter) this.secondaryPanelRouter.get()).interceptShowPanel(z2, new ControlCenterExpandController$showPanel$secondaryPanelIntercepted$1(this));
        Log.v(TAG, "show panel with anim: " + z2 + " with blur: " + z3 + " state: " + ((ControlCenterWindowViewImpl) getView()).getLifecycle().getCurrentState() + " " + zInterceptShowPanel);
        if (z2) {
            if (!zInterceptShowPanel) {
                this.pendingState = 2;
            }
            Object obj = this.blurController.get();
            kotlin.jvm.internal.n.f(obj, "get(...)");
            BlurController.showBlur$default((BlurController) obj, true, true, z3, false, 8, null);
            Object obj2 = this.spreadRowsAnimator.get();
            kotlin.jvm.internal.n.f(obj2, "get(...)");
            SpreadRowsAnimator.changeExpand$default((SpreadRowsAnimator) obj2, getExpandThresh(), 0.0f, false, true, 2, null);
            ControlCenterExpandController$expansionAnimator$1.setExpandProgress$default(this.expansionAnimator, false, 1.0f, false, 5, null);
            return;
        }
        this.pendingState = 0;
        Object obj3 = this.blurController.get();
        kotlin.jvm.internal.n.f(obj3, "get(...)");
        BlurController.showBlur$default((BlurController) obj3, true, false, z3, false, 8, null);
        ControlCenterExpandController$expansionAnimator$1.setExpandProgress$default(this.expansionAnimator, false, 1.0f, false, 1, null);
        if (!zInterceptShowPanel) {
            Object obj4 = this.spreadRowsAnimator.get();
            kotlin.jvm.internal.n.f(obj4, "get(...)");
            SpreadRowsAnimator.changeVisible$default((SpreadRowsAnimator) obj4, true, false, false, 4, null);
        }
        handleAnimFinish();
    }
}
