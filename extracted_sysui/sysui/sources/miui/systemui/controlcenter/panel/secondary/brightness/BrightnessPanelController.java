package miui.systemui.controlcenter.panel.secondary.brightness;

import android.util.Log;
import android.view.KeyEvent;
import android.view.ViewGroup;
import androidx.lifecycle.Lifecycle;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.n;
import miui.systemui.controlcenter.ConfigUtils;
import miui.systemui.controlcenter.R;
import miui.systemui.controlcenter.dagger.ControlCenterScope;
import miui.systemui.controlcenter.dagger.qualifiers.Qualifiers;
import miui.systemui.controlcenter.databinding.BrightnessPanelBinding;
import miui.systemui.controlcenter.databinding.ControlCenterSecondaryBinding;
import miui.systemui.controlcenter.databinding.ToggleSliderItemViewBinding;
import miui.systemui.controlcenter.events.ControlCenterEventTracker;
import miui.systemui.controlcenter.panel.main.MainPanelController;
import miui.systemui.controlcenter.panel.secondary.BrightnessPanelParams;
import miui.systemui.controlcenter.panel.secondary.BrightnessToView;
import miui.systemui.controlcenter.panel.secondary.SecondaryPanelControllerBase;
import miui.systemui.controlcenter.panel.secondary.SliderFromView;
import miui.systemui.controlcenter.widget.AnimateColorView;
import miui.systemui.controlcenter.widget.BrightnessPanelRootView;
import miui.systemui.controlcenter.widget.ToggleSliderView;
import miui.systemui.util.CommonUtils;
import miui.systemui.widget.LinearLayout;
import miui.systemui.widget.View;
import systemui.plugin.eventtracking.EventTracker;

/* JADX INFO: loaded from: classes.dex */
@ControlCenterScope
public final class BrightnessPanelController extends SecondaryPanelControllerBase<BrightnessPanelParams> implements BrightnessToView {
    private final BrightnessPanelAnimator animator;
    private final BrightnessPanelBinding binding;
    private final BrightnessPanelDelegate delegate;
    private final Lifecycle lifecycle;
    private final E0.a mainPanelController;
    private int preValue;
    private final ControlCenterSecondaryBinding secondaryBinding;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public BrightnessPanelController(ControlCenterSecondaryBinding secondaryBinding, BrightnessPanelBinding binding, E0.a mainPanelController, BrightnessPanelDelegate delegate, BrightnessPanelAnimator animator, @Qualifiers.ControlCenter Lifecycle lifecycle) {
        super(secondaryBinding, mainPanelController, delegate, animator, lifecycle);
        n.g(secondaryBinding, "secondaryBinding");
        n.g(binding, "binding");
        n.g(mainPanelController, "mainPanelController");
        n.g(delegate, "delegate");
        n.g(animator, "animator");
        n.g(lifecycle, "lifecycle");
        this.secondaryBinding = secondaryBinding;
        this.binding = binding;
        this.mainPanelController = mainPanelController;
        this.delegate = delegate;
        this.animator = animator;
        this.lifecycle = lifecycle;
    }

    private final boolean getTouchingSlider() {
        return this.binding.brightnessContainer.getTouching();
    }

    private final void updateText() {
        View contentBg = getContentBg();
        contentBg.setContentDescription(contentBg.getContext().getString(R.string.brightness_setting_panel_title));
    }

    public final BrightnessPanelDelegate getDelegate() {
        return this.delegate;
    }

    @Override // miui.systemui.controlcenter.panel.secondary.BrightnessToView
    public float getOutlineRadius() {
        return this.delegate.getOutlineRadius();
    }

    @Override // miui.systemui.controlcenter.panel.secondary.BrightnessToView
    public float getProgressRadius() {
        return this.delegate.getProgressRadius();
    }

    public final ToggleSliderItemViewBinding getSliderBinding() {
        ToggleSliderItemViewBinding toggleSlider = this.binding.toggleSlider;
        n.f(toggleSlider, "toggleSlider");
        return toggleSlider;
    }

    @Override // miui.systemui.controlcenter.panel.secondary.SecondaryPanelControllerBase
    public int getSpecificHeight() {
        return getResources().getDimensionPixelSize(n.c(((MainPanelController) this.mainPanelController.get()).getUseSeparatedPanels(), Boolean.TRUE) ? R.dimen.control_center_brightness_volume_panel_content_height_horizontal : R.dimen.control_center_brightness_volume_panel_content_height_vertical);
    }

    @Override // miui.systemui.controlcenter.panel.secondary.BrightnessToView
    public ToggleSliderView getToggleSlider() {
        ToggleSliderView toggleSlider = getSliderBinding().toggleSlider;
        n.f(toggleSlider, "toggleSlider");
        return toggleSlider;
    }

    @Override // miui.systemui.controlcenter.panel.secondary.SecondaryPanelControllerBase
    public boolean getUseSpecificHeight() {
        return true;
    }

    @Override // miui.systemui.controlcenter.panel.secondary.SecondaryPanelControllerBase
    public void hide() {
        super.hide();
        ControlCenterEventTracker.Companion.trackBrightnessSeekbarAdjustEvent(true, EventTracker.Companion.getScreenType(getContext()), this.preValue, this.delegate.getCurrentProgress());
    }

    @Override // miui.systemui.controlcenter.panel.secondary.SecondaryPanelControllerBase, miui.systemui.controlcenter.utils.ControlCenterViewController, miui.systemui.controlcenter.ConfigUtils.OnConfigChangeListener
    public void onConfigurationChanged(int i2) {
        super.onConfigurationChanged(i2);
        if (ConfigUtils.INSTANCE.textsChanged(i2)) {
            updateText();
        }
    }

    @Override // miui.systemui.controlcenter.panel.secondary.SecondaryPanelControllerBase, miui.systemui.util.ViewController
    public void onCreate() {
        super.onCreate();
        CommonUtils.removeAccessibilityClick$default(getContentBg(), false, 2, null);
        getSliderBinding().slider.setToggleSliderView(getToggleSlider());
    }

    @Override // miui.systemui.controlcenter.panel.secondary.SecondaryPanelControllerBase, miui.systemui.controlcenter.utils.ControlCenterViewController
    public void onDestroy() {
        super.onDestroy();
        getSliderBinding().slider.setToggleSliderView(null);
    }

    @Override // miui.systemui.controlcenter.panel.secondary.SecondaryPanelControllerBase, miui.systemui.controlcenter.panel.SecondaryPanelRouter.SecondaryPanel
    public Boolean onKeyEvent(KeyEvent event) {
        n.g(event, "event");
        if (!getTouchingSlider() || event.getKeyCode() != 4) {
            return super.onKeyEvent(event);
        }
        Log.i(getTag(), "ignore back");
        return Boolean.FALSE;
    }

    @Override // miui.systemui.controlcenter.panel.secondary.SecondaryPanelControllerBase
    public void onSecondaryVisible(boolean z2, boolean z3) {
        SliderFromView fromView;
        ViewGroup content;
        SliderFromView fromView2;
        ViewGroup content2;
        super.onSecondaryVisible(z2, z3);
        if (z2) {
            BrightnessPanelParams panelParams = getPanelParams();
            if (panelParams == null || (fromView2 = panelParams.getFromView()) == null || (content2 = fromView2.getContent()) == null) {
                return;
            }
            CommonUtils.INSTANCE.setInvisible(content2);
            return;
        }
        BrightnessPanelParams panelParams2 = getPanelParams();
        if (panelParams2 == null || (fromView = panelParams2.getFromView()) == null || (content = fromView.getContent()) == null) {
            return;
        }
        CommonUtils.INSTANCE.setVisible(content);
    }

    @Override // miui.systemui.controlcenter.panel.secondary.BrightnessToView
    public void setOutlineRadius(float f2) {
        this.delegate.setOutlineRadius(f2);
    }

    @Override // miui.systemui.controlcenter.panel.secondary.BrightnessToView
    public void setProgressRadius(float f2) {
        this.delegate.setProgressRadius(f2);
    }

    @Override // miui.systemui.controlcenter.panel.secondary.SecondaryPanelControllerBase
    public void setSpecificHeight(int i2) {
    }

    @Override // miui.systemui.controlcenter.panel.secondary.SecondaryPanelControllerBase
    public void setUseSpecificHeight(boolean z2) {
    }

    @Override // miui.systemui.controlcenter.panel.secondary.SecondaryPanelControllerBase, miui.systemui.controlcenter.panel.SecondaryPanelRouter.SecondaryPanel
    public void startShow(Function0 completeAction) {
        n.g(completeAction, "completeAction");
        super.startShow(completeAction);
        this.binding.brightnessContainer.setTouching(false);
        this.preValue = this.delegate.getCurrentProgress();
    }

    @Override // miui.systemui.controlcenter.panel.secondary.SecondaryPanelControllerBase, miui.systemui.controlcenter.panel.secondary.ContentBgToView
    public View getContentBg() {
        View brightnessPanelBg = this.binding.brightnessPanelBg;
        n.f(brightnessPanelBg, "brightnessPanelBg");
        return brightnessPanelBg;
    }

    @Override // miui.systemui.controlcenter.panel.secondary.BrightnessToView
    public BrightnessPanelRootView getItemFrame() {
        BrightnessPanelRootView brightnessContainer = this.binding.brightnessContainer;
        n.f(brightnessContainer, "brightnessContainer");
        return brightnessContainer;
    }

    @Override // miui.systemui.controlcenter.panel.secondary.SecondaryPanelControllerBase
    public BrightnessPanelRootView getSecondaryPanelContainer() {
        BrightnessPanelRootView brightnessContainer = this.binding.brightnessContainer;
        n.f(brightnessContainer, "brightnessContainer");
        return brightnessContainer;
    }

    @Override // miui.systemui.controlcenter.panel.secondary.BrightnessToView
    public AnimateColorView getSliderIcon() {
        AnimateColorView icon = getSliderBinding().icon;
        n.f(icon, "icon");
        return icon;
    }

    @Override // miui.systemui.controlcenter.panel.secondary.BrightnessToView
    public LinearLayout getTilesContainer() {
        LinearLayout tilesContainer = this.binding.tilesContainer;
        n.f(tilesContainer, "tilesContainer");
        return tilesContainer;
    }

    @Override // miui.systemui.controlcenter.panel.secondary.BrightnessToView
    public LinearLayout getTilesContent() {
        LinearLayout tilesContent = this.binding.tilesContent;
        n.f(tilesContent, "tilesContent");
        return tilesContent;
    }
}
