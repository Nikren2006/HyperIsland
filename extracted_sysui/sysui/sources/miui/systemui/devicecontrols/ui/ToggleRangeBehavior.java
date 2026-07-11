package miui.systemui.devicecontrols.ui;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.drawable.ClipDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.os.Bundle;
import android.service.controls.Control;
import android.service.controls.templates.ControlTemplate;
import android.service.controls.templates.RangeTemplate;
import android.service.controls.templates.TemperatureControlTemplate;
import android.service.controls.templates.ToggleRangeTemplate;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;
import java.util.Arrays;
import java.util.IllegalFormatException;
import kotlin.jvm.internal.DefaultConstructorMarker;
import miui.systemui.Interpolators;
import miui.systemui.devicecontrols.R;
import miui.systemui.util.MiuiMathUtils;
import systemui.plugin.eventtracking.events.MiPlayEventsKt;

/* JADX INFO: loaded from: classes3.dex */
public final class ToggleRangeBehavior implements Behavior {
    public static final Companion Companion = new Companion(null);
    private static final String DEFAULT_FORMAT = "%.1f";
    public Drawable clipLayer;
    private int colorOffset;
    public Context context;
    public Control control;
    public ControlViewHolder cvh;
    private boolean isChecked;
    private boolean isToggleable;
    private ValueAnimator rangeAnimator;
    public RangeTemplate rangeTemplate;
    public String templateId;
    private CharSequence currentStatusText = "";
    private String currentRangeValue = "";

    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }

    public final class ToggleRangeGestureListener extends GestureDetector.SimpleOnGestureListener {
        private boolean isDragging;
        final /* synthetic */ ToggleRangeBehavior this$0;

        /* JADX INFO: renamed from: v, reason: collision with root package name */
        private final View f5663v;

        public ToggleRangeGestureListener(ToggleRangeBehavior toggleRangeBehavior, View v2) {
            kotlin.jvm.internal.n.g(v2, "v");
            this.this$0 = toggleRangeBehavior;
            this.f5663v = v2;
        }

        public final View getV() {
            return this.f5663v;
        }

        public final boolean isDragging() {
            return this.isDragging;
        }

        @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnGestureListener
        public boolean onDown(MotionEvent e2) {
            kotlin.jvm.internal.n.g(e2, "e");
            return true;
        }

        @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnGestureListener
        public void onLongPress(MotionEvent e2) {
            kotlin.jvm.internal.n.g(e2, "e");
            if (this.isDragging) {
                return;
            }
            this.this$0.getCvh().getControlActionCoordinator().longPress(this.this$0.getCvh());
        }

        @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnGestureListener
        public boolean onScroll(MotionEvent e12, MotionEvent e2, float f2, float f3) {
            kotlin.jvm.internal.n.g(e12, "e1");
            kotlin.jvm.internal.n.g(e2, "e2");
            if (!this.isDragging) {
                this.f5663v.getParent().requestDisallowInterceptTouchEvent(true);
                this.this$0.beginUpdateRange();
                this.isDragging = true;
            }
            int width = (int) (10000 * ((-f2) / this.f5663v.getWidth()));
            ToggleRangeBehavior toggleRangeBehavior = this.this$0;
            toggleRangeBehavior.updateRange(toggleRangeBehavior.getClipLayer().getLevel() + width, true, true);
            return true;
        }

        @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnGestureListener
        public boolean onSingleTapUp(MotionEvent e2) {
            kotlin.jvm.internal.n.g(e2, "e");
            if (!this.this$0.isToggleable()) {
                return false;
            }
            this.this$0.getCvh().getControlActionCoordinator().toggle(this.this$0.getCvh(), this.this$0.getTemplateId(), this.this$0.isChecked());
            return true;
        }

        public final void setDragging(boolean z2) {
            this.isDragging = z2;
        }
    }

    private final String format(String str, String str2, float f2) {
        try {
            kotlin.jvm.internal.C c2 = kotlin.jvm.internal.C.f5034a;
            String str3 = String.format(str, Arrays.copyOf(new Object[]{Float.valueOf(findNearestStep(f2))}, 1));
            kotlin.jvm.internal.n.f(str3, "format(...)");
            return str3;
        } catch (IllegalFormatException e2) {
            Log.w("ControlsUiController", "Illegal format in range template", e2);
            return kotlin.jvm.internal.n.c(str2, "") ? "" : format(str2, "", f2);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean initialize$lambda$0(GestureDetector gestureDetector, ToggleRangeGestureListener gestureListener, ToggleRangeBehavior this$0, View v2, MotionEvent e2) {
        kotlin.jvm.internal.n.g(gestureDetector, "$gestureDetector");
        kotlin.jvm.internal.n.g(gestureListener, "$gestureListener");
        kotlin.jvm.internal.n.g(this$0, "this$0");
        kotlin.jvm.internal.n.g(v2, "v");
        kotlin.jvm.internal.n.g(e2, "e");
        if (!gestureDetector.onTouchEvent(e2) && e2.getAction() == 1 && gestureListener.isDragging()) {
            v2.getParent().requestDisallowInterceptTouchEvent(false);
            gestureListener.setDragging(false);
            this$0.endUpdateRange();
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final float levelToRangeValue(int i2) {
        return MiuiMathUtils.INSTANCE.constrainedMap(getRangeTemplate().getMinValue(), getRangeTemplate().getMaxValue(), 0.0f, 10000.0f, i2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final int rangeToLevelValue(float f2) {
        return (int) MiuiMathUtils.INSTANCE.constrainedMap(0.0f, 10000.0f, getRangeTemplate().getMinValue(), getRangeTemplate().getMaxValue(), f2);
    }

    private final void setup(ToggleRangeTemplate toggleRangeTemplate) {
        RangeTemplate range = toggleRangeTemplate.getRange();
        kotlin.jvm.internal.n.f(range, "getRange(...)");
        setRangeTemplate(range);
        this.isToggleable = true;
        this.isChecked = toggleRangeTemplate.isChecked();
    }

    private final boolean setupTemplate(ControlTemplate controlTemplate) {
        if (controlTemplate instanceof ToggleRangeTemplate) {
            setup((ToggleRangeTemplate) controlTemplate);
            return true;
        }
        if (controlTemplate instanceof RangeTemplate) {
            setup((RangeTemplate) controlTemplate);
            return true;
        }
        if (controlTemplate instanceof TemperatureControlTemplate) {
            ControlTemplate template = ((TemperatureControlTemplate) controlTemplate).getTemplate();
            kotlin.jvm.internal.n.f(template, "getTemplate(...)");
            return setupTemplate(template);
        }
        Log.e("ControlsUiController", "Unsupported template type: " + controlTemplate);
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void updateRange$lambda$2$lambda$1(ToggleRangeBehavior this$0, ValueAnimator valueAnimator) {
        kotlin.jvm.internal.n.g(this$0, "this$0");
        ClipDrawable clipLayer = this$0.getCvh().getClipLayer();
        Object animatedValue = valueAnimator.getAnimatedValue();
        kotlin.jvm.internal.n.e(animatedValue, "null cannot be cast to non-null type kotlin.Int");
        clipLayer.setLevel(((Integer) animatedValue).intValue());
    }

    public final void beginUpdateRange() {
        getCvh().setUserInteractionInProgress(true);
        getCvh().setStatusTextSize(getContext().getResources().getDimensionPixelSize(R.dimen.control_status_expanded));
    }

    @Override // miui.systemui.devicecontrols.ui.Behavior
    public void bind(ControlWithState cws, int i2) {
        kotlin.jvm.internal.n.g(cws, "cws");
        Control control = cws.getControl();
        kotlin.jvm.internal.n.d(control);
        setControl(control);
        this.colorOffset = i2;
        CharSequence statusText = getControl().getStatusText();
        kotlin.jvm.internal.n.f(statusText, "getStatusText(...)");
        this.currentStatusText = statusText;
        getCvh().getLayout().setOnLongClickListener(null);
        Drawable background = getCvh().getLayout().getBackground();
        kotlin.jvm.internal.n.e(background, "null cannot be cast to non-null type android.graphics.drawable.LayerDrawable");
        Drawable drawableFindDrawableByLayerId = ((LayerDrawable) background).findDrawableByLayerId(R.id.clip_layer);
        kotlin.jvm.internal.n.f(drawableFindDrawableByLayerId, "findDrawableByLayerId(...)");
        setClipLayer(drawableFindDrawableByLayerId);
        ControlTemplate controlTemplate = getControl().getControlTemplate();
        kotlin.jvm.internal.n.d(controlTemplate);
        if (setupTemplate(controlTemplate)) {
            String templateId = controlTemplate.getTemplateId();
            kotlin.jvm.internal.n.f(templateId, "getTemplateId(...)");
            setTemplateId(templateId);
            updateRange(rangeToLevelValue(getRangeTemplate().getCurrentValue()), this.isChecked, false);
            ControlViewHolder.applyRenderInfo$miui_devicecontrols_release$default(getCvh(), this.isChecked, i2, false, 4, null);
            getCvh().getLayout().setAccessibilityDelegate(new View.AccessibilityDelegate() { // from class: miui.systemui.devicecontrols.ui.ToggleRangeBehavior.bind.1
                @Override // android.view.View.AccessibilityDelegate
                public void onInitializeAccessibilityNodeInfo(View host, AccessibilityNodeInfo info) {
                    kotlin.jvm.internal.n.g(host, "host");
                    kotlin.jvm.internal.n.g(info, "info");
                    super.onInitializeAccessibilityNodeInfo(host, info);
                    float fLevelToRangeValue = ToggleRangeBehavior.this.levelToRangeValue(0);
                    ToggleRangeBehavior toggleRangeBehavior = ToggleRangeBehavior.this;
                    float fLevelToRangeValue2 = toggleRangeBehavior.levelToRangeValue(toggleRangeBehavior.getClipLayer().getLevel());
                    float fLevelToRangeValue3 = ToggleRangeBehavior.this.levelToRangeValue(10000);
                    double stepValue = ToggleRangeBehavior.this.getRangeTemplate().getStepValue();
                    int i3 = (stepValue == Math.floor(stepValue) ? 1 : 0) ^ 1;
                    if (ToggleRangeBehavior.this.isChecked()) {
                        info.setRangeInfo(AccessibilityNodeInfo.RangeInfo.obtain(i3, fLevelToRangeValue, fLevelToRangeValue3, fLevelToRangeValue2));
                    }
                    info.addAction(AccessibilityNodeInfo.AccessibilityAction.ACTION_SET_PROGRESS);
                }

                @Override // android.view.View.AccessibilityDelegate
                public boolean onRequestSendAccessibilityEvent(ViewGroup host, View child, AccessibilityEvent event) {
                    kotlin.jvm.internal.n.g(host, "host");
                    kotlin.jvm.internal.n.g(child, "child");
                    kotlin.jvm.internal.n.g(event, "event");
                    return true;
                }

                @Override // android.view.View.AccessibilityDelegate
                public boolean performAccessibilityAction(View host, int i3, Bundle bundle) {
                    kotlin.jvm.internal.n.g(host, "host");
                    if (i3 == 16) {
                        if (ToggleRangeBehavior.this.isToggleable()) {
                            ToggleRangeBehavior.this.getCvh().getControlActionCoordinator().toggle(ToggleRangeBehavior.this.getCvh(), ToggleRangeBehavior.this.getTemplateId(), ToggleRangeBehavior.this.isChecked());
                            return true;
                        }
                    } else {
                        if (i3 == 32) {
                            ToggleRangeBehavior.this.getCvh().getControlActionCoordinator().longPress(ToggleRangeBehavior.this.getCvh());
                            return true;
                        }
                        if (i3 == AccessibilityNodeInfo.AccessibilityAction.ACTION_SET_PROGRESS.getId() && bundle != null && bundle.containsKey(AccessibilityNodeInfoCompat.ACTION_ARGUMENT_PROGRESS_VALUE)) {
                            int iRangeToLevelValue = ToggleRangeBehavior.this.rangeToLevelValue(bundle.getFloat(AccessibilityNodeInfoCompat.ACTION_ARGUMENT_PROGRESS_VALUE));
                            ToggleRangeBehavior toggleRangeBehavior = ToggleRangeBehavior.this;
                            toggleRangeBehavior.updateRange(iRangeToLevelValue, toggleRangeBehavior.isChecked(), true);
                            ToggleRangeBehavior.this.endUpdateRange();
                            return true;
                        }
                    }
                    return super.performAccessibilityAction(host, i3, bundle);
                }
            });
        }
    }

    public final void endUpdateRange() {
        getCvh().setStatusTextSize(getContext().getResources().getDimensionPixelSize(R.dimen.device_controls_status_normal));
        ControlViewHolder cvh = getCvh();
        CharSequence charSequence = this.currentStatusText;
        cvh.setStatusText(((Object) charSequence) + " " + this.currentRangeValue, true);
        ControlActionCoordinator controlActionCoordinator = getCvh().getControlActionCoordinator();
        ControlViewHolder cvh2 = getCvh();
        String templateId = getRangeTemplate().getTemplateId();
        kotlin.jvm.internal.n.f(templateId, "getTemplateId(...)");
        controlActionCoordinator.setValue(cvh2, templateId, findNearestStep(levelToRangeValue(getClipLayer().getLevel())));
        getCvh().setUserInteractionInProgress(false);
    }

    public final float findNearestStep(float f2) {
        float minValue = getRangeTemplate().getMinValue();
        float f3 = Float.MAX_VALUE;
        while (minValue <= getRangeTemplate().getMaxValue()) {
            float fAbs = Math.abs(f2 - minValue);
            if (fAbs >= f3) {
                return minValue - getRangeTemplate().getStepValue();
            }
            minValue += getRangeTemplate().getStepValue();
            f3 = fAbs;
        }
        return getRangeTemplate().getMaxValue();
    }

    public final Drawable getClipLayer() {
        Drawable drawable = this.clipLayer;
        if (drawable != null) {
            return drawable;
        }
        kotlin.jvm.internal.n.w("clipLayer");
        return null;
    }

    public final int getColorOffset() {
        return this.colorOffset;
    }

    public final Context getContext() {
        Context context = this.context;
        if (context != null) {
            return context;
        }
        kotlin.jvm.internal.n.w("context");
        return null;
    }

    public final Control getControl() {
        Control control = this.control;
        if (control != null) {
            return control;
        }
        kotlin.jvm.internal.n.w(MiPlayEventsKt.POSITION_TV_CONTROLLER);
        return null;
    }

    public final String getCurrentRangeValue() {
        return this.currentRangeValue;
    }

    public final CharSequence getCurrentStatusText() {
        return this.currentStatusText;
    }

    public final ControlViewHolder getCvh() {
        ControlViewHolder controlViewHolder = this.cvh;
        if (controlViewHolder != null) {
            return controlViewHolder;
        }
        kotlin.jvm.internal.n.w("cvh");
        return null;
    }

    public final RangeTemplate getRangeTemplate() {
        RangeTemplate rangeTemplate = this.rangeTemplate;
        if (rangeTemplate != null) {
            return rangeTemplate;
        }
        kotlin.jvm.internal.n.w("rangeTemplate");
        return null;
    }

    public final String getTemplateId() {
        String str = this.templateId;
        if (str != null) {
            return str;
        }
        kotlin.jvm.internal.n.w("templateId");
        return null;
    }

    @Override // miui.systemui.devicecontrols.ui.Behavior
    public void initialize(ControlViewHolder cvh) {
        kotlin.jvm.internal.n.g(cvh, "cvh");
        setCvh(cvh);
        setContext(cvh.getContext());
        final ToggleRangeGestureListener toggleRangeGestureListener = new ToggleRangeGestureListener(this, cvh.getLayout());
        final GestureDetector gestureDetector = new GestureDetector(getContext(), toggleRangeGestureListener);
        cvh.getLayout().setOnTouchListener(new View.OnTouchListener() { // from class: miui.systemui.devicecontrols.ui.L
            @Override // android.view.View.OnTouchListener
            public final boolean onTouch(View view, MotionEvent motionEvent) {
                return ToggleRangeBehavior.initialize$lambda$0(gestureDetector, toggleRangeGestureListener, this, view, motionEvent);
            }
        });
    }

    public final boolean isChecked() {
        return this.isChecked;
    }

    public final boolean isToggleable() {
        return this.isToggleable;
    }

    public final void setChecked(boolean z2) {
        this.isChecked = z2;
    }

    public final void setClipLayer(Drawable drawable) {
        kotlin.jvm.internal.n.g(drawable, "<set-?>");
        this.clipLayer = drawable;
    }

    public final void setColorOffset(int i2) {
        this.colorOffset = i2;
    }

    public final void setContext(Context context) {
        kotlin.jvm.internal.n.g(context, "<set-?>");
        this.context = context;
    }

    public final void setControl(Control control) {
        kotlin.jvm.internal.n.g(control, "<set-?>");
        this.control = control;
    }

    public final void setCurrentRangeValue(String str) {
        kotlin.jvm.internal.n.g(str, "<set-?>");
        this.currentRangeValue = str;
    }

    public final void setCurrentStatusText(CharSequence charSequence) {
        kotlin.jvm.internal.n.g(charSequence, "<set-?>");
        this.currentStatusText = charSequence;
    }

    public final void setCvh(ControlViewHolder controlViewHolder) {
        kotlin.jvm.internal.n.g(controlViewHolder, "<set-?>");
        this.cvh = controlViewHolder;
    }

    public final void setRangeTemplate(RangeTemplate rangeTemplate) {
        kotlin.jvm.internal.n.g(rangeTemplate, "<set-?>");
        this.rangeTemplate = rangeTemplate;
    }

    public final void setTemplateId(String str) {
        kotlin.jvm.internal.n.g(str, "<set-?>");
        this.templateId = str;
    }

    public final void setToggleable(boolean z2) {
        this.isToggleable = z2;
    }

    public final void updateRange(int i2, boolean z2, boolean z3) {
        int iMax = Math.max(0, Math.min(10000, i2));
        if (getClipLayer().getLevel() == 0 && iMax > 0) {
            getCvh().applyRenderInfo$miui_devicecontrols_release(z2, this.colorOffset, false);
        }
        ValueAnimator valueAnimator = this.rangeAnimator;
        if (valueAnimator != null) {
            valueAnimator.cancel();
        }
        if (z3) {
            boolean z4 = iMax == 0 || iMax == 10000;
            if (getClipLayer().getLevel() != iMax) {
                getCvh().getControlActionCoordinator().drag(z4);
                getClipLayer().setLevel(iMax);
            }
        } else if (iMax != getClipLayer().getLevel()) {
            ValueAnimator valueAnimatorOfInt = ValueAnimator.ofInt(getCvh().getClipLayer().getLevel(), iMax);
            valueAnimatorOfInt.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: miui.systemui.devicecontrols.ui.M
                @Override // android.animation.ValueAnimator.AnimatorUpdateListener
                public final void onAnimationUpdate(ValueAnimator valueAnimator2) {
                    ToggleRangeBehavior.updateRange$lambda$2$lambda$1(this.f5660a, valueAnimator2);
                }
            });
            valueAnimatorOfInt.addListener(new AnimatorListenerAdapter() { // from class: miui.systemui.devicecontrols.ui.ToggleRangeBehavior$updateRange$1$2
                @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
                public void onAnimationEnd(Animator animator) {
                    this.this$0.rangeAnimator = null;
                }
            });
            valueAnimatorOfInt.setDuration(700L);
            valueAnimatorOfInt.setInterpolator(Interpolators.CONTROL_STATE);
            valueAnimatorOfInt.start();
            this.rangeAnimator = valueAnimatorOfInt;
        }
        if (!z2) {
            ControlViewHolder.setStatusText$default(getCvh(), this.currentStatusText, false, 2, null);
            return;
        }
        this.currentRangeValue = format(getRangeTemplate().getFormatString().toString(), DEFAULT_FORMAT, levelToRangeValue(iMax));
        if (z3) {
            getCvh().setStatusText(this.currentRangeValue, true);
            return;
        }
        ControlViewHolder cvh = getCvh();
        CharSequence charSequence = this.currentStatusText;
        ControlViewHolder.setStatusText$default(cvh, ((Object) charSequence) + " " + this.currentRangeValue, false, 2, null);
    }

    private final void setup(RangeTemplate rangeTemplate) {
        setRangeTemplate(rangeTemplate);
        this.isChecked = !(getRangeTemplate().getCurrentValue() == getRangeTemplate().getMinValue());
    }
}
