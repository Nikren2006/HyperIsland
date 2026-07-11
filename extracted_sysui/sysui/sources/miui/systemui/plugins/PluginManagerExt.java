package miui.systemui.plugins;

import H0.d;
import H0.e;
import H0.s;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.IPackageDeleteObserver;
import android.content.pm.PackageManager;
import android.os.IBinder;
import android.util.Log;
import java.lang.reflect.Method;
import java.util.Arrays;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.n;

/* JADX INFO: loaded from: classes4.dex */
@SuppressLint({"PrivateApi"})
public final class PluginManagerExt {
    private static final String PLUGIN_PACKAGE_NAME = "miui.systemui.plugin";
    private static final String TAG = "PluginManagerExt";
    private final Context context;
    private final d needUninstallUpdateApp$delegate;
    private final d packageManager$delegate;
    public static final Companion Companion = new Companion(null);
    private static final d isControlCenterSecondaryExist$delegate = e.b(PluginManagerExt$Companion$isControlCenterSecondaryExist$2.INSTANCE);

    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void deletePackageAsUser(Object obj, String str, int i2, IPackageDeleteObserver iPackageDeleteObserver, int i3, int i4) {
            try {
                Class cls = Integer.TYPE;
                callObjectMethod(obj, "deletePackageAsUser", new Class[]{String.class, cls, IPackageDeleteObserver.class, cls, cls}, str, Integer.valueOf(i2), iPackageDeleteObserver, Integer.valueOf(i3), Integer.valueOf(i4));
            } catch (Throwable th) {
                Log.e(PluginManagerExt.TAG, "delete " + str + " failed.", th);
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final int getAppVersionCode(PackageManager packageManager, String str) {
            try {
                return packageManager.getPackageInfo(str, 0).versionCode;
            } catch (Throwable th) {
                Log.e(PluginManagerExt.TAG, "getAppVersionCode of " + str + " failed.", th);
                return 0;
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final boolean isControlCenterSecondaryExist() {
            return ((Boolean) PluginManagerExt.isControlCenterSecondaryExist$delegate.getValue()).booleanValue();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final boolean isUpdateSystemApp(PackageManager packageManager, String str) {
            try {
                ApplicationInfo applicationInfo = packageManager.getApplicationInfo(str, 0);
                Log.i(PluginManagerExt.TAG, "isUpdateSystemApp: " + ((applicationInfo.flags & 1) != 0));
                return (applicationInfo.flags & 128) != 0;
            } catch (Throwable th) {
                Log.e(PluginManagerExt.TAG, "isUpdateSystemApp: ", th);
                return false;
            }
        }

        public final Object callObjectMethod(Object target, String str, Class<?>[] parameterTypes, Object... values) {
            n.g(target, "target");
            n.g(parameterTypes, "parameterTypes");
            n.g(values, "values");
            return target.getClass().getDeclaredMethod(str, (Class[]) Arrays.copyOf(parameterTypes, parameterTypes.length)).invoke(target, Arrays.copyOf(values, values.length));
        }

        public final Object callStaticObjectMethod(Class<?> clazz, String str, Class<?>[] parameterTypes, Object... values) throws NoSuchMethodException {
            n.g(clazz, "clazz");
            n.g(parameterTypes, "parameterTypes");
            n.g(values, "values");
            Method declaredMethod = clazz.getDeclaredMethod(str, (Class[]) Arrays.copyOf(parameterTypes, parameterTypes.length));
            declaredMethod.setAccessible(true);
            return declaredMethod.invoke(null, Arrays.copyOf(values, values.length));
        }

        private Companion() {
        }
    }

    public PluginManagerExt(Context context) {
        n.g(context, "context");
        this.context = context;
        this.packageManager$delegate = e.b(new PluginManagerExt$packageManager$2(this));
        this.needUninstallUpdateApp$delegate = e.b(new PluginManagerExt$needUninstallUpdateApp$2(this));
        if (getNeedUninstallUpdateApp()) {
            uninstallUpdatePluginApp();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final PackageManager getPackageManager() {
        return (PackageManager) this.packageManager$delegate.getValue();
    }

    private final Object uninstallUpdatePluginApp() {
        try {
            Companion companion = Companion;
            PackageManager packageManager = this.context.getPackageManager();
            n.f(packageManager, "getPackageManager(...)");
            int appVersionCode = companion.getAppVersionCode(packageManager, "miui.systemui.plugin");
            Log.w(TAG, "uninstall " + appVersionCode);
            Class<?> cls = Class.forName("android.os.ServiceManager");
            n.f(cls, "forName(...)");
            Object objCallStaticObjectMethod = companion.callStaticObjectMethod(cls, "getService", new Class[]{String.class}, "package");
            n.e(objCallStaticObjectMethod, "null cannot be cast to non-null type android.os.IBinder");
            Class<?> cls2 = Class.forName("android.content.pm.IPackageManager$Stub");
            n.f(cls2, "forName(...)");
            Object objCallStaticObjectMethod2 = companion.callStaticObjectMethod(cls2, "asInterface", new Class[]{IBinder.class}, (IBinder) objCallStaticObjectMethod);
            n.d(objCallStaticObjectMethod2);
            companion.deletePackageAsUser(objCallStaticObjectMethod2, "miui.systemui.plugin", appVersionCode, null, 0, 0);
            return s.f314a;
        } catch (Throwable th) {
            return Integer.valueOf(Log.e(TAG, "uninstall failed.", th));
        }
    }

    public final boolean getNeedUninstallUpdateApp() {
        return ((Boolean) this.needUninstallUpdateApp$delegate.getValue()).booleanValue();
    }
}
