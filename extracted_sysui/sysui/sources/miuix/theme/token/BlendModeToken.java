package miuix.theme.token;

import com.android.systemui.miui.volume.VolumePanelViewController;
import miuix.animation.utils.EaseManager;

/* JADX INFO: loaded from: classes5.dex */
public enum BlendModeToken {
    CLEAR(0),
    SRC(1),
    DST(2),
    SRC_OVER(3),
    DST_OVER(4),
    SRC_IN(5),
    DST_IN(6),
    SRC_OUT(7),
    DST_OUT(8),
    SRC_ATOP(9),
    DST_ATOP(10),
    XOR(11),
    PLUS(12),
    MODULATE(13),
    SCREEN(14),
    OVERLAY(15),
    DARKEN(16),
    LIGHTEN(17),
    COLOR_DODGE(18),
    COLOR_BURN(19),
    HARD_LIGHT(20),
    SOFT_LIGHT(21),
    DIFFERENCE(22),
    EXCLUSION(23),
    MULTIPLY(24),
    HUE(25),
    SATURATION(26),
    COLOR(27),
    LUMINOSITY(28),
    LAST_COEFF(29),
    LAST_SEPARABLE(30),
    LAST(31),
    LINEAR_LIGHT(100),
    LINEAR_LIGHT_WITH_GREYSCALE(101),
    MI_DIFFERENCE(102),
    LAB_LIGHTEN_WITH_GREYSCALE(103),
    LAB_DARKEN_WITH_GREYSCALE(105),
    LAB(106),
    LINEAR_LIGHT_LAB(107),
    COLOR_DODGE_V2(118),
    COLOR_BURN_V2(119),
    PLUS_DARKER(120),
    PLUS_LIGHTER(121),
    SATURATION_V2(EaseManager.EaseStyleDef.PERLIN),
    BRIGHTNESS(VolumePanelViewController.HAPTIC_V2_VOLUME_MIN),
    LUMINANCE(VolumePanelViewController.HAPTIC_V2_VOLUME_MAX);

    public final int value;

    BlendModeToken(int i2) {
        this.value = i2;
    }

    public static int toValue(BlendModeToken blendModeToken) {
        return blendModeToken.value;
    }
}
