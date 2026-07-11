package miui.systemui.devicecontrols.ui;

import android.R;
import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.app.Dialog;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.drawable.ClipDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.Icon;
import android.graphics.drawable.LayerDrawable;
import android.graphics.drawable.StateListDrawable;
import android.service.controls.Control;
import android.service.controls.actions.ControlAction;
import android.service.controls.templates.ControlTemplate;
import android.service.controls.templates.ControlTemplateExpose;
import android.service.controls.templates.RangeTemplate;
import android.service.controls.templates.StatelessTemplate;
import android.service.controls.templates.TemperatureControlTemplate;
import android.service.controls.templates.ThumbnailTemplate;
import android.service.controls.templates.ToggleRangeTemplate;
import android.service.controls.templates.ToggleTemplate;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Interpolator;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.ColorInt;
import androidx.annotation.VisibleForTesting;
import d1.InterfaceC0324c;
import java.util.List;
import java.util.Set;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import miui.systemui.Interpolators;
import miui.systemui.controlcenter.ConfigUtils;
import miui.systemui.controls.ColorUtils;
import miui.systemui.devicecontrols.CustomIconCache;
import miui.systemui.devicecontrols.controller.ControlsController;
import miui.systemui.devicecontrols.management.BaseHolder;
import miui.systemui.devicecontrols.management.ElementWrapper;
import miui.systemui.devicecontrols.util.ControlsUtils;
import miui.systemui.util.CommonUtils;
import miui.systemui.util.HapticFeedback;
import miui.systemui.util.MiuiMathUtils;
import miui.systemui.util.ThemeUtils;
import miui.systemui.util.ThreadUtils;
import miui.systemui.util.concurrency.DelayableExecutor;

/* JADX INFO: loaded from: classes3.dex */
public class ControlViewHolder extends BaseHolder {
    private static final int ALPHA_DISABLED = 0;
    private static final int ALPHA_ENABLED = 255;
    public static final int MAX_LEVEL = 10000;
    public static final int MIN_LEVEL = 0;
    public static final long STATE_ANIMATION_DURATION = 700;
    private static final float STATUS_ALPHA_DIMMED = 0.45f;
    private static final float STATUS_ALPHA_ENABLED = 1.0f;
    private final GradientDrawable baseLayer;
    private Behavior behavior;
    private final DelayableExecutor bgExecutor;
    private final ClipDrawable clipLayer;
    private final Context context;
    private final ControlActionCoordinator controlActionCoordinator;
    private final ControlsController controlsController;
    public ControlWithState cws;
    private final View divider;
    private boolean enabledForState;
    private final ImageView icon;
    private final CustomIconCache iconCache;
    private boolean isLoading;
    private ControlAction lastAction;
    private Dialog lastChallengeDialog;
    private final ViewGroup layout;
    private CharSequence nextStatusText;
    private final Function0 onDialogCancel;
    private final View ripple;
    private ValueAnimator stateAnimator;
    private final TextView status;
    private Animator statusAnimator;
    private final LinearLayout statusLine;
    private final TextView subtitle;
    private final TextView title;
    private final float toggleBackgroundIntensity;
    private final DelayableExecutor uiExecutor;
    private boolean userInteractionInProgress;
    private Dialog visibleDialog;
    public static final Companion Companion = new Companion(null);
    private static final Set<Integer> FORCE_PANEL_DEVICES = I0.K.d(49, 50);
    private static final int[] ATTR_ENABLED = {R.attr.state_enabled};
    private static final int[] ATTR_DISABLED = {-16842910};

    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public static /* synthetic */ InterfaceC0324c findBehaviorClass$default(Companion companion, int i2, ControlTemplate controlTemplate, int i3, String str, int i4, Object obj) {
            if ((i4 & 8) != 0) {
                str = "";
            }
            return companion.findBehaviorClass(i2, controlTemplate, i3, str);
        }

        public final InterfaceC0324c findBehaviorClass(int i2, ControlTemplate template, int i3, String packageName) {
            kotlin.jvm.internal.n.g(template, "template");
            kotlin.jvm.internal.n.g(packageName, "packageName");
            if (i2 != 1) {
                return kotlin.jvm.internal.z.b(StatusBehavior.class);
            }
            if (kotlin.jvm.internal.n.c(template, ControlTemplateExpose.INSTANCE.getNO_TEMPLATE())) {
                return kotlin.jvm.internal.z.b(TouchBehavior.class);
            }
            if (template instanceof ThumbnailTemplate) {
                return kotlin.jvm.internal.z.b(ThumbnailBehavior.class);
            }
            if (i3 == 50 && !kotlin.jvm.internal.n.c(packageName, "com.xiaomi.smarthome")) {
                return kotlin.jvm.internal.z.b(TouchBehavior.class);
            }
            if (template instanceof ToggleTemplate) {
                return kotlin.jvm.internal.z.b(ToggleBehavior.class);
            }
            if (template instanceof StatelessTemplate) {
                return kotlin.jvm.internal.z.b(TouchBehavior.class);
            }
            if (!(template instanceof ToggleRangeTemplate) && !(template instanceof RangeTemplate)) {
                return kotlin.jvm.internal.z.b(template instanceof TemperatureControlTemplate ? TemperatureControlBehavior.class : DefaultBehavior.class);
            }
            return kotlin.jvm.internal.z.b(ToggleRangeBehavior.class);
        }

        private Companion() {
        }
    }

    /* JADX INFO: renamed from: miui.systemui.devicecontrols.ui.ControlViewHolder$setErrorStatus$1, reason: invalid class name */
    public static final class AnonymousClass1 extends kotlin.jvm.internal.o implements Function0 {
        final /* synthetic */ String $text;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass1(String str) {
            super(0);
            this.$text = str;
        }

        @Override // kotlin.jvm.functions.Function0
        public /* bridge */ /* synthetic */ Object invoke() {
            m125invoke();
            return H0.s.f314a;
        }

        /* JADX INFO: renamed from: invoke, reason: collision with other method in class */
        public final void m125invoke() {
            ControlViewHolder controlViewHolder = ControlViewHolder.this;
            String text = this.$text;
            kotlin.jvm.internal.n.f(text, "$text");
            controlViewHolder.setStatusText(text, true);
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ControlViewHolder(ViewGroup layout, ControlsController controlsController, DelayableExecutor uiExecutor, DelayableExecutor bgExecutor, ControlActionCoordinator controlActionCoordinator, CustomIconCache iconCache) {
        super(layout);
        kotlin.jvm.internal.n.g(layout, "layout");
        kotlin.jvm.internal.n.g(controlsController, "controlsController");
        kotlin.jvm.internal.n.g(uiExecutor, "uiExecutor");
        kotlin.jvm.internal.n.g(bgExecutor, "bgExecutor");
        kotlin.jvm.internal.n.g(controlActionCoordinator, "controlActionCoordinator");
        kotlin.jvm.internal.n.g(iconCache, "iconCache");
        this.layout = layout;
        this.controlsController = controlsController;
        this.uiExecutor = uiExecutor;
        this.bgExecutor = bgExecutor;
        this.controlActionCoordinator = controlActionCoordinator;
        this.iconCache = iconCache;
        this.enabledForState = true;
        this.toggleBackgroundIntensity = layout.getContext().getResources().getFraction(miui.systemui.devicecontrols.R.fraction.controls_toggle_bg_intensity, 1, 1);
        View viewRequireViewById = layout.requireViewById(miui.systemui.devicecontrols.R.id.icon);
        kotlin.jvm.internal.n.f(viewRequireViewById, "requireViewById(...)");
        this.icon = (ImageView) viewRequireViewById;
        View viewRequireViewById2 = layout.requireViewById(miui.systemui.devicecontrols.R.id.status);
        kotlin.jvm.internal.n.f(viewRequireViewById2, "requireViewById(...)");
        TextView textView = (TextView) viewRequireViewById2;
        this.status = textView;
        this.nextStatusText = "";
        View viewRequireViewById3 = layout.requireViewById(miui.systemui.devicecontrols.R.id.title);
        kotlin.jvm.internal.n.f(viewRequireViewById3, "requireViewById(...)");
        this.title = (TextView) viewRequireViewById3;
        View viewRequireViewById4 = layout.requireViewById(miui.systemui.devicecontrols.R.id.subtitle);
        kotlin.jvm.internal.n.f(viewRequireViewById4, "requireViewById(...)");
        this.subtitle = (TextView) viewRequireViewById4;
        View viewRequireViewById5 = layout.requireViewById(miui.systemui.devicecontrols.R.id.v_border);
        kotlin.jvm.internal.n.f(viewRequireViewById5, "requireViewById(...)");
        this.divider = viewRequireViewById5;
        View viewRequireViewById6 = layout.requireViewById(miui.systemui.devicecontrols.R.id.ll_status);
        kotlin.jvm.internal.n.f(viewRequireViewById6, "requireViewById(...)");
        this.statusLine = (LinearLayout) viewRequireViewById6;
        View viewRequireViewById7 = layout.requireViewById(miui.systemui.devicecontrols.R.id.v_ripple);
        kotlin.jvm.internal.n.f(viewRequireViewById7, "requireViewById(...)");
        this.ripple = viewRequireViewById7;
        Context context = layout.getContext();
        kotlin.jvm.internal.n.f(context, "getContext(...)");
        this.context = context;
        this.onDialogCancel = new ControlViewHolder$onDialogCancel$1(this);
        Drawable background = layout.getBackground();
        kotlin.jvm.internal.n.e(background, "null cannot be cast to non-null type android.graphics.drawable.LayerDrawable");
        LayerDrawable layerDrawable = (LayerDrawable) background;
        layerDrawable.mutate();
        Drawable drawableFindDrawableByLayerId = layerDrawable.findDrawableByLayerId(miui.systemui.devicecontrols.R.id.clip_layer);
        kotlin.jvm.internal.n.e(drawableFindDrawableByLayerId, "null cannot be cast to non-null type android.graphics.drawable.ClipDrawable");
        this.clipLayer = (ClipDrawable) drawableFindDrawableByLayerId;
        Drawable drawableFindDrawableByLayerId2 = layerDrawable.findDrawableByLayerId(miui.systemui.devicecontrols.R.id.background);
        kotlin.jvm.internal.n.e(drawableFindDrawableByLayerId2, "null cannot be cast to non-null type android.graphics.drawable.GradientDrawable");
        this.baseLayer = (GradientDrawable) drawableFindDrawableByLayerId2;
        textView.setSelected(true);
    }

    private final void animateBackgroundChange(boolean z2, boolean z3, int i2) {
        List listJ;
        ColorStateList customColor;
        this.enabledForState = z3;
        ThemeUtils themeUtils = ThemeUtils.INSTANCE;
        Context context = this.context;
        int i3 = miui.systemui.devicecontrols.R.attr.deviceControlItemBgColor;
        int themedAttrColor = themeUtils.getThemedAttrColor(context, i3);
        if (z3) {
            Control control = getCws().getControl();
            listJ = I0.m.j(Integer.valueOf((control == null || (customColor = control.getCustomColor()) == null) ? themeUtils.getThemedColor(this.context, i2) : customColor.getColorForState(new int[]{R.attr.state_enabled}, customColor.getDefaultColor())), 255);
        } else {
            listJ = I0.m.j(Integer.valueOf(themeUtils.getThemedAttrColor(this.context, i3)), 0);
        }
        int iIntValue = ((Number) listJ.get(0)).intValue();
        int iIntValue2 = ((Number) listJ.get(1)).intValue();
        if (this.behavior instanceof ToggleRangeBehavior) {
            themedAttrColor = ColorUtils.INSTANCE.blendARGB(themedAttrColor, iIntValue, this.toggleBackgroundIntensity);
        }
        int i4 = themedAttrColor;
        Drawable drawable = this.clipLayer.getDrawable();
        if (drawable != null) {
            this.clipLayer.setAlpha(0);
            ValueAnimator valueAnimator = this.stateAnimator;
            if (valueAnimator != null) {
                valueAnimator.cancel();
            }
            if (z2) {
                startBackgroundAnimation(drawable, iIntValue2, iIntValue, i4);
            } else {
                applyBackgroundChange(drawable, iIntValue2, iIntValue, i4, 1.0f);
            }
        }
    }

    private final void animateStatusChange(boolean z2, Function0 function0) {
        Animator animator = this.statusAnimator;
        if (animator != null) {
            animator.cancel();
        }
        if (!z2) {
            function0.invoke();
            return;
        }
        if (this.isLoading) {
            function0.invoke();
            ObjectAnimator objectAnimatorOfFloat = ObjectAnimator.ofFloat(this.status, "alpha", 0.45f);
            objectAnimatorOfFloat.setRepeatMode(2);
            objectAnimatorOfFloat.setRepeatCount(-1);
            objectAnimatorOfFloat.setDuration(500L);
            objectAnimatorOfFloat.setInterpolator(Interpolators.LINEAR);
            objectAnimatorOfFloat.setStartDelay(900L);
            objectAnimatorOfFloat.start();
            this.statusAnimator = objectAnimatorOfFloat;
            return;
        }
        ObjectAnimator objectAnimatorOfFloat2 = ObjectAnimator.ofFloat(this.status, "alpha", 0.0f);
        objectAnimatorOfFloat2.setDuration(200L);
        Interpolator interpolator = Interpolators.LINEAR;
        objectAnimatorOfFloat2.setInterpolator(interpolator);
        ObjectAnimator objectAnimatorOfFloat3 = ObjectAnimator.ofFloat(this.status, "alpha", 1.0f);
        objectAnimatorOfFloat3.setDuration(200L);
        objectAnimatorOfFloat3.setInterpolator(interpolator);
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playSequentially(objectAnimatorOfFloat2, objectAnimatorOfFloat3);
        animatorSet.addListener(new AnimatorListenerAdapter() { // from class: miui.systemui.devicecontrols.ui.ControlViewHolder$animateStatusChange$2$1
            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationEnd(Animator animator2) {
                this.this$0.statusAnimator = null;
            }
        });
        animatorSet.start();
        this.statusAnimator = animatorSet;
    }

    private final void applyBackgroundChange(Drawable drawable, int i2, @ColorInt int i3, @ColorInt int i4, float f2) {
        drawable.setAlpha(i2);
        if (drawable instanceof GradientDrawable) {
            ((GradientDrawable) drawable).setColor(i3);
        }
        this.baseLayer.setColor(i4);
        this.layout.setAlpha(f2);
    }

    public static /* synthetic */ void applyRenderInfo$miui_devicecontrols_release$default(ControlViewHolder controlViewHolder, boolean z2, int i2, boolean z3, int i3, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: applyRenderInfo");
        }
        if ((i3 & 4) != 0) {
            z3 = false;
        }
        controlViewHolder.applyRenderInfo$miui_devicecontrols_release(z2, i2, z3);
    }

    public static /* synthetic */ Behavior bindBehavior$default(ControlViewHolder controlViewHolder, Behavior behavior, InterfaceC0324c interfaceC0324c, int i2, int i3, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: bindBehavior");
        }
        if ((i3 & 4) != 0) {
            i2 = 0;
        }
        return controlViewHolder.bindBehavior(behavior, interfaceC0324c, i2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean bindData$lambda$5$lambda$4(ControlViewHolder this$0, View view) {
        kotlin.jvm.internal.n.g(this$0, "this$0");
        this$0.controlActionCoordinator.longPress(this$0);
        return true;
    }

    private final void setEnabled(boolean z2) {
        this.title.setEnabled(z2);
        this.subtitle.setEnabled(z2);
        this.status.setEnabled(z2);
        this.icon.setEnabled(z2);
        this.divider.setEnabled(z2);
    }

    public static /* synthetic */ void setStatusText$default(ControlViewHolder controlViewHolder, CharSequence charSequence, boolean z2, int i2, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: setStatusText");
        }
        if ((i2 & 2) != 0) {
            z2 = false;
        }
        controlViewHolder.setStatusText(charSequence, z2);
    }

    private final void setViewColor(ColorStateList colorStateList) {
        this.title.setTextColor(colorStateList);
        this.status.setTextColor(colorStateList);
        this.subtitle.setTextColor(colorStateList);
        this.divider.setAlpha(1.0f);
        this.divider.setBackgroundTintList(colorStateList);
    }

    private final void startBackgroundAnimation(final Drawable drawable, int i2, @ColorInt final int i3, @ColorInt final int i4) {
        ColorStateList color;
        final int defaultColor = (!(drawable instanceof GradientDrawable) || (color = ((GradientDrawable) drawable).getColor()) == null) ? i3 : color.getDefaultColor();
        ColorStateList color2 = this.baseLayer.getColor();
        final int defaultColor2 = color2 != null ? color2.getDefaultColor() : i4;
        final float alpha = this.layout.getAlpha();
        ValueAnimator valueAnimatorOfInt = ValueAnimator.ofInt(this.clipLayer.getAlpha(), i2);
        valueAnimatorOfInt.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: miui.systemui.devicecontrols.ui.l
            @Override // android.animation.ValueAnimator.AnimatorUpdateListener
            public final void onAnimationUpdate(ValueAnimator valueAnimator) {
                ControlViewHolder.startBackgroundAnimation$lambda$12$lambda$11(defaultColor, i3, defaultColor2, i4, alpha, this, drawable, valueAnimator);
            }
        });
        valueAnimatorOfInt.addListener(new AnimatorListenerAdapter() { // from class: miui.systemui.devicecontrols.ui.ControlViewHolder$startBackgroundAnimation$1$2
            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationEnd(Animator animator) {
                this.this$0.stateAnimator = null;
            }
        });
        valueAnimatorOfInt.setDuration(700L);
        valueAnimatorOfInt.setInterpolator(Interpolators.CONTROL_STATE);
        valueAnimatorOfInt.start();
        this.stateAnimator = valueAnimatorOfInt;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void startBackgroundAnimation$lambda$12$lambda$11(int i2, int i3, int i4, int i5, float f2, ControlViewHolder this$0, Drawable clipDrawable, ValueAnimator valueAnimator) {
        kotlin.jvm.internal.n.g(this$0, "this$0");
        kotlin.jvm.internal.n.g(clipDrawable, "$clipDrawable");
        Object animatedValue = valueAnimator.getAnimatedValue();
        kotlin.jvm.internal.n.e(animatedValue, "null cannot be cast to non-null type kotlin.Int");
        int iIntValue = ((Integer) animatedValue).intValue();
        ColorUtils colorUtils = ColorUtils.INSTANCE;
        this$0.applyBackgroundChange(clipDrawable, iIntValue, colorUtils.blendARGB(i2, i3, valueAnimator.getAnimatedFraction()), colorUtils.blendARGB(i4, i5, valueAnimator.getAnimatedFraction()), MiuiMathUtils.INSTANCE.lerp(f2, 1.0f, valueAnimator.getAnimatedFraction()));
    }

    private final void updateContentDescription() {
        this.layout.setContentDescription(((Object) this.title.getText()) + " " + ((Object) this.subtitle.getText()) + " " + ((Object) this.status.getText()));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void updateStatusRow$lambda$18$lambda$17(ControlViewHolder this$0, Drawable drawable) {
        kotlin.jvm.internal.n.g(this$0, "this$0");
        this$0.icon.setImageDrawable(drawable);
        this$0.icon.setImageTintList(null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void updateStatusRow$lambda$22$lambda$20$lambda$19(ControlViewHolder this_run, Drawable drawable) {
        kotlin.jvm.internal.n.g(this_run, "$this_run");
        this_run.icon.setImageDrawable(drawable);
        this_run.icon.setImageTintList(null);
    }

    public final void action(ControlAction action) {
        kotlin.jvm.internal.n.g(action, "action");
        this.lastAction = action;
        this.controlsController.action(getCws().getComponentName(), getCws().getCi(), action);
    }

    public final void actionResponse(int i2) {
        this.controlActionCoordinator.enableActionOnTouch(getCws().getCi().getControlId());
        boolean z2 = this.lastChallengeDialog != null;
        if (i2 == 0) {
            this.lastChallengeDialog = null;
            setErrorStatus();
            return;
        }
        if (i2 == 1) {
            this.lastChallengeDialog = null;
            return;
        }
        if (i2 == 2) {
            this.lastChallengeDialog = null;
            setErrorStatus();
            return;
        }
        if (i2 == 3) {
            Dialog dialogCreateConfirmationDialog = ChallengeDialogs.INSTANCE.createConfirmationDialog(this, this.onDialogCancel);
            this.lastChallengeDialog = dialogCreateConfirmationDialog;
            if (dialogCreateConfirmationDialog != null) {
                dialogCreateConfirmationDialog.show();
                return;
            }
            return;
        }
        if (i2 == 4) {
            Dialog dialogCreatePinDialog = ChallengeDialogs.INSTANCE.createPinDialog(this, false, z2, this.onDialogCancel);
            this.lastChallengeDialog = dialogCreatePinDialog;
            if (dialogCreatePinDialog != null) {
                dialogCreatePinDialog.show();
                return;
            }
            return;
        }
        if (i2 != 5) {
            return;
        }
        Dialog dialogCreatePinDialog2 = ChallengeDialogs.INSTANCE.createPinDialog(this, true, z2, this.onDialogCancel);
        this.lastChallengeDialog = dialogCreatePinDialog2;
        if (dialogCreatePinDialog2 != null) {
            dialogCreatePinDialog2.show();
        }
    }

    public final void applyRenderInfo$miui_devicecontrols_release(boolean z2, int i2, boolean z3) {
        this.enabledForState = z2;
        RenderInfo renderInfoLookup = RenderInfo.Companion.lookup(this.context, getCws().getComponentName(), (getControlStatus() == 1 || getControlStatus() == 0) ? getDeviceType() : -1000, i2);
        ColorStateList colorStateList = this.context.getResources().getColorStateList(renderInfoLookup.getForeground(), this.context.getTheme());
        CharSequence charSequence = this.nextStatusText;
        Control control = getCws().getControl();
        if (kotlin.jvm.internal.n.c(charSequence, this.status.getText())) {
            z3 = false;
        }
        animateStatusChange(false, new ControlViewHolder$applyRenderInfo$1(this, z2, charSequence, renderInfoLookup, colorStateList, control));
        kotlin.jvm.internal.n.d(colorStateList);
        setViewColor(colorStateList);
        animateBackgroundChange(z3, z2, renderInfoLookup.getEnabledBackground());
    }

    public final Behavior bindBehavior(Behavior behavior, InterfaceC0324c clazz, int i2) {
        kotlin.jvm.internal.n.g(clazz, "clazz");
        if (behavior == null || !kotlin.jvm.internal.n.c(kotlin.jvm.internal.z.b(behavior.getClass()), clazz)) {
            behavior = (Behavior) U0.a.a(clazz).newInstance();
            behavior.initialize(this);
            this.layout.setAccessibilityDelegate(null);
        }
        behavior.bind(getCws(), i2);
        kotlin.jvm.internal.n.f(behavior, "also(...)");
        return behavior;
    }

    @Override // miui.systemui.devicecontrols.management.BaseHolder
    public void bindData(ElementWrapper cws) {
        kotlin.jvm.internal.n.g(cws, "cws");
        super.bindData(cws);
        ControlWithState controlWithState = (ControlWithState) cws;
        if (this.userInteractionInProgress) {
            return;
        }
        setCws(controlWithState);
        if (getControlStatus() == 0 || getControlStatus() == 2) {
            this.title.setText(controlWithState.getCi().getControlTitle());
            this.subtitle.setText(controlWithState.getCi().getControlSubtitle());
        } else {
            Control control = controlWithState.getControl();
            if (control != null) {
                this.title.setText(control.getTitle());
                this.subtitle.setText(control.getSubtitle());
            }
        }
        if (controlWithState.getControl() != null) {
            this.layout.setClickable(true);
            this.layout.setHapticFeedbackEnabled(true ^ HapticFeedback.Companion.getIS_SUPPORT_LINEAR_MOTOR_VIBRATE());
            this.layout.setOnLongClickListener(new View.OnLongClickListener() { // from class: miui.systemui.devicecontrols.ui.k
                @Override // android.view.View.OnLongClickListener
                public final boolean onLongClick(View view) {
                    return ControlViewHolder.bindData$lambda$5$lambda$4(this.f5681a, view);
                }
            });
            this.controlActionCoordinator.runPendingAction(controlWithState.getCi().getControlId());
        }
        this.isLoading = false;
        Behavior behavior = this.behavior;
        Companion companion = Companion;
        int controlStatus = getControlStatus();
        ControlTemplate controlTemplate = getControlTemplate();
        int deviceType = getDeviceType();
        String packageName = controlWithState.getComponentName().getPackageName();
        kotlin.jvm.internal.n.f(packageName, "getPackageName(...)");
        this.behavior = bindBehavior$default(this, behavior, companion.findBehaviorClass(controlStatus, controlTemplate, deviceType, packageName), 0, 4, null);
        updateContentDescription();
        if (ControlsUtils.INSTANCE.checkSenseType(controlWithState.getCi().getControlId())) {
            this.ripple.setBackground(this.context.getDrawable(miui.systemui.devicecontrols.R.drawable.sense_item_ripple));
        }
    }

    public final void dismiss() {
        Dialog dialog = this.lastChallengeDialog;
        if (dialog != null) {
            dialog.dismiss();
        }
        this.lastChallengeDialog = null;
        Dialog dialog2 = this.visibleDialog;
        if (dialog2 != null) {
            dialog2.dismiss();
        }
        this.visibleDialog = null;
    }

    public final Behavior getBehavior() {
        return this.behavior;
    }

    public final DelayableExecutor getBgExecutor() {
        return this.bgExecutor;
    }

    public final ClipDrawable getClipLayer() {
        return this.clipLayer;
    }

    public final Context getContext() {
        return this.context;
    }

    public final ControlActionCoordinator getControlActionCoordinator() {
        return this.controlActionCoordinator;
    }

    public final int getControlStatus() {
        Control control = getCws().getControl();
        if (control != null) {
            return control.getStatus();
        }
        return 0;
    }

    public final ControlTemplate getControlTemplate() {
        Control control = getCws().getControl();
        ControlTemplate controlTemplate = control != null ? control.getControlTemplate() : null;
        return controlTemplate == null ? ControlTemplateExpose.INSTANCE.getNO_TEMPLATE() : controlTemplate;
    }

    public final ControlsController getControlsController() {
        return this.controlsController;
    }

    public final ControlWithState getCws() {
        ControlWithState controlWithState = this.cws;
        if (controlWithState != null) {
            return controlWithState;
        }
        kotlin.jvm.internal.n.w("cws");
        return null;
    }

    public final int getDeviceType() {
        Control control = getCws().getControl();
        return control != null ? control.getDeviceType() : getCws().getCi().getDeviceType();
    }

    public final View getDivider() {
        return this.divider;
    }

    public final boolean getEnabledForState$miui_devicecontrols_release() {
        return this.enabledForState;
    }

    public final ImageView getIcon() {
        return this.icon;
    }

    public final ControlAction getLastAction() {
        return this.lastAction;
    }

    public final ViewGroup getLayout() {
        return this.layout;
    }

    public final View getRipple() {
        return this.ripple;
    }

    public final TextView getStatus() {
        return this.status;
    }

    public final LinearLayout getStatusLine() {
        return this.statusLine;
    }

    public final CharSequence getStatusText() {
        return this.status.getText();
    }

    public final TextView getSubtitle() {
        return this.subtitle;
    }

    public final TextView getTitle() {
        return this.title;
    }

    public final DelayableExecutor getUiExecutor() {
        return this.uiExecutor;
    }

    public final boolean getUserInteractionInProgress() {
        return this.userInteractionInProgress;
    }

    public final Dialog getVisibleDialog() {
        return this.visibleDialog;
    }

    public final boolean isLoading() {
        return this.isLoading;
    }

    @Override // miui.systemui.controlcenter.ConfigUtils.OnConfigChangeListener
    public void onConfigurationChanged(int i2) {
        ConfigUtils configUtils = ConfigUtils.INSTANCE;
        boolean zDimensionsChanged = configUtils.dimensionsChanged(i2);
        if (configUtils.textAppearanceChanged(i2)) {
            this.title.setTextAppearance(miui.systemui.devicecontrols.R.style.TextAppearance_Control_Device_Title);
            this.subtitle.setTextAppearance(miui.systemui.devicecontrols.R.style.TextAppearance_Control_Device_Subtitle);
            this.status.setTextAppearance(miui.systemui.devicecontrols.R.style.TextAppearance_Control_Device_Status);
            TextView textView = this.title;
            int dimensionPixelSize = this.context.getResources().getDimensionPixelSize(miui.systemui.devicecontrols.R.dimen.device_controls_item_title_min_text_size);
            int dimensionPixelSize2 = this.context.getResources().getDimensionPixelSize(miui.systemui.devicecontrols.R.dimen.device_controls_item_title_max_text_size);
            Resources resources = this.context.getResources();
            int i3 = miui.systemui.devicecontrols.R.dimen.device_controls_item_auto_size_step_granularity;
            textView.setAutoSizeTextTypeUniformWithConfiguration(dimensionPixelSize, dimensionPixelSize2, resources.getDimensionPixelSize(i3), 0);
            TextView textView2 = this.subtitle;
            Resources resources2 = this.context.getResources();
            int i4 = miui.systemui.devicecontrols.R.dimen.device_controls_item_subtitle_min_text_size;
            int dimensionPixelSize3 = resources2.getDimensionPixelSize(i4);
            Resources resources3 = this.context.getResources();
            int i5 = miui.systemui.devicecontrols.R.dimen.device_controls_item_subtitle_max_text_size;
            textView2.setAutoSizeTextTypeUniformWithConfiguration(dimensionPixelSize3, resources3.getDimensionPixelSize(i5), this.context.getResources().getDimensionPixelSize(i3), 0);
            this.status.setAutoSizeTextTypeUniformWithConfiguration(this.context.getResources().getDimensionPixelSize(i4), this.context.getResources().getDimensionPixelSize(i5), this.context.getResources().getDimensionPixelSize(i3), 0);
        }
        if (zDimensionsChanged) {
            Resources resources4 = this.itemView.getResources();
            float dimension = resources4.getDimension(miui.systemui.devicecontrols.R.dimen.control_corner_radius);
            Drawable background = this.layout.getBackground();
            kotlin.jvm.internal.n.e(background, "null cannot be cast to non-null type android.graphics.drawable.LayerDrawable");
            Drawable drawableFindDrawableByLayerId = ((LayerDrawable) background).findDrawableByLayerId(miui.systemui.devicecontrols.R.id.background);
            GradientDrawable gradientDrawable = drawableFindDrawableByLayerId instanceof GradientDrawable ? (GradientDrawable) drawableFindDrawableByLayerId : null;
            if (gradientDrawable != null) {
                gradientDrawable.setCornerRadius(dimension);
            }
            Drawable drawable = this.clipLayer.getDrawable();
            GradientDrawable gradientDrawable2 = drawable instanceof GradientDrawable ? (GradientDrawable) drawable : null;
            if (gradientDrawable2 != null) {
                gradientDrawable2.setCornerRadius(dimension);
            }
            View view = this.itemView;
            CommonUtils commonUtils = CommonUtils.INSTANCE;
            kotlin.jvm.internal.n.d(view);
            CommonUtils.setLayoutSize$default(commonUtils, view, resources4.getDimensionPixelSize(miui.systemui.devicecontrols.R.dimen.device_controls_item_width), resources4.getDimensionPixelSize(miui.systemui.devicecontrols.R.dimen.device_controls_item_height), false, 4, null);
            view.setPaddingRelative(0, resources4.getDimensionPixelSize(miui.systemui.devicecontrols.R.dimen.device_controls_item_margin_top), 0, 0);
            int dimensionPixelSize4 = resources4.getDimensionPixelSize(miui.systemui.devicecontrols.R.dimen.control_icon_size);
            CommonUtils.setLayoutSize$default(commonUtils, this.icon, dimensionPixelSize4, dimensionPixelSize4, false, 4, null);
            TextView textView3 = this.title;
            CommonUtils.setMarginTop$default(commonUtils, textView3, resources4.getDimensionPixelSize(miui.systemui.devicecontrols.R.dimen.device_controls_item_title_margin_top), false, 2, null);
            CommonUtils.setMarginHorizontal$default(commonUtils, textView3, resources4.getDimensionPixelSize(miui.systemui.devicecontrols.R.dimen.device_controls_item_title_margin_horizontal), false, 2, null);
            CommonUtils.setLayoutHeight$default(commonUtils, this.title, resources4.getDimensionPixelSize(miui.systemui.devicecontrols.R.dimen.device_controls_item_title_height), false, 2, null);
            CommonUtils.setLayoutHeight$default(commonUtils, this.statusLine, resources4.getDimensionPixelSize(miui.systemui.devicecontrols.R.dimen.device_controls_item_subtitle_height), false, 2, null);
            CommonUtils.setMarginTop$default(commonUtils, this.statusLine, resources4.getDimensionPixelSize(miui.systemui.devicecontrols.R.dimen.device_controls_item_subtitle_margin_top), false, 2, null);
            CommonUtils.setLayoutSize$default(commonUtils, this.divider, resources4.getDimensionPixelSize(miui.systemui.devicecontrols.R.dimen.device_controls_item_border_width), resources4.getDimensionPixelSize(miui.systemui.devicecontrols.R.dimen.device_controls_item_border_height), false, 4, null);
        }
    }

    public final void setBehavior(Behavior behavior) {
        this.behavior = behavior;
    }

    public final void setCws(ControlWithState controlWithState) {
        kotlin.jvm.internal.n.g(controlWithState, "<set-?>");
        this.cws = controlWithState;
    }

    public final void setEnabledForState$miui_devicecontrols_release(boolean z2) {
        this.enabledForState = z2;
    }

    public final void setErrorStatus() {
        animateStatusChange(true, new AnonymousClass1(this.context.getResources().getString(miui.systemui.devicecontrols.R.string.controls_error_failed)));
    }

    public final void setLastAction(ControlAction controlAction) {
        this.lastAction = controlAction;
    }

    public final void setLoading(boolean z2) {
        this.isLoading = z2;
    }

    public final void setStatusText(CharSequence text, boolean z2) {
        kotlin.jvm.internal.n.g(text, "text");
        if (z2) {
            this.status.setAlpha(1.0f);
            this.status.setText(text);
            this.divider.setVisibility(TextUtils.isEmpty(this.status.getText()) ? 8 : 0);
            updateContentDescription();
        }
        this.nextStatusText = text;
    }

    public final void setStatusTextSize(float f2) {
        this.status.setTextSize(0, f2);
    }

    public final void setUserInteractionInProgress(boolean z2) {
        this.userInteractionInProgress = z2;
    }

    public final void setVisibleDialog(Dialog dialog) {
        this.visibleDialog = dialog;
    }

    @VisibleForTesting
    public final void updateStatusRow$miui_devicecontrols_release(boolean z2, CharSequence text, Drawable drawable, ColorStateList color, Control control) {
        H0.s sVar;
        Icon customIcon;
        kotlin.jvm.internal.n.g(text, "text");
        kotlin.jvm.internal.n.g(color, "color");
        setEnabled(z2);
        this.enabledForState = z2;
        this.status.setText(text);
        this.divider.setVisibility(TextUtils.isEmpty(this.status.getText()) ? 8 : 0);
        updateContentDescription();
        this.status.setTextColor(color);
        this.status.setAlpha(0.5f);
        H0.s sVar2 = null;
        if (control == null || (customIcon = control.getCustomIcon()) == null) {
            sVar = null;
        } else {
            customIcon.loadDrawableAsync(this.context, new Icon.OnDrawableLoadedListener() { // from class: miui.systemui.devicecontrols.ui.m
                @Override // android.graphics.drawable.Icon.OnDrawableLoadedListener
                public final void onDrawableLoaded(Drawable drawable2) {
                    ControlViewHolder.updateStatusRow$lambda$18$lambda$17(this.f5689a, drawable2);
                }
            }, ThreadUtils.getUiThreadHandler());
            sVar = H0.s.f314a;
        }
        if (sVar == null) {
            Icon iconRetrieve = this.iconCache.retrieve(getCws().getComponentName(), getCws().getCi().getControlId());
            if (iconRetrieve != null) {
                iconRetrieve.loadDrawableAsync(this.context, new Icon.OnDrawableLoadedListener() { // from class: miui.systemui.devicecontrols.ui.n
                    @Override // android.graphics.drawable.Icon.OnDrawableLoadedListener
                    public final void onDrawableLoaded(Drawable drawable2) {
                        ControlViewHolder.updateStatusRow$lambda$22$lambda$20$lambda$19(this.f5690a, drawable2);
                    }
                }, ThreadUtils.getUiThreadHandler());
                sVar2 = H0.s.f314a;
            }
            if (sVar2 == null) {
                if (drawable instanceof StateListDrawable) {
                    if (this.icon.getDrawable() == null || !(this.icon.getDrawable() instanceof StateListDrawable)) {
                        this.icon.setImageDrawable(drawable);
                    }
                    this.icon.setImageState(z2 ? ATTR_ENABLED : ATTR_DISABLED, true);
                } else {
                    this.icon.setImageDrawable(drawable);
                }
                if (getDeviceType() != 52) {
                    this.icon.setImageTintList(color);
                }
            }
        }
    }

    public final boolean usePanel() {
        return FORCE_PANEL_DEVICES.contains(Integer.valueOf(getDeviceType())) || kotlin.jvm.internal.n.c(getControlTemplate(), ControlTemplateExpose.INSTANCE.getNO_TEMPLATE());
    }
}
