package com.xiaomi.onetrack.util;

import android.annotation.TargetApi;
import android.content.ContentResolver;
import android.content.Context;
import android.os.Build;
import android.os.Process;
import android.provider.Settings;
import android.text.TextUtils;
import android.util.Log;
import androidx.exifinterface.media.ExifInterface;
import com.xiaomi.onetrack.OneTrack;
import java.lang.reflect.Method;
import java.util.Locale;
import java.util.TimeZone;
import miuix.provider.ExtraSettings;

/* JADX INFO: loaded from: classes2.dex */
public class r {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final int f3641a = 33;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final int f3642b = 29;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final int f3643c = 25;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static final int f3644d = 24;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static final int f3645e = 23;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public static final int f3646f = 22;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public static final int f3647g = 21;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public static final int f3648h = 19;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public static final int f3649i = 17;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public static final int f3650j = 28;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public static final int f3651k = 2;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public static final int f3652l = 4;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    private static final String f3653m = "OsUtil";

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    private static Class f3654n = null;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    private static Method f3655o = null;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    private static Boolean f3656p = null;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    private static final String f3657q = "";

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    private static Method f3658r = null;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    private static boolean f3659s = false;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    private static String f3660t = null;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    private static boolean f3661u = false;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    private static int f3662v = 0;

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    private static boolean f3663w = false;

    static {
        try {
            f3658r = Class.forName("android.os.SystemProperties").getMethod("get", String.class);
        } catch (Throwable th) {
            q.b(f3653m, "sGetProp init failed ex: " + th.getMessage());
        }
    }

    public static void a() {
        if (f3663w) {
            return;
        }
        f3663w = true;
        try {
            f3654n = Class.forName("miui.os.Build");
        } catch (Throwable th) {
            q.b(f3653m, "sMiuiBuild init failed ex: " + th.getMessage());
        }
        try {
            Method declaredMethod = Class.forName("android.provider.MiuiSettings$Secure").getDeclaredMethod("isUserExperienceProgramEnable", ContentResolver.class);
            f3655o = declaredMethod;
            declaredMethod.setAccessible(true);
        } catch (Throwable th2) {
            q.b(f3653m, "sMiuiUEPMethod init failed ex: " + th2.getMessage());
        }
    }

    public static boolean b() {
        Boolean bool = f3656p;
        if (bool != null) {
            return bool.booleanValue();
        }
        if (TextUtils.isEmpty(c("ro.miui.ui.version.code"))) {
            f3656p = Boolean.FALSE;
        } else {
            f3656p = Boolean.TRUE;
        }
        return f3656p.booleanValue();
    }

    private static String c(String str) {
        try {
            Method method = f3658r;
            if (method != null) {
                return String.valueOf(method.invoke(null, str));
            }
        } catch (Throwable th) {
            q.b(f3653m, "getProp failed ex: " + th.getMessage());
        }
        return null;
    }

    public static String d() {
        Class cls = f3654n;
        if (cls == null) {
            return "";
        }
        try {
            if (((Boolean) cls.getField("IS_ALPHA_BUILD").get(null)).booleanValue()) {
                return ExifInterface.GPS_MEASUREMENT_IN_PROGRESS;
            }
            if (((Boolean) f3654n.getField("IS_STABLE_VERSION").get(null)).booleanValue()) {
                return ExifInterface.LATITUDE_SOUTH;
            }
            boolean zContains = Build.VERSION.INCREMENTAL.contains(".DEV");
            boolean zBooleanValue = ((Boolean) f3654n.getField("IS_DEVELOPMENT_VERSION").get(null)).booleanValue();
            return (!zBooleanValue || zContains) ? (zBooleanValue && zContains) ? "X" : "" : "D";
        } catch (Throwable th) {
            Log.e(f3653m, "getRomBuildCode failed: " + th.getMessage());
            return "";
        }
    }

    public static String e() {
        return Build.VERSION.INCREMENTAL;
    }

    public static String f() {
        return ac.a("ro.mi.os.version.incremental", "");
    }

    public static String g() {
        return Build.VERSION.RELEASE;
    }

    public static int h() {
        Integer num;
        Throwable th;
        try {
            Method declaredMethod = Class.forName("android.os.UserHandle").getDeclaredMethod("getUserId", Integer.TYPE);
            declaredMethod.setAccessible(true);
            int iMyUid = Process.myUid();
            num = (Integer) declaredMethod.invoke(null, Integer.valueOf(iMyUid));
            try {
                q.a(f3653m, String.format("getUserId, uid:%d, userId:%d", Integer.valueOf(iMyUid), num));
            } catch (Throwable th2) {
                th = th2;
                Log.e(q.a(f3653m), "getUserId exception: " + th.getMessage());
            }
        } catch (Throwable th3) {
            num = null;
            th = th3;
        }
        if (num == null) {
            num = 0;
        }
        return num.intValue();
    }

    @TargetApi(17)
    public static boolean i() {
        try {
            boolean z2 = Settings.Global.getInt(com.xiaomi.onetrack.f.a.b().getContentResolver(), "device_provisioned", 0) != 0;
            if (!z2) {
                q.c(f3653m, "Provisioned: " + z2);
            }
            return z2;
        } catch (Throwable th) {
            q.b(f3653m, "isDeviceProvisioned exception：" + th.getMessage());
            return true;
        }
    }

    public static boolean j() {
        Class cls = f3654n;
        if (cls != null) {
            try {
                return ((Boolean) cls.getField("IS_INTERNATIONAL_BUILD").get(null)).booleanValue();
            } catch (Throwable unused) {
            }
        }
        if (TextUtils.isEmpty(n())) {
            return false;
        }
        return !TextUtils.equals("CN", r0.toUpperCase());
    }

    public static boolean k() {
        return (!b() || f3661u) ? f3659s : j();
    }

    public static String l() {
        if ((!b() || f3661u) && !TextUtils.isEmpty(f3660t)) {
            return f3660t;
        }
        return n();
    }

    public static String m() {
        return n();
    }

    private static String n() {
        try {
            String strA = ac.a("ro.miui.region", "");
            if (TextUtils.isEmpty(strA)) {
                strA = ac.a("ro.product.locale.region", "");
            }
            if (TextUtils.isEmpty(strA)) {
                Object objInvoke = Class.forName("android.os.LocaleList").getMethod("getDefault", null).invoke(null, null);
                Object objInvoke2 = objInvoke.getClass().getMethod("size", null).invoke(objInvoke, null);
                if ((objInvoke2 instanceof Integer) && ((Integer) objInvoke2).intValue() > 0) {
                    Object objInvoke3 = objInvoke.getClass().getMethod("get", Integer.TYPE).invoke(objInvoke, 0);
                    Object objInvoke4 = objInvoke3.getClass().getMethod("getCountry", null).invoke(objInvoke3, null);
                    if (objInvoke4 instanceof String) {
                        strA = (String) objInvoke4;
                    }
                }
            }
            if (TextUtils.isEmpty(strA)) {
                strA = Locale.getDefault().getCountry();
            }
            if (!TextUtils.isEmpty(strA)) {
                return strA.trim();
            }
        } catch (Throwable th) {
            q.b(f3653m, "getRegion Exception: " + th.getMessage());
        }
        return "";
    }

    public static String c() {
        return a(TimeZone.getDefault().getRawOffset());
    }

    public static boolean b(String str) {
        if (!aa.b(str)) {
            str = l();
        }
        return a.f3426k.contains(str.toUpperCase());
    }

    public static String a(int i2) {
        char c2;
        try {
            int i3 = i2 / 60000;
            if (i3 < 0) {
                i3 = -i3;
                c2 = '-';
            } else {
                c2 = '+';
            }
            StringBuilder sb = new StringBuilder(9);
            sb.append("GMT");
            sb.append(c2);
            a(sb, i3 / 60);
            sb.append(':');
            a(sb, i3 % 60);
            return sb.toString();
        } catch (Exception unused) {
            return "";
        }
    }

    private static void a(StringBuilder sb, int i2) {
        String string = Integer.toString(i2);
        for (int i3 = 0; i3 < 2 - string.length(); i3++) {
            sb.append('0');
        }
        sb.append(string);
    }

    public static boolean a(Context context) {
        if (f3655o == null) {
            try {
                if (b()) {
                    int i2 = Settings.Secure.getInt(context.getContentResolver(), ExtraSettings.Secure.UPLOAD_LOG, -1);
                    q.a(f3653m, "isUserExperiencePlanEnabled upload_log_value: " + i2);
                    if (i2 != 1 && i2 == 0) {
                        return false;
                    }
                }
                return true;
            } catch (Throwable th) {
                q.a(f3653m, "Settings failed: " + th.toString());
            }
        }
        try {
            return ((Boolean) f3655o.invoke(null, context.getContentResolver())).booleanValue();
        } catch (Throwable th2) {
            Log.d(f3653m, "isUserExperiencePlanEnabled failed: " + th2.getMessage());
            return true;
        }
    }

    public static boolean a(String str) {
        if (!OneTrack.isDisable() && !OneTrack.isUseSystemNetTrafficOnly()) {
            if (!i()) {
                q.c(str, "should not access network or location, not provisioned");
                return true;
            }
            if (com.xiaomi.onetrack.c.j.b()) {
                return false;
            }
            q.c(str, "should not access network or location, cta");
            return true;
        }
        q.c(str, "should not access network or location, cta");
        return true;
    }

    public static void a(boolean z2, String str, OneTrack.Mode mode) {
        int i2;
        if (mode == OneTrack.Mode.APP) {
            i2 = 3;
        } else if (mode == OneTrack.Mode.PLUGIN) {
            i2 = 2;
        } else {
            i2 = mode == OneTrack.Mode.SDK ? 1 : 0;
        }
        if (f3662v <= i2) {
            f3659s = z2;
            f3660t = str;
            f3662v = i2;
        }
    }

    public static void a(boolean z2) {
        f3661u = z2;
    }
}
