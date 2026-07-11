package miui.systemui.miplay.tracker;

import android.content.Context;
import android.util.Log;
import com.android.systemui.FoldFieldManager;
import com.android.systemui.MiPlayDetailViewModel;
import com.android.systemui.MiPlayExtentionsKt;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import m0.i;
import miui.os.Build;
import miui.systemui.util.CommonUtils;
import miui.systemui.util.SmartDeviceUtils;
import systemui.plugin.eventtracking.events.MiPlayBluetoothConnectEvent;
import systemui.plugin.eventtracking.events.MiPlayClickEvents;
import systemui.plugin.eventtracking.events.MiPlayConnectPeerVoiceTimeEvent;
import systemui.plugin.eventtracking.events.MiPlayControlCenterExposedEvent;
import systemui.plugin.eventtracking.events.MiPlayDeviceExposeEvent;
import systemui.plugin.eventtracking.events.MiPlayEntertainmentDAU;
import systemui.plugin.eventtracking.events.MiPlayEventsKt;
import systemui.plugin.eventtracking.events.MiPlayExposeEvent;
import systemui.plugin.eventtracking.events.MiPlayFindDeviceListEvent;
import systemui.plugin.eventtracking.events.MiPlaySelectDeviceEvents;
import systemui.plugin.eventtracking.trackers.BaseEventTracker;
import systemui.plugin.eventtracking.utils.EventsUtils;

/* JADX INFO: loaded from: classes3.dex */
public class MiPlayEventTracker {
    private static final int DEVICE_TYPE_CAR_FOR_PHONE = 10;
    private static final int DEVICE_TYPE_CAR_FOR_TABLET = 9;
    private static final int DEVICE_TYPE_OTHERS = -1;
    private static final int DEVICE_TYPE_PC_FOR_PHONE = 7;
    private static final int DEVICE_TYPE_PC_FOR_TABLET = 8;
    private static final int DEVICE_TYPE_SCREEN_SPEAKER_FOR_PHONE = 2;
    private static final int DEVICE_TYPE_SCREEN_SPEAKER_FOR_TABLET = 5;
    private static final int DEVICE_TYPE_SPEAKER_FOR_PHONE = 0;
    private static final int DEVICE_TYPE_SPEAKER_FOR_TABLET = 3;
    private static final int DEVICE_TYPE_TV_FOR_PHONE = 1;
    private static final int DEVICE_TYPE_TV_FOR_TABLET = 4;
    private static String EVENT_MI_PLAY_EXPOSE_TIP = "178.1.1.1.18767";
    private static final String MEDIA_TYPE_AUDIO = "audio";
    private static final String MEDIA_TYPE_VIDEO = "video";
    private static String ORIENTATION_LANDSCAPE = "横屏";
    private static String ORIENTATION_PORTRAIT = "竖屏";
    private static String STYLE_COLLAPSED = "2*2";
    private static String STYLE_EXPANDED = "4*1";
    private static final String TAG = "MiPlayEventTracker";

    private MiPlayEventTracker() {
    }

    private static String getDeviceType(i iVar) {
        return iVar.k().getType() == 2 ? MiPlayEventsKt.DEVICE_TYPE_BT : iVar.k().getLyraId() != null ? MiPlayEventsKt.DEVICE_TYPE_LYRA : MiPlayEventsKt.DEVICE_TYPE_IDM;
    }

    private static List<Map<String, String>> getFindDeviceMessage(LinkedHashMap<i, Long> linkedHashMap, Context context) {
        LinkedHashMap linkedHashMap2 = new LinkedHashMap(linkedHashMap);
        ArrayList arrayList = new ArrayList();
        String strValueOf = String.valueOf(CommonUtils.getVersionCode(context, SmartDeviceUtils.MI_LINK_PACKAGE_NAME));
        int i2 = 0;
        for (i iVar : linkedHashMap2.keySet()) {
            i2++;
            HashMap map = new HashMap();
            Long l2 = (Long) linkedHashMap2.get(iVar);
            long jLongValue = l2 != null ? l2.longValue() - MiPlayDetailViewModel.INSTANCE.getStartScanDeviceTime() : 0L;
            String strIsCacheFound = MiPlayExtentionsKt.isCacheFound(iVar);
            map.put(MiPlayEventsKt.EVENT_KEY_FIND_DEVICE_FIND_TIME, String.valueOf(jLongValue));
            map.put(MiPlayEventsKt.EVENT_KEY_FIND_DEVICE_FIND_RANK, String.valueOf(i2));
            map.put("protocol", getDeviceType(iVar));
            map.put(MiPlayEventsKt.EVENT_KEY_FIND_DEVICE_CONNECT_TYPE, String.valueOf(getPerVoiceDeviceType(MiPlayExtentionsKt.getMiPlayDeviceType(iVar.k()))));
            map.put(MiPlayEventsKt.EVENT_KEY_FIND_DEVICE_DEVICE_MODEL, Build.DEVICE);
            map.put("peer_model", iVar.k().getPeerModel());
            map.put("peer_rom_version", iVar.k().getPeerRomVersion());
            map.put(MiPlayEventsKt.EVENT_KEY_FIND_DEVICE_IF_CACHE, strIsCacheFound);
            map.put(MiPlayEventsKt.EVENT_KEY_FIND_DEVICE_MILINK_VERSION_CODE, strValueOf);
            arrayList.add(map);
        }
        return arrayList;
    }

    private static String getMediaType(int i2) {
        return i2 == 1 ? "video" : "audio";
    }

    private static int getPerVoiceDeviceType(int i2) {
        if (Build.IS_TABLET) {
            if (i2 == 2) {
                return 4;
            }
            if (i2 == 3) {
                return 8;
            }
            if (i2 == 4) {
                return 3;
            }
            if (i2 != 5) {
                return i2 != 16 ? -1 : 5;
            }
            return 9;
        }
        if (i2 == 2) {
            return 1;
        }
        if (i2 == 3) {
            return 7;
        }
        if (i2 == 4) {
            return 0;
        }
        if (i2 != 5) {
            return i2 != 16 ? -1 : 2;
        }
        return 10;
    }

    private static String getPhoneType() {
        return FoldFieldManager.INSTANCE.getPhoneType();
    }

    private static String getScreenType() {
        return FoldFieldManager.INSTANCE.getScreenType();
    }

    public static void printJavaStack() {
        Log.d(TAG, Log.getStackTraceString(new Throwable()));
    }

    public static void trackBluetoothConnect(long j2, boolean z2, String str, String str2) {
        BaseEventTracker.get().track(new MiPlayBluetoothConnectEvent(j2, z2 ? MiPlayEventsKt.VALUE_RESULT_SUCCESS : MiPlayEventsKt.VALUE_RESULT_FAIL, str, str2));
    }

    public static void trackClick(String str, String str2, String str3) {
        MiPlayDetailViewModel miPlayDetailViewModel = MiPlayDetailViewModel.INSTANCE;
        BaseEventTracker.get().track(new MiPlayClickEvents(str, str2, str3, miPlayDetailViewModel.hasMediaData(), miPlayDetailViewModel.getMediaType(), miPlayDetailViewModel.getSourcePackage(), miPlayDetailViewModel.getMIsCasting().getValue().booleanValue(), getPhoneType(), getScreenType()));
    }

    public static void trackDeviceExpose(boolean z2, int i2, String str, boolean z3, int i3, String str2) {
        BaseEventTracker.Companion companion = BaseEventTracker.get();
        MiPlayDetailViewModel miPlayDetailViewModel = MiPlayDetailViewModel.INSTANCE;
        companion.track(new MiPlayDeviceExposeEvent(z2, i2, str, z3, i3, str2, miPlayDetailViewModel.hasMediaData(), miPlayDetailViewModel.getMediaType(), miPlayDetailViewModel.getSourcePackage(), getPhoneType(), getScreenType()));
    }

    public static void trackExpose(String str, String str2) {
        BaseEventTracker.Companion companion = BaseEventTracker.get();
        MiPlayDetailViewModel miPlayDetailViewModel = MiPlayDetailViewModel.INSTANCE;
        companion.track(new MiPlayExposeEvent(str, str2, miPlayDetailViewModel.hasMediaData(), miPlayDetailViewModel.getMediaType(), miPlayDetailViewModel.getSourcePackage(), getPhoneType(), getScreenType()));
    }

    public static void trackFindDeviceList(LinkedHashMap<i, Long> linkedHashMap, Context context) {
        if (linkedHashMap.isEmpty()) {
            return;
        }
        BaseEventTracker.get().track(new MiPlayFindDeviceListEvent(getFindDeviceMessage(linkedHashMap, context)));
    }

    public static void trackMiPlayExpose(int i2, boolean z2) {
        BaseEventTracker.Companion companion = BaseEventTracker.get();
        EventsUtils eventsUtils = EventsUtils.INSTANCE;
        String getDate = eventsUtils.getGetDate();
        String str = i2 == 1 ? ORIENTATION_PORTRAIT : ORIENTATION_LANDSCAPE;
        String str2 = z2 ? STYLE_COLLAPSED : STYLE_EXPANDED;
        String versionName = eventsUtils.getVersionName();
        String str3 = EVENT_MI_PLAY_EXPOSE_TIP;
        MiPlayDetailViewModel miPlayDetailViewModel = MiPlayDetailViewModel.INSTANCE;
        companion.track(new MiPlayControlCenterExposedEvent(getDate, str, str2, versionName, str3, miPlayDetailViewModel.hasMediaData(), miPlayDetailViewModel.getMediaType(), miPlayDetailViewModel.getSourcePackage(), getPhoneType(), getScreenType()));
    }

    public static void trackPeerVoiceTime(long j2, int i2, int i3, String str, String str2) {
        BaseEventTracker.get().track(new MiPlayConnectPeerVoiceTimeEvent(j2, getMediaType(i2), String.valueOf(getPerVoiceDeviceType(i3)), str, str2));
    }

    public static void trackSelectDevice(boolean z2, String str, String str2, int i2, String str3, boolean z3, boolean z4, boolean z5, String str4, String str5, String str6, String str7, String str8, String str9, String str10, String str11) {
        BaseEventTracker.Companion companion = BaseEventTracker.get();
        MiPlayDetailViewModel miPlayDetailViewModel = MiPlayDetailViewModel.INSTANCE;
        companion.track(new MiPlaySelectDeviceEvents(z2, str, str2, i2, str3, str5, z3, z4, z5, str4, miPlayDetailViewModel.hasMediaData(), str6, str7, str8, str9, str10, str11, miPlayDetailViewModel.getMediaType(), miPlayDetailViewModel.getSourcePackage(), getPhoneType(), getScreenType()));
        BaseEventTracker.get().track(new MiPlayEntertainmentDAU(MiPlayEventsKt.EVENT_MIPLAY_SYSTEMUI_SELECT_DEVICE, z2, str, i2, str3, str5, z3, z4, z5, str4, miPlayDetailViewModel.hasMediaData(), str6, str7, str8, str9, str10, str11, getPhoneType(), getScreenType()));
    }
}
