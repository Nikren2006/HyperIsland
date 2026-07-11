package miui.systemui.util;

/* JADX INFO: loaded from: classes4.dex */
public final class HapticConstants {
    public static final HapticConstants INSTANCE = new HapticConstants();
    private static final int EFFECT_ID_BOUNDARY_TIME = 1;
    private static final int EFFECT_ID_BUTTON_LIGHT = 4;

    private HapticConstants() {
    }

    public final int getEFFECT_ID_BOUNDARY_TIME() {
        return EFFECT_ID_BOUNDARY_TIME;
    }

    public final int getEFFECT_ID_BUTTON_LIGHT() {
        return EFFECT_ID_BUTTON_LIGHT;
    }
}
