package miui.systemui.quicksettings;

import android.app.ActivityManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.Bundle;
import android.os.SystemClock;
import android.os.SystemProperties;
import android.provider.Settings;
import android.text.TextUtils;
import android.util.Slog;
import android.widget.Switch;
import android.widget.Toast;
import com.android.systemui.miui.volume.VolumePanelViewController;
import com.android.systemui.plugins.annotations.Requires;
import com.android.systemui.plugins.miui.qs.MiuiQSTile;
import com.android.systemui.plugins.miui.qs.MiuiQSTilePlugin;
import com.android.systemui.plugins.qs.QSTile;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import miui.systemui.util.FoldUtils;
import miuix.device.DeviceUtils;
import systemui.plugin.eventtracking.events.TrackDeskTopFunctionGuideExposeEvent;
import systemui.plugin.eventtracking.events.TrackDeskTopOpenEvent;
import systemui.plugin.eventtracking.trackers.BaseEventTracker;
import systemui.plugin.eventtracking.trackers.ControlsEventTracker;

/* JADX INFO: loaded from: classes4.dex */
@Requires(target = MiuiQSTilePlugin.class, version = 1)
public class MiuiDesktopModeTile implements MiuiQSTile {
    private static final String DATA_VERSION = "23060800";
    private static final String FUNCTION_GUIDE_EXPOSE_SHOW_GUIDE_CONTROL_BUTTON = "控制中心";
    private static final String FUNCTION_GUIDE_EXPOSE_TIP = "621.0.0.0.29079";
    private static final long MIN_CLICK_INTERVAL = 1000;
    private static final String MIUI_CTS = "ro.miui.cts";
    private static final String MIUI_DEFAULT_LAUNCHER_ACTIVITY = "default_launcher_activity";
    private static final String MIUI_DEFAULT_LAUNCHER_PACKAGE = "default_launcher_package";
    private static final String MIUI_DESKTOP_MODE_SETTINGS_ACTION = "miui.settings.intent.action.DESKTOP_MODE_SETTINGS";
    private static final String MIUI_DESKTOP_MODE_SETTINGS_ACTION_HIGH = "com.miui.freeform.settings.action.DESKTOP_SETTINGS";
    private static final String MIUI_HOME = "com.miui.home";
    private static final String MIUI_OPTIMIZATION = "persist.sys.miui_optimization";
    private static final String OPEN_CONTROL_BUTTON = "控制中心";
    private static final String OPEN_TIP = "621.0.0.0.29078";
    private static final String POCO_LAUNCHER_PACKAGE = "com.mi.android.globallauncher";
    public static final String PROP_ENTERPRISE_ACTIVATED = "persist.sys.mi.ep.activated";
    private static final String SHOWING_GUIDE_MIUI_DESKTOP_MODE = "showing_guide_miui_desktop_mode";
    private static final String SHOW_GUIDE_MIUI_DESKTOP_MODE = "show_guide_miui_desktop_mode";
    private static final String TAG = "MiuiDesktopModeTile";
    private static final String TILE_SPEC = "dtmdtm";
    public static int USER_OWNER;
    private static final AtomicLong lastClickTime = new AtomicLong(0);
    private boolean mActive;
    private ArrayList<QSTile.Callback> mCallbacks;
    private ResolveInfo mDefaultHomeResolveInfo;
    private PackageManager mPackageManager;
    private Context mPluginContext;
    private QSTile.State mState;
    private ResolveInfo mSystemHomeResolveInfo;
    private Context mSysuiContext;
    private String MIUI_DESKTOP_MODE = "miui_dkt_mode";
    private int MIUI_DEKTOP_MODE_ON = 1;
    private int MIUI_DEKTOP_OFF = 0;

    public MiuiDesktopModeTile(Context context, Context context2) {
        this.mActive = false;
        this.mPluginContext = context2;
        this.mSysuiContext = context;
        QSTile.State state = new QSTile.State();
        this.mState = state;
        state.state = 1;
        this.mCallbacks = new ArrayList<>();
        this.mActive = isActive();
        this.mPackageManager = this.mPluginContext.getPackageManager();
        getMiuiHome();
    }

    private void adaptHome(boolean z2) {
        if (!z2) {
            if (getSettingsString(this.mPluginContext, MIUI_DEFAULT_LAUNCHER_PACKAGE).equals("com.miui.home") || !isHomeAlive(getSettingsString(this.mPluginContext, MIUI_DEFAULT_LAUNCHER_PACKAGE)) || getSettingsString(this.mPluginContext, MIUI_DEFAULT_LAUNCHER_ACTIVITY) == null) {
                return;
            }
            PackageManager packageManager = this.mPackageManager;
            launcherHome(packageManager, getDefaultHomePackageName(packageManager), new ComponentName(getSettingsString(this.mPluginContext, MIUI_DEFAULT_LAUNCHER_PACKAGE), getSettingsString(this.mPluginContext, MIUI_DEFAULT_LAUNCHER_ACTIVITY)));
            return;
        }
        if (isUseMiuiHomeAsDefaultHome(this.mPackageManager) || isUsePocoHomeAsDefaultHome(this.mPackageManager)) {
            return;
        }
        PackageManager packageManager2 = this.mPackageManager;
        String defaultHomePackageName = getDefaultHomePackageName(packageManager2);
        ActivityInfo activityInfo = this.mSystemHomeResolveInfo.activityInfo;
        launcherHome(packageManager2, defaultHomePackageName, new ComponentName(activityInfo.packageName, activityInfo.name));
    }

    private static <T> T callStaticObjectMethod(Class<?> cls, Class<T> cls2, String str, Class<?>[] clsArr, Object... objArr) throws NoSuchMethodException {
        Method declaredMethod = cls.getDeclaredMethod(str, clsArr);
        declaredMethod.setAccessible(true);
        return (T) declaredMethod.invoke(null, objArr);
    }

    private void collapseStatusBar(Context context) {
        try {
            Object systemService = context.getSystemService(VolumePanelViewController.SERVICE_STATUS_BAR);
            systemService.getClass().getMethod("collapsePanels", null).invoke(systemService, null);
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    private void downDefaultHome() {
        if (isActive()) {
            return;
        }
        this.mDefaultHomeResolveInfo = getCurrentHome(this.mPackageManager);
        Settings.System.putString(this.mPluginContext.getContentResolver(), MIUI_DEFAULT_LAUNCHER_PACKAGE, this.mDefaultHomeResolveInfo.activityInfo.packageName);
        Settings.System.putString(this.mPluginContext.getContentResolver(), MIUI_DEFAULT_LAUNCHER_ACTIVITY, this.mDefaultHomeResolveInfo.activityInfo.name);
    }

    private ResolveInfo getCurrentHome(PackageManager packageManager) {
        if (packageManager == null) {
            return null;
        }
        Intent intent = new Intent("android.intent.action.MAIN");
        intent.addCategory("android.intent.category.HOME");
        return packageManager.resolveActivity(intent, 65536);
    }

    private String getDefaultHomePackageName(PackageManager packageManager) {
        ResolveInfo currentHome = getCurrentHome(packageManager);
        if (currentHome == null) {
            return null;
        }
        return currentHome.activityInfo.packageName;
    }

    private void getMiuiHome() {
        if (this.mSystemHomeResolveInfo != null) {
            return;
        }
        Intent intent = new Intent("android.intent.action.MAIN");
        intent.addCategory("android.intent.category.HOME");
        for (ResolveInfo resolveInfo : this.mPackageManager.queryIntentActivities(intent, 0)) {
            if (!this.mPluginContext.getPackageName().equals(resolveInfo.activityInfo.packageName) && resolveInfo.activityInfo.packageName.equals("com.miui.home")) {
                this.mSystemHomeResolveInfo = resolveInfo;
            }
        }
    }

    private String getSettingsString(Context context, String str) {
        return Settings.System.getString(context.getContentResolver(), str);
    }

    private boolean isActive() {
        if (!isSupportMiuiDekstopModeByFK()) {
            return false;
        }
        try {
            return Settings.System.getInt(this.mPluginContext.getContentResolver(), this.MIUI_DESKTOP_MODE) != 0;
        } catch (Settings.SettingNotFoundException e2) {
            Slog.d(TAG, "Failed to read MIUI_DESKTOP_MODE settings " + e2);
            return false;
        }
    }

    public static boolean isFoldDevice() {
        return SystemProperties.getInt(FoldUtils.MUILT_DISPLAY_TYPE, 0) == 2;
    }

    private boolean isHomeAlive(String str) {
        if (str == null) {
            return false;
        }
        Intent intent = new Intent("android.intent.action.MAIN");
        intent.addCategory("android.intent.category.HOME");
        for (ResolveInfo resolveInfo : this.mPackageManager.queryIntentActivities(intent, 0)) {
            if (!this.mPluginContext.getPackageName().equals(resolveInfo.activityInfo.packageName) && resolveInfo.activityInfo.packageName.equals(str)) {
                return true;
            }
        }
        return false;
    }

    private boolean isMiuiOptimization() {
        return SystemProperties.getBoolean(MIUI_OPTIMIZATION, !"1".equals(SystemProperties.get(MIUI_CTS)));
    }

    public static boolean isScreenLayoutLarge(Context context) {
        if (context == null) {
            return false;
        }
        int i2 = context.getResources().getConfiguration().screenLayout & 15;
        return i2 == 3 || i2 == 4;
    }

    private boolean isShowMiuiDesktopModeGuide() {
        return Settings.System.getInt(this.mPluginContext.getContentResolver(), SHOW_GUIDE_MIUI_DESKTOP_MODE, -1) != 1;
    }

    private boolean isShowingMiuiDesktopModeGuide() {
        return Settings.System.getInt(this.mPluginContext.getContentResolver(), SHOWING_GUIDE_MIUI_DESKTOP_MODE, -1) == 1;
    }

    private static boolean isSupportMiuiDekstopModeByFK() {
        return SystemProperties.getBoolean("ro.config.miui_desktop_mode_enabled", false);
    }

    private static boolean isSupportMiuiDekstopModeByKid(Context context) {
        return Settings.Global.getInt(context.getContentResolver(), "kid_mode_status", -1) != 1;
    }

    private boolean isSupportMiuiDesktopModeByEn() {
        return SystemProperties.getBoolean(PROP_ENTERPRISE_ACTIVATED, false);
    }

    private boolean isSupportMiuiDesktopModeByHome() {
        try {
            Bundle bundle = this.mPluginContext.getPackageManager().getApplicationInfo("com.miui.home", 128).metaData;
            if (bundle != null) {
                return bundle.getBoolean("supportDesktopMode");
            }
            return false;
        } catch (Exception unused) {
            Slog.d(TAG, "Failed to get ApplicationInfo");
            return false;
        }
    }

    private static boolean isTablet() {
        return "tablet".equals(SystemProperties.get("ro.build.characteristics", "default"));
    }

    private boolean isUseMiuiHomeAsDefaultHome(PackageManager packageManager) {
        return TextUtils.equals(getDefaultHomePackageName(packageManager), "com.miui.home");
    }

    private boolean isUsePocoHomeAsDefaultHome(PackageManager packageManager) {
        return TextUtils.equals(getDefaultHomePackageName(packageManager), POCO_LAUNCHER_PACKAGE);
    }

    private void launcherHome(PackageManager packageManager, String str, ComponentName componentName) {
        Intent intent = new Intent("android.intent.action.MAIN");
        intent.addCategory("android.intent.category.HOME");
        List<ResolveInfo> listQueryIntentActivities = packageManager.queryIntentActivities(intent, 131072);
        int size = listQueryIntentActivities.size();
        ComponentName[] componentNameArr = new ComponentName[size];
        int i2 = 0;
        for (int i3 = 0; i3 < size; i3++) {
            ResolveInfo resolveInfo = listQueryIntentActivities.get(i3);
            ActivityInfo activityInfo = resolveInfo.activityInfo;
            componentNameArr[i3] = new ComponentName(activityInfo.packageName, activityInfo.name);
            int i4 = resolveInfo.match;
            if (i4 > i2) {
                i2 = i4;
            }
        }
        packageManager.clearPackagePreferredActivities(str);
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.intent.action.MAIN");
        intentFilter.addCategory("android.intent.category.HOME");
        intentFilter.addCategory("android.intent.category.DEFAULT");
        packageManager.addPreferredActivity(intentFilter, i2, componentNameArr, componentName);
    }

    private boolean setShowingMiuiDesktopModeGuide() {
        return Settings.System.putInt(this.mPluginContext.getContentResolver(), SHOWING_GUIDE_MIUI_DESKTOP_MODE, 1);
    }

    public void addCallback(QSTile.Callback callback) {
        if (this.mCallbacks.contains(callback)) {
            return;
        }
        this.mCallbacks.add(callback);
        callback.onStateChanged(this.mState);
    }

    public String composeChangeAnnouncement() {
        return null;
    }

    public Intent getLongClickIntent() {
        Intent intent = new Intent();
        intent.setAction(MIUI_DESKTOP_MODE_SETTINGS_ACTION_HIGH);
        intent.setClassName("com.miui.securitycore", "com.miui.freeform.guide.MiuiDesktopModeActivity");
        if (!isActivityExit(intent, this.mPackageManager)) {
            intent.setAction(MIUI_DESKTOP_MODE_SETTINGS_ACTION_HIGH);
            intent.setClassName("com.miui.freeform", "com.miui.freeform.guide.MiuiDesktopModeActivity");
        }
        return intent;
    }

    public int getMetricsCategory() {
        return 0;
    }

    public QSTile.State getState() {
        return this.mState;
    }

    public String getTileSpec() {
        return TILE_SPEC;
    }

    public void handleClick() {
        long jUptimeMillis = SystemClock.uptimeMillis();
        if (jUptimeMillis - lastClickTime.getAndSet(jUptimeMillis) < 1000) {
            Slog.w(TAG, "handleClick too fast, skip click");
            return;
        }
        collapseStatusBar(this.mPluginContext);
        downDefaultHome();
        if (isShowingMiuiDesktopModeGuide() && isShowMiuiDesktopModeGuide()) {
            return;
        }
        if (!isMiuiOptimization()) {
            Context context = this.mPluginContext;
            Toast.makeText(context, context.getResources().getString(R.string.open_system_optimization), 1).show();
            return;
        }
        if (!isActive() && isShowMiuiDesktopModeGuide()) {
            setShowingMiuiDesktopModeGuide();
            trackExpose(this.mPluginContext);
            Intent intent = new Intent();
            intent.setComponent(new ComponentName("com.miui.freeform", "com.miui.freeform.guide.MiuiDesktopModeGuideActivity")).addFlags(268435456);
            if (!isActivityExit(intent, this.mPackageManager)) {
                intent.setComponent(new ComponentName("com.miui.securitycore", "com.miui.freeform.guide.MiuiDesktopModeGuideActivity"));
            }
            this.mPluginContext.startActivity(intent);
            refreshState(null);
            refreshTile();
            return;
        }
        try {
            boolean zIsActive = isActive();
            this.mActive = zIsActive;
            adaptHome(!zIsActive);
            if (this.mActive) {
                setDeskTopMode(this.MIUI_DEKTOP_OFF);
            } else {
                setDeskTopMode(this.MIUI_DEKTOP_MODE_ON);
                trackClick(this.mPluginContext);
            }
        } catch (Exception unused) {
            Slog.d(TAG, "Failed to handleClick");
        }
        refreshState(null);
        refreshTile();
    }

    public boolean isActivityExit(Intent intent, PackageManager packageManager) {
        return packageManager.queryIntentActivities(intent, 65536).size() > 0;
    }

    public boolean isAvailable() {
        try {
            if (ActivityManager.getCurrentUser() == USER_OWNER && isSupportMiuiDekstopModeByFK() && isSupportMiuiDekstopModeByKid(this.mPluginContext) && DeviceUtils.getTotalRam() >= 8) {
                return ((Boolean) callStaticObjectMethod(Class.forName("android.util.MiuiMultiWindowUtils"), Boolean.TYPE, "supportFreeform", null, new Object[0])).booleanValue();
            }
            return false;
        } catch (ClassNotFoundException | IllegalAccessException | NoSuchMethodException | InvocationTargetException unused) {
            Slog.d(TAG, "falied to return isAvailable");
            return false;
        }
    }

    public QSTile.State newTileState() {
        return this.mState;
    }

    public void refreshState(Object obj) {
        this.mState.state = isActive() ? 2 : 1;
        this.mState.label = this.mPluginContext.getString(R.string.multi_desktop_mode);
        this.mState.icon = new DrawableIcon(this.mPluginContext.getResources().getDrawable(R.drawable.ic_qs_desktop));
        this.mState.expandedAccessibilityClassName = Switch.class.getName();
        QSTile.State state = this.mState;
        state.contentDescription = state.label;
    }

    public void refreshTile() {
        Iterator<QSTile.Callback> it = this.mCallbacks.iterator();
        while (it.hasNext()) {
            it.next().onStateChanged(this.mState);
        }
    }

    public void removeCallback(QSTile.Callback callback) {
        this.mCallbacks.remove(callback);
    }

    public void setDeskTopMode(int i2) {
        Settings.System.putInt(this.mPluginContext.getContentResolver(), this.MIUI_DESKTOP_MODE, i2);
    }

    public void setListening(boolean z2) {
        refreshState(null);
        refreshTile();
    }

    public void trackClick(Context context) {
        BaseEventTracker.get().track(new TrackDeskTopOpenEvent(OPEN_TIP, isTablet() ? "pad" : "手机", context.getResources().getConfiguration().orientation == 2 ? ControlsEventTracker.ORIENTATION_LANDSCAPE : ControlsEventTracker.ORIENTATION_PORTRAIT, isFoldDevice() ? isScreenLayoutLarge(context) ? "內屏" : "外屏" : "nothing", DATA_VERSION, isActive() ? "工作台模式" : "普通模式", "控制中心"));
    }

    public void trackExpose(Context context) {
        BaseEventTracker.get().track(new TrackDeskTopFunctionGuideExposeEvent(FUNCTION_GUIDE_EXPOSE_TIP, isTablet() ? "pad" : "手机", context.getResources().getConfiguration().orientation == 2 ? ControlsEventTracker.ORIENTATION_LANDSCAPE : ControlsEventTracker.ORIENTATION_PORTRAIT, isFoldDevice() ? isScreenLayoutLarge(context) ? "內屏" : "外屏" : "nothing", DATA_VERSION, isActive() ? "工作台模式" : "普通模式", "控制中心"));
    }
}
