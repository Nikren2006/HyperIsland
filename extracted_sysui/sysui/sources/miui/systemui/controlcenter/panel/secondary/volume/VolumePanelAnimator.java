package miui.systemui.controlcenter.panel.secondary.volume;

import I0.m;
import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.os.SystemClock;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import com.android.systemui.miui.volume.VolumeColumn;
import com.android.systemui.miui.volume.widget.ExpandCollapseLinearLayout;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.n;
import miui.systemui.animation.FolmeUtilsExtKt;
import miui.systemui.controlcenter.R;
import miui.systemui.controlcenter.dagger.ControlCenterScope;
import miui.systemui.controlcenter.events.ControlCenterScenarioTracker;
import miui.systemui.controlcenter.panel.secondary.SecondaryPanelAnimatorBase;
import miui.systemui.controlcenter.panel.secondary.SliderFromView;
import miui.systemui.controlcenter.panel.secondary.VolumePanelParams;
import miui.systemui.controlcenter.utils.ControlCenterUtils;
import miui.systemui.controlcenter.widget.MainPanelRecyclerView;
import miui.systemui.controlcenter.windowview.ControlCenterWindowViewController;
import miui.systemui.controls.ColorUtils;
import miui.systemui.util.CommonUtils;
import miui.systemui.util.MiBlurCompat;
import miui.systemui.util.MiuiColorBlendToken;
import miuix.animation.Folme;
import miuix.animation.IStateStyle;
import miuix.animation.base.AnimConfig;
import miuix.animation.listener.TransitionListener;
import miuix.animation.listener.UpdateInfo;
import miuix.animation.property.FloatProperty;
import systemui.plugin.eventtracking.trackers.VolumeEventTracker;

/* JADX INFO: loaded from: classes.dex */
@ControlCenterScope
public final class VolumePanelAnimator extends SecondaryPanelAnimatorBase<VolumePanelParams> {
    private final IStateStyle anim;
    private final AnimConfig animConfig;
    private final List<String> animConfigTagList;
    private AnimValue animValue;
    private float color;
    private final E0.a contentController;
    private final Context context;
    private SliderFromView fromView;
    private AnimValue lastAnimValue;
    private final E0.a mainPanelController;
    private float position;
    private float scaleSlider;
    private float size;
    private final VolumePanelAnimator$transitionListener$1 transitionListener;
    private List<VolumeColumn> volumeColumnList;
    private final E0.a volumePanelController;
    private FloatProperty<VolumePanelAnimator>[] volumesPropertyX;
    private Float[] volumesSizeX;
    private final E0.a windowViewController;
    public static final Companion Companion = new Companion(null);
    private static VolumePanelAnimator$Companion$SIZE$1 SIZE = new FloatProperty<VolumePanelAnimator>() { // from class: miui.systemui.controlcenter.panel.secondary.volume.VolumePanelAnimator$Companion$SIZE$1
        @Override // miuix.animation.property.FloatProperty
        public float getValue(VolumePanelAnimator anim) {
            n.g(anim, "anim");
            return anim.size;
        }

        @Override // miuix.animation.property.FloatProperty
        public void setValue(VolumePanelAnimator anim, float f2) {
            n.g(anim, "anim");
            if (anim.size == f2) {
                return;
            }
            anim.size = f2;
            anim.scheduleUpdate();
        }
    };
    private static VolumePanelAnimator$Companion$COLOR$1 COLOR = new FloatProperty<VolumePanelAnimator>() { // from class: miui.systemui.controlcenter.panel.secondary.volume.VolumePanelAnimator$Companion$COLOR$1
        @Override // miuix.animation.property.FloatProperty
        public float getValue(VolumePanelAnimator anim) {
            n.g(anim, "anim");
            return anim.color;
        }

        @Override // miuix.animation.property.FloatProperty
        public void setValue(VolumePanelAnimator anim, float f2) {
            n.g(anim, "anim");
            if (anim.color == f2) {
                return;
            }
            anim.color = f2;
            anim.scheduleUpdate();
        }
    };
    private static VolumePanelAnimator$Companion$SCALE_SLIDER$1 SCALE_SLIDER = new FloatProperty<VolumePanelAnimator>() { // from class: miui.systemui.controlcenter.panel.secondary.volume.VolumePanelAnimator$Companion$SCALE_SLIDER$1
        @Override // miuix.animation.property.FloatProperty
        public float getValue(VolumePanelAnimator anim) {
            n.g(anim, "anim");
            return anim.scaleSlider;
        }

        @Override // miuix.animation.property.FloatProperty
        public void setValue(VolumePanelAnimator anim, float f2) {
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
        private final SecondaryPanelAnimatorBase.ViewValue fromTopText;
        private final SecondaryPanelAnimatorBase.ViewLocValue ringMode;
        private final float toCenterX;
        private final float toCenterY;
        private final SecondaryPanelAnimatorBase.ViewLocValue toContent;
        private final SecondaryPanelAnimatorBase.ViewLocValue toContentBg;
        private final SecondaryPanelAnimatorBase.ViewLocValue toContentColumns;
        private final SecondaryPanelAnimatorBase.ViewLocValue toInnerContent;
        private final float toOutlineRadius;
        private final float toProgressRadius;
        private final SecondaryPanelAnimatorBase.ViewValue[] toVolumeIcons;
        private final SecondaryPanelAnimatorBase.ViewValue toVolumeSuper;
        private final SecondaryPanelAnimatorBase.ViewLocValue[] toVolumes;

        public AnimValue(SecondaryPanelAnimatorBase.ViewLocValue from, SecondaryPanelAnimatorBase.ViewValue fromIcon, SecondaryPanelAnimatorBase.ViewValue fromTopText, SecondaryPanelAnimatorBase.ViewLocValue toContent, SecondaryPanelAnimatorBase.ViewLocValue toContentBg, SecondaryPanelAnimatorBase.ViewLocValue viewLocValue, SecondaryPanelAnimatorBase.ViewLocValue viewLocValue2, SecondaryPanelAnimatorBase.ViewLocValue[] viewLocValueArr, SecondaryPanelAnimatorBase.ViewValue[] viewValueArr, SecondaryPanelAnimatorBase.ViewValue viewValue, SecondaryPanelAnimatorBase.ViewLocValue viewLocValue3, float f2, float f3, float f4, float f5) {
            n.g(from, "from");
            n.g(fromIcon, "fromIcon");
            n.g(fromTopText, "fromTopText");
            n.g(toContent, "toContent");
            n.g(toContentBg, "toContentBg");
            this.from = from;
            this.fromIcon = fromIcon;
            this.fromTopText = fromTopText;
            this.toContent = toContent;
            this.toContentBg = toContentBg;
            this.toInnerContent = viewLocValue;
            this.toContentColumns = viewLocValue2;
            this.toVolumes = viewLocValueArr;
            this.toVolumeIcons = viewValueArr;
            this.toVolumeSuper = viewValue;
            this.ringMode = viewLocValue3;
            this.fromProgressRadius = f2;
            this.fromOutlineRadius = f3;
            this.toProgressRadius = f4;
            this.toOutlineRadius = f5;
            float f6 = 2;
            this.fromCenterX = from.getLocLeft() + (from.getWidth() / f6);
            this.fromCenterY = from.getLocTop() + (from.getHeight() / f6);
            this.toCenterX = toContentBg.getLocLeft() + (toContentBg.getWidth() / f6);
            this.toCenterY = toContentBg.getLocTop() + (toContentBg.getHeight() / f6);
        }

        public final SecondaryPanelAnimatorBase.ViewLocValue component1() {
            return this.from;
        }

        public final SecondaryPanelAnimatorBase.ViewValue component10() {
            return this.toVolumeSuper;
        }

        public final SecondaryPanelAnimatorBase.ViewLocValue component11() {
            return this.ringMode;
        }

        public final float component12() {
            return this.fromProgressRadius;
        }

        public final float component13() {
            return this.fromOutlineRadius;
        }

        public final float component14() {
            return this.toProgressRadius;
        }

        public final float component15() {
            return this.toOutlineRadius;
        }

        public final SecondaryPanelAnimatorBase.ViewValue component2() {
            return this.fromIcon;
        }

        public final SecondaryPanelAnimatorBase.ViewValue component3() {
            return this.fromTopText;
        }

        public final SecondaryPanelAnimatorBase.ViewLocValue component4() {
            return this.toContent;
        }

        public final SecondaryPanelAnimatorBase.ViewLocValue component5() {
            return this.toContentBg;
        }

        public final SecondaryPanelAnimatorBase.ViewLocValue component6() {
            return this.toInnerContent;
        }

        public final SecondaryPanelAnimatorBase.ViewLocValue component7() {
            return this.toContentColumns;
        }

        public final SecondaryPanelAnimatorBase.ViewLocValue[] component8() {
            return this.toVolumes;
        }

        public final SecondaryPanelAnimatorBase.ViewValue[] component9() {
            return this.toVolumeIcons;
        }

        public final AnimValue copy(SecondaryPanelAnimatorBase.ViewLocValue from, SecondaryPanelAnimatorBase.ViewValue fromIcon, SecondaryPanelAnimatorBase.ViewValue fromTopText, SecondaryPanelAnimatorBase.ViewLocValue toContent, SecondaryPanelAnimatorBase.ViewLocValue toContentBg, SecondaryPanelAnimatorBase.ViewLocValue viewLocValue, SecondaryPanelAnimatorBase.ViewLocValue viewLocValue2, SecondaryPanelAnimatorBase.ViewLocValue[] viewLocValueArr, SecondaryPanelAnimatorBase.ViewValue[] viewValueArr, SecondaryPanelAnimatorBase.ViewValue viewValue, SecondaryPanelAnimatorBase.ViewLocValue viewLocValue3, float f2, float f3, float f4, float f5) {
            n.g(from, "from");
            n.g(fromIcon, "fromIcon");
            n.g(fromTopText, "fromTopText");
            n.g(toContent, "toContent");
            n.g(toContentBg, "toContentBg");
            return new AnimValue(from, fromIcon, fromTopText, toContent, toContentBg, viewLocValue, viewLocValue2, viewLocValueArr, viewValueArr, viewValue, viewLocValue3, f2, f3, f4, f5);
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof AnimValue)) {
                return false;
            }
            AnimValue animValue = (AnimValue) obj;
            return n.c(this.from, animValue.from) && n.c(this.fromIcon, animValue.fromIcon) && n.c(this.fromTopText, animValue.fromTopText) && n.c(this.toContent, animValue.toContent) && n.c(this.toContentBg, animValue.toContentBg) && n.c(this.toInnerContent, animValue.toInnerContent) && n.c(this.toContentColumns, animValue.toContentColumns) && n.c(this.toVolumes, animValue.toVolumes) && n.c(this.toVolumeIcons, animValue.toVolumeIcons) && n.c(this.toVolumeSuper, animValue.toVolumeSuper) && n.c(this.ringMode, animValue.ringMode) && Float.compare(this.fromProgressRadius, animValue.fromProgressRadius) == 0 && Float.compare(this.fromOutlineRadius, animValue.fromOutlineRadius) == 0 && Float.compare(this.toProgressRadius, animValue.toProgressRadius) == 0 && Float.compare(this.toOutlineRadius, animValue.toOutlineRadius) == 0;
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

        public final SecondaryPanelAnimatorBase.ViewValue getFromTopText() {
            return this.fromTopText;
        }

        public final SecondaryPanelAnimatorBase.ViewLocValue getRingMode() {
            return this.ringMode;
        }

        public final float getToCenterX() {
            return this.toCenterX;
        }

        public final float getToCenterY() {
            return this.toCenterY;
        }

        public final SecondaryPanelAnimatorBase.ViewLocValue getToContent() {
            return this.toContent;
        }

        public final SecondaryPanelAnimatorBase.ViewLocValue getToContentBg() {
            return this.toContentBg;
        }

        public final SecondaryPanelAnimatorBase.ViewLocValue getToContentColumns() {
            return this.toContentColumns;
        }

        public final SecondaryPanelAnimatorBase.ViewLocValue getToInnerContent() {
            return this.toInnerContent;
        }

        public final float getToOutlineRadius() {
            return this.toOutlineRadius;
        }

        public final float getToProgressRadius() {
            return this.toProgressRadius;
        }

        public final SecondaryPanelAnimatorBase.ViewValue[] getToVolumeIcons() {
            return this.toVolumeIcons;
        }

        public final SecondaryPanelAnimatorBase.ViewValue getToVolumeSuper() {
            return this.toVolumeSuper;
        }

        public final SecondaryPanelAnimatorBase.ViewLocValue[] getToVolumes() {
            return this.toVolumes;
        }

        public int hashCode() {
            int iHashCode = ((((((((this.from.hashCode() * 31) + this.fromIcon.hashCode()) * 31) + this.fromTopText.hashCode()) * 31) + this.toContent.hashCode()) * 31) + this.toContentBg.hashCode()) * 31;
            SecondaryPanelAnimatorBase.ViewLocValue viewLocValue = this.toInnerContent;
            int iHashCode2 = (iHashCode + (viewLocValue == null ? 0 : viewLocValue.hashCode())) * 31;
            SecondaryPanelAnimatorBase.ViewLocValue viewLocValue2 = this.toContentColumns;
            int iHashCode3 = (iHashCode2 + (viewLocValue2 == null ? 0 : viewLocValue2.hashCode())) * 31;
            SecondaryPanelAnimatorBase.ViewLocValue[] viewLocValueArr = this.toVolumes;
            int iHashCode4 = (iHashCode3 + (viewLocValueArr == null ? 0 : Arrays.hashCode(viewLocValueArr))) * 31;
            SecondaryPanelAnimatorBase.ViewValue[] viewValueArr = this.toVolumeIcons;
            int iHashCode5 = (iHashCode4 + (viewValueArr == null ? 0 : Arrays.hashCode(viewValueArr))) * 31;
            SecondaryPanelAnimatorBase.ViewValue viewValue = this.toVolumeSuper;
            int iHashCode6 = (iHashCode5 + (viewValue == null ? 0 : viewValue.hashCode())) * 31;
            SecondaryPanelAnimatorBase.ViewLocValue viewLocValue3 = this.ringMode;
            return ((((((((iHashCode6 + (viewLocValue3 != null ? viewLocValue3.hashCode() : 0)) * 31) + Float.hashCode(this.fromProgressRadius)) * 31) + Float.hashCode(this.fromOutlineRadius)) * 31) + Float.hashCode(this.toProgressRadius)) * 31) + Float.hashCode(this.toOutlineRadius);
        }

        public String toString() {
            return "AnimValue(from=" + this.from + ", fromIcon=" + this.fromIcon + ", fromTopText=" + this.fromTopText + ", toContent=" + this.toContent + ", toContentBg=" + this.toContentBg + ", toInnerContent=" + this.toInnerContent + ", toContentColumns=" + this.toContentColumns + ", toVolumes=" + Arrays.toString(this.toVolumes) + ", toVolumeIcons=" + Arrays.toString(this.toVolumeIcons) + ", toVolumeSuper=" + this.toVolumeSuper + ", ringMode=" + this.ringMode + ", fromProgressRadius=" + this.fromProgressRadius + ", fromOutlineRadius=" + this.fromOutlineRadius + ", toProgressRadius=" + this.toProgressRadius + ", toOutlineRadius=" + this.toOutlineRadius + ")";
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
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r2v4, types: [miui.systemui.controlcenter.panel.secondary.volume.VolumePanelAnimator$transitionListener$1] */
    public VolumePanelAnimator(Context context, E0.a volumePanelController, E0.a contentController, E0.a windowViewController, E0.a mainPanelController) {
        super(context, mainPanelController);
        n.g(context, "context");
        n.g(volumePanelController, "volumePanelController");
        n.g(contentController, "contentController");
        n.g(windowViewController, "windowViewController");
        n.g(mainPanelController, "mainPanelController");
        this.context = context;
        this.volumePanelController = volumePanelController;
        this.contentController = contentController;
        this.windowViewController = windowViewController;
        this.mainPanelController = mainPanelController;
        this.anim = Folme.useValue(this);
        this.animConfigTagList = new ArrayList();
        ?? r2 = new TransitionListener() { // from class: miui.systemui.controlcenter.panel.secondary.volume.VolumePanelAnimator$transitionListener$1
            @Override // miuix.animation.listener.TransitionListener
            public void onBegin(Object obj) {
                super.onBegin(obj);
                this.this$0.cancelTouchEvent();
                VolumePanelDelegate volumePanelDelegate = (VolumePanelDelegate) this.this$0.contentController.get();
                View content = volumePanelDelegate != null ? volumePanelDelegate.getContent() : null;
                ExpandCollapseLinearLayout expandCollapseLinearLayout = content instanceof ExpandCollapseLinearLayout ? (ExpandCollapseLinearLayout) content : null;
                if (expandCollapseLinearLayout != null) {
                    expandCollapseLinearLayout.setInterceptTouchEvent(true);
                }
            }

            @Override // miuix.animation.listener.TransitionListener
            public void onUpdate(Object obj, Collection<UpdateInfo> collection) {
                if (collection != null) {
                    VolumePanelAnimator volumePanelAnimator = this.this$0;
                    for (UpdateInfo updateInfo : collection) {
                        String name = updateInfo.property.getName();
                        if (updateInfo.isCompleted) {
                            if (volumePanelAnimator.animConfigTagList.contains(name)) {
                                volumePanelAnimator.animConfigTagList.remove(name);
                            }
                            if (volumePanelAnimator.animConfigTagList.isEmpty()) {
                                volumePanelAnimator.onAnimComplete();
                            }
                        } else if (n.c(updateInfo.property, VolumePanelAnimator.COLOR)) {
                            volumePanelAnimator.doUpdateClipHeaderCheck(updateInfo.getFloatValue());
                        }
                    }
                }
            }
        };
        this.transitionListener = r2;
        this.animConfig = new AnimConfig().addListeners(r2);
        this.scaleSlider = 0.94f;
    }

    private final void animUpdateProgressColor(Context context, float f2, View view, View view2, boolean z2) {
        if (ControlCenterUtils.getBackgroundBlurOpenedInDefaultTheme(context)) {
            MiuiColorBlendToken miuiColorBlendToken = MiuiColorBlendToken.INSTANCE;
            MiBlurCompat.setMiBackgroundBlendColors(view2, miuiColorBlendToken.getVC_SLIDER_BG_CC_MAIN(), miuiColorBlendToken.getVC_SLIDER_BG_CC(), f2, true);
            MiBlurCompat.setMiBackgroundBlendColors(view, z2 ? miuiColorBlendToken.getVC_PROGRESS_MUTE_CC_MAIN() : miuiColorBlendToken.getVC_PROGRESS_CC_MAIN(), z2 ? miuiColorBlendToken.getVC_PROGRESS_MUTE_CC() : miuiColorBlendToken.getVC_PROGRESS_CC(), f2, true);
            return;
        }
        Drawable background = view2.getBackground();
        GradientDrawable gradientDrawable = background instanceof GradientDrawable ? (GradientDrawable) background : null;
        if (gradientDrawable != null) {
            gradientDrawable.setColor(ColorUtils.INSTANCE.blendARGB(context.getResources().getColor(R.color.toggle_slider_progress_background_color), context.getResources().getColor(R.color.miui_volume_seekbar_bg_color_cc), f2));
        }
        Drawable background2 = view2.getBackground();
        GradientDrawable gradientDrawable2 = background2 instanceof GradientDrawable ? (GradientDrawable) background2 : null;
        if (gradientDrawable2 != null) {
            gradientDrawable2.setCornerRadius(0.0f);
        }
        SeekBar seekBar = view2 instanceof SeekBar ? (SeekBar) view2 : null;
        if (seekBar == null) {
            return;
        }
        seekBar.setProgressTintList(ColorStateList.valueOf(ColorUtils.INSTANCE.blendARGB(z2 ? context.getResources().getColor(R.color.toggle_slider_progress_disabled_color) : context.getResources().getColor(R.color.toggle_slider_progress_color), z2 ? context.getResources().getColor(R.color.miui_volume_disabled_color_cc) : context.getResources().getColor(R.color.miui_volume_color_accent), f2)));
    }

    private final void calculateViewValues() {
        SecondaryPanelAnimatorBase.ViewLocValue viewLocValue;
        SecondaryPanelAnimatorBase.ViewLocValue viewLocValue2;
        List<VolumeColumn> visibleColumns;
        SecondaryPanelAnimatorBase.ViewLocValue[] viewLocValueArr;
        SecondaryPanelAnimatorBase.ViewValue[] viewValueArr;
        SecondaryPanelAnimatorBase.ViewValue viewValue;
        SecondaryPanelAnimatorBase.ViewLocValue viewLocValue3;
        SecondaryPanelAnimatorBase.ViewLocValue from;
        SecondaryPanelAnimatorBase.ViewValue toVolumeSuper;
        SliderFromView sliderFromView = this.fromView;
        if (sliderFromView == null) {
            return;
        }
        ViewGroup content = sliderFromView.getContent();
        int i2 = 2;
        int[] iArr = new int[2];
        CommonUtils commonUtils = CommonUtils.INSTANCE;
        commonUtils.getLocationInWindowWithoutTransform(content, iArr);
        SecondaryPanelAnimatorBase.ViewLocValue viewLocValue4 = new SecondaryPanelAnimatorBase.ViewLocValue(iArr[0], iArr[1], content.getLeft(), content.getTop(), content.getWidth(), content.getHeight());
        SecondaryPanelAnimatorBase.ViewValue viewValue2 = new SecondaryPanelAnimatorBase.ViewValue(sliderFromView.getIcon().getLeft(), sliderFromView.getIcon().getTop(), sliderFromView.getIcon().getWidth(), sliderFromView.getIcon().getHeight());
        SecondaryPanelAnimatorBase.ViewValue viewValue3 = new SecondaryPanelAnimatorBase.ViewValue(sliderFromView.getTopText().getLeft(), sliderFromView.getTopText().getTop(), sliderFromView.getTopText().getWidth(), sliderFromView.getTopText().getHeight());
        View content2 = ((VolumePanelDelegate) this.contentController.get()).getContent();
        if (content2 == null) {
            return;
        }
        int[] iArr2 = new int[2];
        commonUtils.getLocationInWindowWithoutTransform(content2, iArr2);
        SecondaryPanelAnimatorBase.ViewLocValue viewLocValue5 = new SecondaryPanelAnimatorBase.ViewLocValue(iArr2[0], iArr2[1], content2.getLeft(), content2.getTop(), content2.getWidth(), content2.getHeight());
        View contentBg = ((VolumePanelDelegate) this.contentController.get()).getContentBg();
        if (contentBg == null) {
            return;
        }
        int[] iArr3 = new int[2];
        commonUtils.getLocationInWindowWithoutTransform(contentBg, iArr3);
        SecondaryPanelAnimatorBase.ViewLocValue viewLocValue6 = new SecondaryPanelAnimatorBase.ViewLocValue(iArr3[0], iArr3[1], contentBg.getLeft(), contentBg.getTop(), contentBg.getWidth(), contentBg.getHeight());
        View innerContent = ((VolumePanelDelegate) this.contentController.get()).getInnerContent();
        if (innerContent != null) {
            int[] iArr4 = new int[2];
            commonUtils.getLocationInWindowWithoutTransform(innerContent, iArr4);
            viewLocValue = new SecondaryPanelAnimatorBase.ViewLocValue(iArr4[0], iArr4[1], innerContent.getLeft(), innerContent.getTop(), innerContent.getWidth(), innerContent.getHeight());
        } else {
            viewLocValue = null;
        }
        View contentColumns = ((VolumePanelDelegate) this.contentController.get()).getContentColumns();
        if (contentColumns != null) {
            int[] iArr5 = new int[2];
            commonUtils.getLocationInWindowWithoutTransform(contentColumns, iArr5);
            viewLocValue2 = new SecondaryPanelAnimatorBase.ViewLocValue(iArr5[0], iArr5[1], contentColumns.getLeft(), contentColumns.getTop(), contentColumns.getWidth(), contentColumns.getHeight());
        } else {
            viewLocValue2 = null;
        }
        VolumePanelDelegate volumePanelDelegate = (VolumePanelDelegate) this.contentController.get();
        if (volumePanelDelegate == null || (visibleColumns = volumePanelDelegate.getVisibleColumns()) == null) {
            visibleColumns = null;
            viewLocValueArr = null;
            viewValueArr = null;
            viewValue = null;
        } else {
            if (!visibleColumns.isEmpty()) {
                commonUtils.setAlphaEx(visibleColumns.get(0).getView(), 1.0f);
            }
            FloatProperty<VolumePanelAnimator>[] floatPropertyArr = this.volumesPropertyX;
            if (floatPropertyArr == null || floatPropertyArr == null || floatPropertyArr.length != visibleColumns.size()) {
                this.volumesSizeX = new Float[visibleColumns.size()];
                this.volumesPropertyX = new FloatProperty[visibleColumns.size()];
                final int i3 = 0;
                for (Object obj : visibleColumns) {
                    int i4 = i3 + 1;
                    if (i3 < 0) {
                        m.n();
                    }
                    FloatProperty<VolumePanelAnimator>[] floatPropertyArr2 = this.volumesPropertyX;
                    n.d(floatPropertyArr2);
                    final String str = "sizeBgX_" + i3;
                    floatPropertyArr2[i3] = new FloatProperty<VolumePanelAnimator>(str) { // from class: miui.systemui.controlcenter.panel.secondary.volume.VolumePanelAnimator$calculateViewValues$1$1$1
                        @Override // miuix.animation.property.FloatProperty
                        public float getValue(VolumePanelAnimator anim) {
                            Float f2;
                            n.g(anim, "anim");
                            Float[] fArr = anim.volumesSizeX;
                            if (fArr != null) {
                                int i5 = i3;
                                if (fArr.length <= i5) {
                                    fArr = null;
                                }
                                if (fArr != null && (f2 = fArr[i5]) != null) {
                                    return f2.floatValue();
                                }
                            }
                            return 0.0f;
                        }

                        @Override // miuix.animation.property.FloatProperty
                        public void setValue(VolumePanelAnimator anim, float f2) {
                            n.g(anim, "anim");
                            Float[] fArr = anim.volumesSizeX;
                            if (fArr != null) {
                                int i5 = i3;
                                if (fArr.length <= i5 || n.b(fArr[i5], f2)) {
                                    fArr = null;
                                }
                                if (fArr != null) {
                                    fArr[i3] = Float.valueOf(f2);
                                    anim.scheduleUpdate();
                                }
                            }
                        }
                    };
                    i3 = i4;
                }
            }
            SecondaryPanelAnimatorBase.ViewLocValue[] viewLocValueArr2 = new SecondaryPanelAnimatorBase.ViewLocValue[visibleColumns.size()];
            SecondaryPanelAnimatorBase.ViewValue[] viewValueArr2 = new SecondaryPanelAnimatorBase.ViewValue[visibleColumns.size()];
            Iterator it = visibleColumns.iterator();
            SecondaryPanelAnimatorBase.ViewValue viewValue4 = null;
            int i5 = 0;
            while (it.hasNext()) {
                Object next = it.next();
                int i6 = i5 + 1;
                if (i5 < 0) {
                    m.n();
                }
                VolumeColumn volumeColumn = (VolumeColumn) next;
                List<VolumeColumn> list = visibleColumns;
                View view = volumeColumn.getView();
                Iterator it2 = it;
                int[] iArr6 = new int[i2];
                view.getLocationOnScreen(iArr6);
                viewLocValueArr2[i5] = new SecondaryPanelAnimatorBase.ViewLocValue(iArr6[0], iArr6[1], view.getLeft(), view.getTop(), view.getWidth(), view.getHeight());
                ImageView icon = volumeColumn.getIcon();
                SecondaryPanelAnimatorBase.ViewLocValue[] viewLocValueArr3 = viewLocValueArr2;
                SecondaryPanelAnimatorBase.ViewValue viewValue5 = viewValue4;
                viewValueArr2[i5] = new SecondaryPanelAnimatorBase.ViewValue(icon.getLeft(), icon.getTop(), icon.getWidth(), icon.getHeight());
                if (i5 == 0) {
                    TextView superVolume = volumeColumn.getSuperVolume();
                    viewValue4 = new SecondaryPanelAnimatorBase.ViewValue(superVolume.getLeft(), superVolume.getTop(), superVolume.getWidth(), superVolume.getHeight());
                } else {
                    viewValue4 = viewValue5;
                }
                i5 = i6;
                visibleColumns = list;
                it = it2;
                viewLocValueArr2 = viewLocValueArr3;
                i2 = 2;
            }
            SecondaryPanelAnimatorBase.ViewValue viewValue6 = viewValue4;
            viewValueArr = viewValueArr2;
            viewLocValueArr = viewLocValueArr2;
            viewValue = viewValue6;
        }
        this.volumeColumnList = visibleColumns;
        View ringModeLayout = ((VolumePanelDelegate) this.contentController.get()).getRingModeLayout();
        if (ringModeLayout != null) {
            int[] iArr7 = new int[2];
            CommonUtils.INSTANCE.getLocationInWindowWithoutTransform(ringModeLayout, iArr7);
            viewLocValue3 = new SecondaryPanelAnimatorBase.ViewLocValue(iArr7[0], iArr7[1], ringModeLayout.getLeft(), ringModeLayout.getTop(), ringModeLayout.getWidth(), ringModeLayout.getHeight());
        } else {
            viewLocValue3 = null;
        }
        AnimValue animValue = new AnimValue(viewLocValue4, viewValue2, viewValue3, viewLocValue5, viewLocValue6, viewLocValue, viewLocValue2, viewLocValueArr, viewValueArr, viewValue, viewLocValue3, sliderFromView.getProgressRadius(), sliderFromView.getOutlineRadius(), getToView().getProgressRadius(), getToView().getCornerRadius());
        Log.i(getTag(), "calculateViewValues av=" + animValue);
        AnimValue animValue2 = this.lastAnimValue;
        if (animValue2 != null && animValue2 != null && (from = animValue2.getFrom()) != null && from.getLocTop() == animValue.getFrom().getLocTop()) {
            AnimValue animValue3 = this.lastAnimValue;
            Integer numValueOf = (animValue3 == null || (toVolumeSuper = animValue3.getToVolumeSuper()) == null) ? null : Integer.valueOf(toVolumeSuper.getWidth());
            SecondaryPanelAnimatorBase.ViewValue toVolumeSuper2 = animValue.getToVolumeSuper();
            if (n.c(numValueOf, toVolumeSuper2 != null ? Integer.valueOf(toVolumeSuper2.getWidth()) : null) && !isOrientationChanged() && !isFoldStateChanged()) {
                this.animValue = this.lastAnimValue;
                return;
            }
        }
        this.animValue = animValue;
        this.lastAnimValue = animValue;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void cancelTouchEvent() {
        View content;
        long jUptimeMillis = SystemClock.uptimeMillis();
        MotionEvent motionEventObtain = MotionEvent.obtain(jUptimeMillis, jUptimeMillis, 3, 0.0f, 0.0f, 0);
        VolumePanelDelegate volumePanelDelegate = (VolumePanelDelegate) this.contentController.get();
        if (volumePanelDelegate != null && (content = volumePanelDelegate.getContent()) != null) {
            content.dispatchTouchEvent(motionEventObtain);
        }
        motionEventObtain.recycle();
    }

    private final VolumePanelController getToView() {
        return (VolumePanelController) this.volumePanelController.get();
    }

    private final float getVolumeAnimNode(boolean z2, int i2) {
        List<VolumeColumn> list = this.volumeColumnList;
        int size = list != null ? list.size() : 0;
        if (!z2) {
            if (i2 == 0) {
                return 0.0f;
            }
            return 0.5f - (((size - 1) - i2) * 0.1f);
        }
        if (i2 == 1) {
            return 0.3f;
        }
        if (i2 == 2) {
            return 0.5f;
        }
        if (i2 != 3) {
            return 0.6f + ((i2 - 3) * 0.1f);
        }
        return 0.6f;
    }

    private final void onAnimCompleteTrack() {
        List<VolumeColumn> list;
        VolumeColumn volumeColumn;
        View view;
        if (!isExpanded() || (list = this.volumeColumnList) == null || (volumeColumn = list.get(0)) == null || (view = volumeColumn.getView()) == null) {
            return;
        }
        int[] iArr = new int[2];
        view.getLocationOnScreen(iArr);
        View contentBg = ((VolumePanelDelegate) this.contentController.get()).getContentBg();
        if (contentBg == null) {
            return;
        }
        int[] iArr2 = new int[2];
        CommonUtils commonUtils = CommonUtils.INSTANCE;
        commonUtils.getLocationInWindowWithoutTransform(contentBg, iArr2);
        View ringModeLayout = ((VolumePanelDelegate) this.contentController.get()).getRingModeLayout();
        if (ringModeLayout == null) {
            return;
        }
        int[] iArr3 = new int[2];
        commonUtils.getLocationInWindowWithoutTransform(ringModeLayout, iArr3);
        int i2 = iArr[0];
        if (i2 < iArr2[0] || iArr[1] < iArr2[1] || i2 + view.getWidth() > iArr2[0] + contentBg.getWidth() || iArr[1] + view.getHeight() > iArr3[1]) {
            VolumeEventTracker.trackError(VolumeEventTracker.ERROR_022001, 2);
        }
    }

    private final void setVolumeContainerTranslationZ(boolean z2) {
        View innerContent;
        VolumePanelDelegate volumePanelDelegate = (VolumePanelDelegate) this.contentController.get();
        if (volumePanelDelegate == null || (innerContent = volumePanelDelegate.getInnerContent()) == null) {
            return;
        }
        Object parent = innerContent.getParent();
        View view = parent instanceof View ? (View) parent : null;
        if (view == null) {
            return;
        }
        view.setTranslationZ(z2 ? 1.0f : 0.0f);
    }

    public static /* synthetic */ void setVolumeContainerTranslationZ$default(VolumePanelAnimator volumePanelAnimator, boolean z2, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            z2 = true;
        }
        volumePanelAnimator.setVolumeContainerTranslationZ(z2);
    }

    @Override // miui.systemui.controlcenter.panel.secondary.SecondaryPanelAnimatorBase
    public void collapse(boolean z2) {
        FloatProperty<VolumePanelAnimator> floatProperty;
        FloatProperty<VolumePanelAnimator> floatProperty2;
        ViewGroup content;
        super.collapse(z2);
        List<VolumeColumn> list = this.volumeColumnList;
        if (list != null) {
            Iterator<T> it = list.iterator();
            while (it.hasNext()) {
                ((VolumeColumn) it.next()).setInCCMainPage(true);
            }
        }
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
        setVolumeContainerTranslationZ$default(this, false, 1, null);
        this.anim.cancel();
        AnimConfig animConfig = this.animConfig;
        animConfig.setSpecial(SIZE, FolmeUtilsExtKt.getEASE_COLLAPSE_SIZE(), new float[0]);
        animConfig.setSpecial(COLOR, FolmeUtilsExtKt.getEASE_COLLAPSE_COLOR(), new float[0]);
        animConfig.setSpecial(SCALE_SLIDER, FolmeUtilsExtKt.getEASE_COLLAPSE_POSITION(), new float[0]);
        if (!z2 || getCollapseWithNoAnim()) {
            this.anim.setTo(SIZE, Float.valueOf(0.0f), COLOR, Float.valueOf(0.0f), SCALE_SLIDER, 0);
            List<VolumeColumn> list2 = this.volumeColumnList;
            if (list2 != null) {
                int i2 = 0;
                for (Object obj : list2) {
                    int i3 = i2 + 1;
                    if (i2 < 0) {
                        m.n();
                    }
                    FloatProperty<VolumePanelAnimator>[] floatPropertyArr = this.volumesPropertyX;
                    if (floatPropertyArr != null && (floatProperty = floatPropertyArr[i2]) != null) {
                        this.animConfig.setSpecial(floatProperty, FolmeUtilsExtKt.getEASE_COLLAPSE_SIZE(), new float[0]);
                        this.anim.setTo(floatProperty, Float.valueOf(0.0f));
                    }
                    i2 = i3;
                }
            }
            onAnimComplete();
            this.lastAnimValue = null;
            return;
        }
        List<String> list3 = this.animConfigTagList;
        list3.clear();
        String name = SIZE.getName();
        n.f(name, "getName(...)");
        list3.add(name);
        String name2 = COLOR.getName();
        n.f(name2, "getName(...)");
        list3.add(name2);
        String name3 = SCALE_SLIDER.getName();
        n.f(name3, "getName(...)");
        list3.add(name3);
        this.anim.to(SIZE, Float.valueOf(0.0f), COLOR, Float.valueOf(0.0f), SCALE_SLIDER, Float.valueOf(0.0f), this.animConfig);
        List<VolumeColumn> list4 = this.volumeColumnList;
        if (list4 != null) {
            int i4 = 0;
            for (Object obj2 : list4) {
                int i5 = i4 + 1;
                if (i4 < 0) {
                    m.n();
                }
                FloatProperty<VolumePanelAnimator>[] floatPropertyArr2 = this.volumesPropertyX;
                if (floatPropertyArr2 != null && (floatProperty2 = floatPropertyArr2[i4]) != null) {
                    List<String> list5 = this.animConfigTagList;
                    String name4 = floatProperty2.getName();
                    n.f(name4, "getName(...)");
                    list5.add(name4);
                    this.animConfig.setSpecial(floatProperty2, FolmeUtilsExtKt.getEASE_COLLAPSE_SIZE(), new float[0]);
                    this.anim.to(floatProperty2, Float.valueOf(0.0f), this.animConfig);
                }
                i4 = i5;
            }
        }
    }

    @Override // miui.systemui.controlcenter.panel.secondary.SecondaryPanelAnimatorBase
    public void expand() {
        FloatProperty<VolumePanelAnimator> floatProperty;
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
        setVolumeContainerTranslationZ$default(this, false, 1, null);
        ControlCenterScenarioTracker.setControlCenterScenarioState$default(ControlCenterScenarioTracker.INSTANCE, 355L, false, 2, null);
        SecondaryPanelAnimatorBase.doFrame$default(this, 0L, 1, null);
        List<String> list = this.animConfigTagList;
        list.clear();
        String name = SIZE.getName();
        n.f(name, "getName(...)");
        list.add(name);
        String name2 = COLOR.getName();
        n.f(name2, "getName(...)");
        list.add(name2);
        String name3 = SCALE_SLIDER.getName();
        n.f(name3, "getName(...)");
        list.add(name3);
        AnimConfig animConfig = this.animConfig;
        animConfig.setSpecial(SIZE, FolmeUtilsExtKt.getEASE_EXPAND_SIZE(), new float[0]);
        animConfig.setSpecial(COLOR, FolmeUtilsExtKt.getEASE_EXPAND_COLOR(), new float[0]);
        animConfig.setSpecial(SCALE_SLIDER, FolmeUtilsExtKt.getEASE_EXPAND_POSITION(), new float[0]);
        this.anim.to(SIZE, Float.valueOf(1.0f), COLOR, Float.valueOf(1.0f), SCALE_SLIDER, Float.valueOf(1.0f), this.animConfig);
        List<VolumeColumn> list2 = this.volumeColumnList;
        if (list2 != null) {
            int i2 = 0;
            for (Object obj : list2) {
                int i3 = i2 + 1;
                if (i2 < 0) {
                    m.n();
                }
                FloatProperty<VolumePanelAnimator>[] floatPropertyArr = this.volumesPropertyX;
                if (floatPropertyArr != null && (floatProperty = floatPropertyArr[i2]) != null) {
                    List<String> list3 = this.animConfigTagList;
                    String name4 = floatProperty.getName();
                    n.f(name4, "getName(...)");
                    list3.add(name4);
                    this.animConfig.setSpecial(floatProperty, FolmeUtilsExtKt.getEASE_EXPAND_SIZE(), new float[0]);
                    this.anim.to(floatProperty, Float.valueOf(1.0f), this.animConfig);
                }
                i2 = i3;
            }
        }
    }

    @Override // miui.systemui.controlcenter.panel.secondary.SecondaryPanelAnimatorBase
    public void forceUpdateClipHeader() {
        ((ControlCenterWindowViewController) this.windowViewController.get()).updateClip(isCollapsing());
    }

    /* JADX WARN: Removed duplicated region for block: B:39:0x017e  */
    @Override // miui.systemui.controlcenter.panel.secondary.SecondaryPanelAnimatorBase
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void frameCallback() {
        /*
            Method dump skipped, instruction units count: 1307
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: miui.systemui.controlcenter.panel.secondary.volume.VolumePanelAnimator.frameCallback():void");
    }

    @Override // miui.systemui.controlcenter.panel.secondary.SecondaryPanelAnimatorBase
    public String getAnimStateString() {
        return "VolumePanelAnimator(size=" + this.size + ", position=" + this.position + ", color=" + this.color + ", scaleSlider=" + this.scaleSlider;
    }

    @Override // miui.systemui.controlcenter.panel.secondary.SecondaryPanelAnimatorBase
    public void onAnimComplete() {
        super.onAnimComplete();
        VolumePanelDelegate volumePanelDelegate = (VolumePanelDelegate) this.contentController.get();
        View content = volumePanelDelegate != null ? volumePanelDelegate.getContent() : null;
        ExpandCollapseLinearLayout expandCollapseLinearLayout = content instanceof ExpandCollapseLinearLayout ? (ExpandCollapseLinearLayout) content : null;
        if (expandCollapseLinearLayout != null) {
            expandCollapseLinearLayout.setInterceptTouchEvent(false);
        }
        if (isExpanded()) {
            ControlCenterScenarioTracker.INSTANCE.setControlCenterScenarioState(355L, false);
        }
        SecondaryPanelAnimatorBase.doFrame$default(this, 0L, 1, null);
        setVolumeContainerTranslationZ(false);
        ((VolumePanelController) this.volumePanelController.get()).onAnimComplete();
        this.animValue = null;
        onAnimCompleteTrack();
    }

    @Override // miui.systemui.controlcenter.panel.secondary.SecondaryPanelAnimatorBase
    public void prepareExpand(VolumePanelParams volumePanelParams) {
        super.prepareExpand(volumePanelParams);
        this.fromView = volumePanelParams != null ? volumePanelParams.getFromView() : null;
        this.animValue = null;
        this.lastAnimValue = null;
        this.size = 0.0f;
        this.color = 0.0f;
        this.scaleSlider = 0.94f;
        List<VolumeColumn> list = this.volumeColumnList;
        if (list != null) {
            int i2 = 0;
            for (Object obj : list) {
                int i3 = i2 + 1;
                if (i2 < 0) {
                    m.n();
                }
                VolumeColumn volumeColumn = (VolumeColumn) obj;
                View view = volumeColumn.getView();
                view.setScaleX(1.0f);
                view.setScaleY(1.0f);
                volumeColumn.setInCCMainPage(false);
                i2 = i3;
            }
        }
    }
}
