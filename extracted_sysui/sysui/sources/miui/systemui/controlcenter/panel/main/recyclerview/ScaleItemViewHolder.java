package miui.systemui.controlcenter.panel.main.recyclerview;

import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.n;
import miui.systemui.controlcenter.panel.main.MainPanelFrameCallback;
import miui.systemui.controlcenter.panel.main.recyclerview.MainPanelItemViewHolder;
import miui.systemui.controlcenter.panel.secondary.ChangeFromView;
import miui.systemui.controlcenter.panel.secondary.ChangeToView;
import miuix.animation.FolmeEase;
import miuix.animation.IStateStyle;
import miuix.animation.base.AnimConfig;
import miuix.animation.property.FloatProperty;
import miuix.animation.utils.EaseManager;

/* JADX INFO: loaded from: classes.dex */
public abstract class ScaleItemViewHolder extends MainPanelItemViewHolder implements MainPanelItemViewHolder.TouchAnimator, ChangeFromView {
    public static final Companion Companion = new Companion(null);
    private static final EaseManager.EaseStyle EASE_TOUCH_DOWN_SIZE;
    private static final EaseManager.EaseStyle EASE_TOUCH_SCALE;
    private static final EaseManager.EaseStyle EASE_TOUCH_UP_SIZE;
    private static ScaleItemViewHolder$Companion$TOUCH_SCALE$1 TOUCH_SCALE;
    private final ViewGroup changeFrame;
    private ChangeToView changeToView;
    private boolean ignoreRelease;
    private float touchScale;

    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final EaseManager.EaseStyle getEASE_TOUCH_DOWN_SIZE() {
            return ScaleItemViewHolder.EASE_TOUCH_DOWN_SIZE;
        }

        public final EaseManager.EaseStyle getEASE_TOUCH_UP_SIZE() {
            return ScaleItemViewHolder.EASE_TOUCH_UP_SIZE;
        }

        private Companion() {
        }
    }

    /* JADX WARN: Type inference failed for: r2v3, types: [miui.systemui.controlcenter.panel.main.recyclerview.ScaleItemViewHolder$Companion$TOUCH_SCALE$1] */
    static {
        EaseManager.EaseStyle style = EaseManager.getStyle(-2, 0.95f, 0.35f);
        n.f(style, "getStyle(...)");
        EASE_TOUCH_SCALE = style;
        TOUCH_SCALE = new FloatProperty<ScaleItemViewHolder>() { // from class: miui.systemui.controlcenter.panel.main.recyclerview.ScaleItemViewHolder$Companion$TOUCH_SCALE$1
            @Override // miuix.animation.property.FloatProperty
            public float getValue(ScaleItemViewHolder scaleItemViewHolder) {
                if (scaleItemViewHolder != null) {
                    return scaleItemViewHolder.touchScale;
                }
                return 1.0f;
            }

            @Override // miuix.animation.property.FloatProperty
            public void setValue(ScaleItemViewHolder scaleItemViewHolder, float f2) {
                if (n.b(scaleItemViewHolder != null ? Float.valueOf(scaleItemViewHolder.touchScale) : null, f2)) {
                    return;
                }
                if (scaleItemViewHolder != null) {
                    scaleItemViewHolder.touchScale = f2;
                }
                if (scaleItemViewHolder != null) {
                    scaleItemViewHolder.scheduleUpdate();
                }
            }
        };
        EaseManager.EaseStyle easeStyleSpring = FolmeEase.spring(1.0f, 1.0f);
        n.f(easeStyleSpring, "spring(...)");
        EASE_TOUCH_DOWN_SIZE = easeStyleSpring;
        EaseManager.EaseStyle easeStyleSpring2 = FolmeEase.spring(0.95f, 0.35f);
        n.f(easeStyleSpring2, "spring(...)");
        EASE_TOUCH_UP_SIZE = easeStyleSpring2;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ScaleItemViewHolder(View itemView) {
        super(itemView);
        n.g(itemView, "itemView");
        this.touchScale = 1.0f;
        this.changeFrame = (ViewGroup) itemView;
    }

    @Override // miui.systemui.controlcenter.panel.secondary.ChangeFromView
    public void changeAction() {
        touchTrigger();
    }

    @Override // miui.systemui.controlcenter.panel.secondary.ChangeFromView
    public ViewGroup getChangeFrame() {
        return this.changeFrame;
    }

    @Override // miui.systemui.controlcenter.panel.secondary.ChangeFromView
    public ChangeToView getChangeToView() {
        return this.changeToView;
    }

    @Override // miui.systemui.controlcenter.panel.main.recyclerview.MainPanelItemViewHolder
    public float getScale() {
        return getHolderScale() * this.touchScale;
    }

    @Override // miui.systemui.controlcenter.panel.main.recyclerview.MainPanelItemViewHolder, miui.systemui.controlcenter.panel.main.recyclerview.ControlCenterViewHolder
    public void onFrameCallback() {
        super.onFrameCallback();
        ChangeToView changeToView = getChangeToView();
        if (changeToView != null) {
            changeToView.getChangeFrame().resetPivot();
            changeToView.getChangeFrame().setScaleX(this.itemView.getScaleX());
            changeToView.getChangeFrame().setScaleY(this.itemView.getScaleY());
        }
    }

    @Override // miui.systemui.controlcenter.panel.main.recyclerview.ControlCenterViewHolder
    public void onViewAttachedToWindow(MainPanelAdapter mainPanelAdapter, MainPanelFrameCallback frameCallback) {
        n.g(mainPanelAdapter, "mainPanelAdapter");
        n.g(frameCallback, "frameCallback");
        super.onViewAttachedToWindow(mainPanelAdapter, frameCallback);
    }

    @Override // miui.systemui.controlcenter.panel.secondary.ChangeFromView
    public void setChangeToView(ChangeToView changeToView) {
        this.changeToView = changeToView;
        if (changeToView != null) {
            onFrameCallback();
        }
    }

    @Override // miui.systemui.controlcenter.panel.main.recyclerview.MainPanelItemViewHolder.TouchAnimator
    public void touchDown(MotionEvent ev) {
        n.g(ev, "ev");
        MainPanelItemViewHolder.Companion.setTouchViewHolder(this);
        this.ignoreRelease = false;
        getAnim().to(TOUCH_SCALE, Float.valueOf(0.0f), new AnimConfig().setEase(EASE_TOUCH_DOWN_SIZE));
    }

    @Override // miui.systemui.controlcenter.panel.main.recyclerview.MainPanelItemViewHolder.TouchAnimator
    public void touchRelease() {
        if (this.ignoreRelease) {
            return;
        }
        getAnim().to(TOUCH_SCALE, Float.valueOf(1.0f), new AnimConfig().setEase(EASE_TOUCH_UP_SIZE));
    }

    @Override // miui.systemui.controlcenter.panel.main.recyclerview.MainPanelItemViewHolder.TouchAnimator
    public void touchReleaseNow() {
        if (this.ignoreRelease) {
            return;
        }
        IStateStyle anim = getAnim();
        anim.setEase(EASE_TOUCH_SCALE, TOUCH_SCALE);
        anim.setTo(TOUCH_SCALE, Float.valueOf(1.0f));
    }

    @Override // miui.systemui.controlcenter.panel.main.recyclerview.MainPanelItemViewHolder.TouchAnimator
    public void touchTrigger() {
        this.ignoreRelease = true;
        getAnim().to(TOUCH_SCALE, Float.valueOf(1.0f), new AnimConfig().setEase(EASE_TOUCH_UP_SIZE));
    }
}
