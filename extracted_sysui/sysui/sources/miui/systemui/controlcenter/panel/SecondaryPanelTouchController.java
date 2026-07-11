package miui.systemui.controlcenter.panel;

import E0.a;
import android.annotation.SuppressLint;
import android.view.MotionEvent;
import android.view.WindowInsets;
import c1.f;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.n;
import miui.systemui.controlcenter.ConfigUtils;
import miui.systemui.controlcenter.R;
import miui.systemui.controlcenter.dagger.ControlCenterScope;
import miui.systemui.controlcenter.dagger.qualifiers.Qualifiers;
import miui.systemui.controlcenter.utils.ControlCenterUtils;
import miui.systemui.controlcenter.utils.ControlCenterViewController;
import miui.systemui.controlcenter.windowview.ControlCenterWindowViewController;
import miui.systemui.controlcenter.windowview.ControlCenterWindowViewImpl;

/* JADX INFO: loaded from: classes.dex */
@ControlCenterScope
@SuppressLint({"ClickableViewAccessibility"})
public final class SecondaryPanelTouchController extends ControlCenterViewController<ControlCenterWindowViewImpl> implements TouchController {
    private static final float COLLAPSE_THRESH = -100.0f;
    public static final Companion Companion = new Companion(null);
    private int bottomInset;
    private int collapseSpaceHeight;
    private float collapseThresh;
    private float initX;
    private float initY;
    private boolean intercepted;
    private final ControlCenterWindowViewImpl windowView;
    private final a windowViewController;

    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public SecondaryPanelTouchController(@Qualifiers.WindowView ControlCenterWindowViewImpl windowView, a windowViewController) {
        super(windowView);
        n.g(windowView, "windowView");
        n.g(windowViewController, "windowViewController");
        this.windowView = windowView;
        this.windowViewController = windowViewController;
        this.collapseThresh = COLLAPSE_THRESH;
    }

    private final boolean getDownInBottomSpace() {
        return ((float) this.collapseSpaceHeight) > ((float) ((ControlCenterWindowViewController) this.windowViewController.get()).getScreenHeight()) - this.initY;
    }

    private final void setBottomInset(int i2) {
        if (this.bottomInset == i2) {
            return;
        }
        this.bottomInset = i2;
        updateCollapseSpace();
    }

    private final void updateCollapseSpace() {
        this.collapseSpaceHeight = f.c(this.bottomInset, getResources().getDimensionPixelSize(R.dimen.minimum_collpase_height));
    }

    private final void updateResources() {
        this.collapseThresh = -getResources().getDimension(R.dimen.control_center_collapse_thresh);
    }

    @Override // miui.systemui.controlcenter.panel.TouchController
    public boolean handleMotionEvent(MotionEvent event) {
        n.g(event, "event");
        return handleMotionEvent(event, false, false);
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
        updateResources();
    }

    @Override // miui.systemui.controlcenter.utils.ControlCenterViewController
    public void onDestroy() {
        super.onDestroy();
    }

    @Override // miui.systemui.controlcenter.panel.TouchController
    public boolean onInterceptTouchEvent(MotionEvent event) {
        n.g(event, "event");
        int actionMasked = event.getActionMasked();
        if (actionMasked == 0) {
            this.initX = event.getRawX();
            this.initY = event.getRawY();
            this.intercepted = false;
        } else if (actionMasked == 2 && getDownInBottomSpace() && ControlCenterUtils.INSTANCE.moveAccept(event.getRawX() - this.initX, event.getRawY() - this.initY, ((ControlCenterWindowViewController) this.windowViewController.get()).getTouchSlop())) {
            this.intercepted = true;
        }
        return this.intercepted;
    }

    @Override // miui.systemui.controlcenter.panel.TouchController
    public boolean handleMotionEvent(MotionEvent event, boolean z2, boolean z3) {
        n.g(event, "event");
        int actionMasked = event.getActionMasked();
        if (actionMasked != 0) {
            if (actionMasked == 1) {
                float rawX = event.getRawX() - this.initX;
                float rawY = event.getRawY() - this.initY;
                if (getDownInBottomSpace() && Math.abs(rawX) < Math.abs(rawY) && rawY < this.collapseThresh) {
                    ((ControlCenterWindowViewController) this.windowViewController.get()).hidePanel(true, true);
                } else if (z3) {
                    ((ControlCenterWindowViewController) this.windowViewController.get()).getSecondaryPanelRouter().routeToMain();
                }
            }
        } else if (z3) {
            this.initX = event.getRawX();
            this.initY = event.getRawY();
        }
        return true;
    }
}
