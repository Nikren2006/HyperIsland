package miui.systemui.controlcenter.panel.main.anim;

import android.annotation.SuppressLint;
import android.view.MotionEvent;
import android.view.View;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.n;
import miui.systemui.controlcenter.panel.main.MainPanelController;
import miui.systemui.controlcenter.panel.main.recyclerview.MainPanelItemViewHolder;
import miui.systemui.controlcenter.panel.main.recyclerview.ScaleItemViewHolder;
import miui.systemui.util.CommonUtils;
import miuix.animation.Folme;
import miuix.animation.IStateStyle;
import miuix.animation.base.AnimConfig;
import miuix.animation.property.FloatProperty;

/* JADX INFO: loaded from: classes.dex */
@SuppressLint({"ClickableViewAccessibility"})
public final class PressEffectAnimator implements MainPanelItemViewHolder.TouchAnimator {
    public static final Companion Companion = new Companion(null);
    private static final PressEffectAnimator$Companion$SCALE_ANIM$1 SCALE_ANIM = new FloatProperty<PressEffectAnimator>() { // from class: miui.systemui.controlcenter.panel.main.anim.PressEffectAnimator$Companion$SCALE_ANIM$1
        @Override // miuix.animation.property.FloatProperty
        public float getValue(PressEffectAnimator pressEffectAnimator) {
            if (pressEffectAnimator != null) {
                return pressEffectAnimator.getViewScale();
            }
            return 1.0f;
        }

        @Override // miuix.animation.property.FloatProperty
        public void setValue(PressEffectAnimator pressEffectAnimator, float f2) {
            if (pressEffectAnimator == null) {
                return;
            }
            pressEffectAnimator.setViewScale(f2);
        }
    };
    private IStateStyle _anim;
    private View view;
    private float viewScale;

    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public PressEffectAnimator() {
        this(null, 1, 0 == true ? 1 : 0);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean attachTouchTarget$lambda$2(PressEffectAnimator this$0, View view, MotionEvent motionEvent) {
        n.g(this$0, "this$0");
        Integer numValueOf = motionEvent != null ? Integer.valueOf(motionEvent.getActionMasked()) : null;
        if (numValueOf != null && numValueOf.intValue() == 0) {
            n.d(motionEvent);
            this$0.touchDown(motionEvent);
            return false;
        }
        if (numValueOf != null && numValueOf.intValue() == 1) {
            this$0.touchRelease();
            return false;
        }
        if (numValueOf == null || numValueOf.intValue() != 3) {
            return false;
        }
        this$0.touchCancel();
        return false;
    }

    private final IStateStyle getAnim() {
        IStateStyle iStateStyle = this._anim;
        if (iStateStyle != null) {
            return iStateStyle;
        }
        IStateStyle iStateStyleUseValue = Folme.useValue(this);
        this._anim = iStateStyleUseValue;
        return iStateStyleUseValue;
    }

    @Override // miui.systemui.controlcenter.panel.main.recyclerview.MainPanelItemViewHolder.TouchAnimator
    public void attachTouchTarget(View view) {
        n.g(view, "view");
        if (MainPanelController.Companion.getLowEndAnim()) {
            return;
        }
        this.view = view;
        view.setOnTouchListener(new View.OnTouchListener() { // from class: miui.systemui.controlcenter.panel.main.anim.a
            @Override // android.view.View.OnTouchListener
            public final boolean onTouch(View view2, MotionEvent motionEvent) {
                return PressEffectAnimator.attachTouchTarget$lambda$2(this.f5353a, view2, motionEvent);
            }
        });
    }

    @Override // miui.systemui.controlcenter.panel.main.recyclerview.MainPanelItemViewHolder.TouchAnimator
    public void detachTouchTarget() {
        if (MainPanelController.Companion.getLowEndAnim()) {
            return;
        }
        this._anim = null;
        this.view = null;
        Folme.clean(this);
    }

    public final float getViewScale() {
        return this.viewScale;
    }

    public final void setViewScale(float f2) {
        if (f2 == this.viewScale) {
            return;
        }
        this.viewScale = f2;
        View view = this.view;
        if (view != null) {
            CommonUtils.INSTANCE.setScaleXEx(view, f2);
        }
        View view2 = this.view;
        if (view2 != null) {
            CommonUtils.INSTANCE.setScaleYEx(view2, f2);
        }
    }

    @Override // miui.systemui.controlcenter.panel.main.recyclerview.MainPanelItemViewHolder.TouchAnimator
    public void touchDown(MotionEvent ev) {
        n.g(ev, "ev");
        getAnim().to(SCALE_ANIM, Float.valueOf(0.9f), new AnimConfig().setEase(ScaleItemViewHolder.Companion.getEASE_TOUCH_DOWN_SIZE()));
    }

    @Override // miui.systemui.controlcenter.panel.main.recyclerview.MainPanelItemViewHolder.TouchAnimator
    public void touchRelease() {
        getAnim().to(SCALE_ANIM, Float.valueOf(1.0f), new AnimConfig().setEase(ScaleItemViewHolder.Companion.getEASE_TOUCH_UP_SIZE()));
    }

    @Override // miui.systemui.controlcenter.panel.main.recyclerview.MainPanelItemViewHolder.TouchAnimator
    public void touchTrigger() {
        getAnim().to(SCALE_ANIM, Float.valueOf(1.0f), new AnimConfig().setEase(ScaleItemViewHolder.Companion.getEASE_TOUCH_UP_SIZE()));
    }

    public PressEffectAnimator(View view) {
        this.view = view;
        this.viewScale = 1.0f;
    }

    public /* synthetic */ PressEffectAnimator(View view, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this((i2 & 1) != 0 ? null : view);
    }
}
