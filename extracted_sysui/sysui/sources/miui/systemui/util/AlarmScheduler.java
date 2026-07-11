package miui.systemui.util;

import android.annotation.SuppressLint;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Handler;
import android.util.Log;
import androidx.core.location.LocationRequestCompat;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import miui.systemui.dagger.qualifiers.Background;
import miui.systemui.dagger.qualifiers.SystemUI;

/* JADX INFO: loaded from: classes4.dex */
public final class AlarmScheduler {
    private static final Companion Companion = new Companion(null);
    private static final String EXTRA_KEY = "extra_key";
    private static final String TAG = "AlarmScheduler";
    private final Function1 alarmCallback;
    private final AlarmManager alarmManager;
    private final TreeMap<String, Long> alarmMap;
    private final AlarmScheduler$alarmReceiver$1 alarmReceiver;
    private final Context context;
    private String currentAlarmKey;
    private PendingIntent currentPendingIntent;
    private final Handler handler;

    @SuppressLint({"MissingPermission"})
    private final Runnable sendAlarmRunnable;
    private final String tag;
    private final String workAction;

    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }

    public interface Factory {
        AlarmScheduler create(String str, Function1 function1);
    }

    public AlarmScheduler(@SystemUI Context context, String workAction, @Background Handler handler, Function1 alarmCallback) {
        kotlin.jvm.internal.n.g(context, "context");
        kotlin.jvm.internal.n.g(workAction, "workAction");
        kotlin.jvm.internal.n.g(handler, "handler");
        kotlin.jvm.internal.n.g(alarmCallback, "alarmCallback");
        this.context = context;
        this.workAction = workAction;
        this.handler = handler;
        this.alarmCallback = alarmCallback;
        Object systemService = context.getSystemService("alarm");
        kotlin.jvm.internal.n.e(systemService, "null cannot be cast to non-null type android.app.AlarmManager");
        this.alarmManager = (AlarmManager) systemService;
        this.tag = "AlarmScheduler-" + f1.o.c0(workAction, ".", workAction);
        this.alarmMap = new TreeMap<>();
        this.alarmReceiver = new AlarmScheduler$alarmReceiver$1(this);
        this.sendAlarmRunnable = new Runnable() { // from class: miui.systemui.util.d
            @Override // java.lang.Runnable
            public final void run() {
                AlarmScheduler.sendAlarmRunnable$lambda$0(this.f5901a);
            }
        };
        registerReceiver();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void addAlarm$lambda$2(AlarmScheduler this$0, String key, long j2) {
        kotlin.jvm.internal.n.g(this$0, "this$0");
        kotlin.jvm.internal.n.g(key, "$key");
        this$0.alarmMap.put(key, Long.valueOf(j2));
        String str = this$0.currentAlarmKey;
        Long l2 = str != null ? this$0.alarmMap.get(str) : null;
        if (this$0.currentAlarmKey != null) {
            if (j2 >= (l2 != null ? l2.longValue() : LocationRequestCompat.PASSIVE_INTERVAL) && !kotlin.jvm.internal.n.c(this$0.currentAlarmKey, key)) {
                return;
            }
        }
        this$0.cancelCurrentAlarm();
        this$0.scheduleNextAlarm();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void cancelAlarm$lambda$5(AlarmScheduler this$0, String key) {
        kotlin.jvm.internal.n.g(this$0, "this$0");
        kotlin.jvm.internal.n.g(key, "$key");
        if (this$0.alarmMap.containsKey(key)) {
            this$0.alarmMap.remove(key);
            if (kotlin.jvm.internal.n.c(this$0.currentAlarmKey, key)) {
                this$0.cancelCurrentAlarm();
                this$0.scheduleNextAlarm();
            }
        }
    }

    private final void cancelCurrentAlarm() {
        String str = this.tag;
        String str2 = this.currentAlarmKey;
        PendingIntent pendingIntent = this.currentPendingIntent;
        Log.d(str, "cancelCurrentAlarm, key=" + str2 + ", intent=" + (pendingIntent != null ? pendingIntent.hashCode() : 0));
        this.handler.post(new Runnable() { // from class: miui.systemui.util.a
            @Override // java.lang.Runnable
            public final void run() {
                AlarmScheduler.cancelCurrentAlarm$lambda$4(this.f5890a);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void cancelCurrentAlarm$lambda$4(AlarmScheduler this$0) {
        kotlin.jvm.internal.n.g(this$0, "this$0");
        PendingIntent pendingIntent = this$0.currentPendingIntent;
        if (pendingIntent != null) {
            this$0.alarmManager.cancel(pendingIntent);
            this$0.currentPendingIntent = null;
        }
        this$0.currentAlarmKey = null;
    }

    private final PendingIntent createPendingIntent(String str, String str2) {
        Context context = this.context;
        int iHashCode = str2.hashCode();
        Intent intent = new Intent(str);
        intent.putExtra(EXTRA_KEY, str2);
        intent.setPackage(this.context.getPackageName());
        intent.addFlags(268435456);
        H0.s sVar = H0.s.f314a;
        return PendingIntent.getBroadcast(context, iHashCode, intent, 201326592);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void onAlarmTriggered(final String str) {
        Log.d(this.tag, "onAlarmTriggered, key=" + str);
        this.handler.post(new Runnable() { // from class: miui.systemui.util.f
            @Override // java.lang.Runnable
            public final void run() {
                AlarmScheduler.onAlarmTriggered$lambda$7(str, this);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onAlarmTriggered$lambda$7(String str, AlarmScheduler this$0) {
        kotlin.jvm.internal.n.g(this$0, "this$0");
        if (str != null) {
            this$0.alarmMap.remove(str);
        }
        this$0.currentAlarmKey = null;
        this$0.currentPendingIntent = null;
        this$0.scheduleNextAlarm();
    }

    private final void registerReceiver() {
        this.context.registerReceiver(this.alarmReceiver, new IntentFilter(this.workAction), 4);
    }

    private final void scheduleNextAlarm() {
        Log.d(this.tag, "scheduleNextAlarm");
        this.handler.post(new Runnable() { // from class: miui.systemui.util.e
            @Override // java.lang.Runnable
            public final void run() {
                AlarmScheduler.scheduleNextAlarm$lambda$11(this.f5902a);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void scheduleNextAlarm$lambda$11(AlarmScheduler this$0) {
        Object obj;
        kotlin.jvm.internal.n.g(this$0, "this$0");
        Set<Map.Entry<String, Long>> setEntrySet = this$0.alarmMap.entrySet();
        kotlin.jvm.internal.n.f(setEntrySet, "<get-entries>(...)");
        Iterator<T> it = setEntrySet.iterator();
        if (it.hasNext()) {
            Object next = it.next();
            if (it.hasNext()) {
                Object value = ((Map.Entry) next).getValue();
                kotlin.jvm.internal.n.f(value, "<get-value>(...)");
                long jLongValue = ((Number) value).longValue();
                do {
                    Object next2 = it.next();
                    Object value2 = ((Map.Entry) next2).getValue();
                    kotlin.jvm.internal.n.f(value2, "<get-value>(...)");
                    long jLongValue2 = ((Number) value2).longValue();
                    if (jLongValue > jLongValue2) {
                        next = next2;
                        jLongValue = jLongValue2;
                    }
                } while (it.hasNext());
            }
            obj = next;
        } else {
            obj = null;
        }
        Map.Entry entry = (Map.Entry) obj;
        if (entry != null) {
            String str = (String) entry.getKey();
            Long l2 = (Long) entry.getValue();
            String str2 = this$0.workAction;
            kotlin.jvm.internal.n.d(str);
            PendingIntent pendingIntentCreatePendingIntent = this$0.createPendingIntent(str2, str);
            Log.d(this$0.tag, "scheduleNextAlarm, key=" + str + ", triggerTime=" + l2 + ", intent=" + (pendingIntentCreatePendingIntent != null ? pendingIntentCreatePendingIntent.hashCode() : 0));
            if (pendingIntentCreatePendingIntent != null) {
                kotlin.jvm.internal.n.d(l2);
                this$0.setExactAlarm(str, l2.longValue(), pendingIntentCreatePendingIntent);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void sendAlarmRunnable$lambda$0(AlarmScheduler this$0) {
        kotlin.jvm.internal.n.g(this$0, "this$0");
        String str = this$0.currentAlarmKey;
        if (str == null) {
            Log.d(this$0.tag, "sendAlarmRunnable, currentAlarmKey is null");
            return;
        }
        Long l2 = this$0.alarmMap.get(str);
        PendingIntent pendingIntent = this$0.currentPendingIntent;
        if (l2 == null || pendingIntent == null) {
            return;
        }
        this$0.alarmManager.setExactAndAllowWhileIdle(2, l2.longValue(), pendingIntent);
    }

    @SuppressLint({"MissingPermission"})
    private final void setExactAlarm(String str, long j2, PendingIntent pendingIntent) {
        Log.d(this.tag, "setExactAlarm, key=" + str + ", triggerTime=" + j2 + ", intent=" + pendingIntent.hashCode());
        if (this.handler.hasCallbacks(this.sendAlarmRunnable)) {
            this.handler.removeCallbacks(this.sendAlarmRunnable);
        }
        this.currentAlarmKey = str;
        this.currentPendingIntent = pendingIntent;
        this.handler.post(this.sendAlarmRunnable);
    }

    public final void addAlarm(final String key, final long j2) {
        kotlin.jvm.internal.n.g(key, "key");
        Log.d(this.tag, "addAlarm, key=" + key + ", triggerTime=" + j2);
        this.handler.post(new Runnable() { // from class: miui.systemui.util.b
            @Override // java.lang.Runnable
            public final void run() {
                AlarmScheduler.addAlarm$lambda$2(this.f5891a, key, j2);
            }
        });
    }

    public final void cancelAlarm(final String key) {
        kotlin.jvm.internal.n.g(key, "key");
        Log.d(this.tag, "cancelAlarm, key=" + key + ", currentKey=" + this.currentAlarmKey);
        this.handler.post(new Runnable() { // from class: miui.systemui.util.c
            @Override // java.lang.Runnable
            public final void run() {
                AlarmScheduler.cancelAlarm$lambda$5(this.f5894a, key);
            }
        });
    }

    public final void destroy() {
        this.context.unregisterReceiver(this.alarmReceiver);
        cancelCurrentAlarm();
        this.alarmMap.clear();
    }
}
