package miui.systemui.controlcenter.windowview;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.Configuration;
import android.util.AttributeSet;
import android.util.Log;
import android.view.ContextThemeWrapper;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowInsets;
import android.widget.FrameLayout;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleEventObserver;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LifecycleRegistry;
import com.android.systemui.plugins.statusbar.StatusBarStateController;
import kotlin.jvm.internal.DefaultConstructorMarker;
import miui.systemui.controlcenter.R;
import miui.systemui.controlcenter.dagger.ControlCenterViewComponent;
import miui.systemui.controlcenter.panel.SecondaryPanelRouter;
import miui.systemui.controlcenter.panel.TouchController;
import miui.systemui.controlcenter.panel.main.MainPanelController;
import miui.systemui.controlcenter.panel.main.qs.QSCardsController;
import miui.systemui.controlcenter.panel.main.qs.QSListController;
import miui.systemui.controlcenter.utils.ControlCenterUtils;
import miui.systemui.util.ThemeUtils;

/* JADX INFO: loaded from: classes.dex */
@SuppressLint({"ViewConstructor"})
public final class ControlCenterWindowViewImpl extends FrameLayout implements LifecycleOwner {
    public static final Companion Companion = new Companion(null);
    private static final String TAG = "ControlCenterWindowViewImpl";
    private boolean allowInterceptSwitchEvent;
    private boolean blockPointerDown;
    private boolean blockTouch;
    private final ControlCenterViewComponent.Factory componentFactory;
    private TouchController currentTouchController;
    private boolean ignoreExternalMotionEvent;
    private boolean interactiveOnDown;
    private final LifecycleRegistry lifecycleRegistry;
    private StatusBarStateController statusBarStateController;
    private final ControlCenterViewComponent viewComponent;
    public ControlCenterWindowViewController windowViewController;

    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ControlCenterWindowViewImpl(Context context, AttributeSet attributeSet, ControlCenterViewComponent.Factory componentFactory) {
        super(new ContextThemeWrapper(context, R.style.ControlCenter), attributeSet);
        kotlin.jvm.internal.n.g(context, "context");
        kotlin.jvm.internal.n.g(componentFactory, "componentFactory");
        this.componentFactory = componentFactory;
        ControlCenterViewComponent controlCenterViewComponentCreate = componentFactory.create(this);
        kotlin.jvm.internal.n.f(controlCenterViewComponentCreate, "create(...)");
        this.viewComponent = controlCenterViewComponentCreate;
        LifecycleRegistry lifecycleRegistry = new LifecycleRegistry(this);
        lifecycleRegistry.addObserver(new LifecycleEventObserver() { // from class: miui.systemui.controlcenter.windowview.ControlCenterWindowViewImpl$lifecycleRegistry$1$1

            public /* synthetic */ class WhenMappings {
                public static final /* synthetic */ int[] $EnumSwitchMapping$0;

                static {
                    int[] iArr = new int[Lifecycle.Event.values().length];
                    try {
                        iArr[Lifecycle.Event.ON_STOP.ordinal()] = 1;
                    } catch (NoSuchFieldError unused) {
                    }
                    $EnumSwitchMapping$0 = iArr;
                }
            }

            @Override // androidx.lifecycle.LifecycleEventObserver
            public void onStateChanged(LifecycleOwner owner, Lifecycle.Event event) {
                kotlin.jvm.internal.n.g(owner, "owner");
                kotlin.jvm.internal.n.g(event, "event");
                if (WhenMappings.$EnumSwitchMapping$0[event.ordinal()] == 1) {
                    this.this$0.requestBlockTouch("ControlCenterWindowViewImpl", false);
                    this.this$0.requestBlockPointerDown("ControlCenterWindowViewImpl", false);
                    this.this$0.setIgnoreExternalMotionEvent(false);
                }
            }
        });
        this.lifecycleRegistry = lifecycleRegistry;
    }

    private final MainPanelController getMainPanelController() {
        return getWindowViewController().getMainPanelController();
    }

    private final SecondaryPanelRouter getSecondaryPanelRouter() {
        return getWindowViewController().getSecondaryPanelRouter();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void setIgnoreExternalMotionEvent(boolean z2) {
        if (getLifecycle().getCurrentState() == Lifecycle.State.CREATED && z2) {
            Log.e(TAG, "requesting block external event when not expanded, ignore that.", new Throwable());
        } else {
            this.ignoreExternalMotionEvent = z2;
        }
    }

    public final void destroy() {
        notifyLifecycleStateChanged(TAG, Lifecycle.State.DESTROYED);
    }

    @Override // android.view.ViewGroup, android.view.View
    public void dispatchConfigurationChanged(Configuration configuration) {
        ThemeUtils.INSTANCE.dispatchConfigurationChanged(getContext(), configuration);
        getWindowViewController().onConfigurationChanged(configuration);
        super.dispatchConfigurationChanged(configuration);
    }

    @Override // android.view.ViewGroup, android.view.View
    public boolean dispatchTouchEvent(MotionEvent motionEvent) {
        if (motionEvent != null && motionEvent.getActionMasked() == 0) {
            this.interactiveOnDown = getWindowViewController().getExpandController().getInteractive() || (this.lifecycleRegistry.getCurrentState().isAtLeast(Lifecycle.State.STARTED) && !getSecondaryPanelRouter().getInMainPanel());
        }
        Log.d(TAG, "dispatchTouchEvent " + ControlCenterUtils.INSTANCE.getEventActionDesc(motionEvent != null ? Integer.valueOf(motionEvent.getActionMasked()) : null) + " " + this.interactiveOnDown);
        if (motionEvent != null && motionEvent.getActionMasked() == 0) {
            setAllowInterceptSwitchEvent(false);
        }
        if (!this.interactiveOnDown) {
            return false;
        }
        if (motionEvent == null) {
            return super.dispatchTouchEvent(motionEvent);
        }
        getMainPanelController().handlePanelTouchEvent(motionEvent);
        int actionMasked = motionEvent.getActionMasked();
        if (actionMasked == 4) {
            Log.w(TAG, "received an outside event, maybe caused by recently installed.");
            return false;
        }
        if (actionMasked == 5 && this.blockPointerDown) {
            return true;
        }
        if (!this.ignoreExternalMotionEvent) {
            Log.d(TAG, "received own event, ignore external event.");
            setIgnoreExternalMotionEvent(true);
        }
        super.dispatchTouchEvent(motionEvent);
        return true;
    }

    @Override // android.view.ViewGroup, android.view.View
    public void dispatchWindowFocusChanged(boolean z2) {
        if (this.lifecycleRegistry.getCurrentState().isAtLeast(Lifecycle.State.RESUMED)) {
            super.dispatchWindowFocusChanged(z2);
        }
    }

    public final boolean getAllowInterceptSwitchEvent() {
        return this.allowInterceptSwitchEvent;
    }

    public final boolean getBlockTouch$miui_controlcenter_release() {
        return this.blockTouch;
    }

    public final boolean getIgnoreExternalMotionEvent$miui_controlcenter_release() {
        return this.ignoreExternalMotionEvent;
    }

    @Override // androidx.lifecycle.LifecycleOwner
    public Lifecycle getLifecycle() {
        return this.lifecycleRegistry;
    }

    public final ControlCenterViewComponent getViewComponent() {
        return this.viewComponent;
    }

    public final ControlCenterWindowViewController getWindowViewController() {
        ControlCenterWindowViewController controlCenterWindowViewController = this.windowViewController;
        if (controlCenterWindowViewController != null) {
            return controlCenterWindowViewController;
        }
        kotlin.jvm.internal.n.w("windowViewController");
        return null;
    }

    public final boolean handleExpandTouchEvent(MotionEvent event) {
        kotlin.jvm.internal.n.g(event, "event");
        if (event.getActionMasked() == 0) {
            setAllowInterceptSwitchEvent(false);
        }
        return handleMotionEvent(event, true);
    }

    public final boolean handleExternalTouchEvent(MotionEvent event) {
        kotlin.jvm.internal.n.g(event, "event");
        if (event.getActionMasked() == 0) {
            setAllowInterceptSwitchEvent(false);
        }
        if (!this.ignoreExternalMotionEvent) {
            Log.d(TAG, "received own event, ignore external event.");
            setIgnoreExternalMotionEvent(true);
        }
        return handleMotionEvent(event, false);
    }

    /* JADX WARN: Removed duplicated region for block: B:29:0x00d1  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final boolean handleMotionEvent(android.view.MotionEvent r13, boolean r14) {
        /*
            Method dump skipped, instruction units count: 267
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: miui.systemui.controlcenter.windowview.ControlCenterWindowViewImpl.handleMotionEvent(android.view.MotionEvent, boolean):boolean");
    }

    @Override // android.view.View
    public boolean hasOverlappingRendering() {
        return false;
    }

    public final void notifyLifecycleStateChanged(String tag, Lifecycle.State state) {
        kotlin.jvm.internal.n.g(tag, "tag");
        kotlin.jvm.internal.n.g(state, "state");
        if (this.lifecycleRegistry.getCurrentState() == Lifecycle.State.DESTROYED) {
            Log.w(TAG, "lifecycle state is DESTROYED, could not change to " + state + " called by " + tag);
            return;
        }
        if (this.lifecycleRegistry.getCurrentState() != state) {
            Log.v(TAG, "lifecycle state changed from " + this.lifecycleRegistry.getCurrentState() + " to " + state + ", called by " + tag);
            this.lifecycleRegistry.setCurrentState(state);
        }
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public void notifySubtreeAccessibilityStateChanged(View child, View source, int i2) {
        kotlin.jvm.internal.n.g(child, "child");
        kotlin.jvm.internal.n.g(source, "source");
        if (this.lifecycleRegistry.getCurrentState().isAtLeast(Lifecycle.State.RESUMED)) {
            super.notifySubtreeAccessibilityStateChanged(child, source, i2);
        }
    }

    @Override // android.view.View
    public WindowInsets onApplyWindowInsets(WindowInsets windowInsets) {
        if (windowInsets != null) {
            getWindowViewController().onApplyWindowInsets(windowInsets);
        }
        WindowInsets windowInsetsOnApplyWindowInsets = super.onApplyWindowInsets(windowInsets);
        kotlin.jvm.internal.n.f(windowInsetsOnApplyWindowInsets, "onApplyWindowInsets(...)");
        return windowInsetsOnApplyWindowInsets;
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        setVisibility(8);
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
    }

    @Override // android.view.View
    public void onFinishInflate() {
        super.onFinishInflate();
        StatusBarStateController statusBarStateController = this.viewComponent.getStatusBarStateController();
        kotlin.jvm.internal.n.f(statusBarStateController, "getStatusBarStateController(...)");
        this.statusBarStateController = statusBarStateController;
        ControlCenterWindowViewController controlCenterWindowViewController = this.viewComponent.getControlCenterWindowViewController();
        kotlin.jvm.internal.n.f(controlCenterWindowViewController, "getControlCenterWindowViewController(...)");
        setWindowViewController(controlCenterWindowViewController);
        getWindowViewController().init();
        notifyLifecycleStateChanged(TAG, Lifecycle.State.CREATED);
        ThemeUtils.INSTANCE.setUiMode(getResources().getConfiguration().uiMode);
    }

    @Override // android.view.ViewGroup
    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        Log.v(TAG, "onInterceptTouchEvent " + ControlCenterUtils.INSTANCE.getEventActionDesc(motionEvent != null ? Integer.valueOf(motionEvent.getActionMasked()) : null) + " " + this.blockTouch + " " + getSecondaryPanelRouter().getInMainPanel());
        if (this.blockTouch) {
            return true;
        }
        if (motionEvent == null) {
            return super.onInterceptTouchEvent(motionEvent);
        }
        if (motionEvent.getActionMasked() == 0) {
            this.currentTouchController = getSecondaryPanelRouter().getInMainPanel() ? getMainPanelController().getTouchController() : getSecondaryPanelRouter().getTouchController();
        }
        TouchController touchController = this.currentTouchController;
        return touchController != null ? touchController.onInterceptTouchEvent(motionEvent) : super.onInterceptTouchEvent(motionEvent);
    }

    @Override // android.view.View, android.view.KeyEvent.Callback
    public boolean onKeyDown(int i2, KeyEvent keyEvent) {
        return getLifecycle().getCurrentState().isAtLeast(Lifecycle.State.STARTED);
    }

    @Override // android.view.View, android.view.KeyEvent.Callback
    public boolean onKeyUp(int i2, KeyEvent keyEvent) {
        return getLifecycle().getCurrentState().isAtLeast(Lifecycle.State.STARTED);
    }

    @Override // android.widget.FrameLayout, android.view.View
    public void onMeasure(int i2, int i3) {
        ControlCenterWindowViewController.updateCustomDensityIfNeeded$default(getWindowViewController(), getContext().getResources().getConfiguration(), false, 2, null);
        super.onMeasure(i2, i3);
    }

    @Override // android.view.View
    @SuppressLint({"ClickableViewAccessibility"})
    public boolean onTouchEvent(MotionEvent motionEvent) {
        return handleMotionEvent(motionEvent, false);
    }

    @Override // android.view.View
    public void onWindowFocusChanged(boolean z2) {
        super.onWindowFocusChanged(z2);
        QSCardsController qsCardsController = ((QSListController) getMainPanelController().getQsController().getQsListController().get()).getQsCardsController();
        if (z2) {
            qsCardsController.restartMarquee();
        } else {
            qsCardsController.stopMarquee();
        }
    }

    public final void requestBlockPointerDown(String from, boolean z2) {
        kotlin.jvm.internal.n.g(from, "from");
        if (this.lifecycleRegistry.getCurrentState().isAtLeast(Lifecycle.State.STARTED)) {
            Log.v(TAG, "block pointer down requested from " + from + " to " + z2);
            this.blockPointerDown = z2;
            return;
        }
        if (z2) {
            Log.w(TAG, "block pointer down request is not affected.");
            return;
        }
        Log.v(TAG, "block pointer down requested from " + from + " to " + z2);
        this.blockPointerDown = z2;
    }

    public final void requestBlockTouch(String from, boolean z2) {
        kotlin.jvm.internal.n.g(from, "from");
        if (this.lifecycleRegistry.getCurrentState().isAtLeast(Lifecycle.State.STARTED)) {
            Log.v(TAG, "block touch requested from " + from + " to " + z2);
            this.blockTouch = z2;
            this.currentTouchController = null;
            return;
        }
        if (z2) {
            Log.w(TAG, "block touch request is not affected.");
            return;
        }
        Log.v(TAG, "block touch requested from " + from + " to " + z2);
        this.blockTouch = z2;
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public void requestDisallowInterceptTouchEvent(boolean z2) {
    }

    public final void setAllowInterceptSwitchEvent(boolean z2) {
        this.allowInterceptSwitchEvent = z2;
        Log.d("test111", "updating allowInterceptSwitchEvent " + z2);
    }

    public final void setBlockTouch$miui_controlcenter_release(boolean z2) {
        this.blockTouch = z2;
    }

    public final void setWindowViewController(ControlCenterWindowViewController controlCenterWindowViewController) {
        kotlin.jvm.internal.n.g(controlCenterWindowViewController, "<set-?>");
        this.windowViewController = controlCenterWindowViewController;
    }
}
