package miui.systemui.controlcenter.panel.main.recyclerview;

import android.annotation.SuppressLint;
import android.view.Choreographer;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.n;
import miui.systemui.controlcenter.panel.main.MainPanelFrameCallback;
import miui.systemui.controlcenter.panel.main.recyclerview.MainPanelItemViewHolder;
import miui.systemui.controlcenter.panel.secondary.ChangeFromView;
import miui.systemui.controlcenter.panel.secondary.ChangeToView;
import miui.systemui.controlcenter.panel.secondary.FakeChangeToView;
import miui.systemui.controlcenter.qs.tileview.QSTileItemIconView;
import miuix.animation.IStateStyle;
import miuix.animation.property.FloatProperty;
import miuix.animation.utils.EaseManager;

/* JADX INFO: loaded from: classes.dex */
@SuppressLint({"RtlHardcoded"})
public abstract class RotationItemViewHolder extends MainPanelItemViewHolder implements MainPanelItemViewHolder.TouchAnimator, ChangeFromView {
    public static final Companion Companion = new Companion(null);
    private static final EaseManager.EaseStyle EASE_ROTATION_CANCEL;
    private static final EaseManager.EaseStyle EASE_ROTATION_DOWN;
    private static final EaseManager.EaseStyle EASE_ROTATION_TRIGGER;
    private static final RotationItemViewHolder$Companion$ROTATION_X$1 ROTATION_X;
    private static final RotationItemViewHolder$Companion$ROTATION_Y$1 ROTATION_Y;
    private final ViewGroup changeFrame;
    private ChangeToView changeToView;
    private final Choreographer choreographer;
    private final Choreographer.FrameCallback frameCallback;
    private boolean ignoreRelease;
    private int pivot;
    private float rotationX;
    private float rotationY;
    private boolean updateScheduled;

    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }

    /* JADX WARN: Type inference failed for: r0v4, types: [miui.systemui.controlcenter.panel.main.recyclerview.RotationItemViewHolder$Companion$ROTATION_X$1] */
    /* JADX WARN: Type inference failed for: r0v5, types: [miui.systemui.controlcenter.panel.main.recyclerview.RotationItemViewHolder$Companion$ROTATION_Y$1] */
    static {
        EaseManager.EaseStyle style = EaseManager.getStyle(-2, 0.95f, 0.35f);
        n.f(style, "getStyle(...)");
        EASE_ROTATION_DOWN = style;
        EaseManager.EaseStyle style2 = EaseManager.getStyle(-2, 0.95f, 0.4f);
        n.f(style2, "getStyle(...)");
        EASE_ROTATION_CANCEL = style2;
        EaseManager.EaseStyle style3 = EaseManager.getStyle(-2, 0.9f, 0.3f);
        n.f(style3, "getStyle(...)");
        EASE_ROTATION_TRIGGER = style3;
        ROTATION_X = new FloatProperty<RotationItemViewHolder>() { // from class: miui.systemui.controlcenter.panel.main.recyclerview.RotationItemViewHolder$Companion$ROTATION_X$1
            @Override // miuix.animation.property.FloatProperty
            public float getValue(RotationItemViewHolder rotationItemViewHolder) {
                if (rotationItemViewHolder != null) {
                    return rotationItemViewHolder.rotationX;
                }
                return 0.0f;
            }

            @Override // miuix.animation.property.FloatProperty
            public void setValue(RotationItemViewHolder rotationItemViewHolder, float f2) {
                if (n.b(rotationItemViewHolder != null ? Float.valueOf(rotationItemViewHolder.rotationX) : null, f2)) {
                    return;
                }
                if (rotationItemViewHolder != null) {
                    rotationItemViewHolder.rotationX = f2;
                }
                if (rotationItemViewHolder != null) {
                    rotationItemViewHolder.innerScheduleUpdate();
                }
            }
        };
        ROTATION_Y = new FloatProperty<RotationItemViewHolder>() { // from class: miui.systemui.controlcenter.panel.main.recyclerview.RotationItemViewHolder$Companion$ROTATION_Y$1
            @Override // miuix.animation.property.FloatProperty
            public float getValue(RotationItemViewHolder rotationItemViewHolder) {
                if (rotationItemViewHolder != null) {
                    return rotationItemViewHolder.rotationY;
                }
                return 0.0f;
            }

            @Override // miuix.animation.property.FloatProperty
            public void setValue(RotationItemViewHolder rotationItemViewHolder, float f2) {
                if (n.b(rotationItemViewHolder != null ? Float.valueOf(rotationItemViewHolder.rotationY) : null, f2)) {
                    return;
                }
                if (rotationItemViewHolder != null) {
                    rotationItemViewHolder.rotationY = f2;
                }
                if (rotationItemViewHolder != null) {
                    rotationItemViewHolder.innerScheduleUpdate();
                }
            }
        };
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public RotationItemViewHolder(final View itemView) {
        super(itemView);
        n.g(itemView, "itemView");
        this.pivot = 51;
        this.choreographer = Choreographer.getInstance();
        this.frameCallback = new Choreographer.FrameCallback() { // from class: miui.systemui.controlcenter.panel.main.recyclerview.e
            @Override // android.view.Choreographer.FrameCallback
            public final void doFrame(long j2) {
                RotationItemViewHolder.frameCallback$lambda$0(this.f5436a, itemView, j2);
            }
        };
        this.changeFrame = (ViewGroup) itemView;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void frameCallback$lambda$0(RotationItemViewHolder this$0, View itemView, long j2) {
        View changeFrame;
        n.g(this$0, "this$0");
        n.g(itemView, "$itemView");
        this$0.updateScheduled = false;
        frameCallback$lambda$0$updateRotation(itemView, this$0);
        ChangeToView changeToView = this$0.getChangeToView();
        FakeChangeToView fakeChangeToView = changeToView instanceof FakeChangeToView ? (FakeChangeToView) changeToView : null;
        ViewGroup fakeFrame = fakeChangeToView != null ? fakeChangeToView.getFakeFrame() : null;
        if (fakeFrame instanceof QSTileItemIconView) {
            return;
        }
        ChangeToView changeToView2 = this$0.getChangeToView();
        if (changeToView2 != null && (changeFrame = changeToView2.getChangeFrame()) != null) {
            frameCallback$lambda$0$updateRotation(changeFrame, this$0);
        }
        if (fakeFrame != null) {
            frameCallback$lambda$0$updateRotation(fakeFrame, this$0);
        }
    }

    private static final void frameCallback$lambda$0$updateRotation(View view, RotationItemViewHolder rotationItemViewHolder) {
        view.setRotationX(rotationItemViewHolder.rotationX);
        view.setRotationY(rotationItemViewHolder.rotationY);
        if (view.getRotationX() == 0.0f && view.getRotationY() == 0.0f) {
            view.resetPivot();
        } else {
            view.setPivotX(rotationItemViewHolder.getPivotRatioX() * view.getWidth());
            view.setPivotY(rotationItemViewHolder.getPivotRatioY() * view.getHeight());
        }
    }

    private final float getPivotRatioX() {
        return (this.pivot & 3) == 3 ? 0.0f : 1.0f;
    }

    private final float getPivotRatioY() {
        return (this.pivot & 48) == 48 ? 0.0f : 1.0f;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void innerScheduleUpdate() {
        if (this.updateScheduled) {
            return;
        }
        this.updateScheduled = true;
        this.choreographer.postFrameCallback(this.frameCallback);
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
            this.frameCallback.doFrame(0L);
        }
    }

    @Override // miui.systemui.controlcenter.panel.main.recyclerview.MainPanelItemViewHolder.TouchAnimator
    public void touchDown(MotionEvent ev) {
        n.g(ev, "ev");
        MainPanelItemViewHolder.Companion.setTouchViewHolder(this);
        this.ignoreRelease = false;
        float rawX = ev.getRawX();
        float rawY = ev.getRawY();
        this.itemView.getLocationOnScreen(new int[2]);
        float width = r2[0] + (this.itemView.getWidth() * 0.5f);
        this.pivot = (rawY > r2[1] + (this.itemView.getHeight() * 0.5f) ? 48 : 80) | (rawX > width ? 3 : 5);
        IStateStyle anim = getAnim();
        EaseManager.EaseStyle easeStyle = EASE_ROTATION_DOWN;
        RotationItemViewHolder$Companion$ROTATION_X$1 rotationItemViewHolder$Companion$ROTATION_X$1 = ROTATION_X;
        anim.setEase(easeStyle, rotationItemViewHolder$Companion$ROTATION_X$1);
        IStateStyle anim2 = getAnim();
        RotationItemViewHolder$Companion$ROTATION_Y$1 rotationItemViewHolder$Companion$ROTATION_Y$1 = ROTATION_Y;
        anim2.setEase(easeStyle, rotationItemViewHolder$Companion$ROTATION_Y$1);
        getAnim().to(rotationItemViewHolder$Companion$ROTATION_X$1, Float.valueOf((this.pivot & 48) == 48 ? -5.0f : 5.0f), rotationItemViewHolder$Companion$ROTATION_Y$1, Float.valueOf((this.pivot & 3) != 3 ? -5.0f : 5.0f));
    }

    @Override // miui.systemui.controlcenter.panel.main.recyclerview.MainPanelItemViewHolder.TouchAnimator
    public void touchRelease() {
        if (this.ignoreRelease) {
            return;
        }
        IStateStyle anim = getAnim();
        EaseManager.EaseStyle easeStyle = EASE_ROTATION_CANCEL;
        RotationItemViewHolder$Companion$ROTATION_X$1 rotationItemViewHolder$Companion$ROTATION_X$1 = ROTATION_X;
        anim.setEase(easeStyle, rotationItemViewHolder$Companion$ROTATION_X$1);
        IStateStyle anim2 = getAnim();
        RotationItemViewHolder$Companion$ROTATION_Y$1 rotationItemViewHolder$Companion$ROTATION_Y$1 = ROTATION_Y;
        anim2.setEase(easeStyle, rotationItemViewHolder$Companion$ROTATION_Y$1);
        getAnim().to(rotationItemViewHolder$Companion$ROTATION_X$1, Float.valueOf(0.0f), rotationItemViewHolder$Companion$ROTATION_Y$1, Float.valueOf(0.0f));
    }

    @Override // miui.systemui.controlcenter.panel.main.recyclerview.MainPanelItemViewHolder.TouchAnimator
    public void touchReleaseNow() {
        if (this.ignoreRelease) {
            return;
        }
        IStateStyle anim = getAnim();
        EaseManager.EaseStyle easeStyle = EASE_ROTATION_CANCEL;
        RotationItemViewHolder$Companion$ROTATION_X$1 rotationItemViewHolder$Companion$ROTATION_X$1 = ROTATION_X;
        anim.setEase(easeStyle, rotationItemViewHolder$Companion$ROTATION_X$1);
        IStateStyle anim2 = getAnim();
        RotationItemViewHolder$Companion$ROTATION_Y$1 rotationItemViewHolder$Companion$ROTATION_Y$1 = ROTATION_Y;
        anim2.setEase(easeStyle, rotationItemViewHolder$Companion$ROTATION_Y$1);
        getAnim().setTo(rotationItemViewHolder$Companion$ROTATION_X$1, Float.valueOf(0.0f), rotationItemViewHolder$Companion$ROTATION_Y$1, Float.valueOf(0.0f));
    }

    @Override // miui.systemui.controlcenter.panel.main.recyclerview.MainPanelItemViewHolder.TouchAnimator
    public void touchTrigger() {
        this.ignoreRelease = true;
        IStateStyle anim = getAnim();
        EaseManager.EaseStyle easeStyle = EASE_ROTATION_TRIGGER;
        RotationItemViewHolder$Companion$ROTATION_X$1 rotationItemViewHolder$Companion$ROTATION_X$1 = ROTATION_X;
        anim.setEase(easeStyle, rotationItemViewHolder$Companion$ROTATION_X$1);
        IStateStyle anim2 = getAnim();
        RotationItemViewHolder$Companion$ROTATION_Y$1 rotationItemViewHolder$Companion$ROTATION_Y$1 = ROTATION_Y;
        anim2.setEase(easeStyle, rotationItemViewHolder$Companion$ROTATION_Y$1);
        IStateStyle anim3 = getAnim();
        Float fValueOf = Float.valueOf(0.0f);
        anim3.to(rotationItemViewHolder$Companion$ROTATION_X$1, fValueOf, rotationItemViewHolder$Companion$ROTATION_Y$1, fValueOf);
    }
}
