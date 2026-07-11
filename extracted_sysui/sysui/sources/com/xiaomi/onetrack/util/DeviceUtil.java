package com.xiaomi.onetrack.util;

import android.annotation.SuppressLint;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.provider.Settings;
import android.telephony.SubscriptionManager;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import com.xiaomi.onetrack.api.ah;
import com.xiaomi.onetrack.api.au;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public class DeviceUtil {

    /* JADX INFO: renamed from: A, reason: collision with root package name */
    private static String f3383A = null;

    /* JADX INFO: renamed from: B, reason: collision with root package name */
    private static Boolean f3384B = null;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    static final int f3385a = 15;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    static final int f3386b = 6;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private static final String f3387c = "DeviceUtil";

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private static final String f3388d = "MI_BOX";

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private static final String f3389e = "MI_TVBOX";

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private static final String f3390f = "MI_PROJECTOR";

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private static final String f3391g = "MI_TV";

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    private static final String f3392h = "MI_PAD";

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    private static final String f3393i = "MI_WIFI_SPEAKER";

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    private static final String f3394j = "MI_PHONE";

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    private static final String f3395k = "OTHER";

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    private static final String f3396l = "";

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    private static final int f3397m = 15;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    private static Method f3398n = null;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    private static Method f3399o = null;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    private static Object f3400p = null;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    private static Method f3401q = null;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    private static Method f3402r = null;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    private static volatile String f3403s = null;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    private static volatile String f3404t = null;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    private static String f3405u = null;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    private static String f3406v = null;

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    private static String f3407w = null;

    /* JADX INFO: renamed from: x, reason: collision with root package name */
    private static String f3408x = null;

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    private static volatile boolean f3409y = false;

    /* JADX INFO: renamed from: z, reason: collision with root package name */
    private static final String f3410z = "0000000000000000";

    public static class a {
        private a() {
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static String b() {
            try {
                Class<?> cls = Class.forName("mitv.common.ConfigurationManager");
                int i2 = Integer.parseInt(String.valueOf(cls.getMethod("getProductCategory", null).invoke(cls.getMethod("getInstance", null).invoke(cls, null), null)));
                Class<?> cls2 = Class.forName("mitv.tv.TvContext");
                return i2 == Integer.parseInt(String.valueOf(a(cls2, "PRODUCT_CATEGORY_MITV"))) ? DeviceUtil.f3391g : i2 == Integer.parseInt(String.valueOf(a(cls2, "PRODUCT_CATEGORY_MIBOX"))) ? DeviceUtil.f3388d : i2 == Integer.parseInt(String.valueOf(a(cls2, "PRODUCT_CATEGORY_MITVBOX"))) ? DeviceUtil.f3389e : i2 == Integer.parseInt(String.valueOf(a(cls2, "PRODUCT_CATEGORY_MIPROJECTOR"))) ? DeviceUtil.f3390f : "";
            } catch (Throwable th) {
                q.c(DeviceUtil.f3387c, "getMiTvProductCategory exception: " + th.getMessage());
                return "";
            }
        }

        private static <T> T a(Class<?> cls, String str) {
            try {
                Field declaredField = cls.getDeclaredField(str);
                declaredField.setAccessible(true);
                return (T) declaredField.get(null);
            } catch (Throwable th) {
                q.c(DeviceUtil.f3387c, "getStaticVariableValue exception: " + th.getMessage());
                return null;
            }
        }
    }

    static {
        try {
            f3398n = Class.forName("android.os.SystemProperties").getMethod("get", String.class);
        } catch (Throwable th) {
            q.b(f3387c, "sGetProp init failed ex: " + th.getMessage());
        }
        try {
            Class<?> cls = Class.forName("miui.telephony.TelephonyManagerEx");
            f3400p = cls.getMethod("getDefault", null).invoke(null, null);
            f3399o = cls.getMethod("getImeiList", null);
            f3402r = cls.getMethod("getSubscriberIdForSlot", Integer.TYPE);
        } catch (Throwable th2) {
            q.b(f3387c, "TelephonyManagerEx init failed ex: " + th2.getMessage());
        }
        try {
            f3401q = Class.forName("android.telephony.TelephonyManager").getMethod("getImei", Integer.TYPE);
        } catch (Throwable th3) {
            q.b(f3387c, "sGetImeiForSlot init failed ex: " + th3.getMessage());
        }
        f3384B = null;
    }

    public static String a(Context context) {
        if (!TextUtils.isEmpty(f3403s)) {
            return f3403s;
        }
        g(context);
        return !TextUtils.isEmpty(f3403s) ? f3403s : "";
    }

    public static String b(Context context) {
        if (!TextUtils.isEmpty(f3406v)) {
            return f3406v;
        }
        String strA = a(context);
        if (TextUtils.isEmpty(strA)) {
            return "";
        }
        String strC = com.xiaomi.onetrack.d.d.c(strA);
        f3406v = strC;
        return strC;
    }

    public static String c(Context context) {
        if (!TextUtils.isEmpty(f3404t)) {
            return f3404t;
        }
        g(context);
        return !TextUtils.isEmpty(f3404t) ? f3404t : "";
    }

    public static String d(Context context) {
        if (!TextUtils.isEmpty(f3407w)) {
            return f3407w;
        }
        String strC = c(context);
        if (TextUtils.isEmpty(strC)) {
            return "";
        }
        String strC2 = com.xiaomi.onetrack.d.d.c(strC);
        f3407w = strC2;
        return strC2;
    }

    public static String e(Context context) {
        if (!TextUtils.isEmpty(f3405u)) {
            return f3405u;
        }
        if (GAIDClient.b(context)) {
            return "";
        }
        String strA = GAIDClient.a(context);
        if (TextUtils.isEmpty(strA)) {
            return "";
        }
        f3405u = strA;
        return strA;
    }

    public static List<String> f(Context context) {
        List<String> listG = g(context);
        ArrayList arrayList = new ArrayList();
        if (listG != null && !listG.isEmpty()) {
            for (int i2 = 0; i2 < listG.size(); i2++) {
                if (!TextUtils.isEmpty(listG.get(i2))) {
                    arrayList.add(i2, com.xiaomi.onetrack.d.d.c(listG.get(i2)));
                }
            }
        }
        return arrayList;
    }

    @SuppressLint({"MissingPermission"})
    public static List<String> g(Context context) {
        List<String> listL = null;
        if (v.a(context)) {
            if (f3409y) {
                return null;
            }
            List<String> listJ = j();
            listL = (listJ == null || listJ.isEmpty()) ? l(context) : listJ;
            f3409y = true;
        }
        if (listL != null && !listL.isEmpty()) {
            Collections.sort(listL);
            f3403s = listL.get(0);
            if (listL.size() >= 2) {
                f3404t = listL.get(1);
            }
        }
        return listL;
    }

    @SuppressLint({"MissingPermission"})
    public static List<String> h(Context context) {
        if (!v.b(context)) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        try {
            if (!k()) {
                String subscriberId = ((TelephonyManager) context.getSystemService(au.f2924d)).getSubscriberId();
                if (c(subscriberId)) {
                    arrayList.add(subscriberId);
                }
                return arrayList;
            }
            Class<?> cls = Class.forName("android.telephony.TelephonyManager");
            TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService(au.f2924d);
            SubscriptionManager subscriptionManager = (SubscriptionManager) context.getSystemService("telephony_subscription_service");
            Class<?> cls2 = Class.forName("android.telephony.SubscriptionManager");
            String str = b(cls, cls2, telephonyManager, subscriptionManager)[0];
            String str2 = b(cls, cls2, telephonyManager, subscriptionManager)[1];
            if (!c(str)) {
                str = "";
            }
            arrayList.add(str);
            if (!c(str2)) {
                str2 = "";
            }
            arrayList.add(str2);
            return arrayList;
        } catch (SecurityException unused) {
            q.a(f3387c, "getImsiList failed with on permission");
            return null;
        } catch (Throwable th) {
            q.b(f3387c, "getImsiList failed: " + th.getMessage());
            return null;
        }
    }

    public static String i(Context context) {
        try {
            List<String> listH = h(context);
            if (listH == null) {
                return "";
            }
            for (int i2 = 0; i2 < listH.size(); i2++) {
                listH.set(i2, com.xiaomi.onetrack.d.d.h(listH.get(i2)));
            }
            return listH.toString();
        } catch (Throwable th) {
            q.b(f3387c, "getImeiListMd5 failed!", th);
            return "";
        }
    }

    private static List<String> j() {
        if (f3399o != null && !l()) {
            try {
                List<String> list = (List) f3399o.invoke(f3400p, null);
                if (list != null && list.size() > 0) {
                    if (!a(list)) {
                        return list;
                    }
                }
            } catch (Throwable th) {
                q.a(f3387c, "getImeiListFromMiui failed ex: " + th.getMessage());
            }
        }
        return null;
    }

    private static boolean k() {
        if ("dsds".equals(a("persist.radio.multisim.config"))) {
            return true;
        }
        String str = Build.DEVICE;
        return "lcsh92_wet_jb9".equals(str) || "lcsh92_wet_tdd".equals(str) || "HM2013022".equals(str) || "HM2013023".equals(str) || "armani".equals(str) || "HM2014011".equals(str) || "HM2014012".equals(str);
    }

    private static boolean l() {
        return false;
    }

    @SuppressLint({"MissingPermission"})
    private static List<String> m(Context context) {
        try {
            ArrayList arrayList = new ArrayList();
            Class<?> cls = Class.forName("android.telephony.TelephonyManager");
            if (!k()) {
                String deviceId = ((TelephonyManager) cls.getMethod("getDefault", null).invoke(null, null)).getDeviceId();
                if (b(deviceId)) {
                    arrayList.add(deviceId);
                }
                return arrayList;
            }
            Class cls2 = Integer.TYPE;
            String deviceId2 = ((TelephonyManager) cls.getMethod("getDefault", cls2).invoke(null, 0)).getDeviceId();
            String deviceId3 = ((TelephonyManager) cls.getMethod("getDefault", cls2).invoke(null, 1)).getDeviceId();
            if (b(deviceId2)) {
                arrayList.add(deviceId2);
            }
            if (b(deviceId3)) {
                arrayList.add(deviceId3);
            }
            return arrayList;
        } catch (Throwable th) {
            q.a(f3387c, "getImeiListBelowLollipop failed ex: " + th.getMessage());
            return null;
        }
    }

    private static boolean n() {
        String strA = ac.a("ro.product.brand");
        return !TextUtils.isEmpty(strA) && strA.contains("XiaoAiTongXue");
    }

    private static List<String> l(Context context) {
        if (f3401q == null) {
            return null;
        }
        try {
            ArrayList arrayList = new ArrayList();
            TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService(au.f2924d);
            String str = (String) f3401q.invoke(telephonyManager, 0);
            if (b(str)) {
                arrayList.add(str);
            }
            if (k()) {
                String str2 = (String) f3401q.invoke(telephonyManager, 1);
                if (b(str2)) {
                    arrayList.add(str2);
                }
            }
            return arrayList;
        } catch (Throwable th) {
            q.a(f3387c, "getImeiListAboveLollipop failed ex: " + th.getMessage());
            return null;
        }
    }

    public static String j(Context context) {
        return com.xiaomi.onetrack.util.oaid.a.a().a(context);
    }

    public static void a() {
        f3405u = null;
    }

    private static boolean b(String str) {
        return (str == null || str.length() != 15 || str.matches("^0*$")) ? false : true;
    }

    private static boolean c(String str) {
        return str != null && str.length() >= 6 && str.length() <= 15 && !str.matches("^0*$");
    }

    public static String d() {
        return Build.MANUFACTURER;
    }

    public static void e() {
        com.xiaomi.onetrack.util.oaid.a.a().b();
    }

    public static boolean i() {
        String strB = a.b();
        return f3391g.equals(strB) || f3388d.equals(strB) || f3389e.equals(strB) || f3390f.equals(strB);
    }

    public static String k(Context context) {
        try {
            if (aa.b(f3408x)) {
                return f3408x;
            }
            String string = Settings.System.getString(context.getContentResolver(), ah.f2795A);
            if (aa.b(string) && !f3410z.equals(string)) {
                f3408x = string;
                return string;
            }
            return "";
        } catch (Throwable th) {
            q.b(f3387c, "getandroid d throwable:" + th.getMessage());
            return "";
        }
    }

    private static String a(String str) {
        try {
            Method method = f3398n;
            if (method != null) {
                return String.valueOf(method.invoke(null, str));
            }
        } catch (Throwable th) {
            q.a(f3387c, "getProp failed ex: " + th.getMessage());
        }
        return null;
    }

    private static String[] b(Class<?> cls, Class<?> cls2, TelephonyManager telephonyManager, SubscriptionManager subscriptionManager) {
        String[] strArr = new String[2];
        try {
            Class cls3 = Integer.TYPE;
            int[] iArr = (int[]) cls2.getMethod("getSubscriptionIds", cls3).invoke(subscriptionManager, 0);
            if (iArr != null) {
                strArr[0] = (String) cls.getMethod("getSubscriberId", cls3).invoke(telephonyManager, Integer.valueOf(iArr[0]));
            }
        } catch (Throwable th) {
            q.b(f3387c, "get imsi1 above Android Q exception:" + th.getMessage());
        }
        try {
            Class cls4 = Integer.TYPE;
            int[] iArr2 = (int[]) cls2.getMethod("getSubscriptionIds", cls4).invoke(subscriptionManager, 1);
            if (iArr2 != null) {
                strArr[1] = (String) cls.getMethod("getSubscriberId", cls4).invoke(telephonyManager, Integer.valueOf(iArr2[0]));
            }
        } catch (Throwable th2) {
            q.b(f3387c, "get imsi2 above Android Q exception:" + th2.getMessage());
        }
        return strArr;
    }

    public static String c() {
        return a("ro.product.marketname");
    }

    public static boolean f() {
        return com.xiaomi.onetrack.util.oaid.a.a().c();
    }

    private static boolean a(List<String> list) {
        Iterator<String> it = list.iterator();
        while (it.hasNext()) {
            if (!b(it.next())) {
                return true;
            }
        }
        return false;
    }

    public static class GAIDClient {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private static final String f3411a = "GAIDClient";

        public static final class AdvertisingConnection implements ServiceConnection {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            private static final int f3412a = 30000;

            /* JADX INFO: renamed from: b, reason: collision with root package name */
            private boolean f3413b;

            /* JADX INFO: renamed from: c, reason: collision with root package name */
            private IBinder f3414c;

            private AdvertisingConnection() {
                this.f3413b = false;
            }

            public IBinder a() {
                IBinder iBinder = this.f3414c;
                if (iBinder != null) {
                    return iBinder;
                }
                if (iBinder == null && !this.f3413b) {
                    synchronized (this) {
                        try {
                            wait(30000L);
                            if (this.f3414c == null) {
                                throw new InterruptedException("Not connect or connect timeout to google play service");
                            }
                        } finally {
                        }
                    }
                }
                return this.f3414c;
            }

            @Override // android.content.ServiceConnection
            public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
                synchronized (this) {
                    this.f3414c = iBinder;
                    notifyAll();
                }
            }

            @Override // android.content.ServiceConnection
            public void onServiceDisconnected(ComponentName componentName) {
                this.f3413b = true;
                this.f3414c = null;
            }
        }

        private GAIDClient() {
        }

        public static String a(Context context) {
            if (!c(context)) {
                q.a(f3411a, "Google play service is not available");
                return "";
            }
            AdvertisingConnection advertisingConnection = new AdvertisingConnection();
            try {
                Intent intent = new Intent("com.google.android.gms.ads.identifier.service.START");
                intent.setPackage("com.google.android.gms");
                if (context.bindService(intent, advertisingConnection, 1)) {
                    return new a(advertisingConnection.a()).a();
                }
            } finally {
                try {
                } finally {
                }
            }
            return "";
        }

        public static boolean b(Context context) {
            if (!c(context)) {
                q.a(f3411a, "Google play service is not available");
                return false;
            }
            AdvertisingConnection advertisingConnection = new AdvertisingConnection();
            try {
                Intent intent = new Intent("com.google.android.gms.ads.identifier.service.START");
                intent.setPackage("com.google.android.gms");
                if (context.bindService(intent, advertisingConnection, 1)) {
                    return new a(advertisingConnection.a()).a(true);
                }
            } finally {
                try {
                } finally {
                }
            }
            return false;
        }

        private static boolean c(Context context) {
            try {
                context.getPackageManager().getPackageInfo("com.android.vending", 16384);
                return true;
            } catch (PackageManager.NameNotFoundException unused) {
                return false;
            }
        }

        public static final class a implements IInterface {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            private IBinder f3415a;

            public a(IBinder iBinder) {
                this.f3415a = iBinder;
            }

            public String a() {
                if (this.f3415a == null) {
                    return "";
                }
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("com.google.android.gms.ads.identifier.internal.IAdvertisingIdService");
                    this.f3415a.transact(1, parcelObtain, parcelObtain2, 0);
                    parcelObtain2.readException();
                    return parcelObtain2.readString();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.f3415a;
            }

            public boolean a(boolean z2) {
                if (this.f3415a == null) {
                    return false;
                }
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("com.google.android.gms.ads.identifier.internal.IAdvertisingIdService");
                    parcelObtain.writeInt(z2 ? 1 : 0);
                    this.f3415a.transact(2, parcelObtain, parcelObtain2, 0);
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }
        }
    }

    public static boolean g() {
        if (f3384B == null) {
            String strB = b();
            if (aa.b(strB)) {
                String upperCase = strB.toUpperCase();
                if (upperCase.contains("MITV") || upperCase.contains("MIBOX") || upperCase.contains("PROJECTOR")) {
                    Boolean bool = Boolean.TRUE;
                    f3384B = bool;
                    return bool.booleanValue();
                }
            }
            f3384B = Boolean.FALSE;
        }
        return f3384B.booleanValue();
    }

    private static String[] a(Class<?> cls, Class<?> cls2, TelephonyManager telephonyManager, SubscriptionManager subscriptionManager) {
        Method method;
        Object obj;
        String[] strArr = new String[2];
        try {
            Class cls3 = Integer.TYPE;
            String str = (String) cls.getMethod("getSubscriberId", cls3).invoke(telephonyManager, Integer.valueOf(((int[]) cls2.getMethod("getSubId", cls3).invoke(subscriptionManager, 0))[0]));
            strArr[0] = str;
            if (c(str) || (method = f3402r) == null || (obj = f3400p) == null) {
                strArr[1] = (String) cls.getMethod("getSubscriberId", cls3).invoke(telephonyManager, Integer.valueOf(((int[]) cls2.getMethod("getSubId", cls3).invoke(subscriptionManager, 1))[0]));
            } else {
                strArr[0] = (String) method.invoke(obj, 0);
                strArr[1] = (String) f3402r.invoke(f3400p, 1);
            }
        } catch (Throwable th) {
            q.a(f3387c, "getImsiFromLToP: " + th.getMessage());
        }
        return strArr;
    }

    public static String b() {
        return Build.MODEL;
    }

    private static boolean m() {
        try {
            return ((Boolean) Class.forName("miui.os.Build").getField("IS_TABLET").get(null)).booleanValue();
        } catch (Throwable th) {
            q.c(f3387c, "get IS_TABLET from miui.os.Build exception: " + th.getMessage());
            try {
                return k.a("is_pad", false);
            } catch (Throwable th2) {
                q.c(f3387c, "get is_pad from FeatureParser exception: " + th2.getMessage());
                return false;
            }
        }
    }

    public static String h() {
        if (TextUtils.isEmpty(f3383A)) {
            String strB = a.b();
            f3383A = strB;
            if (!TextUtils.isEmpty(strB)) {
                return f3383A;
            }
            if (m()) {
                f3383A = f3392h;
                return f3392h;
            }
            if (n()) {
                f3383A = f3393i;
                return f3393i;
            }
            if (r.b()) {
                f3383A = f3394j;
                return f3394j;
            }
            f3383A = f3395k;
            return f3395k;
        }
        return f3383A;
    }
}
