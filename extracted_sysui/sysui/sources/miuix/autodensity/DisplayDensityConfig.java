package miuix.autodensity;

import android.content.Context;
import android.content.res.Configuration;
import android.graphics.Point;
import android.os.Build;
import android.os.Process;
import android.provider.Settings;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.xiaomi.onetrack.util.aa;
import miuix.core.util.EnvStateManager;
import miuix.core.util.RomUtils;
import miuix.core.util.SystemProperties;
import miuix.os.Build;
import miuix.os.DeviceHelper;
import miuix.provider.ExtraSettings;

/* JADX INFO: loaded from: classes3.dex */
public class DisplayDensityConfig {
    private final int mDisplayId;

    @Nullable
    private DisplayMetrics mDisplayMetrics;
    private final String mDisplayName;
    private boolean mIsRearDisplay;
    private float mMaxSizeInch;
    private float mMinSizeInch;

    @Nullable
    private DensityConfig mOriginConfig;
    private final DensityConfig mTargetConfig;
    private boolean mUseStableDensityLogic = false;

    @Deprecated
    private boolean mUseDeprecatedDensityLogic = false;
    private int mCurrentDefaultDpi = 160;
    private int mCurrentAccessibilityDpi = 160;
    private int mCurrentForcedDpi = 160;
    private float mCurrentAccessibilityDpiDelta = 1.0f;
    private double mDeviceScale = 0.0d;
    private double mPPI = 0.0d;
    private float mScreenInches = 1.0f;
    private double mUserDeviceScale = 0.0d;
    private int mUserPPI = 0;
    private float mUserAccessibilityDpiDelta = 0.0f;
    private int mUserForcedDpi = 0;
    private final Point mPhysicalScreenSize = new Point();
    private final Point mScreenSize = new Point();
    private boolean mAutoDensityEnable = true;

    public DisplayDensityConfig(@NonNull Context context, @NonNull Display display) {
        this.mDisplayMetrics = null;
        int displayId = display.getDisplayId();
        this.mDisplayId = displayId;
        this.mDisplayName = display.getName();
        boolean zIsInRearDisplay = DeviceHelper.isInRearDisplay(display);
        this.mIsRearDisplay = zIsInRearDisplay;
        if (zIsInRearDisplay) {
            DisplayMetrics displayMetrics = new DisplayMetrics();
            this.mDisplayMetrics = displayMetrics;
            display.getRealMetrics(displayMetrics);
            this.mOriginConfig = new DensityConfig(this.mDisplayMetrics);
        }
        this.mTargetConfig = new DensityConfig(context.getResources().getConfiguration());
        if (DebugUtil.isEnableDebug()) {
            DebugUtil.printDensityLog("DisplayDensityConfig init id:" + displayId);
        }
        updateConfig(context, display, context.getResources().getConfiguration());
    }

    private float getAccessibilityDelta(Context context) {
        float f2 = 1.0f;
        if (Build.IS_FLIP && DeviceHelper.isTinyScreen(context)) {
            if (DebugUtil.isEnableDebug()) {
                DebugUtil.printDensityLog("in flip external screen delta: 1.0f");
            }
            return 1.0f;
        }
        int i2 = this.mCurrentDefaultDpi;
        if (DebugUtil.isEnableDebug()) {
            DebugUtil.printDensityLog("default dpi: " + i2);
        }
        if (Process.isIsolated()) {
            Log.d("AutoDensity", "getAccessibilityDelta failed reason: this process is isolated");
            return 1.0f;
        }
        if (i2 != -1) {
            f2 = this.mCurrentAccessibilityDpiDelta;
            if (DebugUtil.isEnableDebug()) {
                DebugUtil.printDensityLog("accessibility dpi: " + this.mCurrentAccessibilityDpi + ", delta: " + f2);
            }
        }
        return f2;
    }

    private float getDebugScale() {
        if (RootUtil.isDeviceRooted()) {
            return DebugUtil.getAutoDensityScaleInDebugMode();
        }
        return 0.0f;
    }

    private void updateDeviceDisplayInfo(Context context, @NonNull Display display) {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        this.mDisplayMetrics = displayMetrics;
        display.getRealMetrics(displayMetrics);
        updateDeviceDisplayInfo(context, display, this.mDisplayMetrics);
        if (DebugUtil.isEnableDebug()) {
            DebugUtil.printDensityLog("DisplayDensityConfig updateDeviceDisplayInfo display-displayMetrics " + this.mDisplayMetrics);
            DebugUtil.printDensityLog("\t\t\tdisplay:" + display);
        }
    }

    private double updateDeviceScale(Context context) {
        double debugScale = getDebugScale();
        if (debugScale < 0.0d) {
            this.mAutoDensityEnable = false;
            Log.d("AutoDensity", "disable auto density in debug mode");
        } else {
            this.mAutoDensityEnable = true;
        }
        double d2 = this.mUserDeviceScale;
        if (d2 > 0.0d) {
            this.mDeviceScale = d2;
            if (DebugUtil.isEnableDebug()) {
                DebugUtil.printDensityLog("updateDeviceScale by userDeviceScale " + this.mUserDeviceScale);
            }
        } else {
            if (DebugUtil.isEnableDebug()) {
                DebugUtil.printDensityLog("updateDeviceScale by calcu " + this.mDeviceScale);
            }
            this.mDeviceScale = AutoDensityPolicy.getDeviceScale(context, this.mMaxSizeInch, this.mMinSizeInch, this.mIsRearDisplay);
        }
        if (debugScale <= 0.0d) {
            debugScale = this.mDeviceScale;
        }
        return debugScale * ((double) getAccessibilityDelta(context));
    }

    private void updateOriginConfigByDisplayMetrics(DisplayMetrics displayMetrics) {
        DensityConfig densityConfig = this.mOriginConfig;
        if (densityConfig == null || displayMetrics == null) {
            return;
        }
        float f2 = displayMetrics.density;
        densityConfig.density = f2;
        float f3 = displayMetrics.scaledDensity;
        densityConfig.scaledDensity = f3;
        densityConfig.densityDpi = displayMetrics.densityDpi;
        densityConfig.fontScale = f3 / f2;
        densityConfig.windowWidthDp = (int) ((displayMetrics.widthPixels / f2) + 0.5f);
        densityConfig.windowHeightDp = (int) ((displayMetrics.heightPixels / f2) + 0.5f);
    }

    private void updateOriginConfigByNewConfig(Configuration configuration) {
        this.mOriginConfig = new DensityConfig(configuration);
    }

    private double updatePPIOfDevice(Context context, Point point, Point point2) {
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        if (DebugUtil.isEnableDebug()) {
            DebugUtil.printDensityLog("physical size: " + point + " cur size: " + point2 + ", display xdpi: " + displayMetrics.xdpi + ", ydpi: " + displayMetrics.ydpi);
        }
        float fMax = Math.max(displayMetrics.xdpi, displayMetrics.ydpi);
        float fMin = Math.min(displayMetrics.xdpi, displayMetrics.ydpi);
        float fMax2 = Math.max(point.x, point.y);
        float fMin2 = Math.min(point.x, point.y);
        float fMax3 = Math.max(point2.x, point2.y);
        float fMin3 = Math.min(point2.x, point2.y);
        if (isEnableLogicMetrics()) {
            fMin2 = fMin3;
            fMax2 = fMax3;
        }
        float f2 = fMax2 / fMax;
        float f3 = fMin2 / fMin;
        this.mMaxSizeInch = Math.max(f3, f2);
        this.mMinSizeInch = Math.min(f3, f2);
        float fSqrt = (float) Math.sqrt(Math.pow(f2, 2.0d) + Math.pow(f3, 2.0d));
        this.mScreenInches = fSqrt;
        int i2 = this.mUserPPI;
        if (i2 > 0) {
            this.mPPI = i2;
            if (DebugUtil.isEnableDebug()) {
                DebugUtil.printDensityLog("Screen inches : " + fSqrt + ", ppi-user:" + this.mUserPPI + ", physicalX:" + f2 + " physicalY:" + f3 + ", logicalX:" + this.mScreenSize.x + " logicalY:" + this.mScreenSize.y + ",min size inches: " + (Math.min(f3, f2) / 2.8f));
            }
            return this.mUserPPI;
        }
        double dSqrt = Math.sqrt(Math.pow(fMax3, 2.0d) + Math.pow(fMin3, 2.0d)) / ((double) fSqrt);
        if (Build.IS_FLIP && fMax3 / displayMetrics.density <= 640.0f && SkuScale.hasSkuPPI()) {
            dSqrt = SkuScale.getSkuPPI(context, false);
        }
        this.mPPI = dSqrt;
        if (DebugUtil.isEnableDebug()) {
            DebugUtil.printDensityLog("Screen inches : " + fSqrt + ", ppi:" + dSqrt + ", physicalX:" + f2 + " physicalY:" + f3 + ", logicalX:" + this.mScreenSize.x + " logicalY:" + this.mScreenSize.y + ",min size inches: " + (Math.min(f3, f2) / 2.8f));
        }
        return dSqrt;
    }

    private void updatePhysicalSizeFromDisplay(Display display) {
        this.mPhysicalScreenSize.set(0, 0);
        Display.Mode[] supportedModes = display.getSupportedModes();
        for (int i2 = 0; i2 < supportedModes.length; i2++) {
            Display.Mode mode = supportedModes[i2];
            if (DebugUtil.isEnableDebug()) {
                DebugUtil.printDensityLog("\tupdatePhysicalSizeFromDisplay mode" + i2 + " " + mode);
            }
            this.mPhysicalScreenSize.x = Math.max(mode.getPhysicalWidth(), this.mPhysicalScreenSize.x);
            this.mPhysicalScreenSize.y = Math.max(mode.getPhysicalHeight(), this.mPhysicalScreenSize.y);
        }
        if (DebugUtil.isEnableDebug()) {
            DebugUtil.printDensityLog("\tupdatePhysicalSizeFromDisplay mPhysicalScreenSize " + this.mPhysicalScreenSize);
        }
    }

    private void updateTargetConfig(int i2, double d2) {
        DensityConfig densityConfig = this.mOriginConfig;
        if (densityConfig == null) {
            return;
        }
        DensityConfig densityConfig2 = this.mTargetConfig;
        densityConfig2.windowWidthDp = densityConfig.windowWidthDp;
        densityConfig2.windowHeightDp = densityConfig.windowHeightDp;
        densityConfig2.defaultBitmapDensity = i2;
        densityConfig2.densityDpi = i2;
        float f2 = i2 / 160.0f;
        densityConfig2.density = f2;
        densityConfig2.fontScale = (float) (((double) densityConfig.fontScale) * d2);
        densityConfig2.scaledDensity = f2 * densityConfig.fontScale;
    }

    public int getAccessibilityDisplayDpi() {
        return this.mCurrentDefaultDpi;
    }

    public int getCurrentAccessibilityDpi() {
        return this.mCurrentAccessibilityDpi;
    }

    public double getCurrentDeviceScale() {
        return this.mDeviceScale;
    }

    public int getCurrentForcedDpi() {
        return this.mCurrentForcedDpi;
    }

    public double getCurrentPPI() {
        return this.mPPI;
    }

    public int getDeviceCurrentDefaultDpi() {
        return this.mCurrentDefaultDpi;
    }

    public int getDeviceDefaultDpi() {
        DensityConfig densityConfig;
        if (this.mDisplayId != DeviceHelper.SUB_BUILTIN_DISPLAY || (densityConfig = this.mOriginConfig) == null) {
            DensityConfig densityConfig2 = this.mOriginConfig;
            return SystemProperties.getInt("ro.sf.lcd_density", densityConfig2 != null ? densityConfig2.densityDpi : -1);
        }
        try {
            return SystemProperties.getInt("ro.sf.lcd_sec_density", densityConfig.densityDpi);
        } catch (Exception unused) {
            return this.mOriginConfig.densityDpi;
        }
    }

    public DensityConfig getOriginConfig() {
        return this.mOriginConfig;
    }

    public float getScreenInches() {
        return this.mScreenInches;
    }

    public DensityConfig getTargetConfig() {
        return this.mTargetConfig;
    }

    public boolean isAutoDensityEnabled() {
        return this.mAutoDensityEnable;
    }

    public boolean isEnableLogicMetrics() {
        if (this.mUseDeprecatedDensityLogic || RomUtils.getMiuiVersion() < 14) {
            return true;
        }
        return Build.VERSION.SDK_INT >= 35 && !this.mUseStableDensityLogic;
    }

    public boolean isLocalOriginDpi(int i2) {
        return i2 == this.mCurrentAccessibilityDpi || i2 == this.mCurrentForcedDpi;
    }

    @Deprecated
    public void setUseDeprecatedDensityLogic(boolean z2) {
        this.mUseDeprecatedDensityLogic = z2;
    }

    @Deprecated
    public void setUseStableDensityLogic(boolean z2) {
        this.mUseStableDensityLogic = z2;
    }

    public void setUserAccessibilityDpiDelta(float f2) {
        this.mUserAccessibilityDpiDelta = f2;
    }

    public void setUserDeviceScale(float f2) {
        this.mUserDeviceScale = f2;
    }

    public void setUserForcedDpi(int i2) {
        this.mUserForcedDpi = i2;
    }

    public void setUserPPI(int i2) {
        this.mUserPPI = i2;
    }

    public boolean tryUpdateConfig(Context context, @NonNull Display display, Configuration configuration) {
        if (this.mTargetConfig == null) {
            Log.w("AutoDensity", "AutoDensity doesn't init, tryUpdateConfig failed id:" + this.mDisplayId);
            return false;
        }
        if (DebugUtil.isEnableDebug()) {
            DebugUtil.printDensityLog("tryUpdateConfig id:" + this.mDisplayId + " newConfig " + configuration + " context " + context);
        }
        DensityConfig densityConfig = this.mOriginConfig;
        if (densityConfig == null) {
            updateConfig(context, display, configuration);
            return true;
        }
        if (configuration.screenWidthDp != densityConfig.windowWidthDp || configuration.screenHeightDp != densityConfig.windowHeightDp || configuration.densityDpi != densityConfig.densityDpi || configuration.fontScale != densityConfig.fontScale) {
            updateConfig(context, display, configuration);
            return true;
        }
        if (DebugUtil.isEnableDebug()) {
            DebugUtil.printDensityLog("tryUpdateConfig failed");
        }
        return false;
    }

    public void updateConfig(Context context, @NonNull Display display, Configuration configuration) {
        if (this.mTargetConfig == null) {
            Log.w("AutoDensity", "AutoDensity doesn't init, updateConfig failed id:" + this.mDisplayId);
            return;
        }
        updateDeviceDisplayInfo(context, display);
        DisplayMetrics displayMetrics = this.mDisplayMetrics;
        if (this.mOriginConfig == null && displayMetrics != null) {
            this.mOriginConfig = new DensityConfig(displayMetrics);
        }
        if (!DeviceHelper.isInRearDisplay(display) && !isLocalOriginDpi(configuration.densityDpi)) {
            if (DebugUtil.isEnableDebug()) {
                DebugUtil.printDensityLog(" <- DisplayDensityConfig id:" + this.mDisplayId + " updateConfig return: newConfig may has been modified by autodensity newConfig.densityDpi=" + configuration.densityDpi + " accessibilityDpi=" + this.mCurrentAccessibilityDpi + " forcedDpi=" + this.mCurrentForcedDpi);
                return;
            }
            return;
        }
        if (DebugUtil.isEnableDebug()) {
            DebugUtil.printDensityLog("DisplayDensityConfig id:" + this.mDisplayId + "updateConfig " + configuration + " context " + context);
        }
        int i2 = context.getResources().getDisplayMetrics().densityDpi;
        updateOriginConfigByDisplayMetrics(displayMetrics);
        if (DebugUtil.isEnableDebug()) {
            DebugUtil.printDensityLog("DisplayDensityConfig id:" + this.mDisplayId + "updateConfig newConfig.densityDpi=" + configuration.densityDpi + " defaultDpi=" + this.mCurrentDefaultDpi + " forceDpi=" + this.mCurrentForcedDpi + " accessibilityDpi=" + this.mCurrentAccessibilityDpi);
        }
        updateOriginConfigByNewConfig(configuration);
        EnvStateManager.updateOriginConfig(this.mOriginConfig);
        double dUpdatePPIOfDevice = updatePPIOfDevice(context, this.mPhysicalScreenSize, this.mScreenSize);
        double dUpdateDeviceScale = updateDeviceScale(context);
        double d2 = (miuix.os.Build.IS_AUTOMOTIVE ? 211.0d : (dUpdatePPIOfDevice * 1.1398963928222656d) * dUpdateDeviceScale) / ((double) this.mCurrentAccessibilityDpi);
        DensityConfig densityConfig = this.mOriginConfig;
        if (densityConfig != null) {
            int iRound = (int) Math.round(((double) densityConfig.densityDpi) * d2);
            if (DebugUtil.isEnableDebug()) {
                DebugUtil.printDensityLog("DisplayDensityConfig id:" + this.mDisplayId + "updateConfig deviceScale:" + dUpdateDeviceScale + " scale:" + d2);
            }
            updateTargetConfig(iRound, d2);
        }
        if (DebugUtil.isEnableDebug()) {
            DebugUtil.printDensityLog("  Config changed. Raw config(" + this.mOriginConfig + ")\n\tTargetConfig(" + this.mTargetConfig + ")");
        }
    }

    private void updateDeviceDisplayInfo(Context context, Display display, DisplayMetrics displayMetrics) {
        int i2;
        updatePhysicalSizeFromDisplay(display);
        if (DebugUtil.isEnableDebug()) {
            DebugUtil.printDensityLog("\tupdateDeviceDisplayInfo context.densityDpi " + context.getResources().getConfiguration().densityDpi);
        }
        int deviceDefaultDpi = getDeviceDefaultDpi();
        if (deviceDefaultDpi == -1) {
            deviceDefaultDpi = displayMetrics.densityDpi;
            Log.w("AutoDensity", "warning!! can not get default dpi!! use defaultDisplayMetrics.densityDpi instead of it: " + deviceDefaultDpi);
        }
        if (DebugUtil.isEnableDebug()) {
            DebugUtil.printDensityLog("\tupdateDeviceDisplayInfo getDeviceDefaultDpi " + deviceDefaultDpi);
        }
        this.mCurrentDefaultDpi = deviceDefaultDpi;
        this.mCurrentAccessibilityDpiDelta = 1.0f;
        Point point = this.mScreenSize;
        Point point2 = this.mPhysicalScreenSize;
        point.set(point2.x, point2.y);
        if (DensityUtil.isSupportSwitchResolution()) {
            String str = SystemProperties.get("persist.sys.miui_resolution", null);
            if (DebugUtil.isEnableDebug()) {
                DebugUtil.printDensityLog("screenResolution: " + str);
            }
            if (!TextUtils.isEmpty(str)) {
                String[] strArrSplit = str.split(aa.f3429b);
                this.mScreenSize.set(Integer.parseInt(strArrSplit[0]), Integer.parseInt(strArrSplit[1]));
            }
            Point point3 = this.mScreenSize;
            int i3 = point3.y;
            Point point4 = this.mPhysicalScreenSize;
            if (i3 != point4.y) {
                this.mCurrentDefaultDpi = (deviceDefaultDpi * point3.x) / point4.x;
            }
        }
        if (DebugUtil.isEnableDebug()) {
            DebugUtil.printDensityLog("\tupdateDeviceDisplayInfo getDeviceDefaultDisplayDpi " + displayMetrics.densityDpi);
        }
        if (this.mUserAccessibilityDpiDelta > 0.0f) {
            if (DebugUtil.isEnableDebug()) {
                DebugUtil.printDensityLog("\tupdateDeviceDisplayInfo mUserCurrentAccessibilityDpiDelta " + this.mUserAccessibilityDpiDelta);
            }
            if (miuix.os.Build.IS_FLIP && DeviceHelper.isTinyScreen(context)) {
                this.mCurrentAccessibilityDpiDelta = 1.0f;
            } else {
                this.mCurrentAccessibilityDpiDelta = this.mUserAccessibilityDpiDelta;
                i2 = this.mUserForcedDpi;
                if (i2 <= 0) {
                    try {
                        i2 = ExtraSettings.Secure.getInt(context.getContentResolver(), "display_density_forced");
                    } catch (Exception e2) {
                        Log.d("AutoDensity", "\tgetAccessibilityDpi on userCurrentDpiDelta Exception: " + e2);
                        i2 = -1;
                    }
                }
            }
            i2 = -1;
        } else {
            if (miuix.os.Build.IS_FLIP && DeviceHelper.isTinyScreen(context)) {
                this.mCurrentAccessibilityDpiDelta = 1.0f;
            } else {
                try {
                    int i4 = Settings.System.getInt(context.getContentResolver(), "key_screen_zoom_level", 1);
                    if (i4 > 1) {
                        this.mCurrentAccessibilityDpiDelta = 1.05f;
                    } else if (i4 < 1) {
                        this.mCurrentAccessibilityDpiDelta = AutoDensityPolicy.ACCESSIBILITY_ZOOM_SMALL;
                    } else {
                        this.mCurrentAccessibilityDpiDelta = 1.0f;
                    }
                    i2 = this.mUserForcedDpi;
                    if (i2 <= 0) {
                        i2 = ExtraSettings.Secure.getInt(context.getContentResolver(), "display_density_forced");
                    }
                } catch (Exception e3) {
                    Log.d("AutoDensity", "\tgetAccessibilityDpi Exception: " + e3);
                    i2 = -1;
                }
            }
            i2 = -1;
        }
        if (i2 == -1) {
            i2 = this.mCurrentDefaultDpi;
        }
        this.mCurrentForcedDpi = i2;
        this.mCurrentAccessibilityDpi = (int) Math.floor(this.mCurrentDefaultDpi * this.mCurrentAccessibilityDpiDelta);
        if (DebugUtil.isEnableDebug()) {
            DebugUtil.printDensityLog("\tupdateDisplayInfo currentDefaultDpi=" + this.mCurrentDefaultDpi + " mCurrentForcedDpi=" + this.mCurrentForcedDpi + " mCurrentAccessibilityDpi=" + this.mCurrentAccessibilityDpi + " delta=" + this.mCurrentAccessibilityDpiDelta + " logicSize=" + this.mScreenSize + " physicalSize=" + this.mPhysicalScreenSize);
        }
    }
}
