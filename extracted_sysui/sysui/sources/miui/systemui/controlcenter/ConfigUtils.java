package miui.systemui.controlcenter;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import kotlin.jvm.internal.n;
import miui.systemui.util.CommonUtils;
import miui.systemui.util.MiBlurCompat;

/* JADX INFO: loaded from: classes.dex */
public final class ConfigUtils {
    public static final ConfigUtils INSTANCE = new ConfigUtils();

    public interface OnConfigChangeListener {
        void onConfigurationChanged(int i2);
    }

    private ConfigUtils() {
    }

    public final boolean blurChanged(int i2) {
        return (MiBlurCompat.INSTANCE.getCONFIG_BLUR() & i2) != 0;
    }

    public final boolean colorsChanged(int i2) {
        return themeChanged(i2) || uiModeChanged(i2);
    }

    public final boolean densityChanged(int i2) {
        return (i2 & 4096) != 0;
    }

    public final boolean dimensionsChanged(int i2) {
        return dpiChanged(i2) || densityChanged(i2);
    }

    public final boolean dpiChanged(int i2) {
        return (i2 & 2048) != 0;
    }

    public final boolean fontSizeChanged(int i2) {
        return (1073741824 & i2) != 0;
    }

    public final boolean isLandscape(Context context) {
        n.g(context, "<this>");
        return context.getResources().getConfiguration().orientation == 2;
    }

    public final boolean layoutDirectionChanged(int i2) {
        return (i2 & 8192) != 0;
    }

    public final boolean orientationChanged(int i2) {
        return (i2 & 128) != 0;
    }

    public final boolean textAppearanceChanged(int i2) {
        return colorsChanged(i2) || dimensionsChanged(i2) || fontSizeChanged(i2);
    }

    public final boolean textsChanged(int i2) {
        return (i2 & 4) != 0;
    }

    public final boolean themeChanged(int i2) {
        return (4194304 & i2) != 0;
    }

    public final boolean uiModeChanged(int i2) {
        return (i2 & 512) != 0;
    }

    public final int update(Configuration configuration, Configuration newConfig) {
        n.g(configuration, "<this>");
        n.g(newConfig, "newConfig");
        int iUpdateFrom = configuration.updateFrom(newConfig);
        return CommonUtils.INSTANCE.miuiThemeChanged(newConfig) ? iUpdateFrom | 4194304 : iUpdateFrom;
    }

    public final boolean isLandscape(Resources resources) {
        n.g(resources, "<this>");
        return resources.getConfiguration().orientation == 2;
    }

    public final boolean isLandscape(Configuration configuration) {
        n.g(configuration, "<this>");
        return configuration.orientation == 2;
    }
}
