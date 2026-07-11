package miui.systemui.dynamicisland;

import android.os.SystemProperties;

/* JADX INFO: loaded from: classes3.dex */
public final class DynamicFeatureConfig {
    public static final DynamicFeatureConfig INSTANCE = new DynamicFeatureConfig();
    private static final boolean FEATURE_DYNAMIC_ISLAND = SystemProperties.getBoolean("persist.sys.feature.island", true);
    private static final boolean FEATURE_DYNAMIC_ISLAND_SHADER = SystemProperties.getBoolean("persist.sys.feature.island.shader", true);
    private static final boolean FEATURE_DYNAMIC_ISLAND_ANIMATION = SystemProperties.getBoolean("persist.sys.feature.island.animation", true);
    private static final boolean DEBUG_AVOID_SCREEN_BURN_IN = SystemProperties.getBoolean("debug.sysui.notif.island.asbi", false);
    private static final boolean ISLAND_XMS_RELEASE = SystemProperties.getBoolean("persist.sys.feature.xms.release", true);
    private static final boolean ISLAND_XMS_SWITCHER = SystemProperties.getBoolean("persist.sys.feature.xms.switcher", false);

    private DynamicFeatureConfig() {
    }

    public final boolean getDEBUG_AVOID_SCREEN_BURN_IN() {
        return DEBUG_AVOID_SCREEN_BURN_IN;
    }

    public final boolean getFEATURE_DYNAMIC_ISLAND() {
        return FEATURE_DYNAMIC_ISLAND;
    }

    public final boolean getFEATURE_DYNAMIC_ISLAND_ANIMATION() {
        return FEATURE_DYNAMIC_ISLAND_ANIMATION;
    }

    public final boolean getFEATURE_DYNAMIC_ISLAND_SHADER() {
        return FEATURE_DYNAMIC_ISLAND_SHADER;
    }

    public final boolean getISLAND_XMS_RELEASE() {
        return ISLAND_XMS_RELEASE;
    }

    public final boolean getISLAND_XMS_SWITCHER() {
        return ISLAND_XMS_SWITCHER;
    }
}
