package systemui.plugin.eventtracking;

import H0.d;
import H0.e;
import android.content.Context;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.SystemProperties;
import android.util.Log;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.n;
import miui.os.Build;
import systemui.plugin.eventtracking.dagger.qualifiers.EventTracking;
import systemui.plugin.eventtracking.events.BaseDesktopModeEvents;
import systemui.plugin.eventtracking.events.BaseMiPlayEvent;
import systemui.plugin.eventtracking.events.DeviceCenterEvent;
import systemui.plugin.eventtracking.events.DynamicIslandEvent;
import systemui.plugin.eventtracking.utils.EventsUtils;

/* JADX INFO: loaded from: classes5.dex */
public final class EventTracker {
    public static final String CAUSE_EXTREMUM_TYPE_200 = "静音关且超大音量200%";
    public static final String CAUSE_EXTREMUM_TYPE_300 = "静音关且超大音量300%";
    public static final String CAUSE_EXTREMUM_TYPE_MUTE_OFF = "静音关";
    public static final String CAUSE_EXTREMUM_TYPE_MUTE_ON = "静音开";
    public static final String CAUSE_EXTREMUM_TYPE_NO = "未触发";
    public static final Companion Companion = new Companion(null);
    private static final boolean DEBUG;
    private static String MODEL_TYPE = null;
    private static final String MODEL_TYPE_PHONE = "手机";
    private static final String MODEL_TYPE_TABLE = "平板";
    private static String PHONE_TYPE = null;
    private static final String PHONE_TYPE_COMMON = "直板";
    private static final String PHONE_TYPE_FLIP = "flip";
    private static final String PHONE_TYPE_FOLD = "fold";
    private static final String SCREEN_TYPE_COMMON = "nothing";
    private static final String SCREEN_TYPE_INNER = "内屏";
    private static final String SCREEN_TYPE_OUTER = "外屏";
    private static final int SUPER_VOLUME_INDEX_ADD;
    public static final String SUPER_VOLUME_THREE = "超大音量300%";
    public static final String SUPER_VOLUME_TWO = "超大音量200%";
    public static final String TAG = "plugin-tracker";
    private final Handler bgHandler;
    private final HandlerThread bgThread;
    private final d desktopTracker$delegate;
    private final d deviceCenterTracker$delegate;
    private final d dynamicIslandTracker$delegate;
    private final d miPlayTracker$delegate;
    private final Tracker systemUITracker;

    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public static /* synthetic */ void getSUPER_VOLUME_INDEX_ADD$annotations() {
        }

        public final String causeExtremumType(int i2, int i3, int i4) {
            return i3 != 0 ? i3 != 10 ? i4 == i2 / 1000 ? EventTracker.SUPER_VOLUME_THREE : EventTracker.CAUSE_EXTREMUM_TYPE_NO : i4 == i2 / 1000 ? EventTracker.SUPER_VOLUME_TWO : EventTracker.CAUSE_EXTREMUM_TYPE_NO : EventTracker.CAUSE_EXTREMUM_TYPE_NO;
        }

        public final String causeExtremumTypeInPanel(boolean z2, int i2, int i3, int i4, int i5) {
            return (i5 == 0 && i3 == 2) ? EventTracker.CAUSE_EXTREMUM_TYPE_MUTE_ON : (getSUPER_VOLUME_INDEX_ADD() == 10 && (i4 == 0 || z2) && i5 == i2 && i3 == 2) ? EventTracker.CAUSE_EXTREMUM_TYPE_200 : (getSUPER_VOLUME_INDEX_ADD() == 20 && (i4 == 0 || z2) && i5 == i2 && i3 == 2) ? EventTracker.CAUSE_EXTREMUM_TYPE_300 : (i3 == 2 && (i4 == 0 || z2)) ? EventTracker.CAUSE_EXTREMUM_TYPE_MUTE_OFF : (getSUPER_VOLUME_INDEX_ADD() == 10 && i5 == i2) ? EventTracker.SUPER_VOLUME_TWO : (getSUPER_VOLUME_INDEX_ADD() == 20 && i5 == i2) ? EventTracker.SUPER_VOLUME_THREE : EventTracker.CAUSE_EXTREMUM_TYPE_NO;
        }

        public final boolean getDEBUG() {
            return EventTracker.DEBUG;
        }

        public final String getMODEL_TYPE() {
            return EventTracker.MODEL_TYPE;
        }

        public final String getPHONE_TYPE() {
            return EventTracker.PHONE_TYPE;
        }

        public final int getSUPER_VOLUME_INDEX_ADD() {
            return EventTracker.SUPER_VOLUME_INDEX_ADD;
        }

        public final String getScreenType(Context context) {
            n.g(context, "context");
            if (EventsUtils.isFlipDevice()) {
                if (!EventsUtils.isTinyScreen(context)) {
                    return EventTracker.SCREEN_TYPE_INNER;
                }
            } else {
                if (!EventsUtils.INSTANCE.getIS_FOLD()) {
                    return EventTracker.SCREEN_TYPE_COMMON;
                }
                if (!EventsUtils.isFoldDeviceInside()) {
                    return EventTracker.SCREEN_TYPE_INNER;
                }
            }
            return EventTracker.SCREEN_TYPE_OUTER;
        }

        public final void setMODEL_TYPE(String str) {
            n.g(str, "<set-?>");
            EventTracker.MODEL_TYPE = str;
        }

        public final void setPHONE_TYPE(String str) {
            n.g(str, "<set-?>");
            EventTracker.PHONE_TYPE = str;
        }

        private Companion() {
        }
    }

    static {
        DEBUG = SystemProperties.getBoolean("debug.sysui", false) || SystemProperties.getBoolean("debug.sysui.event", false);
        EventsUtils eventsUtils = EventsUtils.INSTANCE;
        MODEL_TYPE = eventsUtils.getIS_TABLET() ? MODEL_TYPE_TABLE : MODEL_TYPE_PHONE;
        PHONE_TYPE = EventsUtils.isFlipDevice() ? "flip" : eventsUtils.getIS_FOLD() ? "fold" : "直板";
        SUPER_VOLUME_INDEX_ADD = SystemProperties.getInt("ro.vendor.audio.volume_super_index_add", 0);
    }

    public EventTracker(@EventTracking Context ctx) {
        n.g(ctx, "ctx");
        HandlerThread handlerThread = new HandlerThread(TAG);
        this.bgThread = handlerThread;
        this.miPlayTracker$delegate = e.b(new EventTracker$miPlayTracker$2(ctx));
        this.deviceCenterTracker$delegate = e.b(new EventTracker$deviceCenterTracker$2(ctx));
        this.desktopTracker$delegate = e.b(new EventTracker$desktopTracker$2(ctx));
        this.dynamicIslandTracker$delegate = e.b(new EventTracker$dynamicIslandTracker$2(ctx));
        handlerThread.start();
        this.bgHandler = new Handler(handlerThread.getLooper());
        this.systemUITracker = EventTrackerFactory.Companion.createSystemUITracker(ctx);
    }

    public static final String causeExtremumType(int i2, int i3, int i4) {
        return Companion.causeExtremumType(i2, i3, i4);
    }

    public static final String causeExtremumTypeInPanel(boolean z2, int i2, int i3, int i4, int i5) {
        return Companion.causeExtremumTypeInPanel(z2, i2, i3, i4, i5);
    }

    private final Tracker getDesktopTracker() {
        return (Tracker) this.desktopTracker$delegate.getValue();
    }

    private final Tracker getDeviceCenterTracker() {
        return (Tracker) this.deviceCenterTracker$delegate.getValue();
    }

    private final Tracker getDynamicIslandTracker() {
        return (Tracker) this.dynamicIslandTracker$delegate.getValue();
    }

    private final Tracker getMiPlayTracker() {
        return (Tracker) this.miPlayTracker$delegate.getValue();
    }

    public static final int getSUPER_VOLUME_INDEX_ADD() {
        return Companion.getSUPER_VOLUME_INDEX_ADD();
    }

    public static final String getScreenType(Context context) {
        return Companion.getScreenType(context);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void trackSupportGlobal$lambda$2(Object event, EventTracker this$0) {
        String strKey;
        Object obj;
        n.g(event, "$event");
        n.g(this$0, "this$0");
        Class<?> cls = event.getClass();
        EventID eventID = (EventID) cls.getAnnotation(EventID.class);
        if (eventID == null) {
            return;
        }
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        Field[] declaredFields = cls.getDeclaredFields();
        n.f(declaredFields, "getDeclaredFields(...)");
        for (Field field : declaredFields) {
            field.setAccessible(true);
            EventKey eventKey = (EventKey) field.getAnnotation(EventKey.class);
            if (eventKey != null && (strKey = eventKey.key()) != null && (obj = field.get(event)) != null) {
                n.d(obj);
                linkedHashMap.put(strKey, obj);
            }
        }
        if (DEBUG) {
            ArrayList arrayList = new ArrayList(linkedHashMap.size());
            Iterator<Map.Entry<String, ? extends Object>> it = linkedHashMap.entrySet().iterator();
            while (it.hasNext()) {
                arrayList.add(it.next());
            }
            Log.d(TAG, "track " + eventID + " " + arrayList);
        }
        if (event instanceof BaseMiPlayEvent) {
            this$0.getMiPlayTracker().track(eventID.id(), linkedHashMap);
            return;
        }
        if (event instanceof DeviceCenterEvent) {
            this$0.getDeviceCenterTracker().track(eventID.id(), linkedHashMap);
            return;
        }
        if (event instanceof BaseDesktopModeEvents) {
            this$0.getDesktopTracker().track(eventID.id(), linkedHashMap);
        } else if (event instanceof DynamicIslandEvent) {
            this$0.getDynamicIslandTracker().track(eventID.id(), linkedHashMap);
        } else {
            this$0.systemUITracker.track(eventID.id(), linkedHashMap);
        }
    }

    public final void destroy() {
        this.bgThread.quitSafely();
    }

    public final void track(Object event) {
        n.g(event, "event");
        if (Build.IS_INTERNATIONAL_BUILD) {
            return;
        }
        trackSupportGlobal(event);
    }

    public final void trackSupportGlobal(final Object event) {
        n.g(event, "event");
        if (((EventID) event.getClass().getAnnotation(EventID.class)) == null) {
            return;
        }
        this.bgHandler.post(new Runnable() { // from class: systemui.plugin.eventtracking.a
            @Override // java.lang.Runnable
            public final void run() {
                EventTracker.trackSupportGlobal$lambda$2(event, this);
            }
        });
    }
}
