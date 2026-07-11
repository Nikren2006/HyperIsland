package miui.systemui.notification;

import H0.k;
import H0.s;
import I0.m;
import N0.l;
import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.service.notification.StatusBarNotification;
import android.view.View;
import androidx.collection.ArrayMap;
import com.android.systemui.plugins.miui.notification.FocusNotificationContent;
import g1.AbstractC0369g;
import g1.E;
import g1.M;
import java.util.Map;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.n;
import kotlin.jvm.internal.o;
import miui.systemui.dagger.qualifiers.Plugin;
import miui.systemui.notification.FullAodStatusManager;

/* JADX INFO: loaded from: classes4.dex */
public final class NotificationChronometerManager implements FullAodStatusManager.IFullAodStatusObserver {
    public static final String CHRONOMETER_VId = "miui.focus.chronometerId";
    public static final Companion Companion = new Companion(null);
    public static final String IS_AUTO_PROGRESS = "isAutoProgress";
    public static final String STATUS_PROGRESS_LAYOUT = "StatusProgressLayout";
    public static final String TAG = "NotificationChronometerManager";
    public static final String TIMER_CURRENT = "timerCurrent";
    public static final String TIMER_SYSTEM_CURRENT = "timerSystemCurrent";
    public static final String TIMER_TOTAL = "timerTotal";
    public static final String TIMER_TYPE = "timerType";
    public static final String TIMER_WHEN = "timerWhen";
    private final E0.a context;
    private final Handler handler;
    private boolean isFullAod;
    private final E pluginScope;
    private final Map<String, NotificationTimeKeeper> timeKeeperMap;

    /* JADX INFO: renamed from: miui.systemui.notification.NotificationChronometerManager$1, reason: invalid class name */
    @N0.f(c = "miui.systemui.notification.NotificationChronometerManager$1", f = "NotificationChronometerManager.kt", l = {51}, m = "invokeSuspend")
    public static final class AnonymousClass1 extends l implements Function2 {
        int label;

        public AnonymousClass1(L0.d dVar) {
            super(2, dVar);
        }

        @Override // N0.a
        public final L0.d create(Object obj, L0.d dVar) {
            return NotificationChronometerManager.this.new AnonymousClass1(dVar);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(E e2, L0.d dVar) {
            return ((AnonymousClass1) create(e2, dVar)).invokeSuspend(s.f314a);
        }

        @Override // N0.a
        public final Object invokeSuspend(Object obj) throws Throwable {
            Object objC = M0.c.c();
            int i2 = this.label;
            if (i2 == 0) {
                k.b(obj);
                FullAodStatusManager.INSTANCE.addObserver(NotificationChronometerManager.this);
                this.label = 1;
                if (M.a(this) == objC) {
                    return objC;
                }
            } else {
                if (i2 != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                k.b(obj);
            }
            throw new H0.c();
        }
    }

    /* JADX INFO: renamed from: miui.systemui.notification.NotificationChronometerManager$2, reason: invalid class name */
    public static final class AnonymousClass2 extends o implements Function1 {
        public AnonymousClass2() {
            super(1);
        }

        @Override // kotlin.jvm.functions.Function1
        public /* bridge */ /* synthetic */ Object invoke(Object obj) {
            invoke((Throwable) obj);
            return s.f314a;
        }

        public final void invoke(Throwable th) {
            NotificationChronometerManager.this.reset();
        }
    }

    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }

    public NotificationChronometerManager(@Plugin E pluginScope, @Plugin E0.a context) {
        n.g(pluginScope, "pluginScope");
        n.g(context, "context");
        this.pluginScope = pluginScope;
        this.context = context;
        this.handler = new Handler(Looper.getMainLooper());
        this.timeKeeperMap = new ArrayMap();
        AbstractC0369g.b(pluginScope, null, null, new AnonymousClass1(null), 3, null).l(new AnonymousClass2());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void addChronometers(FocusNotificationContent focusNotificationContent, NotificationTimeKeeper notificationTimeKeeper, int i2, boolean z2, int i3) {
        int i4;
        int i5;
        for (View view : m.j(focusNotificationContent.getDeco(), focusNotificationContent.getDecoDark(), focusNotificationContent.getDecoLand(), focusNotificationContent.getDecoLandDark(), focusNotificationContent.getFocusNotification(), focusNotificationContent.getFocusNotificationDark(), focusNotificationContent.getFocusNotificationModal(), focusNotificationContent.getFocusNotificationDarkModal(), focusNotificationContent.getIslandExpandedView(), focusNotificationContent.getIslandExpandedViewFake(), focusNotificationContent.getTinyKeyguardView(), focusNotificationContent.getTinyView(), focusNotificationContent.getTinyViewDark(), focusNotificationContent.getTinyViewDarkModal(), focusNotificationContent.getTinyViewKeyguardDark(), focusNotificationContent.getTinyViewModal())) {
            if (z2) {
                i5 = i2;
                i4 = i3;
            } else {
                i4 = 0;
                i5 = i2;
            }
            notificationTimeKeeper.addChronometerFromView(view, i5, z2, i4);
        }
        notificationTimeKeeper.setFocusNotificationIn(true);
    }

    private final TimerInfo createTimerInfo(StatusBarNotification statusBarNotification) {
        int i2 = statusBarNotification.getNotification().extras.getInt("timerType", 0);
        if (i2 == 0) {
            return null;
        }
        long j2 = statusBarNotification.getNotification().extras.getLong("timerWhen");
        return new TimerInfo(i2, Long.valueOf(j2), statusBarNotification.getNotification().extras.getLong("timerTotal", 0L), Long.valueOf(statusBarNotification.getNotification().extras.getLong("timerSystemCurrent")));
    }

    private final void notifyFullAodStatusChanged(boolean z2) {
        for (Map.Entry<String, NotificationTimeKeeper> entry : this.timeKeeperMap.entrySet()) {
            entry.getKey();
            entry.getValue().fullAodStatusChanged(z2);
        }
    }

    private final H0.n parseNotificationExtras(StatusBarNotification statusBarNotification) {
        return new H0.n(Integer.valueOf(statusBarNotification.getNotification().extras.getInt("miui.focus.chronometerId")), Boolean.valueOf(statusBarNotification.getNotification().extras.getBoolean("isAutoProgress", false)), Integer.valueOf(statusBarNotification.getNotification().extras.getInt("StatusProgressLayout")));
    }

    private final void setFullAod(boolean z2) {
        if (this.isFullAod == z2) {
            return;
        }
        this.isFullAod = z2;
        notifyFullAodStatusChanged(z2);
    }

    public final void addDynamicIslandChronometer(View view, View view2, String str, int i2) {
        if (str != null) {
            NotificationTimeKeeper notificationTimeKeeper = this.timeKeeperMap.get(str);
            if (notificationTimeKeeper == null) {
                Object obj = this.context.get();
                n.f(obj, "get(...)");
                notificationTimeKeeper = new NotificationTimeKeeper((Context) obj, this.handler);
            }
            NotificationTimeKeeper notificationTimeKeeper2 = notificationTimeKeeper;
            NotificationTimeKeeper.addChronometerFromView$default(notificationTimeKeeper2, view, i2, false, 0, 12, null);
            NotificationTimeKeeper.addChronometerFromView$default(notificationTimeKeeper2, view2, i2, false, 0, 12, null);
            notificationTimeKeeper.setDynamicIslandIn(true);
            this.timeKeeperMap.put(str, notificationTimeKeeper);
        }
    }

    @Override // miui.systemui.notification.FullAodStatusManager.IFullAodStatusObserver
    public void fullAodStatusChanged(boolean z2) {
        setFullAod(z2);
    }

    public final E0.a getContext() {
        return this.context;
    }

    public final boolean needUpdateIslandTimeInfo(String str) {
        NotificationTimeKeeper notificationTimeKeeper = this.timeKeeperMap.get(str);
        boolean z2 = false;
        if (notificationTimeKeeper != null && notificationTimeKeeper.getFocusNotificationIn()) {
            z2 = true;
        }
        return !z2;
    }

    public final void removeTimeKeeper(String str) {
        NotificationTimeKeeper notificationTimeKeeperRemove;
        if (str == null || (notificationTimeKeeperRemove = this.timeKeeperMap.remove(str)) == null) {
            return;
        }
        notificationTimeKeeperRemove.reset();
    }

    public final void reset() {
        for (Map.Entry<String, NotificationTimeKeeper> entry : this.timeKeeperMap.entrySet()) {
            entry.getKey();
            entry.getValue().reset();
        }
        this.timeKeeperMap.clear();
        FullAodStatusManager.INSTANCE.removeObserver(this);
    }

    public final void saveTempTimeKeeperStatus(StatusBarNotification statusBarNotification) {
    }

    public final void updateTimeKeeper(StatusBarNotification sbn, FocusNotificationContent fnc) {
        n.g(sbn, "sbn");
        n.g(fnc, "fnc");
        TimerInfo timerInfoCreateTimerInfo = createTimerInfo(sbn);
        if (timerInfoCreateTimerInfo == null) {
            NotificationTimeKeeper notificationTimeKeeper = this.timeKeeperMap.get(sbn.getKey());
            if (notificationTimeKeeper != null) {
                if (notificationTimeKeeper.getDynamicIslandIn()) {
                    notificationTimeKeeper = null;
                }
                if (notificationTimeKeeper != null) {
                    removeTimeKeeper(sbn.getKey());
                    return;
                }
                return;
            }
            return;
        }
        NotificationTimeKeeper notificationTimeKeeper2 = this.timeKeeperMap.get(sbn.getKey());
        if (notificationTimeKeeper2 == null) {
            Object obj = this.context.get();
            n.f(obj, "get(...)");
            notificationTimeKeeper2 = new NotificationTimeKeeper((Context) obj, this.handler);
            Map<String, NotificationTimeKeeper> map = this.timeKeeperMap;
            String key = sbn.getKey();
            n.f(key, "getKey(...)");
            map.put(key, notificationTimeKeeper2);
        }
        if (n.c(this.timeKeeperMap.get(sbn.getKey()), notificationTimeKeeper2) && !notificationTimeKeeper2.getFocusNotificationIn()) {
            H0.n notificationExtras = parseNotificationExtras(sbn);
            addChronometers(fnc, notificationTimeKeeper2, ((Number) notificationExtras.a()).intValue(), ((Boolean) notificationExtras.b()).booleanValue(), ((Number) notificationExtras.c()).intValue());
        }
        notificationTimeKeeper2.updateInfo(timerInfoCreateTimerInfo);
    }

    public final void updateTimeKeeperForIsland(String str, int i2, Long l2, long j2, Long l3) {
        NotificationTimeKeeper notificationTimeKeeper = this.timeKeeperMap.get(str);
        if (notificationTimeKeeper != null) {
            notificationTimeKeeper.updateInfo(new TimerInfo(i2, l2, j2, l3));
        }
    }

    public static final class TimerInfo {
        private Long timerSystemCurrent;
        private long timerTotal;
        private int timerType;
        private Long timerWhen;

        public TimerInfo(int i2, Long l2, long j2, Long l3) {
            this.timerType = i2;
            this.timerWhen = l2;
            this.timerTotal = j2;
            this.timerSystemCurrent = l3;
        }

        public static /* synthetic */ TimerInfo copy$default(TimerInfo timerInfo, int i2, Long l2, long j2, Long l3, int i3, Object obj) {
            if ((i3 & 1) != 0) {
                i2 = timerInfo.timerType;
            }
            if ((i3 & 2) != 0) {
                l2 = timerInfo.timerWhen;
            }
            Long l4 = l2;
            if ((i3 & 4) != 0) {
                j2 = timerInfo.timerTotal;
            }
            long j3 = j2;
            if ((i3 & 8) != 0) {
                l3 = timerInfo.timerSystemCurrent;
            }
            return timerInfo.copy(i2, l4, j3, l3);
        }

        public final int component1() {
            return this.timerType;
        }

        public final Long component2() {
            return this.timerWhen;
        }

        public final long component3() {
            return this.timerTotal;
        }

        public final Long component4() {
            return this.timerSystemCurrent;
        }

        public final TimerInfo copy(int i2, Long l2, long j2, Long l3) {
            return new TimerInfo(i2, l2, j2, l3);
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof TimerInfo)) {
                return false;
            }
            TimerInfo timerInfo = (TimerInfo) obj;
            return this.timerType == timerInfo.timerType && n.c(this.timerWhen, timerInfo.timerWhen) && this.timerTotal == timerInfo.timerTotal && n.c(this.timerSystemCurrent, timerInfo.timerSystemCurrent);
        }

        public final Long getTimerSystemCurrent() {
            return this.timerSystemCurrent;
        }

        public final long getTimerTotal() {
            return this.timerTotal;
        }

        public final int getTimerType() {
            return this.timerType;
        }

        public final Long getTimerWhen() {
            return this.timerWhen;
        }

        public int hashCode() {
            int iHashCode = Integer.hashCode(this.timerType) * 31;
            Long l2 = this.timerWhen;
            int iHashCode2 = (((iHashCode + (l2 == null ? 0 : l2.hashCode())) * 31) + Long.hashCode(this.timerTotal)) * 31;
            Long l3 = this.timerSystemCurrent;
            return iHashCode2 + (l3 != null ? l3.hashCode() : 0);
        }

        public final void setTimerSystemCurrent(Long l2) {
            this.timerSystemCurrent = l2;
        }

        public final void setTimerTotal(long j2) {
            this.timerTotal = j2;
        }

        public final void setTimerType(int i2) {
            this.timerType = i2;
        }

        public final void setTimerWhen(Long l2) {
            this.timerWhen = l2;
        }

        public String toString() {
            return "TimerInfo(timerType=" + this.timerType + ", timerWhen=" + this.timerWhen + ", timerTotal=" + this.timerTotal + ", timerSystemCurrent=" + this.timerSystemCurrent + ")";
        }

        public /* synthetic */ TimerInfo(int i2, Long l2, long j2, Long l3, int i3, DefaultConstructorMarker defaultConstructorMarker) {
            this((i3 & 1) != 0 ? 0 : i2, l2, (i3 & 4) != 0 ? 0L : j2, l3);
        }
    }
}
