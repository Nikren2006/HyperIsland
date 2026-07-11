package miui.systemui.notification.focus;

import android.content.Context;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.UserHandle;
import android.provider.Settings;
import android.service.notification.StatusBarNotification;
import android.text.TextUtils;
import android.util.Log;
import android.widget.RemoteViews;
import com.android.systemui.plugins.miui.notification.FocusNotificationContent;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import kotlin.jvm.internal.D;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.n;
import miui.systemui.notification.NotificationUtil;
import miui.systemui.notification.focus.Const;
import miui.systemui.notification.focus.template.FocusTemplate;
import miui.systemui.util.CommonUtils;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes4.dex */
public final class FocusNotifUtils {
    public static final Companion Companion = new Companion(null);
    public static final int MAX_SEQ_SIZE = 50;
    public static final String TAG = "FocusNotifUtils";
    private final Map<String, FocusTemplate> OS1Template = new LinkedHashMap();
    private final LinkedHashMap<String, Long> maxSeq = new LinkedHashMap<String, Long>() { // from class: miui.systemui.notification.focus.FocusNotifUtils$maxSeq$1
        @Override // java.util.HashMap, java.util.AbstractMap, java.util.Map
        public final /* bridge */ boolean containsKey(Object obj) {
            if (obj instanceof String) {
                return containsKey((String) obj);
            }
            return false;
        }

        public /* bridge */ boolean containsValue(Long l2) {
            return super.containsValue((Object) l2);
        }

        @Override // java.util.LinkedHashMap, java.util.HashMap, java.util.AbstractMap, java.util.Map
        public final /* bridge */ Set<Map.Entry<String, Long>> entrySet() {
            return getEntries();
        }

        @Override // java.util.LinkedHashMap, java.util.HashMap, java.util.AbstractMap, java.util.Map
        public final /* bridge */ Long get(Object obj) {
            if (obj instanceof String) {
                return get((String) obj);
            }
            return null;
        }

        public /* bridge */ Set<Map.Entry<String, Long>> getEntries() {
            return super.entrySet();
        }

        public /* bridge */ Set<String> getKeys() {
            return super.keySet();
        }

        public final /* bridge */ Long getOrDefault(Object obj, Long l2) {
            return !(obj instanceof String) ? l2 : getOrDefault((String) obj, l2);
        }

        public /* bridge */ int getSize() {
            return super.size();
        }

        public /* bridge */ Collection<Long> getValues() {
            return super.values();
        }

        @Override // java.util.LinkedHashMap, java.util.HashMap, java.util.AbstractMap, java.util.Map
        public final /* bridge */ Set<String> keySet() {
            return getKeys();
        }

        @Override // java.util.HashMap, java.util.AbstractMap, java.util.Map
        public final /* bridge */ Long remove(Object obj) {
            if (obj instanceof String) {
                return remove((String) obj);
            }
            return null;
        }

        @Override // java.util.LinkedHashMap
        public boolean removeEldestEntry(Map.Entry<String, Long> eldest) {
            n.g(eldest, "eldest");
            return size() > 50;
        }

        @Override // java.util.HashMap, java.util.AbstractMap, java.util.Map
        public final /* bridge */ int size() {
            return getSize();
        }

        @Override // java.util.LinkedHashMap, java.util.HashMap, java.util.AbstractMap, java.util.Map
        public final /* bridge */ Collection<Long> values() {
            return getValues();
        }

        public /* bridge */ boolean containsKey(String str) {
            return super.containsKey((Object) str);
        }

        @Override // java.util.LinkedHashMap, java.util.HashMap, java.util.AbstractMap, java.util.Map
        public final /* bridge */ boolean containsValue(Object obj) {
            if (obj instanceof Long) {
                return containsValue((Long) obj);
            }
            return false;
        }

        public /* bridge */ Long get(String str) {
            return (Long) super.get((Object) str);
        }

        public /* bridge */ Long getOrDefault(String str, Long l2) {
            return (Long) super.getOrDefault((Object) str, l2);
        }

        public /* bridge */ Long remove(String str) {
            return (Long) super.remove((Object) str);
        }

        @Override // java.util.LinkedHashMap, java.util.HashMap, java.util.AbstractMap, java.util.Map
        public final /* bridge */ /* synthetic */ Object get(Object obj) {
            if (obj instanceof String) {
                return get((String) obj);
            }
            return null;
        }

        @Override // java.util.LinkedHashMap, java.util.HashMap, java.util.Map
        public final /* bridge */ /* synthetic */ Object getOrDefault(Object obj, Object obj2) {
            return !(obj instanceof String) ? obj2 : getOrDefault((String) obj, (Long) obj2);
        }

        @Override // java.util.HashMap, java.util.AbstractMap, java.util.Map
        public final /* bridge */ /* synthetic */ Object remove(Object obj) {
            if (obj instanceof String) {
                return remove((String) obj);
            }
            return null;
        }

        @Override // java.util.HashMap, java.util.Map
        public final /* bridge */ boolean remove(Object obj, Object obj2) {
            if ((obj instanceof String) && (obj2 instanceof Long)) {
                return remove((String) obj, (Long) obj2);
            }
            return false;
        }

        public /* bridge */ boolean remove(String str, Long l2) {
            return super.remove((Object) str, (Object) l2);
        }
    };

    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }

    private final Context getContextForUser(Context context, int i2) {
        if (i2 < 0) {
            return context;
        }
        try {
            Context contextCreatePackageContextAsUser = context.createPackageContextAsUser(context.getPackageName(), 4, new UserHandle(i2));
            n.f(contextCreatePackageContextAsUser, "createPackageContextAsUser(...)");
            return contextCreatePackageContextAsUser;
        } catch (PackageManager.NameNotFoundException unused) {
            return context;
        }
    }

    private final JSONObject parseFocusParamObject(StatusBarNotification statusBarNotification, String str) {
        if (!statusBarNotification.getNotification().extras.containsKey(str)) {
            return null;
        }
        String string = statusBarNotification.getNotification().extras.getString(str, "");
        NotificationUtil.debugLog(TAG, str + " plugin param= " + CommonUtils.encodeDataToBase64(string));
        if (TextUtils.isEmpty(string)) {
            NotificationUtil.debugLog(TAG, "params is empty");
            return null;
        }
        JSONObject jSONObject = new JSONObject(string);
        NotificationUtil.debugLog(TAG, "plugin length=" + jSONObject.length());
        if (jSONObject.length() >= 1) {
            return jSONObject;
        }
        return null;
    }

    public final Bundle call(Context context, String method, Bundle bundle) {
        n.g(context, "context");
        n.g(method, "method");
        try {
            return context.getContentResolver().call(Uri.parse(Const.Provider.NOTIFICATION_PROVIDER), method, (String) null, bundle);
        } catch (Exception e2) {
            Log.e(TAG, "Error call " + e2);
            return null;
        }
    }

    public final boolean canShowFocus(Context context, String str, int i2) {
        n.g(context, "context");
        Bundle bundle = new Bundle();
        bundle.putString("package", str);
        Bundle bundleCall = call(getContextForUser(context, i2), "canShowFocus", bundle);
        boolean z2 = false;
        if (bundleCall != null && bundleCall.getBoolean("canShowFocus", false)) {
            z2 = true;
        }
        NotificationUtil.debugLog(TAG, "canShowFocus: " + z2);
        return z2;
    }

    public final LinkedHashMap<String, Long> getMaxSeq() {
        return this.maxSeq;
    }

    public final Map<String, FocusTemplate> getOS1Template() {
        return this.OS1Template;
    }

    public final int getScreenWidth(Context context) {
        n.g(context, "context");
        return context.getResources().getDisplayMetrics().widthPixels;
    }

    public final boolean hasCustomFocusView(StatusBarNotification sbn) {
        n.g(sbn, "sbn");
        try {
            return sbn.getNotification().extras.getParcelable("miui.focus.rv") instanceof RemoteViews;
        } catch (Exception unused) {
            return false;
        }
    }

    public final boolean isSupportFocusNotification(Context context) {
        n.g(context, "context");
        return Settings.System.getInt(context.getContentResolver(), Const.Provider.NOTIFICATION_FOCUS_PROTOCOL, 0) > 0;
    }

    public final void onNotificationRemoved(StatusBarNotification statusBarNotification) {
        D.d(this.OS1Template).remove(statusBarNotification != null ? statusBarNotification.getKey() : null);
    }

    public final JSONObject parseFocusParam(StatusBarNotification sbn, String type, FocusNotificationContent focusNotificationContent) {
        n.g(sbn, "sbn");
        n.g(type, "type");
        try {
            return parseFocusParamObject(sbn, type);
        } catch (JSONException e2) {
            Bundle extras = sbn.getNotification().extras;
            n.f(extras, "extras");
            this.resetAllParam(extras);
            if (focusNotificationContent != null) {
                focusNotificationContent.reset();
            }
            NotificationUtil.debugLog(TAG, e2.toString());
            e2.printStackTrace();
            return null;
        }
    }

    public final void resetAllParam(Bundle extras) {
        n.g(extras, "extras");
        resetParam(extras, "miui.focus.rv");
        resetParam(extras, Const.Param.LAYOUT_NIGHT);
        resetParam(extras, Const.Param.LAYOUT_AOD);
        resetParam(extras, Const.Param.LAYOUT_FULL_AOD);
        resetParam(extras, Const.Param.TICKER);
        resetParam(extras, Const.Param.IS_FOCUS_LAYOUT);
    }

    public final void resetParam(Bundle bundle, String param) {
        n.g(bundle, "bundle");
        n.g(param, "param");
        bundle.remove(param);
    }
}
