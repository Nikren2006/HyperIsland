package miui.systemui.controlcenter.panel.main.anim;

import H0.i;
import c1.f;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.n;
import miui.systemui.controlcenter.panel.main.MainPanelController;
import miui.systemui.controlcenter.panel.main.MainPanelFrameCallback;
import miui.systemui.controlcenter.panel.main.recyclerview.MainPanelItemViewHolder;
import miui.systemui.controlcenter.windowview.ControlCenterExpandController;
import miuix.animation.Folme;
import miuix.animation.FolmeEase;
import miuix.animation.IStateStyle;
import miuix.animation.base.AnimConfig;
import miuix.animation.property.FloatProperty;
import miuix.animation.utils.EaseManager;

/* JADX INFO: loaded from: classes.dex */
public final class LowEndItemHolderAnimator {
    public static final Companion Companion = new Companion(null);
    private static final FloatProperty<LowEndItemHolderAnimator> EXPANDED_STRETCH;
    private static final FloatProperty<LowEndItemHolderAnimator> EXPAND_ALPHA;
    private static final FloatProperty<LowEndItemHolderAnimator> EXPAND_SCALE;
    private static final EaseManager.EaseStyle alphaLowEndEase;
    private static final EaseManager.EaseStyle mirrorAlphaEase;
    private static final EaseManager.EaseStyle releaseTransYEase;
    private static final EaseManager.EaseStyle scaleLowEndEase;
    private IStateStyle _anim;
    private final AnimConfig animateConfig;
    private float expandAlpha;
    private final E0.a expandController;
    private float expandScaleRatio;
    private float expandedStretch;
    private i expandedStretchPair;
    private final MainPanelFrameCallback frameCallback;
    private final E0.a mainPanelController;
    private boolean mirrorShowing;
    private float preFrameExpandedStretch;
    private final SpreadRowsAnimator spreadRowsAnimator;
    private float thresh;

    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final void changeVisibleWhenAttach(MainPanelItemViewHolder holder, boolean z2) {
            n.g(holder, "holder");
            float f2 = z2 ? 1.0f : 0.0f;
            holder.setExpandAlpha(f2);
            holder.setExpandScaleRatio(f2);
            holder.setShrinkX(f2);
            holder.setShrinkY(f2);
            holder.scheduleUpdate();
        }

        public final EaseManager.EaseStyle getAlphaLowEndEase() {
            return LowEndItemHolderAnimator.alphaLowEndEase;
        }

        public final FloatProperty<LowEndItemHolderAnimator> getEXPANDED_STRETCH() {
            return LowEndItemHolderAnimator.EXPANDED_STRETCH;
        }

        public final FloatProperty<LowEndItemHolderAnimator> getEXPAND_ALPHA() {
            return LowEndItemHolderAnimator.EXPAND_ALPHA;
        }

        public final FloatProperty<LowEndItemHolderAnimator> getEXPAND_SCALE() {
            return LowEndItemHolderAnimator.EXPAND_SCALE;
        }

        public final EaseManager.EaseStyle getMirrorAlphaEase() {
            return LowEndItemHolderAnimator.mirrorAlphaEase;
        }

        public final EaseManager.EaseStyle getReleaseTransYEase() {
            return LowEndItemHolderAnimator.releaseTransYEase;
        }

        public final EaseManager.EaseStyle getScaleLowEndEase() {
            return LowEndItemHolderAnimator.scaleLowEndEase;
        }

        private Companion() {
        }
    }

    static {
        EaseManager.EaseStyle style = EaseManager.getStyle(-2, 1.0f, 0.4f);
        n.f(style, "getStyle(...)");
        releaseTransYEase = style;
        EaseManager.EaseStyle style2 = EaseManager.getStyle(-2, 0.95f, 0.35f);
        n.f(style2, "getStyle(...)");
        scaleLowEndEase = style2;
        EaseManager.EaseStyle style3 = EaseManager.getStyle(-2, 1.0f, 0.15f);
        n.f(style3, "getStyle(...)");
        alphaLowEndEase = style3;
        mirrorAlphaEase = FolmeEase.spring(0.95f, 0.35f);
        EXPAND_ALPHA = new FloatProperty<LowEndItemHolderAnimator>() { // from class: miui.systemui.controlcenter.panel.main.anim.LowEndItemHolderAnimator$Companion$EXPAND_ALPHA$1
            @Override // miuix.animation.property.FloatProperty
            public float getValue(LowEndItemHolderAnimator lowEndItemHolderAnimator) {
                if (lowEndItemHolderAnimator != null) {
                    return lowEndItemHolderAnimator.expandAlpha;
                }
                return 1.0f;
            }

            @Override // miuix.animation.property.FloatProperty
            public void setValue(LowEndItemHolderAnimator lowEndItemHolderAnimator, float f2) {
                MainPanelFrameCallback mainPanelFrameCallback;
                if (lowEndItemHolderAnimator != null) {
                    lowEndItemHolderAnimator.expandAlpha = f2;
                }
                if (lowEndItemHolderAnimator == null || (mainPanelFrameCallback = lowEndItemHolderAnimator.frameCallback) == null) {
                    return;
                }
                mainPanelFrameCallback.scheduleUpdate();
            }
        };
        EXPAND_SCALE = new FloatProperty<LowEndItemHolderAnimator>() { // from class: miui.systemui.controlcenter.panel.main.anim.LowEndItemHolderAnimator$Companion$EXPAND_SCALE$1
            @Override // miuix.animation.property.FloatProperty
            public float getValue(LowEndItemHolderAnimator lowEndItemHolderAnimator) {
                if (lowEndItemHolderAnimator != null) {
                    return lowEndItemHolderAnimator.expandScaleRatio;
                }
                return 1.0f;
            }

            @Override // miuix.animation.property.FloatProperty
            public void setValue(LowEndItemHolderAnimator lowEndItemHolderAnimator, float f2) {
                MainPanelFrameCallback mainPanelFrameCallback;
                if (lowEndItemHolderAnimator != null) {
                    lowEndItemHolderAnimator.expandScaleRatio = f2;
                }
                if (lowEndItemHolderAnimator == null || (mainPanelFrameCallback = lowEndItemHolderAnimator.frameCallback) == null) {
                    return;
                }
                mainPanelFrameCallback.scheduleUpdate();
            }
        };
        EXPANDED_STRETCH = new FloatProperty<LowEndItemHolderAnimator>() { // from class: miui.systemui.controlcenter.panel.main.anim.LowEndItemHolderAnimator$Companion$EXPANDED_STRETCH$1
            @Override // miuix.animation.property.FloatProperty
            public float getValue(LowEndItemHolderAnimator lowEndItemHolderAnimator) {
                if (lowEndItemHolderAnimator != null) {
                    return lowEndItemHolderAnimator.expandedStretch;
                }
                return 0.0f;
            }

            @Override // miuix.animation.property.FloatProperty
            public void setValue(LowEndItemHolderAnimator lowEndItemHolderAnimator, float f2) {
                MainPanelFrameCallback mainPanelFrameCallback;
                if (lowEndItemHolderAnimator != null) {
                    lowEndItemHolderAnimator.expandedStretch = f2;
                }
                if (lowEndItemHolderAnimator == null || (mainPanelFrameCallback = lowEndItemHolderAnimator.frameCallback) == null) {
                    return;
                }
                mainPanelFrameCallback.scheduleUpdate();
            }
        };
    }

    public LowEndItemHolderAnimator(E0.a expandController, MainPanelFrameCallback frameCallback, SpreadRowsAnimator spreadRowsAnimator, E0.a mainPanelController) {
        n.g(expandController, "expandController");
        n.g(frameCallback, "frameCallback");
        n.g(spreadRowsAnimator, "spreadRowsAnimator");
        n.g(mainPanelController, "mainPanelController");
        this.expandController = expandController;
        this.frameCallback = frameCallback;
        this.spreadRowsAnimator = spreadRowsAnimator;
        this.mainPanelController = mainPanelController;
        this.animateConfig = new AnimConfig().setSpecial(EXPAND_SCALE, scaleLowEndEase, new float[0]).setSpecial(EXPAND_ALPHA, alphaLowEndEase, new float[0]);
        this.preFrameExpandedStretch = -1.0f;
    }

    public static /* synthetic */ void changeVisible$default(LowEndItemHolderAnimator lowEndItemHolderAnimator, boolean z2, boolean z3, boolean z4, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            z3 = true;
        }
        if ((i2 & 4) != 0) {
            z4 = false;
        }
        lowEndItemHolderAnimator.changeVisible(z2, z3, z4);
    }

    private final void expandedStretchAnimate(MainPanelItemViewHolder mainPanelItemViewHolder) {
        this.preFrameExpandedStretch = this.expandedStretch;
        i iVar = this.expandedStretchPair;
        if (iVar != null && ((Number) iVar.e()).floatValue() == mainPanelItemViewHolder.getCenterYFromHeader()) {
            mainPanelItemViewHolder.setExpandTransY(((Number) iVar.d()).floatValue());
            return;
        }
        i iVarCalExpandedStretch$default = MainPanelItemViewHolder.calExpandedStretch$default(mainPanelItemViewHolder, this.expandedStretch, this.thresh, mainPanelItemViewHolder.getDeltaY(), false, ((MainPanelController) this.mainPanelController.get()).getMainPanelScrolling(), 8, null);
        this.expandedStretchPair = iVarCalExpandedStretch$default;
        mainPanelItemViewHolder.setExpandTransY(((Number) iVarCalExpandedStretch$default.d()).floatValue());
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

    public final void changeVisible(boolean z2, boolean z3, boolean z4) {
        this.mirrorShowing = z4;
        AnimConfig animConfig = this.animateConfig;
        FloatProperty<LowEndItemHolderAnimator> floatProperty = EXPAND_ALPHA;
        animConfig.setSpecial(floatProperty, (z4 && z2) ? mirrorAlphaEase : alphaLowEndEase, new float[0]);
        if (z3) {
            getAnim().to(floatProperty, Float.valueOf(z2 ? 1.0f : 0.0f), EXPAND_SCALE, Float.valueOf((z4 || z2) ? 1.0f : 0.0f), this.animateConfig);
        } else {
            getAnim().setTo(floatProperty, Float.valueOf(z2 ? 1.0f : 0.0f), EXPAND_SCALE, Float.valueOf((z4 || z2) ? 1.0f : 0.0f));
        }
    }

    public final void expandedStretch(float f2, float f3, boolean z2, boolean z3) {
        this.thresh = f3;
        if (z2) {
            getAnim().to(EXPANDED_STRETCH, Float.valueOf(f2), new AnimConfig().setEase(z3 ? releaseTransYEase : MainPanelItemViewHolder.Companion.getExpandTransYEase()));
        } else {
            getAnim().setTo(EXPANDED_STRETCH, Float.valueOf(f2));
        }
    }

    public final boolean getExpandedStretchChanged() {
        return !(this.preFrameExpandedStretch == this.expandedStretch);
    }

    public final void onFrameCallback(MainPanelItemViewHolder holder, boolean z2) {
        n.g(holder, "holder");
        holder.setExpandAlpha(this.expandAlpha);
        holder.setExpandScaleRatio(this.expandScaleRatio);
        holder.setMirrorShowing(this.mirrorShowing);
        if (z2 && holder.getAttached$miui_controlcenter_release()) {
            expandedStretchAnimate(holder);
        }
    }

    public final void release() {
        Folme.clean(this);
        this._anim = null;
    }

    public final void updateHeaderStretch() {
        float fLerp;
        float fB = f.b(this.expandedStretch / this.thresh, 0.0f);
        if (fB > 1.0f) {
            float f2 = this.thresh;
            fLerp = f2 + (Folme.afterFrictionValue(this.expandedStretch - f2, this.spreadRowsAnimator.getScreenHeight()) * 0.4f);
        } else {
            fLerp = MainPanelItemViewHolder.Companion.lerp(fB, 0.0f, this.thresh);
        }
        ((ControlCenterExpandController) this.expandController.get()).notifyStretchHeightChanged(fLerp - this.thresh);
    }
}
