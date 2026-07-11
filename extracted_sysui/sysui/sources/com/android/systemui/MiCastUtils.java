package com.android.systemui;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.os.Bundle;
import android.util.Log;
import miui.os.Build;
import miui.systemui.util.SmartDeviceUtils;

/* JADX INFO: loaded from: classes.dex */
public final class MiCastUtils {
    public static final String TAG = "MiCastUtils";
    private static Boolean castEnable;
    public static final MiCastUtils INSTANCE = new MiCastUtils();
    private static String milinkPackage = SmartDeviceUtils.MI_LINK_PACKAGE_NAME;
    private static String meteDataKey = "com.xiaomi.googlecast.enable";

    private MiCastUtils() {
    }

    public final boolean useInternationalCast(Context context) {
        Object obj;
        ApplicationInfo applicationInfo;
        kotlin.jvm.internal.n.g(context, "context");
        if (Build.IS_INTERNATIONAL_BUILD && castEnable == null) {
            try {
                PackageInfo packageInfo = context.getPackageManager().getPackageInfo(milinkPackage, 128);
                Bundle bundle = (packageInfo == null || (applicationInfo = packageInfo.applicationInfo) == null) ? null : applicationInfo.metaData;
                castEnable = (bundle == null || (obj = bundle.get(meteDataKey)) == null) ? Boolean.FALSE : Boolean.valueOf(obj.equals("enable"));
            } catch (Throwable th) {
                castEnable = Boolean.FALSE;
                Log.e(TAG, " error when get useInternationalCast", th);
            }
        }
        Log.d(TAG, "useInternationalCast: " + castEnable);
        return kotlin.jvm.internal.n.c(castEnable, Boolean.TRUE);
    }
}
