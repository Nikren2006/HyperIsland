package miui.systemui.controlcenter.panel.main.recyclerview;

import H0.s;
import android.util.Log;
import android.view.View;
import androidx.annotation.CallSuper;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.n;
import miui.systemui.controlcenter.panel.main.MainPanelFrameCallback;
import miuix.animation.Folme;
import miuix.animation.FolmeEase;
import miuix.animation.IStateStyle;
import miuix.animation.base.AnimConfig;
import miuix.animation.property.FloatProperty;
import miuix.animation.utils.EaseManager;

/* JADX INFO: loaded from: classes.dex */
public abstract class ControlCenterViewHolder extends RecyclerView.ViewHolder {
    private static final FloatProperty<ControlCenterViewHolder> ALPHA;
    public static final Companion Companion = new Companion(null);
    private static final EaseManager.EaseStyle EASE_EDIT_HIDE_POSITION;
    private static final EaseManager.EaseStyle EASE_EDIT_SHOW_POSITION;
    private static final EaseManager.EaseStyle EASE_SCALE;
    private static final EaseManager.EaseStyle EASE_TRANS_X;
    private static final EaseManager.EaseStyle EASE_TRANS_Y;
    private static final FloatProperty<ControlCenterViewHolder> SCALE;
    private static final String TAG = "ControlCenterViewHolder";
    private static final FloatProperty<ControlCenterViewHolder> TRANS_X;
    private static final FloatProperty<ControlCenterViewHolder> TRANS_Y;
    private IStateStyle _anim;
    private final ArrayList<Anim> animList;
    private ControlCenterItemAnimator animator;
    private boolean attached;
    private final Runnable callback;
    private MainPanelFrameCallback frameCallback;
    private float holderAlpha;
    private float holderScale;
    private float holderTransX;
    private float holderTransY;
    private boolean ignoreHolderAlpha;
    private boolean ignoreHolderScale;
    private boolean ignoreHolderTranslation;
    private boolean isDragging;
    private boolean isMovedTile;
    private MainPanelAdapter mainPanelAdapter;
    private float targetTransX;
    private float targetTransY;
    private boolean toShow;

    /* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
    /* JADX WARN: Unknown enum class pattern. Please report as an issue! */
    public static final class Anim {
        private static final /* synthetic */ O0.a $ENTRIES;
        private static final /* synthetic */ Anim[] $VALUES;
        public static final Anim ADD = new Anim("ADD", 0);
        public static final Anim REMOVE = new Anim("REMOVE", 1);
        public static final Anim MOVE = new Anim("MOVE", 2);
        public static final Anim CHANGE = new Anim("CHANGE", 3);
        public static final Anim CHANGE_OLD = new Anim("CHANGE_OLD", 4);

        private static final /* synthetic */ Anim[] $values() {
            return new Anim[]{ADD, REMOVE, MOVE, CHANGE, CHANGE_OLD};
        }

        static {
            Anim[] animArr$values = $values();
            $VALUES = animArr$values;
            $ENTRIES = O0.b.a(animArr$values);
        }

        private Anim(String str, int i2) {
        }

        public static O0.a getEntries() {
            return $ENTRIES;
        }

        public static Anim valueOf(String str) {
            return (Anim) Enum.valueOf(Anim.class, str);
        }

        public static Anim[] values() {
            return (Anim[]) $VALUES.clone();
        }
    }

    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final FloatProperty<ControlCenterViewHolder> getALPHA() {
            return ControlCenterViewHolder.ALPHA;
        }

        public final FloatProperty<ControlCenterViewHolder> getSCALE() {
            return ControlCenterViewHolder.SCALE;
        }

        public final FloatProperty<ControlCenterViewHolder> getTRANS_X() {
            return ControlCenterViewHolder.TRANS_X;
        }

        public final FloatProperty<ControlCenterViewHolder> getTRANS_Y() {
            return ControlCenterViewHolder.TRANS_Y;
        }

        private Companion() {
        }
    }

    static {
        EaseManager.EaseStyle style = EaseManager.getStyle(-2, 0.95f, 0.35f);
        n.f(style, "getStyle(...)");
        EASE_SCALE = style;
        EaseManager.EaseStyle style2 = EaseManager.getStyle(-2, 0.95f, 0.3f);
        n.f(style2, "getStyle(...)");
        EASE_TRANS_X = style2;
        EaseManager.EaseStyle style3 = EaseManager.getStyle(-2, 0.95f, 0.3f);
        n.f(style3, "getStyle(...)");
        EASE_TRANS_Y = style3;
        EaseManager.EaseStyle easeStyleSpring = FolmeEase.spring(1.0f, 0.4f);
        n.f(easeStyleSpring, "spring(...)");
        EASE_EDIT_SHOW_POSITION = easeStyleSpring;
        EaseManager.EaseStyle easeStyleSpring2 = FolmeEase.spring(0.95f, 0.35f);
        n.f(easeStyleSpring2, "spring(...)");
        EASE_EDIT_HIDE_POSITION = easeStyleSpring2;
        ALPHA = new FloatProperty<ControlCenterViewHolder>() { // from class: miui.systemui.controlcenter.panel.main.recyclerview.ControlCenterViewHolder$Companion$ALPHA$1
            @Override // miuix.animation.property.FloatProperty
            public float getValue(ControlCenterViewHolder controlCenterViewHolder) {
                if (controlCenterViewHolder != null) {
                    return controlCenterViewHolder.getHolderAlpha();
                }
                return 1.0f;
            }

            @Override // miuix.animation.property.FloatProperty
            public void setValue(ControlCenterViewHolder controlCenterViewHolder, float f2) {
                if (controlCenterViewHolder != null) {
                    controlCenterViewHolder.setHolderAlpha(f2);
                }
                if (controlCenterViewHolder != null) {
                    controlCenterViewHolder.scheduleUpdate();
                }
            }
        };
        TRANS_X = new FloatProperty<ControlCenterViewHolder>() { // from class: miui.systemui.controlcenter.panel.main.recyclerview.ControlCenterViewHolder$Companion$TRANS_X$1
            @Override // miuix.animation.property.FloatProperty
            public float getValue(ControlCenterViewHolder controlCenterViewHolder) {
                if (controlCenterViewHolder != null) {
                    return controlCenterViewHolder.getHolderTransX();
                }
                return 0.0f;
            }

            @Override // miuix.animation.property.FloatProperty
            public void setValue(ControlCenterViewHolder controlCenterViewHolder, float f2) {
                if (controlCenterViewHolder != null) {
                    controlCenterViewHolder.setHolderTransX(f2);
                }
                if (controlCenterViewHolder != null) {
                    controlCenterViewHolder.scheduleUpdate();
                }
            }
        };
        TRANS_Y = new FloatProperty<ControlCenterViewHolder>() { // from class: miui.systemui.controlcenter.panel.main.recyclerview.ControlCenterViewHolder$Companion$TRANS_Y$1
            @Override // miuix.animation.property.FloatProperty
            public float getValue(ControlCenterViewHolder controlCenterViewHolder) {
                if (controlCenterViewHolder != null) {
                    return controlCenterViewHolder.getHolderTransY();
                }
                return 0.0f;
            }

            @Override // miuix.animation.property.FloatProperty
            public void setValue(ControlCenterViewHolder controlCenterViewHolder, float f2) {
                if (controlCenterViewHolder != null) {
                    controlCenterViewHolder.setHolderTransY(f2);
                }
                if (controlCenterViewHolder != null) {
                    controlCenterViewHolder.scheduleUpdate();
                }
            }
        };
        SCALE = new FloatProperty<ControlCenterViewHolder>() { // from class: miui.systemui.controlcenter.panel.main.recyclerview.ControlCenterViewHolder$Companion$SCALE$1
            @Override // miuix.animation.property.FloatProperty
            public float getValue(ControlCenterViewHolder controlCenterViewHolder) {
                if (controlCenterViewHolder != null) {
                    return controlCenterViewHolder.getHolderScale();
                }
                return 1.0f;
            }

            @Override // miuix.animation.property.FloatProperty
            public void setValue(ControlCenterViewHolder controlCenterViewHolder, float f2) {
                if (controlCenterViewHolder != null) {
                    controlCenterViewHolder.setHolderScale(f2);
                }
                if (controlCenterViewHolder != null) {
                    controlCenterViewHolder.scheduleUpdate();
                }
            }
        };
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ControlCenterViewHolder(View itemView) {
        super(itemView);
        n.g(itemView, "itemView");
        this.holderAlpha = 1.0f;
        this.holderScale = 1.0f;
        this.toShow = true;
        this.animList = new ArrayList<>();
        this.callback = new Runnable() { // from class: miui.systemui.controlcenter.panel.main.recyclerview.a
            @Override // java.lang.Runnable
            public final void run() {
                ControlCenterViewHolder.callback$lambda$2(this.f5432a);
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void callback$lambda$2(ControlCenterViewHolder this$0) {
        n.g(this$0, "this$0");
        for (Anim anim : this$0.animList) {
            ControlCenterItemAnimator controlCenterItemAnimator = this$0.animator;
            if (controlCenterItemAnimator != null) {
                controlCenterItemAnimator.performFinished(this$0, true);
            }
        }
        this$0.animList.clear();
        this$0.toShow = true;
        this$0.targetTransX = 0.0f;
        this$0.setTargetTransY(0.0f);
        if (this$0.isDragging) {
            return;
        }
        this$0.ignoreHolderTranslation = false;
    }

    public final void endAnimation() {
        boolean z2 = this.toShow;
        Float fValueOf = Float.valueOf(0.0f);
        getAnim().setTo(ALPHA, Float.valueOf(z2 ? 1.0f : 0.0f), SCALE, Float.valueOf(z2 ? 1.0f : 0.8f), TRANS_X, fValueOf, TRANS_Y, fValueOf);
        onFrameCallback();
        this.itemView.removeCallbacks(this.callback);
        this.callback.run();
    }

    public final IStateStyle getAnim() {
        IStateStyle iStateStyle = this._anim;
        if (iStateStyle != null) {
            return iStateStyle;
        }
        IStateStyle ease = Folme.useValue(this).setEase(EASE_SCALE, SCALE).setEase(EASE_TRANS_X, TRANS_X).setEase(EASE_TRANS_Y, TRANS_Y);
        this._anim = ease;
        n.d(ease);
        return ease;
    }

    public float getHolderAlpha() {
        return this.holderAlpha;
    }

    public float getHolderScale() {
        return this.holderScale;
    }

    public float getHolderTransX() {
        return this.holderTransX;
    }

    public float getHolderTransY() {
        return this.holderTransY;
    }

    public final boolean getIgnoreHolderAlpha() {
        return this.ignoreHolderAlpha;
    }

    public final boolean getIgnoreHolderScale() {
        return this.ignoreHolderScale;
    }

    public final boolean getIgnoreHolderTranslation() {
        return this.ignoreHolderTranslation;
    }

    public final MainPanelAdapter getMainPanelAdapter() {
        MainPanelAdapter mainPanelAdapter = this.mainPanelAdapter;
        if (mainPanelAdapter != null) {
            return mainPanelAdapter;
        }
        n.w("mainPanelAdapter");
        return null;
    }

    public float getTargetTransY() {
        return this.targetTransY;
    }

    public abstract void onFrameCallback();

    public final void onStartDrag() {
        this.isDragging = true;
        this.ignoreHolderTranslation = true;
    }

    public final void onStopDrag() {
        this.isDragging = false;
        if (this.animList.isEmpty()) {
            this.ignoreHolderTranslation = false;
        }
    }

    public void onViewAttachedToWindow() {
    }

    @CallSuper
    public void onViewDetachedFromWindow() {
        this.attached = false;
    }

    public final void performAnimation() {
        AnimConfig animConfig;
        boolean z2 = this.toShow;
        float f2 = z2 ? 1.0f : 0.0f;
        float f3 = z2 ? 1.0f : 0.0f;
        if (getMainPanelAdapter().getModeChanged()) {
            boolean z3 = this.toShow;
            boolean z4 = this.isMovedTile;
            MainPanelAdapter mainPanelAdapter = getMainPanelAdapter();
            n.e(this, "null cannot be cast to non-null type miui.systemui.controlcenter.panel.main.recyclerview.MainPanelItemViewHolder");
            animConfig = updateAnimConfig(z3, z4, mainPanelAdapter.getAddTileSpreadRow((MainPanelItemViewHolder) this), getMainPanelAdapter().getCount());
        } else {
            animConfig = new AnimConfig();
        }
        AnimConfig animConfig2 = animConfig;
        IStateStyle anim = getAnim();
        FloatProperty<ControlCenterViewHolder> floatProperty = ALPHA;
        Float fValueOf = Float.valueOf(f2);
        FloatProperty<ControlCenterViewHolder> floatProperty2 = SCALE;
        Float fValueOf2 = Float.valueOf(f3);
        FloatProperty<ControlCenterViewHolder> floatProperty3 = TRANS_X;
        Float fValueOf3 = Float.valueOf(this.targetTransX);
        FloatProperty<ControlCenterViewHolder> floatProperty4 = TRANS_Y;
        anim.to(floatProperty, fValueOf, floatProperty2, fValueOf2, floatProperty3, fValueOf3, floatProperty4, Float.valueOf(getTargetTransY()), animConfig2);
        this.isMovedTile = false;
        long jPredictDuration = getAnim().predictDuration(floatProperty, Float.valueOf(f2), floatProperty2, Float.valueOf(f3), floatProperty3, Float.valueOf(this.targetTransX), floatProperty4, Float.valueOf(getTargetTransY()));
        Log.i(TAG, "performAnimation " + jPredictDuration);
        this.itemView.removeCallbacks(this.callback);
        this.itemView.postDelayed(this.callback, jPredictDuration);
    }

    public final void prepareAdd(ControlCenterItemAnimator animator, float f2) {
        n.g(animator, "animator");
        this.animator = animator;
        this.toShow = true;
        if (getHolderAlpha() == 1.0f) {
            setHolderAlpha(0.0f);
        }
        if (getHolderScale() == 1.0f) {
            setHolderScale(0.0f);
        }
        if (getMainPanelAdapter().getModeChanged()) {
            update(this.toShow, f2);
        }
        onFrameCallback();
        this.animList.add(Anim.ADD);
    }

    public final void prepareAddByChange(ControlCenterItemAnimator animator, int i2, int i3, int i4, int i5) {
        n.g(animator, "animator");
        this.animator = animator;
        this.toShow = true;
        if (getHolderAlpha() == 1.0f) {
            setHolderAlpha(0.0f);
        }
        setHolderTransX(i2 - i4);
        setHolderTransY(i3 - i5);
        this.targetTransX = 0.0f;
        setTargetTransY(0.0f);
        this.animList.add(Anim.CHANGE);
    }

    public final void prepareMove(ControlCenterItemAnimator animator, int i2, int i3, int i4, int i5) {
        n.g(animator, "animator");
        int i6 = i2 - i4;
        int i7 = i3 - i5;
        this.animator = animator;
        IStateStyle anim = getAnim();
        FloatProperty<ControlCenterViewHolder> floatProperty = TRANS_X;
        Float fValueOf = Float.valueOf(getHolderTransX() + i6);
        FloatProperty<ControlCenterViewHolder> floatProperty2 = TRANS_Y;
        anim.setTo(floatProperty, fValueOf, floatProperty2, Float.valueOf(getHolderTransY() + i7));
        onFrameCallback();
        Log.i(TAG, "prepareMove: " + i7 + " " + (!this.animList.isEmpty()));
        if (!this.animList.isEmpty() || this.toShow) {
            getAnim().to(floatProperty, Float.valueOf(this.targetTransX), floatProperty2, Float.valueOf(getTargetTransY()), new AnimConfig().setSpecial(floatProperty2, this.toShow ? EASE_EDIT_HIDE_POSITION : EASE_EDIT_SHOW_POSITION, new float[0]));
        }
        this.isMovedTile = true;
        this.animList.add(Anim.MOVE);
    }

    public final void prepareRemove(ControlCenterItemAnimator animator, float f2) {
        n.g(animator, "animator");
        this.animator = animator;
        this.toShow = false;
        if (getMainPanelAdapter().getModeChanged()) {
            update(false, f2);
        }
        this.animList.add(Anim.REMOVE);
    }

    public final void prepareRemoveByChange(ControlCenterItemAnimator animator, int i2, int i3, int i4, int i5) {
        n.g(animator, "animator");
        this.animator = animator;
        this.toShow = false;
        this.targetTransX = i4 - i2;
        setTargetTransY(i5 - i3);
        this.animList.add(Anim.CHANGE_OLD);
    }

    @CallSuper
    public void recycle() {
        this._anim = null;
        Folme.clean(this);
    }

    public final void scheduleUpdate() {
        s sVar;
        MainPanelFrameCallback mainPanelFrameCallback = this.frameCallback;
        if (mainPanelFrameCallback != null) {
            mainPanelFrameCallback.scheduleUpdate();
            sVar = s.f314a;
        } else {
            sVar = null;
        }
        if (sVar == null) {
            onFrameCallback();
        }
    }

    public void setHolderAlpha(float f2) {
        this.holderAlpha = f2;
    }

    public void setHolderScale(float f2) {
        this.holderScale = f2;
    }

    public void setHolderTransX(float f2) {
        this.holderTransX = f2;
    }

    public void setHolderTransY(float f2) {
        this.holderTransY = f2;
    }

    public final void setIgnoreHolderAlpha(boolean z2) {
        this.ignoreHolderAlpha = z2;
    }

    public final void setIgnoreHolderScale(boolean z2) {
        this.ignoreHolderScale = z2;
    }

    public final void setIgnoreHolderTranslation(boolean z2) {
        this.ignoreHolderTranslation = z2;
    }

    public void setTargetTransY(float f2) {
        this.targetTransY = f2;
    }

    public void update(boolean z2, float f2) {
    }

    public AnimConfig updateAnimConfig(boolean z2, boolean z3, int i2, int i3) {
        AnimConfig special = new AnimConfig().setSpecial(TRANS_X, EASE_TRANS_X, new float[0]);
        n.f(special, "setSpecial(...)");
        return special;
    }

    @CallSuper
    public void onViewAttachedToWindow(MainPanelAdapter mainPanelAdapter, MainPanelFrameCallback frameCallback) {
        n.g(mainPanelAdapter, "mainPanelAdapter");
        n.g(frameCallback, "frameCallback");
        this.attached = true;
        this.mainPanelAdapter = mainPanelAdapter;
        this.frameCallback = frameCallback;
        setHolderAlpha(1.0f);
        setHolderScale(1.0f);
        setHolderTransX(0.0f);
        setHolderTransY(0.0f);
        this.targetTransX = 0.0f;
        setTargetTransY(0.0f);
        this.toShow = true;
        onViewAttachedToWindow();
        onFrameCallback();
    }
}
