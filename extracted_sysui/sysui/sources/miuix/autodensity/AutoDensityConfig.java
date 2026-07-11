package miuix.autodensity;

import android.app.Activity;
import android.app.Application;
import android.app.Fragment;
import android.app.ICompatCameraControlCallback;
import android.content.ComponentCallbacks;
import android.content.ComponentCallbacks2;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.pm.ActivityInfo;
import android.content.pm.ApplicationInfo;
import android.content.res.Configuration;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.view.ViewRootImpl;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import miuix.autodensity.DensityProcessor;
import miuix.core.util.EnvStateManager;
import miuix.core.util.ScreenModeHelper;
import miuix.core.util.WindowBaseInfo;
import miuix.os.DeviceHelper;
import miuix.reflect.ReflectionHelper;

/* JADX INFO: loaded from: classes3.dex */
public class AutoDensityConfig extends DensityProcessor {
    private static final String TAG_CONFIG_CHANGE_FRAGMENT = "ConfigurationChangeFragment";
    private static AutoDensityConfig sInstance = null;
    private static boolean sUpdateSystemResources = true;
    private boolean sCanAccessHiddenAPI = false;

    private AutoDensityConfig(final Application application) {
        prepareInApplication(application);
        if (!(application instanceof miuix.app.Application)) {
            application.registerActivityLifecycleCallbacks(new DensityProcessor.DensityProcessorLifecycleCallbacks(this));
            application.registerComponentCallbacks(new ComponentCallbacks() { // from class: miuix.autodensity.AutoDensityConfig.2
                @Override // android.content.ComponentCallbacks
                public void onConfigurationChanged(Configuration configuration) {
                    AutoDensityConfig.this.processOnAppConfigChanged(application, configuration);
                }

                @Override // android.content.ComponentCallbacks
                public void onLowMemory() {
                }
            });
        } else {
            miuix.app.Application application2 = (miuix.app.Application) application;
            application2.registerActivityLifecycleSubCallbacks(new DensityProcessor.DensityProcessorLifecycleCallbacks(this));
            application2.registerComponentSubCallbacks(new ComponentCallbacks() { // from class: miuix.autodensity.AutoDensityConfig.1
                @Override // android.content.ComponentCallbacks
                public void onConfigurationChanged(Configuration configuration) {
                    AutoDensityConfig.this.processOnAppConfigChanged(application, configuration);
                }

                @Override // android.content.ComponentCallbacks
                public void onLowMemory() {
                }
            });
        }
    }

    private void addForOnConfigurationChange(Activity activity) {
        Fragment configurationChangeFragment = getConfigurationChangeFragment(activity);
        if (configurationChangeFragment != null) {
            ((ConfigurationChangeFragment) configurationChangeFragment).setDensityProcessor(this);
            Log.d("AutoDensity", "ConfigurationChangeFragment has already added");
        } else {
            ConfigurationChangeFragment configurationChangeFragment2 = new ConfigurationChangeFragment();
            configurationChangeFragment2.setDensityProcessor(this);
            activity.getFragmentManager().beginTransaction().add(configurationChangeFragment2, TAG_CONFIG_CHANGE_FRAGMENT).commitAllowingStateLoss();
        }
    }

    private void changeCurrentConfig(Activity activity) {
        try {
            ((Configuration) ReflectionHelper.getFieldValue(Activity.class, activity, "mCurrentConfig")).densityDpi = DensityConfigManager.getInstance().getTargetConfig(DensityUtil.getCurrentDisplay(activity)).densityDpi;
            ActivityInfo activityInfo = (ActivityInfo) ReflectionHelper.getFieldValue(Activity.class, activity, "mActivityInfo");
            int i2 = activityInfo.configChanges;
            if ((i2 & 4096) == 0) {
                activityInfo.configChanges = i2 | 4096;
                Fragment configurationChangeFragment = getConfigurationChangeFragment(activity);
                if (configurationChangeFragment != null) {
                    ((ConfigurationChangeFragment) configurationChangeFragment).removeDensityChangeFlag();
                }
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    public static Context createAutoDensityContextWrapper(@NonNull Context context) {
        return createAutoDensityContextWrapper(context, 0, 0);
    }

    public static Context createAutoDensityContextWrapperWithBaseDp(@NonNull Context context, int i2) {
        return createAutoDensityContextWrapper(context, 0, i2);
    }

    public static void forceUpdateDensity(Context context) {
        if (sInstance != null) {
            DensityUtil.updateCustomDensity(context);
        }
    }

    private Fragment getConfigurationChangeFragment(Activity activity) {
        return activity.getFragmentManager().findFragmentByTag(TAG_CONFIG_CHANGE_FRAGMENT);
    }

    public static AutoDensityConfig init(Application application) {
        if (sInstance == null) {
            sInstance = init(application, true);
        }
        return sInstance;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Multi-variable type inference failed */
    public static boolean isShouldAdaptAutoDensity(Application application) {
        if (application instanceof IDensity) {
            return ((IDensity) application).shouldAdaptAutoDensity();
        }
        return true;
    }

    private void removeCurrentConfig(Activity activity) {
        try {
            ReflectionHelper.setFieldValue(Activity.class, activity, "mCurrentConfig", null);
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    public static void setForceDeviceScale(float f2) {
        if (DebugUtil.isEnableDebug()) {
            DebugUtil.printDensityLog("setForceDeviceScale " + f2 + " trace:" + Log.getStackTraceString(new Throwable()));
        }
        DensityConfigManager.getInstance().setUserDeviceScale(f2);
    }

    public static void setForcePPI(int i2) {
        if (DebugUtil.isEnableDebug()) {
            DebugUtil.printDensityLog("setForcePPI " + i2 + " trace:" + Log.getStackTraceString(new Throwable()));
        }
        DensityConfigManager.getInstance().setUserPPI(i2);
    }

    public static void setUpdateSystemRes(boolean z2) {
        sUpdateSystemResources = z2;
        if (z2) {
            if (DensityConfigManager.getInstance().getTargetConfig() == null) {
                return;
            }
            DensityUtil.setSystemResources(DensityConfigManager.getInstance().getTargetConfig());
        } else {
            DensityConfig originConfig = DensityConfigManager.getInstance().getOriginConfig();
            if (originConfig == null) {
                return;
            }
            DensityUtil.setSystemResources(originConfig);
        }
    }

    @Deprecated
    public static void setUseDeprecatedDensityLogic(boolean z2) {
        DensityConfigManager.getInstance().setUseDeprecatedDensityLogic(z2);
    }

    @Deprecated
    public static void setUseStableDensityLogic(boolean z2) {
        DensityConfigManager.getInstance().setUseStableDensityLogic(z2);
    }

    public static boolean shouldUpdateSystemResource() {
        return sUpdateSystemResources;
    }

    private void tryToAddActivityConfigCallback(final Activity activity) {
        View viewPeekDecorView;
        if (!this.sCanAccessHiddenAPI || (viewPeekDecorView = activity.getWindow().peekDecorView()) == null) {
            return;
        }
        View.OnAttachStateChangeListener onAttachStateChangeListener = new View.OnAttachStateChangeListener() { // from class: miuix.autodensity.AutoDensityConfig.3
            @Override // android.view.View.OnAttachStateChangeListener
            public void onViewAttachedToWindow(@NonNull View view) {
                try {
                    Object objInvokeObject = ReflectionHelper.invokeObject(View.class, view, "getViewRootImpl", new Class[0], new Object[0]);
                    final Object fieldValue = ReflectionHelper.getFieldValue(ViewRootImpl.class, objInvokeObject, "mActivityConfigCallback");
                    ReflectionHelper.invokeObject(ViewRootImpl.class, objInvokeObject, "setActivityConfigCallback", new Class[]{ViewRootImpl.ActivityConfigCallback.class}, new ViewRootImpl.ActivityConfigCallback() { // from class: miuix.autodensity.AutoDensityConfig.3.1
                        public void onConfigurationChanged(Configuration configuration, int i2) {
                            try {
                                ReflectionHelper.invokeObject(ViewRootImpl.ActivityConfigCallback.class, fieldValue, "onConfigurationChanged", new Class[]{Configuration.class, Integer.TYPE}, configuration, Integer.valueOf(i2));
                                boolean zIsShouldAdaptAutoDensity = AutoDensityConfig.isShouldAdaptAutoDensity(activity.getApplication());
                                ComponentCallbacks2 componentCallbacks2 = activity;
                                if (componentCallbacks2 instanceof IDensity) {
                                    zIsShouldAdaptAutoDensity = ((IDensity) componentCallbacks2).shouldAdaptAutoDensity();
                                }
                                if (zIsShouldAdaptAutoDensity) {
                                    DensityUtil.updateCustomDensity(activity);
                                }
                            } catch (Exception unused) {
                            }
                        }

                        public void requestCompatCameraControl(boolean z2, boolean z3, ICompatCameraControlCallback iCompatCameraControlCallback) {
                        }
                    });
                } catch (Exception unused) {
                }
                view.removeOnAttachStateChangeListener(this);
            }

            @Override // android.view.View.OnAttachStateChangeListener
            public void onViewDetachedFromWindow(@NonNull View view) {
            }
        };
        viewPeekDecorView.addOnAttachStateChangeListener(onAttachStateChangeListener);
        this.mModifier.get(Integer.valueOf(activity.hashCode())).addOnAttachStateChangeListener(onAttachStateChangeListener);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r1v0 */
    /* JADX WARN: Type inference failed for: r1v1 */
    /* JADX WARN: Type inference failed for: r1v10 */
    /* JADX WARN: Type inference failed for: r1v11 */
    /* JADX WARN: Type inference failed for: r1v2, types: [android.app.Activity] */
    /* JADX WARN: Type inference failed for: r1v9 */
    public static void updateDensity(Context context) {
        Application application;
        boolean z2;
        boolean zIsShouldAdaptAutoDensity;
        if (sInstance == null) {
            return;
        }
        ?? r12 = 0;
        r12 = 0;
        r12 = 0;
        if (context instanceof Activity) {
            r12 = (Activity) context;
            application = null;
        } else if (context instanceof Application) {
            application = (Application) context;
        } else if (context instanceof ContextWrapper) {
            ContextWrapper contextWrapper = (ContextWrapper) context;
            while (contextWrapper.getBaseContext() instanceof ContextWrapper) {
                contextWrapper = (ContextWrapper) contextWrapper.getBaseContext();
                if (contextWrapper instanceof Activity) {
                    context = (Activity) contextWrapper;
                    application = null;
                    r12 = context;
                    break;
                } else if (contextWrapper instanceof Application) {
                    application = (Application) contextWrapper;
                    break;
                }
            }
            application = null;
        } else {
            application = null;
        }
        if (r12 != 0) {
            zIsShouldAdaptAutoDensity = isShouldAdaptAutoDensity(r12.getApplication());
            if (r12 instanceof IDensity) {
                boolean zShouldAdaptAutoDensity = ((IDensity) r12).shouldAdaptAutoDensity();
                z2 = zIsShouldAdaptAutoDensity;
                zIsShouldAdaptAutoDensity = zShouldAdaptAutoDensity;
            } else {
                z2 = zIsShouldAdaptAutoDensity;
            }
        } else {
            z2 = false;
            zIsShouldAdaptAutoDensity = application != null ? isShouldAdaptAutoDensity(application) : false;
        }
        if (zIsShouldAdaptAutoDensity) {
            forceUpdateDensity(context);
        } else if (z2) {
            DensityUtil.restoreDefaultDensity(context);
        }
    }

    public static boolean updateDensityByConfig(@Nullable Context context, Configuration configuration) {
        AutoDensityConfig autoDensityConfig = sInstance;
        if (autoDensityConfig == null || context == null) {
            return false;
        }
        return autoDensityConfig.updateDensityOnConfigChanged(context, configuration);
    }

    public static Configuration updateDensityOverrideConfiguration(@NonNull Context context, @NonNull Configuration configuration) {
        Configuration noDensityOverrideConfiguration = DensityUtil.getNoDensityOverrideConfiguration(context);
        if (noDensityOverrideConfiguration != null) {
            EnvStateManager.markWindowInfoDirty(context);
        } else {
            noDensityOverrideConfiguration = configuration;
        }
        if (!DensityUtil.shouldUpdateDensityForConfig(noDensityOverrideConfiguration, DensityUtil.getCurrentDisplay(context))) {
            return configuration;
        }
        Configuration configuration2 = new Configuration(noDensityOverrideConfiguration);
        DensityUtil.updateDensityForConfig(context, configuration2);
        return configuration2;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // miuix.autodensity.DensityProcessor
    public boolean isEnableProcessInActivity(Activity activity) {
        boolean zShouldAdaptAutoDensity = false;
        try {
            if (activity instanceof IDensity) {
                zShouldAdaptAutoDensity = ((IDensity) activity).shouldAdaptAutoDensity();
            } else if (activity.getApplication() instanceof IDensity) {
                zShouldAdaptAutoDensity = ((IDensity) activity.getApplication()).shouldAdaptAutoDensity();
            }
        } catch (Exception unused) {
        }
        return zShouldAdaptAutoDensity;
    }

    @Override // miuix.autodensity.DensityProcessor
    public void onDensityChangedOnActivityCreated(Activity activity) {
        super.onDensityChangedOnActivityCreated(activity);
        addForOnConfigurationChange(activity);
    }

    @Override // miuix.autodensity.DensityProcessor
    public void onRegisterDensityCallback(Object obj) {
        if (DebugUtil.isEnableDebug()) {
            DebugUtil.printDensityLog("registerCallback obj: " + obj);
        }
    }

    @Override // miuix.autodensity.DensityProcessor
    public void prepareInApplication(Application application) {
        try {
            this.sCanAccessHiddenAPI = ((Boolean) ReflectionHelper.invokeObject(ApplicationInfo.class, application.getApplicationInfo(), "isAllowedToUseHiddenApis", new Class[0], new Object[0])).booleanValue();
        } catch (Exception unused) {
        }
        DebugUtil.initAutoDensityDebugEnable();
        DensityConfigManager.getInstance().init(application);
        if (isShouldAdaptAutoDensity(application)) {
            DensityUtil.updateCustomDensity(application);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // miuix.autodensity.DensityProcessor
    public void processBeforeActivityConfigChanged(Activity activity, Configuration configuration) {
        if (DebugUtil.isEnableDebug()) {
            DebugUtil.printDensityLog("->processBeforeActivityConfigChanged");
        }
        boolean zIsShouldAdaptAutoDensity = isShouldAdaptAutoDensity(activity.getApplication());
        if (activity instanceof IDensity ? ((IDensity) activity).shouldAdaptAutoDensity() : zIsShouldAdaptAutoDensity) {
            DensityUtil.updateCustomDensity(activity);
            WindowBaseInfo windowInfo = EnvStateManager.getWindowInfo(activity);
            onDensityChangedBeforeActivityConfigChanged(activity, configuration, windowInfo);
            if (!ScreenModeHelper.isInSplitScreenMode(windowInfo.windowMode)) {
                ScreenModeHelper.isInFreeFormMode(windowInfo.windowMode);
            }
            changeCurrentConfig(activity);
            return;
        }
        if (zIsShouldAdaptAutoDensity) {
            boolean zRestoreDefaultDensity = DensityUtil.restoreDefaultDensity(activity);
            WindowBaseInfo windowInfo2 = EnvStateManager.getWindowInfo(activity);
            onDensityChangedBeforeActivityConfigChanged(activity, configuration, windowInfo2);
            if (zRestoreDefaultDensity) {
                if (!ScreenModeHelper.isInSplitScreenMode(windowInfo2.windowMode)) {
                    ScreenModeHelper.isInFreeFormMode(windowInfo2.windowMode);
                }
                changeCurrentConfig(activity);
            }
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // miuix.autodensity.DensityProcessor
    public void processOnActivityCreated(Activity activity) {
        if (DebugUtil.isEnableDebug()) {
            DebugUtil.printDensityLog("->processOnActivityCreated");
        }
        boolean zIsShouldAdaptAutoDensity = isShouldAdaptAutoDensity(activity.getApplication());
        boolean zShouldAdaptAutoDensity = activity instanceof IDensity ? ((IDensity) activity).shouldAdaptAutoDensity() : zIsShouldAdaptAutoDensity;
        if (zShouldAdaptAutoDensity && DeviceHelper.isInRearDisplay(activity) && activity.getResources() != null) {
            DensityConfigManager.getInstance().tryUpdateConfig(activity, activity.getResources().getConfiguration());
        }
        updateApplicationDensity(activity.getApplication());
        if (zShouldAdaptAutoDensity) {
            DensityUtil.updateCustomDensity(activity);
            onDensityChangedOnActivityCreated(activity);
        } else if (zIsShouldAdaptAutoDensity) {
            DensityUtil.restoreDefaultDensity(activity);
            onDensityChangedOnActivityCreated(activity);
        }
    }

    @Override // miuix.autodensity.DensityProcessor
    public void processOnActivityDestroyed(Activity activity) {
        unregisterCallback(activity);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // miuix.autodensity.DensityProcessor
    public void processOnActivityDisplayChanged(int i2, Activity activity) {
        if (DebugUtil.isEnableDebug()) {
            DebugUtil.printDensityLog("->onDisplayChanged displayId: " + i2 + " config " + activity.getResources().getConfiguration() + "\n activity: " + activity);
        }
        boolean zIsShouldAdaptAutoDensity = isShouldAdaptAutoDensity(activity.getApplication());
        if (activity instanceof IDensity ? ((IDensity) activity).shouldAdaptAutoDensity() : zIsShouldAdaptAutoDensity) {
            DensityUtil.updateCustomDensity(activity);
            onDensityChangedOnActivityDisplayChanged(i2, activity);
        } else if (zIsShouldAdaptAutoDensity) {
            DensityUtil.restoreDefaultDensity(activity);
            onDensityChangedOnActivityDisplayChanged(i2, activity);
        }
    }

    @Override // miuix.autodensity.DensityProcessor
    public void processOnAppConfigChanged(Application application, Configuration configuration) {
        if (DebugUtil.isEnableDebug()) {
            DebugUtil.printDensityLog("->processOnAppConfigChanged");
        }
        DensityConfigManager.getInstance().tryUpdateConfig(application, configuration);
        if (isShouldAdaptAutoDensity(application)) {
            Display currentDisplay = DensityUtil.getCurrentDisplay(application);
            DensityUtil.updateCustomDensity(application);
            onDensityChangedOnAppConfigChanged(application);
            configuration.densityDpi = DensityConfigManager.getInstance().getTargetConfig(currentDisplay).densityDpi;
        }
    }

    @Override // miuix.autodensity.DensityProcessor
    public void registerCallback(Activity activity) {
        super.registerCallback(activity);
        tryToAddActivityConfigCallback(activity);
    }

    public void updateApplicationDensity(Application application) {
    }

    public boolean updateDensityOnConfigChanged(Context context, Configuration configuration) {
        boolean zTryUpdateConfig = DensityConfigManager.getInstance().tryUpdateConfig(context, configuration);
        if (context instanceof Activity) {
            Application application = ((Activity) context).getApplication();
            if (isShouldAdaptAutoDensity(application)) {
                updateApplicationDensity(application);
            }
        }
        updateDensity(context);
        return zTryUpdateConfig;
    }

    public static Context createAutoDensityContextWrapper(@NonNull Context context, int i2) {
        return createAutoDensityContextWrapper(context, i2, 0);
    }

    public static Context createAutoDensityContextWrapper(@NonNull Context context, int i2, int i3) {
        Configuration configuration = context.getResources().getConfiguration();
        Configuration configuration2 = new Configuration(configuration);
        Display currentDisplay = DensityUtil.getCurrentDisplay(context);
        if (DensityConfigManager.getInstance().getTargetConfig(currentDisplay) == null) {
            DensityConfigManager.getInstance().init(context);
        }
        AutoDensityContextWrapper autoDensityContextWrapper = new AutoDensityContextWrapper(context, i2);
        DensityConfigManager.getInstance().updateConfig(context, configuration, currentDisplay);
        autoDensityContextWrapper.setOriginConfiguration(configuration2);
        DensityUtil.updateCustomDensity(autoDensityContextWrapper, i3, currentDisplay);
        return autoDensityContextWrapper;
    }

    public static AutoDensityConfig init(Application application, boolean z2) {
        if (sInstance == null) {
            sUpdateSystemResources = z2;
            sInstance = new AutoDensityConfig(application);
        }
        return sInstance;
    }
}
