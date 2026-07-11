package miui.systemui.util;

import miuix.theme.token.BlendModeToken;
import miuix.theme.token.ColorBlendToken;

/* JADX INFO: loaded from: classes4.dex */
public final class MiuiColorBlendToken {
    private static final ColorBlendToken CC_BACKGROUND_BLEND_COLORS;
    private static final ColorBlendToken CC_BRIGHTNESS_SLIDER_ICON_BLEND_COLORS;
    private static final ColorBlendToken CC_BRIGHTNESS_SLIDER_ICON_BLEND_COLORS_DEFAULT;
    private static final ColorBlendToken CC_DETAIL_PANEL_BACKGROUND_BLEND_COLORS;
    private static final ColorBlendToken CC_DETAIL_PANEL_LIST_BLEND_COLORS;
    private static final ColorBlendToken CC_DETAIL_PANEL_MORE_BUTTON_BLEND_COLORS;
    private static final ColorBlendToken CC_DETAIL_PANEL_MORE_BUTTON_PRESSED_BLEND_COLORS;
    private static final ColorBlendToken CC_DETAIL_PANEL_SLIDER_BACKGROUND_BLEND_COLORS;
    private static final ColorBlendToken CC_DETAIL_PANEL_SLIDER_BLEND_COLORS;
    private static final ColorBlendToken CC_DETAIL_PANEL_SLIDER_ICON_BLEND_COLORS;
    private static final ColorBlendToken CC_DETAIL_PANEL_TILE_DEFAULT_BLEND_COLORS;
    private static final ColorBlendToken CC_DETAIL_PANEL_TILE_ON_BLEND_COLORS;
    private static final ColorBlendToken CC_EDIT_BUTTON_BLEND_COLORS;
    private static final ColorBlendToken CC_MIPLAY_DEVICE_ICON_BLEND_COLORS;
    private static final ColorBlendToken CC_MIPLAY_PRE_NEXT_BLEND_COLORS;
    private static final ColorBlendToken CC_SLIDER_BLEND_COLORS;
    private static final ColorBlendToken CC_SLIDER_DISABLED_BLEND_COLORS;
    private static final ColorBlendToken CC_SLIDER_ICON_BLEND_COLORS;
    private static final ColorBlendToken CC_TILE_DEFAULT_BLEND_COLORS;
    private static final ColorBlendToken CC_TILE_ON_BLEND_COLORS;
    private static final ColorBlendToken CC_TILE_RESTRICTED_BLEND_COLORS;
    private static final ColorBlendToken CC_TILE_RESTRICTED_ICON_BLEND_COLORS;
    private static final ColorBlendToken CC_VOLUME_SLIDER_ICON_BLEND_COLORS;
    private static final ColorBlendToken CC_VOLUME_SLIDER_ICON_BLEND_COLORS_DEFAULT;
    public static final MiuiColorBlendToken INSTANCE = new MiuiColorBlendToken();
    private static final ColorBlendToken LINEAR_LIGHT_66B0B0B0_LAB_FF59CF00_SRC_OVER_1AFFFFFF;
    private static final ColorBlendToken LINEAR_LIGHT_66B0B0B0_LAB_FF63C400_SRC_OVER_1AFFFFFF;
    private static final ColorBlendToken LINEAR_LIGHT_66B0B0B0_LAB_FF63D600_SRC_OVER_1AFFFFFF;
    private static final ColorBlendToken LINEAR_LIGHT_66B0B0B0_LAB_FF6EC900_SRC_OVER_1AFFFFFF;
    private static final ColorBlendToken LINEAR_LIGHT_807D7D7D_LAB_FF59B500_SRC_OVER_003482FF;
    private static final ColorBlendToken LINEAR_LIGHT_807D7D7D_LAB_FF59C200_SRC_OVER_003482FF;
    private static final ColorBlendToken LINEAR_LIGHT_807D7D7D_LAB_FF66AD00_SRC_OVER_003482FF;
    private static final ColorBlendToken LINEAR_LIGHT_807D7D7D_LAB_FF66AD00_SRC_OVER_FF3482FF;
    private static final ColorBlendToken LINEAR_LIGHT_807D7D7D_LAB_FF6BB800_SRC_OVER_003482FF;
    private static final ColorBlendToken LINEAR_LIGHT_B37D7D7D_LAB_FF129600;
    private static final ColorBlendToken LINEAR_LIGHT_B37D7D7D_LAB_FF2E8A00;
    private static final ColorBlendToken LINEAR_LIGHT_BF7D7D7D_LAB_FF29AD00;
    private static final ColorBlendToken LINEAR_LIGHT_BF7D7D7D_LAB_FF45A100;
    private static final ColorBlendToken LINEAR_LIGHT_CCB0B0B0_LAB_FFEBFF00_SRC_OVER_99FFFFFF;
    private static final ColorBlendToken RINGER_BG_OFF;
    private static final ColorBlendToken RINGER_BG_OFF_CC;
    private static final ColorBlendToken RINGER_BG_OFF_EXPANDED;
    private static final ColorBlendToken RINGER_BG_ON;
    private static final ColorBlendToken RINGER_BG_ON_CC;
    private static final ColorBlendToken RINGER_BG_ON_EXPANDED;
    private static final ColorBlendToken TIMER_BG_CC;
    private static final ColorBlendToken TIMER_BG_EXPANDED;
    private static final ColorBlendToken TIMER_PROGRESS_CC;
    private static final ColorBlendToken TIMER_PROGRESS_EXPANDED;
    private static final ColorBlendToken VC_EXPANDED_BTN_ICON;
    private static final ColorBlendToken VC_ICON;
    private static final ColorBlendToken VC_ICON_CC;
    private static final ColorBlendToken VC_ICON_CC_MAIN;
    private static final ColorBlendToken VC_ICON_DOMINANT_COLOR;
    private static final ColorBlendToken VC_ICON_EXPANDED;
    private static final ColorBlendToken VC_PROGRESS;
    private static final ColorBlendToken VC_PROGRESS_CC;
    private static final ColorBlendToken VC_PROGRESS_CC_MAIN;
    private static final ColorBlendToken VC_PROGRESS_EXPANDED;
    private static final ColorBlendToken VC_PROGRESS_MUTE;
    private static final ColorBlendToken VC_PROGRESS_MUTE_CC;
    private static final ColorBlendToken VC_PROGRESS_MUTE_CC_MAIN;
    private static final ColorBlendToken VC_PROGRESS_MUTE_EXPANDED;
    private static final ColorBlendToken VC_SLIDER_BG;
    private static final ColorBlendToken VC_SLIDER_BG_CC;
    private static final ColorBlendToken VC_SLIDER_BG_CC_MAIN;
    private static final ColorBlendToken VC_SLIDER_BG_EXPANDED;
    private static final ColorBlendToken VOLUME_DIALOG_BG_CC;
    private static final ColorBlendToken VOLUME_DIALOG_BG_EXPANDED;

    static {
        ColorBlendToken colorBlendTokenBuild = new ColorBlendToken.Builder().setConfig(new int[]{1384153216}, new int[]{BlendModeToken.LUMINOSITY.value}).build();
        kotlin.jvm.internal.n.f(colorBlendTokenBuild, "build(...)");
        CC_BACKGROUND_BLEND_COLORS = colorBlendTokenBuild;
        BlendModeToken blendModeToken = BlendModeToken.LINEAR_LIGHT;
        int i2 = blendModeToken.value;
        BlendModeToken blendModeToken2 = BlendModeToken.LAB;
        ColorBlendToken colorBlendTokenBuild2 = new ColorBlendToken.Builder().setConfig(new int[]{-1283621507, -15559168}, new int[]{i2, blendModeToken2.value}).build();
        kotlin.jvm.internal.n.f(colorBlendTokenBuild2, "build(...)");
        CC_TILE_DEFAULT_BLEND_COLORS = colorBlendTokenBuild2;
        int i3 = blendModeToken.value;
        int i4 = blendModeToken2.value;
        BlendModeToken blendModeToken3 = BlendModeToken.SRC_OVER;
        ColorBlendToken colorBlendTokenBuild3 = new ColorBlendToken.Builder().setConfig(new int[]{-860835664, -1310976, -1711276033}, new int[]{i3, i4, blendModeToken3.value}).build();
        kotlin.jvm.internal.n.f(colorBlendTokenBuild3, "build(...)");
        CC_TILE_ON_BLEND_COLORS = colorBlendTokenBuild3;
        ColorBlendToken colorBlendTokenBuild4 = new ColorBlendToken.Builder().setConfig(new int[]{-1716473680, -13378048, 1728053247}, new int[]{blendModeToken.value, blendModeToken2.value, blendModeToken3.value}).build();
        kotlin.jvm.internal.n.f(colorBlendTokenBuild4, "build(...)");
        CC_TILE_RESTRICTED_BLEND_COLORS = colorBlendTokenBuild4;
        ColorBlendToken colorBlendTokenBuild5 = new ColorBlendToken.Builder().setConfig(new int[]{-860835664, -1310976, -1711276033}, new int[]{blendModeToken.value, blendModeToken2.value, blendModeToken3.value}).build();
        kotlin.jvm.internal.n.f(colorBlendTokenBuild5, "build(...)");
        CC_SLIDER_BLEND_COLORS = colorBlendTokenBuild5;
        ColorBlendToken colorBlendTokenBuild6 = new ColorBlendToken.Builder().setConfig(new int[]{1722855600, -9516800, 452984831}, new int[]{blendModeToken.value, blendModeToken2.value, blendModeToken3.value}).build();
        kotlin.jvm.internal.n.f(colorBlendTokenBuild6, "build(...)");
        CC_SLIDER_DISABLED_BLEND_COLORS = colorBlendTokenBuild6;
        ColorBlendToken colorBlendTokenBuild7 = new ColorBlendToken.Builder().setConfig(new int[]{-2139259523, -10898176}, new int[]{blendModeToken.value, blendModeToken2.value}).build();
        kotlin.jvm.internal.n.f(colorBlendTokenBuild7, "build(...)");
        CC_SLIDER_ICON_BLEND_COLORS = colorBlendTokenBuild7;
        ColorBlendToken colorBlendTokenBuild8 = new ColorBlendToken.Builder().setConfig(new int[]{-2139259523, -10898176, 16752389}, new int[]{blendModeToken.value, blendModeToken2.value, blendModeToken3.value}).build();
        kotlin.jvm.internal.n.f(colorBlendTokenBuild8, "build(...)");
        CC_BRIGHTNESS_SLIDER_ICON_BLEND_COLORS_DEFAULT = colorBlendTokenBuild8;
        ColorBlendToken colorBlendTokenBuild9 = new ColorBlendToken.Builder().setConfig(new int[]{-2139259523, -10898176, -24827}, new int[]{blendModeToken.value, blendModeToken2.value, blendModeToken3.value}).build();
        kotlin.jvm.internal.n.f(colorBlendTokenBuild9, "build(...)");
        CC_BRIGHTNESS_SLIDER_ICON_BLEND_COLORS = colorBlendTokenBuild9;
        ColorBlendToken colorBlendTokenBuild10 = new ColorBlendToken.Builder().setConfig(new int[]{-2139259523, -10898176, 3441407}, new int[]{blendModeToken.value, blendModeToken2.value, blendModeToken3.value}).build();
        kotlin.jvm.internal.n.f(colorBlendTokenBuild10, "build(...)");
        CC_VOLUME_SLIDER_ICON_BLEND_COLORS_DEFAULT = colorBlendTokenBuild10;
        ColorBlendToken colorBlendTokenBuild11 = new ColorBlendToken.Builder().setConfig(new int[]{-2139259523, -10898176, -13335809}, new int[]{blendModeToken.value, blendModeToken2.value, blendModeToken3.value}).build();
        kotlin.jvm.internal.n.f(colorBlendTokenBuild11, "build(...)");
        CC_VOLUME_SLIDER_ICON_BLEND_COLORS = colorBlendTokenBuild11;
        ColorBlendToken colorBlendTokenBuild12 = new ColorBlendToken.Builder().setConfig(new int[]{1299543413, -14697216}, new int[]{blendModeToken.value, blendModeToken2.value}).build();
        kotlin.jvm.internal.n.f(colorBlendTokenBuild12, "build(...)");
        CC_EDIT_BUTTON_BLEND_COLORS = colorBlendTokenBuild12;
        ColorBlendToken colorBlendTokenBuild13 = new ColorBlendToken.Builder().setConfig(new int[]{-1283621507, -15559168}, new int[]{blendModeToken.value, blendModeToken2.value}).build();
        kotlin.jvm.internal.n.f(colorBlendTokenBuild13, "build(...)");
        CC_DETAIL_PANEL_BACKGROUND_BLEND_COLORS = colorBlendTokenBuild13;
        ColorBlendToken colorBlendTokenBuild14 = new ColorBlendToken.Builder().setConfig(new int[]{-1082294915, -14045952}, new int[]{blendModeToken.value, blendModeToken2.value}).build();
        kotlin.jvm.internal.n.f(colorBlendTokenBuild14, "build(...)");
        CC_DETAIL_PANEL_SLIDER_BACKGROUND_BLEND_COLORS = colorBlendTokenBuild14;
        ColorBlendToken colorBlendTokenBuild15 = new ColorBlendToken.Builder().setConfig(new int[]{-860835664, -1310976, -1711276033}, new int[]{blendModeToken.value, blendModeToken2.value, blendModeToken3.value}).build();
        kotlin.jvm.internal.n.f(colorBlendTokenBuild15, "build(...)");
        CC_DETAIL_PANEL_SLIDER_BLEND_COLORS = colorBlendTokenBuild15;
        ColorBlendToken colorBlendTokenBuild16 = new ColorBlendToken.Builder().setConfig(new int[]{-1082294915, -14045952}, new int[]{blendModeToken.value, blendModeToken2.value}).build();
        kotlin.jvm.internal.n.f(colorBlendTokenBuild16, "build(...)");
        CC_DETAIL_PANEL_TILE_DEFAULT_BLEND_COLORS = colorBlendTokenBuild16;
        ColorBlendToken colorBlendTokenBuild17 = new ColorBlendToken.Builder().setConfig(new int[]{-1283621507, -15555072}, new int[]{blendModeToken.value, blendModeToken2.value}).build();
        kotlin.jvm.internal.n.f(colorBlendTokenBuild17, "build(...)");
        CC_TILE_RESTRICTED_ICON_BLEND_COLORS = colorBlendTokenBuild17;
        ColorBlendToken colorBlendTokenBuild18 = new ColorBlendToken.Builder().setConfig(new int[]{-860835664, -1310976, -1310976}, new int[]{blendModeToken.value, blendModeToken2.value, blendModeToken3.value}).build();
        kotlin.jvm.internal.n.f(colorBlendTokenBuild18, "build(...)");
        CC_DETAIL_PANEL_TILE_ON_BLEND_COLORS = colorBlendTokenBuild18;
        ColorBlendToken colorBlendTokenBuild19 = new ColorBlendToken.Builder().setConfig(new int[]{-2139259523, -10894848}, new int[]{blendModeToken.value, blendModeToken2.value}).build();
        kotlin.jvm.internal.n.f(colorBlendTokenBuild19, "build(...)");
        CC_DETAIL_PANEL_SLIDER_ICON_BLEND_COLORS = colorBlendTokenBuild19;
        ColorBlendToken colorBlendTokenBuild20 = new ColorBlendToken.Builder().setConfig(new int[]{-1082294915, -14045952}, new int[]{blendModeToken.value, blendModeToken2.value}).build();
        kotlin.jvm.internal.n.f(colorBlendTokenBuild20, "build(...)");
        CC_DETAIL_PANEL_MORE_BUTTON_BLEND_COLORS = colorBlendTokenBuild20;
        ColorBlendToken colorBlendTokenBuild21 = new ColorBlendToken.Builder().setConfig(new int[]{-1082294915, -14045952, 352321535}, new int[]{blendModeToken.value, blendModeToken2.value, blendModeToken3.value}).build();
        kotlin.jvm.internal.n.f(colorBlendTokenBuild21, "build(...)");
        CC_DETAIL_PANEL_MORE_BUTTON_PRESSED_BLEND_COLORS = colorBlendTokenBuild21;
        ColorBlendToken colorBlendTokenBuild22 = new ColorBlendToken.Builder().setConfig(new int[]{-1082294915, -14045952}, new int[]{blendModeToken.value, blendModeToken2.value}).build();
        kotlin.jvm.internal.n.f(colorBlendTokenBuild22, "build(...)");
        CC_DETAIL_PANEL_LIST_BLEND_COLORS = colorBlendTokenBuild22;
        ColorBlendToken colorBlendTokenBuild23 = new ColorBlendToken.Builder().setConfig(new int[]{-8553091, -6165248}, new int[]{blendModeToken.value, blendModeToken2.value}).build();
        kotlin.jvm.internal.n.f(colorBlendTokenBuild23, "build(...)");
        CC_MIPLAY_DEVICE_ICON_BLEND_COLORS = colorBlendTokenBuild23;
        ColorBlendToken colorBlendTokenBuild24 = new ColorBlendToken.Builder().setConfig(new int[]{-8553091, -6165248}, new int[]{blendModeToken.value, blendModeToken2.value}).build();
        kotlin.jvm.internal.n.f(colorBlendTokenBuild24, "build(...)");
        CC_MIPLAY_PRE_NEXT_BLEND_COLORS = colorBlendTokenBuild24;
        ColorBlendToken colorBlendTokenBuild25 = new ColorBlendToken.Builder().setConfig(new int[]{-860835664, -1310976, -1711276033}, new int[]{blendModeToken.value, blendModeToken2.value, blendModeToken3.value}).build();
        kotlin.jvm.internal.n.f(colorBlendTokenBuild25, "build(...)");
        LINEAR_LIGHT_CCB0B0B0_LAB_FFEBFF00_SRC_OVER_99FFFFFF = colorBlendTokenBuild25;
        ColorBlendToken colorBlendTokenBuild26 = new ColorBlendToken.Builder().setConfig(new int[]{1722855600, -10234368, 452984831}, new int[]{blendModeToken.value, blendModeToken2.value, blendModeToken3.value}).build();
        kotlin.jvm.internal.n.f(colorBlendTokenBuild26, "build(...)");
        LINEAR_LIGHT_66B0B0B0_LAB_FF63D600_SRC_OVER_1AFFFFFF = colorBlendTokenBuild26;
        ColorBlendToken colorBlendTokenBuild27 = new ColorBlendToken.Builder().setConfig(new int[]{1722855600, -10238976, 452984831}, new int[]{blendModeToken.value, blendModeToken2.value, blendModeToken3.value}).build();
        kotlin.jvm.internal.n.f(colorBlendTokenBuild27, "build(...)");
        LINEAR_LIGHT_66B0B0B0_LAB_FF63C400_SRC_OVER_1AFFFFFF = colorBlendTokenBuild27;
        ColorBlendToken colorBlendTokenBuild28 = new ColorBlendToken.Builder().setConfig(new int[]{-1082294915, -14045952}, new int[]{blendModeToken.value, blendModeToken2.value}).build();
        kotlin.jvm.internal.n.f(colorBlendTokenBuild28, "build(...)");
        LINEAR_LIGHT_BF7D7D7D_LAB_FF29AD00 = colorBlendTokenBuild28;
        ColorBlendToken colorBlendTokenBuild29 = new ColorBlendToken.Builder().setConfig(new int[]{-1082294915, -12214016}, new int[]{blendModeToken.value, blendModeToken2.value}).build();
        kotlin.jvm.internal.n.f(colorBlendTokenBuild29, "build(...)");
        LINEAR_LIGHT_BF7D7D7D_LAB_FF45A100 = colorBlendTokenBuild29;
        ColorBlendToken colorBlendTokenBuild30 = new ColorBlendToken.Builder().setConfig(new int[]{1722855600, -10891520, 452984831}, new int[]{blendModeToken.value, blendModeToken2.value, blendModeToken3.value}).build();
        kotlin.jvm.internal.n.f(colorBlendTokenBuild30, "build(...)");
        LINEAR_LIGHT_66B0B0B0_LAB_FF59CF00_SRC_OVER_1AFFFFFF = colorBlendTokenBuild30;
        ColorBlendToken colorBlendTokenBuild31 = new ColorBlendToken.Builder().setConfig(new int[]{1722855600, -9516800, 452984831}, new int[]{blendModeToken.value, blendModeToken2.value, blendModeToken3.value}).build();
        kotlin.jvm.internal.n.f(colorBlendTokenBuild31, "build(...)");
        LINEAR_LIGHT_66B0B0B0_LAB_FF6EC900_SRC_OVER_1AFFFFFF = colorBlendTokenBuild31;
        ColorBlendToken colorBlendTokenBuild32 = new ColorBlendToken.Builder().setConfig(new int[]{-2139259523, -10894848, 3441407}, new int[]{blendModeToken.value, blendModeToken2.value, blendModeToken3.value}).build();
        kotlin.jvm.internal.n.f(colorBlendTokenBuild32, "build(...)");
        LINEAR_LIGHT_807D7D7D_LAB_FF59C200_SRC_OVER_003482FF = colorBlendTokenBuild32;
        ColorBlendToken colorBlendTokenBuild33 = new ColorBlendToken.Builder().setConfig(new int[]{-2139259523, -10898176, 3441407}, new int[]{blendModeToken.value, blendModeToken2.value, blendModeToken3.value}).build();
        kotlin.jvm.internal.n.f(colorBlendTokenBuild33, "build(...)");
        LINEAR_LIGHT_807D7D7D_LAB_FF59B500_SRC_OVER_003482FF = colorBlendTokenBuild33;
        ColorBlendToken colorBlendTokenBuild34 = new ColorBlendToken.Builder().setConfig(new int[]{-2139259523, -10048256, 3441407}, new int[]{blendModeToken.value, blendModeToken2.value, blendModeToken3.value}).build();
        kotlin.jvm.internal.n.f(colorBlendTokenBuild34, "build(...)");
        LINEAR_LIGHT_807D7D7D_LAB_FF66AD00_SRC_OVER_003482FF = colorBlendTokenBuild34;
        ColorBlendToken colorBlendTokenBuild35 = new ColorBlendToken.Builder().setConfig(new int[]{-2139259523, -9717760, 3441407}, new int[]{blendModeToken.value, blendModeToken2.value, blendModeToken3.value}).build();
        kotlin.jvm.internal.n.f(colorBlendTokenBuild35, "build(...)");
        LINEAR_LIGHT_807D7D7D_LAB_FF6BB800_SRC_OVER_003482FF = colorBlendTokenBuild35;
        ColorBlendToken colorBlendTokenBuild36 = new ColorBlendToken.Builder().setConfig(new int[]{-1283621507, -13727232}, new int[]{blendModeToken.value, blendModeToken2.value}).build();
        kotlin.jvm.internal.n.f(colorBlendTokenBuild36, "build(...)");
        LINEAR_LIGHT_B37D7D7D_LAB_FF2E8A00 = colorBlendTokenBuild36;
        ColorBlendToken colorBlendTokenBuild37 = new ColorBlendToken.Builder().setConfig(new int[]{-1283621507, -15559168}, new int[]{blendModeToken.value, blendModeToken2.value}).build();
        kotlin.jvm.internal.n.f(colorBlendTokenBuild37, "build(...)");
        LINEAR_LIGHT_B37D7D7D_LAB_FF129600 = colorBlendTokenBuild37;
        ColorBlendToken colorBlendTokenBuild38 = new ColorBlendToken.Builder().setConfig(new int[]{-2139259523, -10048256, -13335809}, new int[]{blendModeToken.value, blendModeToken2.value, blendModeToken3.value}).build();
        kotlin.jvm.internal.n.f(colorBlendTokenBuild38, "build(...)");
        LINEAR_LIGHT_807D7D7D_LAB_FF66AD00_SRC_OVER_FF3482FF = colorBlendTokenBuild38;
        VC_EXPANDED_BTN_ICON = colorBlendTokenBuild34;
        VC_SLIDER_BG = colorBlendTokenBuild36;
        VC_PROGRESS = colorBlendTokenBuild25;
        VC_PROGRESS_MUTE = colorBlendTokenBuild26;
        VC_ICON = colorBlendTokenBuild34;
        RINGER_BG_ON = colorBlendTokenBuild25;
        RINGER_BG_OFF = colorBlendTokenBuild36;
        VC_SLIDER_BG_EXPANDED = colorBlendTokenBuild29;
        VC_PROGRESS_EXPANDED = colorBlendTokenBuild25;
        VC_PROGRESS_MUTE_EXPANDED = colorBlendTokenBuild27;
        VC_ICON_EXPANDED = colorBlendTokenBuild35;
        RINGER_BG_ON_EXPANDED = colorBlendTokenBuild25;
        RINGER_BG_OFF_EXPANDED = colorBlendTokenBuild29;
        TIMER_BG_EXPANDED = colorBlendTokenBuild29;
        TIMER_PROGRESS_EXPANDED = colorBlendTokenBuild25;
        VOLUME_DIALOG_BG_EXPANDED = colorBlendTokenBuild36;
        VC_SLIDER_BG_CC = colorBlendTokenBuild28;
        VC_SLIDER_BG_CC_MAIN = colorBlendTokenBuild37;
        VC_PROGRESS_CC = colorBlendTokenBuild25;
        VC_PROGRESS_CC_MAIN = colorBlendTokenBuild25;
        VC_PROGRESS_MUTE_CC = colorBlendTokenBuild30;
        VC_PROGRESS_MUTE_CC_MAIN = colorBlendTokenBuild31;
        VC_ICON_CC_MAIN = colorBlendTokenBuild33;
        VC_ICON_CC = colorBlendTokenBuild32;
        VC_ICON_DOMINANT_COLOR = colorBlendTokenBuild38;
        RINGER_BG_ON_CC = colorBlendTokenBuild25;
        RINGER_BG_OFF_CC = colorBlendTokenBuild28;
        TIMER_BG_CC = colorBlendTokenBuild29;
        TIMER_PROGRESS_CC = colorBlendTokenBuild25;
        VOLUME_DIALOG_BG_CC = colorBlendTokenBuild37;
    }

    private MiuiColorBlendToken() {
    }

    public final ColorBlendToken getCC_BACKGROUND_BLEND_COLORS() {
        return CC_BACKGROUND_BLEND_COLORS;
    }

    public final ColorBlendToken getCC_BRIGHTNESS_SLIDER_ICON_BLEND_COLORS() {
        return CC_BRIGHTNESS_SLIDER_ICON_BLEND_COLORS;
    }

    public final ColorBlendToken getCC_BRIGHTNESS_SLIDER_ICON_BLEND_COLORS_DEFAULT() {
        return CC_BRIGHTNESS_SLIDER_ICON_BLEND_COLORS_DEFAULT;
    }

    public final ColorBlendToken getCC_DETAIL_PANEL_BACKGROUND_BLEND_COLORS() {
        return CC_DETAIL_PANEL_BACKGROUND_BLEND_COLORS;
    }

    public final ColorBlendToken getCC_DETAIL_PANEL_LIST_BLEND_COLORS() {
        return CC_DETAIL_PANEL_LIST_BLEND_COLORS;
    }

    public final ColorBlendToken getCC_DETAIL_PANEL_MORE_BUTTON_BLEND_COLORS() {
        return CC_DETAIL_PANEL_MORE_BUTTON_BLEND_COLORS;
    }

    public final ColorBlendToken getCC_DETAIL_PANEL_MORE_BUTTON_PRESSED_BLEND_COLORS() {
        return CC_DETAIL_PANEL_MORE_BUTTON_PRESSED_BLEND_COLORS;
    }

    public final ColorBlendToken getCC_DETAIL_PANEL_SLIDER_BACKGROUND_BLEND_COLORS() {
        return CC_DETAIL_PANEL_SLIDER_BACKGROUND_BLEND_COLORS;
    }

    public final ColorBlendToken getCC_DETAIL_PANEL_SLIDER_BLEND_COLORS() {
        return CC_DETAIL_PANEL_SLIDER_BLEND_COLORS;
    }

    public final ColorBlendToken getCC_DETAIL_PANEL_SLIDER_ICON_BLEND_COLORS() {
        return CC_DETAIL_PANEL_SLIDER_ICON_BLEND_COLORS;
    }

    public final ColorBlendToken getCC_DETAIL_PANEL_TILE_DEFAULT_BLEND_COLORS() {
        return CC_DETAIL_PANEL_TILE_DEFAULT_BLEND_COLORS;
    }

    public final ColorBlendToken getCC_DETAIL_PANEL_TILE_ON_BLEND_COLORS() {
        return CC_DETAIL_PANEL_TILE_ON_BLEND_COLORS;
    }

    public final ColorBlendToken getCC_EDIT_BUTTON_BLEND_COLORS() {
        return CC_EDIT_BUTTON_BLEND_COLORS;
    }

    public final ColorBlendToken getCC_MIPLAY_DEVICE_ICON_BLEND_COLORS() {
        return CC_MIPLAY_DEVICE_ICON_BLEND_COLORS;
    }

    public final ColorBlendToken getCC_MIPLAY_PRE_NEXT_BLEND_COLORS() {
        return CC_MIPLAY_PRE_NEXT_BLEND_COLORS;
    }

    public final ColorBlendToken getCC_SLIDER_BLEND_COLORS() {
        return CC_SLIDER_BLEND_COLORS;
    }

    public final ColorBlendToken getCC_SLIDER_DISABLED_BLEND_COLORS() {
        return CC_SLIDER_DISABLED_BLEND_COLORS;
    }

    public final ColorBlendToken getCC_SLIDER_ICON_BLEND_COLORS() {
        return CC_SLIDER_ICON_BLEND_COLORS;
    }

    public final ColorBlendToken getCC_TILE_DEFAULT_BLEND_COLORS() {
        return CC_TILE_DEFAULT_BLEND_COLORS;
    }

    public final ColorBlendToken getCC_TILE_ON_BLEND_COLORS() {
        return CC_TILE_ON_BLEND_COLORS;
    }

    public final ColorBlendToken getCC_TILE_RESTRICTED_BLEND_COLORS() {
        return CC_TILE_RESTRICTED_BLEND_COLORS;
    }

    public final ColorBlendToken getCC_TILE_RESTRICTED_ICON_BLEND_COLORS() {
        return CC_TILE_RESTRICTED_ICON_BLEND_COLORS;
    }

    public final ColorBlendToken getCC_VOLUME_SLIDER_ICON_BLEND_COLORS() {
        return CC_VOLUME_SLIDER_ICON_BLEND_COLORS;
    }

    public final ColorBlendToken getCC_VOLUME_SLIDER_ICON_BLEND_COLORS_DEFAULT() {
        return CC_VOLUME_SLIDER_ICON_BLEND_COLORS_DEFAULT;
    }

    public final ColorBlendToken getRINGER_BG_OFF() {
        return RINGER_BG_OFF;
    }

    public final ColorBlendToken getRINGER_BG_OFF_CC() {
        return RINGER_BG_OFF_CC;
    }

    public final ColorBlendToken getRINGER_BG_OFF_EXPANDED() {
        return RINGER_BG_OFF_EXPANDED;
    }

    public final ColorBlendToken getRINGER_BG_ON() {
        return RINGER_BG_ON;
    }

    public final ColorBlendToken getRINGER_BG_ON_CC() {
        return RINGER_BG_ON_CC;
    }

    public final ColorBlendToken getRINGER_BG_ON_EXPANDED() {
        return RINGER_BG_ON_EXPANDED;
    }

    public final ColorBlendToken getTIMER_BG_CC() {
        return TIMER_BG_CC;
    }

    public final ColorBlendToken getTIMER_BG_EXPANDED() {
        return TIMER_BG_EXPANDED;
    }

    public final ColorBlendToken getTIMER_PROGRESS_CC() {
        return TIMER_PROGRESS_CC;
    }

    public final ColorBlendToken getTIMER_PROGRESS_EXPANDED() {
        return TIMER_PROGRESS_EXPANDED;
    }

    public final ColorBlendToken getVC_EXPANDED_BTN_ICON() {
        return VC_EXPANDED_BTN_ICON;
    }

    public final ColorBlendToken getVC_ICON() {
        return VC_ICON;
    }

    public final ColorBlendToken getVC_ICON_CC() {
        return VC_ICON_CC;
    }

    public final ColorBlendToken getVC_ICON_CC_MAIN() {
        return VC_ICON_CC_MAIN;
    }

    public final ColorBlendToken getVC_ICON_DOMINANT_COLOR() {
        return VC_ICON_DOMINANT_COLOR;
    }

    public final ColorBlendToken getVC_ICON_EXPANDED() {
        return VC_ICON_EXPANDED;
    }

    public final ColorBlendToken getVC_PROGRESS() {
        return VC_PROGRESS;
    }

    public final ColorBlendToken getVC_PROGRESS_CC() {
        return VC_PROGRESS_CC;
    }

    public final ColorBlendToken getVC_PROGRESS_CC_MAIN() {
        return VC_PROGRESS_CC_MAIN;
    }

    public final ColorBlendToken getVC_PROGRESS_EXPANDED() {
        return VC_PROGRESS_EXPANDED;
    }

    public final ColorBlendToken getVC_PROGRESS_MUTE() {
        return VC_PROGRESS_MUTE;
    }

    public final ColorBlendToken getVC_PROGRESS_MUTE_CC() {
        return VC_PROGRESS_MUTE_CC;
    }

    public final ColorBlendToken getVC_PROGRESS_MUTE_CC_MAIN() {
        return VC_PROGRESS_MUTE_CC_MAIN;
    }

    public final ColorBlendToken getVC_PROGRESS_MUTE_EXPANDED() {
        return VC_PROGRESS_MUTE_EXPANDED;
    }

    public final ColorBlendToken getVC_SLIDER_BG() {
        return VC_SLIDER_BG;
    }

    public final ColorBlendToken getVC_SLIDER_BG_CC() {
        return VC_SLIDER_BG_CC;
    }

    public final ColorBlendToken getVC_SLIDER_BG_CC_MAIN() {
        return VC_SLIDER_BG_CC_MAIN;
    }

    public final ColorBlendToken getVC_SLIDER_BG_EXPANDED() {
        return VC_SLIDER_BG_EXPANDED;
    }

    public final ColorBlendToken getVOLUME_DIALOG_BG_CC() {
        return VOLUME_DIALOG_BG_CC;
    }

    public final ColorBlendToken getVOLUME_DIALOG_BG_EXPANDED() {
        return VOLUME_DIALOG_BG_EXPANDED;
    }
}
