package miui.systemui.controlcenter.panel.main;

import android.util.Log;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.WindowInsets;
import androidx.lifecycle.Lifecycle;
import com.android.systemui.plugins.statusbar.StatusBarStateController;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.n;
import miui.systemui.controlcenter.ConfigUtils;
import miui.systemui.controlcenter.R;
import miui.systemui.controlcenter.dagger.ControlCenterScope;
import miui.systemui.controlcenter.dagger.qualifiers.Qualifiers;
import miui.systemui.controlcenter.events.ControlCenterEventTracker;
import miui.systemui.controlcenter.events.ControlCenterScenarioTracker;
import miui.systemui.controlcenter.panel.TouchController;
import miui.systemui.controlcenter.panel.main.MainPanelController;
import miui.systemui.controlcenter.panel.main.header.MainPanelHeaderController;
import miui.systemui.controlcenter.utils.ControlCenterUtils;
import miui.systemui.controlcenter.utils.ControlCenterViewController;
import miui.systemui.controlcenter.windowview.ControlCenterExpandController;
import miui.systemui.controlcenter.windowview.ControlCenterWindowViewController;
import miui.systemui.controlcenter.windowview.ControlCenterWindowViewImpl;
import miui.systemui.controlcenter.windowview.GestureDispatcher;
import miui.systemui.util.CommonUtils;
import systemui.plugin.eventtracking.EventTracker;

/* JADX INFO: loaded from: classes.dex */
@ControlCenterScope
public final class MainPanelTouchController extends ControlCenterViewController<ControlCenterWindowViewImpl> implements TouchController {
    public static final Companion Companion = new Companion(null);
    private static final String TAG = "MainPanelTouchController";
    private int bottomInset;
    private boolean calledScrollToTop;
    private int collapseSpaceHeight;
    private boolean expandChanging;
    private final ControlCenterExpandController expandController;
    private boolean forceInterceptUntilStop;
    private boolean forceInterceptUntilUp;
    private final GestureDispatcher gestureDispatcher;
    private final MainPanelTouchController$gestureHelper$1 gestureHelper;
    private boolean gestureHorizontal;
    private boolean hasDown;
    private final MainPanelHeaderController headerController;
    private float initX;
    private float initY;
    private boolean interceptHeadEvent;
    private float lastX;
    private float lastY;
    private final E0.a mainPanelController;
    private boolean pendingClick;
    private boolean selfEventMoveAccepted;
    private final StatusBarStateController statusBarStateController;
    private final VelocityTracker velocityTracker;
    private final E0.a windowViewController;

    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Type inference failed for: r3v1, types: [miui.systemui.controlcenter.panel.main.MainPanelTouchController$gestureHelper$1] */
    public MainPanelTouchController(@Qualifiers.WindowView final ControlCenterWindowViewImpl windowView, E0.a windowViewController, ControlCenterExpandController expandController, final GestureDispatcher gestureDispatcher, StatusBarStateController statusBarStateController, E0.a mainPanelController, MainPanelHeaderController headerController) {
        super(windowView);
        n.g(windowView, "windowView");
        n.g(windowViewController, "windowViewController");
        n.g(expandController, "expandController");
        n.g(gestureDispatcher, "gestureDispatcher");
        n.g(statusBarStateController, "statusBarStateController");
        n.g(mainPanelController, "mainPanelController");
        n.g(headerController, "headerController");
        this.windowViewController = windowViewController;
        this.expandController = expandController;
        this.gestureDispatcher = gestureDispatcher;
        this.statusBarStateController = statusBarStateController;
        this.mainPanelController = mainPanelController;
        this.headerController = headerController;
        this.gestureHelper = new GestureDispatcher.GestureHelper(windowView, gestureDispatcher) { // from class: miui.systemui.controlcenter.panel.main.MainPanelTouchController$gestureHelper$1
            {
                Boolean bool = Boolean.TRUE;
            }

            @Override // miui.systemui.controlcenter.windowview.GestureDispatcher.GestureHelper
            public boolean check(boolean z2, boolean z3) {
                Log.v("MainPanelTouchController", "GestureHelper check " + z2 + " " + z3);
                MainPanelTouchController mainPanelTouchController = this;
                mainPanelTouchController.gestureHorizontal = (z2 || mainPanelTouchController.forceInterceptUntilUp) ? false : true;
                return true;
            }

            @Override // miui.systemui.controlcenter.windowview.GestureDispatcher.GestureHelper
            public boolean intercept(boolean z2, boolean z3) {
                return this.getDownInBottomSpace() && z2 && !z3;
            }

            @Override // miui.systemui.controlcenter.windowview.GestureDispatcher.GestureHelper
            public void onTouchEvent(MotionEvent event) {
                n.g(event, "event");
            }
        };
        this.velocityTracker = VelocityTracker.obtain();
    }

    private final void computeVelocity() {
        this.velocityTracker.computeCurrentVelocity(1000, ((ControlCenterWindowViewController) this.windowViewController.get()).getMaxVelocity());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final boolean getDownInBottomSpace() {
        return ((float) this.collapseSpaceHeight) > ((float) ((ControlCenterWindowViewController) this.windowViewController.get()).getScreenHeight()) - this.initY;
    }

    private final int getPanelHeadHeight() {
        return this.headerController.getLayoutParams().height;
    }

    private final float getVelocityY() {
        computeVelocity();
        return this.velocityTracker.getYVelocity(0);
    }

    private final void setBottomInset(int i2) {
        if (this.bottomInset == i2) {
            return;
        }
        this.bottomInset = i2;
        updateCollapseSpace();
    }

    private final void updateCollapseSpace() {
        this.collapseSpaceHeight = c1.f.c(this.bottomInset, getResources().getDimensionPixelSize(R.dimen.minimum_collpase_height));
    }

    public final boolean getForceInterceptUntilStop() {
        return this.forceInterceptUntilStop;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // miui.systemui.controlcenter.panel.TouchController
    public boolean handleMotionEvent(MotionEvent event, boolean z2, boolean z3) {
        n.g(event, "event");
        ControlCenterUtils controlCenterUtils = ControlCenterUtils.INSTANCE;
        Log.v(TAG, "handleMotionEvent " + controlCenterUtils.getEventActionDesc(Integer.valueOf(event.getActionMasked())) + " " + this.gestureHorizontal + " " + this.hasDown + " " + getGestureAccept() + " " + ((MainPanelController) this.mainPanelController.get()).getModeController().getMode() + " " + getDownInBottomSpace());
        if (!getDownInBottomSpace() && (!((ControlCenterWindowViewController) this.windowViewController.get()).getSecondaryPanelRouter().getInMainPanel() || ((MainPanelController) this.mainPanelController.get()).getMode() != MainPanelController.Mode.NORMAL)) {
            return false;
        }
        this.velocityTracker.addMovement(event);
        if (((ControlCenterWindowViewImpl) getView()).getLifecycle().getCurrentState() == Lifecycle.State.CREATED) {
            this.forceInterceptUntilUp = true;
        }
        if (event.getActionMasked() != 0 && this.gestureHorizontal && !this.forceInterceptUntilUp) {
            ((ControlCenterWindowViewImpl) getView()).setAllowInterceptSwitchEvent(true);
            this.gestureDispatcher.finish();
            return false;
        }
        int actionMasked = event.getActionMasked();
        if (actionMasked == 0) {
            this.hasDown = true;
            this.initX = event.getRawX();
            this.initY = event.getRawY();
            Lifecycle.State currentState = ((ControlCenterWindowViewImpl) getView()).getLifecycle().getCurrentState();
            Lifecycle.State state = Lifecycle.State.STARTED;
            if (currentState.isAtLeast(state)) {
                this.pendingClick = true;
            }
            if (z2 && !((ControlCenterWindowViewImpl) getView()).getLifecycle().getCurrentState().isAtLeast(state)) {
                ((ControlCenterWindowViewImpl) getView()).notifyLifecycleStateChanged(TAG, state);
            }
            this.selfEventMoveAccepted = false;
            this.calledScrollToTop = false;
            this.gestureHorizontal = false;
            this.expandChanging = false;
        } else if (actionMasked == 1) {
            if (this.expandChanging) {
                this.expandController.onExpandChange(event.getRawY() - this.lastY, event.getRawY() - this.initY, true);
            }
            reset();
            this.expandController.onExpandFinish(getVelocityY());
            this.forceInterceptUntilUp = false;
            if (this.pendingClick) {
                ((ControlCenterWindowViewImpl) getView()).performClick();
                this.pendingClick = false;
            }
            this.gestureDispatcher.finish();
            this.hasDown = false;
            this.expandChanging = false;
            this.interceptHeadEvent = false;
        } else if (actionMasked != 2) {
            if (actionMasked == 3) {
                reset();
                this.expandController.onExpandFinish(getVelocityY());
                this.forceInterceptUntilUp = false;
                this.pendingClick = false;
                this.gestureDispatcher.finish();
                this.hasDown = false;
                this.expandChanging = false;
                this.interceptHeadEvent = false;
            }
        } else if (this.hasDown) {
            float rawY = event.getRawY();
            float rawX = event.getRawX() - this.initX;
            float f2 = rawY - this.initY;
            boolean zMoveAccept = controlCenterUtils.moveAccept(rawX, f2, ((ControlCenterWindowViewController) this.windowViewController.get()).getTouchSlop());
            if (!getGestureAccept() && !this.selfEventMoveAccepted && zMoveAccept) {
                boolean z4 = Math.abs(rawX) > Math.abs(f2) && !this.forceInterceptUntilUp;
                this.gestureHorizontal = z4;
                Log.d(TAG, "self event accepted " + z4 + " " + ((ControlCenterWindowViewImpl) getView()).getLifecycle().getCurrentState());
                ControlCenterEventTracker.Companion.trackControlCenterEvent(this.gestureHorizontal, CommonUtils.isLocked(this.statusBarStateController), EventTracker.Companion.getScreenType(getContext()), getContext().getResources().getConfiguration().orientation);
                this.selfEventMoveAccepted = true;
                if (this.gestureHorizontal) {
                    this.expandController.onExpandCancel();
                    ((ControlCenterWindowViewImpl) getView()).setAllowInterceptSwitchEvent(true);
                    return false;
                }
            }
            boolean zSmoothScrollToTop = (zMoveAccept && event.getRawY() - this.lastY > 0.0f && ((ControlCenterWindowViewImpl) getView()).getLifecycle().getCurrentState().isAtLeast(Lifecycle.State.STARTED)) ? ((MainPanelController) this.mainPanelController.get()).smoothScrollToTop() : true;
            if (getGestureAccept() || this.selfEventMoveAccepted) {
                ((ControlCenterWindowViewImpl) getView()).notifyLifecycleStateChanged(TAG, Lifecycle.State.STARTED);
                if (zSmoothScrollToTop) {
                    if (!this.expandChanging) {
                        float rawY2 = event.getRawY() - this.lastY;
                        if (rawY2 > 0.0f) {
                            ControlCenterScenarioTracker.INSTANCE.reportExpandEvents(true);
                        } else if (rawY2 < 0.0f) {
                            ControlCenterScenarioTracker.INSTANCE.reportCollapseEvents(true);
                        }
                        this.expandChanging = true;
                    }
                    ControlCenterExpandController.onExpandChange$default(this.expandController, event.getRawY() - this.lastY, 0.0f, false, 6, null);
                }
            }
            if (this.pendingClick && zMoveAccept) {
                this.pendingClick = false;
            }
        } else {
            this.hasDown = true;
            this.initX = event.getRawX();
            this.initY = event.getRawY();
            this.selfEventMoveAccepted = false;
            this.calledScrollToTop = false;
            this.expandChanging = false;
        }
        this.lastX = event.getRawX();
        this.lastY = event.getRawY();
        return true;
    }

    @Override // miui.systemui.controlcenter.utils.ControlCenterViewController
    public void onApplyWindowInsets(WindowInsets insets) {
        n.g(insets, "insets");
        setBottomInset(insets.getStableInsetBottom());
    }

    @Override // miui.systemui.controlcenter.utils.ControlCenterViewController, miui.systemui.controlcenter.ConfigUtils.OnConfigChangeListener
    public void onConfigurationChanged(int i2) {
        if (ConfigUtils.INSTANCE.dimensionsChanged(i2)) {
            updateCollapseSpace();
        }
    }

    @Override // miui.systemui.util.ViewController
    public void onCreate() {
        updateCollapseSpace();
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // miui.systemui.controlcenter.panel.TouchController
    public boolean onInterceptTouchEvent(MotionEvent event) {
        n.g(event, "event");
        Log.v(TAG, "onInterceptTouchEvent panel state main " + ((ControlCenterWindowViewController) this.windowViewController.get()).getExpandState() + " " + ((ControlCenterWindowViewImpl) getView()).getLifecycle().getCurrentState() + " " + this.forceInterceptUntilUp + " " + this.forceInterceptUntilStop);
        if (((ControlCenterWindowViewImpl) getView()).getLifecycle().getCurrentState() == Lifecycle.State.CREATED) {
            return true;
        }
        if (event.getActionMasked() == 0) {
            this.initX = event.getRawX();
            this.initY = event.getRawY();
            this.lastX = event.getRawX();
            this.lastY = event.getRawY();
            this.calledScrollToTop = false;
            this.gestureHorizontal = false;
            this.interceptHeadEvent = event.getY() <= ((float) getPanelHeadHeight());
        }
        if (this.forceInterceptUntilUp || this.forceInterceptUntilStop || this.interceptHeadEvent || onInterceptTouchEvent(event)) {
            return true;
        }
        this.velocityTracker.addMovement(event);
        return false;
    }

    @Override // miui.systemui.controlcenter.utils.ControlCenterViewController
    public void onStop() {
        this.forceInterceptUntilStop = false;
    }

    public final void setForceInterceptUntilStop(boolean z2) {
        this.forceInterceptUntilStop = z2;
    }
}
