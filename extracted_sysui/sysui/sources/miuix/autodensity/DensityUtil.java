package miuix.autodensity;

import android.app.Activity;
import android.app.ResourcesManager;
import android.content.Context;
import android.content.res.CompatibilityInfo;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.content.res.ResourcesImpl;
import android.content.res.ResourcesKey;
import android.content.res.loader.ResourcesLoader;
import android.graphics.Bitmap;
import android.hardware.display.DisplayManager;
import android.os.Build;
import android.util.ArrayMap;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.ContextThemeWrapper;
import android.view.Display;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import java.lang.ref.WeakReference;
import miuix.appcompat.app.floatingactivity.multiapp.MethodCodeHelper;
import miuix.core.util.RomUtils;
import miuix.os.DeviceHelper;
import miuix.reflect.ReflectionHelper;
import miuix.view.DisplayConfig;

/* JADX INFO: loaded from: classes3.dex */
public class DensityUtil {
    private static final String SYNERGY_OWNER_ANDROID_PAD = "AndroidPad";
    private static final String SYNERGY_OWNER_ANDROID_PAD_CAR = "AndroidPadCar";
    private static final String SYNERGY_OWNER_ANDROID_PHONE = "AndroidPhone";
    private static final String SYNERGY_OWNER_WINDOWS = "Windows";
    private static boolean sIsSupportSwitchResolution;
    private static Object sLock;
    private static ArrayMap<ResourcesKey, WeakReference<ResourcesImpl>> sResourcesImpls;
    private static ResourcesManager sResourcesManager;

    static {
        try {
            sResourcesManager = (ResourcesManager) ReflectionHelper.getConstructorInstance(ResourcesManager.class, new Class[0], new Object[0]);
            ResourcesManager resourcesManager = ResourcesManager.getInstance();
            sResourcesManager = resourcesManager;
            sResourcesImpls = (ArrayMap) ReflectionHelper.getFieldValue(ResourcesManager.class, resourcesManager, "mResourceImpls");
            sLock = sResourcesManager;
        } catch (Exception unused) {
        }
        try {
            sLock = ReflectionHelper.getFieldValue(ResourcesManager.class, sResourcesManager, "mLock");
        } catch (Exception unused2) {
            sLock = null;
        }
        if (sResourcesManager == null || sResourcesImpls == null || sLock == null) {
            Log.w("AutoDensity", "ResourcesManager reflection failed, this app do not have permission to disable AutoDensity for activity/application");
        }
        try {
            int[] iArr = (int[]) ReflectionHelper.invokeObject(Class.forName("miui.util.FeatureParser"), null, "getIntArray", new Class[]{String.class}, "screen_resolution_supported");
            if (iArr == null || iArr.length <= 1) {
                return;
            }
            sIsSupportSwitchResolution = true;
        } catch (Throwable th) {
            Log.d("AutoDensity", "DensityUtil init screen_resolution_supported Exception: " + th);
        }
    }

    private static void changeDensity(Resources resources, int i2, Display display) {
        DensityConfig targetConfig = DensityConfigManager.getInstance().getTargetConfig(display);
        if (targetConfig != null) {
            if (i2 > 0 || resources.getDisplayMetrics().densityDpi != targetConfig.densityDpi) {
                doChangeDensity(targetConfig, resources, i2);
                if (AutoDensityConfig.shouldUpdateSystemResource()) {
                    setSystemResources(targetConfig);
                }
            }
        }
    }

    public static void doChangeDensity(@NonNull DisplayConfig displayConfig, Resources resources, int i2) {
        tryToCreateAndSetResourcesImpl(resources, displayConfig);
        DisplayMetrics displayMetrics = resources.getDisplayMetrics();
        Configuration configuration = resources.getConfiguration();
        int i3 = displayMetrics.widthPixels;
        float f2 = 1.0f;
        float f3 = displayConfig.density;
        float f4 = (i3 * 1.0f) / f3;
        if (i2 > 0) {
            float f5 = i2;
            if (f4 < f5) {
                f2 = i3 / (f5 * f3);
            }
        }
        int i4 = (int) (displayConfig.densityDpi * f2);
        configuration.densityDpi = i4;
        displayMetrics.densityDpi = i4;
        displayMetrics.density = f3 * f2;
        displayMetrics.scaledDensity = displayConfig.scaledDensity * f2;
        configuration.fontScale = displayConfig.fontScale;
        DebugUtil.printDensityLog("after doChangeDensity baseWidthDp:" + i2 + " ratio:" + f2 + " " + displayMetrics + " " + configuration);
    }

    public static AutoDensityContextWrapper findAutoDensityContextWrapper(Context context) {
        if (!(context instanceof ContextThemeWrapper)) {
            return null;
        }
        while (true) {
            ContextThemeWrapper contextThemeWrapper = (ContextThemeWrapper) context;
            if (!(contextThemeWrapper.getBaseContext() instanceof ContextThemeWrapper)) {
                return null;
            }
            if (contextThemeWrapper.getBaseContext() instanceof AutoDensityContextWrapper) {
                return (AutoDensityContextWrapper) contextThemeWrapper.getBaseContext();
            }
            context = contextThemeWrapper.getBaseContext();
        }
    }

    private static ResourcesImpl findOrCreateResourcesImplForKeyLocked(ResourcesKey resourcesKey, DisplayConfig displayConfig) {
        try {
            Configuration configuration = new Configuration();
            Configuration configuration2 = (Configuration) ReflectionHelper.getFieldValue(ResourcesKey.class, resourcesKey, "mOverrideConfiguration");
            if (Build.VERSION.SDK_INT >= 35 && configuration.equals(configuration2)) {
                return null;
            }
            configuration.setTo(configuration2);
            configuration.densityDpi = displayConfig.densityDpi;
            Integer num = (Integer) ReflectionHelper.getFieldValue(ResourcesKey.class, resourcesKey, "mDisplayId");
            num.intValue();
            ResourcesKey resourcesKey2 = (ResourcesKey) ReflectionHelper.getConstructorInstance(ResourcesKey.class, new Class[]{String.class, String[].class, String[].class, String[].class, Integer.TYPE, Configuration.class, CompatibilityInfo.class, ResourcesLoader[].class}, resourcesKey.mResDir, resourcesKey.mSplitResDirs, (String[]) ReflectionHelper.getFieldValue(ResourcesKey.class, resourcesKey, "mOverlayPaths"), (String[]) ReflectionHelper.getFieldValue(ResourcesKey.class, resourcesKey, "mLibDirs"), num, configuration, (CompatibilityInfo) ReflectionHelper.getFieldValue(ResourcesKey.class, resourcesKey, "mCompatInfo"), (ResourcesLoader[]) ReflectionHelper.getFieldValue(ResourcesKey.class, resourcesKey, "mLoaders"));
            DebugUtil.printDensityLog("newKey " + resourcesKey2);
            return (ResourcesImpl) ReflectionHelper.invokeObject(ResourcesManager.class, sResourcesManager, "findOrCreateResourcesImplForKeyLocked", new Class[]{ResourcesKey.class}, resourcesKey2);
        } catch (Error e2) {
            DebugUtil.printDensityLog("findOrCreateResourcesImplForKeyLocked failed " + e2.toString());
            return null;
        } catch (Exception e3) {
            DebugUtil.printDensityLog("findOrCreateResourcesImplForKeyLocked failed " + e3.toString());
            return null;
        }
    }

    private static ResourcesKey findResourcesKeyByResourcesImplLocked(ResourcesImpl resourcesImpl) {
        int size = sResourcesImpls.size();
        int i2 = 0;
        while (true) {
            if (i2 >= size) {
                return null;
            }
            WeakReference<ResourcesImpl> weakReferenceValueAt = sResourcesImpls.valueAt(i2);
            if (resourcesImpl == (weakReferenceValueAt != null ? weakReferenceValueAt.get() : null)) {
                return sResourcesImpls.keyAt(i2);
            }
            i2++;
        }
    }

    public static Display getCurrentDisplay(Context context) {
        try {
            return context.getDisplay();
        } catch (Exception unused) {
            return getDefaultDisplay(context);
        }
    }

    public static int getDefaultBitmapDensity() {
        try {
            return ((Integer) ReflectionHelper.invokeObject(Bitmap.class, null, "getDefaultDensity", new Class[0], new Object[0])).intValue();
        } catch (Exception e2) {
            if (!DebugUtil.isEnableDebug()) {
                return -1;
            }
            DebugUtil.printDensityLog("reflect exception: " + e2.toString());
            return -1;
        }
    }

    public static Display getDefaultDisplay(Context context) {
        return ((DisplayManager) context.getSystemService("display")).getDisplay(0);
    }

    @Nullable
    public static Configuration getNoDensityOverrideConfiguration(Context context) {
        Configuration noOverrideConfiguration;
        AutoDensityContextWrapper autoDensityContextWrapperFindAutoDensityContextWrapper = findAutoDensityContextWrapper(context);
        if (autoDensityContextWrapperFindAutoDensityContextWrapper == null || (noOverrideConfiguration = autoDensityContextWrapperFindAutoDensityContextWrapper.getNoOverrideConfiguration()) == null) {
            return null;
        }
        return noOverrideConfiguration;
    }

    private static String getSynergyOwnerDevice(@NonNull Display display) {
        int iIndexOf;
        String name = display.getName();
        return (name == null || (iIndexOf = name.indexOf(MethodCodeHelper.IDENTITY_INFO_SEPARATOR)) == -1) ? "" : name.substring(iIndexOf + 1);
    }

    public static boolean isSupportSwitchResolution() {
        return sIsSupportSwitchResolution;
    }

    public static boolean restoreDefaultDensity(Context context) {
        if (context == null) {
            Log.w("AutoDensity", "restoreDefaultDensity context should not null");
            return false;
        }
        Display currentDisplay = getCurrentDisplay(context);
        if (DensityConfigManager.getInstance().isAutoDensityEnabled()) {
            return restoreDensity(context.getResources(), currentDisplay);
        }
        return false;
    }

    private static boolean restoreDensity(Resources resources, Display display) {
        DensityConfig originConfig = DensityConfigManager.getInstance().getOriginConfig(display);
        DisplayMetrics displayMetrics = resources.getDisplayMetrics();
        if (originConfig == null || originConfig.density == displayMetrics.density) {
            return false;
        }
        DebugUtil.printDensityLog("restoreDensity success");
        doChangeDensity(originConfig, resources, 0);
        return true;
    }

    private static void setDefaultBitmapDensity(int i2) {
        try {
            ReflectionHelper.invoke(Bitmap.class, null, "setDefaultDensity", new Class[]{Integer.TYPE}, Integer.valueOf(i2));
            DebugUtil.printDensityLog("setDefaultBitmapDensity " + i2);
        } catch (Exception e2) {
            DebugUtil.printDensityLog("reflect exception: " + e2.toString());
        }
    }

    public static void setSystemResources(DisplayConfig displayConfig) {
        DisplayMetrics displayMetrics = Resources.getSystem().getDisplayMetrics();
        Configuration configuration = Resources.getSystem().getConfiguration();
        int i2 = displayConfig.densityDpi;
        configuration.densityDpi = i2;
        displayMetrics.densityDpi = i2;
        displayMetrics.scaledDensity = displayConfig.scaledDensity;
        displayMetrics.density = displayConfig.density;
        configuration.fontScale = displayConfig.fontScale;
        setDefaultBitmapDensity(displayConfig.defaultBitmapDensity);
        DebugUtil.printDensityLog("setSystemResources " + displayMetrics + " " + configuration + " defaultBitmapDensity:" + displayConfig.defaultBitmapDensity);
    }

    public static boolean shouldProcessDensity(Context context, Display display) {
        if (DeviceHelper.isCarWithScreen(context, display)) {
            if (DebugUtil.isEnableDebug()) {
                DebugUtil.printDensityLog("shouldProcessDensity isCarWithScreen");
            }
            return false;
        }
        boolean zIsXiaomiSynergy = DeviceHelper.isXiaomiSynergy(context);
        if (DebugUtil.isEnableDebug()) {
            DebugUtil.printDensityLog("shouldProcessDensity isSynergy " + zIsXiaomiSynergy);
        }
        if (zIsXiaomiSynergy) {
            String synergyOwnerDevice = getSynergyOwnerDevice(display);
            if ("Windows".contentEquals(synergyOwnerDevice)) {
                return false;
            }
            if ("AndroidPad".contentEquals(synergyOwnerDevice)) {
                if (RomUtils.getHyperOsVersion() > 1) {
                    return false;
                }
            } else if (SYNERGY_OWNER_ANDROID_PAD_CAR.contentEquals(synergyOwnerDevice) && display.getDisplayId() != 0) {
                return false;
            }
        }
        return true;
    }

    public static boolean shouldUpdateDensityForConfig(Configuration configuration) {
        DensityConfig targetConfig = DensityConfigManager.getInstance().getTargetConfig();
        return (targetConfig == null || configuration.densityDpi == targetConfig.densityDpi) ? false : true;
    }

    private static void tryToCreateAndSetResourcesImpl(Resources resources, DisplayConfig displayConfig) {
        Object obj;
        ResourcesImpl resourcesImplFindOrCreateResourcesImplForKeyLocked;
        if (sResourcesManager == null || sResourcesImpls == null || (obj = sLock) == null) {
            return;
        }
        try {
            synchronized (obj) {
                try {
                    ResourcesKey resourcesKeyFindResourcesKeyByResourcesImplLocked = findResourcesKeyByResourcesImplLocked((ResourcesImpl) ReflectionHelper.getFieldValue(Resources.class, resources, "mResourcesImpl"));
                    DebugUtil.printDensityLog("oldKey " + resourcesKeyFindResourcesKeyByResourcesImplLocked);
                    if (resourcesKeyFindResourcesKeyByResourcesImplLocked != null && (resourcesImplFindOrCreateResourcesImplForKeyLocked = findOrCreateResourcesImplForKeyLocked(resourcesKeyFindResourcesKeyByResourcesImplLocked, displayConfig)) != null) {
                        ReflectionHelper.invoke(Resources.class, resources, "setImpl", new Class[]{ResourcesImpl.class}, resourcesImplFindOrCreateResourcesImplForKeyLocked);
                        DebugUtil.printDensityLog("set impl success " + resourcesImplFindOrCreateResourcesImplForKeyLocked);
                    }
                } finally {
                }
            }
        } catch (Exception e2) {
            DebugUtil.printDensityLog("tryToCreateAndSetResourcesImpl failed " + e2.toString());
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static void updateCustomDensity(Context context) {
        boolean zShouldProcessDensity;
        if (context == 0) {
            Log.w("AutoDensity", "updateCustomDensity context should not null");
            return;
        }
        if (DebugUtil.isEnableDebug()) {
            DebugUtil.printDensityLog("updateCustomDensity context is " + context);
        }
        if (DensityConfigManager.getInstance().isAutoDensityEnabled()) {
            int ratioUiBaseWidthDp = context instanceof IDensity ? ((IDensity) context).getRatioUiBaseWidthDp() : 0;
            Display currentDisplay = getCurrentDisplay(context);
            if (context instanceof Activity) {
                Activity activity = (Activity) context;
                Configuration configuration = activity.getResources().getConfiguration();
                zShouldProcessDensity = shouldProcessDensity(activity, currentDisplay);
                if (DebugUtil.isEnableDebug()) {
                    StringBuilder sb = new StringBuilder();
                    sb.append("updateCustomDensity -> display is ");
                    sb.append(currentDisplay != null ? currentDisplay.getName() : "null");
                    sb.append(" id:");
                    sb.append(currentDisplay != null ? Integer.valueOf(currentDisplay.getDisplayId()) : "null");
                    sb.append(" shouldProcessDensity=");
                    sb.append(zShouldProcessDensity);
                    sb.append(" activity is ");
                    sb.append(activity);
                    sb.append(" config is ");
                    sb.append(configuration);
                    DebugUtil.printDensityLog(sb.toString());
                }
                if (!zShouldProcessDensity && configuration.densityDpi == DensityConfigManager.getInstance().getDeviceCurrentAccessibilityDpi(currentDisplay)) {
                    if (DebugUtil.isEnableDebug()) {
                        StringBuilder sb2 = new StringBuilder();
                        sb2.append("updateCustomDensity -> will not changeDensity cause display is ");
                        sb2.append(currentDisplay != null ? currentDisplay.getName() : "null");
                        sb2.append(", try restore density, activity is ");
                        sb2.append(activity);
                        sb2.append(" config is ");
                        sb2.append(configuration);
                        DebugUtil.printDensityLog(sb2.toString());
                    }
                    restoreDefaultDensity(activity);
                }
            } else {
                zShouldProcessDensity = true;
            }
            if (zShouldProcessDensity) {
                changeDensity(context.getResources(), ratioUiBaseWidthDp, currentDisplay);
            }
        }
    }

    public static boolean updateDensityForConfig(Context context, Configuration configuration) {
        DensityConfig targetConfig = DensityConfigManager.getInstance().getTargetConfig(getCurrentDisplay(context));
        if (targetConfig == null) {
            return false;
        }
        Resources resources = context.getResources();
        resources.getConfiguration().setTo(configuration);
        doChangeDensity(targetConfig, resources, 0);
        return true;
    }

    public static boolean shouldUpdateDensityForConfig(Configuration configuration, Display display) {
        DensityConfig targetConfig = DensityConfigManager.getInstance().getTargetConfig(display);
        return (targetConfig == null || configuration.densityDpi == targetConfig.densityDpi) ? false : true;
    }

    @Deprecated
    public static void updateCustomDensity(Context context, int i2) {
        if (context == null) {
            Log.w("AutoDensity", "context should not null");
        } else if (DensityConfigManager.getInstance().isAutoDensityEnabled()) {
            changeDensity(context.getResources(), i2, null);
        }
    }

    public static void updateCustomDensity(Context context, int i2, Display display) {
        if (context == null) {
            Log.w("AutoDensity", "context should not null");
        } else if (DensityConfigManager.getInstance().isAutoDensityEnabled()) {
            changeDensity(context.getResources(), i2, display);
        }
    }
}
