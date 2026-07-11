package miui.systemui.controlcenter.panel.secondary.brightness;

import android.content.Context;
import android.util.Log;
import android.view.ViewGroup;
import android.view.ViewParent;
import java.util.Collection;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.n;
import miui.systemui.animation.FolmeUtilsExtKt;
import miui.systemui.controlcenter.dagger.ControlCenterScope;
import miui.systemui.controlcenter.panel.secondary.BrightnessPanelParams;
import miui.systemui.controlcenter.panel.secondary.SecondaryPanelAnimatorBase;
import miui.systemui.controlcenter.panel.secondary.SliderFromView;
import miui.systemui.controlcenter.widget.MainPanelRecyclerView;
import miui.systemui.controlcenter.widget.ToggleSliderView;
import miui.systemui.controlcenter.windowview.ControlCenterWindowViewController;
import miui.systemui.util.CommonUtils;
import miui.systemui.widget.LinearLayout;
import miuix.animation.Folme;
import miuix.animation.IStateStyle;
import miuix.animation.base.AnimConfig;
import miuix.animation.listener.TransitionListener;
import miuix.animation.listener.UpdateInfo;
import miuix.animation.property.FloatProperty;

/* JADX INFO: loaded from: classes.dex */
@ControlCenterScope
public final class BrightnessPanelAnimator extends SecondaryPanelAnimatorBase<BrightnessPanelParams> {
    private final IStateStyle anim;
    private final AnimConfig animConfig;
    private AnimValue animValue;
    private float color;
    private final Context context;
    private SliderFromView fromView;
    private AnimValue lastAnimValue;
    private final E0.a mainPanelController;
    private final E0.a panelController;
    private float position;
    private float scaleSlider;
    private float size;
    private final BrightnessPanelSliderDelegate sliderDelegate;
    private final E0.a windowViewController;
    public static final Companion Companion = new Companion(null);
    private static BrightnessPanelAnimator$Companion$SIZE$1 SIZE = new FloatProperty<BrightnessPanelAnimator>() { // from class: miui.systemui.controlcenter.panel.secondary.brightness.BrightnessPanelAnimator$Companion$SIZE$1
        @Override // miuix.animation.property.FloatProperty
        public float getValue(BrightnessPanelAnimator anim) {
            n.g(anim, "anim");
            return anim.size;
        }

        @Override // miuix.animation.property.FloatProperty
        public void setValue(BrightnessPanelAnimator anim, float f2) {
            n.g(anim, "anim");
            if (anim.size == f2) {
                return;
            }
            anim.size = f2;
            anim.scheduleUpdate();
        }
    };
    private static BrightnessPanelAnimator$Companion$COLOR$1 COLOR = new FloatProperty<BrightnessPanelAnimator>() { // from class: miui.systemui.controlcenter.panel.secondary.brightness.BrightnessPanelAnimator$Companion$COLOR$1
        @Override // miuix.animation.property.FloatProperty
        public float getValue(BrightnessPanelAnimator anim) {
            n.g(anim, "anim");
            return anim.color;
        }

        @Override // miuix.animation.property.FloatProperty
        public void setValue(BrightnessPanelAnimator anim, float f2) {
            n.g(anim, "anim");
            if (anim.color == f2) {
                return;
            }
            anim.color = f2;
            anim.scheduleUpdate();
        }
    };
    private static BrightnessPanelAnimator$Companion$POSITION$1 POSITION = new FloatProperty<BrightnessPanelAnimator>() { // from class: miui.systemui.controlcenter.panel.secondary.brightness.BrightnessPanelAnimator$Companion$POSITION$1
        @Override // miuix.animation.property.FloatProperty
        public float getValue(BrightnessPanelAnimator anim) {
            n.g(anim, "anim");
            return anim.position;
        }

        @Override // miuix.animation.property.FloatProperty
        public void setValue(BrightnessPanelAnimator anim, float f2) {
            n.g(anim, "anim");
            if (anim.position == f2) {
                return;
            }
            anim.position = f2;
            anim.scheduleUpdate();
        }
    };
    private static BrightnessPanelAnimator$Companion$SCALE_SLIDER$1 SCALE_SLIDER = new FloatProperty<BrightnessPanelAnimator>() { // from class: miui.systemui.controlcenter.panel.secondary.brightness.BrightnessPanelAnimator$Companion$SCALE_SLIDER$1
        @Override // miuix.animation.property.FloatProperty
        public float getValue(BrightnessPanelAnimator anim) {
            n.g(anim, "anim");
            return anim.scaleSlider;
        }

        @Override // miuix.animation.property.FloatProperty
        public void setValue(BrightnessPanelAnimator anim, float f2) {
            n.g(anim, "anim");
            if (anim.scaleSlider == f2) {
                return;
            }
            anim.scaleSlider = f2;
            anim.scheduleUpdate();
        }
    };

    public static final class AnimValue {
        private final SecondaryPanelAnimatorBase.ViewLocValue from;
        private final float fromCenterX;
        private final float fromCenterY;
        private final SecondaryPanelAnimatorBase.ViewValue fromIcon;
        private final float fromOutlineRadius;
        private final float fromProgressRadius;
        private final float toCenterX;
        private final float toCenterY;
        private final SecondaryPanelAnimatorBase.ViewLocValue toContainer;
        private final float toContentBgRadius;
        private final SecondaryPanelAnimatorBase.ViewValue toIcon;
        private final float toOutlineRadius;
        private final float toProgressRadius;
        private final SecondaryPanelAnimatorBase.ViewLocValue toSlider;
        private final SecondaryPanelAnimatorBase.ViewLocValue toTiles;

        public AnimValue(SecondaryPanelAnimatorBase.ViewLocValue from, SecondaryPanelAnimatorBase.ViewValue fromIcon, SecondaryPanelAnimatorBase.ViewLocValue toContainer, SecondaryPanelAnimatorBase.ViewLocValue toSlider, SecondaryPanelAnimatorBase.ViewLocValue toTiles, SecondaryPanelAnimatorBase.ViewValue toIcon, float f2, float f3, float f4, float f5, float f6) {
            n.g(from, "from");
            n.g(fromIcon, "fromIcon");
            n.g(toContainer, "toContainer");
            n.g(toSlider, "toSlider");
            n.g(toTiles, "toTiles");
            n.g(toIcon, "toIcon");
            this.from = from;
            this.fromIcon = fromIcon;
            this.toContainer = toContainer;
            this.toSlider = toSlider;
            this.toTiles = toTiles;
            this.toIcon = toIcon;
            this.fromProgressRadius = f2;
            this.fromOutlineRadius = f3;
            this.toContentBgRadius = f4;
            this.toProgressRadius = f5;
            this.toOutlineRadius = f6;
            float f7 = 2;
            this.fromCenterX = from.getLocLeft() + (from.getWidth() / f7);
            this.fromCenterY = from.getLocTop() + (from.getHeight() / f7);
            this.toCenterX = toContainer.getLocLeft() + (toContainer.getWidth() / f7);
            this.toCenterY = toContainer.getLocTop() + (toContainer.getHeight() / f7);
        }

        public final SecondaryPanelAnimatorBase.ViewLocValue component1() {
            return this.from;
        }

        public final float component10() {
            return this.toProgressRadius;
        }

        public final float component11() {
            return this.toOutlineRadius;
        }

        public final SecondaryPanelAnimatorBase.ViewValue component2() {
            return this.fromIcon;
        }

        public final SecondaryPanelAnimatorBase.ViewLocValue component3() {
            return this.toContainer;
        }

        public final SecondaryPanelAnimatorBase.ViewLocValue component4() {
            return this.toSlider;
        }

        public final SecondaryPanelAnimatorBase.ViewLocValue component5() {
            return this.toTiles;
        }

        public final SecondaryPanelAnimatorBase.ViewValue component6() {
            return this.toIcon;
        }

        public final float component7() {
            return this.fromProgressRadius;
        }

        public final float component8() {
            return this.fromOutlineRadius;
        }

        public final float component9() {
            return this.toContentBgRadius;
        }

        public final AnimValue copy(SecondaryPanelAnimatorBase.ViewLocValue from, SecondaryPanelAnimatorBase.ViewValue fromIcon, SecondaryPanelAnimatorBase.ViewLocValue toContainer, SecondaryPanelAnimatorBase.ViewLocValue toSlider, SecondaryPanelAnimatorBase.ViewLocValue toTiles, SecondaryPanelAnimatorBase.ViewValue toIcon, float f2, float f3, float f4, float f5, float f6) {
            n.g(from, "from");
            n.g(fromIcon, "fromIcon");
            n.g(toContainer, "toContainer");
            n.g(toSlider, "toSlider");
            n.g(toTiles, "toTiles");
            n.g(toIcon, "toIcon");
            return new AnimValue(from, fromIcon, toContainer, toSlider, toTiles, toIcon, f2, f3, f4, f5, f6);
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof AnimValue)) {
                return false;
            }
            AnimValue animValue = (AnimValue) obj;
            return n.c(this.from, animValue.from) && n.c(this.fromIcon, animValue.fromIcon) && n.c(this.toContainer, animValue.toContainer) && n.c(this.toSlider, animValue.toSlider) && n.c(this.toTiles, animValue.toTiles) && n.c(this.toIcon, animValue.toIcon) && Float.compare(this.fromProgressRadius, animValue.fromProgressRadius) == 0 && Float.compare(this.fromOutlineRadius, animValue.fromOutlineRadius) == 0 && Float.compare(this.toContentBgRadius, animValue.toContentBgRadius) == 0 && Float.compare(this.toProgressRadius, animValue.toProgressRadius) == 0 && Float.compare(this.toOutlineRadius, animValue.toOutlineRadius) == 0;
        }

        public final SecondaryPanelAnimatorBase.ViewLocValue getFrom() {
            return this.from;
        }

        public final float getFromCenterX() {
            return this.fromCenterX;
        }

        public final float getFromCenterY() {
            return this.fromCenterY;
        }

        public final SecondaryPanelAnimatorBase.ViewValue getFromIcon() {
            return this.fromIcon;
        }

        public final float getFromOutlineRadius() {
            return this.fromOutlineRadius;
        }

        public final float getFromProgressRadius() {
            return this.fromProgressRadius;
        }

        public final float getToCenterX() {
            return this.toCenterX;
        }

        public final float getToCenterY() {
            return this.toCenterY;
        }

        public final SecondaryPanelAnimatorBase.ViewLocValue getToContainer() {
            return this.toContainer;
        }

        public final float getToContentBgRadius() {
            return this.toContentBgRadius;
        }

        public final SecondaryPanelAnimatorBase.ViewValue getToIcon() {
            return this.toIcon;
        }

        public final float getToOutlineRadius() {
            return this.toOutlineRadius;
        }

        public final float getToProgressRadius() {
            return this.toProgressRadius;
        }

        public final SecondaryPanelAnimatorBase.ViewLocValue getToSlider() {
            return this.toSlider;
        }

        public final SecondaryPanelAnimatorBase.ViewLocValue getToTiles() {
            return this.toTiles;
        }

        public int hashCode() {
            return (((((((((((((((((((this.from.hashCode() * 31) + this.fromIcon.hashCode()) * 31) + this.toContainer.hashCode()) * 31) + this.toSlider.hashCode()) * 31) + this.toTiles.hashCode()) * 31) + this.toIcon.hashCode()) * 31) + Float.hashCode(this.fromProgressRadius)) * 31) + Float.hashCode(this.fromOutlineRadius)) * 31) + Float.hashCode(this.toContentBgRadius)) * 31) + Float.hashCode(this.toProgressRadius)) * 31) + Float.hashCode(this.toOutlineRadius);
        }

        public String toString() {
            return "AnimValue(from=" + this.from + ", fromIcon=" + this.fromIcon + ", toContainer=" + this.toContainer + ", toSlider=" + this.toSlider + ", toTiles=" + this.toTiles + ", toIcon=" + this.toIcon + ", fromProgressRadius=" + this.fromProgressRadius + ", fromOutlineRadius=" + this.fromOutlineRadius + ", toContentBgRadius=" + this.toContentBgRadius + ", toProgressRadius=" + this.toProgressRadius + ", toOutlineRadius=" + this.toOutlineRadius + ")";
        }
    }

    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public BrightnessPanelAnimator(Context context, E0.a panelController, BrightnessPanelSliderDelegate sliderDelegate, E0.a windowViewController, E0.a mainPanelController) {
        super(context, mainPanelController);
        n.g(context, "context");
        n.g(panelController, "panelController");
        n.g(sliderDelegate, "sliderDelegate");
        n.g(windowViewController, "windowViewController");
        n.g(mainPanelController, "mainPanelController");
        this.context = context;
        this.panelController = panelController;
        this.sliderDelegate = sliderDelegate;
        this.windowViewController = windowViewController;
        this.mainPanelController = mainPanelController;
        this.anim = Folme.useValue(this);
        this.animConfig = new AnimConfig().addListeners(new TransitionListener() { // from class: miui.systemui.controlcenter.panel.secondary.brightness.BrightnessPanelAnimator$animConfig$1
            @Override // miuix.animation.listener.TransitionListener
            public void onUpdate(Object obj, Collection<UpdateInfo> collection) {
                boolean z2 = true;
                if (collection != null) {
                    BrightnessPanelAnimator brightnessPanelAnimator = this.this$0;
                    boolean z3 = true;
                    for (UpdateInfo updateInfo : collection) {
                        z3 = z3 && updateInfo.isCompleted;
                        if (n.c(updateInfo.property, BrightnessPanelAnimator.COLOR)) {
                            brightnessPanelAnimator.doUpdateClipHeaderCheck(updateInfo.getFloatValue());
                        }
                    }
                    z2 = z3;
                }
                if (z2) {
                    this.this$0.onAnimComplete();
                }
            }
        });
        this.scaleSlider = 0.94f;
    }

    private final void calculateViewValues() {
        SecondaryPanelAnimatorBase.ViewLocValue from;
        SliderFromView sliderFromView = this.fromView;
        if (sliderFromView == null) {
            return;
        }
        ViewGroup content = sliderFromView.getContent();
        int[] iArr = new int[2];
        CommonUtils commonUtils = CommonUtils.INSTANCE;
        commonUtils.getLocationInWindowWithoutTransform(content, iArr);
        int[] iArr2 = new int[2];
        commonUtils.getLocationInWindowWithoutTransform(getToView().getItemFrame(), iArr2);
        int[] iArr3 = new int[2];
        commonUtils.getLocationInWindowWithoutTransform(getToView().getToggleSlider(), iArr3);
        int[] iArr4 = new int[2];
        commonUtils.getLocationInWindowWithoutTransform(getToView().getTilesContent(), iArr4);
        AnimValue animValue = new AnimValue(new SecondaryPanelAnimatorBase.ViewLocValue(iArr[0], iArr[1], content.getLeft(), content.getTop(), content.getWidth(), content.getHeight()), new SecondaryPanelAnimatorBase.ViewValue(sliderFromView.getIcon().getLeft(), sliderFromView.getIcon().getTop(), sliderFromView.getIcon().getWidth(), sliderFromView.getIcon().getHeight()), new SecondaryPanelAnimatorBase.ViewLocValue(iArr2[0], iArr2[1], getToView().getItemFrame().getLeft(), getToView().getItemFrame().getTop(), getToView().getItemFrame().getWidth(), getToView().getItemFrame().getHeight()), new SecondaryPanelAnimatorBase.ViewLocValue(iArr3[0], iArr3[1], getToView().getToggleSlider().getLeft(), getToView().getToggleSlider().getTop(), getToView().getToggleSlider().getWidth(), getToView().getToggleSlider().getHeight()), new SecondaryPanelAnimatorBase.ViewLocValue(iArr4[0], iArr4[1], getToView().getTilesContent().getLeft(), getToView().getTilesContent().getTop(), getToView().getTilesContent().getWidth(), getToView().getTilesContent().getHeight()), new SecondaryPanelAnimatorBase.ViewValue(getToView().getSliderIcon().getLeft(), getToView().getSliderIcon().getTop(), getToView().getSliderIcon().getWidth(), getToView().getSliderIcon().getHeight()), sliderFromView.getProgressRadius(), sliderFromView.getOutlineRadius(), getToView().getContentBgRadius(), getToView().getProgressRadius(), getToView().getOutlineRadius());
        AnimValue animValue2 = this.lastAnimValue;
        if (animValue2 != null && animValue2 != null && (from = animValue2.getFrom()) != null && from.getLocTop() == animValue.getFrom().getLocTop() && !isOrientationChanged() && !isFoldStateChanged()) {
            this.animValue = this.lastAnimValue;
        } else {
            this.animValue = animValue;
            this.lastAnimValue = animValue;
        }
    }

    private final BrightnessPanelController getToView() {
        return (BrightnessPanelController) this.panelController.get();
    }

    @Override // miui.systemui.controlcenter.panel.secondary.SecondaryPanelAnimatorBase
    public void collapse(boolean z2) {
        ViewGroup content;
        super.collapse(z2);
        SliderFromView sliderFromView = this.fromView;
        if (sliderFromView != null && (content = sliderFromView.getContent()) != null) {
            ViewParent parent = content.getParent();
            MainPanelRecyclerView mainPanelRecyclerView = parent instanceof MainPanelRecyclerView ? (MainPanelRecyclerView) parent : null;
            if (mainPanelRecyclerView != null) {
                mainPanelRecyclerView.setTopDrawingChild(content);
            }
            if (!content.isLayoutSuppressed()) {
                calculateViewValues();
            }
        }
        Log.i(getTag(), "collapse " + z2 + " " + getCollapseWithNoAnim() + " " + this.animValue);
        this.anim.cancel();
        AnimConfig animConfig = this.animConfig;
        animConfig.setSpecial(SIZE, FolmeUtilsExtKt.getEASE_COLLAPSE_SIZE(), new float[0]);
        animConfig.setSpecial(POSITION, FolmeUtilsExtKt.getEASE_COLLAPSE_POSITION(), new float[0]);
        animConfig.setSpecial(COLOR, FolmeUtilsExtKt.getEASE_COLLAPSE_COLOR(), new float[0]);
        if (z2 && !getCollapseWithNoAnim()) {
            this.anim.to(SIZE, Float.valueOf(0.0f), POSITION, Float.valueOf(0.0f), COLOR, Float.valueOf(0.0f), this.animConfig);
            return;
        }
        this.anim.setTo(SIZE, Float.valueOf(0.0f), POSITION, Float.valueOf(0.0f), COLOR, Float.valueOf(0.0f));
        onAnimComplete();
        this.lastAnimValue = null;
    }

    @Override // miui.systemui.controlcenter.panel.secondary.SecondaryPanelAnimatorBase
    public void expand() {
        ViewGroup content;
        super.expand();
        SliderFromView sliderFromView = this.fromView;
        if (sliderFromView != null && (content = sliderFromView.getContent()) != null) {
            ViewParent parent = content.getParent();
            MainPanelRecyclerView mainPanelRecyclerView = parent instanceof MainPanelRecyclerView ? (MainPanelRecyclerView) parent : null;
            if (mainPanelRecyclerView != null) {
                mainPanelRecyclerView.setTopDrawingChild(content);
            }
            if (!content.isLayoutSuppressed()) {
                calculateViewValues();
            }
        }
        Log.i(getTag(), "expand " + this.animValue);
        SecondaryPanelAnimatorBase.doFrame$default(this, 0L, 1, null);
        AnimConfig animConfig = this.animConfig;
        animConfig.setSpecial(SIZE, FolmeUtilsExtKt.getEASE_EXPAND_SIZE(), new float[0]);
        animConfig.setSpecial(POSITION, FolmeUtilsExtKt.getEASE_EXPAND_POSITION(), new float[0]);
        animConfig.setSpecial(COLOR, FolmeUtilsExtKt.getEASE_EXPAND_COLOR(), new float[0]);
        animConfig.setSpecial(SCALE_SLIDER, FolmeUtilsExtKt.getEASE_EXPAND_POSITION(), new float[0]);
        this.anim.to(SIZE, Float.valueOf(1.0f), POSITION, Float.valueOf(1.0f), COLOR, Float.valueOf(1.0f), SCALE_SLIDER, Float.valueOf(1.0f), this.animConfig);
    }

    @Override // miui.systemui.controlcenter.panel.secondary.SecondaryPanelAnimatorBase
    public void forceUpdateClipHeader() {
        ((ControlCenterWindowViewController) this.windowViewController.get()).updateClip(isCollapsing());
    }

    @Override // miui.systemui.controlcenter.panel.secondary.SecondaryPanelAnimatorBase
    public void frameCallback() {
        AnimValue animValue;
        super.frameCallback();
        if (this.fromView == null || (animValue = this.animValue) == null) {
            return;
        }
        float width = animValue.getFrom().getWidth() + ((animValue.getToContainer().getWidth() - animValue.getFrom().getWidth()) * this.size);
        float height = animValue.getFrom().getHeight() + ((animValue.getToContainer().getHeight() - animValue.getFrom().getHeight()) * this.size);
        float fromCenterX = animValue.getFromCenterX() + ((animValue.getToCenterX() - animValue.getFromCenterX()) * this.size);
        float f2 = 2;
        float fromCenterY = (animValue.getFromCenterY() + ((animValue.getToCenterY() - animValue.getFromCenterY()) * this.size)) - (height / f2);
        float left = (fromCenterX - (width / f2)) - animValue.getToContainer().getLeft();
        float width2 = width / animValue.getToTiles().getWidth();
        float height2 = animValue.getToTiles().getHeight() * width2;
        float height3 = (height - height2) - (((animValue.getToContainer().getHeight() - animValue.getToTiles().getTop()) - animValue.getToTiles().getHeight()) * this.size);
        float width3 = animValue.getFrom().getWidth() + ((animValue.getToSlider().getWidth() - animValue.getFrom().getWidth()) * this.size);
        float height4 = animValue.getFrom().getHeight() + ((animValue.getToSlider().getHeight() - animValue.getFrom().getHeight()) * this.size);
        float locLeft = (animValue.getFrom().getLocLeft() - animValue.getToContainer().getLocLeft()) - ((animValue.getFrom().getLocLeft() - animValue.getToSlider().getLocLeft()) * this.size);
        float top = animValue.getToSlider().getTop() * this.size;
        float left2 = animValue.getFromIcon().getLeft() + ((animValue.getToIcon().getLeft() - animValue.getFromIcon().getLeft()) * this.size);
        float top2 = animValue.getFromIcon().getTop() + ((animValue.getToIcon().getTop() - animValue.getFromIcon().getTop()) * this.size);
        float width4 = animValue.getFromIcon().getWidth() + ((animValue.getToIcon().getWidth() - animValue.getFromIcon().getWidth()) * this.size);
        getToView().getItemFrame().setTop(Y0.b.b(fromCenterY));
        getToView().getItemFrame().setBottom((int) (fromCenterY + height));
        float f3 = width + left;
        getToView().getContentBg().setLeftTopRightBottom(Y0.b.b(left), 0, Y0.b.b(f3), Y0.b.b(height));
        ToggleSliderView toggleSlider = getToView().getToggleSlider();
        toggleSlider.setLeftTopRightBottom(Y0.b.b(locLeft), Y0.b.b(top), Y0.b.b(locLeft + width3), Y0.b.b(top + height4));
        if (isMainPanelCollapsing()) {
            CommonUtils.INSTANCE.setAlphaEx(toggleSlider, this.color);
        }
        toggleSlider.setScaleX(isExpanding() ? this.scaleSlider : 1.0f);
        toggleSlider.setScaleY(isExpanding() ? this.scaleSlider : 1.0f);
        BrightnessPanelSliderDelegate brightnessPanelSliderDelegate = this.sliderDelegate;
        brightnessPanelSliderDelegate.getVProgressBg().setLeftTopRightBottom(0, 0, Y0.b.b(width3), Y0.b.b(height4));
        brightnessPanelSliderDelegate.getVProgress().setLeftTopRightBottom(0, 0, Y0.b.b(width3), Y0.b.b(height4));
        brightnessPanelSliderDelegate.getVIcon().setLeftTopRightBottom(Y0.b.b(left2), Y0.b.b(top2), Y0.b.b(left2 + width4), Y0.b.b(top2 + width4));
        brightnessPanelSliderDelegate.getVToggleSliderInner().setLeftTopRightBottom(0, 0, Y0.b.b(width3), Y0.b.b(height4));
        brightnessPanelSliderDelegate.animUpdateBlendBlur(this.color);
        getToView().getTilesContent().setLeftTopRightBottom(Y0.b.b(left), Y0.b.b(height3), Y0.b.b(f3), Y0.b.b(height3 + height2));
        float f4 = isExpanding() || isExpanded() ? this.color : (this.size - 0.2f) * 1.25f;
        LinearLayout tilesContainer = getToView().getTilesContainer();
        tilesContainer.setPivotX(0.0f);
        tilesContainer.setPivotY(0.0f);
        CommonUtils commonUtils = CommonUtils.INSTANCE;
        commonUtils.setScaleXEx(tilesContainer, width2);
        commonUtils.setScaleYEx(tilesContainer, width2);
        commonUtils.setAlphaEx(tilesContainer, f4);
        getToView().setBlurBgRatio(this.color);
        commonUtils.setAlphaEx(getToView().getContentBg(), f4);
        getToView().setContentBgRadius(animValue.getFromOutlineRadius() + ((animValue.getToContentBgRadius() - animValue.getFromOutlineRadius()) * this.size));
        getToView().setProgressRadius(animValue.getFromProgressRadius() + ((animValue.getToProgressRadius() - animValue.getFromProgressRadius()) * this.size));
        getToView().setOutlineRadius(animValue.getFromOutlineRadius() + ((animValue.getToOutlineRadius() - animValue.getFromOutlineRadius()) * this.size));
    }

    @Override // miui.systemui.controlcenter.panel.secondary.SecondaryPanelAnimatorBase
    public String getAnimStateString() {
        return "BrightnessPanelAnimator(size=" + this.size + ", color=" + this.color + ", position=" + this.position + ")";
    }

    @Override // miui.systemui.controlcenter.panel.secondary.SecondaryPanelAnimatorBase
    public void onAnimComplete() {
        super.onAnimComplete();
        SecondaryPanelAnimatorBase.doFrame$default(this, 0L, 1, null);
        ((BrightnessPanelController) this.panelController.get()).onAnimComplete();
        this.animValue = null;
    }

    @Override // miui.systemui.controlcenter.panel.secondary.SecondaryPanelAnimatorBase
    public void prepareExpand(BrightnessPanelParams brightnessPanelParams) {
        super.prepareExpand(brightnessPanelParams);
        this.fromView = brightnessPanelParams != null ? brightnessPanelParams.getFromView() : null;
        ToggleSliderView toggleSlider = getToView().getToggleSlider();
        CommonUtils.INSTANCE.setAlphaEx(toggleSlider, 1.0f);
        toggleSlider.setScaleX(0.94f);
        toggleSlider.setScaleY(0.94f);
        this.animValue = null;
        this.lastAnimValue = null;
        this.size = 0.0f;
        this.position = 0.0f;
        this.color = 0.0f;
        this.scaleSlider = 0.94f;
    }
}
