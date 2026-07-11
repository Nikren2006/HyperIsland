package miui.systemui.controlcenter.panel.secondary;

import I0.m;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;
import androidx.lifecycle.Lifecycle;
import java.util.ArrayList;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.n;
import miui.systemui.controlcenter.ConfigUtils;
import miui.systemui.controlcenter.R;
import miui.systemui.controlcenter.databinding.ControlCenterSecondaryBinding;
import miui.systemui.controlcenter.events.ControlCenterScenarioTracker;
import miui.systemui.controlcenter.panel.SecondaryPanelRouter;
import miui.systemui.controlcenter.panel.main.MainPanelController;
import miui.systemui.controlcenter.panel.secondary.SecondaryParams;
import miui.systemui.controlcenter.utils.ControlCenterUtils;
import miui.systemui.controlcenter.utils.ControlCenterViewController;
import miui.systemui.controlcenter.widget.NonTouchableFrameLayout;
import miui.systemui.controlcenter.windowview.ControlCenterScreenshot;
import miui.systemui.controlcenter.windowview.ControlCenterWindowViewController;
import miui.systemui.controls.ColorUtils;
import miui.systemui.util.BoostHelper;
import miui.systemui.util.CommonUtils;
import miui.systemui.util.MiBlurCompat;
import miui.systemui.util.MiuiColorBlendToken;

/* JADX INFO: loaded from: classes.dex */
public abstract class SecondaryPanelControllerBase<P extends SecondaryParams> extends ControlCenterViewController<ConstraintLayout> implements SecondaryPanelRouter.SecondaryPanel<P>, BlurBgToView, ContentBgToView {
    private float _blurRation;
    private final SecondaryPanelAnimatorBase<P> animatorBase;
    private float blurBgRatio;
    private final ArrayList<SecondaryPanelDelegateBase<P>> childControllers;
    private Function0 completeAction;
    private final View contentBg;
    private float contentBgColor;
    private float contentBgRadius;
    private final SecondaryPanelDelegateBase<P> delegateBase;
    private final Lifecycle lifecycle;
    private final E0.a mainPanelController;
    private final SecondaryPanelControllerBase$onScreenshotListener$1 onScreenshotListener;
    private P panelParams;
    private boolean rightOrLeft;
    private final ControlCenterSecondaryBinding secondaryBinding;
    private int specificHeight;
    private int specificWidth;
    private boolean supportWideMode;
    private final String tag;
    private boolean useDefaultContainerConstraint;
    private boolean useSpecificHeight;
    private boolean useSpecificWidth;

    /* JADX WARN: Illegal instructions before constructor call */
    /* JADX WARN: Type inference failed for: r3v6, types: [miui.systemui.controlcenter.panel.secondary.SecondaryPanelControllerBase$onScreenshotListener$1] */
    public SecondaryPanelControllerBase(ControlCenterSecondaryBinding secondaryBinding, E0.a mainPanelController, SecondaryPanelDelegateBase<P> delegateBase, SecondaryPanelAnimatorBase<P> animatorBase, Lifecycle lifecycle) {
        n.g(secondaryBinding, "secondaryBinding");
        n.g(mainPanelController, "mainPanelController");
        n.g(delegateBase, "delegateBase");
        n.g(animatorBase, "animatorBase");
        n.g(lifecycle, "lifecycle");
        miui.systemui.widget.ConstraintLayout root = secondaryBinding.getRoot();
        n.f(root, "getRoot(...)");
        super(root);
        this.secondaryBinding = secondaryBinding;
        this.mainPanelController = mainPanelController;
        this.delegateBase = delegateBase;
        this.animatorBase = animatorBase;
        this.lifecycle = lifecycle;
        String simpleName = getClass().getSimpleName();
        n.f(simpleName, "getSimpleName(...)");
        this.tag = simpleName;
        this.childControllers = m.f(delegateBase);
        this.useDefaultContainerConstraint = true;
        this.rightOrLeft = true;
        this.onScreenshotListener = new ControlCenterScreenshot.OnScreenshotListener(this) { // from class: miui.systemui.controlcenter.panel.secondary.SecondaryPanelControllerBase$onScreenshotListener$1
            final /* synthetic */ SecondaryPanelControllerBase<P> this$0;

            {
                this.this$0 = this;
            }

            @Override // miui.systemui.controlcenter.windowview.ControlCenterScreenshot.OnScreenshotListener
            public void onScreenshot() {
                this.this$0.getScreenshot().addDumpMessage("secondaryContainerParentVisibility", String.valueOf(this.this$0.getSecondaryContainerParent().getVisibility()));
                ControlCenterScreenshot screenshot = this.this$0.getScreenshot();
                ViewGroup secondaryPanelContainer = this.this$0.getSecondaryPanelContainer();
                screenshot.addDumpMessage("secondaryPanelContainerVisibility", String.valueOf(secondaryPanelContainer != null ? Integer.valueOf(secondaryPanelContainer.getVisibility()) : null));
            }
        };
    }

    private final void applyMiBlur(float f2) {
        if (getDisallowApplyBlur()) {
            f2 = 0.0f;
        }
        if (f2 == this._blurRation) {
            return;
        }
        this._blurRation = f2;
        ((MainPanelController) this.mainPanelController.get()).getBlurUtilsExt().applyBlur(getBlurBg(), this._blurRation);
    }

    private final boolean getDisallowApplyBlur() {
        return this.lifecycle.getCurrentState().compareTo(Lifecycle.State.RESUMED) < 0 && !((MainPanelController) this.mainPanelController.get()).getExpandController().getAppearance();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final ControlCenterScreenshot getScreenshot() {
        return ((ControlCenterWindowViewController) ((MainPanelController) this.mainPanelController.get()).getWindowViewController().get()).getScreenshot();
    }

    private final FrameLayout getSecondaryContainer() {
        return getSecondaryContainerController().getSecondaryContainer();
    }

    private final SecondaryContainerController getSecondaryContainerController() {
        return ((MainPanelController) this.mainPanelController.get()).getSecondaryContainerController();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final FrameLayout getSecondaryContainerParent() {
        return getSecondaryContainerController().getSecondaryContainerParent();
    }

    private final SecondaryPanelRouter getSecondaryPanelRouter() {
        return (SecondaryPanelRouter) ((MainPanelController) this.mainPanelController.get()).getSecondaryPanelRouter().get();
    }

    public static /* synthetic */ void onSecondaryVisible$default(SecondaryPanelControllerBase secondaryPanelControllerBase, boolean z2, boolean z3, int i2, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: onSecondaryVisible");
        }
        if ((i2 & 2) != 0) {
            z3 = false;
        }
        secondaryPanelControllerBase.onSecondaryVisible(z2, z3);
    }

    private final void updateBlurBg() {
        View blurBg = getBlurBg();
        Context context = blurBg.getContext();
        n.f(context, "getContext(...)");
        if (ControlCenterUtils.getBackgroundBlurOpenedInDefaultTheme(context)) {
            CommonUtils.INSTANCE.setVisible(blurBg);
            MiBlurCompat.setPassWindowBlurEnabledCompat(blurBg, false);
            MiBlurCompat.setMiBackgroundBlurModeCompat(blurBg, 1);
        } else {
            CommonUtils.INSTANCE.setGone(blurBg);
            MiBlurCompat.setPassWindowBlurEnabledCompat(blurBg, false);
            MiBlurCompat.setMiBackgroundBlurModeCompat(blurBg, 0);
            MiBlurCompat.setMiViewBlurModeCompat(blurBg, 0);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    private final void updateContainerConstraint() {
        ViewGroup secondaryPanelContainer;
        int i2;
        int panelWidth;
        int panelWidth2;
        if (getUseDefaultContainerConstraint() && (secondaryPanelContainer = getSecondaryPanelContainer()) != null) {
            Log.i(this.tag, getRightOrLeft() + " " + getSupportWideMode() + " " + getUseSpecificWidth() + " " + getUseSpecificHeight());
            MainPanelController mainPanelController = (MainPanelController) this.mainPanelController.get();
            int screenWidth = ((ControlCenterWindowViewController) mainPanelController.getWindowViewController().get()).getScreenWidth();
            int specificWidth = getUseSpecificWidth() ? getSpecificWidth() : getResources().getDimensionPixelSize(R.dimen.control_center_secondary_panel_content_width);
            int specificHeight = getUseSpecificHeight() ? getSpecificHeight() : -2;
            CommonUtils commonUtils = CommonUtils.INSTANCE;
            if (!commonUtils.useAlignEndStyle()) {
                if (commonUtils.getInVerticalMode(getContext()) || getSupportWideMode()) {
                    i2 = 0;
                    panelWidth = 0;
                } else if (getRightOrLeft()) {
                    panelWidth2 = mainPanelController.getPanelWidth() + mainPanelController.getPanelMargin();
                } else {
                    panelWidth = mainPanelController.getPanelWidth() + mainPanelController.getPanelMargin();
                    i2 = 0;
                }
                ConstraintSet constraintSet = new ConstraintSet();
                constraintSet.constrainWidth(secondaryPanelContainer.getId(), specificWidth);
                constraintSet.constrainHeight(secondaryPanelContainer.getId(), specificHeight);
                constraintSet.connect(secondaryPanelContainer.getId(), 3, 0, 3);
                constraintSet.connect(secondaryPanelContainer.getId(), 6, 0, 6, i2);
                constraintSet.connect(secondaryPanelContainer.getId(), 7, 0, 7, panelWidth);
                constraintSet.connect(secondaryPanelContainer.getId(), 4, 0, 4);
                constraintSet.applyTo((ConstraintLayout) getView());
            }
            panelWidth2 = (screenWidth - mainPanelController.getPanelWidth()) - (getResources().getDimensionPixelOffset(R.dimen.control_center_align_end_style_padding_right) * 2);
            i2 = panelWidth2;
            panelWidth = 0;
            ConstraintSet constraintSet2 = new ConstraintSet();
            constraintSet2.constrainWidth(secondaryPanelContainer.getId(), specificWidth);
            constraintSet2.constrainHeight(secondaryPanelContainer.getId(), specificHeight);
            constraintSet2.connect(secondaryPanelContainer.getId(), 3, 0, 3);
            constraintSet2.connect(secondaryPanelContainer.getId(), 6, 0, 6, i2);
            constraintSet2.connect(secondaryPanelContainer.getId(), 7, 0, 7, panelWidth);
            constraintSet2.connect(secondaryPanelContainer.getId(), 4, 0, 4);
            constraintSet2.applyTo((ConstraintLayout) getView());
        }
    }

    private final void updateContentBg() {
        View contentBg = getContentBg();
        if (contentBg == null) {
            return;
        }
        contentBg.setBackground(AppCompatResources.getDrawable(getContext(), R.drawable.secondary_panel_background));
        if (!ControlCenterUtils.getBackgroundBlurOpenedInDefaultTheme(getContext())) {
            updateContentBg$applyBgColor(contentBg, this);
            return;
        }
        Drawable background = contentBg.getBackground();
        GradientDrawable gradientDrawable = background instanceof GradientDrawable ? (GradientDrawable) background : null;
        if (gradientDrawable == null) {
            updateContentBg$applyBgColor(contentBg, this);
            return;
        }
        gradientDrawable.setColor(getContext().getColor(R.color.transparent));
        MiBlurCompat.setMiViewBlurModeCompat(contentBg, 1);
        MiBlurCompat.setMiBackgroundBlendColors(contentBg, MiuiColorBlendToken.INSTANCE.getCC_DETAIL_PANEL_BACKGROUND_BLEND_COLORS());
    }

    private static final <P extends SecondaryParams> void updateContentBg$applyBgColor(View view, SecondaryPanelControllerBase<P> secondaryPanelControllerBase) {
        CommonUtils.INSTANCE.clearMiBlurBlendEffect(view);
        Drawable background = view.getBackground();
        GradientDrawable gradientDrawable = background instanceof GradientDrawable ? (GradientDrawable) background : null;
        if (gradientDrawable != null) {
            gradientDrawable.setColor(secondaryPanelControllerBase.getContext().getColor(R.color.secondary_panel_background_color));
        }
    }

    private final void updateSize() {
        setContentBgRadius(getResources().getDimensionPixelSize(R.dimen.detail_panel_background_corner_radius));
    }

    @Override // miui.systemui.controlcenter.panel.SecondaryPanelRouter.SecondaryPanel
    public void cancelPrepare() {
        Log.e(this.tag, "cancelPrepare");
        onSecondaryVisible(false, getSecondaryPanelRouter().getInMainPanel() && !getSecondaryPanelRouter().toSamePanel(this));
        ViewGroup secondaryPanelContainer = getSecondaryPanelContainer();
        if (secondaryPanelContainer != null) {
            secondaryPanelContainer.suppressLayout(false);
        }
        this.delegateBase.cancelPrepare();
    }

    @Override // miui.systemui.controlcenter.panel.SecondaryPanelRouter.SecondaryPanel
    public void forceToHide() {
        Log.e(this.tag, "forceToHide");
        this.animatorBase.forceToHide();
        onHidden(null);
    }

    @Override // miui.systemui.controlcenter.panel.secondary.BlurBgToView
    public View getBlurBg() {
        return getSecondaryContainerController().getSecondaryBlurProvider();
    }

    @Override // miui.systemui.controlcenter.panel.secondary.BlurBgToView
    public float getBlurBgRatio() {
        return this.blurBgRatio;
    }

    @Override // miui.systemui.controlcenter.panel.secondary.ContentBgToView
    public View getContentBg() {
        return this.contentBg;
    }

    @Override // miui.systemui.controlcenter.panel.secondary.ContentBgToView
    public float getContentBgColor() {
        return this.contentBgColor;
    }

    @Override // miui.systemui.controlcenter.panel.secondary.ContentBgToView
    public float getContentBgRadius() {
        return this.contentBgRadius;
    }

    public final P getPanelParams() {
        return this.panelParams;
    }

    public boolean getRightOrLeft() {
        return this.rightOrLeft;
    }

    public abstract ViewGroup getSecondaryPanelContainer();

    public int getSpecificHeight() {
        return this.specificHeight;
    }

    public int getSpecificWidth() {
        return this.specificWidth;
    }

    public boolean getSupportWideMode() {
        return this.supportWideMode;
    }

    public final String getTag() {
        return this.tag;
    }

    public boolean getUseDefaultContainerConstraint() {
        return this.useDefaultContainerConstraint;
    }

    public boolean getUseSpecificHeight() {
        return this.useSpecificHeight;
    }

    public boolean getUseSpecificWidth() {
        return this.useSpecificWidth;
    }

    public void hide() {
        getSecondaryPanelRouter().routeToMain();
    }

    @Override // miui.systemui.controlcenter.panel.SecondaryPanelRouter.SecondaryPanel
    public boolean isAnimating() {
        return this.completeAction != null;
    }

    public final boolean isCollapsed() {
        return this.animatorBase.isCollapsed();
    }

    public final boolean isCollapsing() {
        return this.animatorBase.isCollapsing();
    }

    public final boolean isExpanded() {
        return this.animatorBase.isExpanded();
    }

    public final boolean isExpanding() {
        return this.animatorBase.isExpanding();
    }

    public final void onAnimComplete() {
        Log.i(this.tag, "onAnimComplete");
        Function0 function0 = this.completeAction;
        this.completeAction = null;
        if (function0 != null) {
            function0.invoke();
        }
    }

    @Override // miui.systemui.controlcenter.panel.SecondaryPanelRouter.SecondaryPanel
    public void onCollapsed() {
        onAnimComplete();
    }

    @Override // miui.systemui.controlcenter.utils.ControlCenterViewController, miui.systemui.controlcenter.ConfigUtils.OnConfigChangeListener
    public void onConfigurationChanged(int i2) {
        ViewGroup secondaryPanelContainer = getSecondaryPanelContainer();
        if (secondaryPanelContainer == null || secondaryPanelContainer.isShown()) {
            ConfigUtils configUtils = ConfigUtils.INSTANCE;
            if (configUtils.dimensionsChanged(i2) || configUtils.orientationChanged(i2)) {
                updateSize();
                updateContainerConstraint();
            }
            if (configUtils.blurChanged(i2)) {
                updateBlurBg();
                updateContentBg();
            }
            if (configUtils.colorsChanged(i2)) {
                updateContentBg();
            }
        }
    }

    @Override // miui.systemui.util.ViewController
    public void onCreate() {
        super.onCreate();
        getScreenshot().addOnScreenshotListener(this.onScreenshotListener);
    }

    @Override // miui.systemui.controlcenter.utils.ControlCenterViewController
    public void onDestroy() {
        super.onDestroy();
        getScreenshot().removeOnScreenshotListener(this.onScreenshotListener);
    }

    @Override // miui.systemui.controlcenter.panel.SecondaryPanelRouter.SecondaryPanel
    public void onHidden(SecondaryPanelRouter.SecondaryPanel<?> secondaryPanel) {
        Log.i(this.tag, "onHidden");
        onSecondaryVisible$default(this, false, false, 2, null);
        ViewGroup secondaryPanelContainer = getSecondaryPanelContainer();
        if (secondaryPanelContainer != null) {
            secondaryPanelContainer.suppressLayout(false);
        }
        this.delegateBase.onHidden();
    }

    @Override // miui.systemui.controlcenter.panel.SecondaryPanelRouter.SecondaryPanel
    public Boolean onKeyEvent(KeyEvent event) {
        n.g(event, "event");
        if (event.getAction() != 1) {
            return null;
        }
        if (event.getKeyCode() != 4 && event.getKeyCode() != 82) {
            return null;
        }
        hide();
        return Boolean.TRUE;
    }

    public void onSecondaryVisible(boolean z2, boolean z3) {
        Log.i(this.tag, "onSecondaryVisible " + z2 + " " + z3);
        if (z2) {
            if (!z3) {
                CommonUtils.INSTANCE.setVisible(getSecondaryContainerParent());
            }
            ViewGroup secondaryPanelContainer = getSecondaryPanelContainer();
            if (secondaryPanelContainer != null) {
                CommonUtils.INSTANCE.setVisible(secondaryPanelContainer);
                return;
            }
            return;
        }
        if (!z3) {
            CommonUtils.INSTANCE.setGone(getSecondaryContainerParent());
        }
        ViewGroup secondaryPanelContainer2 = getSecondaryPanelContainer();
        if (secondaryPanelContainer2 != null) {
            CommonUtils.INSTANCE.setGone(secondaryPanelContainer2);
        }
        CommonUtils commonUtils = CommonUtils.INSTANCE;
        NonTouchableFrameLayout fakeContainer = this.secondaryBinding.fakeContainer;
        n.f(fakeContainer, "fakeContainer");
        commonUtils.setGone(fakeContainer);
    }

    @Override // miui.systemui.controlcenter.panel.SecondaryPanelRouter.SecondaryPanel
    public void onShown() {
        Log.i(this.tag, "onShown");
        ControlCenterScenarioTracker.INSTANCE.setControlCenterSecondaryOnShow(false);
        onSecondaryVisible$default(this, true, false, 2, null);
        ViewGroup secondaryPanelContainer = getSecondaryPanelContainer();
        if (secondaryPanelContainer != null) {
            secondaryPanelContainer.suppressLayout(false);
        }
        this.delegateBase.onShown();
    }

    @Override // miui.systemui.controlcenter.utils.ControlCenterViewController
    public void onStop() {
        super.onStop();
        onSecondaryVisible$default(this, false, false, 2, null);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r3v0, types: [android.view.View] */
    @Override // miui.systemui.controlcenter.panel.SecondaryPanelRouter.SecondaryPanel
    public void prepareHide() {
        Log.i(this.tag, "prepareHide");
        BoostHelper.getInstance().boostWithCpuFreq(2000L, getView());
        this.delegateBase.prepareHide();
        this.animatorBase.prepareCollapse();
    }

    @Override // miui.systemui.controlcenter.panel.secondary.BlurBgToView
    public void setBlurBgRatio(float f2) {
        if (this.blurBgRatio == f2) {
            return;
        }
        this.blurBgRatio = f2;
        applyMiBlur(f2);
    }

    @Override // miui.systemui.controlcenter.panel.secondary.ContentBgToView
    public void setContentBgColor(float f2) {
        if (this.contentBgColor == f2) {
            return;
        }
        this.contentBgColor = f2;
        if (ControlCenterUtils.getBackgroundBlurOpenedInDefaultTheme(getContext())) {
            View contentBg = getContentBg();
            if (contentBg != null) {
                MiuiColorBlendToken miuiColorBlendToken = MiuiColorBlendToken.INSTANCE;
                MiBlurCompat.setMiBackgroundBlendColors(contentBg, miuiColorBlendToken.getCC_TILE_DEFAULT_BLEND_COLORS(), miuiColorBlendToken.getCC_DETAIL_PANEL_BACKGROUND_BLEND_COLORS(), f2, true);
                return;
            }
            return;
        }
        View contentBg2 = getContentBg();
        Drawable background = contentBg2 != null ? contentBg2.getBackground() : null;
        GradientDrawable gradientDrawable = background instanceof GradientDrawable ? (GradientDrawable) background : null;
        if (gradientDrawable != null) {
            gradientDrawable.setColor(ColorUtils.INSTANCE.blendARGB(getContext().getColor(R.color.external_entry_background_color), getContext().getColor(R.color.secondary_panel_background_color), f2));
        }
    }

    @Override // miui.systemui.controlcenter.panel.secondary.ContentBgToView
    public void setContentBgRadius(float f2) {
        if (this.contentBgRadius == f2) {
            return;
        }
        this.contentBgRadius = f2;
        View contentBg = getContentBg();
        Drawable background = contentBg != null ? contentBg.getBackground() : null;
        GradientDrawable gradientDrawable = background instanceof GradientDrawable ? (GradientDrawable) background : null;
        if (gradientDrawable == null) {
            return;
        }
        gradientDrawable.setCornerRadius(f2);
    }

    public final void setPanelParams(P p2) {
        this.panelParams = p2;
    }

    public void setRightOrLeft(boolean z2) {
        this.rightOrLeft = z2;
    }

    public void setSpecificHeight(int i2) {
        this.specificHeight = i2;
    }

    public void setSpecificWidth(int i2) {
        this.specificWidth = i2;
    }

    public void setSupportWideMode(boolean z2) {
        this.supportWideMode = z2;
    }

    public void setUseDefaultContainerConstraint(boolean z2) {
        this.useDefaultContainerConstraint = z2;
    }

    public void setUseSpecificHeight(boolean z2) {
        this.useSpecificHeight = z2;
    }

    public void setUseSpecificWidth(boolean z2) {
        this.useSpecificWidth = z2;
    }

    @Override // miui.systemui.controlcenter.panel.SecondaryPanelRouter.SecondaryPanel
    public void startHide(Function0 completeAction) {
        n.g(completeAction, "completeAction");
        if (this.animatorBase.isCollapsing()) {
            return;
        }
        Log.i(this.tag, "startHide");
        this.completeAction = completeAction;
        ViewGroup secondaryPanelContainer = getSecondaryPanelContainer();
        if (secondaryPanelContainer != null) {
            secondaryPanelContainer.suppressLayout(true);
        }
        this.delegateBase.startHide();
        this.animatorBase.collapse(true);
    }

    @Override // miui.systemui.controlcenter.panel.SecondaryPanelRouter.SecondaryPanel
    public void startShow(Function0 completeAction) {
        n.g(completeAction, "completeAction");
        Log.i(this.tag, "startShow");
        this.completeAction = completeAction;
        ViewGroup secondaryPanelContainer = getSecondaryPanelContainer();
        if (secondaryPanelContainer != null) {
            secondaryPanelContainer.suppressLayout(true);
        }
        ControlCenterScenarioTracker.INSTANCE.setControlCenterSecondaryStartShow(false);
        this.delegateBase.startShow();
        this.animatorBase.expand();
    }

    @Override // miui.systemui.controlcenter.panel.SecondaryPanelRouter.SecondaryPanel
    public void forceToShow(P p2) {
        Log.e(this.tag, "Secondary panel does not have a force to show option.");
    }

    @Override // miui.systemui.controlcenter.utils.ControlCenterViewController
    public ArrayList<SecondaryPanelDelegateBase<P>> getChildControllers() {
        return this.childControllers;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r4v0, types: [android.view.View] */
    @Override // miui.systemui.controlcenter.panel.SecondaryPanelRouter.SecondaryPanel
    public boolean prepareShow(P p2) {
        Log.i(this.tag, "prepareShow " + p2);
        ControlCenterScenarioTracker.INSTANCE.setControlCenterSecondaryState(true);
        this.panelParams = p2;
        BoostHelper.getInstance().boostWithCpuFreq(2000L, getView());
        onSecondaryVisible$default(this, true, false, 2, null);
        updateSize();
        updateBlurBg();
        updateContentBg();
        updateContainerConstraint();
        this.delegateBase.prepareShow(p2);
        this.animatorBase.prepareExpand(p2);
        return true;
    }
}
