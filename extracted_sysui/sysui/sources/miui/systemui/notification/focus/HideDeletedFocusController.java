package miui.systemui.notification.focus;

import H0.d;
import H0.e;
import android.os.SystemClock;
import android.service.notification.StatusBarNotification;
import android.text.TextUtils;
import android.util.Log;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.n;
import miui.systemui.notification.NotificationSettingsManager;
import miui.systemui.notification.NotificationUtil;
import miui.systemui.notification.focus.Const;
import miui.systemui.util.AlarmScheduler;

/* JADX INFO: loaded from: classes4.dex */
public final class HideDeletedFocusController {
    public static final String ACTION_NOTIFICATION_TIMEOUT = "miui.intent.action.FOCUS_NOTIFICATION_DELETE_TIMEOUT";
    public static final Companion Companion = new Companion(null);
    public static final String TAG = "HideDeletedFocusController";
    public static final int TIMEOUT = 86400000;
    public static final int TIMEOUT_DEBUG = 60000;
    private final d alarmScheduler$delegate;
    private Map<String, Boolean> mDeletedNotifs;
    private final NotificationSettingsManager notificationSettingsManager;

    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }

    public HideDeletedFocusController(AlarmScheduler.Factory alarmSchedulerFactory, NotificationSettingsManager notificationSettingsManager) {
        n.g(alarmSchedulerFactory, "alarmSchedulerFactory");
        n.g(notificationSettingsManager, "notificationSettingsManager");
        this.notificationSettingsManager = notificationSettingsManager;
        this.alarmScheduler$delegate = e.b(new HideDeletedFocusController$alarmScheduler$2(alarmSchedulerFactory, this));
        this.mDeletedNotifs = new LinkedHashMap<String, Boolean>() { // from class: miui.systemui.notification.focus.HideDeletedFocusController$mDeletedNotifs$1
            @Override // java.util.HashMap, java.util.AbstractMap, java.util.Map
            public final /* bridge */ boolean containsKey(Object obj) {
                if (obj == null ? true : obj instanceof String) {
                    return containsKey((String) obj);
                }
                return false;
            }

            public /* bridge */ boolean containsValue(Boolean bool) {
                return super.containsValue((Object) bool);
            }

            @Override // java.util.LinkedHashMap, java.util.HashMap, java.util.AbstractMap, java.util.Map
            public final /* bridge */ Set<Map.Entry<String, Boolean>> entrySet() {
                return getEntries();
            }

            @Override // java.util.LinkedHashMap, java.util.HashMap, java.util.AbstractMap, java.util.Map
            public final /* bridge */ Boolean get(Object obj) {
                if (obj == null ? true : obj instanceof String) {
                    return get((String) obj);
                }
                return null;
            }

            public /* bridge */ Set<Map.Entry<String, Boolean>> getEntries() {
                return super.entrySet();
            }

            public /* bridge */ Set<String> getKeys() {
                return super.keySet();
            }

            public final /* bridge */ Boolean getOrDefault(Object obj, Boolean bool) {
                return !(obj == null ? true : obj instanceof String) ? bool : getOrDefault((String) obj, bool);
            }

            public /* bridge */ int getSize() {
                return super.size();
            }

            public /* bridge */ Collection<Boolean> getValues() {
                return super.values();
            }

            @Override // java.util.LinkedHashMap, java.util.HashMap, java.util.AbstractMap, java.util.Map
            public final /* bridge */ Set<String> keySet() {
                return getKeys();
            }

            @Override // java.util.HashMap, java.util.AbstractMap, java.util.Map
            public final /* bridge */ Boolean remove(Object obj) {
                if (obj == null ? true : obj instanceof String) {
                    return remove((String) obj);
                }
                return null;
            }

            @Override // java.util.LinkedHashMap
            public boolean removeEldestEntry(Map.Entry<String, Boolean> entry) {
                boolean z2 = size() > 50;
                if (z2) {
                    if ((entry != null ? entry.getKey() : null) != null) {
                        this.this$0.cancelAlarm(entry.getKey());
                    }
                }
                return z2;
            }

            @Override // java.util.HashMap, java.util.AbstractMap, java.util.Map
            public final /* bridge */ int size() {
                return getSize();
            }

            @Override // java.util.LinkedHashMap, java.util.HashMap, java.util.AbstractMap, java.util.Map
            public final /* bridge */ Collection<Boolean> values() {
                return getValues();
            }

            public /* bridge */ boolean containsKey(String str) {
                return super.containsKey((Object) str);
            }

            @Override // java.util.LinkedHashMap, java.util.HashMap, java.util.AbstractMap, java.util.Map
            public final /* bridge */ boolean containsValue(Object obj) {
                if (obj == null ? true : obj instanceof Boolean) {
                    return containsValue((Boolean) obj);
                }
                return false;
            }

            public /* bridge */ Boolean get(String str) {
                return (Boolean) super.get((Object) str);
            }

            public /* bridge */ Boolean getOrDefault(String str, Boolean bool) {
                return (Boolean) super.getOrDefault((Object) str, bool);
            }

            public /* bridge */ Boolean remove(String str) {
                return (Boolean) super.remove((Object) str);
            }

            @Override // java.util.LinkedHashMap, java.util.HashMap, java.util.AbstractMap, java.util.Map
            public final /* bridge */ /* synthetic */ Object get(Object obj) {
                if (obj == null ? true : obj instanceof String) {
                    return get((String) obj);
                }
                return null;
            }

            @Override // java.util.LinkedHashMap, java.util.HashMap, java.util.Map
            public final /* bridge */ /* synthetic */ Object getOrDefault(Object obj, Object obj2) {
                return !(obj == null ? true : obj instanceof String) ? obj2 : getOrDefault((String) obj, (Boolean) obj2);
            }

            @Override // java.util.HashMap, java.util.AbstractMap, java.util.Map
            public final /* bridge */ /* synthetic */ Object remove(Object obj) {
                if (obj == null ? true : obj instanceof String) {
                    return remove((String) obj);
                }
                return null;
            }

            @Override // java.util.HashMap, java.util.Map
            public final /* bridge */ boolean remove(Object obj, Object obj2) {
                if (!(obj == null ? true : obj instanceof String)) {
                    return false;
                }
                if (obj2 != null ? obj2 instanceof Boolean : true) {
                    return remove((String) obj, (Boolean) obj2);
                }
                return false;
            }

            public /* bridge */ boolean remove(String str, Boolean bool) {
                return super.remove((Object) str, (Object) bool);
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void cancelAlarm(String str) {
        if (str != null) {
            getAlarmScheduler().cancelAlarm(str);
        }
    }

    private final AlarmScheduler getAlarmScheduler() {
        return (AlarmScheduler) this.alarmScheduler$delegate.getValue();
    }

    private final void scheduleFocusDeletedTimeout(StatusBarNotification statusBarNotification) {
        Log.i(TAG, "scheduleFocusDeletedTimeout: entry.getKey() " + statusBarNotification.getKey() + " entry.getKey().hashCode() " + statusBarNotification.getKey().hashCode());
        int i2 = NotificationUtil.DEBUG ? 60000 : 86400000;
        if (statusBarNotification.getKey() != null) {
            AlarmScheduler alarmScheduler = getAlarmScheduler();
            String key = statusBarNotification.getKey();
            n.f(key, "getKey(...)");
            alarmScheduler.addAlarm(key, SystemClock.elapsedRealtime() + ((long) i2));
        }
    }

    public final Map<String, Boolean> getMDeletedNotifs() {
        return this.mDeletedNotifs;
    }

    public final void onNotificationRemoved(StatusBarNotification statusBarNotification, Integer num) {
        if (statusBarNotification != null) {
            if (num != null && num.intValue() == 12) {
                return;
            }
            Log.d(TAG, "onNotificationRemoved " + num + " " + statusBarNotification.getKey());
            if (num == null || num.intValue() != 2) {
                this.mDeletedNotifs.remove(statusBarNotification.getKey());
                cancelAlarm(statusBarNotification.getKey());
                return;
            }
            if (!this.notificationSettingsManager.isFocusNotificationDeletedBlackList(statusBarNotification.getPackageName())) {
                if (this.mDeletedNotifs.containsKey(statusBarNotification.getKey())) {
                    return;
                }
                this.mDeletedNotifs.put(statusBarNotification.getKey(), Boolean.TRUE);
                scheduleFocusDeletedTimeout(statusBarNotification);
                return;
            }
            Log.d(TAG, "isFocusNotificationDeletedBlackList " + statusBarNotification.getPackageName() + " " + statusBarNotification.getKey());
        }
    }

    public final void setMDeletedNotifs(Map<String, Boolean> map) {
        n.g(map, "<set-?>");
        this.mDeletedNotifs = map;
    }

    public final boolean suppressDeletedFocus(StatusBarNotification sbn) {
        n.g(sbn, "sbn");
        if (this.mDeletedNotifs.containsKey(sbn.getKey()) && sbn.getNotification().extras.containsKey(Const.Param.EXTRA_FOCUS_REOPEN)) {
            String string = sbn.getNotification().extras.getString(Const.Param.EXTRA_FOCUS_REOPEN);
            Log.d(TAG, "filter out extraReopen=" + string + " " + sbn.getKey());
            if (TextUtils.equals("reopen", string)) {
                this.mDeletedNotifs.remove(sbn.getKey());
                cancelAlarm(sbn.getKey());
                return false;
            }
            if (TextUtils.equals(Const.Param.REOPEN_FALSE, string)) {
                return true;
            }
        }
        return false;
    }
}
