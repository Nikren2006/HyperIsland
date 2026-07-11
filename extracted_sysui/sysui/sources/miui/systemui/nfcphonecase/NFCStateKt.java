package miui.systemui.nfcphonecase;

import H0.s;
import kotlin.jvm.internal.n;
import miuix.animation.FolmeEase;
import miuix.animation.base.AnimConfig;
import miuix.animation.base.AnimSpecialConfig;
import miuix.animation.controller.AnimState;
import miuix.animation.property.FloatProperty;
import miuix.animation.property.ValueProperty;
import miuix.animation.utils.EaseManager;

/* JADX INFO: loaded from: classes4.dex */
public final class NFCStateKt {
    public static final String ANIM_STATE_HIDE = "ANIM_STATE_HIDE";
    public static final String ANIM_STATE_INIT = "ANIM_STATE_HIDE";
    public static final String ANIM_STATE_SHOW_1 = "ANIM_STATE_SHOW_1";
    public static final String ANIM_STATE_SHOW_2 = "ANIM_STATE_SHOW_2";
    public static final String ANIM_STATE_SHOW_3 = "ANIM_STATE_SHOW_3";
    public static final String ANIM_STATE_SHOW_4 = "ANIM_STATE_SHOW_4";
    private static final EaseManager.EaseStyle HIDE_EASE;
    private static final EaseManager.EaseStyle HIDE_EASE_INTENSITY;
    private static final EaseManager.EaseStyle HIDE_EASE_SIZE;
    private static final EaseManager.EaseStyle HIDE_EASE_STRENGTH;
    private static final EaseManager.EaseStyle SHOW_EASE_FOUR;
    private static final EaseManager.EaseStyle SHOW_EASE_ONE;
    private static final EaseManager.EaseStyle SHOW_EASE_ONE_INTENSITY;
    private static final EaseManager.EaseStyle SHOW_EASE_ONE_STRENGTH;
    private static final EaseManager.EaseStyle SHOW_EASE_THREE;
    private static final EaseManager.EaseStyle SHOW_EASE_THREE_SIZE;
    private static final EaseManager.EaseStyle SHOW_EASE_THREE_STRENGTH;
    private static final EaseManager.EaseStyle SHOW_EASE_TWO;
    private static final NFCState SHOW_STATE = new NFCState(240.0f, 0.95f, 0.4f, 60.0f, 1.2f, 1.0f, 1.0f, 1.0f, 1.0f, 0.0f, 80.0f, 40.0f, 1.0f, 1.0f, 1.0f, 1.0f, 0.6f);
    private static final NFCState HIDE_STATE = new NFCState(0.0f, 1.0f, 0.0f, 100.0f, 0.0f, 1.0f, 1.0f, 1.0f, 1.0f, 0.0f, 120.0f, 40.0f, 0.0f, 1.0f, 1.0f, 1.0f, 0.6f);
    private static final ValueProperty<String> SCREEN_RADIUS = new ValueProperty<>("screenRadius", 0.002f);
    private static final ValueProperty<String> BLUR_RADIUS = new ValueProperty<>("blurRadius", 0.002f);
    private static final ValueProperty<String> BLUR_SCALE = new ValueProperty<>("blurScale", 0.002f);
    private static final ValueProperty<String> DIM_RATIO = new ValueProperty<>("dimRatio", 0.002f);
    private static final ValueProperty<String> BOX_GLOW_STRENGTH = new ValueProperty<>("boxGlowStrength", 0.002f);
    private static final ValueProperty<String> BOX_GLOW_INTENSITY = new ValueProperty<>("boxGlowIntensity", 0.002f);
    private static final ValueProperty<String> BOX_GLOW_COLOR_R = new ValueProperty<>("boxGlowColorR", 0.002f);
    private static final ValueProperty<String> BOX_GLOW_COLOR_G = new ValueProperty<>("boxGlowColorG", 0.002f);
    private static final ValueProperty<String> BOX_GLOW_COLOR_B = new ValueProperty<>("boxGlowColorB", 0.002f);
    private static final ValueProperty<String> BOX_GLOW_COLOR_A = new ValueProperty<>("boxGlowColorA", 0.002f);
    private static final ValueProperty<String> RING_SIZE = new ValueProperty<>("ringSize", 0.002f);
    private static final ValueProperty<String> RING_THICKNESS = new ValueProperty<>("ringThickness", 0.002f);
    private static final ValueProperty<String> RING_GLOW_STRENGTH = new ValueProperty<>("ringGlowStrength", 0.002f);
    private static final ValueProperty<String> RING_GLOW_INTENSITY = new ValueProperty<>("ringGlowIntensity", 0.002f);
    private static final ValueProperty<String> RING_GLOW_COLOR_R = new ValueProperty<>("ringGlowColorR", 0.002f);
    private static final ValueProperty<String> RING_GLOW_COLOR_G = new ValueProperty<>("ringGlowColorG", 0.002f);
    private static final ValueProperty<String> RING_GLOW_COLOR_B = new ValueProperty<>("ringGlowColorB", 0.002f);
    private static final ValueProperty<String> RING_GLOW_COLOR_A = new ValueProperty<>("ringGlowColorA", 0.002f);

    static {
        EaseManager.EaseStyle easeStyleSpring = FolmeEase.spring(1.0f, 0.25f);
        n.f(easeStyleSpring, "spring(...)");
        SHOW_EASE_ONE = easeStyleSpring;
        EaseManager.EaseStyle easeStyleSpring2 = FolmeEase.spring(1.0f, 0.2f);
        n.f(easeStyleSpring2, "spring(...)");
        SHOW_EASE_ONE_INTENSITY = easeStyleSpring2;
        EaseManager.EaseStyle easeStyleSpring3 = FolmeEase.spring(1.0f, 0.28f);
        n.f(easeStyleSpring3, "spring(...)");
        SHOW_EASE_ONE_STRENGTH = easeStyleSpring3;
        EaseManager.EaseStyle easeStyleSpring4 = FolmeEase.spring(1.0f, 0.35f);
        n.f(easeStyleSpring4, "spring(...)");
        SHOW_EASE_TWO = easeStyleSpring4;
        EaseManager.EaseStyle easeStyleSpring5 = FolmeEase.spring(0.9f, 0.35f);
        n.f(easeStyleSpring5, "spring(...)");
        SHOW_EASE_THREE = easeStyleSpring5;
        EaseManager.EaseStyle easeStyleSpring6 = FolmeEase.spring(0.75f, 0.48f);
        n.f(easeStyleSpring6, "spring(...)");
        SHOW_EASE_THREE_SIZE = easeStyleSpring6;
        EaseManager.EaseStyle easeStyleLinear = FolmeEase.linear(230L);
        n.f(easeStyleLinear, "linear(...)");
        SHOW_EASE_THREE_STRENGTH = easeStyleLinear;
        EaseManager.EaseStyle easeStyleLinear2 = FolmeEase.linear(2000L);
        n.f(easeStyleLinear2, "linear(...)");
        SHOW_EASE_FOUR = easeStyleLinear2;
        EaseManager.EaseStyle easeStyleSpring7 = FolmeEase.spring(1.0f, 0.45f);
        n.f(easeStyleSpring7, "spring(...)");
        HIDE_EASE = easeStyleSpring7;
        EaseManager.EaseStyle easeStyleSpring8 = FolmeEase.spring(0.8f, 0.4f);
        n.f(easeStyleSpring8, "spring(...)");
        HIDE_EASE_SIZE = easeStyleSpring8;
        EaseManager.EaseStyle easeStyleSpring9 = FolmeEase.spring(0.95f, 0.3f);
        n.f(easeStyleSpring9, "spring(...)");
        HIDE_EASE_STRENGTH = easeStyleSpring9;
        EaseManager.EaseStyle easeStyleSpring10 = FolmeEase.spring(0.95f, 0.28f);
        n.f(easeStyleSpring10, "spring(...)");
        HIDE_EASE_INTENSITY = easeStyleSpring10;
    }

    public static final ValueProperty<String> getBLUR_RADIUS() {
        return BLUR_RADIUS;
    }

    public static final ValueProperty<String> getBLUR_SCALE() {
        return BLUR_SCALE;
    }

    public static final ValueProperty<String> getBOX_GLOW_COLOR_A() {
        return BOX_GLOW_COLOR_A;
    }

    public static final ValueProperty<String> getBOX_GLOW_COLOR_B() {
        return BOX_GLOW_COLOR_B;
    }

    public static final ValueProperty<String> getBOX_GLOW_COLOR_G() {
        return BOX_GLOW_COLOR_G;
    }

    public static final ValueProperty<String> getBOX_GLOW_COLOR_R() {
        return BOX_GLOW_COLOR_R;
    }

    public static final ValueProperty<String> getBOX_GLOW_INTENSITY() {
        return BOX_GLOW_INTENSITY;
    }

    public static final ValueProperty<String> getBOX_GLOW_STRENGTH() {
        return BOX_GLOW_STRENGTH;
    }

    public static final ValueProperty<String> getDIM_RATIO() {
        return DIM_RATIO;
    }

    public static final AnimState getDefaultState(String stateName, float f2) {
        n.g(stateName, "stateName");
        AnimState animState = new AnimState(stateName);
        ValueProperty<String> valueProperty = BLUR_RADIUS;
        NFCState nFCState = HIDE_STATE;
        animState.add(valueProperty, nFCState.getBlurRadius(), new long[0]);
        animState.add(BLUR_SCALE, nFCState.getBlurScale(), new long[0]);
        animState.add(DIM_RATIO, nFCState.getDimRatio(), new long[0]);
        animState.add(BOX_GLOW_STRENGTH, nFCState.getBoxGlowStrength(), new long[0]);
        animState.add(BOX_GLOW_INTENSITY, nFCState.getBoxGlowIntensity(), new long[0]);
        animState.add(BOX_GLOW_COLOR_R, nFCState.getBoxGlowColorR(), new long[0]);
        animState.add(BOX_GLOW_COLOR_G, nFCState.getBoxGlowColorG(), new long[0]);
        animState.add(BOX_GLOW_COLOR_B, nFCState.getBoxGlowColorB(), new long[0]);
        animState.add(BOX_GLOW_COLOR_A, nFCState.getBoxGlowColorA(), new long[0]);
        animState.add(RING_SIZE, f2, new long[0]);
        animState.add(RING_THICKNESS, nFCState.getRingThickness(), new long[0]);
        animState.add(RING_GLOW_STRENGTH, nFCState.getRingGlowStrength(), new long[0]);
        animState.add(RING_GLOW_INTENSITY, nFCState.getRingGlowIntensity(), new long[0]);
        animState.add(RING_GLOW_COLOR_R, nFCState.getRingGlowColorR(), new long[0]);
        animState.add(RING_GLOW_COLOR_G, nFCState.getRingGlowColorG(), new long[0]);
        animState.add(RING_GLOW_COLOR_B, nFCState.getRingGlowColorB(), new long[0]);
        animState.add(RING_GLOW_COLOR_A, nFCState.getRingGlowColorA(), new long[0]);
        return animState;
    }

    public static final EaseManager.EaseStyle getHIDE_EASE() {
        return HIDE_EASE;
    }

    public static final EaseManager.EaseStyle getHIDE_EASE_INTENSITY() {
        return HIDE_EASE_INTENSITY;
    }

    public static final EaseManager.EaseStyle getHIDE_EASE_SIZE() {
        return HIDE_EASE_SIZE;
    }

    public static final EaseManager.EaseStyle getHIDE_EASE_STRENGTH() {
        return HIDE_EASE_STRENGTH;
    }

    public static final NFCState getHIDE_STATE() {
        return HIDE_STATE;
    }

    public static final AnimConfig getHideAnimConfig() {
        AnimConfig animConfig = new AnimConfig();
        animConfig.setEase(HIDE_EASE);
        ValueProperty<String> valueProperty = RING_SIZE;
        AnimSpecialConfig animSpecialConfig = new AnimSpecialConfig();
        animSpecialConfig.setEase(HIDE_EASE_SIZE);
        s sVar = s.f314a;
        animConfig.setSpecial(valueProperty, animSpecialConfig);
        ValueProperty<String> valueProperty2 = RING_GLOW_STRENGTH;
        AnimSpecialConfig animSpecialConfig2 = new AnimSpecialConfig();
        animSpecialConfig2.setEase(HIDE_EASE_STRENGTH);
        animConfig.setSpecial(valueProperty2, animSpecialConfig2);
        ValueProperty<String> valueProperty3 = RING_GLOW_INTENSITY;
        AnimSpecialConfig animSpecialConfig3 = new AnimSpecialConfig();
        animSpecialConfig3.setEase(HIDE_EASE_INTENSITY);
        animConfig.setSpecial(valueProperty3, animSpecialConfig3);
        return animConfig;
    }

    public static final AnimState getHideState(float f2) {
        return getDefaultState("ANIM_STATE_HIDE", f2 * 1.22f);
    }

    public static final AnimState getInitState(float f2) {
        return getDefaultState("ANIM_STATE_HIDE", f2 * 3);
    }

    public static final ValueProperty<String> getRING_GLOW_COLOR_A() {
        return RING_GLOW_COLOR_A;
    }

    public static final ValueProperty<String> getRING_GLOW_COLOR_B() {
        return RING_GLOW_COLOR_B;
    }

    public static final ValueProperty<String> getRING_GLOW_COLOR_G() {
        return RING_GLOW_COLOR_G;
    }

    public static final ValueProperty<String> getRING_GLOW_COLOR_R() {
        return RING_GLOW_COLOR_R;
    }

    public static final ValueProperty<String> getRING_GLOW_INTENSITY() {
        return RING_GLOW_INTENSITY;
    }

    public static final ValueProperty<String> getRING_GLOW_STRENGTH() {
        return RING_GLOW_STRENGTH;
    }

    public static final ValueProperty<String> getRING_SIZE() {
        return RING_SIZE;
    }

    public static final ValueProperty<String> getRING_THICKNESS() {
        return RING_THICKNESS;
    }

    public static final ValueProperty<String> getSCREEN_RADIUS() {
        return SCREEN_RADIUS;
    }

    public static final EaseManager.EaseStyle getSHOW_EASE_FOUR() {
        return SHOW_EASE_FOUR;
    }

    public static final EaseManager.EaseStyle getSHOW_EASE_ONE() {
        return SHOW_EASE_ONE;
    }

    public static final EaseManager.EaseStyle getSHOW_EASE_ONE_INTENSITY() {
        return SHOW_EASE_ONE_INTENSITY;
    }

    public static final EaseManager.EaseStyle getSHOW_EASE_ONE_STRENGTH() {
        return SHOW_EASE_ONE_STRENGTH;
    }

    public static final EaseManager.EaseStyle getSHOW_EASE_THREE() {
        return SHOW_EASE_THREE;
    }

    public static final EaseManager.EaseStyle getSHOW_EASE_THREE_SIZE() {
        return SHOW_EASE_THREE_SIZE;
    }

    public static final EaseManager.EaseStyle getSHOW_EASE_THREE_STRENGTH() {
        return SHOW_EASE_THREE_STRENGTH;
    }

    public static final EaseManager.EaseStyle getSHOW_EASE_TWO() {
        return SHOW_EASE_TWO;
    }

    public static final NFCState getSHOW_STATE() {
        return SHOW_STATE;
    }

    public static final AnimConfig getShowFourAnimConfig() {
        AnimConfig animConfig = new AnimConfig();
        animConfig.setEase(SHOW_EASE_FOUR);
        return animConfig;
    }

    public static final AnimState getShowFourStepState() {
        AnimState animState = new AnimState(ANIM_STATE_SHOW_4);
        animState.add((FloatProperty) BOX_GLOW_STRENGTH, 50.0f, new long[0]);
        animState.add((FloatProperty) BOX_GLOW_INTENSITY, 0.7f, new long[0]);
        ValueProperty<String> valueProperty = BOX_GLOW_COLOR_A;
        NFCState nFCState = SHOW_STATE;
        animState.add(valueProperty, nFCState.getRingGlowColorA(), new long[0]);
        animState.add(BOX_GLOW_COLOR_R, nFCState.getRingGlowColorR(), new long[0]);
        animState.add(BOX_GLOW_COLOR_G, nFCState.getRingGlowColorG(), new long[0]);
        animState.add(BOX_GLOW_COLOR_B, nFCState.getRingGlowColorB(), new long[0]);
        return animState;
    }

    public static final AnimConfig getShowOneAnimConfig() {
        AnimConfig animConfig = new AnimConfig();
        animConfig.setEase(SHOW_EASE_ONE);
        ValueProperty<String> valueProperty = BOX_GLOW_INTENSITY;
        AnimSpecialConfig animSpecialConfig = new AnimSpecialConfig();
        animSpecialConfig.setEase(SHOW_EASE_ONE_INTENSITY);
        s sVar = s.f314a;
        animConfig.setSpecial(valueProperty, animSpecialConfig);
        ValueProperty<String> valueProperty2 = BOX_GLOW_STRENGTH;
        AnimSpecialConfig animSpecialConfig2 = new AnimSpecialConfig();
        animSpecialConfig2.setEase(SHOW_EASE_ONE_STRENGTH);
        animConfig.setSpecial(valueProperty2, animSpecialConfig2);
        return animConfig;
    }

    public static final AnimState getShowOneStepState() {
        AnimState animState = new AnimState(ANIM_STATE_SHOW_1);
        animState.add((FloatProperty) BOX_GLOW_STRENGTH, 30, new long[0]);
        ValueProperty<String> valueProperty = BOX_GLOW_INTENSITY;
        NFCState nFCState = SHOW_STATE;
        animState.add(valueProperty, nFCState.getBoxGlowIntensity(), new long[0]);
        animState.add(BOX_GLOW_COLOR_A, nFCState.getBoxGlowColorA(), new long[0]);
        animState.add(BOX_GLOW_COLOR_R, nFCState.getBoxGlowColorR(), new long[0]);
        animState.add(BOX_GLOW_COLOR_G, nFCState.getBoxGlowColorG(), new long[0]);
        animState.add(BOX_GLOW_COLOR_B, nFCState.getBoxGlowColorB(), new long[0]);
        animState.add(RING_GLOW_COLOR_A, nFCState.getRingGlowColorA(), new long[0]);
        animState.add(RING_GLOW_COLOR_R, nFCState.getRingGlowColorR(), new long[0]);
        animState.add(RING_GLOW_COLOR_G, nFCState.getRingGlowColorG(), new long[0]);
        animState.add(RING_GLOW_COLOR_B, nFCState.getRingGlowColorB(), new long[0]);
        return animState;
    }

    public static final AnimConfig getShowThreeAnimConfig() {
        AnimConfig animConfig = new AnimConfig();
        animConfig.setEase(SHOW_EASE_THREE);
        ValueProperty<String> valueProperty = RING_SIZE;
        AnimSpecialConfig animSpecialConfig = new AnimSpecialConfig();
        animSpecialConfig.setEase(SHOW_EASE_THREE_SIZE);
        s sVar = s.f314a;
        animConfig.setSpecial(valueProperty, animSpecialConfig);
        ValueProperty<String> valueProperty2 = RING_GLOW_STRENGTH;
        AnimSpecialConfig animSpecialConfig2 = new AnimSpecialConfig();
        animSpecialConfig2.setEase(SHOW_EASE_THREE_STRENGTH);
        animConfig.setSpecial(valueProperty2, animSpecialConfig2);
        return animConfig;
    }

    public static final AnimState getShowThreeStepState(float f2) {
        AnimState animState = new AnimState(ANIM_STATE_SHOW_3);
        animState.add((FloatProperty) RING_GLOW_INTENSITY, 1.0f, new long[0]);
        animState.add(RING_SIZE, f2, new long[0]);
        ValueProperty<String> valueProperty = RING_THICKNESS;
        NFCState nFCState = SHOW_STATE;
        animState.add(valueProperty, nFCState.getRingThickness(), new long[0]);
        animState.add(RING_GLOW_STRENGTH, nFCState.getRingGlowStrength(), new long[0]);
        return animState;
    }

    public static final AnimConfig getShowTwoAnimConfig() {
        AnimConfig animConfig = new AnimConfig();
        animConfig.setDelay(130L);
        animConfig.setEase(SHOW_EASE_TWO);
        return animConfig;
    }

    public static final AnimState getShowTwoStepState() {
        AnimState animState = new AnimState(ANIM_STATE_SHOW_2);
        ValueProperty<String> valueProperty = DIM_RATIO;
        NFCState nFCState = SHOW_STATE;
        animState.add(valueProperty, nFCState.getDimRatio(), new long[0]);
        animState.add(BLUR_RADIUS, nFCState.getBlurRadius(), new long[0]);
        animState.add(BLUR_SCALE, nFCState.getBlurScale(), new long[0]);
        return animState;
    }
}
