package miui.systemui.controlcenter.panel.customize;

import H0.s;
import I0.m;
import android.annotation.SuppressLint;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;
import java.util.ArrayList;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.n;
import kotlin.jvm.internal.o;
import miui.systemui.controlcenter.ConfigUtils;
import miui.systemui.controlcenter.R;
import miui.systemui.controlcenter.customize.CustomizeAdapter;
import miui.systemui.controlcenter.dagger.ControlCenterScope;
import miui.systemui.controlcenter.databinding.CustomizerPanelBinding;
import miui.systemui.controlcenter.panel.SecondaryPanelBase;
import miui.systemui.controlcenter.panel.SecondaryPanelRouter;
import miui.systemui.controlcenter.panel.main.MainPanelController;
import miui.systemui.controlcenter.utils.ControlCenterViewController;
import miui.systemui.controlcenter.windowview.ControlCenterScreenshot;
import miui.systemui.util.CommonUtils;
import miui.systemui.util.OnClickListenerEx;
import miui.systemui.widget.ConstraintLayout;
import miui.systemui.widget.LinearLayout;
import miui.systemui.widget.VisibleFocusedTextView;
import miuix.animation.IStateStyle;
import miuix.animation.controller.AnimState;
import miuix.animation.property.ViewProperty;
import miuix.recyclerview.widget.RecyclerView;

/* JADX INFO: loaded from: classes.dex */
@ControlCenterScope
@SuppressLint({"UseCompatLoadingForDrawables"})
public final class CustomizePanelController extends SecondaryPanelBase<CustomizePanel, CustomizeAdapter> {
    private static final int ANIM_TRANS_Y = 100;
    public static final Companion Companion = new Companion(null);
    private static final String STATE_HIDE = "qs_customizer_hide";
    private static final String STATE_SHOW = "qs_customizer_show";
    private static final String TAG = "QSCustomizerController";
    private final CustomizerPanelBinding binding;
    private final ArrayList<ControlCenterViewController<?>> childControllers;
    private CustomizeAdapter customizeAdapter;
    private final AnimState hideAnim;
    private final CustomizePanelLinkageController linkageController;
    private final E0.a mainPanelController;
    private final CustomizePanelController$onScreenshotListener$1 onScreenshotListener;
    private final E0.a screenshot;
    private final AnimState showAnim;

    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }

    /* JADX INFO: renamed from: miui.systemui.controlcenter.panel.customize.CustomizePanelController$onCreate$1, reason: invalid class name */
    public static final class AnonymousClass1 extends o implements Function1 {
        public AnonymousClass1() {
            super(1);
        }

        @Override // kotlin.jvm.functions.Function1
        public /* bridge */ /* synthetic */ Object invoke(Object obj) {
            invoke((View) obj);
            return s.f314a;
        }

        public final void invoke(View view) {
            CustomizeAdapter customizeAdapter = CustomizePanelController.this.customizeAdapter;
            if (customizeAdapter != null) {
                customizeAdapter.hide(2);
            }
        }
    }

    /* JADX WARN: Illegal instructions before constructor call */
    /* JADX WARN: Type inference failed for: r5v10, types: [miui.systemui.controlcenter.panel.customize.CustomizePanelController$onScreenshotListener$1] */
    public CustomizePanelController(CustomizerPanelBinding binding, E0.a mainPanelController, CustomizePanelLinkageController linkageController, E0.a screenshot) {
        n.g(binding, "binding");
        n.g(mainPanelController, "mainPanelController");
        n.g(linkageController, "linkageController");
        n.g(screenshot, "screenshot");
        CustomizePanel root = binding.getRoot();
        n.f(root, "getRoot(...)");
        super(root, mainPanelController);
        this.binding = binding;
        this.mainPanelController = mainPanelController;
        this.linkageController = linkageController;
        this.screenshot = screenshot;
        AnimState animState = new AnimState(STATE_SHOW);
        ViewProperty viewProperty = ViewProperty.ALPHA;
        AnimState animStateAdd = animState.add(viewProperty, 1.0f, new long[0]);
        ViewProperty viewProperty2 = ViewProperty.TRANSLATION_Y;
        AnimState animStateAdd2 = animStateAdd.add(viewProperty2, 0.0f, new long[0]);
        n.f(animStateAdd2, "add(...)");
        this.showAnim = animStateAdd2;
        AnimState animStateAdd3 = new AnimState(STATE_HIDE).add(viewProperty, 0.0f, new long[0]).add(viewProperty2, 100, new long[0]);
        n.f(animStateAdd3, "add(...)");
        this.hideAnim = animStateAdd3;
        this.childControllers = m.f(linkageController);
        this.onScreenshotListener = new ControlCenterScreenshot.OnScreenshotListener() { // from class: miui.systemui.controlcenter.panel.customize.CustomizePanelController$onScreenshotListener$1
            @Override // miui.systemui.controlcenter.windowview.ControlCenterScreenshot.OnScreenshotListener
            public void onScreenshot() {
                ((ControlCenterScreenshot) this.this$0.screenshot.get()).addDumpMessage("customizeVisibility", String.valueOf(CustomizePanelController.access$getView(this.this$0).getVisibility()));
            }
        };
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static final /* synthetic */ CustomizePanel access$getView(CustomizePanelController customizePanelController) {
        return (CustomizePanel) customizePanelController.getView();
    }

    /* JADX WARN: Multi-variable type inference failed */
    private final void updateAppearance() {
        ViewGroup.LayoutParams layoutParams = ((CustomizePanel) getView()).getLayoutParams();
        n.e(layoutParams, "null cannot be cast to non-null type android.widget.FrameLayout.LayoutParams");
        FrameLayout.LayoutParams layoutParams2 = (FrameLayout.LayoutParams) layoutParams;
        if (!CommonUtils.INSTANCE.useAlignEndStyle()) {
            layoutParams2.width = -1;
            layoutParams2.gravity = 17;
        } else {
            layoutParams2.width = ((MainPanelController) this.mainPanelController.get()).getPanelWidth() + (getResources().getDimensionPixelOffset(R.dimen.control_center_align_end_style_padding_right) * 2);
            layoutParams2.gravity = 5;
        }
    }

    private final void updateBackground() {
        this.binding.save.setBackground(getContext().getDrawable(R.drawable.customize_panel_save_button));
        this.binding.notAddedContainer.setBackground(getContext().getDrawable(R.drawable.customize_panel_not_added_background));
        this.binding.indicator.setImageResource(R.drawable.customize_panel_not_added_indicator);
    }

    private final void updateResources() {
        CommonUtils commonUtils = CommonUtils.INSTANCE;
        int dimensionPixelOffset = commonUtils.useAlignEndStyle() ? getResources().getDimensionPixelOffset(R.dimen.control_center_align_end_style_padding_right) : getResources().getDimensionPixelSize(R.dimen.qs_control_customizer_tiles_content_horizontal_margin);
        ConstraintLayout constraintLayout = this.binding.addedContainer;
        constraintLayout.setPadding(dimensionPixelOffset, constraintLayout.getResources().getDimensionPixelSize(R.dimen.miui_controls_edit_panel_margin_top), dimensionPixelOffset, constraintLayout.getPaddingBottom());
        VisibleFocusedTextView save = this.binding.save;
        n.f(save, "save");
        CommonUtils.setLayoutSize$default(commonUtils, save, getResources().getDimensionPixelSize(R.dimen.qs_control_customizer_tiles_save_width), getResources().getDimensionPixelSize(R.dimen.qs_control_customizer_tiles_save_height), false, 4, null);
        VisibleFocusedTextView save2 = this.binding.save;
        n.f(save2, "save");
        CommonUtils.setMarginEnd$default(commonUtils, save2, getResources().getDimensionPixelSize(R.dimen.miui_controls_edit_panel_save_button_margin_end), false, 2, null);
        RecyclerView addedList = this.binding.addedList;
        n.f(addedList, "addedList");
        CommonUtils.setMarginTop$default(commonUtils, addedList, getResources().getDimensionPixelSize(R.dimen.qs_control_customizer_tiles_content_margin_top), false, 2, null);
        LinearLayout linearLayout = this.binding.notAddedContainer;
        linearLayout.setPadding(dimensionPixelOffset, linearLayout.getResources().getDimensionPixelSize(R.dimen.qs_control_customizer_others_padding_top), dimensionPixelOffset, linearLayout.getPaddingBottom());
        this.binding.indicator.setImageResource(R.drawable.customize_panel_not_added_indicator);
        TextView othersTitle = this.binding.othersTitle;
        n.f(othersTitle, "othersTitle");
        CommonUtils.setMargins$default(commonUtils, othersTitle, 0, getResources().getDimensionPixelSize(R.dimen.qs_control_customizer_others_title_margin_top), 0, getResources().getDimensionPixelSize(R.dimen.qs_control_customizer_others_padding_bottom), false, 21, null);
    }

    private final void updateTextAppearance() {
        this.binding.title.setTextAppearance(R.style.TextAppearance_Customize_AddedTitle);
        this.binding.save.setTextAppearance(R.style.TextAppearance_Customize_SaveButton);
        this.binding.subtitle.setTextAppearance(R.style.TextAppearance_Customize_AddedSubtitle);
        this.binding.othersTitle.setTextAppearance(R.style.TextAppearance_Customize_NotAddedTitle);
    }

    private final void updateTexts() {
        CustomizeAdapter customizeAdapter = this.customizeAdapter;
        if (customizeAdapter != null) {
            ConstraintLayout addedContainer = this.binding.addedContainer;
            n.f(addedContainer, "addedContainer");
            customizeAdapter.onShowStart(addedContainer);
            this.binding.title.setText(customizeAdapter.getAddedTitle());
            this.binding.subtitle.setText(customizeAdapter.getAddedSubtitle());
            this.binding.save.setText(customizeAdapter.getSave());
            this.binding.othersTitle.setText(customizeAdapter.getNotAddedTitle());
        }
    }

    @Override // miui.systemui.controlcenter.panel.SecondaryPanelBase
    public AnimState getHideAnim() {
        return this.hideAnim;
    }

    @Override // miui.systemui.controlcenter.panel.SecondaryPanelBase
    public AnimState getShowAnim() {
        return this.showAnim;
    }

    @Override // miui.systemui.controlcenter.panel.SecondaryPanelRouter.SecondaryPanel
    public boolean needCollapseOnOrientationChanged() {
        return true;
    }

    @Override // miui.systemui.controlcenter.panel.SecondaryPanelRouter.SecondaryPanel
    public void onCollapsed() {
        CustomizeAdapter customizeAdapter = this.customizeAdapter;
        if (customizeAdapter != null) {
            customizeAdapter.hide(1);
        }
    }

    @Override // miui.systemui.controlcenter.utils.ControlCenterViewController, miui.systemui.controlcenter.ConfigUtils.OnConfigChangeListener
    public void onConfigurationChanged(int i2) {
        ConfigUtils configUtils = ConfigUtils.INSTANCE;
        if (configUtils.textsChanged(i2)) {
            updateTexts();
        }
        if (configUtils.dimensionsChanged(i2)) {
            updateResources();
        }
        if (configUtils.textAppearanceChanged(i2)) {
            updateTextAppearance();
        }
        if (configUtils.colorsChanged(i2)) {
            updateBackground();
        }
        updateAppearance();
    }

    @Override // miui.systemui.controlcenter.panel.SecondaryPanelBase, miui.systemui.util.ViewController
    public void onCreate() {
        super.onCreate();
        IStateStyle anim = getAnim();
        if (anim != null) {
            anim.setTo(getHideAnim());
        }
        this.binding.addedList.setSpringEnabled(false);
        this.binding.notAddedList.setSpringEnabled(false);
        OnClickListenerEx onClickListenerEx = OnClickListenerEx.INSTANCE;
        VisibleFocusedTextView save = this.binding.save;
        n.f(save, "save");
        onClickListenerEx.setOnClickListenerEx(save, new AnonymousClass1());
        updateAppearance();
        ((ControlCenterScreenshot) this.screenshot.get()).addOnScreenshotListener(this.onScreenshotListener);
    }

    @Override // miui.systemui.controlcenter.panel.SecondaryPanelBase, miui.systemui.controlcenter.utils.ControlCenterViewController
    public void onDestroy() {
        super.onDestroy();
        this.binding.save.setOnClickListener(null);
        ((ControlCenterScreenshot) this.screenshot.get()).removeOnScreenshotListener(this.onScreenshotListener);
    }

    @Override // miui.systemui.controlcenter.panel.SecondaryPanelBase, miui.systemui.controlcenter.panel.SecondaryPanelRouter.SecondaryPanel
    public void onHidden(SecondaryPanelRouter.SecondaryPanel<?> secondaryPanel) {
        super.onHidden(secondaryPanel);
        CustomizeAdapter customizeAdapter = this.customizeAdapter;
        if (customizeAdapter != null) {
            customizeAdapter.onHideFinish();
        }
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
        CustomizeAdapter customizeAdapter = this.customizeAdapter;
        if (customizeAdapter != null) {
            customizeAdapter.hide(0);
        }
        return Boolean.TRUE;
    }

    @Override // miui.systemui.controlcenter.panel.SecondaryPanelBase, miui.systemui.controlcenter.panel.SecondaryPanelRouter.SecondaryPanel
    public void onShown() {
        super.onShown();
        CustomizeAdapter customizeAdapter = this.customizeAdapter;
        if (customizeAdapter != null) {
            customizeAdapter.onShowFinish();
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // miui.systemui.controlcenter.utils.ControlCenterViewController
    public void onStop() {
        if (((CustomizePanel) getView()).getVisibility() != 8) {
            ((CustomizePanel) getView()).setVisibility(8);
            Log.w(TAG, "view visibility is not GONE when panel collapsed.");
        }
        this.customizeAdapter = null;
    }

    @Override // miui.systemui.controlcenter.panel.SecondaryPanelBase, miui.systemui.controlcenter.panel.SecondaryPanelRouter.SecondaryPanel
    public void prepareHide() {
        CustomizeAdapter customizeAdapter = this.customizeAdapter;
        if (customizeAdapter != null) {
            customizeAdapter.onHideStart();
        }
        super.prepareHide();
    }

    @Override // miui.systemui.controlcenter.utils.ControlCenterViewController
    public ArrayList<ControlCenterViewController<?>> getChildControllers() {
        return this.childControllers;
    }

    @Override // miui.systemui.controlcenter.panel.SecondaryPanelBase, miui.systemui.controlcenter.panel.SecondaryPanelRouter.SecondaryPanel
    public boolean prepareShow(CustomizeAdapter customizeAdapter) {
        if (customizeAdapter == null) {
            Log.e(TAG, "show customizer with a null customize adapter.");
            return false;
        }
        this.customizeAdapter = customizeAdapter;
        this.binding.addedList.setAdapter(customizeAdapter.getAddedAdapter());
        RecyclerView recyclerView = this.binding.notAddedList;
        CustomizeAdapter customizeAdapter2 = this.customizeAdapter;
        recyclerView.setAdapter(customizeAdapter2 != null ? customizeAdapter2.getNotAddedAdapter() : null);
        this.binding.addedList.scrollTo(0, 0);
        this.binding.notAddedList.scrollTo(0, 0);
        this.linkageController.reset();
        updateTexts();
        return super.prepareShow(customizeAdapter);
    }
}
