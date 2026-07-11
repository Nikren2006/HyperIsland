package miui.systemui.controlcenter.panel.secondary;

import android.annotation.SuppressLint;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;
import com.android.systemui.plugins.miui.controlcenter.ControlCenterSecondary;
import kotlin.jvm.internal.n;
import miui.systemui.controlcenter.dagger.ControlCenterScope;
import miui.systemui.controlcenter.dagger.qualifiers.Qualifiers;
import miui.systemui.controlcenter.databinding.ControlCenterSecondaryBinding;
import miui.systemui.controlcenter.panel.SecondaryPanelTouchController;
import miui.systemui.controlcenter.panel.TouchController;
import miui.systemui.controlcenter.utils.ControlCenterViewController;
import miui.systemui.controlcenter.widget.BrightnessPanelRootView;
import miui.systemui.controlcenter.widget.MaxHeightFrameLayout;
import miui.systemui.controlcenter.widget.MaxHeightLinearLayout;
import miui.systemui.controlcenter.widget.NonTouchableFrameLayout;
import miui.systemui.controlcenter.windowview.ControlCenterWindowViewImpl;
import miui.systemui.util.CommonUtils;

/* JADX INFO: loaded from: classes.dex */
@ControlCenterScope
public final class SecondaryContainerController extends ControlCenterViewController<ControlCenterWindowViewImpl> {
    private final ControlCenterSecondary controlCenterSecondary;
    private final ControlCenterSecondaryBinding secondaryBinding;
    private final SecondaryPanelTouchController touchController;
    private final ControlCenterWindowViewImpl windowView;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public SecondaryContainerController(@Qualifiers.WindowView ControlCenterWindowViewImpl windowView, ControlCenterSecondary controlCenterSecondary, ControlCenterSecondaryBinding secondaryBinding, SecondaryPanelTouchController touchController) {
        super(windowView);
        n.g(windowView, "windowView");
        n.g(controlCenterSecondary, "controlCenterSecondary");
        n.g(secondaryBinding, "secondaryBinding");
        n.g(touchController, "touchController");
        this.windowView = windowView;
        this.controlCenterSecondary = controlCenterSecondary;
        this.secondaryBinding = secondaryBinding;
        this.touchController = touchController;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean onCreate$lambda$5$lambda$1(SecondaryContainerController this$0, View view, MotionEvent motionEvent) {
        n.g(this$0, "this$0");
        SecondaryPanelTouchController secondaryPanelTouchController = this$0.touchController;
        n.d(motionEvent);
        return TouchController.handleMotionEvent$default(secondaryPanelTouchController, motionEvent, false, true, 2, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onCreate$lambda$5$lambda$2(View view) {
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onCreate$lambda$5$lambda$3(View view) {
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onCreate$lambda$5$lambda$4(View view) {
    }

    public final View getSecondaryBlurProvider() {
        return this.controlCenterSecondary.getSecondaryBlurProvider();
    }

    public final FrameLayout getSecondaryContainer() {
        return this.controlCenterSecondary.getSecondaryContainer();
    }

    public final FrameLayout getSecondaryContainerParent() {
        return this.controlCenterSecondary.getSecondaryContainerParent();
    }

    @Override // miui.systemui.util.ViewController
    @SuppressLint({"ClickableViewAccessibility"})
    public void onCreate() {
        super.onCreate();
        CommonUtils commonUtils = CommonUtils.INSTANCE;
        commonUtils.setGone(getSecondaryContainerParent());
        commonUtils.setGone(getSecondaryBlurProvider());
        FrameLayout secondaryContainer = getSecondaryContainer();
        secondaryContainer.removeAllViews();
        secondaryContainer.addView(this.secondaryBinding.getRoot());
        ControlCenterSecondaryBinding controlCenterSecondaryBinding = this.secondaryBinding;
        controlCenterSecondaryBinding.brightnessPanel.brightnessContainer.setLifecycle(this.windowView.getLifecycle());
        controlCenterSecondaryBinding.getRoot().setOnTouchListener(new View.OnTouchListener() { // from class: miui.systemui.controlcenter.panel.secondary.a
            @Override // android.view.View.OnTouchListener
            public final boolean onTouch(View view, MotionEvent motionEvent) {
                return SecondaryContainerController.onCreate$lambda$5$lambda$1(this.f5455a, view, motionEvent);
            }
        });
        controlCenterSecondaryBinding.detailPanel.detailContainer.setOnClickListener(new View.OnClickListener() { // from class: miui.systemui.controlcenter.panel.secondary.b
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                SecondaryContainerController.onCreate$lambda$5$lambda$2(view);
            }
        });
        controlCenterSecondaryBinding.mediaPanel.mediaContainer.setOnClickListener(new View.OnClickListener() { // from class: miui.systemui.controlcenter.panel.secondary.c
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                SecondaryContainerController.onCreate$lambda$5$lambda$3(view);
            }
        });
        controlCenterSecondaryBinding.brightnessPanel.brightnessContainer.setOnClickListener(new View.OnClickListener() { // from class: miui.systemui.controlcenter.panel.secondary.d
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                SecondaryContainerController.onCreate$lambda$5$lambda$4(view);
            }
        });
    }

    @Override // miui.systemui.controlcenter.utils.ControlCenterViewController
    public void onDestroy() {
        super.onDestroy();
        CommonUtils commonUtils = CommonUtils.INSTANCE;
        commonUtils.setGone(getSecondaryContainerParent());
        commonUtils.setGone(getSecondaryBlurProvider());
        getSecondaryContainer().removeAllViews();
        ControlCenterSecondaryBinding controlCenterSecondaryBinding = this.secondaryBinding;
        controlCenterSecondaryBinding.getRoot().setOnClickListener(null);
        controlCenterSecondaryBinding.detailPanel.detailContainer.setOnClickListener(null);
        controlCenterSecondaryBinding.mediaPanel.mediaContainer.setOnClickListener(null);
        controlCenterSecondaryBinding.brightnessPanel.brightnessContainer.setOnClickListener(null);
    }

    @Override // miui.systemui.controlcenter.utils.ControlCenterViewController
    public void onPause() {
        super.onPause();
        this.secondaryBinding.brightnessPanel.brightnessContainer.requestDisallowInterceptTouchEvent(false);
    }

    @Override // miui.systemui.controlcenter.utils.ControlCenterViewController
    public void onStop() {
        super.onStop();
        CommonUtils commonUtils = CommonUtils.INSTANCE;
        commonUtils.setGone(getSecondaryContainerParent());
        commonUtils.setGone(getSecondaryBlurProvider());
        ControlCenterSecondaryBinding controlCenterSecondaryBinding = this.secondaryBinding;
        NonTouchableFrameLayout fakeContainer = controlCenterSecondaryBinding.fakeContainer;
        n.f(fakeContainer, "fakeContainer");
        commonUtils.setGone(fakeContainer);
        MaxHeightLinearLayout root = controlCenterSecondaryBinding.detailPanel.getRoot();
        n.f(root, "getRoot(...)");
        commonUtils.setGone(root);
        MaxHeightFrameLayout root2 = controlCenterSecondaryBinding.mediaPanel.getRoot();
        n.f(root2, "getRoot(...)");
        commonUtils.setGone(root2);
        BrightnessPanelRootView root3 = controlCenterSecondaryBinding.brightnessPanel.getRoot();
        n.f(root3, "getRoot(...)");
        commonUtils.setGone(root3);
        miui.systemui.widget.FrameLayout root4 = controlCenterSecondaryBinding.volumePanel.getRoot();
        n.f(root4, "getRoot(...)");
        commonUtils.setGone(root4);
    }
}
