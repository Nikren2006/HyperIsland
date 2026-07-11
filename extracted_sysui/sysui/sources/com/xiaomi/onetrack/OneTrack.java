package com.xiaomi.onetrack;

import android.content.Context;
import android.content.Intent;
import com.xiaomi.onetrack.api.ah;
import com.xiaomi.onetrack.api.an;
import com.xiaomi.onetrack.c.j;
import com.xiaomi.onetrack.f.a;
import com.xiaomi.onetrack.util.aa;
import com.xiaomi.onetrack.util.i;
import com.xiaomi.onetrack.util.q;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import miui.systemui.notification.focus.Const;
import miuix.android.content.MiuiIntent;

/* JADX INFO: loaded from: classes2.dex */
public class OneTrack {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static boolean f2632a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private static volatile int f2633b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private static boolean f2634c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private static boolean f2635d;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private static HttpRequestProperty f2636f;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private an f2637e;

    public enum HttpRequestProperty {
        KEEP_ALIVE("keep-alive"),
        CLOSE(Const.Param.REOPEN_FALSE);


        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private String f2640a;

        HttpRequestProperty(String str) {
            this.f2640a = str;
        }

        public String getType() {
            return this.f2640a;
        }
    }

    public interface ICommonPropertyProvider {
        Map<String, Object> getDynamicProperty(String str);
    }

    public interface IEventHook {
        boolean isCustomDauEvent(String str);

        boolean isRecommendEvent(String str);
    }

    public enum Mode {
        APP("app"),
        PLUGIN("plugin"),
        SDK("sdk");


        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private String f2642a;

        Mode(String str) {
            this.f2642a = str;
        }

        public String getType() {
            return this.f2642a;
        }
    }

    public enum NetType {
        NOT_CONNECTED("NONE"),
        MOBILE_2G("2G"),
        MOBILE_3G("3G"),
        MOBILE_4G("4G"),
        MOBILE_5G("5G"),
        WIFI(MiuiIntent.WIFI_NAME),
        ETHERNET("ETHERNET"),
        UNKNOWN("UNKNOWN"),
        CONNECTED("CONNECTED");


        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private String f2644a;

        NetType(String str) {
            this.f2644a = str;
        }

        @Override // java.lang.Enum
        public String toString() {
            return this.f2644a;
        }
    }

    public enum UserIdType {
        XIAOMI("xiaomi"),
        PHONE_NUMBER("phone_number"),
        WEIXIN("weixin"),
        WEIBO("weibo"),
        QQ("qq"),
        OTHER("other");


        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private String f2646a;

        UserIdType(String str) {
            this.f2646a = str;
        }

        public String getUserIdType() {
            return this.f2646a;
        }
    }

    private OneTrack(Context context, Configuration configuration) {
        a.a(context.getApplicationContext());
        this.f2637e = new an(context, configuration);
        setEventHook(new DefaultEventHook());
    }

    private static void a(Context context) {
        if (context == null) {
            throw new IllegalStateException("context is null!");
        }
        a.a(context.getApplicationContext());
    }

    public static OneTrack createInstance(Context context, Configuration configuration) {
        return new OneTrack(context, configuration);
    }

    public static int getGlobalBasicModeEnable() {
        return f2633b;
    }

    public static HttpRequestProperty getHttpReqPropConnection() {
        return f2636f;
    }

    public static boolean isDisable() {
        return f2632a;
    }

    public static boolean isRestrictGetNetworkInfo() {
        return f2635d;
    }

    public static boolean isUseSystemNetTrafficOnly() {
        return f2634c;
    }

    public static void registerCrashHook(Context context) {
        CrashAnalysis.a(context);
    }

    public static String sdkVersion() {
        return BuildConfig.SDK_VERSION;
    }

    public static void setAccessNetworkEnable(Context context, final boolean z2) {
        a(context);
        i.a(new Runnable() { // from class: com.xiaomi.onetrack.OneTrack.1
            @Override // java.lang.Runnable
            public void run() {
                j.a(z2);
                j.b(z2);
            }
        });
    }

    public static void setDebugMode(boolean z2) {
        q.a(z2);
    }

    public static void setDisable(boolean z2) {
        f2632a = z2;
    }

    public static void setGlobalBasicModeEnable(boolean z2) {
        if (z2) {
            f2633b = 2;
        } else {
            f2633b = 1;
        }
    }

    public static void setHttpReqPropConnection(HttpRequestProperty httpRequestProperty) {
        f2636f = httpRequestProperty;
    }

    public static void setRestrictGetNetworkInfo(boolean z2) {
        f2635d = z2;
    }

    public static void setTestMode(boolean z2) {
        q.b(z2);
    }

    public static void setUseSystemNetTrafficOnly() {
        f2634c = true;
    }

    public void adTrack(String str, Map<String, Object> map) {
        this.f2637e.a(str, map, (List<String>) null);
    }

    public String appActiveBroadcast(Intent intent) {
        return this.f2637e.a(intent);
    }

    public void clearCommonProperty() {
        this.f2637e.b();
    }

    public String getAndroidId(Context context) {
        return this.f2637e.d(context);
    }

    public String getInstanceId() {
        return this.f2637e.d();
    }

    public String getOAID(Context context) {
        return this.f2637e.c(context);
    }

    public boolean hasMemoryOaid() {
        return this.f2637e.f();
    }

    public void login(String str, UserIdType userIdType, Map<String, Object> map, boolean z2) {
        this.f2637e.a(str, userIdType, map, z2);
    }

    public void logout() {
        logout(null, false);
    }

    public void refreshGetOaidTime() {
        this.f2637e.e();
    }

    public void removeCommonProperty(String str) {
        this.f2637e.b(str);
    }

    public void setBasicModeEnable(boolean z2) {
        this.f2637e.c(z2);
    }

    public void setCloseOaidDependMsaSDK(boolean z2) {
        this.f2637e.d(z2);
    }

    public void setCommonProperty(Map<String, Object> map) {
        this.f2637e.c(map);
    }

    public void setCustomPrivacyPolicyAccepted(boolean z2) {
        this.f2637e.b(z2);
    }

    public void setDynamicCommonProperty(ICommonPropertyProvider iCommonPropertyProvider) {
        this.f2637e.a(iCommonPropertyProvider);
    }

    public void setEventHook(IEventHook iEventHook) {
        this.f2637e.a(iEventHook);
    }

    public void setInstanceId(String str) {
        this.f2637e.c(str);
    }

    public void setUserProfile(Map<String, Object> map) {
        this.f2637e.a(map);
    }

    public void track(String str, Map<String, Object> map) {
        this.f2637e.a(str, map);
    }

    public void trackEventFromH5(String str) {
        this.f2637e.a(str);
    }

    public void trackPluginEvent(String str, String str2, Map<String, Object> map) {
        this.f2637e.a(str, str2, map);
    }

    public void trackServiceQualityEvent(ServiceQualityEvent serviceQualityEvent) {
        this.f2637e.a(serviceQualityEvent);
    }

    public void userProfileIncrement(Map<String, ? extends Number> map) {
        this.f2637e.b(map);
    }

    public void adTrack(String str, Map<String, Object> map, List<String> list) {
        this.f2637e.a(str, map, list);
    }

    public void login(String str, UserIdType userIdType, Map<String, Object> map) {
        login(str, userIdType, map, false);
    }

    public void logout(Map<String, Object> map, boolean z2) {
        this.f2637e.a(map, z2);
    }

    public void setUserProfile(String str, Object obj) {
        this.f2637e.a(str, obj);
    }

    public void track(String str, List<String> list, Map<String, Object> map) {
        HashMap map2 = new HashMap();
        if (map != null) {
            map2.putAll(map);
        }
        map2.put(ah.ac, String.join(aa.f3429b, list));
        this.f2637e.a(str, (Map<String, Object>) map2);
    }

    public void userProfileIncrement(String str, Number number) {
        this.f2637e.a(str, number);
    }
}
