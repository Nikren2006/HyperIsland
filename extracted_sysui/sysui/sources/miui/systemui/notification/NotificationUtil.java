package miui.systemui.notification;

import android.app.Notification;
import android.content.Context;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.service.notification.StatusBarNotification;
import android.text.TextUtils;
import android.util.Log;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import miui.systemui.notification.focus.Const;
import miuix.core.util.SystemProperties;

/* JADX INFO: loaded from: classes4.dex */
public class NotificationUtil {
    public static final String BUILD_REGION;
    public static final boolean IS_INTERNATIONAL_BUILD;
    private static final String PKG_HYBRID = "com.miui.hybrid";
    public static final String PRODUCT_MODE_DEVICE = "ro.product.mod_device";
    private static final String TAG = "NotificationUtil";
    private static final String sSubstitutePackagesResName = "config_canSendSubstituteNotificationPackages";
    public static final boolean DEBUG = SystemProperties.getBoolean("debug.sysui", false);
    private static final List<String> sSubstitutePackages = new ArrayList();

    static {
        String str = android.os.SystemProperties.get(PRODUCT_MODE_DEVICE, "");
        BUILD_REGION = str;
        IS_INTERNATIONAL_BUILD = str.contains("_global");
    }

    public static boolean canSendSubstituteNotification(Context context, String str) {
        initSubstitutePackages(context);
        return sSubstitutePackages.contains(str);
    }

    public static void debugLog(String str, String str2) {
        Log.d(str, str2);
    }

    public static Bitmap drawableToBitmap(Drawable drawable) {
        if (drawable instanceof BitmapDrawable) {
            return ((BitmapDrawable) drawable).getBitmap();
        }
        Bitmap bitmapCreateBitmap = Bitmap.createBitmap(drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmapCreateBitmap);
        drawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
        drawable.draw(canvas);
        return bitmapCreateBitmap;
    }

    public static Drawable getAppIcon(Context context, String str) {
        try {
            PackageManager packageManager = context.getPackageManager();
            return packageManager.getPackageInfo(str, 128).applicationInfo.loadIcon(packageManager);
        } catch (Exception e2) {
            e2.printStackTrace();
            return null;
        }
    }

    public static CharSequence getNotificationTargetPkg(Notification notification) {
        return notification.extras.containsKey(Const.Param.EXTRA_TARGET_PKG) ? notification.extras.getCharSequence(Const.Param.EXTRA_TARGET_PKG) : notification.extraNotification.getTargetPkg();
    }

    public static String getSbnTargetPkg(Context context, StatusBarNotification statusBarNotification) {
        if (statusBarNotification == null) {
            return "";
        }
        String packageName = statusBarNotification.getPackageName();
        if ("com.miui.hybrid".equals(packageName) && statusBarNotification.getNotification().extras != null) {
            String string = statusBarNotification.getNotification().extras.getString("miui.category");
            if (!TextUtils.isEmpty(string)) {
                return string;
            }
        }
        if (!canSendSubstituteNotification(context, packageName)) {
            return packageName;
        }
        CharSequence notificationTargetPkg = getNotificationTargetPkg(statusBarNotification.getNotification());
        return !TextUtils.isEmpty(notificationTargetPkg) ? notificationTargetPkg.toString() : packageName;
    }

    public static void initSubstitutePackages(Context context) {
        List<String> list = sSubstitutePackages;
        if (list.isEmpty()) {
            Resources resources = context.getResources();
            list.addAll(Arrays.asList(resources.getStringArray(resources.getIdentifier(sSubstitutePackagesResName, "array", context.getPackageName()))));
            debugLog(TAG, "sSubstitutePackages=" + list);
        }
    }
}
