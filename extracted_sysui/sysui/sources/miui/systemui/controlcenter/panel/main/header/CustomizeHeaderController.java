package miui.systemui.controlcenter.panel.main.header;

import android.annotation.SuppressLint;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;
import androidx.lifecycle.Lifecycle;
import com.android.systemui.plugins.miui.shade.ShadeHeader;
import com.android.systemui.plugins.miui.shade.ShadeHeaderController;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.n;
import miui.systemui.controlcenter.ConfigUtils;
import miui.systemui.controlcenter.R;
import miui.systemui.controlcenter.dagger.ControlCenterScope;
import miui.systemui.controlcenter.dagger.qualifiers.Qualifiers;
import miui.systemui.controlcenter.databinding.MainPanelCustomizeHeaderBinding;
import miui.systemui.controlcenter.events.ControlCenterEventTracker;
import miui.systemui.controlcenter.panel.main.MainPanelController;
import miui.systemui.controlcenter.panel.main.anim.PressEffectAnimator;
import miui.systemui.controlcenter.panel.main.qs.QSListController;
import miui.systemui.controlcenter.panel.main.recyclerview.MainPanelItemViewHolder;
import miui.systemui.controlcenter.qs.QSController;
import miui.systemui.controlcenter.utils.ControlCenterUtils;
import miui.systemui.controlcenter.utils.ControlCenterViewController;
import miui.systemui.controlcenter.utils.WordlessModeCompat;
import miui.systemui.util.BoostHelper;
import miui.systemui.util.CommonUtils;
import miui.systemui.util.DeviceUtils;
import miui.systemui.util.MiBlurCompat;
import miui.systemui.util.MiuiColorBlendToken;
import miui.systemui.util.ViewOutlineProviderExt;
import miui.systemui.widget.ConstraintLayout;
import miui.systemui.widget.FrameLayout;
import miui.systemui.widget.ImageView;
import miuix.animation.Folme;
import miuix.animation.FolmeEase;
import miuix.animation.IStateStyle;
import miuix.animation.base.AnimConfig;
import miuix.animation.listener.TransitionListener;
import miuix.animation.property.ViewProperty;
import miuix.animation.utils.EaseManager;
import systemui.plugin.eventtracking.EventTracker;

/* JADX INFO: loaded from: classes.dex */
@ControlCenterScope
@SuppressLint({"InflateParams"})
public final class CustomizeHeaderController extends ControlCenterViewController<View> implements ShadeHeader, MainPanelItemViewHolder.TouchAnimator {
    private static final float HEADER_TRAN_Y = 100.0f;
    private static final long SHOW_DELAY = 50;
    private static final String TAG = "CustomizeHeaderController";
    private final /* synthetic */ PressEffectAnimator $$delegate_0;
    private final IStateStyle animator;
    private final MainPanelCustomizeHeaderBinding binding;
    private final E0.a headerController;
    private final View headerView;
    private final AnimConfig hideConfig;
    private final Lifecycle lifecycle;
    private final E0.a mainPanelController;
    private final QSController qsController;
    private final ShadeHeaderController shadeHeaderController;
    private final int type;
    public static final Companion Companion = new Companion(null);
    private static final EaseManager.EaseStyle EASE_TRANS_Y_SHOW = FolmeEase.spring(1.0f, 0.4f);
    private static final EaseManager.EaseStyle EASE_ALPHA_SHOW = FolmeEase.spring(0.95f, 0.35f);
    private static final EaseManager.EaseStyle EASE_TRANS_Y_HIDE = FolmeEase.spring(0.95f, 0.35f);
    private static final EaseManager.EaseStyle EASE_ALPHA_HIDE = FolmeEase.spring(0.95f, 0.15f);

    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }

    /* JADX WARN: Illegal instructions before constructor call */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r3v2, types: [android.view.View] */
    /* JADX WARN: Type inference failed for: r3v4, types: [android.view.View] */
    public CustomizeHeaderController(MainPanelCustomizeHeaderBinding binding, @Qualifiers.ControlCenter Lifecycle lifecycle, ShadeHeaderController shadeHeaderController, E0.a mainPanelController, QSController qsController, E0.a headerController) {
        n.g(binding, "binding");
        n.g(lifecycle, "lifecycle");
        n.g(shadeHeaderController, "shadeHeaderController");
        n.g(mainPanelController, "mainPanelController");
        n.g(qsController, "qsController");
        n.g(headerController, "headerController");
        FrameLayout root = binding.getRoot();
        n.f(root, "getRoot(...)");
        super(root);
        this.binding = binding;
        this.lifecycle = lifecycle;
        this.shadeHeaderController = shadeHeaderController;
        this.mainPanelController = mainPanelController;
        this.qsController = qsController;
        this.headerController = headerController;
        this.$$delegate_0 = new PressEffectAnimator(null, 1, 0 == true ? 1 : 0);
        this.headerView = getView();
        this.type = MainPanelHeaderController.HEADER_TYPE_EDIT;
        this.animator = Folme.useAt(getView()).state().setTo(ViewProperty.ALPHA, Float.valueOf(0.0f));
        this.hideConfig = new AnimConfig().addListeners(new TransitionListener() { // from class: miui.systemui.controlcenter.panel.main.header.CustomizeHeaderController$hideConfig$1
            @Override // miuix.animation.listener.TransitionListener
            public void onComplete(Object obj) {
                this.this$0.getView().setVisibility(4);
            }
        });
    }

    private final boolean isLowDevice() {
        return DeviceUtils.isLowLevel() || DeviceUtils.isMidLowLevel();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onCreate$lambda$0(CustomizeHeaderController this$0, View view) {
        n.g(this$0, "this$0");
        BoostHelper.getInstance().boostWithCpuFreq(1000L, this$0.binding.completeButton);
        if (!CommonUtils.isTinyScreen(this$0.getContext())) {
            ControlCenterEventTracker.Companion.trackEditPanelQuitEvent(EventTracker.Companion.getScreenType(this$0.getContext()), ((QSListController) this$0.qsController.getQsListController().get()).getTileCustomized(), 2);
        }
        ((MainPanelController) this$0.mainPanelController.get()).getModeController().changeMode(MainPanelController.Mode.NORMAL, 1, true);
    }

    private final void updateBlendStyle() {
        if (!ControlCenterUtils.getBackgroundBlurOpenedInDefaultTheme(getContext())) {
            CommonUtils commonUtils = CommonUtils.INSTANCE;
            ConstraintLayout customizeHeader = this.binding.customizeHeader;
            n.f(customizeHeader, "customizeHeader");
            commonUtils.clearMiBlurBlendEffect(customizeHeader);
            ImageView completeButton = this.binding.completeButton;
            n.f(completeButton, "completeButton");
            commonUtils.clearMiBlurBlendEffect(completeButton);
            this.binding.completeButton.setOutlineProvider(null);
            this.binding.completeButton.setImageResource(R.drawable.ic_header_edit_mode_complete);
            this.binding.completeButton.setBackgroundResource(R.drawable.ic_header_edit_mode_complete_background);
            return;
        }
        ConstraintLayout customizeHeader2 = this.binding.customizeHeader;
        n.f(customizeHeader2, "customizeHeader");
        MiBlurCompat.setMiBackgroundBlurModeCompat(customizeHeader2, 1);
        ConstraintLayout customizeHeader3 = this.binding.customizeHeader;
        n.f(customizeHeader3, "customizeHeader");
        MiBlurCompat.setMixEffectEnabledCompat(customizeHeader3, false);
        this.binding.completeButton.setImageResource(R.drawable.ic_header_edit_mode_complete);
        this.binding.completeButton.setBackground(null);
        ImageView completeButton2 = this.binding.completeButton;
        n.f(completeButton2, "completeButton");
        MiBlurCompat.setMiViewBlurModeCompat(completeButton2, 1);
        ImageView completeButton3 = this.binding.completeButton;
        n.f(completeButton3, "completeButton");
        MiBlurCompat.setMiBackgroundBlendColors(completeButton3, MiuiColorBlendToken.INSTANCE.getCC_EDIT_BUTTON_BLEND_COLORS());
        this.binding.completeButton.setOutlineProvider(ViewOutlineProviderExt.INSTANCE.getOutlineProvider(getContext().getResources().getDimensionPixelSize(R.dimen.control_center_edit_complete_button_corner_radius)));
    }

    private final void updateResources() {
        this.binding.customizeHeader.setLayoutParams(new FrameLayout.LayoutParams(((MainPanelHeaderController) this.headerController.get()).getLayoutParams()));
        this.binding.customizeTitle.setTextAppearance(R.style.TextAppearance_Header_Title);
        WordlessModeCompat wordlessModeCompat = WordlessModeCompat.INSTANCE;
        ConstraintLayout customizeHeader = this.binding.customizeHeader;
        n.f(customizeHeader, "customizeHeader");
        wordlessModeCompat.updateResourcesCompat(customizeHeader);
        updateBlendStyle();
    }

    @Override // miui.systemui.controlcenter.panel.main.recyclerview.MainPanelItemViewHolder.TouchAnimator
    public void attachTouchTarget(View view) {
        n.g(view, "view");
        this.$$delegate_0.attachTouchTarget(view);
    }

    @Override // miui.systemui.controlcenter.panel.main.recyclerview.MainPanelItemViewHolder.TouchAnimator
    public void detachTouchTarget() {
        this.$$delegate_0.detachTouchTarget();
    }

    public View getHeaderView() {
        return this.headerView;
    }

    public float getHeight() {
        return this.binding.customizeHeader.getHeight();
    }

    public boolean getShouldShow() {
        return this.lifecycle.getCurrentState().compareTo(Lifecycle.State.STARTED) >= 0 && ((MainPanelController) this.mainPanelController.get()).getModeController().getMode() == MainPanelController.Mode.EDIT;
    }

    public int getType() {
        return this.type;
    }

    @Override // miui.systemui.controlcenter.utils.ControlCenterViewController, miui.systemui.controlcenter.ConfigUtils.OnConfigChangeListener
    public void onConfigurationChanged(int i2) {
        ConfigUtils configUtils = ConfigUtils.INSTANCE;
        boolean zDimensionsChanged = configUtils.dimensionsChanged(i2);
        if (zDimensionsChanged || configUtils.colorsChanged(i2) || configUtils.themeChanged(i2)) {
            updateBlendStyle();
        }
        if (zDimensionsChanged || configUtils.orientationChanged(i2)) {
            updateResources();
        }
        if (configUtils.textAppearanceChanged(i2)) {
            this.binding.customizeTitle.setTextAppearance(R.style.TextAppearance_Header_Title);
        }
        if (configUtils.textsChanged(i2)) {
            this.binding.customizeTitle.setText(R.string.qs_customize_header_title);
            this.binding.completeButton.setContentDescription(getContext().getString(R.string.qs_control_customize_save_text));
        }
    }

    @Override // miui.systemui.util.ViewController
    public void onCreate() {
        this.shadeHeaderController.registerHeader(this);
        this.binding.completeButton.setOnClickListener(new View.OnClickListener() { // from class: miui.systemui.controlcenter.panel.main.header.a
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                CustomizeHeaderController.onCreate$lambda$0(this.f5404a, view);
            }
        });
        updateResources();
        ImageView completeButton = this.binding.completeButton;
        n.f(completeButton, "completeButton");
        attachTouchTarget(completeButton);
    }

    @Override // miui.systemui.controlcenter.utils.ControlCenterViewController
    public void onDestroy() {
        this.shadeHeaderController.unregisterHeader(this);
        detachTouchTarget();
    }

    @Override // miui.systemui.controlcenter.panel.main.recyclerview.MainPanelItemViewHolder.TouchAnimator, android.view.View.OnTouchListener
    public boolean onTouch(View view, MotionEvent motionEvent) {
        return this.$$delegate_0.onTouch(view, motionEvent);
    }

    @Override // miui.systemui.controlcenter.panel.main.recyclerview.MainPanelItemViewHolder.TouchAnimator
    public void touchCancel() {
        this.$$delegate_0.touchCancel();
    }

    @Override // miui.systemui.controlcenter.panel.main.recyclerview.MainPanelItemViewHolder.TouchAnimator
    public void touchDown(MotionEvent ev) {
        n.g(ev, "ev");
        this.$$delegate_0.touchDown(ev);
    }

    @Override // miui.systemui.controlcenter.panel.main.recyclerview.MainPanelItemViewHolder.TouchAnimator
    public void touchRelease() {
        this.$$delegate_0.touchRelease();
    }

    @Override // miui.systemui.controlcenter.panel.main.recyclerview.MainPanelItemViewHolder.TouchAnimator
    public void touchReleaseNow() {
        this.$$delegate_0.touchReleaseNow();
    }

    @Override // miui.systemui.controlcenter.panel.main.recyclerview.MainPanelItemViewHolder.TouchAnimator
    public void touchTrigger() {
        this.$$delegate_0.touchTrigger();
    }

    /* JADX WARN: Type inference failed for: r5v3, types: [android.view.View] */
    public void transitionFrom(ShadeHeader from, boolean z2) {
        n.g(from, "from");
        Log.d(TAG, "transitionFrom " + from.getType());
        getView().setVisibility(0);
        Float fValueOf = Float.valueOf(1.0f);
        if (!z2 || isLowDevice()) {
            this.animator.setTo(ViewProperty.ALPHA, fValueOf);
            return;
        }
        IStateStyle iStateStyle = this.animator;
        ViewProperty viewProperty = ViewProperty.TRANSLATION_Y;
        iStateStyle.setTo(viewProperty, Float.valueOf(HEADER_TRAN_Y));
        AnimConfig animConfig = new AnimConfig();
        animConfig.setSpecial(viewProperty, EASE_TRANS_Y_SHOW, new float[0]);
        ViewProperty viewProperty2 = ViewProperty.ALPHA;
        animConfig.setSpecial(viewProperty2, EASE_ALPHA_SHOW, new float[0]);
        iStateStyle.to(viewProperty, Float.valueOf(0.0f), viewProperty2, fValueOf, animConfig);
    }

    public void transitionTo(ShadeHeader to, boolean z2) {
        n.g(to, "to");
        Log.d(TAG, "transitionTo " + to.getType());
        Float fValueOf = Float.valueOf(0.0f);
        if (!z2 || isLowDevice()) {
            this.animator.setTo(ViewProperty.ALPHA, fValueOf, this.hideConfig);
            return;
        }
        IStateStyle iStateStyle = this.animator;
        AnimConfig animConfig = this.hideConfig;
        ViewProperty viewProperty = ViewProperty.TRANSLATION_Y;
        animConfig.setSpecial(viewProperty, EASE_TRANS_Y_HIDE, new float[0]);
        AnimConfig animConfig2 = this.hideConfig;
        ViewProperty viewProperty2 = ViewProperty.ALPHA;
        animConfig2.setSpecial(viewProperty2, EASE_ALPHA_HIDE, new float[0]);
        iStateStyle.to(viewProperty, Float.valueOf(HEADER_TRAN_Y), viewProperty2, fValueOf, this.hideConfig);
    }
}
