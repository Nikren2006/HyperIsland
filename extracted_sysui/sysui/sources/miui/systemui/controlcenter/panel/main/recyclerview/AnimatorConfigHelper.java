package miui.systemui.controlcenter.panel.main.recyclerview;

import android.content.Context;
import android.util.Log;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.n;
import miui.systemui.controlcenter.panel.main.brightness.BrightnessSliderController;
import miui.systemui.controlcenter.panel.main.devicecenter.entry.DeviceCenterEntryController;
import miui.systemui.controlcenter.panel.main.devicecontrol.DeviceControlsEntryController;
import miui.systemui.controlcenter.panel.main.media.MediaPlayerController;
import miui.systemui.controlcenter.panel.main.qs.EditButtonController;
import miui.systemui.controlcenter.panel.main.qs.QSListController;
import miui.systemui.controlcenter.panel.main.qs.WordlessModeController;
import miui.systemui.controlcenter.panel.main.recyclerview.ControlCenterViewHolder;
import miui.systemui.controlcenter.panel.main.volume.VolumeSliderController;
import miui.systemui.util.DeviceUtils;
import miui.systemui.util.MiuiMathUtils;
import miuix.animation.FolmeEase;
import miuix.animation.IStateStyle;
import miuix.animation.base.AnimConfig;
import miuix.animation.property.FloatProperty;
import miuix.animation.utils.EaseManager;

/* JADX INFO: loaded from: classes.dex */
public final class AnimatorConfigHelper {
    private static final long ANIM_DELAY = 50;
    public static final Companion Companion = new Companion(null);
    private static final EaseManager.EaseStyle EASE_EDIT_ADD_ICON_HIDE_COLOR;
    private static final EaseManager.EaseStyle EASE_EDIT_ADD_ICON_SHOW_COLOR;
    private static final EaseManager.EaseStyle EASE_EDIT_CARD_HIDE_COLOR;
    private static final EaseManager.EaseStyle EASE_EDIT_CARD_HIDE_SIZE;
    private static final EaseManager.EaseStyle EASE_EDIT_CARD_SHOW_COLOR;
    private static final EaseManager.EaseStyle EASE_EDIT_CARD_SHOW_SIZE;
    private static final EaseManager.EaseStyle EASE_EDIT_HEADER_HIDE_COLOR;
    private static final EaseManager.EaseStyle EASE_EDIT_HEADER_SHOW_COLOR;
    private static final EaseManager.EaseStyle EASE_EDIT_HIDE_POSITION;
    private static final EaseManager.EaseStyle EASE_EDIT_SHOW_POSITION;
    private static final EaseManager.EaseStyle EASE_TRANS_X;
    private static final int EDIT_MAX_ROWS = 5;
    private static final int ROWS_COUNT = 4;
    private static final float SHOW_LABEL_TRAN_Y = 200.0f;
    private static final String TAG = "AnimatorConfigHelper";
    private final Context context;
    private final H0.d density$delegate;
    private final AnimConfig hideConfig;
    private final AnimConfig showConfig;

    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final EaseManager.EaseStyle getEASE_EDIT_ADD_ICON_HIDE_COLOR() {
            return AnimatorConfigHelper.EASE_EDIT_ADD_ICON_HIDE_COLOR;
        }

        public final EaseManager.EaseStyle getEASE_EDIT_ADD_ICON_SHOW_COLOR() {
            return AnimatorConfigHelper.EASE_EDIT_ADD_ICON_SHOW_COLOR;
        }

        public final EaseManager.EaseStyle getEASE_EDIT_CARD_HIDE_COLOR() {
            return AnimatorConfigHelper.EASE_EDIT_CARD_HIDE_COLOR;
        }

        public final EaseManager.EaseStyle getEASE_EDIT_CARD_HIDE_SIZE() {
            return AnimatorConfigHelper.EASE_EDIT_CARD_HIDE_SIZE;
        }

        public final EaseManager.EaseStyle getEASE_EDIT_CARD_SHOW_COLOR() {
            return AnimatorConfigHelper.EASE_EDIT_CARD_SHOW_COLOR;
        }

        public final EaseManager.EaseStyle getEASE_EDIT_CARD_SHOW_SIZE() {
            return AnimatorConfigHelper.EASE_EDIT_CARD_SHOW_SIZE;
        }

        public final EaseManager.EaseStyle getEASE_EDIT_HEADER_HIDE_COLOR() {
            return AnimatorConfigHelper.EASE_EDIT_HEADER_HIDE_COLOR;
        }

        public final EaseManager.EaseStyle getEASE_EDIT_HEADER_SHOW_COLOR() {
            return AnimatorConfigHelper.EASE_EDIT_HEADER_SHOW_COLOR;
        }

        public final EaseManager.EaseStyle getEASE_EDIT_HIDE_POSITION() {
            return AnimatorConfigHelper.EASE_EDIT_HIDE_POSITION;
        }

        public final EaseManager.EaseStyle getEASE_EDIT_SHOW_POSITION() {
            return AnimatorConfigHelper.EASE_EDIT_SHOW_POSITION;
        }

        private Companion() {
        }
    }

    static {
        EaseManager.EaseStyle style = EaseManager.getStyle(-2, 0.95f, 0.3f);
        n.f(style, "getStyle(...)");
        EASE_TRANS_X = style;
        EaseManager.EaseStyle easeStyleSpring = FolmeEase.spring(0.95f, 0.35f);
        n.f(easeStyleSpring, "spring(...)");
        EASE_EDIT_CARD_SHOW_SIZE = easeStyleSpring;
        EaseManager.EaseStyle easeStyleSpring2 = FolmeEase.spring(0.95f, 0.35f);
        n.f(easeStyleSpring2, "spring(...)");
        EASE_EDIT_CARD_SHOW_COLOR = easeStyleSpring2;
        EaseManager.EaseStyle easeStyleSpring3 = FolmeEase.spring(1.0f, 0.4f);
        n.f(easeStyleSpring3, "spring(...)");
        EASE_EDIT_SHOW_POSITION = easeStyleSpring3;
        EaseManager.EaseStyle easeStyleSpring4 = FolmeEase.spring(0.95f, 0.35f);
        n.f(easeStyleSpring4, "spring(...)");
        EASE_EDIT_ADD_ICON_SHOW_COLOR = easeStyleSpring4;
        EaseManager.EaseStyle easeStyleSpring5 = FolmeEase.spring(0.95f, 0.35f);
        n.f(easeStyleSpring5, "spring(...)");
        EASE_EDIT_HEADER_SHOW_COLOR = easeStyleSpring5;
        EaseManager.EaseStyle easeStyleSpring6 = FolmeEase.spring(0.95f, 0.15f);
        n.f(easeStyleSpring6, "spring(...)");
        EASE_EDIT_CARD_HIDE_SIZE = easeStyleSpring6;
        EaseManager.EaseStyle easeStyleSpring7 = FolmeEase.spring(0.95f, 0.15f);
        n.f(easeStyleSpring7, "spring(...)");
        EASE_EDIT_CARD_HIDE_COLOR = easeStyleSpring7;
        EaseManager.EaseStyle easeStyleSpring8 = FolmeEase.spring(0.95f, 0.35f);
        n.f(easeStyleSpring8, "spring(...)");
        EASE_EDIT_HIDE_POSITION = easeStyleSpring8;
        EaseManager.EaseStyle easeStyleSpring9 = FolmeEase.spring(0.95f, 0.15f);
        n.f(easeStyleSpring9, "spring(...)");
        EASE_EDIT_ADD_ICON_HIDE_COLOR = easeStyleSpring9;
        EaseManager.EaseStyle easeStyleSpring10 = FolmeEase.spring(0.95f, 0.15f);
        n.f(easeStyleSpring10, "spring(...)");
        EASE_EDIT_HEADER_HIDE_COLOR = easeStyleSpring10;
    }

    public AnimatorConfigHelper(Context context) {
        n.g(context, "context");
        this.context = context;
        AnimConfig animConfig = new AnimConfig();
        ControlCenterViewHolder.Companion companion = ControlCenterViewHolder.Companion;
        FloatProperty<ControlCenterViewHolder> trans_x = companion.getTRANS_X();
        EaseManager.EaseStyle easeStyle = EASE_TRANS_X;
        this.hideConfig = animConfig.setSpecial(trans_x, easeStyle, new float[0]);
        this.showConfig = new AnimConfig().setSpecial(companion.getTRANS_X(), easeStyle, new float[0]);
        this.density$delegate = H0.e.b(new AnimatorConfigHelper$density$2(this));
    }

    private final float getDensity() {
        return ((Number) this.density$delegate.getValue()).floatValue();
    }

    public static /* synthetic */ AnimConfig updateAnimEase$default(AnimatorConfigHelper animatorConfigHelper, ControlCenterViewHolder controlCenterViewHolder, boolean z2, boolean z3, int i2, int i3, int i4, Object obj) {
        if ((i4 & 4) != 0) {
            z3 = false;
        }
        return animatorConfigHelper.updateAnimEase(controlCenterViewHolder, z2, z3, i2, i3);
    }

    private final float updateProFactor(int i2, int i3) {
        int i4 = i3 % 4;
        int i5 = i3 / 4;
        if (i4 != 0) {
            i5++;
        }
        int i6 = (i2 - i5) - 1;
        if (i6 > 5 || i6 <= 0) {
            return 1.0f;
        }
        return (i6 * 1.0f) / 5;
    }

    public final boolean isHighDevice() {
        return DeviceUtils.isHighLevel() || DeviceUtils.isUltraLevel();
    }

    public final boolean isLowDevice() {
        return DeviceUtils.isLowLevel() || DeviceUtils.isMidLowLevel();
    }

    public final boolean isMidDevice() {
        return DeviceUtils.isNormalLevel();
    }

    public final AnimConfig updateAnimEase(ControlCenterViewHolder holder, boolean z2, boolean z3, int i2, int i3) {
        AnimConfig animConfig;
        String str;
        n.g(holder, "holder");
        switch (holder.getItemViewType()) {
            case 2273:
            case EditButtonController.TYPE_EDIT /* 3348 */:
            case MediaPlayerController.TYPE_MEDIA /* 63342 */:
            case BrightnessSliderController.TYPE_BRIGHTNESS_SLIDER /* 274442 */:
            case DeviceCenterEntryController.TYPE_DEVICE_CENTER /* 338423 */:
            case VolumeSliderController.TYPE_VOLUME_SLIDER /* 865269 */:
            case DeviceControlsEntryController.TYPE_DEVICE_CONTROLS /* 2668765 */:
                if (!z2) {
                    AnimConfig animConfig2 = this.hideConfig;
                    ControlCenterViewHolder.Companion companion = ControlCenterViewHolder.Companion;
                    animConfig2.setSpecial(companion.getALPHA(), EASE_EDIT_CARD_HIDE_COLOR, new float[0]);
                    this.hideConfig.setSpecial(companion.getSCALE(), EASE_EDIT_CARD_HIDE_SIZE, new float[0]);
                } else {
                    AnimConfig animConfig3 = this.showConfig;
                    ControlCenterViewHolder.Companion companion2 = ControlCenterViewHolder.Companion;
                    animConfig3.setSpecial(companion2.getALPHA(), EASE_EDIT_CARD_SHOW_COLOR, ANIM_DELAY, new float[0]);
                    this.showConfig.setSpecial(companion2.getSCALE(), EASE_EDIT_CARD_SHOW_SIZE, ANIM_DELAY, new float[0]);
                }
                break;
            case QSListController.EditModeDividerTextItem.ITEM_TYPE_DIVIDER_TEXT /* 8398 */:
            case QSListController.EditModeDividerItem.ITEM_TYPE_DIVIDER /* 3484337 */:
                if (z2) {
                    AnimConfig animConfig4 = this.showConfig;
                    ControlCenterViewHolder.Companion companion3 = ControlCenterViewHolder.Companion;
                    animConfig4.setSpecial(companion3.getALPHA(), EASE_EDIT_ADD_ICON_SHOW_COLOR, ANIM_DELAY, new float[0]);
                    if (isMidDevice()) {
                        this.showConfig.setSpecial(companion3.getTRANS_Y(), EASE_EDIT_SHOW_POSITION, new float[0]);
                    } else if (isHighDevice()) {
                        float fUpdateProFactor = updateProFactor(i2, i3);
                        MiuiMathUtils miuiMathUtils = MiuiMathUtils.INSTANCE;
                        this.showConfig.setSpecial(companion3.getTRANS_Y(), FolmeEase.spring(miuiMathUtils.lerp(fUpdateProFactor, 1.0f, 1.3f), miuiMathUtils.lerp(fUpdateProFactor, 0.4f, 0.5f)), new float[0]);
                    }
                } else if (isHighDevice() || isMidDevice()) {
                    AnimConfig animConfig5 = this.hideConfig;
                    ControlCenterViewHolder.Companion companion4 = ControlCenterViewHolder.Companion;
                    animConfig5.setSpecial(companion4.getTRANS_Y(), EASE_EDIT_HIDE_POSITION, new float[0]);
                    this.hideConfig.setSpecial(companion4.getALPHA(), EASE_EDIT_ADD_ICON_HIDE_COLOR, new float[0]);
                }
                break;
            case 8453:
                if (!z3) {
                    if (z2) {
                        AnimConfig animConfig6 = this.showConfig;
                        ControlCenterViewHolder.Companion companion5 = ControlCenterViewHolder.Companion;
                        animConfig6.setSpecial(companion5.getALPHA(), EASE_EDIT_ADD_ICON_SHOW_COLOR, ANIM_DELAY, new float[0]);
                        if (isMidDevice()) {
                            this.showConfig.setSpecial(companion5.getTRANS_Y(), EASE_EDIT_SHOW_POSITION, new float[0]);
                        } else if (isHighDevice()) {
                            float fUpdateProFactor2 = updateProFactor(i2, i3);
                            MiuiMathUtils miuiMathUtils2 = MiuiMathUtils.INSTANCE;
                            this.showConfig.setSpecial(companion5.getTRANS_Y(), FolmeEase.spring(miuiMathUtils2.lerp(fUpdateProFactor2, 1.0f, 1.3f), miuiMathUtils2.lerp(fUpdateProFactor2, 0.4f, 0.5f)), new float[0]);
                            Log.i(TAG, "driftingAnim factor : " + fUpdateProFactor2);
                        }
                    } else if (isHighDevice() || isMidDevice()) {
                        AnimConfig animConfig7 = this.hideConfig;
                        ControlCenterViewHolder.Companion companion6 = ControlCenterViewHolder.Companion;
                        animConfig7.setSpecial(companion6.getTRANS_Y(), EASE_EDIT_HIDE_POSITION, new float[0]);
                        this.hideConfig.setSpecial(companion6.getALPHA(), EASE_EDIT_ADD_ICON_HIDE_COLOR, new float[0]);
                    }
                } else if (holder.getHolderTransY() <= 0.0f) {
                    this.showConfig.setSpecial(ControlCenterViewHolder.Companion.getTRANS_Y(), EASE_EDIT_HIDE_POSITION, new float[0]);
                } else {
                    this.showConfig.setSpecial(ControlCenterViewHolder.Companion.getTRANS_Y(), EASE_EDIT_SHOW_POSITION, new float[0]);
                }
                break;
            case WordlessModeController.TYPE_SHOW_LABEL /* 334833 */:
                if (!z2) {
                    AnimConfig animConfig8 = this.hideConfig;
                    ControlCenterViewHolder.Companion companion7 = ControlCenterViewHolder.Companion;
                    animConfig8.setSpecial(companion7.getTRANS_Y(), EASE_EDIT_HIDE_POSITION, new float[0]);
                    this.hideConfig.setSpecial(companion7.getALPHA(), EASE_EDIT_HEADER_HIDE_COLOR, new float[0]);
                } else {
                    AnimConfig animConfig9 = this.showConfig;
                    ControlCenterViewHolder.Companion companion8 = ControlCenterViewHolder.Companion;
                    animConfig9.setSpecial(companion8.getTRANS_Y(), EASE_EDIT_SHOW_POSITION, new float[0]);
                    this.showConfig.setSpecial(companion8.getALPHA(), EASE_EDIT_HEADER_SHOW_COLOR, ANIM_DELAY, new float[0]);
                }
                break;
        }
        if (z2) {
            animConfig = this.showConfig;
            str = "showConfig";
        } else {
            animConfig = this.hideConfig;
            str = "hideConfig";
        }
        n.f(animConfig, str);
        return animConfig;
    }

    public final void updateTranY(ControlCenterViewHolder holder, boolean z2, float f2) {
        n.g(holder, "holder");
        if (holder.getItemViewType() == 334833) {
            if (!z2) {
                holder.setTargetTransY(200.0f);
                return;
            }
            IStateStyle anim = holder.getAnim();
            ControlCenterViewHolder.Companion companion = ControlCenterViewHolder.Companion;
            anim.setTo(companion.getTRANS_X(), Float.valueOf(holder.getHolderTransX()), companion.getTRANS_Y(), Float.valueOf(200.0f));
            return;
        }
        Log.d(TAG, "update: " + f2 + " " + getDensity() + " ");
        if (!z2) {
            if (isMidDevice() || isHighDevice()) {
                holder.setTargetTransY(f2 / getDensity());
                return;
            }
            return;
        }
        if (isMidDevice() || isHighDevice()) {
            IStateStyle anim2 = holder.getAnim();
            ControlCenterViewHolder.Companion companion2 = ControlCenterViewHolder.Companion;
            anim2.setTo(companion2.getTRANS_X(), Float.valueOf(holder.getHolderTransX()), companion2.getTRANS_Y(), Float.valueOf(f2 / getDensity()));
        }
    }
}
