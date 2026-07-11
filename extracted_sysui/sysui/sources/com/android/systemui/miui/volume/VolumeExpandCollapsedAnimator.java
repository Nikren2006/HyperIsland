package com.android.systemui.miui.volume;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.Choreographer;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.ImageView;
import com.android.systemui.miui.volume.widget.VolumeBlurFrameLayout;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import kotlin.jvm.internal.DefaultConstructorMarker;
import miui.systemui.animation.FolmeUtilsExtKt;
import miui.systemui.util.CommonUtils;
import miui.systemui.util.MiBlurCompat;
import miuix.animation.Folme;
import miuix.animation.IStateStyle;
import miuix.animation.base.AnimConfig;
import miuix.animation.listener.TransitionListener;
import miuix.animation.listener.UpdateInfo;
import miuix.animation.property.FloatProperty;
import systemui.plugin.eventtracking.trackers.VolumeEventTracker;

/* JADX INFO: loaded from: classes2.dex */
public final class VolumeExpandCollapsedAnimator {
    private static final String TAG = "VolumeExpandCollapsedAnimator";
    private final IStateStyle anim;
    private final AnimConfig animConfig;
    private AnimValue animValue;
    private final List<String> animViewConfigTag;
    private final Choreographer choreographer;
    private float color;
    private ViewGroup dndLayout;
    private boolean expandWithAnim;
    private final Choreographer.FrameCallback frameCallback;
    private ViewLocValue fromCollapsedContentValue;
    private ViewValue fromCollapsedIconValue;
    private ViewLocValue fromCollapsedRingerLocValue;
    private List<ViewValue> fromRingerBtnIconValueList;
    private List<ViewLocValue> fromRingerBtnValueList;
    private ViewLocValue fromShowContentValue;
    private ViewValue fromShowIconValue;
    private ViewLocValue fromShowRingerModeLayoutValue;
    private VolumeFromView fromView;
    private TransitionListener mCallback;
    private final Context mContext;
    private View mExpandBgView;
    private Drawable mExpandBgViewBlurDrawable;
    private boolean mExpanded;
    private boolean mIsAnimating;
    private final MiuiVolumeDialogMotion mMotion;
    private View mRingerModeLayout;
    private View mShadowView;
    private View mSuperVolume;
    private final VolumeExpandCollapsedAnimator$mTransitionListener$1 mTransitionListener;
    private View mVolumeView;
    private float position;
    private ViewGroup ringerLayout;
    private int shadowPaddingBottom;
    private int shadowPaddingLeft;
    private int shadowPaddingTop;
    private float size;
    private long startTime;
    private VolumeToView toView;
    private boolean updateScheduled;
    private List<VolumeColumn> volumeColumnList;
    private List<VolumeColumn> volumeColumns;
    private ViewGroup volumeDialogColumnCollapsed;
    private ViewGroup volumeDialogColumnTemp;
    private ViewGroup volumeDialogColumns;
    private ViewGroup volumeDialogContent;
    private FloatProperty<VolumeExpandCollapsedAnimator>[] volumesPropertyX;
    private Float[] volumesSizeX;
    public static final Companion Companion = new Companion(null);
    private static VolumeExpandCollapsedAnimator$Companion$SIZE$1 SIZE = new FloatProperty<VolumeExpandCollapsedAnimator>() { // from class: com.android.systemui.miui.volume.VolumeExpandCollapsedAnimator$Companion$SIZE$1
        @Override // miuix.animation.property.FloatProperty
        public float getValue(VolumeExpandCollapsedAnimator anim) {
            kotlin.jvm.internal.n.g(anim, "anim");
            return anim.size;
        }

        @Override // miuix.animation.property.FloatProperty
        public void setValue(VolumeExpandCollapsedAnimator anim, float f2) {
            kotlin.jvm.internal.n.g(anim, "anim");
            if (anim.size == f2) {
                return;
            }
            anim.size = f2;
            anim.scheduleUpdate();
        }
    };
    private static VolumeExpandCollapsedAnimator$Companion$COLOR$1 COLOR = new FloatProperty<VolumeExpandCollapsedAnimator>() { // from class: com.android.systemui.miui.volume.VolumeExpandCollapsedAnimator$Companion$COLOR$1
        @Override // miuix.animation.property.FloatProperty
        public float getValue(VolumeExpandCollapsedAnimator anim) {
            kotlin.jvm.internal.n.g(anim, "anim");
            return anim.color;
        }

        @Override // miuix.animation.property.FloatProperty
        public void setValue(VolumeExpandCollapsedAnimator anim, float f2) {
            kotlin.jvm.internal.n.g(anim, "anim");
            if (anim.color == f2) {
                return;
            }
            anim.color = f2;
            anim.scheduleUpdate();
        }
    };
    private static VolumeExpandCollapsedAnimator$Companion$POSITION$1 POSITION = new FloatProperty<VolumeExpandCollapsedAnimator>() { // from class: com.android.systemui.miui.volume.VolumeExpandCollapsedAnimator$Companion$POSITION$1
        @Override // miuix.animation.property.FloatProperty
        public float getValue(VolumeExpandCollapsedAnimator anim) {
            kotlin.jvm.internal.n.g(anim, "anim");
            return anim.position;
        }

        @Override // miuix.animation.property.FloatProperty
        public void setValue(VolumeExpandCollapsedAnimator anim, float f2) {
            kotlin.jvm.internal.n.g(anim, "anim");
            if (anim.position == f2) {
                return;
            }
            anim.position = f2;
            anim.scheduleUpdate();
        }
    };

    public static final class AnimValue {
        public ViewLocValue fromContentBg;
        private final List<ViewLocValue> fromRingerBtn;
        private final List<ViewValue> fromRingerBtnIcons;
        private int fromRingerDividerHeight;
        private final ViewLocValue fromRingerMode;
        private ViewLocValue fromVolume;
        private ViewValue fromVolumeIcon;
        private final int fromVolumeRadius;
        private final ViewLocValue toContent;
        private final ViewLocValue toContentBg;
        private final ViewLocValue toContentColumns;
        private final int toExpandBgRadius;
        private final ViewLocValue toInnerContent;
        private final ViewLocValue toRingMode;
        private final List<ViewLocValue> toRingerBtn;
        private final List<ViewValue> toRingerBtnIcons;
        private int toRingerDividerHeight;
        private final List<ViewLocValue> toRingerTimers;
        private final ViewLocValue toShadow;
        private final List<ViewValue> toVolumeIcons;
        private final int toVolumeRadius;
        private final List<ViewLocValue> toVolumes;

        public AnimValue(ViewLocValue fromVolume, ViewValue fromVolumeIcon, ViewLocValue toContent, ViewLocValue toContentBg, ViewLocValue toShadow, ViewLocValue toInnerContent, ViewLocValue toContentColumns, List<ViewLocValue> toVolumes, List<ViewValue> toVolumeIcons, ViewLocValue fromRingerMode, List<ViewLocValue> fromRingerBtn, List<ViewValue> fromRingerBtnIcons, ViewLocValue toRingMode, List<ViewLocValue> toRingerBtn, List<ViewValue> toRingerBtnIcons, List<ViewLocValue> toRingerTimers, int i2, int i3, int i4) {
            kotlin.jvm.internal.n.g(fromVolume, "fromVolume");
            kotlin.jvm.internal.n.g(fromVolumeIcon, "fromVolumeIcon");
            kotlin.jvm.internal.n.g(toContent, "toContent");
            kotlin.jvm.internal.n.g(toContentBg, "toContentBg");
            kotlin.jvm.internal.n.g(toShadow, "toShadow");
            kotlin.jvm.internal.n.g(toInnerContent, "toInnerContent");
            kotlin.jvm.internal.n.g(toContentColumns, "toContentColumns");
            kotlin.jvm.internal.n.g(toVolumes, "toVolumes");
            kotlin.jvm.internal.n.g(toVolumeIcons, "toVolumeIcons");
            kotlin.jvm.internal.n.g(fromRingerMode, "fromRingerMode");
            kotlin.jvm.internal.n.g(fromRingerBtn, "fromRingerBtn");
            kotlin.jvm.internal.n.g(fromRingerBtnIcons, "fromRingerBtnIcons");
            kotlin.jvm.internal.n.g(toRingMode, "toRingMode");
            kotlin.jvm.internal.n.g(toRingerBtn, "toRingerBtn");
            kotlin.jvm.internal.n.g(toRingerBtnIcons, "toRingerBtnIcons");
            kotlin.jvm.internal.n.g(toRingerTimers, "toRingerTimers");
            this.fromVolume = fromVolume;
            this.fromVolumeIcon = fromVolumeIcon;
            this.toContent = toContent;
            this.toContentBg = toContentBg;
            this.toShadow = toShadow;
            this.toInnerContent = toInnerContent;
            this.toContentColumns = toContentColumns;
            this.toVolumes = toVolumes;
            this.toVolumeIcons = toVolumeIcons;
            this.fromRingerMode = fromRingerMode;
            this.fromRingerBtn = fromRingerBtn;
            this.fromRingerBtnIcons = fromRingerBtnIcons;
            this.toRingMode = toRingMode;
            this.toRingerBtn = toRingerBtn;
            this.toRingerBtnIcons = toRingerBtnIcons;
            this.toRingerTimers = toRingerTimers;
            this.fromVolumeRadius = i2;
            this.toVolumeRadius = i3;
            this.toExpandBgRadius = i4;
            init();
        }

        public final ViewLocValue component1() {
            return this.fromVolume;
        }

        public final ViewLocValue component10() {
            return this.fromRingerMode;
        }

        public final List<ViewLocValue> component11() {
            return this.fromRingerBtn;
        }

        public final List<ViewValue> component12() {
            return this.fromRingerBtnIcons;
        }

        public final ViewLocValue component13() {
            return this.toRingMode;
        }

        public final List<ViewLocValue> component14() {
            return this.toRingerBtn;
        }

        public final List<ViewValue> component15() {
            return this.toRingerBtnIcons;
        }

        public final List<ViewLocValue> component16() {
            return this.toRingerTimers;
        }

        public final int component17() {
            return this.fromVolumeRadius;
        }

        public final int component18() {
            return this.toVolumeRadius;
        }

        public final int component19() {
            return this.toExpandBgRadius;
        }

        public final ViewValue component2() {
            return this.fromVolumeIcon;
        }

        public final ViewLocValue component3() {
            return this.toContent;
        }

        public final ViewLocValue component4() {
            return this.toContentBg;
        }

        public final ViewLocValue component5() {
            return this.toShadow;
        }

        public final ViewLocValue component6() {
            return this.toInnerContent;
        }

        public final ViewLocValue component7() {
            return this.toContentColumns;
        }

        public final List<ViewLocValue> component8() {
            return this.toVolumes;
        }

        public final List<ViewValue> component9() {
            return this.toVolumeIcons;
        }

        public final AnimValue copy(ViewLocValue fromVolume, ViewValue fromVolumeIcon, ViewLocValue toContent, ViewLocValue toContentBg, ViewLocValue toShadow, ViewLocValue toInnerContent, ViewLocValue toContentColumns, List<ViewLocValue> toVolumes, List<ViewValue> toVolumeIcons, ViewLocValue fromRingerMode, List<ViewLocValue> fromRingerBtn, List<ViewValue> fromRingerBtnIcons, ViewLocValue toRingMode, List<ViewLocValue> toRingerBtn, List<ViewValue> toRingerBtnIcons, List<ViewLocValue> toRingerTimers, int i2, int i3, int i4) {
            kotlin.jvm.internal.n.g(fromVolume, "fromVolume");
            kotlin.jvm.internal.n.g(fromVolumeIcon, "fromVolumeIcon");
            kotlin.jvm.internal.n.g(toContent, "toContent");
            kotlin.jvm.internal.n.g(toContentBg, "toContentBg");
            kotlin.jvm.internal.n.g(toShadow, "toShadow");
            kotlin.jvm.internal.n.g(toInnerContent, "toInnerContent");
            kotlin.jvm.internal.n.g(toContentColumns, "toContentColumns");
            kotlin.jvm.internal.n.g(toVolumes, "toVolumes");
            kotlin.jvm.internal.n.g(toVolumeIcons, "toVolumeIcons");
            kotlin.jvm.internal.n.g(fromRingerMode, "fromRingerMode");
            kotlin.jvm.internal.n.g(fromRingerBtn, "fromRingerBtn");
            kotlin.jvm.internal.n.g(fromRingerBtnIcons, "fromRingerBtnIcons");
            kotlin.jvm.internal.n.g(toRingMode, "toRingMode");
            kotlin.jvm.internal.n.g(toRingerBtn, "toRingerBtn");
            kotlin.jvm.internal.n.g(toRingerBtnIcons, "toRingerBtnIcons");
            kotlin.jvm.internal.n.g(toRingerTimers, "toRingerTimers");
            return new AnimValue(fromVolume, fromVolumeIcon, toContent, toContentBg, toShadow, toInnerContent, toContentColumns, toVolumes, toVolumeIcons, fromRingerMode, fromRingerBtn, fromRingerBtnIcons, toRingMode, toRingerBtn, toRingerBtnIcons, toRingerTimers, i2, i3, i4);
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof AnimValue)) {
                return false;
            }
            AnimValue animValue = (AnimValue) obj;
            return kotlin.jvm.internal.n.c(this.fromVolume, animValue.fromVolume) && kotlin.jvm.internal.n.c(this.fromVolumeIcon, animValue.fromVolumeIcon) && kotlin.jvm.internal.n.c(this.toContent, animValue.toContent) && kotlin.jvm.internal.n.c(this.toContentBg, animValue.toContentBg) && kotlin.jvm.internal.n.c(this.toShadow, animValue.toShadow) && kotlin.jvm.internal.n.c(this.toInnerContent, animValue.toInnerContent) && kotlin.jvm.internal.n.c(this.toContentColumns, animValue.toContentColumns) && kotlin.jvm.internal.n.c(this.toVolumes, animValue.toVolumes) && kotlin.jvm.internal.n.c(this.toVolumeIcons, animValue.toVolumeIcons) && kotlin.jvm.internal.n.c(this.fromRingerMode, animValue.fromRingerMode) && kotlin.jvm.internal.n.c(this.fromRingerBtn, animValue.fromRingerBtn) && kotlin.jvm.internal.n.c(this.fromRingerBtnIcons, animValue.fromRingerBtnIcons) && kotlin.jvm.internal.n.c(this.toRingMode, animValue.toRingMode) && kotlin.jvm.internal.n.c(this.toRingerBtn, animValue.toRingerBtn) && kotlin.jvm.internal.n.c(this.toRingerBtnIcons, animValue.toRingerBtnIcons) && kotlin.jvm.internal.n.c(this.toRingerTimers, animValue.toRingerTimers) && this.fromVolumeRadius == animValue.fromVolumeRadius && this.toVolumeRadius == animValue.toVolumeRadius && this.toExpandBgRadius == animValue.toExpandBgRadius;
        }

        public final ViewLocValue getFromContentBg() {
            ViewLocValue viewLocValue = this.fromContentBg;
            if (viewLocValue != null) {
                return viewLocValue;
            }
            kotlin.jvm.internal.n.w("fromContentBg");
            return null;
        }

        public final List<ViewLocValue> getFromRingerBtn() {
            return this.fromRingerBtn;
        }

        public final List<ViewValue> getFromRingerBtnIcons() {
            return this.fromRingerBtnIcons;
        }

        public final int getFromRingerDividerHeight() {
            return this.fromRingerDividerHeight;
        }

        public final ViewLocValue getFromRingerMode() {
            return this.fromRingerMode;
        }

        public final ViewLocValue getFromVolume() {
            return this.fromVolume;
        }

        public final ViewValue getFromVolumeIcon() {
            return this.fromVolumeIcon;
        }

        public final int getFromVolumeRadius() {
            return this.fromVolumeRadius;
        }

        public final ViewLocValue getToContent() {
            return this.toContent;
        }

        public final ViewLocValue getToContentBg() {
            return this.toContentBg;
        }

        public final ViewLocValue getToContentColumns() {
            return this.toContentColumns;
        }

        public final int getToExpandBgRadius() {
            return this.toExpandBgRadius;
        }

        public final ViewLocValue getToInnerContent() {
            return this.toInnerContent;
        }

        public final ViewLocValue getToRingMode() {
            return this.toRingMode;
        }

        public final List<ViewLocValue> getToRingerBtn() {
            return this.toRingerBtn;
        }

        public final List<ViewValue> getToRingerBtnIcons() {
            return this.toRingerBtnIcons;
        }

        public final int getToRingerDividerHeight() {
            return this.toRingerDividerHeight;
        }

        public final List<ViewLocValue> getToRingerTimers() {
            return this.toRingerTimers;
        }

        public final ViewLocValue getToShadow() {
            return this.toShadow;
        }

        public final List<ViewValue> getToVolumeIcons() {
            return this.toVolumeIcons;
        }

        public final int getToVolumeRadius() {
            return this.toVolumeRadius;
        }

        public final List<ViewLocValue> getToVolumes() {
            return this.toVolumes;
        }

        public int hashCode() {
            return (((((((((((((((((((((((((((((((((((this.fromVolume.hashCode() * 31) + this.fromVolumeIcon.hashCode()) * 31) + this.toContent.hashCode()) * 31) + this.toContentBg.hashCode()) * 31) + this.toShadow.hashCode()) * 31) + this.toInnerContent.hashCode()) * 31) + this.toContentColumns.hashCode()) * 31) + this.toVolumes.hashCode()) * 31) + this.toVolumeIcons.hashCode()) * 31) + this.fromRingerMode.hashCode()) * 31) + this.fromRingerBtn.hashCode()) * 31) + this.fromRingerBtnIcons.hashCode()) * 31) + this.toRingMode.hashCode()) * 31) + this.toRingerBtn.hashCode()) * 31) + this.toRingerBtnIcons.hashCode()) * 31) + this.toRingerTimers.hashCode()) * 31) + Integer.hashCode(this.fromVolumeRadius)) * 31) + Integer.hashCode(this.toVolumeRadius)) * 31) + Integer.hashCode(this.toExpandBgRadius);
        }

        public final void init() {
            setFromContentBg(new ViewLocValue(this.fromVolume.getLocLeft(), this.fromVolume.getLocTop(), this.fromVolume.getLocLeft(), this.fromVolume.getLocTop(), this.fromVolume.getWidth(), this.fromRingerMode.getHeight() + this.fromRingerMode.getTop()));
            List<ViewLocValue> list = this.fromRingerBtn;
            if (list.size() <= 1) {
                list = null;
            }
            if (list != null) {
                this.fromRingerDividerHeight = (list.get(1).getLocTop() - list.get(0).getLocTop()) - list.get(0).getHeight();
            }
            List<ViewLocValue> list2 = this.toRingerBtn;
            List<ViewLocValue> list3 = list2.size() > 1 ? list2 : null;
            if (list3 != null) {
                this.toRingerDividerHeight = (list3.get(1).getLocTop() - list3.get(0).getLocTop()) - list3.get(0).getHeight();
            }
        }

        public final void setFromContentBg(ViewLocValue viewLocValue) {
            kotlin.jvm.internal.n.g(viewLocValue, "<set-?>");
            this.fromContentBg = viewLocValue;
        }

        public final void setFromRingerDividerHeight(int i2) {
            this.fromRingerDividerHeight = i2;
        }

        public final void setFromVolume(ViewLocValue viewLocValue) {
            kotlin.jvm.internal.n.g(viewLocValue, "<set-?>");
            this.fromVolume = viewLocValue;
        }

        public final void setFromVolumeIcon(ViewValue viewValue) {
            kotlin.jvm.internal.n.g(viewValue, "<set-?>");
            this.fromVolumeIcon = viewValue;
        }

        public final void setToRingerDividerHeight(int i2) {
            this.toRingerDividerHeight = i2;
        }

        public String toString() {
            return "AnimValue(\nfromVolume=" + this.fromVolume + ", \nfromVolumeIcon=" + this.fromVolumeIcon + ", \ntoContent=" + this.toContent + ", \ntoContentBg=" + this.toContentBg + ", \ntoShadow=" + this.toShadow + ", \ntoInnerContent=" + this.toInnerContent + ", \ntoContentColumns=" + this.toContentColumns + ", \ntoVolumes=" + this.toVolumes + ", \ntoVolumeIcon=" + this.toVolumeIcons + ", \nfromRingMode=" + this.fromRingerMode + ", fromRingerBtn=" + this.fromRingerBtn + ", fromRingerBtnIcons=" + this.fromRingerBtnIcons + ", \ntoRingMode=" + this.toRingMode + ", toRingerBtn=" + this.toRingerBtn + ", toRingerBtnIcons=" + this.toRingerBtnIcons + ", toRingerTimers=" + this.toRingerTimers + ", \nfromRingerDividerHeight=" + this.fromRingerDividerHeight + ", toRingerDividerHeight=" + this.toRingerDividerHeight + ", \nfromVolumeRadius=" + this.fromVolumeRadius + ", toVolumeRadius=" + this.toVolumeRadius + ", toExpandBgRadius=" + this.toExpandBgRadius + ", \nfromContentBg=" + getFromContentBg() + ")";
        }
    }

    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }

    public static final class ViewLocValue {
        private final int height;
        private final int left;
        private final int locLeft;
        private final int locTop;
        private final int top;
        private final int width;

        public ViewLocValue(int i2, int i3, int i4, int i5, int i6, int i7) {
            this.locLeft = i2;
            this.locTop = i3;
            this.left = i4;
            this.top = i5;
            this.width = i6;
            this.height = i7;
        }

        public static /* synthetic */ ViewLocValue copy$default(ViewLocValue viewLocValue, int i2, int i3, int i4, int i5, int i6, int i7, int i8, Object obj) {
            if ((i8 & 1) != 0) {
                i2 = viewLocValue.locLeft;
            }
            if ((i8 & 2) != 0) {
                i3 = viewLocValue.locTop;
            }
            int i9 = i3;
            if ((i8 & 4) != 0) {
                i4 = viewLocValue.left;
            }
            int i10 = i4;
            if ((i8 & 8) != 0) {
                i5 = viewLocValue.top;
            }
            int i11 = i5;
            if ((i8 & 16) != 0) {
                i6 = viewLocValue.width;
            }
            int i12 = i6;
            if ((i8 & 32) != 0) {
                i7 = viewLocValue.height;
            }
            return viewLocValue.copy(i2, i9, i10, i11, i12, i7);
        }

        public final int component1() {
            return this.locLeft;
        }

        public final int component2() {
            return this.locTop;
        }

        public final int component3() {
            return this.left;
        }

        public final int component4() {
            return this.top;
        }

        public final int component5() {
            return this.width;
        }

        public final int component6() {
            return this.height;
        }

        public final ViewLocValue copy(int i2, int i3, int i4, int i5, int i6, int i7) {
            return new ViewLocValue(i2, i3, i4, i5, i6, i7);
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof ViewLocValue)) {
                return false;
            }
            ViewLocValue viewLocValue = (ViewLocValue) obj;
            return this.locLeft == viewLocValue.locLeft && this.locTop == viewLocValue.locTop && this.left == viewLocValue.left && this.top == viewLocValue.top && this.width == viewLocValue.width && this.height == viewLocValue.height;
        }

        public final int getHeight() {
            return this.height;
        }

        public final int getLeft() {
            return this.left;
        }

        public final int getLocLeft() {
            return this.locLeft;
        }

        public final int getLocTop() {
            return this.locTop;
        }

        public final int getTop() {
            return this.top;
        }

        public final int getWidth() {
            return this.width;
        }

        public int hashCode() {
            return (((((((((Integer.hashCode(this.locLeft) * 31) + Integer.hashCode(this.locTop)) * 31) + Integer.hashCode(this.left)) * 31) + Integer.hashCode(this.top)) * 31) + Integer.hashCode(this.width)) * 31) + Integer.hashCode(this.height);
        }

        public String toString() {
            return "ViewLocValue(locLeft=" + this.locLeft + ", locTop=" + this.locTop + ", left=" + this.left + ", top=" + this.top + ", width=" + this.width + ", height=" + this.height + ")";
        }
    }

    public static final class ViewValue {
        private int height;
        private int left;
        private int top;
        private int width;

        public ViewValue(int i2, int i3, int i4, int i5) {
            this.left = i2;
            this.top = i3;
            this.width = i4;
            this.height = i5;
        }

        public static /* synthetic */ ViewValue copy$default(ViewValue viewValue, int i2, int i3, int i4, int i5, int i6, Object obj) {
            if ((i6 & 1) != 0) {
                i2 = viewValue.left;
            }
            if ((i6 & 2) != 0) {
                i3 = viewValue.top;
            }
            if ((i6 & 4) != 0) {
                i4 = viewValue.width;
            }
            if ((i6 & 8) != 0) {
                i5 = viewValue.height;
            }
            return viewValue.copy(i2, i3, i4, i5);
        }

        public final int component1() {
            return this.left;
        }

        public final int component2() {
            return this.top;
        }

        public final int component3() {
            return this.width;
        }

        public final int component4() {
            return this.height;
        }

        public final ViewValue copy(int i2, int i3, int i4, int i5) {
            return new ViewValue(i2, i3, i4, i5);
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof ViewValue)) {
                return false;
            }
            ViewValue viewValue = (ViewValue) obj;
            return this.left == viewValue.left && this.top == viewValue.top && this.width == viewValue.width && this.height == viewValue.height;
        }

        public final int getHeight() {
            return this.height;
        }

        public final int getLeft() {
            return this.left;
        }

        public final int getTop() {
            return this.top;
        }

        public final int getWidth() {
            return this.width;
        }

        public int hashCode() {
            return (((((Integer.hashCode(this.left) * 31) + Integer.hashCode(this.top)) * 31) + Integer.hashCode(this.width)) * 31) + Integer.hashCode(this.height);
        }

        public final void setHeight(int i2) {
            this.height = i2;
        }

        public final void setLeft(int i2) {
            this.left = i2;
        }

        public final void setTop(int i2) {
            this.top = i2;
        }

        public final void setWidth(int i2) {
            this.width = i2;
        }

        public String toString() {
            return "ViewValue(left=" + this.left + ", top=" + this.top + ", width=" + this.width + ", height=" + this.height + ")";
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r4v6, types: [com.android.systemui.miui.volume.VolumeExpandCollapsedAnimator$mTransitionListener$1] */
    public VolumeExpandCollapsedAnimator(Context mContext, MiuiVolumeDialogMotion mMotion) {
        kotlin.jvm.internal.n.g(mContext, "mContext");
        kotlin.jvm.internal.n.g(mMotion, "mMotion");
        this.mContext = mContext;
        this.mMotion = mMotion;
        this.choreographer = Choreographer.getInstance();
        this.anim = Folme.useValue(this);
        this.animViewConfigTag = new ArrayList();
        this.frameCallback = new Choreographer.FrameCallback() { // from class: com.android.systemui.miui.volume.r
            @Override // android.view.Choreographer.FrameCallback
            public final void doFrame(long j2) {
                VolumeExpandCollapsedAnimator.frameCallback$lambda$9(this.f1506a, j2);
            }
        };
        ?? r4 = new TransitionListener() { // from class: com.android.systemui.miui.volume.VolumeExpandCollapsedAnimator$mTransitionListener$1
            @Override // miuix.animation.listener.TransitionListener
            public void onBegin(Object obj, Collection<UpdateInfo> collection) {
                if (collection != null) {
                    Iterator<T> it = collection.iterator();
                    while (it.hasNext()) {
                        Log.i("VolumeExpandCollapsedAnimator", "listener_onBegin: " + ((UpdateInfo) it.next()).property.getName());
                    }
                }
            }

            @Override // miuix.animation.listener.TransitionListener
            public void onCancel(Object obj) {
                Log.e("VolumeExpandCollapsedAnimator", "listener_onCancel: ");
            }

            @Override // miuix.animation.listener.TransitionListener
            public void onComplete(Object obj) {
                VolumeExpandCollapsedAnimator.onAnimComplete$default(this.this$0, false, 1, null);
            }

            @Override // miuix.animation.listener.TransitionListener
            public void onUpdate(Object obj, Collection<UpdateInfo> collection) {
                if (collection != null) {
                    VolumeExpandCollapsedAnimator volumeExpandCollapsedAnimator = this.this$0;
                    for (UpdateInfo updateInfo : collection) {
                        String name = updateInfo.property.getName();
                        if (updateInfo.isCompleted && volumeExpandCollapsedAnimator.animViewConfigTag.contains(name)) {
                            long jCurrentTimeMillis = System.currentTimeMillis() - volumeExpandCollapsedAnimator.startTime;
                            Log.i("VolumeExpandCollapsedAnimator", "listener_remove: " + volumeExpandCollapsedAnimator.animViewConfigTag.size() + ", " + jCurrentTimeMillis + ", " + name + " --> " + updateInfo.getFloatValue());
                            volumeExpandCollapsedAnimator.animViewConfigTag.remove(name);
                        }
                    }
                }
            }
        };
        this.mTransitionListener = r4;
        this.animConfig = new AnimConfig().addListeners(r4);
        this.expandWithAnim = true;
        this.fromRingerBtnValueList = new ArrayList();
        this.fromRingerBtnIconValueList = new ArrayList();
    }

    public static /* synthetic */ void calculateFromViewValues$default(VolumeExpandCollapsedAnimator volumeExpandCollapsedAnimator, boolean z2, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            z2 = true;
        }
        volumeExpandCollapsedAnimator.calculateFromViewValues(z2);
    }

    private final List<Object> calculateRinger() {
        ArrayList arrayList = new ArrayList();
        View view = this.mRingerModeLayout;
        ViewGroup viewGroup = null;
        if (view == null) {
            kotlin.jvm.internal.n.w("mRingerModeLayout");
            view = null;
        }
        int[] iArr = new int[2];
        view.getLocationOnScreen(iArr);
        arrayList.add(new ViewLocValue(iArr[0], iArr[1], view.getLeft(), view.getTop(), view.getWidth(), view.getHeight()));
        ArrayList arrayList2 = new ArrayList();
        ArrayList arrayList3 = new ArrayList();
        ArrayList arrayList4 = new ArrayList();
        ViewGroup viewGroup2 = this.ringerLayout;
        if (viewGroup2 == null) {
            kotlin.jvm.internal.n.w("ringerLayout");
            viewGroup2 = null;
        }
        ViewGroup viewGroup3 = this.dndLayout;
        if (viewGroup3 == null) {
            kotlin.jvm.internal.n.w("dndLayout");
        } else {
            viewGroup = viewGroup3;
        }
        ViewGroup[] viewGroupArr = {viewGroup2, viewGroup};
        for (int i2 = 0; i2 < 2; i2++) {
            ViewGroup viewGroup4 = viewGroupArr[i2];
            View viewFindViewById = viewGroup4.findViewById(R.id.bg_blur);
            View viewFindViewById2 = viewGroup4.findViewById(R.id.icon);
            int[] iArr2 = new int[2];
            viewFindViewById.getLocationOnScreen(iArr2);
            arrayList2.add(new ViewLocValue(iArr2[0], iArr2[1], viewFindViewById.getLeft(), viewFindViewById.getTop(), viewFindViewById.getWidth(), viewFindViewById.getHeight()));
            arrayList3.add(new ViewValue(viewFindViewById2.getLeft(), viewFindViewById2.getTop(), viewFindViewById2.getWidth(), viewFindViewById2.getHeight()));
            View viewFindViewById3 = viewGroup4.findViewById(R.id.timer_layout);
            int[] iArr3 = new int[2];
            viewFindViewById3.getLocationOnScreen(iArr3);
            arrayList4.add(new ViewLocValue(iArr3[0], iArr3[1], viewFindViewById3.getLeft(), viewFindViewById3.getTop(), viewFindViewById3.getWidth(), viewFindViewById3.getHeight()));
        }
        arrayList.add(arrayList2);
        arrayList.add(arrayList3);
        arrayList.add(arrayList4);
        return arrayList;
    }

    private final boolean checkCollapsedPositionAvailable() {
        ViewLocValue viewLocValue = this.fromCollapsedContentValue;
        return (viewLocValue != null ? viewLocValue.getLocLeft() : 0) > this.mMotion.getDisplayWidthPixels();
    }

    private final ViewLocValue checkViewLocValue(ViewLocValue viewLocValue) {
        return viewLocValue == null ? new ViewLocValue(0, 0, 0, 0, 0, 0) : viewLocValue;
    }

    private final ViewValue checkViewValue(ViewValue viewValue) {
        return viewValue == null ? new ViewValue(0, 0, 0, 0) : viewValue;
    }

    public static /* synthetic */ void expand$default(VolumeExpandCollapsedAnimator volumeExpandCollapsedAnimator, boolean z2, TransitionListener transitionListener, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            transitionListener = null;
        }
        volumeExpandCollapsedAnimator.expand(z2, transitionListener);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void frameCallback$lambda$9(VolumeExpandCollapsedAnimator this$0, long j2) {
        int height;
        boolean z2;
        Float f2;
        kotlin.jvm.internal.n.g(this$0, "this$0");
        AnimValue animValue = this$0.animValue;
        if (animValue == null) {
            return;
        }
        int i2 = 0;
        this$0.updateScheduled = false;
        ViewLocValue toInnerContent = animValue.getToInnerContent();
        ViewLocValue toContentColumns = animValue.getToContentColumns();
        int iB = Y0.b.b((((animValue.getFromVolume().getLocLeft() + animValue.getFromVolume().getWidth()) - animValue.getToInnerContent().getLocLeft()) - animValue.getToInnerContent().getWidth()) * (1.0f - this$0.size));
        float left = animValue.getFromContentBg().getLeft() + ((animValue.getToContentBg().getLeft() - animValue.getFromContentBg().getLeft()) * this$0.size);
        float top = animValue.getFromContentBg().getTop() + ((animValue.getToContentBg().getTop() - animValue.getFromContentBg().getTop()) * this$0.size);
        float width = animValue.getFromContentBg().getWidth() + ((animValue.getToContentBg().getWidth() - animValue.getFromContentBg().getWidth()) * this$0.size);
        float height2 = animValue.getFromContentBg().getHeight() + ((animValue.getToContentBg().getHeight() - animValue.getFromContentBg().getHeight()) * this$0.size);
        boolean z3 = this$0.mExpanded;
        float f3 = z3 ? 1.4285715f * this$0.color : (this$0.color - 0.2f) * 1.25f;
        float fH = c1.f.h(z3 ? 3.3333333f * (this$0.color - 0.7f) : f3, 0.0f, 1.0f);
        View view = this$0.mExpandBgView;
        VolumeColumn volumeColumn = null;
        if (view == null) {
            kotlin.jvm.internal.n.w("mExpandBgView");
            view = null;
        }
        view.setLeftTopRightBottom(Y0.b.b(left), Y0.b.b(top), Y0.b.b(left + width), Y0.b.b(top + height2));
        CommonUtils commonUtils = CommonUtils.INSTANCE;
        commonUtils.setAlphaEx(view, f3);
        Drawable drawable = this$0.mExpandBgViewBlurDrawable;
        if (drawable != null) {
            drawable.setAlpha((int) (fH * 255));
        }
        Util.setRoundRect(view, animValue.getFromVolumeRadius() + ((animValue.getToExpandBgRadius() - animValue.getFromVolumeRadius()) * this$0.size));
        float f4 = this$0.shadowPaddingLeft;
        float f5 = this$0.size;
        int i3 = (int) (f4 * f5);
        int i4 = (int) (this$0.shadowPaddingTop * f5);
        int i5 = (int) (this$0.shadowPaddingBottom * f5);
        View view2 = this$0.mShadowView;
        if (view2 == null) {
            kotlin.jvm.internal.n.w("mShadowView");
            view2 = null;
        }
        view2.setLeftTopRightBottom(view.getLeft() - i3, view.getTop() - i4, view.getRight() + i3, view.getBottom() + i5);
        View view3 = this$0.mShadowView;
        if (view3 == null) {
            kotlin.jvm.internal.n.w("mShadowView");
            view3 = null;
        }
        commonUtils.setAlphaEx(view3, this$0.color);
        H0.s sVar = H0.s.f314a;
        View view4 = this$0.mVolumeView;
        if (view4 == null) {
            kotlin.jvm.internal.n.w("mVolumeView");
            view4 = null;
        }
        int locLeft = animValue.getToContent().getLocLeft() + iB;
        View view5 = this$0.mExpandBgView;
        if (view5 == null) {
            kotlin.jvm.internal.n.w("mExpandBgView");
            view5 = null;
        }
        int top2 = view5.getTop();
        view4.setLeftTopRightBottom(locLeft, top2, animValue.getToContent().getWidth() + locLeft, animValue.getToContent().getHeight() + top2);
        int iB2 = Y0.b.b(toInnerContent.getTop() * this$0.size);
        ViewGroup viewGroup = this$0.volumeDialogContent;
        if (viewGroup == null) {
            kotlin.jvm.internal.n.w("volumeDialogContent");
            viewGroup = null;
        }
        viewGroup.setLeftTopRightBottom(viewGroup.getLeft(), iB2, viewGroup.getRight(), animValue.getToInnerContent().getHeight() + iB2);
        View view6 = this$0.mRingerModeLayout;
        if (view6 == null) {
            kotlin.jvm.internal.n.w("mRingerModeLayout");
            view6 = null;
        }
        ViewLocValue toRingMode = animValue.getToRingMode();
        float f6 = iB;
        float locLeft2 = (((animValue.getFromVolume().getLocLeft() - toRingMode.getLocLeft()) - ((animValue.getFromVolume().getLocLeft() - toRingMode.getLocLeft()) * this$0.size)) + toRingMode.getLeft()) - f6;
        float top3 = animValue.getFromRingerMode().getTop() + ((animValue.getToRingMode().getTop() - animValue.getFromRingerMode().getTop()) * this$0.size);
        float width2 = animValue.getFromRingerMode().getWidth() + ((animValue.getToRingMode().getWidth() - animValue.getFromRingerMode().getWidth()) * this$0.size);
        view6.setLeftTopRightBottom(Y0.b.b(locLeft2), Y0.b.b(top3), Y0.b.b(locLeft2 + width2), Y0.b.b(top3 + animValue.getFromRingerMode().getHeight() + ((animValue.getToRingMode().getHeight() - animValue.getFromRingerMode().getHeight()) * this$0.size)));
        ViewGroup viewGroup2 = this$0.ringerLayout;
        if (viewGroup2 == null) {
            kotlin.jvm.internal.n.w("ringerLayout");
            viewGroup2 = null;
        }
        ViewGroup viewGroup3 = this$0.dndLayout;
        if (viewGroup3 == null) {
            kotlin.jvm.internal.n.w("dndLayout");
            viewGroup3 = null;
        }
        ViewGroup[] viewGroupArr = {viewGroup2, viewGroup3};
        int i6 = 0;
        int i7 = 0;
        while (i6 < 2) {
            ViewGroup viewGroup4 = viewGroupArr[i6];
            int i8 = i7 + 1;
            ViewLocValue viewLocValue = animValue.getFromRingerBtn().get(i7);
            ViewLocValue viewLocValue2 = animValue.getToRingerBtn().get(i7);
            float width3 = viewLocValue.getWidth() + ((viewLocValue2.getWidth() - viewLocValue.getWidth()) * this$0.size);
            float height3 = viewLocValue.getHeight() + ((viewLocValue2.getHeight() - viewLocValue.getHeight()) * this$0.size);
            float fromRingerDividerHeight = i7 == 0 ? 0.0f : animValue.getFromRingerDividerHeight() + ((animValue.getToRingerDividerHeight() - animValue.getFromRingerDividerHeight()) * this$0.size) + height3;
            viewGroup4.setLeftTopRightBottom(0, Y0.b.b(fromRingerDividerHeight), Y0.b.b(width2), Y0.b.b(fromRingerDividerHeight + height3));
            View[] viewArr = {viewGroup4.findViewById(R.id.bg_blur), viewGroup4.findViewById(R.id.miui_standard_btn)};
            int i9 = 0;
            while (i9 < 2) {
                viewArr[i9].setLeftTopRightBottom(0, 0, Y0.b.b(width3), Y0.b.b(height3));
                i9++;
                viewGroupArr = viewGroupArr;
                width2 = width2;
            }
            ViewGroup[] viewGroupArr2 = viewGroupArr;
            float f7 = width2;
            ViewValue viewValue = animValue.getFromRingerBtnIcons().get(i7);
            ViewValue viewValue2 = animValue.getToRingerBtnIcons().get(i7);
            float left2 = viewValue.getLeft() + ((viewValue2.getLeft() - viewValue.getLeft()) * this$0.size);
            float top4 = viewValue.getTop() + ((viewValue2.getTop() - viewValue.getTop()) * this$0.size);
            viewGroup4.findViewById(R.id.icon).setLeftTopRightBottom(Y0.b.b(left2), Y0.b.b(top4), Y0.b.b(left2 + viewValue.getWidth() + ((viewValue2.getWidth() - viewValue.getWidth()) * this$0.size)), Y0.b.b(top4 + viewValue.getHeight() + ((viewValue2.getHeight() - viewValue.getHeight()) * this$0.size)));
            if (animValue.getToRingerTimers().get(i7).getWidth() != 0) {
                float left3 = r1.getLeft() * this$0.size;
                float fE = c1.f.e(((viewGroup4.getWidth() - left3) * 1.0f) / r1.getWidth(), 1.0f);
                float height4 = (viewGroup4.getHeight() - (r1.getHeight() * fE)) / 2.0f;
                View viewFindViewById = viewGroup4.findViewById(R.id.timer_layout);
                CommonUtils commonUtils2 = CommonUtils.INSTANCE;
                kotlin.jvm.internal.n.d(viewFindViewById);
                commonUtils2.setAlphaEx(viewFindViewById, this$0.color);
                viewFindViewById.setPivotX(0.0f);
                viewFindViewById.setPivotY(0.0f);
                viewFindViewById.setScaleX(fE);
                viewFindViewById.setScaleY(fE);
                viewFindViewById.setLeftTopRightBottom(Y0.b.b(left3), Y0.b.b(height4), Y0.b.b(left3 + r1.getWidth()), Y0.b.b(height4 + r1.getHeight()));
                H0.s sVar2 = H0.s.f314a;
            }
            i6++;
            i7 = i8;
            viewGroupArr = viewGroupArr2;
            width2 = f7;
        }
        float f8 = 0.0f;
        H0.s sVar3 = H0.s.f314a;
        List<VolumeColumn> list = this$0.volumeColumnList;
        if (list != null) {
            Iterator it = list.iterator();
            int i10 = 0;
            while (it.hasNext()) {
                Object next = it.next();
                int i11 = i10 + 1;
                if (i10 < 0) {
                    I0.m.n();
                }
                VolumeColumn volumeColumn2 = (VolumeColumn) next;
                ViewLocValue viewLocValue3 = animValue.getToVolumes().get(i10);
                ViewValue viewValue3 = animValue.getToVolumeIcons().get(i10);
                Float[] fArr = this$0.volumesSizeX;
                float fFloatValue = (fArr == null || (f2 = fArr[i10]) == null) ? f8 : f2.floatValue();
                if (i10 == 0) {
                    volumeColumn = volumeColumn2;
                }
                int iB3 = Y0.b.b(((animValue.getFromVolume().getLocLeft() - toContentColumns.getLocLeft()) - ((animValue.getFromVolume().getLocLeft() - viewLocValue3.getLocLeft()) * fFloatValue)) - f6);
                int iB4 = Y0.b.b(animValue.getFromVolume().getWidth() + ((viewLocValue3.getWidth() - animValue.getFromVolume().getWidth()) * fFloatValue));
                int iB5 = Y0.b.b(animValue.getFromVolume().getHeight() + ((viewLocValue3.getHeight() - animValue.getFromVolume().getHeight()) * fFloatValue));
                if (i10 == 0) {
                    height = i2;
                } else {
                    kotlin.jvm.internal.n.d(volumeColumn);
                    height = (volumeColumn.getView().getHeight() - iB5) / 2;
                }
                Iterator it2 = it;
                volumeColumn2.getView().setLeftTopRightBottom(iB3, height, iB3 + iB4, height + iB5);
                volumeColumn2.getSlider().setLeftTopRightBottom(0, 0, iB4, iB5);
                volumeColumn2.getProgressView().setLeftTopRightBottom(0, 0, iB4, iB5);
                volumeColumn2.getProgressViewBg().setLeftTopRightBottom(0, 0, iB4, iB5);
                float left4 = animValue.getFromVolumeIcon().getLeft() + ((viewValue3.getLeft() - animValue.getFromVolumeIcon().getLeft()) * fFloatValue);
                float top5 = animValue.getFromVolumeIcon().getTop() + ((viewValue3.getTop() - animValue.getFromVolumeIcon().getTop()) * fFloatValue);
                float width4 = animValue.getFromVolumeIcon().getWidth() + ((viewValue3.getWidth() - animValue.getFromVolumeIcon().getWidth()) * fFloatValue);
                volumeColumn2.getIcon().setLeftTopRightBottom(Y0.b.b(left4), Y0.b.b(top5), Y0.b.b(left4 + width4), Y0.b.b(top5 + width4));
                Util.setRoundRect(volumeColumn2.getView(), animValue.getFromVolumeRadius() + ((animValue.getToVolumeRadius() - animValue.getFromVolumeRadius()) * this$0.size));
                if (MiBlurCompat.getBackgroundBlurOpened(this$0.mContext)) {
                    volumeColumn2.getProgressView().frameUpdateProgressHeightForVolume();
                }
                if (i10 != 0) {
                    float volumeAnimNode = this$0.getVolumeAnimNode(this$0.mExpanded, i10);
                    z2 = true;
                    float f9 = (1.0f / (1 - volumeAnimNode)) * (fFloatValue - volumeAnimNode);
                    float f10 = (0.4f * f9) + 0.6f;
                    View view7 = volumeColumn2.getView();
                    CommonUtils.INSTANCE.setAlphaEx(view7, f9);
                    view7.setTranslationZ(-(i10 * fFloatValue));
                    view7.setScaleX(f10);
                    view7.setScaleY(f10);
                    H0.s sVar4 = H0.s.f314a;
                } else {
                    z2 = true;
                }
                i2 = 0;
                i10 = i11;
                it = it2;
                f8 = 0.0f;
            }
            H0.s sVar5 = H0.s.f314a;
        }
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

    private final void onAnimBegin(boolean z2) {
        if (z2 || !this.mIsAnimating) {
            long jCurrentTimeMillis = System.currentTimeMillis() - this.startTime;
            Log.e(TAG, "listener_onAnimBegin:" + this.mExpanded + ", beginTime: " + jCurrentTimeMillis);
            setVolumeContainerTranslationZ(true);
            setSuppressLayout(true);
            setMIsAnimating(true);
            TransitionListener transitionListener = this.mCallback;
            if (transitionListener != null) {
                transitionListener.onBegin("");
            }
        }
    }

    public static /* synthetic */ void onAnimBegin$default(VolumeExpandCollapsedAnimator volumeExpandCollapsedAnimator, boolean z2, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            z2 = false;
        }
        volumeExpandCollapsedAnimator.onAnimBegin(z2);
    }

    private final void onAnimComplete(boolean z2) {
        if (z2 || this.mIsAnimating) {
            long jCurrentTimeMillis = System.currentTimeMillis() - this.startTime;
            Log.e(TAG, "listener_onAnimComplete:" + this.mExpanded + ", " + this.mIsAnimating + ", " + z2 + ", " + jCurrentTimeMillis);
            setVolumeContainerTranslationZ(false);
            setSuppressLayout(false);
            setMIsAnimating(false);
            TransitionListener transitionListener = this.mCallback;
            if (transitionListener != null) {
                transitionListener.onComplete("");
            }
            onAnimCompleteTrack();
        }
    }

    public static /* synthetic */ void onAnimComplete$default(VolumeExpandCollapsedAnimator volumeExpandCollapsedAnimator, boolean z2, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            z2 = false;
        }
        volumeExpandCollapsedAnimator.onAnimComplete(z2);
    }

    private final void onAnimCompleteTrack() {
        List<VolumeColumn> list;
        VolumeColumn volumeColumn;
        View view;
        if (!this.mExpanded || (list = this.volumeColumnList) == null || (volumeColumn = list.get(0)) == null || (view = volumeColumn.getView()) == null) {
            return;
        }
        int[] iArr = new int[2];
        view.getLocationOnScreen(iArr);
        View view2 = this.mExpandBgView;
        ViewGroup viewGroup = null;
        if (view2 == null) {
            kotlin.jvm.internal.n.w("mExpandBgView");
            view2 = null;
        }
        int[] iArr2 = new int[2];
        CommonUtils commonUtils = CommonUtils.INSTANCE;
        commonUtils.getLocationInWindowWithoutTransform(view2, iArr2);
        ViewGroup viewGroup2 = this.ringerLayout;
        if (viewGroup2 == null) {
            kotlin.jvm.internal.n.w("ringerLayout");
        } else {
            viewGroup = viewGroup2;
        }
        int[] iArr3 = new int[2];
        commonUtils.getLocationInWindowWithoutTransform(viewGroup, iArr3);
        int i2 = iArr[0];
        if (i2 < iArr2[0] || iArr[1] < iArr2[1] || i2 + view.getWidth() > iArr2[0] + view2.getWidth() || iArr[1] + view.getHeight() > iArr3[1]) {
            VolumeEventTracker.trackError(VolumeEventTracker.ERROR_022001, 1);
        }
    }

    private final void resetAnimValue() {
        this.size = 0.0f;
        this.position = 0.0f;
        this.color = 0.0f;
        List<VolumeColumn> list = this.volumeColumnList;
        if (list != null) {
            this.volumesSizeX = new Float[list.size()];
            FloatProperty<VolumeExpandCollapsedAnimator>[] floatPropertyArr = this.volumesPropertyX;
            if (floatPropertyArr != null) {
                kotlin.jvm.internal.n.d(floatPropertyArr);
                if (floatPropertyArr.length == list.size()) {
                    return;
                }
            }
            this.volumesPropertyX = new FloatProperty[list.size()];
            final int i2 = 0;
            for (Object obj : list) {
                int i3 = i2 + 1;
                if (i2 < 0) {
                    I0.m.n();
                }
                FloatProperty<VolumeExpandCollapsedAnimator>[] floatPropertyArr2 = this.volumesPropertyX;
                kotlin.jvm.internal.n.d(floatPropertyArr2);
                final String str = "sizeBgX_" + i2;
                floatPropertyArr2[i2] = new FloatProperty<VolumeExpandCollapsedAnimator>(str) { // from class: com.android.systemui.miui.volume.VolumeExpandCollapsedAnimator$resetAnimValue$1$1$1
                    @Override // miuix.animation.property.FloatProperty
                    public float getValue(VolumeExpandCollapsedAnimator anim) {
                        Float f2;
                        kotlin.jvm.internal.n.g(anim, "anim");
                        Float[] fArr = anim.volumesSizeX;
                        if (fArr != null) {
                            int i4 = i2;
                            if (fArr.length <= i4) {
                                fArr = null;
                            }
                            if (fArr != null && (f2 = fArr[i4]) != null) {
                                return f2.floatValue();
                            }
                        }
                        return 0.0f;
                    }

                    @Override // miuix.animation.property.FloatProperty
                    public void setValue(VolumeExpandCollapsedAnimator anim, float f2) {
                        kotlin.jvm.internal.n.g(anim, "anim");
                        Float[] fArr = anim.volumesSizeX;
                        if (fArr != null) {
                            int i4 = i2;
                            if (fArr.length <= i4 || kotlin.jvm.internal.n.b(fArr[i4], f2)) {
                                fArr = null;
                            }
                            if (fArr != null) {
                                fArr[i2] = Float.valueOf(f2);
                                anim.scheduleUpdate();
                            }
                        }
                    }
                };
                i2 = i3;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void scheduleUpdate() {
        if (this.updateScheduled) {
            return;
        }
        this.updateScheduled = true;
        this.choreographer.postFrameCallback(this.frameCallback);
    }

    private final void setMIsAnimating(boolean z2) {
        Log.i(TAG, "mIsAnimating_set: " + this.mIsAnimating + " --> " + z2);
        this.mIsAnimating = z2;
    }

    private final void setSuppressLayout(boolean z2) {
        View view = this.mVolumeView;
        if (view == null) {
            kotlin.jvm.internal.n.w("mVolumeView");
            view = null;
        }
        ViewParent parent = view.getParent();
        ViewGroup viewGroup = parent instanceof ViewGroup ? (ViewGroup) parent : null;
        if (viewGroup == null || viewGroup.isLayoutSuppressed() == z2) {
            return;
        }
        viewGroup.suppressLayout(z2);
    }

    private final void setVolumeContainerTranslationZ(boolean z2) {
        ViewGroup viewGroup = this.volumeDialogContent;
        ViewGroup viewGroup2 = null;
        if (viewGroup == null) {
            kotlin.jvm.internal.n.w("volumeDialogContent");
            viewGroup = null;
        }
        Object parent = viewGroup.getParent();
        View view = parent instanceof View ? (View) parent : null;
        if (view != null) {
            view.setTranslationZ(z2 ? 1.0f : 0.0f);
        }
        ViewGroup viewGroup3 = this.ringerLayout;
        if (viewGroup3 == null) {
            kotlin.jvm.internal.n.w("ringerLayout");
            viewGroup3 = null;
        }
        int i2 = R.id.bg_blur;
        View viewFindViewById = viewGroup3.findViewById(i2);
        ViewGroup viewGroup4 = this.dndLayout;
        if (viewGroup4 == null) {
            kotlin.jvm.internal.n.w("dndLayout");
        } else {
            viewGroup2 = viewGroup4;
        }
        View[] viewArr = {viewFindViewById, viewGroup2.findViewById(i2)};
        for (int i3 = 0; i3 < 2; i3++) {
            viewArr[i3].setTranslationZ(z2 ? 1.0f : 0.0f);
        }
    }

    public static /* synthetic */ void setVolumeContainerTranslationZ$default(VolumeExpandCollapsedAnimator volumeExpandCollapsedAnimator, boolean z2, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            z2 = true;
        }
        volumeExpandCollapsedAnimator.setVolumeContainerTranslationZ(z2);
    }

    public final void calculateFromViewValues(boolean z2) {
        VolumeFromView volumeFromView = this.fromView;
        ViewGroup viewGroup = null;
        if (volumeFromView == null) {
            kotlin.jvm.internal.n.w("fromView");
            volumeFromView = null;
        }
        if (volumeFromView.getContent() == null || volumeFromView.getIcon() == null) {
            volumeFromView = null;
        }
        if (volumeFromView != null) {
            View content = volumeFromView.getContent();
            kotlin.jvm.internal.n.d(content);
            View icon = volumeFromView.getIcon();
            kotlin.jvm.internal.n.d(icon);
            int[] iArr = new int[2];
            content.getLocationOnScreen(iArr);
            ViewLocValue viewLocValue = new ViewLocValue(iArr[0], iArr[1], content.getLeft(), content.getTop(), content.getWidth(), content.getHeight());
            ViewValue viewValue = new ViewValue(icon.getLeft(), icon.getTop(), icon.getWidth(), icon.getHeight());
            if (z2) {
                this.fromShowContentValue = viewLocValue;
                this.fromShowIconValue = viewValue;
            } else {
                this.fromCollapsedContentValue = viewLocValue;
                this.fromCollapsedIconValue = viewValue;
            }
            View content2 = volumeFromView.getContent();
            VolumeBlurFrameLayout volumeBlurFrameLayout = content2 instanceof VolumeBlurFrameLayout ? (VolumeBlurFrameLayout) content2 : null;
            Object tag = volumeBlurFrameLayout != null ? volumeBlurFrameLayout.getTag() : null;
            VolumeColumn volumeColumn = tag instanceof VolumeColumn ? (VolumeColumn) tag : null;
            volumeFromView.setVolumeRadius(volumeColumn != null ? volumeColumn.getRadius() : 0);
        }
        View view = this.mRingerModeLayout;
        if (view == null) {
            kotlin.jvm.internal.n.w("mRingerModeLayout");
            view = null;
        }
        int[] iArr2 = new int[2];
        view.getLocationOnScreen(iArr2);
        ViewLocValue viewLocValue2 = new ViewLocValue(iArr2[0], iArr2[1], view.getLeft(), view.getTop(), view.getWidth(), view.getHeight());
        if (z2) {
            this.fromShowRingerModeLayoutValue = viewLocValue2;
        } else {
            this.fromCollapsedRingerLocValue = viewLocValue2;
        }
        this.fromRingerBtnValueList.clear();
        this.fromRingerBtnIconValueList.clear();
        ViewGroup viewGroup2 = this.ringerLayout;
        if (viewGroup2 == null) {
            kotlin.jvm.internal.n.w("ringerLayout");
            viewGroup2 = null;
        }
        int i2 = R.id.bg_blur;
        View viewFindViewById = viewGroup2.findViewById(i2);
        ViewGroup viewGroup3 = this.dndLayout;
        if (viewGroup3 == null) {
            kotlin.jvm.internal.n.w("dndLayout");
        } else {
            viewGroup = viewGroup3;
        }
        View[] viewArr = {viewFindViewById, viewGroup.findViewById(i2)};
        int i3 = 0;
        for (int i4 = 2; i3 < i4; i4 = 2) {
            View view2 = viewArr[i3];
            int[] iArr3 = new int[i4];
            view2.getLocationOnScreen(iArr3);
            this.fromRingerBtnValueList.add(new ViewLocValue(iArr3[0], iArr3[1], view2.getLeft(), view2.getTop(), view2.getWidth(), view2.getHeight()));
            View viewFindViewById2 = view2.findViewById(R.id.icon);
            this.fromRingerBtnIconValueList.add(new ViewValue(viewFindViewById2.getLeft(), viewFindViewById2.getTop(), viewFindViewById2.getWidth(), viewFindViewById2.getHeight()));
            i3++;
        }
        List<Object> listCalculateRinger = calculateRinger();
        Object obj = listCalculateRinger.get(0);
        kotlin.jvm.internal.n.e(obj, "null cannot be cast to non-null type com.android.systemui.miui.volume.VolumeExpandCollapsedAnimator.ViewLocValue");
        ViewLocValue viewLocValue3 = (ViewLocValue) obj;
        if (z2) {
            this.fromShowRingerModeLayoutValue = viewLocValue3;
        } else {
            this.fromCollapsedRingerLocValue = viewLocValue3;
        }
        this.fromRingerBtnValueList.clear();
        List<ViewLocValue> list = this.fromRingerBtnValueList;
        Object obj2 = listCalculateRinger.get(1);
        kotlin.jvm.internal.n.e(obj2, "null cannot be cast to non-null type kotlin.collections.MutableList<*>");
        List listC = kotlin.jvm.internal.D.c(obj2);
        ArrayList arrayList = new ArrayList(I0.n.o(listC, 10));
        for (Object obj3 : listC) {
            kotlin.jvm.internal.n.e(obj3, "null cannot be cast to non-null type com.android.systemui.miui.volume.VolumeExpandCollapsedAnimator.ViewLocValue");
            arrayList.add((ViewLocValue) obj3);
        }
        list.addAll(arrayList);
        this.fromRingerBtnIconValueList.clear();
        List<ViewValue> list2 = this.fromRingerBtnIconValueList;
        Object obj4 = listCalculateRinger.get(2);
        kotlin.jvm.internal.n.e(obj4, "null cannot be cast to non-null type kotlin.collections.MutableList<*>");
        List listC2 = kotlin.jvm.internal.D.c(obj4);
        ArrayList arrayList2 = new ArrayList(I0.n.o(listC2, 10));
        for (Object obj5 : listC2) {
            kotlin.jvm.internal.n.e(obj5, "null cannot be cast to non-null type com.android.systemui.miui.volume.VolumeExpandCollapsedAnimator.ViewValue");
            arrayList2.add((ViewValue) obj5);
        }
        list2.addAll(arrayList2);
    }

    public final void calculateHideViewValues() {
        calculateFromViewValues(false);
    }

    public final void calculateToViewValues() {
        ArrayList arrayList;
        VolumeToView volumeToView;
        View view = this.mVolumeView;
        if (view == null) {
            kotlin.jvm.internal.n.w("mVolumeView");
            view = null;
        }
        int i2 = 2;
        int[] iArr = new int[2];
        view.getLocationOnScreen(iArr);
        char c2 = 0;
        ViewLocValue viewLocValue = new ViewLocValue(iArr[0], iArr[1], view.getLeft(), view.getTop(), view.getWidth(), view.getHeight());
        View view2 = this.mExpandBgView;
        if (view2 == null) {
            kotlin.jvm.internal.n.w("mExpandBgView");
            view2 = null;
        }
        int[] iArr2 = new int[2];
        view2.getLocationOnScreen(iArr2);
        ViewLocValue viewLocValue2 = new ViewLocValue(iArr2[0], iArr2[1], view2.getLeft(), view2.getTop(), view2.getWidth(), view2.getHeight());
        int[] iArr3 = new int[2];
        View view3 = this.mShadowView;
        if (view3 == null) {
            kotlin.jvm.internal.n.w("mShadowView");
            view3 = null;
        }
        view3.getLocationOnScreen(iArr3);
        int i3 = iArr3[0];
        int i4 = iArr3[1];
        View view4 = this.mShadowView;
        if (view4 == null) {
            kotlin.jvm.internal.n.w("mShadowView");
            view4 = null;
        }
        int left = view4.getLeft();
        View view5 = this.mShadowView;
        if (view5 == null) {
            kotlin.jvm.internal.n.w("mShadowView");
            view5 = null;
        }
        int top = view5.getTop();
        View view6 = this.mShadowView;
        if (view6 == null) {
            kotlin.jvm.internal.n.w("mShadowView");
            view6 = null;
        }
        int width = view6.getWidth();
        View view7 = this.mShadowView;
        if (view7 == null) {
            kotlin.jvm.internal.n.w("mShadowView");
            view7 = null;
        }
        ViewLocValue viewLocValue3 = new ViewLocValue(i3, i4, left, top, width, view7.getHeight());
        this.shadowPaddingLeft = (viewLocValue3.getWidth() - viewLocValue2.getWidth()) / 2;
        this.shadowPaddingTop = viewLocValue2.getTop() - viewLocValue3.getTop();
        this.shadowPaddingBottom = ((viewLocValue3.getLocTop() + viewLocValue3.getHeight()) - viewLocValue2.getLocTop()) - viewLocValue2.getHeight();
        ViewGroup viewGroup = this.volumeDialogContent;
        if (viewGroup == null) {
            kotlin.jvm.internal.n.w("volumeDialogContent");
            viewGroup = null;
        }
        int[] iArr4 = new int[2];
        viewGroup.getLocationOnScreen(iArr4);
        ViewLocValue viewLocValue4 = new ViewLocValue(iArr4[0], iArr4[1], viewGroup.getLeft(), viewGroup.getTop(), viewGroup.getWidth(), viewGroup.getHeight());
        ViewGroup viewGroup2 = this.volumeDialogColumns;
        if (viewGroup2 == null) {
            kotlin.jvm.internal.n.w("volumeDialogColumns");
            viewGroup2 = null;
        }
        int[] iArr5 = new int[2];
        viewGroup2.getLocationOnScreen(iArr5);
        ViewLocValue viewLocValue5 = new ViewLocValue(iArr5[0], iArr5[1], viewGroup2.getLeft(), viewGroup2.getTop(), viewGroup2.getWidth(), viewGroup2.getHeight());
        ArrayList arrayList2 = new ArrayList();
        ArrayList arrayList3 = new ArrayList();
        List<VolumeColumn> list = this.volumeColumns;
        if (list != null) {
            arrayList = new ArrayList();
            for (Object obj : list) {
                if (((VolumeColumn) obj).getView().getVisibility() == 0) {
                    arrayList.add(obj);
                }
            }
            Iterator it = arrayList.iterator();
            int i5 = 0;
            while (it.hasNext()) {
                Object next = it.next();
                int i6 = i5 + 1;
                if (i5 < 0) {
                    I0.m.n();
                }
                VolumeColumn volumeColumn = (VolumeColumn) next;
                View view8 = volumeColumn.getView();
                int[] iArr6 = new int[i2];
                view8.getLocationOnScreen(iArr6);
                arrayList2.add(new ViewLocValue(iArr6[c2], iArr6[1], view8.getLeft(), view8.getTop(), view8.getWidth(), view8.getHeight()));
                ImageView icon = volumeColumn.getIcon();
                Iterator it2 = it;
                arrayList3.add(new ViewValue(icon.getLeft(), icon.getTop(), icon.getWidth(), icon.getHeight()));
                if (i5 == 0) {
                    VolumeToView volumeToView2 = this.toView;
                    if (volumeToView2 == null) {
                        kotlin.jvm.internal.n.w("toView");
                        volumeToView2 = null;
                    }
                    volumeToView2.setVolumeRadius(volumeColumn.getRadius());
                }
                i5 = i6;
                it = it2;
                i2 = 2;
                c2 = 0;
            }
        } else {
            arrayList = null;
        }
        this.volumeColumnList = arrayList;
        List<Object> listCalculateRinger = calculateRinger();
        Object obj2 = listCalculateRinger.get(0);
        kotlin.jvm.internal.n.e(obj2, "null cannot be cast to non-null type com.android.systemui.miui.volume.VolumeExpandCollapsedAnimator.ViewLocValue");
        ViewLocValue viewLocValue6 = (ViewLocValue) obj2;
        Object obj3 = listCalculateRinger.get(1);
        kotlin.jvm.internal.n.e(obj3, "null cannot be cast to non-null type kotlin.collections.MutableList<*>");
        List listC = kotlin.jvm.internal.D.c(obj3);
        ArrayList arrayList4 = new ArrayList(I0.n.o(listC, 10));
        for (Object obj4 : listC) {
            kotlin.jvm.internal.n.e(obj4, "null cannot be cast to non-null type com.android.systemui.miui.volume.VolumeExpandCollapsedAnimator.ViewLocValue");
            arrayList4.add((ViewLocValue) obj4);
        }
        Object obj5 = listCalculateRinger.get(2);
        kotlin.jvm.internal.n.e(obj5, "null cannot be cast to non-null type kotlin.collections.MutableList<*>");
        List listC2 = kotlin.jvm.internal.D.c(obj5);
        ArrayList arrayList5 = new ArrayList(I0.n.o(listC2, 10));
        for (Object obj6 : listC2) {
            kotlin.jvm.internal.n.e(obj6, "null cannot be cast to non-null type com.android.systemui.miui.volume.VolumeExpandCollapsedAnimator.ViewValue");
            arrayList5.add((ViewValue) obj6);
        }
        Object obj7 = listCalculateRinger.get(3);
        kotlin.jvm.internal.n.e(obj7, "null cannot be cast to non-null type kotlin.collections.MutableList<*>");
        List listC3 = kotlin.jvm.internal.D.c(obj7);
        ArrayList arrayList6 = new ArrayList(I0.n.o(listC3, 10));
        for (Object obj8 : listC3) {
            kotlin.jvm.internal.n.e(obj8, "null cannot be cast to non-null type com.android.systemui.miui.volume.VolumeExpandCollapsedAnimator.ViewLocValue");
            arrayList6.add((ViewLocValue) obj8);
        }
        ViewLocValue viewLocValueCheckViewLocValue = checkViewLocValue(this.fromShowContentValue);
        ViewValue viewValueCheckViewValue = checkViewValue(this.fromShowIconValue);
        ViewLocValue viewLocValueCheckViewLocValue2 = checkViewLocValue(this.fromShowRingerModeLayoutValue);
        List<ViewLocValue> list2 = this.fromRingerBtnValueList;
        List<ViewValue> list3 = this.fromRingerBtnIconValueList;
        VolumeFromView volumeFromView = this.fromView;
        if (volumeFromView == null) {
            kotlin.jvm.internal.n.w("fromView");
            volumeFromView = null;
        }
        int volumeRadius = volumeFromView.getVolumeRadius();
        VolumeToView volumeToView3 = this.toView;
        if (volumeToView3 == null) {
            kotlin.jvm.internal.n.w("toView");
            volumeToView3 = null;
        }
        int volumeRadius2 = volumeToView3.getVolumeRadius();
        VolumeToView volumeToView4 = this.toView;
        if (volumeToView4 == null) {
            kotlin.jvm.internal.n.w("toView");
            volumeToView = null;
        } else {
            volumeToView = volumeToView4;
        }
        this.animValue = new AnimValue(viewLocValueCheckViewLocValue, viewValueCheckViewValue, viewLocValue, viewLocValue2, viewLocValue3, viewLocValue4, viewLocValue5, arrayList2, arrayList3, viewLocValueCheckViewLocValue2, list2, list3, viewLocValue6, arrayList4, arrayList5, arrayList6, volumeRadius, volumeRadius2, volumeToView.getExpandBgRadius());
    }

    public final void cancel() {
        IStateStyle iStateStyle = this.anim;
        if (iStateStyle != null) {
            iStateStyle.cancel();
        }
    }

    public final void clean() {
        Folme.clean(this);
        setSuppressLayout(false);
    }

    public final void collapse(boolean z2, TransitionListener listener) {
        FloatProperty<VolumeExpandCollapsedAnimator> floatProperty;
        FloatProperty<VolumeExpandCollapsedAnimator> floatProperty2;
        kotlin.jvm.internal.n.g(listener, "listener");
        AnimValue animValue = this.animValue;
        H0.s sVar = null;
        if (animValue != null) {
            ViewLocValue viewLocValue = this.fromCollapsedContentValue;
            if (viewLocValue == null || this.fromCollapsedIconValue == null) {
                animValue = null;
            }
            if (animValue != null) {
                kotlin.jvm.internal.n.d(viewLocValue);
                animValue.setFromVolume(viewLocValue);
                ViewValue viewValue = this.fromCollapsedIconValue;
                kotlin.jvm.internal.n.d(viewValue);
                animValue.setFromVolumeIcon(viewValue);
                animValue.init();
                sVar = H0.s.f314a;
            }
        }
        if (sVar == null) {
            Log.e(TAG, "collapse: fromView null return!");
            return;
        }
        boolean zCheckCollapsedPositionAvailable = checkCollapsedPositionAvailable();
        boolean z3 = z2 && this.expandWithAnim && zCheckCollapsedPositionAvailable;
        if (!z3) {
            this.mMotion.setVolumeDialogVisible(false, "collapsed: " + z2 + " & " + this.expandWithAnim + " & " + zCheckCollapsedPositionAvailable + " error");
        }
        Log.i(TAG, "collapse: " + z2 + ", " + this.expandWithAnim + ", " + zCheckCollapsedPositionAvailable + " --> " + z3 + ", " + this.animValue);
        this.startTime = System.currentTimeMillis();
        this.mCallback = listener;
        this.mExpanded = false;
        this.animViewConfigTag.clear();
        onAnimBegin(true);
        AnimConfig animConfig = this.animConfig;
        animConfig.setSpecial(SIZE, FolmeUtilsExtKt.getEASE_COLLAPSE_SIZE(), new float[0]);
        animConfig.setSpecial(COLOR, FolmeUtilsExtKt.getEASE_COLLAPSE_COLOR(), new float[0]);
        animConfig.setSpecial(POSITION, FolmeUtilsExtKt.getEASE_COLLAPSE_POSITION(), new float[0]);
        if (!z3) {
            this.anim.cancel();
            ArrayList arrayList = new ArrayList();
            arrayList.add(SIZE);
            arrayList.add(Float.valueOf(0.0f));
            arrayList.add(COLOR);
            arrayList.add(Float.valueOf(0.0f));
            arrayList.add(POSITION);
            arrayList.add(Float.valueOf(0.0f));
            List<VolumeColumn> list = this.volumeColumnList;
            if (list != null) {
                int i2 = 0;
                for (Object obj : list) {
                    int i3 = i2 + 1;
                    if (i2 < 0) {
                        I0.m.n();
                    }
                    FloatProperty<VolumeExpandCollapsedAnimator>[] floatPropertyArr = this.volumesPropertyX;
                    if (floatPropertyArr != null && (floatProperty = floatPropertyArr[i2]) != null) {
                        List<String> list2 = this.animViewConfigTag;
                        String name = floatProperty.getName();
                        kotlin.jvm.internal.n.f(name, "getName(...)");
                        list2.add(name);
                        this.animConfig.setSpecial(floatProperty, FolmeUtilsExtKt.getEASE_COLLAPSE_SIZE(), new float[0]);
                        arrayList.add(floatProperty);
                        arrayList.add(Float.valueOf(0.0f));
                    }
                    i2 = i3;
                }
            }
            Object[] array = arrayList.toArray();
            this.anim.setTo(Arrays.copyOf(array, array.length));
            this.frameCallback.doFrame(0L);
            onAnimComplete(true);
            return;
        }
        List<String> list3 = this.animViewConfigTag;
        String name2 = SIZE.getName();
        kotlin.jvm.internal.n.f(name2, "getName(...)");
        list3.add(name2);
        String name3 = COLOR.getName();
        kotlin.jvm.internal.n.f(name3, "getName(...)");
        list3.add(name3);
        String name4 = POSITION.getName();
        kotlin.jvm.internal.n.f(name4, "getName(...)");
        list3.add(name4);
        ArrayList arrayList2 = new ArrayList();
        arrayList2.add(SIZE);
        arrayList2.add(Float.valueOf(0.0f));
        arrayList2.add(COLOR);
        arrayList2.add(Float.valueOf(0.0f));
        arrayList2.add(POSITION);
        arrayList2.add(Float.valueOf(0.0f));
        List<VolumeColumn> list4 = this.volumeColumnList;
        if (list4 != null) {
            int i4 = 0;
            for (Object obj2 : list4) {
                int i5 = i4 + 1;
                if (i4 < 0) {
                    I0.m.n();
                }
                FloatProperty<VolumeExpandCollapsedAnimator>[] floatPropertyArr2 = this.volumesPropertyX;
                if (floatPropertyArr2 != null && (floatProperty2 = floatPropertyArr2[i4]) != null) {
                    List<String> list5 = this.animViewConfigTag;
                    String name5 = floatProperty2.getName();
                    kotlin.jvm.internal.n.f(name5, "getName(...)");
                    list5.add(name5);
                    this.animConfig.setSpecial(floatProperty2, FolmeUtilsExtKt.getEASE_COLLAPSE_SIZE(), new float[0]);
                    arrayList2.add(floatProperty2);
                    arrayList2.add(Float.valueOf(0.0f));
                }
                i4 = i5;
            }
        }
        Object[] array2 = arrayList2.toArray();
        IStateStyle iStateStyle = this.anim;
        kotlin.jvm.internal.B b2 = new kotlin.jvm.internal.B(2);
        b2.b(array2);
        b2.a(this.animConfig);
        iStateStyle.to(b2.d(new Object[b2.c()]));
    }

    /* JADX WARN: Removed duplicated region for block: B:14:0x0029  */
    /* JADX WARN: Removed duplicated region for block: B:32:0x0059  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void expand(boolean r9, miuix.animation.listener.TransitionListener r10) {
        /*
            Method dump skipped, instruction units count: 390
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.miui.volume.VolumeExpandCollapsedAnimator.expand(boolean, miuix.animation.listener.TransitionListener):void");
    }

    public final Context getMContext() {
        return this.mContext;
    }

    public final VolumeExpandCollapsedAnimator initView(View volumeView, View expandBgView, View shadowView, View superVolume, View ringerModeLayout) {
        kotlin.jvm.internal.n.g(volumeView, "volumeView");
        kotlin.jvm.internal.n.g(expandBgView, "expandBgView");
        kotlin.jvm.internal.n.g(shadowView, "shadowView");
        kotlin.jvm.internal.n.g(superVolume, "superVolume");
        kotlin.jvm.internal.n.g(ringerModeLayout, "ringerModeLayout");
        this.mVolumeView = volumeView;
        this.mExpandBgView = expandBgView;
        this.mShadowView = shadowView;
        this.mSuperVolume = superVolume;
        this.mRingerModeLayout = ringerModeLayout;
        View view = null;
        if (volumeView == null) {
            kotlin.jvm.internal.n.w("mVolumeView");
            volumeView = null;
        }
        View viewFindViewById = volumeView.findViewById(R.id.volume_dialog_columns);
        kotlin.jvm.internal.n.f(viewFindViewById, "findViewById(...)");
        this.volumeDialogColumns = (ViewGroup) viewFindViewById;
        View view2 = this.mVolumeView;
        if (view2 == null) {
            kotlin.jvm.internal.n.w("mVolumeView");
            view2 = null;
        }
        View viewFindViewById2 = view2.findViewById(R.id.volume_dialog_column_temp);
        kotlin.jvm.internal.n.f(viewFindViewById2, "findViewById(...)");
        this.volumeDialogColumnTemp = (ViewGroup) viewFindViewById2;
        View view3 = this.mVolumeView;
        if (view3 == null) {
            kotlin.jvm.internal.n.w("mVolumeView");
            view3 = null;
        }
        View viewFindViewById3 = view3.findViewById(R.id.volume_dialog_column_collapsed);
        kotlin.jvm.internal.n.f(viewFindViewById3, "findViewById(...)");
        this.volumeDialogColumnCollapsed = (ViewGroup) viewFindViewById3;
        View view4 = this.mVolumeView;
        if (view4 == null) {
            kotlin.jvm.internal.n.w("mVolumeView");
        } else {
            view = view4;
        }
        View viewFindViewById4 = view.findViewById(R.id.volume_dialog_content);
        kotlin.jvm.internal.n.f(viewFindViewById4, "findViewById(...)");
        this.volumeDialogContent = (ViewGroup) viewFindViewById4;
        View viewFindViewById5 = ringerModeLayout.findViewById(R.id.ringer_layout);
        kotlin.jvm.internal.n.f(viewFindViewById5, "findViewById(...)");
        this.ringerLayout = (ViewGroup) viewFindViewById5;
        View viewFindViewById6 = ringerModeLayout.findViewById(R.id.dnd_layout);
        kotlin.jvm.internal.n.f(viewFindViewById6, "findViewById(...)");
        this.dndLayout = (ViewGroup) viewFindViewById6;
        this.fromView = new VolumeFromView() { // from class: com.android.systemui.miui.volume.VolumeExpandCollapsedAnimator.initView.1
            private View content;
            private View icon;
            private int volumeRadius;

            /* JADX WARN: Removed duplicated region for block: B:20:0x0038  */
            @Override // com.android.systemui.miui.volume.VolumeFromView
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct add '--show-bad-code' argument
            */
            public android.view.View getContent() {
                /*
                    r3 = this;
                    com.android.systemui.miui.volume.VolumeExpandCollapsedAnimator r0 = com.android.systemui.miui.volume.VolumeExpandCollapsedAnimator.this
                    android.view.ViewGroup r0 = com.android.systemui.miui.volume.VolumeExpandCollapsedAnimator.access$getVolumeDialogColumnTemp$p(r0)
                    r1 = 0
                    if (r0 != 0) goto Lf
                    java.lang.String r0 = "volumeDialogColumnTemp"
                    kotlin.jvm.internal.n.w(r0)
                    r0 = r1
                Lf:
                    int r2 = r0.getVisibility()
                    if (r2 != 0) goto L16
                    goto L17
                L16:
                    r0 = r1
                L17:
                    if (r0 == 0) goto L38
                    e1.e r0 = androidx.core.view.ViewGroupKt.getChildren(r0)
                    if (r0 == 0) goto L38
                    com.android.systemui.miui.volume.VolumeExpandCollapsedAnimator$initView$1$content$tempFromView$2 r2 = com.android.systemui.miui.volume.VolumeExpandCollapsedAnimator$initView$1$content$tempFromView$2.INSTANCE
                    e1.e r0 = e1.l.i(r0, r2)
                    if (r0 == 0) goto L38
                    int r2 = e1.l.h(r0)
                    if (r2 <= 0) goto L2e
                    goto L2f
                L2e:
                    r0 = r1
                L2f:
                    if (r0 == 0) goto L38
                    java.lang.Object r0 = e1.l.l(r0)
                    android.view.View r0 = (android.view.View) r0
                    goto L39
                L38:
                    r0 = r1
                L39:
                    if (r0 != 0) goto L65
                    com.android.systemui.miui.volume.VolumeExpandCollapsedAnimator r3 = com.android.systemui.miui.volume.VolumeExpandCollapsedAnimator.this
                    android.view.ViewGroup r3 = com.android.systemui.miui.volume.VolumeExpandCollapsedAnimator.access$getVolumeDialogColumnCollapsed$p(r3)
                    if (r3 != 0) goto L49
                    java.lang.String r3 = "volumeDialogColumnCollapsed"
                    kotlin.jvm.internal.n.w(r3)
                    r3 = r1
                L49:
                    e1.e r3 = androidx.core.view.ViewGroupKt.getChildren(r3)
                    com.android.systemui.miui.volume.VolumeExpandCollapsedAnimator$initView$1$content$1 r0 = com.android.systemui.miui.volume.VolumeExpandCollapsedAnimator$initView$1$content$1.INSTANCE
                    e1.e r3 = e1.l.i(r3, r0)
                    int r0 = e1.l.h(r3)
                    if (r0 <= 0) goto L5a
                    goto L5b
                L5a:
                    r3 = r1
                L5b:
                    if (r3 == 0) goto L66
                    java.lang.Object r3 = e1.l.l(r3)
                    r1 = r3
                    android.view.View r1 = (android.view.View) r1
                    goto L66
                L65:
                    r1 = r0
                L66:
                    return r1
                */
                throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.miui.volume.VolumeExpandCollapsedAnimator.AnonymousClass1.getContent():android.view.View");
            }

            @Override // com.android.systemui.miui.volume.VolumeFromView
            public View getIcon() {
                View content = getContent();
                if (content != null) {
                    return content.findViewById(R.id.volume_column_icon);
                }
                return null;
            }

            @Override // com.android.systemui.miui.volume.VolumeFromView
            public int getVolumeRadius() {
                return this.volumeRadius;
            }

            @Override // com.android.systemui.miui.volume.VolumeFromView
            public void setContent(View view5) {
                this.content = view5;
            }

            @Override // com.android.systemui.miui.volume.VolumeFromView
            public void setIcon(View view5) {
                this.icon = view5;
            }

            @Override // com.android.systemui.miui.volume.VolumeFromView
            public void setVolumeRadius(int i2) {
                this.volumeRadius = i2;
            }
        };
        this.toView = new VolumeToView() { // from class: com.android.systemui.miui.volume.VolumeExpandCollapsedAnimator.initView.2
            private int expandBgRadius;
            private View panelBg;
            private int volumeRadius;

            {
                this.expandBgRadius = MiuiVolumeDialogRes.getBgRadius(VolumeExpandCollapsedAnimator.this.getMContext());
            }

            @Override // com.android.systemui.miui.volume.VolumeToView
            public int getExpandBgRadius() {
                return this.expandBgRadius;
            }

            @Override // com.android.systemui.miui.volume.VolumeToView
            public View getPanelBg() {
                View view5 = VolumeExpandCollapsedAnimator.this.mExpandBgView;
                if (view5 != null) {
                    return view5;
                }
                kotlin.jvm.internal.n.w("mExpandBgView");
                return null;
            }

            @Override // com.android.systemui.miui.volume.VolumeToView
            public int getVolumeRadius() {
                return this.volumeRadius;
            }

            @Override // com.android.systemui.miui.volume.VolumeToView
            public void setExpandBgRadius(int i2) {
                this.expandBgRadius = i2;
            }

            @Override // com.android.systemui.miui.volume.VolumeToView
            public void setPanelBg(View view5) {
                this.panelBg = view5;
            }

            @Override // com.android.systemui.miui.volume.VolumeToView
            public void setVolumeRadius(int i2) {
                this.volumeRadius = i2;
            }
        };
        return this;
    }

    public final boolean isRunning() {
        boolean z2 = this.mIsAnimating;
        Log.i(TAG, "isRunning: " + z2 + ", mExpanded: " + this.mExpanded);
        return z2;
    }

    public final void setVolumeColumns(List<VolumeColumn> columns) {
        kotlin.jvm.internal.n.g(columns, "columns");
        this.volumeColumns = columns;
    }
}
