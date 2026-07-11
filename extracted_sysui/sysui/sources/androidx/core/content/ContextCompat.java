package androidx.core.content;

import android.accounts.AccountManager;
import android.annotation.SuppressLint;
import android.app.ActivityManager;
import android.app.AlarmManager;
import android.app.AppOpsManager;
import android.app.DownloadManager;
import android.app.KeyguardManager;
import android.app.NotificationManager;
import android.app.SearchManager;
import android.app.UiModeManager;
import android.app.WallpaperManager;
import android.app.admin.DevicePolicyManager;
import android.app.job.JobScheduler;
import android.app.usage.UsageStatsManager;
import android.appwidget.AppWidgetManager;
import android.bluetooth.BluetoothManager;
import android.content.BroadcastReceiver;
import android.content.ClipboardManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.RestrictionsManager;
import android.content.pm.LauncherApps;
import android.content.res.ColorStateList;
import android.graphics.drawable.Drawable;
import android.hardware.ConsumerIrManager;
import android.hardware.SensorManager;
import android.hardware.camera2.CameraManager;
import android.hardware.display.DisplayManager;
import android.hardware.input.InputManager;
import android.hardware.usb.UsbManager;
import android.location.LocationManager;
import android.media.AudioManager;
import android.media.MediaRouter;
import android.media.projection.MediaProjectionManager;
import android.media.session.MediaSessionManager;
import android.media.tv.TvInputManager;
import android.net.ConnectivityManager;
import android.net.nsd.NsdManager;
import android.net.wifi.WifiManager;
import android.net.wifi.p2p.WifiP2pManager;
import android.nfc.NfcManager;
import android.os.BatteryManager;
import android.os.Bundle;
import android.os.DropBoxManager;
import android.os.Handler;
import android.os.PowerManager;
import android.os.Process;
import android.os.UserManager;
import android.os.Vibrator;
import android.os.storage.StorageManager;
import android.print.PrintManager;
import android.telecom.TelecomManager;
import android.telephony.SubscriptionManager;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.WindowManager;
import android.view.accessibility.AccessibilityManager;
import android.view.accessibility.CaptioningManager;
import android.view.inputmethod.InputMethodManager;
import android.view.textservice.TextServicesManager;
import androidx.annotation.ColorInt;
import androidx.annotation.ColorRes;
import androidx.annotation.DrawableRes;
import androidx.annotation.ReplaceWith;
import androidx.annotation.RequiresApi;
import androidx.annotation.RestrictTo;
import androidx.core.app.LocaleManagerCompat;
import androidx.core.content.res.ResourcesCompat;
import androidx.core.util.ObjectsCompat;
import com.miui.circulate.device.api.Constant;
import com.miui.miplay.audio.data.DeviceInfo;
import com.xiaomi.onetrack.api.au;
import java.io.File;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.HashMap;
import java.util.concurrent.Executor;
import miui.systemui.controlcenter.qs.TileSpecsKt;

/* JADX INFO: loaded from: classes.dex */
@SuppressLint({"PrivateConstructorForUtilityClass"})
public class ContextCompat {
    private static final String DYNAMIC_RECEIVER_NOT_EXPORTED_PERMISSION_SUFFIX = ".DYNAMIC_RECEIVER_NOT_EXPORTED_PERMISSION";
    public static final int RECEIVER_EXPORTED = 2;
    public static final int RECEIVER_NOT_EXPORTED = 4;
    public static final int RECEIVER_VISIBLE_TO_INSTANT_APPS = 1;
    private static final String TAG = "ContextCompat";
    private static final Object sSync = new Object();

    @RequiresApi(21)
    public static class Api21Impl {
        private Api21Impl() {
        }

        public static File getCodeCacheDir(Context context) {
            return context.getCodeCacheDir();
        }

        public static Drawable getDrawable(Context context, int i2) {
            return context.getDrawable(i2);
        }

        public static File getNoBackupFilesDir(Context context) {
            return context.getNoBackupFilesDir();
        }
    }

    @RequiresApi(23)
    public static class Api23Impl {
        private Api23Impl() {
        }

        public static int getColor(Context context, int i2) {
            return context.getColor(i2);
        }

        public static <T> T getSystemService(Context context, Class<T> cls) {
            return (T) context.getSystemService(cls);
        }

        public static String getSystemServiceName(Context context, Class<?> cls) {
            return context.getSystemServiceName(cls);
        }
    }

    @RequiresApi(24)
    public static class Api24Impl {
        private Api24Impl() {
        }

        public static Context createDeviceProtectedStorageContext(Context context) {
            return context.createDeviceProtectedStorageContext();
        }

        public static File getDataDir(Context context) {
            return context.getDataDir();
        }

        public static boolean isDeviceProtectedStorage(Context context) {
            return context.isDeviceProtectedStorage();
        }
    }

    @RequiresApi(26)
    public static class Api26Impl {
        private Api26Impl() {
        }

        public static Intent registerReceiver(Context context, BroadcastReceiver broadcastReceiver, IntentFilter intentFilter, String str, Handler handler, int i2) {
            return ((i2 & 4) == 0 || str != null) ? context.registerReceiver(broadcastReceiver, intentFilter, str, handler, i2 & 1) : context.registerReceiver(broadcastReceiver, intentFilter, ContextCompat.obtainAndCheckReceiverPermission(context), handler);
        }

        public static ComponentName startForegroundService(Context context, Intent intent) {
            return context.startForegroundService(intent);
        }
    }

    @RequiresApi(28)
    public static class Api28Impl {
        private Api28Impl() {
        }

        public static Executor getMainExecutor(Context context) {
            return context.getMainExecutor();
        }
    }

    @RequiresApi(30)
    public static class Api30Impl {
        private Api30Impl() {
        }

        public static Context createAttributionContext(Context context, String str) {
            return context.createAttributionContext(str);
        }

        public static String getAttributionTag(Context context) {
            return context.getAttributionTag();
        }

        public static Display getDisplayOrDefault(Context context) {
            try {
                return context.getDisplay();
            } catch (UnsupportedOperationException unused) {
                Log.w(ContextCompat.TAG, "The context:" + context + " is not associated with any display. Return a fallback display instead.");
                return ((DisplayManager) context.getSystemService(DisplayManager.class)).getDisplay(0);
            }
        }
    }

    @RequiresApi(33)
    public static class Api33Impl {
        private Api33Impl() {
        }

        public static Intent registerReceiver(Context context, BroadcastReceiver broadcastReceiver, IntentFilter intentFilter, String str, Handler handler, int i2) {
            return context.registerReceiver(broadcastReceiver, intentFilter, str, handler, i2);
        }
    }

    public static final class LegacyServiceMapHolder {
        static final HashMap<Class<?>, String> SERVICES;

        static {
            HashMap<Class<?>, String> map = new HashMap<>();
            SERVICES = map;
            map.put(SubscriptionManager.class, "telephony_subscription_service");
            map.put(UsageStatsManager.class, "usagestats");
            map.put(AppWidgetManager.class, "appwidget");
            map.put(BatteryManager.class, "batterymanager");
            map.put(CameraManager.class, "camera");
            map.put(JobScheduler.class, "jobscheduler");
            map.put(LauncherApps.class, "launcherapps");
            map.put(MediaProjectionManager.class, "media_projection");
            map.put(MediaSessionManager.class, "media_session");
            map.put(RestrictionsManager.class, "restrictions");
            map.put(TelecomManager.class, "telecom");
            map.put(TvInputManager.class, "tv_input");
            map.put(AppOpsManager.class, "appops");
            map.put(CaptioningManager.class, "captioning");
            map.put(ConsumerIrManager.class, "consumer_ir");
            map.put(PrintManager.class, "print");
            map.put(BluetoothManager.class, Constant.DeviceType.BLUETOOTH);
            map.put(DisplayManager.class, "display");
            map.put(UserManager.class, "user");
            map.put(InputManager.class, "input");
            map.put(MediaRouter.class, "media_router");
            map.put(NsdManager.class, "servicediscovery");
            map.put(AccessibilityManager.class, "accessibility");
            map.put(AccountManager.class, "account");
            map.put(ActivityManager.class, "activity");
            map.put(AlarmManager.class, "alarm");
            map.put(AudioManager.class, DeviceInfo.AUDIO_SUPPORT);
            map.put(ClipboardManager.class, "clipboard");
            map.put(ConnectivityManager.class, "connectivity");
            map.put(DevicePolicyManager.class, "device_policy");
            map.put(DownloadManager.class, "download");
            map.put(DropBoxManager.class, "dropbox");
            map.put(InputMethodManager.class, "input_method");
            map.put(KeyguardManager.class, "keyguard");
            map.put(LayoutInflater.class, "layout_inflater");
            map.put(LocationManager.class, "location");
            map.put(NfcManager.class, "nfc");
            map.put(NotificationManager.class, "notification");
            map.put(PowerManager.class, "power");
            map.put(SearchManager.class, Constant.PARAM_SEARCH);
            map.put(SensorManager.class, "sensor");
            map.put(StorageManager.class, "storage");
            map.put(TelephonyManager.class, au.f2924d);
            map.put(TextServicesManager.class, "textservices");
            map.put(UiModeManager.class, "uimode");
            map.put(UsbManager.class, "usb");
            map.put(Vibrator.class, "vibrator");
            map.put(WallpaperManager.class, "wallpaper");
            map.put(WifiP2pManager.class, "wifip2p");
            map.put(WifiManager.class, TileSpecsKt.TILE_SPEC_WIFI);
            map.put(WindowManager.class, "window");
        }

        private LegacyServiceMapHolder() {
        }
    }

    @Retention(RetentionPolicy.SOURCE)
    @RestrictTo({RestrictTo.Scope.LIBRARY})
    public @interface RegisterReceiverFlags {
    }

    public static int checkSelfPermission(Context context, String str) {
        ObjectsCompat.requireNonNull(str, "permission must be non-null");
        return context.checkPermission(str, Process.myPid(), Process.myUid());
    }

    public static Context createAttributionContext(Context context, String str) {
        return Api30Impl.createAttributionContext(context, str);
    }

    public static Context createDeviceProtectedStorageContext(Context context) {
        return Api24Impl.createDeviceProtectedStorageContext(context);
    }

    private static File createFilesDir(File file) {
        synchronized (sSync) {
            try {
                if (!file.exists()) {
                    if (file.mkdirs()) {
                        return file;
                    }
                    Log.w(TAG, "Unable to create files subdir " + file.getPath());
                }
                return file;
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public static String getAttributionTag(Context context) {
        return Api30Impl.getAttributionTag(context);
    }

    public static File getCodeCacheDir(Context context) {
        return Api21Impl.getCodeCacheDir(context);
    }

    @ColorInt
    public static int getColor(Context context, @ColorRes int i2) {
        return Api23Impl.getColor(context, i2);
    }

    public static ColorStateList getColorStateList(Context context, @ColorRes int i2) {
        return ResourcesCompat.getColorStateList(context.getResources(), i2, context.getTheme());
    }

    public static Context getContextForLanguage(Context context) {
        LocaleManagerCompat.getApplicationLocales(context);
        return context;
    }

    public static File getDataDir(Context context) {
        return Api24Impl.getDataDir(context);
    }

    public static Display getDisplayOrDefault(Context context) {
        return Api30Impl.getDisplayOrDefault(context);
    }

    public static Drawable getDrawable(Context context, @DrawableRes int i2) {
        return Api21Impl.getDrawable(context, i2);
    }

    @ReplaceWith(expression = "context.getExternalCacheDirs()")
    @Deprecated
    public static File[] getExternalCacheDirs(Context context) {
        return context.getExternalCacheDirs();
    }

    @ReplaceWith(expression = "context.getExternalFilesDirs(type)")
    @Deprecated
    public static File[] getExternalFilesDirs(Context context, String str) {
        return context.getExternalFilesDirs(str);
    }

    public static Executor getMainExecutor(Context context) {
        return Api28Impl.getMainExecutor(context);
    }

    public static File getNoBackupFilesDir(Context context) {
        return Api21Impl.getNoBackupFilesDir(context);
    }

    @ReplaceWith(expression = "context.getObbDirs()")
    @Deprecated
    public static File[] getObbDirs(Context context) {
        return context.getObbDirs();
    }

    public static String getString(Context context, int i2) {
        return getContextForLanguage(context).getString(i2);
    }

    public static <T> T getSystemService(Context context, Class<T> cls) {
        return (T) Api23Impl.getSystemService(context, cls);
    }

    public static String getSystemServiceName(Context context, Class<?> cls) {
        return Api23Impl.getSystemServiceName(context, cls);
    }

    public static boolean isDeviceProtectedStorage(Context context) {
        return Api24Impl.isDeviceProtectedStorage(context);
    }

    public static String obtainAndCheckReceiverPermission(Context context) {
        String str = context.getApplicationContext().getPackageName() + DYNAMIC_RECEIVER_NOT_EXPORTED_PERMISSION_SUFFIX;
        if (PermissionChecker.checkSelfPermission(context, str) == 0) {
            return str;
        }
        String str2 = context.getOpPackageName() + DYNAMIC_RECEIVER_NOT_EXPORTED_PERMISSION_SUFFIX;
        if (PermissionChecker.checkSelfPermission(context, str2) == 0) {
            return str2;
        }
        throw new RuntimeException("Permission " + str2 + " is required by your application to receive broadcasts, please add it to your manifest");
    }

    public static Intent registerReceiver(Context context, BroadcastReceiver broadcastReceiver, IntentFilter intentFilter, int i2) {
        return registerReceiver(context, broadcastReceiver, intentFilter, null, null, i2);
    }

    public static boolean startActivities(Context context, Intent[] intentArr) {
        return startActivities(context, intentArr, null);
    }

    @ReplaceWith(expression = "context.startActivity(intent, options)")
    @Deprecated
    public static void startActivity(Context context, Intent intent, Bundle bundle) {
        context.startActivity(intent, bundle);
    }

    public static void startForegroundService(Context context, Intent intent) {
        Api26Impl.startForegroundService(context, intent);
    }

    public static Intent registerReceiver(Context context, BroadcastReceiver broadcastReceiver, IntentFilter intentFilter, String str, Handler handler, int i2) {
        int i3 = i2 & 1;
        if (i3 != 0 && (i2 & 4) != 0) {
            throw new IllegalArgumentException("Cannot specify both RECEIVER_VISIBLE_TO_INSTANT_APPS and RECEIVER_NOT_EXPORTED");
        }
        if (i3 != 0) {
            i2 |= 2;
        }
        int i4 = i2;
        int i5 = i4 & 2;
        if (i5 == 0 && (i4 & 4) == 0) {
            throw new IllegalArgumentException("One of either RECEIVER_EXPORTED or RECEIVER_NOT_EXPORTED is required");
        }
        if (i5 == 0 || (i4 & 4) == 0) {
            return Api33Impl.registerReceiver(context, broadcastReceiver, intentFilter, str, handler, i4);
        }
        throw new IllegalArgumentException("Cannot specify both RECEIVER_EXPORTED and RECEIVER_NOT_EXPORTED");
    }

    public static boolean startActivities(Context context, Intent[] intentArr, Bundle bundle) {
        context.startActivities(intentArr, bundle);
        return true;
    }
}
