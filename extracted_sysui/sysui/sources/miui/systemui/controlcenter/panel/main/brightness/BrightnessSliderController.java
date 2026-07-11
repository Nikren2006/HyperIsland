package miui.systemui.controlcenter.panel.main.brightness;

import I0.m;
import android.animation.AnimatorSet;
import android.graphics.drawable.AnimatedVectorDrawable;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LifecycleRegistry;
import com.android.systemui.plugins.miui.controlcenter.BrightnessControllerBase;
import com.android.systemui.plugins.miui.settings.SuperSaveModeController;
import java.util.List;
import java.util.concurrent.Executor;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.n;
import miui.systemui.animation.drawable.SVGUtils;
import miui.systemui.animation.drawable.SVGUtilsExt;
import miui.systemui.animation.drawable.VectorDrawableSetParams;
import miui.systemui.controlcenter.ConfigUtils;
import miui.systemui.controlcenter.R;
import miui.systemui.controlcenter.dagger.ControlCenterScope;
import miui.systemui.controlcenter.dagger.qualifiers.Qualifiers;
import miui.systemui.controlcenter.databinding.ToggleSliderItemViewBinding;
import miui.systemui.controlcenter.events.ControlCenterEventTracker;
import miui.systemui.controlcenter.panel.SecondaryPanelRouter;
import miui.systemui.controlcenter.panel.main.MainPanelContent;
import miui.systemui.controlcenter.panel.main.MainPanelController;
import miui.systemui.controlcenter.panel.main.MainPanelModeController;
import miui.systemui.controlcenter.panel.main.MainPanelStyleController;
import miui.systemui.controlcenter.panel.main.recyclerview.MainPanelItemViewHolder;
import miui.systemui.controlcenter.panel.main.recyclerview.MainPanelListItem;
import miui.systemui.controlcenter.panel.main.recyclerview.ToggleSliderViewHolder;
import miui.systemui.controlcenter.panel.secondary.BrightnessPanelParams;
import miui.systemui.controlcenter.panel.secondary.brightness.BrightnessPanelController;
import miui.systemui.controlcenter.widget.AnimateColorView;
import miui.systemui.controlcenter.widget.VerticalSeekBar;
import miui.systemui.controlcenter.windowview.ControlCenterWindowViewController;
import miui.systemui.controlcenter.windowview.ControlCenterWindowViewImpl;
import miui.systemui.dagger.qualifiers.Background;
import miui.systemui.dagger.qualifiers.Main;
import miui.systemui.util.CommonUtils;
import miui.systemui.util.DeviceStateManagerCompat;
import miui.systemui.util.HapticFeedback;
import miui.systemui.util.MiuiColorBlendToken;
import miui.systemui.util.MiuiMathUtils;
import miui.systemui.widget.FrameLayout;
import q.AbstractC0731a;
import q.AbstractC0732b;
import systemui.plugin.eventtracking.EventTracker;

/* JADX INFO: loaded from: classes.dex */
@ControlCenterScope
public final class BrightnessSliderController extends MainPanelListItem.Controller<ControlCenterWindowViewImpl> implements MainPanelContent, LifecycleOwner, MainPanelModeController.OnModeChangedListener {
    public static final Companion Companion = new Companion(null);
    private static final String TAG = "BrightnessSliderController";
    public static final int TYPE_BRIGHTNESS_SLIDER = 274442;
    private int afterValue;
    private VectorDrawableSetParams animateIconParams;
    private final Executor bgExecutor;
    private final BrightnessControllerBase brightnessController;
    private final E0.a brightnessPanelController;
    private VerticalSeekBar.ColorState currentColorState;
    private final BrightnessSliderController$foldStateCallback$1 foldStateCallback;
    private final HapticFeedback hapticFeedback;
    private boolean inMirror;
    private boolean isInEditMode;
    private boolean isTouch;
    private final ToggleSliderViewHolder.Factory itemFactory;
    private final List<MainPanelListItem> listItems;
    private boolean listening;
    private final View.OnLongClickListener longClickListener;
    private final E0.a mainPanelModeController;
    private final E0.a mainPanelStyleController;
    private int marginHorizontal;
    private int marginVertical;
    private final LifecycleRegistry mirrorLifecycle;
    private boolean needCallStopTrackingTouchMethod;
    private int preValue;
    private final int priority;
    private final boolean rightOrLeft;
    private final E0.a secondaryPanelRouter;
    private final BrightnessSliderController$seekBarListener$1 seekBarListener;
    private final int spanSize;
    private final SuperSaveModeController superSaveModeController;
    private final int type;
    private final Executor uiExecutor;
    private final E0.a windowViewController;
    private final Lifecycle windowViewLifecycle;

    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Type inference failed for: r2v1, types: [miui.systemui.controlcenter.panel.main.brightness.BrightnessSliderController$foldStateCallback$1] */
    public BrightnessSliderController(@Qualifiers.WindowView ControlCenterWindowViewImpl windowView, @Qualifiers.ControlCenter Lifecycle windowViewLifecycle, ToggleSliderViewHolder.Factory itemFactory, @Main Executor uiExecutor, @Background Executor bgExecutor, E0.a windowViewController, E0.a mainPanelStyleController, E0.a mainPanelModeController, HapticFeedback hapticFeedback, E0.a secondaryPanelRouter, E0.a brightnessPanelController, BrightnessControllerBase brightnessController, SuperSaveModeController superSaveModeController) {
        super(windowView, windowViewLifecycle);
        n.g(windowView, "windowView");
        n.g(windowViewLifecycle, "windowViewLifecycle");
        n.g(itemFactory, "itemFactory");
        n.g(uiExecutor, "uiExecutor");
        n.g(bgExecutor, "bgExecutor");
        n.g(windowViewController, "windowViewController");
        n.g(mainPanelStyleController, "mainPanelStyleController");
        n.g(mainPanelModeController, "mainPanelModeController");
        n.g(hapticFeedback, "hapticFeedback");
        n.g(secondaryPanelRouter, "secondaryPanelRouter");
        n.g(brightnessPanelController, "brightnessPanelController");
        n.g(brightnessController, "brightnessController");
        n.g(superSaveModeController, "superSaveModeController");
        this.windowViewLifecycle = windowViewLifecycle;
        this.itemFactory = itemFactory;
        this.uiExecutor = uiExecutor;
        this.bgExecutor = bgExecutor;
        this.windowViewController = windowViewController;
        this.mainPanelStyleController = mainPanelStyleController;
        this.mainPanelModeController = mainPanelModeController;
        this.hapticFeedback = hapticFeedback;
        this.secondaryPanelRouter = secondaryPanelRouter;
        this.brightnessPanelController = brightnessPanelController;
        this.brightnessController = brightnessController;
        this.superSaveModeController = superSaveModeController;
        this.mirrorLifecycle = new LifecycleRegistry(this);
        this.animateIconParams = new VectorDrawableSetParams();
        this.marginHorizontal = getResources().getDimensionPixelSize(R.dimen.brightness_slider_shadow_margin_horizontal);
        this.marginVertical = getResources().getDimensionPixelSize(R.dimen.brightness_slider_shadow_margin_vertical);
        this.seekBarListener = new BrightnessSliderController$seekBarListener$1(this, windowView);
        this.foldStateCallback = new DeviceStateManagerCompat.FoldStateCallback() { // from class: miui.systemui.controlcenter.panel.main.brightness.BrightnessSliderController$foldStateCallback$1
            @Override // miui.systemui.util.DeviceStateManagerCompat.FoldStateCallback
            public void onFoldStateChanged(boolean z2) {
                if (this.this$0.mirrorLifecycle.getCurrentState() == Lifecycle.State.STARTED) {
                    VerticalSeekBar slider = this.this$0.getSlider();
                    if (slider != null) {
                        this.this$0.seekBarListener.onStopTrackingTouch(slider);
                    }
                    Log.w("BrightnessSliderController", "Fold state changed, reset brightness slider.");
                }
            }
        };
        this.longClickListener = new View.OnLongClickListener() { // from class: miui.systemui.controlcenter.panel.main.brightness.c
            @Override // android.view.View.OnLongClickListener
            public final boolean onLongClick(View view) {
                return BrightnessSliderController.longClickListener$lambda$3(this.f5359a, view);
            }
        };
        this.currentColorState = VerticalSeekBar.ColorState.HIGHLIGHT;
        this.type = TYPE_BRIGHTNESS_SLIDER;
        this.spanSize = 1;
        this.priority = 31;
        this.rightOrLeft = true;
        this.listItems = m.f(this);
    }

    private final void checkRestrictionAndSetEnabled() {
        this.bgExecutor.execute(new Runnable() { // from class: miui.systemui.controlcenter.panel.main.brightness.a
            @Override // java.lang.Runnable
            public final void run() {
                BrightnessSliderController.checkRestrictionAndSetEnabled$lambda$11(this.f5356a);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void checkRestrictionAndSetEnabled$lambda$11(final BrightnessSliderController this$0) {
        n.g(this$0, "this$0");
        final AbstractC0731a.C0165a c0165aD = AbstractC0732b.d(this$0.getContext(), "no_config_brightness", ((ControlCenterWindowViewController) this$0.windowViewController.get()).getCurrentUserId());
        if (c0165aD != null) {
            Log.i(TAG, "brightness control is blocked, device is managed by admin!");
        }
        this$0.uiExecutor.execute(new Runnable() { // from class: miui.systemui.controlcenter.panel.main.brightness.b
            @Override // java.lang.Runnable
            public final void run() {
                BrightnessSliderController.checkRestrictionAndSetEnabled$lambda$11$lambda$10(this.f5357a, c0165aD);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void checkRestrictionAndSetEnabled$lambda$11$lambda$10(BrightnessSliderController this$0, AbstractC0731a.C0165a c0165a) {
        n.g(this$0, "this$0");
        VerticalSeekBar slider = this$0.getSlider();
        if (slider != null) {
            slider.setEnforcedAdmin(c0165a);
        }
    }

    private final FrameLayout getShadowView() {
        ToggleSliderItemViewBinding binding;
        ToggleSliderViewHolder sliderHolder = getSliderHolder();
        if (sliderHolder == null || (binding = sliderHolder.getBinding()) == null) {
            return null;
        }
        return binding.brightnessShadow;
    }

    private final ToggleSliderViewHolder getSliderHolder() {
        MainPanelItemViewHolder holder = getHolder();
        if (holder instanceof ToggleSliderViewHolder) {
            return (ToggleSliderViewHolder) holder;
        }
        return null;
    }

    private final boolean getSupportLongClick() {
        return (this.superSaveModeController.isActive() || ((MainPanelStyleController) this.mainPanelStyleController.get()).getStyle() == MainPanelController.Style.COMPACT) ? false : true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean longClickListener$lambda$3(BrightnessSliderController this$0, View view) {
        n.g(this$0, "this$0");
        if (!this$0.supportLongClick()) {
            MainPanelItemViewHolder.Companion.releaseTouchNow();
            return false;
        }
        ControlCenterEventTracker.Companion.trackBrightnessSeekbarLongClickEvent(EventTracker.Companion.getScreenType(this$0.getContext()), this$0.getContext().getResources().getConfiguration().orientation, "滑动条", ControlCenterEventTracker.QS_BRIGHTNESS_BAR);
        if (HapticFeedback.Companion.getIS_SUPPORT_LINEAR_MOTOR_VIBRATE()) {
            this$0.hapticFeedback.longClick();
        } else {
            VerticalSeekBar slider = this$0.getSlider();
            if (slider != null) {
                slider.performHapticFeedback(0);
            }
        }
        SecondaryPanelRouter secondaryPanelRouter = (SecondaryPanelRouter) this$0.secondaryPanelRouter.get();
        ToggleSliderViewHolder sliderHolder = this$0.getSliderHolder();
        if (sliderHolder == null) {
            sliderHolder = null;
        }
        secondaryPanelRouter.routeToBrightnessPanel(new BrightnessPanelParams(sliderHolder));
        return true;
    }

    private final void setInMirror(boolean z2) {
        if (this.inMirror == z2) {
            return;
        }
        this.inMirror = z2;
        ToggleSliderViewHolder sliderHolder = getSliderHolder();
        if (sliderHolder != null) {
            sliderHolder.setInMirror(z2);
        }
        ((ControlCenterWindowViewController) this.windowViewController.get()).getExpandController().setInMirror(z2);
    }

    private final boolean supportLongClick() {
        if (!getSupportLongClick()) {
            Log.w(TAG, "long click: not support");
            return false;
        }
        if (this.mirrorLifecycle.getCurrentState() == Lifecycle.State.STARTED) {
            Log.w(TAG, "long click: in mirror");
            return false;
        }
        if (((MainPanelModeController) this.mainPanelModeController.get()).getInNormalMode() && !((MainPanelModeController) this.mainPanelModeController.get()).getInPendingEditMode()) {
            return true;
        }
        Log.w(TAG, "long click: not in normal mode");
        return false;
    }

    private final void updateIcon() {
        ToggleSliderItemViewBinding binding;
        AnimateColorView animateColorView;
        AnimatorSet animatorSetCompat;
        ToggleSliderViewHolder sliderHolder = getSliderHolder();
        if (sliderHolder == null || (binding = sliderHolder.getBinding()) == null || (animateColorView = binding.icon) == null) {
            return;
        }
        if (!SVGUtilsExt.INSTANCE.getSupportVectorDrawableParams()) {
            animateColorView.setImageResource(R.drawable.ic_brightness_slider);
            return;
        }
        animateColorView.setImageResource(R.drawable.ic_brightness_slider_animate_icon);
        Drawable drawable = animateColorView.getDrawable();
        AnimatedVectorDrawable animatedVectorDrawable = drawable instanceof AnimatedVectorDrawable ? (AnimatedVectorDrawable) drawable : null;
        if (animatedVectorDrawable == null || (animatorSetCompat = SVGUtilsExt.getAnimatorSetCompat(animatedVectorDrawable)) == null) {
            return;
        }
        this.animateIconParams.clearDrawableParams();
        SVGUtils.analyzeAnimator(animatorSetCompat, this.animateIconParams, animateColorView.getContext().getColor(R.color.toggle_slider_icon_color));
        updateIconProgress(true);
    }

    private final void updateIconProgress(boolean z2) {
        VerticalSeekBar slider;
        ToggleSliderItemViewBinding binding;
        AnimateColorView animateColorView;
        if (SVGUtilsExt.INSTANCE.getSupportVectorDrawableParams() && (slider = getSlider()) != null) {
            float fLerpInv = MiuiMathUtils.INSTANCE.lerpInv(slider.getMin(), slider.getMax(), slider.getValue());
            this.animateIconParams.setFraction(fLerpInv);
            ToggleSliderViewHolder sliderHolder = getSliderHolder();
            if (sliderHolder == null || (binding = sliderHolder.getBinding()) == null || (animateColorView = binding.icon) == null) {
                return;
            }
            if (fLerpInv >= 0.12f) {
                VerticalSeekBar.ColorState colorState = this.currentColorState;
                VerticalSeekBar.ColorState colorState2 = VerticalSeekBar.ColorState.HIGHLIGHT;
                if (colorState != colorState2 || z2) {
                    MiuiColorBlendToken miuiColorBlendToken = MiuiColorBlendToken.INSTANCE;
                    animateColorView.updateIconColor(miuiColorBlendToken.getCC_BRIGHTNESS_SLIDER_ICON_BLEND_COLORS_DEFAULT(), miuiColorBlendToken.getCC_BRIGHTNESS_SLIDER_ICON_BLEND_COLORS(), R.color.toggle_slider_icon_color, R.color.toggle_slider_brightness_icon_color, !z2);
                    this.currentColorState = colorState2;
                }
            } else {
                VerticalSeekBar.ColorState colorState3 = this.currentColorState;
                VerticalSeekBar.ColorState colorState4 = VerticalSeekBar.ColorState.NORMAL;
                if (colorState3 != colorState4 || z2) {
                    MiuiColorBlendToken miuiColorBlendToken2 = MiuiColorBlendToken.INSTANCE;
                    animateColorView.updateIconColor(miuiColorBlendToken2.getCC_BRIGHTNESS_SLIDER_ICON_BLEND_COLORS(), miuiColorBlendToken2.getCC_BRIGHTNESS_SLIDER_ICON_BLEND_COLORS_DEFAULT(), R.color.toggle_slider_brightness_icon_color, R.color.toggle_slider_icon_color, !z2);
                    this.currentColorState = colorState4;
                }
            }
            animateColorView.invalidate();
        }
    }

    public static /* synthetic */ void updateIconProgress$default(BrightnessSliderController brightnessSliderController, boolean z2, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            z2 = false;
        }
        brightnessSliderController.updateIconProgress(z2);
    }

    private final void updateSize() {
        this.marginHorizontal = getContext().getResources().getDimensionPixelSize(R.dimen.brightness_slider_shadow_margin_horizontal);
        this.marginVertical = getContext().getResources().getDimensionPixelSize(R.dimen.brightness_slider_shadow_margin_vertical);
    }

    @Override // miui.systemui.controlcenter.panel.main.MainPanelContent
    public boolean available(boolean z2) {
        return ((MainPanelModeController) this.mainPanelModeController.get()).getMode() != MainPanelController.Mode.EDIT;
    }

    @Override // miui.systemui.controlcenter.panel.main.MainPanelContent
    public MainPanelItemViewHolder createViewHolder(ViewGroup parent, int i2) {
        n.g(parent, "parent");
        if (i2 != 274442) {
            return null;
        }
        ToggleSliderItemViewBinding toggleSliderItemViewBindingInflate = ToggleSliderItemViewBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        n.f(toggleSliderItemViewBindingInflate, "inflate(...)");
        return this.itemFactory.create(toggleSliderItemViewBindingInflate);
    }

    public final boolean getInMirror() {
        return this.inMirror;
    }

    @Override // androidx.lifecycle.LifecycleOwner
    public Lifecycle getLifecycle() {
        return this.mirrorLifecycle;
    }

    @Override // miui.systemui.controlcenter.panel.main.MainPanelContent
    public List<MainPanelListItem> getListItems() {
        return this.listItems;
    }

    @Override // miui.systemui.controlcenter.panel.main.recyclerview.MainPanelListItem.Controller
    public boolean getListening() {
        return this.listening;
    }

    @Override // miui.systemui.controlcenter.panel.main.MainPanelContent
    public int getPriority() {
        return this.priority;
    }

    @Override // miui.systemui.controlcenter.panel.main.MainPanelContent
    public boolean getRightOrLeft() {
        return this.rightOrLeft;
    }

    public final VerticalSeekBar getSlider() {
        ToggleSliderItemViewBinding binding;
        ToggleSliderViewHolder sliderHolder = getSliderHolder();
        if (sliderHolder == null || (binding = sliderHolder.getBinding()) == null) {
            return null;
        }
        return binding.slider;
    }

    @Override // miui.systemui.controlcenter.panel.main.recyclerview.MainPanelListItem
    public int getSpanSize() {
        return this.spanSize;
    }

    @Override // miui.systemui.controlcenter.panel.main.recyclerview.MainPanelListItem
    public int getType() {
        return this.type;
    }

    @Override // miui.systemui.controlcenter.panel.main.recyclerview.MainPanelListItem
    public void onBindViewHolder() {
        VerticalSeekBar slider = getSlider();
        if (slider != null) {
            slider.setContentDescription(slider.getContext().getString(R.string.accessibility_brightness));
        }
        updateIcon();
    }

    @Override // miui.systemui.controlcenter.panel.main.MainPanelContent
    public void onBrightnessChange(float f2, boolean z2) {
        setInMirror(z2 && f2 < 1.0f);
    }

    @Override // miui.systemui.controlcenter.utils.ControlCenterViewController, miui.systemui.controlcenter.ConfigUtils.OnConfigChangeListener
    public void onConfigurationChanged(int i2) {
        VerticalSeekBar slider;
        ConfigUtils configUtils = ConfigUtils.INSTANCE;
        boolean zDimensionsChanged = configUtils.dimensionsChanged(i2);
        if (configUtils.textsChanged(i2) && (slider = getSlider()) != null) {
            slider.setContentDescription(getContext().getString(R.string.accessibility_brightness));
        }
        if (zDimensionsChanged) {
            ToggleSliderViewHolder sliderHolder = getSliderHolder();
            if (sliderHolder != null) {
                sliderHolder.updateSize();
            }
            updateSize();
        }
        if (configUtils.colorsChanged(i2) || configUtils.blurChanged(i2)) {
            updateIcon();
        }
    }

    @Override // miui.systemui.util.ViewController
    public void onCreate() {
        super.onCreate();
        ((MainPanelModeController) this.mainPanelModeController.get()).addOnModeChangedListener(this);
        ((ControlCenterWindowViewController) this.windowViewController.get()).addFoldStateCallback(this.foldStateCallback);
    }

    @Override // miui.systemui.controlcenter.utils.ControlCenterViewController
    public void onDestroy() {
        ToggleSliderItemViewBinding binding;
        AnimateColorView animateColorView;
        super.onDestroy();
        ToggleSliderViewHolder sliderHolder = getSliderHolder();
        if (sliderHolder != null && (binding = sliderHolder.getBinding()) != null && (animateColorView = binding.icon) != null) {
            animateColorView.recycle();
        }
        ((MainPanelModeController) this.mainPanelModeController.get()).removeOnModeChangedListener(this);
        ((ControlCenterWindowViewController) this.windowViewController.get()).removeFoldStateCallback(this.foldStateCallback);
    }

    @Override // miui.systemui.controlcenter.panel.main.MainPanelModeController.OnModeChangedListener
    public void onModeChanged() {
        VerticalSeekBar slider;
        MainPanelController.Mode mode = ((MainPanelModeController) this.mainPanelModeController.get()).getMode();
        Log.d(TAG, "updatePanelMode mode: " + mode);
        this.isInEditMode = mode == MainPanelController.Mode.EDIT;
        VerticalSeekBar slider2 = getSlider();
        if (slider2 == null || !slider2.getTracking() || (slider = getSlider()) == null) {
            return;
        }
        slider.stopTrackingTouch(getSlider());
    }

    @Override // miui.systemui.controlcenter.panel.main.MainPanelContent
    public void onSpreadChange(MainPanelItemViewHolder holder, float f2, float f3) {
        n.g(holder, "holder");
        holder.setScaleFactor(f2);
        holder.setSpreadSlideTransX(f3);
        holder.onFrameCallback();
    }

    @Override // miui.systemui.controlcenter.panel.main.recyclerview.MainPanelListItem.Controller, miui.systemui.controlcenter.utils.ControlCenterViewController
    public void onStop() {
        super.onStop();
        VerticalSeekBar slider = getSlider();
        if (slider != null) {
            slider.resetDragAnim();
        }
        ToggleSliderViewHolder sliderHolder = getSliderHolder();
        if (sliderHolder != null) {
            sliderHolder.setIgnoreHolderScale(false);
            sliderHolder.setIgnoreHolderTranslation(false);
        }
        if (this.afterValue - this.preValue != 0 && ((ControlCenterWindowViewController) this.windowViewController.get()).isCollapsed() && this.isTouch) {
            ControlCenterEventTracker.Companion.trackBrightnessSeekbarAdjustEvent(false, EventTracker.Companion.getScreenType(getContext()), this.preValue, this.afterValue);
            this.isTouch = false;
        }
    }

    @Override // miui.systemui.controlcenter.utils.ControlCenterViewController
    public void onSuperPowerModeChanged(boolean z2) {
        VerticalSeekBar slider;
        if (!getListening() || (slider = getSlider()) == null) {
            return;
        }
        slider.setOnLongClickListener(getSupportLongClick() ? this.longClickListener : null);
    }

    @Override // miui.systemui.controlcenter.panel.main.recyclerview.MainPanelListItem
    public void onUnbindViewHolder() {
        setListening(false);
    }

    @Override // miui.systemui.controlcenter.panel.main.recyclerview.MainPanelListItem.Controller
    public void setListening(boolean z2) {
        if (this.listening == z2) {
            return;
        }
        this.listening = z2;
        if (z2) {
            checkRestrictionAndSetEnabled();
            this.brightnessController.addToggleSlider(getSlider());
            ((BrightnessPanelController) this.brightnessPanelController.get()).getDelegate().setListening(true);
            VerticalSeekBar slider = getSlider();
            if (slider != null) {
                slider.setOnLongClickListener(getSupportLongClick() ? this.longClickListener : null);
                slider.setOnSeekBarChangeListener(this.seekBarListener);
            }
            this.brightnessController.registerCallbacks();
            return;
        }
        this.brightnessController.removeToggleSlider(getSlider());
        ((BrightnessPanelController) this.brightnessPanelController.get()).getDelegate().setListening(false);
        VerticalSeekBar slider2 = getSlider();
        if (slider2 != null) {
            if (this.needCallStopTrackingTouchMethod) {
                this.needCallStopTrackingTouchMethod = false;
                slider2.stopTrackingTouch(slider2);
            }
            slider2.setOnLongClickListener(null);
            slider2.setOnSeekBarChangeListener(null);
        }
        this.brightnessController.unregisterCallbacks();
    }

    public final void updateShadow(boolean z2) {
        FrameLayout shadowView = getShadowView();
        if (shadowView != null) {
            if (!z2) {
                shadowView.setAlpha(0.0f);
                return;
            }
            shadowView.setAlpha(0.07f);
            shadowView.setBackgroundResource(R.drawable.brightness_mirror_shade);
            CommonUtils commonUtils = CommonUtils.INSTANCE;
            int i2 = this.marginHorizontal;
            int i3 = this.marginVertical;
            commonUtils.setMargins(shadowView, i2, i3, i2, i3, true);
        }
    }
}
