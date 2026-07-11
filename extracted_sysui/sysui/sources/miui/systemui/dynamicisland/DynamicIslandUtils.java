package miui.systemui.dynamicisland;

import H0.j;
import H0.k;
import I0.u;
import android.app.ActivityManager;
import android.app.INotificationManager;
import android.app.PendingIntent;
import android.app.WindowConfiguration;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.ServiceManager;
import android.provider.Settings;
import android.service.notification.StatusBarNotification;
import android.text.TextUtils;
import android.util.Log;
import android.util.MiuiMultiWindowUtils;
import android.view.Display;
import android.view.DisplayCutout;
import android.widget.RemoteViews;
import androidx.mediarouter.media.SystemMediaRouteProvider;
import c1.f;
import com.android.systemui.plugins.miui.dynamicisland.DynamicIslandData;
import f1.o;
import java.lang.reflect.Method;
import java.util.Iterator;
import java.util.List;
import kotlin.jvm.internal.n;
import miui.app.MiuiFreeFormManager;
import miui.systemui.drawable.AppIconsManager;
import miui.systemui.util.CommonUtils;
import miui.systemui.util.ReflectUtil;
import miui.systemui.util.WindowConfigurationCompat;
import miuix.os.Build;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes3.dex */
public final class DynamicIslandUtils {
    public static final String TAG = "DynamicIslandUtils";
    private static int cutoutBoundingRectTopWidth;
    private static Context onlyApplicationContext;
    private static int tinyScreenInsetBottom;
    private static int tinyScreenInsetLeft;
    private static int tinyScreenInsetRight;
    private static int tinyScreenInsetTop;
    public static final DynamicIslandUtils INSTANCE = new DynamicIslandUtils();
    private static final String traceName = "DynamicIslandAnimation#IslandAnimRunning";
    private static final int traceCookie = "DynamicIslandAnimation#IslandAnimRunning".hashCode();
    private static final boolean isInternationalBuild = Build.IS_INTERNATIONAL_BUILD;
    private static final String ISLAND_ANIM_DURATION_SCALE = "island_animator_duration_scale";
    private static float animDurationScale = -1.0f;

    private DynamicIslandUtils() {
    }

    private final Context buildOnlyApplicationContext(Context context) {
        return new ContextWrapper(context) { // from class: miui.systemui.dynamicisland.DynamicIslandUtils.buildOnlyApplicationContext.1
            final /* synthetic */ Context $context;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(context);
                this.$context = context;
            }

            @Override // android.content.ContextWrapper, android.content.Context
            public Context getApplicationContext() {
                return this;
            }

            @Override // android.content.ContextWrapper, android.content.Context
            public Resources getResources() {
                Resources resources = this.$context.getResources();
                n.f(resources, "getResources(...)");
                return resources;
            }
        };
    }

    private final void cancelNotification(StatusBarNotification statusBarNotification) {
        String packageName;
        INotificationManager iNotificationManagerAsInterface = INotificationManager.Stub.asInterface(ServiceManager.getService("notification"));
        try {
            Class cls = Integer.TYPE;
            Class[] clsArr = {String.class, String.class, String.class, cls, cls};
            if (statusBarNotification.getKey() == null) {
                packageName = statusBarNotification.getPackageName();
            } else {
                String key = statusBarNotification.getKey();
                n.f(key, "getKey(...)");
                String packageName2 = statusBarNotification.getPackageName();
                n.f(packageName2, "getPackageName(...)");
                packageName = o.v(key, packageName2, false, 2, null) ? statusBarNotification.getPackageName() : statusBarNotification.getOpPkg();
            }
            ReflectUtil.callObjectMethod(iNotificationManagerAsInterface, Void.class, "cancelNotificationWithTag", clsArr, packageName, statusBarNotification.getOpPkg(), statusBarNotification.getTag(), Integer.valueOf(statusBarNotification.getId()), Integer.valueOf(statusBarNotification.getUserId()));
        } catch (Exception e2) {
            Log.e(TAG, "cancelNotification: " + statusBarNotification.getKey() + ", " + e2);
        }
    }

    private final Context contextWrapper(Context context) {
        if (onlyApplicationContext == null) {
            onlyApplicationContext = buildOnlyApplicationContext(context);
        }
        return new ContextWrapper(context) { // from class: miui.systemui.dynamicisland.DynamicIslandUtils.contextWrapper.1
            final /* synthetic */ Context $context;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(context);
                this.$context = context;
            }

            @Override // android.content.ContextWrapper, android.content.Context
            public Context getApplicationContext() {
                return DynamicIslandUtils.onlyApplicationContext;
            }

            @Override // android.content.ContextWrapper, android.content.Context
            public Resources getResources() {
                Resources resources = this.$context.getResources();
                n.f(resources, "getResources(...)");
                return resources;
            }
        };
    }

    private final List<String> getTopSplitPackageNames(Context context) {
        List<String> list;
        try {
            Object objInvoke = MiuiMultiWindowUtils.class.getMethod("getTopSplitPackageNames", Context.class).invoke(null, context);
            n.e(objInvoke, "null cannot be cast to non-null type kotlin.collections.List<kotlin.String>");
            list = (List) objInvoke;
        } catch (Exception unused) {
            Log.e(TAG, "getTopSplitPackageNames");
        }
        if (list.size() > 0) {
            return list;
        }
        return null;
    }

    private final boolean hasCustomFocusView(StatusBarNotification statusBarNotification) {
        try {
            return statusBarNotification.getNotification().extras.getParcelable("miui.focus.rv") instanceof RemoteViews;
        } catch (Exception unused) {
            return false;
        }
    }

    private final boolean shouldCancelNotification(StatusBarNotification statusBarNotification) {
        return (hasCustomFocusView(statusBarNotification) || (statusBarNotification.getNotification().flags & 16) == 0) ? false : true;
    }

    public final boolean activitySupportFreeform(Context context, PendingIntent pendingIntent) {
        ResolveInfo resolveInfoResolveActivity;
        n.g(context, "context");
        if (pendingIntent == null || (resolveInfoResolveActivity = context.getPackageManager().resolveActivity(pendingIntent.getIntent(), 0)) == null) {
            return true;
        }
        return ActivityInfo.isResizeableMode(resolveInfoResolveActivity.activityInfo.resizeMode);
    }

    public final void clearIslandNotification(StatusBarNotification sbn) {
        n.g(sbn, "sbn");
        if (shouldCancelNotification(sbn)) {
            Log.w(TAG, "notification_cancel clear " + sbn.getKey() + " on island.");
            cancelNotification(sbn);
        }
    }

    public final int dpToPx(int i2, Context context) {
        n.g(context, "context");
        return (int) (i2 * context.getResources().getDisplayMetrics().density);
    }

    public final Drawable getAppIcon(Context pluginContext, String str, Integer num) {
        n.g(pluginContext, "pluginContext");
        if (num == null || str == null) {
            return null;
        }
        try {
            Context contextContextWrapper = contextWrapper(pluginContext);
            return AppIconsManager.INSTANCE.getAppIcon(str, num.intValue(), contextContextWrapper.getPackageManager().getApplicationInfo(str, 128), contextContextWrapper.getPackageManager(), contextContextWrapper);
        } catch (PackageManager.NameNotFoundException unused) {
            Log.e(TAG, "error when getAppIcon caught NameNotFoundException");
            return null;
        } catch (Exception unused2) {
            Log.e(TAG, "error when getAppIcon");
            return null;
        }
    }

    public final String getAppName(Context context, DynamicIslandData dynamicIslandData) {
        Bundle extras;
        n.g(context, "context");
        try {
            PackageManager packageManager = context.getPackageManager();
            String string = (dynamicIslandData == null || (extras = dynamicIslandData.getExtras()) == null) ? null : extras.getString("miui.pkg.name");
            ApplicationInfo applicationInfo = string != null ? packageManager.getApplicationInfo(string, 0) : null;
            if (applicationInfo != null) {
                return packageManager.getApplicationLabel(applicationInfo).toString();
            }
            return null;
        } catch (Exception e2) {
            e2.printStackTrace();
            return null;
        }
    }

    public final int getCutoutBoundingRectTopWidth() {
        return cutoutBoundingRectTopWidth;
    }

    public final float getDebugIslandAnimScale(Context ctx) {
        n.g(ctx, "ctx");
        if (animDurationScale < 0.0f) {
            animDurationScale = f.h(Settings.Global.getFloat(ctx.getContentResolver(), ISLAND_ANIM_DURATION_SCALE, 1.0f), 0.1f, 10.0f);
        }
        return animDurationScale;
    }

    public final List<MiuiFreeFormManager.MiuiFreeFormStackInfo> getFreeFormList() {
        try {
            j.a aVar = j.f299a;
            Method method = MiuiFreeFormManager.class.getMethod("getAllFreeFormStackInfosOnDisplay", Integer.TYPE);
            n.f(method, "getMethod(...)");
            Object objInvoke = method.invoke(null, 0);
            if (objInvoke instanceof List) {
                return (List) objInvoke;
            }
            return null;
        } catch (Throwable th) {
            j.a aVar2 = j.f299a;
            Object objA = j.a(k.a(th));
            return (List) (j.c(objA) ? null : objA);
        }
    }

    public final String getISLAND_ANIM_DURATION_SCALE() {
        return ISLAND_ANIM_DURATION_SCALE;
    }

    public final int getNavigationBarFrameHeight() {
        try {
            Resources system = Resources.getSystem();
            int identifier = system.getIdentifier("navigation_bar_frame_height", "dimen", SystemMediaRouteProvider.PACKAGE_NAME);
            if (identifier == 0) {
                return 0;
            }
            return system.getDimensionPixelSize(identifier);
        } catch (Throwable th) {
            Log.e(TAG, "get navigation_bar_frame_height failed.", th);
            return 0;
        }
    }

    public final int getScreenHeight(Context context) {
        n.g(context, "<this>");
        return getScreenHeightOld(context);
    }

    public final int getScreenHeightOld(Context context) {
        n.g(context, "context");
        return context.getResources().getDisplayMetrics().heightPixels;
    }

    public final int getScreenWidth(Context context) {
        n.g(context, "<this>");
        return getScreenWidthOld(context);
    }

    public final int getScreenWidthOld(Context context) {
        Rect maxBoundsCompat;
        n.g(context, "context");
        WindowConfigurationCompat windowConfigurationCompat = WindowConfigurationCompat.INSTANCE;
        Configuration configuration = context.getResources().getConfiguration();
        n.f(configuration, "getConfiguration(...)");
        WindowConfiguration windowConfigurationCompat2 = windowConfigurationCompat.getWindowConfigurationCompat(configuration);
        if (windowConfigurationCompat2 == null || (maxBoundsCompat = windowConfigurationCompat.getMaxBoundsCompat(windowConfigurationCompat2)) == null) {
            return 0;
        }
        return (maxBoundsCompat.width() - tinyScreenInsetLeft) - tinyScreenInsetRight;
    }

    public final int getTinyScreenInsetBottom() {
        return tinyScreenInsetBottom;
    }

    public final int getTinyScreenInsetLeft() {
        return tinyScreenInsetLeft;
    }

    public final int getTinyScreenInsetRight() {
        return tinyScreenInsetRight;
    }

    public final int getTinyScreenInsetTop() {
        return tinyScreenInsetTop;
    }

    public final int getTraceCookie() {
        return traceCookie;
    }

    public final String getTraceName() {
        return traceName;
    }

    public final boolean isAppInstalledForUser(Context context, String str, Integer num) {
        n.g(context, "context");
        try {
            PackageManager packageManager = context.getPackageManager();
            n.d(str);
            packageManager.getPackageInfo(str, 1);
            return true;
        } catch (PackageManager.NameNotFoundException unused) {
            return false;
        }
    }

    public final boolean isGlowEffectEnabledForBigState$miui_dynamicisland_release(DynamicIslandData dynamicIslandData) {
        Bundle extras;
        String string = (dynamicIslandData == null || (extras = dynamicIslandData.getExtras()) == null) ? null : extras.getString(DynamicIslandConstants.EXTRA_BIG_ISLAND_EFFECT);
        return !(string == null || string.length() == 0);
    }

    public final boolean isGlowEffectEnabledForExpandState$miui_dynamicisland_release(DynamicIslandData dynamicIslandData) {
        Bundle extras;
        String string = (dynamicIslandData == null || (extras = dynamicIslandData.getExtras()) == null) ? null : extras.getString("miui.effect.src");
        return !(string == null || string.length() == 0);
    }

    public final String isIntentActivityExist(Context context, Intent intent, String str) {
        List<ResolveInfo> listQueryIntentActivities;
        n.g(context, "context");
        n.g(intent, "intent");
        try {
            PackageManager packageManager = context.getPackageManager();
            if (packageManager == null || (listQueryIntentActivities = packageManager.queryIntentActivities(intent, 786432)) == null || listQueryIntentActivities.size() <= 0) {
                return str;
            }
            Iterator<ResolveInfo> it = listQueryIntentActivities.iterator();
            while (it.hasNext()) {
                if (TextUtils.equals(it.next().activityInfo.packageName, str)) {
                    return str;
                }
            }
            return listQueryIntentActivities.get(0).activityInfo.packageName;
        } catch (Exception unused) {
            Log.e(TAG, "error get resolve info");
            return str;
        }
    }

    public final boolean isInternationalBuild() {
        return isInternationalBuild;
    }

    public final boolean isPinMode(String str, PendingIntent pendingIntent) {
        try {
            Object objInvoke = MiuiFreeFormManager.class.getMethod("hasMiuiFreeformOnShellFeature", null).invoke(null, null);
            n.e(objInvoke, "null cannot be cast to non-null type kotlin.Boolean");
            boolean zBooleanValue = ((Boolean) objInvoke).booleanValue();
            Method method = MiuiFreeFormManager.class.getMethod("getAllFreeFormStackInfosOnDisplay", Integer.TYPE);
            n.f(method, "getMethod(...)");
            Object objInvoke2 = method.invoke(null, 0);
            List<MiuiFreeFormManager.MiuiFreeFormStackInfo> list = objInvoke2 instanceof List ? (List) objInvoke2 : null;
            Intent intent = pendingIntent != null ? pendingIntent.getIntent() : null;
            if (zBooleanValue && list != null && !list.isEmpty() && intent != null) {
                for (MiuiFreeFormManager.MiuiFreeFormStackInfo miuiFreeFormStackInfo : list) {
                    if (TextUtils.equals(miuiFreeFormStackInfo.packageName, str) && miuiFreeFormStackInfo.inPinMode) {
                        return true;
                    }
                }
            }
            return false;
        } catch (Exception unused) {
            Log.w(TAG, "getAllFreeFormStackInfosOnDisplay");
            return false;
        }
    }

    public final boolean isSplitPkg(String str, Context context) {
        n.g(context, "context");
        boolean zIsInSplitScreenWindowingMode = ActivityManager.isInSplitScreenWindowingMode();
        List<String> topSplitPackageNames = getTopSplitPackageNames(context);
        return zIsInSplitScreenWindowingMode && topSplitPackageNames != null && u.F(topSplitPackageNames, str);
    }

    public final boolean isSupportFreeFormAnim(Context context) {
        n.g(context, "context");
        try {
            return context.getPackageManager().getApplicationInfo("com.android.systemui", 128).metaData.getBoolean("miui.dynamicIsland.supportFreeFormAnim", true);
        } catch (Throwable th) {
            Log.e(TAG, "supportFreeFormAnim available true " + th);
            return true;
        }
    }

    public final boolean packageSupportFreeform(Context context, String str) {
        n.g(context, "context");
        try {
            Method declaredMethod = MiuiMultiWindowUtils.class.getDeclaredMethod("packageSupportFreeform", Context.class, String.class);
            n.f(declaredMethod, "getDeclaredMethod(...)");
            declaredMethod.setAccessible(true);
            Object objInvoke = declaredMethod.invoke(null, context, str);
            n.e(objInvoke, "null cannot be cast to non-null type kotlin.Boolean");
            return ((Boolean) objInvoke).booleanValue();
        } catch (Exception e2) {
            e2.printStackTrace();
            return false;
        }
    }

    public final JSONObject resolveFocusParam(StatusBarNotification sbn) {
        JSONObject jSONObjectOptJSONObject;
        n.g(sbn, "sbn");
        String string = sbn.getNotification().extras.getString(hasCustomFocusView(sbn) ? "miui.focus.param.custom" : "miui.focus.param");
        JSONObject jSONObject = string != null ? new JSONObject(string) : null;
        return (hasCustomFocusView(sbn) || jSONObject == null || (jSONObjectOptJSONObject = jSONObject.optJSONObject("param_v2")) == null) ? jSONObject : jSONObjectOptJSONObject;
    }

    public final void setCutoutBoundingRectTopWidth(int i2) {
        cutoutBoundingRectTopWidth = i2;
    }

    public final void setTinyScreenInsetBottom(int i2) {
        tinyScreenInsetBottom = i2;
    }

    public final void setTinyScreenInsetLeft(int i2) {
        tinyScreenInsetLeft = i2;
    }

    public final void setTinyScreenInsetRight(int i2) {
        tinyScreenInsetRight = i2;
    }

    public final void setTinyScreenInsetTop(int i2) {
        tinyScreenInsetTop = i2;
    }

    public final void updateBoundingRect(Context context) {
        Rect boundingRectTop;
        n.g(context, "context");
        DisplayCutout cutout = context.getDisplay().getCutout();
        if (cutout == null || (boundingRectTop = cutout.getBoundingRectTop()) == null) {
            return;
        }
        cutoutBoundingRectTopWidth = boundingRectTop.width();
    }

    public final void updateFlipOutInsetRight(Context context) {
        DisplayCutout cutout;
        DisplayCutout cutout2;
        DisplayCutout cutout3;
        DisplayCutout cutout4;
        n.g(context, "context");
        int safeInsetBottom = 0;
        if (!CommonUtils.isFlipDevice() || !CommonUtils.isTinyScreen(context)) {
            tinyScreenInsetLeft = 0;
            tinyScreenInsetRight = 0;
            tinyScreenInsetTop = 0;
            tinyScreenInsetBottom = 0;
            return;
        }
        Display display = context.getDisplay();
        tinyScreenInsetLeft = (display == null || (cutout4 = display.getCutout()) == null) ? 0 : cutout4.getSafeInsetLeft();
        Display display2 = context.getDisplay();
        tinyScreenInsetRight = (display2 == null || (cutout3 = display2.getCutout()) == null) ? 0 : cutout3.getSafeInsetRight();
        Display display3 = context.getDisplay();
        tinyScreenInsetTop = (display3 == null || (cutout2 = display3.getCutout()) == null) ? 0 : cutout2.getSafeInsetTop();
        Display display4 = context.getDisplay();
        if (display4 != null && (cutout = display4.getCutout()) != null) {
            safeInsetBottom = cutout.getSafeInsetBottom();
        }
        tinyScreenInsetBottom = safeInsetBottom;
    }
}
