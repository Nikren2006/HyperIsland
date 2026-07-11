package x0;

import android.content.ActivityNotFoundException;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ServiceInfo;
import android.content.res.Resources;
import java.lang.reflect.Method;
import java.util.Arrays;
import m0.F;
import miui.systemui.util.SmartDeviceUtils;

/* JADX INFO: loaded from: classes2.dex */
public abstract class y {
    public static int a() {
        try {
            Method method = Class.forName("miui.securityspace.CrossUserUtils").getMethod("getCurrentUserId", null);
            method.setAccessible(true);
            return ((Integer) method.invoke(null, null)).intValue();
        } catch (Exception e2) {
            z0.e.b("StatusChecker", "getCurrentUserId: ", e2);
            return -1;
        }
    }

    public static boolean b(Context context) {
        try {
            PackageManager packageManager = context.getPackageManager();
            Resources resourcesForApplication = packageManager.getResourcesForApplication(packageManager.getApplicationInfo("com.android.systemui", 0));
            int identifier = resourcesForApplication.getIdentifier("system_foreground_notification_whitelist", "array", "com.android.systemui");
            if (identifier <= 0) {
                z0.e.c("StatusChecker", "not found notification whitelist resources");
                return false;
            }
            boolean zContains = Arrays.asList(resourcesForApplication.getStringArray(identifier)).contains("com.milink.service:hide_foreground");
            z0.e.c("StatusChecker", "found milink in whiteList:" + zContains);
            return zContains;
        } catch (Exception e2) {
            z0.e.b("StatusChecker", "notificationReady", e2);
            return false;
        }
    }

    public static boolean c(Context context) {
        if (context == null) {
            throw new IllegalArgumentException("ctx is NULL");
        }
        try {
            ServiceInfo serviceInfo = context.getPackageManager().getServiceInfo(new ComponentName(SmartDeviceUtils.MI_LINK_PACKAGE_NAME, "com.miui.miplay.audio.service.CoreService"), 131072);
            Class<?> clsLoadClass = context.getClassLoader().loadClass("miui.media.MiuiAudioPlaybackRecorder");
            boolean z2 = serviceInfo != null;
            boolean z3 = clsLoadClass != null;
            boolean zD = d(context);
            boolean z4 = F.f5278a;
            boolean zB = b(context);
            z0.e.c("StatusChecker", "support miplay, hasService:" + z2 + ", hasAudio:" + z3 + ", hasSystemUI:" + zD + ", international: " + z4 + ", hasWhiteList: " + zB);
            return !z4 && z2 && z3 && zD && zB;
        } catch (Exception e2) {
            z0.e.b("StatusChecker", "supportMiPlayAudio", e2);
            return false;
        }
    }

    public static boolean d(Context context) {
        Intent intent = new Intent("miui.intent.action.ACTIVITY_MIPLAY_DETAIL");
        intent.addFlags(268435456);
        try {
            return context.getPackageManager().resolveActivity(intent, 65536) != null;
        } catch (ActivityNotFoundException unused) {
            return false;
        }
    }
}
