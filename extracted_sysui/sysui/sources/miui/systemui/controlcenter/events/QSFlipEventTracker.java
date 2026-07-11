package miui.systemui.controlcenter.events;

import I0.m;
import I0.u;
import com.xiaomi.onetrack.util.aa;
import f1.o;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.n;
import systemui.plugin.eventtracking.trackers.BaseEventTracker;

/* JADX INFO: loaded from: classes.dex */
public final class QSFlipEventTracker {
    public static final Companion Companion = new Companion(null);
    private static final String QS_FLIP_IS_EDITED = "是";
    private static final String QS_FLIP_NOT_EDITED = "否";
    private static final String TILE_TYPE_AIRPLANE_DISPLAY_NAME = "飞行模式";
    private static final String TILE_TYPE_AUTO_BRIGHTNESS_DISPLAY_NAME = "自动亮度";
    private static final String TILE_TYPE_BATTERY_SAVER_DISPLAY_NAME = "省电模式";
    private static final String TILE_TYPE_BT_DISPLAY_NAME = "蓝牙";
    private static final String TILE_TYPE_FLASHLIGHT_DISPLAY_NAME = "手电筒";
    private static final String TILE_TYPE_GPS_DISPLAY_NAME = "GPS";
    private static final String TILE_TYPE_HOTSPOT_DISPLAY_NAME = "热点";
    private static final String TILE_TYPE_MUTE_DISPLAY_NAME = "静音";
    private static final String TILE_TYPE_NFC_DISPLAY_NAME = "NFC";
    private static final String TILE_TYPE_NIGHT_DISPLAY_NAME = "深色模式";
    private static final String TILE_TYPE_PAPER_MODE_DISPLAY_NAME = "护眼模式";
    private static final String TILE_TYPE_QUIET_MODE_DISPLAY_NAME = "勿扰模式";
    private static final String TILE_TYPE_ROTATION_DISPLAY_NAME = "方向锁定";
    private static final String TILE_TYPE_SCREENSHOT_DISPLAY_NAME = "截屏";
    private static final String TILE_TYPE_SCREEN_LOCK_DISPLAY_NAME = "锁屏";
    private static final String TILE_TYPE_SCREEN_RECORD_DISPLAY_NAME = "屏幕录制";
    private static final String TILE_TYPE_VIBRATE_DISPLAY_NAME = "振动";

    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
        /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
        /* JADX WARN: Removed duplicated region for block: B:71:0x00e3 A[RETURN, SYNTHETIC] */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        private final java.lang.String getDisplayName(java.lang.String r1) {
            /*
                Method dump skipped, instruction units count: 304
                To view this dump add '--comments-level debug' option
            */
            throw new UnsupportedOperationException("Method not decompiled: miui.systemui.controlcenter.events.QSFlipEventTracker.Companion.getDisplayName(java.lang.String):java.lang.String");
        }

        private final List<Map<String, String>> getMessage(String str) {
            ArrayList arrayList = new ArrayList();
            int i2 = 0;
            for (Object obj : u.m0(o.T(str, new String[]{aa.f3429b}, false, 0, 6, null))) {
                int i3 = i2 + 1;
                if (i2 < 0) {
                    m.n();
                }
                String str2 = (String) obj;
                HashMap map = new HashMap();
                map.put(QsFlipEventsKt.EVENT_KEY_FLIP_QS_NAME, str2);
                map.put(QsFlipEventsKt.EVENT_KEY_FLIP_QS_DISPLAY_NAME, QSFlipEventTracker.Companion.getDisplayName(str2));
                map.put(QsFlipEventsKt.EVENT_KEY_FLIP_QS_INDEX, String.valueOf(i3));
                arrayList.add(map);
                i2 = i3;
            }
            return arrayList;
        }

        public final void trackQsFlipEnterEvent() {
            BaseEventTracker.Companion.get().track(new QsFlipEnterEvent(null, 1, null));
        }

        public final void trackQsFlipQuitEvent(boolean z2, String beforeEditSpec, String afterEditSpec) {
            n.g(beforeEditSpec, "beforeEditSpec");
            n.g(afterEditSpec, "afterEditSpec");
            BaseEventTracker.Companion.get().track(z2 ? new QsFlipQuitEvent("是", getMessage(beforeEditSpec), getMessage(afterEditSpec), null, 8, null) : new QsFlipQuitNotSaveEvent("否", null, 2, null));
        }

        private Companion() {
        }
    }
}
