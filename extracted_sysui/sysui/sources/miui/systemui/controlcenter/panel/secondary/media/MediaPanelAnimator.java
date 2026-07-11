package miui.systemui.controlcenter.panel.secondary.media;

import H0.s;
import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.TextView;
import com.android.systemui.MiPlayDetailViewModel;
import com.android.systemui.QSControlMiPlayDetailHeader;
import com.miui.miplay.audio.data.MediaMetaData;
import java.util.Collection;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.n;
import miui.systemui.animation.FolmeUtilsExtKt;
import miui.systemui.controlcenter.dagger.ControlCenterScope;
import miui.systemui.controlcenter.events.ControlCenterScenarioTracker;
import miui.systemui.controlcenter.panel.secondary.MediaFromView;
import miui.systemui.controlcenter.panel.secondary.MediaPanelParams;
import miui.systemui.controlcenter.panel.secondary.SecondaryPanelAnimatorBase;
import miui.systemui.controlcenter.widget.MainPanelRecyclerView;
import miui.systemui.controlcenter.windowview.ControlCenterWindowViewController;
import miui.systemui.util.CommonUtils;
import miuix.animation.Folme;
import miuix.animation.FolmeEase;
import miuix.animation.IStateStyle;
import miuix.animation.base.AnimConfig;
import miuix.animation.listener.TransitionListener;
import miuix.animation.listener.UpdateInfo;
import miuix.animation.property.FloatProperty;
import miuix.animation.utils.EaseManager;

/* JADX INFO: loaded from: classes.dex */
@ControlCenterScope
public final class MediaPanelAnimator extends SecondaryPanelAnimatorBase<MediaPanelParams> {
    private static MediaPanelAnimator$Companion$ALPHA_BG$1 ALPHA_BG;
    private static MediaPanelAnimator$Companion$ALPHA_DEVICE_ICON$1 ALPHA_DEVICE_ICON;
    private static MediaPanelAnimator$Companion$ALPHA_PROGRESS$1 ALPHA_PROGRESS;
    private static MediaPanelAnimator$Companion$COLOR$1 COLOR;
    private static MediaPanelAnimator$Companion$CONTENT_COLOR$1 CONTENT_COLOR;
    public static final Companion Companion = new Companion(null);
    private static final EaseManager.EaseStyle EASE_COLLAPSE_ALPHA_BG;
    private static final EaseManager.EaseStyle EASE_COLLAPSE_ALPHA_DEVICE_ICON;
    private static final EaseManager.EaseStyle EASE_COLLAPSE_ALPHA_PROGRESS;
    private static final EaseManager.EaseStyle EASE_COLLAPSE_FONT_SIZE;
    private static final EaseManager.EaseStyle EASE_COLLAPSE_FONT_WIDTH_HEIGHT;
    private static final EaseManager.EaseStyle EASE_EXPAND_ALPHA_BG;
    private static final EaseManager.EaseStyle EASE_EXPAND_ALPHA_DEVICE_ICON;
    private static final EaseManager.EaseStyle EASE_EXPAND_ALPHA_PROGRESS;
    private static final EaseManager.EaseStyle EASE_EXPAND_FONT_SIZE;
    private static final EaseManager.EaseStyle EASE_EXPAND_FONT_WIDTH_HEIGHT;
    private static MediaPanelAnimator$Companion$FONT_SIZE$1 FONT_SIZE;
    private static MediaPanelAnimator$Companion$FONT_WIDTH_HEIGHT$1 FONT_WIDTH_HEIGHT;
    private static MediaPanelAnimator$Companion$POSITION$1 POSITION;
    private static MediaPanelAnimator$Companion$SIZE$1 SIZE;
    private float alphaBg;
    private float alphaDeviceIcon;
    private float alphaProgress;
    private final IStateStyle anim;
    private final AnimConfig animConfig;
    private AnimValue animValue;
    private float color;
    private float contentColor;
    private final MediaPanelDelegate contentController;
    private final Context context;
    private MediaMetaData expandMediaMetaData;
    private float fontSize;
    private float fontWidthHeight;
    private float fromNoPlayFontSize;
    private MediaFromView fromView;
    private AnimValue lastAnimValue;
    private final E0.a mainPanelController;
    private final float noPlayValue;
    private final E0.a panelController;
    private float position;
    private float size;
    private float subtitleFromFontSize;
    private float subtitleToFontSize;
    private float titleFromFontSize;
    private float titleToFontSize;
    private float toNoPlayFontSize;
    private final E0.a windowViewController;

    public static final class AnimValue {
        private final SecondaryPanelAnimatorBase.ViewLocValue fromArtist;
        private final SecondaryPanelAnimatorBase.ViewLocValue fromAudioContainer;
        private final SecondaryPanelAnimatorBase.ViewValue fromAudioNext;
        private final SecondaryPanelAnimatorBase.ViewValue fromAudioPlay;
        private final SecondaryPanelAnimatorBase.ViewValue fromAudioPrev;
        private final float fromCenterX;
        private final float fromCenterY;
        private final SecondaryPanelAnimatorBase.ViewLocValue fromContent;
        private final float fromCornerRadius;
        private final SecondaryPanelAnimatorBase.ViewLocValue fromCover;
        private final float fromCoverRadius;
        private final SecondaryPanelAnimatorBase.ViewLocValue fromNoPlay;
        private final SecondaryPanelAnimatorBase.ViewLocValue fromTitle;
        private final SecondaryPanelAnimatorBase.ViewLocValue toAppIconContainer;
        private final SecondaryPanelAnimatorBase.ViewLocValue toAudioContainer;
        private final SecondaryPanelAnimatorBase.ViewValue toAudioNext;
        private final SecondaryPanelAnimatorBase.ViewValue toAudioPlay;
        private final SecondaryPanelAnimatorBase.ViewValue toAudioPrev;
        private final float toCenterX;
        private final float toCenterY;
        private final SecondaryPanelAnimatorBase.ViewLocValue toContent;
        private final float toCornerRadius;
        private final SecondaryPanelAnimatorBase.ViewLocValue toCover;
        private final SecondaryPanelAnimatorBase.ViewLocValue toCoverParent;
        private final float toCoverRadius;
        private final SecondaryPanelAnimatorBase.ViewValue toDivider;
        private final SecondaryPanelAnimatorBase.ViewValue toHeader;
        private final SecondaryPanelAnimatorBase.ViewValue toList;
        private final SecondaryPanelAnimatorBase.ViewLocValue toMetaInfo;
        private final SecondaryPanelAnimatorBase.ViewLocValue toNoPlay;
        private final SecondaryPanelAnimatorBase.ViewValue toProgressContainer;
        private final SecondaryPanelAnimatorBase.ViewLocValue toSubtitle;
        private final SecondaryPanelAnimatorBase.ViewLocValue toTitle;
        private final SecondaryPanelAnimatorBase.ViewValue toTvContainer;
        private final SecondaryPanelAnimatorBase.ViewValue toVolumeContainer;

        public AnimValue(SecondaryPanelAnimatorBase.ViewLocValue fromContent, SecondaryPanelAnimatorBase.ViewLocValue fromCover, SecondaryPanelAnimatorBase.ViewLocValue fromNoPlay, SecondaryPanelAnimatorBase.ViewLocValue fromTitle, SecondaryPanelAnimatorBase.ViewLocValue fromArtist, SecondaryPanelAnimatorBase.ViewLocValue fromAudioContainer, SecondaryPanelAnimatorBase.ViewValue fromAudioPrev, SecondaryPanelAnimatorBase.ViewValue fromAudioPlay, SecondaryPanelAnimatorBase.ViewValue fromAudioNext, SecondaryPanelAnimatorBase.ViewLocValue toContent, SecondaryPanelAnimatorBase.ViewValue viewValue, SecondaryPanelAnimatorBase.ViewLocValue viewLocValue, SecondaryPanelAnimatorBase.ViewLocValue viewLocValue2, SecondaryPanelAnimatorBase.ViewLocValue viewLocValue3, SecondaryPanelAnimatorBase.ViewLocValue viewLocValue4, SecondaryPanelAnimatorBase.ViewLocValue viewLocValue5, SecondaryPanelAnimatorBase.ViewLocValue viewLocValue6, SecondaryPanelAnimatorBase.ViewLocValue viewLocValue7, SecondaryPanelAnimatorBase.ViewLocValue viewLocValue8, SecondaryPanelAnimatorBase.ViewValue viewValue2, SecondaryPanelAnimatorBase.ViewValue viewValue3, SecondaryPanelAnimatorBase.ViewValue viewValue4, SecondaryPanelAnimatorBase.ViewValue viewValue5, SecondaryPanelAnimatorBase.ViewValue viewValue6, SecondaryPanelAnimatorBase.ViewValue viewValue7, SecondaryPanelAnimatorBase.ViewValue viewValue8, SecondaryPanelAnimatorBase.ViewValue viewValue9, float f2, float f3, float f4, float f5) {
            n.g(fromContent, "fromContent");
            n.g(fromCover, "fromCover");
            n.g(fromNoPlay, "fromNoPlay");
            n.g(fromTitle, "fromTitle");
            n.g(fromArtist, "fromArtist");
            n.g(fromAudioContainer, "fromAudioContainer");
            n.g(fromAudioPrev, "fromAudioPrev");
            n.g(fromAudioPlay, "fromAudioPlay");
            n.g(fromAudioNext, "fromAudioNext");
            n.g(toContent, "toContent");
            this.fromContent = fromContent;
            this.fromCover = fromCover;
            this.fromNoPlay = fromNoPlay;
            this.fromTitle = fromTitle;
            this.fromArtist = fromArtist;
            this.fromAudioContainer = fromAudioContainer;
            this.fromAudioPrev = fromAudioPrev;
            this.fromAudioPlay = fromAudioPlay;
            this.fromAudioNext = fromAudioNext;
            this.toContent = toContent;
            this.toHeader = viewValue;
            this.toMetaInfo = viewLocValue;
            this.toCover = viewLocValue2;
            this.toCoverParent = viewLocValue3;
            this.toAppIconContainer = viewLocValue4;
            this.toNoPlay = viewLocValue5;
            this.toTitle = viewLocValue6;
            this.toSubtitle = viewLocValue7;
            this.toAudioContainer = viewLocValue8;
            this.toAudioPrev = viewValue2;
            this.toAudioPlay = viewValue3;
            this.toAudioNext = viewValue4;
            this.toProgressContainer = viewValue5;
            this.toTvContainer = viewValue6;
            this.toVolumeContainer = viewValue7;
            this.toDivider = viewValue8;
            this.toList = viewValue9;
            this.fromCornerRadius = f2;
            this.fromCoverRadius = f3;
            this.toCornerRadius = f4;
            this.toCoverRadius = f5;
            float f6 = 2;
            this.fromCenterX = fromContent.getLocLeft() + (fromContent.getWidth() / f6);
            this.fromCenterY = fromContent.getLocTop() + (fromContent.getHeight() / f6);
            this.toCenterX = toContent.getLocLeft() + (toContent.getWidth() / f6);
            this.toCenterY = toContent.getLocTop() + (toContent.getHeight() / f6);
        }

        public final SecondaryPanelAnimatorBase.ViewLocValue component1() {
            return this.fromContent;
        }

        public final SecondaryPanelAnimatorBase.ViewLocValue component10() {
            return this.toContent;
        }

        public final SecondaryPanelAnimatorBase.ViewValue component11() {
            return this.toHeader;
        }

        public final SecondaryPanelAnimatorBase.ViewLocValue component12() {
            return this.toMetaInfo;
        }

        public final SecondaryPanelAnimatorBase.ViewLocValue component13() {
            return this.toCover;
        }

        public final SecondaryPanelAnimatorBase.ViewLocValue component14() {
            return this.toCoverParent;
        }

        public final SecondaryPanelAnimatorBase.ViewLocValue component15() {
            return this.toAppIconContainer;
        }

        public final SecondaryPanelAnimatorBase.ViewLocValue component16() {
            return this.toNoPlay;
        }

        public final SecondaryPanelAnimatorBase.ViewLocValue component17() {
            return this.toTitle;
        }

        public final SecondaryPanelAnimatorBase.ViewLocValue component18() {
            return this.toSubtitle;
        }

        public final SecondaryPanelAnimatorBase.ViewLocValue component19() {
            return this.toAudioContainer;
        }

        public final SecondaryPanelAnimatorBase.ViewLocValue component2() {
            return this.fromCover;
        }

        public final SecondaryPanelAnimatorBase.ViewValue component20() {
            return this.toAudioPrev;
        }

        public final SecondaryPanelAnimatorBase.ViewValue component21() {
            return this.toAudioPlay;
        }

        public final SecondaryPanelAnimatorBase.ViewValue component22() {
            return this.toAudioNext;
        }

        public final SecondaryPanelAnimatorBase.ViewValue component23() {
            return this.toProgressContainer;
        }

        public final SecondaryPanelAnimatorBase.ViewValue component24() {
            return this.toTvContainer;
        }

        public final SecondaryPanelAnimatorBase.ViewValue component25() {
            return this.toVolumeContainer;
        }

        public final SecondaryPanelAnimatorBase.ViewValue component26() {
            return this.toDivider;
        }

        public final SecondaryPanelAnimatorBase.ViewValue component27() {
            return this.toList;
        }

        public final float component28() {
            return this.fromCornerRadius;
        }

        public final float component29() {
            return this.fromCoverRadius;
        }

        public final SecondaryPanelAnimatorBase.ViewLocValue component3() {
            return this.fromNoPlay;
        }

        public final float component30() {
            return this.toCornerRadius;
        }

        public final float component31() {
            return this.toCoverRadius;
        }

        public final SecondaryPanelAnimatorBase.ViewLocValue component4() {
            return this.fromTitle;
        }

        public final SecondaryPanelAnimatorBase.ViewLocValue component5() {
            return this.fromArtist;
        }

        public final SecondaryPanelAnimatorBase.ViewLocValue component6() {
            return this.fromAudioContainer;
        }

        public final SecondaryPanelAnimatorBase.ViewValue component7() {
            return this.fromAudioPrev;
        }

        public final SecondaryPanelAnimatorBase.ViewValue component8() {
            return this.fromAudioPlay;
        }

        public final SecondaryPanelAnimatorBase.ViewValue component9() {
            return this.fromAudioNext;
        }

        public final AnimValue copy(SecondaryPanelAnimatorBase.ViewLocValue fromContent, SecondaryPanelAnimatorBase.ViewLocValue fromCover, SecondaryPanelAnimatorBase.ViewLocValue fromNoPlay, SecondaryPanelAnimatorBase.ViewLocValue fromTitle, SecondaryPanelAnimatorBase.ViewLocValue fromArtist, SecondaryPanelAnimatorBase.ViewLocValue fromAudioContainer, SecondaryPanelAnimatorBase.ViewValue fromAudioPrev, SecondaryPanelAnimatorBase.ViewValue fromAudioPlay, SecondaryPanelAnimatorBase.ViewValue fromAudioNext, SecondaryPanelAnimatorBase.ViewLocValue toContent, SecondaryPanelAnimatorBase.ViewValue viewValue, SecondaryPanelAnimatorBase.ViewLocValue viewLocValue, SecondaryPanelAnimatorBase.ViewLocValue viewLocValue2, SecondaryPanelAnimatorBase.ViewLocValue viewLocValue3, SecondaryPanelAnimatorBase.ViewLocValue viewLocValue4, SecondaryPanelAnimatorBase.ViewLocValue viewLocValue5, SecondaryPanelAnimatorBase.ViewLocValue viewLocValue6, SecondaryPanelAnimatorBase.ViewLocValue viewLocValue7, SecondaryPanelAnimatorBase.ViewLocValue viewLocValue8, SecondaryPanelAnimatorBase.ViewValue viewValue2, SecondaryPanelAnimatorBase.ViewValue viewValue3, SecondaryPanelAnimatorBase.ViewValue viewValue4, SecondaryPanelAnimatorBase.ViewValue viewValue5, SecondaryPanelAnimatorBase.ViewValue viewValue6, SecondaryPanelAnimatorBase.ViewValue viewValue7, SecondaryPanelAnimatorBase.ViewValue viewValue8, SecondaryPanelAnimatorBase.ViewValue viewValue9, float f2, float f3, float f4, float f5) {
            n.g(fromContent, "fromContent");
            n.g(fromCover, "fromCover");
            n.g(fromNoPlay, "fromNoPlay");
            n.g(fromTitle, "fromTitle");
            n.g(fromArtist, "fromArtist");
            n.g(fromAudioContainer, "fromAudioContainer");
            n.g(fromAudioPrev, "fromAudioPrev");
            n.g(fromAudioPlay, "fromAudioPlay");
            n.g(fromAudioNext, "fromAudioNext");
            n.g(toContent, "toContent");
            return new AnimValue(fromContent, fromCover, fromNoPlay, fromTitle, fromArtist, fromAudioContainer, fromAudioPrev, fromAudioPlay, fromAudioNext, toContent, viewValue, viewLocValue, viewLocValue2, viewLocValue3, viewLocValue4, viewLocValue5, viewLocValue6, viewLocValue7, viewLocValue8, viewValue2, viewValue3, viewValue4, viewValue5, viewValue6, viewValue7, viewValue8, viewValue9, f2, f3, f4, f5);
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof AnimValue)) {
                return false;
            }
            AnimValue animValue = (AnimValue) obj;
            return n.c(this.fromContent, animValue.fromContent) && n.c(this.fromCover, animValue.fromCover) && n.c(this.fromNoPlay, animValue.fromNoPlay) && n.c(this.fromTitle, animValue.fromTitle) && n.c(this.fromArtist, animValue.fromArtist) && n.c(this.fromAudioContainer, animValue.fromAudioContainer) && n.c(this.fromAudioPrev, animValue.fromAudioPrev) && n.c(this.fromAudioPlay, animValue.fromAudioPlay) && n.c(this.fromAudioNext, animValue.fromAudioNext) && n.c(this.toContent, animValue.toContent) && n.c(this.toHeader, animValue.toHeader) && n.c(this.toMetaInfo, animValue.toMetaInfo) && n.c(this.toCover, animValue.toCover) && n.c(this.toCoverParent, animValue.toCoverParent) && n.c(this.toAppIconContainer, animValue.toAppIconContainer) && n.c(this.toNoPlay, animValue.toNoPlay) && n.c(this.toTitle, animValue.toTitle) && n.c(this.toSubtitle, animValue.toSubtitle) && n.c(this.toAudioContainer, animValue.toAudioContainer) && n.c(this.toAudioPrev, animValue.toAudioPrev) && n.c(this.toAudioPlay, animValue.toAudioPlay) && n.c(this.toAudioNext, animValue.toAudioNext) && n.c(this.toProgressContainer, animValue.toProgressContainer) && n.c(this.toTvContainer, animValue.toTvContainer) && n.c(this.toVolumeContainer, animValue.toVolumeContainer) && n.c(this.toDivider, animValue.toDivider) && n.c(this.toList, animValue.toList) && Float.compare(this.fromCornerRadius, animValue.fromCornerRadius) == 0 && Float.compare(this.fromCoverRadius, animValue.fromCoverRadius) == 0 && Float.compare(this.toCornerRadius, animValue.toCornerRadius) == 0 && Float.compare(this.toCoverRadius, animValue.toCoverRadius) == 0;
        }

        public final SecondaryPanelAnimatorBase.ViewLocValue getFromArtist() {
            return this.fromArtist;
        }

        public final SecondaryPanelAnimatorBase.ViewLocValue getFromAudioContainer() {
            return this.fromAudioContainer;
        }

        public final SecondaryPanelAnimatorBase.ViewValue getFromAudioNext() {
            return this.fromAudioNext;
        }

        public final SecondaryPanelAnimatorBase.ViewValue getFromAudioPlay() {
            return this.fromAudioPlay;
        }

        public final SecondaryPanelAnimatorBase.ViewValue getFromAudioPrev() {
            return this.fromAudioPrev;
        }

        public final float getFromCenterX() {
            return this.fromCenterX;
        }

        public final float getFromCenterY() {
            return this.fromCenterY;
        }

        public final SecondaryPanelAnimatorBase.ViewLocValue getFromContent() {
            return this.fromContent;
        }

        public final float getFromCornerRadius() {
            return this.fromCornerRadius;
        }

        public final SecondaryPanelAnimatorBase.ViewLocValue getFromCover() {
            return this.fromCover;
        }

        public final float getFromCoverRadius() {
            return this.fromCoverRadius;
        }

        public final SecondaryPanelAnimatorBase.ViewLocValue getFromNoPlay() {
            return this.fromNoPlay;
        }

        public final SecondaryPanelAnimatorBase.ViewLocValue getFromTitle() {
            return this.fromTitle;
        }

        public final SecondaryPanelAnimatorBase.ViewLocValue getToAppIconContainer() {
            return this.toAppIconContainer;
        }

        public final SecondaryPanelAnimatorBase.ViewLocValue getToAudioContainer() {
            return this.toAudioContainer;
        }

        public final SecondaryPanelAnimatorBase.ViewValue getToAudioNext() {
            return this.toAudioNext;
        }

        public final SecondaryPanelAnimatorBase.ViewValue getToAudioPlay() {
            return this.toAudioPlay;
        }

        public final SecondaryPanelAnimatorBase.ViewValue getToAudioPrev() {
            return this.toAudioPrev;
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

        public final float getToCornerRadius() {
            return this.toCornerRadius;
        }

        public final SecondaryPanelAnimatorBase.ViewLocValue getToCover() {
            return this.toCover;
        }

        public final SecondaryPanelAnimatorBase.ViewLocValue getToCoverParent() {
            return this.toCoverParent;
        }

        public final float getToCoverRadius() {
            return this.toCoverRadius;
        }

        public final SecondaryPanelAnimatorBase.ViewValue getToDivider() {
            return this.toDivider;
        }

        public final SecondaryPanelAnimatorBase.ViewValue getToHeader() {
            return this.toHeader;
        }

        public final SecondaryPanelAnimatorBase.ViewValue getToList() {
            return this.toList;
        }

        public final SecondaryPanelAnimatorBase.ViewLocValue getToMetaInfo() {
            return this.toMetaInfo;
        }

        public final SecondaryPanelAnimatorBase.ViewLocValue getToNoPlay() {
            return this.toNoPlay;
        }

        public final SecondaryPanelAnimatorBase.ViewValue getToProgressContainer() {
            return this.toProgressContainer;
        }

        public final SecondaryPanelAnimatorBase.ViewLocValue getToSubtitle() {
            return this.toSubtitle;
        }

        public final SecondaryPanelAnimatorBase.ViewLocValue getToTitle() {
            return this.toTitle;
        }

        public final SecondaryPanelAnimatorBase.ViewValue getToTvContainer() {
            return this.toTvContainer;
        }

        public final SecondaryPanelAnimatorBase.ViewValue getToVolumeContainer() {
            return this.toVolumeContainer;
        }

        public int hashCode() {
            int iHashCode = ((((((((((((((((((this.fromContent.hashCode() * 31) + this.fromCover.hashCode()) * 31) + this.fromNoPlay.hashCode()) * 31) + this.fromTitle.hashCode()) * 31) + this.fromArtist.hashCode()) * 31) + this.fromAudioContainer.hashCode()) * 31) + this.fromAudioPrev.hashCode()) * 31) + this.fromAudioPlay.hashCode()) * 31) + this.fromAudioNext.hashCode()) * 31) + this.toContent.hashCode()) * 31;
            SecondaryPanelAnimatorBase.ViewValue viewValue = this.toHeader;
            int iHashCode2 = (iHashCode + (viewValue == null ? 0 : viewValue.hashCode())) * 31;
            SecondaryPanelAnimatorBase.ViewLocValue viewLocValue = this.toMetaInfo;
            int iHashCode3 = (iHashCode2 + (viewLocValue == null ? 0 : viewLocValue.hashCode())) * 31;
            SecondaryPanelAnimatorBase.ViewLocValue viewLocValue2 = this.toCover;
            int iHashCode4 = (iHashCode3 + (viewLocValue2 == null ? 0 : viewLocValue2.hashCode())) * 31;
            SecondaryPanelAnimatorBase.ViewLocValue viewLocValue3 = this.toCoverParent;
            int iHashCode5 = (iHashCode4 + (viewLocValue3 == null ? 0 : viewLocValue3.hashCode())) * 31;
            SecondaryPanelAnimatorBase.ViewLocValue viewLocValue4 = this.toAppIconContainer;
            int iHashCode6 = (iHashCode5 + (viewLocValue4 == null ? 0 : viewLocValue4.hashCode())) * 31;
            SecondaryPanelAnimatorBase.ViewLocValue viewLocValue5 = this.toNoPlay;
            int iHashCode7 = (iHashCode6 + (viewLocValue5 == null ? 0 : viewLocValue5.hashCode())) * 31;
            SecondaryPanelAnimatorBase.ViewLocValue viewLocValue6 = this.toTitle;
            int iHashCode8 = (iHashCode7 + (viewLocValue6 == null ? 0 : viewLocValue6.hashCode())) * 31;
            SecondaryPanelAnimatorBase.ViewLocValue viewLocValue7 = this.toSubtitle;
            int iHashCode9 = (iHashCode8 + (viewLocValue7 == null ? 0 : viewLocValue7.hashCode())) * 31;
            SecondaryPanelAnimatorBase.ViewLocValue viewLocValue8 = this.toAudioContainer;
            int iHashCode10 = (iHashCode9 + (viewLocValue8 == null ? 0 : viewLocValue8.hashCode())) * 31;
            SecondaryPanelAnimatorBase.ViewValue viewValue2 = this.toAudioPrev;
            int iHashCode11 = (iHashCode10 + (viewValue2 == null ? 0 : viewValue2.hashCode())) * 31;
            SecondaryPanelAnimatorBase.ViewValue viewValue3 = this.toAudioPlay;
            int iHashCode12 = (iHashCode11 + (viewValue3 == null ? 0 : viewValue3.hashCode())) * 31;
            SecondaryPanelAnimatorBase.ViewValue viewValue4 = this.toAudioNext;
            int iHashCode13 = (iHashCode12 + (viewValue4 == null ? 0 : viewValue4.hashCode())) * 31;
            SecondaryPanelAnimatorBase.ViewValue viewValue5 = this.toProgressContainer;
            int iHashCode14 = (iHashCode13 + (viewValue5 == null ? 0 : viewValue5.hashCode())) * 31;
            SecondaryPanelAnimatorBase.ViewValue viewValue6 = this.toTvContainer;
            int iHashCode15 = (iHashCode14 + (viewValue6 == null ? 0 : viewValue6.hashCode())) * 31;
            SecondaryPanelAnimatorBase.ViewValue viewValue7 = this.toVolumeContainer;
            int iHashCode16 = (iHashCode15 + (viewValue7 == null ? 0 : viewValue7.hashCode())) * 31;
            SecondaryPanelAnimatorBase.ViewValue viewValue8 = this.toDivider;
            int iHashCode17 = (iHashCode16 + (viewValue8 == null ? 0 : viewValue8.hashCode())) * 31;
            SecondaryPanelAnimatorBase.ViewValue viewValue9 = this.toList;
            return ((((((((iHashCode17 + (viewValue9 != null ? viewValue9.hashCode() : 0)) * 31) + Float.hashCode(this.fromCornerRadius)) * 31) + Float.hashCode(this.fromCoverRadius)) * 31) + Float.hashCode(this.toCornerRadius)) * 31) + Float.hashCode(this.toCoverRadius);
        }

        public String toString() {
            return "AnimValue(fromContent=" + this.fromContent + ", fromCover=" + this.fromCover + ", fromNoPlay=" + this.fromNoPlay + ", fromTitle=" + this.fromTitle + ", fromArtist=" + this.fromArtist + ", fromAudioContainer=" + this.fromAudioContainer + ", fromAudioPrev=" + this.fromAudioPrev + ", fromAudioPlay=" + this.fromAudioPlay + ", fromAudioNext=" + this.fromAudioNext + ", toContent=" + this.toContent + ", toHeader=" + this.toHeader + ", toMetaInfo=" + this.toMetaInfo + ", toCover=" + this.toCover + ", toCoverParent=" + this.toCoverParent + ", toAppIconContainer=" + this.toAppIconContainer + ", toNoPlay=" + this.toNoPlay + ", toTitle=" + this.toTitle + ", toSubtitle=" + this.toSubtitle + ", toAudioContainer=" + this.toAudioContainer + ", toAudioPrev=" + this.toAudioPrev + ", toAudioPlay=" + this.toAudioPlay + ", toAudioNext=" + this.toAudioNext + ", toProgressContainer=" + this.toProgressContainer + ", toTvContainer=" + this.toTvContainer + ", toVolumeContainer=" + this.toVolumeContainer + ", toDivider=" + this.toDivider + ", toList=" + this.toList + ", fromCornerRadius=" + this.fromCornerRadius + ", fromCoverRadius=" + this.fromCoverRadius + ", toCornerRadius=" + this.toCornerRadius + ", toCoverRadius=" + this.toCoverRadius + ")";
        }
    }

    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }

    /* JADX WARN: Type inference failed for: r0v10, types: [miui.systemui.controlcenter.panel.secondary.media.MediaPanelAnimator$Companion$ALPHA_BG$1] */
    /* JADX WARN: Type inference failed for: r0v11, types: [miui.systemui.controlcenter.panel.secondary.media.MediaPanelAnimator$Companion$ALPHA_DEVICE_ICON$1] */
    /* JADX WARN: Type inference failed for: r0v12, types: [miui.systemui.controlcenter.panel.secondary.media.MediaPanelAnimator$Companion$ALPHA_PROGRESS$1] */
    /* JADX WARN: Type inference failed for: r0v13, types: [miui.systemui.controlcenter.panel.secondary.media.MediaPanelAnimator$Companion$CONTENT_COLOR$1] */
    /* JADX WARN: Type inference failed for: r0v5, types: [miui.systemui.controlcenter.panel.secondary.media.MediaPanelAnimator$Companion$FONT_SIZE$1] */
    /* JADX WARN: Type inference failed for: r0v6, types: [miui.systemui.controlcenter.panel.secondary.media.MediaPanelAnimator$Companion$FONT_WIDTH_HEIGHT$1] */
    /* JADX WARN: Type inference failed for: r0v7, types: [miui.systemui.controlcenter.panel.secondary.media.MediaPanelAnimator$Companion$SIZE$1] */
    /* JADX WARN: Type inference failed for: r0v8, types: [miui.systemui.controlcenter.panel.secondary.media.MediaPanelAnimator$Companion$COLOR$1] */
    /* JADX WARN: Type inference failed for: r0v9, types: [miui.systemui.controlcenter.panel.secondary.media.MediaPanelAnimator$Companion$POSITION$1] */
    static {
        EaseManager.EaseStyle easeStyleSpring = FolmeEase.spring(1.0f, 0.3f);
        n.f(easeStyleSpring, "spring(...)");
        EASE_EXPAND_FONT_SIZE = easeStyleSpring;
        EaseManager.EaseStyle easeStyleSpring2 = FolmeEase.spring(1.0f, 0.25f);
        n.f(easeStyleSpring2, "spring(...)");
        EASE_EXPAND_FONT_WIDTH_HEIGHT = easeStyleSpring2;
        EaseManager.EaseStyle easeStyleSpring3 = FolmeEase.spring(1.0f, 0.4f);
        n.f(easeStyleSpring3, "spring(...)");
        EASE_EXPAND_ALPHA_BG = easeStyleSpring3;
        EaseManager.EaseStyle easeStyleSpring4 = FolmeEase.spring(0.95f, 0.25f);
        n.f(easeStyleSpring4, "spring(...)");
        EASE_EXPAND_ALPHA_DEVICE_ICON = easeStyleSpring4;
        EaseManager.EaseStyle easeStyleSpring5 = FolmeEase.spring(0.8f, 0.35f);
        n.f(easeStyleSpring5, "spring(...)");
        EASE_EXPAND_ALPHA_PROGRESS = easeStyleSpring5;
        EaseManager.EaseStyle easeStyleSpring6 = FolmeEase.spring(1.0f, 0.3f);
        n.f(easeStyleSpring6, "spring(...)");
        EASE_COLLAPSE_FONT_SIZE = easeStyleSpring6;
        EaseManager.EaseStyle easeStyleSpring7 = FolmeEase.spring(1.0f, 0.25f);
        n.f(easeStyleSpring7, "spring(...)");
        EASE_COLLAPSE_FONT_WIDTH_HEIGHT = easeStyleSpring7;
        EaseManager.EaseStyle easeStyleSpring8 = FolmeEase.spring(1.0f, 0.4f);
        n.f(easeStyleSpring8, "spring(...)");
        EASE_COLLAPSE_ALPHA_BG = easeStyleSpring8;
        EaseManager.EaseStyle easeStyleSpring9 = FolmeEase.spring(0.95f, 0.35f);
        n.f(easeStyleSpring9, "spring(...)");
        EASE_COLLAPSE_ALPHA_DEVICE_ICON = easeStyleSpring9;
        EaseManager.EaseStyle easeStyleSpring10 = FolmeEase.spring(0.8f, 0.25f);
        n.f(easeStyleSpring10, "spring(...)");
        EASE_COLLAPSE_ALPHA_PROGRESS = easeStyleSpring10;
        FONT_SIZE = new FloatProperty<MediaPanelAnimator>() { // from class: miui.systemui.controlcenter.panel.secondary.media.MediaPanelAnimator$Companion$FONT_SIZE$1
            @Override // miuix.animation.property.FloatProperty
            public float getValue(MediaPanelAnimator anim) {
                n.g(anim, "anim");
                return anim.fontSize;
            }

            @Override // miuix.animation.property.FloatProperty
            public void setValue(MediaPanelAnimator anim, float f2) {
                n.g(anim, "anim");
                if (anim.fontSize == f2) {
                    return;
                }
                anim.fontSize = f2;
                anim.scheduleUpdate();
            }
        };
        FONT_WIDTH_HEIGHT = new FloatProperty<MediaPanelAnimator>() { // from class: miui.systemui.controlcenter.panel.secondary.media.MediaPanelAnimator$Companion$FONT_WIDTH_HEIGHT$1
            @Override // miuix.animation.property.FloatProperty
            public float getValue(MediaPanelAnimator anim) {
                n.g(anim, "anim");
                return anim.fontWidthHeight;
            }

            @Override // miuix.animation.property.FloatProperty
            public void setValue(MediaPanelAnimator anim, float f2) {
                n.g(anim, "anim");
                if (anim.fontWidthHeight == f2) {
                    return;
                }
                anim.fontWidthHeight = f2;
                anim.scheduleUpdate();
            }
        };
        SIZE = new FloatProperty<MediaPanelAnimator>() { // from class: miui.systemui.controlcenter.panel.secondary.media.MediaPanelAnimator$Companion$SIZE$1
            @Override // miuix.animation.property.FloatProperty
            public float getValue(MediaPanelAnimator anim) {
                n.g(anim, "anim");
                return anim.size;
            }

            @Override // miuix.animation.property.FloatProperty
            public void setValue(MediaPanelAnimator anim, float f2) {
                n.g(anim, "anim");
                if (anim.size == f2) {
                    return;
                }
                anim.size = f2;
                anim.scheduleUpdate();
            }
        };
        COLOR = new FloatProperty<MediaPanelAnimator>() { // from class: miui.systemui.controlcenter.panel.secondary.media.MediaPanelAnimator$Companion$COLOR$1
            @Override // miuix.animation.property.FloatProperty
            public float getValue(MediaPanelAnimator anim) {
                n.g(anim, "anim");
                return anim.color;
            }

            @Override // miuix.animation.property.FloatProperty
            public void setValue(MediaPanelAnimator anim, float f2) {
                n.g(anim, "anim");
                if (anim.color == f2) {
                    return;
                }
                anim.color = f2;
                anim.scheduleUpdate();
            }
        };
        POSITION = new FloatProperty<MediaPanelAnimator>() { // from class: miui.systemui.controlcenter.panel.secondary.media.MediaPanelAnimator$Companion$POSITION$1
            @Override // miuix.animation.property.FloatProperty
            public float getValue(MediaPanelAnimator anim) {
                n.g(anim, "anim");
                return anim.position;
            }

            @Override // miuix.animation.property.FloatProperty
            public void setValue(MediaPanelAnimator anim, float f2) {
                n.g(anim, "anim");
                if (anim.position == f2) {
                    return;
                }
                anim.position = f2;
                anim.scheduleUpdate();
            }
        };
        ALPHA_BG = new FloatProperty<MediaPanelAnimator>() { // from class: miui.systemui.controlcenter.panel.secondary.media.MediaPanelAnimator$Companion$ALPHA_BG$1
            @Override // miuix.animation.property.FloatProperty
            public float getValue(MediaPanelAnimator anim) {
                n.g(anim, "anim");
                return anim.alphaBg;
            }

            @Override // miuix.animation.property.FloatProperty
            public void setValue(MediaPanelAnimator anim, float f2) {
                n.g(anim, "anim");
                if (anim.alphaBg == f2) {
                    return;
                }
                anim.alphaBg = f2;
                anim.scheduleUpdate();
            }
        };
        ALPHA_DEVICE_ICON = new FloatProperty<MediaPanelAnimator>() { // from class: miui.systemui.controlcenter.panel.secondary.media.MediaPanelAnimator$Companion$ALPHA_DEVICE_ICON$1
            @Override // miuix.animation.property.FloatProperty
            public float getValue(MediaPanelAnimator anim) {
                n.g(anim, "anim");
                return anim.alphaDeviceIcon;
            }

            @Override // miuix.animation.property.FloatProperty
            public void setValue(MediaPanelAnimator anim, float f2) {
                n.g(anim, "anim");
                if (anim.alphaDeviceIcon == f2) {
                    return;
                }
                anim.alphaDeviceIcon = f2;
                anim.scheduleUpdate();
            }
        };
        ALPHA_PROGRESS = new FloatProperty<MediaPanelAnimator>() { // from class: miui.systemui.controlcenter.panel.secondary.media.MediaPanelAnimator$Companion$ALPHA_PROGRESS$1
            @Override // miuix.animation.property.FloatProperty
            public float getValue(MediaPanelAnimator anim) {
                n.g(anim, "anim");
                return anim.alphaProgress;
            }

            @Override // miuix.animation.property.FloatProperty
            public void setValue(MediaPanelAnimator anim, float f2) {
                n.g(anim, "anim");
                if (anim.alphaProgress == f2) {
                    return;
                }
                anim.alphaProgress = f2;
                anim.scheduleUpdate();
            }
        };
        CONTENT_COLOR = new FloatProperty<MediaPanelAnimator>() { // from class: miui.systemui.controlcenter.panel.secondary.media.MediaPanelAnimator$Companion$CONTENT_COLOR$1
            @Override // miuix.animation.property.FloatProperty
            public float getValue(MediaPanelAnimator anim) {
                n.g(anim, "anim");
                return anim.contentColor;
            }

            @Override // miuix.animation.property.FloatProperty
            public void setValue(MediaPanelAnimator anim, float f2) {
                n.g(anim, "anim");
                if (anim.contentColor == f2) {
                    return;
                }
                anim.contentColor = f2;
                anim.scheduleUpdate();
            }
        };
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public MediaPanelAnimator(Context context, E0.a panelController, MediaPanelDelegate contentController, E0.a windowViewController, E0.a mainPanelController) {
        super(context, mainPanelController);
        n.g(context, "context");
        n.g(panelController, "panelController");
        n.g(contentController, "contentController");
        n.g(windowViewController, "windowViewController");
        n.g(mainPanelController, "mainPanelController");
        this.context = context;
        this.panelController = panelController;
        this.contentController = contentController;
        this.windowViewController = windowViewController;
        this.mainPanelController = mainPanelController;
        this.anim = Folme.useValue(this);
        this.noPlayValue = 1.05f;
        this.animConfig = new AnimConfig().addListeners(new TransitionListener() { // from class: miui.systemui.controlcenter.panel.secondary.media.MediaPanelAnimator$animConfig$1
            @Override // miuix.animation.listener.TransitionListener
            public void onUpdate(Object obj, Collection<UpdateInfo> collection) {
                boolean z2 = true;
                if (collection != null) {
                    MediaPanelAnimator mediaPanelAnimator = this.this$0;
                    boolean z3 = true;
                    for (UpdateInfo updateInfo : collection) {
                        z3 = z3 && updateInfo.isCompleted;
                        if (n.c(updateInfo.property, MediaPanelAnimator.COLOR)) {
                            mediaPanelAnimator.doUpdateClipHeaderCheck(updateInfo.getFloatValue());
                        }
                    }
                    z2 = z3;
                }
                if (z2) {
                    this.this$0.onAnimComplete();
                }
            }
        });
        this.alphaDeviceIcon = 1.0f;
    }

    /* JADX WARN: Type inference fix 'apply assigned field type' failed
    java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$ArrayArg
    	at jadx.core.dex.instructions.args.ArgType.getObject(ArgType.java:593)
    	at jadx.core.dex.attributes.nodes.ClassTypeVarsAttr.getTypeVarsMapFor(ClassTypeVarsAttr.java:35)
    	at jadx.core.dex.nodes.utils.TypeUtils.replaceClassGenerics(TypeUtils.java:177)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.insertExplicitUseCast(FixTypesVisitor.java:397)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryFieldTypeWithNewCasts(FixTypesVisitor.java:359)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.applyFieldType(FixTypesVisitor.java:309)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
     */
    private final void calculateViewValues() {
        SecondaryPanelAnimatorBase.ViewLocValue viewLocValue;
        SecondaryPanelAnimatorBase.ViewLocValue viewLocValue2;
        SecondaryPanelAnimatorBase.ViewLocValue viewLocValue3;
        SecondaryPanelAnimatorBase.ViewLocValue viewLocValue4;
        SecondaryPanelAnimatorBase.ViewLocValue viewLocValue5;
        SecondaryPanelAnimatorBase.ViewLocValue viewLocValue6;
        SecondaryPanelAnimatorBase.ViewLocValue viewLocValue7;
        SecondaryPanelAnimatorBase.ViewLocValue viewLocValue8;
        SecondaryPanelAnimatorBase.ViewLocValue fromContent;
        MediaFromView mediaFromView = this.fromView;
        if (mediaFromView == null) {
            return;
        }
        int[] iArr = new int[2];
        CommonUtils commonUtils = CommonUtils.INSTANCE;
        commonUtils.getLocationInWindowWithoutTransform(mediaFromView.getContentView(), iArr);
        boolean z2 = false;
        SecondaryPanelAnimatorBase.ViewLocValue viewLocValue9 = new SecondaryPanelAnimatorBase.ViewLocValue(iArr[0], iArr[1], mediaFromView.getContentView().getLeft(), mediaFromView.getContentView().getTop(), mediaFromView.getContentView().getWidth(), mediaFromView.getContentView().getHeight());
        int[] iArr2 = new int[2];
        commonUtils.getLocationInWindowWithoutTransform(mediaFromView.getCoverView(), iArr2);
        SecondaryPanelAnimatorBase.ViewLocValue viewLocValue10 = new SecondaryPanelAnimatorBase.ViewLocValue(iArr2[0], iArr2[1], mediaFromView.getCoverView().getLeft(), mediaFromView.getCoverView().getTop(), mediaFromView.getCoverView().getWidth(), mediaFromView.getCoverView().getHeight());
        int[] iArr3 = new int[2];
        commonUtils.getLocationInWindowWithoutTransform(mediaFromView.getNoPlayView(), iArr3);
        SecondaryPanelAnimatorBase.ViewLocValue viewLocValue11 = new SecondaryPanelAnimatorBase.ViewLocValue(iArr3[0], iArr3[1], mediaFromView.getNoPlayView().getLeft(), mediaFromView.getNoPlayView().getTop(), mediaFromView.getNoPlayView().getWidth(), mediaFromView.getNoPlayView().getHeight());
        int[] iArr4 = new int[2];
        commonUtils.getLocationInWindowWithoutTransform(mediaFromView.getTitleView(), iArr4);
        SecondaryPanelAnimatorBase.ViewLocValue viewLocValue12 = new SecondaryPanelAnimatorBase.ViewLocValue(iArr4[0], iArr4[1], mediaFromView.getTitleView().getLeft(), mediaFromView.getTitleView().getTop(), mediaFromView.getTitleView().getWidth(), mediaFromView.getTitleView().getHeight());
        int[] iArr5 = new int[2];
        commonUtils.getLocationInWindowWithoutTransform(mediaFromView.getArtistView(), iArr5);
        SecondaryPanelAnimatorBase.ViewLocValue viewLocValue13 = new SecondaryPanelAnimatorBase.ViewLocValue(iArr5[0], iArr5[1], mediaFromView.getArtistView().getLeft(), mediaFromView.getArtistView().getTop(), mediaFromView.getArtistView().getWidth(), mediaFromView.getArtistView().getHeight());
        int[] iArr6 = new int[2];
        commonUtils.getLocationInWindowWithoutTransform(mediaFromView.getAudioContainerView(), iArr6);
        SecondaryPanelAnimatorBase.ViewLocValue viewLocValue14 = new SecondaryPanelAnimatorBase.ViewLocValue(iArr6[0], iArr6[1], mediaFromView.getAudioContainerView().getLeft(), mediaFromView.getAudioContainerView().getTop(), mediaFromView.getAudioContainerView().getWidth(), mediaFromView.getAudioContainerView().getHeight());
        SecondaryPanelAnimatorBase.ViewValue viewValue = new SecondaryPanelAnimatorBase.ViewValue(mediaFromView.getAudioPrevView().getLeft(), mediaFromView.getAudioPrevView().getTop(), mediaFromView.getAudioPrevView().getWidth(), mediaFromView.getAudioPrevView().getHeight());
        SecondaryPanelAnimatorBase.ViewValue viewValue2 = new SecondaryPanelAnimatorBase.ViewValue(mediaFromView.getAudioPlayView().getLeft(), mediaFromView.getAudioPlayView().getTop(), mediaFromView.getAudioPlayView().getWidth(), mediaFromView.getAudioPlayView().getHeight());
        SecondaryPanelAnimatorBase.ViewValue viewValue3 = new SecondaryPanelAnimatorBase.ViewValue(mediaFromView.getAudioNextView().getLeft(), mediaFromView.getAudioNextView().getTop(), mediaFromView.getAudioNextView().getWidth(), mediaFromView.getAudioNextView().getHeight());
        int[] iArr7 = new int[2];
        commonUtils.getLocationInWindowWithoutTransform(getToView().getItemFrame(), iArr7);
        SecondaryPanelAnimatorBase.ViewLocValue viewLocValue15 = new SecondaryPanelAnimatorBase.ViewLocValue(iArr7[0], iArr7[1], getToView().getItemFrame().getLeft(), getToView().getItemFrame().getTop(), getToView().getItemFrame().getWidth(), getToView().getItemFrame().getHeight());
        QSControlMiPlayDetailHeader headerLayout = this.contentController.getHeaderLayout();
        SecondaryPanelAnimatorBase.ViewValue viewValue4 = headerLayout != null ? new SecondaryPanelAnimatorBase.ViewValue(headerLayout.getLeft(), headerLayout.getTop(), headerLayout.getWidth(), headerLayout.getHeight()) : null;
        ViewGroup metaInfo = this.contentController.getMetaInfo();
        if (metaInfo != null) {
            int[] iArr8 = new int[2];
            commonUtils.getLocationInWindowWithoutTransform(metaInfo, iArr8);
            viewLocValue = new SecondaryPanelAnimatorBase.ViewLocValue(iArr8[0], iArr8[1], metaInfo.getLeft(), metaInfo.getTop(), metaInfo.getWidth(), metaInfo.getHeight());
        } else {
            viewLocValue = null;
        }
        ViewGroup coverParentView = this.contentController.getCoverParentView();
        if (coverParentView != null) {
            int[] iArr9 = new int[2];
            commonUtils.getLocationInWindowWithoutTransform(coverParentView, iArr9);
            viewLocValue2 = new SecondaryPanelAnimatorBase.ViewLocValue(iArr9[0], iArr9[1], coverParentView.getLeft(), coverParentView.getTop(), coverParentView.getWidth(), coverParentView.getHeight());
        } else {
            viewLocValue2 = null;
        }
        View coverView = this.contentController.getCoverView();
        if (coverView != null) {
            int[] iArr10 = new int[2];
            commonUtils.getLocationInWindowWithoutTransform(coverView, iArr10);
            viewLocValue3 = new SecondaryPanelAnimatorBase.ViewLocValue(iArr10[0], iArr10[1], coverView.getLeft(), coverView.getTop(), coverView.getWidth(), coverView.getHeight());
        } else {
            viewLocValue3 = null;
        }
        View appIconContainerView = this.contentController.getAppIconContainerView();
        if (appIconContainerView != null) {
            int[] iArr11 = new int[2];
            commonUtils.getLocationInWindowWithoutTransform(appIconContainerView, iArr11);
            viewLocValue4 = new SecondaryPanelAnimatorBase.ViewLocValue(iArr11[0], iArr11[1], appIconContainerView.getLeft(), appIconContainerView.getTop(), appIconContainerView.getWidth(), appIconContainerView.getHeight());
        } else {
            viewLocValue4 = null;
        }
        TextView noPlayView = this.contentController.getNoPlayView();
        if (noPlayView != null) {
            int[] iArr12 = new int[2];
            commonUtils.getLocationInWindowWithoutTransform(noPlayView, iArr12);
            viewLocValue5 = new SecondaryPanelAnimatorBase.ViewLocValue(iArr12[0], iArr12[1], noPlayView.getLeft(), noPlayView.getTop(), noPlayView.getWidth(), noPlayView.getHeight());
        } else {
            viewLocValue5 = null;
        }
        TextView titleView = this.contentController.getTitleView();
        if (titleView != null) {
            int[] iArr13 = new int[2];
            commonUtils.getLocationInWindowWithoutTransform(titleView, iArr13);
            viewLocValue6 = new SecondaryPanelAnimatorBase.ViewLocValue(iArr13[0], iArr13[1], titleView.getLeft(), titleView.getTop(), titleView.getWidth(), titleView.getHeight());
        } else {
            viewLocValue6 = null;
        }
        TextView subtitleView = this.contentController.getSubtitleView();
        if (subtitleView != null) {
            int[] iArr14 = new int[2];
            commonUtils.getLocationInWindowWithoutTransform(subtitleView, iArr14);
            viewLocValue7 = new SecondaryPanelAnimatorBase.ViewLocValue(iArr14[0], iArr14[1], subtitleView.getLeft(), subtitleView.getTop(), subtitleView.getWidth(), subtitleView.getHeight());
        } else {
            viewLocValue7 = null;
        }
        View avContainerView = this.contentController.getAvContainerView();
        if (avContainerView != null) {
            int[] iArr15 = new int[2];
            commonUtils.getLocationInWindowWithoutTransform(avContainerView, iArr15);
            viewLocValue8 = new SecondaryPanelAnimatorBase.ViewLocValue(iArr15[0], iArr15[1], avContainerView.getLeft(), avContainerView.getTop(), avContainerView.getWidth(), avContainerView.getHeight());
        } else {
            viewLocValue8 = null;
        }
        View avPrevView = this.contentController.getAvPrevView();
        SecondaryPanelAnimatorBase.ViewValue viewValue5 = avPrevView != null ? new SecondaryPanelAnimatorBase.ViewValue(avPrevView.getLeft(), avPrevView.getTop(), avPrevView.getWidth(), avPrevView.getHeight()) : null;
        View avPlayView = this.contentController.getAvPlayView();
        SecondaryPanelAnimatorBase.ViewValue viewValue6 = avPlayView != null ? new SecondaryPanelAnimatorBase.ViewValue(avPlayView.getLeft(), avPlayView.getTop(), avPlayView.getWidth(), avPlayView.getHeight()) : null;
        View avNextView = this.contentController.getAvNextView();
        SecondaryPanelAnimatorBase.ViewValue viewValue7 = avNextView != null ? new SecondaryPanelAnimatorBase.ViewValue(avNextView.getLeft(), avNextView.getTop(), avNextView.getWidth(), avNextView.getHeight()) : null;
        View progressContainerView = this.contentController.getProgressContainerView();
        SecondaryPanelAnimatorBase.ViewValue viewValue8 = progressContainerView != null ? new SecondaryPanelAnimatorBase.ViewValue(progressContainerView.getLeft(), progressContainerView.getTop(), progressContainerView.getWidth(), progressContainerView.getHeight()) : null;
        View tvContainerView = this.contentController.getTvContainerView();
        SecondaryPanelAnimatorBase.ViewValue viewValue9 = tvContainerView != null ? new SecondaryPanelAnimatorBase.ViewValue(tvContainerView.getLeft(), tvContainerView.getTop(), tvContainerView.getWidth(), tvContainerView.getHeight()) : null;
        View volumeBarContainerView = this.contentController.getVolumeBarContainerView();
        SecondaryPanelAnimatorBase.ViewValue viewValue10 = volumeBarContainerView != null ? new SecondaryPanelAnimatorBase.ViewValue(volumeBarContainerView.getLeft(), volumeBarContainerView.getTop(), volumeBarContainerView.getWidth(), volumeBarContainerView.getHeight()) : null;
        View divider = this.contentController.getDivider();
        SecondaryPanelAnimatorBase.ViewValue viewValue11 = divider != null ? new SecondaryPanelAnimatorBase.ViewValue(divider.getLeft(), divider.getTop(), divider.getWidth(), divider.getHeight()) : null;
        View listView = this.contentController.getListView();
        AnimValue animValue = new AnimValue(viewLocValue9, viewLocValue10, viewLocValue11, viewLocValue12, viewLocValue13, viewLocValue14, viewValue, viewValue2, viewValue3, viewLocValue15, viewValue4, viewLocValue, viewLocValue3, viewLocValue2, viewLocValue4, viewLocValue5, viewLocValue6, viewLocValue7, viewLocValue8, viewValue5, viewValue6, viewValue7, viewValue8, viewValue9, viewValue10, viewValue11, listView != null ? new SecondaryPanelAnimatorBase.ViewValue(listView.getLeft(), listView.getTop(), listView.getWidth(), listView.getHeight()) : null, mediaFromView.getCornerRadius(), mediaFromView.getCoverRadius(), getToView().getContentBgRadius(), this.contentController.getCoverRadius());
        if (this.expandMediaMetaData == null && MiPlayDetailViewModel.INSTANCE.getMMediaMetaData().getValue() != null) {
            z2 = true;
        }
        AnimValue animValue2 = this.lastAnimValue;
        if (animValue2 != null && animValue2 != null && (fromContent = animValue2.getFromContent()) != null && fromContent.getLocTop() == animValue.getFromContent().getLocTop() && !isOrientationChanged() && !isFoldStateChanged() && !z2) {
            this.animValue = this.lastAnimValue;
        } else {
            this.animValue = animValue;
            this.lastAnimValue = animValue;
        }
    }

    private final MediaPanelController getToView() {
        return (MediaPanelController) this.panelController.get();
    }

    private final void relayoutNoPlayView(boolean z2) {
        View noPlayView;
        ViewGroup.LayoutParams layoutParams;
        MediaFromView mediaFromView = this.fromView;
        if (mediaFromView == null || (noPlayView = mediaFromView.getNoPlayView()) == null || (layoutParams = noPlayView.getLayoutParams()) == null) {
            return;
        }
        layoutParams.width = z2 ? 0 : -1;
    }

    private final void resetFontSize() {
        MediaPanelDelegate mediaPanelDelegate = this.contentController;
        Float fValueOf = mediaPanelDelegate != null ? Float.valueOf(mediaPanelDelegate.getToTitleFontSize()) : null;
        n.d(fValueOf);
        this.titleToFontSize = fValueOf.floatValue();
        MediaPanelDelegate mediaPanelDelegate2 = this.contentController;
        Float fValueOf2 = mediaPanelDelegate2 != null ? Float.valueOf(mediaPanelDelegate2.getFromTitleFontSize()) : null;
        n.d(fValueOf2);
        this.titleFromFontSize = fValueOf2.floatValue();
        MediaPanelDelegate mediaPanelDelegate3 = this.contentController;
        Float fValueOf3 = mediaPanelDelegate3 != null ? Float.valueOf(mediaPanelDelegate3.getToSubtitleFontSize()) : null;
        n.d(fValueOf3);
        this.subtitleToFontSize = fValueOf3.floatValue();
        MediaPanelDelegate mediaPanelDelegate4 = this.contentController;
        Float fValueOf4 = mediaPanelDelegate4 != null ? Float.valueOf(mediaPanelDelegate4.getFromSubtitleFontSize()) : null;
        n.d(fValueOf4);
        this.subtitleFromFontSize = fValueOf4.floatValue();
        MediaPanelDelegate mediaPanelDelegate5 = this.contentController;
        Float fValueOf5 = mediaPanelDelegate5 != null ? Float.valueOf(mediaPanelDelegate5.getToNoPlayFontSize()) : null;
        n.d(fValueOf5);
        this.toNoPlayFontSize = fValueOf5.floatValue();
        MediaPanelDelegate mediaPanelDelegate6 = this.contentController;
        Float fValueOf6 = mediaPanelDelegate6 != null ? Float.valueOf(mediaPanelDelegate6.getFromNoPlayFontSize()) : null;
        n.d(fValueOf6);
        this.fromNoPlayFontSize = fValueOf6.floatValue();
    }

    @Override // miui.systemui.controlcenter.panel.secondary.SecondaryPanelAnimatorBase
    public void collapse(boolean z2) {
        s sVar;
        TextView titleView;
        miui.systemui.widget.TextView titleView2;
        ViewGroup contentView;
        super.collapse(z2);
        resetFontSize();
        MediaFromView mediaFromView = this.fromView;
        if (mediaFromView != null && (contentView = mediaFromView.getContentView()) != null) {
            ViewParent parent = contentView.getParent();
            MainPanelRecyclerView mainPanelRecyclerView = parent instanceof MainPanelRecyclerView ? (MainPanelRecyclerView) parent : null;
            if (mainPanelRecyclerView != null) {
                mainPanelRecyclerView.setTopDrawingChild(contentView);
            }
            if (!contentView.isLayoutSuppressed()) {
                calculateViewValues();
            }
        }
        Log.i(getTag(), "collapse " + z2 + " " + getCollapseWithNoAnim() + " " + this.animValue);
        MediaFromView mediaFromView2 = this.fromView;
        if (mediaFromView2 != null) {
            mediaFromView2.changeAction();
        }
        this.anim.cancel();
        MediaFromView mediaFromView3 = this.fromView;
        if (mediaFromView3 == null || (titleView2 = mediaFromView3.getTitleView()) == null) {
            sVar = null;
        } else {
            TextView titleView3 = this.contentController.getTitleView();
            if (titleView3 != null) {
                titleView3.setTypeface(titleView2.getTypeface());
            }
            sVar = s.f314a;
        }
        if (sVar == null && (titleView = this.contentController.getTitleView()) != null) {
            titleView.setTypeface(this.contentController.getTitleOriginalTypeface());
        }
        this.contentColor = 1.0f;
        AnimConfig animConfig = this.animConfig;
        animConfig.setSpecial(FONT_SIZE, EASE_COLLAPSE_FONT_SIZE, new float[0]);
        animConfig.setSpecial(FONT_WIDTH_HEIGHT, EASE_COLLAPSE_FONT_WIDTH_HEIGHT, new float[0]);
        animConfig.setSpecial(SIZE, FolmeUtilsExtKt.getEASE_COLLAPSE_SIZE(), new float[0]);
        animConfig.setSpecial(COLOR, FolmeUtilsExtKt.getEASE_COLLAPSE_COLOR(), new float[0]);
        animConfig.setSpecial(ALPHA_BG, EASE_COLLAPSE_ALPHA_BG, new float[0]);
        animConfig.setSpecial(ALPHA_DEVICE_ICON, EASE_COLLAPSE_ALPHA_DEVICE_ICON, new float[0]);
        animConfig.setSpecial(ALPHA_PROGRESS, EASE_COLLAPSE_ALPHA_PROGRESS, new float[0]);
        if (z2 && !getCollapseWithNoAnim()) {
            this.anim.to(FONT_SIZE, Float.valueOf(0.0f), FONT_WIDTH_HEIGHT, Float.valueOf(0.0f), SIZE, Float.valueOf(0.0f), COLOR, Float.valueOf(0.0f), ALPHA_BG, Float.valueOf(0.0f), ALPHA_DEVICE_ICON, Float.valueOf(1.0f), ALPHA_PROGRESS, Float.valueOf(0.0f), this.animConfig);
            return;
        }
        this.anim.setTo(SIZE, Float.valueOf(0.0f), COLOR, Float.valueOf(0.0f), ALPHA_BG, Float.valueOf(0.0f), ALPHA_DEVICE_ICON, Float.valueOf(1.0f), ALPHA_PROGRESS, Float.valueOf(0.0f));
        SecondaryPanelAnimatorBase.doFrame$default(this, 0L, 1, null);
        onAnimComplete();
        this.lastAnimValue = null;
    }

    @Override // miui.systemui.controlcenter.panel.secondary.SecondaryPanelAnimatorBase
    public void expand() {
        ViewGroup contentView;
        super.expand();
        this.expandMediaMetaData = MiPlayDetailViewModel.INSTANCE.getMMediaMetaData().getValue();
        TextView titleView = this.contentController.getTitleView();
        if (titleView != null) {
            titleView.setTypeface(this.contentController.getTitleOriginalTypeface());
        }
        MediaFromView mediaFromView = this.fromView;
        if (mediaFromView != null && (contentView = mediaFromView.getContentView()) != null) {
            ViewParent parent = contentView.getParent();
            MainPanelRecyclerView mainPanelRecyclerView = parent instanceof MainPanelRecyclerView ? (MainPanelRecyclerView) parent : null;
            if (mainPanelRecyclerView != null) {
                mainPanelRecyclerView.setTopDrawingChild(contentView);
            }
            if (!contentView.isLayoutSuppressed()) {
                calculateViewValues();
            }
        }
        Log.i(getTag(), "expand " + this.animValue);
        MediaFromView mediaFromView2 = this.fromView;
        if (mediaFromView2 != null) {
            mediaFromView2.changeAction();
        }
        ControlCenterScenarioTracker.setControlCenterScenarioState$default(ControlCenterScenarioTracker.INSTANCE, 356L, false, 2, null);
        SecondaryPanelAnimatorBase.doFrame$default(this, 0L, 1, null);
        AnimConfig animConfig = this.animConfig;
        animConfig.setSpecial(FONT_SIZE, EASE_EXPAND_FONT_SIZE, new float[0]);
        animConfig.setSpecial(FONT_WIDTH_HEIGHT, EASE_EXPAND_FONT_WIDTH_HEIGHT, new float[0]);
        animConfig.setSpecial(SIZE, FolmeUtilsExtKt.getEASE_EXPAND_SIZE(), new float[0]);
        animConfig.setSpecial(COLOR, FolmeUtilsExtKt.getEASE_EXPAND_COLOR(), new float[0]);
        animConfig.setSpecial(ALPHA_BG, EASE_EXPAND_ALPHA_BG, new float[0]);
        animConfig.setSpecial(ALPHA_DEVICE_ICON, EASE_EXPAND_ALPHA_DEVICE_ICON, new float[0]);
        animConfig.setSpecial(ALPHA_PROGRESS, EASE_EXPAND_ALPHA_PROGRESS, new float[0]);
        animConfig.setSpecial(CONTENT_COLOR, FolmeUtilsExtKt.getEASE_EXPAND_COLOR(), new float[0]);
        this.anim.to(FONT_SIZE, Float.valueOf(1.0f), FONT_WIDTH_HEIGHT, Float.valueOf(1.0f), SIZE, Float.valueOf(1.0f), COLOR, Float.valueOf(1.0f), ALPHA_BG, Float.valueOf(1.0f), ALPHA_DEVICE_ICON, Float.valueOf(0.0f), ALPHA_PROGRESS, Float.valueOf(1.0f), CONTENT_COLOR, Float.valueOf(1.0f), this.animConfig);
    }

    @Override // miui.systemui.controlcenter.panel.secondary.SecondaryPanelAnimatorBase
    public void forceUpdateClipHeader() {
        ((ControlCenterWindowViewController) this.windowViewController.get()).updateClip(isCollapsing());
    }

    /* JADX WARN: Removed duplicated region for block: B:81:0x036e  */
    @Override // miui.systemui.controlcenter.panel.secondary.SecondaryPanelAnimatorBase
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void frameCallback() {
        /*
            Method dump skipped, instruction units count: 2256
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: miui.systemui.controlcenter.panel.secondary.media.MediaPanelAnimator.frameCallback():void");
    }

    @Override // miui.systemui.controlcenter.panel.secondary.SecondaryPanelAnimatorBase
    public String getAnimStateString() {
        return "MediaPanelAnimator(fontSize=" + this.fontSize + ", fontWidthHeight=" + this.fontWidthHeight + ", sizeBgX=" + this.size + ", alphaBg=" + this.alphaBg + ", alphaDeviceIcon=" + this.alphaDeviceIcon + ", alphaProgress=" + this.alphaProgress + ")";
    }

    @Override // miui.systemui.controlcenter.panel.secondary.SecondaryPanelAnimatorBase
    public void onAnimComplete() {
        super.onAnimComplete();
        if (isExpanded()) {
            ControlCenterScenarioTracker.INSTANCE.setControlCenterScenarioState(356L, false);
        }
        SecondaryPanelAnimatorBase.doFrame$default(this, 0L, 1, null);
        ((MediaPanelController) this.panelController.get()).onAnimComplete();
        this.animValue = null;
        if (isCollapsed()) {
            relayoutNoPlayView(false);
        }
    }

    @Override // miui.systemui.controlcenter.panel.secondary.SecondaryPanelAnimatorBase
    public void prepareExpand(MediaPanelParams mediaPanelParams) {
        super.prepareExpand(mediaPanelParams);
        MediaFromView fromView = mediaPanelParams != null ? mediaPanelParams.getFromView() : null;
        this.fromView = fromView;
        if (fromView != null) {
            fromView.setChangeToView(getToView());
        }
        relayoutNoPlayView(true);
        CommonUtils commonUtils = CommonUtils.INSTANCE;
        commonUtils.setAlphaEx(getToView().getItemFrame(), 1.0f);
        View coverView = this.contentController.getCoverView();
        if (coverView != null) {
            commonUtils.setAlphaEx(coverView, 1.0f);
        }
        View deviceIconView = this.contentController.getDeviceIconView();
        if (deviceIconView != null) {
            commonUtils.setVisible(deviceIconView);
        }
        resetFontSize();
        this.animValue = null;
        this.lastAnimValue = null;
        this.size = 0.0f;
        this.color = 0.0f;
        this.position = 0.0f;
        this.alphaBg = 0.0f;
        this.contentColor = 0.0f;
        this.alphaDeviceIcon = 1.0f;
        this.alphaProgress = 0.0f;
    }
}
