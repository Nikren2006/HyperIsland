package miuix.autodensity;

import android.content.Context;
import android.content.res.Configuration;
import android.os.Build;
import android.util.Log;
import android.view.Display;
import androidx.annotation.Nullable;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import miuix.core.util.RomUtils;
import miuix.os.DeviceHelper;

/* JADX INFO: loaded from: classes3.dex */
public class DensityConfigManager {
    private static DensityConfigManager sInstance;
    private ConcurrentHashMap<Integer, DisplayDensityConfig> mDisplayConfigMap = new ConcurrentHashMap<>();
    private boolean mUseStableDensityLogic = false;

    @Deprecated
    private boolean mUseDeprecatedDensityLogic = false;
    private double mUserDeviceScale = 0.0d;
    private int mUserPPI = 0;
    private float mUserAccessibilityDpiDelta = 0.0f;
    private int mUserForcedDpi = 0;

    private DensityConfigManager() {
    }

    public static DensityConfigManager getInstance() {
        if (sInstance == null) {
            sInstance = new DensityConfigManager();
        }
        return sInstance;
    }

    @Deprecated
    public int getAccessibilityDefaultDisplayDpi(int i2) {
        DisplayDensityConfig defaultConfig = getDefaultConfig();
        if (defaultConfig == null) {
            return 160;
        }
        return defaultConfig.getAccessibilityDisplayDpi();
    }

    public double getCurrentDeviceScale(Display display) {
        DisplayDensityConfig displayConfig = getDisplayConfig(display);
        if (displayConfig == null) {
            return 1.0d;
        }
        return displayConfig.getCurrentDeviceScale();
    }

    public double getCurrentPPI(Display display) {
        DisplayDensityConfig displayConfig = getDisplayConfig(display);
        if (displayConfig == null) {
            return 0.0d;
        }
        return displayConfig.getCurrentPPI();
    }

    @Nullable
    public DisplayDensityConfig getDefaultConfig() {
        return this.mDisplayConfigMap.get(0);
    }

    public int getDeviceCurrentAccessibilityDpi(@Nullable Display display) {
        if (display == null) {
            return getDeviceCurrentAccessibilityDpi();
        }
        DisplayDensityConfig displayDensityConfig = this.mDisplayConfigMap.get(Integer.valueOf(display.getDisplayId() == 0 ? 0 : display.hashCode()));
        if (displayDensityConfig == null) {
            return 160;
        }
        return displayDensityConfig.getCurrentAccessibilityDpi();
    }

    public int getDeviceCurrentDefaultDpi() {
        DisplayDensityConfig defaultConfig = getDefaultConfig();
        if (defaultConfig == null) {
            return 160;
        }
        return defaultConfig.getDeviceCurrentDefaultDpi();
    }

    public int getDeviceCurrentForcedDpi(@Nullable Display display) {
        if (display == null) {
            return getDeviceCurrentForcedDpi();
        }
        DisplayDensityConfig displayDensityConfig = this.mDisplayConfigMap.get(Integer.valueOf(display.getDisplayId() == 0 ? 0 : display.hashCode()));
        if (displayDensityConfig == null) {
            return 160;
        }
        return displayDensityConfig.getCurrentForcedDpi();
    }

    public int getDeviceDefaultDpi() {
        DisplayDensityConfig defaultConfig = getDefaultConfig();
        if (defaultConfig == null) {
            return 160;
        }
        return defaultConfig.getDeviceDefaultDpi();
    }

    @Nullable
    public DisplayDensityConfig getDisplayConfig(@Nullable Display display) {
        if (display == null) {
            return null;
        }
        return this.mDisplayConfigMap.get(display.getDisplayId() == 0 ? 0 : Integer.valueOf(display.hashCode()));
    }

    @Nullable
    public DisplayDensityConfig getOrCreateDisplayConfig(Context context, Display display) {
        if (context == null || display == null) {
            return null;
        }
        int iValueOf = display.getDisplayId() == 0 ? 0 : Integer.valueOf(display.hashCode());
        DisplayDensityConfig displayDensityConfig = this.mDisplayConfigMap.get(iValueOf);
        if (displayDensityConfig == null) {
            displayDensityConfig = new DisplayDensityConfig(context, display);
            if (DebugUtil.isEnableDebug()) {
                DebugUtil.printDensityLog("DisplayDensityConfig create DisplayConfig display:  display: " + display + " context: " + context);
            }
            displayDensityConfig.setUserPPI(this.mUserPPI);
            displayDensityConfig.setUserDeviceScale((float) this.mUserDeviceScale);
            displayDensityConfig.setUseStableDensityLogic(this.mUseStableDensityLogic);
            displayDensityConfig.setUseDeprecatedDensityLogic(this.mUseDeprecatedDensityLogic);
            this.mDisplayConfigMap.put(iValueOf, displayDensityConfig);
        }
        return displayDensityConfig;
    }

    @Nullable
    public DensityConfig getOriginConfig(@Nullable Display display) {
        if (display == null) {
            return getOriginConfig();
        }
        DisplayDensityConfig displayDensityConfig = this.mDisplayConfigMap.get(Integer.valueOf(display.getDisplayId() == 0 ? 0 : display.hashCode()));
        if (displayDensityConfig == null) {
            return null;
        }
        return displayDensityConfig.getOriginConfig();
    }

    public float getScreenInches(Display display) {
        DisplayDensityConfig displayConfig = getDisplayConfig(display);
        if (displayConfig == null) {
            return 0.0f;
        }
        return displayConfig.getScreenInches();
    }

    @Nullable
    public DensityConfig getTargetConfig(@Nullable Display display) {
        if (display == null) {
            return getTargetConfig();
        }
        DisplayDensityConfig displayDensityConfig = this.mDisplayConfigMap.get(Integer.valueOf(display.getDisplayId() == 0 ? 0 : display.hashCode()));
        if (displayDensityConfig == null) {
            return null;
        }
        return displayDensityConfig.getTargetConfig();
    }

    public void init(Context context) {
        Display currentDisplay = DensityUtil.getCurrentDisplay(context);
        if (DeviceHelper.isCarWithScreen(context, currentDisplay) || DeviceHelper.isXiaomiSynergy(context)) {
            return;
        }
        getOrCreateDisplayConfig(context, currentDisplay);
    }

    public boolean isAutoDensityEnabled() {
        DisplayDensityConfig defaultConfig = getDefaultConfig();
        if (defaultConfig == null) {
            return false;
        }
        return defaultConfig.isAutoDensityEnabled();
    }

    public boolean isEnableLogicMetrics() {
        if (this.mUseDeprecatedDensityLogic || RomUtils.getMiuiVersion() < 14) {
            return true;
        }
        return Build.VERSION.SDK_INT >= 35 && !this.mUseStableDensityLogic;
    }

    public boolean isLocalOriginDpi(int i2) {
        DisplayDensityConfig defaultConfig = getDefaultConfig();
        if (defaultConfig == null) {
            return false;
        }
        return defaultConfig.isLocalOriginDpi(i2);
    }

    @Deprecated
    public void setUseDeprecatedDensityLogic(boolean z2) {
        this.mUseDeprecatedDensityLogic = z2;
        Iterator<Map.Entry<Integer, DisplayDensityConfig>> it = this.mDisplayConfigMap.entrySet().iterator();
        while (it.hasNext()) {
            it.next().getValue().setUseDeprecatedDensityLogic(z2);
        }
    }

    @Deprecated
    public void setUseStableDensityLogic(boolean z2) {
        this.mUseStableDensityLogic = z2;
        Iterator<Map.Entry<Integer, DisplayDensityConfig>> it = this.mDisplayConfigMap.entrySet().iterator();
        while (it.hasNext()) {
            it.next().getValue().setUseStableDensityLogic(z2);
        }
    }

    public void setUserAccessibilityDpiDelta(float f2) {
        this.mUserAccessibilityDpiDelta = f2;
        Iterator<Map.Entry<Integer, DisplayDensityConfig>> it = this.mDisplayConfigMap.entrySet().iterator();
        while (it.hasNext()) {
            it.next().getValue().setUserAccessibilityDpiDelta(f2);
        }
    }

    public void setUserDeviceScale(float f2) {
        this.mUserDeviceScale = f2;
        Iterator<Map.Entry<Integer, DisplayDensityConfig>> it = this.mDisplayConfigMap.entrySet().iterator();
        while (it.hasNext()) {
            it.next().getValue().setUserDeviceScale(f2);
        }
    }

    public void setUserForcedDpi(int i2) {
        this.mUserForcedDpi = i2;
        Iterator<Map.Entry<Integer, DisplayDensityConfig>> it = this.mDisplayConfigMap.entrySet().iterator();
        while (it.hasNext()) {
            it.next().getValue().setUserForcedDpi(i2);
        }
    }

    public void setUserPPI(int i2) {
        this.mUserPPI = i2;
        Iterator<Map.Entry<Integer, DisplayDensityConfig>> it = this.mDisplayConfigMap.entrySet().iterator();
        while (it.hasNext()) {
            it.next().getValue().setUserPPI(i2);
        }
    }

    public boolean tryUpdateConfig(Context context, Configuration configuration) {
        Display currentDisplay = DensityUtil.getCurrentDisplay(context);
        DisplayDensityConfig orCreateDisplayConfig = getOrCreateDisplayConfig(context, currentDisplay);
        if (orCreateDisplayConfig == null) {
            Log.w("AutoDensity", " -> tryUpdateConfig failed: displayConfig is null, ");
            return false;
        }
        if (DebugUtil.isEnableDebug()) {
            DebugUtil.printDensityLog("tryUpdateConfig newConfig " + configuration + " context " + context);
        }
        return orCreateDisplayConfig.tryUpdateConfig(context, currentDisplay, configuration);
    }

    public void updateConfig(Context context, Configuration configuration) {
        Display currentDisplay = DensityUtil.getCurrentDisplay(context);
        DisplayDensityConfig orCreateDisplayConfig = getOrCreateDisplayConfig(context, currentDisplay);
        if (orCreateDisplayConfig == null) {
            Log.w("AutoDensity", " -> updateConfig failed: displayConfig is null");
        } else {
            orCreateDisplayConfig.updateConfig(context, currentDisplay, configuration);
        }
    }

    public double getCurrentDeviceScale() {
        DisplayDensityConfig defaultConfig = getDefaultConfig();
        if (defaultConfig == null) {
            return 1.0d;
        }
        return defaultConfig.getCurrentDeviceScale();
    }

    public double getCurrentPPI() {
        DisplayDensityConfig defaultConfig = getDefaultConfig();
        if (defaultConfig == null) {
            return 0.0d;
        }
        return defaultConfig.getCurrentPPI();
    }

    public float getScreenInches() {
        DisplayDensityConfig defaultConfig = getDefaultConfig();
        if (defaultConfig == null) {
            return 0.0f;
        }
        return defaultConfig.getScreenInches();
    }

    public void setUserAccessibilityDpiDelta(float f2, Display display) {
        DisplayDensityConfig displayConfig = getDisplayConfig(display);
        if (displayConfig != null) {
            displayConfig.setUserAccessibilityDpiDelta(f2);
        }
    }

    public void setUserDeviceScale(float f2, Display display) {
        DisplayDensityConfig displayConfig = getDisplayConfig(display);
        if (displayConfig != null) {
            displayConfig.setUserDeviceScale(f2);
        }
    }

    public void setUserForcedDpi(int i2, Display display) {
        DisplayDensityConfig displayConfig = getDisplayConfig(display);
        if (displayConfig != null) {
            displayConfig.setUserForcedDpi(i2);
        }
    }

    public void setUserPPI(int i2, Display display) {
        DisplayDensityConfig displayConfig = getDisplayConfig(display);
        if (displayConfig != null) {
            displayConfig.setUserPPI(i2);
        }
    }

    public int getDeviceCurrentAccessibilityDpi() {
        DisplayDensityConfig defaultConfig = getDefaultConfig();
        if (defaultConfig == null) {
            return 160;
        }
        return defaultConfig.getCurrentAccessibilityDpi();
    }

    public int getDeviceCurrentForcedDpi() {
        DisplayDensityConfig defaultConfig = getDefaultConfig();
        if (defaultConfig == null) {
            return 160;
        }
        return defaultConfig.getCurrentForcedDpi();
    }

    @Deprecated
    public DensityConfig getOriginConfig() {
        DisplayDensityConfig defaultConfig = getDefaultConfig();
        if (defaultConfig == null) {
            return null;
        }
        return defaultConfig.getOriginConfig();
    }

    @Nullable
    @Deprecated
    public DensityConfig getTargetConfig() {
        DisplayDensityConfig defaultConfig = getDefaultConfig();
        if (defaultConfig == null) {
            return null;
        }
        return defaultConfig.getTargetConfig();
    }

    public void updateConfig(Context context, Configuration configuration, Display display) {
        if (display == null) {
            updateConfig(context, configuration);
            return;
        }
        DisplayDensityConfig orCreateDisplayConfig = getOrCreateDisplayConfig(context, display);
        if (orCreateDisplayConfig == null) {
            Log.w("AutoDensity", " -> updateConfig failed: displayConfig is null");
        } else {
            orCreateDisplayConfig.updateConfig(context, display, configuration);
        }
    }
}
