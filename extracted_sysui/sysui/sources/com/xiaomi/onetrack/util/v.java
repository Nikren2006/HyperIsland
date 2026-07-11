package com.xiaomi.onetrack.util;

import android.content.Context;
import android.os.Process;
import android.text.TextUtils;
import android.util.Log;
import androidx.mediarouter.media.SystemMediaRouteProvider;
import com.xiaomi.onetrack.api.at;
import java.util.HashSet;
import java.util.Set;

/* JADX INFO: loaded from: classes2.dex */
public class v {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static final String f3668a = "PermissionUtil";

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private static final String f3669b = "android.permission.READ_PRIVILEGED_PHONE_STATE";

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private static Set<String> f3670c;

    static {
        try {
            HashSet hashSet = new HashSet();
            f3670c = hashSet;
            hashSet.add(SystemMediaRouteProvider.PACKAGE_NAME);
            f3670c.add(at.f2905a);
            f3670c.add("com.miui.cit");
            f3670c.add("com.xiaomi.finddevice");
            f3670c.add("com.miui.securitycenter");
            f3670c.add("com.android.settings");
            f3670c.add("com.android.vending");
            f3670c.add("com.google.android.gms");
            f3670c.add("com.xiaomi.factory.mmi");
            f3670c.add("com.miui.qr");
            f3670c.add("com.android.contacts");
            f3670c.add("com.qualcomm.qti.autoregistration");
            f3670c.add("com.miui.tsmclient");
            f3670c.add("com.miui.sekeytool");
            f3670c.add("com.android.updater");
            if (!"cn_chinamobile".equals(ac.a("ro.miui.cust_variant")) && !"cn_chinatelecom".equals(ac.a("ro.miui.cust_variant"))) {
                return;
            }
            f3670c.add("com.mobiletools.systemhelper");
            f3670c.add("com.miui.dmregservice");
        } catch (Exception e2) {
            Log.e(f3668a, "static initializer: " + e2.toString());
        }
    }

    public static boolean a(Context context) {
        return a() ? a(com.xiaomi.onetrack.f.a.e()) && a(context, f3669b) : a(context, f3669b);
    }

    public static boolean b(Context context) {
        return a(context, f3669b);
    }

    private static boolean a() {
        try {
            if (r.b() && !r.k()) {
                return "1".equals(ac.a("ro.miui.restrict_imei"));
            }
            return false;
        } catch (Exception e2) {
            q.b(f3668a, "isRestrictIMEI " + e2.toString());
            return false;
        }
    }

    private static boolean a(String str) {
        Set<String> set;
        return (TextUtils.isEmpty(str) || (set = f3670c) == null || !set.contains(str)) ? false : true;
    }

    private static boolean a(Context context, String str) {
        return context.checkPermission(str, Process.myPid(), Process.myUid()) == 0;
    }
}
