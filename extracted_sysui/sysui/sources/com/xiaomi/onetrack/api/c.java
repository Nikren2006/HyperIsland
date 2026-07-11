package com.xiaomi.onetrack.api;

import android.app.Application;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.util.Log;
import com.xiaomi.onetrack.Configuration;
import com.xiaomi.onetrack.OnMainThreadException;
import com.xiaomi.onetrack.OneTrack;
import com.xiaomi.onetrack.ServiceQualityEvent;
import com.xiaomi.onetrack.util.DeviceUtil;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes2.dex */
public abstract class c {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    protected static ExecutorService f2950c = null;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    private static final String f2951k = "BaseOneTrackImp";

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    protected ak f2952a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    protected ak f2953b;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    protected Context f2954d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    protected al f2955e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    protected Configuration f2956f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    protected OneTrack.ICommonPropertyProvider f2957g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    protected OneTrack.IEventHook f2958h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    protected com.xiaomi.onetrack.util.w f2959i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    protected boolean f2960j = false;

    public c(Context context, Configuration configuration) {
        Context applicationContext = context.getApplicationContext();
        this.f2954d = applicationContext;
        this.f2956f = configuration;
        e(applicationContext);
        Log.d(f2951k, "OneTrackImp init : " + configuration.toString());
        Log.d(f2951k, "OneTrackImp sdk ver : 3.0.2");
        com.xiaomi.onetrack.util.q.a(f2951k, "BuildConfig.Channel:master");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean d(String str) {
        boolean zA = com.xiaomi.onetrack.util.s.a(str);
        if (!zA) {
            com.xiaomi.onetrack.util.q.b(f2951k, String.format("Invalid eventname: %s. Eventname can only consist of numbers, letters, underscores ,and can not start with a number or \"onetrack_\" or \"ot_\"", str));
        }
        return !zA;
    }

    private void e(Context context) {
        com.xiaomi.onetrack.util.r.a(this.f2956f.isInternational(), this.f2956f.getRegion(), this.f2956f.getMode());
        if (f2950c == null) {
            f2950c = new ThreadPoolExecutor(0, 1, 60L, TimeUnit.SECONDS, new LinkedBlockingQueue());
        }
        this.f2959i = new com.xiaomi.onetrack.util.w(this.f2956f);
        a(context);
        if (this.f2956f.getMode() == OneTrack.Mode.APP) {
            com.xiaomi.onetrack.util.r.a(this.f2956f.isOverrideMiuiRegionSetting());
            b(context);
            if (this.f2956f.isExceptionCatcherEnable()) {
                com.xiaomi.onetrack.util.i.a(new d(this, context));
            }
        }
        f2950c.execute(new o(this));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public JSONObject f(String str) throws Throwable {
        try {
            OneTrack.ICommonPropertyProvider iCommonPropertyProvider = this.f2957g;
            JSONObject jSONObjectA = com.xiaomi.onetrack.util.s.a(iCommonPropertyProvider != null ? iCommonPropertyProvider.getDynamicProperty(str) : null, false);
            String strA = com.xiaomi.onetrack.util.l.a(com.xiaomi.onetrack.util.s.a(this.f2956f));
            return com.xiaomi.onetrack.util.s.a(jSONObjectA, !TextUtils.isEmpty(strA) ? new JSONObject(strA) : null);
        } catch (Exception e2) {
            com.xiaomi.onetrack.util.q.b(f2951k, "getCommonProperty failed, e: " + e2.getMessage());
            return null;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void l() {
        f2950c.execute(new j(this));
    }

    private void m() {
        f2950c.execute(new t(this));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void n() {
        if (com.xiaomi.onetrack.c.j.d()) {
            f2950c.execute(new u(this));
        }
    }

    private static boolean o() {
        try {
            int i2 = com.xiaomi.onetrack.f.a.b().getPackageManager().getPackageInfo(at.f2905a, 0).versionCode;
            if (i2 >= 2020062900) {
                return true;
            }
            com.xiaomi.onetrack.util.q.a(f2951k, "system analytics version: " + i2);
        } catch (Throwable th) {
            com.xiaomi.onetrack.util.q.b(f2951k, "isSupportEmptyEvent error:" + th.getMessage());
        }
        return false;
    }

    private boolean p() {
        try {
            if (TextUtils.isEmpty(this.f2956f.getAdEventAppId()) || OneTrack.isUseSystemNetTrafficOnly()) {
                return true;
            }
            int i2 = com.xiaomi.onetrack.f.a.b().getPackageManager().getPackageInfo(at.f2905a, 0).versionCode;
            com.xiaomi.onetrack.util.q.a(f2951k, "system analytics version: " + i2);
            return i2 >= 2022042900;
        } catch (Throwable th) {
            com.xiaomi.onetrack.util.q.b(f2951k, "isSupportAdMonitor error:" + th.getMessage());
            return true;
        }
    }

    public abstract void a(Context context);

    public void g() {
        try {
            if (!h() && this.f2956f.getMode() == OneTrack.Mode.APP) {
                long jD = com.xiaomi.onetrack.f.a.d();
                String strA = a(jD, com.xiaomi.onetrack.f.a.c());
                String strA2 = com.xiaomi.onetrack.util.ab.A();
                if (TextUtils.isEmpty(strA2)) {
                    com.xiaomi.onetrack.util.ab.j(strA);
                    return;
                }
                JSONObject jSONObject = new JSONObject(strA2);
                long jOptLong = jSONObject.optLong(ah.f2817W);
                String strOptString = jSONObject.optString(ah.f2818X);
                if (jOptLong != jD) {
                    com.xiaomi.onetrack.util.ab.j(strA);
                    i().a(ah.f2830j, ai.a(jOptLong, strOptString, jD, com.xiaomi.onetrack.f.a.f(), this.f2956f, this.f2958h, this.f2959i, this.f2960j));
                }
            }
        } catch (Exception e2) {
            com.xiaomi.onetrack.util.q.b(f2951k, "trackUpgradeEvent error: " + e2.toString());
        }
    }

    public boolean h() {
        if (!OneTrack.isDisable()) {
            return false;
        }
        com.xiaomi.onetrack.util.q.a(f2951k, "isDisable is true,Not allowed Track");
        return true;
    }

    public ak i() {
        return this.f2952a;
    }

    public boolean j() {
        if (com.xiaomi.onetrack.util.q.f3627a) {
            com.xiaomi.onetrack.util.q.a(f2951k, "enable:" + k() + " isSupportEmptyEvent: " + o() + "_isSupportAdMonitor():" + p());
        }
        return k() && o() && p();
    }

    public boolean k() {
        try {
            int componentEnabledSetting = com.xiaomi.onetrack.f.a.b().getPackageManager().getComponentEnabledSetting(new ComponentName(at.f2905a, at.f2906b));
            return componentEnabledSetting == 1 || componentEnabledSetting == 0;
        } catch (Exception e2) {
            com.xiaomi.onetrack.util.q.b(f2951k, "enable error:" + e2.toString());
            return false;
        }
    }

    public void c(Map<String, Object> map) {
        if (map == null) {
            return;
        }
        f2950c.execute(new n(this, map));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public JSONObject d(Map<String, Object> map) throws Throwable {
        try {
            JSONObject jSONObjectA = com.xiaomi.onetrack.util.s.a(map, false);
            String strA = com.xiaomi.onetrack.util.l.a(com.xiaomi.onetrack.util.s.a(this.f2956f));
            return com.xiaomi.onetrack.util.s.a(jSONObjectA, !TextUtils.isEmpty(strA) ? new JSONObject(strA) : null);
        } catch (Exception e2) {
            com.xiaomi.onetrack.util.q.b(f2951k, "getCommonPropertyForException failed, e: " + e2.getMessage());
            return null;
        }
    }

    public void b(Map<String, ? extends Number> map) {
        f2950c.execute(new f(this, map));
    }

    public OneTrack.ICommonPropertyProvider c() {
        return this.f2957g;
    }

    public void b(Context context) {
        ((Application) context).registerActivityLifecycleCallbacks(new i(this));
    }

    public void c(String str) {
        f2950c.execute(new r(this, str));
    }

    public void b() {
        f2950c.execute(new p(this));
    }

    public String c(Context context) throws OnMainThreadException {
        if (!com.xiaomi.onetrack.util.x.a()) {
            return DeviceUtil.j(context);
        }
        throw new OnMainThreadException("Can't call this method on main thread");
    }

    public boolean a() {
        if (this.f2956f.isOverrideMiuiRegionSetting()) {
            return TextUtils.equals(com.xiaomi.onetrack.util.r.m(), this.f2956f.getRegion());
        }
        return true;
    }

    public void b(String str) {
        f2950c.execute(new q(this, str));
    }

    public void b(boolean z2) {
        if (this.f2956f.isUseCustomPrivacyPolicy()) {
            f2950c.execute(new v(this, z2));
        }
    }

    public void a(String str, String str2, Map<String, Object> map) {
        f2950c.execute(new w(this, str2, map, str));
    }

    public void c(boolean z2) {
        this.f2960j = z2;
    }

    public boolean f() {
        return DeviceUtil.f();
    }

    public void a(String str, Map<String, Object> map) {
        f2950c.execute(new x(this, str, map));
    }

    public String d() throws OnMainThreadException {
        if (!com.xiaomi.onetrack.util.x.a()) {
            return com.xiaomi.onetrack.util.p.a().b();
        }
        throw new OnMainThreadException("Can't call this method on main thread");
    }

    public void a(String str, Map<String, Object> map, List<String> list) {
        f2950c.execute(new y(this, str, map, list));
    }

    public void a(String str) {
        f2950c.execute(new z(this, str));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e(boolean z2) {
        f2950c.execute(new m(this, z2));
    }

    public void a(String str, String str2, String str3, String str4, String str5, long j2, Map<String, Object> map) {
        f2950c.execute(new aa(this, map, str, str2, str3, str5, str4, j2));
    }

    public String d(Context context) throws OnMainThreadException {
        if (!com.xiaomi.onetrack.util.x.a()) {
            return DeviceUtil.k(context);
        }
        throw new OnMainThreadException("Can't call this method on main thread");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean e(String str) {
        if ("onetrack_dau".equals(str) || ah.ae.equals(str) || "ot_login".equals(str) || "ot_logout".equals(str)) {
            return false;
        }
        boolean zA = com.xiaomi.onetrack.util.s.a(str);
        if (!zA) {
            com.xiaomi.onetrack.util.q.b(f2951k, String.format("Invalid eventname: %s. Eventname can only consist of numbers, letters, underscores ,and can not start with a number or \"onetrack_\" or \"ot_\"", str));
        }
        return !zA;
    }

    public void a(Map<String, Object> map) {
        f2950c.execute(new ab(this, map));
    }

    public void a(String str, Object obj) {
        f2950c.execute(new ac(this, obj, str));
    }

    public void a(String str, OneTrack.UserIdType userIdType, Map<String, Object> map, boolean z2) {
        f2950c.execute(new e(this, str, userIdType, z2, map));
    }

    public void d(boolean z2) {
        com.xiaomi.onetrack.util.oaid.a.a().a(z2);
    }

    public void a(String str, Number number) {
        f2950c.execute(new g(this, str, number));
    }

    public void a(Map<String, Object> map, boolean z2) {
        f2950c.execute(new h(this, z2, map));
    }

    public void e() {
        DeviceUtil.e();
    }

    public void a(String str, boolean z2) {
        f2950c.execute(new k(this, str, z2));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(String str, long j2) {
        f2950c.execute(new l(this, str, j2));
    }

    public void a(OneTrack.ICommonPropertyProvider iCommonPropertyProvider) {
        this.f2957g = iCommonPropertyProvider;
    }

    public void a(ServiceQualityEvent serviceQualityEvent) {
        if (serviceQualityEvent == null) {
            return;
        }
        f2950c.execute(new s(this, serviceQualityEvent));
    }

    public void a(boolean z2) {
        com.xiaomi.onetrack.util.q.f3627a = z2;
    }

    private String a(long j2, String str) throws JSONException {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put(ah.f2818X, str);
        jSONObject.put(ah.f2817W, j2);
        return jSONObject.toString();
    }

    public void a(OneTrack.IEventHook iEventHook) {
        this.f2958h = iEventHook;
        this.f2959i.a(iEventHook);
    }

    public String a(Intent intent) throws OnMainThreadException {
        if (!com.xiaomi.onetrack.util.r.k()) {
            if (!com.xiaomi.onetrack.util.x.a()) {
                if (intent != null && a.a().c()) {
                    return a.a().a(intent);
                }
                com.xiaomi.onetrack.util.q.b(f2951k, "Not allowed to call,intent is null or Not specify the package name");
                return "";
            }
            throw new OnMainThreadException("Can't call this method on main thread");
        }
        com.xiaomi.onetrack.util.q.a(f2951k, "this appActiveBroadcast method is not supported in the international version");
        return "";
    }
}
