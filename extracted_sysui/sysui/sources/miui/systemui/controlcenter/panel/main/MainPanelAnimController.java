package miui.systemui.controlcenter.panel.main;

import android.util.Log;
import android.view.KeyEvent;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import j1.I;
import j1.K;
import j1.u;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.n;
import miui.systemui.controlcenter.ConfigUtils;
import miui.systemui.controlcenter.dagger.ControlCenterScope;
import miui.systemui.controlcenter.dagger.qualifiers.Qualifiers;
import miui.systemui.controlcenter.databinding.MainPanelHeaderBinding;
import miui.systemui.controlcenter.panel.SecondaryPanelBase;
import miui.systemui.controlcenter.panel.SecondaryPanelRouter;
import miui.systemui.controlcenter.panel.main.header.MainPanelHeaderController;
import miui.systemui.controlcenter.panel.main.qs.QSCardsController;
import miui.systemui.controlcenter.utils.ControlCenterUtils;
import miui.systemui.util.BlurUtilsExt;
import miui.systemui.util.MiuiMathUtils;
import miui.systemui.widget.FrameLayout;
import miuix.animation.FolmeEase;
import miuix.animation.base.AnimConfig;
import miuix.animation.controller.AnimState;
import miuix.animation.property.ViewProperty;
import miuix.animation.utils.EaseManager;

/* JADX INFO: loaded from: classes.dex */
@ControlCenterScope
public final class MainPanelAnimController extends SecondaryPanelBase<ViewGroup, Object> {
    public static final Companion Companion = new Companion(null);
    private static final EaseManager.EaseStyle EASE_HIDE_COLOR;
    private static final EaseManager.EaseStyle EASE_HIDE_SIZE;
    private static final EaseManager.EaseStyle EASE_SHOW_COLOR;
    private static final EaseManager.EaseStyle EASE_SHOW_SIZE;
    private final u _mainPanelAppearance;
    private final BlurUtilsExt blurUtilsExt;
    private final MainPanelHeaderBinding headerBinding;
    private final E0.a headerController;
    private final AnimState hideAnim;
    private final I mainPanelAppearance;
    private final LinearLayout mainPanelContainer;
    private final E0.a mainPanelController;
    private final QSCardsController qsCardsController;
    private final E0.a secondaryPanelRouter;
    private final AnimState showAnim;

    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final EaseManager.EaseStyle getEASE_HIDE_COLOR() {
            return MainPanelAnimController.EASE_HIDE_COLOR;
        }

        public final EaseManager.EaseStyle getEASE_HIDE_SIZE() {
            return MainPanelAnimController.EASE_HIDE_SIZE;
        }

        public final EaseManager.EaseStyle getEASE_SHOW_COLOR() {
            return MainPanelAnimController.EASE_SHOW_COLOR;
        }

        public final EaseManager.EaseStyle getEASE_SHOW_SIZE() {
            return MainPanelAnimController.EASE_SHOW_SIZE;
        }

        private Companion() {
        }
    }

    static {
        EaseManager.EaseStyle easeStyleSpring = FolmeEase.spring(0.9f, 0.4f);
        n.f(easeStyleSpring, "spring(...)");
        EASE_SHOW_SIZE = easeStyleSpring;
        EaseManager.EaseStyle easeStyleSpring2 = FolmeEase.spring(0.9f, 0.3f);
        n.f(easeStyleSpring2, "spring(...)");
        EASE_SHOW_COLOR = easeStyleSpring2;
        EaseManager.EaseStyle easeStyleSpring3 = FolmeEase.spring(0.9f, 0.3f);
        n.f(easeStyleSpring3, "spring(...)");
        EASE_HIDE_SIZE = easeStyleSpring3;
        EaseManager.EaseStyle easeStyleQuadOut = FolmeEase.quadOut(120L);
        n.f(easeStyleQuadOut, "quadOut(...)");
        EASE_HIDE_COLOR = easeStyleQuadOut;
    }

    /* JADX WARN: Illegal instructions before constructor call */
    public MainPanelAnimController(MainPanelHeaderBinding headerBinding, @Qualifiers.MainPanelContainer LinearLayout mainPanelContainer, E0.a mainPanelController, E0.a headerController, E0.a secondaryPanelRouter, BlurUtilsExt blurUtilsExt, QSCardsController qsCardsController) {
        n.g(headerBinding, "headerBinding");
        n.g(mainPanelContainer, "mainPanelContainer");
        n.g(mainPanelController, "mainPanelController");
        n.g(headerController, "headerController");
        n.g(secondaryPanelRouter, "secondaryPanelRouter");
        n.g(blurUtilsExt, "blurUtilsExt");
        n.g(qsCardsController, "qsCardsController");
        FrameLayout root = headerBinding.getRoot();
        n.f(root, "getRoot(...)");
        super(root, mainPanelController);
        this.headerBinding = headerBinding;
        this.mainPanelContainer = mainPanelContainer;
        this.mainPanelController = mainPanelController;
        this.headerController = headerController;
        this.secondaryPanelRouter = secondaryPanelRouter;
        this.blurUtilsExt = blurUtilsExt;
        this.qsCardsController = qsCardsController;
        u uVarA = K.a(Boolean.TRUE);
        this._mainPanelAppearance = uVarA;
        this.mainPanelAppearance = uVarA;
        AnimState animState = new AnimState("STATE_SHOW");
        ViewProperty viewProperty = ViewProperty.ALPHA;
        AnimState animStateAdd = animState.add(viewProperty, 1.0f, new long[0]);
        ViewProperty viewProperty2 = ViewProperty.SCALE_X;
        AnimState animStateAdd2 = animStateAdd.add(viewProperty2, 1.0f, new long[0]);
        ViewProperty viewProperty3 = ViewProperty.SCALE_Y;
        AnimState animStateAdd3 = animStateAdd2.add(viewProperty3, 1.0f, new long[0]);
        n.f(animStateAdd3, "add(...)");
        this.showAnim = animStateAdd3;
        AnimState animStateAdd4 = new AnimState("STATE_SHOW").add(viewProperty, 0.0f, new long[0]).add(viewProperty2, 0.95f, new long[0]).add(viewProperty3, 0.95f, new long[0]);
        n.f(animStateAdd4, "add(...)");
        this.hideAnim = animStateAdd4;
    }

    private final boolean getShowMainContent() {
        return (!ControlCenterUtils.getBackgroundBlurOpenedInDefaultTheme(getContext()) || ((SecondaryPanelRouter) this.secondaryPanelRouter.get()).getToDeviceControlPanel() || ((SecondaryPanelRouter) this.secondaryPanelRouter.get()).getInDeviceControlPanel()) ? false : true;
    }

    @Override // miui.systemui.controlcenter.panel.SecondaryPanelBase, miui.systemui.controlcenter.panel.SecondaryPanelRouter.SecondaryPanel
    public void forceToHide() {
        super.forceToHide();
        ((MainPanelHeaderController) this.headerController.get()).onMainPanelVisibleChanged(false, false);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // miui.systemui.controlcenter.panel.SecondaryPanelBase, miui.systemui.controlcenter.panel.SecondaryPanelRouter.SecondaryPanel
    public void forceToShow(Object obj) {
        super.forceToShow(obj);
        ((ViewGroup) getView()).setAlpha(1.0f);
        this.blurUtilsExt.applyAlphaScale(this.mainPanelContainer, 1.0f);
        ((MainPanelHeaderController) this.headerController.get()).onMainPanelVisibleChanged(true, false);
        this._mainPanelAppearance.setValue(Boolean.TRUE);
    }

    @Override // miui.systemui.controlcenter.panel.SecondaryPanelBase
    public AnimState getHideAnim() {
        return this.hideAnim;
    }

    public final I getMainPanelAppearance() {
        return this.mainPanelAppearance;
    }

    @Override // miui.systemui.controlcenter.panel.SecondaryPanelBase
    public AnimState getShowAnim() {
        return this.showAnim;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // miui.systemui.controlcenter.panel.SecondaryPanelBase
    public void onAnimUpdate(boolean z2) {
        float fLerp = getShowMainContent() ? z2 ? MiuiMathUtils.INSTANCE.lerp(0.5f, 1.0f, c1.f.h(((ViewGroup) getView()).getAlpha(), 0.0f, 1.0f)) : MiuiMathUtils.INSTANCE.lerp(1.0f, 0.5f, c1.f.h(1.0f - ((ViewGroup) getView()).getAlpha(), 0.0f, 1.0f)) : ((ViewGroup) getView()).getAlpha();
        if (fLerp == 0.0f || fLerp == 0.5f || fLerp == 1.0f) {
            Log.i(getTag(), "update alpha " + fLerp);
        }
        this.blurUtilsExt.applyAlphaScale(this.mainPanelContainer, fLerp);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // miui.systemui.controlcenter.utils.ControlCenterViewController, miui.systemui.controlcenter.ConfigUtils.OnConfigChangeListener
    public void onConfigurationChanged(int i2) {
        if (ConfigUtils.INSTANCE.orientationChanged(i2)) {
            this.mainPanelContainer.setAlpha(getShowMainContent() ? 1.0f : ((ViewGroup) getView()).getAlpha());
            this.mainPanelContainer.setVisibility(getShowMainContent() ? 0 : ((ViewGroup) getView()).getVisibility());
        }
    }

    @Override // miui.systemui.controlcenter.panel.SecondaryPanelBase, miui.systemui.controlcenter.panel.SecondaryPanelRouter.SecondaryPanel
    public void onHidden(SecondaryPanelRouter.SecondaryPanel<?> secondaryPanel) {
        super.onHidden(secondaryPanel);
        if (secondaryPanel != null) {
            secondaryPanel.onMainPanelHidden();
        }
        this.mainPanelContainer.setImportantForAccessibility(4);
        this._mainPanelAppearance.setValue(Boolean.FALSE);
    }

    @Override // miui.systemui.controlcenter.panel.SecondaryPanelRouter.SecondaryPanel
    public Boolean onKeyEvent(KeyEvent event) {
        n.g(event, "event");
        return ((MainPanelController) this.mainPanelController.get()).onKeyEvent(event);
    }

    @Override // miui.systemui.controlcenter.panel.SecondaryPanelBase, miui.systemui.controlcenter.panel.SecondaryPanelRouter.SecondaryPanel
    public void onShown() {
        super.onShown();
        ((MainPanelController) this.mainPanelController.get()).handleMainPanelShown();
        this.qsCardsController.restartMarquee();
        this.mainPanelContainer.setImportantForAccessibility(1);
    }

    @Override // miui.systemui.controlcenter.panel.SecondaryPanelBase, miui.systemui.controlcenter.panel.SecondaryPanelRouter.SecondaryPanel
    public void prepareHide() {
        super.prepareHide();
        this.qsCardsController.stopMarquee();
    }

    @Override // miui.systemui.controlcenter.panel.SecondaryPanelBase, miui.systemui.controlcenter.panel.SecondaryPanelRouter.SecondaryPanel
    public void startHide(Function0 completeAction) {
        n.g(completeAction, "completeAction");
        super.startHide(completeAction);
        ((MainPanelHeaderController) this.headerController.get()).onMainPanelVisibleChanged(false, true);
    }

    @Override // miui.systemui.controlcenter.panel.SecondaryPanelBase, miui.systemui.controlcenter.panel.SecondaryPanelRouter.SecondaryPanel
    public void startShow(Function0 completeAction) {
        n.g(completeAction, "completeAction");
        super.startShow(completeAction);
        ((MainPanelHeaderController) this.headerController.get()).onMainPanelVisibleChanged(true, true);
        this._mainPanelAppearance.setValue(Boolean.TRUE);
    }

    @Override // miui.systemui.controlcenter.panel.SecondaryPanelBase
    public void suppressLayout(boolean z2) {
        super.suppressLayout(z2);
        this.mainPanelContainer.suppressLayout(z2);
    }

    @Override // miui.systemui.controlcenter.panel.SecondaryPanelBase
    public AnimConfig updateHideAnimConfig() {
        AnimConfig animConfig = new AnimConfig();
        ViewProperty viewProperty = ViewProperty.SCALE_X;
        EaseManager.EaseStyle easeStyle = EASE_HIDE_SIZE;
        animConfig.setSpecial(viewProperty, easeStyle, new float[0]);
        animConfig.setSpecial(ViewProperty.SCALE_Y, easeStyle, new float[0]);
        animConfig.setSpecial(ViewProperty.ALPHA, EASE_HIDE_COLOR, new float[0]);
        return animConfig;
    }

    @Override // miui.systemui.controlcenter.panel.SecondaryPanelBase
    public AnimConfig updateShowAnimConfig() {
        AnimConfig animConfig = new AnimConfig();
        ViewProperty viewProperty = ViewProperty.SCALE_X;
        EaseManager.EaseStyle easeStyle = EASE_SHOW_SIZE;
        animConfig.setSpecial(viewProperty, easeStyle, new float[0]);
        animConfig.setSpecial(ViewProperty.SCALE_Y, easeStyle, new float[0]);
        animConfig.setSpecial(ViewProperty.ALPHA, EASE_SHOW_COLOR, new float[0]);
        return animConfig;
    }

    @Override // miui.systemui.controlcenter.panel.SecondaryPanelBase
    public void updateVisibility(int i2) {
        super.updateVisibility(i2);
        this.mainPanelContainer.setVisibility(0);
    }
}
